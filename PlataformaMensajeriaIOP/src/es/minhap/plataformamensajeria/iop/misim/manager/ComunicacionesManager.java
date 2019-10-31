package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.query.ComunicacionQuery;

public interface ComunicacionesManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	List<Comunicacion> getComunicaciones(ComunicacionQuery comunicacion);

	List<Comunicacion> getComunicacionesOrdenados();

	Comunicacion getComunicacion(Long idComunicacion);

//	/**
//	 * recupera el servicio por el id
//	 * 
//	 * @param servicioId
//	 * @return TblServicios
//	 */
//	public TblServicios getServicio(Long servicioId);
//	
//	/**
//	 * retorna si es multi organismo o no
//	 * 
//	 * @param servicioId
//	 * @return
//	 */
//	public boolean isMultiOrganismo(Integer servicioId);
//
//	/**
//	 * Insertamos un nuevo servicio
//	 *
//	 * @param servicio
//	 * @param source
//	 * @param accion
//	 * @param accionId
//	 * @return
//	 */
//	public Long insert(TblServicios servicio, String source, String accion, Long accionId);
//
//	/**
//	 * Actualizamos el servicio
//	 *
//	 * @param servicio
//	 * @param source
//	 * @param accion
//	 * @param accionId
//	 * @return
//	 */
//	public void update(TblServicios servicio, String source, String accion, Long accionId);
//
//	
//
}
