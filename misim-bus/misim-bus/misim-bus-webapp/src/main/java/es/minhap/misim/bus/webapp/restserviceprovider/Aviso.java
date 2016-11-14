package es.minhap.misim.bus.webapp.restserviceprovider;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para mensaje complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensaje"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ErrorMensaje" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aviso", propOrder = { "IdAviso", "Titulo", "Cuerpo", "Estado", "FechaEstado" })
public class Aviso {

	@XmlElement(name = "IdAviso", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected String IdAviso;
	@XmlElement(name = "Titulo", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected String Titulo;
	@XmlElement(name = "Cuerpo", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected String Cuerpo;
	@XmlElement(name = "Estado", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected String Estado;
	@XmlElement(name = "FechaEstado", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected String FechaEstado;
	/**
	 * @return the idAviso
	 */
	public String getIdAviso() {
		return IdAviso;
	}
	/**
	 * @param idAviso the idAviso to set
	 */
	public void setIdAviso(String idAviso) {
		IdAviso = idAviso;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return Titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return Cuerpo;
	}
	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		Cuerpo = cuerpo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return Estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		Estado = estado;
	}
	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return FechaEstado;
	}
	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		FechaEstado = fechaEstado;
	}
	

}