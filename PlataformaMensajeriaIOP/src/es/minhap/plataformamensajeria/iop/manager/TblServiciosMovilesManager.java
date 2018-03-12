package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;
import es.minhap.sim.model.TblServiciosMoviles;
import es.minhap.sim.query.TblServiciosMovilesQuery;



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


	TblServiciosMoviles getServicioMovilById(long idServicioMovil);


	List<TblServiciosMoviles> getListaServiciosMovilesByQuery(TblServiciosMovilesQuery query);


	Long insert(TblServiciosMoviles servicioMovil, String source, String accion, Long accionId);


	void update(TblServiciosMoviles servicioMovil, String source, String accion, Long accionId);


	TblServiciosMoviles getServicioMovil(Long servicioMovilId);


	List<TblServiciosMoviles> getServiciosMovilesPaginado(int start, int size, String order, String columnSort, 
			String nombreServicioMovil, boolean count);


	public void delete(Long serviciosmovilesid, String source, String accionServicioMovil, Long accionIdServicioMovil);
}
