package es.minhap.plataformamensajeria.iop.services.envioPremium;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta;
import es.minhap.plataformamensajeria.iop.beans.EnvioAEATXMLBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidoresOrganismos;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblErrorMensajeLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblUrlMensajePremiumManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUrlMensajePremium;

/**
 * 
 * @author everis
 * 
 */
@Service("envioPremiumAEATService")
public class EnvioPremiumImpl implements IEnvioPremiumService {
	public final static Logger LOG = LoggerFactory.getLogger(IEnvioPremiumService.class);

	private static String getStatusTextKo = "plataformaErrores.envioPremiumAEAT.STATUSTEXT_KO";

	private static String getStatusTextOk = "plataformaErrores.envioPremiumAEAT.STATUSTEXT_OK";

	private static String getCodOk = "plataformaErrores.envioPremiumAEAT.STATUS_OK";

	private String statusTextKO;

	private String statusTextOK;

	private String codOK;

	@Resource
	private TblServiciosManager serviciosManager;

	@Resource
	private TblServidoresManager servidoresManager;

	@Resource
	private TblOrganismosManager organismosManager;

	@Resource
	private TblErrorMensajeLogManager errorMensajeLogManager;

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;

	@Resource
	TblEstadosManager estadosManager;

	@Resource
	private TblLotesEnviosManager lotesManager;

	@Resource
	private TblUrlMensajePremiumManager urlMensajePremiunManager;

	@Resource
	TblHistoricosManager hitoricosManager;

	@Autowired
	private QueryExecutorOrganismos queryExecutorOrganismos;

	@Autowired
	private QueryExecutorMensajes queryExecutorMensajes;

	@Autowired
	private QueryExecutorServidores queryExecutorServidores;

	@Autowired
	private QueryExecutorServicios queryExecutorServicios;

	@Autowired
	private QueryExecutorServidoresOrganismos queryExecutorServidoresOrganismos;

	@Autowired
	private QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	@Override
	public String enviarSMSPremium(EnvioAEATXMLBean envio, String username, String password, Integer servicio,
			String usernameMISIM, String passwordMISIM, Integer reintentos) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		statusTextKO = ps.getMessage(getStatusTextKo, null);

		Integer idMensaje = new Integer(0);
		String respuestaFinal = null;

		try {
			respuestaFinal = comprobacionesGenerales(envio, username, password, servicio, ps);

			if (null == respuestaFinal) {
				// Tratamiento de reintentos
				idMensaje = queryExecutorDestinatariosMensajes.checkIdExternoExists(envio.getIdExterno(), true);

				if (idMensaje != null) {
					// el mensaje se está procesando. Codificamos respuesta de
					// OK ala espera de que se envie.
					respuestaFinal = reenviarMensaje(envio, username, ps, idMensaje, servicio);
				} else { // Se puede crear el mensaje
					respuestaFinal = crearMensaje(envio, username, password, servicio, ps);
					Respuesta respuesta = new Respuesta();
					respuesta.loadObjectFromXML(respuestaFinal);
					idMensaje = Integer.parseInt(respuesta.getIdMensaje());
				}

			}
		} catch (Exception e) {
			LOG.error("[EnvioPremiumImpl.enviarSMSPremium] Enviando Mensaje AEAT", e);
		}
		return respuestaFinal;
	}

	@Override
	public String cambiarEstadoSMSPremium(Integer idMensaje, String statusText) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		statusTextOK = ps.getMessage(getStatusTextOk, null);
		codOK = ps.getMessage(getCodOk, null);
		statusTextKO = ps.getMessage("plataformaErrores.envioPremiumAEAT.STATUS_KO", null);
		String detailsOK = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_OK", null);
		String codKO = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_GENERAL", null);
		String detailsKO = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_GENERAL", null);
		String estadoPendienteOperadora = ps.getMessage("constantes.ESTADO_PENDIENTE_OPERADORA", null);
		String usuario = ps.getMessage("aeat.usuario.sms", null);
		String estadoIncidencia = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		Respuesta resp = new Respuesta();
		String idExterno = "";
		String respuesta = null;
		try {
			idExterno = destinatariosMensajesManager.getIdExterno(idMensaje.longValue());
			if (statusText.contains("OK")) {
				mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoPendienteOperadora, statusText, false,
						null, null, usuario, null);
				resp = codificarRespuesta(codOK, detailsOK, statusTextOK, idExterno, idMensaje);
			} else {
				resp = comprobarError(statusText, idExterno, idMensaje, ps);
				mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoIncidencia, statusText, false, null,
						null, usuario, null);
			}
			respuesta = resp.toXMLSMS(resp);
		} catch (Exception e) {
			LOG.error("[EnvioPremiumImpl.cambiarEstadoSMSPremium] Cambiando Estado Mensaje AEAT", e);
			resp = codificarRespuesta(codKO, detailsKO, statusTextKO, idExterno, idMensaje);
			try {
				respuesta = resp.toXMLSMS(resp);
			} catch (PlataformaBusinessException e1) {
				LOG.error("[EnvioPremiumImpl.cambiarEstadoSMSPremium] Cambiando Estado Mensaje AEAT", e1);
			}
		}
		return respuesta;
	}

	@Override
	public String gerUrlEndpoint(String messageId) {
		String url = "";
		try {

			if (null != messageId && Integer.parseInt(messageId) > 0) {
				TblUrlMensajePremium bean = urlMensajePremiunManager.getUrlByMensaje(Long.parseLong(messageId));
				url = bean.getUrl();
			}

		} catch (Exception e) {
			LOG.error("[EnvioPremiumImpl.gerUrlEndpoint] Obteniendo url Endpoint", e);
		}
		return url;
	}

	private Respuesta comprobarError(String statusText, String idExterno, Integer idMensaje, PropertiesServices ps) {
		Respuesta resp;
		String codErrorCredito = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_CREDITO", null);
		String detailsErrorCredito = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_CREDITO", null);
		String codErrorAuth = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_AUTENTICACION", null);
		String detailsErrorAuth = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_AUTENTICACION", null);
		String codErrorGeneral = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_GENERAL", null);

		if (statusText.contains("credit")) {
			resp = codificarRespuesta(codErrorCredito, detailsErrorCredito, statusTextKO, idExterno, idMensaje);
		} else if (statusText.contains("Authorization failed")) {
			resp = codificarRespuesta(codErrorAuth, detailsErrorAuth, statusTextKO, idExterno, idMensaje);
		} else {
			resp = codificarRespuesta(codErrorGeneral, statusText, statusTextKO, idExterno, idMensaje);
		}

		return resp;
	}

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta codificarRespuesta(String codError,
			String detailsError, String statusError, String idExterno, Integer idMensaje) {

		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = new es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta();
		res.setDetails(detailsError);
		res.setStatusCode(codError);
		res.setStatusText(statusError);
		res.setIdExterno(idExterno);
		if (null == idMensaje)
			res.setIdMensaje("");
		else
			res.setIdMensaje(idMensaje.toString());

		return res;
	}

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta peticionCorrectaSMS(
			String codOrganismoPagador, String cuerpo, String idExterno, String destinatario, String deliveryUrl,
			PropertiesServices ps) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res;

		res = comprobarOrganismoYCuerpo(codOrganismoPagador, cuerpo, idExterno, true, ps);
		if (null == res)
			res = comprobarIdExternoYDestinatario(idExterno, destinatario, deliveryUrl, ps);

		return res;
	}

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta peticionCorrectaSMSMultiOrganismo(
			String codOrganismoPagador, String cuerpo, String idExterno, String destinatario, String deliveryUrl,
			boolean organismoActivo, PropertiesServices ps) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res;

		res = comprobarOrganismoYCuerpo(codOrganismoPagador, cuerpo, idExterno, organismoActivo, ps);
		if (null == res)
			res = comprobarIdExternoYDestinatario(idExterno, destinatario, deliveryUrl, ps);

		return res;
	}

	private Respuesta comprobarIdExternoYDestinatario(String idExterno, String destinatario, String deliveryUrl,
			PropertiesServices ps) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = null;
		String codIdExterno = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_ID_EXTERNO_AEAT", null);
		String desIdExterno = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_ID_EXTERNO_AEAT", null);
		String cod2002 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2002_GENERAL", null);
		String des2002 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2002_GENERAL", null);
		String cod2020 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_0020_GENERAL", null);
		String des2020 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_0020_GENRAL", null);

		if (null == idExterno || idExterno.isEmpty()) {
			res = codificarRespuesta(codIdExterno, desIdExterno, statusTextKO, "", null);

		} else if (null == destinatario || destinatario.isEmpty()) {
			res = codificarRespuesta(cod2002, des2002, statusTextKO, idExterno, null);
		} else if (null == deliveryUrl || deliveryUrl.isEmpty()) {
			res = codificarRespuesta(cod2020, des2020, statusTextKO, idExterno, null);

		}
		return res;
	}

	private Respuesta comprobarOrganismoYCuerpo(String codOrganismoPagador, String cuerpo, String idExterno,
			boolean organismoActivo, PropertiesServices ps) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = null;
		String cod2012 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2012_GENERAL", null);
		String des2012 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2012_GENERAL", null);
		String cod0007 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2007_GENERAL", null);
		String des0007 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2007_GENERAL", null);

		if (null == codOrganismoPagador || codOrganismoPagador.isEmpty() || !organismoActivo) {
			res = codificarRespuesta(cod2012, des2012, statusTextKO, idExterno, null);

		} else if (null == cuerpo || cuerpo.isEmpty()) {
			res = codificarRespuesta(cod0007, des0007, statusTextKO, idExterno, null);

		}
		return res;
	}

	private String reenviarMensaje(EnvioAEATXMLBean envio, String username, PropertiesServices ps, Integer idMensaje,
			Integer servicio) throws PlataformaBusinessException {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		String respuesta = null;
		String estadoPendienteOperadora = ps.getMessage("constantes.ESTADO_PENDIENTE_OPERADORA", null);
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String estadoPendiente = ps.getMessage("constantes.ESTADO_PENDIENTE", null);
		statusTextOK = ps.getMessage(getStatusTextOk, null);
		codOK = ps.getMessage(getCodOk, null);
		String detailsOK = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_OK", null);
		String cod2006 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2006_GENERAL", null);
		String des2006 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2006_GENERAL", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		
		TblMensajes mensaje = mensajesManager.getMensaje(idMensaje.longValue());
		String status = mensaje.getEstadoactual();
		try{
			if (estadoPendienteOperadora.equals(status)) {
				TblUrlMensajePremium bean = urlMensajePremiunManager.getUrlByMensaje(idMensaje.longValue());
				bean.setUrl(envio.getDeliveryReportURL());
				urlMensajePremiunManager.update(bean);
				resp = codificarRespuesta(codOK, detailsOK, statusTextOK, envio.getIdExterno(), idMensaje);
				respuesta = resp.toXMLSMS(resp);
			} else if (estadoAnulado.equals(status)) {
	
				for (TblDestinatariosMensajes destinatario : destinatariosMensajesManager.getDestinatarioMensajes(idMensaje
						.longValue())) {
					mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoPendiente, null, false, null, null,
							username, null);
	
					MensajeJMS mensajeJms = new MensajeJMS();
					mensajeJms.setIdMensaje(idMensaje.toString());
					mensajeJms.setIdExterno(envio.getIdExterno());
					mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
					mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
					mensajeJms.setIdLote(queryExecutorServidores.getIdLoteByIdMensaje(idMensaje.longValue()).toString());
					mensajeJms.setUsuarioAplicacion(username);
					TblServicios servicioAEAT = serviciosManager.getServicio(servicio.longValue());
	
					sender.send(mensajeJms, 0, servicioAEAT.getServicioid().toString(), true);
				}
				resp = codificarRespuesta(codOK, detailsOK, statusTextOK, envio.getIdExterno(), idMensaje);
				respuesta = resp.toXMLSMS(resp);
			} else {
				resp = codificarRespuesta(codOK, detailsOK, statusTextOK, envio.getIdExterno(), idMensaje);
				respuesta = resp.toXMLSMS(resp);
			}
		}catch (CannotCreateTransactionException e) {
			LOG.error("EnvioPremiumImpl.crearMensaje --Error ActiveMq--", e);
			mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoAnulado, descripcionErrorActiveMq, false, null, null, username, null);
			resp = codificarRespuesta(cod2006, des2006, statusTextKO, envio.getIdExterno(), null);
				respuesta = resp.toXMLSMS(resp);
		}
		return respuesta;
	}

	private String crearMensaje(EnvioAEATXMLBean envio, String username, String password, Integer servicio,
			PropertiesServices ps) throws PlataformaBusinessException {
		String nombreLote = ps.getMessage("plataformaErrores.envioPremiumAEAT.NOMBRE_LOTE_AEAT", null);
		String cod0014 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_0014_GENERAL", null);
		String des0014 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_0014_GENERAL", null);
		String codErrorAplicacion = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_ERROR_APLICACION", null);
		String desErrorAplicacion = ps.getMessage("plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_APLICACION", null);
		String cod0016 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_0016_GENERAL", null);
		String des0016 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_0016_GENERAL", null);
		String cod2006 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2006_GENERAL", null);
		String des2006 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2006_GENERAL", null);
		String codServidorIncorrecto = ps.getMessage(
				"plataformaErrores.envioPremiumAEAT.COD_ERROR_SERVIDOR_INCORRECTO", null);
		String desServidorIncorrecto = ps.getMessage(
				"plataformaErrores.envioPremiumAEAT.DETAILS_ERROR_SERVIDOR_INCORRECTO", null);
		String codigoOK = ps.getMessage("plataformaErrores.envioPremiumAEAT.STATUS_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.envioPremiumAEAT.NOMBRE_LOTE_AEAT", null);
		String statusOK = ps.getMessage("plataformaErrores.envioPremiumAEAT.STATUSTEXT_OK", null);
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);

		Integer idLote;
		Integer idMensaje = null;
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		String respuesta;

		try{
			// creamos el lote
			idLote = lotesManager.insertarLote(servicio.longValue(), nombreLote, username, password);
			if (null == idLote) {
				resp = codificarRespuesta(cod0014, des0014, statusTextKO, envio.getIdExterno(), null);
				respuesta = resp.toXMLSMS(resp);
			} else {
				if (idLote == -1) {
					resp = codificarRespuesta(codErrorAplicacion, desErrorAplicacion, statusTextKO, envio.getIdExterno(),
							null);
					respuesta = resp.toXMLSMS(resp);
				} else if (idLote == -2 || idLote == -3) {
					resp = codificarRespuesta(cod0016, des0016, statusTextKO, envio.getIdExterno(), null);
					respuesta = resp.toXMLSMS(resp);
				} else {
					resp = codificarRespuesta(cod0014, des0014, statusTextKO, envio.getIdExterno(), null);
					respuesta = resp.toXMLSMS(resp);
				}
			}
	
			Long estadoId;
	
			// Crear Mensaje
			idMensaje = mensajesManager.insertarMensajeGISS(idLote, envio.getCuerpo(), null,
					envio.getCodOrganismoPagadorSMS(), envio.getIdExterno(), envio.getDestinatario(), username, password);
			if (null == idMensaje || idMensaje <= 0) {
				resp = codificarRespuesta(cod2006, des2006, statusTextKO, envio.getIdExterno(), null);
				respuesta = resp.toXMLSMS(resp);
			} else {
				estadoId = estadosManager.getEstadoByName(
						mensajesManager.getMensaje(idMensaje.longValue()).getEstadoactual()).getEstadoid();
				insertarUrlPremium(envio.getDeliveryReportURL(), idMensaje);
				for (TblDestinatariosMensajes destinatario : destinatariosMensajesManager.getDestinatarioMensajes(idMensaje
						.longValue())) {
					hitoricosManager.creaHistorico(idMensaje.longValue(), destinatario.getDestinatariosmensajes(),
							estadoId, null, null, null, username);
					MensajeJMS mensajeJms = new MensajeJMS();
					mensajeJms.setIdMensaje(idMensaje.toString());
					mensajeJms.setIdExterno(envio.getIdExterno());
					mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
					mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
					mensajeJms.setIdLote((null != idLote) ? idLote.toString() : "0");
					mensajeJms.setUsuarioAplicacion(username);
					TblServicios servicioAEAT = serviciosManager.getServicio(Long.parseLong(servicio.toString()));
	
					sender.send(mensajeJms, 0, servicioAEAT.getServicioid().toString(), true);
				}
			}
			if (null == respuesta) {
				resp = codificarRespuesta(codServidorIncorrecto, desServidorIncorrecto, statusTextKO, envio.getIdExterno(),
						idMensaje);
				respuesta = resp.toXMLSMS(resp);
			} else {
				resp = codificarRespuesta(codigoOK, detailsOK, statusOK, envio.getIdExterno(), idMensaje);
				respuesta = resp.toXMLSMS(resp);
	
			}
		}catch (CannotCreateTransactionException e) {
			LOG.error("EnvioPremiumImpl.crearMensaje --Error ActiveMq--", e);
			mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoAnulado, descripcionErrorActiveMq, false, null, null, username, null);
			resp = codificarRespuesta(cod2006, des2006, statusTextKO, envio.getIdExterno(), null);
				respuesta = resp.toXMLSMS(resp);
		}
		return respuesta;
	}

	private void insertarUrlPremium(String deliveryReportURL, Integer idMensaje) {
		TblUrlMensajePremium tbl = new TblUrlMensajePremium();
		tbl.setMensajeId(idMensaje.longValue());
		tbl.setUrl(deliveryReportURL);
		tbl.setReintentos(null);
		urlMensajePremiunManager.insert(tbl);

	}

	private String comprobacionesGenerales(EnvioAEATXMLBean envio, String username, String password, Integer servicio,
			PropertiesServices ps) throws PlataformaBusinessException {
		String respuestaFinal;
		// Comprobamos OrganismoActivo en Servicio
		respuestaFinal = comprobarOrganismoActivoEnServicio(envio, servicio, ps);

		// Comprobamos el servicio
		if (respuestaFinal == null)
			respuestaFinal = comprobarServicio(envio, servicio, ps);

		// Comprobamos usuario y password
		if (respuestaFinal == null)
			respuestaFinal = comprobarUsuarioPassword(envio, username, password, ps);

		// Comprobamos telefono
		if (respuestaFinal == null)
			respuestaFinal = comprobarTelefono(envio, ps);

		// Comprobamos que la aplicaci�n est� activa
		if (respuestaFinal == null)
			respuestaFinal = comprobarAplicacionActiva(envio, servicio, ps);

		return respuestaFinal;
	}

	private String comprobarAplicacionActiva(EnvioAEATXMLBean envio, Integer servicio, PropertiesServices ps)
			throws PlataformaBusinessException {
		String cod0008 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_0008_GENERAL", null);
		String des0008 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_0008_GENERAL", null);
		String apNoActiva = ps.getMessage("plataformaErrores.envioPremiumAEAT.APLICACION_NOACTIVA", null);
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;

		Integer activeApplication = queryExecutorOrganismos.checkActiveApplication(servicio);
		if (activeApplication.intValue() == 0) {
			resp = codificarRespuesta(cod0008, des0008 + apNoActiva, statusTextKO, envio.getIdExterno(), null);
			return resp.toXMLSMS(resp);
		}
		return null;
	}

	private String comprobarTelefono(EnvioAEATXMLBean envio, PropertiesServices ps) throws PlataformaBusinessException {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		String cod2000 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2000_GENERAL", null);
		String des2000 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2000_GENERAL", null);
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");
		if (Utils.validarTelefono(envio.getDestinatario(), telefonoExcepcion) == 1) {
			resp = codificarRespuesta(cod2000, des2000, statusTextKO, envio.getIdExterno(), null);
			return resp.toXMLSMS(resp);
		}
		return null;
	}

	private String comprobarUsuarioPassword(EnvioAEATXMLBean envio, String username, String password,
			PropertiesServices ps) throws PlataformaBusinessException {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		String cod2008 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_0008_GENERAL", null);
		String des2008 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_0008_GENERAL", null);
		if (null == username || null == password) {
			resp = codificarRespuesta(cod2008, des2008, statusTextKO, envio.getIdExterno(), null);
			return resp.toXMLSMS(resp);
		}
		return null;
	}

	private String comprobarServicio(EnvioAEATXMLBean envio, Integer servicio, PropertiesServices ps)
			throws PlataformaBusinessException {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		String cod2010 = ps.getMessage("plataformaErrores.envioPremiumAEAT.COD_2010_GENERAL", null);
		String des2010 = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_2010_GENERAL", null);
		if (null == servicio) {
			resp = codificarRespuesta(cod2010, des2010, statusTextKO, envio.getIdExterno(), null);
			return resp.toXMLSMS(resp);
		}
		return null;
	}

	private String comprobarOrganismoActivoEnServicio(EnvioAEATXMLBean envio, Integer servicio, PropertiesServices ps)
			throws PlataformaBusinessException {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp;
		if (!serviciosManager.isMultiOrganismo(servicio)) {
			resp = peticionCorrectaSMS(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(), envio.getIdExterno(),
					envio.getDestinatario(), envio.getDeliveryReportURL(), ps);
		} else {
			boolean activo = queryExecutorOrganismos.organismoActivoEnServicio(servicio,
					envio.getCodOrganismoPagadorSMS());
			resp = peticionCorrectaSMSMultiOrganismo(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(),
					envio.getIdExterno(), envio.getDestinatario(), envio.getDeliveryReportURL(), activo, ps);
		}

		if (null != resp) {
			return resp.toXMLSMS(resp);
		}
		return null;
	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager
	 *            the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}

	/**
	 * @return the servidoresManager
	 */
	public TblServidoresManager getServidoresManager() {
		return servidoresManager;
	}

	/**
	 * @param servidoresManager
	 *            the servidoresManager to set
	 */
	public void setServidoresManager(TblServidoresManager servidoresManager) {
		this.servidoresManager = servidoresManager;
	}

	/**
	 * @return the organismosManager
	 */
	public TblOrganismosManager getOrganismosManager() {
		return organismosManager;
	}

	/**
	 * @param organismosManager
	 *            the organismosManager to set
	 */
	public void setOrganismosManager(TblOrganismosManager organismosManager) {
		this.organismosManager = organismosManager;
	}

	/**
	 * @return the errorMensajeLogManager
	 */
	public TblErrorMensajeLogManager getErrorMensajeLogManager() {
		return errorMensajeLogManager;
	}

	/**
	 * @param errorMensajeLogManager
	 *            the errorMensajeLogManager to set
	 */
	public void setErrorMensajeLogManager(TblErrorMensajeLogManager errorMensajeLogManager) {
		this.errorMensajeLogManager = errorMensajeLogManager;
	}

	/**
	 * @return the aplicacionesManager
	 */
	public TblAplicacionesManager getAplicacionesManager() {
		return aplicacionesManager;
	}

	/**
	 * @param aplicacionesManager
	 *            the aplicacionesManager to set
	 */
	public void setAplicacionesManager(TblAplicacionesManager aplicacionesManager) {
		this.aplicacionesManager = aplicacionesManager;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager
	 *            the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}

	/**
	 * @return the destinatariosMensajesManager
	 */
	public TblDestinatariosMensajesManager getDestinatariosMensajesManager() {
		return destinatariosMensajesManager;
	}

	/**
	 * @param destinatariosMensajesManager
	 *            the destinatariosMensajesManager to set
	 */
	public void setDestinatariosMensajesManager(TblDestinatariosMensajesManager destinatariosMensajesManager) {
		this.destinatariosMensajesManager = destinatariosMensajesManager;
	}

	/**
	 * @return the lotesManager
	 */
	public TblLotesEnviosManager getLotesManager() {
		return lotesManager;
	}

	/**
	 * @param lotesManager
	 *            the lotesManager to set
	 */
	public void setLotesManager(TblLotesEnviosManager lotesManager) {
		this.lotesManager = lotesManager;
	}

	/**
	 * @return the urlMensajePremiunManager
	 */
	public TblUrlMensajePremiumManager getUrlMensajePremiunManager() {
		return urlMensajePremiunManager;
	}

	/**
	 * @param urlMensajePremiunManager
	 *            the urlMensajePremiunManager to set
	 */
	public void setUrlMensajePremiunManager(TblUrlMensajePremiumManager urlMensajePremiunManager) {
		this.urlMensajePremiunManager = urlMensajePremiunManager;
	}

	/**
	 * @return the queryExecutorOrganismos
	 */
	public QueryExecutorOrganismos getQueryExecutorOrganismos() {
		return queryExecutorOrganismos;
	}

	/**
	 * @param queryExecutorOrganismos
	 *            the queryExecutorOrganismos to set
	 */
	public void setQueryExecutorOrganismos(QueryExecutorOrganismos queryExecutorOrganismos) {
		this.queryExecutorOrganismos = queryExecutorOrganismos;
	}

	/**
	 * @return the queryExecutorMensajes
	 */
	public QueryExecutorMensajes getQueryExecutorMensajes() {
		return queryExecutorMensajes;
	}

	/**
	 * @param queryExecutorMensajes
	 *            the queryExecutorMensajes to set
	 */
	public void setQueryExecutorMensajes(QueryExecutorMensajes queryExecutorMensajes) {
		this.queryExecutorMensajes = queryExecutorMensajes;
	}

	/**
	 * @return the queryExecutorServidores
	 */
	public QueryExecutorServidores getQueryExecutorServidores() {
		return queryExecutorServidores;
	}

	/**
	 * @param queryExecutorServidores
	 *            the queryExecutorServidores to set
	 */
	public void setQueryExecutorServidores(QueryExecutorServidores queryExecutorServidores) {
		this.queryExecutorServidores = queryExecutorServidores;
	}

	/**
	 * @return the queryExecutorServicios
	 */
	public QueryExecutorServicios getQueryExecutorServicios() {
		return queryExecutorServicios;
	}

	/**
	 * @param queryExecutorServicios
	 *            the queryExecutorServicios to set
	 */
	public void setQueryExecutorServicios(QueryExecutorServicios queryExecutorServicios) {
		this.queryExecutorServicios = queryExecutorServicios;
	}

	/**
	 * @return the queryExecutorServidoresOrganismos
	 */
	public QueryExecutorServidoresOrganismos getQueryExecutorServidoresOrganismos() {
		return queryExecutorServidoresOrganismos;
	}

	/**
	 * @param queryExecutorServidoresOrganismos
	 *            the queryExecutorServidoresOrganismos to set
	 */
	public void setQueryExecutorServidoresOrganismos(QueryExecutorServidoresOrganismos queryExecutorServidoresOrganismos) {
		this.queryExecutorServidoresOrganismos = queryExecutorServidoresOrganismos;
	}

	/**
	 * @return the queryExecutorDestinatariosMensajes
	 */
	public QueryExecutorDestinatariosMensajes getQueryExecutorDestinatariosMensajes() {
		return queryExecutorDestinatariosMensajes;
	}

	/**
	 * @param queryExecutorDestinatariosMensajes
	 *            the queryExecutorDestinatariosMensajes to set
	 */
	public void setQueryExecutorDestinatariosMensajes(
			QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes) {
		this.queryExecutorDestinatariosMensajes = queryExecutorDestinatariosMensajes;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource
	 *            the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
