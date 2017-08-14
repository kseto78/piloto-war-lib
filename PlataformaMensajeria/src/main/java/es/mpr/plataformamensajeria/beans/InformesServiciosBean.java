package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author jgonzvil
 */
public class InformesServiciosBean implements Audit{

	public InformesServiciosBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.estado=null;
		this.numTotal=null;
		this.codOrganismo = null;
		this.codOrganismoPagador = null;
		this.codSia = null;
	}
	
	private Integer servicioId;
	private Integer anno;
	private Integer mes;
	private String estado;
	private String codOrganismo;
	private String codOrganismoPagador;
	private String codSia;
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

	/**
	 * @return the codOrganismo
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}

	/**
	 * @param codOrganismo the codOrganismo to set
	 */
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	/**
	 * @return the codOrganismoPagador
	 */
	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	/**
	 * @param codOrganismoPagador the codOrganismoPagador to set
	 */
	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	/**
	 * @return the codSia
	 */
	public String getCodSia() {
		return codSia;
	}

	/**
	 * @param codSia the codSia to set
	 */
	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}
	
	
}
