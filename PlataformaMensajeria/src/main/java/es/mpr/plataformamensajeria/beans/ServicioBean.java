package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un servicio para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class ServicioBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7986269951829415111L;

	/**
	 * Constructor de servicio bean.
	 */
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
		this.fcmprojectkey = null;
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
		this.exclusivo = null;
		this.smsExtranjeros= null;
		this.numeroMaxReenvios = null;
		this.eliminado = null;
		this.caducidadWebPush = null;
		this.vapidPublicKey = null;
		this.vapidPrivateKey = null;
	}

	/**  servicio id. */
	protected Integer servicioId;
	
	/**  nombre. */
	protected String nombre = null;
	
	/**  descripcion. */
	protected String descripcion = null;
	
	/**  activo. */
	protected Boolean activo = null;
	
	/**  canalid. */
	protected Integer canalid = null;
	
	/**  aplicacionid. */
	protected Integer aplicacionid = null;
	
	/**  fechacreacion. */
	protected Date fechacreacion = null;
	
	/**  creadopor. */
	protected String creadopor = null;
	
	/**  fechamodificacion. */
	protected Date fechamodificacion = null;
	
	/**  modificadopor. */
	protected String modificadopor = null;
	
	/**  externalid. */
	protected String externalid = null;
	
	/**  nmaxenvios. */
	protected Integer nmaxenvios = null;
	
	/**  is activo. */
	protected String isActivo = null;
	
	/**  canalnombre. */
	protected String canalnombre = null;
	
	/**  aplicacionnombre. */
	protected String aplicacionnombre = null;
	
	/**  frommail. */
	protected String frommail = null;
	
	/**  frommailname. */
	protected String frommailname = null;
	
	/**  historificacion. */
	protected Integer historificacion = null;
	
	/**  historificacion input. */
	protected Integer historificacionInput = null;
	
	/**  motivohistorificacion. */
	protected String motivohistorificacion = null;
	
	/**  conservacion. */
	protected Integer conservacion = null;
	
	/**  conservacion input. */
	protected Integer conservacionInput = null;
	
	/**  motivoconservacion. */
	protected String motivoconservacion = null;
	
	/**  pendienteaprobacion. */
	protected Boolean pendienteaprobacion = null;
	
	/**  is pendiente aprobacion. */
	protected String isPendienteAprobacion = null;
	
	/**  nombreloteenvio. */
	protected String nombreloteenvio = null;
	
	/**  badge. */
	protected Integer badge = null;
	
	/**  fcmprojectkey. */
	protected String fcmprojectkey = null;
	
	/**  apnsrutacertificado. */
	protected String apnsrutacertificado = null;
	
	/**  apnspasswordcertificado. */
	protected String apnspasswordcertificado = null;
	
	/**  androidplataforma. */
	protected Boolean androidplataforma = null;
	
	/**  iosplataforma. */
	protected Boolean iosplataforma = null;
	
	/**  is android plataforma. */
	protected String isAndroidPlataforma = null;
	
	/**  is ios plataforma. */
	protected String isIosPlataforma = null;
	
	/**  endpoint. */
	protected String endpoint = null;
	
	/**  informesactivo. */
	protected Boolean informesactivo = null;
	
	/**  is informes activo. */
	protected String isInformesActivo = null;
	
	/**  agrupacionestado. */
	protected Boolean agrupacionestado = null;
	
	/**  is agrupacion estado. */
	protected String isAgrupacionEstado = null;
	
	/**  agrupacioncodorg. */
	protected Boolean agrupacioncodorg = null;
	
	/**  is agrupacion cod org. */
	protected String isAgrupacionCodOrg = null;
	
	/**  agrupacioncodsia. */
	protected Boolean agrupacioncodsia = null;
	
	/**  is agrupacion cod sia. */
	protected String isAgrupacionCodSia = null;
	
	/**  agrupacioncodorgpagador. */
	protected Boolean agrupacioncodorgpagador = null;
	
	/**  is agrupacion cod org pagador. */
	protected String isAgrupacionCodOrgPagador = null;
	
	/**  informesdestinatarios. */
	protected String informesdestinatarios = null;

	/**  responsablefuncionalnombre. */
	protected String responsablefuncionalnombre = null;
	
	/**  responsablefuncionalemail. */
	protected String responsablefuncionalemail = null;
	
	/**  responsabletecniconombre. */
	protected String responsabletecniconombre = null;
	
	/**  responsabletecnicoemail. */
	protected String responsabletecnicoemail = null;
	
	/**  multiorganismo. */
	protected Boolean multiorganismo = null;
	
	/**  is multiorganismo. */
	protected String isMultiorganismo = null;
	
	/**  premium. */
	protected Boolean premium = null;
	
	/**  is premium. */
	protected String isPremium = null;
	
	/**  exclusivo. */
	protected Boolean exclusivo = null;
	
	/**  is exclusivo. */
	protected String isExclusivo = null;
	
	/**  smsExtranjeros. */
	protected Boolean smsExtranjeros = null;
	
	/**  is smsExtranjeros. */
	protected String isSmsExtranjeros = null;
	
	/**  numero max reenvios. */
	protected Integer numeroMaxReenvios = null;
	
	/**  eliminado. */
	protected String eliminado = null;
	
	/**  caducidad web push. */
	protected Integer caducidadWebPush = null;
	
	/**  vapid public key. */
	protected String vapidPublicKey = null;
	
	/**  vapid private key. */
	protected String vapidPrivateKey = null;
	
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
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return servicioId;
	}

	/**
	 * Modificar id.
	 *
	 * @param servicioId new id
	 */
	public void setId(Object servicioId) {
		this.servicioId = (Integer) servicioId;
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
	 * Obtener checks if is pendiente aprobacion.
	 *
	 * @return checks if is pendiente aprobacion
	 */
	public String getIsPendienteAprobacion() {
		if (pendienteaprobacion != null && pendienteaprobacion) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}
	}

	/**
	 * Modificar checks if is pendiente aprobacion.
	 *
	 * @param isPendienteAprobacion new checks if is pendiente aprobacion
	 */
	public void setIsPendienteAprobacion(String isPendienteAprobacion) {
		if (isPendienteAprobacion != null && isPendienteAprobacion.equals("true")) {
			this.pendienteaprobacion = true;
		} else {
			this.pendienteaprobacion = false;
		}
		this.isPendienteAprobacion = isPendienteAprobacion; // TODO
	}

	/**
	 * Obtener checks if is android plataforma.
	 *
	 * @return checks if is android plataforma
	 */
	public String getIsAndroidPlataforma() {
		if (this.androidplataforma != null && this.androidplataforma) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is android plataforma.
	 *
	 * @param androidPlataforma new checks if is android plataforma
	 */
	public void setIsAndroidPlataforma(String androidPlataforma) {
		this.isAndroidPlataforma = androidPlataforma;
		if (androidPlataforma != null && androidPlataforma.equals("true")) {
			this.androidplataforma = true;
		} else {
			this.androidplataforma = false;
		}

	}

	/**
	 * Obtener checks if is ios plataforma.
	 *
	 * @return checks if is ios plataforma
	 */
	public String getIsIosPlataforma() {
		if (this.iosplataforma != null && this.iosplataforma) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is ios plataforma.
	 *
	 * @param iosPlataforma new checks if is ios plataforma
	 */
	public void setIsIosPlataforma(String iosPlataforma) {
		this.isIosPlataforma = iosPlataforma;
		if (iosPlataforma != null && iosPlataforma.equals("true")) {
			this.iosplataforma = true;
		} else {
			this.iosplataforma = false;
		}
	}

	/**
	 * Modificar informes activado.
	 *
	 * @param informesActivado new informes activado
	 */
	public void setInformesActivado(String informesActivado) {
		if (informesActivado != null && informesActivado.equals("true")) {
			this.informesactivo = true;
		} else {
			this.informesactivo = false;
		}
	}

	/**
	 * Obtener informes activado.
	 *
	 * @return informes activado
	 */
	public String getInformesActivado() {
		if (informesactivo != null && informesactivo) {
			return "true";
		} else {
			return "false";
		}

	}

	/**
	 * Obtener checks if is informes activo.
	 *
	 * @return checks if is informes activo
	 */
	public String getIsInformesActivo() {
		if (informesactivo != null && informesactivo) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is informes activo.
	 *
	 * @param isInformesActivo new checks if is informes activo
	 */
	public void setIsInformesActivo(String isInformesActivo) {
		if (isInformesActivo != null && isInformesActivo.equals("true")) {
			this.informesactivo = true;
		} else {
			this.informesactivo = false;
		}
		this.isInformesActivo = isInformesActivo;
	}

	/**
	 * Modificar agrupacion estado activado.
	 *
	 * @param agrupacionEstadoActivado new agrupacion estado activado
	 */
	public void setAgrupacionEstadoActivado(String agrupacionEstadoActivado) {
		if (agrupacionEstadoActivado != null && agrupacionEstadoActivado.equals("true")) {
			this.agrupacionestado = true;
		} else {
			this.agrupacionestado = false;
		}
		this.isAgrupacionEstado = isInformesActivo;
	}

	/**
	 * Obtener agrupacion estado activado.
	 *
	 * @return agrupacion estado activado
	 */
	public String getAgrupacionEstadoActivado() {
		if (agrupacionestado != null && agrupacionestado) {
			return "true";
		} else {
			return "false";
		}

	}

	/**
	 * Obtener checks if is agrupacion estado.
	 *
	 * @return checks if is agrupacion estado
	 */
	public String getIsAgrupacionEstado() {
		if (agrupacionestado != null && agrupacionestado) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is agrupacion estado.
	 *
	 * @param isAgrupacionEstado new checks if is agrupacion estado
	 */
	public void setIsAgrupacionEstado(String isAgrupacionEstado) {
		if (isAgrupacionEstado != null && isAgrupacionEstado.equals("true")) {
			this.agrupacionestado = true;
		} else {
			this.agrupacionestado = false;
		}
		this.isAgrupacionEstado = isAgrupacionEstado;
	}

	/**
	 * Modificar agrupacion cod org activado.
	 *
	 * @param agrupacionCodOrgActivado new agrupacion cod org activado
	 */
	public void setAgrupacionCodOrgActivado(String agrupacionCodOrgActivado) {
		if (agrupacionCodOrgActivado != null && agrupacionCodOrgActivado.equals("true")) {
			this.agrupacioncodorg = true;
		} else {
			this.agrupacioncodorg = false;
		}
	}

	/**
	 * Obtener grupacion cod org activado.
	 *
	 * @return grupacion cod org activado
	 */
	public String getgrupacionCodOrgActivado() {
		if (agrupacioncodorg != null && agrupacioncodorg) {
			return "true";
		} else {
			return "false";
		}

	}

	/**
	 * Obtener checks if is agrupacion cod org.
	 *
	 * @return checks if is agrupacion cod org
	 */
	public String getIsAgrupacionCodOrg() {
		if (agrupacioncodorg != null && agrupacioncodorg) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is agrupacion cod org.
	 *
	 * @param isAgrupacionCodOrg new checks if is agrupacion cod org
	 */
	public void setIsAgrupacionCodOrg(String isAgrupacionCodOrg) {
		if (isAgrupacionCodOrg != null && isAgrupacionCodOrg.equals("true")) {
			this.agrupacioncodorg = true;
		} else {
			this.agrupacioncodorg = false;
		}
		this.isAgrupacionCodOrg = isAgrupacionCodOrg;
	}

	/**
	 * Modificar agrupacion cod sia activado.
	 *
	 * @param agrupacionCodSiaActivado new agrupacion cod sia activado
	 */
	public void setAgrupacionCodSiaActivado(String agrupacionCodSiaActivado) {
		if (agrupacionCodSiaActivado != null && agrupacionCodSiaActivado.equals("true")) {
			this.agrupacioncodsia = true;
		} else {
			this.agrupacioncodsia = false;
		}
	}

	/**
	 * Obtener agrupacion cod org activado.
	 *
	 * @return agrupacion cod org activado
	 */
	public String getAgrupacionCodOrgActivado() {
		if (agrupacioncodsia != null && agrupacioncodsia) {
			return "true";
		} else {
			return "false";
		}

	}

	/**
	 * Obtener checks if is agrupacion cod sia.
	 *
	 * @return checks if is agrupacion cod sia
	 */
	public String getIsAgrupacionCodSia() {
		if (agrupacioncodsia != null && agrupacioncodsia) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is agrupacion cod sia.
	 *
	 * @param isAgrupacionCodSia new checks if is agrupacion cod sia
	 */
	public void setIsAgrupacionCodSia(String isAgrupacionCodSia) {
		if (isAgrupacionCodSia != null && isAgrupacionCodSia.equals("true")) {
			this.agrupacioncodsia = true;
		} else {
			this.agrupacioncodsia = false;
		}
		this.isAgrupacionCodSia = isAgrupacionCodSia;
	}

	/**
	 * Modificar agrupacion cod org pagador activado.
	 *
	 * @param agrupacionCodOrgPagadorActivado new agrupacion cod org pagador activado
	 */
	public void setAgrupacionCodOrgPagadorActivado(String agrupacionCodOrgPagadorActivado) {
		if (agrupacionCodOrgPagadorActivado != null && agrupacionCodOrgPagadorActivado.equals("true")) {
			this.agrupacioncodorgpagador = true;
		} else {
			this.agrupacioncodorgpagador = false;
		}
	}

	/**
	 * Obtener agrupacion cod org pagador activado.
	 *
	 * @return agrupacion cod org pagador activado
	 */
	public String getAgrupacionCodOrgPagadorActivado() {
		if (agrupacioncodorgpagador != null && agrupacioncodorgpagador) {
			return "true";
		} else {
			return "false";
		}

	}

	/**
	 * Obtener checks if is agrupacion cod org pagador.
	 *
	 * @return checks if is agrupacion cod org pagador
	 */
	public String getIsAgrupacionCodOrgPagador() {
		if (agrupacioncodorgpagador != null && agrupacioncodorgpagador) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is agrupacion cod org pagador.
	 *
	 * @param isAgrupacionCodOrgPagador new checks if is agrupacion cod org pagador
	 */
	public void setIsAgrupacionCodOrgPagador(String isAgrupacionCodOrgPagador) {
		if (isAgrupacionCodOrgPagador != null && isAgrupacionCodOrgPagador.equals("true")) {
			this.agrupacioncodorgpagador = true;
		} else {
			this.agrupacioncodorgpagador = false;
		}
		this.isAgrupacionCodOrgPagador = isAgrupacionCodOrgPagador;
	}

	/**
	 * Obtener checks if is multiorganismo.
	 *
	 * @return checks if is multiorganismo
	 */
	public String getIsMultiorganismo() {
		if (multiorganismo != null && multiorganismo) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is multiorganismo.
	 *
	 * @param isMultiorganismo new checks if is multiorganismo
	 */
	public void setIsMultiorganismo(String isMultiorganismo) {
		if (isMultiorganismo != null && isMultiorganismo.equals("true")) {
			this.multiorganismo = true;
		} else {
			this.multiorganismo = false;
		}
		this.isMultiorganismo = isMultiorganismo;
	}

	/**
	 * Obtener checks if is premium.
	 *
	 * @return checks if is premium
	 */
	public String getIsPremium() {
		if (premium != null && premium) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is premium.
	 *
	 * @param isPremium new checks if is premium
	 */
	public void setIsPremium(String isPremium) {
		if (isPremium != null && isPremium.equals("true")) {
			this.premium = true;
		} else {
			this.premium = false;
		}
		this.isPremium = isPremium;
	}
	
	/**
	 * Obtener checks if is exclusivo.
	 *
	 * @return checks if is exclusivo
	 */
	public String getIsExclusivo() {
		if (exclusivo != null && exclusivo) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is exclusivo.
	 *
	 * @param isExclusivo new checks if is exclusivo
	 */
	public void setIsExclusivo(String isExclusivo) {
		if (isExclusivo != null && isExclusivo.equals("true")) {
			this.exclusivo = true;
		} else {
			this.exclusivo = false;
		}
		this.isExclusivo = isExclusivo;
	}
	
	/**
	 * Obtener checks if is smsExtranjeros.
	 *
	 * @return checks if is smsExtranjeros
	 */
	public String getIsSmsExtranjeros() {
		if (smsExtranjeros != null && smsExtranjeros) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Modificar checks if is smsExtranjeros.
	 *
	 * @param isSmsExtranjeros new checks if is smsExtranjeros
	 */
	public void setIsSmsExtranjeros(String isSmsExtranjeros) {
		if (isSmsExtranjeros!= null && isSmsExtranjeros.equals("true")) {
			this.smsExtranjeros = true;
		} else {
			this.smsExtranjeros = false;
		}
		this.isSmsExtranjeros = isSmsExtranjeros;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return the servicioId
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId            the servicioId to set
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
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
	 * Obtener canalid.
	 *
	 * @return the canalid
	 */
	public Integer getCanalid() {
		return canalid;
	}

	/**
	 * Modificar canalid.
	 *
	 * @param canalid            the canalid to set
	 */
	public void setCanalid(Integer canalid) {
		this.canalid = canalid;
	}

	/**
	 * Obtener aplicacionid.
	 *
	 * @return the aplicacionid
	 */
	public Integer getAplicacionid() {
		return aplicacionid;
	}

	/**
	 * Modificar aplicacionid.
	 *
	 * @param aplicacionid            the aplicacionid to set
	 */
	public void setAplicacionid(Integer aplicacionid) {
		this.aplicacionid = aplicacionid;
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
	public String getExternalid() {
		return externalid;
	}

	/**
	 * Modificar externalid.
	 *
	 * @param externalid            the externalid to set
	 */
	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	/**
	 * Obtener nmaxenvios.
	 *
	 * @return the nmaxenvios
	 */
	public Integer getNmaxenvios() {
		return nmaxenvios;
	}

	/**
	 * Modificar nmaxenvios.
	 *
	 * @param nmaxenvios            the nmaxenvios to set
	 */
	public void setNmaxenvios(Integer nmaxenvios) {
		this.nmaxenvios = nmaxenvios;
	}

	/**
	 * Obtener canalnombre.
	 *
	 * @return the canalnombre
	 */
	public String getCanalnombre() {
		return canalnombre;
	}

	/**
	 * Modificar canalnombre.
	 *
	 * @param canalnombre            the canalnombre to set
	 */
	public void setCanalnombre(String canalnombre) {
		this.canalnombre = canalnombre;
	}

	/**
	 * Obtener aplicacionnombre.
	 *
	 * @return the aplicacionnombre
	 */
	public String getAplicacionnombre() {
		return aplicacionnombre;
	}

	/**
	 * Modificar aplicacionnombre.
	 *
	 * @param aplicacionnombre            the aplicacionnombre to set
	 */
	public void setAplicacionnombre(String aplicacionnombre) {
		this.aplicacionnombre = aplicacionnombre;
	}

	/**
	 * Obtener frommail.
	 *
	 * @return the frommail
	 */
	public String getFrommail() {
		return frommail;
	}

	/**
	 * Modificar frommail.
	 *
	 * @param frommail            the frommail to set
	 */
	public void setFrommail(String frommail) {
		this.frommail = frommail;
	}

	/**
	 * Obtener frommailname.
	 *
	 * @return the frommailname
	 */
	public String getFrommailname() {
		return frommailname;
	}

	/**
	 * Modificar frommailname.
	 *
	 * @param frommailname            the frommailname to set
	 */
	public void setFrommailname(String frommailname) {
		this.frommailname = frommailname;
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
	 * Obtener historificacion input.
	 *
	 * @return the historificacionInput
	 */
	public Integer getHistorificacionInput() {
		return historificacionInput;
	}

	/**
	 * Modificar historificacion input.
	 *
	 * @param historificacionInput            the historificacionInput to set
	 */
	public void setHistorificacionInput(Integer historificacionInput) {
		this.historificacionInput = historificacionInput;
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
	 * Obtener conservacion input.
	 *
	 * @return the conservacionInput
	 */
	public Integer getConservacionInput() {
		return conservacionInput;
	}

	/**
	 * Modificar conservacion input.
	 *
	 * @param conservacionInput            the conservacionInput to set
	 */
	public void setConservacionInput(Integer conservacionInput) {
		this.conservacionInput = conservacionInput;
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
	 * Obtener pendienteaprobacion.
	 *
	 * @return the pendienteaprobacion
	 */
	public Boolean getPendienteaprobacion() {
		return pendienteaprobacion;
	}

	/**
	 * Modificar pendienteaprobacion.
	 *
	 * @param pendienteaprobacion            the pendienteaprobacion to set
	 */
	public void setPendienteaprobacion(Boolean pendienteaprobacion) {
		this.pendienteaprobacion = pendienteaprobacion;
	}

	/**
	 * Obtener nombreloteenvio.
	 *
	 * @return the nombreloteenvio
	 */
	public String getNombreloteenvio() {
		return nombreloteenvio;
	}

	/**
	 * Modificar nombreloteenvio.
	 *
	 * @param nombreloteenvio            the nombreloteenvio to set
	 */
	public void setNombreloteenvio(String nombreloteenvio) {
		this.nombreloteenvio = nombreloteenvio;
	}

	/**
	 * Obtener badge.
	 *
	 * @return the badge
	 */
	public Integer getBadge() {
		return badge;
	}

	/**
	 * Modificar badge.
	 *
	 * @param badge            the badge to set
	 */
	public void setBadge(Integer badge) {
		this.badge = badge;
	}

	/**
	 * Obtener fcmprojectkey.
	 *
	 * @return the fcmprojectkey
	 */
	public String getFcmprojectkey() {
		return fcmprojectkey;
	}

	/**
	 * Modificar fcmprojectkey.
	 *
	 * @param fcmprojectkey            the fcmprojectkey to set
	 */
	public void setFcmprojectkey(String fcmprojectkey) {
		this.fcmprojectkey = fcmprojectkey;
	}

	/**
	 * Obtener apnsrutacertificado.
	 *
	 * @return the apnsrutacertificado
	 */
	public String getApnsrutacertificado() {
		return apnsrutacertificado;
	}

	/**
	 * Modificar apnsrutacertificado.
	 *
	 * @param apnsrutacertificado            the apnsrutacertificado to set
	 */
	public void setApnsrutacertificado(String apnsrutacertificado) {
		this.apnsrutacertificado = apnsrutacertificado;
	}

	/**
	 * Obtener apnspasswordcertificado.
	 *
	 * @return the apnspasswordcertificado
	 */
	public String getApnspasswordcertificado() {
		return apnspasswordcertificado;
	}

	/**
	 * Modificar apnspasswordcertificado.
	 *
	 * @param apnspasswordcertificado            the apnspasswordcertificado to set
	 */
	public void setApnspasswordcertificado(String apnspasswordcertificado) {
		this.apnspasswordcertificado = apnspasswordcertificado;
	}

	/**
	 * Obtener androidplataforma.
	 *
	 * @return the androidplataforma
	 */
	public Boolean getAndroidplataforma() {
		return androidplataforma;
	}

	/**
	 * Modificar androidplataforma.
	 *
	 * @param androidplataforma            the androidplataforma to set
	 */
	public void setAndroidplataforma(Boolean androidplataforma) {
		this.androidplataforma = androidplataforma;
	}

	/**
	 * Obtener iosplataforma.
	 *
	 * @return the iosplataforma
	 */
	public Boolean getIosplataforma() {
		return iosplataforma;
	}

	/**
	 * Modificar iosplataforma.
	 *
	 * @param iosplataforma            the iosplataforma to set
	 */
	public void setIosplataforma(Boolean iosplataforma) {
		this.iosplataforma = iosplataforma;
	}

	/**
	 * Obtener endpoint.
	 *
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Modificar endpoint.
	 *
	 * @param endpoint            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * Obtener informesactivo.
	 *
	 * @return the informesactivo
	 */
	public Boolean getInformesactivo() {
		return informesactivo;
	}

	/**
	 * Modificar informesactivo.
	 *
	 * @param informesactivo            the informesactivo to set
	 */
	public void setInformesactivo(Boolean informesactivo) {
		this.informesactivo = informesactivo;
	}

	/**
	 * Obtener agrupacionestado.
	 *
	 * @return the agrupacionestado
	 */
	public Boolean getAgrupacionestado() {
		return agrupacionestado;
	}

	/**
	 * Modificar agrupacionestado.
	 *
	 * @param agrupacionestado            the agrupacionestado to set
	 */
	public void setAgrupacionestado(Boolean agrupacionestado) {
		this.agrupacionestado = agrupacionestado;
	}

	/**
	 * Obtener agrupacioncodorg.
	 *
	 * @return the agrupacioncodorg
	 */
	public Boolean getAgrupacioncodorg() {
		return agrupacioncodorg;
	}

	/**
	 * Modificar agrupacioncodorg.
	 *
	 * @param agrupacioncodorg            the agrupacioncodorg to set
	 */
	public void setAgrupacioncodorg(Boolean agrupacioncodorg) {
		this.agrupacioncodorg = agrupacioncodorg;
	}

	/**
	 * Obtener agrupacioncodsia.
	 *
	 * @return the agrupacioncodsia
	 */
	public Boolean getAgrupacioncodsia() {
		return agrupacioncodsia;
	}

	/**
	 * Modificar agrupacioncodsia.
	 *
	 * @param agrupacioncodsia            the agrupacioncodsia to set
	 */
	public void setAgrupacioncodsia(Boolean agrupacioncodsia) {
		this.agrupacioncodsia = agrupacioncodsia;
	}

	/**
	 * Obtener agrupacioncodorgpagador.
	 *
	 * @return the agrupacioncodorgpagador
	 */
	public Boolean getAgrupacioncodorgpagador() {
		return agrupacioncodorgpagador;
	}

	/**
	 * Modificar agrupacioncodorgpagador.
	 *
	 * @param agrupacioncodorgpagador            the agrupacioncodorgpagador to set
	 */
	public void setAgrupacioncodorgpagador(Boolean agrupacioncodorgpagador) {
		this.agrupacioncodorgpagador = agrupacioncodorgpagador;
	}

	/**
	 * Obtener informesdestinatarios.
	 *
	 * @return the informesdestinatarios
	 */
	public String getInformesdestinatarios() {
		return informesdestinatarios;
	}

	/**
	 * Modificar informesdestinatarios.
	 *
	 * @param informesdestinatarios            the informesdestinatarios to set
	 */
	public void setInformesdestinatarios(String informesdestinatarios) {
		this.informesdestinatarios = informesdestinatarios;
	}

	/**
	 * Obtener responsablefuncionalnombre.
	 *
	 * @return the responsablefuncionalnombre
	 */
	public String getResponsablefuncionalnombre() {
		return responsablefuncionalnombre;
	}

	/**
	 * Modificar responsablefuncionalnombre.
	 *
	 * @param responsablefuncionalnombre            the responsablefuncionalnombre to set
	 */
	public void setResponsablefuncionalnombre(String responsablefuncionalnombre) {
		this.responsablefuncionalnombre = responsablefuncionalnombre;
	}

	/**
	 * Obtener responsablefuncionalemail.
	 *
	 * @return the responsablefuncionalemail
	 */
	public String getResponsablefuncionalemail() {
		return responsablefuncionalemail;
	}

	/**
	 * Modificar responsablefuncionalemail.
	 *
	 * @param responsablefuncionalemail            the responsablefuncionalemail to set
	 */
	public void setResponsablefuncionalemail(String responsablefuncionalemail) {
		this.responsablefuncionalemail = responsablefuncionalemail;
	}

	/**
	 * Obtener responsabletecniconombre.
	 *
	 * @return the responsabletecniconombre
	 */
	public String getResponsabletecniconombre() {
		return responsabletecniconombre;
	}

	/**
	 * Modificar responsabletecniconombre.
	 *
	 * @param responsabletecniconombre            the responsabletecniconombre to set
	 */
	public void setResponsabletecniconombre(String responsabletecniconombre) {
		this.responsabletecniconombre = responsabletecniconombre;
	}

	/**
	 * Obtener responsabletecnicoemail.
	 *
	 * @return the responsabletecnicoemail
	 */
	public String getResponsabletecnicoemail() {
		return responsabletecnicoemail;
	}

	/**
	 * Modificar responsabletecnicoemail.
	 *
	 * @param responsabletecnicoemail            the responsabletecnicoemail to set
	 */
	public void setResponsabletecnicoemail(String responsabletecnicoemail) {
		this.responsabletecnicoemail = responsabletecnicoemail;
	}

	/**
	 * Obtener multiorganismo.
	 *
	 * @return the multiorganismo
	 */
	public Boolean getMultiorganismo() {
		return multiorganismo;
	}

	/**
	 * Modificar multiorganismo.
	 *
	 * @param multiorganismo            the multiorganismo to set
	 */
	public void setMultiorganismo(Boolean multiorganismo) {
		this.multiorganismo = multiorganismo;
	}

	/**
	 * Obtener premium.
	 *
	 * @return the premium
	 */
	public Boolean getPremium() {
		return premium;
	}

	/**
	 * Modificar premium.
	 *
	 * @param premium            the premium to set
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}
	
	/**
	 * Obtener exclusivo.
	 *
	 * @return the exclusivo
	 */
	public Boolean getExclusivo() {
		return exclusivo;
	}

	/**
	 * Modificar exclusivo.
	 *
	 * @param exclusivo        the exclusivo to set
	 */
	public void setExclusivo(Boolean exclusivo) {
		this.exclusivo = exclusivo;
	}
	
	public Boolean getSmsExtranjeros() {
		return smsExtranjeros;
	}

	public void setSmsExtranjeros(Boolean smsExtranjeros) {
		this.smsExtranjeros = smsExtranjeros;
	}
	
	/**
	 * Obtener numero max reenvios.
	 *
	 * @return numero max reenvios
	 */
	public Integer getNumeroMaxReenvios() {
		return numeroMaxReenvios;
	}

	/**
	 * Modificar numero max reenvios.
	 *
	 * @param numeroMaxReenvios new numero max reenvios
	 */
	public void setNumeroMaxReenvios(Integer numeroMaxReenvios) {
		this.numeroMaxReenvios = numeroMaxReenvios;
	}

	/**
	 * Obtener eliminado.
	 *
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * Modificar eliminado.
	 *
	 * @param eliminado the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * Obtener caducidad web push.
	 *
	 * @return the caducidadWebPush
	 */
	public Integer getCaducidadWebPush() {
		return caducidadWebPush;
	}

	/**
	 * Modificar caducidad web push.
	 *
	 * @param caducidadWebPush the caducidadWebPush to set
	 */
	public void setCaducidadWebPush(Integer caducidadWebPush) {
		this.caducidadWebPush = caducidadWebPush;
	}

	/**
	 * Obtener vapid public key.
	 *
	 * @return the vapidPublicKey
	 */
	public String getVapidPublicKey() {
		return vapidPublicKey;
	}

	/**
	 * Modificar vapid public key.
	 *
	 * @param vapidPublicKey the vapidPublicKey to set
	 */
	public void setVapidPublicKey(String vapidPublicKey) {
		this.vapidPublicKey = vapidPublicKey;
	}

	/**
	 * Obtener vapid private key.
	 *
	 * @return the vapidPrivateKey
	 */
	public String getVapidPrivateKey() {
		return vapidPrivateKey;
	}

	/**
	 * Modificar vapid private key.
	 *
	 * @param vapidPrivateKey the vapidPrivateKey to set
	 */
	public void setVapidPrivateKey(String vapidPrivateKey) {
		this.vapidPrivateKey = vapidPrivateKey;
	}

}
