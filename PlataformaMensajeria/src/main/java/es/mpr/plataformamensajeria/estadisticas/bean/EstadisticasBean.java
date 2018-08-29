package es.mpr.plataformamensajeria.estadisticas.bean;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * Clase EstadisticasBean.
 */
public class EstadisticasBean implements Audit {

    /**  aplicacion id. */
    private Integer aplicacionId;
    
    /**  servidor id. */
    private Integer servidorId;
    
    /**  canal id. */
    private Integer canalId;
    
    /**  servicio id. */
    private Integer servicioId;
    
    /**  estado id. */
    private Integer estadoId;
    
    /**  vista id. */
    private Integer vistaId;
    
    /**  fecha desde. */
    private java.util.Date fechaDesde;
    
    /**  fecha hasta. */
    private java.util.Date fechaHasta;
    
    /**  agrupar id. */
    private Integer agruparId;
    
    /**  doc usuario. */
    private String docUsuario;
	
	/**  cod SIA. */
	private String codSIA;
	
	/**  cod organismo. */
	private String codOrganismo;
	
	/**  cod organismo pagador. */
	private String codOrganismoPagador;
	
	
    /**
     * Constructor de estadisticas bean.
     */
    public EstadisticasBean(){
    	super();
    	reset();
    }
    
	/**
	 * Reset.
	 */
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
	 * Obtener vista id.
	 *
	 * @return vista id
	 */
	public Integer getVistaId() {
		return vistaId;
	}


	/**
	 * Modificar vista id.
	 *
	 * @param vistaId new vista id
	 */
	public void setVistaId(Integer vistaId) {
		this.vistaId = vistaId;
	}


	/**
	 * Obtener fecha desde.
	 *
	 * @return fecha desde
	 */
	public java.util.Date getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * Modificar fecha desde.
	 *
	 * @param fechaDesde new fecha desde
	 */
	public void setFechaDesde(java.util.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * Obtener fecha hasta.
	 *
	 * @return fecha hasta
	 */
	public java.util.Date getFechaHasta() {
		return fechaHasta;
	}


	/**
	 * Modificar fecha hasta.
	 *
	 * @param fechaHasta new fecha hasta
	 */
	public void setFechaHasta(java.util.Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	/**
	 * Obtener agrupar id.
	 *
	 * @return agrupar id
	 */
	public Integer getAgruparId() {
		return agruparId;
	}


	/**
	 * Modificar agrupar id.
	 *
	 * @param agruparId new agrupar id
	 */
	public void setAgruparId(Integer agruparId) {
		this.agruparId = agruparId;
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
	 * Obtener doc usuario.
	 *
	 * @return doc usuario
	 */
	public String getDocUsuario() {
		return docUsuario;
	}

	/**
	 * Modificar doc usuario.
	 *
	 * @param docUsuario new doc usuario
	 */
	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}

	/**
	 * Obtener cod SIA.
	 *
	 * @return cod SIA
	 */
	public String getCodSIA() {
		return codSIA;
	}

	/**
	 * Modificar cod SIA.
	 *
	 * @param codSIA new cod SIA
	 */
	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
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

}
