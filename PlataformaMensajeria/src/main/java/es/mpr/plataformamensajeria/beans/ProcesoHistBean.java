package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author jgonzvil
 */
public class ProcesoHistBean implements Audit{

	public ProcesoHistBean() {
		this.procesoHistId=null;
		this.codigoEstado=null;
		this.descripcionEstado=null;
		this.fechaCreacion=null;
		this.fechaInicio=null;
		this.fechaFin=null;
	}
	
	private Long procesoHistId;
	private String codigoEstado;
	private String descripcionEstado;
	private Date fechaCreacion;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Long getProcesoHistId() {
		return procesoHistId;
	}
	
	public void setProcesoHistId(Long procesoHistId) {
		this.procesoHistId = procesoHistId;
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
