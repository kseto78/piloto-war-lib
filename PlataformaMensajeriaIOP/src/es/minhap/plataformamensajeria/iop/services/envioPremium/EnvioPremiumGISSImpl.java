package es.minhap.plataformamensajeria.iop.services.envioPremium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import es.minhap.plataformamensaferia.iop.beans.envioPremium.DatosEspecificos;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionEnvioXML;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Atributos;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Detail;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault;
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

public class EnvioPremiumGISSImpl implements IEnvioPremiumGISSService {
	static Logger logger = Logger.getLogger(IEnvioPremiumService.class);
	
	private static int GESTION_MULTIDESTINATARIOS = 1;
	private static String INCIDENCIA="INCIDENCIA";
	private static String PENDIENTE_OPERADORA ="PENDIENTE DE OPERADORA";
	private static String ESTADO_ENVIANDO ="ENVIANDO";
	private static String APLICACION_NOACTIVA =". La aplicacion no esta activa";
	private StringTokenizer tokenizer = null;

	
	

	@Override
	public String enviarSMSGISS(EnvioGISSXMLBean envio, String username,
			String password, Integer servicio, String usernameMISIM,
			String passwordMISIM) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		EnvioMensajesDAO daoLote = new EnvioMensajesDAO();
		MensajeDAO dm = new MensajeDAO();
		ServicioDAO serviciodao = new ServicioDAO();
		OrganismosDAO organismodao = new OrganismosDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		PeticionEnvioXML peticionEnvioXML = null;
		Integer idLote = null;
		Integer idMensaje = null;

		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp = null;
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault respFault = null;
		
		try {
			if (null!=envio.getUsuSistemaEnvio() && null!=envio.getPassSistemaEnvio() && envio.getUsuSistemaEnvio().equals(envio.getPassSistemaEnvio())  ){
				  tokenizer = new StringTokenizer(envio.getUsuSistemaEnvio(), "_");
				  String nif = tokenizer.nextToken();
				  envio.setCodOrganismoPagadorSMS(tokenizer.nextToken()); 
			}else{
				resp = codificarRespuesta(envio.getIdExterno(),
						PlataformaErrores.COD_10_GISS,
						PlataformaErrores.DESCRIPCION_COD_10_GISS,
						PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
				return resp.toXMLSMS(resp);
			}

			serviciodao.beginTransaction();
			organismodao.beginTransaction();

			boolean existeOrganismo = organismodao.existeOrganismo(envio
					.getCodOrganismoPagadorSMS());
			if (!serviciodao.isMultiOrganismo("" + servicio)) {
				resp = peticionCorrectaSMS(envio.getCodOrganismoPagadorSMS(),
						envio.getCuerpo(), envio.getIdExterno(),
						envio.getDestinatario(),	existeOrganismo);
			} else {

				boolean activo = organismodao.organismoActivoEnServicio(
						servicio, envio.getCodOrganismoPagadorSMS());

				resp = peticionCorrectaSMSMultiOrganismo(
						envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(),
						envio.getIdExterno(), envio.getDestinatario(), activo, existeOrganismo);
			}
			organismodao.endTransaction(false);
			if (resp != null) {
				return resp.toXMLSMS(resp);
			}
			dao.beginTransaction();

			// Comprobamos
			if (Utils.validarTelefono(envio.getDestinatario()) == 1) {
				resp = codificarRespuesta(envio.getIdExterno(),
						PlataformaErrores.COD_10_GISS,
						PlataformaErrores.DESCRIPCION_COD_10_GISS
								+ PlataformaErrores.ERROR_DESTINATARIO,
						PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
				return resp.toXMLSMS(resp);
			}
			
			//Comprobamos si la aplicaci�n est� activa
			aplicacionDao.beginTransaction();
			Integer activeApplication = aplicacionDao.checkActiveApplication(servicio);
			if (activeApplication.intValue() == 0) {
				resp = codificarRespuesta(envio.getIdExterno(),
						PlataformaErrores.COD_0008_GENERAL,
						PlataformaErrores.DESC_0008_GENERAL + APLICACION_NOACTIVA,
						PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
				return resp.toXMLSMS(resp);
			}
			aplicacionDao.endTransaction(false);

			// Tratamiento de reintentos
			idMensaje = checkMessageExists(dao, envio.getIdExterno());
			if (idMensaje == null) {
				// comprobamos idPeticionRepetido
				if (dao.idExternoRepetido(envio.getIdExterno())) {
					resp = codificarRespuesta(envio.getIdExterno(),
							PlataformaErrores.COD_20_GISS,
							PlataformaErrores.DESCRIPCION_COD_20_GISS,
							PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
					return resp.toXMLSMS(resp);
				}
				daoLote.beginTransaction();
				idLote = daoLote.crearLote(servicio.toString(), PlataformaErrores.NOMBRE_LOTE_GISS,
						username, password, GESTION_MULTIDESTINATARIOS);
				if (null == idLote || idLote <= 0) {
					if (idLote == -10){
						respFault = codificarRespuestaFault(envio.getIdExterno(),
								PlataformaErrores.COD_300_GISS,
								PlataformaErrores.DESCRIPCION_COD_300_GISS);
						return respFault.toXMLSMS(respFault);
					}else{//es error porque la aplicacion no activa o servicio no activo es resDerdak
						resp = codificarRespuesta(envio.getIdExterno(),
								PlataformaErrores.COD_0008_GENERAL,
								PlataformaErrores.DESC_0008_GENERAL + APLICACION_NOACTIVA,
								PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
						return resp.toXMLSMS(resp);
					}
				}

				///////////////////////////////////////////////////////////////////////////////////
				//////////////////////// ver si aquí se gestiona reintentos o no /////////////////
				
				// Crear Mensaje
				idMensaje = dao.crearMensajeGISS(idLote, envio.getCuerpo(), null,
						envio.getCodOrganismoPagadorSMS(), envio.getIdExterno(),
						envio.getDestinatario(), username, password);
				dao.endTransaction(true);
				daoLote.endTransaction(true);
				
			}
			if (null == idMensaje || idMensaje < 0) {
				respFault = codificarRespuestaFault(envio.getIdExterno(),
						PlataformaErrores.COD_300_GISS,
						PlataformaErrores.DESCRIPCION_COD_300_GISS);
				return respFault.toXMLSMS(respFault);
			} else  if (idMensaje == 0){
				// comprobamos idPeticionRepetido
				if (dao.idExternoRepetido(envio.getIdExterno())) {
					resp = codificarRespuesta(envio.getIdExterno(),
							PlataformaErrores.COD_2008_GENERAL,
							PlataformaErrores.DESC_2008_GENERAL,
							PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
					return resp.toXMLSMS(resp);
				}
			} else {
				logger.info("EnvioPremiumGISSImpl.enviarSMSGISS se va a reenviar el mensaje con id: " + idMensaje);
				peticionEnvioXML = reenviarSMS(envio, idMensaje, username,
						password, usernameMISIM, passwordMISIM);
				if (null != peticionEnvioXML) {
					resp = codificarRespuesta(envio.getIdExterno(),
							PlataformaErrores.STATUS_OK,
							PlataformaErrores.DETAILS_OK,
							PlataformaErrores.OK_RETURN_DERDACK_GISS);
				} else {
					resp = codificarRespuesta(envio.getIdExterno(),
							PlataformaErrores.COD_0999_GENERAL,
							PlataformaErrores.DESC_0999_GENERAL,
							PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
				}
			}

			dao.beginTransaction();
			dm.beginTransaction();
			String aux = resp.getEstado().getLiteralError();
			String peticion = "";
			if (null != peticionEnvioXML) {
				peticion = "<![CDATA[" + peticionEnvioXML.toXMLSMS() + "]]>";
			}

			aux = aux + " | " + peticion;

			resp.getEstado().setLiteralError(aux);
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
			aplicacionDao.connClose();
			aplicacionDao.closeAll();
		}
		return "";
	}

	
	@Override
	public String cambiarEstadoSMSPremium(Integer idMensaje, String statusText) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta();
		String idExterno = "";
		String respuesta = null;
		try {	
			dao.beginTransaction();
			idExterno = dao.getIdExterno(idMensaje);
			if (statusText.contains("OK")) {
				dao.setEstadoMensajeEnviado(idMensaje, PENDIENTE_OPERADORA, statusText);
				resp = codificarRespuesta(idExterno,PlataformaErrores.STATUS_OK,
						PlataformaErrores.DETAILS_OK,
						PlataformaErrores.OK_RETURN_DERDACK_GISS);
			}else{
				resp = codificarRespuesta(idExterno,PlataformaErrores.COD_0999_GENERAL,
						PlataformaErrores.DESC_0999_GENERAL,
						PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
				dao.setEstadoMensajeEnviado(idMensaje, INCIDENCIA, statusText);
				
			}
			dao.endTransaction(true);
			respuesta = resp.toXMLSMS(resp);
		} catch (Exception e) {
			resp = codificarRespuesta(idExterno, PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_ERROR_GENERAL , PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
		return respuesta;
	}
	

	
	private PeticionEnvioXML reenviarSMS(
			EnvioGISSXMLBean envio, Integer idMensaje, String usuarioAplicacion, String passAplicacion,String usernameMISIM,String passwordMISIM) {
		return postSMS(idMensaje,envio.getCodOrganismoPagadorSMS(), usuarioAplicacion, passAplicacion, usernameMISIM, passwordMISIM);
	}
	
	private PeticionEnvioXML postSMS(Integer idMensaje, String codOrganismo, String usuarioAplicacion, String passAplicacion, String usernameMISIM, String passwordMISIM) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		Map<Integer, Servicio> mapServicios = null;
		Integer proveedorID = null;
		PeticionEnvioXML peticionEnvio = null;
		try{
			dao.beginTransaction();
			SMSData smsData = dao.getSMSData(idMensaje);
			
			boolean sendOK = false;
						
			if (null != smsData.Body) {
				int indice = 0;
				int numServidores = smsData.Servers.size();
				// esto debe tener una lista..debe..debe..
				mapServicios = dao.findServicioByMessageId(idMensaje);
				while ((indice < numServidores) && !sendOK) {

					proveedorID = smsData.Servers.get(indice)
							.getProveedorId();

					if (mapServicios.containsKey(proveedorID)) {
						Servicio s = mapServicios.get(proveedorID);
						if (logger.isDebugEnabled())
							logger.debug("Llamamos a Envio SMS");
						try {
							// tomamos el Header que corresponde con
							// el proveedor
							smsData.ServiceData.setHeaderSMS(s
									.getHeaderSMS());
							// PROCESO DE ENVIO DEL SMS A TRAVES DEL
							// PROVEEDOR
							peticionEnvio = generateRequestXML(dao, smsData, idMensaje,
									s, indice, codOrganismo, usuarioAplicacion, passAplicacion, usernameMISIM, passwordMISIM);
							 
							if (logger.isInfoEnabled())
								logger.info("Respuesta: " + peticionEnvio);
							if(null != peticionEnvio) {

								//ponemos el estado a enviando
								dao.setEstadoMensaje(idMensaje, ESTADO_ENVIANDO);
								
								sendOK = true;
							}
						} catch (Exception e) {
							e.printStackTrace();
							String errorMessage = e.getMessage();
							if (logger.isInfoEnabled())
								logger.info("Excepcion :"+ errorMessage	+ " (postSMS)");
							dao.setLogError("SendMailService.postSMS",0,"SMS_ID: " + idMensaje+ ". Error: ("
											+ e.hashCode() + ") "+ errorMessage);
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
	
	private PeticionEnvioXML generateRequestXML(EnvioMensajesPremiumDAO dao, SMSData smsData, Integer idMensaje, Servicio s,
			int indice, String codigoOrganismo, String usuarioAplicacion, String passAplicacion, String usernameMISIM, String passwordMISIM) {
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
			
			String usuario= dao.getUsuario(codigoOrganismo, pp.getProveedorId());
			String pass= dao.getPassword(codigoOrganismo, pp.getProveedorId());
			
			de.setSMS_USUARIO((null != usuario) ? usuario : smsData.ServiceData.getUsuarioServidor());
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
	

	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta codificarRespuesta(
			String idExterno, String codigoError, String descripcion, String estadoDerdack) {
		
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta();
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Estado estado = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Estado();
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.TransmisionDerdack derdack = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.TransmisionDerdack();
		
		estado.setCodigoEstado(codigoError);
		estado.setDescripcionError(descripcion);
		estado.setLiteralError(descripcion);
		
		derdack.setReturn(estadoDerdack);
		if (estadoDerdack.equals("-1"))
			derdack.setNIndex("Error");
		else
			derdack.setNIndex("Correcto");
		derdack.setStrErrorDescription(descripcion);
		
		res.setIdPeticion(idExterno);
		res.setEstado(estado);
		res.setTransmisionDerdack(derdack);
		
		
		return res;
	}


	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta peticionCorrectaSMS(String codOrganismoPagador, String cuerpo,
			String idExterno, String destinatario, boolean existeOrganismo) {
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = null;
		if ((null == codOrganismoPagador || codOrganismoPagador.isEmpty()) ||
				(null == cuerpo || cuerpo.isEmpty()) || (null == idExterno || idExterno.isEmpty()) ||
				(null == destinatario || destinatario.isEmpty()) ){
			return codificarRespuesta(idExterno, PlataformaErrores.COD_10_GISS, 
					PlataformaErrores.DESCRIPCION_COD_10_GISS,PlataformaErrores.ERROR_RETURN_DERDACK_GISS ); // error 10
		}else if (!existeOrganismo){
			return codificarRespuesta(idExterno, PlataformaErrores.COD_0031_GENERAL, 
					PlataformaErrores.DESC_0031_GENERAL, PlataformaErrores.ERROR_RETURN_DERDACK_GISS);
		}
		return res;
	}
	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta peticionCorrectaSMSMultiOrganismo(String codOrganismoPagador, String cuerpo,
			String idExterno, String destinatario, boolean organismoActivo, boolean existeOrganismo) {
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = null;
		if ((null == codOrganismoPagador || codOrganismoPagador.isEmpty()) ||
				(null == cuerpo || cuerpo.isEmpty()) || (null == idExterno || idExterno.isEmpty()) ||
				(null == destinatario || destinatario.isEmpty()) ){
			return codificarRespuesta(idExterno, PlataformaErrores.COD_10_GISS,
					PlataformaErrores.DESCRIPCION_COD_10_GISS, PlataformaErrores.ERROR_RETURN_DERDACK_GISS );
		}else if (!existeOrganismo){
			return codificarRespuesta(idExterno, PlataformaErrores.COD_40_GISS, 
					PlataformaErrores.DESCRIPCION_COD_40_GISS, PlataformaErrores.ERROR_RETURN_DERDACK_GISS); // no existe el organismo id estado 40
		}else if(!organismoActivo){
			return codificarRespuesta(idExterno, PlataformaErrores.COD_50_GISS, 
					PlataformaErrores.DESCRIPCION_COD_50_GISS, PlataformaErrores.ERROR_RETURN_DERDACK_GISS); // error 50
		}
		return res;
	}
	
	
	private Fault codificarRespuestaFault(String idExterno,
			String codError, String descripcionError) {
		Fault res = new Fault();
		Detail detail = new Detail();
		Atributos atributos = new Atributos();
		
		atributos.setCodigoError(codError);
		atributos.setDescripcionError(descripcionError);
		atributos.setIdPeticion(idExterno);
		atributos.setLiteralError("");
		
		detail.setAtributos(atributos);
		
		res.setDetail(detail);
		res.setFaultcode(codError);
		res.setFaultstring(descripcionError);
				
		return res;
	}
	
	
	@Override
	public List<EnvioGISSXMLBean> reenviarSMSGISS(String username,
			String password, Integer servicio, Integer reintentos, String usernameMISIM,
			String passwordMISIM) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		List<EnvioGISSXMLBean> reenvios= new ArrayList<EnvioGISSXMLBean>();
		try {
			dao.beginTransaction();
			reenvios= dao.obtenerMensajesReenvioGISS(servicio, reintentos);
			dao.endTransaction(false);
		} catch (Exception e) {
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
		return reenvios;
	}
	
	@Override
	public void anularSMS(Integer servicio, Integer reintentos) {
		EnvioMensajesPremiumDAO dao = new EnvioMensajesPremiumDAO();
		try {
			logger.debug("EnvioPremiumGISSImpl.anularSMS. Se van a anular los mensajes que han sobrepasado el numero de reintentos:" + reintentos);
			dao.beginTransaction();
			dao.anularMensajesCaducadosGISS(servicio, reintentos, PlataformaErrores.NUMERO_REINTENTOS_SUPERADO + reintentos);
			dao.endTransaction(true);
			logger.debug("EnvioPremiumGISSImpl.anularSMS. Se han anulado los mensajes que han sobrepasado el numero de reintos ");
		} catch (Exception e) {
			dao.endTransaction(false);
		} finally {
			dao.connClose();
			dao.closeAll();
		}
	}
	
	private Integer checkMessageExists(EnvioMensajesPremiumDAO dao, String idExterno) {
		return dao.checkIdExternoExists(idExterno, true);
	}
}
