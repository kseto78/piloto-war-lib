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

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Atributos;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Detail;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault;
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
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.TblDestinatariosMensajes;

/**
 * Esta clase es la encargada de gestionar los SMS de GISS
 * 
 * @author everis
 * 
 */
@Service("envioPremiumGISSServiceImpl")
public class EnvioPremiumGISSImpl implements IEnvioPremiumGISSService {
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

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	
	/**
	 * 
	 * Metodo que se encarga de enviar un SMS a partir de una peticion
	 */
	@Override
	public String enviarSMSGISS(EnvioGISSXMLBean envio, String username, String password, Integer servicio,
			String usernameMISIM, String passwordMISIM) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		Integer idLote = null;
		Integer idMensaje = null;
		Long estadoId;
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta resp = null;
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault respFault = null;

		try {
			if (null != envio.getUsuSistemaEnvio() && null != envio.getPassSistemaEnvio()
					&& envio.getUsuSistemaEnvio().equals(envio.getPassSistemaEnvio())) {
				tokenizer = new StringTokenizer(envio.getUsuSistemaEnvio(), SEPARATOR);
				tokenizer.nextToken();
				envio.setCodOrganismoPagadorSMS(tokenizer.nextToken());
			} else {
				resp = codificarRespuesta(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_10_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_10_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);
			}
			boolean existeOrganismo = organismosManager.existeOrganismo(envio.getCodOrganismoPagadorSMS());
			if (!serviciosManager.isMultiOrganismo(servicio)) {
				resp = peticionCorrectaSMS(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(), envio.getIdExterno(),
						envio.getDestinatario(), existeOrganismo);
			} else {

				boolean activo = queryExecutorOrganismos.organismoActivoEnServicio(servicio,
						envio.getCodOrganismoPagadorSMS());
				resp = peticionCorrectaSMSMultiOrganismo(envio.getCodOrganismoPagadorSMS(), envio.getCuerpo(),
						envio.getIdExterno(), envio.getDestinatario(), activo, existeOrganismo);
			}
			if (resp != null) {
				return resp.toXMLSMS(resp);
			}

			// Comprobamos
			if (Utils.validarTelefono(envio.getDestinatario(), telefonoExcepcion) == 1) {
				resp = codificarRespuesta(
						envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_10_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_10_GISS", null)
								+ ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_DESTINATARIO", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);
			}

			// Comprobamos si la aplicacion esta activa
			Integer activeApplication = queryExecutorOrganismos.checkActiveApplication(servicio);
			if (activeApplication.intValue() == 0) {
				resp = codificarRespuesta(
						envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_0008_GENERAL", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESC_0008_GENERAL", null)
								+ ps.getMessage("plataformaErrores.envioPremiumGISS.APLICACION_NOACTIVA", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);
			}

			// comprobamos idPeticionRepetido
			if (destinatariosMensajesManager.checkIdExternoExists(envio.getIdExterno()) > 0) {
				resp = codificarRespuesta(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_20_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_20_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);
			} else {
				idLote = lotesManager.insertarLote(servicio.longValue(), PlataformaErrores.NOMBRE_LOTE_GISS, username,
						password);
				if (null == idLote || idLote <= 0) {
					if (idLote == -10) {
						respFault = codificarRespuestaFault(envio.getIdExterno(),
								ps.getMessage("plataformaErrores.envioPremiumGISS.COD_300_GISS", null),
								ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_300_GISS", null));
						return respFault.toXMLSMS(respFault);
					} else {// es error porque la aplicacion no activa o
							// servicio no activo es resDerdak
						resp = codificarRespuesta(
								envio.getIdExterno(),
								ps.getMessage("plataformaErrores.envioPremiumGISS.COD_0008_GENERAL", null),
								ps.getMessage("plataformaErrores.envioPremiumGISS.DESC_0008_GENERAL", null)
										+ ps.getMessage("plataformaErrores.envioPremiumGISS.APLICACION_NOACTIVA", null),
								ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
						return resp.toXMLSMS(resp);
					}
				}

				// Crear Mensaje
				idMensaje = mensajesManager.insertarMensajeGISS(idLote, envio.getCuerpo(), null,
						envio.getCodOrganismoPagadorSMS(), envio.getIdExterno(), envio.getDestinatario(), username,
						password);
			}
			if (null == idMensaje || idMensaje < 0) {
				respFault = codificarRespuestaFault(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.envioPremiumGISS.COD_300_GISS", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_300_GISS", null));
				return respFault.toXMLSMS(respFault);
			} else {
				LOG.info("EnvioPremiumGISSImpl.enviarSMSGISS se va a enviar el mensaje con id: " + idMensaje);

				estadoId = estadosManager.getEstadoByName(
						mensajesManager.getMensaje(idMensaje.longValue()).getEstadoactual()).getEstadoid();

				for (TblDestinatariosMensajes destinatario : destinatariosMensajesManager
						.getDestinatarioMensajes(idMensaje.longValue())) {
					hitoricosManager.creaHistorico(idMensaje.longValue(), destinatario.getDestinatariosmensajes(),
							estadoId, null, null, null, username);
//					MensajeJMS mensajeJms = new MensajeJMS();
//					mensajeJms.setIdMensaje(idMensaje.toString());
//					mensajeJms.setIdExterno(envio.getIdExterno());
//					mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_SMS", null));
//					mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
//					Long maxRetries = null;
//
//					TblServicios servicioAEAT = serviciosManager.getServicio(Long.parseLong(servicio.toString()));
//					if (servicioAEAT.getNumeroMaxReenvios() != null && servicioAEAT.getNumeroMaxReenvios() > 0) {
//						maxRetries = servicioAEAT.getNumeroMaxReenvios().longValue();
//					} else {
//						maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
//					}
//					sender.send(mensajeJms, maxRetries, servicioAEAT.getServicioid().toString(), true);
				}
				resp = codificarRespuesta(envio.getIdExterno(),
						ps.getMessage("plataformaErrores.generales.STATUS_OK", null),
						ps.getMessage("plataformaErrores.generales.DETAILS_OK", null),
						ps.getMessage("plataformaErrores.envioPremiumGISS.OK_RETURN_DERDACK_GISS", null));
				return resp.toXMLSMS(resp);

			}

		} catch (CannotCreateTransactionException e) {
			LOG.error("EnvioPremiumGISSImpl.enviarSMSGISS --Error ActiveMq--", e);
			mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoAnulado, descripcionErrorActiveMq, false,
					null, null, username, null);
			respFault = codificarRespuestaFault(envio.getIdExterno(),
					ps.getMessage("plataformaErrores.envioPremiumGISS.COD_300_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_300_GISS", null));
			try {
				return respFault.toXMLSMS(respFault);
			} catch (PlataformaBusinessException e1) {
				LOG.error("EnvioPremiumGISSImpl.enviarSMSGISS --Error ActiveMq--", e);
			}
		} catch (Exception e) {
			LOG.error("Se ha producido un error", e);
			respFault = codificarRespuestaFault(envio.getIdExterno(),
					ps.getMessage("plataformaErrores.envioPremiumGISS.COD_300_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_300_GISS", null));
			try {
				return respFault.toXMLSMS(respFault);
			} catch (PlataformaBusinessException e1) {
				LOG.error("Se ha producido un error", e);
			}
		}
		return "";
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
						ps.getMessage("giss.usuario.sms", null, null, null), null);
				resp = codificarRespuesta(idExterno, PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK,
						PlataformaErrores.OK_RETURN_DERDACK_GISS);
			} else {
				resp = codificarRespuesta(idExterno, PlataformaErrores.COD_0999_GENERAL,
						PlataformaErrores.DESC_0999_GENERAL,
						ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
				mensajesManager.setEstadoMensaje(idMensaje.longValue(),
						ps.getMessage("constantes.ESTADO_INCIDENCIA", null), statusText, false, null, null,
						ps.getMessage("giss.usuario.sms", null, null, null), null);

			}
			respuesta = resp.toXMLSMS(resp);
		} catch (Exception e) {
			LOG.error("Se ha producido un error", e);
			resp = codificarRespuesta(idExterno, PlataformaErrores.COD_ERROR_GENERAL,
					PlataformaErrores.DETAILS_ERROR_GENERAL,
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
			try {
				respuesta = resp.toXMLSMS(resp);
			} catch (PlataformaBusinessException pe) {
				LOG.error("Se ha producido un error", pe);
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

	/**
	 * Se comprueba si los parametros obligatorios vienen informados
	 * 
	 * @param codOrganismoPagador
	 * @param cuerpo
	 * @param idExterno
	 * @param destinatario
	 * @param existeOrganismo
	 * @return
	 */
	private es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta peticionCorrectaSMS(
			String codOrganismoPagador, String cuerpo, String idExterno, String destinatario, boolean existeOrganismo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		es.minhap.plataformamensajeria.iop.beans.enviosGISS.Respuesta res = null;
		if (checkOrganismoPagador(codOrganismoPagador) || checkOrganismoPagador(cuerpo)
				|| checkOrganismoPagador(idExterno) || checkOrganismoPagador(destinatario)) {
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_10_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_10_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null)); // error
																											// 10
		} else if (!existeOrganismo) {
			return codificarRespuesta(idExterno,
					ps.getMessage("plataformaErrores.envioPremiumGISS.COD_0031_GENERAL", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESC_0031_GENERAL", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
		}
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
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_10_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_10_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null));
		} else if (!existeOrganismo) {
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_40_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_40_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null)); // no
																											// existe
																											// el
																											// organismo
																											// id
																											// estado
																											// 40
		} else if (!organismoActivo) {
			return codificarRespuesta(idExterno, ps.getMessage("plataformaErrores.envioPremiumGISS.COD_50_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.DESCRIPCION_COD_50_GISS", null),
					ps.getMessage("plataformaErrores.envioPremiumGISS.ERROR_RETURN_DERDACK_GISS", null)); // error
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
			LOG.error("Se ha producido un error", e);
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
			LOG.error("Se ha producido un error", e);
		}
	}

	private void anularMensajesCaducadosGISS(Long servicio, Integer reintentos, String descripcion) {
				List<Long> mensajes = queryExecutorMensajes.getMensajesParaAnular(servicio, reintentos);
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		for (Long idMensaje : mensajes) {
			mensajesManager.setEstadoMensaje(idMensaje, ps.getMessage("constantes.ESTADO_ANULADO", null), descripcion,
					false, null, null, ps.getMessage("giss.usuario.sms", null, null, null), null);
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
