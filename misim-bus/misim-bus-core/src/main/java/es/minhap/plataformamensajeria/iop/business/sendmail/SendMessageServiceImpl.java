package es.minhap.plataformamensajeria.iop.business.sendmail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.smtp.SMTPAddressFailedException;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.DatosEspecificos;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionEnvioXML;
import es.minhap.plataformamensajeria.iop.beans.SMTPAuthenticator;
import es.minhap.plataformamensajeria.iop.business.beans.push.DatosEspecificosPush;
import es.minhap.plataformamensajeria.iop.business.beans.push.DatosEspecificosWebPush;
import es.minhap.plataformamensajeria.iop.business.beans.push.NotificacionDataRequest;
import es.minhap.plataformamensajeria.iop.business.beans.push.PeticionPush;
import es.minhap.plataformamensajeria.iop.business.beans.push.PeticionWebPush;
import es.minhap.plataformamensajeria.iop.business.beans.recepcionsms.DatosEspecificosRecepcionSMS;
import es.minhap.plataformamensajeria.iop.business.beans.recepcionsms.EnvioAplicacionRequest;
import es.minhap.plataformamensajeria.iop.business.beans.recepcionsms.PeticionRecepcionSMS;
import es.minhap.plataformamensajeria.iop.business.common.ICommonUtilitiesService;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAplicaciones;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajeAdjuntos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidoresOrganismos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewReceptoresPrioridad;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewServidoresPrioridad;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblErrorMensajeLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.sm.modelo.Adjunto;
import es.minhap.plataformamensajeria.sm.modelo.DestinatarioDMensaje;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionWebPushData;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorWebPush;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblErrorMensajeLog;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("sendMessageService")
public class SendMessageServiceImpl implements ISendMessageService {

	private static final Logger LOG = LoggerFactory.getLogger(SendMessageServiceImpl.class);

	@Autowired
	private QueryExecutorServidoresOrganismos queryExecutorServidoresOrganismos;

	@Resource
	private TblServidoresManager servidoresManager;

	@Resource
	private TblServiciosManager serviciosManager;

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Autowired
	private QueryExecutorMensajes queryExecutorMensajes;

	@Autowired
	private QueryExecutorMensajeAdjuntos queryExecutorMensajeAdjuntos;

	@Autowired
	private QueryExecutorViewServidoresPrioridad queryExecutorViewServidoresPrioridad;

	@Autowired
	private QueryExecutorServidores queryExecutorServidores;

	@Autowired
	private QueryExecutorLotesEnvios queryExecutorLotesEnvios;

	@Autowired
	private QueryExecutorServicios queryExecutorServicios;

	@Autowired
	private QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes;

	@Autowired
	private QueryExecutorViewReceptoresPrioridad queryExecutorViewReceptoresPrioridad;

	@Autowired
	private QueryExecutorAplicaciones queryExecutorAplicaciones;

	@Resource
	private TblErrorMensajeLogManager errorMensajeLogManager;

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;

	@Resource
	private ICommonUtilitiesService commonUtilitiesService;

	@Autowired(required = true)
	private SIMMessageSender messageSender;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Autowired
	private SessionFactory sessionFactorySIMApp;
	
	private String urlDestinoOperador = null;

	/**
	 * @return the sessionFactorySIMApp
	 */
	public SessionFactory getSessionFactorySIMApp() {
		return sessionFactorySIMApp;
	}

	/**
	 * @param sessionFactorySIMApp
	 *            the sessionFactorySIMApp to set
	 */
	public void setSessionFactorySIMApp(SessionFactory sessionFactorySIMApp) {
		this.sessionFactorySIMApp = sessionFactorySIMApp;
	}

	/************************************/
	/*** INICIO TRATAMIENTO SNS ***/
	/************************************/
	@Override
	@Transactional
	public String postSMS(Long idMensaje, Long loteId, Long destinatarioMensajeId, String codOrganismo,
			String usuarioAplicacion, String passAplicacion, String aplicacionPremium) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String okEnvio = ps.getMessage("constantes.ESTADO_PENDIENTE_OPERADORA", null);
		String koEnvio = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		String usuarioMISIM = ps.getMessage("usuarioMISIM", null);
		String passMISIM = ps.getMessage("passwordMISIM", null);
		Integer numMaxReintentos = null;
		Map<Integer, Servicio> mapServicios = null;
		Integer proveedorID = null;
		String resultadoSMS = null;
		Servicio s = null;
		TblServicios serv = null;
		SMSData smsData = commonUtilitiesService.getSMSData(idMensaje, destinatarioMensajeId);

		boolean sendOK = false;
		int numServidores = smsData.Servers.size();
		mapServicios = commonUtilitiesService.getMapServicios(idMensaje);
		boolean encontrado = false;
		int indice = 0;
		if (numServidores > 0) {
			if (mapServicios.size() > 0) {
				for (ParametrosProveedor pp : smsData.Servers) {
					if (mapServicios.containsKey(Integer.valueOf(pp.getProveedorId()))) {
						proveedorID = Integer.valueOf(pp.getProveedorId());
						encontrado = true;
						break;
					}
					indice++;
				}
			} else {
				proveedorID = Integer.valueOf(((ParametrosProveedor) smsData.Servers.get(indice)).getProveedorId());
			}
			if (encontrado) {
				registerSMSParametersDebug(smsData, indice);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("Llamamos a Envio SMS (postSMS)");
			}
			try {
				s = (Servicio) mapServicios.get(proveedorID);
				smsData.ServiceData.setHeaderSMS(s.getHeaderSMS());
				mensajesManager.setEstadoMensaje(Long.valueOf(idMensaje),
						ps.getMessage("constantes.ESTADO_ENVIANDO", null), "", false, smsData.destinatarioMensajeId,
						null, null, null);
				resultadoSMS = sendSMS(smsData, idMensaje, loteId, s, indice, codOrganismo, usuarioAplicacion,
						passAplicacion, usuarioMISIM, passMISIM);
				if (LOG.isInfoEnabled()) {
					LOG.info("Respuesta: " + resultadoSMS);
				}
				if (resultadoSMS.contains("OK")) {
					sendOK = true;
				}
			} catch (Exception e) {
				LOG.error("Se ha producido un error", e);
				String errorMessage = e.getMessage();
				if (LOG.isInfoEnabled())
					LOG.info("Excepcion :" + errorMessage + " (postSMS)");

				TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
				tblErrorMensajeLog.setCodigoerror(new Long(0));
				tblErrorMensajeLog.setFecha(new Date());
				tblErrorMensajeLog.setOperacion("postSMS");
				tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + idMensaje + ". Error: (" + e.hashCode() + ") "
						+ errorMessage);
				errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			}
			///////
//			sendOK = true;
//			resultadoSMS = "OK | 3892164636401109584";
			if (sendOK) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Mensaje numero " + idMensaje + " enviado (SMS)");
				}
				mensajesManager.setEstadoMensaje(idMensaje, okEnvio, resultadoSMS, false,
						smsData.destinatarioMensajeId, null, null, proveedorID.longValue());
				
				// encolamos en cola RefreshStatus si método consulta = 1
				// (Consulta de estado)(Tempos)
				TblMensajes mensaje = mensajesManager.getMensaje(idMensaje);
				if (mensaje.getMetodoconsulta()){
					if (null != s &&  null != s.getServicioId()){
						serv = serviciosManager.getServicio(s.getServicioId().longValue());
						numMaxReintentos = (null != serv && null != serv.getReintentosConsultaEStado()) ? serv
								.getReintentosConsultaEStado() : Integer.parseInt(ps.getMessage(
								"constantes.consultaEstado.numMaxReintentos", null));
					}
					encolarMsjRefreshStatus(smsData, idMensaje, numMaxReintentos, mensaje, aplicacionPremium, ps);
				}
			} else {
				mensajesManager.setEstadoMensaje(idMensaje, koEnvio, resultadoSMS, false,
						smsData.destinatarioMensajeId, null, null, proveedorID.longValue());
				throw new Exception("[SendMessageServiceImpl.postSMS] -ERROR ENVIANDO SMS- KO Recibido enviando SMS: " + idMensaje + " Descripcion: " +resultadoSMS);
			}
		} else {
			mensajesManager.setEstadoMensaje(Long.valueOf(idMensaje),
					ps.getMessage("constantes.ESTADO_INCIDENCIA", null), "SMS_ID: " + idMensaje
							+ ". Error: No existe ningun Servidor Disponible", false, smsData.destinatarioMensajeId,
					null, null, null);
			throw new Exception("[SendMessageServiceImpl.postSMS] -ERROR ENVIANDO SMS- No existe ningun Servidor Disponible: " + idMensaje);
		}
		
		return urlDestinoOperador;

	}

	private void registerSMSParametersDebug(SMSData smsData, int indice) {
		ParametrosProveedor ps = (ParametrosProveedor) smsData.Servers.get(indice);
		String url = "";
		String id = "";
		String telefono = "";
		String texto = "";
		String uim = "";
		if (ps.getUrl() != null) {
			url = ps.getUrl();
		}
		if (ps.getId() != null) {
			id = ps.getId();
		}
		if (ps.getTelefono() != null) {
			telefono = ps.getTelefono();
		}
		if (ps.getTexto() != null) {
			texto = ps.getTexto();
		}
		if (ps.getUIM() != null) {
			uim = ps.getUIM();
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("----------------------------");
			LOG.debug("DATOS DEL PROVEEDOR (postSMS)");
			LOG.debug("----------------------------");
			LOG.debug("Url: " + url + " (postSMS)");
			if (id != "") {
				LOG.debug("Id:" + id + " (postSMS)");
			}
			if (telefono != "") {
				LOG.debug("Telefono: " + telefono + " (postSMS)");
			}
			if (texto != "") {
				LOG.debug("Texto: " + texto + " (postSMS)");
			}
			if (uim != "") {
				LOG.debug("Texto: " + uim + " (postSMS)");
			}
			LOG.debug("-----------------------------------");
		}
	}

	@Transactional
	private String sendSMS(SMSData smsData, Long idMensaje, Long loteId, Servicio s, int indice,
			String codigoOrganismo, String usuarioAplicacion, String passAplicacion, String usernameMISIM,
			String passwordMISIM) {
		PeticionEnvioXML envio = new PeticionEnvioXML();
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		try {
			ParametrosProveedor pp = (ParametrosProveedor) smsData.Servers.get(indice);
			TblServidoresQuery query = new TblServidoresQuery();
			query.setServidorid(Long.valueOf(pp.getProveedorId()));
			TblServidores servidor = servidoresManager.getServidor(query);
			if (servidor != null) {
				LOG.debug("Recuperado proveedor: " + servidor.getServidorid());
			} else {
				LOG.debug("No encontrado proveedor");
				return null;
			}
			
			//Declaramos una variable global con la url del operador para poder obtenerla en postSMS
			//y poder especificarla en los errores del graylog
			urlDestinoOperador = servidor.getUrldestino();

			// generamos la peticion de envio
			envio.setUsuario(usernameMISIM);
			envio.setPassword(passwordMISIM);
			envio.setProveedor(servidor.getNombre());
			envio.setMensajeId(idMensaje.toString());
			envio.setProducto("SMS");
			envio.setUrlEndpoint(servidor.getUrldestino());
			envio.setIdLote(loteId.toString());
			DatosEspecificos de = new DatosEspecificos();
			de.setSMS_DESTINATARIO(smsData.Telefono);
			de.setSMS_HEADER(smsData.ServiceData.getHeaderSMS());
			de.setSMS_ID(idMensaje.toString());
			de.setSMS_TEXTO(smsData.Body);

			String usuario = queryExecutorServidoresOrganismos.getUsuario(codigoOrganismo, pp.getProveedorId());
			String pass = queryExecutorServidoresOrganismos.getPassword(codigoOrganismo, pp.getProveedorId());

			de.setSMS_USUARIO((null != usuario) ? usuario : s.getProveedorUsuarioSMS());
			de.setSMS_PASSWORD((null != pass) ? pass : s.getProveedorPassSMS());

			envio.setDatosEspecificos(de);

			String res = commonUtilitiesService.sendMessage(envio, ps.getMessage("constantes.SOAP_ACTION", null),
					ps.getMessage("constantes.RECEPT_QUEUE", null), idMensaje);

			if (res.contains("OK")) {
				TblMensajes mensaje = mensajesManager.getMensaje(Long.valueOf(idMensaje));
				mensaje.setMetodoconsulta(servidor.getMetodoconsulta());
				mensajesManager.update(mensaje);
				sessionFactorySIMApp.getCurrentSession().flush();
			}

			return res;

		} catch (Exception ex) {
			LOG.error("SendMessageServiceImpl [sendSMS]", ex);
		}
		return "";
	}

	private void encolarMsjRefreshStatus(SMSData smsData, Long idMensaje, Integer numMaxReintentos, TblMensajes mensaje, String aplicacionPremium, PropertiesServices ps)
			throws InterruptedException {
		LOG.info("Starting Refresh Listener Message: " + idMensaje);
		String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
		String usuarioAeat = ps.getMessage("aeat.usuario.sms", null, "aeat");
		MensajeJMS mns = new MensajeJMS();
		if (null != smsData.destinatarioMensajeId) {
//			mns.setIdExterno(destinatariosMensajesManager.getDestinatarioMensaje(smsData.destinatarioMensajeId)
//					.getCodigoexterno());
			mns.setDestinatarioMensajeId(smsData.destinatarioMensajeId.toString());
		} else {
//			mns.setIdExterno(mensaje.getCodigoexterno());
		}

//		mns.setCodSia(mensaje.getCodsia());
		mns.setIdMensaje(mensaje.getMensajeid().toString());
		mns.setIdLote(mensajesManager.getIdLoteByIdMensaje(mensaje.getMensajeid()).toString());
		
		if (null != aplicacionPremium && usuarioAeat.toLowerCase().contains(aplicacionPremium.toLowerCase()) ){
			mns.setUsuarioAplicacion(aplicacionPremium);	
		}

		try {
			messageSender.sendRefresh(mns, numMaxReintentos);
		} catch (CannotCreateTransactionException e) {
			LOG.error(errorActiveMq+" HiloEnviarMensajesPremium.run --Error ActiveMq-- Mensaje: " + mns.getIdMensaje());
		}	
		

	}

	/************************************/
	/*** INICIO TRATAMIENTO EMAILS ******/
	/************************************/
	@Override
	public void postMail(Long mensajeId, String destinatarioMensajeId) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String ejecutarTodosServidores = ps.getMessage("mail.ejecutarTodosServidores", null);
		String rutaTemporal = ps.getMessage("constantes.RUTA_ARCHIVO_TEMPORAL", null);
		String okEnvio = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String koEnvio = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		StringBuilder errorMessage = new StringBuilder();
		MailData mailData = new MailData();
		boolean sendOK = false;
		int indice = 0;
		List<String> lista = Arrays.asList(destinatarioMensajeId.split(";"));

		if (LOG.isDebugEnabled()) {
			LOG.debug("Intentamos recuperar los datos del mail con id: " + mensajeId + " destinatarioMensaje: "
					+ lista.toString() + " (postMail)");
		}
		if (lista.size() == 1)
			mailData = getMailData(mensajeId, Long.parseLong(lista.get(0)));
		else
			mailData = getMailData(mensajeId, null);
		sendOK = false;
		if (mailData.Servers.isEmpty()) {
			mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId),
					ps.getMessage("constantes.ESTADO_INCIDENCIA", null), "MAIL_ID: " + mensajeId
							+ ". Error: No existe ningun Servidor Disponible", false, mailData.destinatarioMensajeId,
					null, null, null);
			TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
			tblErrorMensajeLog.setCodigoerror(new Long("0"));
			tblErrorMensajeLog.setDescripcionerror("Error: No existe ningun Servidor Disponible");
			tblErrorMensajeLog.setFecha(new Date());
			tblErrorMensajeLog.setOperacion("postMail");
			errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			throw new Exception("[SendMessageServiceImpl.postMail] -ERROR ENVIANDO EMAIL- No existe ningun Servidor Disponible: " + mensajeId);
		} else {
			if ((!mailData.Subject.isEmpty()) || (!mailData.Body.isEmpty())) {
				registerMailDetailsDebug(mailData);
				mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId),
						ps.getMessage("constantes.ESTADO_ENVIANDO", null), "", false, mailData.destinatarioMensajeId,
						null, null, null);

				int numServidores = mailData.Servers.size();
				if (LOG.isDebugEnabled()) {
					LOG.debug("Numero de servidores: " + numServidores);
				}
				if ("S".equals(ejecutarTodosServidores)) {
					while ((indice < numServidores) && (!sendOK)) {
						registerMailParametersDebug(mailData, indice);
						if (LOG.isDebugEnabled()) {
							LOG.debug("Llamamos a sendMail (postMail)");
						}
						try {
							// LOG.info("BEFORE SENDMAIL TS");
							sendMail(mailData, indice, rutaTemporal);
							// LOG.info("AFTER SENDMAIL TS");
							sendOK = true;

						} catch (Exception e) {
							sendOK = false;
							errorMessage.append(e.getMessage());
							//Exception ex = e;
							LOG.error("Excepcion :" + errorMessage + " (postMail)", e);

							TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
							tblErrorMensajeLog.setCodigoerror(new Long("0"));
							tblErrorMensajeLog.setDescripcionerror("MAIL_ID: " + mensajeId + ". Error: ("
									+ e.hashCode() + ") " + errorMessage);
							tblErrorMensajeLog.setFecha(new Date());
							tblErrorMensajeLog.setOperacion("postMail");
							errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
//							if ((ex instanceof SMTPAddressFailedException)) {
//								SMTPAddressFailedException smtpEx = (SMTPAddressFailedException) ex;
//								errorMessage.append(". Address: " + smtpEx.getAddress());
//								errorMessage.append(". Command failed: " + smtpEx.getCommand()
//										+ ". Reason for the failure: ");
//								errorMessage.append(smtpEx.getReturnCode() + ". Error: " + smtpEx.getMessage());
//							} else if ((ex instanceof SendFailedException)) {
//								SendFailedException sfex = (SendFailedException) ex;
//								Address[] invalid = sfex.getInvalidAddresses();
//								if (invalid.length <= 0) {
//									errorMessage.append(". Causa: " + sfex.getCause());
//								} else {
//									errorMessage.append(". Mail: " + invalid[0].toString() + " Causa: "
//											+ sfex.getCause());
//								}
//							}
							

							sendOK = false;

							indice++;
						}
					}
				} else { // SOLO PROBAMOS EL ENVIO DEL EMAIL EN EL
							// PRIMER SERVIDOR RECUPERADO
					if (numServidores > 0) {
						registerMailParametersDebug(mailData, indice);
						if (LOG.isDebugEnabled()) {
							LOG.debug("Llamamos a sendMail (postMail)");
						}
						try {
							// LOG.info("BEFORE SENDMAIL");
							sendMail(mailData, indice, rutaTemporal);
							// LOG.info("AFTER SENDMAIL");
							sendOK = true;

						} catch (Exception e) {
							sendOK = false;
							errorMessage.append(e.getMessage());
							Exception ex = e;
							if ((ex instanceof SMTPAddressFailedException)) {
								SMTPAddressFailedException smtpEx = (SMTPAddressFailedException) ex;
								errorMessage.append(". Address: " + smtpEx.getAddress());
								errorMessage.append(". Command failed: " + smtpEx.getCommand()
										+ ". Reason for the failure: ");
								errorMessage.append(smtpEx.getReturnCode() + ". Error: " + smtpEx.getMessage());
							} else if ((ex instanceof SendFailedException)) {
								SendFailedException sfex = (SendFailedException) ex;
								Address[] invalid = sfex.getInvalidAddresses();
								if (invalid.length <= 0) {
									errorMessage.append(". Causa: " + sfex.getCause());
								} else {
									errorMessage.append(". Mail: " + invalid[0].toString() + " Causa: "
											+ sfex.getCause());
								}
							}
							LOG.error("Excepcion :" + errorMessage.toString() + " (postMail)", e);

							TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
							tblErrorMensajeLog.setCodigoerror(new Long("0"));
							tblErrorMensajeLog.setDescripcionerror("MAIL_ID: " + mensajeId + ". Error: ("
									+ e.hashCode() + ") " + errorMessage);
							tblErrorMensajeLog.setFecha(new Date());
							tblErrorMensajeLog.setOperacion("postMail");
							errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);

							sendOK = false;
						}
					}
				}
			}
			// MANDAR MENSAJE COMO QUE SE HA ENVIADO CORRECTAMENTE O QUE
			// HA HABIDO UN ERROR
			Long servidorId = Long.valueOf(((ParametrosServidor) mailData.Servers.get(indice)).getServidor());
			if (sendOK) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Mail numero " + mensajeId + " enviado (postMail) ");
				}
				// LOG.info("BEFORE SETESTADOMENSAJE");
				mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId), okEnvio, "Mensaje Enviado Correctamente",
						false, mailData.destinatarioMensajeId, null, null, servidorId);
				// LOG.info("AFTER SETESTADOMENSAJE");
			} else {
				mensajesManager.setEstadoMensaje(
						Long.valueOf(mensajeId),
						koEnvio,
						" Error: Se ha producido un error en el envio del Mensaje. Detalle del Error: "
								+ errorMessage.toString(), false, mailData.destinatarioMensajeId, null, null,
						servidorId);
				throw new Exception("[SendMessageServiceImpl.postMail] -ERROR ENVIANDO EMAIL- KO Recibido enviando EMAIL: " + mensajeId + " Descripcion: " +errorMessage.toString());
			}
		}

	}

	private void sendMail(MailData mailData, int indice, String rutaTemporal) throws Exception {
		boolean debug = false;

		ArrayList<File> listaFicherosTemporales = new ArrayList<File>();
		
		Properties props = new Properties();

		ParametrosServidor ps = (ParametrosServidor) mailData.Servers.get(indice);

		props.put("mail.smtp.host", ps.getIP().toString());
//		props.put("mail.smtp.host", "localhost");
		if (LOG.isDebugEnabled()) {
			LOG.debug("Con el Servidor: " + ps.getIP().toString() + " (SendMail)");
		}
		if (ps.getPuerto() != "") {
			props.put("mail.smtp.port", ps.getPuerto());
		} else {
			props.put("mail.smtp.port", Integer.valueOf(25));
		}
		
//		props.put("mail.smtp.port", 385);

		Session session = null;
		Authenticator auth = null;
		if (ps.getTimeOut() != "") {
			int timeout = Integer.parseInt(ps.getTimeOut()) * 1000;
			if (LOG.isDebugEnabled()) {
				LOG.debug("Con Tiempo de Espera " + ps.getTimeOut() + " milisegundos(SendMail)");
			}
			props.put("mail.smtp.connectiontimeout", Integer.valueOf(timeout));
			props.put("mail.smtp.timeout", Integer.valueOf(timeout));
		}
		if ((ps.getConexion() != null) && (ps.getConexion().equals("true"))) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Con conexion segura (SendMail)");
			}
			props.setProperty("mail.smtp.starttls.enable", "true");
		}
		if ((ps.getAutentificacion() != null) && (ps.getAutentificacion().equals("true"))) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Con autenticacion basica (SendMail)");
			}
			props.put("mail.smtp.auth", Boolean.valueOf(true));
			auth = new SMTPAuthenticator(ps.getUsuario(), ps.getContrasena());
			session = Session.getInstance(props, auth);
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Sin autenticacion basica (SendMail)");
			}
			props.put("mail.smtp.auth", Boolean.valueOf(false));
			session = Session.getInstance(props, null);
		}
		session.setDebug(debug);

		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		if (mailData.ServiceData.getFromMailNombre() != null) {
			addressFrom = new InternetAddress(mailData.ServiceData.getFromMail(),
					mailData.ServiceData.getFromMailNombre());

			msg.setFrom(addressFrom);
		} else {
			addressFrom = new InternetAddress(mailData.ServiceData.getFromMail());

			msg.setFrom(addressFrom);
		}
		InternetAddress[] addressTo = generateInternetAddress(mailData.Recipients.To);
		InternetAddress[] addressCC = generateInternetAddress(mailData.Recipients.Cc);
		InternetAddress[] addressBCC = generateInternetAddress(mailData.Recipients.Bcc);

		msg.setRecipients(Message.RecipientType.TO, addressTo);
		msg.setRecipients(Message.RecipientType.CC, addressCC);
		msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		if (LOG.isDebugEnabled()) {
			LOG.debug("-----------------------------------");
			LOG.debug("Subject (SendMail)");
			LOG.debug(mailData.Subject);
			LOG.debug("-----------------------------------");
			LOG.debug("Body (SendMail)");
			LOG.debug(mailData.Body + " (SendMail)");
			LOG.debug("-----------------------------------");
		}
		msg.setSubject(mailData.Subject, mailData.TipoCodificacion);

		Multipart multipart = null;
		if (mailData.Images.size() > 0) {
			multipart = new MimeMultipart("related");
		} else {
			multipart = new MimeMultipart();
		}
		MimeBodyPart cuerpo = new MimeBodyPart();
		String body = updateBody(mailData.Body, multipart, rutaTemporal, listaFicherosTemporales);
		String typeContent = mailData.TipoCuerpo + "; charset=" + mailData.TipoCodificacion;

		cuerpo.setContent(body, typeContent);
		multipart.addBodyPart(cuerpo);
		if (LOG.isDebugEnabled()) {
			LOG.debug("-----------------------------------------");
			LOG.debug("Adjuntos: " + mailData.Attachments.size());
		}
		if (mailData.Attachments.size() > 0) {
			addAttachToMail(mailData.Attachments, multipart, rutaTemporal, listaFicherosTemporales);
		}
		if (mailData.Images.size() > 0) {
			addImagesToMail(mailData.Images, multipart, rutaTemporal, listaFicherosTemporales);
		}
		msg.setContent(multipart);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Realizamos el envio desde mail.jar (SendMail)");
		}
		Transport.send(msg);
		
		if(!listaFicherosTemporales.isEmpty()){	//Borramos los ficheros temporales
			for(File f:listaFicherosTemporales){
				f.delete();
			}
		}		

	}

	private void registerMailDetailsDebug(MailData mailData) {
		if (LOG.isDebugEnabled()) {
			String correosTO = convertRecipientsToString(mailData.Recipients.To);
			String correosCC = convertRecipientsToString(mailData.Recipients.Cc);
			String correosBCC = convertRecipientsToString(mailData.Recipients.Bcc);

			LOG.debug("-----------------------------------");
			LOG.debug("DATOS DE ENVIO DE CORREO (postMail)");
			LOG.debug("-----------------------------------");
			LOG.debug("RecipientsTO: " + correosTO + " (postMail)");
			LOG.debug("RecipientsCC: " + correosCC + " (postMail)");
			LOG.debug("RecipientsBCC: " + correosBCC + " (postMail)");
			LOG.debug("Subject: " + mailData.Subject + " (postMail)");
		}
	}

	private String convertRecipientsToString(ArrayList<DestinatarioDMensaje> recipients) {
		String correos = "";
		for (int i = 0; i < recipients.size(); i++) {
			if (i == 0) {
				correos = correos + ((DestinatarioDMensaje) recipients.get(i)).email;
			} else {
				correos = correos + ", " + ((DestinatarioDMensaje) recipients.get(i)).email;
			}
		}
		return correos;
	}

	private void addAttachToMail(ArrayList<Adjunto> attachs, Multipart multipart, String rutaTemporal, ArrayList<File> listaFicheros)
			throws FileNotFoundException, SQLException, IOException, MessagingException {
		for (int indice = 0; indice < attachs.size(); indice++) {
			FileOutputStream fos = null;
			File file = File.createTempFile(rutaTemporal, String.valueOf(indice));
			listaFicheros.add(file);
			fos = new FileOutputStream(file);
			MimeBodyPart adjunto = new MimeBodyPart();
			Blob bin = ((Adjunto) attachs.get(indice)).getContenido();
			InputStream input = bin.getBinaryStream();

			int size = (int) bin.length();
			byte[] buffer = new byte[size];
			int length = -1;
			while ((length = input.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
			if (fos != null) {
				fos.close();
			}
			input.close();

			DataSource source1 = new FileDataSource(file);
			adjunto.setDataHandler(new DataHandler(source1));
			adjunto.setFileName(((Adjunto) attachs.get(indice)).getNombre());
			multipart.addBodyPart(adjunto);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Se ha adjuntado el documento " + ((Adjunto) attachs.get(indice)).getNombre()
						+ " en el HTML (SendMail)");
			}
		}
	}

	private void addImagesToMail(ArrayList<Adjunto> images, Multipart multipart, String rutaTemporal, ArrayList<File> listaFicheros)
			throws FileNotFoundException, SQLException, IOException, MessagingException {
		for (int indice = 0; indice < images.size(); indice++) {
			FileOutputStream fos = null;
			File file = File.createTempFile(rutaTemporal, String.valueOf(indice));
			listaFicheros.add(file);
			fos = new FileOutputStream(file);
			Blob bin = ((Adjunto) images.get(indice)).getContenido();
			InputStream input = bin.getBinaryStream();

			int size = (int) bin.length();
			byte[] buffer = new byte[size];
			int length = -1;
			while ((length = input.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
			if (fos != null) {
				fos.close();
			}
			input.close();

			MimeBodyPart imagen = new MimeBodyPart();
			DataSource source1 = new FileDataSource(file);
			imagen.setDataHandler(new DataHandler(source1));
			imagen.setHeader("Content-ID", "<" + ((Adjunto) images.get(indice)).getNombre() + ">");

			multipart.addBodyPart(imagen);
			if (LOG.isInfoEnabled()) {
				LOG.info("Imagen Embebida " + ((Adjunto) images.get(indice)).getNombre() + " se ha anadido (SendMail)");
			}
		}
	}

	private String updateBody(String body, Multipart multipart, String rutaTemporal, ArrayList<File> listaFicheros) throws IOException,
			MessagingException {
		String newBody = "";
		String[] partImages = body.split("<img");
		int cont = 0;
		for (String part : partImages) {
			if (cont > 0) {
				String img = "<img" + part.substring(0, part.split(">")[0].length() + 1);
				try {
					String base64 = img.split("src=\"data:image")[1].split(",")[1].split("\"")[0];

					byte[] bytes = Base64.decodeBase64(base64.getBytes());
					FileOutputStream fos = null;
					File file = File.createTempFile("File",null,new File(rutaTemporal));
					listaFicheros.add(file);
					fos = new FileOutputStream(file);
					int size = bytes.length;
					fos.write(bytes, 0, size);
					if (fos != null) {
						fos.close();
					}
					MimeBodyPart imagen = new MimeBodyPart();
					DataSource source1 = new FileDataSource(file);
					imagen.setDataHandler(new DataHandler(source1));
					imagen.setHeader("Content-ID", "<imagen00" + cont + ">");
					multipart.addBodyPart(imagen);

					String texto = img.split("src=")[1].split("\"")[1];
					newBody = newBody + "<img"
							+ part.replace(texto, new StringBuilder().append("cid:imagen00").append(cont).toString());
				} catch (ArrayIndexOutOfBoundsException e) {
					newBody = newBody + "<img" + part;
				}
			} else {
				newBody = part;
			}
			cont++;
		}
		if (!newBody.isEmpty()) {
			return newBody;
		}
		return body;
	}

	private InternetAddress[] generateInternetAddress(ArrayList<DestinatarioDMensaje> recipients)
			throws AddressException {
		InternetAddress[] address = new InternetAddress[recipients.size()];
		for (int i = 0; i < recipients.size(); i++) {
			address[i] = new InternetAddress(((DestinatarioDMensaje) recipients.get(i)).email);
		}
		return address;
	}

	private void registerMailParametersDebug(MailData mailData, int indice) {
		String mailFrom = mailData.ServiceData.getFromMail();
		ParametrosServidor ps = (ParametrosServidor) mailData.Servers.get(indice);
		String smtpServer = "";
		String smtpUserName = "";
		String smtpPassword = "";
		String smtpPort = "";
		if (ps.getIP() != null) {
			smtpServer = ps.getIP();
		}
		if (ps.getUsuario() != null) {
			smtpUserName = ps.getUsuario();
		}
		if (ps.getContrasena() != null) {
			smtpPassword = ps.getContrasena();
		}
		if (ps.getPuerto() != null) {
			smtpPort = ps.getPuerto();
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("----------------------------");
			LOG.debug("DATOS DEL SERVIDOR (postMail)");
			LOG.debug("----------------------------");
			LOG.debug("Smtp server: " + smtpServer + " (postMail)");
			LOG.debug("Sender: " + mailFrom + " (postMail)");
			if (smtpUserName != "") {
				LOG.debug("Smtp user name:" + smtpUserName + " (postMail)");
			}
			if (smtpPassword != "") {
				LOG.debug("Smpt password: " + smtpPassword + " (postMail)");
			}
			if (smtpPort != "") {
				LOG.debug("Puerto: " + smtpPort + " (postMail)");
			}
			LOG.debug("-----------------------------------");
		}
	}

	private MailData getMailData(Long mensajeId, Long destinatarioMensajeId) throws SQLException {
		MailData data = new MailData();

		try {

			if (queryExecutorLotesEnvios.esMultidestinatario(mensajeId)) {
				MailData mailDataComun = new MailData();
				mailDataComun.Attachments = (ArrayList<Adjunto>) queryExecutorMensajeAdjuntos.getAttachment(mensajeId);
				mailDataComun.Images = (ArrayList<Adjunto>) queryExecutorMensajeAdjuntos.getImage(mensajeId);
				mailDataComun.Servers = (ArrayList<ParametrosServidor>) queryExecutorViewServidoresPrioridad
						.getServidores(mensajeId);
				mailDataComun.ServiceData = queryExecutorMensajes.getDataFromServices(mensajeId);
				data = queryExecutorMensajes.getDetailsMultidestinatario(mensajeId);
				data.esMultidestinatario = true;
				if (null != destinatarioMensajeId) {
					data.Recipients = queryExecutorDestinatariosMensajes.getRecipientsMultidestinatarioModo1(mensajeId,
							destinatarioMensajeId);
					data.destinatarioMensajeId = destinatarioMensajeId;
				} else
					data.Recipients = queryExecutorDestinatariosMensajes.getRecipientsMultidestinatario(mensajeId);
				data.Attachments = mailDataComun.Attachments;
				data.Images = mailDataComun.Images;
				data.Servers = mailDataComun.Servers;
				data.ServiceData = mailDataComun.ServiceData;

			} else {// no multidestinatario
				data = queryExecutorMensajes.getDetails(mensajeId);

				data.Recipients = queryExecutorDestinatariosMensajes.getRecipients(mensajeId);
				data.Attachments = (ArrayList<Adjunto>) queryExecutorMensajeAdjuntos.getAttachment(mensajeId);
				data.Images = (ArrayList<Adjunto>) queryExecutorMensajeAdjuntos.getImage(mensajeId);
				data.Servers = (ArrayList<ParametrosServidor>) queryExecutorViewServidoresPrioridad
						.getServidores(mensajeId);
				data.ServiceData = queryExecutorMensajes.getDataFromServices(mensajeId);
			}
		} catch (Exception e) {
			LOG.error("Error en getMailData : ", e);
		}
		return data;
	}

	// ---- Fin tratamieento para EMAILS

	/******************************************/
	/*** TRATAMIENTO NOTIFICACIONES PUSH *****/
	/******************************************/

	@Override
	public void postNotificacionPush(Long mensajeId, Long loteId, Long destinatarioMensaje) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String okEnvio = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String koEnvio = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		String ejecutarTodosServidores = ps.getMessage("push.ejecutarTodosServidores", null);
		String usuarioMISIM = ps.getMessage("usuarioMISIM", null);
		String passMISIM = ps.getMessage("passwordMISIM", null);
		Integer servidorPushID = Integer.valueOf(0);
		NotificacionPushData notificacionPushData;
		int indice = 0;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Intentamos recuperar los datos de la notificacion Push con id: " + mensajeId
					+ " (postNotificacionPush)");
		}
		notificacionPushData = getNotificacionPushData(mensajeId, destinatarioMensaje);
		if (notificacionPushData != null) {
			LOG.info("getNotificacionPushData distinto a null y mayor que 0");
		} else {
			LOG.info("getNotificacionPushData null o vacio");
		}

		boolean sendOK = false;
		String resultNotificacionPush = "";
		if (null == notificacionPushData.servers || notificacionPushData.servers.isEmpty()) {
			
			if (null == notificacionPushData.getUsuarioId()){
				mensajesManager.setEstadoMensaje(mensajeId, estadoAnulado, "Error: El usuario ha sido eliminado",
						false, destinatarioMensaje, null, null, null);
				LOG.info("Mensaje Anulado, el usuario ha sido eliminado");
			}else{
				
				mensajesManager.setEstadoMensaje(mensajeId, koEnvio, null,
						false, notificacionPushData.getDestinatarioMensajeId(), null, null, null);
				
				mensajesManager.setEstadoMensaje(mensajeId, estadoAnulado, "Error: No existe ningun Servidor Push Disponible",
						false, notificacionPushData.getDestinatarioMensajeId(), null, null, null);
				TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
				tblErrorMensajeLog.setCodigoerror(new Long("0"));
				tblErrorMensajeLog.setDescripcionerror("Error: No existe ningun Servidor Push Disponible");
				tblErrorMensajeLog.setFecha(new Date());
				tblErrorMensajeLog.setOperacion("postNotificacionPush");
				errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
				throw new Exception("[SendMessageServiceImpl.postNotificacionPush] -ERROR ENVIANDO PUSH- No existe ningun Servidor Disponible: " + mensajeId);
			}
		} else {
			registerServidorPushDetailsDebug(notificacionPushData);
			mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId),
					ps.getMessage("constantes.ESTADO_ENVIANDO", null), "", false,
					notificacionPushData.getDestinatarioMensajeId(), null, null, null);

			int numServidoresPush = notificacionPushData.servers.size();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Numero de servidores Push: " + numServidoresPush);
			}
			if ("S".equals(ejecutarTodosServidores)) {
				while ((indice < numServidoresPush) && (!sendOK)) {
					registerServidorPushParametersDebug(notificacionPushData, indice);

					servidorPushID = Integer
							.valueOf(((ParametrosServidorPush) notificacionPushData.servers.get(indice))
									.getServidorPushId());
					if (LOG.isDebugEnabled()) {
						LOG.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
					}
					try {
						resultNotificacionPush = sendServidorPushByMISIM(notificacionPushData, mensajeId, loteId,
								indice, usuarioMISIM, passMISIM);
						if (LOG.isInfoEnabled()) {
							LOG.info("Respuesta: " + resultNotificacionPush);
						}
						if (resultNotificacionPush.contains("OK")) {
							sendOK = true;
						}
					} catch (Exception e) {
						String errorMessage = e.getMessage();
						LOG.error("Excepcion :" + errorMessage + " (postNotificacionPush)", e);
						TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
						tblErrorMensajeLog.setCodigoerror(new Long("0"));
						tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + mensajeId + ". Error: (" + e.hashCode()
								+ ") " + errorMessage);
						tblErrorMensajeLog.setFecha(new Date());
						tblErrorMensajeLog.setOperacion("postNotificacionPush");
						errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
					}
					indice++;
				}
			} else {
				if (numServidoresPush > 0) {
					registerServidorPushParametersDebug(notificacionPushData, indice);

					servidorPushID = Integer
							.valueOf(((ParametrosServidorPush) notificacionPushData.servers.get(indice))
									.getServidorPushId());
					if (LOG.isDebugEnabled()) {
						LOG.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
					}
					try {
						resultNotificacionPush = sendServidorPushByMISIM(notificacionPushData, mensajeId, loteId,
								indice, usuarioMISIM, passMISIM);
						if (LOG.isInfoEnabled()) {
							LOG.info("Respuesta: " + resultNotificacionPush);
						}
						if (resultNotificacionPush.contains("OK")) {
							sendOK = true;
						}
					} catch (Exception e) {
						String errorMessage = e.getMessage();
						LOG.error("Excepcion :" + errorMessage + " (postNotificacionPush)", e);

						TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
						tblErrorMensajeLog.setCodigoerror(new Long("0"));
						tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + mensajeId + ". Error: (" + e.hashCode()
								+ ") " + errorMessage);
						tblErrorMensajeLog.setFecha(new Date());
						tblErrorMensajeLog.setOperacion("postNotificacionPush");
						errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
					}
				}
			}

			if (sendOK) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Notificacion numero " + mensajeId + " enviado (postNotificacionPush)");
				}
				mensajesManager.setEstadoMensaje(mensajeId, okEnvio, resultNotificacionPush, false,
						notificacionPushData.getDestinatarioMensajeId(), null, null, servidorPushID.longValue());

			} else {
				mensajesManager.setEstadoMensaje(mensajeId, koEnvio, resultNotificacionPush, false,
						notificacionPushData.getDestinatarioMensajeId(), null, null, servidorPushID.longValue());
				throw new Exception("[SendMessageServiceImpl.postNotificacionPush] -ERROR ENVIANDO PUSH- KO Recibido enviando PUSH: " + mensajeId + " Descripcion: " +resultNotificacionPush);
			}
		}
	}

	private NotificacionPushData getNotificacionPushData(Long mensajeId, Long destinatarioMensajeId)
			throws SQLException {
		NotificacionPushData res = new NotificacionPushData();
		try {
			NotificacionPushData smsDataComun = new NotificacionPushData();
			smsDataComun.serviceData = queryExecutorMensajes.getDataFromServices(mensajeId);
			if (null != destinatarioMensajeId) {
				smsDataComun.esMultidestinatario = true;
				res = queryExecutorMensajes.getDetailsServidorPushMultidestinatario(mensajeId, destinatarioMensajeId,
						smsDataComun.serviceData);
			} else {
				res = queryExecutorMensajes.getDetailsServidorPush(mensajeId, smsDataComun.serviceData);
			}
		} catch (Exception e) {
			TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
			tblErrorMensajeLog.setCodigoerror(new Long("0"));
			tblErrorMensajeLog.setDescripcionerror("Error: GetNotificacionPushData : " + e.getMessage());
			tblErrorMensajeLog.setFecha(new Date());
			tblErrorMensajeLog.setOperacion("postNotificacionPush");
			errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			LOG.error("getNotificacionPushData : " + e.getMessage(), e);
		}
		return res;
	}

	private String sendServidorPushByMISIM(NotificacionPushData messageData, Long messageId, Long loteId,
			Integer indice, String usuarioMISIM, String passMISIM) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String resultado = "";
		messageData.setIndice("" + indice);
		messageData.setMessageID("" + messageId);
		String proveedor = "";
		PeticionPush peticionPush = new PeticionPush();
		peticionPush.setMensajeId(messageId.toString());
		peticionPush.setProducto("PUSH");
		peticionPush.setUsuario(usuarioMISIM);
		peticionPush.setPassword(passMISIM);
		peticionPush.setIdLote(loteId.toString());
		
		NotificacionDataRequest ndr = new NotificacionDataRequest();

		ParametrosServidorPush psp = (ParametrosServidorPush) messageData.servers.get(indice);
		TblServidoresQuery tblServQuery = new TblServidoresQuery();
		tblServQuery.setServidorid(Long.valueOf(messageData.servers.get(indice).getServidorPushId()));		
		List<TblServidores> listServ = servidoresManager.getServidoresByQuery(tblServQuery);
		if(!listServ.isEmpty()){
			proveedor = listServ.get(0).getNombre();
			peticionPush.setProveedor(proveedor);
		}
		
		if ((null != psp) && (psp.getPlataformaId() == 1)) {
			if ((null != messageData.tokensUsuario) && (!messageData.tokensUsuario.isEmpty())) {
				ndr.setfCMApiKey(messageData.fCMApiKey);

				ndr.setBadge(messageData.badge);
			} else {
				resultado = "KO | El username no esta dado de alta en la plataforma Android";
			}
		} else if (psp.getPlataformaId() == 2) {
			if ((null != messageData.tokensUsuario) && (!messageData.tokensUsuario.isEmpty())) {
				//Solo seteamos el nodo notificacionSilenciosa para el caso apple
				peticionPush.setNotificacionSilenciosa((messageData.getNotificacionSilenciosa() != null && messageData.getNotificacionSilenciosa())?messageData.getNotificacionSilenciosa():false);
				ndr.setRutaCertificadoAPNS(messageData.rutaCertificadoAPNS);
				ndr.setPasswordCertificadoAPNS(messageData.passwordCertificadoAPNS);
				ndr.setBadge(messageData.badge);
				ndr.setPuertoUrl(psp.getPuertoUrl() + "");
				ndr.setUrlFeedback(psp.getUrlFeedback() + "");
				ndr.setPuertoUrlFeedback(psp.getPuertoUrlFeedback() + "");
			} else {
				resultado = "KO | El username no esta dado de alta en la plataforma iOS";
			}
		}
		if (!resultado.equals("")) {
			return resultado;
		}
		ndr.setToken(messageData.tokensUsuario);
		ndr.setUrl(psp.getUrl());
		ndr.setCabecera(messageData.cabecera);
		ndr.setCuerpo(messageData.cuerpo);
		DatosEspecificosPush datosEspecificosPush = new DatosEspecificosPush();
		datosEspecificosPush.setNotificacionDataRequest(ndr);
		peticionPush.setDatosEspecificos(datosEspecificosPush);

		String res = commonUtilitiesService.sendMessage(peticionPush, ps.getMessage("constantes.SOAP_ACTION", null),
				ps.getMessage("constantes.RECEPT_QUEUE", null), messageId);

		return res;
	}

	private void registerServidorPushDetailsDebug(NotificacionPushData notificacionPushData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("----------------------------------------------------------");
			LOG.debug("DATOS DE ENVIO DE NOTIFICACION PUSH (postNotificacionPush)");
			LOG.debug("----------------------------------------------------------");
			LOG.debug("Titulo: " + notificacionPushData.cabecera + " (postNotificacionPush)");

			LOG.debug("Cuerpo: " + notificacionPushData.cuerpo + " (postNotificacionPush)");
		}
	}

	private void registerServidorPushParametersDebug(NotificacionPushData notificacionPushData, int indice) {
		ParametrosServidorPush ps = (ParametrosServidorPush) notificacionPushData.servers.get(indice);
		int plataformaId = 0;
		String url = "";
		String urlFeedback = "";
		int puertoUrl = 0;
		int puertoUrlFeedback = 0;
		plataformaId = ps.getPlataformaId();
		if (ps.getUrl() != null) {
			url = ps.getUrl();
		}
		if (ps.getUrlFeedback() != null) {
			urlFeedback = ps.getUrlFeedback();
		}
		puertoUrl = ps.getPuertoUrl();
		puertoUrlFeedback = ps.getPuertoUrlFeedback();
		if (LOG.isDebugEnabled()) {
			LOG.debug("-------------------------------------");
			LOG.debug("DATOS DEL RECEPTOR (postNotificacionPush)");
			LOG.debug("-------------------------------------");
			LOG.debug("PlataformaId: " + plataformaId + " (postNotificacionPush)");
			if (url != "") {
				LOG.debug("URL: " + url + " (postNotificacionPush)");
			}
			if (urlFeedback != "") {
				LOG.debug("URLFeedback: " + urlFeedback + " (postNotificacionPush)");
			}
			LOG.debug("PuertoURL: " + puertoUrl + " (postNotificacionPush)");
			LOG.debug("PuertoURLFeedback: " + puertoUrlFeedback + " (postNotificacionPush)");

			LOG.debug("-----------------------------------");
		}
	}

	// ---- Fin tratamiento notificaciones PUSH
	
	/******************************************/
	/*** TRATAMIENTO NOTIFICACIONES PUSH *****/
	/******************************************/
	
	@Override
	public void postNotificacionWebPush(Long mensajeId, Long idLote, Long destinatarioMensajeId) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String okEnvio = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String koEnvio = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		String ejecutarTodosServidores = ps.getMessage("webpush.ejecutarTodosServidores", null);
		String usuarioMISIM = ps.getMessage("usuarioMISIM", null);
		String passMISIM = ps.getMessage("passwordMISIM", null);
		Integer servidorWebPushID = Integer.valueOf(0);
		NotificacionWebPushData notificacionWebPushData;
		int indice = 0;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Intentamos recuperar los datos de la notificacion Push con id: " + mensajeId
					+ " (postNotificacionPush)");
		}
		notificacionWebPushData = getNotificacionWebPushData(mensajeId, destinatarioMensajeId);
		if (notificacionWebPushData != null) { //comprobar si lo he eliminado y viene vacío, anulo el mensaje del tiron.
			LOG.info("getNotificacionPushData distinto a null y mayor que 0");
		} else {
			LOG.info("getNotificacionPushData null o vacio");
		}

		boolean sendOK = false;
		String resultNotificacionPush = "";
		if (null == notificacionWebPushData.servers || notificacionWebPushData.servers.isEmpty()) {
			if (null == notificacionWebPushData.getUsuarioId()){
				mensajesManager.setEstadoMensaje(mensajeId, estadoAnulado, "Error: El usuario ha sido eliminado",
						false, destinatarioMensajeId, null, null, null);
				LOG.info("Mensaje Anulado, el usuario ha sido eliminado");
			}else{
				mensajesManager.setEstadoMensaje(mensajeId, koEnvio, "Error: No existe ningun Servidor Push Disponible",
						false, notificacionWebPushData.getDestinatarioMensajeId(), null, null, null);
				TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
				tblErrorMensajeLog.setCodigoerror(new Long("0"));
				tblErrorMensajeLog.setDescripcionerror("Error: No existe ningun Servidor Web Push Disponible");
				tblErrorMensajeLog.setFecha(new Date());
				tblErrorMensajeLog.setOperacion("postNotificacionWebPush");
				errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
				throw new Exception("[SendMessageServiceImpl.postNotificacionWebPush] -ERROR ENVIANDO WEB PUSH- No existe ningun Servidor Disponible: " + mensajeId);
			}
		} else {
			registerServidorWebPushDetailsDebug(notificacionWebPushData);
			mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId),
					ps.getMessage("constantes.ESTADO_ENVIANDO", null), "", false,
					notificacionWebPushData.getDestinatarioMensajeId(), null, null, null);

			int numServidoresPush = notificacionWebPushData.servers.size();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Numero de servidores Push: " + numServidoresPush);
			}
			if ("S".equals(ejecutarTodosServidores)) {
				while ((indice < numServidoresPush) && (!sendOK)) {
					registerServidorWebPushDetailsDebug(notificacionWebPushData);

					servidorWebPushID = Integer
							.valueOf((notificacionWebPushData.servers.get(indice))
									.getServidorWebPushId());
					if (LOG.isDebugEnabled()) {
						LOG.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
					}
					try {
						resultNotificacionPush = sendServidorWebPushByMISIM(notificacionWebPushData, mensajeId, idLote,
								indice, usuarioMISIM, passMISIM);
						if (LOG.isInfoEnabled()) {
							LOG.info("Respuesta: " + resultNotificacionPush);
						}
						if (resultNotificacionPush.contains("OK")) {
							sendOK = true;
						}
					} catch (Exception e) {
						String errorMessage = e.getMessage();
						LOG.error("Excepcion :" + errorMessage + " (postNotificacionPush)", e);
						TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
						tblErrorMensajeLog.setCodigoerror(new Long("0"));
						tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + mensajeId + ". Error: (" + e.hashCode()
								+ ") " + errorMessage);
						tblErrorMensajeLog.setFecha(new Date());
						tblErrorMensajeLog.setOperacion("postNotificacionPush");
						errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
					}
					indice++;
				}
			} else {
				if (numServidoresPush > 0) {
					registerServidorWebPushDetailsDebug(notificacionWebPushData);

					servidorWebPushID = Integer.valueOf((notificacionWebPushData.servers.get(indice))
									.getServidorWebPushId());
					if (LOG.isDebugEnabled()) {
						LOG.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
					}
					try {
						resultNotificacionPush = sendServidorWebPushByMISIM(notificacionWebPushData, mensajeId, idLote,
								indice, usuarioMISIM, passMISIM);
						if (LOG.isInfoEnabled()) {
							LOG.info("Respuesta: " + resultNotificacionPush);
						}
						if (resultNotificacionPush.contains("OK")) {
							sendOK = true;
						}
					} catch (Exception e) {
						String errorMessage = e.getMessage();
						LOG.error("Excepcion :" + errorMessage + " (postNotificacionWebPush)", e);

						TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
						tblErrorMensajeLog.setCodigoerror(new Long("0"));
						tblErrorMensajeLog.setDescripcionerror("WebPushId: " + mensajeId + ". Error: (" + e.hashCode()
								+ ") " + errorMessage);
						tblErrorMensajeLog.setFecha(new Date());
						tblErrorMensajeLog.setOperacion("postNotificacionWebPush");
						errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
					}
				}
			}

			if (sendOK) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Notificacion numero " + mensajeId + " enviado (postNotificacionPush)");
				}
				mensajesManager.setEstadoMensaje(mensajeId, okEnvio, resultNotificacionPush, false,
						notificacionWebPushData.getDestinatarioMensajeId(), null, null, servidorWebPushID.longValue());

			} else {
				mensajesManager.setEstadoMensaje(mensajeId, koEnvio, resultNotificacionPush, false,
						notificacionWebPushData.getDestinatarioMensajeId(), null, null, servidorWebPushID.longValue());
				throw new Exception("[SendMessageServiceImpl.postNotificacionPush] -ERROR ENVIANDO PUSH- KO Recibido enviando PUSH: " + mensajeId + " Descripcion: " +resultNotificacionPush);
			}
		}
	}
	
	private String sendServidorWebPushByMISIM(NotificacionWebPushData messageData, Long messageId, Long loteId,
			Integer indice, String usuarioMISIM, String passMISIM) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String resultado = "";
		messageData.setIndice("" + indice);
		messageData.setMessageID("" + messageId);
		PeticionWebPush peticionWebPush = new PeticionWebPush();
		peticionWebPush.setMensajeId(messageId.toString());
		peticionWebPush.setProducto("WEB_PUSH");
		peticionWebPush.setUsuario(usuarioMISIM);
		peticionWebPush.setPassword(passMISIM);
		peticionWebPush.setIdLote(loteId.toString());

		ParametrosServidorWebPush psw = messageData.getServers().get(indice);
		TblServidoresQuery query = new TblServidoresQuery();
		query.setServidorid(Long.valueOf(psw.getServidorWebPushId()));
		TblServidores servidor = servidoresManager.getServidor(query);
		if (servidor != null) {
			LOG.debug("Recuperado servidor web push: " + servidor.getServidorid());
		} else {
			LOG.debug("No encontrado servidor web push");
			return null;
		}
		peticionWebPush.setProveedor(servidor.getNombre()); 
			
		if (!resultado.equals("")) {
			return resultado;
		}
			
		DatosEspecificosWebPush datosEspecificosWebPush = new DatosEspecificosWebPush();
				
		datosEspecificosWebPush.setAuth(messageData.getAuth());
		datosEspecificosWebPush.setEndpoint(messageData.getEndpoint());
		datosEspecificosWebPush.setPdh(messageData.getPdh());
		datosEspecificosWebPush.setVapidPrivateKey(messageData.getVapidPrivateKey());
		datosEspecificosWebPush.setVapidPublicKey(messageData.getVapidPublicKey());
		datosEspecificosWebPush.setCabecera(messageData.getCabecera());
		datosEspecificosWebPush.setCuerpo(messageData.getCuerpo());
		
		//caducidad mensaje webpush
		datosEspecificosWebPush.setCaducidad((null != messageData.getCaducidadWebPush())? String.valueOf(messageData.getCaducidadWebPush()) : ps.getMessage("webpush.caducidadDefectoWEBPUSH", null, "30"));
		
		peticionWebPush.setDatosEspecificos(datosEspecificosWebPush);
		
		return commonUtilitiesService.sendMessage(peticionWebPush, ps.getMessage("constantes.SOAP_ACTION", null),
				ps.getMessage("constantes.RECEPT_QUEUE", null), messageId);

	}


	private void registerServidorWebPushDetailsDebug(NotificacionWebPushData notificacionWebPushData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("----------------------------------------------------------");
			LOG.debug("DATOS DE ENVIO DE NOTIFICACION WEB PUSH (postNotificacionWebPush)");
			LOG.debug("----------------------------------------------------------");
			LOG.debug("Titulo: " + notificacionWebPushData.cabecera + " (postNotificacionWebPush)");

			LOG.debug("Cuerpo: " + notificacionWebPushData.cuerpo + " (postNotificacionWebPush)");
		}
	}
	
	private NotificacionWebPushData getNotificacionWebPushData(Long mensajeId, Long destinatarioMensajeId)
			throws Exception {
		NotificacionWebPushData res = new NotificacionWebPushData();
		try {
			NotificacionWebPushData smsDataComun = new NotificacionWebPushData();
			smsDataComun.setEsMultidestinatario(true);
				res = queryExecutorMensajes.getDetailsServidorWebPushMultidestinatario(mensajeId, destinatarioMensajeId);
			
		} catch (Exception e) {
			TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
			tblErrorMensajeLog.setCodigoerror(new Long("0"));
			tblErrorMensajeLog.setDescripcionerror("Error: GetNotificacionWebPushData : " + e.getMessage());
			tblErrorMensajeLog.setFecha(new Date());
			tblErrorMensajeLog.setOperacion("postNotificacionWebPush");
			errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			LOG.error("getNotificacionWebPushData : " + e.getMessage(), e);
		}
		return res;
	}
	
	// ---- Fin tratamiento notificaciones WEBPUSH

	/***********************************/
	/****** RECEPCION SMS ***********/
	/***********************************/
	@Override
	public void postRecepcionSMS(Long mensajeId, Long destinatarioMensajeId) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String okEnvio = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String koEnvio = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		String ejecutarTodosServidores = ps.getMessage("receptorSMS.ejecutarTodosServidores", null);
		String usuarioMISIM = ps.getMessage("usuarioMISIM", null);
		String passMISIM = ps.getMessage("passwordMISIM", null);
		Integer receptorID = Integer.valueOf(0);

		ReceptorSMSData smsData = getRecepcionSMSData(mensajeId, destinatarioMensajeId);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Intentamos recuperar los datos del SMS con id: " + mensajeId + " destinatariosMensajeId:"
					+ smsData.destinatarioMensajeId + " (postRecepcionSMS)");
		}
		boolean sendOK = false;
		String resultSMS = "";
		if (smsData.Servers.isEmpty()) {
			mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId), koEnvio,
					". Error: No existe ningun Receptor Disponible", false, smsData.destinatarioMensajeId, null, null,
					null);
			TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
			tblErrorMensajeLog.setCodigoerror(new Long("0"));
			tblErrorMensajeLog.setDescripcionerror("Error: No existe ningun Receptor Disponible");
			tblErrorMensajeLog.setFecha(new Date());
			tblErrorMensajeLog.setOperacion("postRecepcionSMS");
			errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			throw new Exception("[SendMessageServiceImpl.postRecepcionSMS] -ERROR ENVIANDO RecepcionSMS- No existe ningun Servidor Disponible: " + mensajeId);
		} else {
			if (null != smsData.Body) {
				registerReceptorSMSDetailsDebug(smsData);
				mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId),
						ps.getMessage("constantes.ESTADO_ENVIANDO", null), null, false, smsData.destinatarioMensajeId,
						null, null, null);
				int indice = 0;
				int numReceptores = smsData.Servers.size();
				if (LOG.isInfoEnabled()) {
					LOG.info("Numero de receptores: " + numReceptores);
				}
				if ("S".equals(ejecutarTodosServidores)) {
					while ((indice < numReceptores) && (!sendOK)) {
						registerReceptorSMSParametersDebug(smsData, indice);

						receptorID = Integer
								.valueOf(((ParametrosReceptor) smsData.Servers.get(indice)).getReceptorId());
						if (LOG.isDebugEnabled()) {
							LOG.debug("Llamamos a Envio SMS (postRecepcionSMS)");
						}
						try {
							resultSMS = sendReceptorSMS(smsData, mensajeId, indice, usuarioMISIM, passMISIM);
							if (LOG.isInfoEnabled()) {
								LOG.info("Respuesta: " + resultSMS);
							}
							if (resultSMS.contains("OK")) {
								sendOK = true;
							}
						} catch (Exception e) {
							String errorMessage = e.getMessage();
							LOG.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
							TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
							tblErrorMensajeLog.setCodigoerror(new Long("0"));
							tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + mensajeId + ". Error: (" + e.hashCode()
									+ ") " + errorMessage);
							tblErrorMensajeLog.setFecha(new Date());
							tblErrorMensajeLog.setOperacion("`postRecepcionSMS");
							errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
						}
						indice++;
					}
				} else {
					if (numReceptores > 0) {
						registerReceptorSMSParametersDebug(smsData, indice);

						receptorID = Integer
								.valueOf(((ParametrosReceptor) smsData.Servers.get(indice)).getReceptorId());
						if (LOG.isDebugEnabled()) {
							LOG.debug("Llamamos a Envio SMS (postRecepcionSMS)");
						}
						try {
							resultSMS = sendReceptorSMS(smsData, mensajeId, indice, usuarioMISIM, passMISIM);
							if (LOG.isInfoEnabled()) {
								LOG.info("Respuesta: " + resultSMS);
							}
							if (resultSMS.contains("OK")) {
								sendOK = true;
							}
						} catch (Exception e) {
							String errorMessage = e.getMessage();
							LOG.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
							TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
							tblErrorMensajeLog.setCodigoerror(new Long("0"));
							tblErrorMensajeLog.setDescripcionerror("SMS_ID: " + mensajeId + ". Error: (" + e.hashCode()
									+ ") " + errorMessage);
							tblErrorMensajeLog.setFecha(new Date());
							tblErrorMensajeLog.setOperacion("`postRecepcionSMS");
							errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
						}
					}
				}
			}
			if (sendOK) {
				if (LOG.isInfoEnabled()) {
					LOG.info("SMS numero " + mensajeId + " enviado a aplicacion (postRecepcionSMS)");
				}
				mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId), okEnvio, resultSMS, false,
						smsData.destinatarioMensajeId, null, null, receptorID.longValue());
			} else {
				mensajesManager.setEstadoMensaje(Long.valueOf(mensajeId), koEnvio, resultSMS, false,
						smsData.destinatarioMensajeId, null, null, receptorID.longValue());
				throw new Exception("[SendMessageServiceImpl.postRecepcionSMS] -ERROR ENVIANDO RecepcionSMS-: " + mensajeId + " Descripcion: " + resultSMS);
			}
		}
	}

	private ReceptorSMSData getRecepcionSMSData(Long mensajeId, Long destinatarioMensajeId) {
		ReceptorSMSData res = new ReceptorSMSData();
		try {

			ReceptorSMSData smsDataComun = new ReceptorSMSData();
			smsDataComun.ServiceData = queryExecutorMensajes.getDataFromServices(mensajeId);
			smsDataComun.Servers = (ArrayList<ParametrosReceptor>) queryExecutorViewReceptoresPrioridad
					.getReceptores(mensajeId);
			if (null != destinatarioMensajeId) {
				res = queryExecutorMensajes.getDetailsReceptorSMSMultidestinatario(mensajeId, destinatarioMensajeId,
						smsDataComun);
			} else {
				res = queryExecutorMensajes.getDetailsReceptorSMS(mensajeId, smsDataComun);
			}
		} catch (Exception e) {
			LOG.error("Error en getMailData : ", e);
		}
		return res;
	}

	private String sendReceptorSMS(ReceptorSMSData smsData, Long messageId, int indice, String usuarioMISIM,
			String passMISIM) throws Exception {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		TblAplicaciones aplicacion = aplicacionesManager.getAplicacion(queryExecutorAplicaciones
				.findAplicacionByMessageId(messageId));
		String proveedor = aplicacion.getNombre();

		EnvioAplicacionRequest envioAplicacionRequest = new EnvioAplicacionRequest();
		envioAplicacionRequest.setUser(smsData.User);
		envioAplicacionRequest.setPassword(smsData.Password);
		envioAplicacionRequest.setSender(smsData.Telefono);
		envioAplicacionRequest.setRecipient(smsData.HeaderSMS);
		envioAplicacionRequest.setSMSText(smsData.Body);
		envioAplicacionRequest.setMessageId(messageId.toString());

		DatosEspecificosRecepcionSMS datosEspecificos = new DatosEspecificosRecepcionSMS();
		datosEspecificos.setEnvioAplicacionRequest(envioAplicacionRequest);

		PeticionRecepcionSMS peticion = new PeticionRecepcionSMS();
		peticion.setMensajeId(String.valueOf(messageId));
		peticion.setPassword(passMISIM);
		peticion.setUsuario(usuarioMISIM);
		peticion.setDatosEspecificos(datosEspecificos);
		peticion.setProveedor(proveedor);
		peticion.setProducto("SMS_APLICACION");
		peticion.setLoteId(smsData.LoteEnvioId);		
		String res = commonUtilitiesService.sendMessage(peticion, ps.getMessage("constantes.SOAP_ACTION", null),
				ps.getMessage("constantes.RECEPT_QUEUE", null), messageId);

		return res;

	}

	private void registerReceptorSMSDetailsDebug(ReceptorSMSData smsData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("----------------------------------------");
			LOG.debug("DATOS DE ENVIO DE SMS (postRecepcionSMS)");
			LOG.debug("----------------------------------------");
			LOG.debug("Telefono: " + smsData.Telefono + " (postRecepcionSMS)");
		}
	}

	private void registerReceptorSMSParametersDebug(ReceptorSMSData smsData, int indice) {
		ParametrosReceptor ps = (ParametrosReceptor) smsData.Servers.get(indice);
		String url = "";
		String id = "";
		String telefono = "";
		String texto = "";
		String uim = "";
		if (ps.getUrl() != null) {
			url = ps.getUrl();
		}
		if (ps.getId() != null) {
			id = ps.getId();
		}
		if (ps.getTelefono() != null) {
			telefono = ps.getTelefono();
		}
		if (ps.getTexto() != null) {
			texto = ps.getTexto();
		}
		if (ps.getUIM() != null) {
			uim = ps.getUIM();
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("-------------------------------------");
			LOG.debug("DATOS DEL RECEPTOR (postRecepcionSMS)");
			LOG.debug("-------------------------------------");
			LOG.debug("Url: " + url + " (postRecepcionSMS)");
			if (id != "") {
				LOG.debug("Id:" + id + " (postRecepcionSMS)");
			}
			if (telefono != "") {
				LOG.debug("Telefono: " + telefono + " (postRecepcionSMS)");
			}
			if (texto != "") {
				LOG.debug("Texto: " + texto + " (postRecepcionSMS)");
			}
			if (uim != "") {
				LOG.debug("Texto: " + uim + " (postRecepcionSMS)");
			}
			LOG.debug("-----------------------------------");
		}
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
	 * @return the queryExecutorMensajeAdjuntos
	 */
	public QueryExecutorMensajeAdjuntos getQueryExecutorMensajeAdjuntos() {
		return queryExecutorMensajeAdjuntos;
	}

	/**
	 * @param queryExecutorMensajeAdjuntos
	 *            the queryExecutorMensajeAdjuntos to set
	 */
	public void setQueryExecutorMensajeAdjuntos(QueryExecutorMensajeAdjuntos queryExecutorMensajeAdjuntos) {
		this.queryExecutorMensajeAdjuntos = queryExecutorMensajeAdjuntos;
	}

	/**
	 * @return the queryExecutorViewServidoresPrioridad
	 */
	public QueryExecutorViewServidoresPrioridad getQueryExecutorViewServidoresPrioridad() {
		return queryExecutorViewServidoresPrioridad;
	}

	/**
	 * @param queryExecutorViewServidoresPrioridad
	 *            the queryExecutorViewServidoresPrioridad to set
	 */
	public void setQueryExecutorViewServidoresPrioridad(
			QueryExecutorViewServidoresPrioridad queryExecutorViewServidoresPrioridad) {
		this.queryExecutorViewServidoresPrioridad = queryExecutorViewServidoresPrioridad;
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
	 * @return the queryExecutorLotesEnvios
	 */
	public QueryExecutorLotesEnvios getQueryExecutorLotesEnvios() {
		return queryExecutorLotesEnvios;
	}

	/**
	 * @param queryExecutorLotesEnvios
	 *            the queryExecutorLotesEnvios to set
	 */
	public void setQueryExecutorLotesEnvios(QueryExecutorLotesEnvios queryExecutorLotesEnvios) {
		this.queryExecutorLotesEnvios = queryExecutorLotesEnvios;
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
	 * @return the queryExecutorViewReceptoresPrioridad
	 */
	public QueryExecutorViewReceptoresPrioridad getQueryExecutorViewReceptoresPrioridad() {
		return queryExecutorViewReceptoresPrioridad;
	}

	/**
	 * @param queryExecutorViewReceptoresPrioridad
	 *            the queryExecutorViewReceptoresPrioridad to set
	 */
	public void setQueryExecutorViewReceptoresPrioridad(
			QueryExecutorViewReceptoresPrioridad queryExecutorViewReceptoresPrioridad) {
		this.queryExecutorViewReceptoresPrioridad = queryExecutorViewReceptoresPrioridad;
	}

	/**
	 * @return the queryExecutorAplicaciones
	 */
	public QueryExecutorAplicaciones getQueryExecutorAplicaciones() {
		return queryExecutorAplicaciones;
	}

	/**
	 * @param queryExecutorAplicaciones
	 *            the queryExecutorAplicaciones to set
	 */
	public void setQueryExecutorAplicaciones(QueryExecutorAplicaciones queryExecutorAplicaciones) {
		this.queryExecutorAplicaciones = queryExecutorAplicaciones;
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
	 * @return the commonUtilitiesService
	 */
	public ICommonUtilitiesService getCommonUtilitiesService() {
		return commonUtilitiesService;
	}

	/**
	 * @param commonUtilitiesService
	 *            the commonUtilitiesService to set
	 */
	public void setCommonUtilitiesService(ICommonUtilitiesService commonUtilitiesService) {
		this.commonUtilitiesService = commonUtilitiesService;
	}

	/**
	 * @return the messageSender
	 */
	public SIMMessageSender getMessageSender() {
		return messageSender;
	}

	/**
	 * @param messageSender
	 *            the messageSender to set
	 */
	public void setMessageSender(SIMMessageSender messageSender) {
		this.messageSender = messageSender;
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


}
