package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.TipoParametroBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de tipo parametro</p>.
 *
 * @author Selered
 */

public interface ServicioTipoParametro {
	
	/**
	 * Obtener tipo parametros servidor.
	 *
	 * @return tipo parametros servidor
	 * @throws BusinessException the business exception
	 */
	List<TipoParametroBean> getTipoParametrosServidor() throws BusinessException;
	
	/**
	 * Obtener tipo parametros proveedor SMS.
	 *
	 * @return tipo parametros proveedor SMS
	 * @throws BusinessException the business exception
	 */
	List<TipoParametroBean> getTipoParametrosProveedorSMS() throws BusinessException;
	
	/**
	 * Obtener tipo parametros receptor SMS.
	 *
	 * @return tipo parametros receptor SMS
	 * @throws BusinessException the business exception
	 */
	List<TipoParametroBean> getTipoParametrosReceptorSMS() throws BusinessException;
	
	/**
	 * Obtener tipo parametros servidor push.
	 *
	 * @return tipo parametros servidor push
	 * @throws BusinessException the business exception
	 */
	List<TipoParametroBean> getTipoParametrosServidorPush() throws BusinessException;
	
}
