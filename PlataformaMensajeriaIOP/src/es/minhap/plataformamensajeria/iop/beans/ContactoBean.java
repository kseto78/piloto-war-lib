package es.minhap.plataformamensajeria.iop.beans;


import java.util.Date;

public class ContactoBean {

	public ContactoBean() {
		super();
		this.contactoId=null;
		this.organismo = null;
		this.aplicacion = null;
		this.aplicacionid = null;
		this.servicio = null;
		this.servicioid = null;
		this.nombre = null;
		this.apellidos = null;
		this.email = null;
		this.telefono = null;
		this.creadopor = null;
		this.fechacreacion = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.eliminado=null;
	}
	
	/** aplicacion id. */
	protected Long contactoId = null;

	/** organismo */
	protected String organismo;

	/** nombre aplicacion. */
	protected String aplicacion = null;
	
	/** aplicacion id. */
	protected Long aplicacionid = null;
	
	/** nombre Servicio. */
	protected String servicio = null;
	
	/** servicio id. */
	protected Long servicioid = null;
	
	/** nombre. */
	protected String nombre = null;
	
	/** apellidos. */
	protected String apellidos = null;

	/** email. */
	protected String email = null;
	
	/** telefono. */
	protected String telefono = null;
	
	/** creado por. */
	protected String creadopor = null;

	/** fecha creacion. */
	protected Date fechacreacion = null;

	/** fecha modificacion. */
	protected Date fechamodificacion = null;

	/** modificado por. */
	protected String modificadopor = null;
	
	/** eliminado por. */
	protected String eliminado = null;
	
	
	public String getEliminado() {
		return eliminado;
	}


	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}


	public Long getContactoId() {
		return contactoId;
	}


	public void setContactoId(Long contactoId) {
		this.contactoId = contactoId;
	}
	

	public String getOrganismo() {
		return organismo;
	}


	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}


	public String getAplicacion() {
		return aplicacion;
	}


	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}


	public Long getAplicacionid() {
		return aplicacionid;
	}


	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	public Long getServicioid() {
		return servicioid;
	}


	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String string) {
		this.telefono = string;
	}


	public String getCreadopor() {
		return creadopor;
	}


	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}


	public Date getFechacreacion() {
		return fechacreacion;
	}


	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}


	public Date getFechamodificacion() {
		return fechamodificacion;
	}


	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}


	public String getModificadopor() {
		return modificadopor;
	}


	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}


}
