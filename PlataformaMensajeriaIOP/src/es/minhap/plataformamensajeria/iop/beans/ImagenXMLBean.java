package es.minhap.plataformamensajeria.iop.beans;
import org.apache.axis.encoding.Base64;
public class ImagenXMLBean {

	private String cid;
	private byte[] contenido;
	private Integer idImagen = null;
	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}
	
	public Integer getIdImagen() {
		return idImagen;
	} 

	

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	public String getContenidoBase64(){
		return Base64.encode(contenido);
	}
	
	public String toXML(){
		//Generar el XML a partir de los otros objetos.
		return "";
	}
}
