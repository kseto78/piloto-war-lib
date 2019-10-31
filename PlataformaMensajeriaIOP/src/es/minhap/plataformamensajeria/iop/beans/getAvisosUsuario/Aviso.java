//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:09:37 PM CET 
//

package es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario;

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
@XmlType(name = "aviso", propOrder = { Aviso.R_CONST_1, Aviso.R_CONST_4, Aviso.R_CONST_3, Aviso.R_CONST_5, Aviso.R_CONST_2 })
public class Aviso {

	protected static final String R_CONST_1 = "IdAviso";
	protected static final String R_CONST_2 = "FechaEstado";
	protected static final String R_CONST_3 = "Cuerpo";
	protected static final String R_CONST_4 = "Titulo";
	protected static final String R_CONST_5 = "Estado";
	protected static final String R_CONST_6 = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario";
	@XmlElement(name = R_CONST_1, required = true, namespace = R_CONST_6)
	protected String IdAviso;
	@XmlElement(name = R_CONST_4, required = true, namespace = R_CONST_6)
	protected String Titulo;
	@XmlElement(name = R_CONST_3, namespace = R_CONST_6)
	protected String Cuerpo;
	@XmlElement(name = R_CONST_5, namespace = R_CONST_6)
	protected String Estado;
	@XmlElement(name = R_CONST_2, namespace = R_CONST_6)
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