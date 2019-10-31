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

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Endpoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Auth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "usuario", "password", "endpoint", "key", "auth", "idServicio", "idUsuario", "accion" })
@XmlRootElement(name = "PeticionRegistroUsuarioWebPush", namespace = PeticionRegistroUsuarioWebPush.R_CONST_1)
public class PeticionRegistroUsuarioWebPush {

	protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	@XmlElement(name = "Usuario", required = true, namespace = R_CONST_1)
	protected String usuario;
	@XmlElement(name = "Password", required = true, namespace = R_CONST_1)
	protected String password;
	@XmlElement(name = "Endpoint", required = true, namespace = R_CONST_1)
	protected String endpoint;
	@XmlElement(name = "Key", namespace = R_CONST_1)
	protected String key;
	@XmlElement(name = "Auth", namespace = R_CONST_1)
	protected String auth;
	@XmlElement(name = "IdServicio", namespace = R_CONST_1)
	protected String idServicio;
	@XmlElement(name = "IdUsuario", namespace = R_CONST_1)
	protected String idUsuario;
	@XmlElement(name = "Accion", namespace = R_CONST_1)
	protected String accion;

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
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the idServicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio
	 *            the idServicio to set
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
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

	public void loadObjectFromXML(String xmlUsuario) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionRegistroUsuarioWebPush.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlUsuario);
			PeticionRegistroUsuarioWebPush usuarios = (PeticionRegistroUsuarioWebPush) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, usuarios);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + R_CONST_2
					+ e.getMessage() + "\nXML:\n" + xmlUsuario);
		}
	}

	public String toXML() throws PlataformaBusinessException {

		PeticionRegistroUsuarioWebPush usuariosBean = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PeticionRegistroUsuarioWebPush.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(usuariosBean, writer);
			// jaxbMarshaller.marshal(usuariosBean, System.out);

			return writer.toString();
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + R_CONST_2
					+ e.getMessage());
		}

	}

}
