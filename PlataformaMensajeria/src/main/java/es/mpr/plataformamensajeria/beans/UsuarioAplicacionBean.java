package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un usuario para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class UsuarioAplicacionBean implements Audit{
	
		private static final String MODO_LECTURA="Lectura";
		private static final String MODO_LECTURA_ESCRITURA="Lectura/Escritura";
		private static final String MODO_NO_DEFINIDO="No definido";
		
		public UsuarioAplicacionBean() {
			super();
			this.usuarioId = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.usuarioAplicacionId = null;
			this.aplicacionId=null;
			this.modo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.nombreUsuario = null;
			this.rolUsuario = null;
			this.modoLectura=null;

		}

		protected Long usuarioAplicacionId;

		protected Long usuarioId;
		
		protected Long aplicacionId;
		
		protected Integer rolId;
		
		protected Integer modo;
		
		protected Date fechaCreacion = null;
		
		protected String creadoPor = null;
		
		protected String nombreAplicacion;
		
		protected String nombreUsuario;
		
		protected String rolUsuario;
		
		protected Boolean isActivo = null;

		private String modoLectura = null;
		
		public void setId(Object id){
			this.usuarioAplicacionId = (Long) id;
		}
		public Long getId(){
			return this.usuarioAplicacionId;
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
			return null;
		}
		public Long getUsuarioId() {
			return usuarioId;
		}
		public void setUsuarioId(Long usuarioId) {
			this.usuarioId = usuarioId;
		}

		public Long getUsuarioAplicacionId() {
			return usuarioAplicacionId;
		}
		public void setUsuarioAplicacionId(Long usuarioAplicacionId) {
			this.usuarioAplicacionId = usuarioAplicacionId;
		}
		public Integer getModo() {
			return modo;
		}
		public void setModo(Integer modo) {
			this.modo = modo;
		}
		public Boolean getIsActivo() {
			return isActivo;
		}
		public void setIsActivo(Boolean isActivo) {
			this.isActivo = isActivo;
		}
		public Integer getRolId() {
			return rolId;
		}
		public void setRolId(Integer rolId) {
			this.rolId = rolId;
		}
		public String getRolUsuario() {
			return rolUsuario;
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
		
		public String getModoLectura(){
			if(modo!=null&&modo.intValue()==1){
				return MODO_LECTURA;
			}else if(modo!=null&&modo.intValue()==2){
				return MODO_LECTURA_ESCRITURA;
			}else{
				return MODO_NO_DEFINIDO;
			}
		}
}
