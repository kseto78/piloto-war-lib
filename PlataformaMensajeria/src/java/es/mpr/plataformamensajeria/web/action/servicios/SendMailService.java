package es.mpr.plataformamensajeria.web.action.servicios;

import java.io.IOException;
import java.io.InputStream;
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


public class SendMailService extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SendMailService.class);
	private static Properties configProp = new Properties();
	private static SendMailService sendMailService = new SendMailService();
	
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; //El salto de linea esta preparado para el correo en formato HTML
	
	
	public void initServicio(String usuario, String aplicacion, String servicioId) throws ServletException{
		InputStream in = this.getClass().getResourceAsStream("/es/mpr/plataformamensajeria/util/conf.properties");
		try {
           configProp.load(in);
           sendMailService.sendMailServicio(usuario, aplicacion, servicioId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				smtpEx.printStackTrace();
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				sfex.printStackTrace();								
			} else {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void initJob(String nombreJob, String resultado, String descripcionJob) throws ServletException{
		InputStream in = this.getClass().getResourceAsStream("/es/mpr/plataformamensajeria/util/conf.properties");
		try {
           configProp.load(in);
           sendMailService.sendMailJob(nombreJob, resultado, descripcionJob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				smtpEx.printStackTrace();
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				sfex.printStackTrace();								
			} else {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void initInformesServicios(String nombreJob, String resultado, String emails, String descripcionJob) throws ServletException{
		InputStream in = this.getClass().getResourceAsStream("/es/mpr/plataformamensajeria/util/conf.properties");
		try {
           configProp.load(in);
           sendMailService.sendMailJobWithEmails(nombreJob, resultado, emails, descripcionJob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException)
			{
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				smtpEx.printStackTrace();
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				sfex.printStackTrace();								
			} else {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public SendMailService() {
		super();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	/**
	 * ENVIA EL CORREO A TRAVES DEL mail.jar (Transport) NOTIFICANDO EL ALTA DE UN SERVICIO POR PARTE DE UN USUARIO PROPIETARIO
	 */
	private void sendMailServicio(String usuario, String aplicacion, String servicioId) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("mail.ip.dns"));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("mail.puerto"));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("mail.tiempoEspera")) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("mail.requiereConexionSegura") != null && configProp.getProperty("mail.requiereConexionSegura").equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("mail.requiereAutenticacion") != null && configProp.getProperty("mail.requiereAutenticacion").equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("mail.usuario"), configProp.getProperty("mail.password"));
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
		if (configProp.getProperty("mail.usuario") != null){
			addressFrom = new InternetAddress(configProp.getProperty("mail.usuario"),"Prueba SIM");
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("mail.usuario"));
			msg.setFrom(addressFrom);
		}
		
		String addressToProperties = configProp.getProperty("mail.to");
		String addressCcProperties = configProp.getProperty("mail.cc");
		String addressBccProperties = configProp.getProperty("mail.bcc");
		
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
		String tituloCorreo = configProp.getProperty("mail.titulo");
		tituloCorreo = tituloCorreo.replace("[IDENTIFICADOR]", servicioId);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("mail.cuerpo");
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
	 */
	private void sendMailJob(String nombreJob, String resultado, String descripcionJob) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("job.mail.ip.dns"));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("job.mail.puerto"));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("job.mail.tiempoEspera")) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("job.mail.requiereConexionSegura") != null && configProp.getProperty("job.mail.requiereConexionSegura").equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("job.mail.requiereAutenticacion") != null && configProp.getProperty("job.mail.requiereAutenticacion").equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("job.mail.usuario"), configProp.getProperty("job.mail.password"));
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
		if (configProp.getProperty("job.mail.usuario") != null){
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario"),"Prueba SIM");
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario"));
			msg.setFrom(addressFrom);
		}
		
		String addressToProperties = configProp.getProperty("job.mail.to");
		String addressCcProperties = configProp.getProperty("job.mail.cc");
		String addressBccProperties = configProp.getProperty("job.mail.bcc");
		
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
		String tituloCorreo = configProp.getProperty("job.mail.titulo");
		tituloCorreo = tituloCorreo.replace("[NOMBREJOB]", nombreJob);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("job.mail.cuerpo");
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
	 */
	private void sendMailJobWithEmails(String nombreJob, String resultado, String emails, String descripcionJob) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put("mail.smtp.host", configProp.getProperty("job.mail.ip.dns"));
		
		// PUERTO
		props.put("mail.smtp.port", configProp.getProperty("job.mail.puerto"));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("job.mail.tiempoEspera")) * 1000;
		props.put("mail.smtp.connectiontimeout", timeout); 
		props.put("mail.smtp.timeout", timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty("job.mail.requiereConexionSegura") != null && configProp.getProperty("job.mail.requiereConexionSegura").equals("S")){
			props.setProperty("mail.smtp.starttls.enable", "true");
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty("job.mail.requiereAutenticacion") != null && configProp.getProperty("job.mail.requiereAutenticacion").equals("S")) {
			props.put("mail.smtp.auth", true);
			auth = new SMTPAuthenticator(configProp.getProperty("job.mail.usuario"), configProp.getProperty("job.mail.password"));
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
		if (configProp.getProperty("job.mail.usuario") != null){
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario"),"Prueba SIM");
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty("job.mail.usuario"));
			msg.setFrom(addressFrom);
		}
		
//		String addressToProperties = configProp.getProperty("job.mail.to");
//		String addressCcProperties = configProp.getProperty("job.mail.cc");
//		String addressBccProperties = configProp.getProperty("job.mail.bcc");
		
		ArrayList<String> recipientsTo = recuperarInternetAddress(emails);
//		ArrayList<String> recipientsCc = recuperarInternetAddress(addressCcProperties);
//		ArrayList<String> recipientsBcc = recuperarInternetAddress(addressBccProperties);
		
		// ANADIR DESTINATARIOS
		InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
//		InternetAddress[] addressCC = generateInternetAddress(recipientsCc);
//		InternetAddress[] addressBCC = generateInternetAddress(recipientsBcc);
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
//		msg.setRecipients(Message.RecipientType.CC, addressCC);
//		msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		
		// ASUNTO
		String tituloCorreo = configProp.getProperty("job.mail.titulo");
		tituloCorreo = tituloCorreo.replace("[NOMBREJOB]", nombreJob);
		
		msg.setSubject(tituloCorreo, "UTF-8");
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = new String();
		
		//TODO
//		String cuerpoCorreo = configProp.getProperty("job.mail.cuerpo");
		
//		cuerpoCorreo = cuerpoCorreo.replace("[NOMBREJOB]", nombreJob);
//		cuerpoCorreo = cuerpoCorreo.replace("[RESULTADO]", resultado);
//		cuerpoCorreo = cuerpoCorreo.concat(CARACTER_SEPARADOR_LINEAS);
		cuerpoCorreo = cuerpoCorreo.concat(descripcionJob);
		
		String typeContent = "text/HTML" +"; charset=" + "UTF-8";
		cuerpo.setContent(cuerpoCorreo, typeContent);
		multipart.addBodyPart(cuerpo);
		
		// INSERTAR EL CUERPO EN EL MENSAJE
		msg.setContent(multipart);
		
		// REALIZAR EL ENVIO FISICO DEL MENSAJE
		Transport.send(msg);
	}
	
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
	private InternetAddress[] generateInternetAddress(ArrayList<String> recipients) throws AddressException {
		
		InternetAddress[] address = new InternetAddress[recipients.size()];
		
		for (int i = 0; i < recipients.size(); i++) {
			address[i] = new InternetAddress(recipients.get(i));
		}
		return address;
	}
	
}
