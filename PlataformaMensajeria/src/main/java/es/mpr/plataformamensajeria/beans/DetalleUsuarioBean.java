package es.mpr.plataformamensajeria.beans;

import java.util.Date;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un servicio para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class DetalleUsuarioBean implements Audit{
	
		public DetalleUsuarioBean() {
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
		protected Integer activo = null;	
		protected Date fechaCreacion = null;		
		protected String creadoPor = null;		
		protected String login = null;		
		protected String email = null;		
		protected Integer rolId = null;		
		protected String rolUsuario = null;		
		protected String nombreAplicacion = null;				
		protected String nombreUsuario = null;		
		protected Integer aplicacionId = null;		
		protected Date fechaModificacion = null;		
		protected String modificadoPor = null;
		protected String isActivo = null;
	

		public void setActivado(String activado){
			if(activado!=null&&activado.equals("true")){
				this.activo = Integer.valueOf(1);
			}else{
				this.activo = Integer.valueOf(0);
			}
		}
		public String getActivado(){
			if(activo!=null&&activo.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsActivo() {
			if(activo!=null&&activo.intValue()==1){
				return "<span class='activo'></span>";
			}else{
				return "<span class='inactivo'></span>";
			}
			
		}


		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = new Integer(1);
			}else{
				this.activo = new Integer(0);
			}
			this.isActivo = isActivo;
		}


	
		public Object getId() {
			return usuarioId;
		}


		public void setId(Object servicioId) {
			this.usuarioId = (Long)usuarioId;
		}

		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public Integer getActivo() {
			return activo;
		}


		public void setActivo(Integer activo) {
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
		public Long getUsuarioId() {
			return usuarioId;
		}
		public void setUsuarioId(Long usuarioId) {
			this.usuarioId = usuarioId;
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


                
                /*
	 * Devuelve el objeto como un XML
	 * 
	 
	public String obtenerXML() {
		StringBuffer sb = new StringBuffer("<objeto>OrganimoJPA</objeto>");
		if(id != null)
			sb.append("<id>"  + id +"</id>" );
		if(nombre != null)
			sb.append("<nombre>"  + nombre +"</nombre>" );
		if(rol != null)
			sb.append("<rol>"  + rol +"</rol>" );
		if(organismoPadre != null)
			sb.append("<organismoPadre>"  + organismoPadre +"</organismoPadre>" );
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}
*/
}
