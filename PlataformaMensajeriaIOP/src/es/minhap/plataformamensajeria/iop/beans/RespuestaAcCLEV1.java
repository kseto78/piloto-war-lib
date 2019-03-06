
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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
 *         &lt;element name="Status" type="{http://registrousuario.ws.plataformamensajeria.minhap.es/}responseStatusType"/>
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
    "status"
})
@XmlRootElement(name = "RespuestaNotificacionEstadoSMS",namespace = "http://misim.redsara.es/misim-bus-webapp/AcCLEV1Sal")
public class RespuestaAcCLEV1 {

	static Logger LOGGER = Logger.getLogger(RespuestaAcCLEV1.class);
    private static final String MENSAJE = "\nMensaje: ";
	private static final String XML = "\nXML:\n";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	@XmlElement(name = "Status", required = true,namespace = "http://misim.redsara.es/misim-bus-webapp/AcCLEV1Sal")
    protected ResponseStatusType status;
    

	public String toXML(RespuestaAcCLEV1 resp) throws PlataformaBusinessException {
		RespuestaAcCLEV1 respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaAcCLEV1.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();

		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + MENSAJE
					+ e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + MENSAJE
					+ e.getMessage());
		}
	}
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaAcCLEV1.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			RespuestaAcCLEV1 peticion = (RespuestaAcCLEV1) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause() + MENSAJE
					+ e.getMessage() + XML + xmlPeticion);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause() + MENSAJE
					+ e.getMessage() + XML + xmlPeticion);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause() + MENSAJE
					+ e.getMessage() + XML + xmlPeticion);
		}
	}
	
    public void operacion(PeticionAcCLEV1 parameters) { 
        System.out.println(parameters);
        try {
            
            ResponseStatusType _status = new ResponseStatusType();
            
            if (validarParametros(parameters)) {
                _status.setStatusCode("1000");
                _status.setStatusText("OK");
                _status.setDetails("Petición procesada correctamente");
            } else {
                _status.setStatusCode("0001");
                _status.setStatusText("KO");
                _status.setDetails("Faltan parámetros en la petición");
            }
            
            setStatus(_status);
            
        } catch (java.lang.Exception ex) {
            LOGGER.error("[RespuestaAcCLEV1]",ex);
            throw new RuntimeException(ex);
        }
    }

	private boolean validarParametros(PeticionAcCLEV1 parameters) {
		if (StringUtils.isEmpty(parameters.getIdExterno()) || StringUtils.isEmpty(parameters.getMessageId()) 
				|| StringUtils.isEmpty(parameters.getMessageStatus()) || StringUtils.isEmpty(parameters.getStatusText())) {
			return false;
		}
		return true;
	}	

	/**
	 * @return the status
	 */
	public ResponseStatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseStatusType status) {
		this.status = status;
	}
}
