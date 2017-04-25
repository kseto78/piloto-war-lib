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
	"user",
	"password",
	"sender",
    "recipient",
    "mensajeId",
    "messajeStatus",
    "statusText"
})
@XmlRootElement(name = "recepcionEstadoSMSXMLBean")
public class RecepcionEstadoSMSXMLBean {

	@XmlElement(name = "Sender", required = true)
	private String sender;
	@XmlElement(name = "Recipient", required = true)
	private String recipient;
	@XmlElement(name = "MessageId", required = true)
	private String mensajeId;
	@XmlElement(name = "MessageStatus", required = true)
	private String messajeStatus;
	@XmlElement(name = "StatusText")
	private String statusText;
	@XmlElement(name = "User", required = true)
	private String user;
	@XmlElement(name = "Password", required = true)
	private String password;


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(String mensajeId) {
		this.mensajeId = mensajeId;
	}

	public String getMessajeStatus() {
		return messajeStatus;
	}

	public void setMessajeStatus(String messajeStatus) {
		this.messajeStatus = messajeStatus;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void loadObjectFromXML(String xmlRecepcionEstadoSMS)throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RecepcionEstadoSMSXMLBean.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRecepcionEstadoSMS);
			RecepcionEstadoSMSXMLBean recepcionEstado = (RecepcionEstadoSMSXMLBean) unmarshaller
					.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,	recepcionEstado);

			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRecepcionEstadoSMS);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRecepcionEstadoSMS);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRecepcionEstadoSMS);
		}
	}

	public String toXML() throws PlataformaBusinessException {

		RecepcionEstadoSMSXMLBean recepcionEstadoSMS = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RecepcionEstadoSMSXMLBean.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(recepcionEstadoSMS, writer);
//			jaxbMarshaller.marshal(recepcionEstadoSMS, System.out);

			return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException(
					"Error generando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error generando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage());
		}

	}


}
