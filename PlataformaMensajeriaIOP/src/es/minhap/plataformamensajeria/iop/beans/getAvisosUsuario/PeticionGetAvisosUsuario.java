package es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
 *          &lt;element name="IdDispositivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="IdPlataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="NumPagina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *            &lt;element name="TamPagina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "usuario", "password", "idDispositivo", "idServicio", "idPlataforma", "idUsuario", "numPagina", "tamPagina", "uidDispositivo", "tokenSession" })
@XmlRootElement(name = "PeticionGetAvisosUsuario", namespace = PeticionGetAvisosUsuario.R_CONST_1)
public class PeticionGetAvisosUsuario {

	protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario";
	@XmlElement(name = "Usuario", required = true, namespace = R_CONST_1)
	protected String usuario;
	@XmlElement(name = "Password", required = true, namespace = R_CONST_1)
	protected String password;
	@XmlElement(name = "IdDispositivo", required = true, namespace = R_CONST_1)
	protected String idDispositivo;
	@XmlElement(name = "IdServicio", required = true, namespace = R_CONST_1)
	protected String idServicio;
	@XmlElement(name = "IdPlataforma", required = true, namespace = R_CONST_1)
	protected String idPlataforma;
	@XmlElement(name = "IdUsuario", required = false, namespace = R_CONST_1)
	protected String idUsuario;
	@XmlElement(name = "NumPagina", required = false, namespace = R_CONST_1)
	protected String numPagina;
	@XmlElement(name = "TamPagina", required = false, namespace = R_CONST_1)
	protected String tamPagina;
	@XmlElement(name = "UidDispositivo", namespace = R_CONST_1)
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = R_CONST_1)
	protected String tokenSession;
	
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

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
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
	 * @return the idPlataforma
	 */
	public String getIdPlataforma() {
		return idPlataforma;
	}

	/**
	 * @param idPlataforma
	 *            the idPlataforma to set
	 */
	public void setIdPlataforma(String idPlataforma) {
		this.idPlataforma = idPlataforma;
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
	 * @return the numPagina
	 */
	public String getNumPagina() {
		return numPagina;
	}

	/**
	 * @param numPagina
	 *            the numPagina to set
	 */
	public void setNumPagina(String numPagina) {
		this.numPagina = numPagina;
	}

	/**
	 * @return the tamPagina
	 */
	public String getTamPagina() {
		return tamPagina;
	}

	/**
	 * @param tamPagina
	 *            the tamPagina to set
	 */
	public void setTamPagina(String tamPagina) {
		this.tamPagina = tamPagina;
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

	public void loadObjectFromXML(String xml) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionGetAvisosUsuario.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			PeticionGetAvisosUsuario pet = (PeticionGetAvisosUsuario) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, pet);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xml);
		}
	}
}