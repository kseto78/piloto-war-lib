package es.minhap.plataformamensajeria.iop.services.envioPremium;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Atributos;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Detail;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta;
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
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblServicios;

/**
 * Esta clase es la encargada de gestionar los SMS de GISS
 * 
 * @author everis
 * 
 */
@Service("envioPremiumGISSServiceImpl")
public class EnvioPremiumGISSImpl implements IEnvioPremiumGISSService {
	protected static final String R_CONST_1 = "[ERROR-CL@VE]: El organismo: ";

	protected static final String R_CONST_2 = "constantes.ESTADO_ANULADO";

	protected static final String R_CONST_3 = "plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_10_GISS";

	protected static final String R_CONST_4 = "plataformaErrores.envioPremiumGISS.COD_10_GISS";

	protected static final String R_CONST_5 = "S";

	protected static final String R_CONST_6 = "plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_300_GISS";

	protected static final String R_CONST_7 = "plataformaErrores.envioPremiumGISS.COD_300_GISS";

	protected static final String R_CONST_8 = "plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS";

	protected static final String R_CONST_9 = "giss.usuario.sms";

	protected static final String R_CONST_10 = "Se ha producido un error";

	static Logger LOG = LoggerFactory.getLogger(IEnvioPremiumService.class);

	private static final String SEPARATOR = "_";

	private StringTokenizer tokenizer = null;

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
	private TblLotesEnviosManager lotesManager;

	@Resource
	TblEstadosManager estadosManager;

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
	
	@Autowired
	private ErroresManager erroresManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	/**
	 * 
	 * Metodo que se encarga de enviar un SMS a partir de una peticion
	 */
	@Override
	public String enviarSMSGISS(EnvioGISSXMLBean envio, String username, String password, Integer servicio,
			String usernameMISIM, String passwordMISIM) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
		String estadoAnulado = ps.getMessage(R_CONST_2, null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		String utilizarActiveMq = ps.getMessage("constantes.ENVIO_ACTIVEMQ", null,R_CONST_5);
		Integer idLote = null;
		Integer idMensaje = null;
		Long estadoId;
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp = null;
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault respFault = null;
		TblServicios serv = null;
		
		int activeMQ = 2;
		
		try {
			resp = comprobacionesIniciales(envio, servicio, ps);
			

			if (resp != null) {
				return resp.toXMLSMS(resp);
			}
	
			// comprobamos idExterno repetido
			if (destinatariosMensajesManager.checkIdExternoExists(envio.getIdExterno()) > 0) {
				resp = codificarRespuesta(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_20_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_20_GISS", null),
						ps.getMessage(R_CONST_8, null));
				return resp.toXMLSMS(resp);
			} else {
				serv = serviciosManager.getServicio(servicio.longValue());
				
				idLote = lotesManager.insertarLotePremium(serv, PlataformaErrores.NOMBRE_LOTE_GISS,
						username, password);

				if (idLote == -10) {
					respFault = codificarRespuestaFault(envio.getIdExterno(),
							ps.getMessage(R_CONST_7, null),
							ps.getMessage(R_CONST_6, null));
					return respFault.toXMLSMS(respFault);
				}

				// Crear Mensaje
				idMensaje = mensajesManager.insertarMensajeGISS(idLote, envio.getCuerpo(), null,
						envio.getCodOrganismoPagadorSMS(), envio.getIdExterno(), envio.getDestinatario(), username,
						password);
			}
			
			//si se ha insertado el mensaje encolamos sino devolvemos error
			if (null == idMensaje || idMensaje < 0) {
				respFault = codificarRespuestaFault(envio.getIdExterno(),
						ps.getMessage(R_CONST_7, null),
						ps.getMessage(R_CONST_6, null));
				return respFault.toXMLSMS(respFault);
			} else {
				LOG.info("EnvioPremiumGISSImpl.enviarSMSGISS se va a enviar el mensaje con id: " + idMensaje);

				estadoId = Long.parseLong(ps.getMessage("constantes.ID_ESTADO_PENDIENTE", null,"3"));

				for (TblDestinatariosMensajes destinatario : destinatariosMensajesManager
						.getDestinatarioMensajes(idMensaje.longValue())) {
					
					hitoricosManager.creaHistoricoPremium(idMensaje.longValue(), destinatario.getDestinatariosmensajes(),
							estadoId, username);
					if (R_CONST_5.equals(utilizarActiveMq)){
							encolarMensaje(envio, username, ps, idLote, idMensaje, serv, destinatario);
					}
				}
				//Comprobamos que si ya se ha actualizado la tabla de errores a true
				activeMQ = 1;//true
				resp = codificarRespuesta(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.generales.STATUS_OK", null),
						ps.getMessage("plataformaErrores.generales.DETAILS_OK", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.OK_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);

			}

		} catch (CannotCreateTransactionException e) {
			//Comprobamos que si ya se ha actualizado la tabla de errores a false
			activeMQ = 0;//false
			LOG.error(errorActiveMq+" EnvioPremiumGISSImpl.enviarSMSGISS --Error ActiveMq--", e);
			
			mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoAnulado, descripcionErrorActiveMq, false,
					null, null, username, null);
			respFault = codificarRespuestaFault(envio.getIdExterno(),
					ps.getMessage(R_CONST_7, null),
					ps.getMessage(R_CONST_6, null));
			try {
				return respFault.toXMLSMS(respFault);
			} catch (PlataformaBusinessException e1) {
				LOG.error("EnvioPremiumGISSImpl.enviarSMSGISS --Error ActiveMq--", e1);
			}
		} catch (Exception e) {
			LOG.error(R_CONST_10, e);
			respFault = codificarRespuestaFault(envio.getIdExterno(),
					ps.getMessage(R_CONST_7, null),
					ps.getMessage(R_CONST_6, null));
			try {
				return respFault.toXMLSMS(respFault);
			} catch (PlataformaBusinessException e1) {
				LOG.error(R_CONST_10, e1);
			}
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug("Estamos en EnvioPremiumGISSImpl-enviarSMSGISS");					
			switch (activeMQ) {
			case 0:
				erroresManager.comprobarActiveMqActivo(false);
				break;
			case 1:
				erroresManager.comprobarActiveMqActivo(true);
				break;
			}
		}
		return "";
	}

	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta comprobacionesIniciales(
			EnvioGISSXMLBean envio, Integer servicio, PropertiesServices ps) throws PlataformaBusinessException {
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp;
		
		///obtenemos el codOrganismo de la peticion si no existe o peticion incompleta return error
		resp = comprobacionesYObtenerCodOrganismo(envio, ps);
		
		if (null == resp) {
			//comprobamos el organismo, organismo-servicio y aplicacion
			resp = comprobarOrgSerApl(servicio, envio, ps);
		}
		
		
		return resp;
	}

	private void encolarMensaje(EnvioGISSXMLBean envio, String username, PropertiesServices ps, Integer idLote,
			Integer idMensaje, TblServicios serv, TblDestinatariosMensajes destinatario) {
		MensajeJMS mensajeJms = new MensajeJMS();
		mensajeJms.setIdMensaje(idMensaje.toString());
//		mensajeJms.setIdExterno(envio.getIdExterno());
		mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
		mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
		mensajeJms.setIdLote(idLote.toString());
		mensajeJms.setUsuarioAplicacion(username);
		
		if(serv.getCaducidad() !=null && serv.getCaducidad() > 0){
			mensajeJms.setCaducidad(serv.getCaducidad().toString());
		}
		
		Long maxRetries;
		
		if (serv.getNumeroMaxReenvios() != null && serv.getNumeroMaxReenvios() >= 0) {
			maxRetries = serv.getNumeroMaxReenvios().longValue();
		} else {
			maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
		}
		sender.send(mensajeJms, maxRetries, serv.getServicioid().toString(), true);
	}

	private Respuesta comprobarOrgSerApl(Integer servicio, EnvioGISSXMLBean envio, PropertiesServices ps) {
		Respuesta resp = null;
		boolean existeOrganismo = organismosManager.existeOrganismo(envio.getCodOrganismoPagadorSMS());

		boolean activo = queryExecutorOrganismos.organismoActivoEnServicio(servicio, envio.getCodOrganismoPagadorSMS());
		resp = peticionCorrectaSMSMultiOrganismo(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(),
				envio.getIdExterno(), envio.getDestinatario(), activo, existeOrganismo);

		if (null != resp) {
			return resp;
		}
		
		// Comprobamos si la aplicacion esta activa
		Integer activeApplication = queryExecutorOrganismos.checkActiveApplication(servicio);
		if (activeApplication.intValue() == 0) {
			resp = codificarRespuesta(
					envio.getIdExterno(),
					ps.getMessage("plataformaErrores.envioPremiumGISS.COD_0008_GENERAL", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESC_0008_GENERAL", null)
							+ ps.getMessage("plataformaErrores.envioPremiumGISS.APLICACION_NOACTIVA", null),
					ps.getMessage(R_CONST_8, null));
			
		}
		return resp;

	}

	private Respuesta comprobacionesYObtenerCodOrganismo(EnvioGISSXMLBean envio, PropertiesServices ps) {
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");
		Respuesta resp;
		
		//obtenemos el codOrganismo
		if (null != envio.getUsuSistemaEnvio() && null != envio.getPassSistemaEnvio()
				&& envio.getUsuSistemaEnvio().equals(envio.getPassSistemaEnvio())) {
			tokenizer = new StringTokenizer(envio.getUsuSistemaEnvio(), SEPARATOR);
			tokenizer.nextToken();
			envio.setCodOrganismoPagadorSMS(tokenizer.nextToken());
		} else {
			return codificarRespuesta(envio.getIdExterno(),
					ps.getMessage(R_CONST_4, null),
					ps.getMessage(R_CONST_3, null),
					ps.getMessage(R_CONST_8, null));
		}
		
		// Comprobamos telefono
		if (Utils.validarTelefono(envio.getDestinatario(), telefonoExcepcion) == 1) {
			return codificarRespuesta(
					envio.getIdExterno(),
					ps.getMessage(R_CONST_4, null),
					ps.getMessage(R_CONST_3, null)
							+ ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_DESTINATARIO", null),
					ps.getMessage(R_CONST_8, null));
		}
		return null;
	}

	@Override
	public String cambiarEstadoSMSPremium(Integer idMensaje, String statusText) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta();
		String idExterno = "";
		String respuesta = null;
		try {
			idExterno = destinatariosMensajesManager.getIdExterno(idMensaje.longValue());
			if (statusText.contains("OK")) {
				mensajesManager.setEstadoMensaje(idMensaje.longValue(),
						ps.getMessage("constantes.ESTADO_PENDIENTE_OPERADORA", null), statusText, false, null, null,
						ps.getMessage(R_CONST_9, null, null, null), null);
				resp = codificarRespuesta(idExterno, PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK,
						PlataformaErrores.OK_RETURN_DERDACK_GISS);
			} else {
				resp = codificarRespuesta(idExterno, PlataformaErrores.COD_0999_GENERAL,
						PlataformaErrores.DESC_0999_GENERAL,
						ps.getMessage(R_CONST_8, null));
				mensajesManager.setEstadoMensaje(idMensaje.longValue(),
						ps.getMessage("constantes.ESTADO_INCIDENCIA", null), statusText, false, null, null,
						ps.getMessage(R_CONST_9, null, null, null), null);

			}
			respuesta = resp.toXMLSMS(resp);
		} catch (Exception e) {
			LOG.error(R_CONST_10, e);
			resp = codificarRespuesta(idExterno, PlataformaErrores.COD_ERROR_GENERAL,
					PlataformaErrores.DETAILS_ERROR_GENERAL,
					ps.getMessage(R_CONST_8, null));
			try {
				respuesta = resp.toXMLSMS(resp);
			} catch (PlataformaBusinessException pe) {
				LOG.error(R_CONST_10, pe);
			}
		}
		return respuesta;
	}

	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta codificarRespuesta(String idExterno,
			String codigoError, String descripcion, String estadoDerdack) {

		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta();
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Estado estado = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.Estado();
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.TransmisionDerdack derdack = new es.minhap.plataformamensajeria.iop.beans.enviosGISS.TransmisionDerdack();

		estado.setCodigoEstado(codigoError);
		estado.setDescripcionError(descripcion);
		estado.setLiteralError(descripcion);

		derdack.setReturn(estadoDerdack);
		if ("-1".equals(estadoDerdack)) {
			derdack.setNIndex("Error");
		} else {
			derdack.setNIndex("Correcto");
		}
		derdack.setStrErrorDescription(descripcion);

		res.setIdPeticion(idExterno);
		res.setEstado(estado);
		res.setTransmisionDerdack(derdack);

		return res;
	}


	private boolean checkOrganismoPagador(String codOrganismoPagador) {
		return null == codOrganismoPagador || codOrganismoPagador.isEmpty();
	}

	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta peticionCorrectaSMSMultiOrganismo(
			String codOrganismoPagador, String cuerpo, String idExterno, String destinatario, boolean organismoActivo,
			boolean existeOrganismo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = null;
		if (checkOrganismoPagador(codOrganismoPagador) || checkOrganismoPagador(cuerpo)
				|| checkOrganismoPagador(idExterno) || checkOrganismoPagador(destinatario)) {
			return codificarRespuesta(idExterno, ps.getMessage(R_CONST_4, null),
					ps.getMessage(R_CONST_3, null),
					ps.getMessage(R_CONST_8, null));
		} else if (!existeOrganismo) {
			//cambiado al stream ERROR-CLAVE para que aparezca el error en el Graylog
//			LOG.error("ERROR-GISS: El organismo: " + codOrganismoPagador + " no existe");
			LOG.error(R_CONST_1 + codOrganismoPagador + " no existe");
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_40_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_40_GISS", null),
					ps.getMessage(R_CONST_8, null)); // no
																											// existe
																											// el
																											// organismo
																											// id
																											// estado
																											// 40
		} else if (!organismoActivo) {
			//cambiado al stream ERROR-CLAVE para que aparezca el error en el Graylog
//			LOG.error("ERROR-GISS: El organismo: " + codOrganismoPagador + " no esta activo en el servicio de GISS");
			LOG.error(R_CONST_1 + codOrganismoPagador + " no esta activo en el servicio de GISS");
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_50_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_50_GISS", null),
					ps.getMessage(R_CONST_8, null)); // error
																											// 50
		}
		return res;
	}

	private Fault codificarRespuestaFault(String idExterno, String codError, String descripcionError) {
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
	public List<EnvioGISSXMLBean> reenviarSMSGISS(Long servicio, Integer reintentosDefault) {
		List<EnvioGISSXMLBean> reenvios = new ArrayList<>();
		try {
			Integer reintentos = null != serviciosManager.getServicio(servicio).getNumeroMaxReenvios() ? serviciosManager.getServicio(servicio).getNumeroMaxReenvios() : reintentosDefault ;
			reenvios = queryExecutorDestinatariosMensajes.obtenerMensajesReenvioGISS(servicio, reintentos);
		} catch (Exception e) {
			LOG.error(R_CONST_10, e);
		}
		return reenvios;
	}

	@Override
	public void anularSMS(Long servicio, Integer reintentosDefault) {
		try {
			Integer reintentos = null != serviciosManager.getServicio(servicio).getNumeroMaxReenvios() ? serviciosManager.getServicio(servicio).getNumeroMaxReenvios() : reintentosDefault ;
			LOG.debug("EnvioPremiumGISSImpl.anularSMS. Se van a anular los mensajes que han sobrepasado el numero de reintentos:"
					+ reintentos);
			anularMensajesCaducadosGISS(servicio, reintentos, PlataformaErrores.NUMERO_REINTENTOS_SUPERADO + reintentos);
			LOG.debug("EnvioPremiumGISSImpl.anularSMS. Se han anulado los mensajes que han sobrepasado el numero de reintos ");
		} catch (Exception e) {
			LOG.error(R_CONST_10, e);
		}
	}

	private void anularMensajesCaducadosGISS(Long servicio, Integer reintentos, String descripcion) {
				List<Long> mensajes = queryExecutorMensajes.getMensajesParaAnular(servicio, reintentos);
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		for (Long idMensaje : mensajes) {
			mensajesManager.setEstadoMensaje(idMensaje, ps.getMessage(R_CONST_2, null), descripcion,
					false, null, null, ps.getMessage(R_CONST_9, null, null, null), null);
		}
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
