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
import es.mpr.plataformamensajeria.model.views.ViewCanalJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioCanalImpl implements ServicioCanal{

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
	 * <p>Convertirmos una lista de ViewCanalJPA a una lista de CanalBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos CanalBean
	 */
	protected List<CanalBean> getListViewCanalBean(List<ViewCanalJPA> listJPA) throws BusinessException
	{	
		List<CanalBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<CanalBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewCanalJPA canalJPA = listJPA.get(indice);
				CanalBean canal =  new CanalBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(canal, canalJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(canal);
			}
		}
			
		return result;
	}
	@Override
	public List<CanalBean> getCanales()
			throws BusinessException {
		List<ViewCanalJPA> listJPA = null;
		
		try {
			listJPA= jpa.executeQuery("selectViewCanalesJPA", null);
			List<CanalBean> result = getListCanalBean(listJPA);					
			return result;
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public List<CanalBean> getCanales(CanalBean criterio)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedList<CanalBean> getCanal(int start, int size, String order,
			String columnSort, CanalBean criterio) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCanales(CanalBean criterio, EntityManager em)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newCanal(CanalBean canal) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCanal(CanalBean canal) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CanalBean loadCanal(CanalBean canal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCanal(CanalBean canal) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * <p>Convertirmos una lista de CanalJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<CanalBean> getListCanalBean(List<ViewCanalJPA> listJPA) throws BusinessException
	{	
		List<CanalBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<CanalBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewCanalJPA canalJPA = listJPA.get(indice);
				CanalBean canal =  new CanalBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(canal, canalJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(canal);
			}
		}
			
		return result;
	}
}
