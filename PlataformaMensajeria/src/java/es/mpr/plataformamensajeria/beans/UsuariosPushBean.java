package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

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
public class UsuariosPushBean implements Audit{
							  
	public UsuariosPushBean() {
		super();
		this.usuarioId = null;
		this.nombreUsuario = null;
		this.servicioId = null;
		this.plataformaId = null;
		this.tokenUsuario = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		
		this.aplicacionId = null;
		this.plataforma = null;
		this.fechaDesde = null;
		this.fechaHasta = null;
		this.fecha = null;
		
		this.aplicacion = null;
		this.servicio = null;
	}
	
	
	protected Integer usuarioId;
	protected String nombreUsuario;
	protected Integer servicioId;
	protected Integer plataformaId;
	protected String tokenUsuario;
	protected Date fechaCreacion;
	protected Date fechaModificacion;
	protected String dispositivoId;
	
	protected Integer aplicacionId;
	protected String plataforma;
	protected Date fechaDesde;
	protected Date fechaHasta;
	protected Date fecha;
	
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
	
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
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


	public String getPlataforma() {
		return plataforma;
	}


	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
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


	public String getDispositivoId() {
		return dispositivoId;
	}


	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	

}
