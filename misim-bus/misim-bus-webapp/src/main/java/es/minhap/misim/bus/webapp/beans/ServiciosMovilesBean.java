package es.minhap.misim.bus.webapp.beans;

public class ServiciosMovilesBean {

	private String IDServicio;	//Identificador único del servicio	Numero
	private String Nombre;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String Descripción;	//Nombre del Servicio disponible para móviles	Cadena de caracteres
	private String Icono;	//Icono asignado al Servicio disponible para móviles	Cadena de caracteres
	private String URL;	//URL del Servicio disponible para móviles	Cadena de caracteres
	private Integer Tipo;	//Tipo de Servicio para móviles	Numérico
	private Integer Estado;	//Estado del servicio móvil para el usuario. 	Numérico
	
	public String getIDServicio() {
		return IDServicio;
	}
	public void setIDServicio(String iDServicio) {
		IDServicio = iDServicio;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripción() {
		return Descripción;
	}
	public void setDescripción(String descripción) {
		Descripción = descripción;
	}
	public String getIcono() {
		return Icono;
	}
	public void setIcono(String icono) {
		Icono = icono;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public Integer getTipo() {
		return Tipo;
	}
	public void setTipo(Integer tipo) {
		Tipo = tipo;
	}
	public Integer getEstado() {
		return Estado;
	}
	public void setEstado(Integer estado) {
		Estado = estado;
	}

}
