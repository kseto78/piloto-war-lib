package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.RecepcionSMSBean;
import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblAuditoriaManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetalladaManager;
import es.minhap.plataformamensajeria.iop.manager.ViewServiciosManager;
import es.minhap.plataformamensajeria.iop.util.MensajesAuditoria;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblLotesEnviosDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblLotesEnvios;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblLotesEnviosQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresQuery;
import es.minhap.sim.query.TblServidoresServiciosQuery;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("TblLotesEnviosManagerImpl")
public class TblLotesEnviosManagerImpl implements TblLotesEnviosManager {

	private static final Logger LOG = LoggerFactory.getLogger(TblLotesEnviosManagerImpl.class);

	private static final boolean GESTION_MULTIDESTINATARIOS = true;

	@Resource
	TblLotesEnviosDAO lotesDAO;

	@Autowired
	TblAplicacionesManager aplicacionesManager;

	@Autowired
	TblServiciosManager serviciosManager;

	@Autowired
	TblMensajesManager mensajeManager;

	@Autowired
	TblAuditoriaManager auditoriaManager;

	@Autowired
	TblEstadosManager estadosManager;

	@Autowired
	QueryExecutorServicios queryExecutorServicios;
	
	@Autowired
	QueryExecutorServidores queryExecutorServidores;

	@Autowired
	ViewServiciosManager viewServiciosManager;

	@Autowired
	TblServidoresManager tblServidoresManager;

	@Autowired
	TblServidoresServiciosManager tblServidoresServiciosManager;
	
	@Autowired
	TblDestinatariosMensajesManager destinatariosMensajesManager;

	@Autowired
	ViewLotesEnviosDetalladaManager viewLotesManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Resource(name = "messageSender")
	private SIMMessageSender sender;


	Long servicioId = null;

	@Override
	public Integer insertarLote(Long servicioId, String nombreLote, String usuario, String password) {
		Integer res;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String operacionCrearLote = ps.getMessage("mensajesAuditoria.lotes.OPERACION_LOTE_CREAR", null);
		String operacionLoteCorrecto = ps.getMessage("mensajesAuditoria.lotes.OPERACION_LOTE_CORRECTO", null);
		String errorUsuarioAplicacion = ps.getMessage("auditoria.errores.ERROR_USUARIO_APLICACION", null);
		Long codErrorUsuarioAplicacion = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_USUARIO_APLICACION",
				null));

		List<TblAplicaciones> listaAplicaciones = getAplicaciones(usuario, password);
		Integer out = (null != listaAplicaciones) ? listaAplicaciones.size() : null;

		// Auditamos con error -1
		if (null == out || out.intValue() != 1) {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, codErrorUsuarioAplicacion, errorUsuarioAplicacion);
			auditoriaManager.insertarAuditoria(auditoria);
			return codErrorUsuarioAplicacion.intValue();
		}

		// Se comprueba que existe el servicio y esta activo
		TblServiciosQuery servicio = new TblServiciosQuery();
		servicio.setServicioid(servicioId);
		servicio.addTblAplicacionesIdIn(listaAplicaciones.get(0));
		List<TblServicios> listaServicios = serviciosManager.getServicios(servicio);
		out = (null != listaServicios) ? listaServicios.size() : null;

		// Auditamos con error -2
		if (null == out || out.intValue() != 1) {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, MensajesAuditoria.COD_ERROR_SERVICIO_INCORRECTO,
					MensajesAuditoria.ERROR_SERVICIO_INCORRECTO);
			auditoriaManager.insertarAuditoria(auditoria);
			return MensajesAuditoria.COD_ERROR_SERVICIO_INCORRECTO.intValue();
		}

		// Se comprueba que el servicio esté activo
		servicio.setActivo(true);
		List<TblServicios> listaServiciosActivos = serviciosManager.getServicios(servicio);
		out = (null != listaServiciosActivos) ? listaServiciosActivos.size() : null;

		// Auditamos con error -3
		if (null == out || out.intValue() != 1) {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, MensajesAuditoria.COD_ERROR_SERVICIO_INACTIVO,
					MensajesAuditoria.ERROR_SERVICIO_INACTIVO);
			auditoriaManager.insertarAuditoria(auditoria);
			return MensajesAuditoria.COD_ERROR_SERVICIO_INACTIVO.intValue();
		}

		TblLotesEnvios lote = crearLote(listaServiciosActivos.get(0), nombreLote, usuario, GESTION_MULTIDESTINATARIOS);
		res = lotesDAO.insert(lote).intValue();

		if (null != res) {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, Long.valueOf(res), operacionLoteCorrecto);
			auditoriaManager.insertarAuditoria(auditoria);

		} else {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, MensajesAuditoria.COD_ERROR_BBDD, operacionLoteCorrecto);
			auditoriaManager.insertarAuditoria(auditoria);
			res = MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}

		return res;
	}

	@Override
	public Integer insertarLotePremium(TblServicios servicio, String nombreLote, String usuario, String password) {
		Integer res;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String operacionCrearLote = ps.getMessage("mensajesAuditoria.lotes.OPERACION_LOTE_CREAR", null);
		String operacionLoteCorrecto = ps.getMessage("mensajesAuditoria.lotes.OPERACION_LOTE_CORRECTO", null);

		TblLotesEnvios lote = crearLote(servicio, nombreLote, usuario, GESTION_MULTIDESTINATARIOS);
		res = lotesDAO.insert(lote).intValue();

		if (null != res) {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, Long.valueOf(res), operacionLoteCorrecto);
			auditoriaManager.insertarAuditoria(auditoria);

		} else {
			AuditoriaBean auditoria = new AuditoriaBean(operacionCrearLote, new Date(), null, null, servicioId, null,
					usuario, password, MensajesAuditoria.COD_ERROR_BBDD, operacionLoteCorrecto);
			auditoriaManager.insertarAuditoria(auditoria);
			res = MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}

		return res;
	}
	
	@Override
	public TblLotesEnvios getLoteEnvioById(Long idLote) {
		return lotesDAO.get(idLote);
	}

	@Override
	public RecepcionSMSBean buscarInfoLote(String recipient, String usuario, String password, String prefijoSMS) {
		RecepcionSMSBean res = new RecepcionSMSBean();
		Long s = null;
		// comprobamos si el servicio es �nico
		comprobarServicio(recipient, res, prefijoSMS);

		// obtenemos el servicio y la aplicaci�n
		try {
			s = Long.parseLong(res.getServicio());
		} catch (NumberFormatException e) {
			return res;
		}
		TblServicios serv = serviciosManager.getServicio(s);

		if (null != serv) {
			Long aplicacionId = serviciosManager.getServicio(s).getTblAplicaciones().getAplicacionid();
			validarUserPassOperadora(usuario, password, res, prefijoSMS);

			if (Integer.parseInt(res.getServicio()) >= 0) {
				res.setNombreLote(serv.getNombreloteenvio());
				TblAplicacionesQuery am = new TblAplicacionesQuery();
				am.setAplicacionid(aplicacionId);
				am.setActivo(true);
				TblAplicaciones ap = aplicacionesManager.getAplicacion(am);
				res.setUserAplicacion(ap.getUsuario());
				res.setPasswordAplicacion(Utils.decode64(ap.getPassword()));
			}
		}
		return res;
	}

	private void validarUserPassOperadora(String usuario, String password, RecepcionSMSBean res, String prefijoSMS) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		TblServidoresQuery query = new TblServidoresQuery();
		query.setActivo(true);
		query.setUsuario(usuario);
		query.setPassword(password);
		query.setTipo(Integer.parseInt(ps.getMessage("constantes.CANAL_RECEPCION_SMS", null)));
		Integer count = tblServidoresManager.countServidor(query);
		if (count != 1)
			res.setServicio(MensajesAuditoria.ERROR_MAS_DE_UN_SERVIDOR);

		query.setTipo(null);

		TblServidores servidor = tblServidoresManager.getServidor(query);

		TblServidoresServiciosQuery ssq = new TblServidoresServiciosQuery();
/////////aki es la modificación si se permite más de un header en el servicio.
		ssq.addTblServiciosIdIn(serviciosManager.getServicio(Long.parseLong(res.getServicio())));
		ssq.addTblServidoresIdIn(servidor);
		ssq.setPrefijosms(prefijoSMS);
		ssq.setPrefijosmsComparator(TextComparator.EQUALS);
		
		count = tblServidoresServiciosManager.countServidoresServicios(ssq);

		if (count != 1)
			res.setServicio(MensajesAuditoria.ERROR_NO_EXISTE_RELACION_SERVIDOR_SERVICIO);
	}

	
	private void comprobarServicio(String recipient, RecepcionSMSBean res, String prefijoSMS) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		List<Long> lista = queryExecutorServicios.comprobarServicioUnico(recipient,
				Long.parseLong(ps.getMessage("constantes.CANAL_RECEPCION_SMS", null)), prefijoSMS);
		switch (lista.size()) {
		case 0:
			res.setServicio(MensajesAuditoria.COMPROBAR_SERVICIO_SERVICIO_INCORRECTO);
			break;
		case 1:
			res.setServicio(String.valueOf(lista.get(0)));
			break;
		default:
			res.setServicio(MensajesAuditoria.COMPROBAR_SERVICIO_SERVICIO_DUPLICADO);
			break;
		}
	}

	private TblLotesEnvios crearLote(TblServicios servicio, String nombreLote, String usuario,
			boolean gestionMultidestinatarios) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		TblLotesEnvios res = new TblLotesEnvios();

		res.setCreadopor(usuario);
		res.setModificadopor(usuario);
		res.setNombre(nombreLote);
		res.setTblServicios(servicio);
		res.setFechacreacion(new Date());
		res.setFechamodificacion(new Date());
		res.setDescripcion(nombreLote);
		res.setEstadoenvioid(Long.parseLong(ps.getMessage("constantes.ID_ESTADO_PENDIENTE", null,"3")));
		res.setMultidestinatario(gestionMultidestinatarios);

		return res;
	}

	
	@Override
	public void update(TblLotesEnvios lote) {
		lotesDAO.update(lote);
	}

	@Override
	public Boolean isMultidestinatario(Long mensajeId) {
		TblMensajes mensaje = mensajeManager.getMensaje(mensajeId);
		return (null != mensaje.getTblLotesEnvios().getMultidestinatario()) ? mensaje.getTblLotesEnvios()
				.getMultidestinatario() : false;
	}

	@Override
	public Integer operacionesLotes(Long idLote, String usuario, String password, String estadoFinal) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String operacion;
		String operacionCorrecto;
		
		if (ps.getMessage("constantes.ESTADO_PENDIENTE", null).equals(estadoFinal)){
			operacion = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_REENVIO_LOTE", null);
			operacionCorrecto = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_REENVIO_LOTE_CORRECTO",	null);
		}else{
			operacion = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE", null);
			operacionCorrecto = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE_CORRECTO",	null);
		}
		String errorLoteIncorrecto = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_LOTE_INCORRECTO", null);
		Long codErrorLoteIncorrecto = Long.parseLong(ps.getMessage(
				"auditoria.errores.COD_ERROR_LOTE_INCORRECTO", null));
		String errorUsuarioAplicacion = ps.getMessage("auditoria.errores.ERROR_USUARIO_APLICACION", null);
		Long codErrorUsuarioAplicacion = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_USUARIO_APLICACION",
				null));
		String errorServicioIncorrecto = ps.getMessage(
				"auditoria.errores.ERROR_SERVICIO_INCORRECTO_OPERACIONES", null);
		Long codErrorServicioIncorrecto = Long.parseLong(ps.getMessage(
				"auditoria.errores.COD_ERROR_SERVICIO_INCORRECTO_OPERACIONES", null));
		String errorMensajeYaEnviado = ps.getMessage("auditoria.errores.ERROR_MENSAJE_YA_ENVIADO", null);
		Long codErrorMensajeYaEnviado = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_MENSAJE_YA_ENVIADO",
				null));
		
		String errorBBDD = ps.getMessage("auditoria.errores.ERROR_BBDD", null);
		Long codErrorBBDD = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_BBDD", null));
		Long codCorrecto = Long.parseLong(ps.getMessage("auditoria.errores.COD_CORRECTO", null));
		String estadoEnviado = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		try {
			// Se comprueba que la aplicacion pertenece al usuario
			List<TblAplicaciones> listaAplicaciones = getAplicaciones(usuario, password);
			Integer out = (null != listaAplicaciones) ? listaAplicaciones.size() : 0;

			// Auditamos con error -1
			if (out.intValue() != 1) {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), idLote, null, null, null,
						usuario, password, codErrorUsuarioAplicacion, errorUsuarioAplicacion);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorUsuarioAplicacion.intValue();
			}

			// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
			Integer count = comprobarLote(idLote, usuario, password);

			// Auditamos con error -2
			if (count <= 0) {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), idLote, null, null, null,
						usuario, password, codErrorUsuarioAplicacion, errorLoteIncorrecto);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorLoteIncorrecto.intValue();
			}

			// Se comprueba que existe el servicio y está activo
			TblServicios serv = serviciosManager.getServicio(servicioId);

			// Auditamos con error -3
			if (null == serv || !serv.getActivo()) {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, servicioId,
						null, usuario, password, codErrorServicioIncorrecto, errorServicioIncorrecto);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorServicioIncorrecto.intValue();
			}

			// Se comprueba si el lote ha sido enviado
			TblLotesEnvios lote = lotesDAO.get(idLote);
			if (lote.getEstadoenvioid().equals(estadosManager.getEstadoByName(estadoEnviado).getEstadoid())) {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), idLote, null, null, null,
						usuario, password, codErrorMensajeYaEnviado, errorMensajeYaEnviado);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorMensajeYaEnviado.intValue();
			}

			if (setEstadoLote(idLote, estadoFinal, null, false, usuario) > 0) {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), idLote, null, null, null,
						usuario, password, codCorrecto, operacionCorrecto);
				auditoriaManager.insertarAuditoria(auditoria);
				return codCorrecto.intValue();
			} else {
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), idLote, null, null, null,
						usuario, password, codErrorBBDD, errorBBDD);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorBBDD.intValue();
			}
		} catch (Exception e) {
			LOG.error(ps.getMessage("auditoria.errores.ERROR_GENERAL", null), e);
			return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
		}

	}

	private List<TblAplicaciones> getAplicaciones(String usuario, String password) {
		TblAplicacionesQuery q = new TblAplicacionesQuery();
		q.setUsuario(usuario);
		q.setPassword(Utils.encode64(password));
		q.setActivo(true);

		List<TblAplicaciones> listaAplicaciones = aplicacionesManager.getAplicaciones(q);
		return listaAplicaciones;
	}

	/**
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @param servicioId
	 * @return
	 */
	private Integer comprobarLote(Long idLote, String usuario, String password) {
		ViewLotesEnviosDetalladaQuery query = new ViewLotesEnviosDetalladaQuery();
		query.setLoteenvioid(idLote);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password.trim()));
		servicioId = viewLotesManager.getViewLoteDetallado(query).getServicioid();
		return viewLotesManager.countViewLoteDetallado(query);
	}

	@Override
	public Integer setEstadoLote(Long idLote, String estado, String descripcion, Boolean controlReintentos,
			String usuario) {

		Integer res = 1;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
		String estadoAnulado = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_ANULADO", null)).getNombre();
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		List<TblMensajes> listaMensajes = null;
		try {
			
			//buscamos los mensajes del lote que no estan enviados
			listaMensajes = mensajeManager.getMensajesByLote(idLote);
			
			//actualizamos todos los mensajes al estado oportuno
			for (TblMensajes tblMensaje : listaMensajes) {
				mensajeManager.setEstadoMensaje(tblMensaje.getMensajeid(), estado, descripcion, null, null, null, usuario, null);
				
				if (!estado.equalsIgnoreCase(estadoAnulado)) {
					List<TblDestinatariosMensajes> destinatarios = destinatariosMensajesManager.getDestinatarioMensajes(tblMensaje.getMensajeid());
					Long canal = Long.parseLong(ps.getMessage("constantes.CANAL_EMAIL", null));
					Integer modo = Integer.parseInt(ps.getMessage("constantes.email.modo", null));
					if (tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().equals(canal)) {
						checkEmailMode(tblMensaje.getMensajeid(), tblMensaje, destinatarios, modo);
					} else {
						sendMessages(tblMensaje.getMensajeid(), tblMensaje, destinatarios);
					}				
				}
			}
			
		} catch (CannotCreateTransactionException e) {
			LOG.error(errorActiveMq+" TblMensajesManagerImpl.operacionMensaje --Error ActiveMq--", e);
			TblServicios servicio = serviciosManager
					.getServicio(lotesDAO.get(idLote).getTblServicios().getServicioid());

			if (servicio.getPremium() != null && servicio.getPremium()) { // Es premium anulamos
				for (TblMensajes tblMensaje : listaMensajes) {
					mensajeManager.setEstadoMensaje(tblMensaje.getMensajeid(), estadoAnulado, descripcionErrorActiveMq, false, null,
							null, usuario, null);
				}
				return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
			}else{
				return res;
			}
			
		}catch (Exception e) {
			LOG.error(ps.getMessage("auditoria.errores.ERROR_GENERAL", null), e);
			return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
		}
		return res;
	}
	
	
	@Override
	public List<Long> getIdLotesEnviosByQuery(TblLotesEnviosQuery query) {
		return lotesDAO.searchId(query).getResults();
	}
	
	@Override
	public void delete(Long loteenvioid) {
		lotesDAO.delete(loteenvioid);
		
	}
	
	private void checkEmailMode(Long idMensaje, TblMensajes tblMensaje, List<TblDestinatariosMensajes> destinatarios,
			Integer modo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		if (tblMensaje.getModo().equals(modo)) {
			StringBuilder destBuilder = new StringBuilder();
			for (TblDestinatariosMensajes destinatario : destinatarios) {
				destBuilder.append(destinatario.getDestinatariosmensajes()+";");
			}
			MensajeJMS mensajeJms = new MensajeJMS();
			mensajeJms.setIdMensaje(idMensaje.toString());
//			mensajeJms.setIdExterno(tblMensaje.getCodigoexterno());
			mensajeJms.setDestinatarioMensajeId(destBuilder.toString());
			mensajeJms.setIdLote(tblMensaje.getTblLotesEnvios().getLoteenvioid().toString());
			Long maxRetries = null;
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().toString());
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios().getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
			}
			sender.send(mensajeJms, maxRetries, servicio.getServicioid().toString(), false);
		} else {
			sendMessages(idMensaje, tblMensaje, destinatarios);
		}
	}

	private void sendMessages(Long idMensaje, TblMensajes tblMensaje, List<TblDestinatariosMensajes> destinatarios) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		for (TblDestinatariosMensajes destinatario : destinatarios) {
			MensajeJMS mensajeJms = new MensajeJMS();
			mensajeJms.setIdMensaje(idMensaje.toString());
//			mensajeJms.setIdExterno(tblMensaje.getCodigoexterno());
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().toString());
			mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
			mensajeJms.setIdLote(tblMensaje.getTblLotesEnvios().getLoteenvioid().toString());
			Long maxRetries = null;
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios().getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
			}
			sender.send(mensajeJms, maxRetries, servicio.getServicioid().toString(), false);
		}
	}

	


}
