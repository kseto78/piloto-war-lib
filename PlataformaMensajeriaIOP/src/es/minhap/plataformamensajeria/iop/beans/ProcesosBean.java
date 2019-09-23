package es.minhap.plataformamensajeria.iop.beans;

import java.io.Serializable;
import java.util.Date;

public class ProcesosBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8610134834369690886L;

	/**
	 * 
	 */
	
	public ProcesosBean() {
		super();
		this.procesosId = null;		
		this.nombre = null;
		this.nombreClase = null;
		this.inicioUltimaEjecucion = null;
		this.finUltimaEjecucion = null;		
		this.estado = null;		
		this.proximaEjecucion = null;
		this.parametro1 = null;	
		this.parametro2 = null;
		this.activo = null;
		this.modificadoPor = null;
		this.fechaModificacion = null;
		this.tipo = null;
		this.horaInicio = null;
		this.lunes =null;
		this.martes = null;
		this.miercoles = null;
		this.jueves = null;
		this.viernes = null;
		this.sabado = null;
		this.domingo = null;		
	}
	

	private Integer procesosId;	
	private String nombre = null;
	private String nombreClase = null;
	private Date inicioUltimaEjecucion = null;
	private Date finUltimaEjecucion = null;	
	private String estado = null;
	private String proximaEjecucion = null;
	private String parametro1 = null;
	private String parametro2 = null;
	private Boolean activo = null;
	private String isActivo = null;
	private String modificadoPor = null;
	private Date fechaModificacion = null;
	private String tipo;
	private String horaInicio;
	private Boolean lunes;
	private Boolean martes;
	private Boolean miercoles;
	private Boolean jueves;
	private Boolean viernes;
	private Boolean sabado;
	private Boolean domingo;

	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Integer getProcesosId() {
		return procesosId;
	}
	public void setProcesosId(Integer procesosId) {
		this.procesosId = procesosId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getInicioUltimaEjecucion() {
		return inicioUltimaEjecucion;
	}
	public void setInicioUltimaEjecucion(Date inicioUltimaEjecucion) {
		this.inicioUltimaEjecucion = inicioUltimaEjecucion;
	}
	public Date getFinUltimaEjecucion() {
		return finUltimaEjecucion;
	}
	public void setFinUltimaEjecucion(Date finUltimaEjecucion) {
		this.finUltimaEjecucion = finUltimaEjecucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getProximaEjecucion() {
		return proximaEjecucion;
	}
	public void setProximaEjecucion(String proximaEjecucion) {
		this.proximaEjecucion = proximaEjecucion;
	}
	public String getParametro1() {
		return parametro1;
	}
	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}
	public String getParametro2() {
		return parametro2;
	}
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
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
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Boolean getLunes() {
		return lunes;
	}
	public void setLunes(Boolean lunes) {
		this.lunes = lunes;
	}
	public Boolean getMartes() {
		return martes;
	}
	public void setMartes(Boolean martes) {
		this.martes = martes;
	}
	public Boolean getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(Boolean miercoles) {
		this.miercoles = miercoles;
	}
	public Boolean getJueves() {
		return jueves;
	}
	public void setJueves(Boolean jueves) {
		this.jueves = jueves;
	}
	public Boolean getViernes() {
		return viernes;
	}
	public void setViernes(Boolean viernes) {
		this.viernes = viernes;
	}
	public Boolean getSabado() {
		return sabado;
	}
	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}
	public Boolean getDomingo() {
		return domingo;
	}
	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	
	
}
