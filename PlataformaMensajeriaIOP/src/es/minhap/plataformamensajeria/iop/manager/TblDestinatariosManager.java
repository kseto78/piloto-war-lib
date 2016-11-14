package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;


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
}
