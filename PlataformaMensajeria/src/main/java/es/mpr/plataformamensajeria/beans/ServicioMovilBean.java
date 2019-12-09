package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;



/**
 * The Class ServicioMovilBean.
 */
public class ServicioMovilBean implements Audit{

	
	protected static final String SPAN_CLASSINACT = "<span class='inactivo'></span>";

	protected static final String TRUE = "true";

	protected static final String FALSE = "false";

	protected static final String SPAN_CLASSACTIV = "<span class='activo'></span>";

	/**  servicio movil id. */
	protected Long servicioMovilId;
	
	/**  nombre. */
	protected String nombre = null;
	
	/**  descripcion. */
	protected String descripcion = null;
	
	/**  tipo. */
	protected Long tipo = null;
	
	/**  estado. */
	protected Integer estado = null;
	
	/**  url servicio. */
	protected String urlServicio = null;
	
	/**  nombre contacto. */
	protected String nombreContacto = null;
	
	/**  telefono contacto. */
	protected String telefonoContacto = null;
	
	/**  fecha creacion. */
	protected Date fechaCreacion = null;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion = null;
	
	/**  creado por. */
	protected String creadoPor = null;
	
	/**  modificado por. */
	protected String modificadoPor = null;
	
	/**  is estado. */
	protected String isEstado = null;
	
	/**  ind suscripcion. */
	protected Integer indSuscripcion = null;
	
	/**  url aviso suscripcion. */
	protected String urlAvisoSuscripcion = null;
	
	/**  is ind suscripcion. */
	protected String isIndSuscripcion = null;
	
	/**  endpoint user. */
	protected String endpointUser = null;
	
	/**  endpoint pass. */
	protected String endpointPass = null;
	
	/**  icono. */
	protected String icono = null;
	
	/**  tipo servicio. */
	protected String tipoServicio = null;
	
	/**
	 * Constructor de servicio movil bean.
	 */
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
		this.indSuscripcion = (Integer) 0;
		this.urlAvisoSuscripcion = null;
		this.isIndSuscripcion = null;
		this.endpointUser = null;
		this.endpointPass = null;
		this.icono = null;
		this.tipoServicio = null;
	}

	
	/**
	 * Obtener servicio movil id.
	 *
	 * @return servicio movil id
	 */
	public Long getServicioMovilId() {
		return servicioMovilId;
	}
	
	
	
	/**
	 * Modificar servicio movil id.
	 *
	 * @param servicioMovilId new servicio movil id
	 */
	public void setServicioMovilId(Long servicioMovilId) {
		this.servicioMovilId = servicioMovilId;
	}
	
	
	
	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	
	/**
	 * Modificar nombre.
	 *
	 * @param nombre new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	/**
	 * Obtener descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
	
	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
		
	/**
	 * Obtener url servicio.
	 *
	 * @return url servicio
	 */
	public String getUrlServicio() {
		return urlServicio;
	}
	
	
	
	/**
	 * Modificar url servicio.
	 *
	 * @param urlServicio new url servicio
	 */
	public void setUrlServicio(String urlServicio) {
		this.urlServicio = urlServicio;
	}
	
	
	
	/**
	 * Obtener nombre contacto.
	 *
	 * @return nombre contacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}
	
	
	
	/**
	 * Modificar nombre contacto.
	 *
	 * @param nombreContacto new nombre contacto
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	
	
	
	/**
	 * Obtener telefono contacto.
	 *
	 * @return telefono contacto
	 */
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	
	
	
	/**
	 * Modificar telefono contacto.
	 *
	 * @param telefonoContacto new telefono contacto
	 */
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	
	
	
	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	
	
	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	
	
	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}
	
	
	
	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	
	
	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}
	
	
	
	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	

	/**
	 * Obtener tipo.
	 *
	 * @return tipo
	 */
	public Long getTipo() {
		return tipo;
	}


	/**
	 * Modificar tipo.
	 *
	 * @param tipo new tipo
	 */
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	
		
	/**
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public Integer getEstado() {
		return estado;
	}


	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	/**
	 * Modificar activado.
	 *
	 * @param activado new activado
	 */
	public void setActivado(String activado){
		if(activado!=null&&TRUE.equals(activado)){
			this.estado = Integer.valueOf(1);
		}else{
			this.estado = Integer.valueOf(0);
		}
	}
	
	
	/**
	 * Obtener activado.
	 *
	 * @return activado
	 */
	public String getActivado(){
		if(estado!=null&&estado.intValue()==1){
			return TRUE;
		}else{
			return FALSE;
		}
	}	
	
	
	/**
	 * Obtener checks if is estado.
	 *
	 * @return checks if is estado
	 */
	public String getIsEstado() {
		if(estado!=null&&estado.intValue()==1){
			return SPAN_CLASSACTIV;
		}else{
			return SPAN_CLASSINACT;
		}
		
	}
	
	
	/**
	 * Modificar checks if is estado.
	 *
	 * @param isEstado new checks if is estado
	 */
	public void setIsEstado(String isEstado) {
		if(isEstado!=null&&TRUE.equals(isEstado)){
			this.estado = Integer.valueOf(1);
		}else{
			this.estado = Integer.valueOf(0);
		}
		this.isEstado   = isEstado;
	}

	/**
	 * Modificar suscrito.
	 *
	 * @param suscrito new suscrito
	 */
	public void setSuscrito(String suscrito){
		if(suscrito != null && TRUE.equals(suscrito)){
			this.indSuscripcion = Integer.valueOf(1);
		}else{
			this.indSuscripcion = Integer.valueOf(0);
		}
	}
	
	
	/**
	 * Obtener suscrito.
	 *
	 * @return suscrito
	 */
	public String getSuscrito(){
		if(indSuscripcion != null && indSuscripcion.intValue()==1){
			return TRUE;
		}else{
			return FALSE;
		}
	}	
	
	
	/**
	 * Obtener checks if is ind suscripcion.
	 *
	 * @return checks if is ind suscripcion
	 */
	public String getIsIndSuscripcion() {
		if(indSuscripcion != null && indSuscripcion.intValue()==1){
			return SPAN_CLASSACTIV;
		}else{
			return SPAN_CLASSINACT;
		}
		
	}
	
	
	/**
	 * Modificar checks if is ind suscripcion.
	 *
	 * @param isIndSuscripcion new checks if is ind suscripcion
	 */
	public void setIsIndSuscripcion(String isIndSuscripcion) {
		if(isIndSuscripcion!=null && TRUE.equals(isIndSuscripcion)){
			this.indSuscripcion = Integer.valueOf(1);
		}else{
			this.indSuscripcion = Integer.valueOf(0);
		}
		this.isIndSuscripcion   = isIndSuscripcion;
	}

	/**
	 * Obtener ind suscripcion.
	 *
	 * @return ind suscripcion
	 */
	public Integer getIndSuscripcion() {
		return indSuscripcion;
	}


	/**
	 * Modificar ind suscripcion.
	 *
	 * @param indSuscripcion new ind suscripcion
	 */
	public void setIndSuscripcion(Integer indSuscripcion) {
		this.indSuscripcion = indSuscripcion;
	}


	/**
	 * Obtener url aviso suscripcion.
	 *
	 * @return url aviso suscripcion
	 */
	public String getUrlAvisoSuscripcion() {
		return urlAvisoSuscripcion;
	}


	/**
	 * Modificar url aviso suscripcion.
	 *
	 * @param urlAvisoSuscripcion new url aviso suscripcion
	 */
	public void setUrlAvisoSuscripcion(String urlAvisoSuscripcion) {
		this.urlAvisoSuscripcion = urlAvisoSuscripcion;
	}


	/**
	 * Obtener endpoint user.
	 *
	 * @return endpoint user
	 */
	public String getEndpointUser() {
		return endpointUser;
	}


	/**
	 * Modificar endpoint user.
	 *
	 * @param endpointUser new endpoint user
	 */
	public void setEndpointUser(String endpointUser) {
		this.endpointUser = endpointUser;
	}


	/**
	 * Obtener endpoint pass.
	 *
	 * @return endpoint pass
	 */
	public String getEndpointPass() {
		return endpointPass;
	}


	/**
	 * Modificar endpoint pass.
	 *
	 * @param endpointPass new endpoint pass
	 */
	public void setEndpointPass(String endpointPass) {
		this.endpointPass = endpointPass;
	}


	/**
	 * Obtener icono.
	 *
	 * @return icono
	 */
	public String getIcono() {
		return icono;
	}


	/**
	 * Modificar icono.
	 *
	 * @param icono new icono
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}


	/**
	 * Obtener tipo servicio.
	 *
	 * @return the tipoServicio
	 */
	public String getTipoServicio() {
		return tipoServicio;
	}


	/**
	 * Modificar tipo servicio.
	 *
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}

