//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:09:37 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles;

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
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="statusCode" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}statusCode"/&gt;
 *         &lt;element name="statusText" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}statusText"/&gt;
 *         &lt;element name="details" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}details" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ayuda" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}ayuda" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "ayuda"
})
@XmlRootElement(name = "Respuesta", namespace="http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
public class Respuesta {

	private static String STATUSCODE_OK = "0000";
	private static String STATUSCODE_KO = "3001";
	private static String STATUSLOGINCODE_KO = "3002";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSDETAILS_KO = "Error en la peticion";
	private static String STATUSLOGIN_KO = "Error al loguearse en la Plataforma. Usuario y/o password incorrectos";
//	private static String STATUSDETAILS_OKNOLENSAJES = "No se ha procesado ningun mensaje";
	
	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";
	
    @XmlElement(name = "Status", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
	private ResponseAyudaStatusType status; 
    @XmlElement(name = "Ayuda", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
	private String ayuda;

 
    
public void loadObjectFromXML (String xmlRespuesta)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			Respuesta operacionesMensajes = (Respuesta) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,	operacionesMensajes);		
		
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		}
	}
	
	public String toXML(String ayuda) throws PlataformaBusinessException{
		Respuesta respuesta = this;
		
		if (null!= ayuda && !ayuda.isEmpty()){
			ResponseAyudaStatusType  status = new ResponseAyudaStatusType();
			status.setStatusCode(STATUSCODE_OK);
			status.setStatusText(STATUSTEXT_OK);
			status.setDetails(STATUSDETAILS_OK);
			respuesta.setStatus(status);
			respuesta.setAyuda(ayuda);
		}else{
			ResponseAyudaStatusType  status = new ResponseAyudaStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSTEXT_KO);
			status.setDetails(STATUSDETAILS_KO);
			respuesta.setStatus(status);
			respuesta.setAyuda(ayuda);
				
		}
		try {
			
	        JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);
			
			return writer.toString();
			
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		}
	}

	public String loginIncorrectotoXML() throws PlataformaBusinessException{
		
		Respuesta respuesta = this;
		ResponseAyudaStatusType  responseStatusType = new ResponseAyudaStatusType();
		responseStatusType.setStatusCode(STATUSLOGINCODE_KO);
		responseStatusType.setStatusText(STATUSLOGIN_KO);
		responseStatusType.setDetails(STATUSLOGIN_KO);
		respuesta.setStatus(responseStatusType);
		respuesta.setAyuda("");
		try {
			
			
	        JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);
			
			
			return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		}
	}


	/**
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	/**
	 * @return the status
	 */
	public ResponseAyudaStatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseAyudaStatusType status) {
		this.status = status;
	}
}
