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

import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ServidoresPushJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresPushJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de servidores Push a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioServidorPushImpl implements ServicioServidorPush{
	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
	private IPaginationJPADAO jpa;
	private static final Integer TIPO_SERVIDOR = Integer.valueOf(4);
	
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
	public List<ServidorPushBean> getServidoresPush()
			throws BusinessException {
		List<ViewServidoresPushJPA> listJPA = null;
		
		try {
			listJPA= jpa.executeQuery("selectViewServidorPush",null);
			List<ServidorPushBean> result = getListServidorPushBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}	
	
	@Override
	public List<ServidorPushBean> getServidoresPush(ServidorPushBean criterio)
			throws BusinessException {
		List<ViewServidoresPushJPA> listJPA = null;
		
		try {
			
			if(criterio== null || criterio.getNombre()== null || criterio.getNombre().equals("")){
							
				ViewServidoresPushJPA criterioJPA = new ViewServidoresPushJPA();
				if(null!=criterio){
					criterioJPA.setServidorPushId(criterio.getServidorPushId());
					criterioJPA.setNombre(criterio.getNombre());
				}
				
				listJPA= jpa.readAll(0,0,criterioJPA);
			}else{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectViewServidorPushJPA",param);
			}
			
			List<ServidorPushBean> result = getListViewServidorPushBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public PaginatedList<ServidorPushBean> getServidoresPush(int start, int size,
			String order, String columnSort, ServidorPushBean criterio)
			throws BusinessException {
		List<ViewServidoresPushJPA> listJPA = null;
		
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
					
					namedQuery = new StringBuffer("selectViewServidorPushByName_orderby");
									
				}else{
					System.out.println("[CRITERIO] Nombre: " + criterio.getNombre());
					nombreCriterio = criterio.getNombre();
					namedQuery = new StringBuffer("selectViewServidorPushByName_orderby");
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
			
			Query query = em.createNamedQuery(namedQuery.toString());
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
			query.setParameter("tipo", TIPO_SERVIDOR);
			if(size>0){
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewServidoresPushJPA>)query.getResultList();
			List<ServidorPushBean> pageList = getListViewServidorPushBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalServidoresPush(criterio,em); 
			
			PaginatedList<ServidorPushBean> result = new PaginatedList<ServidorPushBean>();
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
	public Integer getTotalServidoresPush(ServidorPushBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectViewServidorPush_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectViewServidorPush_count");
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
	@Transactional
	public Long newServidorPush(ServidorPushBean servidor) throws BusinessException {
		try{
			ServidoresPushJPA servidorPush = getServidoresPushJPA(servidor);
			servidorPush.setTipo(TIPO_SERVIDOR);
			servidorPush.setFechaCreacion(new Date());
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			servidorPush.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			servidorPush.setServidorPushId(null);
			jpa.insert(servidorPush);
			
			servidor.setServidorPushId((Long)servidorPush.getId());
			servidor.setNombre(servidorPush.getNombre());
			servidor.setDescripcion(servidorPush.getDescripcion());
			servidor.setPlataformaId(servidorPush.getPlataformaId());
			servidor.setActivo(servidorPush.getActivo());
			servidor.setFechaCreacion(servidorPush.getFechaCreacion());
			servidor.setCreadoPor(servidorPush.getCreadoPor());
			return servidorPush.getServidorPushId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateServidorPush(ServidorPushBean servidor) throws BusinessException {
		try {
			ServidoresPushJPA servidorJPA = getServidoresPushJPA(servidor);
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
	public ServidorPushBean loadServidorPush(ServidorPushBean servidor) throws BusinessException {
		try {
			ServidoresPushJPA servidorJPA = getServidoresPushJPA(servidor);
			
			return getServidorPushBean((ServidoresPushJPA) jpa.read(servidorJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	@Override

	@Transactional
	public void deleteServidorPush(ServidorPushBean proveedor) throws BusinessException {
		try {
			ServidorPushBean proveedorBBDD = loadServidorPush(proveedor);
			ServidoresPushJPA servidorPushJPA = getServidoresPushJPA(proveedorBBDD);
			String[] paramServidor = new String[]{servidorPushJPA.getServidorPushId().toString()};
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
			servidorPushJPA.setModificadoPor(modificador);
			servidorPushJPA.setFechaModificacion(new Date());
			servidorPushJPA.setEliminado("S");
			jpa.update(servidorPushJPA);
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
	protected ServidoresPushJPA getServidoresPushJPA(ServidorPushBean proveedorSMS) throws BusinessException
	{
		ServidoresPushJPA servidorPushJPA = new ServidoresPushJPA();
		
		try {
			BeanUtils.copyProperties(servidorPushJPA,proveedorSMS);
		} catch (IllegalAccessException  e) {
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		
		return servidorPushJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de ServidoresPushJPA a una lista de ServidorPushBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServidorPushBean> getListServidorPushBean(List<ViewServidoresPushJPA> listJPA) throws BusinessException
	{	
		List<ServidorPushBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ServidorPushBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewServidoresPushJPA servidorJPA = listJPA.get(indice);
				ServidorPushBean servidor =  new ServidorPushBean();
			
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
	 * <p>Obtenemos un objeto ServidorPushBean a partir de un objeto ServidoresPushJPA</p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ServidorPushBean
	 */
	protected ServidorPushBean getServidorPushBean(ServidoresPushJPA servidorJPA) throws BusinessException
	{
		ServidorPushBean servidor = new ServidorPushBean();
		
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
	 * <p>Convertirmos una lista de ViewServidoresPushJPA a una lista de ServidorPushBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServidorPushBean> getListViewServidorPushBean(List<ViewServidoresPushJPA> listJPA) throws BusinessException
	{	
		List<ServidorPushBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ServidorPushBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewServidoresPushJPA servidorJPA = listJPA.get(indice);
				ServidorPushBean servidor =  new ServidorPushBean();
			
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
	public List<ServidorPushBean> getServidoresPushNoAsignados(
			Integer idServicio) throws BusinessException {
		ArrayList<ServidorPushBean> listaServidores = new ArrayList<ServidorPushBean>();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt =con.prepareStatement("select s.servidorid, s.nombre from tbl_servidores s where s.servidorid not in (select servidorid from tbl_servidores_servicios ss where ss.servicioid="+idServicio+") and s.tipo="+ TIPO_SERVIDOR +
					" and (s.eliminado is null or s.eliminado = 'N')"); 
			rs = pstmt.executeQuery();
			while(rs.next()){
				ServidorPushBean servidorBean = new ServidorPushBean();
				Integer idServidor = rs.getInt(1);
				String nombreServidor = rs.getString(2);
				servidorBean.setServidorPushId(idServidor.longValue());
				servidorBean.setNombre(nombreServidor);
				listaServidores.add(servidorBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		
		return listaServidores;
	}
	
}
