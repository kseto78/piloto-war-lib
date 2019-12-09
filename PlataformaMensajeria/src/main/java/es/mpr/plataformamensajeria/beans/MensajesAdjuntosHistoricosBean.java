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

	/**  mensaje adjunto id. */
	private Integer mensajeAdjuntoId;

	/**  mensaje id. */
	private Integer mensajeId;

	/**  adjunto id. */
	private Integer adjuntoId;

	/**  fecha historificacion. */
	private Date fechaHistorificacion;


	/**
	 * Constructor de mensajes adjuntos historicos bean.
	 */
	public MensajesAdjuntosHistoricosBean() {
		this.mensajeAdjuntoId = null;
		this.mensajeId = null;
		this.adjuntoId=null;
		this.fechaHistorificacion=null;
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
	 * Obtener mensaje adjunto id.
	 *
	 * @return mensaje adjunto id
	 */
	public Integer getMensajeAdjuntoId() {
		return mensajeAdjuntoId;
	}



	/**
	 * Modificar mensaje adjunto id.
	 *
	 * @param mensajeAdjuntoId new mensaje adjunto id
	 */
	public void setMensajeAdjuntoId(Integer mensajeAdjuntoId) {
		this.mensajeAdjuntoId = mensajeAdjuntoId;
	}



	/**
	 * Obtener mensaje id.
	 *
	 * @return mensaje id
	 */
	public Integer getMensajeId() {
		return mensajeId;
	}



	/**
	 * Modificar mensaje id.
	 *
	 * @param mensajeId new mensaje id
	 */
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}



	/**
	 * Obtener adjunto id.
	 *
	 * @return adjunto id
	 */
	public Integer getAdjuntoId() {
		return adjuntoId;
	}



	/**
	 * Modificar adjunto id.
	 *
	 * @param adjuntoId new adjunto id
	 */
	public void setAdjuntoId(Integer adjuntoId) {
		this.adjuntoId = adjuntoId;
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
}
