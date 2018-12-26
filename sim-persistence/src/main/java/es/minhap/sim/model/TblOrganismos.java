package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TblOrganismos generated by hbm2java
 */
@Entity
@Table(name = "TBL_ORGANISMOS")
public class TblOrganismos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6508061878633453602L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORGANISMOID_SEC")
    @SequenceGenerator(name="ORGANISMOID_SEC", sequenceName="ORGANISMOID_SEC", allocationSize=1)
	@Column(name = "ORGANISMOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long organismoid;

	@Column(name = "DIR3", nullable = false, length = 100)
	private String dir3;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	private Boolean activo;

	@Column(name = "FECHACREACION", nullable = false, length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", nullable = false, length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "EXTERNALID", precision = 22, scale = 0)
	private String externalid;

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

	@Column(name = "ELIMINADO", length = 1)
	private String eliminado;
	
	@Column(name = "ESTADO", length = 1)
	private String estado;
	
	@Column(name = "NIFCIF", length = 9)
	private String nifcif;
	
	@Column(name = "SIGLAS", length = 10)
	private String siglas;
	
	@Column(name = "NIVELADMINISTRACION")
	private Integer nivelAdministracion;
	
	@Column(name = "NIVELJERARQUICO")
	private Integer nivelJerarquico;
	
	@Column(name = "CODUNIDADSUPERIOR", length = 5)
	private String codUnidadSuperior;
	
	@Column(name = "DENOMUNIDADSUPERIOR", length = 300)
	private String denomUnidadSuperior;
	
	@Column(name = "CODUNIDADRAIZ", length = 9)
	private String codUnidadRaiz;
	
	@Column(name = "DENOMUNIDADRAIZ", length = 300)
	private String denomUnidadRaiz;
	
	@Column(name = "ESEDP", length = 1)
	private String esEdp;
	
	@Column(name = "CODEDPPRINCIPAL", length = 9)
	private String codEdpPrincipal;
	
	@Column(name = "DENOMEDPPRINCIPAL", length = 300)
	private String denomEdpPrincipal;
	
	@Column(name = "CODTIPOENTPUBLIC", length = 2)
	private String codTipoEntPublic;
	
	@Column(name = "CODTIPOUNIDAD", length = 3)
	private String codTipoUnidad;
	
	@Column(name = "CODAMBTERRITORIAL", length = 2)
	private String codAmbTerritorial;
	
	@Column(name = "CODAMBENTGEOGRAFICA", length = 2)
	private String codAmbEntGeografica;
	
	@Column(name = "CODAMBPAIS", length = 3)
	private String codAmbPais;
	
	@Column(name = "CODAMBCOMUNIDAD", length = 2)
	private String codAmbComunidad;
	
	@Column(name = "CODAMBPROVINCIA", length = 2)
	private String codAmbProvincia;
	
	@Column(name = "CODAMBMUNICIPIO", length = 4)
	private String codAmbMunicipio;
	
	@Column(name = "CODAMBISLA", length = 2)
	private String codAmbIsla;
	
	@Column(name = "CODAMBELM", length = 4)
	private String codAmbElm;
	
	@Column(name = "CODAMBLOCEXTRANJERA", length = 1000)
	private String codAmbLocExtranjera;
	
	@Column(name = "COMPETENCIAS", length = 300)
	private String competencias;
	
	@Column(name = "DISPOSICIONLEGAL", length = 300)
	private String disposicionLegal;
	
	@Column(name = "FECHAALTAOFICIAL")
	private Date fechaAltaOficial;
	
	@Column(name = "FECHABAJAOFICIAL")
	private Date fechaBajaOficial;
	
	@Column(name = "FECHAEXTINCION")
	private Date fechaExtincion;
	
	@Column(name = "FECHAANULACION")
	private Date fechaAnulacion;
	
	@Column(name = "OBSERVGENERALES", length = 400)
	private String observGenerales;
	
	@Column(name = "OBSERVBAJA", length = 400)
	private String observBaja;
	
	@Column(name = "TIPOVIA", length = 2)
	private String tipoVia;
	
	@Column(name = "NOMBREVIA", length = 300)
	private String nombreVia;
	
	@Column(name = "NUMVIA", length = 1000)
	private String numVia;
	
	@Column(name = "COMPLEMENTO", length = 1000)
	private String complemento;
	
	@Column(name = "CODPOSTAL", length = 100)
	private String codPostal;
	
	@Column(name = "CODPAIS", length = 3)
	private String codPais;
	
	@Column(name = "CODCOMUNIDAD", length = 2)
	private String codComunidad;
	
	@Column(name = "CODPROVINCIA", length = 2)
	private String codProvincia;
	
	@Column(name = "CODLOCALIDAD", length = 2)
	private String codLocalidad;
	
	@Column(name = "CODENTGEOGRAFICA", length = 2)
	private String codEntGeografica;
	
	@Column(name = "DIREXTRANJERA", length = 1000)
	private String dirExtranjera;
	
	@Column(name = "LOCEXTRANJERA", length = 1000)
	private String locExtranjera;
	
	@Column(name = "OBSERVACIONES", length = 2000)
	private String observaciones;
	
	@Column(name = "MANUAL", precision = 1, scale = 0)
	private Boolean manual;
	
	@Column(name = "FECHAACTIVO", length = 7)
	private Date fechaActivo;
	
	@Column(name = "IDORGANISMOPDP", precision = 22, scale = 0)
	private Long idOrganismoPdp;
	
	public TblOrganismos() {
	}

	/**
	 * @return the organismoid
	 */
	public Long getOrganismoid() {
		return organismoid;
	}

	/**
	 * @param organismoid
	 *            the organismoid to set
	 */
	public void setOrganismoid(Long organismoid) {
		this.organismoid = organismoid;
	}

	/**
	 * @return the dir3
	 */
	public String getDir3() {
		return dir3;
	}

	/**
	 * @param dir3
	 *            the dir3 to set
	 */
	public void setDir3(String dir3) {
		this.dir3 = dir3;
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
	public String getExternalid() {
		return externalid;
	}

	/**
	 * @param externalid
	 *            the externalid to set
	 */
	public void setExternalid(String externalid) {
		this.externalid = externalid;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the nifcif
	 */
	public String getNifcif() {
		return nifcif;
	}

	/**
	 * @param nifcif the nifcif to set
	 */
	public void setNifcif(String nifcif) {
		this.nifcif = nifcif;
	}

	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * @return the nivelAdministracion
	 */
	public Integer getNivelAdministracion() {
		return nivelAdministracion;
	}

	/**
	 * @param nivelAdministracion the nivelAdministracion to set
	 */
	public void setNivelAdministracion(Integer nivelAdministracion) {
		this.nivelAdministracion = nivelAdministracion;
	}

	/**
	 * @return the nivelJerarquico
	 */
	public Integer getNivelJerarquico() {
		return nivelJerarquico;
	}

	/**
	 * @param nivelJerarquico the nivelJerarquico to set
	 */
	public void setNivelJerarquico(Integer nivelJerarquico) {
		this.nivelJerarquico = nivelJerarquico;
	}

	/**
	 * @return the codUnidadSuperior
	 */
	public String getCodUnidadSuperior() {
		return codUnidadSuperior;
	}

	/**
	 * @param codUnidadSuperior the codUnidadSuperior to set
	 */
	public void setCodUnidadSuperior(String codUnidadSuperior) {
		this.codUnidadSuperior = codUnidadSuperior;
	}

	/**
	 * @return the denomUnidadSuperior
	 */
	public String getDenomUnidadSuperior() {
		return denomUnidadSuperior;
	}

	/**
	 * @param denomUnidadSuperior the denomUnidadSuperior to set
	 */
	public void setDenomUnidadSuperior(String denomUnidadSuperior) {
		this.denomUnidadSuperior = denomUnidadSuperior;
	}

	/**
	 * @return the codUnidadRaiz
	 */
	public String getCodUnidadRaiz() {
		return codUnidadRaiz;
	}

	/**
	 * @param codUnidadRaiz the codUnidadRaiz to set
	 */
	public void setCodUnidadRaiz(String codUnidadRaiz) {
		this.codUnidadRaiz = codUnidadRaiz;
	}

	/**
	 * @return the denomUnidadRaiz
	 */
	public String getDenomUnidadRaiz() {
		return denomUnidadRaiz;
	}

	/**
	 * @param denomUnidadRaiz the denomUnidadRaiz to set
	 */
	public void setDenomUnidadRaiz(String denomUnidadRaiz) {
		this.denomUnidadRaiz = denomUnidadRaiz;
	}

	/**
	 * @return the esEdp
	 */
	public String getEsEdp() {
		return esEdp;
	}

	/**
	 * @param esEdp the esEdp to set
	 */
	public void setEsEdp(String esEdp) {
		this.esEdp = esEdp;
	}

	/**
	 * @return the codEdpPrincipal
	 */
	public String getCodEdpPrincipal() {
		return codEdpPrincipal;
	}

	/**
	 * @param codEdpPrincipal the codEdpPrincipal to set
	 */
	public void setCodEdpPrincipal(String codEdpPrincipal) {
		this.codEdpPrincipal = codEdpPrincipal;
	}

	/**
	 * @return the denomEdpPrincipal
	 */
	public String getDenomEdpPrincipal() {
		return denomEdpPrincipal;
	}

	/**
	 * @param denomEdpPrincipal the denomEdpPrincipal to set
	 */
	public void setDenomEdpPrincipal(String denomEdpPrincipal) {
		this.denomEdpPrincipal = denomEdpPrincipal;
	}

	/**
	 * @return the codTipoEntPublic
	 */
	public String getCodTipoEntPublic() {
		return codTipoEntPublic;
	}

	/**
	 * @param codTipoEntPublic the codTipoEntPublic to set
	 */
	public void setCodTipoEntPublic(String codTipoEntPublic) {
		this.codTipoEntPublic = codTipoEntPublic;
	}

	/**
	 * @return the codTipoUnidad
	 */
	public String getCodTipoUnidad() {
		return codTipoUnidad;
	}

	/**
	 * @param codTipoUnidad the codTipoUnidad to set
	 */
	public void setCodTipoUnidad(String codTipoUnidad) {
		this.codTipoUnidad = codTipoUnidad;
	}

	/**
	 * @return the codAmbTerritorial
	 */
	public String getCodAmbTerritorial() {
		return codAmbTerritorial;
	}

	/**
	 * @param codAmbTerritorial the codAmbTerritorial to set
	 */
	public void setCodAmbTerritorial(String codAmbTerritorial) {
		this.codAmbTerritorial = codAmbTerritorial;
	}

	/**
	 * @return the codAmbEntGeografica
	 */
	public String getCodAmbEntGeografica() {
		return codAmbEntGeografica;
	}

	/**
	 * @param codAmbEntGeografica the codAmbEntGeografica to set
	 */
	public void setCodAmbEntGeografica(String codAmbEntGeografica) {
		this.codAmbEntGeografica = codAmbEntGeografica;
	}

	/**
	 * @return the codAmbPais
	 */
	public String getCodAmbPais() {
		return codAmbPais;
	}

	/**
	 * @param codAmbPais the codAmbPais to set
	 */
	public void setCodAmbPais(String codAmbPais) {
		this.codAmbPais = codAmbPais;
	}

	/**
	 * @return the codAmbComunidad
	 */
	public String getCodAmbComunidad() {
		return codAmbComunidad;
	}

	/**
	 * @param codAmbComunidad the codAmbComunidad to set
	 */
	public void setCodAmbComunidad(String codAmbComunidad) {
		this.codAmbComunidad = codAmbComunidad;
	}

	/**
	 * @return the codAmbProvincia
	 */
	public String getCodAmbProvincia() {
		return codAmbProvincia;
	}

	/**
	 * @param codAmbProvincia the codAmbProvincia to set
	 */
	public void setCodAmbProvincia(String codAmbProvincia) {
		this.codAmbProvincia = codAmbProvincia;
	}

	/**
	 * @return the codAmbMunicipio
	 */
	public String getCodAmbMunicipio() {
		return codAmbMunicipio;
	}

	/**
	 * @param codAmbMunicipio the codAmbMunicipio to set
	 */
	public void setCodAmbMunicipio(String codAmbMunicipio) {
		this.codAmbMunicipio = codAmbMunicipio;
	}

	/**
	 * @return the codAmbIsla
	 */
	public String getCodAmbIsla() {
		return codAmbIsla;
	}

	/**
	 * @param codAmbIsla the codAmbIsla to set
	 */
	public void setCodAmbIsla(String codAmbIsla) {
		this.codAmbIsla = codAmbIsla;
	}

	/**
	 * @return the codAmbElm
	 */
	public String getCodAmbElm() {
		return codAmbElm;
	}

	/**
	 * @param codAmbElm the codAmbElm to set
	 */
	public void setCodAmbElm(String codAmbElm) {
		this.codAmbElm = codAmbElm;
	}

	/**
	 * @return the codAmbLocExtranjera
	 */
	public String getCodAmbLocExtranjera() {
		return codAmbLocExtranjera;
	}

	/**
	 * @param codAmbLocExtranjera the codAmbLocExtranjera to set
	 */
	public void setCodAmbLocExtranjera(String codAmbLocExtranjera) {
		this.codAmbLocExtranjera = codAmbLocExtranjera;
	}

	/**
	 * @return the competencias
	 */
	public String getCompetencias() {
		return competencias;
	}

	/**
	 * @param competencias the competencias to set
	 */
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	/**
	 * @return the disposicionLegal
	 */
	public String getDisposicionLegal() {
		return disposicionLegal;
	}

	/**
	 * @param disposicionLegal the disposicionLegal to set
	 */
	public void setDisposicionLegal(String disposicionLegal) {
		this.disposicionLegal = disposicionLegal;
	}

	/**
	 * @return the fechaAltaOficial
	 */
	public Date getFechaAltaOficial() {
		return fechaAltaOficial;
	}

	/**
	 * @param fechaAltaOficial the fechaAltaOficial to set
	 */
	public void setFechaAltaOficial(Date fechaAltaOficial) {
		this.fechaAltaOficial = fechaAltaOficial;
	}

	/**
	 * @return the fechaBajaOficial
	 */
	public Date getFechaBajaOficial() {
		return fechaBajaOficial;
	}

	/**
	 * @param fechaBajaOficial the fechaBajaOficial to set
	 */
	public void setFechaBajaOficial(Date fechaBajaOficial) {
		this.fechaBajaOficial = fechaBajaOficial;
	}

	/**
	 * @return the fechaExtincion
	 */
	public Date getFechaExtincion() {
		return fechaExtincion;
	}

	/**
	 * @param fechaExtincion the fechaExtincion to set
	 */
	public void setFechaExtincion(Date fechaExtincion) {
		this.fechaExtincion = fechaExtincion;
	}

	/**
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	/**
	 * @return the observGenerales
	 */
	public String getObservGenerales() {
		return observGenerales;
	}

	/**
	 * @param observGenerales the observGenerales to set
	 */
	public void setObservGenerales(String observGenerales) {
		this.observGenerales = observGenerales;
	}

	/**
	 * @return the observBaja
	 */
	public String getObservBaja() {
		return observBaja;
	}

	/**
	 * @param observBaja the observBaja to set
	 */
	public void setObservBaja(String observBaja) {
		this.observBaja = observBaja;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @param tipoVia the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @return the nombreVia
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @param nombreVia the nombreVia to set
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @return the numVia
	 */
	public String getNumVia() {
		return numVia;
	}

	/**
	 * @param numVia the numVia to set
	 */
	public void setNumVia(String numVia) {
		this.numVia = numVia;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the codPostal
	 */
	public String getCodPostal() {
		return codPostal;
	}

	/**
	 * @param codPostal the codPostal to set
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * @return the codComunidad
	 */
	public String getCodComunidad() {
		return codComunidad;
	}

	/**
	 * @param codComunidad the codComunidad to set
	 */
	public void setCodComunidad(String codComunidad) {
		this.codComunidad = codComunidad;
	}

	/**
	 * @return the codProvincia
	 */
	public String getCodProvincia() {
		return codProvincia;
	}

	/**
	 * @param codProvincia the codProvincia to set
	 */
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	/**
	 * @return the codLocalidad
	 */
	public String getCodLocalidad() {
		return codLocalidad;
	}

	/**
	 * @param codLocalidad the codLocalidad to set
	 */
	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	/**
	 * @return the codEntGeografica
	 */
	public String getCodEntGeografica() {
		return codEntGeografica;
	}

	/**
	 * @param codEntGeografica the codEntGeografica to set
	 */
	public void setCodEntGeografica(String codEntGeografica) {
		this.codEntGeografica = codEntGeografica;
	}

	/**
	 * @return the dirExtranjera
	 */
	public String getDirExtranjera() {
		return dirExtranjera;
	}

	/**
	 * @param dirExtranjera the dirExtranjera to set
	 */
	public void setDirExtranjera(String dirExtranjera) {
		this.dirExtranjera = dirExtranjera;
	}

	/**
	 * @return the locExtranjera
	 */
	public String getLocExtranjera() {
		return locExtranjera;
	}

	/**
	 * @param locExtranjera the locExtranjera to set
	 */
	public void setLocExtranjera(String locExtranjera) {
		this.locExtranjera = locExtranjera;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the manual
	 */
	public Boolean getManual() {
		return manual;
	}

	/**
	 * @param manual the manual to set
	 */
	public void setManual(Boolean manual) {
		this.manual = manual;
	}

	/**
	 * @return the fechaactivo
	 */

	public Date getFechaActivo() {
		return fechaActivo;
	}
	/**
	 * @param fechaactivo
	 *            the fechaactivo to set
	 */
	public void setFechaActivo(Date fechaactivo) {
		this.fechaActivo = fechaactivo;
	}

	/**
	 * @return the organismoPdpId
	 */
	public Long getIdOrganismoPdp() {
		return idOrganismoPdp;
	}

	/**
	 * @param organismoPdpId the organismoPdpId to set
	 */
	public void setIdOrganismoPdp(Long idOrganismoPdp) {
		this.idOrganismoPdp = idOrganismoPdp;
	}	

	
}
