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
 *  Representa la vista canales de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "TBL_USUARIOS_APLICACIONES")
public class UsuarioAplicacionJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioAplicacionJPA() {
		super();
		this.usuarioId = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.usuarioAplicacionId = null;
		this.aplicacionId=null;
		this.modo = null;
	}

	
	public Integer getUsuarioAplicacionId() {
		return usuarioAplicacionId;
	}
	public void setUsuarioAplicacionId(Integer usuarioAplicacionId) {
		this.usuarioAplicacionId = usuarioAplicacionId;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Integer getModo() {
		return modo;
	}
	public void setModo(Integer modo) {
		this.modo = modo;
	}

	@Id
	@SequenceGenerator(name="usuario", sequenceName="USUARIOAPLICACIONID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
	protected Integer usuarioAplicacionId;

	@Column(name="USUARIOID")
	protected Integer usuarioId;
	
	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name="MODO")
	protected Integer modo;
	
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	public Object getId() {
		// TODO Auto-generated method stub
		return this.usuarioAplicacionId;
	}
	public void setId(Object id){
		this.usuarioAplicacionId =(Integer)id;
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
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}



}
