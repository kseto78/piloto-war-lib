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
			this.canalid = null;
			this.aplicacionid = null;
			this.fechacreacion = null;
			this.creadopor = null;
			this.fechamodificacion = null;
			this.modificadopor = null;
			this.externalid = null;
			this.nmaxenvios = null;
			this.headersms = null;
			this.canalnombre = null;
			this.aplicacionnombre = null;
			this.historificacion = null;
			this.conservacion = null;
			this.pendienteaprobacion = null;
		}

		
		protected Integer servicioId;
		protected String nombre = null;
		protected String descripcion = null;
		protected Boolean activo = null;
		protected Integer canalid = null;
		protected Integer aplicacionid = null;
		protected Date fechacreacion = null;
		protected String creadopor = null;
		protected Date fechamodificacion = null;
		protected String modificadopor = null;
		protected String  externalid = null;
		protected Integer nmaxenvios = null;
		protected String headersms = null;
		protected String isActivo = null; 
		protected String canalnombre = null;
		protected String aplicacionnombre = null;
		protected List<ServidoresServiciosBean> listaServidoresServicios;
		protected List<PlanificacionBean> listaPlanificaciones;
		protected String frommail;
		protected String frommailname;
		protected Integer historificacion = null;
		protected Integer conservacion = null;
		protected Boolean pendienteaprobacion = null;
		

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
				this.activo = true;
			}else{
				this.activo = false;
			}
		}
		public String getActivado(){
			if(activo!=null&&activo){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsActivo() {
			if(activo!=null&&activo){
				return "<span class='activo'></span>";
			}else{
				return "<span class='inactivo'></span>";
			}
			
		}


		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = true;
			}else{
				this.activo = false;
			}
			this.isActivo = isActivo;
		}


	
		public Object getId() {
			return servicioId;
		}


		public void setId(Object servicioId) {
			this.servicioId = (Integer)servicioId;
		}

		@Override
		public String obtenerXML() {
			// TODO Auto-generated method stub
			return null;
		}
		/**
		 * @return the servicioId
		 */
		public Integer getServicioId() {
			return servicioId;
		}
		/**
		 * @param servicioId the servicioId to set
		 */
		public void setServicioId(Integer servicioId) {
			this.servicioId = servicioId;
		}
		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}
		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		/**
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}
		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		/**
		 * @return the activo
		 */
		public Boolean getActivo() {
			return activo;
		}
		/**
		 * @param activo the activo to set
		 */
		public void setActivo(Boolean activo) {
			this.activo = activo;
		}
		/**
		 * @return the canalid
		 */
		public Integer getCanalid() {
			return canalid;
		}
		/**
		 * @param canalid the canalid to set
		 */
		public void setCanalid(Integer canalid) {
			this.canalid = canalid;
		}
		/**
		 * @return the aplicacionid
		 */
		public Integer getAplicacionid() {
			return aplicacionid;
		}
		/**
		 * @param aplicacionid the aplicacionid to set
		 */
		public void setAplicacionid(Integer aplicacionid) {
			this.aplicacionid = aplicacionid;
		}
		/**
		 * @return the fechacreacion
		 */
		public Date getFechacreacion() {
			return fechacreacion;
		}
		/**
		 * @param fechacreacion the fechacreacion to set
		 */
		public void setFechacreacion(Date fechacreacion) {
			this.fechacreacion = fechacreacion;
		}
		/**
		 * @return the creadopor
		 */
		public String getCreadopor() {
			return creadopor;
		}
		/**
		 * @param creadopor the creadopor to set
		 */
		public void setCreadopor(String creadopor) {
			this.creadopor = creadopor;
		}
		/**
		 * @return the fechamodificacion
		 */
		public Date getFechamodificacion() {
			return fechamodificacion;
		}
		/**
		 * @param fechamodificacion the fechamodificacion to set
		 */
		public void setFechamodificacion(Date fechamodificacion) {
			this.fechamodificacion = fechamodificacion;
		}
		/**
		 * @return the modificadopor
		 */
		public String getModificadopor() {
			return modificadopor;
		}
		/**
		 * @param modificadopor the modificadopor to set
		 */
		public void setModificadopor(String modificadopor) {
			this.modificadopor = modificadopor;
		}
		/**
		 * @return the externalid
		 */
		public String getExternalid() {
			return externalid;
		}
		/**
		 * @param externalid the externalid to set
		 */
		public void setExternalid(String externalid) {
			this.externalid = externalid;
		}
		/**
		 * @return the nmaxenvios
		 */
		public Integer getNmaxenvios() {
			return nmaxenvios;
		}
		/**
		 * @param nmaxenvios the nmaxenvios to set
		 */
		public void setNmaxenvios(Integer nmaxenvios) {
			this.nmaxenvios = nmaxenvios;
		}
		/**
		 * @return the headersms
		 */
		public String getHeadersms() {
			return headersms;
		}
		/**
		 * @param headersms the headersms to set
		 */
		public void setHeadersms(String headersms) {
			this.headersms = headersms;
		}
		/**
		 * @return the canalnombre
		 */
		public String getCanalnombre() {
			return canalnombre;
		}
		/**
		 * @param canalnombre the canalnombre to set
		 */
		public void setCanalnombre(String canalnombre) {
			this.canalnombre = canalnombre;
		}
		/**
		 * @return the aplicacionnombre
		 */
		public String getAplicacionnombre() {
			return aplicacionnombre;
		}
		/**
		 * @param aplicacionnombre the aplicacionnombre to set
		 */
		public void setAplicacionnombre(String aplicacionnombre) {
			this.aplicacionnombre = aplicacionnombre;
		}
		/**
		 * @return the frommail
		 */
		public String getFrommail() {
			return frommail;
		}
		/**
		 * @param frommail the frommail to set
		 */
		public void setFrommail(String frommail) {
			this.frommail = frommail;
		}
		/**
		 * @return the frommailname
		 */
		public String getFrommailname() {
			return frommailname;
		}
		/**
		 * @param frommailname the frommailname to set
		 */
		public void setFrommailname(String frommailname) {
			this.frommailname = frommailname;
		}
		/**
		 * @return the historificacion
		 */
		public Integer getHistorificacion() {
			return historificacion;
		}
		/**
		 * @param historificacion the historificacion to set
		 */
		public void setHistorificacion(Integer historificacion) {
			this.historificacion = historificacion;
		}
		/**
		 * @return the conservacion
		 */
		public Integer getConservacion() {
			return conservacion;
		}
		/**
		 * @param conservacion the conservacion to set
		 */
		public void setConservacion(Integer conservacion) {
			this.conservacion = conservacion;
		}
		/**
		 * @return the pendienteaprobacion
		 */
		public Boolean getPendienteaprobacion() {
			return pendienteaprobacion;
		}
		/**
		 * @param pendienteaprobacion the pendienteaprobacion to set
		 */
		public void setPendienteaprobacion(Boolean pendienteaprobacion) {
			this.pendienteaprobacion = pendienteaprobacion;
		}
		
		
		
}
