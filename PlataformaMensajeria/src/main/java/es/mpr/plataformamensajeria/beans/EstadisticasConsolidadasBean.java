package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>Bean para mapear la información de las estadísticas consolidadas
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
public class EstadisticasConsolidadasBean implements Audit{

	/**
	 * Constructor de estadisticas consolidadas bean.
	 */
	public EstadisticasConsolidadasBean() {
		this.estadisticasConsId = null;
		this.servidorId = null;
		this.servidorNombre = null;
		this.aplicacionId = null;
		this.planificacionId = 0; // En el SQL de VIEW_ESTADISTICA siempre setea este atributo a valor 0
		this.aplicacionNombre = null;
		this.servicioId = null;
		this.servicioNombre = null;
		this.canalId = null;
		this.canalNombre = null;
		this.estadoId = null;
		this.estadoNombre = null;
		this.anno = null;
		this.mes = null;
		this.numTotal = null;

	}
	
	/**  estadisticas cons id. */
	private Integer estadisticasConsId;
	
	/**  servidor id. */
	private Integer servidorId;
	
	/**  servidor nombre. */
	private String servidorNombre;
	
	/**  aplicacion id. */
	private Integer aplicacionId;
	
	/**  planificacion id. */
	private Integer planificacionId;
	
	/**  aplicacion nombre. */
	private String aplicacionNombre;
	
	/**  servicio id. */
	private Integer servicioId;
	
	/**  servicio nombre. */
	private String servicioNombre;
	
	/**  canal id. */
	private Integer canalId;
	
	/**  canal nombre. */
	private String canalNombre;
	
	/**  estado id. */
	private Integer estadoId;
	
	/**  estado nombre. */
	private String estadoNombre;
	
	/**  anno. */
	private Integer anno;
	
	/**  mes. */
	private String mes;
	
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
	 * Obtener estadisticas cons id.
	 *
	 * @return estadisticas cons id
	 */
	public Integer getEstadisticasConsId() {
		return estadisticasConsId;
	}

	/**
	 * Modificar estadisticas cons id.
	 *
	 * @param estadisticasConsId new estadisticas cons id
	 */
	public void setEstadisticasConsId(Integer estadisticasConsId) {
		this.estadisticasConsId = estadisticasConsId;
	}

	/**
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Integer getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener servidor nombre.
	 *
	 * @return servidor nombre
	 */
	public String getServidorNombre() {
		return servidorNombre;
	}

	/**
	 * Modificar servidor nombre.
	 *
	 * @param servidorNombre new servidor nombre
	 */
	public void setServidorNombre(String servidorNombre) {
		this.servidorNombre = servidorNombre;
	}

	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}

	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	/**
	 * Obtener planificacion id.
	 *
	 * @return planificacion id
	 */
	public Integer getPlanificacionId() {
		return planificacionId;
	}

	/**
	 * Modificar planificacion id.
	 *
	 * @param planificacionId new planificacion id
	 */
	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

	/**
	 * Obtener aplicacion nombre.
	 *
	 * @return aplicacion nombre
	 */
	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	/**
	 * Modificar aplicacion nombre.
	 *
	 * @param aplicacionNombre new aplicacion nombre
	 */
	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
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
	 * Obtener servicio nombre.
	 *
	 * @return servicio nombre
	 */
	public String getServicioNombre() {
		return servicioNombre;
	}

	/**
	 * Modificar servicio nombre.
	 *
	 * @param servicioNombre new servicio nombre
	 */
	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}

	/**
	 * Obtener canal id.
	 *
	 * @return canal id
	 */
	public Integer getCanalId() {
		return canalId;
	}

	/**
	 * Modificar canal id.
	 *
	 * @param canalId new canal id
	 */
	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	/**
	 * Obtener canal nombre.
	 *
	 * @return canal nombre
	 */
	public String getCanalNombre() {
		return canalNombre;
	}

	/**
	 * Modificar canal nombre.
	 *
	 * @param canalNombre new canal nombre
	 */
	public void setCanalNombre(String canalNombre) {
		this.canalNombre = canalNombre;
	}

	/**
	 * Obtener estado id.
	 *
	 * @return estado id
	 */
	public Integer getEstadoId() {
		return estadoId;
	}

	/**
	 * Modificar estado id.
	 *
	 * @param estadoId new estado id
	 */
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * Obtener estado nombre.
	 *
	 * @return estado nombre
	 */
	public String getEstadoNombre() {
		return estadoNombre;
	}

	/**
	 * Modificar estado nombre.
	 *
	 * @param estadoNombre new estado nombre
	 */
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
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
	public String getMes() {
		return mes;
	}

	/**
	 * Modificar mes.
	 *
	 * @param mes new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
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
