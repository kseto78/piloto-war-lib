
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
@XmlRootElement(name = "envioAplicacioResponse",namespace = RespuestaEnvioAplicacion.R_CONST_1)
public class RespuestaEnvioAplicacion {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	private static final String XML = "\nXML:\n";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	@XmlElement(name = "Status", required = true,namespace = R_CONST_1)
    protected ResponseStatusType status;
    

	public String toXML(RespuestaEnvioAplicacion resp) throws PlataformaBusinessException {
		RespuestaEnvioAplicacion respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaEnvioAplicacion.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + R_CONST_2
					+ e.getMessage());
		}
	}
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaEnvioAplicacion.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			RespuestaEnvioAplicacion peticion = (RespuestaEnvioAplicacion) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause() + R_CONST_2
					+ e.getMessage() + XML + xmlPeticion);
		}
	}
	
    public void recibirSmsAplicacion(PeticionEnvioAplicacion parameters) { 
        System.out.println(parameters);
        try {
            
            ResponseStatusType status = new ResponseStatusType();
            
            if (validarParametros(parameters)) {
                status.setStatusCode("1000");
                status.setStatusText("OK");
                status.setDetails("Petición procesada correctamente");
            } else {
                status.setStatusCode("0001");
                status.setStatusText("KO");
                status.setDetails("Faltan parámetros en la petición");
            }
            
            setStatus(status);
            
        } catch (java.lang.Exception ex) {
            throw new RuntimeException(ex);
        }
    }

	private boolean validarParametros(PeticionEnvioAplicacion parameters) {
		return !StringUtils.isEmpty(parameters.getUser()) && !StringUtils.isEmpty(parameters.getPassword()) && !StringUtils.isEmpty(parameters.getSender()) && !StringUtils.isEmpty(parameters.getRecipient()) && !StringUtils.isEmpty(parameters.smsText) && !StringUtils.isEmpty(parameters.getLoteId()) && !StringUtils.isEmpty(parameters.getMessageId());
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
