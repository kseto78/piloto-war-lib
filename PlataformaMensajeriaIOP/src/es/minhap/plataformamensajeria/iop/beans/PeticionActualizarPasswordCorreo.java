
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
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password_new" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "password_new"
})
@XmlRootElement(name = "ActualizarPasswordPeticion", namespace=PeticionActualizarPasswordCorreo.R_CONST_1)
public class PeticionActualizarPasswordCorreo {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Usuario", required = true, namespace=R_CONST_1)
    protected String usuario;
    @XmlElement(name = "Password_New", required = true, namespace=R_CONST_1)
    protected String password_new;
   
	
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionActualizarPasswordCorreo.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			PeticionActualizarPasswordCorreo peticion = (PeticionActualizarPasswordCorreo) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
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
	 * @return the password_new
	 */
	public String getPassword_new() {
		return password_new;
	}



	/**
	 * @param passwordNew the password_new to set
	 */
	public void setPassword_new(String passwordNew) {
		this.password_new = passwordNew;
	}
	
	
}
