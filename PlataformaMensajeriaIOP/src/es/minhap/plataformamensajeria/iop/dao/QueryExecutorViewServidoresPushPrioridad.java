package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorWebPush;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorViewServidoresPushPrioridad {

	/**
	 * Se recupera la lista de parametros de servidores push a partir del mensajeId y la plataforma
	 * 
	 * @param mensajeId
	 * @param plataformaId
	 * @return
	 */
	public List<ParametrosServidorPush> getServidoresPush(Long mensajeId, Integer plataformaId);

	/**
	 * Se recupera la lista de parametros de servidores web push a partir del mensajeId
	 * 
	 * @param mensajeId
	 * @return
	 */
	public List<ParametrosServidorWebPush> getServidoresWebPush(Long mensajeId);
	
	
}
