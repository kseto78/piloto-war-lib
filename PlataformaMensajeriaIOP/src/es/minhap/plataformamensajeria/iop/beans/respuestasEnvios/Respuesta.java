//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:09:37 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.respuestasEnvios;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
 *         &lt;element name="Status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/&gt;
 *         &lt;element name="Lote" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}lote"/&gt;
 *         &lt;element name="Mensajes" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}mensajes" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "lote",
    "mensajes"
})
@XmlRootElement(name = "Respuesta", namespace=Respuesta.R_CONST_2)
public class Respuesta {

	protected static final String R_CONST_1 = "\\nXML:\\n";
	protected static final String R_CONST_2 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	protected static final String R_CONST_3 = "\\nMensaje: ";
	protected static final String R_CONST_4 = "Error procesando el XML.\\nCausa: ";
	private static String STATUSCODE_OK = "1000";
	private static String STATUSCODE_KO = "0998";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSDETAILS_KO = "Error en la peticion";
	private static String STATUSDETAILS_OKNOLENSAJES = "No se ha procesado ningun mensaje";
	
	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";
	
    @XmlElement(name = "Status", required = true, namespace=R_CONST_2)
    protected ResponseStatusType status;
    @XmlElement(name = "Lote", required = true, namespace=R_CONST_2)
    protected Lote lote;
    @XmlElement(name = "Mensajes", namespace=R_CONST_2)
    protected List<Mensajes> mensajes;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setStatus(ResponseStatusType value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad lote.
     * 
     * @return
     *     possible object is
     *     {@link Lote }
     *     
     */
    public Lote getLote() {
        return lote;
    }

    /**
     * Define el valor de la propiedad lote.
     * 
     * @param value
     *     allowed object is
     *     {@link Lote }
     *     
     */
    public void setLote(Lote value) {
        this.lote = value;
    }

    /**
     * Gets the value of the mensajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mensajes }
     * 
     * 
     */
    public List<Mensajes> getMensajes() {
        if (mensajes == null) {
            mensajes = new ArrayList<>();
        }
        return this.mensajes;
    }
    
    
    public void loadObjectFromXML (String xmlRespuesta)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlRespuesta);
		Respuesta operacionesMensajes = (Respuesta) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, operacionesMensajes);
		
		
		
		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException(R_CONST_4 + e.getCause()+R_CONST_3 + e.getMessage()+ R_CONST_1+xmlRespuesta);
		}
	}
    
    public Respuesta loadObjectFromXMLWithList (String xmlRespuesta)throws PlataformaBusinessException {
    	Respuesta respuesta = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlRespuesta);
		respuesta  = (Respuesta) unmarshaller.unmarshal(reader);


		} catch (JAXBException e) {
			throw new PlataformaBusinessException(R_CONST_4 + e.getCause()+R_CONST_3 + e.getMessage()+ R_CONST_1+xmlRespuesta);
		}
		
		return respuesta;
	}
	
	public String toXMLSMS(Integer idLote, ArrayList<es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje> listaMensajesProcesados, ArrayList<String> listaErroresGenerales,
			ArrayList<String> listaErroresLote) throws PlataformaBusinessException{
		boolean error = false;
		
		Respuesta respuesta = this;
		
		
		
		if (listaErroresLote != null && !listaErroresLote.isEmpty()){
			error = true;
			Lote lote = new Lote();
			for (String string : listaErroresLote) {
				lote.idLote = idLote.toString();
				ResponseStatusType status =new ResponseStatusType();
				status.setStatusCode(STATUSCODE_KO);
				status.setStatusText(STATUSDETAILS_KO);
				status.setDetails(string);
				lote.setErrorLote(status);
			}
			this.setLote(lote);
		}else{
			Lote lote = new Lote();
			if (idLote != null){
				lote.idLote = idLote.toString();
			}else{
				lote.idLote ="";
			}
			this.setLote(lote);
		}
				
		
		if (listaMensajesProcesados != null && !listaMensajesProcesados.isEmpty()){
						
			this.mensajes = new ArrayList<>();
			
			for (es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje mensaje : listaMensajesProcesados) {
				mensaje.setIdExterno((null == mensaje.getIdExterno())? "" : mensaje.getIdExterno());
				Mensajes msj = new Mensajes();
				msj.setMensaje(mensaje);
				this.mensajes.add(msj);
				if (mensaje.getErrorMensaje() != null){
					error = true;
				}
			}
			
		}

		if (error||(listaErroresGenerales != null && !listaErroresGenerales.isEmpty())){
			
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSTEXT_KO);
			if (!listaErroresGenerales.isEmpty()){
				status.setDetails(listaErroresGenerales.get(0));
			}else{
				status.setDetails(STATUSDETAILS_KO);
			}
			this.setStatus(status);
		}else{
			if (!listaMensajesProcesados.isEmpty()){
				ResponseStatusType status = new ResponseStatusType();
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OK);
				this.setStatus(status);
			}else{
				ResponseStatusType status = new ResponseStatusType();
				status.setStatusCode(STATUSCODE_OK);
				status.setStatusText(STATUSTEXT_OK);
				status.setDetails(STATUSDETAILS_OKNOLENSAJES);
				this.setStatus(status);
			}
		}
		
		
		try {
			
			
        JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(respuesta, writer);
		
		
		return writer.toString();
		
		
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+R_CONST_3 + e.getMessage());
		}
		
	}
		
}
