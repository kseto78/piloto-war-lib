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
 * TblHistoricosHist generated by hbm2java
 */
@Entity
@Table(name = "TBL_HISTORICOS_HIST")
public class TblHistoricosHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4478175507683642895L;

	@Id
	@Column(name = "HISTORICOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long historicoid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLANIFICACIONID")
	private TblPlanificaciones tblPlanificaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ESTADOID")
	private TblEstados tblEstados;

	@Column(name = "FECHA", length = 7)
	private Date fecha;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "SERVIDORID", precision = 22, scale = 0)
	private Long servidorid;

	@Column(name = "DESCRIPCION", length = 1000)
	private String descripcion;

	@Column(name = "SUBESTADOID", precision = 22, scale = 0)
	private Long subestadoid;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	@Column(name = "DESTINATARIOSMENSAJES", precision = 22, scale = 0)
	private Long destinatariosmensajes;

	public TblHistoricosHist() {
	}

	/**
	 * @return the historicoid
	 */
	public Long getHistoricoid() {
		return historicoid;
	}

	/**
	 * @param historicoid
	 *            the historicoid to set
	 */
	public void setHistoricoid(Long historicoid) {
		this.historicoid = historicoid;
	}

	/**
	 * @return the tblPlanificaciones
	 */
	public TblPlanificaciones getTblPlanificaciones() {
		return tblPlanificaciones;
	}

	/**
	 * @param tblPlanificaciones
	 *            the tblPlanificaciones to set
	 */
	public void setTblPlanificaciones(TblPlanificaciones tblPlanificaciones) {
		this.tblPlanificaciones = tblPlanificaciones;
	}

	/**
	 * @return the tblEstados
	 */
	public TblEstados getTblEstados() {
		return tblEstados;
	}

	/**
	 * @param tblEstados
	 *            the tblEstados to set
	 */
	public void setTblEstados(TblEstados tblEstados) {
		this.tblEstados = tblEstados;
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
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * @param servidorid
	 *            the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
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

	/**
	 * @return the subestadoid
	 */
	public Long getSubestadoid() {
		return subestadoid;
	}

	/**
	 * @param subestadoid
	 *            the subestadoid to set
	 */
	public void setSubestadoid(Long subestadoid) {
		this.subestadoid = subestadoid;
	}

	/**
	 * @return the fechahistorificacion
	 */
	public Date getFechahistorificacion() {
		return fechahistorificacion;
	}

	/**
	 * @param fechahistorificacion
	 *            the fechahistorificacion to set
	 */
	public void setFechahistorificacion(Date fechahistorificacion) {
		this.fechahistorificacion = fechahistorificacion;
	}

	/**
	 * @return the destinatariosmensajes
	 */
	public Long getDestinatariosmensajes() {
		return destinatariosmensajes;
	}

	/**
	 * @param destinatariosmensajes
	 *            the destinatariosmensajes to set
	 */
	public void setDestinatariosmensajes(Long destinatariosmensajes) {
		this.destinatariosmensajes = destinatariosmensajes;
	}

}
