package es.minhap.plataformamensajeria.iop.beans;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "messageId"
})
@XmlRootElement(name = "Respuesta", namespace=RespuestaEnvioXMLBean.R_CONST_1)
public class RespuestaEnvioXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	@XmlElement(name = "Status", required = true, namespace=R_CONST_1)
    protected ResponseStatusTypeEnvioSMS status;
    @XmlElement(name = "MessageId", required = true, namespace=R_CONST_1)
    protected String messageId;

	public void loadObjectFromXML (String xmlRespuesta)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaEnvioXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlRespuesta);
		RespuestaEnvioXMLBean respuestaEnvio = (RespuestaEnvioXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, respuestaEnvio);
		
		
		
		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+R_CONST_2 + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		}
	}
	
	public String toXML() throws PlataformaBusinessException{
		
		RespuestaEnvioXMLBean consultasServiciosBean = this;
		
		try {
        JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaEnvioXMLBean.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(consultasServiciosBean, writer);
//		jaxbMarshaller.marshal(consultasServiciosBean, System.out);
		
		return writer.toString();
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+R_CONST_2 + e.getMessage());
		}
		
	}

	@Override
	public String toString() {
		return "Respuesta [status=" + status + ", messageId=" + messageId + "]";
	}

	public ResponseStatusTypeEnvioSMS getStatus() {
		return status;
	}

	public void setStatus(ResponseStatusTypeEnvioSMS status) {
		this.status = status;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
