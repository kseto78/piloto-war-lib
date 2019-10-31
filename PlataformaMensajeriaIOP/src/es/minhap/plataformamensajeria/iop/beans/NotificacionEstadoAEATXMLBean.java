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
@XmlType(name = "", propOrder = { "usuario", "password", "idUsuario", "idServicioMovil", "accion" })
@XmlRootElement(name = "PeticionRegistroUsuario", namespace = NotificacionEstadoAEATXMLBean.R_CONST_1)
public class NotificacionEstadoAEATXMLBean {

	protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	@XmlElement(name = "Usuario", required = true, namespace = R_CONST_1)
	private String usuario;
	@XmlElement(name = "Password", required = true, namespace = R_CONST_1)
	private String password;
	@XmlElement(name = "IdUsuario", required = true, namespace = R_CONST_1)
	private String idUsuario;
	@XmlElement(name = "IdServicioMovil", required = true, namespace = R_CONST_1)
	private String idServicioMovil;
	@XmlElement(name = "Accion", required = true, namespace = R_CONST_1)
	private String accion;

	public void loadObjectFromXML(String xmlUsuario) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(NotificacionEstadoAEATXMLBean.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlUsuario);
			NotificacionEstadoAEATXMLBean usuarios = (NotificacionEstadoAEATXMLBean) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, usuarios);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + R_CONST_2 + e.getMessage() + "\nXML:\n" + xmlUsuario);
		}
	}

	public String toXML() throws PlataformaBusinessException {

		NotificacionEstadoAEATXMLBean usuariosBean = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NotificacionEstadoAEATXMLBean.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(usuariosBean, writer);
//			jaxbMarshaller.marshal(usuariosBean, System.out);

			return writer.toString();
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + R_CONST_2 + e.getMessage());
		}

	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the idServicioMovil
	 */
	public String getIdServicioMovil() {
		return idServicioMovil;
	}

	/**
	 * @param idServicioMovil the idServicioMovil to set
	 */
	public void setIdServicioMovil(String idServicioMovil) {
		this.idServicioMovil = idServicioMovil;
	}
}
