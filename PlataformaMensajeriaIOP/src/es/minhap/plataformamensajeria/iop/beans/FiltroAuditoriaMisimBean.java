package es.minhap.plataformamensajeria.iop.beans;

import java.util.Date;

import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n
 * 
 * @author everis
 * 
 */
public class FiltroAuditoriaMisimBean {

	

	public FiltroAuditoriaMisimBean() {
		super();
		this.idAuditoria = null;
		this.proveedor = null;
		this.aplicacion = null;
		this.producto = null;
		this.peticion = null;
		this.idMensaje = null;
		this.fechaCreacion = null;
		this.fechaActualizacion = null;
		this.idLote = null;
	}

	protected Long idAuditoria = null;
	protected Proveedor proveedor = null;
	protected Aplicacion aplicacion = null;
	protected Producto producto = null;
	protected Peticion peticion = null;
	protected Long idMensaje = null;
	protected Date fechaCreacion = null;
	protected Date fechaActualizacion = null;
	protected Long idLote = null;
	
	
	public Long getIdAuditoria() {
		return idAuditoria;
	}
	
	
	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}
	
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	
	
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	
	public Producto getProducto() {
		return producto;
	}
	
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	public Peticion getPeticion() {
		return peticion;
	}
	
	
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}
	
	
	public Long getIdMensaje() {
		return idMensaje;
	}
	
	
	public void setIdMensaje(Long idMensaje) {
		this.idMensaje = idMensaje;
	}
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	
	public Long getIdLote() {
		return idLote;
	}
	
	
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	

}
