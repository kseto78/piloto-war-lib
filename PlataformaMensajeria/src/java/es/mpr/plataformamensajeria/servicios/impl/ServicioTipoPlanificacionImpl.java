package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

import es.mpr.plataformamensajeria.beans.TipoPlanificacionBean;
import es.mpr.plataformamensajeria.model.TipoPlanificacionJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoPlanificacion;

public class ServicioTipoPlanificacionImpl implements ServicioTipoPlanificacion {
	private IPaginationJPADAO jpa;
	@Override
	public List<TipoPlanificacionBean> getTipoPlanificaciones() throws BusinessException {
		// TODO Auto-generated method stub
		List<TipoPlanificacionJPA> listJPA = null;
		TipoPlanificacionJPA  criterioJPA = new TipoPlanificacionJPA();
		try {
			
			
				listJPA= jpa.readAll(0,0,criterioJPA);
				List<TipoPlanificacionBean> result = getListTipoPlanificacionBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	@Override
	public void newTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoPlanificacionBean loadTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)
			throws BusinessException {
		// TODO Auto-generated method stub

	}
	/**
	 * <p>Convertirmos una lista de TipoPlanificacionJPA a una lista de TipoPlanificacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<TipoPlanificacionBean> getListTipoPlanificacionBean(List<TipoPlanificacionJPA> listJPA) throws BusinessException
	{	
		List<TipoPlanificacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<TipoPlanificacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				TipoPlanificacionJPA tipoPlanificacionJPA = listJPA.get(indice);
				TipoPlanificacionBean tipoPlanificacion =  new TipoPlanificacionBean();
			
				try {
					BeanUtils.copyProperties(tipoPlanificacion, tipoPlanificacionJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(tipoPlanificacion);
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
