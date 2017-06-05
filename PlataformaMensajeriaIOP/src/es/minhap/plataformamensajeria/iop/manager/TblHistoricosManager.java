package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.entity.HistoricoBean;

public interface TblHistoricosManager {

	/**
	 * Implementa la l�gica del trigger e inserta el hist�rico
	 * 
	 * @param HistoricoBean
	 * @param usuario
	 * @return
	 */
	public Integer insertarHistoricos(HistoricoBean hist, String usuario);

	
	/**
	 * Crea e inserta un Historico
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param estadoId
	 * @param servidorId
	 * @param descripcion
	 * @param usuario 
	 * @return
	 */
	Integer creaHistorico(Long mensajeId, Long destinatariosMensajesId, Long estadoId, Long servidorId, String descripcion, String subestado, String usuario);

	/**
	 * Crea e inserta un Historico
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param estadoId
	 * @param usuario 
	 * @return
	 */
	Integer creaHistoricoPremium(Long mensajeId, Long destinatariosMensajesId, Long estadoId,  String usuario);
	

	/**
	 * Crea e inserta un Historico
	 * 
	 * @param mensajeId
	 * @param List<Long>
	 * @param estadoId
	 * @param servidorId
	 * @param descripcion
	 * @return
	 */
	void creaHistoricoEmail(String mensajeId, List<Long> destinatariosMensajes,Long estadoId, Long servidorId, String descripcion, String usuario);
	
	/**
	 * Crea e inserta un Historico
	 * 
	 * @param mensajeId
	 * @param destinatariosMensajes
	 * @return
	 */
	public Long getUltimoEstadoHistorico(Long mensajeId, Long destinatariosMensajes);


	/**
	 * Comprueba si un mensaje ya ha tenido un estado enviado para el destinatario si lo tuviera
	 * 
	 * @param mensajeId
	 * @param destinatariosMensajes
	 * @return
	 */
	public Boolean checkMensajeYaEnviado(Long mensajeId, Long destinatarioMensajeId, Long estado);


}
