package es.minhap.plataformamensajeria.iop.services.seguimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionNotificacionEstadoSMS;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean.Filtro;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Registro;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Respuesta;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.ViewGestionEnviosDetalladaManager;
import es.minhap.plataformamensajeria.iop.manager.ViewHistoricoManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.ViewGestionenviosDetallada;
import es.minhap.sim.model.ViewHistorico;
import es.minhap.sim.model.ViewHistoricoMultidest;
import es.minhap.sim.query.ViewGestionenviosDetalladaQuery;
import es.minhap.sim.query.ViewHistoricoMultidestQuery;
import es.minhap.sim.query.ViewHistoricoQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("seguimientoMensajesImpl")
public class SeguimientoMensajesImpl implements ISeguimientoMensajesService {

	private static final Logger LOG = LoggerFactory.getLogger(SeguimientoMensajesImpl.class);

	@Resource
	private TblLotesEnviosManager lotesManager;

	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;

	@Resource
	private ViewGestionEnviosDetalladaManager viewGestionEnviosDetalladaManager;

	@Resource
	private ViewHistoricoManager viewHistoricoManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public String consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId, Integer loteId,
			Integer idMensaje, String idExterno, Integer estadoId, String fechaDesde, String fechaHasta,
			String usuario, String password) {

		LOG.debug("[ConsultarEstado] Consultando estado de ServicioID " + servicioId + ", MensajeID " + idMensaje);
		String xmlResultado = "";
		List<ViewGestionenviosDetallada> listado;
		ArrayList<SeguimientoMensaje> listaResultados;
		LOG.debug("[ConsultarEstado] Iniciando transaccion");

		Date fDesde = null;
		Date fHasta = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (fechaDesde != null && !fechaDesde.isEmpty()) {
				fDesde = sdf.parse(fechaDesde);
			}
		} catch (ParseException e) {
			fDesde = null;
		}
		try {
			if (fechaHasta != null && !fechaHasta.isEmpty()) {
				fHasta = sdf.parse(fechaHasta);
			}
		} catch (ParseException e) {
			fHasta = null;
		}
		try {
			LOG.debug("[ConsultarEstado] Antes de consultarEstado");
			listado = viewGestionEnviosDetalladaManager.getEstadosFiltroMensaje(getFiltro(servicioId, canalId,
					aplicacionId, loteId, idMensaje, idExterno, estadoId, fDesde, fHasta, usuario, password));
			listaResultados = getListaResultados(listado);
			LOG.debug("[ConsultarEstado] Despues de consultarEstado");
			Respuesta respuesta = new Respuesta();

			xmlResultado = respuesta.toXMLEstado(listaResultados);
			LOG.trace(xmlResultado);
			LOG.debug("[ConsultarEstado] XML de respuesta generado");
		} catch (PlataformaBusinessException pbe) {
			LOG.error("[ConsultarEstado] Generando XML de respuesta", pbe);
		}

		return xmlResultado;
	}

	private ArrayList<SeguimientoMensaje> getListaResultados(List<ViewGestionenviosDetallada> listado) {
		ArrayList<SeguimientoMensaje> listaMensajes = new ArrayList<>();
		for (ViewGestionenviosDetallada v : listado) {
			SeguimientoMensaje mens = getMensaje(v);
			listaMensajes.add(mens);
		}
		return listaMensajes;
	}

	private SeguimientoMensaje getMensaje(ViewGestionenviosDetallada v) {
		SeguimientoMensaje mens = new SeguimientoMensaje();
		mens.setIdServicio((null == v.getServicioid()) ? 0 : v.getServicioid().intValue());
		mens.setServicio((null == v.getServicio()) ? null : v.getServicio());
		mens.setIdCanal((null == v.getCanalid()) ? 0 : v.getCanalid().intValue());
		mens.setCanal((null == v.getCanal()) ? null : v.getCanal());
		mens.setIdAplicacion((null == v.getAplicacionid()) ? 0 : v.getAplicacionid().intValue());
		mens.setAplicacion((null == v.getAplicacion()) ? null : v.getAplicacion());
		mens.setIdLote((null == v.getLoteenvioid()) ? 0 : v.getLoteenvioid().intValue());
		mens.setIdMensaje((null == v.getMensajeid()) ? 0 : v.getMensajeid().intValue());
		mens.setIdExterno((null == v.getIdexterno()) ? null : v.getIdexterno());
		mens.setEstado((null == v.getEstado()) ? null : v.getEstado());
		mens.setReintentos((null == v.getNumeroenvios()) ? 0 : v.getNumeroenvios());

		mens.setFecha((null == v.getUltimoenvio()) ? null : toXMLGregorianCalendar(v.getUltimoenvio()));
		return mens;
	}

	private ViewGestionenviosDetalladaQuery getFiltro(Integer servicioId, Integer canalId, Integer aplicacionId,
			Integer loteId, Integer idMensaje, String idExterno, Integer estadoId, Date fDesde, Date fHasta,
			String usuario, String password) {
		ViewGestionenviosDetalladaQuery query = new ViewGestionenviosDetalladaQuery();
		query.setAplicacionid((null != aplicacionId) ? aplicacionId.longValue() : null);
		query.setCanalid((null != canalId) ? canalId.longValue() : null);
		query.setServicioid((null != servicioId) ? servicioId.longValue() : null);
		query.setEstadoid((null != estadoId) ? estadoId.longValue() : null);
		query.setLoteenvioid((null != loteId) ? loteId.longValue() : null);
		query.setMensajeid((null != idMensaje) ? idMensaje.longValue() : null);
		query.setIdexternoComparator(TextComparator.EQUALS);
		query.setIdexterno((null != idExterno) ? idExterno : null);
		query.setUltimoenvioMin((null != fDesde) ? fDesde : null);
		query.setUltimoenvioMax((null != fHasta) ? fHasta : null);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.addOrder("ultimoenvio", OrderType.ASC);

		return query;
	}

	

	@Override
	public String consultarEstado(ConsultaEstadoBean consultaEstado) {

		String xmlResultado;
		int idServicio = 0;
		int idCanal = 0;
		int idAplicacion = 0;
		int idLote = 0;
		int idMensaje = 0;
		int idEstado = 0;

		try {
			if (consultaEstado.getIdServicio() != null && !consultaEstado.getIdServicio().isEmpty()) {
				idServicio = Integer.parseInt(consultaEstado.getIdServicio());
			}
		} catch (NumberFormatException e) {
			idServicio = 0;
		}
		try {
			if (consultaEstado.getCanal() != null && !consultaEstado.getCanal().isEmpty()) {
				idCanal = Integer.parseInt(consultaEstado.getCanal());
			}
		} catch (NumberFormatException e) {
			idCanal = 0;
		}
		try {
			if (consultaEstado.getIdAplicacion() != null && !consultaEstado.getIdAplicacion().isEmpty()) {
				idAplicacion = Integer.parseInt(consultaEstado.getIdAplicacion());
			}
		} catch (NumberFormatException e) {
			idAplicacion = 0;
		}
		try {
			if (consultaEstado.getLote() != null && !consultaEstado.getLote().isEmpty()) {
				idLote = Integer.parseInt(consultaEstado.getLote());
			}
		} catch (NumberFormatException e) {
			idLote = 0;
		}
		try {
			if (consultaEstado.getIdMensaje() != null && !consultaEstado.getIdMensaje().isEmpty()) {
				idMensaje = Integer.parseInt(consultaEstado.getIdMensaje());
			}
		} catch (NumberFormatException e) {
			idMensaje = 0;
		}
		try {
			if (consultaEstado.getEstado() != null && !consultaEstado.getEstado().isEmpty()) {
				idEstado = Integer.parseInt(consultaEstado.getEstado());
			}
		} catch (NumberFormatException e) {
			idEstado = 0;
		}

		LOG.debug("[ConsultarEstado] Antes de consultarEstado");

		xmlResultado = this.consultarEstado(idServicio, idCanal, idAplicacion, idLote, idMensaje,
				consultaEstado.getIdExterno(), idEstado, consultaEstado.getFechaDesde(),
				consultaEstado.getFechaHasta(), consultaEstado.getUsuario(), consultaEstado.getPassword());
		LOG.debug("[ConsultarEstado] Despues de consultarEstado");

		return xmlResultado;
	}

	@Override
	public String consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password) {
		LOG.debug("[ConsultarHistorial] Consultando Historial del mensaje con id" + idMensaje);

		String xmlResultado = "";
		List<ViewHistorico> listado;
		List<ViewHistoricoMultidest> listadoMultidest;
		ArrayList<Registro> listaResultados;
		Respuesta respuesta = null;
		LOG.debug("[ConsultarHistorial] Antes de consultarHistorial");
		try {

			respuesta = new Respuesta();
			listado = viewHistoricoManager.getHistorico(getFiltroHistorico(idMensaje, idExterno, usuario, password));
			listaResultados = getListaResultadosHistorico(listado);
			listadoMultidest = viewHistoricoManager.getHistoricoMultidest(getFiltroHistoricoMultidest(idMensaje,
					idExterno, usuario, password));
			listaResultados = getListaResultadosHistoricoMultidest(listadoMultidest);

			LOG.debug("[ConsultarHistorial] Despues de consultarHistorial");
			xmlResultado = respuesta.toXMLHistorial(listaResultados);

			LOG.debug("[ConsultarHistorial] Generando XML de respuesta");

			LOG.trace(xmlResultado);
			LOG.debug("[ConsultarHistorial] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			LOG.error("[ConsultarHistorial] Generando XML de respuesta");
		}

		return xmlResultado;
	}
	

	private ArrayList<Registro> getListaResultadosHistoricoMultidest(List<ViewHistoricoMultidest> listadoMultidest) {
		ArrayList<Registro> listaRegistros = new ArrayList<>();
		if (null != listadoMultidest) {
			for (ViewHistoricoMultidest v : listadoMultidest) {
				Registro reg = new Registro();
				reg.setIdMensaje((null == v.getMensajeid()) ? 0 : v.getMensajeid().intValue());
				reg.setEstado((null == v.getEstado()) ? null : v.getEstado());
				reg.setFecha((null == v.getFecha()) ? null : toXMLGregorianCalendar(v.getFecha()));
				reg.setIdExterno((null == v.getCodigoexterno()) ? null : v.getCodigoexterno());
				reg.setIdServidor((null == v.getServidorid()) ? 0 : v.getServidorid().intValue());
				reg.setServidor((null == v.getServidor()) ? null : v.getServidor());

				listaRegistros.add(reg);
			}
		}
		return listaRegistros;
	}

	private ArrayList<Registro> getListaResultadosHistorico(List<ViewHistorico> listado) {
		ArrayList<Registro> listaRegistros = new ArrayList<>();
		if (null != listado) {
			for (ViewHistorico v : listado) {
				Registro reg = new Registro();
				reg.setIdMensaje((null == v.getMensajeid()) ? 0 : v.getMensajeid().intValue());
				reg.setEstado((null == v.getEstado()) ? null : v.getEstado());
				reg.setFecha((null == v.getFecha()) ? null : toXMLGregorianCalendar(v.getFecha()));
				reg.setIdExterno((null == v.getCodigoexterno()) ? null : v.getCodigoexterno());
				reg.setIdServidor((null == v.getServidorid()) ? 0 : v.getServidorid().intValue());
				reg.setServidor((null == v.getServidor()) ? null : v.getServidor());

				listaRegistros.add(reg);
			}
		}
		return listaRegistros;
	}

	private ViewHistoricoQuery getFiltroHistorico(Integer idMensaje, String idExterno, String usuario, String password) {
		ViewHistoricoQuery query = new ViewHistoricoQuery();
		query.setMensajeid((null == idMensaje) ? null : idMensaje.longValue());
		query.setCodigoexternoComparator(TextComparator.EQUALS);
		query.setCodigoexterno(idExterno);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.addOrder("fecha", OrderType.DESC);
		return query;
	}

	private ViewHistoricoMultidestQuery getFiltroHistoricoMultidest(Integer idMensaje, String idExterno,
			String usuario, String password) {
		ViewHistoricoMultidestQuery query = new ViewHistoricoMultidestQuery();
		query.setMensajeid((null == idMensaje) ? null : idMensaje.longValue());
		query.setCodigoexternoComparator(TextComparator.EQUALS);
		query.setCodigoexterno(idExterno);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.addOrder("fecha", OrderType.DESC);
		return query;
	}

	@Override
	public String consultarEstado(ConsultaEstadoXMLBean consultaEstado) {
		String xmlResultado = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		try {
		if (!comprobarPeticionConsultaEstado(consultaEstado.getUsuario(), consultaEstado.getPassword(), consultaEstado.getFiltro())) {
			Respuesta respuesta = new Respuesta();
			String statusCode = ps.getMessage("plataformaErrores.seguimientoMensajes.STATUSCODE_KO", null);
			String statusText = ps.getMessage("plataformaErrores.seguimientoMensajes.STATUSTEXT_KO", null);
			String detailsUsuario = ps.getMessage("plataformaErrores.seguimientoMensajes.DETAILSUSUARIOFILTRO", null);
			ResponseStatusType status = new ResponseStatusType();
			status.setDetails(detailsUsuario);
			status.setStatusCode(statusCode);
			status.setStatusText(statusText);
			respuesta.setStatus(status);
			
			xmlResultado = respuesta.toXML(respuesta);
			
		} else {
			Integer idServicio = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getIdServicio() != null
					&& !("").equals(consultaEstado.getFiltro().getIdServicio())) {
				idServicio = Integer.parseInt(consultaEstado.getFiltro().getIdServicio());
			}

			Integer idCanal = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getIdCanal() != null
					&& !("").equals(consultaEstado.getFiltro().getIdCanal())) {
				idCanal = Integer.parseInt(consultaEstado.getFiltro().getIdCanal());
			}

			Integer idAplicacion = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getIdAplicacion() != null
					&& !("").equals(consultaEstado.getFiltro().getIdAplicacion())) {
				idAplicacion = Integer.parseInt(consultaEstado.getFiltro().getIdAplicacion());
			}

			Integer idLote = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getIdLote() != null
					&& !("").equals(consultaEstado.getFiltro().getIdLote())) {
				idLote = Integer.parseInt(consultaEstado.getFiltro().getIdLote());
			}

			Integer idEstado = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getIdEstado() != null
					&& !("").equals(consultaEstado.getFiltro().getIdEstado())) {
				idEstado = Integer.parseInt(consultaEstado.getFiltro().getIdEstado());
			}

			Integer idMensaje = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getMensaje() != null
					&& consultaEstado.getFiltro().getMensaje().getIdMensaje() != null
					&& !("").equals(consultaEstado.getFiltro().getMensaje().getIdMensaje())) {
				idMensaje = Integer.parseInt(consultaEstado.getFiltro().getMensaje().getIdMensaje());
			}

			String idExterno = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getMensaje() != null
					&& !("").equals(consultaEstado.getFiltro().getMensaje().getIdExterno())) {
				idExterno = consultaEstado.getFiltro().getMensaje().getIdExterno();
			}

			String fechaDesde = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getFechaDesde() != null
					&& !("").equals(consultaEstado.getFiltro().getFechaDesde())) {
				fechaDesde = consultaEstado.getFiltro().getFechaDesde();
			}

			String fechaHasta = null;
			if (consultaEstado.getFiltro() != null && consultaEstado.getFiltro().getFechaHasta() != null
					&& !("").equals(consultaEstado.getFiltro().getFechaHasta())) {
				fechaHasta = consultaEstado.getFiltro().getFechaHasta();
			}

			return this.consultarEstado(idServicio, idCanal, idAplicacion, idLote, idMensaje, idExterno, idEstado,
					fechaDesde, fechaHasta, consultaEstado.getUsuario(), consultaEstado.getPassword());
		}
		} catch (PlataformaBusinessException pbe) {
			LOG.error("[ConsultarEstado] Generando XML de respuesta", pbe);
		}
		return xmlResultado;
	}

	@Override
	public String consultarHistorial(ConsultaHistoricoXMLBean consultaHistorico) {
		String xmlResultado = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		try {
			if (!comprobarPeticionConsultaHistorial(consultaHistorico.getUsuario(), consultaHistorico.getPassword(),
					consultaHistorico.getMensaje())) {
				Respuesta respuesta = new Respuesta();
				String statusCode = ps.getMessage("plataformaErrores.seguimientoMensajes.STATUSCODE_KO", null);
				String statusText = ps.getMessage("plataformaErrores.seguimientoMensajes.STATUSTEXT_KO", null);
				String detailsUsuario = ps.getMessage("plataformaErrores.seguimientoMensajes.DETAILSUSUARIO", null);
				ResponseStatusType status = new ResponseStatusType();
				status.setDetails(detailsUsuario);
				status.setStatusCode(statusCode);
				status.setStatusText(statusText);
				respuesta.setStatus(status);

				xmlResultado = respuesta.toXML(respuesta);

			} else {

				Integer idMensaje = null;
				if (consultaHistorico.getMensaje() != null && consultaHistorico.getMensaje().getIdMensaje() != null
						&& !("").equals(consultaHistorico.getMensaje().getIdMensaje())) {
					idMensaje = Integer.parseInt(consultaHistorico.getMensaje().getIdMensaje());
				}

				String idExterno = null;
				if (consultaHistorico.getMensaje() != null && consultaHistorico.getMensaje().getIdExterno() != null
						&& !("").equals(consultaHistorico.getMensaje().getIdExterno())) {
					idExterno = consultaHistorico.getMensaje().getIdExterno();
				}

				return this.consultarHistorial(idMensaje, idExterno, consultaHistorico.getUsuario(),
						consultaHistorico.getPassword());
			}
		} catch (PlataformaBusinessException pbe) {
			LOG.error("[ConsultarEstado] Generando XML de respuesta", pbe);
		}
		return xmlResultado;

	}

	@Override
	public String consultarEstadoAEAT(Integer servicioId, Integer idMensaje, String idExterno, String usuario,
			String password, String sender, String recipient, String statusText) {
		LOG.debug("[consultarEstadoAEAT] Consultando estado de ServicioID " + servicioId + ", MensajeID " + idMensaje);
		String xmlResultado = "";

		List<ViewGestionenviosDetallada> listado;
		ArrayList<SeguimientoMensaje> listaResultados;
		LOG.debug("[consultarEstadoAEAT] Iniciando transaccion");

		LOG.debug("[consultarEstadoAEAT] Antes de consultarEstadoAEAT");
		listado = viewGestionEnviosDetalladaManager.getEstadosFiltroMensaje(getFiltro(servicioId, null, null, null,
				idMensaje, idExterno, null, null, null, usuario, password));
		listaResultados = getListaResultados(listado);

		LOG.debug("[consultarEstadoAEAT] Despues de consultarEstado");
		PeticionNotificacionEstadoSMS peticionNotificacion = new PeticionNotificacionEstadoSMS();

		try {
			LOG.debug("[consultarEstadoAEAT] Generando XML de respuesta");

			xmlResultado = peticionNotificacion.toXMLEstado(listaResultados, sender, recipient, statusText);
			LOG.trace(xmlResultado);
			LOG.debug("[consultarEstadoAEAT] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			LOG.error("[consultarEstadoAEAT] Error al invocar a toXMLEstado ", e);
		}

		return xmlResultado;
	}

	private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			LOG.error("[XMLGregorianCalendar] Error al crear SMLGregorianCalendar ", ex);
		}
		return xmlCalendar;
	}
	
	private boolean comprobarPeticionConsultaEstado(String usuario, String password, Filtro filtro) {
		
		return ((null == usuario || usuario.length() < 1 || null == password || password.length() < 1 || null == filtro))? false : true;
			
	}
	
	private boolean comprobarPeticionConsultaHistorial(String usuario, String password, ConsultaHistoricoXMLBean.Mensaje mensaje) {
		return ((null == usuario || usuario.length() < 1) || (null == password || password.length() < 1) || (null == mensaje))? false : true;
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
	 * @return the viewGestionEnviosDetalladaManager
	 */
	public ViewGestionEnviosDetalladaManager getViewGestionEnviosDetalladaManager() {
		return viewGestionEnviosDetalladaManager;
	}

	/**
	 * @param viewGestionEnviosDetalladaManager
	 *            the viewGestionEnviosDetalladaManager to set
	 */
	public void setViewGestionEnviosDetalladaManager(ViewGestionEnviosDetalladaManager viewGestionEnviosDetalladaManager) {
		this.viewGestionEnviosDetalladaManager = viewGestionEnviosDetalladaManager;
	}

	/**
	 * @return the viewHistoricoManager
	 */
	public ViewHistoricoManager getViewHistoricoManager() {
		return viewHistoricoManager;
	}

	/**
	 * @param viewHistoricoManager
	 *            the viewHistoricoManager to set
	 */
	public void setViewHistoricoManager(ViewHistoricoManager viewHistoricoManager) {
		this.viewHistoricoManager = viewHistoricoManager;
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

}
