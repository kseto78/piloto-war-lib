package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

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
 *  Representa la vista canales de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_ENVIOSPENDIENTESPORCANAL")
@NamedQueries({         
	@NamedQuery(name = "selectViewEnviosPendientesCanal", query = "SELECT e FROM ViewEnviosPendientesCanalJPA e")})

public class ViewEnviosPendientesCanalJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewEnviosPendientesCanalJPA() {
		super();
		this.aplicacion = null;
		this.email = null;
		this.sms = null;
		this.recepcionSMS = null;
		this.push = null;
	}

	
	@Id
	@Column(name="NOMBRE")
	protected String aplicacion;

	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name="EMAILS")
	protected Integer email;
	
	@Column(name="SMS")
	protected Integer sms;
	
	@Column(name="RECEPCIONSMS")
	protected Integer recepcionSMS;
	
	@Column(name="PUSH")
	protected Integer push;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.aplicacion;
	}
	public void setId(Object id){
		this.aplicacion =(String)id;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
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

	

	
	
}
