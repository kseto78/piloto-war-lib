package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblDestinatarios;


public interface TblDestinatariosManager {

	/**
	 * inserta los destinatarios de un email
	 * 
	 * @param mensajeId
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param usuario
	 * @return ArrayList<Long>
	 */
	public  List<Long> setDestinatarios(Long mensajeId, String to, String cc, String bcc, String usuario);
	

	/**
	 * obtiene los destinatarios de un email
	 * 
	 * @param mensajeId
	 * @return ArrayList<String>
	 */
	public List<String> getDestinatarios(Long mensajeId);

	/**
	 * Elimina los destinatarios de un email
	 * 
	 * @param destinatarioid
	 */
	public void delete(Long destinatarioid);


	/**
	 * recupera el destinatario by id
	 * 
	 * @param destinatarioId
	 * @return
	 */
	public TblDestinatarios getDestinatario(Long destinatarioId);
}
