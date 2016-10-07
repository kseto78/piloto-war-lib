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
import com.map.j2ee.util.beanutils.converters.IntegerConverter;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.model.AplicacionJPA;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ServicioJPA;
import es.mpr.plataformamensajeria.model.views.ViewAplicacionJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioAplicacionImpl implements ServicioAplicacion{
	private static final String APLICACIONES_MENU_ALL = 
			"Select ap.aplicacionId, ap.nombre from view_aplicaciones ap" +
			" where ap.activo=1 and (ap.eliminado is null or ap.eliminado = 'N') order by ap.nombre ASC";
	private static final String APLICACIONES_BY_USUARIO = "Select ap.aplicacionId, ap.nombreaplicacion from" +
			" view_usuarios_aplicaciones ap where ap.activo=1 and ap.usuarioid=? and (ap.eliminado is null or ap.eliminado = 'N') order by ap.nombreaplicacion ASC";
	private static final String EXISTE_USUARIO_APLICACION = "select ap.aplicacionId, ap.nombre from" +
															" view_aplicaciones ap where ap.usuario = ?";	
			//TODO: comentar con raul que se deben filtrar las vistas por el campo eliminado de las entidades a las que afecta el borrado lógico
	private IPaginationJPADAO jpa;
	protected EntityManager entityManager;
	 
    public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
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
	public List<AplicacionBean> getAplicaciones(AplicacionBean criterio)
			throws BusinessException {
		List<AplicacionJPA> listJPA = null;
		
		try {
			String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
			listJPA= jpa.executeQuery("selectAplicacionJPA",param);
			List<AplicacionBean> result = getListAplicacionBean(listJPA);					
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public List<AplicacionBean> getAplicaciones()
			throws BusinessException {
		List<AplicacionJPA> listJPA = null;
		
		try {
			
			
				AplicacionJPA criterioJPA = new AplicacionJPA();
				
				listJPA= jpa.executeQuery("selectAplicacionJPA",null);
		
			
			List<AplicacionBean> result = getListAplicacionBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public List<AplicacionBean> getAplicacionesNoAsignadas(String idUsuario)
			throws BusinessException {
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		 List<AplicacionBean> listBean = new ArrayList<AplicacionBean>();
		try{
			pstmt = con.prepareStatement("Select ap.aplicacionId, ap.nombre from" +
					" view_aplicaciones ap where ap.activo=1 and ap.aplicacionId not in(" +
					" SELECT nvl(uap.aplicacionId,-1) from" +
					" view_usuarios_aplicaciones uap where uap.usuarioid=? ) and (ap.eliminado is null or ap.eliminado = 'N') order by ap.nombre ASC");
			pstmt.setInt(1, new Integer(idUsuario));
			rs = pstmt.executeQuery();
			while(rs.next()){
				String idAplicacion = rs.getString(1);
				String nombre = rs.getString(2);
				AplicacionBean app = new AplicacionBean();
				app.setAplicacionId(new Integer(idAplicacion));
				app.setNombre(nombre);
				listBean.add(app);
			}

		}catch(SQLException e){
			
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				
					con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return listBean;
	}	
	@Override
	public boolean existeUsuario(String usuario){
		boolean existe = false;
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		 List<AplicacionBean> listBean = new ArrayList<AplicacionBean>();
		try{
			pstmt = con.prepareStatement(EXISTE_USUARIO_APLICACION);
			pstmt.setString(1, usuario);
			rs = pstmt.executeQuery();
			if(rs.next()){
				existe=true;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				
					con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		 return existe;
		
	}
	@Override
	public List<AplicacionBean> getAplicacionesMenu(String rolUsuario, Integer userName){
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		 List<AplicacionBean> listBean = new ArrayList<AplicacionBean>();
		try{
			if(rolUsuario!=null&&rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
				pstmt = con.prepareStatement(APLICACIONES_MENU_ALL);
			}else{
				pstmt = con.prepareStatement(APLICACIONES_BY_USUARIO);
				pstmt.setInt(1, userName);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				String idAplicacion = rs.getString(1);
				String nombre = rs.getString(2);
				AplicacionBean app = new AplicacionBean();
				app.setAplicacionId(new Integer(idAplicacion));
				app.setNombre(nombre);
				listBean.add(app);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				
					con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		 return listBean;
		
	}
	@Override
	public PaginatedList<AplicacionBean> getAplicaciones(int start, int size,
			String order, String columnSort, AplicacionBean criterio)
			throws BusinessException {
		List<ViewAplicacionJPA> listJPA = null;
		
		try {
			
			StringBuffer namedQuery = null;
			
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("1","Nombre");
			columns.put("2","Nombre");
			columns.put("3","Nombre");
			if (columnSort==null){
				columnSort = "1"; //Id
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "Nombre";
			}
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
					namedQuery = new StringBuffer("selectViewAplicacionByName_orderby");
				}else{
					nombreCriterio = criterio.getNombre();
					namedQuery = new StringBuffer("selectViewAplicacionByName_orderby");
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
			Query query =  entityManager.createNamedQuery(namedQuery.toString());
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
			if(size>0){
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewAplicacionJPA>)query.getResultList();
			List<AplicacionBean> pageList = getListViewAplicacionBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalAplicaciones(criterio,entityManager); 
			
			PaginatedList<AplicacionBean> result = new PaginatedList<AplicacionBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
//			try{
//				if(em.isOpen()){
//					em.close();
//					emf.close();
//				}
//			}catch(Exception e){
//			}
			return result;
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

	@Override
	public Integer getTotalAplicaciones(AplicacionBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectViewAplicacion_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectViewAplicacion_count");
				String nombreCriterio = criterio.getNombre();
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio));
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
	public Integer newAplicacion(AplicacionBean aplicacion) throws BusinessException {
		try{
			AplicacionJPA aplicacionJPA = getAplicacionJPA(aplicacion);
			
			aplicacionJPA.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			aplicacionJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			aplicacion.setAplicacionId(null);
			jpa.insert(aplicacionJPA);
			
			aplicacion.setAplicacionId((Integer)aplicacionJPA.getId());
			aplicacion.setNombre(aplicacionJPA.getNombre());
			aplicacion.setDescripcion(aplicacionJPA.getDescripcion());
			aplicacion.setActivo(aplicacionJPA.getActivo());
			aplicacion.setFechaCreacion(aplicacionJPA.getFechaCreacion());
			aplicacion.setCreadoPor(aplicacionJPA.getCreadoPor());
			return aplicacionJPA.getAplicacionId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateAplicacion(AplicacionBean aplicacion) throws BusinessException {
		try {
			AplicacionJPA aplicacionJPA = getAplicacionJPA(aplicacion);
			aplicacionJPA.setFechaModificacion(new Date());
			aplicacionJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			aplicacionJPA.setFechaCreacion(aplicacion.getFechaCreacion());
			jpa.update(aplicacionJPA);
			return;	
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	@Override
	//@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public AplicacionBean loadAplicacion(AplicacionBean aplicacion)	throws BusinessException {
		try {
			AplicacionJPA aplicacionJPA = getAplicacionJPA(aplicacion);
			
			return getAplicacionBean((AplicacionJPA) jpa.read(aplicacionJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	//@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public AplicacionJPA loadAplicacionJPA(AplicacionJPA aplicacion)	throws BusinessException {
		try {
	
			
			return (AplicacionJPA) jpa.read(aplicacion);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override

	@Transactional
	public void deleteAplicacion(AplicacionBean aplicacion) throws BusinessException {
		try {
			AplicacionBean aplicacionBBDD = loadAplicacion(aplicacion);
			AplicacionJPA aplicacionJPA = getAplicacionJPA(aplicacionBBDD);		
			String[] paramAplicacion = new String[]{aplicacionJPA.getAplicacionId().toString()};
			List<Object> listaServicios = jpa.executeQuery("selectServicioJPAByIdAplicacion", paramAplicacion);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			for (Object object : listaServicios) {
				if(object instanceof ServicioJPA){
					ServicioJPA servicio = (ServicioJPA) object;
					String[] paramServicio = new String[]{servicio.getservicioId().toString()};
					List<Object> listaPlanificacionesServicio = jpa.executeQuery("selectPlanificacionByServicioIdJPA", paramServicio);
					for (Object object2 : listaPlanificacionesServicio) {
						if(object instanceof PlanificacionJPA){
							PlanificacionJPA planificacion = (PlanificacionJPA ) object2;
							planificacion.setEliminado("S");
							planificacion.setModificadoPor(modificador);
							planificacion.setFechaModificacion(new Date());
							jpa.update(planificacion);
						}
					}
					servicio.setEliminado("S");
					servicio.setModificadoPor(modificador);
					servicio.setFechaModificacion(new Date());
					jpa.update(servicio);
				}
			}
			aplicacionJPA.setModificadoPor(modificador);
			aplicacionJPA.setFechaModificacion(new Date());
			aplicacionJPA.setEliminado("S");
			jpa.update(aplicacionJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param aplicacion 
	 * 
	 * @return objeto AplicacionJPA
	 */
	protected AplicacionJPA getAplicacionJPA(AplicacionBean aplicacion)
	{
		 
		AplicacionJPA aplicacionJPA = new AplicacionJPA();
		
		try {
			
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(aplicacionJPA,aplicacion);
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
		return aplicacionJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de AplicacionJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<AplicacionBean> getListAplicacionBean(List<AplicacionJPA> listJPA) throws BusinessException
	{	
		List<AplicacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<AplicacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				AplicacionJPA aplicacionJPA = listJPA.get(indice);
				AplicacionBean aplicacion =  new AplicacionBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(aplicacion, aplicacionJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(aplicacion);
			}
		}
			
		return result;
	}
	/**
	 * <p>Obtenemos un objeto AplicacionBean a partir de un objeto AplicacionJPA</p>
	 * 
	 * @param aplicacionJPA
	 * 
	 * @return objeto AplicacionBean
	 */
	protected AplicacionBean getAplicacionBean(AplicacionJPA aplicacionJPA) throws BusinessException
	{
		AplicacionBean aplicacion = new AplicacionBean();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);         
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(aplicacion, aplicacionJPA);
			BeanUtils.copyProperties(aplicacion, aplicacionJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return aplicacion;
	}
	/**
	 * <p>Convertirmos una lista de ViewAplicacionJPA a una lista de AplicacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos AplicacionBean
	 */
	protected List<AplicacionBean> getListViewAplicacionBean(List<ViewAplicacionJPA> listJPA) throws BusinessException
	{	
		List<AplicacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<AplicacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewAplicacionJPA aplicacionJPA = listJPA.get(indice);
				AplicacionBean aplicacion =  new AplicacionBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(aplicacion, aplicacionJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(aplicacion);
			}
		}
			
		return result;
	}}
