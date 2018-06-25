package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;

/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorEstados {
	
	/**
	 * Obtiene el estado para un lote
	 * 
	 * @param loteId
	 * @return
	 */
	public EstadosBean getEstadoByLoteId(Long loteId);
	
	/**
	 * Obtiene el estado para un mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	public EstadosBean getEstadoByMensajeId(Long mensajeId);
	
	/**
	 * Obtiene el estado para un mensaje
	 * 
	 * @param mensajeId
	 * @param listaUsuarios
	 * @return String
	 */
	public String getEstadoByMensajeIdUsuarioPush(Long mensajeId, List<String> listaUsuarios);
}
