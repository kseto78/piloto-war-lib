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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "password"
})
@XmlRootElement(name = "PeticionSolicitudAyuda", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudAyuda")
public class AyudaXMLBean {

    private static final String XML = "\nXML:\n";
	private static final String MENSAJE = "\nMensaje: ";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	@XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudAyuda")
	private String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudAyuda")
	private String password;
    
    private static Logger LOG = LoggerFactory.getLogger(AyudaXMLBean.class);
	
	
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

	public void loadObjectFromXML (String xmlUsuario)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(AyudaXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlUsuario);
		AyudaXMLBean usuarios = (AyudaXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, usuarios);
		
		
		
		} catch (JAXBException e) {
			LOG.error("Error en AyudaXMLBean", e);
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause()+MENSAJE + e.getMessage()+ XML+xmlUsuario);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause()+MENSAJE + e.getMessage()+ XML+xmlUsuario);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause()+MENSAJE + e.getMessage()+ XML+xmlUsuario);
		}
	}
	
	public String toXML() throws PlataformaBusinessException{
		
		AyudaXMLBean ayudaBean = this;
		
		try {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsuariosXMLBean.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(ayudaBean, writer);
		
		return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+MENSAJE + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+MENSAJE + e.getMessage());
		}
		
	}

	
	
}
