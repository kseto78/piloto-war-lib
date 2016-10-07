package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class ServidorBean implements Audit{
	
	
		private static final long serialVersionUID = 1L;

		public ServidorBean() {
			super();
			this.servidorId = null;
			this.nombre = null;
			this.descripcion = null;
			this.porDefecto = null;
			this.activo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
			this.urlDestino = null;
			this.tipo = null;
			this.externalId = null;
		}

		
		protected Long servidorId;
		protected String nombre = null;
		protected String descripcion = null;
		protected Integer porDefecto = null;
		protected Integer activo = null;
		protected Date fechaCreacion = null;
		protected String creadoPor = null;
		protected Date fechaModificacion = null;
		protected String modificadoPor = null;
		protected String urlDestino = null;
		protected Integer tipo = null;
		protected String  externalId = null;
		protected String isActivo = null;
		protected String isDefecto = null;
		
		public void setDefecto(String defecto){
			if(defecto!=null&&defecto.equals("true")){
				this.porDefecto = new Integer(1);
			}else{
				this.porDefecto= new Integer(0);
			}		
		}
		public void setActivado(String activado){
			if(activado!=null&&activado.equals("true")){
				this.activo = new Integer(1);
			}else{
				this.activo = new Integer(0);
			}
		}
		public String getDefecto(){
			if(porDefecto!=null&&porDefecto.intValue()==1){
				return "true";
			}else{
				return "false";
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
		public String getIsDefecto() {
			if(porDefecto!=null&&porDefecto.intValue()==1){
				return "<span class='activo'></span>";
			}else{
				return "<span class='inactivo'></span>";
			}
			
		}
		public void setIsDefecto(String isDefecto) {
			if(isDefecto!=null&&isDefecto.equals("true")){
				this.porDefecto = new Integer(1);
			}else{
				this.porDefecto= new Integer(0);
			}
			this.isDefecto= isDefecto;
		}

		
		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = new Integer(1);
			}else{
				this.activo = new Integer(0);
			}
			this.isActivo = isActivo;
		}


		public Long getServidorId() {
			return servidorId;
		}


		public void setServidorId(Long servidorId) {
			this.servidorId = servidorId;
		}

		public Long getId() {
			return servidorId;
		}


		public void setId(Long servidorId) {
			this.servidorId = servidorId;
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


		public String getUrlDestino() {
			return urlDestino;
		}


		public void setUrlDestino(String urlDestino) {
			this.urlDestino = urlDestino;
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
