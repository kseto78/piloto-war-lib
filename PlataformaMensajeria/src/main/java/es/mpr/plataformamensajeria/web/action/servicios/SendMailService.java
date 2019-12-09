package es.mpr.plataformamensajeria.web.action.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import org.apache.log4j.Logger;

import com.sun.mail.smtp.SMTPAddressFailedException;

import es.minhap.plataformamensajeria.iop.manager.TblParametrosServidorManager;
import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.minhap.sim.model.TblParametrosServidor;
import es.minhap.sim.query.TblParametrosServidorQuery;
import es.minhap.sim.query.TblServidoresQuery;
import es.minhap.sim.query.TblTiposParametrosQuery;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;

/**
 * Clase SendMailService.
 */
////MIGRADO
public class SendMailService extends HttpServlet  {

	protected static final String PRUEBA_SIM = "Prueba SIM";

	protected static final String JOBDOTMAILDOTRE = "job.mail.requiereAutenticacion";

	protected static final String JOBDOTMAILDOTUS = "job.mail.usuario";

	protected static final String MAILDOTSMTPDOTP = "mail.smtp.port";

	protected static final String MAILDOTSMTPDOTS = "mail.smtp.starttls.enable";

	protected static final String MAILDOTSMTPDOTC = "mail.smtp.connectiontimeout";

	protected static final String S = "S";

	protected static final String MAILDOTREQUIERE = "mail.requiereAutenticacion";

	protected static final String JOBDOTMAILDOTTI = "job.mail.tiempoEspera";

	protected static final String MAILDOTSMTPDOTT = "mail.smtp.timeout";

	protected static final String TXTSENDMAILSERVICE = "SendMailService - initJob:";

	protected static final String JOBDOTMAILDOTIP = "job.mail.ip.dns";

	protected static final String TEXTHTML = "text/HTML";

	protected static final String MAILDOTUSUARIO = "mail.usuario";

	protected static final String SENDMAILSERVICE0 = "SendMailService - initInformesServicios:";

	protected static final String SENDMAILSERVICE1 = "SendMailService - initServicio:";

	protected static final String UTF_8 = "UTF-8";

	protected static final String JOBDOTMAILDOTPU = "job.mail.puerto";

	protected static final String MAILDOTSMTPDOTA = "mail.smtp.auth";

	protected static final String MAILDOTSMTPDOTH = "mail.smtp.host";

	protected static final String BLANKCHARSET = "; charset=";

	protected static final String MAILDOTREQUIERE0 = "mail.requiereConexionSegura";

	protected static final String IDENTIFICADOR = "[IDENTIFICADOR]";

	protected static final String JOBDOTMAILDOTTI0 = "job.mail.titulo";

	protected static final String TRUE = "true";

	protected static final String NOMBREJOB = "[NOMBREJOB]";

	protected static final String JOBDOTMAILDOTRE0 = "job.mail.requiereConexionSegura";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(SendMailService.class);
	
	/**  send mail service. */
	private static SendMailService sendMailService = new SendMailService();
	
	/**  caracter separador lineas. */
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; 
	//El salto de linea esta preparado para el correo en formato HTML
	
	
	/**
	 * Constructor de send mail service.
	 */
	public SendMailService() {
		super();
		
	}

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
			logger.error(SENDMAILSERVICE1 + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException) {
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error(SENDMAILSERVICE1 + smtpEx);
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error(SENDMAILSERVICE1 + sfex);							
			} else {
				logger.error(SENDMAILSERVICE1 + e);
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
	public void initJob(String nombreJob, String resultado, String descripcionJob, PlataformaMensajeriaProperties configProp, TblParametrosServidorManagerImpl tblParametrosServidorManager) throws ServletException{
	
		try {
           sendMailService.sendMailJob(nombreJob, resultado, descripcionJob, configProp, tblParametrosServidorManager);
		} catch (IOException e) {
			logger.error(TXTSENDMAILSERVICE + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException) {
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error(TXTSENDMAILSERVICE + smtpEx);	
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error(TXTSENDMAILSERVICE + sfex);							
			} else {
				logger.error(TXTSENDMAILSERVICE + e);
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
	public void initInformesServicios(String nombreJob, String resultado, String emails, String descripcionJob, PlataformaMensajeriaProperties configProp, TblParametrosServidorManager tblParametrosServidorManager) throws ServletException{
		try {
    	
          sendMailService.sendMailJobWithEmails(nombreJob, resultado, emails, descripcionJob, configProp, tblParametrosServidorManager);
		} catch (IOException e) {
			logger.error(SENDMAILSERVICE0 + e);
		} catch (Exception e) {
			if (e instanceof SMTPAddressFailedException) {
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)e;
				logger.error(SENDMAILSERVICE0 + smtpEx);	
			}else if (e instanceof SendFailedException){
				SendFailedException sfex = (SendFailedException)e;
				logger.error(SENDMAILSERVICE0 + sfex);						
			} else {
				logger.error(SENDMAILSERVICE0 + e);
			}
		}
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
		props.put(MAILDOTSMTPDOTH, configProp.getProperty("mail.ip.dns",null));
		
		// PUERTO
		props.put(MAILDOTSMTPDOTP, configProp.getProperty("mail.puerto", null));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty("mail.tiempoEspera", null)) * 1000;
		props.put(MAILDOTSMTPDOTC, timeout); 
		props.put(MAILDOTSMTPDOTT, timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty(MAILDOTREQUIERE0, null) != null && S.equals(configProp.getProperty(MAILDOTREQUIERE0, null))){
			props.setProperty(MAILDOTSMTPDOTS, TRUE);
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty(MAILDOTREQUIERE, null) != null && S.equals(configProp.getProperty(MAILDOTREQUIERE, null))) {
			props.put(MAILDOTSMTPDOTA, true);
			auth = new SMTPAuthenticator(configProp.getProperty(MAILDOTUSUARIO, null), configProp.getProperty("mail.password", null));
			session = Session.getInstance(props, auth);
		}else{
			props.put(MAILDOTSMTPDOTA, false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty(MAILDOTUSUARIO, null) != null){
			addressFrom = new InternetAddress(configProp.getProperty(MAILDOTUSUARIO, null),PRUEBA_SIM, null);
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty(MAILDOTUSUARIO, null));
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
		tituloCorreo = tituloCorreo.replace(IDENTIFICADOR, servicioId);
		
		msg.setSubject(tituloCorreo, UTF_8);
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("mail.cuerpo", null);
		cuerpoCorreo = cuerpoCorreo.replace("[USUARIO]", usuario);
		cuerpoCorreo = cuerpoCorreo.replace("[APLICACION]", aplicacion);
		cuerpoCorreo = cuerpoCorreo.replace(IDENTIFICADOR, servicioId);
		
		String typeContent = TEXTHTML +BLANKCHARSET + UTF_8;
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
	private void sendMailJob(String nombreJob, String resultado, String descripcionJob, PlataformaMensajeriaProperties configProp, TblParametrosServidorManager tblParametrosServidorManager) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put(MAILDOTSMTPDOTH, configProp.getProperty(JOBDOTMAILDOTIP, null));
		
		// PUERTO
		props.put(MAILDOTSMTPDOTP, configProp.getProperty(JOBDOTMAILDOTPU, null));
		
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty(JOBDOTMAILDOTTI, null)) * 1000;
		props.put(MAILDOTSMTPDOTC, timeout); 
		props.put(MAILDOTSMTPDOTT, timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty(JOBDOTMAILDOTRE0, null) != null && S.equals(configProp.getProperty(JOBDOTMAILDOTRE0, null))){
			props.setProperty(MAILDOTSMTPDOTS, TRUE);
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty(JOBDOTMAILDOTRE, null) != null && S.equals(configProp.getProperty(JOBDOTMAILDOTRE, null))) {
			props.put(MAILDOTSMTPDOTA, true);
			
			//Buscamos todos los parametros con el valor de la property de job.mail.usuario
			TblParametrosServidorQuery tblParQuery = new TblParametrosServidorQuery();
			tblParQuery.setValor(configProp.getProperty(JOBDOTMAILDOTUS, null));				
			List<TblParametrosServidor> parametrosServidor = tblParametrosServidorManager.getByQuery(tblParQuery);
			
			//Buscamos los parametros con el servidor id de la consulta anterior y el tipo de parametro 3
			TblParametrosServidorQuery tblParQuery2 = new TblParametrosServidorQuery();			
			TblServidoresQuery queryServ = new TblServidoresQuery();
			queryServ.setServidorid(parametrosServidor.get(0).getTblServidores().getServidorid());
			tblParQuery2.setTblServidores(queryServ);
			TblTiposParametrosQuery tiposQuery = new TblTiposParametrosQuery();
			tiposQuery.setTipoparametroid(3L); 
			//Tipo de parametro de contraseña
			tblParQuery2.setTblTiposParametros(tiposQuery);
			List<TblParametrosServidor> valorFinal = tblParametrosServidorManager.getByQuery(tblParQuery2);
			String contraseniaJobs = valorFinal.get(0).getValor();

			auth = new SMTPAuthenticator(configProp.getProperty(JOBDOTMAILDOTUS, null), contraseniaJobs);
			session = Session.getInstance(props, auth);
		}else{
			props.put(MAILDOTSMTPDOTA, false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty(JOBDOTMAILDOTUS, null) != null){
			addressFrom = new InternetAddress(configProp.getProperty(JOBDOTMAILDOTUS, null),PRUEBA_SIM);
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty(JOBDOTMAILDOTUS, null));
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
		String tituloCorreo = configProp.getProperty(JOBDOTMAILDOTTI0, null);
		tituloCorreo = tituloCorreo.replace(NOMBREJOB, nombreJob);
		
		msg.setSubject(tituloCorreo, UTF_8);
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = configProp.getProperty("job.mail.cuerpo", null);
		cuerpoCorreo = cuerpoCorreo.replace(NOMBREJOB, nombreJob);
		cuerpoCorreo = cuerpoCorreo.replace("[RESULTADO]", resultado);
		cuerpoCorreo = cuerpoCorreo.concat(CARACTER_SEPARADOR_LINEAS);
		cuerpoCorreo = cuerpoCorreo.concat(descripcionJob);
		
		String typeContent = TEXTHTML +BLANKCHARSET + UTF_8;
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
	private void sendMailJobWithEmails(String nombreJob, String resultado, String emails, String descripcionJob, PlataformaMensajeriaProperties configProp, TblParametrosServidorManager tblParametrosServidorManager) throws Exception {
		
		boolean debug = false;
		
		// CONFIGURAMOS EL SERVIDOR
		Properties props = new Properties();
		
		Session session = null;
		Authenticator auth = null;
		
		// HOST
		props.put(MAILDOTSMTPDOTH, configProp.getProperty(JOBDOTMAILDOTIP, null));
		
		// PUERTO
		props.put(MAILDOTSMTPDOTP, configProp.getProperty(JOBDOTMAILDOTPU, null));
				
		// TIEMPO DE ESPERA
		int timeout = Integer.parseInt(configProp.getProperty(JOBDOTMAILDOTTI, null)) * 1000;
		props.put(MAILDOTSMTPDOTC, timeout); 
		props.put(MAILDOTSMTPDOTT, timeout);
		
		// SI REQUIERE CONEXION SEGURA
		if (configProp.getProperty(JOBDOTMAILDOTRE0, null) != null && S.equals(configProp.getProperty(JOBDOTMAILDOTRE0, null))){
			props.setProperty(MAILDOTSMTPDOTS, TRUE);
		}	
		
		// SI REQUIERE AUTENTIFICACION 
		if (configProp.getProperty(JOBDOTMAILDOTRE, null) != null && S.equals(configProp.getProperty(JOBDOTMAILDOTRE, null))) {
			props.put(MAILDOTSMTPDOTA, true);
			
			//Buscamos todos los parametros con el valor de la property de job.mail.usuario
			TblParametrosServidorQuery tblParQuery = new TblParametrosServidorQuery();
			tblParQuery.setValor(configProp.getProperty(JOBDOTMAILDOTUS, null));				
			List<TblParametrosServidor> parametrosServidor = tblParametrosServidorManager.getByQuery(tblParQuery);
			
			//Buscamos los parametros con el servidor id de la consulta anterior y el tipo de parametro 3
			TblParametrosServidorQuery tblParQuery2 = new TblParametrosServidorQuery();			
			TblServidoresQuery queryServ = new TblServidoresQuery();
			queryServ.setServidorid(parametrosServidor.get(0).getTblServidores().getServidorid());
			tblParQuery2.setTblServidores(queryServ);
			TblTiposParametrosQuery tiposQuery = new TblTiposParametrosQuery();
			tiposQuery.setTipoparametroid(3L); 
			//Tipo de parametro de contraseña
			tblParQuery2.setTblTiposParametros(tiposQuery);
			List<TblParametrosServidor> valorFinal = tblParametrosServidorManager.getByQuery(tblParQuery2);
			String contraseniaJobs = valorFinal.get(0).getValor();

			auth = new SMTPAuthenticator(configProp.getProperty(MAILDOTUSUARIO, null), contraseniaJobs);			
			session = Session.getInstance(props, auth);
		}else{
			props.put(MAILDOTSMTPDOTA, false);
			session = Session.getInstance(props, null);
		}
		
		session.setDebug(debug);
		
		// CREAMOS EL MENSAJE
		MimeMessage msg = new MimeMessage(session);

		InternetAddress addressFrom = null;
		
		// ANADIR EL SENDER
		if (configProp.getProperty(JOBDOTMAILDOTUS, null) != null){
			addressFrom = new InternetAddress(configProp.getProperty(JOBDOTMAILDOTUS, null),PRUEBA_SIM);
			msg.setFrom(addressFrom);			
		}else{
			addressFrom = new InternetAddress(configProp.getProperty(JOBDOTMAILDOTUS, null));
			msg.setFrom(addressFrom);
		}
		
		
		ArrayList<String> recipientsTo = recuperarInternetAddress(emails);
		
		// ANADIR DESTINATARIOS
		InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		
		// ASUNTO
		String tituloCorreo = configProp.getProperty(JOBDOTMAILDOTTI0, null);
		tituloCorreo = tituloCorreo.replace(NOMBREJOB, nombreJob);
		
		msg.setSubject(tituloCorreo, UTF_8);
		
		/////////  CREACION DEL CUERPO DEL MENSAJE  ///////////////
		Multipart multipart = new MimeMultipart();
		MimeBodyPart cuerpo = new MimeBodyPart();
		
		String cuerpoCorreo = "";
		
		cuerpoCorreo = cuerpoCorreo.concat(descripcionJob);
		
		String typeContent = TEXTHTML +BLANKCHARSET + UTF_8;
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
		
		ArrayList<String> recipients = new ArrayList<>();
		int contador = 0;
		
		if(null != address && !address.isEmpty()){
			//Si existe solo una direccion de correo
			if(address.indexOf(';') == -1){
				recipients.add(address);
			} else {
				//Recuperamos todos las direcciones de correo
				while (address.indexOf(';', contador) > -1) {
					recipients.add(address.substring(contador, address.indexOf(';', contador)));
					contador = address.indexOf(';', contador) + 1;
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
		
		for (int i = 0, s = recipients.size(); i < s; i++) {
			address[i] = new InternetAddress(recipients.get(i));
		}
		return address;
	}
	
}
