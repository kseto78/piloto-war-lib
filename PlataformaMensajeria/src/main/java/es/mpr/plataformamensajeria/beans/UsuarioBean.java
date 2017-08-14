package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un usuario para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class UsuarioBean implements Audit{


		public UsuarioBean() {
			super();
			this.usuarioId = null;
			this.nombre = null;
			this.login = null;
			this.email = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.activo = null;
			this.rolId = null;
			this.rolUsuario = null;
			this.nombreAplicacion = null;
			this.nombreUsuario = null;
			this.aplicacionId = null;

		}

		protected Long usuarioId;

		protected String nombre = null;
		
		protected Boolean activo = null;
		
		protected Date fechaCreacion = null;
		
		protected String creadoPor = null;
		
		protected String login = null;
		
		protected String email = null;
		
		protected Integer rolId = null;
		
		protected String rolUsuario = null;
		
		protected String nombreAplicacion = null;
				
		protected String nombreUsuario = null;
		
		protected Long aplicacionId = null;
		
		protected Date fechaModificacion = null;
		
		protected String modificadoPor = null;
		
		protected String isActivo = null;
		
		
		public void setActivado(String activado) {
			if (activado != null && activado.equals("true")) {
				this.activo = true;
			} else {
				this.activo = false;
			}
		}

		public String getActivado() {
			if (activo != null && activo) {
				return "true";
			} else if (activo != null && !activo) {
				return "false";
			} else {
				return null;
			}

		}
		
		public String getIsActivo() {
			if (activo != null && activo) {
				return "<span class='activo'></span>";
			} else if (activo != null && !activo) {
				return "<span class='inactivo'></span>";
			} else {
				return null;
			}

		}

		public void setIsActivo(String isActivo) {
			if (isActivo != null && isActivo.equals("true")) {
				this.activo = true;
			} else {
				this.activo = false;
			}

		}
		
		public void setId(Object id){
			this.usuarioId = (Long) id;
		}
		public Long getId(){
			return this.usuarioId;
		}
		
		public Boolean getActivo() {
			return activo;
		}
		public void setActivo(Boolean activo) {
			this.activo = activo;
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
		
		@Override
		public String obtenerXML() {
			// TODO Auto-generated method stub
			return null;
		}
		public Long getUsuarioId() {
			return usuarioId;
		}
		public void setUsuarioId(Long usuarioId) {
			this.usuarioId = usuarioId;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getRolId() {
			return rolId;
		}
		public void setRolId(Integer rolId) {
			this.rolId = rolId;
		}
		public String getRolUsuario() {
			if(rolId!=null&&rolId.intValue()==1){
				return "Administrador";
			}else if(rolId!=null&&rolId.intValue()==2){
				return "Propietario Aplicacion";
			}else{
				return "<Rol no definido>";
			}
		}
		public void setRolUsuario(String rolUsuario) {
			this.rolUsuario = rolUsuario;
		}
		public String getNombreAplicacion() {
			return nombreAplicacion;
		}
		public void setNombreAplicacion(String nombreAplicacion) {
			this.nombreAplicacion = nombreAplicacion;
		}
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		public Long getAplicacionId() {
			return aplicacionId;
		}
		public void setAplicacionId(Long aplicacionId) {
			this.aplicacionId = aplicacionId;
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
}
