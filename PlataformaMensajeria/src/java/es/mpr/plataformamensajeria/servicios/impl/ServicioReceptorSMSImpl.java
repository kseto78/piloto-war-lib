package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ReceptoresSMSJPA;
import es.mpr.plataformamensajeria.model.views.ViewReceptoresSMSJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y busqueda de servidores a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioReceptorSMSImpl implements ServicioReceptorSMS{
	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
	private IPaginationJPADAO jpa;
	private static final Integer TIPO_SERVIDOR= Integer.valueOf(3);
	
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
	public List<ReceptorSMSBean> getReceptoresSMS()
			throws BusinessException {
		List<ViewReceptoresSMSJPA> listJPA = null;
		
		try {
			listJPA= jpa.executeQuery("selectViewReceptorSMS",null);
			List<ReceptorSMSBean> result = getListReceptorSMSBean(listJPA);					
			
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}	
	
	@Override
	public List<ReceptorSMSBean> getReceptoresSMS(ReceptorSMSBean criterio)
			throws BusinessException {
		List<ViewReceptoresSMSJPA> listJPA = null;
		
		try {
			
			if(criterio== null || criterio.getNombre()== null || criterio.getNombre().equals("")){
							
				ViewReceptoresSMSJPA criterioJPA = new ViewReceptoresSMSJPA();
				if(null != criterio){
					criterioJPA.setReceptorSMSId(criterio.getReceptorSMSId());
					criterioJPA.setNombre(criterio.getNombre());
				}
				listJPA= jpa.readAll(0,0,criterioJPA);
			}else{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectReceptoresSMSJPA",param);
			}
			
			List<ReceptorSMSBean> result = getListViewReceptorSMSBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public PaginatedList<ReceptorSMSBean> getReceptoresSMS(int start, int size,
			String order, String columnSort, ReceptorSMSBean criterio)
			throws BusinessException {
		List<ViewReceptoresSMSJPA> listJPA = null;
		
		try {
			
			StringBuffer namedQuery = null;
			
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("1","Nombre");
			columns.put("2","Descripcion");
			columns.put("3","Activo");
			if (columnSort==null)
				columnSort = "1"; //Id
			
			String column = columns.get(columnSort);
			if (column==null)
				column = "Nombre";
			
			 String nombreCriterio = null;
			
				//Determinamos la NamedQuery
				if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals("")){
									
					//Sin criterio
					nombreCriterio="";
					
					namedQuery = new StringBuffer("selectViewReceptorSMSByName_orderby");
									
				}else{
					System.out.println("[CRITERIO] Nombre: " + criterio.getNombre());
					nombreCriterio = criterio.getNombre();
					namedQuery = new StringBuffer("selectViewReceptorSMSByName_orderby");
				}
			
			namedQuery.append(column);
			
			//Orden ascendente o descendente
			if (order==null || order.equals("1")) //ASC
			{
				namedQuery.append("_ASC"); 
			}
			else if (order.equals("2")) //DESC 
			{
				namedQuery.append("_DESC");
			}
			//Lista parcial
			
			//listJPA = jpa.executeQuery(namedQuery.toString(), param, start, size);
			Query query =  em.createNamedQuery(namedQuery.toString());
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
			query.setParameter("tipo", TIPO_SERVIDOR);
			if(size>0){
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewReceptoresSMSJPA>)query.getResultList();
			List<ReceptorSMSBean> pageList = getListViewReceptorSMSBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalReceptoresSMS(criterio,em); 
			
			PaginatedList<ReceptorSMSBean> result = new PaginatedList<ReceptorSMSBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

	@Override
	public Integer getTotalReceptoresSMS(ReceptorSMSBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectViewReceptorSMS_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectViewReceptorSMS_count");
				String nombreCriterio = criterio.getNombre();
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
		}
	}

	@Override
	//@Auditable(operacion=Constants.AUDITORIA_OPERACION_ALTA)
	@Transactional
	public Long newReceptorSMS(ReceptorSMSBean servidor) throws BusinessException {
		try{
			ReceptoresSMSJPA receptorSMS = getReceptoresSMSJPA(servidor);
			receptorSMS.setTipo(TIPO_SERVIDOR);
			receptorSMS.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			receptorSMS.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			//servidorJPA.setPorDefecto(POR_DEFECTO);
			receptorSMS.setReceptorSMSId(null);
			jpa.insert(receptorSMS);
			
			servidor.setReceptorSMSId((Long)receptorSMS.getId());
			servidor.setNombre(receptorSMS.getNombre());
			servidor.setDescripcion(receptorSMS.getDescripcion());
			servidor.setActivo(receptorSMS.getActivo());
			servidor.setFechaCreacion(receptorSMS.getFechaCreacion());
			servidor.setCreadoPor(receptorSMS.getCreadoPor());
			return receptorSMS.getReceptorSMSId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateReceptorSMS(ReceptorSMSBean servidor) throws BusinessException {
		try {
			ReceptoresSMSJPA servidorJPA = getReceptoresSMSJPA(servidor);
			servidorJPA.setFechaModificacion(new Date());
			servidorJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servidorJPA.setFechaCreacion(servidor.getFechaCreacion());
			jpa.update(servidorJPA);
			return;	
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	@Override
	//@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public ReceptorSMSBean loadReceptorSMS(ReceptorSMSBean servidor)	throws BusinessException {
		try {
			ReceptoresSMSJPA servidorJPA = getReceptoresSMSJPA(servidor);
			
			return getReceptorSMSBean((ReceptoresSMSJPA) jpa.read(servidorJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	@Override

	@Transactional
	public void deleteReceptorSMS(ReceptorSMSBean receptor) throws BusinessException {
		try {
			ReceptorSMSBean receptorBBDD = loadReceptorSMS(receptor);
			ReceptoresSMSJPA receptorSMSJPA = getReceptoresSMSJPA(receptorBBDD);
			String[] paramServidor = new String[]{receptorSMSJPA.getReceptorSMSId().toString()};
			
			List<Object> listaPlanificaciones = jpa.executeQuery("selectPlanificacionByServidorIdJPA", paramServidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			for (Object object : listaPlanificaciones) {
				if(object instanceof PlanificacionJPA){
					PlanificacionJPA planificacion = (PlanificacionJPA) object;
					planificacion.setEliminado("S");
					planificacion.setModificadoPor(modificador);
					planificacion.setFechaModificacion(new Date());
					jpa.update(planificacion);
				}
				
			}
			receptorSMSJPA.setModificadoPor(modificador);
			receptorSMSJPA.setFechaModificacion(new Date());
			receptorSMSJPA.setEliminado("S");
			jpa.update(receptorSMSJPA);
			return;
		}catch (Exception e) {
			throw new BusinessException("Error eliminando receptor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	protected ReceptoresSMSJPA getReceptoresSMSJPA(ReceptorSMSBean receptorSMS) throws BusinessException
	{
		ReceptoresSMSJPA receptorSMSJPA = new ReceptoresSMSJPA();
		
		try {
			BeanUtils.copyProperties(receptorSMSJPA,receptorSMS);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		
		return receptorSMSJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de ReceptoresSMSJPA a una lista de ReceptorSMSBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ReceptorSMSBean> getListReceptorSMSBean(List<ViewReceptoresSMSJPA> listJPA) throws BusinessException
	{	
		List<ReceptorSMSBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ReceptorSMSBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewReceptoresSMSJPA servidorJPA = listJPA.get(indice);
				ReceptorSMSBean servidor =  new ReceptorSMSBean();
			
				try {
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}
	/**
	 * <p>Obtenemos un objeto ReceptorSMSBean a partir de un objeto ReceptoresSMSJPA</p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ReceptorSMSBean
	 */
	protected ReceptorSMSBean getReceptorSMSBean(ReceptoresSMSJPA servidorJPA) throws BusinessException
	{
		ReceptorSMSBean servidor = new ReceptorSMSBean();
		
		try {
			BeanUtils.copyProperties(servidor, servidorJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return servidor;
	}
	/**
	 * <p>Convertirmos una lista de ViewReceptoresSMSJPA a una lista de ReceptorSMSBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ReceptorSMSBean> getListViewReceptorSMSBean(List<ViewReceptoresSMSJPA> listJPA) throws BusinessException
	{	
		List<ReceptorSMSBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ReceptorSMSBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewReceptoresSMSJPA servidorJPA = listJPA.get(indice);
				ReceptorSMSBean servidor =  new ReceptorSMSBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}

	@Override
	public List<ReceptorSMSBean> getReceptoresSMSNoAsignados(
			Integer idServicio) throws BusinessException {
		ArrayList<ReceptorSMSBean> listaServidores = new ArrayList<ReceptorSMSBean>();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt =con.prepareStatement("select s.servidorid, s.nombre from tbl_servidores s where s.servidorid not in (select servidorid from tbl_servidores_servicios ss where ss.servicioid="+idServicio+") and s.tipo=3" +
					" and (s.eliminado is null or s.eliminado = 'N')"); 
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReceptorSMSBean servidorBean = new ReceptorSMSBean();
				Integer idServidor = rs.getInt(1);
				String nombreServidor = rs.getString(2);
				servidorBean.setReceptorSMSId(idServidor.longValue());
				servidorBean.setNombre(nombreServidor);
				listaServidores.add(servidorBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		
		return listaServidores;
	}
}
