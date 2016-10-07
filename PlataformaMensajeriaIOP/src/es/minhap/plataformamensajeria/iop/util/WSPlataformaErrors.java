package es.minhap.plataformamensajeria.iop.util;

import java.util.HashMap;

/**
 * En esta clase se definen las constantes para determinar los errores
 * 
 * @author i-nercya
 * 
 */
public class WSPlataformaErrors {

	static final String CREARLOTE_1 = "Error de Autenticacion. No existe aplicacion para el usuario/password.";
	static final String CREARLOTE_2 = "Servicio Incorrecto. No esta asignado a ninguna aplicacion.";
	static final String CREARLOTE_3 = "El servicio incorrecto o existe pero esta inactivo.";
	static final String CREARLOTE_10 = "Error BBDD al crear el Lote.";

	static final String CREAREMAIL_1 = "Error de Autenticacion. No existe lote para el usuario/password.";
	static final String CREAREMAIL_2 = "Canal Erroneo.";
	static final String CREAREMAIL_3 = "Formato de Destinatarios Incorrecto.";
	static final String CREAREMAIL_10 = "Error BBDD al crear el email.";

	static final String CREARANEXO_1 = "Error de Autenticacion. No existe el mensaje para el usuario/password.";
	static final String CREARANEXO_10 = "Canal Erroneo.";

	static final String NUEVAIMAGEN_1 = "Error de Autenticacion. No existe el mensaje para el usuario/password.";
	static final String NUEVAIMAGEN_10 = "Error en  BBDD al crear el email";

	static final String ASOCIARIMAGEN_1 = "Error de Autenticacion. No existe el mensaje para el usuario/password.";
	static final String ASOCIARIMAGEN_2 = "Error de Autenticacion. No existe la imagen para el usuario/password.";
	static final String ASOCIARIMAGEN_10 = "Error BBDD al asociar imagen";

	static final String ASOCIARANEXO_1 = "Error de Autenticacion. No existe el mensaje para el usuario/password.";
	static final String ASOCIARANEXO_2 = "Error de Autenticacion. No existe el anexo para el usuario/password.";
	static final String ASOCIARANEXO_10 = "Error BBDD al insertar Anexo";

	static final String CREARSMS_1 = "Error de Autenticacion. No existe lote para el usuario/password.";
	static final String CREARSMS_2 = "Canal Erroneo";
	static final String CREARSMS_3 = "El numero de telefono no es valido";
	static final String CREARSMS_10 = "Error BBDD al crear el SMS";
	static final String CREARSMS_40 = "Error de Autenticacion. El campo Organismo pagador es obligatorio para los servicios multiorganismo";

	static final String REENVIARLOTE_1 = "Error de Autenticacion. No existe aplicacion para el usuario/password.";
	static final String REENVIARLOTE_2 = "Lote Incorrecto. No existe o no esta asignado a la aplicacion.";
	static final String REENVIARLOTE_3 = "Servicio inactivo o no existe.";
	static final String REENVIARLOTE_10 = "Error BBDD al actualizar el estado";

	static final String ANULARLOTE_1 = "Error de Autenticacion. No existe aplicacion para el usuario/password.";
	static final String ANULARLOTE_2 = "Lote Incorrecto. No existe o no esta asignado a la aplicacion.";
	static final String ANULARLOTE_3 = "Servicio inactivo o no existe.";
	static final String ANULARLOTE_10 = "Error BBDD al actualizar el estado";

	static final String REENVIARMENSAJE_1 = "Error de Autenticacion. No existe aplicacion para el usuario/password.";
	static final String REENVIARMENSAJE_2 = "Mensaje incorrecto";
	static final String REENVIARMENSAJE_3 = "El mensaje ya ha sido enviado y no se puede reenviar";
	static final String REENVIARMENSAJE_10 = "Error BBDD al actualizar el estado";

	static final String ANULARMENSAJE_1 = "Error de Autenticacion. No existe aplicacion para el usuario/password.";
	static final String ANULARMENSAJE_2 = "Mensaje incorrecto";
	static final String ANULARMENSAJE_3 = "El mensaje ya ha sido enviado y no se puede anular";
	static final String ANULARMENSAJE_10 = "Error BBDD al actualizar el estado";

	private static final HashMap<Integer, String> anularMensaje = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5580500057784184823L;

		{
			put(-1, ANULARMENSAJE_1);
			put(-2, ANULARMENSAJE_2);
			put(-3, ANULARMENSAJE_3);
			put(-10, ANULARMENSAJE_10);
		}
	};
	private static final HashMap<Integer, String> reenviarMensaje = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8796739515580960990L;

		{
			put(-1, REENVIARMENSAJE_1);
			put(-2, REENVIARMENSAJE_2);
			put(-3, REENVIARMENSAJE_3);
			put(-10, REENVIARMENSAJE_10);
		}
	};
	private static final HashMap<Integer, String> anularLote = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7478042698710818740L;

		{
			put(-1, ANULARLOTE_1);
			put(-2, ANULARLOTE_2);
			put(-3, ANULARLOTE_3);
			put(-10, ANULARLOTE_10);
		}
	};
	private static final HashMap<Integer, String> reenviarLote = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7712451762701792353L;

		{
			put(-1, REENVIARLOTE_1);
			put(-2, REENVIARLOTE_2);
			put(-3, REENVIARLOTE_3);
			put(-10, REENVIARLOTE_10);
		}
	};

	private static final HashMap<Integer, String> crearLoteErrores = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), CREARLOTE_1);
			put(new Integer(-2), CREARLOTE_2);
			put(new Integer(-3), CREARLOTE_3);
			put(new Integer(-10), CREARLOTE_10);
		}
	};
	private static final HashMap<Integer, String> crearImagen = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), NUEVAIMAGEN_1);
			put(new Integer(-10), NUEVAIMAGEN_10);
		}
	};
	private static final HashMap<Integer, String> asociarImagen = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), ASOCIARIMAGEN_1);
			put(new Integer(-2), ASOCIARIMAGEN_2);
			put(new Integer(-10), ASOCIARIMAGEN_10);
		}
	};
	private static final HashMap<Integer, String> crearAnexo = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), CREARANEXO_1);
			put(new Integer(-10), CREARANEXO_10);
		}
	};
	private static final HashMap<Integer, String> asociarAnexo = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), ASOCIARANEXO_1);
			put(new Integer(-2), ASOCIARANEXO_2);
			put(new Integer(-10), ASOCIARANEXO_10);
		}
	};
	private static final HashMap<Integer, String> crearEmail = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), CREAREMAIL_1);
			put(new Integer(-2), CREAREMAIL_2);
			put(new Integer(-3), CREAREMAIL_3);
			put(new Integer(-10), CREAREMAIL_10);
		}
	};
	private static final HashMap<Integer, String> crearSMS = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), CREARSMS_1);
			put(new Integer(-2), CREARSMS_2);
			put(new Integer(-3), CREARSMS_3);
			put(new Integer(-10), CREARSMS_10);
		}
	};

	/**
	 * 
	 * @param idLote
	 * @return
	 */
	public static String getErrorCrearLote(Integer idLote) {
		if (idLote == null) {
			return "Error desconocido creando lote";
		}
		return crearLoteErrores.get(idLote);
	}

	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getErrorCrearEmail(Integer idMensaje) {
		if (idMensaje == null) {
			return "Error desconocido creando email";
		}
		return crearEmail.get(idMensaje);
	}

	/**
	 * 
	 * @param idAnexo
	 * @return
	 */
	public static String getErrorNuevaImagen(Integer idImagen) {
		if (idImagen == null) {
			return "Error desconocido creando imagen";
		}
		return crearImagen.get(idImagen);
	}

	/**
	 * 
	 * @param salida
	 * @return
	 */
	public static String getErrorAsociarImagen(Integer salida) {
		if (salida == null) {
			return "Error desconocido asociando imagen";
		}
		return asociarImagen.get(salida);
	}

	/**
	 * 
	 * @param idAnexo
	 * @return
	 */
	public static String getErrorCrearAnexo(Integer idAnexo) {
		if (idAnexo == null) {
			return "Error desconocido creando anexo";
		}
		return crearAnexo.get(idAnexo);
	}

	/**
	 * 
	 * @param salida
	 * @return
	 */
	public static String getErrorAsociarAnexo(Integer salida) {
		if (salida == null) {
			return "Error desconocido asociando anexo";
		}
		return asociarAnexo.get(salida);
	}

	/**
	 * 
	 * @param idSMS
	 * @return
	 */
	public static String getErrorCrearSMS(Integer idSMS) {
		if (idSMS == null) {
			return "Error desconocido creando SMS";
		}
		return crearSMS.get(idSMS);
	}

	/**
	 * 
	 * @param idLote
	 * @return
	 */
	public static String getErrorReenviarLote(Integer idLote) {
		if (idLote == null) {
			return "Error desconocido reenviando lote";
		}
		return reenviarLote.get(idLote);
	}

	/**
	 * 
	 * @param idLote
	 * @return
	 */
	public static String getErrorAnularLote(Integer idLote) {
		if (idLote == null) {
			return "Error desconocido anulando lote";
		}
		return anularLote.get(idLote);
	}

	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getErrorReenviarMensaje(Integer idMensaje) {
		if (idMensaje == null) {
			return "Error desconocido reenviando mensaje";
		}
		return reenviarMensaje.get(idMensaje);
	}

	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getErrorAnularMensaje(Integer idMensaje) {
		if (idMensaje == null) {
			return "Error desconocido anulando mensaje";
		}
		return anularMensaje.get(idMensaje);
	}

	public static String getErrorFaltaIdExterno() {

		return "El campo IdExterno del mensaje es obligatorio";
	}

	public static String getErrorFaltaIdentificadorUsuario() {

		return "El campo Identificador de Usuario del mensaje es obligatorio";
	}

	public static String getErrorFaltaTitulo() {

		return "El campo Titulo del mensaje es obligatorio";
	}

	public static String getErrorFaltaCuerpo() {

		return "El campo Cuerpo del mensaje es obligatorio";
	}
	public static String getErrorFaltaOrganismoPagador() {

		return "El campo Organismo Pagador del mensaje es obligatorio";
	}

	public static String getErrorMultiOrganismoOrganismoPagador(){
		return CREARSMS_40;
	}

	public static String getErrorOrganismoNoAsociadoServicio(){
		return "El Organismo Pagador insertado no esta registrado para este servicio";
	}
	
	public static String getErrorCreandoNotificacion() {

		return "Hay mensajes con errores";
	}

	public static String getErrorFaltaCampoServicio() {

		return "El campo servicioID es obligario";
	}

	public static String getErrorGeneral() {

		return "La peticion no se ha podido procesar";
	}

	public static String getErrorFaltaUsuario() {

		return "El campo Usuario es obligatorio";
	}

	public static String getErrorFaltaPassword() {

		return "El campo Password es obligatorio";
	}

	public static String getErrorFaltaServicio() {

		return "El campo Servicio es obligatorio";
	}

	public static String getErrorFaltaLote() {

		return "El campo Nombre Lote es obligatorio";
	}

	public static String getErrorFaltaDestinatario() {

		return "El destinatario y el Id Externo son obligatorios";
	}

	public static String getErrorIdLoteIncorrecto() {

		return "El campo idlote es incorrecto";
	}

	public static String getErrorIdMensajeIncorrecto() {

		return "El campo mensajeid es incorrecto";
	}
	public static String getErrorFaltaAsunto() {

		return "El asunto del mensaje es obligatorio";
	}
}
