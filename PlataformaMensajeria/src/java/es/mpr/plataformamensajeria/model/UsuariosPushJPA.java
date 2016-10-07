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
 *  Representa la vista usuarios movil de la base de datos
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_USUARIOS_PUSH")
@NamedQueries({         
	@NamedQuery(name = "selectUsuariosPushJPA", query = "SELECT e FROM UsuariosPushJPA e"),
	@NamedQuery(name = "selectUsuariosPushByIdJPA", query = "SELECT e.nombreUsuario FROM UsuariosPushJPA e where to_char(e.usuarioId) = :usuarioId")})

public class UsuariosPushJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuariosPushJPA() {
		super();
		this.usuarioId = null;
		this.servicioId = null;
		this.plataformaId = null;
		this.tokenUsuario = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.dispositivoId = null;
	}

	
	@Id
	@SequenceGenerator(name="usuario", sequenceName="USUARIOS_PUSH_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
	protected Integer usuarioId;
	
	@Column(name="NOMBREUSUARIO")
	protected String nombreUsuario;
	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name = "PLATAFORMAID")
	protected Integer plataformaId;
	
	@Column(name = "TOKENUSUARIO")
	protected String tokenUsuario;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion;
	
	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion;
	
	@Column(name = "DISPOSITIVOID")
	protected String dispositivoId;
	
	@Override
	public Object getId() {
		return this.usuarioId;
	}
	
	public void setId(Object id){
		this.usuarioId =(Integer)id;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getPlataformaId() {
		return plataformaId;
	}

	public void setPlataformaId(Integer plataformaId) {
		this.plataformaId = plataformaId;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	
	

	
}
