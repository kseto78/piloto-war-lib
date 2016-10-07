package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ServidoresJPA;
import es.mpr.plataformamensajeria.model.views.ViewPlanificacionJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de planificaciones a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioPlanificacionImpl implements ServicioPlanificacion{

	private IPaginationJPADAO jpa;
	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
	/*
	 * Integer tipo,Integer servidorId,Integer servicioId, String lunes, String marter,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde
	 */
	private static final String VALIDAR_PLANIFICACION_OPTIMA = "{call VALIDARPLANIFICACION(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String VALIDAR_PLANIFICACION_SERVIDOR = "{call VALIDARPLANIFICACIONSERVIDOR(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String CREAR_JOB_POR_PLANIFICACION = "{call crearJobporPlanificacion(?,?)}";
	private static final String MODIFICAR_JOB_POR_PLANIFICACION = "{call modificarJobporPlanificacion(?,?)}";
	private static final String ELIMINAR_JOB_POR_PLANIFICACION = "{call eliminarJobporPlanificacion(?,?)}";
	private static final String VALIDAR_PLANIFICACION_OPTIMA_ORGANISMO = "{call VALIDARPLANIFICACIONORGANISMOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	/**
	/**
	 * 
	 * @return Objeto BaseJPADao
	 */
	public IPaginationJPADAO getJpa() {
		return jpa;
	}

	/**
	 * Establece la propiedad jpa
	 * 
	 * @param jpa
	 */
	public void setJpa(IPaginationJPADAO jpa) {
		this.jpa = jpa;
	}

	@Override
	public List<PlanificacionBean> getPlanificaciones(PlanificacionBean criterio)
			throws BusinessException {
		List<PlanificacionJPA> listJPA = null;
		
		try {
			
			if(criterio== null /*|| criterio.getNombre()== null || criterio.getNombre().equals("")*/){
							
				ServidoresJPA criterioJPA = new ServidoresJPA();
				//criterioJPA.setServidorId(criterio.getServidorId());
				//criterioJPA.setNombre(criterio.getNombre());
				
				listJPA= jpa.readAll(0,0,criterioJPA);
			}else{
				String[] param = new String[]{"%"+criterio.getCreadoPor().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectPlanificacionJPA",param);
			}
			
			List<PlanificacionBean> result = getListPlanificacionBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<PlanificacionBean> getPlanificaciones(int start, int size,
			String order, String columnSort, PlanificacionBean criterio)
			throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
	
		try {
			
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("1","Aplicacion");
			columns.put("2","Servicio");
			
			if (columnSort==null)
				columnSort = "1"; //Id
			
			String column = columns.get(columnSort);
			if (column==null)
				column = "Aplicacion";
			
			boolean aplicacionId=false;
			boolean servicioId=false;
			boolean lunes=false,martes=false,miercoles=false,jueves=false,viernes=false,sabado=false,domingo=false;
			boolean inicioDesde=false,inicioHasta=false,finDesde=false,finHasta=false;
			boolean canalId = false;
			boolean servidorId=false;
			
			//Lista parcial
			StringBuffer querySbf = new StringBuffer("FROM ViewPlanificacionJPA p WHERE 1=1");
			String  querySelect= "SELECT p ";
			String queryCount = "SELECT count(p)";

			if(criterio!=null&&criterio.getAplicacionId()!=null&&criterio.getAplicacionId().intValue()!=0){
				querySbf.append(" AND to_char(p.aplicacionId)=:aplicacionId");
				aplicacionId=true;
			}
			if(criterio!=null&&criterio.getServicioId()!=null&&criterio.getServicioId().intValue()!=0){
					querySbf.append(" AND to_char(p.servicioId)=:servicioId");
					servicioId=true;
			}
			if(criterio!=null&&criterio.getLunes()!=null&&criterio.getLunes().length()>0){
					querySbf.append(" AND to_char(p.lunes)=:lunes");
					lunes=true;
			}			
			if(criterio!=null&&criterio.getMartes()!=null&&criterio.getMartes().length()>0){
				querySbf.append(" AND to_char(p.martes)=:martes");
				martes=true;
			}			
			if(criterio!=null&&criterio.getMiercoles()!=null&&criterio.getMiercoles().length()>0){
				querySbf.append(" AND to_char(p.miercoles)=:miercoles");
				miercoles=true;
			}
			if(criterio!=null&&criterio.getJueves()!=null&&criterio.getJueves().length()>0){
				querySbf.append(" AND to_char(p.jueves)=:jueves");
				jueves=true;
			}	
			if(criterio!=null&&criterio.getViernes()!=null&&criterio.getViernes().length()>0){
				querySbf.append(" AND to_char(p.viernes)=:viernes");
				viernes=true;
			}	
			if(criterio!=null&&criterio.getSabado()!=null&&criterio.getSabado().length()>0){
				querySbf.append(" AND to_char(p.sabado)=:sabado");
				sabado=true;
			}	
			if(criterio!=null&&criterio.getDomingo()!=null&&criterio.getDomingo().length()>0){
				querySbf.append(" AND to_char(p.domingo)=:domingo");
				domingo=true;
			}
			
			if(criterio!=null&&(!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesde())||!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHasta()))){
				querySbf.append(" AND ");
				if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHasta()))
				{	
					querySbf.append(" (to_timestamp(decode(p.horaDesde, '24:00', '23:59', p.horaDesde),'HH24:Mi')>=to_timestamp(:horaDesde,'HH24:Mi') AND to_timestamp(p.horaDesde,'HH24:Mi')<=to_timestamp(:horaHasta,'HH24:Mi'))");
					inicioDesde=true;
					inicioHasta=true;
				}else if(criterio!=null&&PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHasta())){
					querySbf.append(" to_timestamp(decode(p.horaDesde, '24:00', '23:59', p.horaHasta),'HH24:Mi')<=to_timestamp(:horaHasta,'HH24:Mi')");
					inicioHasta=true;
				}else if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHasta())){
					querySbf.append(" to_timestamp(decode(p.horaDesde, '24:00', '23:59', p.horaDesde),'HH24:Mi')>=to_timestamp(:horaDesde,'HH24:Mi')");
					inicioDesde=true;
				}
			}
			if(criterio!=null&&(!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesdeFin())||!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHastaFin()))){
				querySbf.append(" AND ");
				if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesdeFin())&&!PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHastaFin()))
				{
					querySbf.append(" (to_timestamp(decode(p.horaHasta, '24:00', '23:59', p.horaHasta),'HH24:Mi')>=to_timestamp(:horaDesdeFin,'HH24:Mi') AND to_timestamp(decode(p.horaHasta, '24:00', '23:59', p.horaHasta),'HH24:Mi')<=to_timestamp(:horaHastaFin,'HH24:Mi'))");
					finDesde=true;
					finHasta=true;
				}else if(criterio!=null && PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesdeFin()) && !PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHastaFin()) && !criterio.getHoraHastaFin().equals("24:00")){
					querySbf.append(" to_timestamp(decode(p.horaHasta, '24:00', '23:59', p.horaHasta),'HH24:Mi')<=to_timestamp(:horaHastaFin,'HH24:Mi')");
					finHasta=true;
				}else if(criterio!=null && !PlataformaMensajeriaUtil.isEmpty(criterio.getHoraDesdeFin()) && PlataformaMensajeriaUtil.isEmpty(criterio.getHoraHastaFin())){
					querySbf.append(" to_timestamp(decode(p.horaHasta, '24:00', '23:59', p.horaHasta),'HH24:Mi')>=to_timestamp(:horaDesdeFin,'HH24:Mi')");
					finDesde=true;
				}
			}			
			if(criterio!=null&&criterio.getCanalId()!=null&&criterio.getCanalId().intValue()!=0){
				querySbf.append(" AND to_char(p.canalId)=:canalId");
				canalId=true;
			}
			if(criterio!=null&&criterio.getServidorId()!=null&&criterio.getServidorId().intValue()!=0){
				querySbf.append(" AND to_char(p.servidorId)=:servidorId");
				servidorId=true;
			}
			//CAMBIO 20120111: Que no aparezcan las planificaciones que no tienen servicio asociado
		    	querySbf.append(" AND p.servicioId is not null AND (p.eliminado is null or p.eliminado = 'N') ");
			//listJPA = jpa.executeQuery(namedQuery.toString(), param, start, size);
		    if(column!=null&&!column.isEmpty()&&column.equals("Aplicacion")){
		    	querySbf.append(" ORDER BY p.nombreAplicacion");
		    }else if(column!=null&&!column.isEmpty()&&column.equals("Servicio")){
		    	querySbf.append(" ORDER BY p.nombreServicio");
		    }
			//Orden ascendente o descendente
			if (order==null || order.equals("1")) //ASC
			{
				querySbf.append(" ASC"); 
			}
			else if (order!=null && order.equals("2")) //DESC 
			{
				querySbf.append(" DESC");
			}
			Query query =  em.createQuery(querySelect + querySbf.toString());

			query = setQueryParameters(aplicacionId,servicioId,lunes,martes,miercoles,jueves,viernes,sabado,
					domingo,inicioDesde,inicioHasta,finDesde,finHasta,canalId,servidorId,criterio,query);
			
			//query.setParameter("tipo", TIPO_SERVIDOR);
			if(size>0){
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewPlanificacionJPA>) query.getResultList();
			List<PlanificacionBean> pageList = getListViewPlanificacionBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalPlanificaciones(criterio,em,queryCount+querySbf.toString(),
					aplicacionId,servicioId,lunes,martes,miercoles,jueves,viernes,sabado,
					domingo,inicioDesde,inicioHasta,finDesde,finHasta,canalId,servidorId); 
			
			PaginatedList<PlanificacionBean> result = new PaginatedList<PlanificacionBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			throw new BusinessException(e,"errors.organismo.getOrganismos");			
		}
	}

	private Query setQueryParameters(boolean aplicacionId, boolean servicioId,
			boolean lunes, boolean martes, boolean miercoles, boolean jueves,
			boolean viernes, boolean sabado, boolean domingo,
			boolean inicioDesde, boolean inicioHasta, boolean finDesde,
			boolean finHasta, boolean canalId, boolean servidorId,
			PlanificacionBean criterio, Query query) {
		if(aplicacionId)
			query.setParameter("aplicacionId", criterio.getAplicacionId().toString());
		if(servicioId)
			query.setParameter("servicioId", criterio.getServicioId().toString());
		if(lunes)
			query.setParameter("lunes", "S");			
		if(martes)
			query.setParameter("martes", "S");
		if(miercoles)
			query.setParameter("miercoles","S");
		if(jueves)
			query.setParameter("jueves", "S");
		if(viernes)
			query.setParameter("viernes", "S");
		if(sabado)
			query.setParameter("sabado", "S");
		if(domingo)
			query.setParameter("domingo", "S");
		
		if(inicioDesde){
			query.setParameter("horaDesde", criterio.getHoraDesde().equals("24:00") ? "23:59":criterio.getHoraDesde());
		}
		if(inicioHasta){
			query.setParameter("horaHasta", criterio.getHoraHasta().equals("24:00") ? "23:59":criterio.getHoraHasta());
		}		
		if(finDesde){
			query.setParameter("horaDesdeFin", criterio.getHoraDesdeFin().equals("24:00") ? "23:59":criterio.getHoraDesdeFin());
		}
		if(finHasta){
			query.setParameter("horaHastaFin", criterio.getHoraHastaFin().equals("24:00") ? "23:59":criterio.getHoraHastaFin());
		}
		if(canalId)
			query.setParameter("canalId", criterio.getCanalId().toString());
		if(servidorId)
			query.setParameter("servidorId", criterio.getServidorId().toString());
		return query;
	}

	@Override
	public Integer getTotalPlanificaciones(PlanificacionBean criterio, EntityManager em,String query,
			boolean aplicacionId, boolean servicioId,
			boolean lunes, boolean martes, boolean miercoles, boolean jueves,
			boolean viernes, boolean sabado, boolean domingo,
			boolean inicioDesde, boolean inicioHasta, boolean finDesde,
			boolean finHasta, boolean canalId, boolean servidorId)
			throws BusinessException {
		try
		{

				Query rowCountQuery = em.createQuery(query);
				
				rowCountQuery = setQueryParameters(aplicacionId,servicioId,lunes,martes,miercoles,jueves,viernes,sabado,
						domingo,inicioDesde,inicioHasta,finDesde,finHasta,canalId,servidorId,criterio,rowCountQuery);
				
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
		}
	}

	@Override 
	//@Auditable(operacion=Constants.AUDITORIA_OPERACION_ALTA)
	@Transactional
	public Integer newPlanificacion(PlanificacionBean planificacion) throws BusinessException {
		try{
			PlanificacionJPA planificacionJPA = getPlanificacionJPA(planificacion);
			planificacionJPA.parseDias();
			//servidorJPA.setTipo(TIPO_SERVIDOR);
			planificacionJPA.setPlanificacionId(null);
			if(planificacionJPA!=null&&planificacionJPA.getServicioId()!=null&&planificacionJPA.getServicioId().intValue()==0){
				planificacionJPA.setServicioId(null);
			}
			if(planificacionJPA!=null&&planificacionJPA.getServidorId()!=null&&planificacionJPA.getServidorId().intValue()==0){
				planificacionJPA.setServidorId(null);
			}
			planificacionJPA.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			planificacionJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			jpa.insert(planificacionJPA);
			
			planificacion.setPlanificacionId((Integer)planificacionJPA.getId());
			planificacion.setActivo(planificacionJPA.getActivo());
			planificacion.setFechaCreacion(planificacionJPA.getFechaCreacion());
			planificacion.setCreadoPor(planificacionJPA.getCreadoPor());
			return (Integer)planificacionJPA.getId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


	@Override
	@Transactional
	public void updatePlanificacion(PlanificacionBean planificacion) throws BusinessException {
		PlanificacionJPA planificacionJPA = getPlanificacionJPA(planificacion);
		planificacionJPA.parseDias();
		planificacionJPA.setFechaModificacion(new Date());
		planificacionJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
		planificacionJPA.setFechaCreacion(planificacion.getFechaCreacion());
		try {
			jpa.update(planificacionJPA);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Valida la planifciaci�n para ver si se garantizan los envios
	 */
	public int validaPlanificacionOptima(String planificacionId,Integer tipo,Integer servidorId,Integer servicioId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException {
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		int garantizaEnvios = 0;
		int planId = 0;
		
		try{
			planId = Integer.parseInt(planificacionId);
		}catch(Exception e)
		{
			e.printStackTrace();			
		}
		
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(VALIDAR_PLANIFICACION_OPTIMA);
			call.setInt(1, planId);
			call.setInt(2, tipo); 
			call.setInt(3,(servidorId!=null&&servidorId>0)?servidorId:0);
			call.setInt(4,servicioId);
			call.setString(5,lunes);
			call.setString(6,martes);
			call.setString(7,miercoles);
			call.setString(8,jueves);
			call.setString(9,viernes);
			call.setString(10,sabado);
			call.setString(11,domingo);
			call.setString(12,horaHasta);
			call.setString(13,horaDesde);
			call.registerOutParameter(14, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(14);
			if(obj != null){ 
				garantizaEnvios = obj.intValue();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return garantizaEnvios;
	}
	
	public int validaPlanificacionServidor(String planificacionId, Integer servidorId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException {
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		int garantizaEnvios = 0;
		int planId = 0;
		
		try{
			planId = Integer.parseInt(planificacionId);
		}catch(Exception e)
		{
			e.printStackTrace();			
		}
		
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(VALIDAR_PLANIFICACION_SERVIDOR);
			call.setInt(1, planId);
			call.setInt(2, servidorId);
			call.setString(3,lunes);
			call.setString(4,martes);
			call.setString(5,miercoles);
			call.setString(6,jueves);
			call.setString(7,viernes);
			call.setString(8,sabado);
			call.setString(9,domingo);
			call.setString(10,horaHasta);
			call.setString(11,horaDesde);
			call.registerOutParameter(12, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(12);
			if(obj != null){ 
				garantizaEnvios = obj.intValue();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return garantizaEnvios;
	}
	
	public boolean modificarJobPorPlanificacion(Integer idPlanificacion){
		boolean retorno = false;
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(MODIFICAR_JOB_POR_PLANIFICACION);
			call.setInt(1,idPlanificacion);
			call.registerOutParameter(2, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(2);
			if(obj != null){ 
				retorno = obj.intValue()==1?true:false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return retorno;
	}
	public boolean eliminarJobPorPlanificacion(Integer idPlanificacion){
		boolean retorno = false;
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(ELIMINAR_JOB_POR_PLANIFICACION);
			call.setInt(1,idPlanificacion);
			call.registerOutParameter(2, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(2);
			if(obj != null){ 
				retorno = obj.intValue()==1?true:false;
			}
			
		}catch(Exception e){
			//No Existe la planificaci�n a eliminar
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return retorno;
	}
	public boolean crearJobPorPlanificacion(Integer idPlanificacion){
		boolean retorno = false;
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(CREAR_JOB_POR_PLANIFICACION);
			call.setInt(1,idPlanificacion);
			call.registerOutParameter(2, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(2);
			if(obj != null){ 
				retorno = obj.intValue()==1?true:false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return retorno;
	}
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public PlanificacionBean loadPlanificacion(PlanificacionBean planificacion)	throws BusinessException {
		try {
			ViewPlanificacionJPA planificacionJPA = getViewPlanificacionJPA(planificacion);
			
			return getPlanificacionBean((ViewPlanificacionJPA) jpa.read(planificacionJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public ViewPlanificacionJPA loadViewPlanificacionJPA(PlanificacionBean planificacion)	throws BusinessException {
		try {
			PlanificacionJPA planificacionJPA = getPlanificacionJPA(planificacion);
			
			return (ViewPlanificacionJPA) jpa.read(planificacionJPA);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public PlanificacionJPA loadPlanificacionJPA(PlanificacionBean planificacion)	throws BusinessException {
		try {
			PlanificacionJPA planificacionJPA = getPlanificacionJPA(planificacion);
			
			return (PlanificacionJPA) jpa.read(planificacionJPA);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override

	@Transactional
	//@Auditable(operacion=Constants.AUDITORIA_OPERACION_BAJA)
	public boolean deletePlanificacion(PlanificacionBean planificacion) throws BusinessException {
		boolean sw=true;
		try {
			try {
				PlanificacionJPA planificacionJPA = loadPlanificacionJPA(planificacion);	 
				planificacionJPA.setEliminado("S");
				planificacionJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				planificacionJPA.setFechaModificacion(new Date());
				jpa.update(planificacionJPA);
				this.eliminarJobPorPlanificacion(planificacion.getPlanificacionId());
			}
			catch (DAOException e){
				//throw new BusinessException(e,"errors.organismo.deleteOrganismo");
				sw=false;
			}
			return sw;
			
		}
		catch (Exception e){
			//throw new BusinessException(e,"errors.planificacion.deletePlanificacion");
			sw=false;
		}
		return sw;
	}
	
	@Override

	@Transactional
	//@Auditable(operacion=Constants.AUDITORIA_OPERACION_BAJA)
	public boolean deletePlanificacionOrganismo(PlanificacionBean planificacion, Integer idOrganismo) throws BusinessException {
		boolean sw=true;
		try {
			try {
				PlanificacionJPA planificacionJPA = loadPlanificacionJPA(planificacion);	 
				planificacionJPA.setEliminado("S");
				planificacionJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				planificacionJPA.setFechaModificacion(new Date());
				jpa.update(planificacionJPA);
				this.eliminarJobPorPlanificacion(planificacion.getPlanificacionId());
			}
			catch (DAOException e){
				//throw new BusinessException(e,"errors.organismo.deleteOrganismo");
				sw=false;
			}
			return sw;
			
		}
		catch (Exception e){
			//throw new BusinessException(e,"errors.planificacion.deletePlanificacion");
			sw=false;
		}
		return sw;
	}
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	protected ViewPlanificacionJPA getViewPlanificacionJPA(PlanificacionBean planificacion) throws BusinessException
	{
		ViewPlanificacionJPA planificacionJPA = new ViewPlanificacionJPA();
		try {
			BeanUtils.copyProperties(planificacionJPA,planificacion);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return planificacionJPA;
	}	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	public PlanificacionJPA getPlanificacionJPA(PlanificacionBean planificacion) throws BusinessException
	{
		PlanificacionJPA planificacionJPA = new PlanificacionJPA();
		try {
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			BeanUtils.copyProperties(planificacionJPA,planificacion);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return planificacionJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de PlanificacionJPA a una lista de PlanificacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos PlanificacionBean
	 */
	protected List<PlanificacionBean> getListPlanificacionBean(List<PlanificacionJPA> listJPA) throws BusinessException
	{	
		List<PlanificacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<PlanificacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				PlanificacionJPA planificacionJPA = listJPA.get(indice);
				PlanificacionBean planificacion =  new PlanificacionBean();
			
				try {
					planificacionJPA.reverseParseDias();
					Integer defaultIntegerValue=null;
					IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(intergerConverter, java.lang.Integer.class);
					BeanUtils.copyProperties(planificacion, planificacionJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
				
				result.add(planificacion);
			}
		}
			
		return result;
	}
	/**
	 * <p>Obtenemos un objeto PlanificacionBean a partir de un objeto PlanificacionJPA</p>
	 * 
	 * @param PlanificacionJPA
	 * 
	 * @return objeto PlanificacionBean
	 */
	protected PlanificacionBean getPlanificacionBean(PlanificacionJPA planificacionJPA) throws BusinessException
	{
		PlanificacionBean planificacion = new PlanificacionBean();
		
		try {
			planificacionJPA.reverseParseDias();
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			BeanUtils.copyProperties(planificacion, planificacionJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return planificacion;
	}
	/**
	 * <p>Obtenemos un objeto PlanificacionBean a partir de un objeto PlanificacionJPA</p>
	 * 
	 * @param PlanificacionJPA
	 * 
	 * @return objeto PlanificacionBean
	 */
	protected PlanificacionBean getPlanificacionBean(ViewPlanificacionJPA planificacionJPA) throws BusinessException
	{
		PlanificacionBean planificacion = new PlanificacionBean();
		
		try {
			planificacionJPA.reverseParseDias();
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			BeanUtils.copyProperties(planificacion, planificacionJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return planificacion;
	}
	/**
	 * <p>Convertirmos una lista de ViewPlanificacionJPA a una lista de PlanificacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos PlanificacionBean
	 */
	protected List<PlanificacionBean> getListViewPlanificacionBean(List<ViewPlanificacionJPA> listJPA) throws BusinessException
	{	
		List<PlanificacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<PlanificacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewPlanificacionJPA planificacionJPA = listJPA.get(indice);
				PlanificacionBean planificacion =  new PlanificacionBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					planificacionJPA.reverseParseDias();
					Integer defaultIntegerValue=null;
					IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(intergerConverter, java.lang.Integer.class);
					BeanUtils.copyProperties(planificacion, planificacionJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(planificacion);
			}
		}
			
		return result;
	}
	@Override
	public List<PlanificacionBean> getPlanificacionesByServidorId(Integer servidorId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{servidorId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByServidorIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}
	@Override
	public List<PlanificacionBean> getPlanificacionesByProveedorSMSId(Integer proveedorSMSId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{proveedorSMSId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByProveedorSMSIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}
	
	@Override
	public List<PlanificacionBean> getPlanificacionesByReceptorSMSId(Integer receptorSMSId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{receptorSMSId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByReceptorSMSIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}
	
	@Override
	public List<PlanificacionBean> getPlanificacionesByServidorPushId(Integer servidorPushId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{servidorPushId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByServidorPushIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}

	@Override
	public List<PlanificacionBean> getPlanificacionesByServicioID(
			Integer servicioId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{servicioId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByServicioIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}
	
	@Override
	public List<PlanificacionBean> getPlanificacionesByOrganismoID(
			Integer organismoId) throws BusinessException {
		List<ViewPlanificacionJPA> listJPA = null;
		String[] params = new String[]{organismoId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewPlanificacionByOrganismoIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PlanificacionBean> result = getListViewPlanificacionBean(listJPA);		
		return result;
	}
	
	@Override
	public int validaPlanificacionOptimaOrganismo(String planificacionId, Integer tipo, Integer servidorId, Integer servicioId, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde,
			Integer organismoId) throws BusinessException {
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		int garantizaEnvios = 0;
		int planId = 0;
		
		try{
			planId = Integer.parseInt(planificacionId);
		}catch(Exception e)
		{
			e.printStackTrace();			
		}
		
		try{
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(VALIDAR_PLANIFICACION_OPTIMA_ORGANISMO);
			call.setInt(1, planId);
			call.setInt(2, tipo); 
			call.setInt(3,(servidorId!=null&&servidorId>0)?servidorId:0);
			call.setInt(4,servicioId);
			call.setString(5,lunes);
			call.setString(6,martes);
			call.setString(7,miercoles);
			call.setString(8,jueves);
			call.setString(9,viernes);
			call.setString(10,sabado);
			call.setString(11,domingo);
			call.setString(12,horaHasta);
			call.setString(13,horaDesde);
			call.setInt(14, organismoId);
			call.registerOutParameter(15, OracleTypes.NUMBER);
			call.executeUpdate();
			BigDecimal obj =  (BigDecimal)call.getObject(15);
			if(obj != null){ 
				garantizaEnvios = obj.intValue();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs,call);
		}
		return garantizaEnvios;
	}
}
