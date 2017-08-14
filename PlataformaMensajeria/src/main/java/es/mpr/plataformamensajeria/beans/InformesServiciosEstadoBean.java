package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author jgonzvil
 */
public class InformesServiciosEstadoBean implements Audit{

	public InformesServiciosEstadoBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.estado=null;
		this.numTotal=null;
	}
	
	private Integer servicioId;
	private Integer anno;
	private Integer mes;
	private String estado;
	private Integer numTotal;
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	
}
