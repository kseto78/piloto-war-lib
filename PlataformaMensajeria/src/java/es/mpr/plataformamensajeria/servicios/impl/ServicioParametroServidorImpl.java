package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.model.ParametroServidorJPA;
import es.mpr.plataformamensajeria.model.views.ViewParametroServidorJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de parametros servidor a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioParametroServidorImpl implements ServicioParametroServidor{

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

	@Override
	public List<ParametroServidorBean> getParametrosServidor(ParametroServidorBean criterio)
			throws BusinessException {
		List<ParametroServidorJPA> listJPA = null;
		
		try {
			listJPA= jpa.readAll(0,0,criterio);
			List<ParametroServidorBean> result = getListParametroServidorBean(listJPA);					
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public PaginatedList<ParametroServidorBean> getParametrosServidor(int start, int size,
			String order, String columnSort, ParametroServidorBean criterio)
			throws BusinessException {
		List<ViewParametroServidorJPA> listJPA = null;
		
		try {
			
			StringBuffer namedQuery = null;
			
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("0","Nombre");
			columns.put("1","Descripcion");
			columns.put("2","Activo");
			if (columnSort==null){
				columnSort = "0"; //Id
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "Id";
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
				if(criterio==null || criterio.getValor()== null || criterio.getValor().equals("")){
					//Sin criterio
					nombreCriterio="";
					namedQuery = new StringBuffer("selectViewServidorByName_orderby");
				}else{
					nombreCriterio = criterio.getValor();
					namedQuery = new StringBuffer("selectViewServidorByName_orderby");
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
			query.setParameter("nombre", nombreCriterio);
			//query.setParameter("tipo", TIPO_SERVIDOR);
			listJPA = (List<ViewParametroServidorJPA>)query.getResultList();
			List<ParametroServidorBean> pageList = getListViewParametroServidorBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalParametrosServidor(criterio,em); 
			
			PaginatedList<ParametroServidorBean> result = new PaginatedList<ParametroServidorBean>();
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
	public Integer getTotalParametrosServidor(ParametroServidorBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getValor()== null || criterio.getValor().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectViewServidor_count");
				rowCountQuery.setParameter("nombre", "");
				//rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectViewServidor_count");
				String nombreCriterio = criterio.getValor();
				rowCountQuery.setParameter("nombre", nombreCriterio);
				//rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
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
	public void newParametroServidor(ParametroServidorBean parametroServidor) throws BusinessException {
		try{
			//EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session sess = sessionFactory.getCurrentSession();
			sess.beginTransaction();
			//EntityManager em=emf.createEntityManager();
			String creadoPor = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			//Query query = em.createNativeQuery("{call PKG_SERVIDOR.deleteServidor(?)}");
			org.hibernate.Query query = sess.getNamedQuery("addParametroServidor");
			query.setParameter(0, parametroServidor.getServidorId().toString());
			query.setParameter(1, parametroServidor.getValor().trim()); //Eliminamos los espacios
			query.setParameter(2,parametroServidor.getTipoParametroId());
			query.setParameter(3,(creadoPor!=null)?creadoPor:"");
			query.setParameter(4, new Integer(0));
			int r = query.executeUpdate();	
			ParametroServidorJPA parametroServidorJPA = getParametroServidorJPA(parametroServidor);
			parametroServidorJPA.setParametroServidorId(null);
			parametroServidorJPA.setActivo(new Integer(1));
			parametroServidorJPA.setFechaCreacion(new Date());
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			parametroServidorJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			//servidorJPA.setPorDefecto(POR_DEFECTO);
		/*	
			jpa.insert(parametroServidorJPA);
		*/
			//TODO: setear los valores correspondientes
			parametroServidor.setId(new Integer(r));
			parametroServidor.setActivo(parametroServidorJPA.getActivo());
			parametroServidor.setFechaCreacion(parametroServidorJPA.getFechaCreacion());
			parametroServidor.setCreadoPor(parametroServidorJPA.getCreadoPor());
			if(sess.isOpen()){
					sess.close();
					sessionFactory.close();
			}
			
			return;
		}catch (ConstraintViolationException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw e;
			
		}
	}



	@Override
	public void updateParametroServidor(ParametroServidorBean parametro) throws BusinessException {
		try {
			ParametroServidorJPA parametroServidorJPA =loadParametroServidorJPA	(parametro);
			parametroServidorJPA.setFechaModificacion(new Date());
			String modificadoPor = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			parametroServidorJPA.setModificadoPor(modificadoPor);
			jpa.update(parametroServidorJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
		
	}

	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public ParametroServidorBean loadParametroServidor(ParametroServidorBean parametroServidor)	throws BusinessException {
		try {
			ParametroServidorJPA parametroServidorJPA = getParametroServidorJPA(parametroServidor);
			
			return getParametroServidorBean((ParametroServidorJPA) jpa.read(parametroServidorJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public ParametroServidorJPA loadParametroServidorJPA(ParametroServidorBean parametroServidor)	throws BusinessException {
		try {
			ParametroServidorJPA parametroServidorJPA = getParametroServidorJPA(parametroServidor);
			
			return (ParametroServidorJPA) jpa.read(parametroServidorJPA);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	@Override

	@Transactional
	//@Auditable(operacion=Constants.AUDITORIA_OPERACION_BAJA)
	public void deleteParametroServidor(ParametroServidorBean parametroServidor) throws BusinessException {
		try {
			ParametroServidorJPA parametroServidorJPA =loadParametroServidorJPA	(parametroServidor);
			parametroServidorJPA.setFechaModificacion(new Date());
			String modificadoPor = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			parametroServidorJPA.setModificadoPor(modificadoPor);
			jpa.update(parametroServidorJPA);
			jpa.delete(parametroServidorJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/**
	 * <p>Obtenemos un objeto ParametroServidorJPA a partir de un objeto ParametroServidorBean</p>
	 * 
	 * @param parametroServidor 
	 * 
	 * @return objeto ParametroServidorJPA
	 */
	protected ParametroServidorJPA getParametroServidorJPA(ParametroServidorBean parametroServidor)
	{
		ParametroServidorJPA parametroServidorJPA = new ParametroServidorJPA();
		
		try {
			BeanUtils.copyProperties(parametroServidorJPA,parametroServidor);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return parametroServidorJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de ParametroServidorJPA a una lista de ParametroServidorBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos ParametroServidorBean
	 */
	protected List<ParametroServidorBean> getListParametroServidorBean(List<ParametroServidorJPA> listJPA) throws BusinessException
	{	
		List<ParametroServidorBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ParametroServidorBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ParametroServidorJPA parametroServidorJPA = listJPA.get(indice);
				ParametroServidorBean parametroServidor =  new ParametroServidorBean();
			
				try {
					BeanUtils.copyProperties(parametroServidor, parametroServidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(parametroServidor);
			}
		}
			
		return result;
	}
	/**
	 * <p>Obtenemos un objeto ParametroServidorBean a partir de un objeto ParametroServidorJPA</p>
	 * 
	 * @param parametroServidorJPA
	 * 
	 * @return objeto ParametroServidorBean
	 */
	protected ParametroServidorBean getParametroServidorBean(ParametroServidorJPA parametroServidorJPA) throws BusinessException
	{
		ParametroServidorBean parametroServidor = new ParametroServidorBean();
		
		try {
			BeanUtils.copyProperties(parametroServidor, parametroServidorJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return parametroServidor;
	}
	/**
	 * <p>Convertirmos una lista de ViewParametroServidorJPA a una lista de ParametroServidorBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos ParametroServidorBean
	 */
	protected List<ParametroServidorBean> getListViewParametroServidorBean(List<ViewParametroServidorJPA> listJPA) throws BusinessException
	{	
		List<ParametroServidorBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<ParametroServidorBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewParametroServidorJPA servidorJPA = listJPA.get(indice);
				ParametroServidorBean servidor =  new ParametroServidorBean();
			
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
	public List<ParametroServidorBean> getParametroServidorByServidorId(Integer servidorId) throws BusinessException {
		List<ViewParametroServidorJPA> listJPA = null;
		String[] params = new String[]{servidorId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewParametrosServidorByServidorIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ParametroServidorBean> result = getListViewParametroServidorBean(listJPA);		
		return result;
	}
	@Override
	public List<ParametroServidorBean> getParametroServidorByProveedorSMSId(Integer proveedorSMSId) throws BusinessException {
		List<ViewParametroServidorJPA> listJPA = null;
		String[] params = new String[]{proveedorSMSId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewParametrosServidorByProveedorSMSIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ParametroServidorBean> result = getListViewParametroServidorBean(listJPA);		
		return result;
	}
	
	@Override
	public List<ParametroServidorBean> getParametroServidorByReceptorSMSId(Integer receptorSMSId) throws BusinessException {
		List<ViewParametroServidorJPA> listJPA = null;
		String[] params = new String[]{receptorSMSId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewParametrosServidorByReceptorSMSIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ParametroServidorBean> result = getListViewParametroServidorBean(listJPA);		
		return result;
	}
	
	@Override
	public List<ParametroServidorBean> getParametroServidorByServidorPushId(Integer servidorPushId) throws BusinessException {
		List<ViewParametroServidorJPA> listJPA = null;
		String[] params = new String[]{servidorPushId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewParametrosServidorByServidorPushIdJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ParametroServidorBean> result = getListViewParametroServidorBean(listJPA);		
		return result;
	}

	@Override
	public boolean existeParametroServidor(
			ParametroServidorBean parametroServidor) throws BusinessException {
		String[] params = new String[]{parametroServidor.getServidorId().toString(),parametroServidor.getTipoParametroId().toString()};
		List<ViewParametroServidorJPA> listaJPA = null;
		try{
			listaJPA = jpa.executeQuery("selectViewParametrosServidorByTipo", params);
			if(listaJPA!=null&&!listaJPA.isEmpty()){
				return true;
			}
		}catch(DAOException e){
			e.printStackTrace();
		}
		return false;
	}	
	
}
