package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.PlataformaBean;
import es.mpr.plataformamensajeria.model.PlataformaJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlataforma;

public class ServicioPlataformaImpl implements ServicioPlataforma {
	
	private IPaginationJPADAO jpa;
	
	@Override
	public List<PlataformaBean> getPlataformas() throws BusinessException {
		
		List<PlataformaJPA> listJPA = null;
		
		try {
			 
			listJPA= jpa.executeQuery("selectPlataformasJPA", null);
			List<PlataformaBean> result = getListPlataformaBean(listJPA);					
		
			return result;
			
		} 
		catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}
	
	/**
	 * <p>Convertirmos una lista de PlataformaJPA a una lista de PlataformaBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<PlataformaBean> getListPlataformaBean(List<PlataformaJPA> listJPA) throws BusinessException
	{	
		List<PlataformaBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<PlataformaBean>();
		
			for (int indice=0; indice<listJPA.size(); indice++) {
					
				PlataformaJPA plataformaJPA = listJPA.get(indice);
				PlataformaBean plataformaBean =  new PlataformaBean();
				
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter(defaultValue);         
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(plataformaBean, plataformaJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(plataformaBean);
			}
		}
			
		return result;
	}	
	
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

}
