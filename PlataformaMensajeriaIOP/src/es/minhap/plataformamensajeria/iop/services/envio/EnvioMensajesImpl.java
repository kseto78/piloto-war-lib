package es.minhap.plataformamensajeria.iop.services.envio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.AdjuntosXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ImagenXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajeSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.UsuariosServiciosMovilesBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesMailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Respuesta;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;
import es.minhap.sim.model.TblServicios;

/**
 * 
 * @author everis
 * 
 */
@Service("envioMensajesImpl")
public class EnvioMensajesImpl implements IEnvioMensajesService {

	private static Logger logger = Logger.getLogger(EnvioMensajesImpl.class);

	@Resource
	private TblLotesEnviosManager lotesManager;

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblHistoricosManager historicosManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;

	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Resource
	private TblAdjuntosManager adjuntosManager;

	@Resource
	private TblServiciosManager serviciosManager;

	@Resource
	private TblServiciosMovilesManager serviciosMovilesManager;

	@Resource
	private TblUsuariosServiciosMovilesManager usuariosServiciosMovilesManager;

	@Resource
	private TblDestinatariosManager destinatariosManager;

	@Resource
	private TblEstadosManager estadosManager;

	@Autowired
	private QueryExecutorOrganismos queryExecutorOrganismos;

	@Autowired
	private QueryExecutorUsuariosPush queryExecutorUsuariosPush;

	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource
	private TblServidoresServiciosManager servidoresServiciosManager;

	@Override
	public String enviarEmail(EnvioEmailXMLBean envioEmail, List<String> listaErrores) {
		logger.debug("[EnviarEmail] XML envioEmail recibido");
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String xmlRespues = null;
		Respuesta respuesta = new Respuesta();
		logger.debug("[EnviarEmail] Iniciando transaccion bbdd");
		ArrayList<String> listaErroresGenerales = new ArrayList<>();

		// Tratamiento para errores producidos en la generaci�n del passbook
		if (listaErrores != null) {
			listaErroresGenerales.addAll(listaErrores);
		}

		ArrayList<String> listaErroresLote = new ArrayList<>();
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<>();
		Integer idLote = null;
		Mensaje mensajeCreado = null;

		try {

			String error = peticionCorrecta(envioEmail.getNombreLote(), envioEmail.getServicio(),
					envioEmail.getUsuario(), envioEmail.getPassword());
			if (error != null) {
				listaErroresGenerales.add(error);
			}
			if (listaErroresGenerales.isEmpty()) {
				Long estadoId = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null))
						.getEstadoid();
				idLote = lotesManager.insertarLote(Long.parseLong(envioEmail.getServicio()),
						envioEmail.getNombreLote(), envioEmail.getUsuario(), envioEmail.getPassword());
				if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
					listaErroresLote.add(WSPlataformaErrors.getErrorCrearLote(idLote));
					xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales,
							listaErroresLote);

					return xmlRespues;
				}

				logger.debug("[EnviarEmail] Lote creado correctamente con ID " + idLote);
				logger.debug("[EnviarEmail] Creando mensajes ");

				ArrayList<MensajesXMLBean> listaMensajes = envioEmail.getListadoMensajes();
				for (MensajesXMLBean mensaje : listaMensajes) {

					String faltaCampoObligatorio = evaluarMensajeMailCompleto(mensaje.getAsunto(), mensaje.getCuerpo(),
							mensaje.getListaDestinatarios());

					if (faltaCampoObligatorio != null) {
						Mensaje msj = new Mensaje();
						msj.setIdExterno("");
						msj.setIdMensaje("");
						ResponseStatusType status = new ResponseStatusType();
						status.setStatusCode(PlataformaErrores.STATUSCODE_KO);
						status.setDetails(PlataformaErrores.STATUSDETAILS_KO);
						status.setStatusText(faltaCampoObligatorio);
						msj.setErrorMensaje(status);
						listaMensajesProcesados.add(msj);
					} else {

						for (DestinatarioPeticionLotesMailXMLBean destinatario : mensaje.getListaDestinatarios()) {
							String to = (null != destinatario.getDestinatarios().getTo() && destinatario.getDestinatarios().getTo().length() > 1)? 
									destinatario.getDestinatarios().getTo() : null;
							String cc = (null != destinatario.getDestinatarios().getCC() && destinatario.getDestinatarios().getCC().length() > 1)? 
									destinatario.getDestinatarios().getCC() : null ;
							String bcc = (null != destinatario.getDestinatarios().getBcc() && destinatario.getDestinatarios().getBcc().length() > 1)? 
									destinatario.getDestinatarios().getBcc() : null;

							logger.debug("[EnviarEmail] - Antes de crearEmail() - Tam Cuerpo: "
									+ mensaje.getCuerpo().length());
							mensajeCreado = mensajesManager.insertarMensajeEmail(Long.valueOf(idLote), mensaje,
									envioEmail, to, cc, bcc);
							logger.debug("[EnviarEmail]  - Despues de crearEmail() ");
							mensaje.setIdMensaje(mensajeCreado.toString());

							listaMensajesProcesados.add(mensajeCreado);

							if (mensajeCreado.getErrorMensaje() == null) {

								// Si no hay errores se guardan los adjuntos
								for (AdjuntosXMLBean adjunto : mensaje.getListaAdjuntos()) {
									Integer adjuntoId = adjuntosManager.insertarAdjunto(
											Long.parseLong(mensajeCreado.getIdMensaje()), adjunto.getNombre(),
											adjunto.getContenido(), envioEmail.getUsuario(), envioEmail.getPassword());
									if (WSPlataformaErrors.getErrorCrearAnexo(adjuntoId) != null) {
										ResponseStatusType status = responseStatusError();
										mensajeCreado.setErrorMensaje(status);
										listaMensajesProcesados.add(mensajeCreado);

									}
								}
								for (AdjuntosXMLBean adjunto : envioEmail.getListadoAdjuntosGenerales()) {
									if (adjunto.getIdAdjunto() == null) {
										Integer adjuntoId = adjuntosManager.insertarAdjunto(
												Long.parseLong(mensajeCreado.getIdMensaje()), adjunto.getNombre(),
												adjunto.getContenido(), envioEmail.getUsuario(),
												envioEmail.getPassword());
										adjunto.setIdAdjunto(adjuntoId);
										if (WSPlataformaErrors.getErrorCrearAnexo(adjuntoId) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}

									} else {
										Integer idAdjuntoAsociar = adjunto.getIdAdjunto();
										Integer salida = adjuntosManager.asociarAnexo(
												Long.parseLong(mensajeCreado.getIdMensaje()),
												Long.valueOf(idAdjuntoAsociar), envioEmail.getUsuario(),
												envioEmail.getPassword());
										if (WSPlataformaErrors.getErrorAsociarAnexo(salida) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}
									}
								}
								for (ImagenXMLBean imagen : mensaje.getListaImagenes()) {
									if (imagen.getIdImagen() == null) {
										Integer imagenId = adjuntosManager.insertarAdjunto(
												Long.parseLong(mensajeCreado.getIdMensaje()), imagen.getCid(),
												imagen.getContenido(), envioEmail.getUsuario(),
												envioEmail.getPassword());
										if (WSPlataformaErrors.getErrorCrearAnexo(imagenId) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}
									} else {
										Integer idImagen = imagen.getIdImagen();
										Integer salida = adjuntosManager.asociarAnexo(
												Long.parseLong(mensajeCreado.getIdMensaje()), Long.valueOf(idImagen),
												envioEmail.getUsuario(), envioEmail.getPassword());
										if (WSPlataformaErrors.getErrorAsociarAnexo(salida) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}
									}
								}

							}
							if (!mensajeCreado.getIdMensaje().isEmpty()) {
								List<Long> listaTblDestinatarios = destinatariosManager.setDestinatarios(
										Long.parseLong(mensajeCreado.getIdMensaje()), to, cc, bcc,
										envioEmail.getUsuario());
								List<Long> listaTblDestinatariosMensajes = destinatariosMensajesManager
										.insertarDestinatarioMensajeEmail(mensajeCreado.getIdMensaje(),
												listaTblDestinatarios, destinatario.getIdExterno(),
												envioEmail.getUsuario(), estadoId);
								historicosManager.creaHistoricoEmail(mensajeCreado.getIdMensaje(),
										listaTblDestinatariosMensajes, estadoId, null, null, envioEmail.getUsuario());

								if (null == mensaje.getModo() || mensaje.getModo().equals("0")) {
									MensajeJMS mensajeJms = new MensajeJMS();
									mensajeJms.setIdExterno(destinatario.getIdExterno());
									mensajeJms.setIdMensaje(mensajeCreado.getIdMensaje());
									mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_EMAIL", null));
									Long maxRetries = null;
									StringBuilder mensajes = new StringBuilder();
									for (Long dest : listaTblDestinatariosMensajes) {
										mensajes.append(dest + ";");
									}
									mensajeJms.setDestinatarioMensajeId(mensajes.toString());
									TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioEmail
											.getServicio()));
									if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() > 0) {
										maxRetries = servicio.getNumeroMaxReenvios().longValue();
									} else {
										maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios",
												null));
									}
									sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
								} else {
									for (Long dest : listaTblDestinatariosMensajes) {
										MensajeJMS mensajeJms = new MensajeJMS();
										mensajeJms.setIdExterno(destinatario.getIdExterno());
										mensajeJms.setIdMensaje(mensajeCreado.getIdMensaje());
										mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_EMAIL", null));
										Long maxRetries = null;
										mensajeJms.setDestinatarioMensajeId(dest.toString());
										TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioEmail
												.getServicio()));
										if (servicio.getNumeroMaxReenvios() != null
												&& servicio.getNumeroMaxReenvios() > 0) {
											maxRetries = servicio.getNumeroMaxReenvios().longValue();
										} else {
											maxRetries = Long.parseLong(ps.getMessage(
													"constantes.servicio.numMaxReenvios", null));
										}
										sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
									}
								}

							}

						}// del for

					}

				}

			}
			logger.debug("[EnviarEmail] Creando XML de respuesta");
			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales, listaErroresLote);
			logger.debug("[EnviarEmail] XML de respuesta generado");
		} catch (Exception e) {
			logger.error("EnvioMensajesImpl.enviarEmail", e);
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
		}
		return Utils.convertToUTF8(xmlRespues);
	}

	@Override
	public String enviarEmail(EnvioEmailXMLBean envioEmail) {
		return enviarEmail(envioEmail, null);
	}

	private ResponseStatusType responseStatusError() {
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(PlataformaErrores.STATUSCODE_KO);
		status.setDetails(PlataformaErrores.STATUSDETAILS_KO);
		status.setStatusText(PlataformaErrores.ERROR_ADJUNTOS);
		return status;
	}

	@Override
	public String enviarSMS(EnvioSMSXMLBean envioSMS) {
		String xmlRespues = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<Mensaje>();
		ArrayList<String> listaErroresGenerales = new ArrayList<>();
		ArrayList<String> listaErroresLote = new ArrayList<>();
		Integer idLote = null;
		Respuesta respuesta = new Respuesta();
		Mensaje mensajeCreado = null;
		try {

			String error = peticionCorrectaSMS(envioSMS.getNombreLote(), envioSMS.getServicio(), envioSMS.getUsuario(),
					envioSMS.getPassword(), envioSMS.getOrganismoPagador());

			if (error != null) {
				listaErroresGenerales.add(error);
			}
			if (listaErroresGenerales.isEmpty()) {
				Long estadoId = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null))
						.getEstadoid();
				idLote = lotesManager.insertarLote(Long.parseLong(envioSMS.getServicio()), envioSMS.getNombreLote(),
						envioSMS.getUsuario(), envioSMS.getPassword());
				if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
					listaErroresLote.add(WSPlataformaErrors.getErrorCrearLote(idLote));
					xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales,
							listaErroresLote);
					return xmlRespues;
				}
				ArrayList<MensajeSMSXMLBean> listaMensajes = envioSMS.getListadoMensajes();

				for (MensajeSMSXMLBean mensaje : listaMensajes) {

					if (mensaje.getCuerpo() == null || mensaje.getCuerpo().length() <= 0
							|| mensaje.getListaDestinatarios().isEmpty()) {
						Mensaje msj = new Mensaje();
						msj.setIdExterno("");
						msj.setIdMensaje("");
						ResponseStatusType status = new ResponseStatusType();
						status.setDetails(PlataformaErrores.STATUSDETAILS_KO);
						status.setStatusCode(PlataformaErrores.STATUSCODE_KO);
						status.setStatusText(PlataformaErrores.ERROR_STATUSTEXT_CUERPO);
						msj.setErrorMensaje(status);
						listaMensajesProcesados.add(msj);

					} else {
						for (DestinatarioPeticionLotesSMSXMLBean destinatario : mensaje.getListaDestinatarios()) {
							if (destinatario.getDestinatario() != null
									&& Utils.validarTelefono(destinatario.getDestinatario()) == 1) {
								Mensaje msj = new Mensaje();
								msj.setIdExterno("");
								msj.setIdMensaje("");
								ResponseStatusType status = new ResponseStatusType();
								status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
								status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
								status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
								msj.setErrorMensaje(status);
								listaMensajesProcesados.add(msj);
							} else {
								if (null == mensajeCreado) {
									mensajeCreado = mensajesManager.insertarMensajeSMS(Long.valueOf(idLote), mensaje,
											envioSMS.getUsuario(), envioSMS.getPassword());
									listaMensajesProcesados.add(mensajeCreado);

									// ahora insertamos en la tabla
									// destinatariosMensajes
									if (!mensajeCreado.getIdMensaje().isEmpty()) {
										Long desMensaje = destinatariosMensajesManager.insertarDestinatarioMensaje(
												mensajeCreado.getIdMensaje(), destinatario.getDestinatario(),
												destinatario.getIdExterno(), envioSMS.getUsuario());
										historicosManager.creaHistorico(Long.parseLong(mensajeCreado.getIdMensaje()),
												desMensaje, estadoId, null, null, null, envioSMS.getUsuario());
										MensajeJMS mensajeJms = new MensajeJMS();
										mensajeJms.setIdMensaje(mensajeCreado.getIdMensaje());
										mensajeJms.setIdExterno(destinatario.getIdExterno());
										mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
										mensajeJms.setDestinatarioMensajeId(desMensaje.toString());
										Long maxRetries = null;
										TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioSMS
												.getServicio()));
										if (servicio.getNumeroMaxReenvios() != null
												&& servicio.getNumeroMaxReenvios() > 0) {
											maxRetries = servicio.getNumeroMaxReenvios().longValue();
										} else {
											maxRetries = Long.parseLong(ps.getMessage(
													"constantes.servicio.numMaxReenvios", null));
										}
										sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
									}

								} else {// es un destinatario mas, hay que
										// insertarlo solo en tabla
										// destinatario-mensajes
									if (!mensajeCreado.getIdMensaje().isEmpty()) {
										Long desMensaje = destinatariosMensajesManager.insertarDestinatarioMensaje(
												mensajeCreado.getIdMensaje(), destinatario.getDestinatario(),
												destinatario.getIdExterno(), envioSMS.getUsuario());
										historicosManager.creaHistorico(Long.parseLong(mensajeCreado.getIdMensaje()),
												desMensaje, estadoId, null, null, null, envioSMS.getUsuario());
										MensajeJMS mensajeJms = new MensajeJMS();
										mensajeJms.setIdMensaje(mensajeCreado.getIdMensaje());
										mensajeJms.setIdExterno(destinatario.getIdExterno());
										mensajeJms.setDestinatarioMensajeId(desMensaje.toString());
										mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
										Long maxRetries = null;
										TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioSMS
												.getServicio()));
										if (servicio.getNumeroMaxReenvios() != null
												&& servicio.getNumeroMaxReenvios() > 0) {
											maxRetries = servicio.getNumeroMaxReenvios().longValue();
										} else {
											maxRetries = Long.parseLong(ps.getMessage(
													"constantes.servicio.numMaxReenvios", null));
										}
										sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);

									}
								}
							}
						}

					}
					mensajeCreado = null;
				}
			}

			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales, listaErroresLote);
		} catch (Exception e) {
			logger.error("EnvioMensajesImpl.enviarSMS", e);
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
			try {
				xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales,
						listaErroresLote);
			} catch (PlataformaBusinessException e1) {
				logger.error("EnvioMensajesImpl.enviarSMS", e);
			}
		}

		return Utils.convertToUTF8(xmlRespues);
	}

	@Override
	public String enviarNotificacion(EnvioPushXMLBean notificacionPush) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String xmlRespues = null;
		String servicioPUSH = ps.getMessage("servicio.notificacionesPUSH", null, null, null);
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje>();
		ArrayList<String> listaErroresGenerales = new ArrayList<>();
		ArrayList<String> listaErroresLote = new ArrayList<>();
		Integer idLote = null;
		Respuesta respuesta = new Respuesta();
		Mensaje mensajeCreado = null;

		try {

			String error = peticionCorrecta(notificacionPush.getNombreLote(), notificacionPush.getServicio(),
					notificacionPush.getUsuario(), notificacionPush.getPassword());
			if (error != null) {
				listaErroresGenerales.add(error);
			}
			if (listaErroresGenerales.isEmpty()) {
				Long estadoId = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null))
						.getEstadoid();
				idLote = lotesManager
						.insertarLote(Long.parseLong(notificacionPush.getServicio()), notificacionPush.getNombreLote(),
								notificacionPush.getUsuario(), notificacionPush.getPassword());

				if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
					listaErroresLote.add(WSPlataformaErrors.getErrorCrearLote(idLote));
					xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales,
							listaErroresLote);

					return xmlRespues;
				}
				ArrayList<MensajePeticionLotesPushXMLBean> listaMensajes = notificacionPush.getListadoMensajes();

				if (notificacionPush.getServicio().equalsIgnoreCase(servicioPUSH)) {
					mensajeCreado = null;
					
					
					for (MensajePeticionLotesPushXMLBean mensaje : listaMensajes) {

						String faltaCampoObligatorio = evaluarMensajeNotificacionCompletoServicio(mensaje.getTitulo(),
								mensaje.getCuerpo());

						if (faltaCampoObligatorio == null) {
						// Tratamiento para el campo idServicio
						String cuerpoMensaje = mensaje.getCuerpo();
						JSONObject jsonObject = new JSONObject(cuerpoMensaje.replace("\\", ""));
						String idServicioMovil = (!jsonObject.isNull("idServicioMovil")) ? jsonObject
								.getString("idServicioMovil") : null;
								
							if (null != idServicioMovil && !idServicioMovil.isEmpty()) {
								if (serviciosMovilesManager.checkMobileActiveService(idServicioMovil)) {
									if (null != mensaje.getDestinatariosPush()) {
										for (DestinatarioPeticionLotesPushXMLBean d : mensaje.getDestinatariosPush()
												.getDestinatarioPush()) {
											// no viene informado el usuario en
											// el
											// tag identificador de usuario
											if (d == null || null == d.getIdentificadorUsuario()
													|| d.getIdentificadorUsuario().length() <= 0) {
												List<UsuariosServiciosMovilesBean> listaDispositivos = queryExecutorUsuariosPush
														.getUsuarioPorServicio(Integer.parseInt(idServicioMovil));
												ResponseStatusType status = new ResponseStatusType();
												if (!listaDispositivos.isEmpty()) {
													for (UsuariosServiciosMovilesBean usuario : listaDispositivos) {
														if (null == mensajeCreado) {
															mensajeCreado = mensajesManager.insertarMensajePush(
																	Long.valueOf(idLote), mensaje, notificacionPush, d,
																	usuario.getIdUsuario());
															listaMensajesProcesados.add(mensajeCreado);

															// insertamos en
															// tabla
															// destinatarios-mensajes
															Long desMensaje = destinatariosMensajesManager
																	.insertarDestinatarioMensaje(mensajeCreado
																			.getIdMensaje(), usuario.getIdUsuario()
																			.toString(), d.getIdExterno(),
																			notificacionPush.getUsuario());
															historicosManager.creaHistorico(
																	Long.parseLong(mensajeCreado.getIdMensaje()),
																	desMensaje, estadoId, null, null, null,
																	notificacionPush.getUsuario());

															sendMensajeJMS(notificacionPush, ps, mensajeCreado, d,
																	desMensaje);
														} else {
															// insertamos en
															// tabla
															// destinatarios-mensajes
															Long desMensaje = destinatariosMensajesManager
																	.insertarDestinatarioMensaje(mensajeCreado
																			.getIdMensaje(), usuario.getIdUsuario()
																			.toString(), d.getIdExterno(),
																			notificacionPush.getUsuario());
															historicosManager.creaHistorico(
																	Long.parseLong(mensajeCreado.getIdMensaje()),
																	desMensaje, estadoId, null, null, null,
																	notificacionPush.getUsuario());
															sendMensajeJMS(notificacionPush, ps, mensajeCreado, d,
																	desMensaje);

														}
													}// del for
														// listaDispositivos
												} else {
													Mensaje msj = new Mensaje();
													msj.setIdExterno("");
													msj.setIdMensaje("");
													status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
													status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
													status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
													msj.setErrorMensaje(status);
													listaMensajesProcesados.add(msj);
												}
											} else {
												// lleva destinatarios
												logger.info("EnvioMensajeImpl [enviarNotificacion]: obteniendo dispositivos de usuario"
														+ d.getIdentificadorUsuario());
												ArrayList<Integer> listaDispositivos = queryExecutorUsuariosPush
														.getDispositivosUsuarioServicioMovil(
																d.getIdentificadorUsuario(),
																Integer.parseInt(notificacionPush.getServicio()),
																Integer.parseInt(idServicioMovil));
												ResponseStatusType status = new ResponseStatusType();
												if (!listaDispositivos.isEmpty()) {
													for (Integer usuarioId : listaDispositivos) {
														if (d == null || d.getIdentificadorUsuario().length() <= 0) {
															error = WSPlataformaErrors.getErrorFaltaDestinatario();
															Mensaje msj = new Mensaje();
															msj.setIdExterno("");
															msj.setIdMensaje("");
															status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
															status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
															status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
															msj.setErrorMensaje(status);
															listaMensajesProcesados.add(msj);
														} else {
															if (usuariosServiciosMovilesManager
																	.comprobarUsuarioServicioValido(usuarioId,
																			idServicioMovil)) {
																if (null == mensajeCreado) {
																	mensajeCreado = mensajesManager
																			.insertarMensajePush(Long.valueOf(idLote),
																					mensaje, notificacionPush, d,
																					usuarioId);
																	listaMensajesProcesados.add(mensajeCreado);

																	// insertamos
																	// en
																	// tabla
																	// destinatarios-mensajes
																	Long desMensaje = destinatariosMensajesManager
																			.insertarDestinatarioMensaje(
																					mensajeCreado.getIdMensaje(),
																					usuarioId.toString(),
																					d.getIdExterno(),
																					notificacionPush.getUsuario());
																	historicosManager.creaHistorico(Long
																			.parseLong(mensajeCreado.getIdMensaje()),
																			desMensaje, estadoId, null, null, null,
																			notificacionPush.getUsuario());

																	sendMensajeJMS(notificacionPush, ps, mensajeCreado,
																			d, desMensaje);
																} else {
																	// insertamos
																	// en
																	// tabla
																	// destinatarios-mensajes
																	Long desMensaje = destinatariosMensajesManager
																			.insertarDestinatarioMensaje(
																					mensajeCreado.getIdMensaje(),
																					usuarioId.toString(),
																					d.getIdExterno(),
																					notificacionPush.getUsuario());
																	historicosManager.creaHistorico(Long
																			.parseLong(mensajeCreado.getIdMensaje()),
																			desMensaje, estadoId, null, null, null,
																			notificacionPush.getUsuario());

																	sendMensajeJMS(notificacionPush, ps, mensajeCreado,
																			d, desMensaje);

																}
															} else {

																logger.info("EnvioMensajeImpl [enviarNotificacion]: El usuario no estÃ¡ suscrito al servicio movil indicado");
																error = WSPlataformaErrors.getErrorFaltaCuerpo();
																Mensaje msj = new Mensaje();
																msj.setIdExterno("");
																msj.setIdMensaje("");
																status = new ResponseStatusType();
																status.setStatusCode(PlataformaErrores.STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
																status.setDetails(PlataformaErrores.STATUSDETAILS_KO_USUARIONOSUSCRITO);
																status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
																msj.setErrorMensaje(status);
																listaMensajesProcesados.add(msj);
															}
														}
													}// del for de
														// listaDispositivos
												}// del
													// listaDispositivos.isEmpty
												else {
													Mensaje msj = new Mensaje();
													msj.setIdExterno("");
													msj.setIdMensaje("");
													status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
													status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
													status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
													msj.setErrorMensaje(status);
													listaMensajesProcesados.add(msj);

												}
											}
										}
									} else {
										// No lleva destinatarios
										List<UsuariosServiciosMovilesBean> listaDispositvos = queryExecutorUsuariosPush
												.getUsuarioPorServicio(Integer.parseInt(idServicioMovil));
										ResponseStatusType status = new ResponseStatusType();
										if (!listaDispositvos.isEmpty()) {
											for (UsuariosServiciosMovilesBean usuario : listaDispositvos) {
												DestinatarioPeticionLotesPushXMLBean bean = new DestinatarioPeticionLotesPushXMLBean();
												bean.setIdentificadorUsuario(usuario.getUsuario());
												if (null == mensajeCreado) {
													logger.info("EnvioMensajeImpl [enviarNotificacion]: creando mensaje.");
													mensajeCreado = mensajesManager.insertarMensajePush(
															Long.valueOf(idLote), mensaje, notificacionPush, bean,
															usuario.getIdUsuario());
													listaMensajesProcesados.add(mensajeCreado);

													// insertamos en tabla
													// destinatarios-mensajes
													// //TODO: ver si el
													// destinatario esta bien
													Long desMensaje = destinatariosMensajesManager
															.insertarDestinatarioMensaje(mensajeCreado.getIdMensaje(),
																	usuario.getIdUsuario().toString(),
																	bean.getIdentificadorUsuario(),
																	notificacionPush.getUsuario());
													historicosManager.creaHistorico(
															Long.parseLong(mensajeCreado.getIdMensaje()), desMensaje,
															estadoId, null, null, null, notificacionPush.getUsuario());

													sendMensajeJMS(notificacionPush, ps, mensajeCreado, bean,
															desMensaje);

												} else {
													// insertamos en tabla
													// destinatarios-mensajes
													// insertamos en tabla
													// destinatarios-mensajes
													Long desMensaje = destinatariosMensajesManager
															.insertarDestinatarioMensaje(mensajeCreado.getIdMensaje(),
																	usuario.getIdUsuario().toString(),
																	bean.getIdExterno(), notificacionPush.getUsuario());
													historicosManager.creaHistorico(
															Long.parseLong(mensajeCreado.getIdMensaje()), desMensaje,
															estadoId, null, null, null, notificacionPush.getUsuario());

													sendMensajeJMS(notificacionPush, ps, mensajeCreado, bean,
															desMensaje);

												}

											}// dell for listaDispositivos
										}// del if listaDispositivo no emtpy
										else {
											Mensaje msj = new Mensaje();
											msj.setIdExterno("");
											msj.setIdMensaje("");
											status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
											status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
											status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
											msj.setErrorMensaje(status);
											listaMensajesProcesados.add(msj);

										}
									}

								} else {
									// Error por id servicio movil incorrecto o
									// no
									// activo

									logger.info("EnvioMensajeImpl [enviarNotificacion]: El servicio movil es incorrecto o no esta activo");
									error = WSPlataformaErrors.getErrorFaltaCuerpo();
									Mensaje msj = new Mensaje();
									msj.setIdExterno("");
									msj.setIdMensaje("");
									ResponseStatusType status = new ResponseStatusType();
									status.setStatusCode(PlataformaErrores.STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
									status.setDetails(PlataformaErrores.DETAILS_KO_CAMPOS_OBLIGATORIOS_SERVICIO);
									status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
									msj.setErrorMensaje(status);
									listaMensajesProcesados.add(msj);
								}
							} else {
								// Error por id Servicio nulo o vacio

								logger.info("EnvioMensajeImpl [enviarNotificacion]: El servicio movil es obligatorio o viene mal informado");
								error = WSPlataformaErrors.getErrorFaltaCuerpo();
								Mensaje msj = new Mensaje();
								msj.setIdExterno("");
								msj.setIdMensaje("");
								ResponseStatusType status = new ResponseStatusType();
								status.setStatusCode(PlataformaErrores.STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
								status.setDetails(PlataformaErrores.DETAILS_KO_CAMPOS_OBLIGATORIOS);
								status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
								msj.setErrorMensaje(status);
								listaMensajesProcesados.add(msj);
							}

						}// 
						else {
							Mensaje msj = new Mensaje();
							msj.setIdExterno("");
							msj.setIdMensaje("");
							ResponseStatusType status = new ResponseStatusType();
							status.setStatusCode(PlataformaErrores.STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
							status.setDetails(PlataformaErrores.STATUSDETAILS_KO_CAMPOS_OBLIGATORIOS);
							status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
							msj.setErrorMensaje(status);
							listaMensajesProcesados.add(msj);
						}
					} // del for
				} else {

					// ///////////////////////////esto es el envío
					// normal////////////////////////
					for (MensajePeticionLotesPushXMLBean mensaje : listaMensajes) {

						String faltaCampoObligatorio = evaluarMensajeNotificacionCompleto(mensaje.getTitulo(),
								mensaje.getCuerpo(), mensaje.getDestinatariosPush().getDestinatarioPush());

						if (faltaCampoObligatorio == null) {
							for (DestinatarioPeticionLotesPushXMLBean d : mensaje.getDestinatariosPush()
									.getDestinatarioPush()) {
								if (d == null || null == d.getIdentificadorUsuario() || d.getIdentificadorUsuario().length() <= 0) {
									Mensaje msj = new Mensaje();
									msj.setIdExterno("");
									msj.setIdMensaje("");
									ResponseStatusType status = new ResponseStatusType();
									status.setStatusCode(ps.getMessage(
											"plataformaErrores.notificacionesPUSH.statusCode.KO", null));
									status.setDetails(ps.getMessage(
											"plataformaErrores.notificacionesPUSH.statusDetails.KO", null));
									status.setStatusText(ps.getMessage(
											"plataformaErrores.notificacionesPUSH.statusText.KO", null));
									msj.setErrorMensaje(status);
									listaMensajesProcesados.add(msj);
								} else {

									List<Long> litaDispositvos = usuariosPushManager.getDispositivosUsuario(
											d.getIdentificadorUsuario(),
											Integer.parseInt(notificacionPush.getServicio()));

									ResponseStatusType status = new ResponseStatusType();
									if (!litaDispositvos.isEmpty()) {
										for (Long usuarioId : litaDispositvos) {
											if (d == null || d.getIdentificadorUsuario().length() <= 0) {
												Mensaje msj = new Mensaje();
												msj.setIdExterno("");
												msj.setIdMensaje("");
												status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
												status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
												status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
												msj.setErrorMensaje(status);
												listaMensajesProcesados.add(msj);
											} else {
												if (null == mensajeCreado) {
													mensajeCreado = mensajesManager.insertarMensajePush(
															Long.valueOf(idLote), mensaje, notificacionPush, d,
															usuarioId.intValue());
													listaMensajesProcesados.add(mensajeCreado);

													// insertamos en tabla
													// destinatarios-mensajes
													Long desMensaje = destinatariosMensajesManager
															.insertarDestinatarioMensaje(mensajeCreado.getIdMensaje(),
																	usuarioId.toString(), d.getIdExterno(),
																	notificacionPush.getUsuario());
													historicosManager.creaHistorico(
															Long.parseLong(mensajeCreado.getIdMensaje()), desMensaje,
															estadoId, null, null, null, notificacionPush.getUsuario());

													sendMensajeJMS(notificacionPush, ps, mensajeCreado, d, desMensaje);

												} else {

													// insertamos en tabla
													// destinatarios-mensajes
													Long desMensaje = destinatariosMensajesManager
															.insertarDestinatarioMensaje(mensajeCreado.getIdMensaje(),
																	usuarioId.toString(), d.getIdExterno(),
																	notificacionPush.getUsuario());
													historicosManager.creaHistorico(
															Long.parseLong(mensajeCreado.getIdMensaje()), desMensaje,
															estadoId, null, null, null, notificacionPush.getUsuario());
													
													sendMensajeJMS(notificacionPush, ps, mensajeCreado, d, desMensaje);
													
												}
											}

										}
									} else {
										Mensaje msj = new Mensaje();
										msj.setIdExterno("");
										msj.setIdMensaje("");
										status.setStatusCode(PlataformaErrores.STATUS_KO_DISPOSITIVO);
										status.setDetails(PlataformaErrores.STATUSDETAILS_KO_DISPOSITIVO);
										status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
										msj.setErrorMensaje(status);
										listaMensajesProcesados.add(msj);

									}
								}

							}
							mensajeCreado = null;
						} else {
							Mensaje msj = new Mensaje();
							msj.setIdExterno("");
							msj.setIdMensaje("");
							ResponseStatusType status = new ResponseStatusType();
							status.setStatusCode(PlataformaErrores.STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
							status.setDetails(PlataformaErrores.STATUSDETAILS_KO_CAMPOS_OBLIGATORIOS);
							status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
							msj.setErrorMensaje(status);
							listaMensajesProcesados.add(msj);
						}
					}

				}
			}
			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados, listaErroresGenerales, listaErroresLote);

		} catch (Exception e) {
			logger.error("EnvioMensajesImpl.enviarNotificacion", e);
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
		}

		return Utils.convertToUTF8(xmlRespues);
	}

	private void sendMensajeJMS(EnvioPushXMLBean notificacionPush, PropertiesServices ps, Mensaje mensajeCreado,
			DestinatarioPeticionLotesPushXMLBean d, Long desMensaje) {
		MensajeJMS mensajeJms = new MensajeJMS();
		mensajeJms.setIdMensaje(mensajeCreado.getIdMensaje());
		mensajeJms.setIdExterno(d.getIdExterno());
		mensajeJms.setDestinatarioMensajeId(desMensaje.toString());
		mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_PUSH",
				null));
		Long maxRetries = null;
		TblServicios servicio = serviciosManager.getServicio(Long
				.parseLong(notificacionPush.getServicio()));
		if (servicio.getNumeroMaxReenvios() != null
				&& servicio.getNumeroMaxReenvios() > 0) {
			maxRetries = servicio.getNumeroMaxReenvios().longValue();
		} else {
			maxRetries = Long.parseLong(ps.getMessage(
					"constantes.servicio.numMaxReenvios", null));
		}
		sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
	}

	private String evaluarMensajeNotificacionCompleto(String titulo, String cuerpo,
			List<DestinatarioPeticionLotesPushXMLBean> listaDestinatarios) {
		String res;
		res = checkTitulo(titulo);
		if (null == res)
			res = checkBodyMessage(cuerpo);

		if (null == res)
			res = checkDestinatariosPush(listaDestinatarios);

		return res;
	}

	private String evaluarMensajeNotificacionCompletoServicio(String titulo, String cuerpo) {
		String res;
		res = checkTitulo(titulo);
		if (null == res)
			res = checkBodyMessage(cuerpo);

		return res;
	}

	
	private String evaluarMensajeMailCompleto(String asunto, String cuerpo,
			List<DestinatarioPeticionLotesMailXMLBean> destinatariosMail) {
		String res;
		res = checkAsunto(asunto);
		if (null == res)
			res = checkBodyMessage(cuerpo);
		if (null == res)
			res = checkDestinatariosEmail(destinatariosMail);
		return res;
	}

	private String checkDestinatariosEmail(List<DestinatarioPeticionLotesMailXMLBean> destinatarios) {
		String res = null;
		if (destinatarios == null || destinatarios.isEmpty()) {
			res = WSPlataformaErrors.getErrorFaltaDestinatario();
		}
		return res;
	}

	private String checkDestinatariosPush(List<DestinatarioPeticionLotesPushXMLBean> destinatarios) {
		String res = null;
		if (destinatarios == null || destinatarios.isEmpty()) {
			res = WSPlataformaErrors.getErrorFaltaDestinatario();
		}
		return res;
	}

	private String checkAsunto(String asunto) {
		String res = null;
		if (asunto == null || asunto.length() <= 0) {
			res = WSPlataformaErrors.getErrorFaltaAsunto();
		}
		return res;
	}

	private String checkTitulo(String titulo) {
		String res = null;
		if (titulo == null || titulo.length() <= 0) {
			res = WSPlataformaErrors.getErrorFaltaTitulo();
		}
		return res;
	}

	private String checkBodyMessage(String cuerpo) {
		String res = null;
		if (cuerpo == null || cuerpo.length() <= 0) {
			res = WSPlataformaErrors.getErrorFaltaCuerpo();
		}
		return res;
	}

	private String peticionCorrecta(String nombreLote, String servicio, String usuario, String password) {
		String res;
		res = checkUsuario(usuario);

		if (null == res)
			res = checkPassword(password);

		if (null == res)
			res = checkServicio(servicio);

		if (null == res)
			res = checkNombreLote(nombreLote);
		return res;
	}

	private String peticionCorrectaSMS(String nombreLote, String servicio, String usuario, String password,
			String organismoPagador) {
		String res;
		res = checkUsuario(usuario);

		if (null == res)
			res = checkPassword(password);

		if (null == res)
			res = checkServicio(servicio);

		if (null == res)
			res = checkNombreLote(nombreLote);

		if (null == res && (esMultiorganismo(servicio))) {
			res = checkOrganismoPagador(organismoPagador);

			if (null == res)
				res = checkOrganismoAsociado(servicio, organismoPagador);

		}

		return res;
	}

	private String checkOrganismoAsociado(String servicio, String organismoPagador) {
		String res = null;
		if (esMultiorganismo(servicio) && !asociadoAlOrganismo(servicio, organismoPagador)) {
			return WSPlataformaErrors.getErrorOrganismoNoAsociadoServicio();
		}
		return res;
	}

	private String checkOrganismoPagador(String organismoPagador) {
		String res = null;
		if (null == organismoPagador || organismoPagador.isEmpty()) {
			return WSPlataformaErrors.getErrorMultiOrganismoOrganismoPagador();
		}
		return res;
	}

	private String checkNombreLote(String nombreLote) {
		String res = null;
		if (null == nombreLote || nombreLote.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaLote();
		}
		return res;
	}

	private String checkServicio(String servicio) {
		String res = null;
		if (null == servicio || servicio.isEmpty()) {
			res = WSPlataformaErrors.getErrorFaltaServicio();
		}
		try {
			Integer.parseInt(servicio);
		} catch (NumberFormatException e) {
			res = WSPlataformaErrors.getErrorServicioNoNumerico();
		}
		return res;
	}

	private String checkPassword(String password) {
		String res = null;
		if (null == password || password.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaPassword();
		}
		return res;
	}

	private String checkUsuario(String usuario) {
		String res = null;
		if (null == usuario || usuario.isEmpty()) {
			res = WSPlataformaErrors.getErrorFaltaUsuario();
		}
		return res;
	}

	@Override
	public boolean esMultiorganismo(String servicio) {
		TblServicios serv = serviciosManager.getServicio(Long.parseLong(servicio));
		return (null != serv && null != serv.getMultiorganismo() && serv.getMultiorganismo()) ? true : false;

	}

	@Override
	public boolean asociadoAlOrganismo(String servicio, String organismoPagador) {
		return queryExecutorOrganismos.asociadoOrganismoServicio(Integer.parseInt(servicio), organismoPagador);
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
	 * @return the historicosManager
	 */
	public TblHistoricosManager getHistoricosManager() {
		return historicosManager;
	}

	/**
	 * @param historicosManager
	 *            the historicosManager to set
	 */
	public void setHistoricosManager(TblHistoricosManager historicosManager) {
		this.historicosManager = historicosManager;
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
	 * @return the usuariosPushManager
	 */
	public TblUsuariosPushManager getUsuariosPushManager() {
		return usuariosPushManager;
	}

	/**
	 * @param usuariosPushManager
	 *            the usuariosPushManager to set
	 */
	public void setUsuariosPushManager(TblUsuariosPushManager usuariosPushManager) {
		this.usuariosPushManager = usuariosPushManager;
	}

	/**
	 * @return the adjuntosManager
	 */
	public TblAdjuntosManager getAdjuntosManager() {
		return adjuntosManager;
	}

	/**
	 * @param adjuntosManager
	 *            the adjuntosManager to set
	 */
	public void setAdjuntosManager(TblAdjuntosManager adjuntosManager) {
		this.adjuntosManager = adjuntosManager;
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
	 * @return the destinatariosManager
	 */
	public TblDestinatariosManager getDestinatariosManager() {
		return destinatariosManager;
	}

	/**
	 * @param destinatariosManager
	 *            the destinatariosManager to set
	 */
	public void setDestinatariosManager(TblDestinatariosManager destinatariosManager) {
		this.destinatariosManager = destinatariosManager;
	}

	/**
	 * @return the estadosManager
	 */
	public TblEstadosManager getEstadosManager() {
		return estadosManager;
	}

	/**
	 * @param estadosManager
	 *            the estadosManager to set
	 */
	public void setEstadosManager(TblEstadosManager estadosManager) {
		this.estadosManager = estadosManager;
	}

	/**
	 * @return the sender
	 */
	public SIMMessageSender getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(SIMMessageSender sender) {
		this.sender = sender;
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
	 * @return the servidoresServiciosManager
	 */
	public TblServidoresServiciosManager getServidoresServiciosManager() {
		return servidoresServiciosManager;
	}

	/**
	 * @param servidoresServiciosManager
	 *            the servidoresServiciosManager to set
	 */
	public void setServidoresServiciosManager(TblServidoresServiciosManager servidoresServiciosManager) {
		this.servidoresServiciosManager = servidoresServiciosManager;
	}

	/**
	 * @return the serviciosMovilesManager
	 */
	public TblServiciosMovilesManager getServiciosMovilesManager() {
		return serviciosMovilesManager;
	}

	/**
	 * @param serviciosMovilesManager
	 *            the serviciosMovilesManager to set
	 */
	public void setServiciosMovilesManager(TblServiciosMovilesManager serviciosMovilesManager) {
		this.serviciosMovilesManager = serviciosMovilesManager;
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

}
