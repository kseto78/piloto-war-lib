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
    "usuario",
    "password",
    "idUsuario",
    "uidDispositivo",
    "tokenSession"
})
@XmlRootElement(name = "PeticionConsultaServiciosDisponibles", namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
public class ServiciosDisponiblesXMLBean {

	    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
		private String usuario;
	    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
		private String password;
	    @XmlElement(name = "IdUsuario", namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
	    private String idUsuario;
	    @XmlElement(name = "UidDispositivo", namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
	    protected String uidDispositivo;
	    @XmlElement(name = "TokenSession", namespace="http://misim.redsara.es/misim-bus-webapp/peticionConsultaServiciosDisponibles")
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


	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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

	public void loadObjectFromXML (String xmlConsultaServicios)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ServiciosDisponiblesXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlConsultaServicios);
		ServiciosDisponiblesXMLBean consultasServicios = (ServiciosDisponiblesXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, consultasServicios);
		
		
		
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaServicios);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaServicios);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaServicios);
		}
	}
	
	public String toXML() throws PlataformaBusinessException{
		
		ServiciosDisponiblesXMLBean consultasServiciosBean = this;
		
		try {
        JAXBContext jaxbContext = JAXBContext.newInstance(ServiciosDisponiblesXMLBean.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(consultasServiciosBean, writer);
		jaxbMarshaller.marshal(consultasServiciosBean, System.out);
		
		return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		}
		
	}
}
