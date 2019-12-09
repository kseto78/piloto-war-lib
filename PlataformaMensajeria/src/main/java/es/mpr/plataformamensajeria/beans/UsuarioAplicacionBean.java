package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

// TODO: Auto-generated Javadoc
/**
 * <p>Clase que representa un usuario para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class UsuarioAplicacionBean implements Audit{
	
		/** Constante MODO_LECTURA. */
		private static final String MODO_LECTURA="Lectura";
		
		/** Constante MODO_LECTURA_ESCRITURA. */
		private static final String MODO_LECTURA_ESCRITURA="Lectura/Escritura";
		
		/** Constante MODO_NO_DEFINIDO. */
		private static final String MODO_NO_DEFINIDO="No definido";

		/**  usuario aplicacion id. */
		protected Long usuarioAplicacionId;

		/**  usuario id. */
		protected Long usuarioId;

		/**  aplicacion id. */
		protected Long aplicacionId;

		/**  rol id. */
		protected Integer rolId;

		/**  modo. */
		protected Integer modo;

		/**  fecha creacion. */
		protected Date fechaCreacion = null;

		/**  creado por. */
		protected String creadoPor = null;

		/**  nombre aplicacion. */
		protected String nombreAplicacion;

		/**  nombre usuario. */
		protected String nombreUsuario;

		/**  rol usuario. */
		protected String rolUsuario;

		/**  is activo. */
		protected Boolean isActivo = null;

		/**  modo lectura. */
		@SuppressWarnings("unused")
		private String modoLectura = null;
		
		/**
		 * Constructor de usuario aplicacion bean.
		 */
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

		/**
		 * Modificar id.
		 *
		 * @param id new id
		 */
		public void setId(Object id){
			this.usuarioAplicacionId = (Long) id;
		}
		
		/**
		 * Obtener id.
		 *
		 * @return id
		 */
		public Long getId(){
			return this.usuarioAplicacionId;
		}
		
		/**
		 * Obtener fecha creacion.
		 *
		 * @return fecha creacion
		 */
		public Date getFechaCreacion() {
			return fechaCreacion;
		}
		
		/**
		 * Modificar fecha creacion.
		 *
		 * @param fechaCreacion new fecha creacion
		 */
		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		
		/**
		 * Obtener creado por.
		 *
		 * @return creado por
		 */
		public String getCreadoPor() {
			return creadoPor;
		}
		
		/**
		 * Modificar creado por.
		 *
		 * @param creadoPor new creado por
		 */
		public void setCreadoPor(String creadoPor) {
			this.creadoPor = creadoPor;
		}
		
		/* (non-Javadoc)
		 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
		 */
		@Override
		public String obtenerXML() {
			return null;
		}
		
		/**
		 * Obtener usuario id.
		 *
		 * @return usuario id
		 */
		public Long getUsuarioId() {
			return usuarioId;
		}
		
		/**
		 * Modificar usuario id.
		 *
		 * @param usuarioId new usuario id
		 */
		public void setUsuarioId(Long usuarioId) {
			this.usuarioId = usuarioId;
		}

		/**
		 * Obtener usuario aplicacion id.
		 *
		 * @return usuario aplicacion id
		 */
		public Long getUsuarioAplicacionId() {
			return usuarioAplicacionId;
		}
		
		/**
		 * Modificar usuario aplicacion id.
		 *
		 * @param usuarioAplicacionId new usuario aplicacion id
		 */
		public void setUsuarioAplicacionId(Long usuarioAplicacionId) {
			this.usuarioAplicacionId = usuarioAplicacionId;
		}
		
		/**
		 * Obtener modo.
		 *
		 * @return modo
		 */
		public Integer getModo() {
			return modo;
		}
		
		/**
		 * Modificar modo.
		 *
		 * @param modo new modo
		 */
		public void setModo(Integer modo) {
			this.modo = modo;
		}
		
		/**
		 * Obtener checks if is activo.
		 *
		 * @return checks if is activo
		 */
		public Boolean getIsActivo() {
			return isActivo;
		}
		
		/**
		 * Modificar checks if is activo.
		 *
		 * @param isActivo new checks if is activo
		 */
		public void setIsActivo(Boolean isActivo) {
			this.isActivo = isActivo;
		}
		
		/**
		 * Obtener rol id.
		 *
		 * @return rol id
		 */
		public Integer getRolId() {
			return rolId;
		}
		
		/**
		 * Modificar rol id.
		 *
		 * @param rolId new rol id
		 */
		public void setRolId(Integer rolId) {
			this.rolId = rolId;
		}
		
		/**
		 * Obtener rol usuario.
		 *
		 * @return rol usuario
		 */
		public String getRolUsuario() {
			return rolUsuario;
		}
		
		/**
		 * Modificar rol usuario.
		 *
		 * @param rolUsuario new rol usuario
		 */
		public void setRolUsuario(String rolUsuario) {
			this.rolUsuario = rolUsuario;
		}
		
		/**
		 * Obtener nombre aplicacion.
		 *
		 * @return nombre aplicacion
		 */
		public String getNombreAplicacion() {
			return nombreAplicacion;
		}
		
		/**
		 * Modificar nombre aplicacion.
		 *
		 * @param nombreAplicacion new nombre aplicacion
		 */
		public void setNombreAplicacion(String nombreAplicacion) {
			this.nombreAplicacion = nombreAplicacion;
		}
		
		/**
		 * Obtener nombre usuario.
		 *
		 * @return nombre usuario
		 */
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		
		/**
		 * Modificar nombre usuario.
		 *
		 * @param nombreUsuario new nombre usuario
		 */
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		
		/**
		 * Obtener aplicacion id.
		 *
		 * @return aplicacion id
		 */
		public Long getAplicacionId() {
			return aplicacionId;
		}
		
		/**
		 * Modificar aplicacion id.
		 *
		 * @param aplicacionId new aplicacion id
		 */
		public void setAplicacionId(Long aplicacionId) {
			this.aplicacionId = aplicacionId;
		}
		
		/**
		 * Obtener modo lectura.
		 *
		 * @return modo lectura
		 */
		public String getModoLectura(){
			if(modo!=null&&modo.intValue()==1){
				return MODO_LECTURA;
			}else if(modo!=null&&modo.intValue()==2){
				return MODO_LECTURA_ESCRITURA;
			}else{
				return MODO_NO_DEFINIDO;
			}
		}

		/**
		 * Modificar modo lectura.
		 *
		 * @param modoLectura the modoLectura to set
		 */
		public void setModoLectura(String modoLectura) {
			this.modoLectura = modoLectura;
		}
}
