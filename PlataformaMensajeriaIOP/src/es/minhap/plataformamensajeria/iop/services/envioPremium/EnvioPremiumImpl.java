package es.minhap.plataformamensajeria.iop.services.envioPremium;

import java.util.Map;

import org.apache.log4j.Logger;

import es.minhap.plataformamensaferia.iop.beans.envioPremium.DatosEspecificos;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionEnvioXML;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta;
import es.minhap.plataformamensajeria.iop.beans.EnvioAEATXMLBean;
import es.minhap.plataformamensajeria.iop.beans.UrlMensajePremiumBean;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.EnvioMensajesDAO;
import es.minhap.plataformamensajeria.iop.jdbc.EnvioMensajesPremiumDAO;
import es.minhap.plataformamensajeria.iop.jdbc.MensajeDAO;
import es.minhap.plataformamensajeria.iop.jdbc.OrganismosDAO;
import es.minhap.plataformamensajeria.iop.jdbc.ServicioDAO;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.Proveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;

public class EnvioPremiumImpl implements IEnvioPremiumService {
	static Logger logger = Logger.getLogger(IEnvioPremiumService.class);

	private static int GESTION_MULTIDESTINATARIOS = 1;
	private static String PENDIENTE_ENVIO = "PENDIENTE DE ENVIO";
	private static String PENDIENTE = "PENDIENTE";
	private static String ENVIANDO = "ENVIANDO";
	private static String INCIDENCIA = "INCIDENCIA";
	private static String PENDIENTE_OPERADORA = "PENDIENTE DE OPERADORA";
	private static String ENVIADO = "ENVIADO";
	private static String ANULADO ="ANULADO";
	private static String APLICACION_NOACTIVA =". La aplicacion no esta activa";

	@Override
	public String enviarSMSPremium(EnvioAEATXMLBean envio, String username, String password, Integer servicio, String usernameMISIM, String passwordMISIM, Integer reintentos) {

		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		EnvioMensajesDAO daoLote = new EnvioMensajesDAO();
		MensajeDAO dm = new MensajeDAO();
		ServicioDAO serviciodao = new ServicioDAO();
		OrganismosDAO organismodao = new OrganismosDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		PeticionEnvioXML peticionEnvioXML = null;
		Integer idLote = null;
		Integer idMensaje = null;
		boolean reenviar = false;
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp = null;
		try {
			serviciodao.beginTransaction();
			// Inicializamos las variables
			// envioPremium.initializeVariables();
			if (!serviciodao.isMultiOrganismo("" + servicio)) {
				resp = peticionCorrectaSMS(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(), envio.getIdExterno(), envio.getDestinatario(), envio.getDeliveryReportURL());
			} else {
				organismodao.beginTransaction();
				boolean activo = organismodao.organismoActivoEnServicio(servicio, envio.getCodOrganismoPagadorSMS());
				organismodao.endTransaction(false);
				resp = peticionCorrectaSMSMultiOrganismo(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(), envio.getIdExterno(), envio.getDestinatario(),
						envio.getDeliveryReportURL(), activo);
			}

			if (resp != null) {
				return resp.toXMLSMS(resp);
			}
			dao.beginTransaction();

			// Comprobamos el servicio
			if (null == servicio) {
				resp = codificarRespuesta(PlataformaErrores.COD_2010_GENERAL, PlataformaErrores.DESC_2010_GENERAL,
						PlataformaErrores.STATUSTEXT_KO, envio.getIdExterno(), null);
				return resp.toXMLSMS(resp);
			}

			// Comprobamos usuario y password
			if (null == username || null == password) {
				resp = codificarRespuesta(PlataformaErrores.COD_0008_GENERAL, PlataformaErrores.DESC_0008_GENERAL, PlataformaErrores.STATUSTEXT_KO,
						envio.getIdExterno(), null);
				return resp.toXMLSMS(resp);
			}

			// Comprobamos
			if (Utils.validarTelefono(envio.getDestinatario()) == 1) {
				resp = codificarRespuesta(PlataformaErrores.COD_2000_GENERAL, PlataformaErrores.DESC_2000_GENERAL, PlataformaErrores.STATUSTEXT_KO,
						envio.getIdExterno(), null);
				return resp.toXMLSMS(resp);
			}
			
			// Comprobamos que la aplicación esté activa
			aplicacionDao.beginTransaction();
			Integer activeApplication = aplicacionDao.checkActiveApplication(servicio);
			if (activeApplication.intValue() == 0) {
				resp = codificarRespuesta(PlataformaErrores.COD_0008_GENERAL,	PlataformaErrores.DESC_0008_GENERAL + APLICACION_NOACTIVA, PlataformaErrores.STATUSTEXT_KO,
						envio.getIdExterno(), null);
				return resp.toXMLSMS(resp);
			}
			aplicacionDao.endTransaction(false);


			// Tratamiento de reintentos
			idMensaje = checkMessageExists(dao, envio.getIdExterno());

			if (idMensaje != null) {

				// Comprobar el estado del SMS en sim
				String status = dao.getStatusMessage(idMensaje);
				if (PENDIENTE_ENVIO.equals(status) || PENDIENTE.equals(status) || ENVIANDO.equals(status) || INCIDENCIA.equals(status)) {
					boolean reenvio = comprobarReintentos(dao, idMensaje);
					
					if (reenvio){
						peticionEnvioXML = reenviarSMS(envio, idMensaje, username, password, usernameMISIM, passwordMISIM);
						if (null == peticionEnvioXML) {
							resp = codificarRespuesta(PlataformaErrores.COD_ERROR_SERVIDOR_INCORRECTO, PlataformaErrores.DETAILS_ERROR_SERVIDOR_INCORRECTO,
									PlataformaErrores.STATUSTEXT_KO, envio.getIdExterno(), idMensaje);
						} else {
							resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, envio.getIdExterno(), idMensaje);
							reenviar = true;
						}
					}else{
						// ponemos el estado a anulado
						dao.setEstadoMensaje(idMensaje, ANULADO);
						//OJOO ver qué devolvemos, ahora se devuelve un OK, le aceptamos el reintento pero no hacemos nada.
						resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, envio.getIdExterno(), idMensaje);
					}
					dao.endTransaction(true);
				}  else if (PENDIENTE_OPERADORA.equals(status)) {
					UrlMensajePremiumBean bean = dao.getURLMensajePremiumBeanByIdMessage(idMensaje);
					if (!bean.getUrl().equalsIgnoreCase(envio.getDeliveryReportURL())) {
						dao.updateURL(idMensaje, envio.getDeliveryReportURL());
						dao.endTransaction(true);
					}
					resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, envio.getIdExterno(), idMensaje);
				} else if (ENVIADO.equals(status) || ANULADO.equals(status)) {
					resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, envio.getIdExterno(), idMensaje);
				}
			} else {

				// Creamos el lote
				daoLote.beginTransaction();
				idLote = daoLote.crearLote(servicio.toString(), PlataformaErrores.NOMBRE_LOTE_AEAT, username, password, GESTION_MULTIDESTINATARIOS);
				if (null == idLote) {
					resp = codificarRespuesta(PlataformaErrores.COD_0014_GENERAL, PlataformaErrores.DESC_0014_GENERAL, PlataformaErrores.STATUSTEXT_KO,
							envio.getIdExterno(), null);
					return resp.toXMLSMS(resp);
				} else {
					if (idLote == -1) {
						resp = codificarRespuesta(PlataformaErrores.COD_ERROR_APLICACION, PlataformaErrores.DETAILS_ERROR_APLICACION, PlataformaErrores.STATUSTEXT_KO,
								envio.getIdExterno(), null);
						return resp.toXMLSMS(resp);
					} else if (idLote == -2 || idLote == -3) {
						resp = codificarRespuesta(PlataformaErrores.COD_0016_GENERAL, PlataformaErrores.DESC_0016_GENERAL,
								PlataformaErrores.STATUSTEXT_KO, envio.getIdExterno(), null);
						return resp.toXMLSMS(resp);
					} else if (idLote < 0) {
						resp = codificarRespuesta(PlataformaErrores.COD_0014_GENERAL, PlataformaErrores.DESC_0014_GENERAL, PlataformaErrores.STATUSTEXT_KO,
								envio.getIdExterno(), null);
						return resp.toXMLSMS(resp);
					}
				}

				// Crear Mensaje
				idMensaje = dao.crearMensaje(idLote, envio.getCuerpo(), null, envio.getCodOrganismoPagadorSMS(), envio.getIdExterno(), envio.getDestinatario(), username, password,
						envio.getDeliveryReportURL(),reintentos);
				dao.endTransaction(true);
				daoLote.endTransaction(true);
				if (null == idMensaje || idMensaje <= 0) {
					resp = codificarRespuesta(PlataformaErrores.COD_2006_GENERAL, PlataformaErrores.DESC_2006_GENERAL, PlataformaErrores.STATUSTEXT_KO,
							envio.getIdExterno(), null);
				} else {
					peticionEnvioXML = reenviarSMS(envio, idMensaje, username, password, usernameMISIM, passwordMISIM);
					if (null == peticionEnvioXML) {
						resp = codificarRespuesta(PlataformaErrores.COD_ERROR_SERVIDOR_INCORRECTO, PlataformaErrores.DETAILS_ERROR_SERVIDOR_INCORRECTO,
								PlataformaErrores.STATUSTEXT_KO, envio.getIdExterno(), idMensaje);
					} else {
						resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, envio.getIdExterno(), idMensaje);
						reenviar = true;
					}

				}
			}
			dao.beginTransaction();
			dm.beginTransaction();
			String aux = resp.getDetails();
			String peticion = "";
			if (null != peticionEnvioXML) {
				peticion = "<![CDATA[" + peticionEnvioXML.toXMLSMS() + "]]>";
			}
			String urlPremium = dm.getUrlPremium(idMensaje);
			if (resp.getStatusText().equals(PlataformaErrores.STATUSTEXT_KO)) {
				aux = aux + " | " + urlPremium;
			} else {
				aux = aux + ((reenviar) ? " | " + urlPremium + " | " + peticion : " | " + urlPremium);
			}
			resp.setDetails(aux);
			dao.endTransaction(false);
			dm.endTransaction(false);
			serviciodao.endTransaction(false);
			return resp.toXMLSMS(resp);
		} catch (Exception e) {
			serviciodao.endTransaction(false);
			dao.endTransaction(false);
			daoLote.endTransaction(false);
			dm.endTransaction(false);
			aplicacionDao.endTransaction(false);
		} finally {
			organismodao.endTransaction(false);
			organismodao.connClose();
			organismodao.closeAll();
			serviciodao.connClose();
			serviciodao.closeAll();
			dao.connClose();
			dao.closeAll();
			daoLote.connClose();
			daoLote.closeAll();
			dm.connClose();
			dm.closeAll();
			aplicacionDao.closeAll();
		}
		return null;
	}

	@Override
	public String cambiarEstadoSMSPremium(Integer idMensaje, String statusText) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		Respuesta resp = new Respuesta();
		String idExterno = "";
		String respuesta = null;
		try {
			dao.beginTransaction();
			idExterno = dao.getIdExterno(idMensaje);
			if (statusText.contains("OK")) {
				dao.setEstadoMensajeEnviado(idMensaje, PENDIENTE_OPERADORA, statusText);
				resp = codificarRespuesta(PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK, PlataformaErrores.STATUSTEXT_OK, idExterno, idMensaje);
			} else {
				resp = comprobarError(statusText, idExterno, idMensaje);
				dao.setEstadoMensajeEnviado(idMensaje, INCIDENCIA, statusText);
				// resp = codificarRespuesta(COD_ERROR_GENERAL, statusText ,
				// STATUSTEXT_KO , idExterno,idMensaje);
			}
			dao.endTransaction(true);
			respuesta = resp.toXMLSMS(resp);
		} catch (Exception e) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_ERROR_GENERAL, PlataformaErrores.STATUSTEXT_KO, idExterno, idMensaje);
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
		return respuesta;
	}
	
	@Override
	public String gerUrlEndpoint(String messageId) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		String url = "";
		try {
			dao.beginTransaction();
			
			if (null != messageId && Integer.parseInt(messageId)>0) {
				UrlMensajePremiumBean bean= dao.getURLMensajePremiumBeanByIdMessage(Integer.parseInt(messageId));
				url= bean.getUrl();
			} 
			dao.endTransaction(false);
			
		} catch (Exception e) {
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
		return url;
	}

	private Respuesta comprobarError(String statusText, String idExterno, Integer idMensaje) {
		Respuesta resp = new Respuesta();
		if (null == statusText || statusText.isEmpty()) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_GENERAL, statusText, PlataformaErrores.STATUSTEXT_KO, idExterno, idMensaje);
		} else if (statusText.contains("credit")) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_CREDITO, PlataformaErrores.DETAILS_ERROR_CREDITO, PlataformaErrores.STATUSTEXT_KO, idExterno, idMensaje);
		} else if (statusText.contains("Authorization failed")) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_AUTENTICACION, PlataformaErrores.DETAILS_ERROR_AUTENTICACION, PlataformaErrores.STATUSTEXT_KO, idExterno,
					idMensaje);
		} else if (statusText.contains("credit")) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_GENERAL, statusText, PlataformaErrores.STATUSTEXT_KO, idExterno, idMensaje);
		} else if (statusText.contains("Error")) {
			resp = codificarRespuesta(PlataformaErrores.COD_ERROR_GENERAL, statusText, PlataformaErrores.STATUSTEXT_KO, idExterno, idMensaje);
		}

		return resp;
	}

	private boolean comprobarReintentos(EnvioMensajesPremiumDAO dao, Integer idMensaje) {
		boolean res = false;
		UrlMensajePremiumBean bean = dao.getURLMensajePremiumBeanByIdMessage(idMensaje);
		if (null != bean && bean.getReintentos().intValue() > 0) {
			dao.decrementReintentos(idMensaje);
			return true;
		}
		return res;
	}

	private PeticionEnvioXML reenviarSMS(EnvioAEATXMLBean envio, Integer idMensaje, String usuarioAplicacion, String passAplicacion, String usernameMISIM, String passwordMISIM) {
		return postSMS(idMensaje, envio.getCodOrganismoPagadorSMS(), usuarioAplicacion, passAplicacion, usernameMISIM, passwordMISIM);
	}

	private Integer checkMessageExists(EnvioMensajesPremiumDAO dao, String idExterno) {
		return dao.checkIdExternoExists(idExterno);
	}

	private PeticionEnvioXML postSMS(Integer idMensaje, String codOrganismo, String usuarioAplicacion, String passAplicacion, String usernameMISIM, String passwordMISIM) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		Map<Integer, Servicio> mapServicios = null;
		Integer proveedorID = null;
		PeticionEnvioXML peticionEnvio = null;
		try {
			dao.beginTransaction();

			SMSData smsData = dao.getSMSData(idMensaje);

			boolean sendOK = false;

			if (null != smsData.Body) {
				int indice = 0;
				int numServidores = smsData.Servers.size();
				// esto debe tener una lista..debe..debe..
				mapServicios = dao.findServicioByMessageId(idMensaje);
				while ((indice < numServidores) && !sendOK) {

					proveedorID = smsData.Servers.get(indice).getProveedorId();

					if (mapServicios.containsKey(proveedorID)) {
						Servicio s = mapServicios.get(proveedorID);
						if (logger.isDebugEnabled())
							logger.debug("Llamamos a Envio SMS");
						try {
							// tomamos el Header que corresponde con el
							// proveedor
							smsData.ServiceData.setHeaderSMS(s.getHeaderSMS());
							smsData.ServiceData.setPasswordServidor(s.getProveedorPassSMS());
							smsData.ServiceData.setUsuarioServidor(s.getProveedorUsuarioSMS());
							// PROCESO DE ENVIO DEL SMS A TRAVES DEL PROVEEDOR
							peticionEnvio = generateRequestXML(dao, smsData, idMensaje, s, indice, codOrganismo, usuarioAplicacion, passAplicacion, usernameMISIM, passwordMISIM);

							if (logger.isInfoEnabled())
								logger.info("Respuesta: " + peticionEnvio);
							if (null != peticionEnvio) {

								// ponemos el estado a enviando
								dao.setEstadoMensaje(idMensaje, ENVIANDO);
							}
							break;
						} catch (Exception e) {
							e.printStackTrace();
							String errorMessage = e.getMessage();
							if (logger.isInfoEnabled())
								logger.info("Excepcion :" + errorMessage + " (postSMS)");
							dao.setLogError("SendMailService.postSMS", 0, "SMS_ID: " + idMensaje + ". Error: (" + e.hashCode() + ") " + errorMessage);
						}
					}
					indice++;
				}

			}
			dao.endTransaction(true);
		} catch (Exception e) {
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
		return peticionEnvio;
	}

	private PeticionEnvioXML generateRequestXML(EnvioMensajesPremiumDAO dao, SMSData smsData, Integer idMensaje, Servicio s, int indice, String codigoOrganismo,
			String usuarioAplicacion, String passAplicacion, String usernameMISIM, String passwordMISIM) {
		PeticionEnvioXML envio = new PeticionEnvioXML();
		try {
			ParametrosProveedor pp = smsData.Servers.get(indice);
			Proveedor p = dao.findProveedor(pp.getProveedorId());
			if (p != null) {
				logger.debug("Recuperado proveedor: " + p.getServidorid());
			} else {
				logger.debug("No encontrado proveedor con id: " + pp.getProveedorId());
				return null;
			}

			// String bytesEncoded = Base64.encode(passAplicacion.getBytes());

			// generamo la peticion de envio
			envio.setUsuario(usernameMISIM);
			envio.setPassword(passwordMISIM);
			envio.setProveedor(p.getNombre());
			envio.setMensajeId(idMensaje.toString());
			envio.setProducto("SMS");
			envio.setUrlEndpoint(p.getUrlDestino());
			DatosEspecificos de = new DatosEspecificos();
			de.setSMS_DESTINATARIO(smsData.Telefono);
			de.setSMS_HEADER(smsData.ServiceData.getHeaderSMS());
			de.setSMS_ID(idMensaje.toString());
			de.setSMS_TEXTO(smsData.Body);
						
			String usu = dao.getUsuario(codigoOrganismo, pp.getProveedorId());
			String pass = dao.getPassword(codigoOrganismo, pp.getProveedorId());
			
			de.setSMS_USUARIO((null != usu) ? usu : smsData.ServiceData.getUsuarioServidor());
			de.setSMS_PASSWORD((null != pass) ? pass : smsData.ServiceData.getPasswordServidor());
			
			envio.setDatosEspecificos(de);

			logger.debug("peticion generada : " + envio);

			//indicamos el metodo de consulta
			dao.setMetodoConsulta(idMensaje,p.getMetodoConsulta());
			return envio;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return envio;
	}

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta codificarRespuesta(String codError, String detailsError, String statusError, String idExterno,
			Integer idMensaje) {

		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = new es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta();
		// es.minhap.plataformamensaferia.iop.beans.envioPremium.ResponseStatusType
		// rs = new
		// es.minhap.plataformamensaferia.iop.beans.envioPremium.ResponseStatusType();
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

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta peticionCorrectaSMS(String codOrganismoPagador, String cuerpo, String idExterno, String destinatario,
			String deliveryUrl) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = null;
		if (null == codOrganismoPagador || codOrganismoPagador.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_2012_GENERAL, PlataformaErrores.DESC_2012_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					idExterno, null);

		} else if (null == cuerpo || cuerpo.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_0007_GENERAL, PlataformaErrores.DESC_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO, idExterno,
					null);

		} else if (null == idExterno || idExterno.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_ERROR_ID_EXTERNO_AEAT, PlataformaErrores.DETAILS_ERROR_ID_EXTERNO_AEAT, PlataformaErrores.STATUSTEXT_KO, "", null);

		} else if (null == destinatario || destinatario.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_2002_GENERAL, PlataformaErrores.DESC_2002_GENERAL,
					PlataformaErrores.STATUSTEXT_KO, idExterno, null);

		} else if (null == deliveryUrl || deliveryUrl.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_0020_GENERAL, PlataformaErrores.DESC_0020_GENRAL, PlataformaErrores.STATUSTEXT_KO, idExterno, null);

		}
		return res;
	}

	private es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta peticionCorrectaSMSMultiOrganismo(String codOrganismoPagador, String cuerpo, String idExterno,
			String destinatario, String deliveryUrl, boolean organismoActivo) {
		es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta res = null;
		if (null == codOrganismoPagador || codOrganismoPagador.isEmpty() || !organismoActivo) {
			return codificarRespuesta(PlataformaErrores.COD_2012_GENERAL, PlataformaErrores.DESC_2012_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					idExterno, null);

		} else if (null == cuerpo || cuerpo.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_0007_GENERAL, PlataformaErrores.DESC_0007_GENERAL, PlataformaErrores.STATUSTEXT_KO, idExterno,
					null);

		} else if (null == idExterno || idExterno.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_ERROR_ID_EXTERNO_AEAT, PlataformaErrores.DETAILS_ERROR_ID_EXTERNO_AEAT, PlataformaErrores.STATUSTEXT_KO, "", null);

		} else if (null == destinatario || destinatario.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_2002_GENERAL, PlataformaErrores.DESC_2002_GENERAL,
					PlataformaErrores.STATUSTEXT_KO, idExterno, null);

		} else if (null == deliveryUrl || deliveryUrl.isEmpty()) {
			return codificarRespuesta(PlataformaErrores.COD_0020_GENERAL, PlataformaErrores.DESC_0020_GENRAL, PlataformaErrores.STATUSTEXT_KO, idExterno, null);

		}
		return res;
	}

}
