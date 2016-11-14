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
 * TblMensajesHist generated by hbm2java
 */
@Entity
@Table(name = "TBL_MENSAJES_HIST")
public class TblMensajesHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059688625347330149L;

	@Id
	@Column(name = "MENSAJEID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long mensajeid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ESTADOID")
	private TblEstados tblEstados;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOTEENVIOID", nullable = false)
	private TblLotesEnviosHist tblLotesEnviosHist;

	@Column(name = "CODIGOEXTERNO", length = 100)
	private String codigoexterno;

	@Column(name = "CABECERA", length = 2000)
	private String cabecera;

	@Column(name = "ESTADOACTUAL", nullable = false, length = 50)
	private String estadoactual;

	@Column(name = "NUMEROENVIOS", precision = 10, scale = 0)
	private Integer numeroenvios;

	@Column(name = "FECHACREACION", nullable = false, length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", nullable = false, length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "ULTIMOENVIO", length = 7)
	private Date ultimoenvio;

	@Column(name = "ULTIMOIDHISTORICO", precision = 22, scale = 0)
	private Long ultimoidhistorico;

	@Column(name = "CUERPO")
	private String cuerpo;

	@Column(name = "TIPOCUERPO", length = 10)
	private String tipocuerpo;

	@Column(name = "TIPOCODIFICACION", length = 50)
	private String tipocodificacion;

	@Column(name = "PRIORIDAD", precision = 22, scale = 0)
	private Integer prioridad;

	@Column(name = "TIPOMENSAJE", nullable = false, length = 20)
	private String tipomensaje;

	@Column(name = "TELEFONO", length = 15)
	private String telefono;

	@Column(name = "UIM", length = 50)
	private String uim;

	@Column(name = "IDENVIOSSMS", precision = 22, scale = 0)
	private Long idenviossms;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	@Column(name = "DOCUSUARIO", length = 20)
	private String docusuario;

	@Column(name = "CODSIA", length = 20)
	private String codsia;

	@Column(name = "CODORGANISMO", length = 20)
	private String codorganismo;

	@Column(name = "CODORGANISMOPAGADOR", length = 20)
	private String codorganismopagador;

	@Column(name = "NOMBREUSUARIO", length = 50)
	private String nombreusuario;

	@Column(name = "ICONO", length = 100)
	private String icono;

	@Column(name = "SONIDO", length = 100)
	private String sonido;

	public TblMensajesHist() {
	}

	/**
	 * @return the mensajeid
	 */
	public Long getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid
	 *            the mensajeid to set
	 */
	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the tblEstados
	 */
	public TblEstados getTblEstados() {
		return tblEstados;
	}

	/**
	 * @param tblEstados
	 *            the tblEstados to set
	 */
	public void setTblEstados(TblEstados tblEstados) {
		this.tblEstados = tblEstados;
	}

	/**
	 * @return the tblLotesEnviosHist
	 */
	public TblLotesEnviosHist getTblLotesEnviosHist() {
		return tblLotesEnviosHist;
	}

	/**
	 * @param tblLotesEnviosHist
	 *            the tblLotesEnviosHist to set
	 */
	public void setTblLotesEnviosHist(TblLotesEnviosHist tblLotesEnviosHist) {
		this.tblLotesEnviosHist = tblLotesEnviosHist;
	}

	/**
	 * @return the codigoexterno
	 */
	public String getCodigoexterno() {
		return codigoexterno;
	}

	/**
	 * @param codigoexterno
	 *            the codigoexterno to set
	 */
	public void setCodigoexterno(String codigoexterno) {
		this.codigoexterno = codigoexterno;
	}

	/**
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @return the estadoactual
	 */
	public String getEstadoactual() {
		return estadoactual;
	}

	/**
	 * @param estadoactual
	 *            the estadoactual to set
	 */
	public void setEstadoactual(String estadoactual) {
		this.estadoactual = estadoactual;
	}

	/**
	 * @return the numeroenvios
	 */
	public Integer getNumeroenvios() {
		return numeroenvios;
	}

	/**
	 * @param numeroenvios
	 *            the numeroenvios to set
	 */
	public void setNumeroenvios(Integer numeroenvios) {
		this.numeroenvios = numeroenvios;
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
	 * @return the ultimoenvio
	 */
	public Date getUltimoenvio() {
		return ultimoenvio;
	}

	/**
	 * @param ultimoenvio
	 *            the ultimoenvio to set
	 */
	public void setUltimoenvio(Date ultimoenvio) {
		this.ultimoenvio = ultimoenvio;
	}

	/**
	 * @return the ultimoidhistorico
	 */
	public Long getUltimoidhistorico() {
		return ultimoidhistorico;
	}

	/**
	 * @param ultimoidhistorico
	 *            the ultimoidhistorico to set
	 */
	public void setUltimoidhistorico(Long ultimoidhistorico) {
		this.ultimoidhistorico = ultimoidhistorico;
	}

	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * @param cuerpo
	 *            the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * @return the tipocuerpo
	 */
	public String getTipocuerpo() {
		return tipocuerpo;
	}

	/**
	 * @param tipocuerpo
	 *            the tipocuerpo to set
	 */
	public void setTipocuerpo(String tipocuerpo) {
		this.tipocuerpo = tipocuerpo;
	}

	/**
	 * @return the tipocodificacion
	 */
	public String getTipocodificacion() {
		return tipocodificacion;
	}

	/**
	 * @param tipocodificacion
	 *            the tipocodificacion to set
	 */
	public void setTipocodificacion(String tipocodificacion) {
		this.tipocodificacion = tipocodificacion;
	}

	/**
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad
	 *            the prioridad to set
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the tipomensaje
	 */
	public String getTipomensaje() {
		return tipomensaje;
	}

	/**
	 * @param tipomensaje
	 *            the tipomensaje to set
	 */
	public void setTipomensaje(String tipomensaje) {
		this.tipomensaje = tipomensaje;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the uim
	 */
	public String getUim() {
		return uim;
	}

	/**
	 * @param uim
	 *            the uim to set
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}

	/**
	 * @return the idenviossms
	 */
	public Long getIdenviossms() {
		return idenviossms;
	}

	/**
	 * @param idenviossms
	 *            the idenviossms to set
	 */
	public void setIdenviossms(Long idenviossms) {
		this.idenviossms = idenviossms;
	}

	/**
	 * @return the fechahistorificacion
	 */
	public Date getFechahistorificacion() {
		return fechahistorificacion;
	}

	/**
	 * @param fechahistorificacion
	 *            the fechahistorificacion to set
	 */
	public void setFechahistorificacion(Date fechahistorificacion) {
		this.fechahistorificacion = fechahistorificacion;
	}

	/**
	 * @return the docusuario
	 */
	public String getDocusuario() {
		return docusuario;
	}

	/**
	 * @param docusuario
	 *            the docusuario to set
	 */
	public void setDocusuario(String docusuario) {
		this.docusuario = docusuario;
	}

	/**
	 * @return the codsia
	 */
	public String getCodsia() {
		return codsia;
	}

	/**
	 * @param codsia
	 *            the codsia to set
	 */
	public void setCodsia(String codsia) {
		this.codsia = codsia;
	}

	/**
	 * @return the codorganismo
	 */
	public String getCodorganismo() {
		return codorganismo;
	}

	/**
	 * @param codorganismo
	 *            the codorganismo to set
	 */
	public void setCodorganismo(String codorganismo) {
		this.codorganismo = codorganismo;
	}

	/**
	 * @return the codorganismopagador
	 */
	public String getCodorganismopagador() {
		return codorganismopagador;
	}

	/**
	 * @param codorganismopagador
	 *            the codorganismopagador to set
	 */
	public void setCodorganismopagador(String codorganismopagador) {
		this.codorganismopagador = codorganismopagador;
	}

	/**
	 * @return the nombreusuario
	 */
	public String getNombreusuario() {
		return nombreusuario;
	}

	/**
	 * @param nombreusuario
	 *            the nombreusuario to set
	 */
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	/**
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono
	 *            the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}

	/**
	 * @return the sonido
	 */
	public String getSonido() {
		return sonido;
	}

	/**
	 * @param sonido
	 *            the sonido to set
	 */
	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

}
