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
 * TblServidoresServicios generated by hbm2java
 */
@Entity
@Table(name = "TBL_SERVIDORES_SERVICIOS")
public class TblServidoresServicios implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2777169274689325304L;

	@Id
	@Column(name = "SERVIDORSERVICIOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long servidorservicioid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVIDORID", nullable = false)
	private TblServidores tblServidores;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICIOID", nullable = false)
	private TblServicios tblServicios;

	@Column(name = "NUMINTENTOS", precision = 10, scale = 0)
	private Integer numintentos;

	@Column(name = "HEADERSMS", length = 50)
	private String headersms;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "PROVEEDORUSUARIOSMS", length = 20)
	private String proveedorusuariosms;

	@Column(name = "PROVEEDORPASSWORDSMS", length = 20)
	private String proveedorpasswordsms;
	
	@Column(name = "PREFIJOSMS", length = 50)
	private String prefijosms;

	public TblServidoresServicios() {
	}

	/**
	 * @return the servidorservicioid
	 */
	public Long getServidorservicioid() {
		return servidorservicioid;
	}

	/**
	 * @param servidorservicioid
	 *            the servidorservicioid to set
	 */
	public void setServidorservicioid(Long servidorservicioid) {
		this.servidorservicioid = servidorservicioid;
	}

	/**
	 * @return the tblServidores
	 */
	public TblServidores getTblServidores() {
		return tblServidores;
	}

	/**
	 * @param tblServidores
	 *            the tblServidores to set
	 */
	public void setTblServidores(TblServidores tblServidores) {
		this.tblServidores = tblServidores;
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
	 * @return the numintentos
	 */
	public Integer getNumintentos() {
		return numintentos;
	}

	/**
	 * @param numintentos
	 *            the numintentos to set
	 */
	public void setNumintentos(Integer numintentos) {
		this.numintentos = numintentos;
	}

	/**
	 * @return the headersms
	 */
	public String getHeadersms() {
		return headersms;
	}

	/**
	 * @param headersms
	 *            the headersms to set
	 */
	public void setHeadersms(String headersms) {
		this.headersms = headersms;
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

	/**
	 * @return the proveedorusuariosms
	 */
	public String getProveedorusuariosms() {
		return proveedorusuariosms;
	}

	/**
	 * @param proveedorusuariosms
	 *            the proveedorusuariosms to set
	 */
	public void setProveedorusuariosms(String proveedorusuariosms) {
		this.proveedorusuariosms = proveedorusuariosms;
	}

	/**
	 * @return the proveedorpasswordsms
	 */
	public String getProveedorpasswordsms() {
		return proveedorpasswordsms;
	}

	/**
	 * @param proveedorpasswordsms
	 *            the proveedorpasswordsms to set
	 */
	public void setProveedorpasswordsms(String proveedorpasswordsms) {
		this.proveedorpasswordsms = proveedorpasswordsms;
	}

	/**
	 * @return the prefijosms
	 */
	public String getPrefijosms() {
		return prefijosms;
	}

	/**
	 * @param prefijosms the prefijosms to set
	 */
	public void setPrefijosms(String prefijosms) {
		this.prefijosms = prefijosms;
	}

}
