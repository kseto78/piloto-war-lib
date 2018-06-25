package es.minhap.plataformamensajeria.iop.manager;

import java.util.Date;
import java.util.List;

public interface MigracionFilesystemManager {

	/**
	 * obtiene un Map con los mensajes por servicio
	 * 
	 * @param mensajeId
	 * @param fechaInicio
	 * @param fechaFin
	 * @param historico
	 * @param max
	 * @param indice
	 * @return List<Object>
	 */
	public List<Object> obtenerMensajesMigracion(Long mensajeId, Date fechaInicio, Date fechaFin, Boolean historico, Integer max, Integer indice);

	
	/**
	 * cuenta los mensajes a migrar
	 * 
	 * @param mensajeId
	 * @param fechaInicio
	 * @param fechaFin
	 * @param historico
	 * @return Integer
	 */
	public Integer countMensajesMigracion(Long idMensaje, Date fechaInicio, Date fechaFin, Boolean historicos);
	
	
}
