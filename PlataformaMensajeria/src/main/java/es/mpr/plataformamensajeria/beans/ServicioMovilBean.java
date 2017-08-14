package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


	
public class ServicioMovilBean implements Audit{

	
	protected Long servicioMovilId;
	protected String nombre = null;
	protected String descripcion = null;
	protected Long tipo = null;
	protected Long estado = null;
	protected String urlServicio = null;
	protected String nombreContacto = null;
	protected String telefonoContacto = null;
	protected Date fechaCreacion = null;
	protected Date fechaModificacion = null;
	protected String creadoPor = null;
	protected String modificadoPor = null;
	protected String isEstado = null;
	protected Long indSuscripcion = null;
	protected String urlAvisoSuscripcion = null;
	protected String isIndSuscripcion = null;
	protected String endpointUser = null;
	protected String endpointPass = null;
	protected String icono = null;
	protected String tipoServicio = null;
	
	public ServicioMovilBean() {
		super();
		this.servicioMovilId = null;
		this.nombre = null;
		this.descripcion = null;
		this.tipo = (long) 0;
		this.estado = null;
		this.urlServicio = null;
		this.nombreContacto = null;
		this.telefonoContacto = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.isEstado = null;
		this.indSuscripcion = (long) 0;
		this.urlAvisoSuscripcion = null;
		this.isIndSuscripcion = null;
		this.endpointUser = null;
		this.endpointPass = null;
		this.icono = null;
		this.tipoServicio = null;
	}

	
	public Long getServicioMovilId() {
		return servicioMovilId;
	}
	
	
	
	public void setServicioMovilId(Long servicioMovilId) {
		this.servicioMovilId = servicioMovilId;
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
	
	
		
	public String getUrlServicio() {
		return urlServicio;
	}
	
	
	
	public void setUrlServicio(String urlServicio) {
		this.urlServicio = urlServicio;
	}
	
	
	
	public String getNombreContacto() {
		return nombreContacto;
	}
	
	
	
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	
	
	
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	
	
	
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
	public String getCreadoPor() {
		return creadoPor;
	}
	
	
	
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	
	
	public String getModificadoPor() {
		return modificadoPor;
	}
	
	
	
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	

	public Long getTipo() {
		return tipo;
	}


	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	
		
	public Long getEstado() {
		return estado;
	}


	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	public void setActivado(String activado){
		if(activado!=null&&activado.equals("true")){
			this.estado = new Long(1);
		}else{
			this.estado = new Long(0);
		}
	}
	
	
	public String getActivado(){
		if(estado!=null&&estado.intValue()==1){
			return "true";
		}else{
			return "false";
		}
	}	
	
	
	public String getIsEstado() {
		if(estado!=null&&estado.intValue()==1){
			return "<span class='activo'></span>";
		}else{
			return "<span class='inactivo'></span>";
		}
		
	}
	
	
	public void setIsEstado(String isEstado) {
		if(isEstado!=null&&isEstado.equals("true")){
			this.estado = new Long(1);
		}else{
			this.estado = new Long(0);
		}
		this.isEstado   = isEstado;
	}

	public void setSuscrito(String suscrito){
		if(suscrito != null && suscrito.equals("true")){
			this.indSuscripcion = new Long(1);
		}else{
			this.indSuscripcion = new Long(0);
		}
	}
	
	
	public String getSuscrito(){
		if(indSuscripcion != null && indSuscripcion.intValue()==1){
			return "true";
		}else{
			return "false";
		}
	}	
	
	
	public String getIsIndSuscripcion() {
		if(indSuscripcion != null && indSuscripcion.intValue()==1){
			return "<span class='activo'></span>";
		}else{
			return "<span class='inactivo'></span>";
		}
		
	}
	
	
	public void setIsIndSuscripcion(String isIndSuscripcion) {
		if(isIndSuscripcion!=null && isIndSuscripcion.equals("true")){
			this.indSuscripcion = new Long(1);
		}else{
			this.indSuscripcion = new Long(0);
		}
		this.isIndSuscripcion   = isIndSuscripcion;
	}

	public Long getIndSuscripcion() {
		return indSuscripcion;
	}


	public void setIndSuscripcion(Long indSuscripcion) {
		this.indSuscripcion = indSuscripcion;
	}


	public String getUrlAvisoSuscripcion() {
		return urlAvisoSuscripcion;
	}


	public void setUrlAvisoSuscripcion(String urlAvisoSuscripcion) {
		this.urlAvisoSuscripcion = urlAvisoSuscripcion;
	}


	public String getEndpointUser() {
		return endpointUser;
	}


	public void setEndpointUser(String endpointUser) {
		this.endpointUser = endpointUser;
	}


	public String getEndpointPass() {
		return endpointPass;
	}


	public void setEndpointPass(String endpointPass) {
		this.endpointPass = endpointPass;
	}


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
	}


	/**
	 * @return the tipoServicio
	 */
	public String getTipoServicio() {
		return tipoServicio;
	}


	/**
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}

