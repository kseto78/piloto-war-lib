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

import es.minhap.plataformamensajeria.iop.manager.TblTiposParametrosManager;
import es.minhap.sim.model.TblTiposParametros;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;

/**
 * The Class ServicioTipoParametroImpl.
 */
@Service("servicioTipoParametroImpl")
public class ServicioTipoParametroImpl implements ServicioTipoParametro {
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioTipoParametroImpl.class);
	
	/**  tbl tipos parametros manager. */
	@Resource(name="tblTiposParametrosManagerImpl")
	private TblTiposParametrosManager tblTiposParametrosManager;
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro#getTipoParametrosServidor()
 */
/////MIGRADO
	@Override
	public List<TipoParametroBean> getTipoParametrosServidor() throws BusinessException {
		try {
			List<TblTiposParametros> lista = tblTiposParametrosManager.listaTiposParametrosPorTipo(2);
			List<TipoParametroBean> result = getListTipoParametroBean(lista);					
		
			return result;
			
		} 
		catch (Exception e){
			logger.error("ServicioTipoParametroImpl - getTipoParametrosServidor:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro#getTipoParametrosProveedorSMS()
 */
/////MIGRADO
	@Override
	public List<TipoParametroBean> getTipoParametrosProveedorSMS() throws BusinessException {
		try {
			List<TblTiposParametros> lista = tblTiposParametrosManager.listaTiposParametrosPorTipo(1);
			List<TipoParametroBean> result = getListTipoParametroBean(lista);	
			return result;
			
		}catch (Exception e){
			logger.error("ServicioTipoParametroImpl - getTipoParametrosProveedorSMS:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro#getTipoParametrosReceptorSMS()
 */
/////MIGRADO
	@Override
	public List<TipoParametroBean> getTipoParametrosReceptorSMS() throws BusinessException {
		try {
			List<TblTiposParametros> lista = tblTiposParametrosManager.listaTiposParametrosPorTipo(3);
			List<TipoParametroBean> result = getListTipoParametroBean(lista);	
			return result;
			
		}catch (Exception e){
			logger.error("ServicioTipoParametroImpl - getTipoParametrosReceptorSMS:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro#getTipoParametrosServidorPush()
 */
/////MIGRADO
	@Override
	public List<TipoParametroBean> getTipoParametrosServidorPush() throws BusinessException {
		try {
			List<TblTiposParametros> lista = tblTiposParametrosManager.listaTiposParametrosPorTipo(4);
			List<TipoParametroBean> result = getListTipoParametroBean(lista);	
			return result;
			
		}catch (Exception e){
			logger.error("ServicioTipoParametroImpl - getTipoParametrosServidorPush:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		} 
	}

	/**
	 * <p>Convertirmos una lista de TipoParametroJPA a una lista de TipoParametroBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	protected List<TipoParametroBean> getListTipoParametroBean(List<TblTiposParametros> lista) throws BusinessException
	{	
		List<TipoParametroBean> result = null;
		
		if (lista!=null && !lista.isEmpty())
		{
			result = new ArrayList<TipoParametroBean>();
		
			for (TblTiposParametros t : lista) {
				TipoParametroBean tipoParametro =  new TipoParametroBean();
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(tipoParametro, t);
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
	
}
