package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

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
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_DESTINATARIOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllDestinatarioHistoricosJPA", query = "SELECT e FROM DestinatarioHistoricosJPA e"),
	@NamedQuery(name = "selectDestinatarioHistoricosJPA", query = "SELECT e FROM DestinatarioHistoricosJPA e where to_char(e.destinatarioId)=:destinatarioId"),
	@NamedQuery(name = "selectDestinatarioByMensajeHistoricosJPA", query = "SELECT e FROM DestinatarioHistoricosJPA e where to_char(e.mensajeId)=:mensajeId")})

public class DestinatarioHistoricosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DestinatarioHistoricosJPA() {
		
		this.destinatarioId=null;
		this.nombre=null;
		this.email=null;
		this.mensajeId=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.tipoDestinatario=null;
		this.fechaHistorificacion = null;

	}

	
	@Id
	@Column(name="DESTINATARIOID")
	protected Integer destinatarioId;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="EMAIL")
	private String email;
	@Column(name="MENSAJEID")
	private String mensajeId;
	@Column(name="FECHACREACION")
	private Date fechaCreacion;
	@Column(name="CREADOPOR")
	private String creadoPor;
	@Column(name="FECHAMODIFICACION")
	private Date fechaModificacion;
	@Column(name="MODIFICADOPOR")
	private String modificadoPor;
	@Column(name="TIPODESTINATARIO")
	private String tipoDestinatario; //TO, CC, BCC	
	@Column (name="FECHAHISTORIFICACION")
	protected Date fechaHistorificacion;
	
	public Integer getDestinatarioId() {
		return destinatarioId;
	}
	public void setDestinatarioId(Integer destinatarioId) {
		this.destinatarioId = destinatarioId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoDestinatario() {
		return tipoDestinatario;
	}
	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.destinatarioId;
	}
	public void setId(Object id){
		this.destinatarioId =(Integer)id;
	}
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}
	public String getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(String mensajeId) {
		this.mensajeId = mensajeId;
	}
	
}
