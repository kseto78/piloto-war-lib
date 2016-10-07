package es.mpr.plataformamensajeria.util;

import java.util.HashMap;

/**
 * En esta clase se definen las constantes para determinar los errores
 * @author i-nercya
 *
 */
public class WSPlataformaErrors {
	
	static final String CREARLOTE_1 = "Error de Autenticación. No existe aplicación para el usuario/password.";
	static final String CREARLOTE_2 = "Servicio Incorrecto. No está asignado a ninguna aplicación.";
	static final String CREARLOTE_3 = "El servicio incorrecto o existe pero está inactivo.";
	static final String CREARLOTE_10 = "Error BBDD al crear el Lote.";
	
	static final String CREAREMAIL_1 = "Error de Autenticación. No existe lote para el usuario/password.";
	static final String CREAREMAIL_2 = "Canal Erróneo.";
	static final String CREAREMAIL_3 = "Formato de Destinatarios Incorrecto.";
	static final String CREAREMAIL_10 = "Error BBDD al crear el email.";
	
	static final String CREARANEXO_1 = "Error de Autenticación. No existe el mensaje para el usuario/password.";
	static final String CREARANEXO_10 = "Canal Erróneo.";
	
	
	static final String NUEVAIMAGEN_1 = "Error de Autenticación. No existe el mensaje para el usuario/password.";
	static final String NUEVAIMAGEN_10 = "Error en  BBDD al crear el email";
	
	static final String ASOCIARIMAGEN_1 = "Error de Autenticación. No existe el mensaje para el usuario/password.";
	static final String ASOCIARIMAGEN_2 = "Error de Autenticación. No existe la imagen para el usuario/password.";
	static final String ASOCIARIMAGEN_10 = "Error BBDD al asociar imagen";
	
	static final String ASOCIARANEXO_1 = "Error de Autenticación. No existe el mensaje para el usuario/password.";
	static final String ASOCIARANEXO_2 = "Error de Autenticación. No existe el anexo para el usuario/password.";
	static final String ASOCIARANEXO_10 = "Error BBDD al insertar Anexo";
		
	
	
	
	static final String CREARSMS_1 = "Error de Autenticación. No existe lote para el usuario/password.";
	static final String CREARSMS_2 = "Canal Erróneo";
	static final String CREARSMS_3 = "Formato de Destinatarios incorrecto";
	static final String CREARSMS_10 = "Error BBDD al crear el email";
	
	static final String REENVIARLOTE_1 = "Error de Autenticación. No existe aplicación para el usuario/password.";
	static final String REENVIARLOTE_2 = "Lote Incorrecto. No existe o no está asignado a la aplicación.";
	static final String REENVIARLOTE_3 = "Servicio inactivo o no existe.";
	static final String REENVIARLOTE_10 = "Error BBDD al actualizar el estado";
	
	static final String ANULARLOTE_1 = "Error de Autenticación. No existe aplicación para el usuario/password.";
	static final String ANULARLOTE_2 = "Lote Incorrecto. No existe o no está asignado a la aplicación.";
	static final String ANULARLOTE_3 = "Servicio inactivo o no existe.";
	static final String ANULARLOTE_10 = "Error BBDD al actualizar el estado";

	static final String REENVIARMENSAJE_1 = "Error de Autenticación. No existe aplicación para el usuario/password.";
	static final String REENVIARMENSAJE_2= "Mensaje incorrecto";
	static final String REENVIARMENSAJE_10 = "Error BBDD al actualizar el estado";

	static final String ANULARMENSAJE_1 = "Error de Autenticación. No existe aplicación para el usuario/password.";
	static final String ANULARMENSAJE_2= "Mensaje incorrecto";
	static final String ANULARMENSAJE_10 = "Error BBDD al actualizar el estado";	
	

	private static final HashMap<Integer,String> anularMensaje = new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 5580500057784184823L;

		{
			put(-1,ANULARMENSAJE_1);
			put(-2,ANULARMENSAJE_2);
			put(-10,ANULARMENSAJE_10);
		}
	};
	private static final HashMap<Integer,String> reenviarMensaje = new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 8796739515580960990L;

		{
			put(-1,REENVIARMENSAJE_1);
			put(-2,REENVIARMENSAJE_2);
			put(-10,REENVIARMENSAJE_10);
		}
	};
	private static final HashMap<Integer,String> anularLote = new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7478042698710818740L;

		{
			put(-1,ANULARLOTE_1);
			put(-2,ANULARLOTE_2);
			put(-3,ANULARLOTE_3);
			put(-10,ANULARLOTE_10);
		}
	};
	private static final HashMap<Integer,String> reenviarLote = new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -7712451762701792353L;

		{
			put(-1,REENVIARLOTE_1);
			put(-2,REENVIARLOTE_2);
			put(-3,REENVIARLOTE_3);
			put(-10,REENVIARLOTE_10);
		}
	};
	
	private static final HashMap<Integer,String> crearLoteErrores = new HashMap<Integer, String>(){
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
	private static final HashMap<Integer,String> crearImagen= new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), NUEVAIMAGEN_1);
			put(new Integer(-10), NUEVAIMAGEN_10);
		}
	};
	private static final HashMap<Integer,String> asociarImagen= new HashMap<Integer, String>(){
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
	private static final HashMap<Integer,String> crearAnexo= new HashMap<Integer, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(new Integer(-1), CREARANEXO_1);
			put(new Integer(-10), CREARANEXO_10);
		}
	};
	private static final HashMap<Integer,String> asociarAnexo= new HashMap<Integer, String>(){
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
	private static final HashMap<Integer,String> crearEmail= new HashMap<Integer, String>(){
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
	private static final HashMap<Integer,String> crearSMS= new HashMap<Integer, String>(){
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
	public static String getErrorCrearLote(Integer idLote){
		if(idLote==null){
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
		if(idMensaje==null){
			return "Error desconocido creando email";
		}
		return crearEmail.get(idMensaje);
	}
	/**
	 * 
	 * @param idAnexo
	 * @return
	 */
	public static String getErrorNuevaImagen(Integer idImagen){
		if(idImagen==null){
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
		if(salida==null){
			return "Error desconocido asociando imagen";
		}
		return asociarImagen.get(salida);
	}
	/**
	 * 
	 * @param idAnexo
	 * @return
	 */
	public static String getErrorCrearAnexo(Integer idAnexo){
		if(idAnexo==null){
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
		if(salida==null){
			return "Error desconocido asociando anexo";
		}
		return asociarAnexo.get(salida);
	}
	/**
	 * 
	 * @param idSMS
	 * @return
	 */
	public static String getErrorCrearSMS(Integer idSMS){
		if(idSMS==null){
			return "Error desconocido creando SMS";
		}
		return crearSMS.get(idSMS);
	}
	/**
	 * 
	 * @param idLote
	 * @return
	 */
	public static String getErrorReenviarLote(Integer idLote){
		if(idLote==null){
			return "Error desconocido reenviando lote";
		}
		return reenviarLote.get(idLote);
	}
	/**
	 * 
	 * @param idLote
	 * @return
	 */
	public static String getErrorAnularLote(Integer idLote){
		if(idLote==null){
			return "Error desconocido anulando lote";
		}
		return anularLote.get(idLote);
	}
	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getErrorReenviarMensaje(Integer idMensaje){
		if(idMensaje==null){
			return "Error desconocido reenviando mensaje";
		}
		return reenviarMensaje.get(idMensaje);
	}
	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getErrorAnularMensaje(Integer idMensaje){
		if(idMensaje==null){
			return "Error desconocido anulando mensaje";
		}
		return anularMensaje.get(idMensaje);
	}			
}

