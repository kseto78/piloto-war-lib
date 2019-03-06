package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TblAplicaciones generated by hbm2java
 */
@Entity
@Table(name = "TBL_CERTIFICADOS")
public class TblCertificados implements java.io.Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CERTIFICADOS_SEC")
    @SequenceGenerator(name="CERTIFICADOS_SEC", sequenceName="CERTIFICADOID_SEC", allocationSize=1)
	@Column(name = "CERTIFICADOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long certificadoid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APLICACIONID")
	private TblAplicaciones tblAplicaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICIOID")
	private TblServicios tblServicios;
	
	@Column(name = "ISSUER", length = 256)
	private String issuer;
	
	@Column(name = "SERIAL", length = 256)
	private String serial;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechaCreacion;
	
	@Column(name = "FECHAACTUALIZACION", length = 7)
	private Date fechaActualizacion;
	
	@Column(name = "MODIFICADOPOR", length = 150)
	private String modificadoPor;
	
	@Column(name = "CADUCADO")
	private Boolean caducado;
	
	@Column(name = "CREADOPOR", length = 150)
	private String creadoPor;
	
	@Column(name = "FECHACADUCIDAD", length = 7)
	private Date fechaCaducidad;
	

	/**
	 * @return the certificadoid
	 */
	public Long getCertificadoid() {
		return certificadoid;
	}

	/**
	 * @param certificadoid the certificadoid to set
	 */
	public void setCertificadoid(Long certificadoid) {
		this.certificadoid = certificadoid;
	}

	/**
	 * @return the tblAplicaciones
	 */
	public TblAplicaciones getTblAplicaciones() {
		return tblAplicaciones;
	}

	/**
	 * @param tblAplicaciones the tblAplicaciones to set
	 */
	public void setTblAplicaciones(TblAplicaciones tblAplicaciones) {
		this.tblAplicaciones = tblAplicaciones;
	}

	/**
	 * @return the tblServicios
	 */
	public TblServicios getTblServicios() {
		return tblServicios;
	}

	/**
	 * @param tblServicios the tblServicios to set
	 */
	public void setTblServicios(TblServicios tblServicios) {
		this.tblServicios = tblServicios;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @param serial the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the caducado
	 */
	public Boolean getCaducado() {
		return caducado;
	}

	/**
	 * @param caducado the caducado to set
	 */
	public void setCaducado(Boolean caducado) {
		this.caducado = caducado;
	}
	
	/**
	 * @return the fechaCaducidad
	 */
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	/**
	 * @param fechaCaducidad the fechaCaducidad to set
	 */
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	

	/**
	 * @return the creadoPor
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	
}
