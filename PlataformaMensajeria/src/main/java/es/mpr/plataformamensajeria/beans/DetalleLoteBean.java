package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Representa los envios de tipo email
 * <p>.
 *
 * @author i-nercya
 */
public class DetalleLoteBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7720405961205898287L;

	/**  nombre aplicacion. */
	private String nombreAplicacion;

	/**  nombre servicio. */
	private String nombreServicio;

	/**  nombre lote envio. */
	private String nombreLoteEnvio;

	/**  id lote envio. */
	private Integer idLoteEnvio;



	/**
	 * Constructor de detalle lote bean.
	 */
	public DetalleLoteBean() {
		this.nombreAplicacion = null;
		this.nombreServicio = null;
		this.nombreLoteEnvio = null;
		this.idLoteEnvio = null;
		
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener nombre aplicacion.
	 *
	 * @return nombre aplicacion
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * Modificar nombre aplicacion.
	 *
	 * @param nombreAplicacion new nombre aplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener nombre lote envio.
	 *
	 * @return nombre lote envio
	 */
	public String getNombreLoteEnvio() {
		return nombreLoteEnvio;
	}

	/**
	 * Modificar nombre lote envio.
	 *
	 * @param nombreLoteEnvio new nombre lote envio
	 */
	public void setNombreLoteEnvio(String nombreLoteEnvio) {
		this.nombreLoteEnvio = nombreLoteEnvio;
	}

	/**
	 * Obtener id lote envio.
	 *
	 * @return id lote envio
	 */
	public Integer getIdLoteEnvio() {
		return idLoteEnvio;
	}

	/**
	 * Modificar id lote envio.
	 *
	 * @param idLoteEnvio new id lote envio
	 */
	public void setIdLoteEnvio(Integer idLoteEnvio) {
		this.idLoteEnvio = idLoteEnvio;
	}

}
