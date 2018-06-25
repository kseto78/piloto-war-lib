package es.minhap.misim.bus.webapp.beans;

/**
 * Clase bean que contiene las propiedades de la tabla TBL_SERVICIOSMOVILES
 * 
 * @author ralberoc
 *
 */
public class ServiciosMovilesBean {

	private String idServicio;	//Identificador único del servicio	Numero
	private String nombre;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String descripcion;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String icono;	//Icono asignado al Servicio disponible para móviles	Cadena de caracteres
	private String url;	//URL del Servicio disponible para móviles	Cadena de caracteres
	private Integer tipo;	//Tipo de Servicio para móviles	Numérico
	private Integer estado;	//Estado del servicio móvil para el usuario. 	Numérico
	
	/**
	 * Obtiene el ID de servicio
	 * @return
	 */
	public String getIDServicio() {
		return this.idServicio;
	}
	/**
	 * Modifica el ID de servicio
	 * @param iDServicio
	 */
	public void setIDServicio(String iDServicio) {
		this.idServicio = iDServicio;
	}
	/**
	 * Obtiene el Nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Modifica el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene la descripcion
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * Modifica la descripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Obtiene el icono
	 * @return
	 */
	public String getIcono() {
		return this.icono;
	}
	/**
	 * Modifica el icono
	 * @param icono
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
	/**
	 * Obtiene la URL
	 * @return
	 */
	public String getURL() {
		return this.url;
	}
	/**
	 * Modifica la URL
	 * @param uRL
	 */
	public void setURL(String uRL) {
		this.url = uRL;
	}
	/**
	 * Obtiene el tipo
	 * @return
	 */
	public Integer getTipo() {
		return this.tipo;
	}
	/**
	 * Modifica el tipo
	 * @param tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	/**
	 * Obtiene el estado
	 * @return
	 */
	public Integer getEstado() {
		return this.estado;
	}
	/**
	 * Modifica el estado
	 * @param estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
