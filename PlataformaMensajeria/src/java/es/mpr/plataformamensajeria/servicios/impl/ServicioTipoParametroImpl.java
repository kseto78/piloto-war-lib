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

import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.model.TipoParametroJPA;
import es.mpr.plataformamensajeria.model.views.ViewTipoParametroJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;

public class ServicioTipoParametroImpl implements ServicioTipoParametro {
	private IPaginationJPADAO jpa;
	@Override
	public List<TipoParametroBean> getTipoParametrosServidor() throws BusinessException {
		// TODO Auto-generated method stub
		List<TipoParametroJPA> listJPA = null;
		ViewTipoParametroJPA  criterioJPA = new ViewTipoParametroJPA();
		try {
			 
				String[] params = new String[]{"1","2"};
				listJPA= jpa.executeQuery("selectTipoParametrosActivos", params);
				List<TipoParametroBean> result = getListTipoParametroBean(listJPA);					
		
			return result;
			
		} 
		catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}
	@Override
	public List<TipoParametroBean> getTipoParametrosProveedorSMS() throws BusinessException {
		// TODO Auto-generated method stub
		List<TipoParametroJPA> listJPA = null;
		ViewTipoParametroJPA  criterioJPA = new ViewTipoParametroJPA();
		try {
			 
				String[] params = new String[]{"1","1"};
				listJPA= jpa.executeQuery("selectTipoParametrosActivos", params);
				List<TipoParametroBean> result = getListTipoParametroBean(listJPA);	

			
			return result;
			
		} 
		catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public List<TipoParametroBean> getTipoParametrosReceptorSMS() throws BusinessException {
		// TODO Auto-generated method stub
		List<TipoParametroJPA> listJPA = null;
		try {
			 
				String[] params = new String[]{"1","3"};
				listJPA= jpa.executeQuery("selectTipoParametrosActivos", params);
				List<TipoParametroBean> result = getListTipoParametroBean(listJPA);	

			
			return result;
			
		} 
		catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public List<TipoParametroBean> getTipoParametrosServidorPush() throws BusinessException {
		// TODO Auto-generated method stub
		List<TipoParametroJPA> listJPA = null;
		try {
			 
				String[] params = new String[]{"1","4"};
				listJPA= jpa.executeQuery("selectTipoParametrosActivos", params);
				List<TipoParametroBean> result = getListTipoParametroBean(listJPA);	

			
			return result;
			
		} 
		catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public void newTipoParametro(TipoParametroBean tipoParametro)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTipoParametro(TipoParametroBean tipoParametro)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoParametroBean loadTipoParametro(TipoParametroBean tipoParametro)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTipoParametro(TipoParametroBean tipoParametro)
			throws BusinessException {
		// TODO Auto-generated method stub

	}
	/**
	 * <p>Convertirmos una lista de TipoParametroJPA a una lista de TipoParametroBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<TipoParametroBean> getListTipoParametroBean(List<TipoParametroJPA> listJPA) throws BusinessException
	{	
		List<TipoParametroBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<TipoParametroBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				TipoParametroJPA tipoParametroJPA = listJPA.get(indice);
				TipoParametroBean tipoParametro =  new TipoParametroBean();
				
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(tipoParametro, tipoParametroJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(tipoParametro);
			}
		}
			
		return result;
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

}
