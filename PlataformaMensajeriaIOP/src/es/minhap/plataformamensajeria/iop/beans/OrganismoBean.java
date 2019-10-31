package es.minhap.plataformamensajeria.iop.beans;

import java.util.Date;

public class OrganismoBean {


	protected static final String R_CONST_1 = "true";
	private Integer organismoId;
	private String dir3;
	private String nombre = null;
	private String descripcion = null;
	private Boolean activo = null;
	private Date fechacreacion = null;
	private Date fechaCreacionDesde = null;
	private Date fechaCreacionHasta = null;
	private Date fechamodificacion = null;
	private String creadopor = null;
	private String modificadopor = null;
	private Long externalid;
	private String nombrecuentaenvio;
	private Integer historificacion;
	private String motivohistorificacion;
	private Integer conservacion;
	private String motivoconservacion;
	private String isActivo = null;
	private String estado;
	private String nifcif;
	private String siglas;
	private Integer nivelAdministracion;
	private Integer nivelJerarquico;
	private String codUnidadSuperior;
	private String denomUnidadSuperior;
	private String codUnidadRaiz;
	private String denomUnidadRaiz;
	private String esEdp;
	private String codEdpPrincipal;
	private String denomEdpPrincipal;
	private String codTipoEntPublic;
	private String codTipoUnidad;
	private String codAmbTerritorial;
	private String codAmbEntGeografica;
	private String codAmbPais;
	private String codAmbComunidad;
	private String codAmbProvincia;
	private String codAmbMunicipio;
	private String codAmbIsla;
	private String codAmbElm;
	private String codAmbLocExtranjera;
	private String competencias;
	private String disposicionLegal;
	private Date fechaAltaOficial;
	private Date fechaBajaOficial;
	private Date fechaExtincion;
	private Date fechaAnulacion;
	private String observGenerales;
	private String observBaja;
	private String tipoVia;
	private String nombreVia;
	private String numVia;
	private String complemento;
	private String codPostal;
	private String codPais;
	private String codComunidad;
	private String codProvincia;
	private String codLocalidad;
	private String codEntGeografica;
	private String dirExtranjera;
	private String locExtranjera;
	private String observaciones;
	private Boolean asociadosServicio;
	private Date fechaEstado;
	private Date fechaActivo;

	public OrganismoBean() {
		super();
		this.organismoId = null;
		this.dir3 = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechacreacion = null;
		this.fechaCreacionDesde = null;
		this.fechaCreacionHasta = null;
		this.fechamodificacion = null;
		this.creadopor = null;
		this.modificadopor = null;
		this.externalid = null;
		this.nombrecuentaenvio = null;
		this.historificacion = null;
		this.motivohistorificacion = null;
		this.conservacion = null;
		this.motivoconservacion = null;
		this.isActivo = null;
		this.nifcif = null;
		this.siglas = null;
		this.nivelAdministracion = null;
		this.nivelJerarquico = null;
		this.codUnidadSuperior = null;
		this.denomUnidadSuperior = null;
		this.codUnidadRaiz = null;
		this.denomUnidadRaiz = null ;
		this.esEdp = null ;
		this.codEdpPrincipal = null;
		this.denomEdpPrincipal = null;
		this.codTipoEntPublic = null;
		this.codTipoUnidad = null;
		this.codAmbTerritorial = null;
		this.codAmbEntGeografica = null;
		this.codAmbPais = null;
		this.codAmbComunidad = null;
		this.codAmbProvincia = null;
		this.codAmbMunicipio = null;
		this.codAmbIsla = null;
		this.codAmbElm = null;
		this.codAmbLocExtranjera = null;
		this.competencias = null;
		this.disposicionLegal = null;
		this.fechaAltaOficial = null;
		this.fechaBajaOficial = null;
		this.fechaExtincion = null;
		this.fechaAnulacion = null;
		this.observGenerales = null;
		this.observBaja = null;
		this.tipoVia = null;
		this.nombreVia = null;
		this.numVia = null;
		this.complemento = null;
		this.codPostal = null;
		this.codPais = null;
		this.codComunidad = null;
		this.codProvincia = null;
		this.codLocalidad = null;
		this.codEntGeografica = null;
		this.dirExtranjera = null;
		this.locExtranjera = null;
		this.observaciones = null;
		this.asociadosServicio = null;
		this.fechaEstado = null;
		this.fechaActivo = null;

	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public void setActivado(String activado) {
		if (R_CONST_1.equals(activado)) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}

	}

	public void setIsActivo(String isActivo) {
		if (R_CONST_1.equals(isActivo)) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	public String getActivado() {
		if (activo != null && activo) {
			return R_CONST_1;
		} else {
			return "false";
		}
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
	 * @return the asociadosServicio
	 */
	public Boolean getAsociadosServicio() {
		return asociadosServicio;
	}

	/**
	 * @param asociadosServicio the asociadosServicio to set
	 */
	public void setAsociadosServicio(Boolean asociadosServicio) {
		this.asociadosServicio = asociadosServicio;
	}

	/**
	 * @return the fechaEstado
	 */
	public Date getFechaEstado() {
		if ("V".equals(estado)){
			return fechaAltaOficial;
		}else if ("T".equals(estado)){
			return fechaBajaOficial;
		}else if ("A".equals(estado)){
			return fechaAnulacion;
		}else if ("E".equals(estado)){
			return fechaExtincion;
		}else {
			return fechamodificacion;
		}
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * @return the fechaCreacionDesde
	 */
	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}

	/**
	 * @param fechaCreacionDesde the fechaCreacionDesde to set
	 */
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}

	/**
	 * @return the fechaCreacionHasta
	 */
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}

	/**
	 * @param fechaCreacionHasta the fechaCreacionHasta to set
	 */
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}
	
	/**
	 * @return the fechaActivo
	 */
	public Date getFechaActivo() {
		return fechaActivo;
	}

	
	/**
	 * @param fechaActivo the fechaActivo to set
	 */
	public void setFechaActivo(Date fechaActivo) {
		this.fechaActivo = fechaActivo;
	}
	
	

	
}
