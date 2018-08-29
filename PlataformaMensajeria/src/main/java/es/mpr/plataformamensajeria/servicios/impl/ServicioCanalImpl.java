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

import es.minhap.plataformamensajeria.iop.manager.TblCanalesManager;
import es.minhap.sim.model.TblCanales;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioCanalImpl")
public class ServicioCanalImpl implements ServicioCanal{

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioCanalImpl.class);
	
	/**  tbl canales manager. */
	@Resource(name="tblCanalesManagerImpl")
	private TblCanalesManager tblCanalesManager;
		
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal#getCanales()
	 */
	///MIGRADO
	@Override
	public List<CanalBean> getCanales()
			throws BusinessException {
		
		try {
			List<TblCanales> lista = tblCanalesManager.getCanalesOrdenados();
			List<CanalBean> result = getListCanalBean(lista);					
			return result;
		} 
		catch (Exception e){
			logger.error("ServicioCanalImpl - getCanales:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	/**
	 * <p>Convertirmos una lista de CanalJPA a una lista de ServidoresBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<CanalBean> getListCanalBean(List<TblCanales> lista) throws BusinessException
	{	
		List<CanalBean> result = null;
		
		if (lista!=null && !lista.isEmpty())
		{
			result = new ArrayList<CanalBean>();
		
			for (TblCanales c : lista) {
				
			CanalBean canal =  new CanalBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(canal, c);
					canal.setCanalId(c.getCanalid().intValue());
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
