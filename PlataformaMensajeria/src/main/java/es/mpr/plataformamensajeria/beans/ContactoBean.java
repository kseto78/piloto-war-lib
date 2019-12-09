package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

public class ContactoBean implements Audit,Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8289126790241537979L;

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


	public ContactoBean() {
		super();
		this.contactoId=null;
		this.organismo = null;
		this.aplicacion = null;
		this.aplicacionid = null;
		this.servicio = null;
		this.servicioid = null;
		this.nombre = null;
		this.email = null;
		this.telefono = null;
		this.creadopor = null;
		this.fechacreacion = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.eliminado=null;
	}
	
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


	public String getaplicacion() {
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


	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}



}
