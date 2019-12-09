package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class InformesServiciosEstadoBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class InformesServiciosEstadoBean implements Audit{

	/**  servicio id. */
	private Integer servicioId;

	/**  anno. */
	private Integer anno;

	/**  mes. */
	private Integer mes;

	/**  estado. */
	private String estado;

	/**  num total. */
	private Integer numTotal;

	/**
	 * Constructor de informes servicios estado bean.
	 */
	public InformesServiciosEstadoBean() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.estado=null;
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
	
	
}
