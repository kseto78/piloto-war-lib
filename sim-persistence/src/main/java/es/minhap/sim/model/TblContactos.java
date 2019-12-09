package es.minhap.sim.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TBL_CONTACTOS")
public class TblContactos implements java.io.Serializable {


	private static final long serialVersionUID = 6178022094290025723L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTACTOID_SEC")
    @SequenceGenerator(name="CONTACTOID_SEC", sequenceName="CONTACTOID_SEC", allocationSize=1)
	
	@Column(name = "CONTACTOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long contactoid;

	@Column(name = "ORGANISMO", length = 100)
	private String organismo;
	
	@Column(name = "APLICACIONID",  precision = 22, scale = 0)
	private Long aplicacionid;

	@Column(name = "SERVICIOID",  precision = 22, scale = 0)
	private Long servicioid;
	
	@Column(name = "NOMBRE", length = 200)
	private String nombre;

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
	public TblContactos() {
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
	public Long getAplicacionid() {
		return aplicacionid;
	}

	/**
	 * @param aplicacionid
	 */
	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}


	/**
	 * @return
	 */
	public Long getServicioid() {
		return servicioid;
	}

	/**
	 * @param servicioid
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
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

	

	
	
	
}
