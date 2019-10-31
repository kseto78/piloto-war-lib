package es.minhap.plataformamensajeria.iop.beans;

import java.io.Serializable;
import java.util.Date;

public class OrganismoPdpBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8095598141992922210L;
	private Integer organismoPdpId;
	private String nombre = null;
	private String descripcion = null;
	private Date fechacreacion = null;
	private Date fechamodificacion = null;
	private String creadopor = null;
	private String modificadopor = null;
	public OrganismoPdpBean() {
		super();
		this.organismoPdpId = null;		
		this.nombre = null;
		this.modificadopor = null;
		this.descripcion = null;		
		this.fechacreacion = null;		
		this.fechamodificacion = null;
		this.creadopor = null;		
		
	}

	/**
	 * @return the organismoPdpId
	 */
	public Integer getOrganismoPdpId() {
		return organismoPdpId;
	}
	/**
	 * @param organismoPdpId the organismoPdpId to set
	 */
	public void setOrganismoPdpId(Integer organismoPdpId) {
		this.organismoPdpId = organismoPdpId;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
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
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}
	/**
	 * @param fechacreacion the fechacreacion to set
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
	 * @param fechamodificacion the fechamodificacion to set
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
	 * @param creadopor the creadopor to set
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
	 * @param modificadopor the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}
	

	
	
	

	
}
