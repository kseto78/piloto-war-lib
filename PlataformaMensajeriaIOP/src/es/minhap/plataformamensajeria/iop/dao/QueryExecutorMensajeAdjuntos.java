package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.sm.modelo.Adjunto;

/**
 * @author everis
 */
public interface QueryExecutorMensajeAdjuntos {
	

	public List<Adjunto> getAttachment(Long mensajeId);
	
	public List<Adjunto> getImage(Long mensajeId);

}
