package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class LotesEnviosBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class LotesEnviosBean implements Audit{

	/**  lote envio id. */
	private Integer loteEnvioId;

	/**  nombre. */
	private String nombre;

	/**  descripcion. */
	private String descripcion;

	/**  servicio id. */
	private Integer servicioId;

	/**  estado envio id. */
	private Integer estadoEnvioId;

	/**  fecha creacion. */
	private Date fechaCreacion;

	/**  creador por. */
	private String creadorPor;

	/**  fecha modificacion. */
	private Date fechaModificacion;

	/**  modificado por. */
	private String modificadoPor;



	/**
	 * Constructor de lotes envios bean.
	 */
	public LotesEnviosBean() {
		this.loteEnvioId=null;
		this.nombre=null;
		this.descripcion=null;
		this.servicioId=null;
		this.estadoEnvioId=null;
		this.fechaCreacion=null;
		this.creadorPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
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
	 * Obtener lote envio id.
	 *
	 * @return lote envio id
	 */
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}



	/**
	 * Modificar lote envio id.
	 *
	 * @param loteEnvioId new lote envio id
	 */
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}



	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * Modificar nombre.
	 *
	 * @param nombre new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Obtener descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}



	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Obtener estado envio id.
	 *
	 * @return estado envio id
	 */
	public Integer getEstadoEnvioId() {
		return estadoEnvioId;
	}



	/**
	 * Modificar estado envio id.
	 *
	 * @param estadoEnvioId new estado envio id
	 */
	public void setEstadoEnvioId(Integer estadoEnvioId) {
		this.estadoEnvioId = estadoEnvioId;
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
	 * Obtener creador por.
	 *
	 * @return creador por
	 */
	public String getCreadorPor() {
		return creadorPor;
	}



	/**
	 * Modificar creador por.
	 *
	 * @param creadorPor new creador por
	 */
	public void setCreadorPor(String creadorPor) {
		this.creadorPor = creadorPor;
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
	
}
