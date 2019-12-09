package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * Clase InformesServiciosBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class InformesServiciosBean implements Audit{

	/**  servicio id. */
	private Integer servicioId;

	/**  anno. */
	private Integer anno;

	/**  mes. */
	private Integer mes;

	/**  estado. */
	private String estado;

	/**  cod organismo. */
	private String codOrganismo;

	/**  cod organismo pagador. */
	private String codOrganismoPagador;

	/**  cod sia. */
	private String codSia;

	/**  num total. */
	private Integer numTotal;

	/**
	 * Constructor de informes servicios bean.
	 */
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
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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

	/**
	 * Obtener cod organismo.
	 *
	 * @return the codOrganismo
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}

	/**
	 * Modificar cod organismo.
	 *
	 * @param codOrganismo the codOrganismo to set
	 */
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	/**
	 * Obtener cod organismo pagador.
	 *
	 * @return the codOrganismoPagador
	 */
	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	/**
	 * Modificar cod organismo pagador.
	 *
	 * @param codOrganismoPagador the codOrganismoPagador to set
	 */
	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	/**
	 * Obtener cod sia.
	 *
	 * @return the codSia
	 */
	public String getCodSia() {
		return codSia;
	}

	/**
	 * Modificar cod sia.
	 *
	 * @param codSia the codSia to set
	 */
	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}
	
	
}
