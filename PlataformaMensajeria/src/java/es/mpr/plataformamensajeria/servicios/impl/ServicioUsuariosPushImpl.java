package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.model.views.ViewUsuariosPushJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios movil a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioUsuariosPushImpl implements ServicioUsuariosPush{

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
	
	static HashMap<Integer,Integer> mapPermisosUsuarioAplicacion = null;
	static String rolUsuario = null;
	
	@Override
	public PaginatedList<UsuariosPushBean> getUsuariosPush(int start, int size, String order,
			String columnSort, UsuariosPushBean criterio, boolean isExport, HttpServletRequest request) throws BusinessException {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>)request.getSession().
				getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String)request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		//Columna para ordenar
		Hashtable<String, String> columns = new Hashtable<String,String>();
		columns.put("0","UsuarioId");
		columns.put("1","NombreUsuario");
		columns.put("2","Aplicacion");
		columns.put("3","Servicio");
		columns.put("4","Plataforma");
		columns.put("5","Fecha");
		if (columnSort==null){
			columnSort = "5"; //Fecha
		}
		String column = columns.get(columnSort);
		if (column==null){
			column = "Fecha";
		}
		PaginatedList<UsuariosPushBean> result = null; 
		StringBuffer sbf = createStringQuery(criterio,order,column,false);
		StringBuffer sbfCount = createStringQuery(criterio, order, columnSort, true);
		Query query =  entityManager.createQuery(sbf.toString());
		query=setParameters(query,criterio);
		if(size>0){
			query.setFirstResult(start);
			query.setMaxResults(size);
		}
		List<ViewUsuariosPushJPA> listJPA = query.getResultList();
		List<UsuariosPushBean> listaAuditoriaBean = getListUsuariosMovilBean(listJPA);
		//Total de usuarios Push
		Integer rowcount = getTotalUsuariosPush(criterio,entityManager,sbfCount); 
		
		result = new PaginatedList<UsuariosPushBean>();
		result.setPageList(listaAuditoriaBean);
		result.setTotalList(rowcount);

		return result;
	}
	
	@Override
	public Integer getTotalUsuariosPush(UsuariosPushBean criterio,
			EntityManager em, StringBuffer sbfCount) throws BusinessException {
			Query query =  em.createQuery(sbfCount.toString());
			query = setParameters(query, criterio);
			Long rowcount = (Long)  query.getSingleResult();
			return (rowcount!=null)?rowcount.intValue():0;
	}
	
	private static StringBuffer createStringQuery(UsuariosPushBean criterio, String order, String columnSort, boolean isCount) {
		StringBuffer sbf = new StringBuffer();
		if(!isCount){
			sbf.append("SELECT a FROM ViewUsuariosPushJPA a WHERE 1=1");
		}else{
			sbf.append("SELECT count(a) FROM ViewUsuariosPushJPA a WHERE 1=1 ");
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getPlataforma())){
			sbf.append(" AND ");		
			sbf.append(" upper(a.plataforma) like upper(concat(concat('%',:paramPlataforma),'%'))");
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId())){
			sbf.append(" AND ");
			sbf.append(" a.servicioId=:paramServicioId");
		}	
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId())){
			sbf.append(" AND ");
			sbf.append(" a.aplicacionId=:paramAplicacionId");	
		} else if(criterio!=null&&PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId())){
			if(mapPermisosUsuarioAplicacion!=null&&(!PlataformaMensajeriaUtil.isEmpty(rolUsuario)
					&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))){
				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();
				
				sbf.append(" AND ");
				sbf.append(" a.aplicacionId in (");	
				
				boolean first=true;
				while(itAplicaciones.hasNext()){
					Integer idAplicacion = itAplicaciones.next();
					if(!first){
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first=false;
				}
				sbf.append(") ");	
			}
		}
		
		if(criterio!=null){
			if(criterio.getFechaDesde()!=null&&criterio.getFechaHasta()!=null){
				sbf.append(" AND ");
				sbf.append(" ( a.fecha>=to_timestamp(:paramDesde,'dd/MM/yyyy HH24:Mi')" +
						" and a.fecha<=to_timestamp(:paramHasta,'dd/MM/yyyy HH24:Mi'))");
			}else if(criterio.getFechaDesde()!=null&&criterio.getFechaHasta()==null){
				sbf.append(" AND ");
				sbf.append(" ( a.fecha>=to_timestamp(:paramDesde,'dd/MM/yyyy HH24:Mi') )");				
			}else if(criterio.getFechaDesde()==null&&criterio.getFechaHasta()!=null){
				sbf.append(" AND ");
				sbf.append(" ( a.fecha<=to_timestamp(:paramHasta,'dd/MM/yyyy HH24:Mi') )");				
			}
		}
		
		if(!isCount){
			if(columnSort!=null&&columnSort.equals("UsuarioId")){
				sbf.append(" order by a.usuarioId");
			}else if(columnSort!=null&&columnSort.equals("NombreUsuario")){
				sbf.append(" order by a.nombreUsuario");
			}else if(columnSort!=null&&columnSort.equals("Plataforma")){
				sbf.append(" order by a.plataforma");
			}else if(columnSort!=null&&columnSort.equals("Servicio")){
				sbf.append(" order by a.servicio");
			}else if(columnSort!=null&&columnSort.equals("Aplicacion")){
				sbf.append(" order by a.aplicacion");
			}else if(columnSort!=null&&columnSort.equals("Fecha")){
				sbf.append(" order by a.fecha");
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

	private Query setParameters(Query query, UsuariosPushBean criterio) {
		//:paramPlataforma,:paramServicioId, :paramAplicacionId, :paramDesde,:paramHasta
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getPlataforma())){
			query.setParameter("paramPlataforma", criterio.getPlataforma());
		}
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId())){
			query.setParameter("paramServicioId", criterio.getServicioId());
		}		
		if(criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId())){
			query.setParameter("paramAplicacionId", criterio.getAplicacionId());
		}
		if(criterio!=null&&criterio.getFechaDesde()!=null){
			String fechaDesde = sdf.format(criterio.getFechaDesde());
			query.setParameter("paramDesde", fechaDesde+" 00:00");
		}
		if(criterio!=null&&criterio.getFechaHasta()!=null){
			String fechaHasta = sdf.format(criterio.getFechaHasta());
			query.setParameter("paramHasta", fechaHasta+" 23:59");
		}		
		
		return query;
	}

	@Override
	public List<UsuariosPushBean> getUsuariosPush(
			UsuariosPushBean criterio) throws BusinessException {
		return null;
	}

	/**
	 * <p>Convertirmos una lista de usuariosPushJPA a una lista de AuditoriasBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuariosPushBean
	 */
	protected List<UsuariosPushBean> getListUsuariosMovilBean(List<ViewUsuariosPushJPA> listJPA) throws BusinessException
	{	
		List<UsuariosPushBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UsuariosPushBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuariosPushJPA usuariosPushJPA = listJPA.get(indice);
				UsuariosPushBean usuariosPush =  new UsuariosPushBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(usuariosPush, usuariosPushJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(usuariosPush);
			}
		}
			
		return result;
	}
}
