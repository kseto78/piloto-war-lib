package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.EnviosPendientesCanalBean;
import es.mpr.plataformamensajeria.beans.EstadoLotesEnviosBean;
import es.mpr.plataformamensajeria.beans.UsoServidoresBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>
 * 
 * @author Selered
 *
 */

public interface ServicioListadosHome {
	/**
	 * Devuelve todos los envios pendientes existentes en la vista VIEW_ENVIOSPENDIENTESPORCANAL
	 * @return
	 * @throws BusinessException
	 */
	List<EnviosPendientesCanalBean> getEnviosPendientesCanal() throws BusinessException;
	
	
	/**
	 * Devuelve el resultado de los envios por servidor y tipo canal
	 * @param anyo
	 * @param mes
	 * @return
	 * @throws BusinessException
	 */
	List<UsoServidoresBean> getUsoServidoresBean(String anyo, String mes) throws BusinessException;
	
	
	/**
	 * Devuelve los estados de los lotes de envios
	 * @param start
	 * @param size
	 * @param anyo
	 * @param mes
	 * @return
	 * @throws BusinessException
	 */
	PaginatedList<EstadoLotesEnviosBean> getEstadosLotesEnvios(int start, int size,String anyo, String mes) throws BusinessException;
}

