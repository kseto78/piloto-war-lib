
package es.minhap.plataformamensajeria.iop.beans;

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
 *         &lt;element name="NumMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicioMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UidDispositivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TokenSession" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "numMovil",
    "idServicioMovil",
    "uidDispositivo",
	"tokenSession"
})
@XmlRootElement(name = "PeticionSolicitudRegistroMovil", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil")
public class PeticionSolicitudRegistroMovil {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil")
    protected String password;
    @XmlElement(name = "NumMovil", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil")
    protected String numMovil;
    @XmlElement(name = "IdServicioMovil", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil")
    protected String idServicioMovil;
    @XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionSolicitudRegistroMovil")
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionSolicitudRegistroMovil")
	protected String tokenSession;
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionSolicitudRegistroMovil.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			PeticionSolicitudRegistroMovil peticion = (PeticionSolicitudRegistroMovil) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		}
	}
	
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
	 * @return the numMovil
	 */
	public String getNumMovil() {
		return numMovil;
	}
	/**
	 * @param numMovil the numMovil to set
	 */
	public void setNumMovil(String numMovil) {
		this.numMovil = numMovil;
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
