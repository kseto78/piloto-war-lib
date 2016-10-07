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
@XmlType(name = "", propOrder = { "usuario", "password", "notificacionId", "status", "idUsuario" })
@XmlRootElement(name = "PeticionNotificacionPush", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
public class NotificacionesPushXMLBean {

	@XmlElement(name = "Usuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	private String usuario;
	@XmlElement(name = "Password", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	private String password;
	@XmlElement(name = "NotificacionId", required= false, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	private String notificacionId;
	@XmlElement(name = "Status", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	private String status;
	@XmlElement(name = "IdUsuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	private String idUsuario;

	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the notificacionId
	 */
	public String getNotificacionId() {
		return notificacionId;
	}

	/**
	 * @param notificacionId the notificacionId to set
	 */
	public void setNotificacionId(String notificacionId) {
		this.notificacionId = notificacionId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void loadObjectFromXML(String xmlConsultaServicios) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(NotificacionesPushXMLBean.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlConsultaServicios);
			NotificacionesPushXMLBean consultasServicios = (NotificacionesPushXMLBean) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, consultasServicios);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlConsultaServicios);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlConsultaServicios);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlConsultaServicios);
		}
	}

	public String toXML() throws PlataformaBusinessException {

		NotificacionesPushXMLBean notificacionesPushBean = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NotificacionesPushXMLBean.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(notificacionesPushBean, writer);
			jaxbMarshaller.marshal(notificacionesPushBean, System.out);

			return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		}

	}
}
