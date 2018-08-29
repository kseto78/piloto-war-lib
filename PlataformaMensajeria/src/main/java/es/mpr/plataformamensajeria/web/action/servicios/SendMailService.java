package es.mpr.plataformamensajeria.web.action.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.mail.smtp.SMTPAddressFailedException;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;

/**
 * Clase SendMailService.
 */
////MIGRADO
public class SendMailService extends HttpServlet  {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(SendMailService.class);
	
	/**  send mail service. */
	private static SendMailService sendMailService = new SendMailService();
	
	/**  caracter separador lineas. */
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; //El salto de linea esta preparado para el correo en formato HTML
	
	
	/**
	 * Inits the servicio.
	 *
	 * @param usuario the usuario
	 * @param aplicacion the aplicacion
	 * @param servicioId the servicio id
	 * @param configProp the config prop
	 * @throws ServletException the servlet exception
	 */
	////MIGRADO
	public void initServicio(String usuario, String aplicacion, String servicioId, PlataformaMensajeriaProperties configProp) throws ServletException{	
		try {
           sendMailService.sendMailServicio(usuario, aplicacion, servicioId, configProp);
		} catch (IOException e) {
			logger.error("SendMailService - initServicio:" + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error("SendMailService - initServicio:" + smtpEx);
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error("SendMailService - initServicio:" + sfex);							
			} else {
				logger.error("SendMailService - initServicio:" + e);
			}
		}
	}
	
	/**
	 * Inits the job.
	 *
	 * @param nombreJob the nombre job
	 * @param resultado the resultado
	 * @param descripcionJob the descripcion job
	 * @param configProp the config prop
	 * @throws ServletException the servlet exception
	 */
	///MIGRADO
	public void initJob(String nombreJob, String resultado, String descripcionJob, PlataformaMensajeriaProperties configProp) throws ServletException{		
		try {
           sendMailService.sendMailJob(nombreJob, resultado, descripcionJob, configProp);
		} catch (IOException e) {
			logger.error("SendMailService - initJob:" + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error("SendMailService - initJob:" + smtpEx);	
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error("SendMailService - initJob:" + sfex);							
			} else {
				logger.error("SendMailService - initJob:" + e);
			}
		}
	}
	
	/**
	 * Inits the informes servicios.
	 *
	 * @param nombreJob the nombre job
	 * @param resultado the resultado
	 * @param emails the emails
	 * @param descripcionJob the descripcion job
	 * @param configProp the config prop
	 * @throws ServletException the servlet exception
	 */
	////MIGRADO
	public void initInformesServicios(String nombreJob, String resultado, String emails, String descripcionJob, PlataformaMensajeriaProperties configProp) throws ServletException{
		try {    
          sendMailService.sendMailJobWithEmails(nombreJob, resultado, emails, descripcionJob, configProp);
		} catch (IOException e) {
			logger.error("SendMailService - initInformesServicios:" + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error("SendMailService - initInformesServicios:" + smtpEx);	
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error("SendMailService - initInformesServicios:" + sfex);						
			} else {
				logger.error("SendMailService - initInformesServicios:" + e);
			}
		}
	}
	
	/**
	 * Constructor de send mail service.
	 */
	public SendMailService() {
		super();
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	/**
	 * ENVIA EL CORREO A TRAVES DEL mail.jar (Transport) NOTIFICANDO EL ALTA DE UN SERVICIO POR PARTE DE UN USUARIO PROPIETARIO
	 *
	 * @param usuario the usuario
	 * @param aplicacion the aplicacion
	 * @param servicioId the servicio id
	 * @param configProp the config prop
	 * @throws Exception the exception
	 */
	////MIGRADO
	private void sendMailServicio(String usuario, String aplicacion, String servicioId, PlataformaMensajeriaProperties configProp) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("mail.ip.dns",null));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("mail.puerto", null));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("mail.tiempoEspera", null)) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("mail.requiereConexionSegura", null) != null && configProp.getProperty("mail.requiereConexionSegura", null).equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("mail.requiereAutenticacion", null) != null && configProp.getProperty("mail.requiereAutenticacion", null).equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("mail.usuario", null), configProp.getProperty("mail.password", null));
			session = Session.getInstance(props, auth);
		}else{
			props.put("mail.smtp.auth", false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty("mail.usuario", null) != null){
			addressFrom = new InternetAddress(configProp.getProperty("mail.usuario", null),"Prueba SIM", null);
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("mail.usuario", null));
			msg.setFrom(addressFrom);
		}
		
		String addressToProperties = configProp.getProperty("mail.to", null);
		String addressCcProperties = configProp.getProperty("mail.cc", null);
		String addressBccProperties = configProp.getProperty("mail.bcc", null);
		
		ArrayList<String> recipientsTo = recuperarInternetAddress(addressToProperties);
		ArrayList<String> recipientsCc = recuperarInternetAddress(addressCcProperties);
		ArrayList<String> recipientsBcc = recuperarInternetAddress(addressBccProperties);
		
		// ANADIR DESTINATARIOS
		InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
		InternetAddress[] addressCC = generateInternetAddress(recipientsCc);
		InternetAddress[] addressBCC = generateInternetAddress(recipientsBcc);
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		msg.setRecipients(Message.RecipientType.CC, addressCC);
		msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		
		// ASUNTO
		String tituloCorreo = configProp.getProperty("mail.titulo", null);
		tituloCorreo = tituloCorreo.replace("[IDENTIFICADOR]", servicioId);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("mail.cuerpo", null);
		cuerpoCorreo = cuerpoCorreo.replace("[USUARIO]", usuario);
		cuerpoCorreo = cuerpoCorreo.replace("[APLICACION]", aplicacion);
		cuerpoCorreo = cuerpoCorreo.replace("[IDENTIFICADOR]", servicioId);
		
		String typeContent = "text/HTML" +"; charset=" + "UTF-8";
		cuerpo.setContent(cuerpoCorreo, typeContent);
		multipart.addBodyPart(cuerpo);
		
		// INSERTAR EL CUERPO EN EL MENSAJE
		msg.setContent(multipart);
		
		// REALIZAR EL ENVIO FISICO DEL MENSAJE
		Transport.send(msg);
	}
	
	/**
	 * ENVIA EL CORREO A TRAVES DEL mail.jar (Transport) NOTIFICANDO EL RESULTADO DE EJECUCION DEL LOS JOBS
	 *
	 * @param nombreJob the nombre job
	 * @param resultado the resultado
	 * @param descripcionJob the descripcion job
	 * @param configProp the config prop
	 * @throws Exception the exception
	 */
	////MIGRADO
	private void sendMailJob(String nombreJob, String resultado, String descripcionJob, PlataformaMensajeriaProperties configProp) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("job.mail.ip.dns", null));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("job.mail.puerto", null));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("job.mail.tiempoEspera", null)) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("job.mail.requiereConexionSegura", null) != null && configProp.getProperty("job.mail.requiereConexionSegura", null).equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("job.mail.requiereAutenticacion", null) != null && configProp.getProperty("job.mail.requiereAutenticacion", null).equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("job.mail.usuario", null), configProp.getProperty("job.mail.password", null));
			session = Session.getInstance(props, auth);
		}else{
			props.put("mail.smtp.auth", false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty("job.mail.usuario", null) != null){
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario", null),"Prueba SIM");
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario", null));
			msg.setFrom(addressFrom);
		}
		
		String addressToProperties = configProp.getProperty("job.mail.to", null);
		String addressCcProperties = configProp.getProperty("job.mail.cc", null);
		String addressBccProperties = configProp.getProperty("job.mail.bcc", null);
		
		ArrayList<String> recipientsTo = recuperarInternetAddress(addressToProperties);
		ArrayList<String> recipientsCc = recuperarInternetAddress(addressCcProperties);
		ArrayList<String> recipientsBcc = recuperarInternetAddress(addressBccProperties);
		
		// ANADIR DESTINATARIOS
		InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
		InternetAddress[] addressCC = generateInternetAddress(recipientsCc);
		InternetAddress[] addressBCC = generateInternetAddress(recipientsBcc);
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		msg.setRecipients(Message.RecipientType.CC, addressCC);
		msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		
		// ASUNTO
		String tituloCorreo = configProp.getProperty("job.mail.titulo", null);
		tituloCorreo = tituloCorreo.replace("[NOMBREJOB]", nombreJob);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("job.mail.cuerpo", null);
		cuerpoCorreo = cuerpoCorreo.replace("[NOMBREJOB]", nombreJob);
		cuerpoCorreo = cuerpoCorreo.replace("[RESULTADO]", resultado);
		cuerpoCorreo = cuerpoCorreo.concat(CARACTER_SEPARADOR_LINEAS);
		cuerpoCorreo = cuerpoCorreo.concat(descripcionJob);
		
		String typeContent = "text/HTML" +"; charset=" + "UTF-8";
		cuerpo.setContent(cuerpoCorreo, typeContent);
		multipart.addBodyPart(cuerpo);
		
		// INSERTAR EL CUERPO EN EL MENSAJE
		msg.setContent(multipart);
		
		// REALIZAR EL ENVIO FISICO DEL MENSAJE
		Transport.send(msg);
	}
	
	/**
	 * ENVIA EL CORREO A TRAVES DEL mail.jar (Transport) NOTIFICANDO EL RESULTADO DE EJECUCION DEL LOS JOBS
	 *
	 * @param nombreJob the nombre job
	 * @param resultado the resultado
	 * @param emails the emails
	 * @param descripcionJob the descripcion job
	 * @param configProp the config prop
	 * @throws Exception the exception
	 */
	////MIGRADO
	private void sendMailJobWithEmails(String nombreJob, String resultado, String emails, String descripcionJob, PlataformaMensajeriaProperties configProp) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("job.mail.ip.dns", null));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("job.mail.puerto", null));
				
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("job.mail.tiempoEspera", null)) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("job.mail.requiereConexionSegura", null) != null && configProp.getProperty("job.mail.requiereConexionSegura", null).equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("job.mail.requiereAutenticacion", null) != null && configProp.getProperty("job.mail.requiereAutenticacion", null).equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("job.mail.usuario", null), configProp.getProperty("job.mail.password", null));
			session = Session.getInstance(props, auth);
		}else{
			props.put("mail.smtp.auth", false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty("job.mail.usuario", null) != null){
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario", null),"Prueba SIM");
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario", null));
			msg.setFrom(addressFrom);
		}
		
		
		ArrayList<String> recipientsTo = recuperarInternetAddress(emails);
		
		// ANADIR DESTINATARIOS
		InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		
		// ASUNTO
		String tituloCorreo = configProp.getProperty("job.mail.titulo", null);
		tituloCorreo = tituloCorreo.replace("[NOMBREJOB]", nombreJob);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = "";
		
		cuerpoCorreo = cuerpoCorreo.concat(descripcionJob);
		
		String typeContent = "text/HTML" +"; charset=" + "UTF-8";
		cuerpo.setContent(cuerpoCorreo, typeContent);
		multipart.addBodyPart(cuerpo);
		
		// INSERTAR EL CUERPO EN EL MENSAJE
		msg.setContent(multipart);
		
		// REALIZAR EL ENVIO FISICO DEL MENSAJE
		Transport.send(msg);
	}
	
	/**
	 * Recuperar internet address.
	 *
	 * @param address the address
	 * @return the array list
	 * @throws AddressException the address exception
	 */
	// METODO QUE RECUPERA LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	private ArrayList<String> recuperarInternetAddress(String address) throws AddressException {
		
		ArrayList<String> recipients = new ArrayList<String>();
		int contador = 0;
		
		if(null != address && !address.isEmpty()){
			//Si existe solo una direccion de correo
			if(address.indexOf(";") == -1){
				recipients.add(address);
			} else {
				//Recuperamos todos las direcciones de correo
				while (address.indexOf(";", contador) > -1) {
					recipients.add(address.substring(contador, address.indexOf(";", contador)));
					contador = address.indexOf(";", contador) + 1;
				}
				recipients.add(address.substring(contador, address.length()));
			}
		}
		
		return recipients;
	}
	
	// METODO QUE ANADE LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	/**
	 * Generate internet address.
	 *
	 * @param recipients the recipients
	 * @return the internet address[]
	 * @throws AddressException the address exception
	 */
	////MIGRADO
	private InternetAddress[] generateInternetAddress(ArrayList<String> recipients) throws AddressException {
		
		InternetAddress[] address = new InternetAddress[recipients.size()];
		
		for (int i = 0; i < recipients.size(); i++) {
			address[i] = new InternetAddress(recipients.get(i));
		}
		return address;
	}
	
}
