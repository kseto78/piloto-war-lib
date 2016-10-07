package es.mpr.plataformamensajeria.model.views;

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
 *  Representa la vista de usuarios movil de la base de datos
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_USUARIOS_PUSH")
@NamedQueries({         
	@NamedQuery(name = "selectViewUsuariosPushJPA", query = "SELECT e FROM ViewUsuariosPushJPA e")})

public class ViewUsuariosPushJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewUsuariosPushJPA() {
		super();
		this.usuarioId = null;
		this.nombreUsuario = null;
		this.plataforma = null;
		this.tokenUsuario = null;
		this.aplicacionId = null;
		this.aplicacion = null;
		this.servicioId = null;
		this.servicio = null;
		this.fecha = null;
	}

	
	@Id
	@SequenceGenerator(name="usuario", sequenceName="USUARIOS_PUSH_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
	protected Integer usuarioId;
	
	@Column(name="NOMBREUSUARIO")
	protected String nombreUsuario;
	
	@Column(name = "PLATAFORMA")
	protected String plataforma;
	
	@Column(name = "TOKENUSUARIO")
	protected String tokenUsuario;
	
	@Column(name = "APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name = "APLICACION")
	protected String aplicacion;
	
	@Column(name = "SERVICIOID")
	protected Integer servicioId;
	
	@Column(name = "SERVICIO")
	protected String servicio;
	
	@Column(name = "FECHA")
	protected Date fecha;
	
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

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
}
