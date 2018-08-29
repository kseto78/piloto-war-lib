package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.CanalBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de canales</p>.
 *
 * @author Selered
 */
public interface ServicioCanal {
	
	/**
	 * Obtener canales.
	 *
	 * @return canales
	 * @throws BusinessException the business exception
	 */
	List<CanalBean> getCanales() throws BusinessException;
			
}
