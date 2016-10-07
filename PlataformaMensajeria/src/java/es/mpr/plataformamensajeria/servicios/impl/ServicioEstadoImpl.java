package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.model.views.ViewEstadoJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de estados a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioEstadoImpl implements ServicioEstado{

	private IPaginationJPADAO jpa;

	
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
	 * <p>Convertirmos una lista de  a una lista de EstadoBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos EstadoBean
	 */
	protected List<EstadoBean> getListViewEstadoBean(List<ViewEstadoJPA> listJPA) throws BusinessException
	{	
		List<EstadoBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<EstadoBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewEstadoJPA estadoJPA = listJPA.get(indice);
				EstadoBean estado =  new EstadoBean();
			
				try {					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(estado, estadoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(estado);
			}
		}
			
		return result;
	}
	@Override
	public List<EstadoBean> getEstados()
			throws BusinessException {
		List<ViewEstadoJPA> listJPA = null;
		
		try {			
			listJPA= jpa.executeQuery("selectViewEstadosJPA",null);
			List<EstadoBean> result = getListViewEstadoBean(listJPA);					
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public List<EstadoBean> getEstados(EstadoBean criterio)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedList<EstadoBean> getCanal(int start, int size,
			String order, String columnSort, EstadoBean criterio)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalEstados(EstadoBean criterio, EntityManager em)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newEstado(EstadoBean estado) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEstado(EstadoBean estado) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EstadoBean loadEstado(EstadoBean estado) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEstado(EstadoBean estado) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
}
