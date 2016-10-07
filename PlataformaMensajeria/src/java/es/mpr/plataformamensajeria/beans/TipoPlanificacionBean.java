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
public class TipoPlanificacionBean implements Audit{
	
	
		private static final long serialVersionUID = 1L;

		public TipoPlanificacionBean() {
			super();
			this.tipoPlanificacionId=null;
			this.nombre=null;
			this.descripcion=null;
			this.activo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
		}

		public Integer getTipoPlanificacionId() {
			return tipoPlanificacionId;
		}
		public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
			this.tipoPlanificacionId = tipoPlanificacionId;
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

		protected Integer tipoPlanificacionId;
		protected String nombre;
		protected String descripcion;
		
		
		protected Integer activo = null;
		
		protected Date fechaCreacion = null;
		
		protected String creadoPor = null;
		
		protected Date fechaModificacion = null;
		
		protected String modificadoPor = null;
		
		public Object getId() {
			return this.tipoPlanificacionId;
		}
		public void setId(Object id){
			this.tipoPlanificacionId =(Integer)id;
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
