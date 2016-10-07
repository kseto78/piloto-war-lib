package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

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
@Service
public interface ServicioListadosHome {
	/**
	 * Devuelve todos los envios pendientes existentes en la vista VIEW_ENVIOSPENDIENTESPORCANAL
	 * @return
	 * @throws BusinessException
	 */
	List<EnviosPendientesCanalBean> getEnviosPendientesCanal() throws BusinessException;
	
	List<UsoServidoresBean> getUsoServidoresBean(String anyo, String mes) throws BusinessException;
	
	PaginatedList<EstadoLotesEnviosBean> getEstadosLotesEnvios(int start, int size,String anyo, String mes) throws BusinessException;
}

