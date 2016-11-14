package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;



public interface TblServiciosMovilesManager {

	/**
	 * comprueba si existe el servicio movil
	 * 
	* @param idServicioMovil
	 * @return boolean
	 */
	public Boolean checkMobileServie(String idServicioMovil);

	
	/**
	 * consulta los servicios disponibles
	 * 
	* @param idDispositivo
	 * @return List<ServicioMovil>
	 */
	public List<ServicioMovil> consultarServiciosDisponibles(String idDispositivo);

	/**
	 * obtiene el servicio m�vil a partir del ID
	 * 
	* @param idServicioMovil
	 * @return Long
	 */
	public Long getServicioMovil(long idServicioMovil);



	/**
	 * obtiene si el servicio móvil está activo y existe
	 * 
	* @param idServicioMovil
	 * @return boolean
	 */
	boolean checkMobileActiveService(String idServicioMovil);
}
