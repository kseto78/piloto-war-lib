package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>Clase que representa un tipo parametro para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class TipoParametroBean implements Audit{
	
	
		private static final long serialVersionUID = 1L;

		protected Integer tipoParametroId;
		protected String nombre;
		protected String descripcion;
		protected String tag;
		protected Integer tipo;
		protected String tipoCampo;
		protected Integer activo;
		protected Date fechaCreacion = null;
		protected String creadoPor = null;
		protected Date fechaModificacion = null;
		protected String modificadoPor = null;		

		public TipoParametroBean() {
			super();
			this.tipoParametroId=null;
			this.nombre = null;
			this.descripcion = null;
			this.tag = null;
			this.activo = null;
			this.tipo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;			
		}
		
		public Object getId() {
			return this.tipoParametroId;
		}
		public void setId(Object id){
			this.tipoParametroId =(Integer)id;
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

		public Integer getTipoParametroId() {
			return tipoParametroId;
		}





		public void setTipoParametroId(Integer tipoParametroId) {
			this.tipoParametroId = tipoParametroId;
		}





		public String getNombre() {
			if(nombre!=null){
				return StringUtil.capitalize(nombre);
			}else{
				return "";
			}
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





		public String getTag() {
			return tag;
		}





		public void setTag(String tag) {
			this.tag = tag;
		}





		public Integer getTipo() {
			return tipo;
		}





		public void setTipo(Integer tipo) {
			this.tipo = tipo;
		}

		public String getTipoCampo() {
			return tipoCampo;
		}
		
		public void setTipoCampo(String tipoCampo) {
			this.tipoCampo = tipoCampo;
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
