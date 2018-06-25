package es.minhap.plataformamensajeria.iop.beans.entity;

import java.util.Date;

/**
 * Bean que contiene la información necesaria para la insersección en TBL_HISTORICO
 * 
 * @author everis
 *
 */
public class HistoricoBean {

	private Long historicoid;

	private Long planificacionId;

	private Long estadoId;

	private Date fecha;

	private Long mensajeid;

	private Long servidorid;

	private String descripcion;

	private Long subestadoid;

	private Long destinatariosmensajes;
	
	private boolean multidestinatario;

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
	 * @return the planificacionId
	 */
	public Long getPlanificacionId() {
		return planificacionId;
	}

	/**
	 * @param planificacionId
	 *            the planificacionId to set
	 */
	public void setPlanificacionId(Long planificacionId) {
		this.planificacionId = planificacionId;
	}

	/**
	 * @return the estadoId
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId
	 *            the estadoId to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
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

	/**
	 * @return the multidestinatario
	 */
	public boolean isMultidestinatario() {
		return multidestinatario;
	}

	/**
	 * @param multidestinatario the multidestinatario to set
	 */
	public void setMultidestinatario(boolean multidestinatario) {
		this.multidestinatario = multidestinatario;
	}

}
