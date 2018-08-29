package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * Clase OrganismoBean.
 */
public class OrganismoBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1268241822059205167L;

	/**
	 * Constructor de organismo bean.
	 */
	public OrganismoBean() {
		super();
		this.organismoId = null;
		this.dir3 = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechacreacion = null;
		this.fechaCreacionDesde=null;
		this.fechaCreacionHasta=null;
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
		this.fechaEstadoStr = null;
		this.manual = null;
		this.fechaActivo = null;
		
	}

	/**  organismo id. */
	protected Integer organismoId;
	
	/**  dir 3. */
	protected String dir3;
	
	/**  nombre. */
	protected String nombre = null;
	
	/**  descripcion. */
	protected String descripcion = null;
	
	/**  activo. */
	protected Boolean activo = null;
	
	/**  fechacreacion. */
	protected Date fechacreacion = null;
	
	/**  fecha creacion desde. */
	protected Date fechaCreacionDesde = null;
	
	/**  fecha creacion hasta. */
	protected Date fechaCreacionHasta = null;
	
	/**  fechamodificacion. */
	protected Date fechamodificacion = null;
	
	/**  creadopor. */
	protected String creadopor = null;
	
	/**  modificadopor. */
	protected String modificadopor = null;
	
	/**  externalid. */
	protected Long externalid;
	
	/**  nombrecuentaenvio. */
	protected String nombrecuentaenvio;
	
	/**  historificacion. */
	protected Integer historificacion;
	
	/**  motivohistorificacion. */
	protected String motivohistorificacion;
	
	/**  conservacion. */
	protected Integer conservacion;
	
	/**  motivoconservacion. */
	protected String motivoconservacion;
	
	/**  is activo. */
	protected String isActivo = null;
	
	/**  estado. */
	protected String estado;
	
	/**  nifcif. */
	protected String nifcif;
	
	/**  siglas. */
	protected String siglas;
	
	/**  nivel administracion. */
	protected Integer nivelAdministracion;
	
	/**  nivel jerarquico. */
	protected Integer nivelJerarquico;
	
	/**  cod unidad superior. */
	protected String codUnidadSuperior;
	
	/**  denom unidad superior. */
	protected String denomUnidadSuperior;
	
	/**  cod unidad raiz. */
	protected String codUnidadRaiz;
	
	/**  denom unidad raiz. */
	protected String denomUnidadRaiz;
	
	/**  es edp. */
	protected String esEdp;
	
	/**  cod edp principal. */
	protected String codEdpPrincipal;
	
	/**  denom edp principal. */
	protected String denomEdpPrincipal;
	
	/**  cod tipo ent public. */
	protected String codTipoEntPublic;
	
	/**  cod tipo unidad. */
	protected String codTipoUnidad;
	
	/**  cod amb territorial. */
	protected String codAmbTerritorial;
	
	/**  cod amb ent geografica. */
	protected String codAmbEntGeografica;
	
	/**  cod amb pais. */
	protected String codAmbPais;
	
	/**  cod amb comunidad. */
	protected String codAmbComunidad;
	
	/**  cod amb provincia. */
	protected String codAmbProvincia;
	
	/**  cod amb municipio. */
	protected String codAmbMunicipio;
	
	/**  cod amb isla. */
	protected String codAmbIsla;
	
	/**  cod amb elm. */
	protected String codAmbElm;
	
	/**  cod amb loc extranjera. */
	protected String codAmbLocExtranjera;
	
	/**  competencias. */
	protected String competencias;
	
	/**  disposicion legal. */
	protected String disposicionLegal;
	
	/**  fecha alta oficial. */
	protected Date fechaAltaOficial;
	
	/**  fecha baja oficial. */
	protected Date fechaBajaOficial;
	
	/**  fecha extincion. */
	protected Date fechaExtincion;
	
	/**  fecha anulacion. */
	protected Date fechaAnulacion;
	
	/**  observ generales. */
	protected String observGenerales;
	
	/**  observ baja. */
	protected String observBaja;
	
	/**  tipo via. */
	protected String tipoVia;
	
	/**  nombre via. */
	protected String nombreVia;
	
	/**  num via. */
	protected String numVia;
	
	/**  complemento. */
	protected String complemento;
	
	/**  cod postal. */
	protected String codPostal;
	
	/**  cod pais. */
	protected String codPais;
	
	/**  cod comunidad. */
	protected String codComunidad;
	
	/**  cod provincia. */
	protected String codProvincia;
	
	/**  cod localidad. */
	protected String codLocalidad;
	
	/**  cod ent geografica. */
	protected String codEntGeografica;
	
	/**  dir extranjera. */
	protected String dirExtranjera;
	
	/**  loc extranjera. */
	protected String locExtranjera;
	
	/**  observaciones. */
	protected String observaciones;
	
	/**  asociados servicio. */
	protected Boolean asociadosServicio;
	
	/**  fecha estado. */
	protected Date fechaEstado;
	
	/**  fecha estado str. */
	protected String fechaEstadoStr;
	
	/**  manual. */
	protected Boolean manual;
	
	/**  fecha activo. */
	protected Date fechaActivo;
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener organismo id.
	 *
	 * @return organismo id
	 */
	public Integer getOrganismoId() {
		return organismoId;
	}

	/**
	 * Modificar organismo id.
	 *
	 * @param organismoId new organismo id
	 */
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	/**
	 * Modificar activado.
	 *
	 * @param activado new activado
	 */
	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	/**
	 * Obtener checks if is activo.
	 *
	 * @return checks if is activo
	 */
	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}

	}

	/**
	 * Modificar checks if is activo.
	 *
	 * @param isActivo new checks if is activo
	 */
	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	/**
	 * Obtener activado.
	 *
	 * @return activado
	 */
	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Obtener dir 3.
	 *
	 * @return the dir3
	 */
	public String getDir3() {
		return dir3;
	}

	/**
	 * Modificar dir 3.
	 *
	 * @param dir3            the dir3 to set
	 */
	public void setDir3(String dir3) {
		this.dir3 = dir3;
	}

	/**
	 * Obtener nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar nombre.
	 *
	 * @param nombre            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtener activo.
	 *
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Obtener fechacreacion.
	 *
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * Modificar fechacreacion.
	 *
	 * @param fechacreacion            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * Obtener fechamodificacion.
	 *
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * Modificar fechamodificacion.
	 *
	 * @param fechamodificacion            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * Obtener creadopor.
	 *
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * Modificar creadopor.
	 *
	 * @param creadopor            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * Obtener modificadopor.
	 *
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * Modificar modificadopor.
	 *
	 * @param modificadopor            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * Obtener externalid.
	 *
	 * @return the externalid
	 */
	public Long getExternalid() {
		return externalid;
	}

	/**
	 * Modificar externalid.
	 *
	 * @param externalid            the externalid to set
	 */
	public void setExternalid(Long externalid) {
		this.externalid = externalid;
	}

	/**
	 * Obtener nombrecuentaenvio.
	 *
	 * @return the nombrecuentaenvio
	 */
	public String getNombrecuentaenvio() {
		return nombrecuentaenvio;
	}

	/**
	 * Modificar nombrecuentaenvio.
	 *
	 * @param nombrecuentaenvio            the nombrecuentaenvio to set
	 */
	public void setNombrecuentaenvio(String nombrecuentaenvio) {
		this.nombrecuentaenvio = nombrecuentaenvio;
	}

	/**
	 * Obtener historificacion.
	 *
	 * @return the historificacion
	 */
	public Integer getHistorificacion() {
		return historificacion;
	}

	/**
	 * Modificar historificacion.
	 *
	 * @param historificacion            the historificacion to set
	 */
	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	/**
	 * Obtener motivohistorificacion.
	 *
	 * @return the motivohistorificacion
	 */
	public String getMotivohistorificacion() {
		return motivohistorificacion;
	}

	/**
	 * Modificar motivohistorificacion.
	 *
	 * @param motivohistorificacion            the motivohistorificacion to set
	 */
	public void setMotivohistorificacion(String motivohistorificacion) {
		this.motivohistorificacion = motivohistorificacion;
	}

	/**
	 * Obtener conservacion.
	 *
	 * @return the conservacion
	 */
	public Integer getConservacion() {
		return conservacion;
	}

	/**
	 * Modificar conservacion.
	 *
	 * @param conservacion            the conservacion to set
	 */
	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * Obtener motivoconservacion.
	 *
	 * @return the motivoconservacion
	 */
	public String getMotivoconservacion() {
		return motivoconservacion;
	}

	/**
	 * Modificar motivoconservacion.
	 *
	 * @param motivoconservacion            the motivoconservacion to set
	 */
	public void setMotivoconservacion(String motivoconservacion) {
		this.motivoconservacion = motivoconservacion;
	}

	/**
	 * Obtener estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modificar estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtener nifcif.
	 *
	 * @return the nifcif
	 */
	public String getNifcif() {
		return nifcif;
	}

	/**
	 * Modificar nifcif.
	 *
	 * @param nifcif the nifcif to set
	 */
	public void setNifcif(String nifcif) {
		this.nifcif = nifcif;
	}

	/**
	 * Obtener siglas.
	 *
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * Modificar siglas.
	 *
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * Obtener nivel administracion.
	 *
	 * @return the nivelAdministracion
	 */
	public Integer getNivelAdministracion() {
		return nivelAdministracion;
	}

	/**
	 * Modificar nivel administracion.
	 *
	 * @param nivelAdministracion the nivelAdministracion to set
	 */
	public void setNivelAdministracion(Integer nivelAdministracion) {
		this.nivelAdministracion = nivelAdministracion;
	}

	/**
	 * Obtener nivel jerarquico.
	 *
	 * @return the nivelJerarquico
	 */
	public Integer getNivelJerarquico() {
		return nivelJerarquico;
	}

	/**
	 * Modificar nivel jerarquico.
	 *
	 * @param nivelJerarquico the nivelJerarquico to set
	 */
	public void setNivelJerarquico(Integer nivelJerarquico) {
		this.nivelJerarquico = nivelJerarquico;
	}

	/**
	 * Obtener cod unidad superior.
	 *
	 * @return the codUnidadSuperior
	 */
	public String getCodUnidadSuperior() {
		return codUnidadSuperior;
	}

	/**
	 * Modificar cod unidad superior.
	 *
	 * @param codUnidadSuperior the codUnidadSuperior to set
	 */
	public void setCodUnidadSuperior(String codUnidadSuperior) {
		this.codUnidadSuperior = codUnidadSuperior;
	}

	/**
	 * Obtener denom unidad superior.
	 *
	 * @return the denomUnidadSuperior
	 */
	public String getDenomUnidadSuperior() {
		return denomUnidadSuperior;
	}

	/**
	 * Modificar denom unidad superior.
	 *
	 * @param denomUnidadSuperior the denomUnidadSuperior to set
	 */
	public void setDenomUnidadSuperior(String denomUnidadSuperior) {
		this.denomUnidadSuperior = denomUnidadSuperior;
	}

	/**
	 * Obtener cod unidad raiz.
	 *
	 * @return the codUnidadRaiz
	 */
	public String getCodUnidadRaiz() {
		return codUnidadRaiz;
	}

	/**
	 * Modificar cod unidad raiz.
	 *
	 * @param codUnidadRaiz the codUnidadRaiz to set
	 */
	public void setCodUnidadRaiz(String codUnidadRaiz) {
		this.codUnidadRaiz = codUnidadRaiz;
	}

	/**
	 * Obtener denom unidad raiz.
	 *
	 * @return the denomUnidadRaiz
	 */
	public String getDenomUnidadRaiz() {
		return denomUnidadRaiz;
	}

	/**
	 * Modificar denom unidad raiz.
	 *
	 * @param denomUnidadRaiz the denomUnidadRaiz to set
	 */
	public void setDenomUnidadRaiz(String denomUnidadRaiz) {
		this.denomUnidadRaiz = denomUnidadRaiz;
	}

	/**
	 * Obtener es edp.
	 *
	 * @return the esEdp
	 */
	public String getEsEdp() {
		return esEdp;
	}

	/**
	 * Modificar es edp.
	 *
	 * @param esEdp the esEdp to set
	 */
	public void setEsEdp(String esEdp) {
		this.esEdp = esEdp;
	}

	/**
	 * Obtener cod edp principal.
	 *
	 * @return the codEdpPrincipal
	 */
	public String getCodEdpPrincipal() {
		return codEdpPrincipal;
	}

	/**
	 * Modificar cod edp principal.
	 *
	 * @param codEdpPrincipal the codEdpPrincipal to set
	 */
	public void setCodEdpPrincipal(String codEdpPrincipal) {
		this.codEdpPrincipal = codEdpPrincipal;
	}

	/**
	 * Obtener denom edp principal.
	 *
	 * @return the denomEdpPrincipal
	 */
	public String getDenomEdpPrincipal() {
		return denomEdpPrincipal;
	}

	/**
	 * Modificar denom edp principal.
	 *
	 * @param denomEdpPrincipal the denomEdpPrincipal to set
	 */
	public void setDenomEdpPrincipal(String denomEdpPrincipal) {
		this.denomEdpPrincipal = denomEdpPrincipal;
	}

	/**
	 * Obtener cod tipo ent public.
	 *
	 * @return the codTipoEntPublic
	 */
	public String getCodTipoEntPublic() {
		return codTipoEntPublic;
	}

	/**
	 * Modificar cod tipo ent public.
	 *
	 * @param codTipoEntPublic the codTipoEntPublic to set
	 */
	public void setCodTipoEntPublic(String codTipoEntPublic) {
		this.codTipoEntPublic = codTipoEntPublic;
	}

	/**
	 * Obtener cod tipo unidad.
	 *
	 * @return the codTipoUnidad
	 */
	public String getCodTipoUnidad() {
		return codTipoUnidad;
	}

	/**
	 * Modificar cod tipo unidad.
	 *
	 * @param codTipoUnidad the codTipoUnidad to set
	 */
	public void setCodTipoUnidad(String codTipoUnidad) {
		this.codTipoUnidad = codTipoUnidad;
	}

	/**
	 * Obtener cod amb territorial.
	 *
	 * @return the codAmbTerritorial
	 */
	public String getCodAmbTerritorial() {
		return codAmbTerritorial;
	}

	/**
	 * Modificar cod amb territorial.
	 *
	 * @param codAmbTerritorial the codAmbTerritorial to set
	 */
	public void setCodAmbTerritorial(String codAmbTerritorial) {
		this.codAmbTerritorial = codAmbTerritorial;
	}

	/**
	 * Obtener cod amb ent geografica.
	 *
	 * @return the codAmbEntGeografica
	 */
	public String getCodAmbEntGeografica() {
		return codAmbEntGeografica;
	}

	/**
	 * Modificar cod amb ent geografica.
	 *
	 * @param codAmbEntGeografica the codAmbEntGeografica to set
	 */
	public void setCodAmbEntGeografica(String codAmbEntGeografica) {
		this.codAmbEntGeografica = codAmbEntGeografica;
	}

	/**
	 * Obtener cod amb pais.
	 *
	 * @return the codAmbPais
	 */
	public String getCodAmbPais() {
		return codAmbPais;
	}

	/**
	 * Modificar cod amb pais.
	 *
	 * @param codAmbPais the codAmbPais to set
	 */
	public void setCodAmbPais(String codAmbPais) {
		this.codAmbPais = codAmbPais;
	}

	/**
	 * Obtener cod amb comunidad.
	 *
	 * @return the codAmbComunidad
	 */
	public String getCodAmbComunidad() {
		return codAmbComunidad;
	}

	/**
	 * Modificar cod amb comunidad.
	 *
	 * @param codAmbComunidad the codAmbComunidad to set
	 */
	public void setCodAmbComunidad(String codAmbComunidad) {
		this.codAmbComunidad = codAmbComunidad;
	}

	/**
	 * Obtener cod amb provincia.
	 *
	 * @return the codAmbProvincia
	 */
	public String getCodAmbProvincia() {
		return codAmbProvincia;
	}

	/**
	 * Modificar cod amb provincia.
	 *
	 * @param codAmbProvincia the codAmbProvincia to set
	 */
	public void setCodAmbProvincia(String codAmbProvincia) {
		this.codAmbProvincia = codAmbProvincia;
	}

	/**
	 * Obtener cod amb municipio.
	 *
	 * @return the codAmbMunicipio
	 */
	public String getCodAmbMunicipio() {
		return codAmbMunicipio;
	}

	/**
	 * Modificar cod amb municipio.
	 *
	 * @param codAmbMunicipio the codAmbMunicipio to set
	 */
	public void setCodAmbMunicipio(String codAmbMunicipio) {
		this.codAmbMunicipio = codAmbMunicipio;
	}

	/**
	 * Obtener cod amb isla.
	 *
	 * @return the codAmbIsla
	 */
	public String getCodAmbIsla() {
		return codAmbIsla;
	}

	/**
	 * Modificar cod amb isla.
	 *
	 * @param codAmbIsla the codAmbIsla to set
	 */
	public void setCodAmbIsla(String codAmbIsla) {
		this.codAmbIsla = codAmbIsla;
	}

	/**
	 * Obtener cod amb elm.
	 *
	 * @return the codAmbElm
	 */
	public String getCodAmbElm() {
		return codAmbElm;
	}

	/**
	 * Modificar cod amb elm.
	 *
	 * @param codAmbElm the codAmbElm to set
	 */
	public void setCodAmbElm(String codAmbElm) {
		this.codAmbElm = codAmbElm;
	}

	/**
	 * Obtener cod amb loc extranjera.
	 *
	 * @return the codAmbLocExtranjera
	 */
	public String getCodAmbLocExtranjera() {
		return codAmbLocExtranjera;
	}

	/**
	 * Modificar cod amb loc extranjera.
	 *
	 * @param codAmbLocExtranjera the codAmbLocExtranjera to set
	 */
	public void setCodAmbLocExtranjera(String codAmbLocExtranjera) {
		this.codAmbLocExtranjera = codAmbLocExtranjera;
	}

	/**
	 * Obtener competencias.
	 *
	 * @return the competencias
	 */
	public String getCompetencias() {
		return competencias;
	}

	/**
	 * Modificar competencias.
	 *
	 * @param competencias the competencias to set
	 */
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	/**
	 * Obtener disposicion legal.
	 *
	 * @return the disposicionLegal
	 */
	public String getDisposicionLegal() {
		return disposicionLegal;
	}

	/**
	 * Modificar disposicion legal.
	 *
	 * @param disposicionLegal the disposicionLegal to set
	 */
	public void setDisposicionLegal(String disposicionLegal) {
		this.disposicionLegal = disposicionLegal;
	}

	/**
	 * Obtener fecha alta oficial.
	 *
	 * @return the fechaAltaOficial
	 */
	public Date getFechaAltaOficial() {
		return fechaAltaOficial;
	}

	/**
	 * Modificar fecha alta oficial.
	 *
	 * @param fechaAltaOficial the fechaAltaOficial to set
	 */
	public void setFechaAltaOficial(Date fechaAltaOficial) {
		this.fechaAltaOficial = fechaAltaOficial;
	}

	/**
	 * Obtener fecha baja oficial.
	 *
	 * @return the fechaBajaOficial
	 */
	public Date getFechaBajaOficial() {
		return fechaBajaOficial;
	}

	/**
	 * Modificar fecha baja oficial.
	 *
	 * @param fechaBajaOficial the fechaBajaOficial to set
	 */
	public void setFechaBajaOficial(Date fechaBajaOficial) {
		this.fechaBajaOficial = fechaBajaOficial;
	}

	/**
	 * Obtener fecha extincion.
	 *
	 * @return the fechaExtincion
	 */
	public Date getFechaExtincion() {
		return fechaExtincion;
	}

	/**
	 * Modificar fecha extincion.
	 *
	 * @param fechaExtincion the fechaExtincion to set
	 */
	public void setFechaExtincion(Date fechaExtincion) {
		this.fechaExtincion = fechaExtincion;
	}

	/**
	 * Obtener fecha anulacion.
	 *
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * Modificar fecha anulacion.
	 *
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	/**
	 * Obtener observ generales.
	 *
	 * @return the observGenerales
	 */
	public String getObservGenerales() {
		return observGenerales;
	}

	/**
	 * Modificar observ generales.
	 *
	 * @param observGenerales the observGenerales to set
	 */
	public void setObservGenerales(String observGenerales) {
		this.observGenerales = observGenerales;
	}

	/**
	 * Obtener observ baja.
	 *
	 * @return the observBaja
	 */
	public String getObservBaja() {
		return observBaja;
	}

	/**
	 * Modificar observ baja.
	 *
	 * @param observBaja the observBaja to set
	 */
	public void setObservBaja(String observBaja) {
		this.observBaja = observBaja;
	}

	/**
	 * Obtener tipo via.
	 *
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * Modificar tipo via.
	 *
	 * @param tipoVia the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * Obtener nombre via.
	 *
	 * @return the nombreVia
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * Modificar nombre via.
	 *
	 * @param nombreVia the nombreVia to set
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * Obtener num via.
	 *
	 * @return the numVia
	 */
	public String getNumVia() {
		return numVia;
	}

	/**
	 * Modificar num via.
	 *
	 * @param numVia the numVia to set
	 */
	public void setNumVia(String numVia) {
		this.numVia = numVia;
	}

	/**
	 * Obtener complemento.
	 *
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Modificar complemento.
	 *
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Obtener cod postal.
	 *
	 * @return the codPostal
	 */
	public String getCodPostal() {
		return codPostal;
	}

	/**
	 * Modificar cod postal.
	 *
	 * @param codPostal the codPostal to set
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	/**
	 * Obtener cod pais.
	 *
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * Modificar cod pais.
	 *
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * Obtener cod comunidad.
	 *
	 * @return the codComunidad
	 */
	public String getCodComunidad() {
		return codComunidad;
	}

	/**
	 * Modificar cod comunidad.
	 *
	 * @param codComunidad the codComunidad to set
	 */
	public void setCodComunidad(String codComunidad) {
		this.codComunidad = codComunidad;
	}

	/**
	 * Obtener cod provincia.
	 *
	 * @return the codProvincia
	 */
	public String getCodProvincia() {
		return codProvincia;
	}

	/**
	 * Modificar cod provincia.
	 *
	 * @param codProvincia the codProvincia to set
	 */
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	/**
	 * Obtener cod localidad.
	 *
	 * @return the codLocalidad
	 */
	public String getCodLocalidad() {
		return codLocalidad;
	}

	/**
	 * Modificar cod localidad.
	 *
	 * @param codLocalidad the codLocalidad to set
	 */
	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	/**
	 * Obtener cod ent geografica.
	 *
	 * @return the codEntGeografica
	 */
	public String getCodEntGeografica() {
		return codEntGeografica;
	}

	/**
	 * Modificar cod ent geografica.
	 *
	 * @param codEntGeografica the codEntGeografica to set
	 */
	public void setCodEntGeografica(String codEntGeografica) {
		this.codEntGeografica = codEntGeografica;
	}

	/**
	 * Obtener dir extranjera.
	 *
	 * @return the dirExtranjera
	 */
	public String getDirExtranjera() {
		return dirExtranjera;
	}

	/**
	 * Modificar dir extranjera.
	 *
	 * @param dirExtranjera the dirExtranjera to set
	 */
	public void setDirExtranjera(String dirExtranjera) {
		this.dirExtranjera = dirExtranjera;
	}

	/**
	 * Obtener loc extranjera.
	 *
	 * @return the locExtranjera
	 */
	public String getLocExtranjera() {
		return locExtranjera;
	}

	/**
	 * Modificar loc extranjera.
	 *
	 * @param locExtranjera the locExtranjera to set
	 */
	public void setLocExtranjera(String locExtranjera) {
		this.locExtranjera = locExtranjera;
	}

	/**
	 * Obtener observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Modificar observaciones.
	 *
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Obtener asociados servicio.
	 *
	 * @return the asociadosServicio
	 */
	public Boolean getAsociadosServicio() {
		return asociadosServicio;
	}

	/**
	 * Modificar asociados servicio.
	 *
	 * @param asociadosServicio the asociadosServicio to set
	 */
	public void setAsociadosServicio(Boolean asociadosServicio) {
		this.asociadosServicio = asociadosServicio;
	}

	/**
	 * Obtener fecha estado.
	 *
	 * @return the fechaEstado
	 */
	public Date getFechaEstado() {
		if ("V".equals(estado)){
			return (null != fechaAltaOficial ) ? DateUtils.truncate(fechaAltaOficial, Calendar.DATE) : fechacreacion;
		}else if ("T".equals(estado)){
			return (null != fechaBajaOficial ) ? DateUtils.truncate(fechaBajaOficial, Calendar.DATE) : fechacreacion;
		}else if ("A".equals(estado)){
			return (null != fechaAnulacion ) ? DateUtils.truncate(fechaAnulacion, Calendar.DATE) : fechacreacion;
		}else if ("E".equals(estado)){
			return (null != fechaExtincion ) ? DateUtils.truncate(fechaExtincion, Calendar.DATE) : fechacreacion;
		}else {
			return fechamodificacion;
		}
	}

	/**
	 * Modificar fecha estado.
	 *
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * Obtener fecha estado str.
	 *
	 * @return the fechaEstadoStr
	 */
	public String getFechaEstadoStr() {
		return fechaEstadoStr;
	}

	/**
	 * Modificar fecha estado str.
	 *
	 * @param fechaEstadoStr the fechaEstadoStr to set
	 */
	public void setFechaEstadoStr(String fechaEstadoStr) {
		this.fechaEstadoStr = fechaEstadoStr;
	}

	/**
	 * Obtener manual.
	 *
	 * @return the manual
	 */
	public Boolean getManual() {
		return manual;
	}

	/**
	 * Modificar manual.
	 *
	 * @param manual the manual to set
	 */
	public void setManual(Boolean manual) {
		this.manual = manual;
	}

	/**
	 * Obtener fecha creacion desde.
	 *
	 * @return the fechaCreacionDesde
	 */
	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}

	/**
	 * Modificar fecha creacion desde.
	 *
	 * @param fechaCreacionDesde the fechaCreacionDesde to set
	 */
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}

	/**
	 * Obtener fecha creacion hasta.
	 *
	 * @return the fechaCreacionHasta
	 */
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}

	/**
	 * Modificar fecha creacion hasta.
	 *
	 * @param fechaCreacionHasta the fechaCreacionHasta to set
	 */
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}


	/**
	 * Obtener fecha activo.
	 *
	 * @return the fechaActivo
	 */
	public Date getFechaActivo() {
		return fechaActivo;
	}

	/**
	 * Modificar fecha activo.
	 *
	 * @param fechaActivo the fechaActivo to set
	 */	
	public void setFechaActivo(Date fechaActivo) {
		this.fechaActivo = fechaActivo;
	}

}
