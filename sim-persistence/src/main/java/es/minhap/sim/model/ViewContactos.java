package es.minhap.sim.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "VIEW_CONTACTOS")
public class ViewContactos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2396245686031183972L;

	@Id	
    	
	@Column(name = "CONTACTOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long contactoid;

	@Column(name = "ORGANISMO", length = 100)
	private String organismo;
	
	@Column(name = "APLICACION", length = 100)
	private String aplicacion;
	
	@Column(name = "APLICACIONID", length = 100, precision = 22, scale = 0)
	private Long aplicacionid;
	
	@Column(name = "SERVICIO", length = 100)
	private String servicio;
	
	@Column(name = "SERVICIOID", length = 100, precision = 22, scale = 0)
	private Long servicioid;

	@Column(name = "NOMBRE", length = 200)
	private String nombre;
	
	@Column(name = "APELLIDOS", length = 300)
	private String apellidos;

	@Column(name = "EMAIL", length = 100)
	private String email;
	
	@Column(name = "TELEFONO", length = 50)
	private String telefono;

	@Column(name = "FECHACREACION", nullable = false, length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", nullable = false, length = 150)
	private String creadopor;

	@Column(name = "MODIFICADOPOR", length = 150)
	private String modificadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "ELIMINADO", length = 1)
	private String eliminado;
	/**
	 * 
	 */
	public ViewContactos() {
	}
	
	/**
	 * @return
	 */
	
	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
	
	public Long getContactoid() {
		return contactoid;
	}

	/**
	 * @param contactoid
	 */
	public void setContactoid(Long contactoid) {
		this.contactoid = contactoid;
	}

	/**
	 * @return
	 */
	public String getOrganismo() {
		return organismo;
	}

	/**
	 * @param organismo
	 */
	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return
	 */
	public String getCreadopor() {
		return creadopor;
	}

	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @return
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
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

	

	
	
	
}
