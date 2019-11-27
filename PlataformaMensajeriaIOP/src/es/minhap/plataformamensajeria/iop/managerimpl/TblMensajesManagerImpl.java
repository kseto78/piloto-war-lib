package es.minhap.plataformamensajeria.iop.managerimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
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
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesWebPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesWebPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
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
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;
import es.minhap.plataformamensajeria.iop.util.MensajesAuditoria;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;
import es.minhap.plataformamensajeria.iop.util.UtilCreateFile;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblMensajesDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblEstados;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblLotesEnviosQuery;
import es.minhap.sim.query.TblMensajesQuery;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;
import es.minhap.sim.query.ViewUsuariosPushQuery;

/**
 * Clase manager encargada de la l�gica de negocios relacionada con los mensajes
 * 
 * @author everis
 * 
 */
@Service("TblMensajesManagerImpl")
public class TblMensajesManagerImpl implements TblMensajesManager {

	protected static final String R_CONST_1 = "auditoria.errores.ERROR_MENSAJE_INCORRECTO";

	protected static final String R_CONST_2 = "auditoria.errores.COD_ERROR_MENSAJE_INCORRECTO";

	protected static final String R_CONST_3 = "[TblMensajesManagerImpl.updateMessagesUsers]";

	protected static final String R_CONST_4 = "constantes.ESTADO_ANULADO";

	protected static final String R_CONST_5 = "constantes.CANAL_EMAIL";

	protected static final String R_CONST_6 = "auditoria.errores.ERROR_USUARIO_APLICACION";

	protected static final String R_CONST_7 = "auditoria.errores.ERROR_MENSAJE_APLICACION";

	protected static final String R_CONST_8 = "'";

	protected static final String R_CONST_9 = "auditoria.errores.ERROR_GENERAL";

	protected static final String R_CONST_10 = "conexion.ERRORACTIVEMQ";

	protected static final String R_CONST_11 = "Estamos en TblMensajesManagerImpl-operacionMensaje";

	protected static final String R_CONST_12 = "plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ";

	protected static final String R_CONST_13 = "mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE_CORRECTO";

	protected static final String R_CONST_14 = ";";

	protected static final String R_CONST_15 = "auditoria.errores.COD_ERROR_MENSAJE_APLICACION";

	protected static final String R_CONST_16 = "mensajesAuditoria.mensajes.OPERACION_REENVIO_MENSAJE";

	protected static final String R_CONST_17 = "auditoria.errores.COD_ERROR_BBDD";

	protected static final String R_CONST_18 = " TblMensajesManagerImpl.operacionMensaje --Error ActiveMq--";

	protected static final String R_CONST_19 = "auditoria.errores.COD_ERROR_MENSAJE_YA_ENVIADO";

	protected static final String R_CONST_20 = "constantes.ESTADO_ENVIADO";

	protected static final String R_CONST_21 = "constantes.email.modo";

	protected static final String R_CONST_22 = "[ERROR-ACTIVEMQ]";

	protected static final String R_CONST_23 = "constantes.errores.devolucion.error10";

	protected static final String R_CONST_24 = "[";

	protected static final String R_CONST_25 = "OK";

	protected static final String R_CONST_26 = "auditoria.errores.COD_ERROR_USUARIO_APLICACION";

	protected static final String R_CONST_27 = "constantes.ESTADO_INCIDENCIA";

	protected static final String R_CONST_28 = "]";

	protected static final String R_CONST_29 = "auditoria.errores.COD_CORRECTO";

	protected static final String R_CONST_30 = "auditoria.errores.ERROR_MENSAJE_YA_ENVIADO";

	protected static final String R_CONST_31 = "','";

	protected static final String R_CONST_32 = "filesystem.maxTamMensajeBBDD";

	protected static final String R_CONST_33 = "constantes.servicio.numMaxReenvios";

	protected static final String R_CONST_34 = "auditoria.errores.ERROR_BBDD";

	protected static final String R_CONST_35 = ", ";

	protected static final String R_CONST_36 = "|";

	protected static final String R_CONST_37 = "Inicio Actualizacion de estados notificaciones de ";

	protected static final String R_CONST_38 = " a ";

	private static final Logger LOG = LoggerFactory.getLogger(TblMensajesManagerImpl.class);

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
	
	@Autowired
	private ErroresManager erroresManager;

	@Resource
	private TblMensajesDAO tblMensajesDAO;

	@Autowired
	private TblServiciosManager serviciosManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	@Resource(name = "UtilCreateFile")
	private UtilCreateFile utilFile;

	private ViewLotesEnviosDetalladaQuery query;

	private static final String ESTADOPENDIENTE = "constantes.ESTADO_PENDIENTE";
	private static final String TIPOMENSAJESMS = "constantes.TIPO_MENSAJE_SMS";

	/**
	 * @see es.minhap.TblMensajesManager.insertarMensajeSMS
	 */

	@Override
	@Transactional
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
				ps.getMessage(TIPOMENSAJESMS, null), null, false);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		// sacamos el cuerpo a file si es necesario
		if (null == mens.getCuerpo()) {
			mens.setCuerpofile(utilFile.createFileMensaje(mens.getMensajeid(), mensaje.getCuerpo(), mens
					.getTblLotesEnvios().getTblServicios().getServicioid(), mens.getTblLotesEnvios().getTblServicios()
					.getConservacion(), mens.getFechacreacion()));
			tblMensajesDAO.update(mens);
		}

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
	
	/**
	 * @see es.minhap.TblMensajesManager.insertarMensajeSMS
	 */

	@Override
	@Transactional
	public Mensaje insertarMensajeWebPush(Long idLote, MensajePeticionLotesWebPushXMLBean mensaje,
			PeticionXMLBean peticion, DestinatarioPeticionLotesWebPushXMLBean destinatario, String usuario, String password) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, usuario, password);

		// Auditamos con error -1
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_LOTE_APLICACION,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return generarRespuesta(PlataformaErrores.COD_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_LOTE_APLICACION, destinatario.getIdExterno(), null);
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_WEBPUSH", null, "5")));

		// Auditamos con error -2
		if (null == count || count <= 0) {
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_CANAL_MENSAJE,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_CANAL_MENSAJE, destinatario.getIdExterno(), null);

		}

		TblMensajes mens = crearMensaje(idLote, mensaje.getCuerpo(), destinatario.getDocUsuario(), peticion.getCodSia(),
				peticion.getCodOrganismo(), null, destinatario.getIdExterno(), usuario,
				ps.getMessage("constantes.TIPO_MENSAJE_WEBPUSH", null), mensaje.getTitulo(), false);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		// sacamos el cuerpo a file si es necesario
		if (null == mens.getCuerpo()) {
			mens.setCuerpofile(utilFile.createFileMensaje(mens.getMensajeid(), mensaje.getCuerpo(), mens
					.getTblLotesEnvios().getTblServicios().getServicioid(), mens.getTblLotesEnvios().getTblServicios()
					.getConservacion(), mens.getFechacreacion()));
			tblMensajesDAO.update(mens);
		}

		if (null != mens.getMensajeid()) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mens.getMensajeid(), usuario, password, MensajesAuditoria.MENSAJE_SMS_CORRECTO,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, mens.getMensajeid());
		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, usuario, password, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR, MensajesAuditoria.COD_ERROR_BBDD);
			return generarRespuesta(MensajesAuditoria.COD_ERROR_BBDD.toString(), PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_BBDD, destinatario.getIdExterno(), null);
		}

		return generarRespuesta(null, null, null, destinatario.getIdExterno(), mens.getMensajeid());

	}

	@Override
	@Transactional
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
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");

		if (Utils.validarTelefono(sender, telefonoExcepcion) == 1) {
			auditarMensaje(idLote, null, userAplicacion, passwordAplicacion,
					MensajesAuditoria.ERROR_DESTINATARIO_MENSAJE, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_DESTINATARIO_MENSAJE);
			return MensajesAuditoria.COD_ERROR_DESTINATARIO_MENSAJE.intValue();
		}

		TblMensajes mens = crearMensaje(idLote, smsText, null, null, null, null, messageId, userAplicacion,
				ps.getMessage("constantes.TIPO_MENSAJE_RECEPCION", null), null, false);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		// sacamos el cuerpo a file si es necesario
		if (null == mens.getCuerpo()) {
			mens.setCuerpofile(utilFile.createFileMensaje(mens.getMensajeid(), smsText, mens.getTblLotesEnvios()
					.getTblServicios().getServicioid(), mens.getTblLotesEnvios().getTblServicios().getConservacion(),
					mens.getFechacreacion()));
			tblMensajesDAO.update(mens);
		}

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
	@Transactional
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
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage(R_CONST_5, null)));

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

		TblMensajes mens = crearMensajeEmail(idLote, mensaje, envio);

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		// sacamos el cuerpo a file si es necesario
		if (null == mens.getCuerpo()) {
			mens.setCuerpofile(utilFile.createFileMensaje(mens.getMensajeid(), mensaje.getCuerpo(), mens
					.getTblLotesEnvios().getTblServicios().getServicioid(), mens.getTblLotesEnvios().getTblServicios()
					.getConservacion(), mens.getFechacreacion()));
			tblMensajesDAO.update(mens);
		}

		if(mensaje.getIdMensaje() == null || mensaje.getIdMensaje().isEmpty()){
			// creamos el mensaje
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
			
		} else {
			mens.setMensajeid(Long.valueOf(mensaje.getIdMensaje()));
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
		List<String> lista;
		// analizamos destinatarios TO
		if (null != to) {
			lista = Arrays.asList(to.split(R_CONST_14));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_TO;
				}
			}
		}

		// analizamos destinatarios CC
		if (null != cc) {
			lista = Arrays.asList(cc.split(R_CONST_14));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_CC;
				}
			}
		}

		// analizamos destinatarios BCC
		if (null != bcc) {
			lista = Arrays.asList(bcc.split(R_CONST_14));
			for (String email : lista) {
				if (!Utils.validarEmail(email.trim())) {
					return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_BCC;
				}
			}
		}

		if (to == null && cc == null && bcc == null) {
			return MensajesAuditoria.ERROR_DESTINATARIOS_EMAIL_TO;
		}

		return res;
	}

	@Override
	@Transactional
	public Mensaje insertarMensajePush(Long idLote, MensajePeticionLotesPushXMLBean mensaje,
			EnvioPushXMLBean notificacion, DestinatarioPeticionLotesPushXMLBean destinatario, Integer usuarioId) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
		Integer count = comprobarLote(idLote, notificacion.getUsuario(), notificacion.getPass());

		// Auditamos con error -1
		if (count <= 0) {
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPass(),
					MensajesAuditoria.ERROR_LOTE_APLICACION, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_LOTE_APLICACION);
			return generarRespuesta(PlataformaErrores.COD_0001_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_LOTE_APLICACION, destinatario.getIdExterno(), null);
		}

		// COMPROBAR EL CANAL SI ES CORRECTO
		count = comprobarCanal(idLote, Long.parseLong(ps.getMessage("constantes.CANAL_PUSH", null)));

		// Auditamos con error -2
		if (count <= 0) {
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPass(),
					MensajesAuditoria.ERROR_CANAL_MENSAJE, MensajesAuditoria.OPERACION_MENSAJE_SMS_CREAR,
					MensajesAuditoria.COD_ERROR_CANAL_MENSAJE);
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_CANAL_MENSAJE, destinatario.getIdExterno(), null);

		}

		// VALIDAMOS NOMBRE USUARIO
		count = validarUsuarioPush(destinatario.getIdentificadorUsuario());

		// Auditamos con error -3
		if (null == count || count < 0) {
			auditarErrorUsuarioNoExiste(idLote, notificacion.getUsuario(), notificacion.getPass());
			return generarRespuesta(PlataformaErrores.COD_0018_GENERAL, PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_USUARIO_PUSH_NO_EXISTE, destinatario.getIdExterno(), null);

		}

		TblMensajes mens = crearMensaje(idLote, mensaje.getCuerpo(), notificacion.getDocUsuario(),
				notificacion.getCodSIA(), notificacion.getCodOrganismo(), null, destinatario.getIdExterno(),
				notificacion.getUsuario(), ps.getMessage("constantes.TIPO_MENSAJE_PUSH", null), mensaje.getTitulo(),
				mensaje.getNotificacionesSilenciosas() != null && mensaje.getNotificacionesSilenciosas());

		mens.setMensajeid(tblMensajesDAO.insert(mens));

		// sacamos el cuerpo a file si es necesario
		if (null == mens.getCuerpo()) {
			mens.setCuerpofile(utilFile.createFileMensaje(mens.getMensajeid(), mensaje.getCuerpo(), mens
					.getTblLotesEnvios().getTblServicios().getServicioid(), mens.getTblLotesEnvios().getTblServicios()
					.getConservacion(), mens.getFechacreacion()));
			tblMensajesDAO.update(mens);
		}

		if (null != mens.getMensajeid()) {

			// auditoria mensaje correcto
			auditarMensaje(idLote, mens.getMensajeid(), notificacion.getUsuario(), notificacion.getPass(),
					MensajesAuditoria.MENSAJE_PUSH_CORRECTO, MensajesAuditoria.OPERACION_MENSAJE_PUSH_CREAR,
					mens.getMensajeid());

		} else {

			// auditoria mensaje incorrecto
			auditarMensaje(idLote, null, notificacion.getUsuario(), notificacion.getPass(),
					MensajesAuditoria.ERROR_BBDD, MensajesAuditoria.OPERACION_MENSAJE_PUSH_CREAR,
					MensajesAuditoria.COD_ERROR_BBDD);
			return generarRespuesta(MensajesAuditoria.COD_ERROR_BBDD.toString(), PlataformaErrores.STATUSTEXT_KO,
					MensajesAuditoria.ERROR_BBDD, destinatario.getIdExterno(), null);
		}

		return generarRespuesta(null, null, null, destinatario.getIdExterno(), mens.getMensajeid());

	}

	@Override
	@Transactional
	public Integer getPrioridadByIdMessage(Long mensajeId) { 
		Integer prioridad = null;
		TblMensajes mens = tblMensajesDAO.get(mensajeId);
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
	@Transactional
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
		if (null == mensajeId) {
			res.setIdMensaje("");
		} else {
			res.setIdMensaje(mensajeId.toString());
		}

		if (null != statusText && !PlataformaErrores.STATUS_OK.equals(statusText)) {
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(codigo);
			status.setStatusText(statusText);
			status.setDetails(error);
			res.setErrorMensaje(status);
		}

		return res;
	}

	private TblMensajes crearMensaje(Long idLote, String cuerpo, String docUsuario, String codSIA, String codOrganismo,
			String codOrganismoPagador, String idExterno, String usuario, String tipoMensaje, String cabecera, Boolean notificacionSilenciosa) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Integer maxTamMensajeBBDD = Integer.parseInt(ps.getMessage(R_CONST_32, null));

		TblMensajes res = new TblMensajes();
		res.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(idLote));
		TblEstados tblEstado = estadosManager.getEstadoByName(ps.getMessage(ESTADOPENDIENTE, null));
		if (!checkCuerpo(cuerpo, maxTamMensajeBBDD)) {
			res.setCuerpo(cuerpo);
		} else {
			res.setCuerpo(null);
			res.setCuerpofile(null);
		}
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
		res.setNotificacionsilenciosa(notificacionSilenciosa);

		return res;
	}

	private TblMensajes crearMensajeEmail(Long idLote, MensajesXMLBean mensaje, EnvioEmailXMLBean envio) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Integer maxTamMensajeBBDD = Integer.parseInt(ps.getMessage(R_CONST_32, null));

		TblMensajes res = new TblMensajes();
		res.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(idLote));
		TblEstados tblEstado = estadosManager.getEstadoByName(ps.getMessage(ESTADOPENDIENTE, null));
		if (!checkCuerpo(mensaje.getCuerpo(), maxTamMensajeBBDD)) {
			res.setCuerpo(mensaje.getCuerpo());
		} else {
			res.setCuerpo(null);
			res.setCuerpofile(null);
		}
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

	private boolean checkCuerpo(String cuerpo, Integer maxTamMensajeBBDD) {
		return cuerpo.length() > maxTamMensajeBBDD;
	}

	private Integer getModo(String modo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		try {
			if (null == modo) {
				return Integer.parseInt(ps.getMessage(R_CONST_21, null));
			} else {
				return Integer.parseInt(modo);
			}
		} catch (NumberFormatException e) {
			// TODO logger.warn(e.getMessage(), e);
			return Integer.parseInt(ps.getMessage(R_CONST_21, null));
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
	@Transactional
	public Integer insertarMensajeGISS(Integer idLote, String cuerpo, String docUsuario, String codOrganismoPagadorSMS,
			String idExterno, String destinatario, String usuario, String password) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Long mensajeId;
		
		try {
			TblMensajes mensaje = crearMensaje(idLote.longValue(), cuerpo, docUsuario, null, codOrganismoPagadorSMS, 
					codOrganismoPagadorSMS, idExterno, usuario, ps.getMessage("constantes.TIPO_MENSAJE_SMS", null),null, false); 

			// Insertamos el mensaje
			mensajeId = tblMensajesDAO.insert(mensaje);
			
			TblDestinatariosMensajes destinatariosMensajes = destinatariosMensajesManager.getDestinatariosMensajes(
					idExterno, destinatario, usuario, ps, mensajeId);

			// insertamos destinatario del mensaje
			destinatariosMensajesManager.insertar(destinatariosMensajes);
			

			auditarMensaje(idLote.longValue(), mensaje.getMensajeid(), usuario, password,
					ps.getMessage("mensajesAuditoria.mensajes.SMS_CORRECTO", null),
					ps.getMessage("mensajesAuditoria.mensajes.SMS_CREAR", null), mensaje.getMensajeid());
		} catch (Exception e) {
			LOG.error("Se ha producido un error", e);
			mensajeId = Long.valueOf(-10);
		}
		return mensajeId.intValue();
	}

	@Override
	@Transactional
	public List<TblMensajes> getMensajesByLote(Long idLote) {
		TblLotesEnviosQuery tblLotesEnviosQuery = new TblLotesEnviosQuery();
		tblLotesEnviosQuery.setLoteenvioid(idLote);
		TblMensajesQuery mensajesQuery = new TblMensajesQuery();
		mensajesQuery.setTblLotesEnvios(tblLotesEnviosQuery);
		return tblMensajesDAO.search(mensajesQuery).getResults();
	}
	
	@Override
	@Transactional
	public List<TblMensajes> getMensajesByLote(Long idLote, Integer max, Integer firstResult) {
		TblLotesEnviosQuery tblLotesEnviosQuery = new TblLotesEnviosQuery();
		tblLotesEnviosQuery.setLoteenvioid(idLote);
		TblMensajesQuery mensajesQuery = new TblMensajesQuery();
		mensajesQuery.setTblLotesEnvios(tblLotesEnviosQuery);
		mensajesQuery.setMaxResult(max);
		mensajesQuery.setFirstResult(firstResult);
		return tblMensajesDAO.search(mensajesQuery).getResults();
	}
	

	@Override
	@Transactional
	public void update(TblMensajes mensaje) {
		tblMensajesDAO.update(mensaje);
	}

	@Override
	@Transactional
	public Long getIdServicioByIdMensaje(Long idMensaje) {
		return queryExecutorServidores.getIdServicioByIdMensaje(idMensaje);
	}

	
	@Override
	@Transactional
	public Long getIdLoteByIdMensaje(Long idMensaje) {
		return queryExecutorLotesEnvios.getIdLoteByIdMensaje(idMensaje);
	}


	@Override
	@Transactional
	public Integer operacionMensaje(Long idMensaje, String usuario, String password, String estadoFinal) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorActiveMq = ps.getMessage(R_CONST_10, null, R_CONST_22);
		String operacionReenviar = ps.getMessage(R_CONST_16, null);
		String operacionReenviarCorrecto = ps.getMessage(
				R_CONST_13, null);
		String errorMensajeIncorrecto = ps.getMessage(R_CONST_1, null);
		Long codErrorMensajeIncorrecto = Long.parseLong(ps.getMessage(R_CONST_2,
				null));
		String errorUsuarioAplicacion = ps.getMessage(R_CONST_6, null);
		Long codErrorUsuarioAplicacion = Long.parseLong(ps.getMessage(R_CONST_26,
				null));
		String errorMensajeYaEnviado = ps.getMessage(R_CONST_30, null);
		Long codErrorMensajeYaEnviado = Long.parseLong(ps.getMessage(R_CONST_19,
				null));
		String errorMensajeAplicacion = ps.getMessage(R_CONST_7, null);
		Long codErrorMensajeAplicacion = Long.parseLong(ps.getMessage(R_CONST_15,
				null));
		String errorBBDD = ps.getMessage(R_CONST_34, null);
		Long codErrorBBDD = Long.parseLong(ps.getMessage(R_CONST_17, null));
		Long codCorrecto = Long.parseLong(ps.getMessage(R_CONST_29, null));
		String estadoEnviado = ps.getMessage(R_CONST_20, null);
		String estadoAnulado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_4, null))
				.getNombre();
		String descripcionErrorActiveMq = ps.getMessage(R_CONST_12, null);
		TblMensajes tblMensaje = null;
		
		int activeMQ = 2;

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
			tblMensaje = tblMensajesDAO.get(idMensaje);

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
					List<TblDestinatariosMensajes> destinatarios = destinatariosMensajesManager
							.getDestinatarioMensajes(idMensaje);
					Long canal = Long.parseLong(ps.getMessage(R_CONST_5, null));
					Integer modo = Integer.parseInt(ps.getMessage(R_CONST_21, null));
					if (tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().equals(canal)) {
						checkEmailMode(idMensaje, tblMensaje, destinatarios, modo);
					} else {
						sendMessages(idMensaje, tblMensaje, destinatarios);
						
					}
					//Comprobamos que si ya se ha actualizado la tabla de errores a true
					activeMQ = 1;//true
				}
				return codCorrecto.intValue();
			} else {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorBBDD, errorBBDD);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorBBDD.intValue();
			}
		} catch (CannotCreateTransactionException e) {
			//Comprobamos que si ya se ha actualizado la tabla de errores a false
			LOG.error(errorActiveMq+R_CONST_18, e);
			activeMQ = 0;//false
			

			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios()
					.getServicioid());
			if (servicio.getPremium() != null && servicio.getPremium()) { //Es premium revolvemos KO y anulamos
				setEstadoMensaje(tblMensaje.getMensajeid(), estadoAnulado, descripcionErrorActiveMq, false, null, null,
						usuario, null);
				return Integer.parseInt(ps.getMessage(R_CONST_23, null));
			} else {// no es premium devolvemos OK
				return codCorrecto.intValue();
			}

		} catch (Exception e) {
			LOG.error(ps.getMessage(R_CONST_9, null), e);
			return Integer.parseInt(ps.getMessage(R_CONST_23, null));
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug(R_CONST_11);					
			switch (activeMQ) {
			case 0:
				erroresManager.comprobarActiveMqActivo(false);
				break;
			case 1:
				erroresManager.comprobarActiveMqActivo(true);
				break;
			}
		}
	}
	@Override
	@Transactional
	public Integer operacionMensajeReenviar(Long idMensaje, String usuario, String password, String estadoFinal) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorActiveMq = ps.getMessage(R_CONST_10, null, R_CONST_22);
		String operacionReenviar = ps.getMessage(R_CONST_16, null);
		String operacionReenviarCorrecto = ps.getMessage(
				R_CONST_13, null);
		String errorMensajeIncorrecto = ps.getMessage(R_CONST_1, null);
		Long codErrorMensajeIncorrecto = Long.parseLong(ps.getMessage(R_CONST_2,
				null));
		String errorUsuarioAplicacion = ps.getMessage(R_CONST_6, null);
		Long codErrorUsuarioAplicacion = Long.parseLong(ps.getMessage(R_CONST_26,
				null));
		String errorMensajeYaEnviado = ps.getMessage(R_CONST_30, null);
		Long codErrorMensajeYaEnviado = Long.parseLong(ps.getMessage(R_CONST_19,
				null));
		String errorMensajeAplicacion = ps.getMessage(R_CONST_7, null);
		Long codErrorMensajeAplicacion = Long.parseLong(ps.getMessage(R_CONST_15,
				null));
		String errorBBDD = ps.getMessage(R_CONST_34, null);
		Long codErrorBBDD = Long.parseLong(ps.getMessage(R_CONST_17, null));
		Long codCorrecto = Long.parseLong(ps.getMessage(R_CONST_29, null));
		String estadoEnviado = ps.getMessage(R_CONST_20, null);
		String estadoAnulado = estadosManager.getEstadoByName(ps.getMessage(R_CONST_4, null))
				.getNombre();
		String descripcionErrorActiveMq = ps.getMessage(R_CONST_12, null);
		TblMensajes tblMensaje = null;
		
		int activeMQ = 2;

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
			tblMensaje = tblMensajesDAO.get(idMensaje);

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

			if (setEstadoMensajeNoEnviados(idMensaje, estadoFinal, null, false, null, null, usuario, null) > 0) {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codCorrecto, operacionReenviarCorrecto);
				auditoriaManager.insertarAuditoria(auditoria);

				if (!estadoFinal.equalsIgnoreCase(estadoAnulado)) {
					List<TblDestinatariosMensajes> destinatarios = destinatariosMensajesManager
							.getDestinatarioMensajesNoEnviado(idMensaje);
					Long canal = Long.parseLong(ps.getMessage(R_CONST_5, null));
					Integer modo = Integer.parseInt(ps.getMessage(R_CONST_21, null));
					if (tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid().equals(canal)) {
						checkEmailMode(idMensaje, tblMensaje, destinatarios, modo);
					} else {
						sendMessages(idMensaje, tblMensaje, destinatarios);
						
					}
					//Comprobamos que si ya se ha actualizado la tabla de errores a true
					activeMQ = 1;//true
				}
				return codCorrecto.intValue();
			} else {
				AuditoriaBean auditoria = new AuditoriaBean(operacionReenviar, new Date(), null, idMensaje, null, null,
						usuario, password, codErrorBBDD, errorBBDD);
				auditoriaManager.insertarAuditoria(auditoria);
				return codErrorBBDD.intValue();
			}
		} catch (CannotCreateTransactionException e) {
			//Comprobamos que si ya se ha actualizado la tabla de errores a false
			LOG.error(errorActiveMq+R_CONST_18, e);
			activeMQ = 0;//false
			

			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios()
					.getServicioid());
			if (servicio.getPremium() != null && servicio.getPremium()) { //Es premium revolvemos KO y anulamos
				setEstadoMensaje(tblMensaje.getMensajeid(), estadoAnulado, descripcionErrorActiveMq, false, null, null,
						usuario, null);
				return Integer.parseInt(ps.getMessage(R_CONST_23, null));
			} else {// no es premium devolvemos OK
				return codCorrecto.intValue();
			}

		} catch (Exception e) {
			LOG.error(ps.getMessage(R_CONST_9, null), e);
			return Integer.parseInt(ps.getMessage(R_CONST_23, null));
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug(R_CONST_11);					
			switch (activeMQ) {
			case 0:
				erroresManager.comprobarActiveMqActivo(false);
				break;
			case 1:
				erroresManager.comprobarActiveMqActivo(true);
				break;
			}
		}
	}
	private void checkEmailMode(Long idMensaje, TblMensajes tblMensaje, List<TblDestinatariosMensajes> destinatarios,
			Integer modo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		if (tblMensaje.getModo().equals(modo)) {
			StringBuilder destBuilder = new StringBuilder();
			for (TblDestinatariosMensajes destinatario : destinatarios) {
				destBuilder.append(destinatario.getDestinatariosmensajes() + R_CONST_14);
			}
			MensajeJMS mensajeJms = new MensajeJMS();
			mensajeJms.setIdMensaje(idMensaje.toString());
//			mensajeJms.setIdExterno(tblMensaje.getCodigoexterno());
			mensajeJms.setDestinatarioMensajeId(destBuilder.toString());
			mensajeJms.setIdLote(tblMensaje.getTblLotesEnvios().getLoteenvioid().toString());
			Long maxRetries = null;
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid()
					.toString());
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios()
					.getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage(R_CONST_33, null));
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
			mensajeJms.setIdCanal(tblMensaje.getTblLotesEnvios().getTblServicios().getTblCanales().getCanalid()
					.toString());
			mensajeJms.setDestinatarioMensajeId(destinatario.getDestinatariosmensajes().toString());
			mensajeJms.setIdLote(tblMensaje.getTblLotesEnvios().getLoteenvioid().toString());
			Long maxRetries = null;
			TblServicios servicio = serviciosManager.getServicio(tblMensaje.getTblLotesEnvios().getTblServicios()
					.getServicioid());
			if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
				maxRetries = servicio.getNumeroMaxReenvios().longValue();
			} else {
				maxRetries = Long.parseLong(ps.getMessage(R_CONST_33, null));
			}
			sender.send(mensajeJms, maxRetries, servicio.getServicioid().toString(), false);
		}
	}
	
	@Override
	public List<Long> getMensajesAnular(String listaServicios, Date fecha,
			Date fechaFin) {
			return queryExecutorMensajes.getAnularMensajes(listaServicios,fecha,fechaFin);		
	}

	@Override
	@Transactional
	public synchronized Integer setEstadoMensaje(Long idMensaje, String estado, String descripcion,
			Boolean controlReintentos, Long destinatarioMensajeId, String subEstadoCode, String usuario,
			Long proveedorId) {

		Integer res = 1;
		String uim = null;
		Long servidorId = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		try {
			if (null == proveedorId) {
				servidorId = queryExecutorServidores.obtenerServidorByIdMensaje(idMensaje);
			} else {
				servidorId = proveedorId;
			}

			// Actualizamos la tabla mensaje

			TblMensajes mensaje = tblMensajesDAO.get(idMensaje);

			if (mensaje != null && ps.getMessage(TIPOMENSAJESMS, null).equals(mensaje.getTipomensaje()) && null != descripcion
					&& descripcion.contains(R_CONST_36) && descripcion.contains(R_CONST_25)) {
				uim = descripcion.substring(descripcion.indexOf('|') + 1).trim();
			}

			// aumentamos el numero de envios para todos los mensajes GISS y los
			// demas cuando el estado siguiente es
			// INCIDENCIA
			if (ps.getMessage(R_CONST_27, null).equals(estado)) {
				mensaje.setNumeroenvios(mensaje.getNumeroenvios() + 1);
			} else if (ps.getMessage(ESTADOPENDIENTE, null).equals(estado)) {
				mensaje.setNumeroenvios(0);
			}
			mensaje.setEstadoactual(estado);
			mensaje.setFechamodificacion(new Date());
			mensaje.setModificadopor(usuario);
			tblMensajesDAO.update(mensaje);

			List<TblDestinatariosMensajes> destinatariosMensaje = new ArrayList<>();
			if (null != destinatarioMensajeId) {
				destinatariosMensaje.add(destinatariosMensajesManager.getDestinatarioMensaje(destinatarioMensajeId));
			} else {
				destinatariosMensaje = destinatariosMensajesManager.getDestinatarioMensajes(idMensaje);
			}
			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {

				// Actualizamos la tabla destinatarios mensajes
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					destinatarioMensaje.setEstado(estado);
					destinatarioMensaje.setModificadopor(usuario);
					destinatarioMensaje.setFechamodificacion(new Date());
					destinatarioMensaje.setUltimoenvio(new Date());
					if (uim != null) {
						destinatarioMensaje.setUim(uim);
					}
					destinatariosMensajesManager.update(destinatarioMensaje);
				}
			}
			sessionFactorySIMApp.getCurrentSession().flush();

			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					hitoricosManager.creaHistorico(idMensaje, destinatarioMensaje.getDestinatariosmensajes(),
							estadosManager.getEstadoByName(estado).getEstadoid(), servidorId, descripcion,
							subEstadoCode, usuario);
				}
			} else {
				hitoricosManager.creaHistorico(idMensaje, null, estadosManager.getEstadoByName(estado).getEstadoid(),
						servidorId, descripcion, subEstadoCode, usuario);
			}

		} catch (Exception e) {
			LOG.error(ps.getMessage(R_CONST_9, null), e);
			return Integer.parseInt(ps.getMessage(R_CONST_23, null));
		}
		return res;
	}
	
	@Override
	@Transactional
	public synchronized Integer setEstadoMensajeNoEnviados(Long idMensaje, String estado, String descripcion,
			Boolean controlReintentos, Long destinatarioMensajeId, String subEstadoCode, String usuario,
			Long proveedorId) {

		Integer res = 1;
		String uim = null;
		Long servidorId = null;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		try {
			if (null == proveedorId) {
				servidorId = queryExecutorServidores.obtenerServidorByIdMensaje(idMensaje);
			} else {
				servidorId = proveedorId;
			}

			// Actualizamos la tabla mensaje

			TblMensajes mensaje = tblMensajesDAO.get(idMensaje);

			if (mensaje != null && ps.getMessage(TIPOMENSAJESMS, null).equals(mensaje.getTipomensaje()) && null != descripcion
					&& descripcion.contains(R_CONST_36) && descripcion.contains(R_CONST_25)) {
				uim = descripcion.substring(descripcion.indexOf('|') + 1).trim();
			}

			// aumentamos el numero de envios para todos los mensajes GISS y los
			// demas cuando el estado siguiente es
			// INCIDENCIA
			if (ps.getMessage(R_CONST_27, null).equals(estado)) {
				mensaje.setNumeroenvios(mensaje.getNumeroenvios() + 1);
			} else if (ps.getMessage(ESTADOPENDIENTE, null).equals(estado)) {
				mensaje.setNumeroenvios(0);
			}
			mensaje.setEstadoactual(estado);
			mensaje.setFechamodificacion(new Date());
			mensaje.setModificadopor(usuario);
			tblMensajesDAO.update(mensaje);

			List<TblDestinatariosMensajes> destinatariosMensaje = new ArrayList<>();
			if (null != destinatarioMensajeId) {
				destinatariosMensaje.add(destinatariosMensajesManager.getDestinatarioMensaje(destinatarioMensajeId));
			} else {
				destinatariosMensaje = destinatariosMensajesManager.getDestinatarioMensajesNoEnviado(idMensaje);
			}
			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {

				// Actualizamos la tabla destinatarios mensajes
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					destinatarioMensaje.setEstado(estado);
					destinatarioMensaje.setModificadopor(usuario);
					destinatarioMensaje.setFechamodificacion(new Date());
					destinatarioMensaje.setUltimoenvio(new Date());
					if (uim != null) {
						destinatarioMensaje.setUim(uim);
					}
					destinatariosMensajesManager.update(destinatarioMensaje);
				}
			}
			sessionFactorySIMApp.getCurrentSession().flush();

			if (mensaje.getTblLotesEnvios().getMultidestinatario()) {
				for (TblDestinatariosMensajes destinatarioMensaje : destinatariosMensaje) {
					hitoricosManager.creaHistorico(idMensaje, destinatarioMensaje.getDestinatariosmensajes(),
							estadosManager.getEstadoByName(estado).getEstadoid(), servidorId, descripcion,
							subEstadoCode, usuario);
				}
			} else {
				hitoricosManager.creaHistorico(idMensaje, null, estadosManager.getEstadoByName(estado).getEstadoid(),
						servidorId, descripcion, subEstadoCode, usuario);
			}

		} catch (Exception e) {
			LOG.error(ps.getMessage(R_CONST_9, null), e);
			return Integer.parseInt(ps.getMessage(R_CONST_23, null));
		}
		return res;
	}

	@Override
	@Transactional
	public TblMensajes getMensajeIDByUIM(String uim){
		TblMensajes mensaje;

		try {
			TblDestinatariosMensajes dm = destinatariosMensajesManager.getDestinatarioMensajeByUim(uim);
			if (null == dm) {// no es multidestinatario
				mensaje = getMensajeByUim(uim);
			} else {
				mensaje = tblMensajesDAO.get(dm.getMensajeid());
			}

			mensaje.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(mensaje.getTblLotesEnvios().getLoteenvioid()));

		} catch (Exception e) {
			LOG.debug("[TblMensajesManagerImpl] - Error en bbdd", e);
			return null;
		}
		return mensaje;
	}

	@Override
	@Transactional
	public int updateMessagesUsers(List<String> usersId, String estadoInicial, String estadoFinal, String usuarioPeticion) {
		int res = 1;
		Map<Long,List<Long>> mapMensajesMult;
		
		LOG.info(R_CONST_37+estadoInicial+R_CONST_38+estadoFinal);
		try{
			String usuarios = usersId.toString().replace(R_CONST_24, R_CONST_8).replace(R_CONST_28, R_CONST_8)
		            .replace(R_CONST_35, R_CONST_31);
			
			//recuperamos los mensajes multides
			mapMensajesMult = queryExecutorMensajes.getMensajesPorUsuariosPushYEstadoMultidest(usuarios, estadoInicial);
			
			//actualizamos los obtenidos
			if (!mapMensajesMult.isEmpty()) {
				
				//actualizamos los mensajes multidest a Recibido y son multidestinatarios
				res = setEstadoMensajeUsuarios(mapMensajesMult, estadoInicial, estadoFinal, usuarioPeticion);
			}
			
			//comentado porque la query tarda mucho y no es necesarioa, porque nunca ha existido un mensaje push
			//que NO sea multidestinatario
//			//recuperamos mensajes no MULTIDESTINATARIO
//			listaMensajes = queryExecutorMensajes.getMensajesPorUsuariosPushYEstado(usuarios, estadoInicial);
//			if (null != listaMensajes && !listaMensajes.isEmpty()){
//				for (Long mensajeId : listaMensajes) {
//					res = setEstadoMensaje(mensajeId, estadoFinal, null, false, null, null, usuarioPeticion, null);
//				}
//				
//			}
		}catch(Exception e){
			LOG.error(R_CONST_3, e);
			res = -1;
		}
		
		return res;
	}

	@Override
	@Transactional
	public int updateMessagesUser(List<String> usersId, String estadoInicial, String estadoFinal, String usuarioPeticion, String mensajeId) {
		int res = 1;
		Map<Long,List<Long>> mapMensajesMult;
		
		LOG.info(R_CONST_37+estadoInicial+R_CONST_38+estadoFinal);
		try{
			String usuarios = usersId.toString().replace(R_CONST_24, R_CONST_8).replace(R_CONST_28, R_CONST_8)
		            .replace(R_CONST_35, R_CONST_31);
			
			//recuperamos los mensajes multides
			mapMensajesMult = queryExecutorMensajes.getMensajesPorUsuariosPushYEstadoYMensajeMultidest(usuarios, estadoInicial, Long.valueOf(mensajeId));
			
			//actualizamos los obtenidos
			if (!mapMensajesMult.isEmpty()) {
				
				//actualizamos los mensajes multidest a Recibido y son multidestinatarios
				res = setEstadoMensajeUsuarios(mapMensajesMult, estadoInicial, estadoFinal, usuarioPeticion);
			}
			
			//comentado porque la query tarda mucho y no es necesarioa, porque nunca ha existido un mensaje push
			//que NO sea multidestinatario
//			//recuperamos mensajes no MULTIDESTINATARIO
//			listaMensajes = queryExecutorMensajes.getMensajesPorUsuariosPushYEstado(usuarios, estadoInicial);
//			if (null != listaMensajes && !listaMensajes.isEmpty()){
//				for (Long mensajeId : listaMensajes) {
//					res = setEstadoMensaje(mensajeId, estadoFinal, null, false, null, null, usuarioPeticion, null);
//				}
//				
//			}
		}catch(Exception e){
			LOG.error(R_CONST_3, e);
			res = -1;
		}
		
		return res;
	}
	
	@Override
	public Boolean isMessageUser(List<String> usersId, Long mensajeId) {
		if (tblMensajesDAO.get(mensajeId).getTblLotesEnvios().getMultidestinatario()) {
			return !destinatariosMensajesManager.getDestinatarioMensajesUsuarios(mensajeId, usersId, null).isEmpty();
		} else {
			TblMensajesQuery queryM = new TblMensajesQuery();
			queryM.setMensajeid(mensajeId);
			for (String s : usersId) {
				queryM.addUsuarioidIn(Long.parseLong(s));
			}
			return !tblMensajesDAO.search(queryM).getResults().isEmpty();
		}
	}

	@Override
	public synchronized Integer setEstadoMensajeUsuarios(Map<Long, List<Long>> mapMensajesMult, String estadoInicial,
			String estadoFinal, String usuarioPeticion) {
		Integer res = -1;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		try {

			for (Map.Entry<Long, List<Long>> entry : mapMensajesMult.entrySet()) {
				Long mensajeId = entry.getKey();
				Long servidorId = queryExecutorServidores.obtenerServidorByIdMensaje(mensajeId);
				List<Long> listaDestinatarios = entry.getValue();
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
			LOG.error(ps.getMessage(R_CONST_9, null), e);
			return Integer.parseInt(ps.getMessage(R_CONST_23, null));
		}
		return res;
	}

	@Override
	public List<Aviso> getAvisosUsuario(String idDispositivo, String idPlataforma, String idServicio, String idUsuario,
			String numPagina, String tamPagina, PropertiesServices ps) {
		List<Long> listaUsuarios = new ArrayList<>();

		if (null != idUsuario) {
			listaUsuarios.add(usuariosPushManager.getDatosUsuario(idServicio, idPlataforma, idDispositivo, idUsuario,
					true).getUsuarioid());
		} else {
			listaUsuarios = usuariosPushManager.getDatosUsuario(idServicio, idPlataforma, idDispositivo);
		}

		String usuarios = listaUsuarios.toString().replace(R_CONST_24, R_CONST_8).replace(R_CONST_28, R_CONST_8).replace(R_CONST_35, R_CONST_31);

		return queryExecutorMensajes.getAvisosMensajeUsuario(usuarios, numPagina, tamPagina, ps);
	}

	@Override
	public Boolean getMultidestinatarioByMensaje(Long mensajeId) {
		return tblMensajesDAO.get(mensajeId).getTblLotesEnvios().getMultidestinatario();

	}

	@Override
	public Map<Long, List<MensajeJMS>> getMensajesReenviar(String serviciosExcluidos, Date fechaInicio, Date fechaFin, String serviciosIncluidos) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String estadoPend = ps.getMessage(ESTADOPENDIENTE, null);
				
		List<Long> listaMensajes = queryExecutorMensajes.getMensajesPendientes(fechaInicio,fechaFin,serviciosExcluidos,serviciosIncluidos);
		Long canalMail = Long.parseLong(ps.getMessage(R_CONST_5, null));
		Map<Long, List<MensajeJMS>> map = new HashMap<>();

		for (Long mensajeId : listaMensajes) {
			TblMensajes mensaje = tblMensajesDAO.get(mensajeId);
			mensaje.setTblLotesEnvios(lotesEnviosManager.getLoteEnvioById(mensaje.getTblLotesEnvios().getLoteenvioid()));
			TblServicios servicio = serviciosManager.getServicio(mensaje.getTblLotesEnvios().getTblServicios()
					.getServicioid());
			List<TblDestinatariosMensajes> listaDestinatarios = destinatariosMensajesManager
					.getDestinatarioMensajesPendientes(mensaje.getMensajeid(), estadoPend);

			if (!servicio.getTblCanales().getCanalid().equals(canalMail)) {
				destinatariosStandart(map, mensaje, servicio, listaDestinatarios);

			} else {
				if (null != mensaje.getModo() && mensaje.getModo() == 0) {
					destinatariosUnidos(map, mensaje, servicio, listaDestinatarios);
				} else {
					destinatariosStandart(map, mensaje, servicio, listaDestinatarios);
				}

			}
		}
		return map;
	}
	
	@Override
	@Transactional
	public void delete(Long mensajeid) {
		tblMensajesDAO.delete(mensajeid);
	}

	@Override
	public List<TblMensajes> getMensajesByQuery(TblMensajesQuery query) {
		return tblMensajesDAO.search(query).getResults();
	}
	
	@Override
	public Integer countMensajesByQuery(TblMensajesQuery query) {
		return tblMensajesDAO.count(query);
	}
	
	
	@Override
	public TblServicios getServicioByMensaje(Long mensajeId) {
		TblMensajes mensaje = tblMensajesDAO.get(mensajeId);
		return mensaje.getTblLotesEnvios().getTblServicios();
	}

	private void destinatariosUnidos(Map<Long, List<MensajeJMS>> map, TblMensajes mensaje, TblServicios servicio,
			List<TblDestinatariosMensajes> listaDestinatarios) {
		StringBuilder mensajes = new StringBuilder();
		String idExterno = "";
		for (TblDestinatariosMensajes dm : listaDestinatarios) {
			mensajes.append(dm.getDestinatariosmensajes() + R_CONST_14);
			idExterno = dm.getCodigoexterno();
		}
		if (map.containsKey(servicio.getServicioid())) {
			addNewMensajeJMSToArray(map, mensaje, servicio, mensajes.toString(), idExterno);

		} else {
			addNewItemMap(map, mensaje, servicio, mensajes.toString(), idExterno);

		}
	}

	private void destinatariosStandart(Map<Long, List<MensajeJMS>> map, TblMensajes mensaje, TblServicios servicio,
			List<TblDestinatariosMensajes> listaDestinatarios) {
		for (TblDestinatariosMensajes dm : listaDestinatarios) {
			if (map.containsKey(servicio.getServicioid())) {
				addNewMensajeJMSToArray(map, mensaje, servicio, dm.getDestinatariosmensajes().toString(),
						dm.getCodigoexterno());
			} else {
				addNewItemMap(map, mensaje, servicio, dm.getDestinatariosmensajes().toString(), dm.getCodigoexterno());
			}
		}
	}

	private void addNewItemMap(Map<Long, List<MensajeJMS>> map, TblMensajes mensaje, TblServicios servicio,
			String destinatarioMensajeId, String codigoExterno) {
		MensajeJMS mensajeJms = new MensajeJMS();
		mensajeJms.setIdMensaje(mensaje.getMensajeid().toString());
//		mensajeJms.setIdExterno(codigoExterno);
		mensajeJms.setDestinatarioMensajeId(destinatarioMensajeId);
		mensajeJms.setIdCanal(servicio.getTblCanales().getCanalid().toString());
//		mensajeJms.setServicio(servicio.getServicioid().toString());
		mensajeJms.setIdLote(mensaje.getTblLotesEnvios().getLoteenvioid().toString());

		List<MensajeJMS> listado = new ArrayList<>();
		listado.add(mensajeJms);
		map.put(servicio.getServicioid(), listado);
	}

	private void addNewMensajeJMSToArray(Map<Long, List<MensajeJMS>> map, TblMensajes mensaje, TblServicios servicio,
			String destinatarioMensajeId, String codigoExterno) {
		MensajeJMS mensajeJms = new MensajeJMS();
		mensajeJms.setIdMensaje(mensaje.getMensajeid().toString());
//		mensajeJms.setIdExterno(codigoExterno);
		mensajeJms.setDestinatarioMensajeId(destinatarioMensajeId);
		mensajeJms.setIdCanal(servicio.getTblCanales().getCanalid().toString());
//		mensajeJms.setServicio(servicio.getServicioid().toString());
		mensajeJms.setIdLote(mensaje.getTblLotesEnvios().getLoteenvioid().toString());
		
		map.get(servicio.getServicioid()).add(mensajeJms);
	}

	private TblMensajes getMensajeByUim(String uim) {
		TblMensajes mensaje;
		TblMensajesQuery q = new TblMensajesQuery();
		q.setUim(uim);
		q.setUimComparator(TextComparator.EQUALS);
		return tblMensajesDAO.searchUnique(q);
	}

	/**
	 * @return the sessionFactorySIMApp
	 */
	public SessionFactory getSessionFactorySIMApp() {
		return sessionFactorySIMApp;
	}

	/**
	 * @param sessionFactorySIMApp
	 *            the sessionFactorySIMApp to set
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
	 * @param aplicacionesManager
	 *            the aplicacionesManager to set
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
	 * @param viewLotesManager
	 *            the viewLotesManager to set
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
	 * @param auditoriaManager
	 *            the auditoriaManager to set
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
	 * @param hitoricosManager
	 *            the hitoricosManager to set
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
	 * @param viewUsiariosPushManager
	 *            the viewUsiariosPushManager to set
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
	 * @return the usuariosPushManager
	 */
	public TblUsuariosPushManager getUsuariosPushManager() {
		return usuariosPushManager;
	}

	/**
	 * @param usuariosPushManager
	 *            the usuariosPushManager to set
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
	 * @param queryExecutorServidores
	 *            the queryExecutorServidores to set
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
	 * @param queryExecutorLotesEnvios
	 *            the queryExecutorLotesEnvios to set
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
	 * @param queryExecutorMensajes
	 *            the queryExecutorMensajes to set
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
	 * @param tblMensajesDAO
	 *            the tblMensajesDAO to set
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
	 * @param serviciosManager
	 *            the serviciosManager to set
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
	 * @param reloadableResourceBundleMessageSource
	 *            the reloadableResourceBundleMessageSource to set
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
	 * @param sender
	 *            the sender to set
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
	 * @param query
	 *            the query to set
	 */
	public void setQuery(ViewLotesEnviosDetalladaQuery query) {
		this.query = query;
	}

	

	

}
