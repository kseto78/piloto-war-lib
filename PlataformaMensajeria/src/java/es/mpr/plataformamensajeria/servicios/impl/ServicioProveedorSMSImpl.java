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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ProveedoresSMSJPA;
import es.mpr.plataformamensajeria.model.views.ViewProveedoresSMSJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de servidores a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioProveedorSMSImpl implements ServicioProveedorSMS{
	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
	private IPaginationJPADAO jpa;
	private static final Integer TIPO_SERVIDOR= new Integer(1);
	private static final Integer POR_DEFECTO = new Integer(1);
	
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
	public List<ProveedorSMSBean> getProveedoresSMS()
			throws BusinessException {
		List<ViewProveedoresSMSJPA> listJPA = null;
		
		try {
			ViewProveedoresSMSJPA criterioJPA = new ViewProveedoresSMSJPA();
				listJPA= jpa.executeQuery("selectViewProveedorSMS",null);
			List<ProveedorSMSBean> result = getListProveedorSMSBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}	
	
	@Override
	public List<ProveedorSMSBean> getProveedoresSMS(ProveedorSMSBean criterio)
			throws BusinessException {
		List<ViewProveedoresSMSJPA> listJPA = null;
		
		try {
			
			if(criterio== null || criterio.getNombre()== null || criterio.getNombre().equals("")){
							
				ViewProveedoresSMSJPA criterioJPA = new ViewProveedoresSMSJPA();
				criterioJPA.setProveedorSMSId(criterio.getProveedorSMSId());
				criterioJPA.setNombre(criterio.getNombre());
				
				listJPA= jpa.readAll(0,0,criterioJPA);
			}else{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectProveedoresSMSJPA",param);
			}
			
			List<ProveedorSMSBean> result = getListViewProveedorSMSBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public PaginatedList<ProveedorSMSBean> getProveedoresSMS(int start, int size,
			String order, String columnSort, ProveedorSMSBean criterio)
			throws BusinessException {
		List<ViewProveedoresSMSJPA> listJPA = null;
		
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
			
			String[] param = {};
//			EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
//			 EntityManager em=emf.createEntityManager();
			 
			 //EntityTransaction entr=em.getTransaction();
			 //Query query=em.createNamedQuery("studentRecords");
			 //query.setParameter(1, "vinod");
			 //query.setParameter(2, "PHD");
			 //List stList=query.getResultList();
			 //Iterator stIterator=stList.iterator();
			 String nombreCriterio = null;
			
				//Determinamos la NamedQuery
				if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals("")){
									
					//Sin criterio
					nombreCriterio="";
					
					namedQuery = new StringBuffer("selectViewProveedorSMSByName_orderby");
									
				}else{
					System.out.println("[CRITERIO] Nombre: " + criterio.getNombre());
					nombreCriterio = criterio.getNombre();
					namedQuery = new StringBuffer("selectViewProveedorSMSByName_orderby");
				}
			
			namedQuery.append(column);
			
			//Orden ascendente o descendente
			if (order==null || order.equals("1")) //ASC
			{
				namedQuery.append("_ASC"); 
			}
			else if (order!=null && order.equals("2")) //DESC 
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
			listJPA = (List<ViewProveedoresSMSJPA>)query.getResultList();
			List<ProveedorSMSBean> pageList = getListViewProveedorSMSBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalProveedoresSMS(criterio,em); 
			
			PaginatedList<ProveedorSMSBean> result = new PaginatedList<ProveedorSMSBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
//			try{
//				if(em!=null&&em.isOpen()){
//					em.close();
//					emf.close();
//				}
//			}catch(Exception e){
//				System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
//			}
			return result;
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

	@Override
	public Integer getTotalProveedoresSMS(ProveedorSMSBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectViewProveedorSMS_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectViewProveedorSMS_count");
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
	public Long newProveedorSMS(ProveedorSMSBean servidor) throws BusinessException {
		try{
			ProveedoresSMSJPA proveedorSMS = getProveedoresSMSJPA(servidor);
			proveedorSMS.setTipo(TIPO_SERVIDOR);
			proveedorSMS.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			proveedorSMS.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			//servidorJPA.setPorDefecto(POR_DEFECTO);
			proveedorSMS.setProveedorSMSId(null);
			//Si es 0 indica Recepcion de estado, 1 = Consulta de estado
			proveedorSMS.setMetodoConsulta(Integer.parseInt(servidor.getMetodoConsulta()));
			jpa.insert(proveedorSMS);
			
			servidor.setProveedorSMSId((Long)proveedorSMS.getId());
			servidor.setNombre(proveedorSMS.getNombre());
			servidor.setDescripcion(proveedorSMS.getDescripcion());
			servidor.setActivo(proveedorSMS.getActivo());
			servidor.setFechaCreacion(proveedorSMS.getFechaCreacion());
			servidor.setCreadoPor(proveedorSMS.getCreadoPor());
			return proveedorSMS.getProveedorSMSId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProveedorSMS(ProveedorSMSBean servidor) throws BusinessException {
		try {
			ProveedoresSMSJPA servidorJPA = getProveedoresSMSJPA(servidor);
			servidorJPA.setFechaModificacion(new Date());
			servidorJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servidorJPA.setFechaCreacion(servidor.getFechaCreacion());
			//0 = Recepción de Estado / 1= Consulta de Estado
			servidorJPA.setMetodoConsulta(Integer.parseInt(servidor.getMetodoConsulta()));
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
	public ProveedorSMSBean loadProveedorSMS(ProveedorSMSBean servidor)	throws BusinessException {
		try {
			ProveedoresSMSJPA servidorJPA = getProveedoresSMSJPA(servidor);
			
			return getProveedorSMSBean((ProveedoresSMSJPA) jpa.read(servidorJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	@Override

	@Transactional
	public void deleteProveedorSMS(ProveedorSMSBean proveedor) throws BusinessException {
		try {
			ProveedorSMSBean proveedorBBDD = loadProveedorSMS(proveedor);
			ProveedoresSMSJPA proveedorSMSJPA = getProveedoresSMSJPA(proveedorBBDD);
			String[] paramServidor = new String[]{proveedorSMSJPA.getProveedorSMSId().toString()};
			//jpa.executeQuery("deleteServidor", params);
			//jpa.delete(servidorJPA);
			//EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
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
			proveedorSMSJPA.setModificadoPor(modificador);
			proveedorSMSJPA.setFechaModificacion(new Date());
			proveedorSMSJPA.setEliminado("S");
			jpa.update(proveedorSMSJPA);
			return;
		}catch (Exception e) {
			throw new BusinessException("Error eliminando proveedor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	protected ProveedoresSMSJPA getProveedoresSMSJPA(ProveedorSMSBean proveedorSMS) throws BusinessException
	{
		ProveedoresSMSJPA proveedorSMSJPA = new ProveedoresSMSJPA();
		
		try {
			BeanUtils.copyProperties(proveedorSMSJPA,proveedorSMS);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		/*servidorJPA.setId(servidor.getId());
		servidorJPA.setServidorId(servidorJPA.getServidorId());
		servidorJPA.setNombre(servidor.getNombre());
		servidorJPA.setDescripcion(servidor.getDescripcion());
		System.out.println("[PLATAFORMA-FORM-ACTIVO]: Valor: " + servidor.getActivo());
		servidorJPA.setActivo(servidor.getActivo());*/
		return proveedorSMSJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de ProveedoresSMSJPA a una lista de ProveedorSMSBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ProveedorSMSBean> getListProveedorSMSBean(List<ViewProveedoresSMSJPA> listJPA) throws BusinessException
	{	
		List<ProveedorSMSBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ProveedorSMSBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewProveedoresSMSJPA servidorJPA = listJPA.get(indice);
				ProveedorSMSBean servidor =  new ProveedorSMSBean();
			
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
	 * <p>Obtenemos un objeto ProveedorSMSBean a partir de un objeto ProveedoresSMSJPA</p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ProveedorSMSBean
	 */
	protected ProveedorSMSBean getProveedorSMSBean(ProveedoresSMSJPA servidorJPA) throws BusinessException
	{
		ProveedorSMSBean servidor = new ProveedorSMSBean();
		
		try {
			BeanUtils.copyProperties(servidor, servidorJPA);
			//0= Recepción de Estado / 1= Consulta de Estado 
			servidor.setMetodoConsulta(String.valueOf(servidorJPA.getMetodoConsulta()));
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return servidor;
	}
	/**
	 * <p>Convertirmos una lista de ViewProveedoresSMSJPA a una lista de ProveedorSMSBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ProveedorSMSBean> getListViewProveedorSMSBean(List<ViewProveedoresSMSJPA> listJPA) throws BusinessException
	{	
		List<ProveedorSMSBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ProveedorSMSBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewProveedoresSMSJPA servidorJPA = listJPA.get(indice);
				ProveedorSMSBean servidor =  new ProveedorSMSBean();
			
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
	public List<ProveedorSMSBean> getProveedoresSMSNoAsignados(
			Integer idServicio) throws BusinessException {
		ArrayList<ProveedorSMSBean> listaServidores = new ArrayList<ProveedorSMSBean>();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt =con.prepareStatement("select s.servidorid, s.nombre from tbl_servidores s where s.servidorid not in (select servidorid from tbl_servidores_servicios ss where ss.servicioid="+idServicio+") and s.tipo=1" +
					" and (s.eliminado is null or s.eliminado = 'N')"); 
			rs = pstmt.executeQuery();
			while(rs.next()){
				ProveedorSMSBean servidorBean = new ProveedorSMSBean();
				Integer idServidor = rs.getInt(1);
				String nombreServidor = rs.getString(2);
				servidorBean.setProveedorSMSId(idServidor.longValue());
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
