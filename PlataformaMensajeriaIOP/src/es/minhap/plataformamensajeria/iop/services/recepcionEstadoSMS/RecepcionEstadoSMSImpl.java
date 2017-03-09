package es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorSubEstados;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUrlMensajePremiumManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblUrlMensajePremium;

/**
 * 
 * @author everis
 *
 */
@Service("recepcionEstadoSMSImpl")
public class RecepcionEstadoSMSImpl implements IRecepcionEstadoSMSService {

	private static final Logger LOG = LoggerFactory.getLogger(RecepcionEstadoSMSImpl.class);

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource
	private TblLotesEnviosManager lotesManager;

	@Resource
	private TblUrlMensajePremiumManager urlMensajeManager;

	@Autowired
	private TblHistoricosManager hitoricosManager;
	
	@Autowired
	private QueryExecutorSubEstados queryExecutorSubestados;
	
	@Resource
	private TblEstadosManager tblEstadosManager;
	

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public RespuestaEstadoSMSXMLBean recibirEstadoSMS(RecepcionEstadoSMSXMLBean recepcionEstadoSMS) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusCodeKO = ps.getMessage("plataformaErrores.recepcionEstado.STATUSCODE_KO", null);
		String errorUimNoExiste = ps.getMessage("plataformaErrores.recepcionEstado.ERROR_UIM_NOEXISTE", null);
		String errorEstadoMensaje = ps.getMessage("plataformaErrores.recepcionEstado.ERROR_ESTADO_MENSAJE", null);
		String statusTextKO = ps.getMessage("plataformaErrores.recepcionEstado.STATUSTEXT_KO", null);
		String statusDetailsKO = ps.getMessage("plataformaErrores.recepcionEstado.STATUSDETAILS_KO", null);
		String statusCodeOK = ps.getMessage("plataformaErrores.recepcionEstado.STATUSCODE_OK", null);
		String statusTextOK = ps.getMessage("plataformaErrores.recepcionEstado.STATUSTEXT_OK", null);
		String statusDetailsOK = ps.getMessage("plataformaErrores.recepcionEstado.STATUSDETAILS_OK", null);

		Long mensajeID = null;
		Long cod;
		RespuestaEstadoSMSXMLBean res = new RespuestaEstadoSMSXMLBean();
		String urlPremium = "";
		TblMensajes mensaje = null;
		String descripcion;
		Long estadoFinalId;
			
		
		try {
			mensaje = mensajesManager.getMensajeIDByUIM(recepcionEstadoSMS.getMensajeId());
			if(mensaje != null) {
				mensajeID = mensaje.getMensajeid();
				urlPremium = getUrlPremium(mensajeID);
			} else {
				LOG.error("[RespuestaEstadoSMSXMLBean] Error recuperando mensaje por UIM");
			}
		} catch (Exception e) {
			LOG.error("[RespuestaEstadoSMSXMLBean] Error recuperando mensaje por UIM", e);
		}

		if (null == mensajeID) {
			LOG.debug("[recibirEstadoSMS] Se ha producido un error buscando el mensajeID a traves del UIM: "
					+ recepcionEstadoSMS.getMensajeId());
			return getRespuestaNoMensaje(recepcionEstadoSMS, statusCodeOK, statusDetailsOK, urlPremium);
		}

		String estado = recepcionEstadoSMS.getMessajeStatus().toUpperCase().trim();

		try {

			cod = new Long(estado);
		} catch (Exception e) {
			LOG.error("[RespuestaEstadoSMSXMLBean] Error recuperando mensaje por UIM", e);
			return getRespuestaNoEstado(statusCodeOK, errorEstadoMensaje, statusTextOK, urlPremium);
		}

		Long destinatarioMensajeId = null;

		if (null != mensaje && lotesManager.isMultidestinatario(mensajeID)) {
			destinatarioMensajeId = destinatariosMensajesManager.getDestinatarioMensajeByUim(
					recepcionEstadoSMS.getMensajeId()).getDestinatariosmensajes();
		}

		EstadosBean estadoMensaje = queryExecutorSubestados.getEstadoByCode(cod.toString());
		estadoFinalId = estadoMensaje.getEstadoId();
		descripcion = estadoMensaje.getDescripcion();
		try {
			Integer ultimoEstadoHistorico = hitoricosManager.getUltimoEstadoHistorico(mensaje.getMensajeid(), destinatarioMensajeId).intValue();
			
			if(null == ultimoEstadoHistorico || ultimoEstadoHistorico != estadoFinalId.intValue()){
//				idHistorico = hitoricosManager.creaHistorico(mensaje.getMensajeid(),destinatarioMensajeId, estadoFinalId, null, 
//					descripcion, cod.toString(), recepcionEstadoSMS.getUser());
				mensajesManager.setEstadoMensaje(mensaje.getMensajeid(),tblEstadosManager.getEstadoById(estadoFinalId).getNombre(), descripcion, false, destinatarioMensajeId, cod.toString(), recepcionEstadoSMS.getUser(), null);
			}

			ResponseStatusType status;
			if (null == ultimoEstadoHistorico || ultimoEstadoHistorico <= 0)
			  //status = getStatus(statusCodeKO, statusTextKO, statusDetailsKO, urlPremium);
				status = getStatus(statusCodeOK, statusTextOK, statusDetailsKO, urlPremium);
			else
				status = getStatus(statusCodeOK, statusTextOK, statusDetailsOK, urlPremium);

			res.setStatus(status);
		} catch (Exception e) {
			LOG.error("[Registrar Historico] Se ha producido un error escribiendo en el Historico el mensaje: "
					+ mensaje.getMensajeid(),e);
		  //ResponseStatusType status = getStatus(statusCodeKO, statusTextKO, statusDetailsKO, urlPremium);
			ResponseStatusType status = getStatus(statusCodeOK, statusTextOK, statusDetailsKO, urlPremium);
			res.setStatus(status);
		}

		return res;
	}

	private ResponseStatusType getStatus(String statusCode, String statusText, String statusDetails, String urlPremium) {
		ResponseStatusType res = new ResponseStatusType();
		res.setStatusCode(statusCode);
		res.setStatusText(statusText);
		if (urlPremium.length() > 0)
			res.setDetails(statusDetails + "," + urlPremium);
		else
			res.setDetails(statusDetails);
		return res;
	}

	private RespuestaEstadoSMSXMLBean getRespuestaNoEstado(String statusCodeOK, String errorEstadoMensaje,
			String statusTextOK, String urlPremium) {
		RespuestaEstadoSMSXMLBean res = new RespuestaEstadoSMSXMLBean();
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(statusCodeOK);
		if (urlPremium.length() > 0)
			status.setDetails(errorEstadoMensaje + "," + urlPremium);
		else
			status.setDetails(errorEstadoMensaje);
		status.setStatusText(statusTextOK);
		res.setStatus(status);
		return res;
	}

	private RespuestaEstadoSMSXMLBean getRespuestaNoMensaje(RecepcionEstadoSMSXMLBean recepcionEstadoSMS,
			String statusCodeOK, String statusDetailsOK, String urlPremium) {
		RespuestaEstadoSMSXMLBean res = new RespuestaEstadoSMSXMLBean();
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(statusCodeOK);
		status.setStatusText(statusDetailsOK);
		if (urlPremium.length() > 0)
			status.setDetails("UIM " + recepcionEstadoSMS.getMensajeId() + " NOT FOUND," + urlPremium);
		else
			status.setDetails("UIM " + recepcionEstadoSMS.getMensajeId() + " NOT FOUND");
		res.setStatus(status);
		return res;
	}

	private String getUrlPremium(Long mensajeID) {
		TblUrlMensajePremium urlPremium = urlMensajeManager.getUrlByMensaje(mensajeID);

		if (mensajeID != null && null != urlPremium && !urlPremium.getUrl().isEmpty()) {
			// comprobamos si es mensaje premium para anadir la url
			return urlPremium.getUrl().concat(" | ").concat(mensajeID.toString());
		}
		return "";
	}

	@Override
	public String recibirEstadoSMSXML(RecepcionEstadoSMSXMLBean recepcionEstadoSMS) {
		RespuestaEstadoSMSXMLBean respuesta = this.recibirEstadoSMS(recepcionEstadoSMS);
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String res = "";
		try {
			res = respuesta.toXML();
		} catch (PlataformaBusinessException e) {
			LOG.error("[Recepcion Estado SMS metodo recibirEstadoSMSXML] Se ha producido un error recibiendo el estado: ",e);
			res = ps.getMessage("plataformaErrores.generales.TAG_ERROR_GENERANDO_RESPUESTA_XML", null);
		}
		return res;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager the mensajesManager to set
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
	 * @param destinatariosMensajesManager the destinatariosMensajesManager to set
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
	 * @param lotesManager the lotesManager to set
	 */
	public void setLotesManager(TblLotesEnviosManager lotesManager) {
		this.lotesManager = lotesManager;
	}

	/**
	 * @return the urlMensajeManager
	 */
	public TblUrlMensajePremiumManager getUrlMensajeManager() {
		return urlMensajeManager;
	}

	/**
	 * @param urlMensajeManager the urlMensajeManager to set
	 */
	public void setUrlMensajeManager(TblUrlMensajePremiumManager urlMensajeManager) {
		this.urlMensajeManager = urlMensajeManager;
	}

	/**
	 * @return the hitoricosManager
	 */
	public TblHistoricosManager getHitoricosManager() {
		return hitoricosManager;
	}

	/**
	 * @param hitoricosManager the hitoricosManager to set
	 */
	public void setHitoricosManager(TblHistoricosManager hitoricosManager) {
		this.hitoricosManager = hitoricosManager;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

	/**
	 * @return the queryExecutorSubestados
	 */
	public QueryExecutorSubEstados getQueryExecutorSubestados() {
		return queryExecutorSubestados;
	}

	/**
	 * @param queryExecutorSubestados the queryExecutorSubestados to set
	 */
	public void setQueryExecutorSubestados(QueryExecutorSubEstados queryExecutorSubestados) {
		this.queryExecutorSubestados = queryExecutorSubestados;
	}

	/**
	 * @return the tblEstadosManager
	 */
	public TblEstadosManager getTblEstadosManager() {
		return tblEstadosManager;
	}

	/**
	 * @param tblEstadosManager the tblEstadosManager to set
	 */
	public void setTblEstadosManager(TblEstadosManager tblEstadosManager) {
		this.tblEstadosManager = tblEstadosManager;
	}

}
