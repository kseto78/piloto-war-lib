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
@XmlType(name = "", propOrder = { "usuario", "password", "idUsuario", "idServicioMovil", "idDispositivo", "accion", "uidDispositivo", "tokenSession" })
@XmlRootElement(name = "PeticionRegistroUsuario", namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
public class RegistroUsuarioXMLBean {

	@XmlElement(name = "Usuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	private String usuario;
	@XmlElement(name = "Password", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	private String password;
	@XmlElement(name = "IdUsuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	private String idUsuario;
	@XmlElement(name = "IdServicioMovil", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	private String idServicioMovil;
	@XmlElement(name = "IdDispositivo", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	protected String idDispositivo;
	@XmlElement(name = "Accion", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
	private String accion;
	@XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioEnServicio")
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioEnServicio")
	protected String tokenSession;

	public void loadObjectFromXML(String xmlUsuario) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RegistroUsuarioXMLBean.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlUsuario);
			RegistroUsuarioXMLBean usuarios = (RegistroUsuarioXMLBean) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, usuarios);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlUsuario);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlUsuario);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlUsuario);
		}
	}

	public String toXML() throws PlataformaBusinessException {

		RegistroUsuarioXMLBean usuariosBean = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RegistroUsuarioXMLBean.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(usuariosBean, writer);
			jaxbMarshaller.marshal(usuariosBean, System.out);

			return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage());
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
	 * @param idServicioMovil
	 *            the idServicioMovil to set
	 */
	public void setIdServicioMovil(String idServicioMovil) {
		this.idServicioMovil = idServicioMovil;
	}

	/**
	 * @return the idDispositivo
	 */
	public String getIdDispositivo() {
		return idDispositivo;
	}

	/**
	 * @param idDispositivo
	 *            the idDispositivo to set
	 */
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	/**
	 * @return the uidDispositivo
	 */
	public String getUidDispositivo() {
		return uidDispositivo;
	}

	/**
	 * @param uidDispositivo the uidDispositivo to set
	 */
	public void setUidDispositivo(String uidDispositivo) {
		this.uidDispositivo = uidDispositivo;
	}

	/**
	 * @return the tokenSession
	 */
	public String getTokenSession() {
		return tokenSession;
	}

	/**
	 * @param tokenSession the tokenSession to set
	 */
	public void setTokenSession(String tokenSession) {
		this.tokenSession = tokenSession;
	}

}
