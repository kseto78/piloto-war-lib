package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;


	
public class OrganismoBean implements Audit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganismoBean() {
		super();
		this.organismoId = null;
		this.DIR3 = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.externalId = null;
		this.nombreCuentaEnvio = null;
		this.historificacion = null;
		this.motivoHistorificacion = null;
		this.conservacion = null;
		this.motivoConservacion = null;
		this.isActivo = null;
				
	}

	protected Integer organismoId;
	protected String DIR3;
	protected String nombre = null;
	protected String descripcion = null;
	protected Integer activo = null;
	protected Date fechaCreacion = null;
	protected Date fechaModificacion = null;
	protected String creadoPor = null;
	protected String modificadoPor = null;
	protected Integer externalId;
	protected String nombreCuentaEnvio;
	protected Integer historificacion;
	protected String motivoHistorificacion;
	protected Integer conservacion;
	protected String motivoConservacion;
	protected String isActivo = null; 
	
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}



	public Integer getOrganismoId() {
		return organismoId;
	}



	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}



	public String getDIR3() {
		return DIR3;
	}



	public void setDIR3(String dIR3) {
		DIR3 = dIR3;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Integer getActivo() {
		return activo;
	}



	public void setActivo(Integer activo) {
		this.activo = activo;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public Date getFechaModificacion() {
		return fechaModificacion;
	}



	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}



	public String getCreadoPor() {
		return creadoPor;
	}



	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}



	public String getModificadoPor() {
		return modificadoPor;
	}



	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}



	public Integer getExternalId() {
		return externalId;
	}



	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}



	public String getNombreCuentaEnvio() {
		return nombreCuentaEnvio;
	}



	public void setNombreCuentaEnvio(String nombreCuentaEnvio) {
		this.nombreCuentaEnvio = nombreCuentaEnvio;
	}



	public Integer getHistorificacion() {
		return historificacion;
	}



	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}



	public String getMotivoHistorificacion() {
		return motivoHistorificacion;
	}



	public void setMotivoHistorificacion(String motivoHistorificacion) {
		this.motivoHistorificacion = motivoHistorificacion;
	}



	public Integer getConservacion() {
		return conservacion;
	}



	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}



	public String getMotivoConservacion() {
		return motivoConservacion;
	}



	public void setMotivoConservacion(String motivoConservacion) {
		this.motivoConservacion = motivoConservacion;
	}


	public void setActivado(String activado){
		if(activado!=null&&activado.equals("true")){
			this.activo = new Integer(1);
		}else{
			this.activo = new Integer(0);
		}
	}
	public String getIsActivo() {
		if(activo!=null&&activo.intValue()==1){
			return "<span class='activo'></span>";
		}else{
			return "<span class='inactivo'></span>";
		}
		
	}

	public void setIsActivo(String isActivo) {
		if(isActivo!=null&&isActivo.equals("true")){
			this.activo = Integer.valueOf(1);
		}else{
			this.activo = Integer.valueOf(0);
		}
		this.isActivo = isActivo;
	}
	public String getActivado(){
		if(activo!=null&&activo.intValue()==1){
			return "true";
		}else{
			return "false";
		}
	}
	
}
