package es.minhap.plataformamensajeria.sm.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Historico {
	
	private Long historicoid;
	private Date fecha;
	private BigDecimal mensajeid;
	private BigDecimal estadoid;
	private BigDecimal servidorid;
	private BigDecimal planificacionid;
	private String descripcion;
	private BigDecimal subestadoid;
	private Long destinatariosMensajes;
	
	public Historico() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fecha
	 * @param mensajeid
	 * @param subestadoid
	 */
	public Historico(Date fecha, BigDecimal mensajeid,BigDecimal subestadoid) {
		this.fecha = fecha;
		this.mensajeid = mensajeid;
		this.subestadoid = subestadoid;
	}
	
	public Historico(Date fecha, BigDecimal mensajeid,BigDecimal subestadoid, String descripcion) {
		this.fecha = fecha;
		this.mensajeid = mensajeid;
		this.subestadoid = subestadoid;
		this.descripcion = descripcion;
	}


	public Long getHistoricoid() {
		return historicoid;
	}

	public void setHistoricoid(Long historicoid) {
		this.historicoid = historicoid;
	}

	public Date getFecha() {
		return fecha;		
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMensajeid() {
		return mensajeid;
	}

	public void setMensajeid(BigDecimal mensajeid) {
		this.mensajeid = mensajeid;
	}

	public BigDecimal getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(BigDecimal estadoid) {
		this.estadoid = estadoid;
	}

	public BigDecimal getServidorid() {
		return servidorid;
	}

	public void setServidorid(BigDecimal servidorid) {
		this.servidorid = servidorid;
	}

	public BigDecimal getPlanificacionid() {
		return planificacionid;
	}

	public void setPlanificacionid(BigDecimal planificacionid) {
		this.planificacionid = planificacionid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getSubestadoid() {
		return subestadoid;
	}

	public void setSubestadoid(BigDecimal subestadoid) {
		this.subestadoid = subestadoid;
	}

	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
}
