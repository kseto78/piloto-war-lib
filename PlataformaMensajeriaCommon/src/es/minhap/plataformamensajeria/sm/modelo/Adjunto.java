package es.minhap.plataformamensajeria.sm.modelo;

import java.sql.Blob;

public class Adjunto {
	
	String Nombre;
	Blob Contenido;
	
	public Adjunto(){}
	
	
	public void setNombre(String nombre){
		this.Nombre = nombre;
	}
	
	public void setContenido(Blob contenido){
		this.Contenido = contenido;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public Blob getContenido(){
		return this.Contenido;
	}

}
