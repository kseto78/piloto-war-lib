package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class InformesServiciosCodOrgPagadorBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class InformesServiciosCodOrgPagadorBean implements Audit{

	/**
	 * Constructor de informes servicios cod org pagador bean.
	 */
	public InformesServiciosCodOrgPagadorBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.codOrganismoPagador=null;
		this.numTotal=null;
	}
	
	/**  servicio id. */
	private Integer servicioId;
	
	/**  anno. */
	private Integer anno;
	
	/**  mes. */
	private Integer mes;
	
	/**  cod organismo pagador. */
	private String codOrganismoPagador;
	
	/**  num total. */
	private Integer numTotal;
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	/**
	 * Obtener anno.
	 *
	 * @return anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * Modificar anno.
	 *
	 * @param anno new anno
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * Obtener mes.
	 *
	 * @return mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Modificar mes.
	 *
	 * @param mes new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Obtener cod organismo pagador.
	 *
	 * @return cod organismo pagador
	 */
	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	/**
	 * Modificar cod organismo pagador.
	 *
	 * @param codOrganismoPagador new cod organismo pagador
	 */
	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	/**
	 * Obtener num total.
	 *
	 * @return num total
	 */
	public Integer getNumTotal() {
		return numTotal;
	}

	/**
	 * Modificar num total.
	 *
	 * @param numTotal new num total
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	
}
