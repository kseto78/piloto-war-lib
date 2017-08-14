package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TblServicios generated by hbm2java
 */
@Entity
@Table(name = "TBL_SERVICIOS")
public class TblServicios implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7547535234802815763L;

	@Id
	@Column(name = "SERVICIOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long servicioid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APLICACIONID")
	private TblAplicaciones tblAplicaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CANALID")
	private TblCanales tblCanales;

	@Column(name = "NOMBRE", length = 100)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	private Boolean activo;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "EXTERNALID", precision = 22, scale = 0)
	private Long externalid;

	@Column(name = "NMAXENVIOS", precision = 10, scale = 0)
	private Integer nmaxenvios;

	@Column(name = "HEADERSMS", length = 50)
	private String headersms;

	@Column(name = "ELIMINADO", length = 1)
	private String eliminado;

	@Column(name = "CUENTAENVIO", length = 256)
	private String cuentaenvio;

	@Column(name = "NOMBRECUENTAENVIO", length = 256)
	private String nombrecuentaenvio;

	@Column(name = "HISTORIFICACION", precision = 22, scale = 0)
	private Integer historificacion;

	@Column(name = "MOTIVOHISTORIFICACION", length = 500)
	private String motivohistorificacion;

	@Column(name = "CONSERVACION", precision = 22, scale = 0)
	private Integer conservacion;

	@Column(name = "MOTIVOCONSERVACION", length = 500)
	private String motivoconservacion;

	@Column(name = "PENDIENTEAPROBACION", precision = 1, scale = 0)
	private Boolean pendienteaprobacion;

	@Column(name = "NOMBRELOTEENVIO", length = 100)
	private String nombreloteenvio;

	@Column(name = "BADGE", precision = 22, scale = 0)
	private Long badge;

	@Column(name = "GCMPROJECTKEY", length = 500)
	private String gcmprojectkey;

	@Column(name = "APNSRUTACERTIFICADO", length = 500)
	private String apnsrutacertificado;

	@Column(name = "APNSPASSWORDCERTIFICADO", length = 500)
	private String apnspasswordcertificado;

	@Column(name = "ANDROIDPLATAFORMA", precision = 1, scale = 0)
	private Boolean androidplataforma;

	@Column(name = "IOSPLATAFORMA", precision = 1, scale = 0)
	private Boolean iosplataforma;

	@Column(name = "ENDPOINT", length = 500)
	private String endpoint;

	@Column(name = "INFORMESACTIVO", precision = 1, scale = 0)
	private Boolean informesactivo;

	@Column(name = "AGRUPACIONESTADO", precision = 1, scale = 0)
	private Boolean agrupacionestado;

	@Column(name = "AGRUPACIONCODORG", precision = 1, scale = 0)
	private Boolean agrupacioncodorg;

	@Column(name = "AGRUPACIONCODSIA", precision = 1, scale = 0)
	private Boolean agrupacioncodsia;

	@Column(name = "AGRUPACIONCODORGPAGADOR", precision = 1, scale = 0)
	private Boolean agrupacioncodorgpagador;

	@Column(name = "INFORMESDESTINATARIOS", length = 500)
	private String informesdestinatarios;

	@Column(name = "RESP_FUNCIONAL_NOMBRE", length = 100)
	private String respFuncionalNombre;

	@Column(name = "RESP_FUNCIONAL_EMAIL", length = 100)
	private String respFuncionalEmail;

	@Column(name = "RESP_TECNICO_NOMBRE", length = 100)
	private String respTecnicoNombre;

	@Column(name = "RESP_TECNICO_EMAIL", length = 100)
	private String respTecnicoEmail;

	@Column(name = "PROVEEDORUSUARIOSMS", length = 20)
	private String proveedorusuariosms;

	@Column(name = "PROVEEDORPASSWORDSMS", length = 20)
	private String proveedorpasswordsms;

	@Column(name = "MULTIORGANISMO", precision = 1, scale = 0)
	private Boolean multiorganismo;

	@Column(name = "PREMIUM", precision = 1, scale = 0)
	private Boolean premium;
	
	@Column(name = "NMAXREENVIOS", precision = 22, scale = 0)
	private Integer numeroMaxReenvios;

	@Column(name = "REINTENTOSCONSULTAESTADO", precision = 22, scale = 0)
	private Integer reintentosConsultaEStado;
	
	@Column(name = "CADUCIDAD", precision = 22, scale = 0)
	private Integer caducidad;	


	public TblServicios() {
	}

	/**
	 * @return the servicioid
	 */
	public Long getServicioid() {
		return servicioid;
	}

	/**
	 * @param servicioid
	 *            the servicioid to set
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	/**
	 * @return the tblAplicaciones
	 */
	public TblAplicaciones getTblAplicaciones() {
		return tblAplicaciones;
	}

	/**
	 * @param tblAplicaciones
	 *            the tblAplicaciones to set
	 */
	public void setTblAplicaciones(TblAplicaciones tblAplicaciones) {
		this.tblAplicaciones = tblAplicaciones;
	}

	/**
	 * @return the tblCanales
	 */
	public TblCanales getTblCanales() {
		return tblCanales;
	}

	/**
	 * @param tblCanales
	 *            the tblCanales to set
	 */
	public void setTblCanales(TblCanales tblCanales) {
		this.tblCanales = tblCanales;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 *            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * @param creadopor
	 *            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 *            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 *            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @return the externalid
	 */
	public Long getExternalid() {
		return externalid;
	}

	/**
	 * @param externalid
	 *            the externalid to set
	 */
	public void setExternalid(Long externalid) {
		this.externalid = externalid;
	}

	/**
	 * @return the nmaxenvios
	 */
	public Integer getNmaxenvios() {
		return nmaxenvios;
	}

	/**
	 * @param nmaxenvios
	 *            the nmaxenvios to set
	 */
	public void setNmaxenvios(Integer nmaxenvios) {
		this.nmaxenvios = nmaxenvios;
	}

	/**
	 * @return the headersms
	 */
	public String getHeadersms() {
		return headersms;
	}

	/**
	 * @param headersms
	 *            the headersms to set
	 */
	public void setHeadersms(String headersms) {
		this.headersms = headersms;
	}

	/**
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado
	 *            the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * @return the cuentaenvio
	 */
	public String getCuentaenvio() {
		return cuentaenvio;
	}

	/**
	 * @param cuentaenvio
	 *            the cuentaenvio to set
	 */
	public void setCuentaenvio(String cuentaenvio) {
		this.cuentaenvio = cuentaenvio;
	}

	/**
	 * @return the nombrecuentaenvio
	 */
	public String getNombrecuentaenvio() {
		return nombrecuentaenvio;
	}

	/**
	 * @param nombrecuentaenvio
	 *            the nombrecuentaenvio to set
	 */
	public void setNombrecuentaenvio(String nombrecuentaenvio) {
		this.nombrecuentaenvio = nombrecuentaenvio;
	}

	/**
	 * @return the historificacion
	 */
	public Integer getHistorificacion() {
		return historificacion;
	}

	/**
	 * @param historificacion
	 *            the historificacion to set
	 */
	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	/**
	 * @return the motivohistorificacion
	 */
	public String getMotivohistorificacion() {
		return motivohistorificacion;
	}

	/**
	 * @param motivohistorificacion
	 *            the motivohistorificacion to set
	 */
	public void setMotivohistorificacion(String motivohistorificacion) {
		this.motivohistorificacion = motivohistorificacion;
	}

	/**
	 * @return the conservacion
	 */
	public Integer getConservacion() {
		return conservacion;
	}

	/**
	 * @param conservacion
	 *            the conservacion to set
	 */
	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * @return the motivoconservacion
	 */
	public String getMotivoconservacion() {
		return motivoconservacion;
	}

	/**
	 * @param motivoconservacion
	 *            the motivoconservacion to set
	 */
	public void setMotivoconservacion(String motivoconservacion) {
		this.motivoconservacion = motivoconservacion;
	}

	/**
	 * @return the pendienteaprobacion
	 */
	public Boolean getPendienteaprobacion() {
		return pendienteaprobacion;
	}

	/**
	 * @param pendienteaprobacion
	 *            the pendienteaprobacion to set
	 */
	public void setPendienteaprobacion(Boolean pendienteaprobacion) {
		this.pendienteaprobacion = pendienteaprobacion;
	}

	/**
	 * @return the nombreloteenvio
	 */
	public String getNombreloteenvio() {
		return nombreloteenvio;
	}

	/**
	 * @param nombreloteenvio
	 *            the nombreloteenvio to set
	 */
	public void setNombreloteenvio(String nombreloteenvio) {
		this.nombreloteenvio = nombreloteenvio;
	}

	/**
	 * @return the badge
	 */
	public Long getBadge() {
		return badge;
	}

	/**
	 * @param badge
	 *            the badge to set
	 */
	public void setBadge(Long badge) {
		this.badge = badge;
	}

	/**
	 * @return the gcmprojectkey
	 */
	public String getGcmprojectkey() {
		return gcmprojectkey;
	}

	/**
	 * @param gcmprojectkey
	 *            the gcmprojectkey to set
	 */
	public void setGcmprojectkey(String gcmprojectkey) {
		this.gcmprojectkey = gcmprojectkey;
	}

	/**
	 * @return the apnsrutacertificado
	 */
	public String getApnsrutacertificado() {
		return apnsrutacertificado;
	}

	/**
	 * @param apnsrutacertificado
	 *            the apnsrutacertificado to set
	 */
	public void setApnsrutacertificado(String apnsrutacertificado) {
		this.apnsrutacertificado = apnsrutacertificado;
	}

	/**
	 * @return the apnspasswordcertificado
	 */
	public String getApnspasswordcertificado() {
		return apnspasswordcertificado;
	}

	/**
	 * @param apnspasswordcertificado
	 *            the apnspasswordcertificado to set
	 */
	public void setApnspasswordcertificado(String apnspasswordcertificado) {
		this.apnspasswordcertificado = apnspasswordcertificado;
	}

	/**
	 * @return the androidplataforma
	 */
	public Boolean getAndroidplataforma() {
		return androidplataforma;
	}

	/**
	 * @param androidplataforma
	 *            the androidplataforma to set
	 */
	public void setAndroidplataforma(Boolean androidplataforma) {
		this.androidplataforma = androidplataforma;
	}

	/**
	 * @return the iosplataforma
	 */
	public Boolean getIosplataforma() {
		return iosplataforma;
	}

	/**
	 * @param iosplataforma
	 *            the iosplataforma to set
	 */
	public void setIosplataforma(Boolean iosplataforma) {
		this.iosplataforma = iosplataforma;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the informesactivo
	 */
	public Boolean getInformesactivo() {
		return informesactivo;
	}

	/**
	 * @param informesactivo
	 *            the informesactivo to set
	 */
	public void setInformesactivo(Boolean informesactivo) {
		this.informesactivo = informesactivo;
	}

	/**
	 * @return the agrupacionestado
	 */
	public Boolean getAgrupacionestado() {
		return agrupacionestado;
	}

	/**
	 * @param agrupacionestado
	 *            the agrupacionestado to set
	 */
	public void setAgrupacionestado(Boolean agrupacionestado) {
		this.agrupacionestado = agrupacionestado;
	}

	/**
	 * @return the agrupacioncodorg
	 */
	public Boolean getAgrupacioncodorg() {
		return agrupacioncodorg;
	}

	/**
	 * @param agrupacioncodorg
	 *            the agrupacioncodorg to set
	 */
	public void setAgrupacioncodorg(Boolean agrupacioncodorg) {
		this.agrupacioncodorg = agrupacioncodorg;
	}

	/**
	 * @return the agrupacioncodsia
	 */
	public Boolean getAgrupacioncodsia() {
		return agrupacioncodsia;
	}

	/**
	 * @param agrupacioncodsia
	 *            the agrupacioncodsia to set
	 */
	public void setAgrupacioncodsia(Boolean agrupacioncodsia) {
		this.agrupacioncodsia = agrupacioncodsia;
	}

	/**
	 * @return the agrupacioncodorgpagador
	 */
	public Boolean getAgrupacioncodorgpagador() {
		return agrupacioncodorgpagador;
	}

	/**
	 * @param agrupacioncodorgpagador
	 *            the agrupacioncodorgpagador to set
	 */
	public void setAgrupacioncodorgpagador(Boolean agrupacioncodorgpagador) {
		this.agrupacioncodorgpagador = agrupacioncodorgpagador;
	}

	/**
	 * @return the informesdestinatarios
	 */
	public String getInformesdestinatarios() {
		return informesdestinatarios;
	}

	/**
	 * @param informesdestinatarios
	 *            the informesdestinatarios to set
	 */
	public void setInformesdestinatarios(String informesdestinatarios) {
		this.informesdestinatarios = informesdestinatarios;
	}

	/**
	 * @return the respFuncionalNombre
	 */
	public String getRespFuncionalNombre() {
		return respFuncionalNombre;
	}

	/**
	 * @param respFuncionalNombre
	 *            the respFuncionalNombre to set
	 */
	public void setRespFuncionalNombre(String respFuncionalNombre) {
		this.respFuncionalNombre = respFuncionalNombre;
	}

	/**
	 * @return the respFuncionalEmail
	 */
	public String getRespFuncionalEmail() {
		return respFuncionalEmail;
	}

	/**
	 * @param respFuncionalEmail
	 *            the respFuncionalEmail to set
	 */
	public void setRespFuncionalEmail(String respFuncionalEmail) {
		this.respFuncionalEmail = respFuncionalEmail;
	}

	/**
	 * @return the respTecnicoNombre
	 */
	public String getRespTecnicoNombre() {
		return respTecnicoNombre;
	}

	/**
	 * @param respTecnicoNombre
	 *            the respTecnicoNombre to set
	 */
	public void setRespTecnicoNombre(String respTecnicoNombre) {
		this.respTecnicoNombre = respTecnicoNombre;
	}

	/**
	 * @return the respTecnicoEmail
	 */
	public String getRespTecnicoEmail() {
		return respTecnicoEmail;
	}

	/**
	 * @param respTecnicoEmail
	 *            the respTecnicoEmail to set
	 */
	public void setRespTecnicoEmail(String respTecnicoEmail) {
		this.respTecnicoEmail = respTecnicoEmail;
	}

	/**
	 * @return the proveedorusuariosms
	 */
	public String getProveedorusuariosms() {
		return proveedorusuariosms;
	}

	/**
	 * @param proveedorusuariosms
	 *            the proveedorusuariosms to set
	 */
	public void setProveedorusuariosms(String proveedorusuariosms) {
		this.proveedorusuariosms = proveedorusuariosms;
	}

	/**
	 * @return the proveedorpasswordsms
	 */
	public String getProveedorpasswordsms() {
		return proveedorpasswordsms;
	}

	/**
	 * @param proveedorpasswordsms
	 *            the proveedorpasswordsms to set
	 */
	public void setProveedorpasswordsms(String proveedorpasswordsms) {
		this.proveedorpasswordsms = proveedorpasswordsms;
	}

	/**
	 * @return the multiorganismo
	 */
	public Boolean getMultiorganismo() {
		return multiorganismo;
	}

	/**
	 * @param multiorganismo
	 *            the multiorganismo to set
	 */
	public void setMultiorganismo(Boolean multiorganismo) {
		this.multiorganismo = multiorganismo;
	}

	/**
	 * @return the premium
	 */
	public Boolean getPremium() {
		return premium;
	}

	/**
	 * @param premium
	 *            the premium to set
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	/**
	 * @return the numeroMaxReenvios
	 */
	public Integer getNumeroMaxReenvios() {
		return numeroMaxReenvios;
	}

	/**
	 * @param numeroMaxReenvios the numeroMaxReenvios to set
	 */
	public void setNumeroMaxReenvios(Integer numeroMaxReenvios) {
		this.numeroMaxReenvios = numeroMaxReenvios;
	}

	/**
	 * @return the reintentosConsultaEStado
	 */
	public Integer getReintentosConsultaEStado() {
		return reintentosConsultaEStado;
	}

	/**
	 * @param reintentosConsultaEStado the reintentosConsultaEStado to set
	 */
	public void setReintentosConsultaEStado(Integer reintentosConsultaEStado) {
		this.reintentosConsultaEStado = reintentosConsultaEStado;
	}

	public Integer getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(Integer caducidad) {
		this.caducidad = caducidad;
	}
	
}
