package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lowagie.text.pdf.codec.Base64;
import com.map.j2ee.auditoria.ifaces.Audit;


	
public class DetalleAplicacionBean implements Audit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleAplicacionBean() {
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
			this.activo = Integer.valueOf(1);
		}else{
			this.activo = Integer.valueOf(0);
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
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
