package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Representa los historicos relacionados con envios de emails y sms
 * 
 * <p>
 * 
 * 
 * @author i-nercya
 */
public class HistoricoBean implements Audit, Serializable {

	private static final long serialVersionUID = -6093724517567453005L;

	public HistoricoBean() {
		this.historicoId = null;
		this.fecha = null;
		this.mensajeId = null;
		this.estadoId = null;
		this.servidorId = null;
		this.planificacionId = null;

		this.fechaCreacion = null;
		this.creadoPor = null;
		this.estado = null;
		this.descripcion = null;

		this.subEstadoId = null;
		this.destinatariosMensajes = null;
	}

	private String estado;
	private String descripcion;
	private Long historicoId;
	private Date fecha;
	private Long mensajeId;
	private Long estadoId;
	private Long servidorId;
	private Long planificacionId;

	private Date fechaCreacion;
	private String creadoPor;
	private Integer idEnvioSMS;
	private String uim;
	private Integer idGrupo;
	private String nombreServidor;
	private String nombreServidorSMS;

	private Long subEstadoId;
	private Long destinatariosMensajes;

	public String getNombreServidorSMS() {
		return nombreServidorSMS;
	}

	public void setNombreServidorSMS(String nombreServidorSMS) {
		this.nombreServidorSMS = nombreServidorSMS;
	}

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	public String getFechaFormateada() {
		String fechaFormateada = "";
		if (fecha != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(fecha);
		}
		return fechaFormateada;
	}

	public Integer getIdEnvioSMS() {
		return idEnvioSMS;
	}

	public void setIdEnvioSMS(Integer idEnvioSMS) {
		this.idEnvioSMS = idEnvioSMS;
	}

	public String getUim() {
		return uim;
	}

	public void setUim(String uim) {
		this.uim = uim;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Long getHistoricoId() {
		return historicoId;
	}

	public void setHistoricoId(Long historicoId) {
		this.historicoId = historicoId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public Long getServidorId() {
		return servidorId;
	}

	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	public Long getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Long planificacionId) {
		this.planificacionId = planificacionId;
	}

	public Long getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	@Override
	public String obtenerXML() {
		return null;
	}

	public Long getSubEstadoId() {
		return subEstadoId;
	}

	public void setSubEstadoId(Long subEstadoId) {
		this.subEstadoId = subEstadoId;
	}

	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

}
