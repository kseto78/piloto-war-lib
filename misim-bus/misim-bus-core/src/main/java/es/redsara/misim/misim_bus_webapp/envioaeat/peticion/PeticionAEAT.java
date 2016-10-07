package es.redsara.misim.misim_bus_webapp.envioaeat.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}MessageId"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdExterno"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}MessageStatus"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}StatusText"/>
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

    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "IdExterno", required = true)
    protected String idExterno;
    @XmlElement(name = "MessageStatus", required = true)
    protected String messageStatus;
    @XmlElement(name = "StatusText", required = true)
    protected String statusText;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getIdExterno() {
		return idExterno;
	}
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
//	public String toXMLEstado(ArrayList<Mensaje> listaResultados)  throws PlataformaBusinessException{
//		
//
//		Mensajes mensajes = new Mensajes();
//		
//		for (Mensaje mensaje : listaResultados) {
//			if (mensaje.getIdExterno()==null){
//				mensaje.setIdExterno("");
//			}
//			mensajes.getMensaje().add(mensaje);
//		}
//		
//		this.setMensajes(mensajes);
//		ResponseStatusType status = new ResponseStatusType();
//		status.setStatusCode(STATUSCODE_OK);
//		status.setStatusText(STATUSTEXT_OK);
//		status.setDetails(STATUSDETAILS_OK);
//		this.setStatus(status);
//		
//		Respuesta operacionesMensajes = this;
//		
//		try {
//        JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//		
//		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		
//		StringWriter writer = new StringWriter();
//		jaxbMarshaller.marshal(operacionesMensajes, writer);
////		jaxbMarshaller.marshal(operacionesMensajes, System.out);
//		
//		return Utils.convertToUTF8(writer.toString());
//
//		
//	} catch (Exception e) {
////		status.setStatusCode(STATUSCODE_KO);
////		status.setStatusText(STATUSCTEXT_KO);
////		status.setDetails(STATUSDETAILS_KO);
////		this.setStatus(status);
//		return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
//	}
//}
    
}
