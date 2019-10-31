package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblDestinatariosHist;

public interface TblDestinatariosHistManager {

	/**
	 * Obtiene un mensaje historico a partir del id pasado
	 * 
	 * @param idMensaje
	 * @return TblMensajesHist
	 */
	List<TblDestinatariosHist> convertDestinatarioTODestinatarioHist(Long idMensaje);

	/**
	 * Inserta el destinatario historicos
	 * 
	 * @param m
	 * @return Long
	 */
	Long insert(TblDestinatariosHist d);

	/**
	 * Elimina el destinatario historicos
	 * 
	 * @param m
	 */
	void eliminar(Long id);

	/**
	 * Obtiene el destinatario historico by id
	 * 
	 * @param destinatarioId
	 * @return TblDestinatariosHist
	 */
	TblDestinatariosHist getDestinatario(Long destinatarioId);

	/**
	 * obtiene los destinatarios de un email
	 * 
	 * @param mensajeId
	 * @return ArrayList<String>
	 */
	List<String> getDestinatarios(Long mensajeid);
	

}
