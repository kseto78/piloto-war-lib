package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
 *  @author i-nercya
 */
@Entity
@Table(name = "TBL_DESTINATARIOS_MENSAJES")
@NamedQueries({         
	@NamedQuery(name = "selectDestinatariosMensajeJPA", query = "SELECT e FROM DestinatariosMensajesJPA e where to_char(e.mensajeId)=:mensajeId"),
	@NamedQuery(name = "selectCountDestinatariosMensajeJPA", query = "SELECT count(e) FROM DestinatariosMensajesJPA e where to_char(e.mensajeId)=:mensajeId")})

public class DestinatariosMensajesJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DestinatariosMensajesJPA() {
		
		this.destinatariosMensajes=null;
		this.mensajeId=null;
		this.destinatario=null;
		this.estado=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.codigoExterno=null;
		this.uim=null;
		this.nodo=null;
		this.ultimoEnvio=null;

	}
	
	@Id
	@SequenceGenerator(name="destinatariosMensajes", sequenceName="DESTINATARIOSMENSAJES_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="destinatariosMensajes")
	protected Integer destinatariosMensajes;
	@Column(name="MENSAJEID")
	private Integer mensajeId;
	@Column(name="DESTINATARIO")
	private String destinatario;
	@Column(name="ESTADO")
	private String estado;
	@Column(name="FECHACREACION")
	private Date fechaCreacion;
	@Column(name="CREADOPOR")
	private String creadoPor;
	@Column(name="FECHAMODIFICACION")
	private Date fechaModificacion;
	@Column(name="MODIFICADOPOR")
	private String modificadoPor;
	@Column(name="CODIGOEXTERNO")
	private String codigoExterno; 	
	@Column(name="UIM")
	private String uim;
	@Column(name="NODO")
	private String nodo; 	
	@Column(name="ULTIMOENVIO")
	private Date ultimoEnvio;

	@Override
	public Object getId() {
		return this.destinatariosMensajes;
	}


	public Integer getDestinatariosMensajes() {
		return destinatariosMensajes;
	}


	public void setDestinatariosMensajes(Integer destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}


	public Integer getMensajeId() {
		return mensajeId;
	}


	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
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


	public String getCodigoExterno() {
		return codigoExterno;
	}


	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}


	public String getUim() {
		return uim;
	}


	public void setUim(String uim) {
		this.uim = uim;
	}


	public String getNodo() {
		return nodo;
	}


	public void setNodo(String nodo) {
		this.nodo = nodo;
	}


	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}


	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

}
