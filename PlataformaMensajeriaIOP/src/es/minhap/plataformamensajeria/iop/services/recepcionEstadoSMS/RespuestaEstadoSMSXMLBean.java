package es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://recepcion.ws.plataformamensajeria.minhap.es/}responseStatusType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlType(name = "", propOrder = { "status", "idLote"})
@XmlRootElement(name = "Respuesta")
public class RespuestaEstadoSMSXMLBean {

	@XmlElement(name = "Status", required = true)
	protected ResponseStatusType status;
	@XmlElement(name = "idLote", required = true)
	protected Long idLote;

	/**
	 * Obtiene el valor de la propiedad status.
	 * 
	 * @return possible object is {@link ResponseStatusType }
	 * 
	 */
/*	public ResponseStatusType getStatus() {
		return status;s
	}
*/
	/**
	 * Define el valor de la propiedad status.
	 * 
	 * @param value
	 *            allowed object is {@link ResponseStatusType }
	 * 
	 */
	public void setStatus(ResponseStatusType value) {
		this.status = value;
	}
	
	
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	@Override
	public String toString() {
		return "RespuestaEstadoSMSXMLBean [statusDetails = " + status.getDetails() + ",statusCode = " + status.getStatusCode() + ", statusText = " + status.getStatusText() + "]";
	}

	public String toXML() throws PlataformaBusinessException {

		try {

			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(RespuestaEstadoSMSXMLBean.class).createMarshaller();
			marshaller.marshal(this, document);
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
			soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration("soap", "http://www.w3.org/2001/12/soap-envelope");
			soapMessage.getSOAPPart().getEnvelope().setPrefix("soap");
			soapMessage.getSOAPHeader().setPrefix("soap");
			soapMessage.getSOAPBody().setPrefix("soap");
			soapMessage.getSOAPBody().addDocument(document);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			String output = new String(outputStream.toByteArray());

			return output;
		} catch (Exception e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		}

	}
}
