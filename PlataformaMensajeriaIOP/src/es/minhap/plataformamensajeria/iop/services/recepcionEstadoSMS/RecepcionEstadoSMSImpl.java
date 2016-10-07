package es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.HistoricoBean;
import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.jdbc.HistoricoDAO;
import es.minhap.plataformamensajeria.iop.jdbc.MensajeDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

public class RecepcionEstadoSMSImpl implements IRecepcionEstadoSMSService {

	static Logger logger = Logger.getLogger(RecepcionEstadoSMSImpl.class);

	private static String STATUSCODE_OK = "0000";
	private static String STATUSCODE_KO = "4000";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSCTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSDETAILS_KO = "Error en la Peticion";
	private static String ERROR_ESTADO_MENSAJE ="Se ha recibido un estado no permitido ";
	private static String ERROR_UIM_NOEXISTE="No existe el UIM indicado";
	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";
	

	@Override
	public RespuestaEstadoSMSXMLBean recibirEstadoSMS(RecepcionEstadoSMSXMLBean recepcionEstadoSMS) {

		MensajeDAO dm = new MensajeDAO();

		Integer mensajeID = null;
		BigDecimal cod = new BigDecimal(0);
		RespuestaEstadoSMSXMLBean res = new RespuestaEstadoSMSXMLBean();
		String urlPremium = "";

		try {
			dm.beginTransaction();
			mensajeID = dm.getMensajeIDByUIM(recepcionEstadoSMS.getMensajeId());
			
			if (mensajeID != null && null != dm.getUrlPremium(mensajeID) && !dm.getUrlPremium(mensajeID).isEmpty()) {
				//comprobamos si es mensaje premium para anadir la url
				urlPremium = dm.getUrlPremium(mensajeID).concat(" | ").concat(mensajeID.toString());
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
		} finally {
			dm.endTransaction(false);
			dm.closeAll();
		}
		
		if (null == mensajeID) {
			logger.debug("[recibirEstadoSMS] Se ha producido un error buscando el mensajeID a traves del UIM: " + recepcionEstadoSMS.getMensajeId());
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(ERROR_UIM_NOEXISTE);
			if (urlPremium.length()>0)
				status.setDetails("UIM "+recepcionEstadoSMS.getMensajeId()+" NOT FOUND,"+urlPremium);
			else
				status.setDetails("UIM "+recepcionEstadoSMS.getMensajeId()+" NOT FOUND");
			res.setStatus(status);	
			return res;
		}
		
		String estado = recepcionEstadoSMS.getMessajeStatus().toUpperCase().trim();

		try {
			
			cod = new BigDecimal(estado);
		} catch (NumberFormatException e) {
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			if (urlPremium.length()>0)
				status.setDetails(ERROR_ESTADO_MENSAJE+","+urlPremium);
			else
				status.setDetails(ERROR_ESTADO_MENSAJE);
			status.setStatusText(STATUSCTEXT_KO);
			res.setStatus(status);
			return res;
		} catch (Exception e1) {
			logger.debug("Cargando fichero propiedades");
		}

		HistoricoBean hist = new HistoricoBean(new Date(), new BigDecimal(mensajeID), cod);
		hist.setDescripcion(recepcionEstadoSMS.getStatusText());
		boolean multidestinatario = false;
		
		HistoricoDAO dao = new HistoricoDAO();

		logger.debug("[Registrar Historico] Antes de registrar el mensajeId: " + hist.getMensajeid());

		try {
			dao.beginTransaction();
			dm.beginTransaction();
			if (dm.isMultidestinatario(mensajeID)){
				hist.setDestinatariosMensajes(dm.getDestinatarioMensajeByUim_Mensaje(mensajeID,recepcionEstadoSMS.getMensajeId()));
				multidestinatario = true;
			}
			dm.endTransaction(false);
			boolean historico = false;
			//actualizamos el estado del lote
			dao.actualizarEstadoLote(hist.getMensajeid().intValue());
			dao.endTransaction(true); //confirmamos
			dao.beginTransaction();
			if (multidestinatario){
				historico = dao.setHistoricoMult(hist);
				dao.actualizarEstadoLote(hist.getMensajeid().intValue());
			}else{
				historico = dao.setHistorico(hist);
			}
			dao.endTransaction(true);
			ResponseStatusType status = new ResponseStatusType();
			if(!historico){
				status.setStatusCode(STATUSCODE_KO);
				status.setStatusText(STATUSCTEXT_KO);
				if (urlPremium.length()>0)
					status.setDetails(STATUSDETAILS_KO+","+urlPremium);
				else
					status.setDetails(STATUSDETAILS_KO);
			}else{
			logger.debug("[Recibir Historico] Despues de registrar el mensajeId: " + hist.getMensajeid());
			status.setStatusCode(STATUSCODE_OK);
			status.setStatusText(STATUSTEXT_OK);
			if (urlPremium.length()>0)
				status.setDetails(STATUSDETAILS_OK+","+urlPremium);
			else
				status.setDetails(STATUSDETAILS_OK);
			}
			res.setStatus(status);
		} catch (Exception e) {
			logger.debug("[Registrar Historico] Se ha producido un error escribiendo en el Historico el mensaje: " + hist.getMensajeid());
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			if (urlPremium.length()>0)
				status.setDetails(STATUSDETAILS_KO+","+urlPremium);
			else
				status.setDetails(STATUSDETAILS_KO);
			res.setStatus(status);
			dao.endTransaction(false);// hacemos rollback
			dm.endTransaction(false);
		} finally {
			dao.closeAll();
			dm.closeAll();
		}

		return res;
	}

	@Override
	public String recibirEstadoSMSXML(RecepcionEstadoSMSXMLBean recepcionEstadoSMS) {
		RespuestaEstadoSMSXMLBean respuesta = this.recibirEstadoSMS(recepcionEstadoSMS);
		String res = "";
		try {
			res = respuesta.toXML();
		} catch (PlataformaBusinessException e) {
			res = TAG_ERROR_GENERANDO_RESPUESTA_XML;
		}
		return res;
	}

}
