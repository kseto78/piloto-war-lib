package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un receptor para la capa de presentacion
 * 
 * @author jgonzvil
 *
 */
public class ReceptorSMSBean implements Audit{
	
	
		private static final long serialVersionUID = 1L;

		public ReceptorSMSBean() {
			super();
			this.receptorSMSId = null;
			this.nombre = null;
			this.descripcion = null;
			this.porDefecto = null;
			this.activo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
			this.tipo = null;
			this.externalId = null;
			this.usuario = null;
			this.password = null;
			this.rePassword = null;
		}

		
		protected Long receptorSMSId;
		protected String nombre = null;
		protected String descripcion = null;
		protected Integer porDefecto = null;
		protected Integer activo = null;
		protected Date fechaCreacion = null;
		protected String creadoPor = null;
		protected Date fechaModificacion = null;
		protected String modificadoPor = null;
		protected Integer tipo = null;
		protected String externalId = null;
		protected String usuario = null;
		protected String password = null;
		protected String rePassword = null;

		public void setDefecto(String defecto){
			if(defecto!=null&&defecto.equals("true")){
				this.porDefecto = Integer.valueOf(1);
			}else{
				this.porDefecto = Integer.valueOf(0);
			}
		}
		public String getDefecto(){
			if(porDefecto!=null&&porDefecto.intValue()==1){
				return "true";
			}else if(porDefecto!=null&&porDefecto.intValue()==0){
				return "false";
			}else{
				return null;
			}
			
			
		}
		
		
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
			}else if(activo!=null&&activo.intValue()==0){
				return "false";
			}else{
				return null;
			}
			
			
		}
		public String getIsDefecto() {
			if(porDefecto!=null&&porDefecto.intValue()==1){
				return "<span class='activo'></span>";
			}else if(porDefecto!=null&&porDefecto.intValue()==0){
				return "<span class='inactivo'></span>";
			}else{
				return null;
			}
			
		}


		public void setIsDefecto(String isDefecto) {
			if(isDefecto!=null&&isDefecto.equals("true")){
				this.porDefecto = Integer.valueOf(1);
			}else{
				this.porDefecto = Integer.valueOf(0);
			}
			
		}		
		public String getIsActivo() {
			if(activo!=null&&activo.intValue()==1){
				return "<span class='activo'></span>";
			}else if(activo!=null&&activo.intValue()==0){
				return "<span class='inactivo'></span>";
			}else{
				return null;
			}
			
		}


		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = Integer.valueOf(1);
			}else{
				this.activo = Integer.valueOf(0);
			}
			
		}


		public Long getReceptorSMSId() {
			return receptorSMSId;
		}


		public void setReceptorSMSId(Long receptorSMSId) {
			this.receptorSMSId = receptorSMSId;
		}

		public Long getId() {
			return receptorSMSId;
		}


		public void setId(Long receptorSMSId) {
			this.receptorSMSId = receptorSMSId;
		}

		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getDescripcion() {
			return descripcion;
		}


		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}


		public Integer getPorDefecto() {
			return porDefecto;
		}


		public void setPorDefecto(Integer porDefecto) {
			this.porDefecto = porDefecto;
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

		public Integer getTipo() {
			return tipo;
		}


		public void setTipo(Integer tipo) {
			this.tipo = tipo;
		}


		public String getExternalId() {
			return externalId;
		}


		public void setExternalId(String externalId) {
			this.externalId = externalId;
		}
		

		@Override
		public String obtenerXML() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRePassword() {
			return rePassword;
		}
		public void setRePassword(String rePassword) {
			this.rePassword = rePassword;
		}
		
}
