package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * Clase InformesServiciosCodSiaBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class InformesServiciosCodSiaBean implements Audit{

	/**  servicio id. */
	private Integer servicioId;

	/**  anno. */
	private Integer anno;

	/**  mes. */
	private Integer mes;

	/**  cod sia. */
	private String codSia;

	/**  num total. */
	private Integer numTotal;

	/**
	 * Constructor de informes servicios cod sia bean.
	 */
	public InformesServiciosCodSiaBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.codSia=null;
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
	 * Obtener cod sia.
	 *
	 * @return cod sia
	 */
	public String getCodSia() {
		return codSia;
	}

	/**
	 * Modificar cod sia.
	 *
	 * @param codSia new cod sia
	 */
	public void setCodSia(String codSia) {
		this.codSia = codSia;
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
