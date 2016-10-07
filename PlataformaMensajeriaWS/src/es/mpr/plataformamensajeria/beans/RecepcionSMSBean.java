package es.mpr.plataformamensajeria.beans;


/**
 * Formato de recepcion de SMS
 <pre>
 	 <User></User>
     <Password></Password>
     <Sender></Sender>
     <Recipient></Recipient>
     <MessageId></MessageId>
     <SMSText></SMSText>
 </pre>
 * @author jgonzvil
 *
 */
public class RecepcionSMSBean {
	
	private String userServidor;
	private String passwordServidor;
	private String nombreLote;
	private String servicio;
	private String userAplicacion;
	private String passwordAplicacion;
	

	public String getUserServidor() {
		return userServidor;
	}

	public void setUserServidor(String userServidor) {
		this.userServidor = userServidor;
	}

	public String getPasswordServidor() {
		return passwordServidor;
	}

	public void setPasswordServidor(String passwordServidor) {
		this.passwordServidor = passwordServidor;
	}
	
	public String getNombreLote() {
		return nombreLote;
	}
	
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	
	public String getServicio() {
		return servicio;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public String getUserAplicacion() {
		return userAplicacion;
	}
	
	public void setUserAplicacion(String userAplicacion) {
		this.userAplicacion = userAplicacion;
	}
	
	public String getPasswordAplicacion() {
		return passwordAplicacion;
	}
	
	public void setPasswordAplicacion(String passwordAplicacion) {
		this.passwordAplicacion = passwordAplicacion;
	}
		
}
