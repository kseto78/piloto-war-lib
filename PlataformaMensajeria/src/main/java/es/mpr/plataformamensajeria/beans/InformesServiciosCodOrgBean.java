package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class InformesServiciosCodOrgBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class InformesServiciosCodOrgBean implements Audit{

	/**  servicio id. */
	private Integer servicioId;

	/**  anno. */
	private Integer anno;

	/**  mes. */
	private Integer mes;

	/**  cod organismo. */
	private String codOrganismo;

	/**  num total. */
	private Integer numTotal;

	/**
	 * Constructor de informes servicios cod org bean.
	 */
	public InformesServiciosCodOrgBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.codOrganismo=null;
		this.numTotal=null;
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
	 * Obtener cod organismo.
	 *
	 * @return cod organismo
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}

	/**
	 * Modificar cod organismo.
	 *
	 * @param codOrganismo new cod organismo
	 */
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
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
