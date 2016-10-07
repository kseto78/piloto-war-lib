package es.mpr.plataformamensajeria.estadisticas.bean;

import com.map.j2ee.auditoria.ifaces.Audit;

public class EstadisticasBean implements Audit {

    private Integer aplicacionId;
    private Integer servidorId;
    private Integer canalId;
    private Integer servicioId;
    private Integer estadoId;
    private Integer vistaId;
    private java.util.Date fechaDesde;
    private java.util.Date fechaHasta;
    private Integer agruparId;
    private String docUsuario;
	private String codSIA;
	private String codOrganismo;
	private String codOrganismoPagador;
	
	
    public EstadisticasBean(){
    	super();
    	reset();
    }
    
	public void reset(){
    	this.aplicacionId = null;
    	this.servidorId = null;
    	this.servicioId = null;
    	this.canalId = null;
    	this.estadoId = null;
    	this.vistaId = null;
    	this.fechaDesde = null;
    	this.fechaHasta = null;
    	this.agruparId = null;
    	this.docUsuario = null;
		this.codSIA = null;
		this.codOrganismo = null;
		this.codOrganismoPagador = null;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}


	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}


	public Integer getServidorId() {
		return servidorId;
	}


	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}


	public Integer getCanalId() {
		return canalId;
	}


	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}


	public Integer getServicioId() {
		return servicioId;
	}


	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}


	public Integer getEstadoId() {
		return estadoId;
	}


	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}


	public Integer getVistaId() {
		return vistaId;
	}


	public void setVistaId(Integer vistaId) {
		this.vistaId = vistaId;
	}


	public java.util.Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(java.util.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public java.util.Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(java.util.Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public Integer getAgruparId() {
		return agruparId;
	}


	public void setAgruparId(Integer agruparId) {
		this.agruparId = agruparId;
	}


	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDocUsuario() {
		return docUsuario;
	}

	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}

	public String getCodSIA() {
		return codSIA;
	}

	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

}
