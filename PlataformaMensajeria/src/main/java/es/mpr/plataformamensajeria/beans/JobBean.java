package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
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
public class JobBean implements Audit, Serializable {

	private static final long serialVersionUID = 6053798532526486070L;



	public JobBean() {
		this.servicioId = null;
		this.nombreJob = null;
		this.fecha = null;
		this.fechaFin = null;
		
	}

	private Long servicioId;
	private Date fecha;
	private String nombreJob;
	private Date fechaFin;
	
	

	@Override
	public String obtenerXML() {
		return null;
	}



	/**
	 * @return the servicioId
	 */
	public Long getServicioId() {
		return servicioId;
	}



	/**
	 * @param servicioId the servicioId to set
	 */
	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
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
	 * @return the nombreJob
	 */
	public String getNombreJob() {
		return nombreJob;
	}



	/**
	 * @param nombreJob the nombreJob to set
	 */
	public void setNombreJob(String nombreJob) {
		this.nombreJob = nombreJob;
	}



	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}



	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


}
