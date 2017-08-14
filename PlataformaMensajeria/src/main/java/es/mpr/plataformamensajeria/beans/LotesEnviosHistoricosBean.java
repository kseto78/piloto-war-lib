package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author jgonzvil
 */
public class LotesEnviosHistoricosBean implements Audit{

	public LotesEnviosHistoricosBean() {
		this.loteEnvioId=null;
		this.nombre=null;
		this.descripcion=null;
		this.servicioId=null;
		this.estadoEnvioId=null;
		this.fechaCreacion=null;
		this.creadorPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.fechaHistorificacion=null;
	}
	
	private Integer loteEnvioId;
	private String nombre;
	private String descripcion;
	private Integer servicioId;
	private Integer estadoEnvioId;
	private Date fechaCreacion;
	private String creadorPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private Date fechaHistorificacion;
	
	
	
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}



	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}



	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
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



	public Integer getServicioId() {
		return servicioId;
	}



	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}



	public Integer getEstadoEnvioId() {
		return estadoEnvioId;
	}



	public void setEstadoEnvioId(Integer estadoEnvioId) {
		this.estadoEnvioId = estadoEnvioId;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public String getCreadorPor() {
		return creadorPor;
	}



	public void setCreadorPor(String creadorPor) {
		this.creadorPor = creadorPor;
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



	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}



	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}
	
}
