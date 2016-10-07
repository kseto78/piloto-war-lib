package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_MENSAJES_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllMensajesHistoricosJPA", query = "SELECT e FROM MensajesHistoricosJPA e"),
	@NamedQuery(name = "selectMensajeHistoricosJPA", query = "SELECT e FROM MensajesHistoricosJPA e where e.mensajeId=:mensajeId"),
	@NamedQuery(name = "selectMensajesHistoricosJPAPorLotes", query = "SELECT e FROM MensajesHistoricosJPA e where to_char(e.loteEnvioId) = :loteId"),
	@NamedQuery(name = "selectMensajesLoteHistoricosCount", query = "SELECT count(e) FROM MensajesHistoricosJPA e where to_char(e.loteEnvioId) = :loteId")})

public class MensajesHistoricosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensajesHistoricosJPA() {
		
		this.mensajeId=null;
		this.loteEnvioId=null;
		this.codigoExterno=null;
		this.cuerpo=null;
		this.cabecera=null;
		this.estadoActual=null;
		this.numeroEnvios=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.ultimoEnvio=null;
		this.ultimoIdHistorico=null;
		this.tipoMensaje=null;
		this.telefono=null;
		this.uim=null;
		this.idEnviosSms=null;
		this.fechaHistorificado = null;
		this.docUsuario=null;
		this.codSIA=null;
		this.codOrganismo=null;
		this.codOrganismoPagador=null;
		this.nombreUsuario=null;
		this.icono=null;
		this.sonido=null;
		

	}

	
	@Id
	@Column(name="MENSAJEID")
	protected Integer mensajeId;
	@Column(name="LOTEENVIOID")
	private Integer loteEnvioId;
	@Column(name="CODIGOEXTERNO")
	private String codigoExterno;
	@Column(name="CUERPO")
	private String cuerpo;
	@Column(name="CABECERA")
	private String cabecera;
	@Column(name="ESTADOACTUAL")
	private String estadoActual;
	@Column(name="NUMEROENVIOS")
	private String numeroEnvios;
	@Column(name="FECHACREACION")
	private Date fechaCreacion;
	@Column(name="CREADOPOR")
	private String creadoPor;
	@Column(name="FECHAMODIFICACION")
	private Date fechaModificacion;
	@Column(name="MODIFICADOPOR")
	private String modificadoPor;
	@Column(name="ULTIMOENVIO")
	private Date ultimoEnvio;
	@Column(name="ULTIMOIDHISTORICO")
	private Integer ultimoIdHistorico;
	@Column(name="TIPOCUERPO")
	private String tipoCuerpo;
	@Column(name="TIPOCODIFICACION")
	private String tipoCodificacion;
	@Column(name="PRIORIDAD")
	private String prioridad;
	@Column(name="TIPOMENSAJE")
	private String tipoMensaje;
	@Column(name="TELEFONO")
	private String telefono;
	@Column(name="UIM")
	private String uim;
	@Column(name="IDENVIOSSMS")
	private Integer idEnviosSms;
	@Column (name="FECHAHISTORIFICACION")
	protected Date fechaHistorificado;
	@Column(name="DOCUSUARIO")
	private String docUsuario;
	@Column(name="CODSIA")
	private String codSIA;
	@Column(name="CODORGANISMO")
	private String codOrganismo;
	@Column(name="CODORGANISMOPAGADOR")
	private String codOrganismoPagador;
	@Column(name="NOMBREUSUARIO")
	private String nombreUsuario;
	@Column(name="ICONO")
	private String icono;
	@Column(name="SONIDO")
	private String sonido;
	
	
	public String getTipoCuerpo() {
		return tipoCuerpo;
	}
	public void setTipoCuerpo(String tipoCuerpo) {
		this.tipoCuerpo = tipoCuerpo;
	}
	public String getTipoCodificacion() {
		return tipoCodificacion;
	}
	public void setTipoCodificacion(String tipoCodificacion) {
		this.tipoCodificacion = tipoCodificacion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}
	public String getCodigoExterno() {
		return codigoExterno;
	}
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getEstadoActual() {
		return estadoActual;
	}
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}
	public String getNumeroEnvios() {
		return numeroEnvios;
	}
	public void setNumeroEnvios(String numeroEnvios) {
		this.numeroEnvios = numeroEnvios;
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
	public Integer getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}
	public void setUltimoIdHistorico(Integer ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.mensajeId;
	}
	public void setId(Object id){
		this.mensajeId =(Integer)id;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}
	public Date getFechaHistorificado() {
		return fechaHistorificado;
	}
	public void setFechaHistorificado(Date fechaHistorificado) {
		this.fechaHistorificado = fechaHistorificado;
	}
	
	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}
	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUim() {
		return uim;
	}
	public void setUim(String uim) {
		this.uim = uim;
	}
	public Integer getIdEnviosSms() {
		return idEnviosSms;
	}
	public void setIdEnviosSms(Integer idEnviosSms) {
		this.idEnviosSms = idEnviosSms;
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
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getSonido() {
		return sonido;
	}
	public void setSonido(String sonido) {
		this.sonido = sonido;
	}
	
	
}
