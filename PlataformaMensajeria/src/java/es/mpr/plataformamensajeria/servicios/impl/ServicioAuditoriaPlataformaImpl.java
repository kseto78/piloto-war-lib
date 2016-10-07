package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
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

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;
import es.mpr.plataformamensajeria.model.views.ViewAuditoriaPlataformaJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAuditoriaPlataforma;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de auditorias a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioAuditoriaPlataformaImpl implements ServicioAuditoriaPlataforma{

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

	

	
	/**
	 * <p>Convertirmos una lista de ViewAdutoriaPlataformaJPA a una lista de AuditoriaPlataformaBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos AuditoriaPlataformaBean
	 */
	protected List<AuditoriaPlataformaBean> 
	getListViewAuditoriaPlataformaBean(List<ViewAuditoriaPlataformaJPA> listJPA) throws BusinessException
	{	
		List<AuditoriaPlataformaBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<AuditoriaPlataformaBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewAuditoriaPlataformaJPA auditoriaPlataformaJPA = listJPA.get(indice);
				AuditoriaPlataformaBean auditoriaPlataforma =  new AuditoriaPlataformaBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(auditoriaPlataforma, auditoriaPlataformaJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(auditoriaPlataforma);
			}
		}
			
		return result;
	}
	@Override
	public PaginatedList<AuditoriaPlataformaBean> getAuditoriasPlataforma(int start, int size, String order,
			String columnSort, AuditoriaPlataformaBean criterio,boolean isExport) throws BusinessException {
		//Columna para ordenar
		Hashtable<String, String> columns = new Hashtable<String,String>();
		columns.put("0","Operacion");
		columns.put("1","Entidad");
		columns.put("2","Id");
		columns.put("4","Fecha");
		columns.put("5","Usuario");
		if (columnSort==null){
			columnSort = "4"; //Id
		}
		String column = columns.get(columnSort);
		if (column==null){
			column = "Fecha";
		}
		PaginatedList<AuditoriaPlataformaBean> result = null; 
//		 	EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
//				 EntityManager em=emf.createEntityManager();
				 StringBuffer sbf = new StringBuffer();
				 StringBuffer sbfCount = new StringBuffer();
				 sbf = createStringQuery(criterio,order,column,false);
				 sbfCount = createStringQuery(criterio, order, columnSort, true);
				 Query query =  entityManager.createQuery(sbf.toString());
				 query=setParameters(query,criterio);
				 if(size>0){
					 query.setFirstResult(start);
					 query.setMaxResults(size);
				 }
			List<ViewAuditoriaPlataformaJPA> listJPA = query.getResultList();
			List<AuditoriaPlataformaBean> listaAuditoriaBean = getListAuditoriaPlataformaBean(listJPA);
			//Total de organismos
			Integer rowcount = getTotalAuditoriasPlataforma(criterio,entityManager,sbfCount); 
			
			result = new PaginatedList<AuditoriaPlataformaBean>();
			result.setPageList(listaAuditoriaBean);
			result.setTotalList(rowcount);
//			try{
//				if(entityManager!=null&&entityManager.isOpen()){
//					em.close();
//					emf.close();
//				}
//			}catch(Exception e){
//				System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
//			}

		return result;
	}
	@Override
	public Integer getTotalAuditoriasPlataforma(AuditoriaPlataformaBean criterio,
			EntityManager em, StringBuffer sbfCount) throws BusinessException {
			Query query =  em.createQuery(sbfCount.toString());
			query = setParameters(query, criterio);
			Long rowcount = (Long)  query.getSingleResult();
			return (rowcount!=null)?rowcount.intValue():0;
	}
	private static StringBuffer createStringQuery(AuditoriaPlataformaBean criterio, String order, String columnSort,boolean isCount) {
		StringBuffer sbf = new StringBuffer();
		boolean where = false;
		if(!isCount){
			sbf.append("SELECT a FROM ViewAuditoriaPlataformaJPA a WHERE 1=1");
		}else{
			sbf.append("SELECT count(a) FROM ViewAuditoriaPlataformaJPA a WHERE 1=1 ");
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getSourceName())){
			sbf.append(" AND ");
			sbf.append(" a.sourceName=:paramEntidad");
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getSourceDescription())){
			sbf.append(" AND ");
			sbf.append(" upper(a.sourceDescription) like upper(concat(concat('%',:paramName),'%'))");			
		}		
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getAdtUsuario())){
			sbf.append(" AND ");
			sbf.append(" upper(a.adtUsuario) like upper(concat(concat('%',:paramModificador),'%'))");			
		}
		
		
		if(criterio!=null){
			if(criterio.getFechaDesde()!=null&&criterio.getFechaHasta()!=null){
				sbf.append(" AND ");
				sbf.append(" ( a.adtFecha>=to_timestamp(:paramDesde,'dd/MM/yyyy HH24:Mi')" +
						" and a.adtFecha<=to_timestamp(:paramHasta,'dd/MM/yyyy HH24:Mi'))");
			}else if(criterio.getFechaDesde()!=null&&criterio.getFechaHasta()==null){
				sbf.append(" AND ");
				sbf.append(" ( a.adtFecha>=to_timestamp(:paramDesde,'dd/MM/yyyy HH24:Mi') )");				
			}else if(criterio.getFechaDesde()==null&&criterio.getFechaHasta()!=null){
				sbf.append(" AND ");
				sbf.append(" ( a.adtFecha<=to_timestamp(:paramHasta,'dd/MM/yyyy HH24:Mi') )");				
			}
		}
		
		if(criterio!=null&&criterio.getLogAccion()!=null){
			sbf.append(" AND ");
			sbf.append(" a.logAccion=:paramOperacion");
		}
		if(!isCount){		
			/**
			 * 		columns.put("1","Operación");
			columns.put("2","Entidad");
			columns.put("3","Id");
			columns.put("4","Fecha");
			columns.put("5","Usuario"); 
			 * 
			 */
			if(columnSort!=null&&columnSort.equals("Operacion")){
				sbf.append(" order by a.logAccion");
			}else if(columnSort!=null&&columnSort.equals("Entidad")){
				sbf.append(" order by a.sourceName");
			}else if(columnSort!=null&&columnSort.equals("Id")){
				sbf.append(" order by a.logId");
			}else if(columnSort!=null&&columnSort.equals("Fecha")){
				sbf.append(" order by a.adtFecha");
			}else if(columnSort!=null&&columnSort.equals("Usuario")){
				sbf.append(" order by a.adtUsuario");
			}
			if(order!=null&&order.equals("1")){
				sbf.append(" ASC");
			}else if(order!=null&&order.equals("2")){
			    sbf.append(" DESC");
			}else if(null==order){
				sbf.append(" DESC");
			}
		}
		
		return sbf;
	}

	private Query setParameters(Query query, AuditoriaPlataformaBean criterio) {
		//:paramAplicacionId,:paramEntidad, :paramName, :paramModificador,:paramDesde,:paramHasta,:paramOperacion
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getSourceName())){
			query.setParameter("paramEntidad", criterio.getSourceName());
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getSourceDescription())){
			query.setParameter("paramName", criterio.getSourceDescription());
		}		
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getAdtUsuario())){
			query.setParameter("paramModificador", criterio.getAdtUsuario());
		}
		if(criterio!=null&&criterio.getFechaDesde()!=null){
			String fechaDesde = sdf.format(criterio.getFechaDesde());
			query.setParameter("paramDesde", fechaDesde+" 00:00");
		}
		if(criterio!=null&&criterio.getFechaHasta()!=null){
			String fechaHasta = sdf.format(criterio.getFechaHasta());
			query.setParameter("paramHasta", fechaHasta+" 23:59");
		}		
		if(criterio!=null&&criterio.getLogAccion()!=null){
			query.setParameter("paramOperacion", criterio.getLogAccion());
		}
		return query;
	}

	@Override
	public List<AuditoriaPlataformaBean> getAuditoriasPlataforma(
			AuditoriaPlataformaBean criterio) throws BusinessException {
		return null;
	}

	/**
	 * <p>Convertirmos una lista de AuditoriaPlataformaJPA a una lista de AuditoriasBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<AuditoriaPlataformaBean> getListAuditoriaPlataformaBean(List<ViewAuditoriaPlataformaJPA> listJPA) throws BusinessException
	{	
		List<AuditoriaPlataformaBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<AuditoriaPlataformaBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewAuditoriaPlataformaJPA auditoriaPlataformaJPA = listJPA.get(indice);
				AuditoriaPlataformaBean auditoriaPlataforma =  new AuditoriaPlataformaBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(auditoriaPlataforma, auditoriaPlataformaJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(auditoriaPlataforma);
			}
		}
			
		return result;
	}
}
