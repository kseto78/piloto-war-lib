package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;


	
public class DetalleServicioMovilBean implements Audit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleServicioMovilBean() {
		super();
		this.servicioMovilId = null;
		this.nombre = null;
		this.descripcion = null;
		this.tipo = 0;
		this.estado = null;
		this.urlServicio = null;
		this.nombreContacto = null;
		this.telefonoContacto = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.isTipo = null;
		this.isEstado = null;
	}

	protected Integer servicioMovilId;
	protected String nombre = null;
	protected String descripcion = null;
	protected Integer tipo = null;
	protected Integer estado = null;
	protected String urlServicio = null;
	protected String nombreContacto = null;
	protected String telefonoContacto = null;
	protected Date fechaCreacion = null;
	protected Date fechaModificacion = null;
	protected String creadoPor = null;
	protected String modificadoPor = null;
	protected String isTipo = null;
	protected String isEstado = null;
	protected List<DetalleUsuarioBean> listaUsuarios;
	protected List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles;
	
	
	
	public Integer getServicioMovilId() {
		return servicioMovilId;
	}
	
	
	
	public void setServicioMovilId(Integer servicioMovilId) {
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
	

	public Integer getTipo() {
		return tipo;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	public void setAutentificado(String autentificado){
		if(autentificado != null && autentificado.equals("true")){
			this.tipo = new Integer(1);
		}else{
			this.tipo = new Integer(0);
		}
	}
	
	
	public String getAutentificado(){
		if(tipo != null && tipo.intValue()==1){
			return "true";
		}else{
			return "false";
		}
	}	
	
	
	public String getIsTipo() {
		if(tipo != null && tipo.intValue()==1){
			return "<span class='activo'></span>";
		}else{
			return "<span class='inactivo'></span>";
		}
		
	}
	
	
	public void setIsTipo(String isTipo) {
		if(isTipo!=null && isTipo.equals("true")){
			this.tipo = new Integer(1);
		}else{
			this.tipo = new Integer(0);
		}
		this.isTipo   = isTipo;
	}

	
	
	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	public void setActivado(String activado){
		if(activado!=null&&activado.equals("true")){
			this.estado = new Integer(1);
		}else{
			this.estado = new Integer(0);
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
			this.estado = new Integer(1);
		}else{
			this.estado = new Integer(0);
		}
		this.isEstado   = isEstado;
	}



	public List<DetalleUsuarioBean> getListaUsuarios() {
		return listaUsuarios;
	}



	public void setListaUsuarios(List<DetalleUsuarioBean> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}



	public List<UsuariosServiciosMovilesBean> getListaUsuariosServiciosMoviles() {
		return listaUsuariosServiciosMoviles;
	}



	public void setListaUsuariosServiciosMoviles(
			List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles) {
		this.listaUsuariosServiciosMoviles = listaUsuariosServiciosMoviles;
	}



	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}



	public void addDetalleUsuario(DetalleUsuarioBean usuariosBean) {
		if(listaUsuarios==null){
			listaUsuarios = new ArrayList<DetalleUsuarioBean>();
		}
		listaUsuarios.add(usuariosBean);
	}
}

