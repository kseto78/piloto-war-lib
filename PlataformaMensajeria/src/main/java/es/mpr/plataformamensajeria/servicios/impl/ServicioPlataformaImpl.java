package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.plataformamensajeria.iop.manager.TblPlataformasManager;
import es.minhap.sim.model.TblPlataformas;
import es.mpr.plataformamensajeria.beans.PlataformaBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlataforma;

@Service("servicioPlataformaImpl")
public class ServicioPlataformaImpl implements ServicioPlataforma {
	
	private static Logger logger = Logger.getLogger(ServicioProveedorSMSImpl.class);
	
	@Resource(name="TblPlataformasManagerImpl")
	private TblPlataformasManager tblPlataformasManager;
	
	///MIGRADO
	@Override
	public List<PlataformaBean> getPlataformas() throws BusinessException {
		try {
			List<TblPlataformas> lista = tblPlataformasManager.getPlataformasActivas();
			List<PlataformaBean> result = getListPlataformaBean(lista);					
		
			return result;		
		} 
		catch (Exception e){
			logger.error("ServicioPlataformaImpl - getPlataformas:" + e);
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
	///MIGRADO
	protected List<PlataformaBean> getListPlataformaBean(List<TblPlataformas> lista) throws BusinessException
	{	
		List<PlataformaBean> result = null;
		
		if (lista!=null && !lista.isEmpty()){
			result = new ArrayList<PlataformaBean>();
			for (TblPlataformas p : lista) {
				PlataformaBean plataformaBean =  new PlataformaBean();
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter(defaultValue);         
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(plataformaBean, p);
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
}
