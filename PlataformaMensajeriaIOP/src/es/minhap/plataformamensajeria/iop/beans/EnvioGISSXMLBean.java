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
@XmlType(name = "", propOrder = {
		"idPeticion",
		"usuSistemaEnvio",
		"passSistemaEnvio",
		"aplicacion",
		"numeroTelefonoDestino",
		"contenidoMsj"
})
@XmlRootElement(name = "Peticion", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class EnvioGISSXMLBean {

    @XmlElement(name = "IdPeticion", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String idPeticion;
    @XmlElement(name = "UsuSistemaEnvio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String usuSistemaEnvio;
    @XmlElement(name = "PassSistemaEnvio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String passSistemaEnvio;
    @XmlElement(name = "Aplicacion", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String aplicacion;
    @XmlElement(name = "NumeroTelefonoDestino", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String numeroTelefonoDestino;
    @XmlElement(name = "ContenidoMsj", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String contenidoMsj;
    

public void loadObjectFromXML (String xmlEnvio)throws PlataformaBusinessException {
	
	JAXBContext jaxbContext;
	try {
		jaxbContext = JAXBContext.newInstance(EnvioGISSXMLBean.class);
	
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	StringReader reader = new StringReader(xmlEnvio);
	EnvioGISSXMLBean usuarios = (EnvioGISSXMLBean) unmarshaller.unmarshal(reader);
	
	org.apache.commons.beanutils.BeanUtils.copyProperties(this, usuarios);
	
	
	
	} catch (JAXBException e) {
		e.printStackTrace();
		throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlEnvio);
	} catch (IllegalAccessException e) {
		throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlEnvio);
	} catch (InvocationTargetException e) {
		throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlEnvio);
	}
}

public String toXML() throws PlataformaBusinessException{
	
	EnvioGISSXMLBean envioXML = this;
	
	try {
    JAXBContext jaxbContext = JAXBContext.newInstance(EnvioGISSXMLBean.class);
	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	StringWriter writer = new StringWriter();
	jaxbMarshaller.marshal(envioXML, writer);
//	jaxbMarshaller.marshal(envioXML, System.out);
	
	return writer.toString();
	} catch (PropertyException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	} catch (JAXBException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	}
	
}

/**
 * @return the idPeticion
 */
public String getIdExterno() {
	return idPeticion;
}

/**
 * @param idPeticion the idPeticion to set
 */
public void setIdPeticion(String idPeticion) {
	this.idPeticion = idPeticion;
}

/**
 * @return the usuSistemaEnvio
 */
public String getUsuSistemaEnvio() {
	return usuSistemaEnvio;
}

/**
 * @param usuSistemaEnvio the usuSistemaEnvio to set
 */
public void setUsuSistemaEnvio(String usuSistemaEnvio) {
	this.usuSistemaEnvio = usuSistemaEnvio;
}

/**
 * @return the passSistemaEnvio
 */
public String getCodOrganismoPagadorSMS() {
	return passSistemaEnvio;
}

/**
 * @param passSistemaEnvio the passSistemaEnvio to set
 */
public void setCodOrganismoPagadorSMS(String passSistemaEnvio) {
	this.passSistemaEnvio = passSistemaEnvio;
}

/**
 * @return the aplicacion
 */
public String getAplicacion() {
	return aplicacion;
}

/**
 * @param aplicacion the aplicacion to set
 */
public void setAplicacion(String aplicacion) {
	this.aplicacion = aplicacion;
}

/**
 * @return the numeroTelefonoDestino
 */
public String getDestinatario() {
	return numeroTelefonoDestino;
}

/**
 * @param numeroTelefonoDestino the numeroTelefonoDestino to set
 */
public void setDestinatario(String numeroTelefonoDestino) {
	this.numeroTelefonoDestino = numeroTelefonoDestino;
}

/**
 * @return the contenidoMsj
 */
public String getCuerpo() {
	return contenidoMsj;
}

/**
 * @param contenidoMsj the contenidoMsj to set
 */
public void setCuerpo(String contenidoMsj) {
	this.contenidoMsj = contenidoMsj;
}

/**
 * @return the passSistemaEnvio
 */
public String getPassSistemaEnvio() {
	return passSistemaEnvio;
}

/**
 * @param passSistemaEnvio the passSistemaEnvio to set
 */
public void setPassSistemaEnvio(String passSistemaEnvio) {
	this.passSistemaEnvio = passSistemaEnvio;
}

/**
 * @return the numeroTelefonoDestino
 */
public String getNumeroTelefonoDestino() {
	return numeroTelefonoDestino;
}

/**
 * @param numeroTelefonoDestino the numeroTelefonoDestino to set
 */
public void setNumeroTelefonoDestino(String numeroTelefonoDestino) {
	this.numeroTelefonoDestino = numeroTelefonoDestino;
}

/**
 * @return the contenidoMsj
 */
public String getContenidoMsj() {
	return contenidoMsj;
}

/**
 * @param contenidoMsj the contenidoMsj to set
 */
public void setContenidoMsj(String contenidoMsj) {
	this.contenidoMsj = contenidoMsj;
}

/**
 * @return the idPeticion
 */
public String getIdPeticion() {
	return idPeticion;
}

}
