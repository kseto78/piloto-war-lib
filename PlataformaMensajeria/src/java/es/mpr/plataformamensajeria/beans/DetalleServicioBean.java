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
public class DetalleServicioBean implements Audit{
	
		public DetalleServicioBean() {
			this.servicioId = null;
			this.nombre = null;
			this.descripcion = null;
			this.activo = null;
			this.canalId = null;
			this.aplicacionId = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
			this.externalId = null;
			this.nmaxenvios = null;
			this.headerSMS = null;
			this.canalNombre = null;
			this.aplicacionNombre = null;
			this.historificacion = null;
			this.conservacion = null;
			this.pendienteAprobacion = null;
		}

		
		protected Integer servicioId;
		protected String nombre = null;
		protected String descripcion = null;
		protected Integer activo = null;
		protected Integer canalId = null;
		protected Integer aplicacionId = null;
		protected Date fechaCreacion = null;
		protected String creadoPor = null;
		protected Date fechaModificacion = null;
		protected String modificadoPor = null;
		protected String  externalId = null;
		protected Integer nmaxenvios = null;
		protected String headerSMS = null;
		protected String isActivo = null; 
		protected String canalNombre = null;
		protected String aplicacionNombre = null;
		protected List<ServidoresServiciosBean> listaServidoresServicios;
		protected List<PlanificacionBean> listaPlanificaciones;
		protected String fromMail;
		protected String fromMailName;
		protected Integer historificacion = null;
		protected Integer conservacion = null;
		protected Integer pendienteAprobacion = null;
		

		public String getFromMail() {
			return fromMail;
		}
		public void setFromMail(String fromMail) {
			this.fromMail = fromMail;
		}
		public String getFromMailName() {
			return fromMailName;
		}
		public void setFromMailName(String fromMailName) {
			this.fromMailName = fromMailName;
		}
		public List<ServidoresServiciosBean> getListaServidoresServicios() {
			return listaServidoresServicios;
		}
		public void setListaServidoresServicios(
				List<ServidoresServiciosBean> listaServidoresServicios) {
			this.listaServidoresServicios = listaServidoresServicios;
		}
		public List<PlanificacionBean> getListaPlanificaciones() {
			return listaPlanificaciones;
		}
		public void setListaPlanificaciones(List<PlanificacionBean> listaPlanificaciones) {
			this.listaPlanificaciones = listaPlanificaciones;
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
			return servicioId;
		}


		public void setId(Object servicioId) {
			this.servicioId = (Integer)servicioId;
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
		public Integer getServicioId() {
			return servicioId;
		}
		public void setServicioId(Integer servicioId) {
			this.servicioId = servicioId;
		}
		public Integer getCanalId() {
			return canalId;
		}
		public void setCanalId(Integer canalId) {
			this.canalId = canalId;
		}
		public Integer getAplicacionId() {
			return aplicacionId;
		}
		public void setAplicacionId(Integer aplicacionId) {
			this.aplicacionId = aplicacionId;
		}
		public Integer getNmaxenvios() {
			return nmaxenvios;
		}
		public void setNmaxenvios(Integer nmaxenvios) {
			this.nmaxenvios = nmaxenvios;
		}
		public String getHeaderSMS() {
			return headerSMS;
		}
		public void setHeaderSMS(String headerSMS) {
			this.headerSMS = headerSMS;
		}
		public String getCanalNombre() {
			return canalNombre;
		}
		public void setCanalNombre(String canalNombre) {
			this.canalNombre = canalNombre;
		}
		public String getAplicacionNombre() {
			return aplicacionNombre;
		}
		public void setAplicacionNombre(String aplicacionNombre) {
			this.aplicacionNombre = aplicacionNombre;
		}
		public Integer getHistorificacion() {
			return historificacion;
		}
		public void setHistorificacion(Integer historificacion) {
			this.historificacion = historificacion;
		}
		public Integer getConservacion() {
			return conservacion;
		}
		public void setConservacion(Integer conservacion) {
			this.conservacion = conservacion;
		}
		public Integer getPendienteAprobacion() {
			return pendienteAprobacion;
		}
		public void setPendienteAprobacion(Integer pendienteAprobacion) {
			this.pendienteAprobacion = pendienteAprobacion;
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
