package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.plataformamensajeria.iop.beans.RecepcionSMSBean;
import es.minhap.sim.model.TblLotesEnvios;
import es.minhap.sim.model.TblServicios;

public interface TblLotesEnviosManager {

	/**
	 * Inserta un lote nuevo con los datos necesarios del envío
	 * 
	 * @param<T> servicioId, nombre lote, usuario, password
	 * 
	 */
	public Integer insertarLote(Long servicioId, String nombreLote,
			String usuario, String password);

	/**
	 * Inserta un lote nuevo de mensaje PREMIUM con los datos necesarios del envío
	 * 
	 * @param<T> serv, nombre lote, usuario, password
	 * @return Integer
	 * 
	 */
	Integer insertarLotePremium(TblServicios serv, String nombreLote, String usuario, String password);
	
	/**
	 * Obtiene el lote a partir de su id
	 * 
	 * @param<T> idLote
	 * 
	 */
	public TblLotesEnvios getLoteEnvioById(Long idLote);
	

	/**
	 * Obtiene la informaci�n del lote
	 * 
	 * @param recipient
	 * @param usuario
	 * @param password
	 * @param prefijoSMS 
	 * @return RecepcionSMSBean
	 * 
	 */
	public RecepcionSMSBean buscarInfoLote(String recipient, String usuario, String password, String prefijoSMS);
	
	
	/**
	 * Actualiza la tabla lote para la entidad pasada por parametro
	 * 
	 * @param lote
	 */
	public void update(TblLotesEnvios lote);

	/**
	 * Obtiene si para el mensaje si es de tipo multidestinatario
	 * 
	 * @param mensajeId
	 * @return
	 */
	public Boolean isMultidestinatario(Long mensajeId);

	
	/**
	 * Realiza las operaciones reenviar y anular un lote
	 * 
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @param estado 
	 * @return
	 */
	public Integer operacionesLotes(Long idLote, String usuario, String password, String estado);

	/**
	 * Cambia el estado de un lote
	 * 
	 * @param idLote
	 * @param estado
	 * @param descripcion
	 * @param controlReintentos 
	 * @param usuario
	 * @return 
	 */
	public Integer setEstadoLote(Long idLote, String estado, String descripcion, Boolean controlReintentos, String usuario);




}
