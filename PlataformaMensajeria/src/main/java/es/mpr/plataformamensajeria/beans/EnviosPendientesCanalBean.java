package es.mpr.plataformamensajeria.beans;

import java.text.DecimalFormat;

import com.map.j2ee.auditoria.ifaces.Audit;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista Envios Pendientes por Canala de la base de datos
 *  
 *  @author 
 */
public class EnviosPendientesCanalBean implements Audit{

	/**
	 * Constructor de envios pendientes canal bean.
	 */
	public EnviosPendientesCanalBean() {
		
		this.aplicacion = null;
		this.email = null;
		this.sms = null;
		this.recepcionSMS = null;
		this.push = null;
	}
	
	/**  aplicacion id. */
	protected Integer aplicacionId;
	
	/**  aplicacion. */
	protected String aplicacion;
	
	/**  email. */
	protected Integer email;
	
	/**  sms. */
	protected Integer sms;
	
	/**  recepcion SMS. */
	protected Integer recepcionSMS;
	
	/**  push. */
	protected Integer push;

	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	
	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	
	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		// TODO Auto-generated method stub
		return this.aplicacion;
	}
	
	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id){
		this.aplicacion =(String)id;
	}

	
	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}
	
	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * Obtener email.
	 *
	 * @return email
	 */
	public Integer getEmail() {
		return email;
	}
	
	/**
	 * Modificar email.
	 *
	 * @param email new email
	 */
	public void setEmail(Integer email) {
		this.email = email;
	}
	
	/**
	 * Obtener sms.
	 *
	 * @return sms
	 */
	public Integer getSms() {
		return sms;
	}
	
	/**
	 * Modificar sms.
	 *
	 * @param sms new sms
	 */
	public void setSms(Integer sms) {
		this.sms = sms;
	}
	
	/**
	 * Obtener recepcion SMS.
	 *
	 * @return recepcion SMS
	 */
	public Integer getRecepcionSMS() {
		return recepcionSMS;
	}
	
	/**
	 * Modificar recepcion SMS.
	 *
	 * @param recepcionSMS new recepcion SMS
	 */
	public void setRecepcionSMS(Integer recepcionSMS) {
		this.recepcionSMS = recepcionSMS;
	}
	
	/**
	 * Obtener push.
	 *
	 * @return push
	 */
	public Integer getPush() {
		return push;
	}
	
	/**
	 * Modificar push.
	 *
	 * @param push new push
	 */
	public void setPush(Integer push) {
		this.push = push;
	}
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override 
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener SMS string.
	 *
	 * @return SMS string
	 */
	public String getSMSString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(sms);
		}
	}
	
	/**
	 * Obtener recepcion SMS string.
	 *
	 * @return recepcion SMS string
	 */
	public String getRecepcionSMSString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(recepcionSMS);
		}
	}
	
	/**
	 * Obtener email string.
	 *
	 * @return email string
	 */
	public String getEmailString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(email);
		}
	}
	
	/**
	 * Obtener push string.
	 *
	 * @return push string
	 */
	public String getPushString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(push);
		}
	}
	
	
}
