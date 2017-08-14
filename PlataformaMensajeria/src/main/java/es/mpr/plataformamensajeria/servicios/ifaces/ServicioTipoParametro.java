package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.TipoParametroBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de tipo parametro</p>
 * 
 * @author Selered
 *
 */

public interface ServicioTipoParametro {
	
	List<TipoParametroBean> getTipoParametrosServidor() throws BusinessException;
	
	List<TipoParametroBean> getTipoParametrosProveedorSMS() throws BusinessException;
	
	List<TipoParametroBean> getTipoParametrosReceptorSMS() throws BusinessException;
	
	List<TipoParametroBean> getTipoParametrosServidorPush() throws BusinessException;
	
}
