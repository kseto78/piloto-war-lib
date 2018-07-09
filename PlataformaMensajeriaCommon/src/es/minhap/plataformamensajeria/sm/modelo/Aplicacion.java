package es.minhap.plataformamensajeria.sm.modelo;

import java.io.Serializable;
import java.util.Date;



	
public class Aplicacion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Aplicacion() {
		super();
		this.aplicacionId = null;
		this.nombre = null;
		this.descripcion = null;
		this.usuario = null;
		this.password = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.isActivo = null;
		
		this.responsableFuncionalNombre = null;
		this.responsableFuncionalEmail = null;
		this.responsableTecnicoNombre = null;
		this.responsableTecnicoEmail = null;
		
		
	}

	protected Integer aplicacionId;
	protected String nombre = null;
	protected String descripcion = null;
	protected String usuario = null;
	protected String password = null;
	protected String rePassword = null;


	protected Integer activo = null;
	protected Date fechaCreacion = null;
	protected String creadoPor = null;
	protected Date fechaModificacion = null;
	protected String modificadoPor = null;
	protected String isActivo = null; 
	
	protected String responsableFuncionalNombre = null;
	protected String responsableFuncionalEmail = null;
	protected String responsableTecnicoNombre = null;
	protected String responsableTecnicoEmail = null;
	
	
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	public void setActivado(String activado){
		if(activado!=null&&activado.equals("true")){
			this.activo = new Integer(1);
		}else{
			this.activo = new Integer(0);
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
			this.activo = Integer.valueOf(1);
		}else{
			this.activo = Integer.valueOf(0);
		}
		this.isActivo = isActivo;
	}
	public String getActivado(){
		if(activo!=null&&activo.intValue()==1){
			return "true";
		}else{
			return "false";
		}
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return this.aplicacionId;
	}
	public void setId(Object id){
		this.aplicacionId =(Integer)id;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getResponsableFuncionalNombre() {
		return responsableFuncionalNombre;
	}
	public void setResponsableFuncionalNombre(String responsableFuncionalNombre) {
		this.responsableFuncionalNombre = responsableFuncionalNombre;
	}
	public String getResponsableFuncionalEmail() {
		return responsableFuncionalEmail;
	}
	public void setResponsableFuncionalEmail(String responsableFuncionalEmail) {
		this.responsableFuncionalEmail = responsableFuncionalEmail;
	}
	public String getResponsableTecnicoNombre() {
		return responsableTecnicoNombre;
	}
	public void setResponsableTecnicoNombre(String responsableTecnicoNombre) {
		this.responsableTecnicoNombre = responsableTecnicoNombre;
	}
	public String getResponsableTecnicoEmail() {
		return responsableTecnicoEmail;
	}
	public void setResponsableTecnicoEmail(String responsableTecnicoEmail) {
		this.responsableTecnicoEmail = responsableTecnicoEmail;
	}
	

	
	
}
