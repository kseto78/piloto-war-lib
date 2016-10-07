package es.minhap.plataformamensajeria.iop.beans;
import org.apache.axis.encoding.Base64;
public class AdjuntosXMLBean {

	public void setIdAdjunto(Integer idAdjunto) { 
		this.idAdjunto = idAdjunto;
	}
	
	private String nombre;
	private byte[] contenido;
	private Integer idAdjunto = null;
	public Integer getIdAdjunto() {
		return idAdjunto; 
	} 

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	public String getContenidoBase64(){
		String contenidoBase64 = Base64.encode(contenido);
		return contenidoBase64;
	}
	
	public String toXML(){
		//Generar el XML a partir de los otros objetos.
		return "";
	}
}
