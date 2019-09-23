package es.minhap.plataformamensajeria.iop.beans;

import java.io.Serializable;
import java.util.Date;

public class ProcesosManualesBean implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5425782927314124581L;

	public ProcesosManualesBean() {
		super();
		this.procesosManualesId = null;		
		this.nombre = null;
		this.procesoId = null;
		this.job = null;
		this.parametro1 = null;
		this.parametro2 = null;
		this.parametro3 = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.eliminado = null;
	}
	

	private Integer procesosManualesId;
	private Integer procesoId = null;
	private String nombre = null;
	private String job = null;
	private String parametro1 = null;
	private String parametro2 = null;
	private String parametro3 = null;
	private String creadoPor = null;
	private Date fechaCreacion= null;
	private String modificadoPor = null;
	private Date fechaModificacion = null;
	private String eliminado = null;

	public Integer getProcesosManualesId() {
		return procesosManualesId;
	}
	public void setProcesosManualesId(Integer procesosManualesId) {
		this.procesosManualesId = procesosManualesId;
	}
	public Integer getProcesoId() {
		return procesoId;
	}
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
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
	public String getParametro3() {
		return parametro3;
	}
	public void setParametro3(String parametro3) {
		this.parametro3 = parametro3;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
