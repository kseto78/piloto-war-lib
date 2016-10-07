package es.mpr.plataformamensajeria.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
/**
 * 
 * @author i-nercya
 *
 */
public class SMTPConnectionTest implements ConnectionListener, TransportListener {
	/**
	 * 
	 * @param ip
	 * @param user
	 * @param password
	 * @param secure
	 * @param port
	 * @return
	 */
	public String checkConnectionClassic(String ip, String user, String password, String secure, String port,String reqAuth){
		String retorno ="";
		Properties props = new Properties();
	     props.put("mail.smtp.host", ip);
	     if(user!=null){
	    	 props.put("mail.stmp.user", user);
	     }	
	     props.put("mail.smtp.auth", (reqAuth==null)?"false":reqAuth); 
	     props.put("mail.smtp.starttls.enable", (secure==null)?"false":secure);
	     if(password!=null){
	    	 props.put("mail.smtp.password",password);
	     }
	     props.put("mail.smtp.port", port);
	     props.put("mail.smtp.timeout", 5000);
	     //props.put("mail.smtp.connectiontimeout", 5000);
	     Session session = Session.getInstance(props);
	     
	     	
	         try {
	           Transport transport = session.getTransport("smtp");
	           transport.addConnectionListener(this);
	           transport.addTransportListener(this);
	           transport.connect(ip,new Integer(port),user,password);
	           
	           if(transport.isConnected()){
	        	  return null;
	           }
	         }catch(javax.mail.AuthenticationFailedException exc) {
	        	   retorno = "Usuario o password incorrectos";
	         }catch (NoSuchProviderException e) {
	        	   retorno = "Proveedor inválido";
	         } catch (NumberFormatException e) {
	        	   retorno = "Puerto introducido incorrecto";
	         } catch (MessagingException e) {
	        	   retorno = "Mensaje del servidor: " + e.getMessage();
			}
		return retorno;
	}
	/**
	 * 
	 * @param ip
	 * @param user
	 * @param password
	 * @param secure
	 * @param port
	 * @return
	 */
	public static boolean checkConnection(String ip, String user, String password, String secure, String port){
		SMTPClient client = new SMTPClient();
		 try {
		      int reply;
		      
		      client.setDefaultPort(new Integer(port));
		      
		      client.connect(ip);
		      
		      System.out.print(client.getReplyString());

		      reply = client.getReplyCode();

		      if(!SMTPReply.isPositiveCompletion(reply)) {
		        client.disconnect();
		        System.err.println("SMTP server refused connection.");
		        System.exit(1);
		      }else{
		    	  if(client.login(ip)){
		    		  return true;
		    	  }
		      }
		      return true;
		    } catch(IOException e) {
		      if(client.isConnected()) {
		        try {
		          client.disconnect();
		        } catch(IOException f) {
		          // do nothing
		        }
		      }
		      System.err.println("Could not connect to server.");
		      e.printStackTrace();
		      System.exit(1);
		    }
		return false;
	}
	@Override
	public void messageDelivered(TransportEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
	@Override
	public void messageNotDelivered(TransportEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
	@Override
	public void messagePartiallyDelivered(TransportEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
	@Override
	public void closed(ConnectionEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
	@Override
	public void disconnected(ConnectionEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
	@Override
	public void opened(ConnectionEvent arg0) {
		String a = "";
		String b = "";
		a = b;
	}
}
