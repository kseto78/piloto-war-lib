package es.mpr.plataformamensajeria.ws.seguimiento;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import es.mpr.plataformamensajeria.beans.ConsultaEstadoBean;
import es.mpr.plataformamensajeria.beans.ConsultaHistoricoBean;
import es.mpr.plataformamensajeria.jdbc.SeguimientoMensajesDAO;
import es.mpr.plataformamensajeria.response.RespuestaConsultaEstado;
import es.mpr.plataformamensajeria.response.RespuestaConsultaHistorial;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;

@WebService(endpointInterface = "es.mpr.plataformamensajeria.ws.seguimiento.ISeguimientoMensajesService")
public class SeguimientoMensajesImpl implements ISeguimientoMensajesService {
	static Logger logger = Logger.getLogger(SeguimientoMensajesImpl.class);
	@Override
		public String consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId,
				Integer loteId, Integer idMensaje, String idExterno, Integer estadoId,
				String fechaDesde, String fechaHasta, String usuario, String password) {
		logger.debug("[ConsultarEstado] Consultando estado de ServicioID "+ servicioId+ ", MensajeID "+idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<ConsultaEstadoBean> listaResultados = new ArrayList<ConsultaEstadoBean>();
		logger.debug("[ConsultarEstado] Iniciando transacción");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try{
			if(fechaDesde!=null&&!fechaDesde.isEmpty()){
				fDesde = sdf.parse(fechaDesde);
			}
		} catch (ParseException e) {
			fDesde=null;
		}
		try{
			if(fechaHasta!=null&&!fechaHasta.isEmpty()){
				fHasta = sdf.parse(fechaHasta);
			}
		} catch (ParseException e) {
			fHasta=null;
		}				
		logger.debug("[ConsultarEstado] Antes de consultarEstado");
			listaResultados = dao.consultarEstado(servicioId,canalId,aplicacionId,loteId,
					idMensaje,idExterno,estadoId,fDesde,fHasta,usuario,password);
			logger.debug("[ConsultarEstado] Despues de consultarEstado");
			RespuestaConsultaEstado rEstado = new RespuestaConsultaEstado();
			try {
				logger.debug("[ConsultarEstado] Generando XML de respuesta");
				xmlResultado = rEstado.generarXMLRespuesta(((servicioId!=null)?servicioId.toString():""), 
						((canalId!=null)?canalId.toString():""), ((aplicacionId!=null)?aplicacionId.toString():""),
						((loteId!=null)?loteId.toString():""),((idMensaje!=null)?idMensaje.toString():""), idExterno,
						((estadoId!=null)?estadoId.toString():""), fechaDesde,fechaHasta, usuario, password, listaResultados);
				logger.trace(xmlResultado);
				logger.debug("[ConsultarEstado] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dao.endTransaction(false);
		return xmlResultado;
	}
	@Override
	public String consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password) {
		logger.debug("[ConsultarHistorial] Consultando Historial del mensaje con id" + idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		logger.debug("[ConsultarHistorial] Iniciando transacción");
		dao.beginTransaction();
		ArrayList<ConsultaHistoricoBean> listaResultados = new ArrayList<ConsultaHistoricoBean>();
		RespuestaConsultaHistorial respuesta = new  RespuestaConsultaHistorial();
		logger.debug("[ConsultarHistorial] Antes de consultarHistorial");
		listaResultados = dao.consultarHistorial(idMensaje, idExterno, usuario, password);
		logger.debug("[ConsultarHistorial] Despues de consultarHistorial");
		try {
			logger.debug("[ConsultarHistorial] Generando XML de respuesta");
			xmlResultado = respuesta.generarXMLRespuesta(((idMensaje!=null)?idMensaje.toString():""),idExterno, usuario, password, listaResultados);
			logger.trace(xmlResultado);
			logger.debug("[ConsultarHistorial] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.endTransaction(false);
		return xmlResultado;
	}
	
	
}
