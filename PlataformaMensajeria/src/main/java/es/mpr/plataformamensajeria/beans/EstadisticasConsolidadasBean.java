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
	
	private Integer estadisticasConsId;
	private Integer servidorId;
	private String servidorNombre;
	private Integer aplicacionId;
	private Integer planificacionId;
	private String aplicacionNombre;
	private Integer servicioId;
	private String servicioNombre;
	private Integer canalId;
	private String canalNombre;
	private Integer estadoId;
	private String estadoNombre;
	private Integer anno;
	private String mes;
	private Integer numTotal;

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getEstadisticasConsId() {
		return estadisticasConsId;
	}

	public void setEstadisticasConsId(Integer estadisticasConsId) {
		this.estadisticasConsId = estadisticasConsId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getServidorNombre() {
		return servidorNombre;
	}

	public void setServidorNombre(String servidorNombre) {
		this.servidorNombre = servidorNombre;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public String getServicioNombre() {
		return servicioNombre;
	}

	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	public String getCanalNombre() {
		return canalNombre;
	}

	public void setCanalNombre(String canalNombre) {
		this.canalNombre = canalNombre;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
}
