package es.minhap.misim.bus.webapp.beans;

public class ServiciosMovilesBean {

	private String idServicio;	//Identificador único del servicio	Numero
	private String nombre;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String descripcion;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String icono;	//Icono asignado al Servicio disponible para móviles	Cadena de caracteres
	private String url;	//URL del Servicio disponible para móviles	Cadena de caracteres
	private Integer tipo;	//Tipo de Servicio para móviles	Numérico
	private Integer estado;	//Estado del servicio móvil para el usuario. 	Numérico
	
	public String getIDServicio() {
		return this.idServicio;
	}
	public void setIDServicio(String iDServicio) {
		this.idServicio = iDServicio;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripción() {
		return this.descripcion;
	}
	public void setDescripción(String descripción) {
		this.descripcion = descripción;
	}
	public String getIcono() {
		return this.icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getURL() {
		return this.url;
	}
	public void setURL(String uRL) {
		this.url = uRL;
	}
	public Integer getTipo() {
		return this.tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getEstado() {
		return this.estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
