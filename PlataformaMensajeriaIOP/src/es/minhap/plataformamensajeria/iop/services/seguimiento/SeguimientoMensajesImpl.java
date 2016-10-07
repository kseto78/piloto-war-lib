package es.minhap.plataformamensajeria.iop.services.seguimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionNotificacionEstadoSMS;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Registro;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Respuesta;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.jdbc.SeguimientoMensajesDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


public class SeguimientoMensajesImpl implements ISeguimientoMensajesService {
	static Logger logger = Logger.getLogger(SeguimientoMensajesImpl.class);
	
	@Override
	public String consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId,
				Integer loteId, Integer idMensaje, String idExterno, Integer estadoId,
				String fechaDesde, String fechaHasta, String usuario, String password) {
		return this.consultarEstado(servicioId, canalId, aplicacionId, loteId, idMensaje, idExterno, estadoId, fechaDesde, fechaHasta, usuario, password, null);
	}
	
	@Override
	public String consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId,
				Integer loteId, Integer idMensaje, String idExterno, Integer estadoId,
				String fechaDesde, String fechaHasta, String usuario, String password, 
				PropertiesServices ps) {
		logger.debug("[ConsultarEstado] Consultando estado de ServicioID "+ servicioId+ ", MensajeID "+idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
		logger.debug("[ConsultarEstado] Iniciando transaccion");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			Respuesta respuesta = new  Respuesta();
			try {
				logger.debug("[ConsultarEstado] Generando XML de respuesta");
				xmlResultado = respuesta.toXMLEstado(listaResultados, ps);
				logger.trace(xmlResultado);
				logger.debug("[ConsultarEstado] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				logger.error("Se ha producido un error ", e);
			}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	
	@Override
	public String consultarEstado(ConsultaEstadoBean consultaEstado) {
		return this.consultarEstado(consultaEstado, null);
	}
	
	@Override
	public String consultarEstado(ConsultaEstadoBean consultaEstado, PropertiesServices ps) {
	
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
		logger.debug("[ConsultarEstado] Iniciando transaccion");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		int idServicio=0,idCanal=0,idAplicacion=0,idLote=0,idMensaje=0,idEstado=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try{
			if(consultaEstado.getIdServicio()!=null&&!consultaEstado.getIdServicio().isEmpty()){
				idServicio = Integer.parseInt(consultaEstado.getIdServicio());
			}
		} catch (NumberFormatException e) {
			idServicio=0;
		}
		try{
			if(consultaEstado.getCanal()!=null&&!consultaEstado.getCanal().isEmpty()){
				idCanal = Integer.parseInt(consultaEstado.getCanal());
			}
		} catch (NumberFormatException e) {
			idCanal=0;
		}
		try{
			if(consultaEstado.getIdAplicacion()!=null&&!consultaEstado.getIdAplicacion().isEmpty()){
				idAplicacion = Integer.parseInt(consultaEstado.getIdAplicacion());
			}
		} catch (NumberFormatException e) {
			idAplicacion=0;
		}
		try{
			if(consultaEstado.getLote()!=null&&!consultaEstado.getLote().isEmpty()){
				idLote = Integer.parseInt(consultaEstado.getLote());
			}
		} catch (NumberFormatException e) {
			idLote=0;
		}
		try{
			if(consultaEstado.getIdMensaje()!=null&&!consultaEstado.getIdMensaje().isEmpty()){
				idMensaje = Integer.parseInt(consultaEstado.getIdMensaje());
			}
		} catch (NumberFormatException e) {
			idMensaje=0;
		}
		try{
			if(consultaEstado.getEstado()!=null&&!consultaEstado.getEstado().isEmpty()){
				idEstado = Integer.parseInt(consultaEstado.getEstado());
			}
		} catch (NumberFormatException e) {
			idEstado=0;
		}
		try{
			if(consultaEstado.getFechaDesde()!=null&&!consultaEstado.getFechaDesde().isEmpty()){
				fDesde = sdf.parse(consultaEstado.getFechaDesde());
			}
		} catch (ParseException e) {
			fDesde=null;
		}
		try{
			if(consultaEstado.getFechaHasta()!=null&&!consultaEstado.getFechaHasta().isEmpty()){
				fHasta = sdf.parse(consultaEstado.getFechaHasta());
			}
		} catch (ParseException e) {
			fHasta=null;
		}				
		logger.debug("[ConsultarEstado] Antes de consultarEstado");
			listaResultados = dao.consultarEstado(idServicio,idCanal,
					idCanal,
					idLote,
					idMensaje,consultaEstado.getIdExterno(),idEstado,fDesde,fHasta,
					consultaEstado.getUsuario(),consultaEstado.getPassword());
			logger.debug("[ConsultarEstado] Despues de consultarEstado");
			Respuesta respuesta = new  Respuesta();
			try {
				logger.debug("[ConsultarEstado] Generando XML de respuesta");
				xmlResultado = respuesta.toXMLEstado(listaResultados, ps);
				logger.trace(xmlResultado);
				logger.debug("[ConsultarEstado] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				logger.error("Se ha producido un error ", e);
			}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	
	@Override
	public String consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password) {
		logger.debug("[ConsultarHistorial] Consultando Historial del mensaje con id" + idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		logger.debug("[ConsultarHistorial] Iniciando transaccion");
		dao.beginTransaction();
		ArrayList<Registro> listaResultados = new ArrayList<Registro>();
		Respuesta respuesta = new  Respuesta();
		logger.debug("[ConsultarHistorial] Antes de consultarHistorial");
		listaResultados = dao.consultarHistorial(idMensaje, idExterno, usuario, password);
		logger.debug("[ConsultarHistorial] Despues de consultarHistorial");
		try {
			logger.debug("[ConsultarHistorial] Generando XML de respuesta");
			xmlResultado = respuesta.toXMLHistorial(listaResultados);
			
			logger.trace(xmlResultado);
			logger.debug("[ConsultarHistorial] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error ", e);
		}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	
	
	@Override
	public String consultarEstado(ConsultaEstadoXMLBean consultaEstado) {
		return this.consultarEstado(consultaEstado, null);
		
	}
	
	@Override
	public String consultarEstado(ConsultaEstadoXMLBean consultaEstado, PropertiesServices ps) {
		
		Integer idServicio = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getIdServicio()!=null && !("").equals(consultaEstado.getFiltro().getIdServicio())){
			idServicio=Integer.parseInt(consultaEstado.getFiltro().getIdServicio());
		}
		
		Integer idCanal = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getIdCanal()!=null && !("").equals(consultaEstado.getFiltro().getIdCanal())){
			idCanal=Integer.parseInt(consultaEstado.getFiltro().getIdCanal());
		}
		
		Integer idAplicacion = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getIdAplicacion()!=null && !("").equals(consultaEstado.getFiltro().getIdAplicacion())){
			idAplicacion=Integer.parseInt(consultaEstado.getFiltro().getIdAplicacion());
		}
		
		Integer idLote = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getIdLote()!=null && !("").equals(consultaEstado.getFiltro().getIdLote())){
			idLote=Integer.parseInt(consultaEstado.getFiltro().getIdLote());
		}
		
		Integer idEstado = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getIdEstado()!=null && !("").equals(consultaEstado.getFiltro().getIdEstado())){
			idEstado=Integer.parseInt(consultaEstado.getFiltro().getIdEstado());
		}
		
		Integer idMensaje = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getMensaje()!=null &&  consultaEstado.getFiltro().getMensaje().getIdMensaje()!=null && !("").equals(consultaEstado.getFiltro().getMensaje().getIdMensaje())) {
			idMensaje=Integer.parseInt(consultaEstado.getFiltro().getMensaje().getIdMensaje());
		}
		
		String idExterno = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getMensaje()!=null && !("").equals(consultaEstado.getFiltro().getMensaje().getIdExterno())) {
			idExterno=consultaEstado.getFiltro().getMensaje().getIdExterno();
		}
		
		String fechaDesde = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getFechaDesde()!=null && !("").equals(consultaEstado.getFiltro().getFechaDesde())){
			fechaDesde=consultaEstado.getFiltro().getFechaDesde();
		}
		
		String fechaHasta = null;
		if(consultaEstado.getFiltro()!=null && consultaEstado.getFiltro().getFechaHasta()!=null && !("").equals(consultaEstado.getFiltro().getFechaHasta())){
			fechaHasta=consultaEstado.getFiltro().getFechaHasta();
		}

		return this.consultarEstado(idServicio,idCanal,idAplicacion,idLote,idMensaje, idExterno, idEstado,	fechaDesde, fechaHasta, consultaEstado.getUsuario(),consultaEstado.getPassword(), ps);
	}
	@Override
	public String consultarHistorial(ConsultaHistoricoXMLBean consultaHistorico) {
		
		Integer idMensaje = null;
		if(consultaHistorico.getMensaje()!=null && consultaHistorico.getMensaje().getIdMensaje()!=null && !("").equals(consultaHistorico.getMensaje().getIdMensaje())) {
			idMensaje=Integer.parseInt(consultaHistorico.getMensaje().getIdMensaje());
		}
		
		String idExterno = null;
		if(consultaHistorico.getMensaje()!=null  && consultaHistorico.getMensaje().getIdExterno()!=null && !("").equals(consultaHistorico.getMensaje().getIdExterno())) {
			idExterno=consultaHistorico.getMensaje().getIdExterno();
		}
		
		return this.consultarHistorial(idMensaje, idExterno, consultaHistorico.getUsuario(), consultaHistorico.getPassword());
	}
	
	public String consultarEstadoAEAT(Integer servicioId, Integer idMensaje, String idExterno, String usuario, String password, String sender, String recipient, String statusText) {
	logger.debug("[consultarEstadoAEAT] Consultando estado de ServicioID "+ servicioId+ ", MensajeID "+idMensaje);
	String xmlResultado = "";
	SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
	ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
	logger.debug("[consultarEstadoAEAT] Iniciando transaccion");
	dao.beginTransaction();
	Date fDesde = null;
	Date fHasta = null;
	logger.debug("[consultarEstadoAEAT] Antes de consultarEstadoAEAT");
		listaResultados = dao.consultarEstado(servicioId,null,null,null,idMensaje,idExterno,null,fDesde,fHasta,usuario,password);
		logger.debug("[consultarEstadoAEAT] Despues de consultarEstado");
		PeticionNotificacionEstadoSMS peticionNotificacion = new PeticionNotificacionEstadoSMS();

		try {
			logger.debug("[consultarEstadoAEAT] Generando XML de respuesta");

			xmlResultado = peticionNotificacion.toXMLEstado(listaResultados,sender,recipient, statusText);
			logger.trace(xmlResultado);
			logger.debug("[consultarEstadoAEAT] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			logger.error("[consultarEstadoAEAT] Error al invocar a toXMLEstado ",e);
		}
	dao.endTransaction(false);
	dao.closeAll();
	return xmlResultado;
}
	
}
