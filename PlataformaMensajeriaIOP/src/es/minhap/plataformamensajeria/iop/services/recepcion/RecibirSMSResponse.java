
package es.minhap.plataformamensajeria.iop.services.recepcion;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",RecibirSMSResponse.R_CONST_1
})
@XmlRootElement(name = "Respuesta")
public class RecibirSMSResponse {

    protected static final String R_CONST_1 = "idLote";
	protected static final String R_CONST_2 = "soap";
	@XmlElement(name = "Status", required = true)
    protected ResponseStatusType status;
    @XmlElement(name = R_CONST_1, required = true)
    protected Integer idLote;
    
    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setStatus(ResponseStatusType value) {
        this.status = value;
    }
    
	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	@Override
	public String toString() {
		return "RecibirSMSResponse [statusDetails = " + status.getDetails() +",statusCode = " + status.getStatusCode()
				+ ", statusText = " + status.getStatusText()+ "]";
	}
	public String toXML() throws PlataformaBusinessException {

		try {
			
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(RecibirSMSResponse.class).createMarshaller();
			marshaller.marshal(this, document);
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
			soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(R_CONST_2, "http://www.w3.org/2001/12/soap-envelope");
			soapMessage.getSOAPPart().getEnvelope().setPrefix(R_CONST_2);
			soapMessage.getSOAPHeader().setPrefix(R_CONST_2);
			soapMessage.getSOAPBody().setPrefix(R_CONST_2);
			soapMessage.getSOAPBody().addDocument(document);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			return new String(outputStream.toByteArray());
		} catch (Exception e) {
			throw new PlataformaBusinessException(
					"Error generando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage());
		} 
		

	}
}
