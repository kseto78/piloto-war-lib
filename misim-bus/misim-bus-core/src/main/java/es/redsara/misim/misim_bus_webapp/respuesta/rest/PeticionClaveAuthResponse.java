package es.redsara.misim.misim_bus_webapp.respuesta.rest;

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
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DispositivoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SAMLResponse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "password",
    "dispositivoId",
    "samlResponse",
    "remoteHost"
})
@XmlRootElement(name = "PeticionClaveAuthResponse", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
public class PeticionClaveAuthResponse {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String password;
    @XmlElement(name = "IdServicio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idServicio;
    @XmlElement(name = "IdPlataforma", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idPlataforma;
    @XmlElement(name = "DispositivoId", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String dispositivoId;
    @XmlElement(name = "SAMLResponse", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String samlResponse;
    @XmlElement(name = "RemoteHost", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String remoteHost;
	
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
	 * @return the dispositivoId
	 */
	public String getDispositivoId() {
		return dispositivoId;
	}
	/**
	 * @param dispositivoId the dispositivoId to set
	 */
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	/**
	 * @return the samlResponse
	 */
	public String getSamlResponse() {
		return samlResponse;
	}
	/**
	 * @param samlResponse the samlResponse to set
	 */
	public void setSamlResponse(String samlResponse) {
		this.samlResponse = samlResponse;
	}
	
	
	/**
	 * @return the idServicio
	 */
	public String getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
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
	 * @param idPlataforma the idPlataforma to set
	 */
	public void setIdPlataforma(String idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public void loadObjectFromXML(String xmlSamlResponse)
			throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionClaveAuthResponse.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlSamlResponse);
			PeticionClaveAuthResponse respuesta = (PeticionClaveAuthResponse) unmarshaller
					.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,
					respuesta);

			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlSamlResponse);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlSamlResponse);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlSamlResponse);
		}
	}
}
