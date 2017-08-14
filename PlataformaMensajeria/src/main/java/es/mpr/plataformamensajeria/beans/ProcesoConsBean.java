package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author jgonzvil
 */
public class ProcesoConsBean implements Audit{

	public ProcesoConsBean() {
		this.procesoConsId=null;
		this.codigoEstado=null;
		this.descripcionEstado=null;
		this.fechaCreacion=null;
		this.fechaInicio=null;
		this.fechaFin=null;
	}
	
	private Long procesoConsId;
	private String codigoEstado;
	private String descripcionEstado;
	private Date fechaCreacion;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Long getProcesoConsId() {
		return procesoConsId;
	}
	
	public void setProcesoConsId(Long procesoConsId) {
		this.procesoConsId = procesoConsId;
	}
	public String getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
