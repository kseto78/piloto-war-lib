package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.model.UsuarioJPA;
import es.mpr.plataformamensajeria.model.views.ViewUsuarioAplicacionJPA;
import es.mpr.plataformamensajeria.model.views.ViewUsuarioJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioUsuarioImpl implements ServicioUsuario{

	private IPaginationJPADAO jpa;

	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
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

	

	
	/**
	 * <p>Convertirmos una lista de ViewUsuarioJPA a una lista de UsuarioBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuarioBean
	 */
	protected List<UsuarioBean> getListViewUsuarioBean(List<ViewUsuarioJPA> listJPA) throws BusinessException
	{	
		List<UsuarioBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UsuarioBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuarioJPA usuarioJPA = listJPA.get(indice);
				UsuarioBean usuario =  new UsuarioBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(usuario, usuarioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(usuario);
			}
		}
			
		return result;
	}
	/**
	 * <p>Convertirmos una lista de ViewUsuarioJPA a una lista de UsuarioBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuarioBean
	 */
	protected List<UsuarioBean> getListViewUsuarioAppBean(List<ViewUsuarioAplicacionJPA> listJPA) throws BusinessException
	{	
		List<UsuarioBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UsuarioBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuarioAplicacionJPA usuarioJPA = listJPA.get(indice);
				UsuarioBean usuario =  new UsuarioBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(usuario, usuarioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(usuario);
			}
		}
			
		return result;
	}	
	@Override
	public List<UsuarioBean> getUsuarios()
			throws BusinessException {
		List<ViewUsuarioJPA> listJPA = null;
		
		try {
			
			
			ViewUsuarioJPA criterioJPA = new ViewUsuarioJPA();
				
				listJPA= jpa.readAll(0,0,criterioJPA);
		
			
			List<UsuarioBean> result = getListUsuarioBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public List<UsuarioBean> getUsuarios(UsuarioBean criterio)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedList<UsuarioBean> getUsuarios(int start, int size, String order,
			String columnSort, UsuarioBean criterio) throws BusinessException {
		List<ViewUsuarioJPA> listJPA = null;
		boolean nombre=false;
		boolean aplicacionId = false;
		boolean rolId = false;
		try {
			
			StringBuffer namedQuery = null;
			StringBuffer namedCountQuery = new StringBuffer();;
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("1","Usuario");
			columns.put("2", "Rol");
			if (columnSort==null) 
				columnSort = "1"; //Id
			
			String column = columns.get(columnSort);
			if (column==null)
				column = "Usuario";
//			EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
//			 EntityManager em=emf.createEntityManager();

			namedQuery = new  StringBuffer();
			
			if(criterio==null){
				namedQuery.append("selectViewUsuarioJPA");
			}else{
				namedQuery.append("selectViewUsuarioJPABy");
				if(!PlataformaMensajeriaUtil.isEmpty(criterio.getNombre())){
					namedQuery.append("Nombre"); nombre=true;
				}
				if(!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) && criterio.getAplicacionId() != -1){
					namedQuery.append("Aplicacion"); aplicacionId = true;
				}
				if(!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getRolId()) && criterio.getRolId() != -1){
					namedQuery.append("Rol"); rolId = true;
				}
				if(!nombre&&!aplicacionId&&!rolId){
					namedQuery.setLength(0);
					namedQuery.append("selectViewUsuarioJPA");
				}
			}
			namedCountQuery.append(namedQuery.toString()).append("_count");
			namedQuery.append("_orderby").append(column);
			
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
			
			query = setQueryParameters(query,criterio,nombre,aplicacionId,rolId);
			if(size>0){
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = query.getResultList();
			listJPA = filtroLista(listJPA);
			List<UsuarioBean> pageList = getListUsuarioBean(listJPA);
			
			//Total de usuarios
			Integer rowcount = getTotalUsuarios(criterio,em,namedCountQuery,nombre,aplicacionId,rolId); 
			if(rowcount!=listJPA.size()){
				rowcount = listJPA.size();
			}
			PaginatedList<UsuarioBean> result = new PaginatedList<UsuarioBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
//			try{
//				if(em.isOpen()){
//					em.close();
//					emf.close();
//				}
//			}catch(Exception e){
//				System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
//			}
			return result;
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}
	/**
	 * 
	 * @param listJPA
	 * @return
	 */
	private static List<ViewUsuarioJPA> filtroLista(List<ViewUsuarioJPA> listJPA) {
		HashMap<Integer, Integer> repetidosMap = new HashMap<Integer,Integer>();
		List<ViewUsuarioJPA> listaFiltrada = new ArrayList<ViewUsuarioJPA>();
		for(ViewUsuarioJPA usuario: listJPA){
			if(usuario!=null&&usuario.getUsuarioId()!=null&&!repetidosMap.containsKey(usuario.getUsuarioId())){
				repetidosMap.put(usuario.getUsuarioId(), usuario.getUsuarioId());
				listaFiltrada.add(usuario);
			}
		}
		return listaFiltrada;
	}

	@Override
	public Integer getTotalUsuarios(UsuarioBean criterio, 
			EntityManager em,StringBuffer namedCountQuery,
			boolean nombre, boolean aplicacionId, boolean rolId)
			throws BusinessException {
		Query rowCountQuery = em.createNamedQuery(namedCountQuery.toString());
		rowCountQuery = setQueryParameters(rowCountQuery, criterio, nombre, aplicacionId, rolId);
		Long rowcount = (Long) rowCountQuery.getSingleResult();
		return (rowcount!=null)?rowcount.intValue():0;
	}
	private static Query setQueryParameters(Query query,UsuarioBean criterio, boolean nombre,
			boolean aplicacionId, boolean rolId) {
		if(nombre){
			String nombreCriterio = criterio.getNombre();
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombreUsuario", nombreCriterio);
		}
		if(aplicacionId){
			query.setParameter("aplicacionId", criterio.getAplicacionId().toString());
		}
		if(rolId){
			query.setParameter("rolId", criterio.getRolId().toString());
		}
		return query;
	}

	

	@Override
	@Transactional
	public Integer newUsuario(UsuarioBean usuario) throws BusinessException {
		try{
			UsuarioJPA usuarioJPA = getUsuarioJPA(usuario);
			usuarioJPA.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			usuarioJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			jpa.insert(usuarioJPA);
			
			usuario.setUsuarioId((Integer)usuarioJPA.getId());
			usuario.setNombre(usuarioJPA.getNombre());
			usuario.setLogin(usuarioJPA.getLogin());
			usuario.setActivo(usuarioJPA.getActivo());
			usuario.setFechaCreacion(usuarioJPA.getFechaCreacion());
			usuario.setCreadoPor(usuarioJPA.getCreadoPor());
			usuario.setEmail(usuarioJPA.getEmail());
			return usuario.getUsuarioId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
		
	}
	@Override
	@Transactional
	public boolean existeUsuario(String loginUsuario) throws BusinessException {
		boolean sw=false;
//		EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
//  	    EntityManager em=emf.createEntityManager();
		Query query = em.createQuery("Select usuario from UsuarioJPA usuario where usuario.login=:loginUsuario");
		query.setParameter("loginUsuario", loginUsuario);
		List<UsuarioJPA> listaUsuarios = query.getResultList();
		if(listaUsuarios!=null&&listaUsuarios.size()>0){
			sw=true;
		}
//			if(em.isOpen()){
//				em.close();
//				emf.close();
//			}
		return sw;
	}
	
	@Override
	@Transactional
	public boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException {
		boolean sw=false;
		
		if(null!=idUsuario && null!=loginUsuario && !loginUsuario.isEmpty()){
			
			Query query = em.createQuery("Select usuario from UsuarioJPA usuario where usuario.usuarioId!=:idUsuario and usuario.login=:loginUsuario");
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("loginUsuario", loginUsuario);
			List<UsuarioJPA> listaUsuarios = query.getResultList();
			if(listaUsuarios!=null&&listaUsuarios.size()>0){
				sw=true;
			}
		}
		
		return sw;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateUsuario(UsuarioBean usuario) throws BusinessException {
			try {
				UsuarioJPA usuarioJPA = getUsuarioJPA(usuario);
				usuarioJPA.setFechaCreacion(usuario.getFechaCreacion());
				usuarioJPA.setCreadoPor(usuario.getCreadoPor());
				usuarioJPA.setFechaModificacion(new Date());
				usuarioJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				jpa.update(usuarioJPA);
				return;	
			}
			catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.updateOrganismo");		
			}	
			
		
		
	}

	@Override
	public UsuarioBean loadUsuario(UsuarioBean usuario) throws BusinessException {
		try {
			UsuarioJPA usuarioJPA = getUsuarioJPA(usuario);
			
			return getUsuarioBean((UsuarioJPA) jpa.read(usuarioJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}	}

	@Override
	@Transactional
	public void deleteUsuario(UsuarioBean usuario) throws BusinessException {
		// TODO Eliminar Usuario y UsuarioAplicacion Relacionados.
		try {
			UsuarioBean usuarioBBDD = loadUsuario(usuario);
			UsuarioJPA usuarioJPA = getUsuarioJPA(usuarioBBDD);		
			
			jpa.delete(usuarioJPA);
			return;
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	/**
	 * <p>Obtenemos un objeto UsuarioJPA a partir de un objeto UsuarioBean</p>
	 * 
	 * @param usuario 
	 * 
	 * @return objeto UsuarioJPA
	 */
	protected UsuarioJPA getUsuarioJPA(UsuarioBean usuario)
	{
		 
		UsuarioJPA usuarioJPA = new UsuarioJPA();
		
		try {
		Date defaultValue = null;         
		DateConverter converter = new DateConverter (defaultValue);   
		Integer defaultIntegerValue=null;
		IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
		ConvertUtils.register(intergerConverter, java.lang.Integer.class);
		ConvertUtils.register (converter, java.util.Date.class);			
			BeanUtils.copyProperties(usuarioJPA,usuario);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return usuarioJPA;
	}
	/**
	 * <p>Obtenemos un objeto UsuarioBean a partir de un objeto UsuarioJPA</p>
	 * 
	 * @param usuario
	 * 
	 * @return objeto UsuarioBean
	 */
	protected UsuarioBean getUsuarioBean(UsuarioJPA usuario)
	{
		 
		UsuarioBean usuarioBean = new UsuarioBean();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);			
			BeanUtils.copyProperties(usuarioBean,usuario);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return usuarioBean;
	}	
	/**
	 * <p>Convertirmos una lista de UsuarioJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<UsuarioBean> getListUsuarioBean(List<ViewUsuarioJPA> listJPA) throws BusinessException
	{	
		List<UsuarioBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UsuarioBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuarioJPA usuarioJPA = listJPA.get(indice);
				UsuarioBean usuario =  new UsuarioBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(usuario, usuarioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(usuario);
			}
		}
			
		return result;
	}
}
