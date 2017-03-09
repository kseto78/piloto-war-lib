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
@XmlType(name = "", propOrder = { "usuario", "password", "servicioId", "nombreUsuario", "plataformaId", "tokenUsuario",
		"dispositivoId", "uidDispositivo", "tokenSession" })
@XmlRootElement(name = "Peticion", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
public class UsuariosXMLBean {

	@XmlElement(name = "Usuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String usuario;
	@XmlElement(name = "Password", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String password;
	@XmlElement(name = "Servicio", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String servicioId;
	@XmlElement(name = "IdUsuario", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String nombreUsuario;
	@XmlElement(name = "Plataforma", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String plataformaId;
	@XmlElement(name = "IdRegistro", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String tokenUsuario;
	@XmlElement(name = "IdDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	private String dispositivoId;
	@XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
	protected String tokenSession;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getServicioId() {
		return servicioId;
	}

	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlataformaId() {
		return plataformaId;
	}

	public void setPlataformaId(String plataformaId) {
		this.plataformaId = plataformaId;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public String getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	/**
	 * @return the uidDispositivo
	 */
	public String getUidDispositivo() {
		return uidDispositivo;
	}

	/**
	 * @param uidDispositivo
	 *            the uidDispositivo to set
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
	 * @param tokenSession
	 *            the tokenSession to set
	 */
	public void setTokenSession(String tokenSession) {
		this.tokenSession = tokenSession;
	}

	public void loadObjectFromXML(String xmlUsuario) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(UsuariosXMLBean.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlUsuario);
			UsuariosXMLBean usuarios = (UsuariosXMLBean) unmarshaller.unmarshal(reader);

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

		UsuariosXMLBean usuariosBean = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UsuariosXMLBean.class);
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

}
