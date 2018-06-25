package es.minhap.plataformamensajeria.iop.beans;

import java.io.Serializable;
import java.util.Date;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  
 *  Representa la tabla de usuarios movil
 *  
 *  @author jgonzvil
 */
public class UsuariosWebPushBean implements Serializable{
							  
	private static final long serialVersionUID = 1L;

	public UsuariosWebPushBean() {
		super();
		this.usuarioId = null;
		this.nombreUsuario = null;
		this.servicioId = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		
		this.aplicacionId = null;
		this.fechaDesde = null;
		this.fechaHasta = null;
		
		this.aplicacion = null;
		this.servicio = null;
	}
	
	
	protected Integer usuarioId;
	protected String nombreUsuario;
	protected Integer servicioId;
	protected Date fechaCreacion;
	protected Date fechaModificacion;
		
	protected Integer aplicacionId;
	protected Date fechaDesde;
	protected Date fechaHasta;
		
	protected String aplicacion;
	protected String servicio;
	
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
	
	
	public Integer getAplicacionId() {
		return aplicacionId;
	}


	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
