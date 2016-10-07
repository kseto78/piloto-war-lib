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

	public EnviosPendientesCanalBean() {
		
		this.aplicacion = null;
		this.email = null;
		this.sms = null;
		this.recepcionSMS = null;
		this.push = null;
	}
	protected Integer aplicacionId;
	protected String aplicacion;
	protected Integer email;
	protected Integer sms;
	protected Integer recepcionSMS;
	protected Integer push;

	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return this.aplicacion;
	}
	public void setId(Object id){
		this.aplicacion =(String)id;
	}

	
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Integer getEmail() {
		return email;
	}
	public void setEmail(Integer email) {
		this.email = email;
	}
	public Integer getSms() {
		return sms;
	}
	public void setSms(Integer sms) {
		this.sms = sms;
	}
	public Integer getRecepcionSMS() {
		return recepcionSMS;
	}
	public void setRecepcionSMS(Integer recepcionSMS) {
		this.recepcionSMS = recepcionSMS;
	}
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
	}
	@Override 
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSMSString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(sms);
		}
	}
	
	public String getRecepcionSMSString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(recepcionSMS);
		}
	}
	
	public String getEmailString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(email);
		}
	}
	
	public String getPushString(){
		if(aplicacion==null||aplicacion.isEmpty()){
			return "";
		}else{
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(push);
		}
	}
	
	
}
