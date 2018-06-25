package es.minhap.plataformamensajeria.iop.beans;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	private String dEmail;
	private String dPassword;

	public SMTPAuthenticator(String email, String password){
		dEmail = email;
		dPassword = password;
	}

	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(dEmail, dPassword);

	}
}
