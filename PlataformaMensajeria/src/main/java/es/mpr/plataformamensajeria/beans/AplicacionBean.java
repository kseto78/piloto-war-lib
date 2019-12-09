package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * Clase quie contiene atributos de una aplicacion	
 * @author ralberoc
 *
 */
public class AplicacionBean implements Audit, Serializable{

	protected static final String TRUE = "true";
	private static final long serialVersionUID = 21522231942501054L;
	protected Integer aplicacionId;
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
	protected String respFuncionalNombre = null;
	protected String respFuncionalEmail = null;
	protected String respTecnicoNombre = null;
	protected String respTecnicoEmail = null;

	/**
	 * Constructor por defecto
	 */
	public AplicacionBean() {
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
		
		this.respFuncionalNombre = null;
		this.respFuncionalEmail = null;
		this.respTecnicoNombre = null;
		this.respTecnicoEmail = null;
		
		
	}

	/**
	 * Modificar activado
	 * @param activado
	 */
	public void setActivado(String activado){
		if(activado!=null&&TRUE.equals(activado)){
			this.activo = true;
		}else{
			this.activo = false;
		}
	}
	
	/**
	 * Obtener Activo
	 * @return
	 */
	public String getIsActivo() {
		if(activo!=null && activo){
			return "<span class='activo'></span>";
		}else{
			return "<span class='inactivo'></span>";
		}
		
	}

	/**
	 * Modificar activo
	 * @param isActivo
	 */
	public void setIsActivo(String isActivo) {
		if(isActivo!=null && TRUE.equals(isActivo)){
			this.activo = true;
		}else{
			this.activo = false;
		}
		this.isActivo = isActivo;
	}
	
	/**
	 * Obtener activado
	 * @return
	 */
	public String getActivado(){
		if(activo!=null && activo){
			return TRUE;
		}else{
			return "false";
		}
	}
	
	/**
	 * Obtener id
	 * @return
	 */
	public Object getId() {
		return this.aplicacionId;
	}
	
	/**
	 * Modificar id
	 * @param id
	 */
	public void setId(Object id){
		this.aplicacionId =(Integer)id;
	}
	
	/**
	 * @return the aplicacionId
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	/**
	 * @param aplicacionId the aplicacionId to set
	 */
	public void setAplicacionId(Integer aplicacionId) {
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
	 * @return the creadomor
	 */
	public String getCreadopor() {
		return creadopor;
	}
	/**
	 * @param creadomor the creadomor to set
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
	 * @return the modificadomor
	 */
	public String getModificadopor() {
		return modificadopor;
	}
	/**
	 * @param modificadomor the modificadomor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}
	/**
	 * @return the respFuncionalNombre
	 */
	public String getRespFuncionalNombre() {
		return respFuncionalNombre;
	}
	/**
	 * @param respFuncionalNombre the respFuncionalNombre to set
	 */
	public void setRespFuncionalNombre(String respFuncionalNombre) {
		this.respFuncionalNombre = respFuncionalNombre;
	}
	/**
	 * @return the respFuncionalEmail
	 */
	public String getRespFuncionalEmail() {
		return respFuncionalEmail;
	}
	/**
	 * @param respFuncionalEmail the respFuncionalEmail to set
	 */
	public void setRespFuncionalEmail(String respFuncionalEmail) {
		this.respFuncionalEmail = respFuncionalEmail;
	}
	/**
	 * @return the respTecnicoNombre
	 */
	public String getRespTecnicoNombre() {
		return respTecnicoNombre;
	}
	/**
	 * @param respTecnicoNombre the respTecnicoNombre to set
	 */
	public void setRespTecnicoNombre(String respTecnicoNombre) {
		this.respTecnicoNombre = respTecnicoNombre;
	}
	/**
	 * @return the respTecnicoEmail
	 */
	public String getRespTecnicoEmail() {
		return respTecnicoEmail;
	}
	/**
	 * @param respTecnicoEmail the respTecnicoEmail to set
	 */
	public void setRespTecnicoEmail(String respTecnicoEmail) {
		this.respTecnicoEmail = respTecnicoEmail;
	}
	
	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
