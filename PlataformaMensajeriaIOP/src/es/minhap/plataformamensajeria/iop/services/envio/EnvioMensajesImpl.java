package es.minhap.plataformamensajeria.iop.services.envio;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

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
import es.minhap.plataformamensajeria.iop.jdbc.EnvioMensajesDAO;
import es.minhap.plataformamensajeria.iop.jdbc.EnvioNotificacionPushDAO;
import es.minhap.plataformamensajeria.iop.jdbc.RegistroUsuariosServiciosMovilesDAO;
import es.minhap.plataformamensajeria.iop.jdbc.ServicioDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;

public class EnvioMensajesImpl implements IEnvioMensajesService {
	static Logger logger = Logger.getLogger(EnvioMensajesImpl.class);

	private static String STATUSTEXT_KO = "KO";
	private static String STATUSDETAILS_KO = "No existe cuerpo del mensaje";
	private static String STATUSCODE_KO = "3014";
	private static String STATUSDETAILS_KO_DISPOSITIVO = "No existen dispositivos asociados para ese idUsuario";
	private static String STATUS_KO_DISPOSITIVO = "3013";
	private static String STATUSDETAILS_KO_CAMPOS_OBLIGATORIOS = "No existe cuerpo del mensaje";
	private static String STATUSCODE_KO_CAMPOS_OBLIGATORIOS = "3005";
	private static String ERROR_ADJUNTOS ="Se ha producido un error al adjuntar archivos";
	private static int GESTION_MULTIDESTINATARIOS = 1;

	@Override
	public String enviarEmail(EnvioEmailXMLBean envioEmail, List<String> listaErrores) {
		logger.debug("[EnviarEmail] XML envioEmail recibido");

		String xmlRespues = null;
		Respuesta respuesta = new Respuesta();
		logger.debug("[EnviarEmail] Iniciando transaccion bbdd");
		ArrayList<String> listaErroresGenerales = new ArrayList<String>();

		// Tratamiento para errores producidos en la generaci�n del passbook 
		if (listaErrores != null) {
			listaErroresGenerales.addAll(listaErrores);
		}
		
		ArrayList<String> listaErroresLote = new ArrayList<String>();
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje>();
		Integer idLote = null;
		Mensaje mensajeCreado = null;
		EnvioMensajesDAO dao = new EnvioMensajesDAO();
		try {

			String error = peticionCorrecta(envioEmail.getNombreLote(),
					envioEmail.getServicio(), envioEmail.getUsuario(),
					envioEmail.getPassword());
			if (error != null) {
				listaErroresGenerales.add(error);
			}
			if (listaErroresGenerales.size() <= 0) {
				dao.beginTransaction();
				idLote = dao.crearLote(envioEmail.getServicio(),
						envioEmail.getNombreLote(), envioEmail.getUsuario(),
						envioEmail.getPassword(), GESTION_MULTIDESTINATARIOS);
				if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
					listaErroresLote.add(WSPlataformaErrors
							.getErrorCrearLote(idLote));
					xmlRespues = respuesta.toXMLSMS(idLote,
							listaMensajesProcesados, listaErroresGenerales,
							listaErroresLote);
					dao.endTransaction(false);
					dao.closeAll();
					return xmlRespues;
				} else {
					dao.endTransaction(true);
					dao.closeAll();
				}
				
				logger.debug("[EnviarEmail] Lote creado correctamente con ID "
						+ idLote);
				logger.debug("[EnviarEmail] Creando mensajes ");

				ArrayList<MensajesXMLBean> listaMensajes = envioEmail
						.getListadoMensajes();
				for (MensajesXMLBean mensaje : listaMensajes) {

					String faltaCampoObligatorio = evaluarMensajeMailCompleto(
							mensaje.getAsunto(), mensaje.getCuerpo(),
							mensaje.getListaDestinatarios());

					if (faltaCampoObligatorio != null) {
						Mensaje msj = new Mensaje();
						msj.setIdExterno("");
						msj.setIdMensaje("");
						ResponseStatusType status = new ResponseStatusType();
						status.setStatusCode(STATUSCODE_KO);
						status.setDetails(STATUSDETAILS_KO);
						status.setStatusText(faltaCampoObligatorio);
						msj.setErrorMensaje(status);
						listaMensajesProcesados.add(msj);
					} else {

						for (DestinatarioPeticionLotesMailXMLBean destinatario : mensaje.getListaDestinatarios()) {
							dao.beginTransaction();
							String to = destinatario.getDestinatarios().getTo();
							String cc = destinatario.getDestinatarios().getCC();
							String bcc = destinatario.getDestinatarios().getBcc();

							logger.debug("[EnviarEmail] MODO 1 - Antes de crearEmail() - Tam Cuerpo: " + mensaje.getCuerpo().length());
							mensajeCreado = dao.crearEmail(idLote, mensaje.getAsunto(), mensaje.getCuerpo(), mensaje.getDocUsuario(), mensaje.getCodSIA(),
									mensaje.getCodOrganismo(), mensaje.getIdExterno(), mensaje.getFormatoCuerpo(), mensaje.getCodificacion(), mensaje.getPrioridad(), cc, bcc, to,
									envioEmail.getUsuario(), envioEmail.getPassword(), mensaje.getModo());
							logger.debug("[EnviarEmail] MODO 1 - Despues de crearEmail() ");
							mensaje.setIdMensaje(mensajeCreado.toString());

							listaMensajesProcesados.add(mensajeCreado);

							if (mensajeCreado.getErrorMensaje() == null) {
								// Si no hay errores se guardan los
								// adjuntos
								for (AdjuntosXMLBean adjunto : mensaje.getListaAdjuntos()) {
									Integer adjuntoId = dao.nuevoAnexo(Integer.parseInt(mensajeCreado.getIdMensaje()), adjunto.getNombre(), adjunto.getContenido(),
											envioEmail.getUsuario(), envioEmail.getPassword());
									if (WSPlataformaErrors.getErrorCrearAnexo(adjuntoId) != null) {
										ResponseStatusType status = responseStatusError();
										mensajeCreado.setErrorMensaje(status);
										listaMensajesProcesados.add(mensajeCreado);

									}
								}
								for (AdjuntosXMLBean adjunto : envioEmail.getListadoAdjuntosGenerales()) {
									if (adjunto.getIdAdjunto() == null) {
										Integer adjuntoId = dao.nuevoAnexo(Integer.parseInt(mensajeCreado.getIdMensaje()), adjunto.getNombre(), adjunto.getContenido(),
												envioEmail.getUsuario(), envioEmail.getPassword());
										adjunto.setIdAdjunto(adjuntoId);
										if (WSPlataformaErrors.getErrorCrearAnexo(adjuntoId) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}

									} else {
										// Integer idAdjuntoAsociar =
										// adjuntosProcesados.get(adjunto.getNombre());
										Integer idAdjuntoAsociar = adjunto.getIdAdjunto();
										Integer salida = dao.asociarAnexo(Integer.parseInt(mensajeCreado.getIdMensaje()), idAdjuntoAsociar, envioEmail.getUsuario(),
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
										Integer imagenId = dao.nuevaImagen(Integer.parseInt(mensajeCreado.getIdMensaje()), imagen.getCid(), imagen.getContenido(),
												envioEmail.getUsuario(), envioEmail.getPassword());
										if (WSPlataformaErrors.getErrorCrearAnexo(imagenId) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}
									} else {
										Integer idImagen = imagen.getIdImagen();
										Integer salida = dao.asociarImagen(Integer.parseInt(mensajeCreado.getIdMensaje()), idImagen, envioEmail.getUsuario(),
												envioEmail.getPassword());
										if (WSPlataformaErrors.getErrorAsociarAnexo(salida) != null) {
											ResponseStatusType status = responseStatusError();
											mensajeCreado.setErrorMensaje(status);
											listaMensajesProcesados.add(mensajeCreado);
										}
									}
								}

							}

							ArrayList<Long> listaTblDestinatarios = dao.getListaTblDestinatarios(mensajeCreado.getIdMensaje());

							dao.setDestinatarioMensajeMail(mensajeCreado.getIdMensaje(), listaTblDestinatarios, destinatario.getIdExterno(), envioEmail.getUsuario());
							dao.endTransaction(true);
							dao.closeAll();

						}// del for

					}
					mensajeCreado = null;
				}
				
			}
			logger.debug("[EnviarEmail] Creando XML de respuesta");
			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados,
					listaErroresGenerales, listaErroresLote);
			logger.debug("[EnviarEmail] XML de respuesta generado");
		} catch (Exception e) {
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
		} finally {
			if (xmlRespues != null && xmlRespues.length() > 0) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}
		return Utils.convertToUTF8(xmlRespues);
	}

	@Override
	public String enviarEmail(EnvioEmailXMLBean envioEmail) {
		return enviarEmail(envioEmail, null);
	}

	private ResponseStatusType responseStatusError() {
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(STATUSCODE_KO);
		status.setDetails(STATUSDETAILS_KO);
		status.setStatusText(ERROR_ADJUNTOS);
		return status;
	}

	@Override
	public String enviarSMS(EnvioSMSXMLBean envioSMS) {
		String xmlRespues = null;

		EnvioMensajesDAO dao = new EnvioMensajesDAO();
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<Mensaje>();
		ArrayList<String> listaErroresGenerales = new ArrayList<String>();
		ArrayList<String> listaErroresLote = new ArrayList<String>();
		Integer idLote = null;
		dao.beginTransaction();
		Respuesta respuesta = new Respuesta();
		Mensaje mensajeCreado = null;
		try {

			String error = peticionCorrectaSMS(envioSMS.getNombreLote(),
					envioSMS.getServicio(), envioSMS.getUsuario(),
					envioSMS.getPassword(), envioSMS.getOrganismoPagador());
			if (error != null) {
				listaErroresGenerales.add(error);
			}
			if (listaErroresGenerales.size() <= 0) {
				idLote = dao.crearLote(envioSMS.getServicio(),
						envioSMS.getNombreLote(), envioSMS.getUsuario(),
						envioSMS.getPassword(), GESTION_MULTIDESTINATARIOS);
				if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
					listaErroresLote.add(WSPlataformaErrors
							.getErrorCrearLote(idLote));
					xmlRespues = respuesta.toXMLSMS(idLote,
							listaMensajesProcesados, listaErroresGenerales,
							listaErroresLote);

					dao.endTransaction(false);
					dao.closeAll();
					return xmlRespues;
				}
				ArrayList<MensajeSMSXMLBean> listaMensajes = envioSMS
						.getListadoMensajes();

				for (MensajeSMSXMLBean mensaje : listaMensajes) {

					if (mensaje.getCuerpo() == null
							|| mensaje.getCuerpo().length() <= 0
							|| mensaje.getListaDestinatarios().size() == 0) {
						error = WSPlataformaErrors.getErrorFaltaCuerpo();
						Mensaje msj = new Mensaje();
						msj.setIdExterno("");
						msj.setIdMensaje("");
						ResponseStatusType status = new ResponseStatusType();
						status.setStatusCode(STATUSCODE_KO);
						status.setDetails(STATUSDETAILS_KO);
						status.setStatusText(STATUSTEXT_KO);
						msj.setErrorMensaje(status);
						listaMensajesProcesados.add(msj);

					} else {
						for (DestinatarioPeticionLotesSMSXMLBean destinatario : mensaje
								.getListaDestinatarios()) {
							if (destinatario.getDestinatario() != null
									&& Utils.validarTelefono(destinatario
											.getDestinatario()) == 1) {
								error = WSPlataformaErrors
										.getErrorFaltaDestinatario();
								Mensaje msj = new Mensaje();
								msj.setIdExterno("");
								msj.setIdMensaje("");
								ResponseStatusType status = new ResponseStatusType();
								status.setStatusCode(STATUS_KO_DISPOSITIVO);
								status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
								status.setStatusText(STATUSTEXT_KO);
								msj.setErrorMensaje(status);
								listaMensajesProcesados.add(msj);
							} else {
								if (null == mensajeCreado) {
									mensajeCreado = dao.crearSMS(idLote,
											mensaje.getCuerpo(),
											mensaje.getDocUsuario(),
											mensaje.getCodSIA(),
											mensaje.getCodOrganismo(),
											mensaje.getCodOrganismoPagador(),
											mensaje.getIdExterno(),
											null, envioSMS.getUsuario(),
											envioSMS.getPassword());
									listaMensajesProcesados.add(mensajeCreado);
									// ahora insertamos en la tabla
									// destinatariosMensajes
									dao.setDestinatarioMensajeSMS(
											mensajeCreado.getIdMensaje(),
											destinatario.getDestinatario(),
											destinatario.getIdExterno(),
											envioSMS.getUsuario());
									//
								} else {// es un destinatario mas, hay que
										// insertarlo solo en tabla
										// destinatario-mensajes
									dao.setDestinatarioMensajeSMS(
											mensajeCreado.getIdMensaje(),
											destinatario.getDestinatario(),
											destinatario.getIdExterno(),
											envioSMS.getUsuario());
									//
								}
							}
						}

					}
					mensajeCreado = null;
				}
			}
			dao.endTransaction(true);
			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados,
					listaErroresGenerales, listaErroresLote);
		} catch (Exception e) {
			dao.endTransaction(false);
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
			try {
				xmlRespues = respuesta.toXMLSMS(idLote,
						listaMensajesProcesados, listaErroresGenerales,
						listaErroresLote);
			} catch (PlataformaBusinessException e1) {

			}
		} finally {
			dao.connClose();

			dao.closeAll();
		}

		return Utils.convertToUTF8(xmlRespues);
	}

	private String peticionCorrecta(String nombreLote, String servicio,
			String usuario, String password) {
		String res = null;
		if (usuario == null || usuario.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaUsuario();
		} else if (password == null || password.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaPassword();
		} else if (servicio == null || servicio.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaServicio();
		} else if (nombreLote == null || nombreLote.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaLote();
		} else {
			return res;
		}

	}

	private String peticionCorrectaSMS(String nombreLote, String servicio,
			String usuario, String password, String organismoPagador) {
		String res = null;
		if (null == usuario || usuario.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaUsuario();
		} else if (null == password || password.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaPassword();
		} else if (null == servicio || servicio.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaServicio();
		} else if (null == nombreLote || nombreLote.isEmpty()) {
			return WSPlataformaErrors.getErrorFaltaLote();
		} else if (esMultiorganismo(servicio)){
				if(null == organismoPagador || organismoPagador.isEmpty()) {
					return WSPlataformaErrors.getErrorMultiOrganismoOrganismoPagador();
				} else if (esMultiorganismo(servicio) && !asociadoAlOrganismo(servicio, organismoPagador)) {
					return WSPlataformaErrors.getErrorOrganismoNoAsociadoServicio();
				}	
		} 
		return res;
	}
	
	@Override
	public String enviarNotificacion(EnvioPushXMLBean notificacionPush) {
		return enviarNotificacion(notificacionPush, null);
	}

	@Override
	public String enviarNotificacion(EnvioPushXMLBean notificacionPush, PropertiesServices ps) {
		String xmlRespues = null;

		EnvioNotificacionPushDAO dao = new EnvioNotificacionPushDAO();
		RegistroUsuariosServiciosMovilesDAO rusmDao = new RegistroUsuariosServiciosMovilesDAO();
		ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados = new ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje>();
		ArrayList<String> listaErroresGenerales = new ArrayList<String>();
		ArrayList<String> listaErroresLote = new ArrayList<String>();
		Integer idLote = null;
		Respuesta respuesta = new Respuesta();
		Mensaje mensajeCreado = null;
		String servicioPUSH = ps.getMessage("servicio.notificacionesPUSH", null, null, null);
		try {
			dao.beginTransaction();

			String error = peticionCorrecta(notificacionPush.getNombreLote(),
					notificacionPush.getServicio(),
					notificacionPush.getUsuario(),
					notificacionPush.getPassword());
			if (error != null) {
				listaErroresGenerales.add(error);
			}
			
			if (listaErroresGenerales.size() <= 0) {
				ArrayList<MensajePeticionLotesPushXMLBean> listaMensajes = notificacionPush.getListadoMensajes();

				if (notificacionPush.getServicio().equalsIgnoreCase(servicioPUSH)) {
					mensajeCreado = null;
					for (MensajePeticionLotesPushXMLBean mensaje : listaMensajes) {
						
						// Tratamiento para el campo idServicio
						String cuerpoMensaje = mensaje.getCuerpo();
						JSONObject jsonObject = new JSONObject(cuerpoMensaje.replace("\\", ""));
						String idServicioMovil = (!jsonObject.isNull("idServicioMovil")) ? jsonObject.getString("idServicioMovil") : null;
	
						if (null != idServicioMovil && !idServicioMovil.isEmpty()) {
							if (rusmDao.checkMobileActiveService(idServicioMovil)) {
								
								if (null != mensaje.getDestinatariosPush()) {
									for (DestinatarioPeticionLotesPushXMLBean d : mensaje
											.getDestinatariosPush().getDestinatarioPush()) {
										// no viene informado usuario en el tag identificador usuario
										if (d == null
												|| d.getIdentificadorUsuario().length() <= 0) {
											List<UsuariosServiciosMovilesBean> listaDispositvos = dao.getUsuarioPorServicio(Integer.parseInt(idServicioMovil));
											ResponseStatusType status = new ResponseStatusType();
											if (listaDispositvos.size()>0){
											for (UsuariosServiciosMovilesBean usuario : listaDispositvos) {
													if (null == mensajeCreado) {
														logger.info("EnvioMensajeImpl [enviarNotificacion]: creando mensaje.");
														if (idLote == null) {
															idLote = dao.crearLote(notificacionPush.getServicio(),
																	notificacionPush.getNombreLote(),
																	notificacionPush.getUsuario(),
																	notificacionPush.getPassword(),
																	GESTION_MULTIDESTINATARIOS);
		
															if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
																logger.info("EnvioMensajeImpl [enviarNotificacion]: Error creando lote.");
																listaErroresLote.add(WSPlataformaErrors
																		.getErrorCrearLote(idLote));
																xmlRespues = respuesta.toXMLSMS(idLote,
																		listaMensajesProcesados, listaErroresGenerales,
																		listaErroresLote);
		
																dao.endTransaction(false);
																dao.closeAll();
																return xmlRespues;
															}
														}

														mensajeCreado = dao
																.crearMensajeNotificacionPush(
																		idLote,
																		mensaje.getTitulo(),
																		mensaje.getCuerpo(),
																		usuario.getDocUsuario(),
																		notificacionPush.getCodSIA(),
																		notificacionPush.getCodOrganismo(),
																		mensaje.getIcono(),
																		mensaje.getSonido(),
																		usuario.getIdExterno(),
																		usuario.getUsuario(),
																		notificacionPush.getUsuario(),
																		notificacionPush.getPassword(),
																		usuario.getIdUsuario());

														listaMensajesProcesados
																.add(mensajeCreado);
														// insertamos en tabla
														// destinatarios-mensajes
														logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado!=null?mensajeCreado.getIdMensaje():"Mensaje nulo" + " para usuario " + usuario);
														dao.setDestinatarioMensajePUSH(
																mensajeCreado.getIdMensaje(),
																usuario.getUsuario(),
																usuario.getIdExterno(),
																notificacionPush.getUsuario(),
																usuario.getIdUsuario());
													} else {
														// insertamos en tabla
														// destinatarios-mensajes
														logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado.getIdMensaje() + " para usuario " + usuario);
														dao.setDestinatarioMensajePUSH(
																mensajeCreado.getIdMensaje(),
																usuario.getUsuario(),
																usuario.getIdExterno(),
																notificacionPush.getUsuario(),
																usuario.getIdUsuario());
													}
											}
											}else{
												Mensaje msj = new Mensaje();
												msj.setIdExterno("");
												msj.setIdMensaje("");
												status.setStatusCode(STATUS_KO_DISPOSITIVO);
												status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
												status.setStatusText(STATUSTEXT_KO);
												msj.setErrorMensaje(status);
												listaMensajesProcesados.add(msj);
												
											}
											
											
										} else {
											
										// lleva destinatarios
										logger.info("EnvioMensajeImpl [enviarNotificacion]: obteniendo dispositivos de usuario" + d.getIdentificadorUsuario());
										ArrayList<Integer> listaDispositvos = dao
												.getDispositivosUsuarioServicioMovil(
														d.getIdentificadorUsuario(),
														Integer.parseInt(notificacionPush
																.getServicio()), Integer.parseInt(idServicioMovil));
										ResponseStatusType status = new ResponseStatusType();
										if (listaDispositvos.size()>0){
										for (Integer usuarioId : listaDispositvos) {
											if (d == null
													/* || d.getIdExterno().length() <= 0 */
													|| d.getIdentificadorUsuario()
															.length() <= 0) {
												error = WSPlataformaErrors
														.getErrorFaltaDestinatario();
												Mensaje msj = new Mensaje();
												msj.setIdExterno("");
												msj.setIdMensaje("");
												status.setStatusCode(STATUS_KO_DISPOSITIVO);
												status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
												status.setStatusText(STATUSTEXT_KO);
												msj.setErrorMensaje(status);
												listaMensajesProcesados.add(msj);
											} else {
												if (rusmDao.comprobarUsuarioServicioValido(usuarioId, idServicioMovil) == true) {
													if (null == mensajeCreado) {
														logger.info("EnvioMensajeImpl [enviarNotificacion]: creando mensaje.");
														if (idLote == null) {
															idLote = dao.crearLote(notificacionPush.getServicio(),
																	notificacionPush.getNombreLote(),
																	notificacionPush.getUsuario(),
																	notificacionPush.getPassword(),
																	GESTION_MULTIDESTINATARIOS);
		
															if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
																logger.info("EnvioMensajeImpl [enviarNotificacion]: Error creando lote.");
																listaErroresLote.add(WSPlataformaErrors
																		.getErrorCrearLote(idLote));
																xmlRespues = respuesta.toXMLSMS(idLote,
																		listaMensajesProcesados, listaErroresGenerales,
																		listaErroresLote);
		
																dao.endTransaction(false);
																dao.closeAll();
																return xmlRespues;
															}
														}

														mensajeCreado = dao
																.crearMensajeNotificacionPush(
																		idLote,
																		mensaje.getTitulo(),
																		mensaje.getCuerpo(),
																		d.getDocUsuario(),
																		notificacionPush
																				.getCodSIA(),
																		notificacionPush
																				.getCodOrganismo(),
																		mensaje.getIcono(),
																		mensaje.getSonido(),
																		d.getIdExterno(),
																		d.getIdentificadorUsuario(),
																		notificacionPush
																				.getUsuario(),
																		notificacionPush
																				.getPassword(),
																		usuarioId);

														listaMensajesProcesados
																.add(mensajeCreado);
														// insertamos en tabla
														// destinatarios-mensajes
														logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado!=null?mensajeCreado.getIdMensaje():"Mensaje nulo" + " para usuario " + usuarioId);
														dao.setDestinatarioMensajePUSH(
																mensajeCreado
																		.getIdMensaje(),
																d.getIdentificadorUsuario(),
																d.getIdExterno(),
																notificacionPush
																		.getUsuario(),
																usuarioId);
													} else {
														// insertamos en tabla
														// destinatarios-mensajes
														logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado.getIdMensaje() + " para usuario " + usuarioId);

														dao.setDestinatarioMensajePUSH(
																mensajeCreado
																		.getIdMensaje(),
																d.getIdentificadorUsuario(),
																d.getIdExterno(),
																notificacionPush
																		.getUsuario(),
																usuarioId);
													}
												} else {
													// TODO: Revisar error por usuario no estar suscrito al servicio movil en cuestion
													logger.info("EnvioMensajeImpl [enviarNotificacion]: El usuario no está suscrito al servicio movil indicado");
													error = WSPlataformaErrors.getErrorFaltaCuerpo();
													Mensaje msj = new Mensaje();
													msj.setIdExterno("");
													msj.setIdMensaje("");
													status= new ResponseStatusType();
													status.setStatusCode(STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
													status.setDetails("El usuario no esta suscrito al servicio movil indicado");
													status.setStatusText(STATUSTEXT_KO);
													msj.setErrorMensaje(status);
													listaMensajesProcesados.add(msj);
												}
												
											}

										}
										}else{
											Mensaje msj = new Mensaje();
											msj.setIdExterno("");
											msj.setIdMensaje("");
											status.setStatusCode(STATUS_KO_DISPOSITIVO);
											status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
											status.setStatusText(STATUSTEXT_KO);
											msj.setErrorMensaje(status);
											listaMensajesProcesados.add(msj);
											
										}
									}
									}
								} else {
									
										// No lleva destinatarios
										List<UsuariosServiciosMovilesBean> listaDispositvos = dao.getUsuarioPorServicio(Integer.parseInt(idServicioMovil));
										ResponseStatusType status = new ResponseStatusType();
										if (listaDispositvos.size()>0){
										for (UsuariosServiciosMovilesBean usuario : listaDispositvos) {
												if (null == mensajeCreado) {
													logger.info("EnvioMensajeImpl [enviarNotificacion]: creando mensaje.");
													if (idLote == null) {
														idLote = dao.crearLote(notificacionPush.getServicio(),
																notificacionPush.getNombreLote(),
																notificacionPush.getUsuario(),
																notificacionPush.getPassword(),
																GESTION_MULTIDESTINATARIOS);
	
														if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
															logger.info("EnvioMensajeImpl [enviarNotificacion]: Error creando lote.");
															listaErroresLote.add(WSPlataformaErrors
																	.getErrorCrearLote(idLote));
															xmlRespues = respuesta.toXMLSMS(idLote,
																	listaMensajesProcesados, listaErroresGenerales,
																	listaErroresLote);
	
															dao.endTransaction(false);
															dao.closeAll();
															return xmlRespues;
														}
													}

													mensajeCreado = dao
															.crearMensajeNotificacionPush(
																	idLote,
																	mensaje.getTitulo(),
																	mensaje.getCuerpo(),
																	usuario.getDocUsuario(),
																	notificacionPush.getCodSIA(),
																	notificacionPush.getCodOrganismo(),
																	mensaje.getIcono(),
																	mensaje.getSonido(),
																	usuario.getIdExterno(),
																	usuario.getUsuario(),
																	notificacionPush.getUsuario(),
																	notificacionPush.getPassword(),
																	usuario.getIdUsuario());

													listaMensajesProcesados
															.add(mensajeCreado);
													// insertamos en tabla
													// destinatarios-mensajes
													logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado!=null?mensajeCreado.getIdMensaje():"Mensaje nulo" + " para usuario " + usuario);
													dao.setDestinatarioMensajePUSH(
															mensajeCreado.getIdMensaje(),
															usuario.getUsuario(),
															usuario.getIdExterno(),
															notificacionPush.getUsuario(),
															usuario.getIdUsuario());
												} else {
													// insertamos en tabla
													// destinatarios-mensajes
													logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado.getIdMensaje() + " para usuario " + usuario);
													dao.setDestinatarioMensajePUSH(
															mensajeCreado.getIdMensaje(),
															usuario.getUsuario(),
															usuario.getIdExterno(),
															notificacionPush.getUsuario(),
															usuario.getIdUsuario());
												}
										}
										}else{
											Mensaje msj = new Mensaje();
											msj.setIdExterno("");
											msj.setIdMensaje("");
											status.setStatusCode(STATUS_KO_DISPOSITIVO);
											status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
											status.setStatusText(STATUSTEXT_KO);
											msj.setErrorMensaje(status);
											listaMensajesProcesados.add(msj);
											
										}
									}
									
							} else {
								//Error por id servicio movil incorrecto o no activo
								
								logger.info("EnvioMensajeImpl [enviarNotificacion]: El servicio movil es incorrecto o no está activo");
								error = WSPlataformaErrors.getErrorFaltaCuerpo();
								Mensaje msj = new Mensaje();
								msj.setIdExterno("");
								msj.setIdMensaje("");
								ResponseStatusType status = new ResponseStatusType();
								status.setStatusCode(STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
								status.setDetails("El servicio movil es incorrecto o no está activo");
								status.setStatusText(STATUSTEXT_KO);
								msj.setErrorMensaje(status);
								listaMensajesProcesados.add(msj);
							}
						} else {
							//Error por id Servicio nulo o vacio
							
							logger.info("EnvioMensajeImpl [enviarNotificacion]: El servicio movil es obligatorio o viene mal informado");
							error = WSPlataformaErrors.getErrorFaltaCuerpo();
							Mensaje msj = new Mensaje();
							msj.setIdExterno("");
							msj.setIdMensaje("");
							ResponseStatusType status = new ResponseStatusType();
							status.setStatusCode(STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
							status.setDetails("El identificador del servicio movil es obligatorio");
							status.setStatusText(STATUSTEXT_KO);
							msj.setErrorMensaje(status);
							listaMensajesProcesados.add(msj);
						}
					}
				} else {
					
					// SE lanza normalmente
					for (MensajePeticionLotesPushXMLBean mensaje : listaMensajes) {
						String faltaCampoObligatorio = evaluarMensajeNotificacionCompleto(
								mensaje.getTitulo(), mensaje.getCuerpo(), mensaje
										.getDestinatariosPush()
										.getDestinatarioPush());

						if (faltaCampoObligatorio == null) {
							for (DestinatarioPeticionLotesPushXMLBean d : mensaje
									.getDestinatariosPush().getDestinatarioPush()) {
								if (d == null
								/* || d.getIdExterno().length() <= 0 */
								|| d.getIdentificadorUsuario().length() <= 0) {
									logger.info("EnvioMensajeImpl [enviarNotificacion]: Destinatario nulo o vacio.");
									error = WSPlataformaErrors
											.getErrorFaltaDestinatario();
									Mensaje msj = new Mensaje();
									msj.setIdExterno("");
									msj.setIdMensaje("");
									ResponseStatusType status = new ResponseStatusType();
									status.setStatusCode(STATUSCODE_KO);
									status.setDetails(STATUSDETAILS_KO);
									status.setStatusText(STATUSTEXT_KO);
									msj.setErrorMensaje(status);
									listaMensajesProcesados.add(msj);
								} else {
									logger.info("EnvioMensajeImpl [enviarNotificacion]: obteniendo dispositivos de usuario" + d.getIdentificadorUsuario());
									ArrayList<Integer> litaDispositvos = dao
											.getDispositivosUsuario(
													d.getIdentificadorUsuario(),
													Integer.parseInt(notificacionPush
															.getServicio()));

									ResponseStatusType status = new ResponseStatusType();
									if (litaDispositvos.size()>0){
									for (Integer usuarioId : litaDispositvos) {
										if (d == null
												/* || d.getIdExterno().length() <= 0 */
												|| d.getIdentificadorUsuario()
														.length() <= 0) {
											error = WSPlataformaErrors
													.getErrorFaltaDestinatario();
											Mensaje msj = new Mensaje();
											msj.setIdExterno("");
											msj.setIdMensaje("");
											status.setStatusCode(STATUS_KO_DISPOSITIVO);
											status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
											status.setStatusText(STATUSTEXT_KO);
											msj.setErrorMensaje(status);
											listaMensajesProcesados.add(msj);
										} else {
											if (null == mensajeCreado) {
												logger.info("EnvioMensajeImpl [enviarNotificacion]: creando mensaje.");
												if (idLote == null) {
													idLote = dao.crearLote(notificacionPush.getServicio(),
															notificacionPush.getNombreLote(),
															notificacionPush.getUsuario(),
															notificacionPush.getPassword(),
															GESTION_MULTIDESTINATARIOS);

													if (WSPlataformaErrors.getErrorCrearLote(idLote) != null) {
														logger.info("EnvioMensajeImpl [enviarNotificacion]: Error creando lote.");
														listaErroresLote.add(WSPlataformaErrors
																.getErrorCrearLote(idLote));
														xmlRespues = respuesta.toXMLSMS(idLote,
																listaMensajesProcesados, listaErroresGenerales,
																listaErroresLote);

														dao.endTransaction(false);
														dao.closeAll();
														return xmlRespues;
													}
												}

												mensajeCreado = dao
														.crearMensajeNotificacionPush(
																idLote,
																mensaje.getTitulo(),
																mensaje.getCuerpo(),
																d.getDocUsuario(),
																notificacionPush
																		.getCodSIA(),
																notificacionPush
																		.getCodOrganismo(),
																mensaje.getIcono(),
																mensaje.getSonido(),
																d.getIdExterno(),
																d.getIdentificadorUsuario(),
																notificacionPush
																		.getUsuario(),
																notificacionPush
																		.getPassword(),
																usuarioId);

												listaMensajesProcesados
														.add(mensajeCreado);
												// insertamos en tabla
												// destinatarios-mensajes
												logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado!=null?mensajeCreado.getIdMensaje():"Mensaje nulo" + " para usuario " + usuarioId);
												dao.setDestinatarioMensajePUSH(
														mensajeCreado
																.getIdMensaje(),
														d.getIdentificadorUsuario(),
														d.getIdExterno(),
														notificacionPush
																.getUsuario(),
														usuarioId);
											} else {
												// insertamos en tabla
												// destinatarios-mensajes
												logger.info("EnvioMensajeImpl [enviarNotificacion]: insertando en tabla de destinatarios:" + mensajeCreado.getIdMensaje() + " para usuario " + usuarioId);

												dao.setDestinatarioMensajePUSH(
														mensajeCreado
																.getIdMensaje(),
														d.getIdentificadorUsuario(),
														d.getIdExterno(),
														notificacionPush
																.getUsuario(),
														usuarioId);
											}
										}

									}
									}else{
										Mensaje msj = new Mensaje();
										msj.setIdExterno("");
										msj.setIdMensaje("");
										status.setStatusCode(STATUS_KO_DISPOSITIVO);
										status.setDetails(STATUSDETAILS_KO_DISPOSITIVO);
										status.setStatusText(STATUSTEXT_KO);
										msj.setErrorMensaje(status);
										listaMensajesProcesados.add(msj);
										
									}
								}
								
							}
							mensajeCreado = null;
						} else {
							logger.info("EnvioMensajeImpl [enviarNotificacion]: Faltan campos obligatorios");
							error = WSPlataformaErrors.getErrorFaltaCuerpo();
							Mensaje msj = new Mensaje();
							msj.setIdExterno("");
							msj.setIdMensaje("");
							ResponseStatusType status = new ResponseStatusType();
							status.setStatusCode(STATUSCODE_KO_CAMPOS_OBLIGATORIOS);
							status.setDetails(STATUSDETAILS_KO_CAMPOS_OBLIGATORIOS);
							status.setStatusText(STATUSTEXT_KO);
							msj.setErrorMensaje(status);
							listaMensajesProcesados.add(msj);
						}
					
					}
					
				}
			}
			dao.endTransaction(true);
			logger.info("EnvioMensajeImpl [enviarNotificacion]: mensajes procesados para el lote " + idLote +": " + listaMensajesProcesados.size());
			xmlRespues = respuesta.toXMLSMS(idLote, listaMensajesProcesados,
					listaErroresGenerales, listaErroresLote);
			dao.endTransaction(true);
		} catch (Exception e) {
			logger.error("EnvioMensajeImpl [enviarNotificacion]", e);
			dao.endTransaction(false);
			listaErroresGenerales.add(WSPlataformaErrors.getErrorGeneral());
		} finally {
			dao.closeAll();
		}

		return Utils.convertToUTF8(xmlRespues);
	}

	private String evaluarMensajeNotificacionCompleto(String titulo,
			String cuerpo,
			List<DestinatarioPeticionLotesPushXMLBean> listaDestinatarios) {
		String res = null;
		if (titulo == null || titulo.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaTitulo();
		} else if (cuerpo == null || cuerpo.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaCuerpo();
		} else if (listaDestinatarios == null || listaDestinatarios.size() == 0) {
			return WSPlataformaErrors.getErrorFaltaDestinatario();
		} else {
			return res;
		}
	}

	private String evaluarMensajeMailCompleto(String asunto, String cuerpo,
			List<DestinatarioPeticionLotesMailXMLBean> destinatariosMail) {
		String res = null;
		if (asunto == null || asunto.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaAsunto();
		} else if (cuerpo == null || cuerpo.length() <= 0) {
			return WSPlataformaErrors.getErrorFaltaCuerpo();
		} else if (destinatariosMail == null || destinatariosMail.size() == 0) {
			return WSPlataformaErrors.getErrorFaltaDestinatario();
		} else {
			return res;
		}
	}

	@Override
	public boolean esMultiorganismo(String servicio) {
		ServicioDAO serv = new ServicioDAO();
		serv.beginTransaction();
		boolean multiorganismo = false;
		if (serv.isMultiOrganismo(servicio)) {
			multiorganismo = true;
		}
		serv.connClose();
		serv.closeAll();
		return multiorganismo;
	}

	@Override
	public boolean asociadoAlOrganismo(String servicio, String organismoPagador) {
		ServicioDAO serv = new ServicioDAO();
		serv.beginTransaction();
		boolean asociadoAlOrganismo = false;
		if (serv.asociadoOrganismoServicio(servicio, organismoPagador)) {
				asociadoAlOrganismo = true;
			}
		serv.connClose();
		serv.closeAll();
		return asociadoAlOrganismo;
	}
}
