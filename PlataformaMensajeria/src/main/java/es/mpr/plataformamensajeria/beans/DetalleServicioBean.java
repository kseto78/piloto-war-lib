package es.mpr.plataformamensajeria.beans;

import java.util.Date;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un servicio para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class DetalleServicioBean implements Audit{
	
		/**
		 * Constructor de detalle servicio bean.
		 */
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

		
		/**  servicio id. */
		protected Integer servicioId;
		
		/**  nombre. */
		protected String nombre = null;
		
		/**  descripcion. */
		protected String descripcion = null;
		
		/**  activo. */
		protected Boolean activo = null;
		
		/**  canalid. */
		protected Integer canalid = null;
		
		/**  aplicacionid. */
		protected Integer aplicacionid = null;
		
		/**  fechacreacion. */
		protected Date fechacreacion = null;
		
		/**  creadopor. */
		protected String creadopor = null;
		
		/**  fechamodificacion. */
		protected Date fechamodificacion = null;
		
		/**  modificadopor. */
		protected String modificadopor = null;
		
		/**  externalid. */
		protected String  externalid = null;
		
		/**  nmaxenvios. */
		protected Integer nmaxenvios = null;
		
		/**  headersms. */
		protected String headersms = null;
		
		/**  is activo. */
		protected String isActivo = null; 
		
		/**  canalnombre. */
		protected String canalnombre = null;
		
		/**  aplicacionnombre. */
		protected String aplicacionnombre = null;
		
		/**  lista servidores servicios. */
		protected List<ServidoresServiciosBean> listaServidoresServicios;
		
		/**  lista planificaciones. */
		protected List<PlanificacionBean> listaPlanificaciones;
		
		/**  frommail. */
		protected String frommail;
		
		/**  frommailname. */
		protected String frommailname;
		
		/**  historificacion. */
		protected Integer historificacion = null;
		
		/**  conservacion. */
		protected Integer conservacion = null;
		
		/**  pendienteaprobacion. */
		protected Boolean pendienteaprobacion = null;
		

		/**
		 * Obtener lista servidores servicios.
		 *
		 * @return lista servidores servicios
		 */
		public List<ServidoresServiciosBean> getListaServidoresServicios() {
			return listaServidoresServicios;
		}
		
		
		/**
		 * Modificar lista servidores servicios.
		 *
		 * @param listaServidoresServicios new lista servidores servicios
		 */
		public void setListaServidoresServicios(
				List<ServidoresServiciosBean> listaServidoresServicios) {
			this.listaServidoresServicios = listaServidoresServicios;
		}
		
		
		/**
		 * Obtener lista planificaciones.
		 *
		 * @return lista planificaciones
		 */
		public List<PlanificacionBean> getListaPlanificaciones() {
			return listaPlanificaciones;
		}
		
		
		/**
		 * Modificar lista planificaciones.
		 *
		 * @param listaPlanificaciones new lista planificaciones
		 */
		public void setListaPlanificaciones(List<PlanificacionBean> listaPlanificaciones) {
			this.listaPlanificaciones = listaPlanificaciones;
		}
		
		
		/**
		 * Modificar activado.
		 *
		 * @param activado new activado
		 */
		public void setActivado(String activado){
			if(activado!=null&&activado.equals("true")){
				this.activo = true;
			}else{
				this.activo = false;
			}
		}
		
		
		/**
		 * Obtener activado.
		 *
		 * @return activado
		 */
		public String getActivado(){
			if(activo!=null&&activo){
				return "true";
			}else{
				return "false";
			}
			
		}
		
		
		/**
		 * Obtener checks if is activo.
		 *
		 * @return checks if is activo
		 */
		public String getIsActivo() {
			if(activo!=null&&activo){
				return "<span class='activo'></span>";
			}else{
				return "<span class='inactivo'></span>";
			}
			
		}


		/**
		 * Modificar checks if is activo.
		 *
		 * @param isActivo new checks if is activo
		 */
		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = true;
			}else{
				this.activo = false;
			}
			this.isActivo = isActivo;
		}


	
		/**
		 * Obtener id.
		 *
		 * @return id
		 */
		public Object getId() {
			return servicioId;
		}


		/**
		 * Modificar id.
		 *
		 * @param servicioId new id
		 */
		public void setId(Object servicioId) {
			this.servicioId = (Integer)servicioId;
		}

		/* (non-Javadoc)
		 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
		 */
		@Override
		public String obtenerXML() {
			// TODO Auto-generated method stub
			return null;
		}
		
		/**
		 * Obtener servicio id.
		 *
		 * @return the servicioId
		 */
		public Integer getServicioId() {
			return servicioId;
		}
		
		/**
		 * Modificar servicio id.
		 *
		 * @param servicioId the servicioId to set
		 */
		public void setServicioId(Integer servicioId) {
			this.servicioId = servicioId;
		}
		
		/**
		 * Obtener nombre.
		 *
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}
		
		/**
		 * Modificar nombre.
		 *
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		/**
		 * Obtener descripcion.
		 *
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}
		
		/**
		 * Modificar descripcion.
		 *
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		/**
		 * Obtener activo.
		 *
		 * @return the activo
		 */
		public Boolean getActivo() {
			return activo;
		}
		
		/**
		 * Modificar activo.
		 *
		 * @param activo the activo to set
		 */
		public void setActivo(Boolean activo) {
			this.activo = activo;
		}
		
		/**
		 * Obtener canalid.
		 *
		 * @return the canalid
		 */
		public Integer getCanalid() {
			return canalid;
		}
		
		/**
		 * Modificar canalid.
		 *
		 * @param canalid the canalid to set
		 */
		public void setCanalid(Integer canalid) {
			this.canalid = canalid;
		}
		
		/**
		 * Obtener aplicacionid.
		 *
		 * @return the aplicacionid
		 */
		public Integer getAplicacionid() {
			return aplicacionid;
		}
		
		/**
		 * Modificar aplicacionid.
		 *
		 * @param aplicacionid the aplicacionid to set
		 */
		public void setAplicacionid(Integer aplicacionid) {
			this.aplicacionid = aplicacionid;
		}
		
		/**
		 * Obtener fechacreacion.
		 *
		 * @return the fechacreacion
		 */
		public Date getFechacreacion() {
			return fechacreacion;
		}
		
		/**
		 * Modificar fechacreacion.
		 *
		 * @param fechacreacion the fechacreacion to set
		 */
		public void setFechacreacion(Date fechacreacion) {
			this.fechacreacion = fechacreacion;
		}
		
		/**
		 * Obtener creadopor.
		 *
		 * @return the creadopor
		 */
		public String getCreadopor() {
			return creadopor;
		}
		
		/**
		 * Modificar creadopor.
		 *
		 * @param creadopor the creadopor to set
		 */
		public void setCreadopor(String creadopor) {
			this.creadopor = creadopor;
		}
		
		/**
		 * Obtener fechamodificacion.
		 *
		 * @return the fechamodificacion
		 */
		public Date getFechamodificacion() {
			return fechamodificacion;
		}
		
		/**
		 * Modificar fechamodificacion.
		 *
		 * @param fechamodificacion the fechamodificacion to set
		 */
		public void setFechamodificacion(Date fechamodificacion) {
			this.fechamodificacion = fechamodificacion;
		}
		
		/**
		 * Obtener modificadopor.
		 *
		 * @return the modificadopor
		 */
		public String getModificadopor() {
			return modificadopor;
		}
		
		/**
		 * Modificar modificadopor.
		 *
		 * @param modificadopor the modificadopor to set
		 */
		public void setModificadopor(String modificadopor) {
			this.modificadopor = modificadopor;
		}
		
		/**
		 * Obtener externalid.
		 *
		 * @return the externalid
		 */
		public String getExternalid() {
			return externalid;
		}
		
		/**
		 * Modificar externalid.
		 *
		 * @param externalid the externalid to set
		 */
		public void setExternalid(String externalid) {
			this.externalid = externalid;
		}
		
		/**
		 * Obtener nmaxenvios.
		 *
		 * @return the nmaxenvios
		 */
		public Integer getNmaxenvios() {
			return nmaxenvios;
		}
		
		/**
		 * Modificar nmaxenvios.
		 *
		 * @param nmaxenvios the nmaxenvios to set
		 */
		public void setNmaxenvios(Integer nmaxenvios) {
			this.nmaxenvios = nmaxenvios;
		}
		
		/**
		 * Obtener headersms.
		 *
		 * @return the headersms
		 */
		public String getHeadersms() {
			return headersms;
		}
		
		/**
		 * Modificar headersms.
		 *
		 * @param headersms the headersms to set
		 */
		public void setHeadersms(String headersms) {
			this.headersms = headersms;
		}
		
		/**
		 * Obtener canalnombre.
		 *
		 * @return the canalnombre
		 */
		public String getCanalnombre() {
			return canalnombre;
		}
		
		/**
		 * Modificar canalnombre.
		 *
		 * @param canalnombre the canalnombre to set
		 */
		public void setCanalnombre(String canalnombre) {
			this.canalnombre = canalnombre;
		}
		
		/**
		 * Obtener aplicacionnombre.
		 *
		 * @return the aplicacionnombre
		 */
		public String getAplicacionnombre() {
			return aplicacionnombre;
		}
		
		/**
		 * Modificar aplicacionnombre.
		 *
		 * @param aplicacionnombre the aplicacionnombre to set
		 */
		public void setAplicacionnombre(String aplicacionnombre) {
			this.aplicacionnombre = aplicacionnombre;
		}
		
		/**
		 * Obtener frommail.
		 *
		 * @return the frommail
		 */
		public String getFrommail() {
			return frommail;
		}
		
		/**
		 * Modificar frommail.
		 *
		 * @param frommail the frommail to set
		 */
		public void setFrommail(String frommail) {
			this.frommail = frommail;
		}
		
		/**
		 * Obtener frommailname.
		 *
		 * @return the frommailname
		 */
		public String getFrommailname() {
			return frommailname;
		}
		
		/**
		 * Modificar frommailname.
		 *
		 * @param frommailname the frommailname to set
		 */
		public void setFrommailname(String frommailname) {
			this.frommailname = frommailname;
		}
		
		/**
		 * Obtener historificacion.
		 *
		 * @return the historificacion
		 */
		public Integer getHistorificacion() {
			return historificacion;
		}
		
		/**
		 * Modificar historificacion.
		 *
		 * @param historificacion the historificacion to set
		 */
		public void setHistorificacion(Integer historificacion) {
			this.historificacion = historificacion;
		}
		
		/**
		 * Obtener conservacion.
		 *
		 * @return the conservacion
		 */
		public Integer getConservacion() {
			return conservacion;
		}
		
		/**
		 * Modificar conservacion.
		 *
		 * @param conservacion the conservacion to set
		 */
		public void setConservacion(Integer conservacion) {
			this.conservacion = conservacion;
		}
		
		/**
		 * Obtener pendienteaprobacion.
		 *
		 * @return the pendienteaprobacion
		 */
		public Boolean getPendienteaprobacion() {
			return pendienteaprobacion;
		}
		
		/**
		 * Modificar pendienteaprobacion.
		 *
		 * @param pendienteaprobacion the pendienteaprobacion to set
		 */
		public void setPendienteaprobacion(Boolean pendienteaprobacion) {
			this.pendienteaprobacion = pendienteaprobacion;
		}
		
		
		
}
