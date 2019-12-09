package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.pdf.codec.Base64;
import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * Clase DetalleAplicacionBean.
 *
 * @author ralberoc
 */
public class DetalleAplicacionBean implements Audit{

	protected static final String TRUE = "true";

	/** The aplicacion id. */
	protected Long aplicacionId;

	/** The nombre. */
	protected String nombre = null;

	/** The descripcion. */
	protected String descripcion = null;

	/** The usuario. */
	protected String usuario = null;

	/** The password. */
	protected String password = null;

	/** The re password. */
	protected String rePassword = null;

	/** The activo. */
	protected Boolean activo = null;

	/** The fechacreacion. */
	protected Date fechacreacion = null;

	/** The creadopor. */
	protected String creadopor = null;

	/** The fechamodificacion. */
	protected Date fechamodificacion = null;

	/** The modificadopor. */
	protected String modificadopor = null;

	/** The is activo. */
	protected String isActivo = null;

	/** The lista servicios. */
	protected List<DetalleServicioBean> listaServicios;

	/** The lista usuarios aplicacion. */
	protected List<UsuarioAplicacionBean> listaUsuariosAplicacion;

	/** The password unhashed. */
	protected String passwordUnhashed;

	/**
	 * Constructor por defecto.
	 */
	public DetalleAplicacionBean() {
		super();
		this.aplicacionId = null;
		this.nombre = null;
		this.descripcion = null;
		this.usuario = null;
		this.password = null;
		this.activo = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.isActivo = null;
	}

	/**
	 * Obtiene la password desencriptada.
	 *
	 * @return password unhashed
	 */
	public String getPasswordUnhashed() {
		if(password!=null){
			return new String(Base64.decode(password));
		}
		return "No definido";
	}
	
	/**
	 * Modificar passwordUnhased.
	 *
	 * @param passwordUnhased new password unhashed
	 */
	public void setPasswordUnhashed(String passwordUnhased) {
		this.passwordUnhashed = passwordUnhased;
	}
	
	/**
	 * Obtiene listaUsuariosAplicacion.
	 *
	 * @return lista usuarios aplicacion
	 */
	public List<UsuarioAplicacionBean> getListaUsuariosAplicacion() {
		return listaUsuariosAplicacion;
	}
	
	
	/**
	 * Modificar lista usuarios aplicacion.
	 *
	 * @param listaUsuariosAplicacion new lista usuarios aplicacion
	 */
	public void setListaUsuariosAplicacion(
			List<UsuarioAplicacionBean> listaUsuariosAplicacion) {
		this.listaUsuariosAplicacion = listaUsuariosAplicacion;
	}
	
	/**
	 * Agrega detalle servicio.
	 *
	 * @param servicioBean the servicio bean
	 */
	public void addDetalleServicio(DetalleServicioBean servicioBean){
		if(listaServicios==null){
			listaServicios = new ArrayList<>();
		}
		listaServicios.add(servicioBean);
	}
	
	
	/**
	 * Obtener lista servicios.
	 *
	 * @return lista servicios
	 */
	public List<DetalleServicioBean> getListaServicios() {
		return listaServicios;
	}
	
	/**
	 * Modificar lista servicios.
	 *
	 * @param listaServicios new lista servicios
	 */
	public void setListaServicios(List<DetalleServicioBean> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	
	/**
	 * Modificar activado.
	 *
	 * @param activado new activado
	 */
	public void setActivado(String activado){
		
		if(activado!=null&&TRUE.equals(activado)){
			this.activo = true;
		}else{
			this.activo = false;
		}
	}
	
	
	/**
	 * Obtener checks if is activo.
	 *
	 * @return checks if is activo
	 */
	public String getIsActivo() {
		if(activo!=null && activo){
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
		if(isActivo!=null&&TRUE.equals(isActivo)){
			this.activo = true;
		}else{
			this.activo = false;
		}
		this.isActivo = isActivo;
	}
	
	/**
	 * Obtener activado.
	 *
	 * @return activado
	 */
	public String getActivado(){
		if(activo!=null && activo){
			return TRUE;
		}else{
			return "false";
		}
	}
	
	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return this.aplicacionId;
	}
	
	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id){
		this.aplicacionId =(Long)id;
	}
	
	/**
	 * Obtener aplicacion id.
	 *
	 * @return the aplicacionId
	 */
	public Long getAplicacionId() {
		return aplicacionId;
	}
	
	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId the aplicacionId to set
	 */
	public void setAplicacionId(Long aplicacionId) {
		this.aplicacionId = aplicacionId;
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
	 * Obtener usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Modificar usuario.
	 *
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtener password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Modificar password.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Obtener re password.
	 *
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}
	
	/**
	 * Modificar re password.
	 *
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}
	
}
