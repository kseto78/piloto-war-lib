package es.mpr.plataformamensajeria.ws.operaciones;

import java.math.BigDecimal;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import es.mpr.plataformamensajeria.jdbc.OperacionesMensajesDAO;
import es.mpr.plataformamensajeria.response.RespuestaOperacion;
import es.mpr.plataformamensajeria.util.WSPlataformaErrors;

@WebService(endpointInterface = "es.mpr.plataformamensajeria.ws.operaciones.IOperacionesMensajesService")
public class OperacionesMensajesImpl implements IOperacionesMensajesService {
	static Logger logger = Logger.getLogger(OperacionesMensajesImpl.class);
	@Override
	public String reenviarMensaje(Integer idMensaje,String usuario, String password) {
		logger.debug("[reenviarMensaje] Reenviando mensaje con id ");
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		logger.debug("[reenviarMensaje] Iniciando transaccion ");
		dao.beginTransaction();
		logger.debug("[reenviarMensaje] Antes de reenviarMensaje"+idMensaje);
		BigDecimal confirmacion = dao.reenviarMensaje(idMensaje, usuario, password);
		logger.debug("[reenviarMensaje] Despues de reenviarMensaje reenviarMensaje");
		RespuestaOperacion respuestaOpracion = new RespuestaOperacion();
		String error = null;
		if(WSPlataformaErrors.getErrorReenviarMensaje(confirmacion.intValue())!=null){
			error = WSPlataformaErrors.getErrorReenviarMensaje(confirmacion.intValue());
			error = confirmacion +"#" + error;
		}
		logger.debug("[reenviarMensaje] Generando XML de respuesta");
		resultadoXML = respuestaOpracion.generarXMLRespuesta(RespuestaOperacion.TAG_REENVIARMENSAJE, error);
		logger.trace(resultadoXML);
		logger.debug("[reenviarMensaje] XML de respuesta generado");
		dao.endTransaction(false);
		dao.closeAll();
		
		return resultadoXML;
	}
	@Override
	public String anularMensaje(Integer idMensaje, String usuario, String password) {
		logger.debug("[anularMensaje] Anulando mensaje con id "+ idMensaje);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		logger.debug("[anularMensaje] Iniciando transaccion");
		dao.beginTransaction();
		logger.debug("[anularMensaje] Antes de anularMensaje");
		BigDecimal confirmacion = dao.anularMensaje(idMensaje, usuario, password);
		logger.debug("[anularMensaje] Despues de anularMensaje");
		RespuestaOperacion respuestaOpracion = new RespuestaOperacion();
		String error = null;
		if(WSPlataformaErrors.getErrorAnularMensaje(confirmacion.intValue())!=null){
		 	error = WSPlataformaErrors.getErrorAnularMensaje(confirmacion.intValue());
		 	error = confirmacion +"#" + error;
		}
		logger.debug("[anularMensaje] Generando XML de respuesta");
		resultadoXML = respuestaOpracion.generarXMLRespuesta(RespuestaOperacion.TAG_ANULARMENSAJE, error);
		logger.trace(resultadoXML);
		logger.debug("[anularMensaje] XML de respuesta generado");
		dao.endTransaction(false);
		dao.closeAll();
		
		return resultadoXML;
	}
	
	@Override
	public String reenviarLote(Integer idLote, String usuario, String password) {
		logger.debug("[reenviarLote] Reenviando lote con id" + idLote);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		logger.debug("[reenviarLote] Iniciando transaccion");
		dao.beginTransaction();
		logger.debug("[reenviarLote] Antes de reenviarLote");
		BigDecimal confirmacion = dao.reenviarLote(idLote, usuario, password);
		logger.debug("[reenviarLote] Despues de reenviarLote");
		RespuestaOperacion respuestaOpracion = new RespuestaOperacion();
		String error = null;
		if(WSPlataformaErrors.getErrorReenviarLote(confirmacion.intValue())!=null){
			error = WSPlataformaErrors.getErrorReenviarLote(confirmacion.intValue());
			error = confirmacion +"#" + error;
		}
		logger.debug("[reenviarLote] Generando XML de respuesta");
		resultadoXML = respuestaOpracion.generarXMLRespuesta(RespuestaOperacion.TAG_REENVIARLOTE, error);
		logger.trace(resultadoXML);
		logger.debug("[reenviarLote] XML de respuesta generado");
		dao.endTransaction(false);
		dao.closeAll();
		
		return resultadoXML;
	}	
	
	@Override
	public String anularLote(Integer idLote, String usuario, String password) {
		logger.debug("[anularLote] Anulando lote con id "+idLote);
		String resultadoXML = "";
		OperacionesMensajesDAO dao = new OperacionesMensajesDAO();
		logger.debug("[anularLote] Iniciando transacción");
		dao.beginTransaction();
		logger.debug("[anularLote] Antes de anular lote");
		BigDecimal confirmacion = dao.anularLote(idLote, usuario, password);
		logger.debug("[anularLote] Despues de anular lote");
		RespuestaOperacion respuestaOpracion = new RespuestaOperacion();
		String error = null;
		if(WSPlataformaErrors.getErrorAnularLote(confirmacion.intValue())!=null){
			error = WSPlataformaErrors.getErrorAnularLote(confirmacion.intValue());
			error = confirmacion +"#" + error;
		}
		logger.debug("[anularLote] Generando XML de respuesta");
		resultadoXML = respuestaOpracion.generarXMLRespuesta(RespuestaOperacion.TAG_ANULARLOTE, error);
		logger.trace(resultadoXML);
		logger.debug("[anularLote] XML de respuesta generado");
		dao.endTransaction(false);
		dao.closeAll();
		
		return resultadoXML;
	}	
	
}
