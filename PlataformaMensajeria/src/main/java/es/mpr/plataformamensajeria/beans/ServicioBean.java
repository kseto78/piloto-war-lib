package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un servicio para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class ServicioBean implements Audit {

	public ServicioBean() {
		super();
		this.servicioId = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.canalid = null;
		this.aplicacionid = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.externalid = null;
		this.nmaxenvios = null;
		this.canalnombre = null;
		this.aplicacionnombre = null;
		this.historificacion = null;
		this.motivohistorificacion = null;
		this.conservacion = null;
		this.motivoconservacion = null;
		this.pendienteaprobacion = null;
		this.nombreloteenvio = null;
		this.badge = null;
		this.gcmprojectkey = null;
		this.apnsrutacertificado = null;
		this.apnspasswordcertificado = null;
		this.androidplataforma = null;
		this.iosplataforma = null;
		this.isAndroidPlataforma = null;
		this.isIosPlataforma = null;
		this.endpoint = null;
		this.informesactivo = null;
		this.isInformesActivo = null;
		this.agrupacionestado = null;
		this.isAgrupacionEstado = null;
		this.agrupacioncodorg = null;
		this.isAgrupacionCodOrg = null;
		this.agrupacioncodsia = null;
		this.isAgrupacionCodSia = null;
		this.agrupacioncodorgpagador = null;
		this.isAgrupacionCodOrgPagador = null;
		this.informesdestinatarios = null;

		this.responsablefuncionalnombre = null;
		this.responsablefuncionalemail = null;
		this.responsabletecniconombre = null;
		this.responsabletecnicoemail = null;
		this.multiorganismo = null;
		this.isMultiorganismo = null;
		this.premium = null;
		this.numeroMaxReenvios = null;
		this.eliminado = null;
	}

	protected Integer servicioId;
	protected String nombre = null;
	protected String descripcion = null;
	protected Boolean activo = null;
	protected Integer canalid = null;
	protected Integer aplicacionid = null;
	protected Date fechacreacion = null;
	protected String creadopor = null;
	protected Date fechamodificacion = null;
	protected String modificadopor = null;
	protected String externalid = null;
	protected Integer nmaxenvios = null;
	protected String isActivo = null;
	protected String canalnombre = null;
	protected String aplicacionnombre = null;
	protected String frommail = null;
	protected String frommailname = null;
	protected Integer historificacion = null;
	protected Integer historificacionInput = null;
	protected String motivohistorificacion = null;
	protected Integer conservacion = null;
	protected Integer conservacionInput = null;
	protected String motivoconservacion = null;
	protected Boolean pendienteaprobacion = null;
	protected String isPendienteAprobacion = null;
	protected String nombreloteenvio = null;
	protected Integer badge = null;
	protected String gcmprojectkey = null;
	protected String apnsrutacertificado = null;
	protected String apnspasswordcertificado = null;
	protected Boolean androidplataforma = null;
	protected Boolean iosplataforma = null;
	protected String isAndroidPlataforma = null;
	protected String isIosPlataforma = null;
	protected String endpoint = null;
	protected Boolean informesactivo = null;
	protected String isInformesActivo = null;
	protected Boolean agrupacionestado = null;
	protected String isAgrupacionEstado = null;
	protected Boolean agrupacioncodorg = null;
	protected String isAgrupacionCodOrg = null;
	protected Boolean agrupacioncodsia = null;
	protected String isAgrupacionCodSia = null;
	protected Boolean agrupacioncodorgpagador = null;
	protected String isAgrupacionCodOrgPagador = null;
	protected String informesdestinatarios = null;

	protected String responsablefuncionalnombre = null;
	protected String responsablefuncionalemail = null;
	protected String responsabletecniconombre = null;
	protected String responsabletecnicoemail = null;
	protected Boolean multiorganismo = null;
	protected String isMultiorganismo = null;
	protected Boolean premium = null;
	protected String isPremium = null;
	protected Integer numeroMaxReenvios = null;
	protected String eliminado = null;

	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else {
			return "false";
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
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	public Object getId() {
		return servicioId;
	}

	public void setId(Object servicioId) {
		this.servicioId = (Integer) servicioId;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIsPendienteAprobacion() {
		if (pendienteaprobacion != null && pendienteaprobacion) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}
	}

	public void setIsPendienteAprobacion(String isPendienteAprobacion) {
		if (isPendienteAprobacion != null && isPendienteAprobacion.equals("true")) {
			this.pendienteaprobacion = true;
		} else {
			this.pendienteaprobacion = false;
		}
		this.isPendienteAprobacion = isPendienteAprobacion; // TODO
	}

	public String getIsAndroidPlataforma() {
		if (this.androidplataforma != null && this.androidplataforma) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsAndroidPlataforma(String androidPlataforma) {
		this.isAndroidPlataforma = androidPlataforma;
		if (androidPlataforma != null && androidPlataforma.equals("true")) {
			this.androidplataforma = true;
		} else {
			this.androidplataforma = false;
		}

	}

	public String getIsIosPlataforma() {
		if (this.iosplataforma != null && this.iosplataforma) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsIosPlataforma(String iosPlataforma) {
		this.isIosPlataforma = iosPlataforma;
		if (iosPlataforma != null && iosPlataforma.equals("true")) {
			this.iosplataforma = true;
		} else {
			this.iosplataforma = false;
		}
	}

	public void setInformesActivado(String informesActivado) {
		if (informesActivado != null && informesActivado.equals("true")) {
			this.informesactivo = true;
		} else {
			this.informesactivo = false;
		}
	}

	public String getInformesActivado() {
		if (informesactivo != null && informesactivo) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsInformesActivo() {
		if (informesactivo != null && informesactivo) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsInformesActivo(String isInformesActivo) {
		if (isInformesActivo != null && isInformesActivo.equals("true")) {
			this.informesactivo = true;
		} else {
			this.informesactivo = false;
		}
		this.isInformesActivo = isInformesActivo;
	}

	public void setAgrupacionEstadoActivado(String agrupacionEstadoActivado) {
		if (agrupacionEstadoActivado != null && agrupacionEstadoActivado.equals("true")) {
			this.agrupacionestado = true;
		} else {
			this.agrupacionestado = false;
		}
		this.isAgrupacionEstado = isInformesActivo;
	}

	public String getAgrupacionEstadoActivado() {
		if (agrupacionestado != null && agrupacionestado) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsAgrupacionEstado() {
		if (agrupacionestado != null && agrupacionestado) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsAgrupacionEstado(String isAgrupacionEstado) {
		if (isAgrupacionEstado != null && isAgrupacionEstado.equals("true")) {
			this.agrupacionestado = true;
		} else {
			this.agrupacionestado = false;
		}
		this.isAgrupacionEstado = isAgrupacionEstado;
	}

	public void setAgrupacionCodOrgActivado(String agrupacionCodOrgActivado) {
		if (agrupacionCodOrgActivado != null && agrupacionCodOrgActivado.equals("true")) {
			this.agrupacioncodorg = true;
		} else {
			this.agrupacioncodorg = false;
		}
	}

	public String getgrupacionCodOrgActivado() {
		if (agrupacioncodorg != null && agrupacioncodorg) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsAgrupacionCodOrg() {
		if (agrupacioncodorg != null && agrupacioncodorg) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsAgrupacionCodOrg(String isAgrupacionCodOrg) {
		if (isAgrupacionCodOrg != null && isAgrupacionCodOrg.equals("true")) {
			this.agrupacioncodorg = true;
		} else {
			this.agrupacioncodorg = false;
		}
		this.isAgrupacionCodOrg = isAgrupacionCodOrg;
	}

	public void setAgrupacionCodSiaActivado(String agrupacionCodSiaActivado) {
		if (agrupacionCodSiaActivado != null && agrupacionCodSiaActivado.equals("true")) {
			this.agrupacioncodsia = true;
		} else {
			this.agrupacioncodsia = false;
		}
	}

	public String getAgrupacionCodOrgActivado() {
		if (agrupacioncodsia != null && agrupacioncodsia) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsAgrupacionCodSia() {
		if (agrupacioncodsia != null && agrupacioncodsia) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsAgrupacionCodSia(String isAgrupacionCodSia) {
		if (isAgrupacionCodSia != null && isAgrupacionCodSia.equals("true")) {
			this.agrupacioncodsia = true;
		} else {
			this.agrupacioncodsia = false;
		}
		this.isAgrupacionCodSia = isAgrupacionCodSia;
	}

	public void setAgrupacionCodOrgPagadorActivado(String agrupacionCodOrgPagadorActivado) {
		if (agrupacionCodOrgPagadorActivado != null && agrupacionCodOrgPagadorActivado.equals("true")) {
			this.agrupacioncodorgpagador = true;
		} else {
			this.agrupacioncodorgpagador = false;
		}
	}

	public String getAgrupacionCodOrgPagadorActivado() {
		if (agrupacioncodorgpagador != null && agrupacioncodorgpagador) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsAgrupacionCodOrgPagador() {
		if (agrupacioncodorgpagador != null && agrupacioncodorgpagador) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsAgrupacionCodOrgPagador(String isAgrupacionCodOrgPagador) {
		if (isAgrupacionCodOrgPagador != null && isAgrupacionCodOrgPagador.equals("true")) {
			this.agrupacioncodorgpagador = true;
		} else {
			this.agrupacioncodorgpagador = false;
		}
		this.isAgrupacionCodOrgPagador = isAgrupacionCodOrgPagador;
	}

	public String getIsMultiorganismo() {
		if (multiorganismo != null && multiorganismo) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsMultiorganismo(String isMultiorganismo) {
		if (isMultiorganismo != null && isMultiorganismo.equals("true")) {
			this.multiorganismo = true;
		} else {
			this.multiorganismo = false;
		}
		this.isMultiorganismo = isMultiorganismo;
	}

	public String getIsPremium() {
		if (premium != null && premium) {
			return "true";
		} else {
			return "false";
		}
	}

	public void setIsPremium(String isPremium) {
		if (isPremium != null && isPremium.equals("true")) {
			this.premium = true;
		} else {
			this.premium = false;
		}
		this.isPremium = isPremium;
	}

	/**
	 * @return the servicioId
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * @param servicioId
	 *            the servicioId to set
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
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
	 * @return the canalid
	 */
	public Integer getCanalid() {
		return canalid;
	}

	/**
	 * @param canalid
	 *            the canalid to set
	 */
	public void setCanalid(Integer canalid) {
		this.canalid = canalid;
	}

	/**
	 * @return the aplicacionid
	 */
	public Integer getAplicacionid() {
		return aplicacionid;
	}

	/**
	 * @param aplicacionid
	 *            the aplicacionid to set
	 */
	public void setAplicacionid(Integer aplicacionid) {
		this.aplicacionid = aplicacionid;
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
	 * @return the canalnombre
	 */
	public String getCanalnombre() {
		return canalnombre;
	}

	/**
	 * @param canalnombre
	 *            the canalnombre to set
	 */
	public void setCanalnombre(String canalnombre) {
		this.canalnombre = canalnombre;
	}

	/**
	 * @return the aplicacionnombre
	 */
	public String getAplicacionnombre() {
		return aplicacionnombre;
	}

	/**
	 * @param aplicacionnombre
	 *            the aplicacionnombre to set
	 */
	public void setAplicacionnombre(String aplicacionnombre) {
		this.aplicacionnombre = aplicacionnombre;
	}

	/**
	 * @return the frommail
	 */
	public String getFrommail() {
		return frommail;
	}

	/**
	 * @param frommail
	 *            the frommail to set
	 */
	public void setFrommail(String frommail) {
		this.frommail = frommail;
	}

	/**
	 * @return the frommailname
	 */
	public String getFrommailname() {
		return frommailname;
	}

	/**
	 * @param frommailname
	 *            the frommailname to set
	 */
	public void setFrommailname(String frommailname) {
		this.frommailname = frommailname;
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
	 * @return the historificacionInput
	 */
	public Integer getHistorificacionInput() {
		return historificacionInput;
	}

	/**
	 * @param historificacionInput
	 *            the historificacionInput to set
	 */
	public void setHistorificacionInput(Integer historificacionInput) {
		this.historificacionInput = historificacionInput;
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
	 * @return the conservacionInput
	 */
	public Integer getConservacionInput() {
		return conservacionInput;
	}

	/**
	 * @param conservacionInput
	 *            the conservacionInput to set
	 */
	public void setConservacionInput(Integer conservacionInput) {
		this.conservacionInput = conservacionInput;
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
	public Integer getBadge() {
		return badge;
	}

	/**
	 * @param badge
	 *            the badge to set
	 */
	public void setBadge(Integer badge) {
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
	 * @return the responsablefuncionalnombre
	 */
	public String getResponsablefuncionalnombre() {
		return responsablefuncionalnombre;
	}

	/**
	 * @param responsablefuncionalnombre
	 *            the responsablefuncionalnombre to set
	 */
	public void setResponsablefuncionalnombre(String responsablefuncionalnombre) {
		this.responsablefuncionalnombre = responsablefuncionalnombre;
	}

	/**
	 * @return the responsablefuncionalemail
	 */
	public String getResponsablefuncionalemail() {
		return responsablefuncionalemail;
	}

	/**
	 * @param responsablefuncionalemail
	 *            the responsablefuncionalemail to set
	 */
	public void setResponsablefuncionalemail(String responsablefuncionalemail) {
		this.responsablefuncionalemail = responsablefuncionalemail;
	}

	/**
	 * @return the responsabletecniconombre
	 */
	public String getResponsabletecniconombre() {
		return responsabletecniconombre;
	}

	/**
	 * @param responsabletecniconombre
	 *            the responsabletecniconombre to set
	 */
	public void setResponsabletecniconombre(String responsabletecniconombre) {
		this.responsabletecniconombre = responsabletecniconombre;
	}

	/**
	 * @return the responsabletecnicoemail
	 */
	public String getResponsabletecnicoemail() {
		return responsabletecnicoemail;
	}

	/**
	 * @param responsabletecnicoemail
	 *            the responsabletecnicoemail to set
	 */
	public void setResponsabletecnicoemail(String responsabletecnicoemail) {
		this.responsabletecnicoemail = responsabletecnicoemail;
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
	
	public Integer getNumeroMaxReenvios() {
		return numeroMaxReenvios;
	}

	public void setNumeroMaxReenvios(Integer numeroMaxReenvios) {
		this.numeroMaxReenvios = numeroMaxReenvios;
	}

	/**
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

}
