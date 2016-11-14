//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 10:06:23 AM CET 
//

package es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.common.properties.PropertiesServices;
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
 *         &lt;element name="SeguimientoMensajes" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}SeguimientoMensajes" minOccurs="0"/&gt;
 *         &lt;element name="Historial" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}historial" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status", "seguimientoMensajes", "historial" })
@XmlRootElement(name = "Respuesta", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Respuesta {
	private static Properties p = new Properties();
	private static String STATUSCODE_OK = "1000";
	private static String STATUSCODE_KO = "0998";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSCTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSDETAILS_KO = "Error en la Peticion";
	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";

	@XmlElement(name = "Status", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta", required = true)
	protected ResponseStatusType status;
	@XmlElement(name = "SeguimientoMensajes", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
	protected SeguimientoMensajes seguimientoMensajes;
	@XmlElement(name = "Historial", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
	protected Historial historial;

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

	/**
	 * Obtiene el valor de la propiedad mensajes.
	 * 
	 * @return possible object is {@link SeguimientoMensajes }
	 * 
	 */
	public SeguimientoMensajes getSeguimientoMensajes() {
		return seguimientoMensajes;
	}

	/**
	 * Define el valor de la propiedad mensajes.
	 * 
	 * @param value
	 *            allowed object is {@link SeguimientoMensajes }
	 * 
	 */
	public void setSeguimientoMensajes(SeguimientoMensajes value) {
		this.seguimientoMensajes = value;
	}

	/**
	 * Obtiene el valor de la propiedad historial.
	 * 
	 * @return possible object is {@link Historial }
	 * 
	 */
	public Historial getHistorial() {
		return historial;
	}

	/**
	 * Define el valor de la propiedad historial.
	 * 
	 * @param value
	 *            allowed object is {@link Historial }
	 * 
	 */
	public void setHistorial(Historial value) {
		this.historial = value;
	}

	public void loadObjectFromXML(String xmlRespuesta) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			Respuesta respuesta = (Respuesta) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, respuesta);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlRespuesta);
		}
	}

	public String toXMLHistorial(ArrayList<Registro> listaResultados) throws PlataformaBusinessException {

		//
		Historial historial = new Historial();

		for (Registro registro : listaResultados) {
			if (registro.getServidor() == null) {
				registro.setServidor("");
			}
			historial.getHistorico().add(registro);
		}

		this.setHistorial(historial);
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(STATUSCODE_OK);
		status.setStatusText(STATUSTEXT_OK);
		status.setDetails(STATUSDETAILS_OK);
		this.setStatus(status);

		Respuesta operacionesMensajes = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(operacionesMensajes, writer);
			// jaxbMarshaller.marshal(operacionesMensajes, System.out);

			return Utils.convertToUTF8(writer.toString());

		} catch (Exception e) {
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(STATUSDETAILS_KO);
			this.setStatus(status);
			return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
		}
	}

	public String toXML(Respuesta resp) throws PlataformaBusinessException {

		Respuesta operacionesMensajes = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(operacionesMensajes, writer);

			return Utils.convertToUTF8(writer.toString());

		} catch (Exception e) {
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(STATUSDETAILS_KO);
			this.setStatus(status);
			return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
		}
	}

	public String toXMLEstado(ArrayList<SeguimientoMensaje> listaResultados) throws PlataformaBusinessException {

		return toXMLEstado(listaResultados, null);
	}

	public String toXMLEstado(ArrayList<SeguimientoMensaje> listaResultados, PropertiesServices ps)
			throws PlataformaBusinessException {

		SeguimientoMensajes mensajes = new SeguimientoMensajes();
		for (SeguimientoMensaje mensaje : listaResultados) {
			if (mensaje.getIdExterno() == null) {
				mensaje.setIdExterno("");
			}
			if (null != ps) {
				String servicioGISS = ps.getMessage("giss.servicio.sms.premium", null, null, null);
				String codEstadoPEI = ps.getMessage("giss.servicio.sms.premium.estado.PEI", null, null, null);
				String codEstadoPO = ps.getMessage("giss.servicio.sms.premium.estado.PO", null, null, null);
				String codEstadoEnviado = ps.getMessage("giss.servicio.sms.premium.estado.Enviado", null, null, null);
				String codEstadoAnulado = ps.getMessage("giss.servicio.sms.premium.estado.Anulado", null, null, null);
				if (mensaje.getIdServicio() == Integer.valueOf(servicioGISS).intValue()) {
					switch (mensaje.getIdEstado()) {
					case 1:
						if (null != codEstadoEnviado) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoEnviado));
						}
						break;
					case 2:
						if (null != codEstadoPEI) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoPEI));
						}
						break;
					case 3:
						if (null != codEstadoPEI) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoPEI));
						}
						break;
					case 4:
						if (null != codEstadoAnulado) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoAnulado));
						}
						break;
					case 6:
						if (null != codEstadoPO) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoPO));
						}
						break;
					case 9:
						if (null != codEstadoPEI) {
							mensaje.setIdEstado(Integer.parseInt(codEstadoPEI));
						}
						break;
					default:
						break;
					}
				}
			}
			mensajes.getMensaje().add(mensaje);
		}

		this.setSeguimientoMensajes(mensajes);
		ResponseStatusType status = new ResponseStatusType();
		status.setStatusCode(STATUSCODE_OK);
		status.setStatusText(STATUSTEXT_OK);
		status.setDetails(STATUSDETAILS_OK);
		this.setStatus(status);

		Respuesta operacionesMensajes = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(operacionesMensajes, writer);

			return Utils.convertToUTF8(writer.toString());

		} catch (Exception e) {
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSCTEXT_KO);
			status.setDetails(STATUSDETAILS_KO);
			this.setStatus(status);
			return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
		}
	}

}
