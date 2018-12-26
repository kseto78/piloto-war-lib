package es.minhap.plataformamensajeria.iop.util;


/**
 * En esta clase se definen las constantes para determinar mensajes de Auditoria
 * 
 * @author everis
 * 
 */
public class MensajesAuditoria {

	//Auditoria Lotes
//	public static final String OPERACION_LOTE_CREAR = "Crear Lote";
//	public static final String OPERACION_LOTE_CORRECTO = "Lote Creado Correctamente";

	
	//Auditoria SMS
	public static final String OPERACION_MENSAJE_SMS_CREAR = "CREAR_MENSAJE_SMS";
	public static final String OPERACION_MENSAJE_PUSH_CREAR = "CREAR_MENSAJE_NOTIFICACION_PUSH";
	public static final String OPERACION_MENSAJE_EMAIL_CREAR = "CREAR_MENSAJE_EMAIL";

	
	//Auditoria Adjuntos
		public static final String OPERACION_NUEVO_ANEXO = "NUEVO_ANEXO_EMAIL";
		public static final String OPERACION_ASOCIAR_ANEXO = "ASOCIAR_ANEXO_EMAIL";
		public static final String ERROR_NO_EXISTE_MENSAJE = "No existe el Mensaje";
		public static final String ANEXO_CREADO = "Anexo creado Correctamente";
		public static final String ANEXO_ASOCIADO = "Anexo Asociado Correctamente";
		public static final String ERROR_NO_EXISTE_ANEXO = "NO EXISTE EL ANEXO PARA EL USUARIO/PASSWORD";
	
	//Errores
//	public static final String ERROR_USUARIO_APLICACION = "Usuario y Contraseña Incorrectos o la aplicacion esta inactiva";
	public static final String ERROR_SERVICIO_INCORRECTO = "El servicio no existe o no es de esta aplicacion";
	public static final String ERROR_SERVICIO_INACTIVO = "El servicio esta inactivo";
	public static final String ERROR_ORGANISMO_INCORRECTO_INACTIVO = "No se reconoce el organismo o no esta activo";
	public static final String ERROR_LOTE_APLICACION = "Lote Incorrecto o lote no permitido para la Aplicacion";
//	public static final Long COD_ERROR_USUARIO_APLICACION = -1L;
	public static final Long COD_ERROR_SERVICIO_INCORRECTO = -2L;
	public static final Long COD_ERROR_SERVICIO_INACTIVO = -3L;
	public static final Long COD_ERROR_ORGANISMO_INCORRECTO_INACTIVO = -4L;
	public static final Long COD_ERROR_LOTE_APLICACION = -1L;
	public static final String ERROR_CANAL_MENSAJE =  "Canal Incorrecto";
	public static final Long COD_ERROR_CANAL_MENSAJE =  -2L;
	public static final String ERROR_USUARIO_PUSH_NO_EXISTE = "Nombre usuario destinatario Incorrecto";
	public static final Long COD_ERROR_USUARIO_NO_EXISTE = -3L;
	public static final String ERROR_DESTINATARIOS_EMAIL_TO = "Error en el Formato de Destinatarios (TO)";
	public static final String ERROR_DESTINATARIOS_EMAIL_CC = "Error en el Formato de Destinatarios (CC)";
	public static final String ERROR_DESTINATARIOS_EMAIL_BCC = "Error en el Formato de Destinatarios (BCC)";
	public static final Long COD_ERROR_DESTINATARIOS_EMAIL = -3L;
	public static final String ERROR_BBDD = "Error en BBDD";
	public static final Long COD_ERROR_BBDD = -10L;
	public static final Long COD_ERROR_1 = -1L;
	public static final Long COD_ERROR_2 = -2L;
	
	
	//mensaje inserción correcta
	public static final String MENSAJE_PUSH_CORRECTO = "Mensaje Notificacion Push Creado Correctamente";
	public static final String MENSAJE_SMS_CORRECTO = "Mensaje SMS Creado Correctamente";
	public static final String MENSAJE_EMAIL_CORRECTO = "Mensaje Email Creado Correctamente";
	
	
	//mensaje recepcion sms
	public static final String COMPROBAR_SERVICIO_SERVICIO_INCORRECTO = "-1";
	public static final String COMPROBAR_SERVICIO_SERVICIO_DUPLICADO = "-2";
	public static final String ERROR_MAS_DE_UN_SERVIDOR = "-4";
	public static final String ERROR_NO_EXISTE_RELACION_SERVIDOR_SERVICIO = "-5";
	public static final String ERROR_DESTINATARIO_MENSAJE = "Error en el Formato del Telefono";
	public static final Long COD_ERROR_DESTINATARIO_MENSAJE = -3L;
	
}
