package es.mpr.plataformamensajeria.beans;


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
	private String aplicacionId;
	private String servicioId;
	private String usuarioAplicacion;
	private String passwordAplicacion;
	private String nombreusuario;
	private String plataformaId;
	private String registroId;
	private String dispositivoId;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public String getAplicacionId() {
		return aplicacionId;
	}
	
	public void setAplicacionId(String aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	
	public String getServicioId() {
		return servicioId;
	}
	
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}
	
	public String getUsuarioAplicacion() {
		return usuarioAplicacion;
	}
	
	public void setUsuarioAplicacion(String usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}
	
	public String getPasswordAplicacion() {
		return passwordAplicacion;
	}
	
	public void setPasswordAplicacion(String passwordAplicacion) {
		this.passwordAplicacion = passwordAplicacion;
	}
	
	public String getNombreusuario() {
		return nombreusuario;
	}
	
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	
	public String getPlataformaId() {
		return plataformaId;
	}
	
	public void setPlataformaId(String plataformaId) {
		this.plataformaId = plataformaId;
	}
	
	public String getRegistroId() {
		return registroId;
	}
	
	public void setRegistroId(String registroId) {
		this.registroId = registroId;
	}

	public String getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	
}
