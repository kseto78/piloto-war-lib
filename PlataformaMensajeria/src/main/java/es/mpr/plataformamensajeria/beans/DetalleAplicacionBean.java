package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.pdf.codec.Base64;
import com.map.j2ee.auditoria.ifaces.Audit;


	
public class DetalleAplicacionBean implements Audit{


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

	protected Long aplicacionId;
	protected String nombre = null;
	protected String descripcion = null;
	protected String usuario = null;
	protected String password = null;
	protected String rePassword = null;
	protected Boolean activo = null;
	protected Date fechacreacion = null;
	protected String creadopor = null;
	protected Date fechamodificacion = null;
	protected String modificadopor = null;
	protected String isActivo = null; 
	protected List<DetalleServicioBean> listaServicios;
	protected List<UsuarioAplicacionBean> listaUsuariosAplicacion;
	protected String passwordUnhashed;
	
	public String getPasswordUnhashed() {
		if(password!=null){
			return new String(Base64.decode(password));
		}
		return "No definido";
	}
	public void setPasswordUnhashed(String passwordUnhased) {
		this.passwordUnhashed = passwordUnhased;
	}
	public List<UsuarioAplicacionBean> getListaUsuariosAplicacion() {
		return listaUsuariosAplicacion;
	}
	public void setListaUsuariosAplicacion(
			List<UsuarioAplicacionBean> listaUsuariosAplicacion) {
		this.listaUsuariosAplicacion = listaUsuariosAplicacion;
	}
	public void addDetalleServicio(DetalleServicioBean servicioBean){
		if(listaServicios==null){
			listaServicios = new ArrayList<DetalleServicioBean>();
		}
		listaServicios.add(servicioBean);
	}
	public List<DetalleServicioBean> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(List<DetalleServicioBean> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public void setActivado(String activado){
		
		if(activado!=null&&activado.equals("true")){
			this.activo = true;
		}else{
			this.activo = false;
		}
	}
	public String getIsActivo() {
		if(activo!=null && activo){
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
	public String getActivado(){
		if(activo!=null && activo){
			return "true";
		}else{
			return "false";
		}
	}
	public Object getId() {
		return this.aplicacionId;
	}
	public void setId(Object id){
		this.aplicacionId =(Long)id;
	}
	
	/**
	 * @return the aplicacionId
	 */
	public Long getAplicacionId() {
		return aplicacionId;
	}
	/**
	 * @param aplicacionId the aplicacionId to set
	 */
	public void setAplicacionId(Long aplicacionId) {
		this.aplicacionId = aplicacionId;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}
	/**
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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


	@Override
	public String obtenerXML() {
		return null;
	}
	
}
