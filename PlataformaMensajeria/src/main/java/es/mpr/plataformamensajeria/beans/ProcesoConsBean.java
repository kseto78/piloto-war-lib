package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class ProcesoConsBean.
 *
 * @author jgonzvil
 * @version 1.0
 */
public class ProcesoConsBean implements Audit{

	/**  proceso cons id. */
	private Long procesoConsId;

	/**  codigo estado. */
	private String codigoEstado;

	/**  descripcion estado. */
	private String descripcionEstado;

	/**  fecha creacion. */
	private Date fechaCreacion;

	/**  fecha inicio. */
	private Date fechaInicio;

	/**  fecha fin. */
	private Date fechaFin;

	/**
	 * Constructor de proceso cons bean.
	 */
	public ProcesoConsBean() {
		this.procesoConsId=null;
		this.codigoEstado=null;
		this.descripcionEstado=null;
		this.fechaCreacion=null;
		this.fechaInicio=null;
		this.fechaFin=null;
	}
	
	/**
	 * Obtener proceso cons id.
	 *
	 * @return proceso cons id
	 */
	public Long getProcesoConsId() {
		return procesoConsId;
	}
	
	/**
	 * Modificar proceso cons id.
	 *
	 * @param procesoConsId new proceso cons id
	 */
	public void setProcesoConsId(Long procesoConsId) {
		this.procesoConsId = procesoConsId;
	}
	
	/**
	 * Obtener codigo estado.
	 *
	 * @return codigo estado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	
	/**
	 * Modificar codigo estado.
	 *
	 * @param codigoEstado new codigo estado
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	/**
	 * Obtener descripcion estado.
	 *
	 * @return descripcion estado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	
	/**
	 * Modificar descripcion estado.
	 *
	 * @param descripcionEstado new descripcion estado
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
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
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener fecha inicio.
	 *
	 * @return fecha inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Modificar fecha inicio.
	 *
	 * @param fechaInicio new fecha inicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtener fecha fin.
	 *
	 * @return fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Modificar fecha fin.
	 *
	 * @param fechaFin new fecha fin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
