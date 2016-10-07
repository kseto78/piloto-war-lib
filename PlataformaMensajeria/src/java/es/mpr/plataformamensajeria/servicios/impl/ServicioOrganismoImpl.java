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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;
import com.map.j2ee.util.beanutils.converters.IntegerConverter;

import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.model.OrganismoJPA;
import es.mpr.plataformamensajeria.model.OrganismosServicioJPA;
import es.mpr.plataformamensajeria.model.ServidoresOrganismosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioOrganismoImpl implements ServicioOrganismo{
	
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
	public List<OrganismoBean> getOrganismos(OrganismoBean criterio)
			throws BusinessException {
		List<OrganismoJPA> listJPA = null;
		
		try {
			String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
			listJPA= jpa.executeQuery("selectOrganismoJPA",param);
			List<OrganismoBean> result = getListOrganismoBean(listJPA);					
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public List<OrganismoBean> getOrganismos()
			throws BusinessException {
		List<OrganismoJPA> listJPA = null;
		
		try {
			
			
				OrganismoJPA criterioJPA = new OrganismoJPA();
				
				listJPA= jpa.executeQuery("selectOrganismoJPA",null);
		
			
			List<OrganismoBean> result = getListOrganismoBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	
	
	@Override
	public PaginatedList<OrganismoBean> getOrganismos(int start, int size,
			String order, String columnSort, OrganismoBean criterio)
			throws BusinessException {
		List<OrganismoJPA> listJPA = null;
		
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
//		
			 String nombreCriterio = null;
			
				//Determinamos la NamedQuery
				if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals("")){
					//Sin criterio
					nombreCriterio="";
					namedQuery = new StringBuffer("selectOrganismoByName_orderby");
				}else{
					nombreCriterio = criterio.getNombre();
					namedQuery = new StringBuffer("selectOrganismoByName_orderby");
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
			listJPA = (List<OrganismoJPA>)query.getResultList();
			List<OrganismoBean> pageList = getListOrganismoBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalOrganismos(criterio,entityManager); 
			
			PaginatedList<OrganismoBean> result = new PaginatedList<OrganismoBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

	@Override
	public Integer getTotalOrganismos(OrganismoBean criterio, EntityManager em)
			throws BusinessException {
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				Query rowCountQuery = em.createNamedQuery("selectOrganismo_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount!=null)?rowcount.intValue():0;
			}
			else
			{
				Query rowCountQuery = em.createNamedQuery("selectOrganismo_count");
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
	
	@Transactional
	public Integer newOrganismo(OrganismoBean organismo) throws BusinessException {
		try{
			OrganismoJPA organismoJPA = getOrganismoJPA(organismo);
			
			organismoJPA.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			organismoJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			organismoJPA.setFechaCreacion(new Date());
			organismo.setOrganismoId(null);
			jpa.insert(organismoJPA);
			
			organismo.setOrganismoId((Integer)organismoJPA.getId());
			organismo.setNombre(organismoJPA.getNombre());
			organismo.setDescripcion(organismoJPA.getDescripcion());
			organismo.setActivo(organismoJPA.getActivo());
			organismo.setFechaCreacion(organismoJPA.getFechaCreacion());
			organismo.setCreadoPor(organismoJPA.getCreadoPor());
			organismo.setDIR3(organismoJPA.getDIR3());
			organismo.setConservacion(organismoJPA.getConservacion());
			organismo.setMotivoConservacion(organismoJPA.getMotivoConservacion());
			organismo.setHistorificacion(organismoJPA.getHistorificacion());
			organismo.setMotivoHistorificacion(organismoJPA.getMotivoHistorificacion());
			organismo.setExternalId(organismoJPA.getExternalId());
			organismo.setNombreCuentaEnvio(organismoJPA.getNombreCuentaEnvio());
			return organismoJPA.getOrganismoId();
		}catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrganismo(OrganismoBean organismo) throws BusinessException {
		
		try {
			OrganismoJPA organismoJPA = getOrganismoJPA(organismo);
			organismoJPA.setFechaModificacion(new Date());
			organismoJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			organismoJPA.setFechaCreacion(organismo.getFechaCreacion());
			jpa.update(organismoJPA);
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		} catch (Exception ex) {
			throw new BusinessException(ex,"errors.organismo.updateOrganismo");
		}
		
	}

	@Override
	@Transactional
	public OrganismoBean loadOrganismo(OrganismoBean organismo)	throws BusinessException {
		try {
			OrganismoJPA organismoJPA = getOrganismoJPA(organismo);
			
			return getOrganismoBean((OrganismoJPA) jpa.read(organismoJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	@Transactional
	public OrganismoJPA loadOrganismoJPA(OrganismoJPA organismo)	throws BusinessException {
		try {
	
			
			return (OrganismoJPA) jpa.read(organismo);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	
	@Override
	@Transactional
	public void deleteOrganismo(OrganismoBean organismo) throws BusinessException {
		try {
			OrganismoBean organismoBBDD = loadOrganismo(organismo);
			OrganismoJPA organismoJPA = getOrganismoJPA(organismoBBDD);		
			String[] paramOrganismo = new String[]{organismoJPA.getOrganismoId().toString()};
			//la query hay que ponerla bien
			List<Object> listaOrganismosServicios = jpa.executeQuery("selectOrganismosServicioJPA", paramOrganismo);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			for (Object object : listaOrganismosServicios) { //ver para hacerlo ServicioServicio linea 402 o eso creo
				if(object instanceof OrganismosServicioJPA){
					OrganismosServicioJPA orgSer = (OrganismosServicioJPA) object;
					jpa.delete(orgSer);
				}
			}
			List<Object> listaServidoresOrganismos = jpa.executeQuery("selectServidoresOrganismoByIdOrganismoIdJPA", paramOrganismo);
			
			for (Object object : listaServidoresOrganismos) { //ver para hacerlo ServicioServicio linea 402 o eso creo
				if(object instanceof ServidoresOrganismosJPA){
					ServidoresOrganismosJPA orgSer = (ServidoresOrganismosJPA) object;
					jpa.delete(orgSer);
				}
			}
						
			organismoJPA.setModificadoPor(modificador);
			organismoJPA.setFechaModificacion(new Date());
			organismoJPA.setEliminado("S");
			jpa.update(organismoJPA);
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
	protected OrganismoJPA getOrganismoJPA(OrganismoBean organismo)
	{
		 
		OrganismoJPA organismoJPA = new OrganismoJPA();
		
		try {
			
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(organismoJPA,organismo);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}

		return organismoJPA;
	}
	
	/**
	 * <p>Convertirmos una lista de AplicacionJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<OrganismoBean> getListOrganismoBean(List<OrganismoJPA> listJPA) throws BusinessException
	{	
		List<OrganismoBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<OrganismoBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				OrganismoJPA organismoJPA = listJPA.get(indice);
				OrganismoBean organismo =  new OrganismoBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(organismo, organismoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(organismo);
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
	protected OrganismoBean getOrganismoBean(OrganismoJPA organismoJPA) throws BusinessException
	{
		OrganismoBean organismo = new OrganismoBean();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);         
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(organismo, organismoJPA);
			BeanUtils.copyProperties(organismo, organismoJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return organismo;
	}
	
}
