package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;
import java.util.Map;

import es.minhap.plataformamensajeria.sm.modelo.Servicio;

public interface QueryExecutorServicios {
	
	/**
	 * Obtiene si el servicio es �nico
	 * @param recipient
	 * @param canalId
	 * @param prefijoSMS 
	 * @return List<Long>
	 */
	public List<Long> comprobarServicioUnico(String recipient, Long canalId, String prefijoSMS);
	
	/**
	 * Obtiene el servicio a partir del organismo pagador y del canal
	 * 
	 * @param codOrganismoPagadorSMS
	 * @param canal
	 * @return
	 */
	public Integer obtenerServicio(String codOrganismoPagadorSMS, String canal);
	
	
	/**
	 * Obtiene una lista con los identificadores de los servicios y si es multiorganismo
	 *  a partir del id del mensaje 
	 * 
	 * @param idMensaje
	 * @return
	 */
	public List<String> findServicioByMessageId(Long idMensaje);

	/**
	 * Obtiene un mapa con identificador del servicio y el detalle del mismo a partir del id de mensaje
	 * y el id del servicio para multiorganismo
	 * 
	 * @param idServicio
	 * @param idMensaje
	 * @return
	 */
	public Map<Integer, Servicio> findServicioMultiorganismo(Long idServicio, Long idMensaje);
	
	/**
	 * Obtiene un mapa con identificador del servicio y el detalle del mismo a partir del id de mensaje
	 * y el id del servicio 
	 * 
	 * @param idServicio
	 * @return
	 */
	public Map<Integer, Servicio> findServicio(Long idServicio);
}
