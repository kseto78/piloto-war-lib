package es.mpr.plataformamensajeria.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
// TODO: Auto-generated Javadoc

/**
 * Clase SMTPConnectionTest.
 *
 * @author i-nercya
 */
public class SMTPConnectionTest implements ConnectionListener, TransportListener {
	
	protected static final String FALSE = "false";

	/**
	 * Check connection classic.
	 *
	 * @param ip the ip
	 * @param user the user
	 * @param password the password
	 * @param secure the secure
	 * @param port the port
	 * @param reqAuth the req auth
	 * @return the string
	 */
	public String checkConnectionClassic(String ip, String user, String password, String secure, String port,String reqAuth){
		String retorno ="";
		Properties props = new Properties();
	     props.put("mail.smtp.host", ip);
	     if(user!=null){
	    	 props.put("mail.stmp.user", user);
	     }	
	     props.put("mail.smtp.auth", (reqAuth==null)?FALSE:reqAuth); 
	     props.put("mail.smtp.starttls.enable", (secure==null)?FALSE:secure);
	     if(password!=null){
	    	 props.put("mail.smtp.password",password);
	     }
	     props.put("mail.smtp.port", port);
	     props.put("mail.smtp.timeout", 5000);
	     Session session = Session.getInstance(props);
	     
	     	
	         try {
	           Transport transport = session.getTransport("smtp");
	           transport.addConnectionListener(this);
	           transport.addTransportListener(this);
	           transport.connect(ip,Integer.valueOf(port),user,password);
	           
	           if(transport.isConnected()){
	        	  return null;
	           }
	         }catch(javax.mail.AuthenticationFailedException exc) {
				retorno = "Usuario o password incorrectos";
	         }catch (NoSuchProviderException e) {
				retorno = "Proveedor invalido";
	         } catch (NumberFormatException e) {
				retorno = "Puerto introducido incorrecto";
	         } catch (MessagingException e) {
	        	   retorno = "Mensaje del servidor: " + e.getMessage();
			}
		return retorno;
	}
	
	/**
	 * Check connection.
	 *
	 * @param ip the ip
	 * @param user the user
	 * @param password the password
	 * @param secure the secure
	 * @param port the port
	 * @return true, if successful
	 */
	public static boolean checkConnection(String ip, String user, String password, String secure, String port){
		SMTPClient client = new SMTPClient();
		 try {
		      int reply;
		      
		      client.setDefaultPort(Integer.valueOf(port));
		      
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
		      System.exit(1);
		    }
		return false;
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.TransportListener#messageDelivered(javax.mail.event.TransportEvent)
	 */
	@Override
	public void messageDelivered(TransportEvent arg0) {
		// This method has to be empty.
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.TransportListener#messageNotDelivered(javax.mail.event.TransportEvent)
	 */
	@Override
	public void messageNotDelivered(TransportEvent arg0) {
		// This method has to be empty.
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.TransportListener#messagePartiallyDelivered(javax.mail.event.TransportEvent)
	 */
	@Override
	public void messagePartiallyDelivered(TransportEvent arg0) {
		// This method has to be empty.
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.ConnectionListener#closed(javax.mail.event.ConnectionEvent)
	 */
	@Override
	public void closed(ConnectionEvent arg0) {
		// This method has to be empty.
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.ConnectionListener#disconnected(javax.mail.event.ConnectionEvent)
	 */
	@Override
	public void disconnected(ConnectionEvent arg0) {
		// This method has to be empty.
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.event.ConnectionListener#opened(javax.mail.event.ConnectionEvent)
	 */
	@Override
	public void opened(ConnectionEvent arg0) {
		// This method has to be empty.
	}
}
