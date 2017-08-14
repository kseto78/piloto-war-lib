package es.mpr.plataformamensajeria.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Representa los historicos relacionados con envios de emails y sms
 *  
 *  <p>
 * 
 *  
 *  @author jgonzvil
 */
public class HistoricoHistBean implements Audit{

	public HistoricoHistBean() {
		this.historicoId = null;
		this.fecha = null;
		this.mensajeId=null;
		this.estadoId=null;
		this.servidorId=null;
		this.planificacionId=null;
		this.descripcion=null;
		this.subEstadoId=null;
		this.fechaHistorificacion=null;
		
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.estado = null;
		this.destinatariosMensajes = null;
		
	}
	
	private Long historicoId;
	private Date fecha;
	private Long mensajeId;
	private Long estadoId;
	private Long servidorId;
	private Long planificacionId;
	private String descripcion;
	private Integer subEstadoId;
	private Date fechaHistorificacion;
	
	private Date fechaCreacion;
	private String creadoPor;
	private String estado;
	private Long idEnvioSMS;
	private String uim;
	private Long idGrupo;
	private String nombreServidor;
	private String nombreServidorSMS;
	private Long destinatariosMensajes;
	

	public String getFechaFormateada(){
		String fechaFormateada = "";
		if(fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(fecha);
		}
		return fechaFormateada;
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

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getSubEstadoId() {
		return subEstadoId;
	}


	public void setSubEstadoId(Integer subEstadoId) {
		this.subEstadoId = subEstadoId;
	}


	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}


	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
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


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Long getIdEnvioSMS() {
		return idEnvioSMS;
	}


	public void setIdEnvioSMS(Long idEnvioSMS) {
		this.idEnvioSMS = idEnvioSMS;
	}


	public String getUim() {
		return uim;
	}


	public void setUim(String uim) {
		this.uim = uim;
	}


	public Long getIdGrupo() {
		return idGrupo;
	}


	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}


	public String getNombreServidor() {
		return nombreServidor;
	}


	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}


	public String getNombreServidorSMS() {
		return nombreServidorSMS;
	}


	public void setNombreServidorSMS(String nombreServidorSMS) {
		this.nombreServidorSMS = nombreServidorSMS;
	}


	/**
	 * @return the destinatariosMensajes
	 */
	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}


	/**
	 * @param destinatariosMensajes the destinatariosMensajes to set
	 */
	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

}
