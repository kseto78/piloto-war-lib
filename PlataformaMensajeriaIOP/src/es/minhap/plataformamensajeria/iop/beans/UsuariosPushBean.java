package es.minhap.plataformamensajeria.iop.beans;

import java.util.Date;


/**
 * Formato de Usuarios Push
 <pre>
 	 <Aplicacion></Aplicacion>
     <Servicio></Servicio>
     <Usuario></Usuario>
     <Password></Password>
     <NombreUsuario></NombreUsuario>
     <Plataforma></Plataforma>
     <RegistroId></RegistroId>
     <DispositivoId></DispositivoId>
 </pre>
 * @author jgonzvil
 *
 */
public class UsuariosPushBean {
	
	private Integer usuarioId;
	private String nombreUsuario;
	private Integer servicioId;
	private Integer plataformaId;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String tokenUsuario;
	private String dispositivoId;
	private String nombre;
	private String apellido1;
	private String apellido2;
	protected String plataforma;
	private Integer aplicacionId;
	private Date fechaDesde;
	private Date fechaHasta;
		
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
	public String getTokenUsuario() {
		return tokenUsuario;
	}
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}
	public String getDispositivoId() {
		return dispositivoId;
	}
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
	/**
	 * @return the plataforma
	 */
	public String getPlataforma() {
		return plataforma;
	}
	/**
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
}
