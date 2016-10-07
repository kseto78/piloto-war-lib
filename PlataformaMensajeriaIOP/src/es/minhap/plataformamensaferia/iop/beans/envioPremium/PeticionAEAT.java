package es.minhap.plataformamensaferia.iop.beans.envioPremium;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MessageStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "PeticionNotificacionEstadoSMS", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class PeticionAEAT {

    private static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Error generando la respuesta";
	@XmlElement(name = "MM7Version", required = true)
    protected String mm7Version;
    @XmlElement(name = "Recipient", required = true)
    protected String recipient;
    @XmlElement(name = "Sender", required = true)
    protected String sender;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "SMSText", required = true)
    protected String statusText;
    @XmlElement(name = "MMStatus", required = true)
    protected String mmStatus;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
//	public String getIdExterno() {
//		return idExterno;
//	}
//	public void setIdExterno(String idExterno) {
//		this.idExterno = idExterno;
//	}
//	public String getMessageStatus() {
//		return messageStatus;
//	}
//	public void setMessageStatus(String messageStatus) {
//		this.messageStatus = messageStatus;
//	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String toXMLEstado(ArrayList<SeguimientoMensaje> listaResultados, String sender, String recipient)  throws PlataformaBusinessException{
		
		for (SeguimientoMensaje mensaje : listaResultados) {
			this.setMessageId(mensaje.getIdExterno());
			this.setStatusText(mensaje.getEstado());
			this.setMmStatus(mensaje.getEstado());
		}
		this.setSender(sender);
		this.setRecipient(recipient);
		PeticionAEAT operacionesMensajes = this;
		
		try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(PeticionAEAT.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(operacionesMensajes, writer);
			jaxbMarshaller.marshal(operacionesMensajes, System.out);
			
			return Utils.convertToUTF8(writer.toString());
			
		} catch (Exception e) {
			return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
		}
	}
//	private void setMessageId(int idMensaje) {
//		// TODO Auto-generated method stub
//		this.messageId = ""+idMensaje;
//		
//	}
	public String getMm7Version() {
		return mm7Version;
	}
	public void setMm7Version(String mm7Version) {
		this.mm7Version = mm7Version;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMmStatus() {
		return mmStatus;
	}
	public void setMmStatus(String mmStatus) {
		this.mmStatus = mmStatus;
	}
    
}
