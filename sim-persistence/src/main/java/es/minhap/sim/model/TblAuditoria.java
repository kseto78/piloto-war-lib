package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TblAuditoria generated by hbm2java
 */
@Entity
@Table(name = "TBL_AUDITORIA")
public class TblAuditoria implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1371424384261570957L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITORIAID_SEC")
    @SequenceGenerator(name="AUDITORIAID_SEC", sequenceName="AUDITORIAID_SEC", allocationSize=1)
	@Column(name = "AUDITORIAID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long auditoriaid;

	@Column(name = "OPERACION", nullable = false, length = 100)
	private String operacion;

	@Column(name = "FECHA", nullable = false, length = 7)
	private Date fecha;

	@Column(name = "LOTEENVIOID", precision = 22, scale = 0)
	private Long loteenvioid;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "SERVICIOID", precision = 22, scale = 0)
	private Long servicioid;

	@Column(name = "ANEXOID", precision = 22, scale = 0)
	private Long anexoid;

	@Column(name = "USUARIO", length = 100)
	private String usuario;

	@Column(name = "PASSWORD", length = 100)
	private String password;

	@Column(name = "CODERROR", nullable = false, precision = 22, scale = 0)
	private Long coderror;

	@Column(name = "DESCRIPCION", length = 250)
	private String descripcion;
	
	public TblAuditoria() {
	}

	/**
	 * @return the auditoriaid
	 */
	public Long getAuditoriaid() {
		return auditoriaid;
	}

	/**
	 * @param auditoriaid
	 *            the auditoriaid to set
	 */
	public void setAuditoriaid(Long auditoriaid) {
		this.auditoriaid = auditoriaid;
	}

	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion
	 *            the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the loteenvioid
	 */
	public Long getLoteenvioid() {
		return loteenvioid;
	}

	/**
	 * @param loteenvioid
	 *            the loteenvioid to set
	 */
	public void setLoteenvioid(Long loteenvioid) {
		this.loteenvioid = loteenvioid;
	}

	/**
	 * @return the mensajeid
	 */
	public Long getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid
	 *            the mensajeid to set
	 */
	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the servicioid
	 */
	public Long getServicioid() {
		return servicioid;
	}

	/**
	 * @param servicioid
	 *            the servicioid to set
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	/**
	 * @return the anexoid
	 */
	public Long getAnexoid() {
		return anexoid;
	}

	/**
	 * @param anexoid
	 *            the anexoid to set
	 */
	public void setAnexoid(Long anexoid) {
		this.anexoid = anexoid;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the coderror
	 */
	public Long getCoderror() {
		return coderror;
	}

	/**
	 * @param coderror
	 *            the coderror to set
	 */
	public void setCoderror(Long coderror) {
		this.coderror = coderror;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
