package es.minhap.plataformamensajeria.iop.beans;

import java.io.Serializable;
import java.util.Date;

public class PdpDiputacionesBean implements Serializable{


	protected static final String R_CONST_1 = "true";
	/**
	 * 
	 */
	private static final long serialVersionUID = -8095598141992922210L;
	private Integer pdpDiputacionesId;
	private String nombre = null;
	private String descripcion = null;
	private Boolean activo = null;
	private String isActivo = null;
	private Date fechacreacion = null;
	private Date fechamodificacion = null;
	private String creadopor = null;
	private String modificadopor = null;
	public PdpDiputacionesBean() {
		super();
		this.pdpDiputacionesId = null;		
		this.nombre = null;
		this.modificadopor = null;
		this.descripcion = null;		
		this.fechacreacion = null;		
		this.fechamodificacion = null;
		this.creadopor = null;	
		this.activo = null;
		
	}

	/**
	 * @return the pdpDiputacionesId
	 */
	public Integer getPdpDiputacionesId() {
		return pdpDiputacionesId;
	}
	/**
	 * @param pdpDiputacionesId the pdpDiputacionesId to set
	 */
	public void setPdpDiputacionesId(Integer pdpDiputacionesId) {
		this.pdpDiputacionesId = pdpDiputacionesId;
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
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
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
	
}
