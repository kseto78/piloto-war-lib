package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Avisos;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="mensajes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *        &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status", "avisos" })
@XmlRootElement(name = "Respuesta", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
public class RespuestaGetAvisosUsuario {

	@XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected AvisosResponseStatusType status;
	@XmlElement(name = "Avisos",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGetAvisosUsuario")
	protected Avisos avisos;

	/**
	 * @return the status
	 */
	public AvisosResponseStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(AvisosResponseStatusType status) {
		this.status = status;
	}

	
	/**
	 * @return the avisos
	 */
	public Avisos getAvisos() {
		return avisos;
	}

	/**
	 * @param avisos the avisos to set
	 */
	public void setAvisos(Avisos avisos) {
		this.avisos = avisos;
	}

	public String toXMLSMS(RespuestaGetAvisosUsuario resp) throws PlataformaBusinessException {
		RespuestaGetAvisosUsuario respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaGetAvisosUsuario.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();

		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		}
	}

}
