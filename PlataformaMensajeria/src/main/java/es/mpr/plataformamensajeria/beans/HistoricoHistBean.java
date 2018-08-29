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

	/**
	 * Constructor de historico hist bean.
	 */
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
	
	/**  historico id. */
	private Long historicoId;
	
	/**  fecha. */
	private Date fecha;
	
	/**  mensaje id. */
	private Long mensajeId;
	
	/**  estado id. */
	private Long estadoId;
	
	/**  servidor id. */
	private Long servidorId;
	
	/**  planificacion id. */
	private Long planificacionId;
	
	/**  descripcion. */
	private String descripcion;
	
	/**  sub estado id. */
	private Integer subEstadoId;
	
	/**  fecha historificacion. */
	private Date fechaHistorificacion;
	
	/**  fecha creacion. */
	private Date fechaCreacion;
	
	/**  creado por. */
	private String creadoPor;
	
	/**  estado. */
	private String estado;
	
	/**  id envio SMS. */
	private Long idEnvioSMS;
	
	/**  uim. */
	private String uim;
	
	/**  id grupo. */
	private Long idGrupo;
	
	/**  nombre servidor. */
	private String nombreServidor;
	
	/**  nombre servidor SMS. */
	private String nombreServidorSMS;
	
	/**  destinatarios mensajes. */
	private Long destinatariosMensajes;
	

	/**
	 * Obtener fecha formateada.
	 *
	 * @return fecha formateada
	 */
	public String getFechaFormateada(){
		String fechaFormateada = "";
		if(fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(fecha);
		}
		return fechaFormateada;
	}
	

	/**
	 * Obtener historico id.
	 *
	 * @return historico id
	 */
	public Long getHistoricoId() {
		return historicoId;
	}
	
	/**
	 * Modificar historico id.
	 *
	 * @param historicoId new historico id
	 */
	public void setHistoricoId(Long historicoId) {
		this.historicoId = historicoId;
	}

	/**
	 * Obtener fecha.
	 *
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Modificar fecha.
	 *
	 * @param fecha new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtener estado id.
	 *
	 * @return estado id
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * Modificar estado id.
	 *
	 * @param estadoId new estado id
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Long getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener planificacion id.
	 *
	 * @return planificacion id
	 */
	public Long getPlanificacionId() {
		return planificacionId;
	}

	/**
	 * Modificar planificacion id.
	 *
	 * @param planificacionId new planificacion id
	 */
	public void setPlanificacionId(Long planificacionId) {
		this.planificacionId = planificacionId;
	}

	/**
	 * Obtener mensaje id.
	 *
	 * @return mensaje id
	 */
	public Long getMensajeId() {
		return mensajeId;
	}

	/**
	 * Modificar mensaje id.
	 *
	 * @param mensajeId new mensaje id
	 */
	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Obtener sub estado id.
	 *
	 * @return sub estado id
	 */
	public Integer getSubEstadoId() {
		return subEstadoId;
	}


	/**
	 * Modificar sub estado id.
	 *
	 * @param subEstadoId new sub estado id
	 */
	public void setSubEstadoId(Integer subEstadoId) {
		this.subEstadoId = subEstadoId;
	}


	/**
	 * Obtener fecha historificacion.
	 *
	 * @return fecha historificacion
	 */
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}


	/**
	 * Modificar fecha historificacion.
	 *
	 * @param fechaHistorificacion new fecha historificacion
	 */
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}


	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}


	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}


	/**
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * Obtener id envio SMS.
	 *
	 * @return id envio SMS
	 */
	public Long getIdEnvioSMS() {
		return idEnvioSMS;
	}


	/**
	 * Modificar id envio SMS.
	 *
	 * @param idEnvioSMS new id envio SMS
	 */
	public void setIdEnvioSMS(Long idEnvioSMS) {
		this.idEnvioSMS = idEnvioSMS;
	}


	/**
	 * Obtener uim.
	 *
	 * @return uim
	 */
	public String getUim() {
		return uim;
	}


	/**
	 * Modificar uim.
	 *
	 * @param uim new uim
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}


	/**
	 * Obtener id grupo.
	 *
	 * @return id grupo
	 */
	public Long getIdGrupo() {
		return idGrupo;
	}


	/**
	 * Modificar id grupo.
	 *
	 * @param idGrupo new id grupo
	 */
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}


	/**
	 * Obtener nombre servidor.
	 *
	 * @return nombre servidor
	 */
	public String getNombreServidor() {
		return nombreServidor;
	}


	/**
	 * Modificar nombre servidor.
	 *
	 * @param nombreServidor new nombre servidor
	 */
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}


	/**
	 * Obtener nombre servidor SMS.
	 *
	 * @return nombre servidor SMS
	 */
	public String getNombreServidorSMS() {
		return nombreServidorSMS;
	}


	/**
	 * Modificar nombre servidor SMS.
	 *
	 * @param nombreServidorSMS new nombre servidor SMS
	 */
	public void setNombreServidorSMS(String nombreServidorSMS) {
		this.nombreServidorSMS = nombreServidorSMS;
	}


	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @return the destinatariosMensajes
	 */
	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}


	/**
	 * Modificar destinatarios mensajes.
	 *
	 * @param destinatariosMensajes the destinatariosMensajes to set
	 */
	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

}
