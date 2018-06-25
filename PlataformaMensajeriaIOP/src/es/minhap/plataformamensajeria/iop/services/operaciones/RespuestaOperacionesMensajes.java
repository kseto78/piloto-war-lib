//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 08:23:39 AM CET 
//

package es.minhap.plataformamensajeria.iop.services.operaciones;

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
import es.minhap.plataformamensajeria.iop.util.Utils;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status" })
@XmlRootElement(name = "Respuesta", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
public class RespuestaOperacionesMensajes {

	@XmlElement(name = "Status",namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta", required = true)
	protected ResponseStatusType status;

	/**
	 * Obtiene el valor de la propiedad status.
	 * 
	 * @return possible object is {@link ResponseStatusType }
	 * 
	 */
	public ResponseStatusType getStatus() {
		return status;
	}

	/**
	 * Define el valor de la propiedad status.
	 * 
	 * @param value
	 *            allowed object is {@link ResponseStatusType }
	 * 
	 */
	public void setStatus(ResponseStatusType value) {
		this.status = value;
	}

	public void loadObjectFromXML(String xmlOperacionesMensajes)
			throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext
					.newInstance(RespuestaOperacionesMensajes.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlOperacionesMensajes);
			RespuestaOperacionesMensajes operacionesMensajes = (RespuestaOperacionesMensajes) unmarshaller
					.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,
					operacionesMensajes);

			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlOperacionesMensajes);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlOperacionesMensajes);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlOperacionesMensajes);
		}
	}

	public String toXML() throws PlataformaBusinessException {


			RespuestaOperacionesMensajes resupestaEstadoSMS = this;
			
			try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaOperacionesMensajes.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(resupestaEstadoSMS, writer);
//			jaxbMarshaller.marshal(resupestaEstadoSMS, System.out);
			
			return Utils.convertToUTF8(writer.toString());
			} catch (PropertyException e) {
				throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
			} catch (JAXBException e) {
				throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
			}
	
	}
}
