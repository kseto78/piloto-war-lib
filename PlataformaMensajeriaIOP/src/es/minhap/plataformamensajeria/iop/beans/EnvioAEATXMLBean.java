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
    "idExterno",
    "codOrganismoPagadorSMS",
    "cuerpo",
    "destinatario",
    "deliveryReportURL"
})
@XmlRootElement(name = "Peticion", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class EnvioAEATXMLBean {

    @XmlElement(name = "IdExterno", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String idExterno;
    @XmlElement(name = "CodOrganismoPagadorSMS", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String codOrganismoPagadorSMS;
    @XmlElement(name = "Cuerpo", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String cuerpo;
    @XmlElement(name = "Destinatario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String destinatario;
    @XmlElement(name = "DeliveryReportURL", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String deliveryReportURL;

public void loadObjectFromXML (String xmlEnvio)throws PlataformaBusinessException {
	
	JAXBContext jaxbContext;
	try {
		jaxbContext = JAXBContext.newInstance(EnvioAEATXMLBean.class);
	
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	StringReader reader = new StringReader(xmlEnvio);
	EnvioAEATXMLBean usuarios = (EnvioAEATXMLBean) unmarshaller.unmarshal(reader);
	
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
	
	EnvioAEATXMLBean envioAEATXML = this;
	
	try {
    JAXBContext jaxbContext = JAXBContext.newInstance(UsuariosXMLBean.class);
	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	StringWriter writer = new StringWriter();
	jaxbMarshaller.marshal(envioAEATXML, writer);
	jaxbMarshaller.marshal(envioAEATXML, System.out);
	
	return writer.toString();
	} catch (PropertyException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	} catch (JAXBException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	}
	
}

public String getIdExterno() {
	return idExterno;
}

public void setIdExterno(String idExterno) {
	this.idExterno = idExterno;
}

public String getCodOrganismoPagadorSMS() {
	return codOrganismoPagadorSMS;
}

public void setCodOrganismoPagadorSMS(String codOrganismoPagadorSMS) {
	this.codOrganismoPagadorSMS = codOrganismoPagadorSMS;
}

public String getCuerpo() {
	return cuerpo;
}

public void setCuerpo(String cuerpo) {
	this.cuerpo = cuerpo;
}

public String getDestinatario() {
	return destinatario;
}

public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
}

public String getDeliveryReportURL() {
	return deliveryReportURL;
}

public void setDeliveryReportURL(String deliveryReportURL) {
	this.deliveryReportURL = deliveryReportURL;
}



}
