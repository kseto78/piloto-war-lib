package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n
 * 
 * @author everis
 * 
 */
public class AplicacionMisimBean implements Audit {

	/**
	 * Constructor por defecto
	 */
	public AplicacionMisimBean() {
		super();
		this.idAplicacion = null;
		this.nombre = null;
		this.usuario = null;
		this.password = null;
	}


	protected Long idAplicacion = null;
	protected String nombre = null;
	protected String usuario = null;
	protected String password = null;

	/**
	 * Obtener id Aplicacion
	 * @return
	 */
	public Long getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Modificar idAplicacion
	 * @param idAplicacion
	 */
	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	/**
	 * Obtener nombre
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
	 * Obtener usuario
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Modificar usuario
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtener password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Modificar password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
