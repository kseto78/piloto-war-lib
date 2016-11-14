package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TblOrganismosServicio generated by hbm2java
 */
@Entity
@Table(name = "TBL_ORGANISMOS_SERVICIO")
public class TblOrganismosServicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983198033790905650L;

	@Id
	@Column(name = "SERVICIOORGANISMOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long servicioorganismoid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICIOID", nullable = false)
	private TblServicios tblServicios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANISMOID", nullable = false)
	private TblOrganismos tblOrganismos;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	public TblOrganismosServicio() {
	}

	/**
	 * @return the servicioorganismoid
	 */
	public Long getServicioorganismoid() {
		return servicioorganismoid;
	}

	/**
	 * @param servicioorganismoid
	 *            the servicioorganismoid to set
	 */
	public void setServicioorganismoid(Long servicioorganismoid) {
		this.servicioorganismoid = servicioorganismoid;
	}

	/**
	 * @return the tblServicios
	 */
	public TblServicios getTblServicios() {
		return tblServicios;
	}

	/**
	 * @param tblServicios
	 *            the tblServicios to set
	 */
	public void setTblServicios(TblServicios tblServicios) {
		this.tblServicios = tblServicios;
	}

	/**
	 * @return the tblOrganismos
	 */
	public TblOrganismos getTblOrganismos() {
		return tblOrganismos;
	}

	/**
	 * @param tblOrganismos
	 *            the tblOrganismos to set
	 */
	public void setTblOrganismos(TblOrganismos tblOrganismos) {
		this.tblOrganismos = tblOrganismos;
	}

	/**
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * @param creadopor
	 *            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 *            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 *            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 *            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

}
