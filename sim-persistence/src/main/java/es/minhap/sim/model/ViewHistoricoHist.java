package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ViewHistoricoHist generated by hbm2java
 */
@Entity
@Table(name = "VIEW_HISTORICO_HIST")
public class ViewHistoricoHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2536834987215306373L;
	@Id
	@Column(name = "HISTORICOID", nullable = false, precision = 22, scale = 0)
	private Long historicoid;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "DESTINATARIOSMENSAJES", precision = 22, scale = 0)
	private Long destinatariosmensajes;

	@Column(name = "ESTADOID", precision = 22, scale = 0)
	private Long estadoid;

	@Column(name = "SERVIDORID", precision = 22, scale = 0)
	private Long servidorid;

	@Column(name = "PLANIFICACIONID", precision = 22, scale = 0)
	private Long planificacionid;

	@Column(name = "ESTADO", nullable = false, length = 100)
	private String estado;

	@Column(name = "FECHA", length = 7)
	private Date fecha;

	@Column(name = "SERVIDOR", length = 100)
	private String servidor;

	@Column(name = "CODIGOEXTERNO", length = 100)
	private String codigoexterno;

	@Column(name = "USUARIO", length = 50)
	private String usuario;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "DESCRIPCION", length = 1000)
	private String descripcion;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	public ViewHistoricoHist() {
	}

	/**
	 * @return the historicoid
	 */
	public Long getHistoricoid() {
		return historicoid;
	}

	/**
	 * @param historicoid the historicoid to set
	 */
	public void setHistoricoid(Long historicoid) {
		this.historicoid = historicoid;
	}

	/**
	 * @return the mensajeid
	 */
	public Long getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid the mensajeid to set
	 */
	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the destinatariosmensajes
	 */
	public Long getDestinatariosmensajes() {
		return destinatariosmensajes;
	}

	/**
	 * @param destinatariosmensajes the destinatariosmensajes to set
	 */
	public void setDestinatariosmensajes(Long destinatariosmensajes) {
		this.destinatariosmensajes = destinatariosmensajes;
	}

	/**
	 * @return the estadoid
	 */
	public Long getEstadoid() {
		return estadoid;
	}

	/**
	 * @param estadoid the estadoid to set
	 */
	public void setEstadoid(Long estadoid) {
		this.estadoid = estadoid;
	}

	/**
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * @param servidorid the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
	}

	/**
	 * @return the planificacionid
	 */
	public Long getPlanificacionid() {
		return planificacionid;
	}

	/**
	 * @param planificacionid the planificacionid to set
	 */
	public void setPlanificacionid(Long planificacionid) {
		this.planificacionid = planificacionid;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the servidor
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * @param servidor the servidor to set
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	/**
	 * @return the codigoexterno
	 */
	public String getCodigoexterno() {
		return codigoexterno;
	}

	/**
	 * @param codigoexterno the codigoexterno to set
	 */
	public void setCodigoexterno(String codigoexterno) {
		this.codigoexterno = codigoexterno;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechahistorificacion
	 */
	public Date getFechahistorificacion() {
		return fechahistorificacion;
	}

	/**
	 * @param fechahistorificacion the fechahistorificacion to set
	 */
	public void setFechahistorificacion(Date fechahistorificacion) {
		this.fechahistorificacion = fechahistorificacion;
	}

	
}
