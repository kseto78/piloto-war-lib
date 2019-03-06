package es.minhap.plataformamensajeria.iop.util;


/**
 * En esta clase se definen las constantes para determinar los errores
 * 
 * @author i-nercya
 * 
 */
public class PlataformaErrores {

	//Estados y codigos de Error GISS
//		public static final String DESCRIPCION_COD_0_GISS = "SMS Tramitado";
//		public static final String COD_0_GISS = "0";
//		public static final String DESCRIPCION_COD_1_GISS = "Tramitandose el envio";
//		public static final String COD_1_GISS = "1";
//		public static final String DESCRIPCION_COD_10_GISS = "Error en los Campos de Entrada";
//		public static final String COD_10_GISS = "10";
//		public static final String DESCRIPCION_COD_20_GISS = "Valor de IdPeticion duplicado";
//		public static final String COD_20_GISS = "20";
//		public static final String ESTADO_NOENCONTRADO_GISS = "IdPetion no encontrado";
//		public static final String COD_NOENCONTRADO_GISS = "30";
//		public static final String DESCRIPCION_COD_40_GISS = "El Usuario de sistema envio no se encuentra en la BBDD";
//		public static final String COD_40_GISS = "40";
//		public static final String DESCRIPCION_COD_50_GISS = "El Usuario de sistema de envio no se corresponde con la Aplicacion";
//		public static final String COD_50_GISS= "50";
//		public static final String DESCRIPCION_COD_60_GISS = "Aplicacion no Activa";
//		public static final String COD_60_GISS = "60";
//		public static final String DESCRIPCION_COD_100_GISS = "SMS enviado a Derdack";
//		public static final String COD_100_GISS = "100";
//		public static final String DESCRIPCION_COD_105_GISS = "SMS esta siendo procesado en Derdack";
//		public static final String COD_105_GISS = "105";
//		public static final String DESCRIPCION_COD_110_GISS = "SMS enviado a Derdack, pero se ha producido un error";
//		public static final String COD_110_GISS = "110";
//		public static final String DESCRIPCION_COD_120_GISS = "SMS con error en la entrega, se ha superado el numero de reintentos";
//		public static final String COD_120_GISS = "120";
//		public static final String DESCRIPCION_COD_130_GISS = "Error en la invocacion a Derdack";
//		public static final String COD_130_GISS = "130";
//		public static final String DESCRIPCION_COD_200_GISS = "SMS Tramitado";
//		public static final String COD_200_GISS = "200";
//		public static final String DESCRIPCION_COD_205_GISS = "SMS entregado al destinatario";
//		public static final String COD_205_GISS = "205";
//		public static final String DESCRIPCION_COD_210_GISS = "Error procesando SMS en Derdack";
//		public static final String COD_210_GISS = "210";
//		public static final String DESCRIPCION_COD_300_GISS = "Error en BBDD de Comunicacion";
//		public static final String COD_300_GISS = "300";
//		public static final String DESCRIPCION_COD_600_GISS = "Error General";
//		public static final String COD_600_GISS = "600";
//		public static final String ERROR_RETURN_DERDACK_GISS = "-1";
		public static final String OK_RETURN_DERDACK_GISS = "0";
		public static final String NOMBRE_LOTE_GISS = "GISS";
		public static final String NUMERO_REINTENTOS_SUPERADO="Se ha superado el numero de reintentos:";
		////FIN CODIGOS GISS////	

		
	////Textos y ERRORES AEAT////
	public static final String COD_ERROR_ID_EXTERNO_AEAT ="2013";
	public static final String DETAILS_ERROR_ID_EXTERNO_AEAT ="Falta IdExterno";
	public static final String COD_ERROR_APLICACION = "0008";
	public static final String DETAILS_ERROR_APLICACION = "No existe aplicaci�n para el usuario/password";
	public static final String COD_ERROR_SERVIDOR_INCORRECTO = "0032";
	public static final String DETAILS_ERROR_SERVIDOR_INCORRECTO = "El servidor es incorrecto o est� inactivo";
	
	public static final String NOMBRE_LOTE_AEAT = "AEAT";
	////FIN CODIGOS AEAT////
	
	////CODIGOS USUARIOS PUSH////
	public static final String COD_ERROR_DISPOSITIVO_NO_ENCONTRADO = "4003";
	public static final String DETAILS_DISPOSITIVO_NO_ACTUALIZADO = "Error al actualizar la informacion del dispositivo. ";
	public static final String DETAILS_SAML_NO_VALIDADO = "Error al validar la peticion de Cl@ve";
	public static final String COD_SAML_NO_VALIDADO = "4001"; 
	public static final String DETAILS_DISPOSITIVO_NO_ENCONTRADO = "No se ha encontrado el identificador de dispositivo.";
	public static final String COD_ERROR_DATOS_NO_ACTUALIZADOS = "4002";
	public static final String DETAILS_ERROR_PETICION = "La peticion no esta construida correctamente. Faltan campos obligatorios o datos erroneos";
	public static final String COD_ERROR_PETICION = "0020";
	public static final String DETAILS_ERROR_SERVICIO = "El Usuario y Password no corresponden a ninguna aplicacion";
	public static final String COD_ERROR_SERVICIO = "4004";
	public static final String DETAILS_ERROR_USUARIO_DISPOSITIVO = "No se ha encontrado un usuario asociado al dispositivo";
	public static final String COD_ERROR_USUARIO_DISPOSITIVO = "4005";
	
	////FIN CODIGOS USUARIOS PUSH////
	
	
////CODIGOS GET AVISOS USUARIO////
	
////CODIGOS GET AVISOS USUARIO////
	

	public static final String STATUSCODE_KO = "2010";
	public static final String STATUSCODE_ADJUNTOS_KO = "1007";
	public static final String STATUSCODE_PETICION_MAIL_KO = "1008";
	public static final String STATUSDETAILS_KO = "Error en la peticion";

	
	////ERRORES GENERALES////
	public static final String STATUSTEXT_KO = "KO";
	public static final String STATUSTEXT_OK = "OK";
	public static final String STATUS_OK = "0000";
	public static final String DETAILS_OK = "Todo correcto";
	public static final String COD_ERROR_GENERAL = "0999";
	public static final String COD_ERROR_CREDITO = "2016";
	public static final String DETAILS_ERROR_CREDITO = "No dispone de saldo suficiente";
	public static final String DETAILS_ERROR_GENERAL = "Error desconocido general";
	public static final String COD_ERROR_AUTENTICACION = "2017";
	public static final String DETAILS_ERROR_AUTENTICACION = "Fallo de autenticacion";
	public static final String ERROR_DESTINATARIO = "Telefono destinatario no valido";
	public static final String ERROR_SERVICIO_INCORRECTO = "Servicio Incorrecto. No esta asignado a ninguna aplicacion";
	public static final String COD_SERVICIO_INCORRECTO = "0998";
	
	
	public static final String COD_0001_GENERAL = "0001";
	public static final String DESC_0001_GENERAL = "El usuario/password no coincide con el del servidor o existe mas de un servidor";
	public static final String COD_0002_GENERAL = "0002";
	public static final String DESC_0002_GENERAL = "El campo Usuario es obligatorio";
	public static final String COD_0003_GENERAL = "0003";
	public static final String DESC_0003_GENERAL = "El campo Password es obligatorio";
	public static final String COD_0007_GENERAL ="0007";
	public static final String DESC_0007_GENERAL ="El Campo Cuerpo del Mensaje es Obgligatorio";
	public static final String COD_0008_GENERAL = "0008";
	public static final String DESC_0008_GENERAL = "No Existe Aplicacion para el usuario/password";
	public static final String COD_0014_GENERAL = "0014";
	public static final String DESC_0014_GENERAL = "Error BBDD al crear el Lote";
	public static final String COD_0015_GENERAL = "0015";
	public static final String DESC_0015_GENERAL = "Error BBDD al actualizar el estado";
	public static final String COD_0016_GENERAL = "0016";
	public static final String DESC_0016_GENERAL = "El servicio es incorrecto o existe pero no est� activo";
	public static final String COD_0017_GENERAL = "0017";
	public static final String DESC_0017_GENERAL = "Servicio incorrecto. No esta asignado a ninguna aplicacion";
	public static final String COD_0018_GENERAL = "0018";
	public static final String DESC_0018_GENERAL = "Canal Erroneo";
	public static final String COD_0019_GENERAL = "0019";
	public static final String DESC_0019_GENERAL = "El servidor no est� asociado al servicio";
	public static final String COD_0020_GENERAL ="0020";
	public static final String DESC_0020_GENRAL ="La Peticion No esta construida Correctamente. Faltan Campos Obligatorios";
	public static final String COD_0028_GENERAL = "0028";
	public static final String DESC_0028_GENERAL = "Error desconocido reenviando mensaje";
	public static final String COD_0029_GENERAL = "0029";
	public static final String DESC_0029_GENERAL = "Se ha producido un error generando la cadena de respuesta";
	public static final String COD_0030_GENERAL = "0030";
	public static final String DESC_0030_GENERAL = "Se ha producido un error procesando la recepcion";
	public static final String COD_0031_GENERAL = "0031";
	public static final String DESC_0031_GENERAL = "El Organismo Pagado insertado no esta registrado para este servicio";
	public static final String COD_0999_GENERAL = "0999";
	public static final String DESC_0999_GENERAL = "Error desconocido general";

	// Errores de SMS
	public static final String COD_2000_GENERAL = "2000";
	public static final String DESC_2000_GENERAL = ". El numero de telefono no es valido";
	public static final String COD_2002_GENERAL ="2002";
	public static final String DESC_2002_GENERAL ="El Campo Destinatario es Obligatorio";
	public static final String COD_2006_GENERAL = "2006";
	public static final String DESC_2006_GENERAL = "Error BBDD al crear el SMS";
	public static final String COD_2007_GENERAL = "2007";
	public static final String DESC_2007_GENERAL = "Mensaje incorrecto";
	public static final String COD_2008_GENERAL = "2008";
	public static final String DESC_2008_GENERAL = "El mensaje ya ha sido enviado y no se puede reenviar";
	public static final String COD_2009_GENERAL = "2009";
	public static final String DESC_2009_GENERAL = "El mensaje ya ha sido enviado y no se puede anular";
	public static final String COD_2010_GENERAL = "2010";
	public static final String DESC_2010_GENERAL = "El Organismo Pagador Insertado no esta registrado para este servicio";
	public static final String COD_2012_GENERAL ="2012";
	public static final String DESC_2012_GENERAL ="No se reconoce el Organismo Pagador o no esta activo";
	public static final String COD_2999_GISS = "2999";
	public static final String DESC_2999_GISS = "Error desconocido creando SMS";

	
	// Errores de ENVIOMENSAJES
	public static final String ERROR_STATUSTEXT_CUERPO = "No existe cuerpo del mensaje o destinatarios";
	public static final String STATUSDETAILS_KO_DISPOSITIVO = "No existen dispositivos asociados para ese idUsuario";
	public static final String STATUSDETAILS_KO_TELEFONO = "El numero de telefono es inexistente o incorrecto";
	public static final String STATUS_KO_DISPOSITIVO = "3013";
	public static final String STATUSDETAILS_KO_CAMPOS_OBLIGATORIOS = "Faltan campos Obligatorios";
	public static final String STATUSDETAILS_KO_USUARIONOSUSCRITO = "El usuario no esta suscrito al servicio movil indicado";
	public static final String STATUSCODE_KO_CAMPOS_OBLIGATORIOS = "3005";
	public static final String DETAILS_KO_CAMPOS_OBLIGATORIOS = "El identificador del servicio movil es obligatorio";
	public static final String DETAILS_KO_CAMPOS_OBLIGATORIOS_SERVICIO = "El servicio movil es incorrecto o no estÃ¡ activo";
	public static final String ERROR_ADJUNTOS = "Se ha producido un error al adjuntar archivos";
	public static final String STATUSDETAILS_KO_NMAXENVIOS = "Se ha sobrepasado el numero maximo de mensajes a enviar por lote";
	public static final String STATUS_KO_NMAXENVIOS = "3014";
	

	
}
