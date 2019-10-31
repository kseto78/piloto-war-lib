package es.minhap.plataformamensajeria.iop.services.operaciones;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.OperacionesLotesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.OperacionesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;

/**
 * 
 * @author everis
 * 
 */
@Service("operacionesMensajesImpl")
public class OperacionesMensajesImpl implements IOperacionesMensajesService {
	protected static final String R_CONST_1 = "[anularLote] Error anulando lote";

	protected static final String R_CONST_2 = "[reenviarMensaje] Generando XML de respuesta";

	protected static final String R_CONST_3 = "[reenviarLote] Error reenviando lote";

	protected static final String R_CONST_4 = "plataformaErrores.generales.STATUSTEXT_KO";

	protected static final String R_CONST_5 = "plataformaErrores.operacionesMensajes.TAG_ERROR_GENERANDO_RESPUESTA_XML";

	protected static final String R_CONST_6 = "plataformaErrores.operacionesMensajes.TAG_MENSAJE_OK";

	protected static final String R_CONST_7 = "plataformaErrores.generales.STATUS_OK";

	protected static final String R_CONST_8 = "plataformaErrores.generales.STATUSTEXT_OK";

	protected static final String R_CONST_9 = "constantes.ESTADO_ANULADO";

	protected static final String R_CONST_10 = "plataformaErrores.operacionesMensajes.STATUSCODE_KO";

	protected static final String R_CONST_11 = "plataformaErrores.operacionesMensajes.STATUSTEXT_KO";

	protected static final String R_CONST_12 = "plataformaErrores.operacionesMensajes.DETAILSUSUARIO";

	protected static final String R_CONST_13 = "constantes.ESTADO_PENDIENTE";

	protected static final String R_CONST_14 = "[reenviarMensaje] Error reenviando mensaje";

	private static final Logger LOG = LoggerFactory.getLogger(OperacionesMensajesImpl.class);

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblLotesEnviosManager lotesEnviosManager;

	@Resource
	private TblEstadosManager estadosManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public String reenviarMensaje(Integer idMensaje, String usuario, String password) {

		LOG.debug("[reenviarMensaje] Reenviando mensaje con id ");
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String resultadoXML = "";

		try {

			LOG.debug("[reenviarMensaje] Antes de reenviarMensaje" + idMensaje);
			String estado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_13, null))
					.getNombre();
			Integer confirmacion = mensajesManager.operacionMensajeReenviar(idMensaje.longValue(), usuario, password, estado);
			LOG.debug("[reenviarMensaje] Despues de reenviarMensaje reenviarMensaje");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorReenviarMensaje(confirmacion);
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_11, null));
				status.setDetails(error);
			} else {
				status.setStatusCode(ps.getMessage(R_CONST_7, null));
				status.setStatusText(ps.getMessage(R_CONST_8, null));
				status.setDetails(ps.getMessage(R_CONST_6, null));
			}
			respuestaOpracion.setStatus(status);
			LOG.debug(R_CONST_2);

			resultadoXML = respuestaOpracion.toXML();
		} catch (Exception e) {
			LOG.error(R_CONST_14, e);
			resultadoXML = ps.getMessage(R_CONST_5,
					null);
		}
		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String anularMensaje(Integer idMensaje, String usuario, String password) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		LOG.debug("[anularMensaje] Anulando mensaje con id " + idMensaje);
		String resultadoXML = "";

		try {
			LOG.debug("[anularMensaje] Antes de anularMensaje");
			String estado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_9, null))
					.getNombre();
			Integer confirmacion = mensajesManager.operacionMensaje(idMensaje.longValue(), usuario, password, estado);
			LOG.debug("[anularMensaje] Despues de anularMensaje");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorAnularMensaje(confirmacion);
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_11, null));
				status.setDetails(error);
			} else {
				status.setStatusCode(ps.getMessage(R_CONST_7, null));
				status.setStatusText(ps.getMessage(R_CONST_8, null));
				status.setDetails(ps.getMessage(R_CONST_6, null));
			}
			respuestaOpracion.setStatus(status);
			LOG.debug(R_CONST_2);

			resultadoXML = respuestaOpracion.toXML();
		} catch (Exception e) {
			LOG.error("[anularMensaje] Error reenviando mensaje", e);
			resultadoXML = ps.getMessage(R_CONST_5,
					null);
		}
		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String reenviarLote(Integer idLote, String usuario, String password) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		LOG.debug("[reenviarLote] Reenviando lote con id" + idLote);
		String resultadoXML = "";
		try {
			LOG.debug("[reenviarLote] Iniciando transaccion");
			String estado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_13, null))
					.getNombre();
			LOG.debug("[reenviarLote] Antes de reenviarLote");
			Integer confirmacion = lotesEnviosManager.operacionesLotes(idLote.longValue(), usuario, password, estado);
			LOG.debug("[reenviarLote] Despues de reenviarLote");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorReenviarLote(confirmacion);
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_11, null));
				status.setDetails(error);
			} else {
				status.setStatusCode(ps.getMessage(R_CONST_7, null));
				status.setStatusText(ps.getMessage(R_CONST_8, null));
				status.setDetails(ps.getMessage(R_CONST_6, null));
			}
			respuestaOpracion.setStatus(status);
			LOG.debug("[reenviarLote] Generando XML de respuesta");

			resultadoXML = respuestaOpracion.toXML();
		} catch (Exception e) {
			LOG.error(R_CONST_3, e);
			resultadoXML = ps.getMessage(R_CONST_5,
					null);
		}
		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String anularLote(Integer idLote, String usuario, String password) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		LOG.debug("[anularLote] Anulando lote con id " + idLote);
		String resultadoXML = "";

		try {
			LOG.debug("[anularLote] Iniciando transaccion");
			String estado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_9, null))
					.getNombre();
			LOG.debug("[anularLote] Antes de anular lote");
			Integer confirmacion = lotesEnviosManager.operacionesLotes(idLote.longValue(), usuario, password, estado);
			LOG.debug("[anularLote] Despues de anular lote");
			RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			String error;
			if (confirmacion.intValue() < 0) {
				error = WSPlataformaErrors.getErrorAnularLote(confirmacion);
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_11, null));
				status.setDetails(error);
			} else {
				status.setStatusCode(ps.getMessage(R_CONST_7, null));
				status.setStatusText(ps.getMessage(R_CONST_8, null));
				status.setDetails(ps.getMessage(R_CONST_6, null));
			}
			respuestaOpracion.setStatus(status);
			LOG.debug("[anularLote] Generando XML de respuesta");

			resultadoXML = respuestaOpracion.toXML();
		} catch (Exception e) {
			LOG.error(R_CONST_1, e);
			resultadoXML = ps.getMessage(R_CONST_5,
					null);
		}

		return Utils.convertToUTF8(resultadoXML);
	}

	@Override
	public String reenviarMensaje(OperacionesMensajesXMLBean operacionesMensajes) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Integer mensajeid = null;

		if (!validarOperacionMensaje(operacionesMensajes.getUsuario(), operacionesMensajes.getPassword())) {
			RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
			String statusCode = ps.getMessage(R_CONST_10, null);
			String statusText = ps.getMessage(R_CONST_11, null);
			String detailsUsuario = ps.getMessage(R_CONST_12, null);
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setDetails(detailsUsuario);
			status.setStatusCode(statusCode);
			status.setStatusText(statusText);
			respuestaOperacion.setStatus(status);

			try {
				return respuestaOperacion.toXML();
			} catch (PlataformaBusinessException e) {
				LOG.error(R_CONST_1, e);
				return null;
			}
		} else {
			try {
				mensajeid = Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje());
			} catch (java.lang.NumberFormatException ex) {
				LOG.error("[reenviarMensaje] idLote no es un Integer", ex);

			}
			if (null == mensajeid || mensajeid <= 0) {
				String error = WSPlataformaErrors.getErrorIdMensajeIncorrecto();
				es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_4, null));
				status.setDetails(error);
				RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
				respuestaOpracion.setStatus(status);
				try {
					return respuestaOpracion.toXML();
				} catch (PlataformaBusinessException e) {
					LOG.error(R_CONST_14, e);
					return null;
				}
			}

			return this.reenviarMensaje(Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje()),
					operacionesMensajes.getUsuario(), operacionesMensajes.getPassword());
		}
	}

	@Override
	public String anularMensaje(OperacionesMensajesXMLBean operacionesMensajes) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Integer mensajeid = null;
		if (!validarOperacionMensaje(operacionesMensajes.getUsuario(), operacionesMensajes.getPassword())) {
			RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
			String statusCode = ps.getMessage(R_CONST_10, null);
			String statusText = ps.getMessage(R_CONST_11, null);
			String detailsUsuario = ps.getMessage(R_CONST_12, null);
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setDetails(detailsUsuario);
			status.setStatusCode(statusCode);
			status.setStatusText(statusText);
			respuestaOperacion.setStatus(status);

			try {
				return respuestaOperacion.toXML();
			} catch (PlataformaBusinessException e) {
				LOG.error(R_CONST_1, e);
				return null;
			}
		} else {
			try {
				mensajeid = Integer.parseInt(operacionesMensajes.getMensaje().getIdMensaje());
			} catch (java.lang.NumberFormatException ex) {
				LOG.error("[anularMensaje] idLote no es un Integer", ex);
			}
			if (null == mensajeid || mensajeid <= 0) {
				String error = WSPlataformaErrors.getErrorIdMensajeIncorrecto();
				es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_4, null));
				status.setDetails(error);
				RespuestaOperacionesMensajes respuestaOpracion = new RespuestaOperacionesMensajes();
				respuestaOpracion.setStatus(status);
				try {
					return respuestaOpracion.toXML();
				} catch (PlataformaBusinessException e) {
					LOG.error("[anularMensaje] Error anulando mensaje", e);
					return null;
				}
			}

			return this.anularMensaje(mensajeid, operacionesMensajes.getUsuario(), operacionesMensajes.getPassword());
		}
	}

	@Override
	public String reenviarLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes) {
		Integer idlote = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		if (!validarOperacionLote(operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword())) {
			RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
			String statusCode = ps.getMessage(R_CONST_10, null);
			String statusText = ps.getMessage(R_CONST_11, null);
			String detailsUsuario = ps.getMessage(R_CONST_12, null);
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setDetails(detailsUsuario);
			status.setStatusCode(statusCode);
			status.setStatusText(statusText);
			respuestaOperacion.setStatus(status);

			try {
				return respuestaOperacion.toXML();
			} catch (PlataformaBusinessException e) {
				LOG.error(R_CONST_1, e);
				return null;
			}
		} else {
			try {
				idlote = Integer.parseInt(operacionesLotesMensajes.getLote());
			} catch (java.lang.NumberFormatException ex) {
				LOG.error("[reenviarLote] idLote no es un Integer", ex);
			}
			if (null == idlote || idlote <= 0) {
				String error = WSPlataformaErrors.getErrorIdLoteIncorrecto();
				es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_4, null));
				status.setDetails(error);
				RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
				respuestaOperacion.setStatus(status);
				try {
					return respuestaOperacion.toXML();
				} catch (PlataformaBusinessException e) {
					LOG.error(R_CONST_3, e);
					return null;
				}
			}

			return this.reenviarLote(Integer.parseInt(operacionesLotesMensajes.getLote()),
					operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword());
		}
	}

	@Override
	public String anularLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes) {
		Integer idlote = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		if (!validarOperacionLote(operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword())) {
			RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
			String statusCode = ps.getMessage(R_CONST_10, null);
			String statusText = ps.getMessage(R_CONST_11, null);
			String detailsUsuario = ps.getMessage(R_CONST_12, null);
			es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
			status.setDetails(detailsUsuario);
			status.setStatusCode(statusCode);
			status.setStatusText(statusText);
			respuestaOperacion.setStatus(status);

			try {
				return respuestaOperacion.toXML();
			} catch (PlataformaBusinessException e) {
				LOG.error(R_CONST_1, e);
				return null;
			}
		} else {
			try {
				idlote = Integer.parseInt(operacionesLotesMensajes.getLote());
			} catch (java.lang.NumberFormatException ex) {
				LOG.error("[anularLote] idLote no es un Integer", ex);
			}
			if (null == idlote || idlote <= 0) {
				String error = WSPlataformaErrors.getErrorIdLoteIncorrecto();
				es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType status = new es.minhap.plataformamensajeria.iop.services.operaciones.ResponseStatusType();
				status.setStatusCode(ps.getMessage(R_CONST_10, null));
				status.setStatusText(ps.getMessage(R_CONST_4, null));
				status.setDetails(error);
				RespuestaOperacionesMensajes respuestaOperacion = new RespuestaOperacionesMensajes();
				respuestaOperacion.setStatus(status);
				try {
					return respuestaOperacion.toXML();
				} catch (PlataformaBusinessException e) {
					LOG.error(R_CONST_1, e);
					return null;
				}
			}

			return this.anularLote(Integer.parseInt(operacionesLotesMensajes.getLote()),
					operacionesLotesMensajes.getUsuario(), operacionesLotesMensajes.getPassword());
		}
	}

	private boolean validarOperacionMensaje(String usuario, String password) {
		return null != usuario && !usuario.isEmpty() && null != password && !password.isEmpty();
	}

	private boolean validarOperacionLote(String usuario, String password) {
		return null != usuario && !usuario.isEmpty() && null != password && !password.isEmpty();
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
	 * @return the lotesEnviosManager
	 */
	public TblLotesEnviosManager getLotesEnviosManager() {
		return lotesEnviosManager;
	}

	/**
	 * @param lotesEnviosManager
	 *            the lotesEnviosManager to set
	 */
	public void setLotesEnviosManager(TblLotesEnviosManager lotesEnviosManager) {
		this.lotesEnviosManager = lotesEnviosManager;
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
