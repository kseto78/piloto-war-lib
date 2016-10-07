package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 * <p>
 * Clase que representa un servicio para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
@Entity
@Table(name = "TBL_SERVICIOS")
@NamedQueries({ @NamedQuery(name = "selectServicioJPAByIdAplicacion", query = "SELECT e FROM ServicioJPA e WHERE to_char(e.aplicacionId) = ? and (e.eliminado is null or e.eliminado = 'N') "),
		@NamedQuery(name = "selectServicioHistJPA", query = "SELECT e FROM ServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')" + " and e.activo = '1' and e.historificacion is not null"),
		@NamedQuery(name = "selectServicioConsJPA", query = "SELECT e FROM ServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')" + " and e.activo = '1' and e.conservacion is not null"),
		@NamedQuery(name = "selectServicioInformesJPA", query = "SELECT e FROM ServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')" + " and e.activo = '1' and e.informesActivo = '1'"),
		@NamedQuery(name = "selectServicioMultiOrganismoJPA", query = "SELECT e FROM ServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')" + " and e.activo = '1' and e.multiorganismo = '1'"),
		@NamedQuery(name="actualizarCheckMultiorganismo", query="UPDATE ServicioJPA set multiorganismo = :activo where servicioId = :idServicio"),})
public class ServicioJPA extends AbstractBaseJPAEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public ServicioJPA() {
		super();
		this.servicioId = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.canalId = null;
		this.aplicacionId = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.externalId = null;
		this.nmaxenvios = null;
		this.eliminado = null;
		this.fromMail = null;
		this.fromMailName = null;
		this.historificacion = null;
		this.motivoHistorificacion = null;
		this.conservacion = null;
		this.motivoConservacion = null;
		this.pendienteAprobacion = null;
		this.nombreLoteEnvio = null;
		this.badge = null;
		this.gcmProjectKey = null;
		this.apnsRutaCertificado = null;
		this.apnsPasswordCertificado = null;
		this.androidPlataforma = null;
		this.iosPlataforma = null;
		this.endPoint = null;
		this.informesActivo = null;
		this.agrupacionEstado = null;
		this.agrupacionCodOrg = null;
		this.agrupacionCodSia = null;
		this.agrupacionCodOrgPagador = null;
		this.informesDestinatarios = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPassSMS = null;
		this.multiorganismo = null;
		this.premium = null;
	}

	@Id
	@SequenceGenerator(name = "servicio", sequenceName = "SERVICIOID_SEC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio")
	@Column(name = "SERVICIOID")
	protected Integer servicioId;
	@Column(name = "NOMBRE")
	protected String nombre = null;
	@Column(name = "DESCRIPCION")
	protected String descripcion = null;
	@Column(name = "ACTIVO")
	protected Integer activo = null;
	@Column(name = "CANALID")
	protected Integer canalId = null;
	@Column(name = "APLICACIONID")
	protected Integer aplicacionId = null;
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	@Column(name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	@Column(name = "EXTERNALID")
	protected String externalId = null;
	@Column(name = "NMAXENVIOS")
	protected Integer nmaxenvios = null;
	
	@Column(name = "ELIMINADO")
	protected String eliminado = null;
	@Column(name = "CUENTAENVIO")
	protected String fromMail = null;
	@Column(name = "NOMBRECUENTAENVIO")
	protected String fromMailName = null;
	@Column(name = "HISTORIFICACION")
	protected Integer historificacion = null;
	@Column(name = "MOTIVOHISTORIFICACION")
	protected String motivoHistorificacion = null;
	@Column(name = "CONSERVACION")
	protected Integer conservacion = null;
	@Column(name = "MOTIVOCONSERVACION")
	protected String motivoConservacion = null;
	@Column(name = "PENDIENTEAPROBACION")
	protected Integer pendienteAprobacion = null;
	@Column(name = "NOMBRELOTEENVIO")
	protected String nombreLoteEnvio = null;
	@Column(name = "BADGE")
	protected Integer badge = null;
	@Column(name = "GCMPROJECTKEY")
	protected String gcmProjectKey = null;
	@Column(name = "APNSRUTACERTIFICADO")
	protected String apnsRutaCertificado = null;
	@Column(name = "APNSPASSWORDCERTIFICADO")
	protected String apnsPasswordCertificado = null;
	@Column(name = "ANDROIDPLATAFORMA")
	protected Integer androidPlataforma = null;
	@Column(name = "IOSPLATAFORMA")
	protected Integer iosPlataforma = null;
	@Column(name = "ENDPOINT")
	protected String endPoint = null;
	@Column(name = "INFORMESACTIVO")
	protected Integer informesActivo = null;
	@Column(name = "AGRUPACIONESTADO")
	protected Integer agrupacionEstado = null;
	@Column(name = "AGRUPACIONCODORG")
	protected Integer agrupacionCodOrg = null;
	@Column(name = "AGRUPACIONCODSIA")
	protected Integer agrupacionCodSia = null;
	@Column(name = "AGRUPACIONCODORGPAGADOR")
	protected Integer agrupacionCodOrgPagador = null;
	@Column(name = "INFORMESDESTINATARIOS")
	protected String informesDestinatarios = null;

	@Column(name = "RESP_FUNCIONAL_NOMBRE")
	protected String responsableFuncionalNombre = null;
	@Column(name = "RESP_FUNCIONAL_EMAIL")
	protected String responsableFuncionalEmail = null;
	@Column(name = "RESP_Tecnico_NOMBRE")
	protected String responsableTecnicoNombre = null;
	@Column(name = "RESP_Tecnico_EMAIL")
	protected String responsableTecnicoEmail = null;

	@Column(name = "PROVEEDORUSUARIOSMS")
	protected String proveedorUsuarioSMS = null;
	@Column(name = "PROVEEDORPASSWORDSMS")
	protected String proveedorPassSMS = null;
	
	@Column(name="MULTIORGANISMO")
	protected Integer multiorganismo =null;
	@Column(name="PREMIUM")
	protected Integer premium =null;

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getFromMail() {
		return this.fromMail;
	}

	public void setFromMailName(String fromMailName) {
		this.fromMailName = fromMailName;
	}

	public String getFromMailName() {
		return this.fromMailName;
	}

	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = Integer.valueOf(1);
		} else {
			this.activo = Integer.valueOf(0);
		}
	}

	public String getActivado() {
		if (activo != null && activo.intValue() == 1) {
			return "true";
		} else {
			return "false";
		}

	}

	public Integer getservicioId() {
		return servicioId;
	}

	public void setservicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Object getId() {
		return servicioId;
	}

	public void setId(Object servicioId) {
		this.servicioId = (Integer) servicioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getNmaxenvios() {
		return nmaxenvios;
	}

	public void setNmaxenvios(Integer nmaxenvios) {
		this.nmaxenvios = nmaxenvios;
	}

	
	public Integer getHistorificacion() {
		return historificacion;
	}

	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	public String getMotivoHistorificacion() {
		return motivoHistorificacion;
	}

	public void setMotivoHistorificacion(String motivoHistorificacion) {
		this.motivoHistorificacion = motivoHistorificacion;
	}

	public Integer getConservacion() {
		return conservacion;
	}

	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	public String getMotivoConservacion() {
		return motivoConservacion;
	}

	public void setMotivoConservacion(String motivoConservacion) {
		this.motivoConservacion = motivoConservacion;
	}

	public Integer getPendienteAprobacion() {
		return pendienteAprobacion;
	}

	public void setPendienteAprobacion(Integer pendienteAprobacion) {
		this.pendienteAprobacion = pendienteAprobacion;
	}

	public String getNombreLoteEnvio() {
		return nombreLoteEnvio;
	}

	public void setNombreLoteEnvio(String nombreLoteEnvio) {
		this.nombreLoteEnvio = nombreLoteEnvio;
	}

	public Integer getBadge() {
		return badge;
	}

	public void setBadge(Integer badge) {
		this.badge = badge;
	}

	public String getGcmProjectKey() {
		return gcmProjectKey;
	}

	public void setGcmProjectKey(String gcmProjectKey) {
		this.gcmProjectKey = gcmProjectKey;
	}

	public String getApnsRutaCertificado() {
		return apnsRutaCertificado;
	}

	public void setApnsRutaCertificado(String apnsRutaCertificado) {
		this.apnsRutaCertificado = apnsRutaCertificado;
	}

	public String getApnsPasswordCertificado() {
		return apnsPasswordCertificado;
	}

	public void setApnsPasswordCertificado(String apnsPasswordCertificado) {
		this.apnsPasswordCertificado = apnsPasswordCertificado;
	}

	public Integer getAndroidPlataforma() {
		return this.androidPlataforma;
	}

	public void setAndroidPlataforma(Integer androidPlataforma) {
		this.androidPlataforma = androidPlataforma;
	}

	public Integer getIosPlataforma() {
		return this.iosPlataforma;
	}

	public void setIosPlataforma(Integer iosPlataforma) {
		this.iosPlataforma = iosPlataforma;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public Integer getInformesActivo() {
		return informesActivo;
	}

	public void setInformesActivo(Integer informesActivo) {
		this.informesActivo = informesActivo;
	}

	public Integer getAgrupacionEstado() {
		return agrupacionEstado;
	}

	public void setAgrupacionEstado(Integer agrupacionEstado) {
		this.agrupacionEstado = agrupacionEstado;
	}

	public Integer getAgrupacionCodOrg() {
		return agrupacionCodOrg;
	}

	public void setAgrupacionCodOrg(Integer agrupacionCodOrg) {
		this.agrupacionCodOrg = agrupacionCodOrg;
	}

	public Integer getAgrupacionCodSia() {
		return agrupacionCodSia;
	}

	public void setAgrupacionCodSia(Integer agrupacionCodSia) {
		this.agrupacionCodSia = agrupacionCodSia;
	}

	public Integer getAgrupacionCodOrgPagador() {
		return agrupacionCodOrgPagador;
	}

	public void setAgrupacionCodOrgPagador(Integer agrupacionCodOrgPagador) {
		this.agrupacionCodOrgPagador = agrupacionCodOrgPagador;
	}

	public String getInformesDestinatarios() {
		return informesDestinatarios;
	}

	public void setInformesDestinatarios(String informesDestinatarios) {
		this.informesDestinatarios = informesDestinatarios;
	}

	public String getResponsableFuncionalNombre() {
		return responsableFuncionalNombre;
	}

	public void setResponsableFuncionalNombre(String responsableFuncionalNombre) {
		this.responsableFuncionalNombre = responsableFuncionalNombre;
	}

	public String getResponsableFuncionalEmail() {
		return responsableFuncionalEmail;
	}

	public void setResponsableFuncionalEmail(String responsableFuncionalEmail) {
		this.responsableFuncionalEmail = responsableFuncionalEmail;
	}

	public String getResponsableTecnicoNombre() {
		return responsableTecnicoNombre;
	}

	public void setResponsableTecnicoNombre(String responsableTecnicoNombre) {
		this.responsableTecnicoNombre = responsableTecnicoNombre;
	}

	public String getResponsableTecnicoEmail() {
		return responsableTecnicoEmail;
	}

	public void setResponsableTecnicoEmail(String responsableTecnicoEmail) {
		this.responsableTecnicoEmail = responsableTecnicoEmail;
	}

	public String getProveedorUsuarioSMS() {
		return proveedorUsuarioSMS;
	}

	public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
		this.proveedorUsuarioSMS = proveedorUsuarioSMS;
	}

	public String getProveedorPassSMS() {
		return proveedorPassSMS;
	}

	public void setProveedorPassSMS(String proveedorPassSMS) {
		this.proveedorPassSMS = proveedorPassSMS;
	}

	public Integer getMultiorganismo() {
		return multiorganismo;
	}

	public void setMultiorganismo(Integer multiorganismo) {
		this.multiorganismo = multiorganismo;
	}

	public Integer getPremium() {
		return premium;
	}

	public void setPremium(Integer premium) {
		this.premium = premium;
	}

	/*
	 * Devuelve el objeto como un XML
	 * 
	 * 
	 * public String obtenerXML() { StringBuffer sb = new StringBuffer("<objeto>OrganimoJPA</objeto>"); if(id != null) sb.append("<id>" + id +"</id>" ); if(nombre != null) sb.append("<nombre>" + nombre +"</nombre>" ); if(rol != null) sb.append("<rol>" + rol +"</rol>" );
	 * if(organismoPadre != null) sb.append("<organismoPadre>" + organismoPadre +"</organismoPadre>" ); if(sb.length()==0) return null; else return sb.toString(); }
	 */
}
