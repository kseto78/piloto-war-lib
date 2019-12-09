package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n
 * 
 * @author everis
 * 
 */
public class ComunicacionBean implements Audit {

	
	protected Long idComunicacion = null;
	protected String nombre = null;

	/**
	 * Constructor por defecto
	 */
	public ComunicacionBean() {
		super();
		this.idComunicacion = null;
		this.nombre = null;
	}

	/**
	 * Obtener idComunicacion
	 * @return
	 */
	public Long getIdComunicacion() {
		return idComunicacion;
	}

	/**
	 * Modificar idComunicacion
	 * @param idComunicacion
	 */
	public void setIdComunicacion(Long idComunicacion) {
		this.idComunicacion = idComunicacion;
	}

	/**
	 * Obtener Nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
