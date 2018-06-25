package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.Transformacion;
import es.minhap.misim.bus.query.TransformacionQuery;

public interface TransformacionesManager {

	/**
	 * recupera la transformacion según datos pasados
	 * 
	 * @param TransformacionQuery
	 * @return
	 */
	public List<Transformacion> getTransformaciones(TransformacionQuery transformacion);

	List<Transformacion> getTransformacionesOrdenados();

	/**
	 * Insertamos un nuevo servicio
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	public Long insert(Transformacion transformacion, String source, String accion, Long accionId);

	/**
	 * Actualizamos la transformacion
	 *
	 * @param transformacion
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */

	void update(Transformacion transformacion, String source, String accion, Long accionId);

	Transformacion getTransformacion(Long idTransformacion);
}
