package es.mpr.plataformamensajeria.web.action.servicios;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Clase SMTPAuthenticator.
 */
public class SMTPAuthenticator extends Authenticator {
	
	/**  d email. */
	private String dEmail;
	
	/**  d password. */
	private String dPassword;

	/**
	 * Constructor de SMTP authenticator.
	 *
	 * @param email the email
	 * @param password the password
	 */
	public SMTPAuthenticator(String email, String password){
		dEmail = email;
		dPassword = password;
	}

	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(dEmail, dPassword);

	}
}
