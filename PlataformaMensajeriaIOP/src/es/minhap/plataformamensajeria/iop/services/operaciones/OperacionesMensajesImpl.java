package es.minhap.plataformamensajeria.iop.services.operaciones;

import java.math.BigDecimal;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.OperacionesLotesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.OperacionesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.jdbc.OperacionesMensajesDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;

public class OperacionesMensajesImpl implements IOperacionesMensajesService {
	static Logger logger = Logger.getLogger(OperacionesMensajesImpl.class);

	private static Properties p = new Properties();

	private static String STATUSCODE_OK = "0000";
	private static String STATUSCODE_KO = "4000";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSCTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSDETAILS_KO = "Error en la Peticion";
	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";

	@Override
	public String reenviarMensaje(Integer idMensaje, String usuario, String password) {

		logger.debug("[reenviarMensaje] Reenviando mensaje con id ");
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		try {
			logger.debug("[reenviarMensaje] Iniciando transaccion ");
			dao.beginTransaction();
			logger.debug("[reenviarMensaje] Antes de reenviarMensaje" + idMensaje);
			BigDecimal confirmacion = dao.reenviarMensaje(idMensaje, usuario, password);
			logger.debug("[reenviarMensaje] Despues de reenviarMensaje reenviarMensaje");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error = null;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorReenviarMensaje(confirmacion.intValue());
				status.setStatusCode(confirmacion.toString());
				status.setStatusText(STATUSCTEXT_KO);
				status.setDetails(error);
			} else {
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OK);
			}
			respuestaOpracion.setStatus(status);
			logger.debug("[reenviarMensaje] Generando XML de respuesta");
		
			resultadoXML = respuestaOpracion.toXML();
		} catch (PlataformaBusinessException e) {
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}catch (Exception e){
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}
		finally {
			if (resultadoXML != null && resultadoXML.length() > 0) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}

		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String anularMensaje(Integer idMensaje, String usuario, String password) {

		logger.debug("[anularMensaje] Anulando mensaje con id " + idMensaje);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		try {
			
			logger.debug("[anularMensaje] Iniciando transaccion");
			dao.beginTransaction();
			logger.debug("[anularMensaje] Antes de anularMensaje");
			BigDecimal confirmacion = dao.anularMensaje(idMensaje, usuario, password);
			logger.debug("[anularMensaje] Despues de anularMensaje");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error = null;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorAnularMensaje(confirmacion.intValue());
				status.setStatusCode(confirmacion.toString());
				status.setStatusText(STATUSCTEXT_KO);
				status.setDetails(error);
			} else {
				//Se puede anular el mensaje. si es Multidestinatario Establecemos el estado a su lote si corresponde
				//y de los destinatarios Mensajes.
				
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OK);
			}
			respuestaOpracion.setStatus(status);
			logger.debug("[reenviarMensaje] Generando XML de respuesta");
			
			resultadoXML = respuestaOpracion.toXML();
		} catch (PlataformaBusinessException e) {
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}catch (Exception e){
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}
		finally {
			if (resultadoXML != null && resultadoXML.length() > 0) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}
		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String reenviarLote(Integer idLote, String usuario, String password) {

		logger.debug("[reenviarLote] Reenviando lote con id" + idLote);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		try {
			logger.debug("[reenviarLote] Iniciando transaccion");
			dao.beginTransaction();
			logger.debug("[reenviarLote] Antes de reenviarLote");
			BigDecimal confirmacion = dao.reenviarLote(idLote, usuario, password);
			logger.debug("[reenviarLote] Despues de reenviarLote");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error = null;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorReenviarLote(confirmacion.intValue());
				status.setStatusCode(confirmacion.toString());
				status.setStatusText(STATUSCTEXT_KO);
				status.setDetails(error);
			} else {
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OK);
			}
			respuestaOpracion.setStatus(status);
			logger.debug("[reenviarLote] Generando XML de respuesta");
			
			resultadoXML = respuestaOpracion.toXML();
		} catch (PlataformaBusinessException e) {
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}catch (Exception e){
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}
		finally {
			if (resultadoXML != null && resultadoXML.length() > 0) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}

		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String anularLote(Integer idLote, String usuario, String password) {

		logger.debug("[anularLote] Anulando lote con id " + idLote);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		try {
			logger.debug("[anularLote] Iniciando transaccion");
			dao.beginTransaction();
			logger.debug("[anularLote] Antes de anular lote");
			BigDecimal confirmacion = dao.anularLote(idLote, usuario, password);
			logger.debug("[anularLote] Despues de anular lote");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error = null;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorAnularLote(confirmacion.intValue());
				status.setStatusCode(confirmacion.toString());
				status.setStatusText(STATUSCTEXT_KO);
				status.setDetails(error);
			} else {
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OK);
			}
			respuestaOpracion.setStatus(status);
			logger.debug("[anularLote] Generando XML de respuesta");
		
			resultadoXML = respuestaOpracion.toXML();
		} catch (PlataformaBusinessException e) {
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}catch (Exception e){
			resultadoXML = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}
		finally {
			if (resultadoXML != null && resultadoXML.length() > 0) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}

		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String reenviarMensaje(OperacionesMensajesXMLBean operacionesMensajes) {

		Integer mensajeid = null;
		try {
			mensajeid = Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje());
		} catch (java.lang.NumberFormatException ex) {
		}
		if (mensajeid == null) {
			String error = WSPlataformaErrors.getErrorIdMensajeIncorrecto();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(error);
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			respuestaOpracion.setStatus(status);
			try {
				return respuestaOpracion.toXML();
			} catch (PlataformaBusinessException e) {
				return null;
			}
		}

		return this.reenviarMensaje(Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje()), operacionesMensajes.getUsuario(), operacionesMensajes.getPassword());
	}

	@Override
	public String anularMensaje(OperacionesMensajesXMLBean operacionesMensajes) {

		Integer mensajeid = null;
		try {
			mensajeid = Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje());
		} catch (java.lang.NumberFormatException ex) {
		}
		if (mensajeid == null) {
			String error = WSPlataformaErrors.getErrorIdMensajeIncorrecto();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(error);
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			respuestaOpracion.setStatus(status);
			try {
				return respuestaOpracion.toXML();
			} catch (PlataformaBusinessException e) {
				return null;
			}
		}

		return this.anularMensaje(mensajeid, operacionesMensajes.getUsuario(), operacionesMensajes.getPassword());
	}

	@Override
	public String reenviarLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes) {
		Integer idlote = null;
		try {
			idlote = Integer.parseInt(operacionesLotesMensajes.getLote());
		} catch (java.lang.NumberFormatException ex) {
		}
		if (idlote == null) {
			String error = WSPlataformaErrors.getErrorIdLoteIncorrecto();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(error);
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			respuestaOpracion.setStatus(status);
			try {
				return respuestaOpracion.toXML();
			} catch (PlataformaBusinessException e) {
				return null;
			}
		}

		return this.reenviarLote(Integer.parseInt(operacionesLotesMensajes.getLote()), operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword());
	}

	@Override
	public String anularLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes) {
		Integer idlote = null;
		try {
			idlote = Integer.parseInt(operacionesLotesMensajes.getLote());
		} catch (java.lang.NumberFormatException ex) {
		}
		if (idlote == null) {
			String error = WSPlataformaErrors.getErrorIdLoteIncorrecto();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(error);
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			respuestaOpracion.setStatus(status);
			try {
				return respuestaOpracion.toXML();
			} catch (PlataformaBusinessException e) {
				return null;
			}
		}

		return this.anularLote(Integer.parseInt(operacionesLotesMensajes.getLote()), operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword());
	}

}
