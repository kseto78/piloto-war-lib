package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Bean para mapear la información de los mensajes adjuntos historico
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
public class MensajesAdjuntosHistoricosBean implements Audit{

	public MensajesAdjuntosHistoricosBean() {
		this.mensajeAdjuntoId = null;
		this.mensajeId = null;
		this.adjuntoId=null;
		this.fechaHistorificacion=null;
	}
	
	private Integer mensajeAdjuntoId;
	private Integer mensajeId;
	private Integer adjuntoId;
	private Date fechaHistorificacion;
	

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMensajeAdjuntoId() {
		return mensajeAdjuntoId;
	}



	public void setMensajeAdjuntoId(Integer mensajeAdjuntoId) {
		this.mensajeAdjuntoId = mensajeAdjuntoId;
	}



	public Integer getMensajeId() {
		return mensajeId;
	}



	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}



	public Integer getAdjuntoId() {
		return adjuntoId;
	}



	public void setAdjuntoId(Integer adjuntoId) {
		this.adjuntoId = adjuntoId;
	}

	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}

	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}
}
