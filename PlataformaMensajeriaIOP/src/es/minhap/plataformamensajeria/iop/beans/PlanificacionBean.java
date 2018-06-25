package es.minhap.plataformamensajeria.iop.beans;

import java.util.Date;

/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class PlanificacionBean {

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

	protected Integer planificacionId;
	protected Integer servidorId;
	protected Integer servicioId;
	protected Integer tipoPlanificacionId;
	protected String lunes;
	protected String martes;
	protected String miercoles;
	protected String jueves;
	protected String viernes;
	protected String sabado;
	protected String domingo;
	protected String horaDesde;
	protected String horaHasta;
	protected Boolean activo;
	protected Date fechaCreacion = null;
	protected String creadoPor = null;
	protected Date fechaModificacion = null;
	protected String modificadoPor = null;
	protected Integer externalId = null;
	protected Integer canalId;
	protected String nombreAplicacion = null;
	protected String nombreTipoPlanificacion = null;
	protected Integer organismoId = null;
	protected String dir3Organismo = null;
	protected Integer aplicacionId;
	protected String nombreServidor;
	protected String nombreServicio;
	protected String horaDesdeFin;
	protected String horaHastaFin;

	public String getNombreTipoPlanificacion() {
		return nombreTipoPlanificacion;
	}

	public void setNombreTipoPlanificacion(String nombreTipoPlanificacion) {
		this.nombreTipoPlanificacion = nombreTipoPlanificacion;
	}

	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	public String getHoraDesdeFin() {
		return horaDesdeFin;
	}

	public void setHoraDesdeFin(String horaDesdeFin) {
		this.horaDesdeFin = horaDesdeFin;
	}

	public String getHoraHastaFin() {
		return horaHastaFin;
	}

	public void setHoraHastaFin(String horaHastaFin) {
		this.horaHastaFin = horaHastaFin;
	}

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

	
	public Integer getExternalId() {
		return externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
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

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public String getLunes() {
		return lunes;
	}

	public void setLunes(String lunes) {
		this.lunes = lunes;
	}

	public String getMartes() {
		return martes;
	}

	public void setMartes(String martes) {
		this.martes = martes;
	}

	public String getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}

	public String getJueves() {
		return jueves;
	}

	public void setJueves(String jueves) {
		this.jueves = jueves;
	}

	public String getViernes() {
		return viernes;
	}

	public void setViernes(String viernes) {
		this.viernes = viernes;
	}

	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
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

	public Integer getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}

	public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

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
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public String getDir3Organismo() {
		return dir3Organismo;
	}

	public void setDir3Organismo(String dir3Organismo) {
		this.dir3Organismo = dir3Organismo;
	}

}
