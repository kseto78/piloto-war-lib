package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class ParametroServidorBean implements Audit{
		public static final int IP=1;
		public static final int USUARIO=2;
		public static final int PASSWORD=3;
		public static final int CONEXION_SEGURA=4;
		public static final int PUERTO=10;
		public static final int REQ_AUTH=11;
		
	
		public ParametroServidorBean() {
			
			this.parametroServidorId = null;
			this.tipoParametroId = null;
			this.tipoNombre = null;
			this.valor = null;
			this.tipoDescripcion = null;
			this.servidorId = null;
			this.tipoTag = null;
			this.activo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;

		}

		protected Integer parametroServidorId;

		protected String valor = null;
		
		protected Integer tipoParametroId = null;
		
		protected Integer servidorId = null;
		
		protected Integer activo = null;
		
		protected Date fechaCreacion = null;
		
		protected String creadoPor = null;
		
		protected Date fechaModificacion = null;
		
		protected String modificadoPor = null;
		
		protected String tipoNombre = null;
		
		protected String tipoDescripcion = null;
		
		protected String tipoTag = null;
		
		public String getTipoNombre() {
			if(tipoNombre!=null){
				return StringUtil.capitalize(tipoNombre);
			}else{
				return "";
			}
		}
		public void setTipoNombre(String tipoNombre) {
			this.tipoNombre = tipoNombre;
		}
		public String getTipoDescripcion() {
			
			return tipoDescripcion;
		}
		public void setTipoDescripcion(String tipoDescripcion) {
			this.tipoDescripcion = tipoDescripcion;
		}
		public String getTipoTag() {
			return tipoTag;
		}
		public void setTipoTag(String tipoTag) {
			this.tipoTag = tipoTag;
		}
		public Object getId() {
			return this.parametroServidorId;
		}
		public void setId(Object id){
			this.parametroServidorId =(Integer)id;
		}
		public Integer getParametroServidorId() {
			return parametroServidorId;
		}
		public void setParametroServidorId(Integer parametroServidorId) {
			this.parametroServidorId = parametroServidorId;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public Integer getTipoParametroId() {
			return tipoParametroId;
		}
		public void setTipoParametroId(Integer tipoParametroId) {
			this.tipoParametroId = tipoParametroId;
		}
		public Integer getServidorId() {
			return servidorId;
		}
		public void setServidorId(Integer servidorId) {
			this.servidorId = servidorId;
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
