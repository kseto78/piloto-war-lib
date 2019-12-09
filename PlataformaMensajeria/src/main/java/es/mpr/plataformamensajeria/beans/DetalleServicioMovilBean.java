package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;


	
/**
 * Clase DetalleServicioMovilBean.
 */
public class DetalleServicioMovilBean implements Audit{



	protected static final String SPAN_CLASSINACT = "<span class='inactivo'></span>";

	protected static final String TRUE = "true";

	protected static final String FALSE = "false";

	protected static final String SPAN_CLASSACTIV = "<span class='activo'></span>";

	/**  servicio movil id. */
	protected Integer servicioMovilId;

	/**  nombre. */
	protected String nombre = null;

	/**  descripcion. */
	protected String descripcion = null;

	/**  tipo. */
	protected Integer tipo = null;

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

	/**  is tipo. */
	protected String isTipo = null;

	/**  is estado. */
	protected String isEstado = null;

	/**  lista usuarios. */
	protected List<DetalleUsuarioBean> listaUsuarios;

	/**  lista usuarios servicios moviles. */
	protected List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles;



	/**
	 * Constructor de detalle servicio movil bean.
	 */
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

	/**
	 * Obtener servicio movil id.
	 *
	 * @return servicio movil id
	 */
	public Integer getServicioMovilId() {
		return servicioMovilId;
	}
	
	
	
	/**
	 * Modificar servicio movil id.
	 *
	 * @param servicioMovilId new servicio movil id
	 */
	public void setServicioMovilId(Integer servicioMovilId) {
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
	public Integer getTipo() {
		return tipo;
	}


	/**
	 * Modificar tipo.
	 *
	 * @param tipo new tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	/**
	 * Modificar autentificado.
	 *
	 * @param autentificado new autentificado
	 */
	public void setAutentificado(String autentificado){
		if(autentificado != null && TRUE.equals(autentificado)){
			this.tipo = Integer.valueOf(1);
		}else{
			this.tipo = Integer.valueOf(0);
		}
	}
	
	
	/**
	 * Obtener autentificado.
	 *
	 * @return autentificado
	 */
	public String getAutentificado(){
		if(tipo != null && tipo.intValue()==1){
			return TRUE;
		}else{
			return FALSE;
		}
	}	
	
	
	/**
	 * Obtener checks if is tipo.
	 *
	 * @return checks if is tipo
	 */
	public String getIsTipo() {
		if(tipo != null && tipo.intValue()==1){
			return SPAN_CLASSACTIV;
		}else{
			return SPAN_CLASSINACT;
		}
		
	}
	
	
	/**
	 * Modificar checks if is tipo.
	 *
	 * @param isTipo new checks if is tipo
	 */
	public void setIsTipo(String isTipo) {
		if(isTipo!=null && TRUE.equals(isTipo)){
			this.tipo = Integer.valueOf(1);
		}else{
			this.tipo = Integer.valueOf(0);
		}
		this.isTipo   = isTipo;
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
	 * Obtener lista usuarios.
	 *
	 * @return lista usuarios
	 */
	public List<DetalleUsuarioBean> getListaUsuarios() {
		return listaUsuarios;
	}



	/**
	 * Modificar lista usuarios.
	 *
	 * @param listaUsuarios new lista usuarios
	 */
	public void setListaUsuarios(List<DetalleUsuarioBean> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}



	/**
	 * Obtener lista usuarios servicios moviles.
	 *
	 * @return lista usuarios servicios moviles
	 */
	public List<UsuariosServiciosMovilesBean> getListaUsuariosServiciosMoviles() {
		return listaUsuariosServiciosMoviles;
	}



	/**
	 * Modificar lista usuarios servicios moviles.
	 *
	 * @param listaUsuariosServiciosMoviles new lista usuarios servicios moviles
	 */
	public void setListaUsuariosServiciosMoviles(
			List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles) {
		this.listaUsuariosServiciosMoviles = listaUsuariosServiciosMoviles;
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
	 * Agrega detalle usuario.
	 *
	 * @param usuariosBean the usuarios bean
	 */
	public void addDetalleUsuario(DetalleUsuarioBean usuariosBean) {
		if(listaUsuarios==null){
			listaUsuarios = new ArrayList<>();
		}
		listaUsuarios.add(usuariosBean);
	}
}

