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
	private Integer ServicioId;
	private Integer PlataformaId;
	private Date FechaCreacion;
	private Date FechaModificacion;
	private String tokenUsuario;
	private String dispositivoId;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	
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
		return ServicioId;
	}
	public void setServicioId(Integer servicioId) {
		ServicioId = servicioId;
	}
	public Integer getPlataformaId() {
		return PlataformaId;
	}
	public void setPlataformaId(Integer plataformaId) {
		PlataformaId = plataformaId;
	}
	public Date getFechaCreacion() {
		return FechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return FechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		FechaModificacion = fechaModificacion;
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
	
	
	

}
