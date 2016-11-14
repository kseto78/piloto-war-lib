package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajeSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblAuditoriaManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetalladaManager;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.util.MensajesAuditoria;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblMensajesDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblEstados;
import es.minhap.sim.model.TblLotesEnvios;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblLotesEnviosQuery;
import es.minhap.sim.query.TblMensajesQuery;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;
import es.minhap.sim.query.ViewUsuariosPushQuery;

/**
 * Clase manager encargada de la l�gica de negocios relacionada con los
 * mensajes
 * 
 * @author everis
 * 
 */
@Service("TblMensajesManagerImpl")
public class TblMensajesManagerImpl implements TblMensajesManager {

	private static final Logger logger = Logger.getLogger(TblMensajesManagerImpl.class);

	@Autowired
	private TblAplicacionesManager aplicacionesManager;

	@Autowired
	private ViewLotesEnviosDetalladaManager viewLotesManager;

	@Autowired
	private TblAuditoriaManager auditoriaManager;

	@Autowired
	private TblHistoricosManager hitoricosManager;

	@Autowired
	private ViewUsuariosPushManager viewUsiariosPushManager;

	@Autowired
	private TblLotesEnviosManager lotesEnviosManager;

	@Autowired
	private TblEstadosManager estadosManager;

	@Autowired
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Autowired
	private TblUsuariosPushManager usuariosPushManager;

	@Autowired
    private SessionFactory sessionFactorySIMApp;

	@Autowired
	private QueryExecutorServidores queryExecutorServidores;

	@Autowired
	private QueryExecutorLotesEnvios queryExecutorLotesEnvios;
	
	@Autowired
	private QueryExecutorMensajes queryExecutorMensajes;

	@Resource
	private TblMensajesDAO tblMensajesDAO;
	
	@Autowired
	private TblServiciosManager serviciosManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	private ViewLotesEnviosDetalladaQuery query;

	/**
	 * @see es.minhap.TblMensajesManager.insertarMensajeSMS
	 */

	@Override
	public Mensaje insertarMensajeSMS(Long idLote, MensajeSMSXMLBean mensaje, String usuario, String password) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, usuario, password);

		// Auditamos con error -1
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_LOTE_APLICACION,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return generarRespuesta(PlataformaErrores.COD_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_LOTE_APLICACION, mensaje.getIdExterno(), null);
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_SMS", null)));

		// Auditamos con error -2
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_CANAL_MENSAJE,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_CANAL_MENSAJE, mensaje.getIdExterno(), null);

		}

		TblMensajes mens = crearMensaje(idLote, mensaje.getCuerpo(), mensaje.getDocUsuario(), mensaje.getCodSIA(),
				mensaje.getCodOrganismo(), mensaje.getCodOrganismoPagador(), mensaje.getIdExterno(), usuario,
				ps.getMessage("constantes.TIPO_MENSAJE_SMS", null),null);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		if (null != mens.getMensajeid()) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mens.getMensajeid(), usuario, password, MensajesAuditoria.MENSAJE_SMS_CORRECTO,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, mens.getMensajeid());
		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_BBDD);
			return generarRespuesta(MensajesAuditoria.COD_ERROR_BBDD.toString(), PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_BBDD, mensaje.getIdExterno(), null);
		}

		return generarRespuesta(null, null, null, mensaje.getIdExterno(), mens.getMensajeid());

	}

	@Override
	public Integer insertarMensajeRecepcionSMS(Long idLote, String smsText, String messageId, String sender,
			String userAplicacion, String passwordAplicacion) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, userAplicacion, passwordAplicacion);

		// Auditamos con error -1
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, userAplicacion, passwordAplicacion, MensajesAuditoria.ERROR_LOTE_APLICACION,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return MensajesAuditoria.COD_ERROR_LOTE_APLICACION.intValue();
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_RECEPCION_SMS", null)));

		// Auditamos con error -2
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, userAplicacion, passwordAplicacion, MensajesAuditoria.ERROR_CANAL_MENSAJE,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return MensajesAuditoria.COD_ERROR_CANAL_MENSAJE.intValue();
		}

		// COMPROBAR SI EL TELEFONO ES VALIDO
		if (Utils.validarTelefono(sender) == 1) {
			auditarMensaje(idLote, null, userAplicacion, passwordAplicacion,
					MensajesAuditoria.ERROR_DESTINATARIO_MENSAJE, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_DESTINATARIO_MENSAJE);
			return MensajesAuditoria.COD_ERROR_DESTINATARIO_MENSAJE.intValue();
		}

		TblMensajes mens = crearMensaje(idLote, smsText, null, null, null, null, messageId, userAplicacion,
				ps.getMessage("constantes.TIPO_MENSAJE_RECEPCION", null), null);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		return getReturn(mens.getMensajeid(), userAplicacion, passwordAplicacion, idLote);
	}

	private Integer getReturn(Long mensajeid, String userAplicacion, String passwordAplicacion, Long idLote) {

		if (null != mensajeid) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mensajeid, userAplicacion, passwordAplicacion,
					MensajesAuditoria.MENSAJE_SMS_CORRECTO, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, mensajeid);
			return mensajeid.intValue();
		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, userAplicacion, passwordAplicacion, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_BBDD);
			return MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}
	}

	private void auditarMensaje(Long idLote, Long mensajeid, String usuario, String password, String textoMensaje,
			String textoOperacion, Long codigoOperacion) {
		AuditoriaBean auditoria = new AuditoriaBean(textoOperacion, new Date(), idLote, mensajeid, null, null, usuario,
				password, codigoOperacion, textoMensaje);
		auditoriaManager.insertarAuditoria(auditoria);
	}

	@Override
	public Mensaje insertarMensajeEmail(Long idLote, MensajesXMLBean mensaje, EnvioEmailXMLBean envio, String to,
			String cc, String bcc) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, envio.getUsuario(), envio.getPassword());

		// Auditamos con error -1
		if (count <= 0) {
			auditarMensaje(idLote, null, envio.getUsuario(), envio.getPassword(),
					MensajesAuditoria.ERROR_LOTE_APLICACION, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return generarRespuesta(PlataformaErrores.COD_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_LOTE_APLICACION, mensaje.getIdExterno(), null);
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_EMAIL", null)));

		// Auditamos con error -2
		if (count <= 0) {
			auditarMensaje(idLote, null, envio.getUsuario(), envio.getPassword(),
					MensajesAuditoria.ERROR_CANAL_MENSAJE, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_CANAL_MENSAJE, mensaje.getIdExterno(), null);

		}

		// COMPROBAMOS TODOS LOS DESTINATARIOS
		String errorDestinatarios = comprobarDestinatariosEmail(to, cc, bcc);

		// Auditamos con error -3
		if (null != errorDestinatarios) {
			auditarErrorDestinatariosMensaje(idLote, envio.getUsuario(), envio.getPassword(), errorDestinatarios);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					errorDestinatarios, mensaje.getIdExterno(), null);

		}

		// creamos el mensaje
		TblMensajes mens = crearMensajeEmail(idLote, mensaje, envio);

		mens.setMensajeid(tblMensajesDAO.insert(mens));
		if (null != mens.getMensajeid()) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mens.getMensajeid(), envio.getUsuario(), envio.getPassword(),
					MensajesAuditoria.MENSAJE_EMAIL_CORRECTO, MensajesAuditoria.OPERACION_MENSAJE_EMAIL_CREAR,
					mens.getMensajeid());

		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, envio.getUsuario(), envio.getPassword(), MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_MENSAJE_EMAIL_CREAR, MensajesAuditoria.COD_ERROR_BBDD);
			return generarRespuesta(MensajesAuditoria.COD_ERROR_BBDD.toString(), PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_BBDD, mensaje.getIdExterno(), null);
		}

		return generarRespuesta(null, null, null, mensaje.getIdExterno(), mens.getMensajeid());
	}

	private void auditarErrorDestinatariosMensaje(Long idLote, String usuario, String password,
			String errorDestinatarios) {
		AuditoriaBean auditoria = new AuditoriaBean(MensajesAuditoria.OPERACION_MENSAJE_EMAIL_CREAR, new Date(),
				idLote, null, null, null, usuario, password, MensajesAuditoria.COD_ERROR_DESTINATARIOS_EMAIL,
				errorDestinatarios);
		auditoriaManager.insertarAuditoria(auditoria);

	}

	private String comprobarDestinatariosEmail(String to, String cc, String bcc) {
		String res = null;
		List<String> lista = new ArrayList<>();
		// analizamos destinatarios TO
		if (null != to){
			lista = Arrays.asList(to.split(";"));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_TO;
				}
			}
		}
		
		// analizamos destinatarios CC
		if (null != cc){
			lista = Arrays.asList(cc.split(";"));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_CC;
				}
			}
		}
	
		// analizamos destinatarios BCC
		if (null != bcc){
			lista = Arrays.asList(bcc.split(";"));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_BCC;
				}
			}
		}
	
		return res;
	}

	@Override
	public Mensaje insertarMensajePush(Long idLote, MensajePeticionLotesPushXMLBean mensaje,
			EnvioPushXMLBean notificacion, DestinatarioPeticionLotesPushXMLBean destinatario, Integer usuarioId) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, notificacion.getUsuario(), notificacion.getPassword());

		// Auditamos con error -1
		if (count <= 0) {
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPassword(),
					MensajesAuditoria.ERROR_LOTE_APLICACION, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return generarRespuesta(PlataformaErrores.COD_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_LOTE_APLICACION, destinatario.getIdExterno(), null);
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_PUSH", null)));

		// Auditamos con error -2
		if (count <= 0) {
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPassword(),
					MensajesAuditoria.ERROR_CANAL_MENSAJE, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_CANAL_MENSAJE, destinatario.getIdExterno(), null);

		}

		// VALIDAMOS NOMBRE USUARIO
		count = validarUsuarioPush(destinatario.getIdentificadorUsuario());

		// Auditamos con error -3
		if (null == count || count < 0) {
			auditarErrorUsuarioNoExiste(idLote, notificacion.getUsuario(), notificacion.getPassword());
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_USUARIO_PUSH_NO_EXISTE, destinatario.getIdExterno(), null);

		}

		TblMensajes mens = crearMensaje(idLote, mensaje.getCuerpo(), notificacion.getDocUsuario(),
				notificacion.getCodSIA(), notificacion.getCodOrganismo(), null, destinatario.getIdExterno(),
				notificacion.getUsuario(), ps.getMessage("constantes.TIPO_MENSAJE_PUSH", null), mensaje.getTitulo());

		mens.setMensajeid(tblMensajesDAO.insert(mens));
		if (null != mens.getMensajeid()) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mens.getMensajeid(), notificacion.getUsuario(), notificacion.getPassword(),
					MensajesAuditoria.MENSAJE_PUSH_CORRECTO, MensajesAuditoria.OPERACION_MENSAJE_PUSH_CREAR,
					mens.getMensajeid());

		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPassword(),
					MensajesAuditoria.ERROR_BBDD, MensajesAuditoria.OPERACION_MENSAJE_PUSH_CREAR,
					MensajesAuditoria.COD_ERROR_BBDD);
			return generarRespuesta(MensajesAuditoria.COD_ERROR_BBDD.toString(), PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_BBDD, destinatario.getIdExterno(), null);
		}

		return generarRespuesta(null, null, null, destinatario.getIdExterno(), mens.getMensajeid());

	}
	
	@Override
	public Integer getPrioridadByIdMessage(Long mensajeId) { 
		Integer prioridad = null;
		TblMensajes mens =tblMensajesDAO.get(mensajeId);
		if (null != mens) {
			prioridad = mens.getPrioridad();
		}
		return prioridad;
	}

	private void auditarErrorUsuarioNoExiste(Long idLote, String usuario, String password) {
		AuditoriaBean auditoria = new AuditoriaBean(MensajesAuditoria.OPERACION_MENSAJE_PUSH_CREAR, new Date(), idLote,
				null, null, null, usuario, password, MensajesAuditoria.COD_ERROR_USUARIO_NO_EXISTE,
				MensajesAuditoria.ERROR_USUARIO_PUSH_NO_EXISTE);
		auditoriaManager.insertarAuditoria(auditoria);

	}

	private Integer validarUsuarioPush(String nombreUsuario) {
		ViewUsuariosPushQuery q = new ViewUsuariosPushQuery();
		q.setNombreusuario(nombreUsuario);

		return viewUsiariosPushManager.countViewUsuariosPush(q);
	}

	@Override
	public TblMensajes getMensaje(Long idMensaje) {
		return tblMensajesDAO.get(idMensaje);
	}

	/**
	 * @param idLote
	 * @return
	 */
	private Integer comprobarCanal(Long idLote, Long canalId) {
		query = new ViewLotesEnviosDetalladaQuery();
		query.setLoteenvioid(idLote);
		query.setCanalid(canalId);

		return viewLotesManager.countViewLoteDetallado(query);

	}

	/**
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	private Integer comprobarLote(Long idLote, String usuario, String password) {
		query = new ViewLotesEnviosDetalladaQuery();
		query.setLoteenvioid(idLote);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password.trim()));

		return viewLotesManager.countViewLoteDetallado(query);
	}

	private Mensaje generarRespuesta(String codigo, String statusText, String error, String idExterno, Long mensajeId) {
		Mensaje res = new Mensaje();
		res.setIdExterno(idExterno);
		if (null == mensajeId)
			res.setIdMensaje("");
		else
			res.setIdMensaje(mensajeId.toString());

		if (null != statusText && !statusText.equals(PlataformaErrores.STATUS_OK)) {
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(codigo);
			status.setStatusText(statusText);
			status.setDetails(error);
			res.setErrorMensaje(status);
		}

		return res;
	}

	private TblMensajes crearMensaje(Long idLote, String cuerpo, String docUsuario, String codSIA, String codOrganismo,
			String codOrganismoPagador, String idExterno, String usuario, String tipoMensaje, String cabecera) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		TblMensajes res = new TblMensajes();
		res.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(idLote));
		TblEstados tblEstado = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null));
		res.setCuerpo(cuerpo.trim());
		res.setCodigoexterno(idExterno);
		res.setTblEstados(tblEstado);
		res.setEstadoactual(tblEstado.getNombre());
		res.setNumeroenvios(0);
		res.setFechacreacion(new Date());
		res.setCreadopor(usuario);
		res.setFechamodificacion(new Date());
		res.setModificadopor(usuario);
		res.setTipomensaje(tipoMensaje);
		res.setDocusuario(docUsuario);
		res.setCodsia(codSIA);
		res.setCodorganismo(codOrganismo);
		res.setCodorganismopagador(codOrganismoPagador);
		res.setCabecera(cabecera);
		
		return res;
	}

	private TblMensajes crearMensajeEmail(Long idLote, MensajesXMLBean mensaje, EnvioEmailXMLBean envio) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		TblMensajes res = new TblMensajes();
		res.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(idLote));
		TblEstados tblEstado = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null));
		res.setCuerpo(mensaje.getCuerpo());
		res.setCodigoexterno(mensaje.getIdExterno());
		res.setCabecera(mensaje.getAsunto());
		res.setEstadoactual(tblEstado.getNombre());
		res.setTblEstados(tblEstado);
		res.setNumeroenvios(0);
		res.setTipocuerpo(getTipoCuerpo(mensaje.getFormatoCuerpo()));
		res.setTipocodificacion(getTipocodificacion(mensaje.getCodificacion()));
		res.setPrioridad(getPrioridad(mensaje.getPrioridad()));
		res.setFechacreacion(new Date());
		res.setCreadopor(envio.getUsuario());
		res.setTipomensaje(ps.getMessage("constantes.TIPO_MENSAJE_EMAIL", null));
		res.setDocusuario(mensaje.getDocUsuario());
		res.setCodsia(mensaje.getCodSIA());
		res.setCodorganismo(mensaje.getCodOrganismo());
		res.setModo(getModo(mensaje.getModo()));

		return res;
	}

	private Integer getModo(String modo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		try {
			if (null == modo)
				return Integer.parseInt(ps.getMessage("constantes.email.modo", null));
			else {
				return Integer.parseInt(modo);
			}
		} catch (NumberFormatException e) {
			return Integer.parseInt(ps.getMessage("constantes.email.modo", null));
		}

	}

	private Integer getPrioridad(Integer prioridad) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		return (null != prioridad) ? prioridad : Integer.parseInt(ps.getMessage("constantes.email.prioridad", null));
	}

	private String getTipoCuerpo(String formatoCuerpo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		return (null != formatoCuerpo) ? formatoCuerpo : ps.getMessage("constantes.email.tipocuerpo", null);
	}

	private String getTipocodificacion(String codificacion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		return (null != codificacion) ? codificacion : ps.getMessage("constantes.email.tipocodificacion", null);
	}

	@Override
	public Integer insertarMensajeGISS(Integer idLote, String cuerpo, String docUsuario, String codOrganismoPagadorSMS,
			String idExterno, String destinatario, String usuario, String password) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Long mensajeId;
		
		try {
			TblLotesEnvios lote = lotesEnviosManager.getLoteEnvioById(idLote.longValue());
			TblMensajes mensaje = crearMensaje(lote.getLoteenvioid(), cuerpo, docUsuario, null, codOrganismoPagadorSMS, 
					codOrganismoPagadorSMS, idExterno, usuario, ps.getMessage("constantes.TIPO_MENSAJE_SMS", null),null); 

			// Insertamos el mensaje
			mensajeId = tblMensajesDAO.insert(mensaje);
			
			TblDestinatariosMensajes destinatariosMensajes = destinatariosMensajesManager.getDestinatariosMensajes(
					idExterno, destinatario, usuario, ps, mensajeId);

			// insertamos destinatario del mensaje
			destinatariosMensajesManager.insertar(destinatariosMensajes);
			

			auditarMensaje(lote.getLoteenvioid(), mensaje.getMensajeid(), usuario, password,
					ps.getMessage("mensajesAuditoria.mensajes.SMS_CORRECTO", null),
					ps.getMessage("mensajesAuditoria.mensajes.SMS_CREAR", null), mensaje.getMensajeid());
		} catch (Exception e) {
			logger.error("Se ha producido un error", e);
			mensajeId = new Long(-10);
		}
		return mensajeId.intValue();
	}

	
	@Override
	public List<TblMensajes> getMensajesByLote(Long idLote) {
		TblLotesEnviosQuery tblLotesEnviosQuery = new TblLotesEnviosQuery();
		tblLotesEnviosQuery.setLoteenvioid(idLote);
		TblMensajesQuery mensajesQuery = new TblMensajesQuery();
		mensajesQuery.setTblLotesEnvios(tblLotesEnviosQuery);
		return tblMensajesDAO.search(mensajesQuery).getResults();
	}

	@Override
	public void update(TblMensajes mensaje) {
		tblMensajesDAO.update(mensaje);
	}

	@Override
	public Long getIdServicioByIdMensaje(Long idMensaje) {
		return queryExecutorServidores.getIdServicioByIdMensaje(idMensaje);
	}

	@Override
	public Integer operacionMensaje(Long idMensaje, String usuario, String password, String estadoFinal) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String operacionReenviar = ps.getMessage("mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE", null);
		String operacionReenviarCorrecto = ps.getMessage(
				"mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE_CORRECTO", null);
		String errorMensajeIncorrecto = ps.getMessage("auditoria.errores.ERROR_MENSAJE_INCORRECTO", null);
		Long codErrorMensajeIncorrecto = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_MENSAJE_INCORRECTO",
				null));
		String errorUsuarioAplicacion = ps.getMessage("auditoria.errores.ERROR_USUARIO_APLICACION", null);
		Long codErrorUsuarioAplicacion = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_USUARIO_APLICACION",
				null));
		String errorMensajeYaEnviado = ps.getMessage("auditoria.errores.ERROR_MENSAJE_YA_ENVIADO", null);
		Long codErrorMensajeYaEnviado = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_MENSAJE_YA_ENVIADO",
				null));
		String errorMensajeAplicacion = ps.getMessage("auditoria.errores.ERROR_MENSAJE_APLICACION", null);
		Long codErrorMensajeAplicacion = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_MENSAJE_APLICACION",
				null));
		String errorBBDD = ps.getMessage("auditoria.errores.ERROR_BBDD", null);
		Long codErrorBBDD = Long.parseLong(ps.getMessage("auditoria.errores.COD_ERROR_BBDD", null));
		Long codCorrecto = Long.parseLong(ps.getMessage("auditoria.errores.COD_CORRECTO", null));
		String estadoEnviado = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String estadoAnulado = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_ANULADO", null)).getNombre();

		try {
			// Se comprueba que la aplicacion pertenece al usuario
			TblAplicacionesQuery q = new TblAplicacionesQuery();
			q.setUsuario(usuario);
			q.setPassword(Utils.encode64(password));
			q.setActivo(true);

			List<TblAplicaciones> listaAplicaciones = aplicacionesManager.getAplicaciones(q);
			Integer out = (null != listaAplicaciones) ? listaAplicaciones.size() : null;

			// Auditamos con error -1
			if (null == out || out.intValue() != 1) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorUsuarioAplicacion, errorUsuarioAplicacion);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorUsuarioAplicacion.intValue();
			}

			// Se comprueba si EXISTE el mensaje
			TblMensajes tblMensaje = tblMensajesDAO.get(idMensaje);

			// Auditamos con error -2
			if (null == tblMensaje) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorMensajeIncorrecto, errorMensajeIncorrecto);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorMensajeIncorrecto.intValue();
			}

			// Se comprueba si el mensaje ha sido enviado
			if (tblMensaje.getEstadoactual().equals(estadoEnviado)) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorMensajeYaEnviado, errorMensajeYaEnviado);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorMensajeYaEnviado.intValue();
			}

			// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
			Integer count = comprobarLote(tblMensaje.getTblLotesEnvios().getLoteenvioid(), usuario, password);

			// Auditamos con error -4
			if (null == count || count <= 0) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorMensajeAplicacion, errorMensajeAplicacion);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorMensajeAplicacion.intValue();
			}

			if (setEstadoMensaje(idMensaje, estadoFinal, null, false, null, null, usuario, null) > 0) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codCorrecto, operacionReenviarCorrecto);
				auditoriaManager.insertarAuditoria(auditoria);
				if (!estadoFinal.equalsIgnoreCase(estadoAnulado)) {
					List<TblDestinatariosMensajes> destinatarios = destinatariosMensajesManager.getDestinatarioMensajes(idMensaje);
					Long canal = Long.parseLong(ps.getMessage("constantes.CANAL_EMAIL", null));
					Integer modo = Integer.parseInt(ps.getMessage("constantes.email.modo", null));
					if (tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().equals(canal)) {
						checkEmailMode(idMensaje, tblMensaje, destinatarios, modo);
					} else {
						sendMessages(idMensaje, tblMensaje, destinatarios);
					}
					
				}
				return codCorrecto.intValue();
			} else {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorBBDD, errorBBDD);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorBBDD.intValue();
			}
		} catch (Exception e) {
			logger.error(ps.getMessage("auditoria.errores.ERROR_GENERAL", null), e);
			return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
		}
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
			mensajeJms.setIdExterno(tblMensaje.getCodigoexterno());
			mensajeJms.setDestinatarioMensajeId(destBuilder.toString());
			Long maxRetries = null;
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().toString());
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios().getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() > 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
			}
			sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
		} else {
			sendMessages(idMensaje, tblMensaje, destinatarios);
		}
	}

	private void sendMessages(Long idMensaje, TblMensajes tblMensaje, List<TblDestinatariosMensajes> destinatarios) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		for (TblDestinatariosMensajes destinatario : destinatarios) {
			MensajeJMS mensajeJms = new MensajeJMS();
			mensajeJms.setIdMensaje(idMensaje.toString());
			mensajeJms.setIdExterno(tblMensaje.getCodigoexterno());
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().toString());
			mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
			Long maxRetries = null;
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios().getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() > 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
			}
			sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
		}
	}

	@Override
	@Transactional
	public synchronized Integer setEstadoMensaje(Long idMensaje, String estado, String descripcion, Boolean controlReintentos,
			Long destinatarioMensajeId, String subEstadoCode, String usuario, Long proveedorId) {

		Integer res = 1;
		String uim = null;
		Long servidorId = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
	
		try {
			if (null == proveedorId)
				servidorId = queryExecutorServidores.obtenerServidorByIdMensaje(idMensaje);
			else
				servidorId = proveedorId;

			// Actualizamos la tabla mensaje

			TblMensajes mensaje = tblMensajesDAO.get(idMensaje);
			
			if (ps.getMessage("constantes.TIPO_MENSAJE_SMS", null).equals(mensaje.getTipomensaje()) && null != descripcion && descripcion.contains("|") && descripcion.contains("OK"))
				uim = descripcion.substring(descripcion.indexOf("|")+1).trim();
			
			// aumentamos el numero de envios para todos los mensajes GISS y los
			// demas cuando el estado siguiente es
			// INCIDENCIA
			if (ps.getMessage("constantes.ESTADO_INCIDENCIA", null).equals(estado)) {
				mensaje.setNumeroenvios(mensaje.getNumeroenvios() + 1);
			} else if (ps.getMessage("constantes.ESTADO_PENDIENTE", null).equals(estado)) {
				mensaje.setNumeroenvios(0);
			}
			mensaje.setEstadoactual(estado);
			mensaje.setFechamodificacion(new Date());
			mensaje.setModificadopor(usuario);
			tblMensajesDAO.update(mensaje);

			List<TblDestinatariosMensajes> destinatariosMensaje = new ArrayList<>();
			if (null != destinatarioMensajeId){
				destinatariosMensaje.add(destinatariosMensajesManager.getDestinatarioMensaje(destinatarioMensajeId));
			}else{
				destinatariosMensaje = destinatariosMensajesManager.getDestinatarioMensajes(idMensaje);
			}
			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {

				// Actualizamos la tabla destinatarios mensajes
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					destinatarioMensaje.setEstado(estado);
					destinatarioMensaje.setModificadopor(usuario);
					destinatarioMensaje.setFechamodificacion(new Date());
					destinatarioMensaje.setUltimoenvio(new Date());
					if (uim != null){
						destinatarioMensaje.setUim(uim);
					}
					destinatariosMensajesManager.update(destinatarioMensaje);
				}
			}
			sessionFactorySIMApp.getCurrentSession().flush();
						
			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					hitoricosManager.creaHistorico(idMensaje, destinatarioMensaje.getDestinatariosmensajes(), estadosManager.getEstadoByName(estado).getEstadoid(),
							servidorId, descripcion, subEstadoCode, usuario);
				}
			} else {
				hitoricosManager.creaHistorico(idMensaje, null, estadosManager.getEstadoByName(estado).getEstadoid(), servidorId, descripcion, subEstadoCode, usuario);
			}

		} catch (Exception e) {
			logger.error(ps.getMessage("auditoria.errores.ERROR_GENERAL", null), e);
			return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
		}
		return res;
	}

	@Override
	public TblMensajes getMensajeIDByUIM(String uim){
		TblMensajes mensaje;
		
		try{
			TblDestinatariosMensajes dm = destinatariosMensajesManager.getDestinatarioMensajeByUim(uim);
			if (null == dm){//no es multidestinatario
				mensaje = getMensajeByUim(uim);
			}else{
				mensaje = tblMensajesDAO.get(dm.getMensajeid());
			}
	
		} catch (Exception e) {
			logger.debug("[TblMensajesManagerImpl] - Error en bbdd", e);
			return null;
		} 
		return mensaje;
	}

	@Override
	public int updateMessagesUsers(List<String> usersId, String estadoInicial, String estadoFinal, String usuarioPeticion) {
		int res = -1;
		Map<Long,List<Long>> mapMensajesMult;
		List<Long> listaMensajes;
		
		logger.info("Inicio Actualizacion de estados notificaciones de "+estadoInicial+" a "+estadoFinal);
		
		String usuarios = usersId.toString().replace("[", "'").replace("]", "'")
	            .replace(", ", "','");
		
		//recuperamos los mensajes multides
		mapMensajesMult =queryExecutorMensajes.getMensajesPorUsuariosPushYEstadoMultidest(usuarios, estadoInicial);
		
		//actualizamos los obtenidos
		if (!mapMensajesMult.isEmpty()) {
			
			//actualizamos los mensajes multidest a Recibido y son multidestinatarios
			res = setEstadoMensajeUsuarios(mapMensajesMult, estadoInicial, estadoFinal, usuarioPeticion);
		}
		
		//recuperamos mensajes no MULTIDESTINATARIO
		listaMensajes = queryExecutorMensajes.getMensajesPorUsuariosPushYEstado(usuarios, estadoInicial);
		if (null != listaMensajes && !listaMensajes.isEmpty()){
			for (Long mensajeId : listaMensajes) {
				res = setEstadoMensaje(mensajeId, estadoFinal, null, false, null, null, usuarioPeticion, null);
			}
			
		}
		
		return res;
	}
		
	
	@Override
	public Boolean isMessageUser(List<String> usersId, Long mensajeId) {
		if (tblMensajesDAO.get(mensajeId).getTblLotesEnvios().getMultidestinatario()){
			return (!destinatariosMensajesManager.getDestinatarioMensajesUsuarios(mensajeId, usersId, null).isEmpty()) 
					? true : false;
		}else{
			TblMensajesQuery queryM = new TblMensajesQuery();
			queryM.setMensajeid(mensajeId);
			for(String s : usersId) 
				queryM.addUsuarioidIn(Long.parseLong(s));
			return (!tblMensajesDAO.search(queryM).getResults().isEmpty()) ? true : false;
		}
	}
	
	@Override
	public Integer setEstadoMensajeUsuarios(Map<Long, List<Long>> mapMensajesMult, String estadoInicial,
			String estadoFinal, String usuarioPeticion) {

		Integer res = -1;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		try {
			
			for (Long mensajeId : mapMensajesMult.keySet()) {
				Long servidorId = queryExecutorServidores.obtenerServidorByIdMensaje(mensajeId);
				List<Long> listaDestinatarios = mapMensajesMult.get(mensajeId);
				for (Long dest : listaDestinatarios) {
					TblDestinatariosMensajes destinatarioMensaje = destinatariosMensajesManager
							.getDestinatarioMensaje(dest);
					destinatarioMensaje.setEstado(estadoFinal);
					destinatarioMensaje.setModificadopor(usuarioPeticion);
					destinatarioMensaje.setFechamodificacion(new Date());
					destinatariosMensajesManager.update(destinatarioMensaje);

					sessionFactorySIMApp.getCurrentSession().flush();

					// insertamos en tbl historicos que actualia la tabla lotes,
					// mensajes e historicos, y gestion de envios
					hitoricosManager.creaHistorico(mensajeId, destinatarioMensaje.getDestinatariosmensajes(),
							estadosManager.getEstadoByName(estadoFinal).getEstadoid(), servidorId, null, null,
							usuarioPeticion);
				}
			}

			res = 1;
		} catch (Exception e) {
			logger.error(ps.getMessage("auditoria.errores.ERROR_GENERAL", null), e);
			return Integer.parseInt(ps.getMessage("constantes.errores.devolucion.error10", null));
		}
		return res;
	}

	@Override
	public List<Aviso> getAvisosUsuario(String idDispositivo, String idPlataforma, String idServicio, String idUsuario,
			String numPagina, String tamPagina, PropertiesServices ps) {
		List<Long> listaUsuarios = new ArrayList<>();
		
		if (null != idUsuario)
			listaUsuarios.add(usuariosPushManager.getDatosUsuario(idServicio, idPlataforma, idDispositivo, idUsuario, true).getUsuarioid());
		else
			listaUsuarios = usuariosPushManager.getDatosUsuario(idServicio, idPlataforma, idDispositivo);
		
		String usuarios = listaUsuarios.toString().replace("[", "'").replace("]", "'")
	            .replace(", ", "','");
		
		return queryExecutorMensajes.getAvisosMensajeUsuario(usuarios, numPagina, tamPagina, ps);
	}

	@Override
	public Boolean getMultidestinatarioByMensaje(Long mensajeId) {
		return tblMensajesDAO.get(mensajeId).getTblLotesEnvios().getMultidestinatario();
		
	}
	
	private TblMensajes getMensajeByUim(String uim) {
		TblMensajes mensaje;
		TblMensajesQuery q = new TblMensajesQuery();
		q.setUim(uim);
		q.setUimComparator(TextComparator.EQUALS);
		mensaje = tblMensajesDAO.searchUnique(q);
		return mensaje;
	}
	/**
	 * @return the sessionFactorySIMApp
	 */
	public SessionFactory getSessionFactorySIMApp() {
		return sessionFactorySIMApp;
	}

	/**
	 * @param sessionFactorySIMApp the sessionFactorySIMApp to set
	 */
	public void setSessionFactorySIMApp(SessionFactory sessionFactorySIMApp) {
		this.sessionFactorySIMApp = sessionFactorySIMApp;
	}

	/**
	 * @return the aplicacionesManager
	 */
	public TblAplicacionesManager getAplicacionesManager() {
		return aplicacionesManager;
	}

	/**
	 * @param aplicacionesManager the aplicacionesManager to set
	 */
	public void setAplicacionesManager(TblAplicacionesManager aplicacionesManager) {
		this.aplicacionesManager = aplicacionesManager;
	}

	/**
	 * @return the viewLotesManager
	 */
	public ViewLotesEnviosDetalladaManager getViewLotesManager() {
		return viewLotesManager;
	}

	/**
	 * @param viewLotesManager the viewLotesManager to set
	 */
	public void setViewLotesManager(ViewLotesEnviosDetalladaManager viewLotesManager) {
		this.viewLotesManager = viewLotesManager;
	}

	/**
	 * @return the auditoriaManager
	 */
	public TblAuditoriaManager getAuditoriaManager() {
		return auditoriaManager;
	}

	/**
	 * @param auditoriaManager the auditoriaManager to set
	 */
	public void setAuditoriaManager(TblAuditoriaManager auditoriaManager) {
		this.auditoriaManager = auditoriaManager;
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
	 * @return the viewUsiariosPushManager
	 */
	public ViewUsuariosPushManager getViewUsiariosPushManager() {
		return viewUsiariosPushManager;
	}

	/**
	 * @param viewUsiariosPushManager the viewUsiariosPushManager to set
	 */
	public void setViewUsiariosPushManager(ViewUsuariosPushManager viewUsiariosPushManager) {
		this.viewUsiariosPushManager = viewUsiariosPushManager;
	}

	/**
	 * @return the lotesEnviosManager
	 */
	public TblLotesEnviosManager getLotesEnviosManager() {
		return lotesEnviosManager;
	}

	/**
	 * @param lotesEnviosManager the lotesEnviosManager to set
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
	 * @param estadosManager the estadosManager to set
	 */
	public void setEstadosManager(TblEstadosManager estadosManager) {
		this.estadosManager = estadosManager;
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
	 * @return the usuariosPushManager
	 */
	public TblUsuariosPushManager getUsuariosPushManager() {
		return usuariosPushManager;
	}

	/**
	 * @param usuariosPushManager the usuariosPushManager to set
	 */
	public void setUsuariosPushManager(TblUsuariosPushManager usuariosPushManager) {
		this.usuariosPushManager = usuariosPushManager;
	}

	/**
	 * @return the queryExecutorServidores
	 */
	public QueryExecutorServidores getQueryExecutorServidores() {
		return queryExecutorServidores;
	}

	/**
	 * @param queryExecutorServidores the queryExecutorServidores to set
	 */
	public void setQueryExecutorServidores(QueryExecutorServidores queryExecutorServidores) {
		this.queryExecutorServidores = queryExecutorServidores;
	}

	/**
	 * @return the queryExecutorLotesEnvios
	 */
	public QueryExecutorLotesEnvios getQueryExecutorLotesEnvios() {
		return queryExecutorLotesEnvios;
	}

	/**
	 * @param queryExecutorLotesEnvios the queryExecutorLotesEnvios to set
	 */
	public void setQueryExecutorLotesEnvios(QueryExecutorLotesEnvios queryExecutorLotesEnvios) {
		this.queryExecutorLotesEnvios = queryExecutorLotesEnvios;
	}

	/**
	 * @return the queryExecutorMensajes
	 */
	public QueryExecutorMensajes getQueryExecutorMensajes() {
		return queryExecutorMensajes;
	}

	/**
	 * @param queryExecutorMensajes the queryExecutorMensajes to set
	 */
	public void setQueryExecutorMensajes(QueryExecutorMensajes queryExecutorMensajes) {
		this.queryExecutorMensajes = queryExecutorMensajes;
	}

	/**
	 * @return the tblMensajesDAO
	 */
	public TblMensajesDAO getTblMensajesDAO() {
		return tblMensajesDAO;
	}

	/**
	 * @param tblMensajesDAO the tblMensajesDAO to set
	 */
	public void setTblMensajesDAO(TblMensajesDAO tblMensajesDAO) {
		this.tblMensajesDAO = tblMensajesDAO;
	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
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
	 * @return the sender
	 */
	public SIMMessageSender getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(SIMMessageSender sender) {
		this.sender = sender;
	}

	/**
	 * @return the query
	 */
	public ViewLotesEnviosDetalladaQuery getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(ViewLotesEnviosDetalladaQuery query) {
		this.query = query;
	}
	
}
