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
		
	}
	
	private Integer historicoId;
	private Date fecha;
	private Integer mensajeId;
	private Integer estadoId;
	private Integer servidorId;
	private Integer planificacionId;
	private String descripcion;
	private Integer subEstadoId;
	private Date fechaHistorificacion;
	
	private Date fechaCreacion;
	private String creadoPor;
	private String estado;
	private Integer idEnvioSMS;
	private String uim;
	private Integer idGrupo;
	private String nombreServidor;
	private String nombreServidorSMS;

	public String getFechaFormateada(){
		String fechaFormateada = "";
		if(fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(fecha);
		}
		return fechaFormateada;
	}
	

	public Integer getHistoricoId() {
		return historicoId;
	}
	
	public void setHistoricoId(Integer historicoId) {
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

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
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

}
