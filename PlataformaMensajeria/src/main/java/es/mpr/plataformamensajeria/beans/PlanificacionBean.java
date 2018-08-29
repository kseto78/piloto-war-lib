package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class PlanificacionBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1274813465180160041L;

	/**
	 * Constructor de planificacion bean.
	 */
	public PlanificacionBean() {
		this.planificacionId = null;
		this.servidorId = null;
		this.servicioId = null;
		this.tipoPlanificacionId = null;
		this.lunes = null;
		this.martes = null;
		this.miercoles = null;
		this.jueves = null;
		this.viernes = null;
		this.sabado = null;
		this.domingo = null;
		this.horaDesde = null;
		this.horaHasta = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.externalId = null;
		this.canalId = null;
		this.aplicacionId = null;
		this.nombreServidor = null;
		this.nombreServicio = null;
		this.horaDesdeFin = null;
		this.horaHastaFin = null;
		this.nombreAplicacion = null;
		this.nombreTipoPlanificacion = null;
		this.organismoId = null;
		this.dir3Organismo = null;
	}

	/**  planificacion id. */
	protected Integer planificacionId;
	
	/**  servidor id. */
	protected Integer servidorId;
	
	/**  servicio id. */
	protected Integer servicioId;
	
	/**  tipo planificacion id. */
	protected Integer tipoPlanificacionId;
	
	/**  lunes. */
	protected String lunes;
	
	/**  martes. */
	protected String martes;
	
	/**  miercoles. */
	protected String miercoles;
	
	/**  jueves. */
	protected String jueves;
	
	/**  viernes. */
	protected String viernes;
	
	/**  sabado. */
	protected String sabado;
	
	/**  domingo. */
	protected String domingo;
	
	/**  hora desde. */
	protected String horaDesde;
	
	/**  hora hasta. */
	protected String horaHasta;
	
	/**  activo. */
	protected Boolean activo;
	
	/**  fecha creacion. */
	protected Date fechaCreacion = null;
	
	/**  creado por. */
	protected String creadoPor = null;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion = null;
	
	/**  modificado por. */
	protected String modificadoPor = null;
	
	/**  external id. */
	protected Integer externalId = null;
	
	/**  canal id. */
	protected Integer canalId;
	
	/**  nombre aplicacion. */
	protected String nombreAplicacion = null;
	
	/**  nombre tipo planificacion. */
	protected String nombreTipoPlanificacion = null;
	
	/**  organismo id. */
	protected Integer organismoId = null;
	
	/**  dir 3 organismo. */
	protected String dir3Organismo = null;
	
	/**  aplicacion id. */
	protected Integer aplicacionId;
	
	/**  nombre servidor. */
	protected String nombreServidor;
	
	/**  nombre servicio. */
	protected String nombreServicio;
	
	/**  hora desde fin. */
	protected String horaDesdeFin;
	
	/**  hora hasta fin. */
	protected String horaHastaFin;

	/**
	 * Obtener nombre tipo planificacion.
	 *
	 * @return nombre tipo planificacion
	 */
	public String getNombreTipoPlanificacion() {
		return nombreTipoPlanificacion;
	}

	/**
	 * Modificar nombre tipo planificacion.
	 *
	 * @param nombreTipoPlanificacion new nombre tipo planificacion
	 */
	public void setNombreTipoPlanificacion(String nombreTipoPlanificacion) {
		this.nombreTipoPlanificacion = nombreTipoPlanificacion;
	}

	/**
	 * Obtener nombre aplicacion.
	 *
	 * @return nombre aplicacion
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * Modificar nombre aplicacion.
	 *
	 * @param nombreAplicacion new nombre aplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * Obtener hora desde fin.
	 *
	 * @return hora desde fin
	 */
	public String getHoraDesdeFin() {
		return horaDesdeFin;
	}

	/**
	 * Modificar hora desde fin.
	 *
	 * @param horaDesdeFin new hora desde fin
	 */
	public void setHoraDesdeFin(String horaDesdeFin) {
		this.horaDesdeFin = horaDesdeFin;
	}

	/**
	 * Obtener hora hasta fin.
	 *
	 * @return hora hasta fin
	 */
	public String getHoraHastaFin() {
		return horaHastaFin;
	}

	/**
	 * Modificar hora hasta fin.
	 *
	 * @param horaHastaFin new hora hasta fin
	 */
	public void setHoraHastaFin(String horaHastaFin) {
		this.horaHastaFin = horaHastaFin;
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

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener external id.
	 *
	 * @return external id
	 */
	public Integer getExternalId() {
		return externalId;
	}

	/**
	 * Modificar external id.
	 *
	 * @param externalId new external id
	 */
	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
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
	 * Obtener nombre servidor.
	 *
	 * @return nombre servidor
	 */
	public String getNombreServidor() {
		return nombreServidor;
	}

	/**
	 * Modificar nombre servidor.
	 *
	 * @param nombreServidor new nombre servidor
	 */
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	/**
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener planificacion id.
	 *
	 * @return planificacion id
	 */
	public Integer getPlanificacionId() {
		return planificacionId;
	}

	/**
	 * Modificar planificacion id.
	 *
	 * @param planificacionId new planificacion id
	 */
	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
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
	 * Obtener lunes.
	 *
	 * @return lunes
	 */
	public String getLunes() {
		return lunes;
	}

	/**
	 * Modificar lunes.
	 *
	 * @param lunes new lunes
	 */
	public void setLunes(String lunes) {
		this.lunes = lunes;
	}

	/**
	 * Obtener martes.
	 *
	 * @return martes
	 */
	public String getMartes() {
		return martes;
	}

	/**
	 * Modificar martes.
	 *
	 * @param martes new martes
	 */
	public void setMartes(String martes) {
		this.martes = martes;
	}

	/**
	 * Obtener miercoles.
	 *
	 * @return miercoles
	 */
	public String getMiercoles() {
		return miercoles;
	}

	/**
	 * Modificar miercoles.
	 *
	 * @param miercoles new miercoles
	 */
	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}

	/**
	 * Obtener jueves.
	 *
	 * @return jueves
	 */
	public String getJueves() {
		return jueves;
	}

	/**
	 * Modificar jueves.
	 *
	 * @param jueves new jueves
	 */
	public void setJueves(String jueves) {
		this.jueves = jueves;
	}

	/**
	 * Obtener viernes.
	 *
	 * @return viernes
	 */
	public String getViernes() {
		return viernes;
	}

	/**
	 * Modificar viernes.
	 *
	 * @param viernes new viernes
	 */
	public void setViernes(String viernes) {
		this.viernes = viernes;
	}

	/**
	 * Obtener sabado.
	 *
	 * @return sabado
	 */
	public String getSabado() {
		return sabado;
	}

	/**
	 * Modificar sabado.
	 *
	 * @param sabado new sabado
	 */
	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	/**
	 * Obtener domingo.
	 *
	 * @return domingo
	 */
	public String getDomingo() {
		return domingo;
	}

	/**
	 * Modificar domingo.
	 *
	 * @param domingo new domingo
	 */
	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	/**
	 * Obtener hora desde.
	 *
	 * @return hora desde
	 */
	public String getHoraDesde() {
		return horaDesde;
	}

	/**
	 * Modificar hora desde.
	 *
	 * @param horaDesde new hora desde
	 */
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	/**
	 * Obtener hora hasta.
	 *
	 * @return hora hasta
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	/**
	 * Modificar hora hasta.
	 *
	 * @param horaHasta new hora hasta
	 */
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	/**
	 * Obtener activo.
	 *
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo new activo
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * Obtener tipo planificacion id.
	 *
	 * @return tipo planificacion id
	 */
	public Integer getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}

	/**
	 * Modificar tipo planificacion id.
	 *
	 * @param tipoPlanificacionId new tipo planificacion id
	 */
	public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

	/**
	 * Obtener dias.
	 *
	 * @return dias
	 */
	public String getDias() {
		StringBuffer sbf = new StringBuffer();
		boolean sw = true;
		if (lunes != null && (lunes.equals("S") || lunes.equals("true"))) {
			sbf.append("L");
			sw = false;
		}
		if (martes != null && (martes.equals("S") || martes.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("M");
			sw = false;
		}
		if (miercoles != null && (miercoles.equals("S") || miercoles.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("X");
			sw = false;
		}
		if (jueves != null && (jueves.equals("S") || jueves.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("J");
			sw = false;
		}
		if (viernes != null && (viernes.equals("S") || viernes.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("V");
			sw = false;
		}
		if (sabado != null && (sabado.equals("S") || sabado.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("S");
			sw = false;
		}
		if (domingo != null && (domingo.equals("S") || domingo.equals("true"))) {
			if (!sw) {
				sbf.append(", ");
			}
			sbf.append("D");
		}

		return sbf.toString();
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
	 * Obtener dir 3 organismo.
	 *
	 * @return dir 3 organismo
	 */
	public String getDir3Organismo() {
		return dir3Organismo;
	}

	/**
	 * Modificar dir 3 organismo.
	 *
	 * @param dir3Organismo new dir 3 organismo
	 */
	public void setDir3Organismo(String dir3Organismo) {
		this.dir3Organismo = dir3Organismo;
	}

}
