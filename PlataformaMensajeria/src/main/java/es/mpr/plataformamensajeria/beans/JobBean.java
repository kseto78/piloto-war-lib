/*
 * 
 */
package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * <p>
 * Representa los historicos relacionados con envios de emails y sms
 * 
 * <p>.
 *
 * @author i-nercya
 */
public class JobBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6053798532526486070L;



	/**
	 * Constructor de job bean.
	 */
	public JobBean() {
		this.servicioId = null;
		this.nombreJob = null;
		this.fecha = null;
		this.fechaFin = null;
		
	}

	/**  servicio id. */
	private Long servicioId;
	
	/**  fecha. */
	private Date fecha;
	
	/**  nombre job. */
	private String nombreJob;
	
	/**  fecha fin. */
	private Date fechaFin;
	
	

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}



	/**
	 * Obtener servicio id.
	 *
	 * @return the servicioId
	 */
	public Long getServicioId() {
		return servicioId;
	}



	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId the servicioId to set
	 */
	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}



	/**
	 * Obtener fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}



	/**
	 * Modificar fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * Obtener nombre job.
	 *
	 * @return the nombreJob
	 */
	public String getNombreJob() {
		return nombreJob;
	}



	/**
	 * Modificar nombre job.
	 *
	 * @param nombreJob the nombreJob to set
	 */
	public void setNombreJob(String nombreJob) {
		this.nombreJob = nombreJob;
	}



	/**
	 * Obtener fecha fin.
	 *
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}



	/**
	 * Modificar fecha fin.
	 *
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


}
