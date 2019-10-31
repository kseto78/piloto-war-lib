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
    "mensaje"
})
@XmlRootElement(name = "PeticionMensaje", namespace=OperacionesMensajesXMLBean.R_CONST_3)
public class OperacionesMensajesXMLBean {

    protected static final String R_CONST_1 = "Mensaje";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	protected static final String R_CONST_3 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Usuario", required = true, namespace=R_CONST_3)
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace=R_CONST_3)
    protected String password;
    @XmlElement(name = R_CONST_1, required = false, namespace=R_CONST_3)
    protected OperacionesMensajesXMLBean.Mensaje mensaje;
//    @XmlElement(name = "Lote", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
//    protected String lote;

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link Peticion.SeguimientoMensaje }
     *     
     */
    public OperacionesMensajesXMLBean.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Peticion.SeguimientoMensaje }
     *     
     */
    public void setMensaje(OperacionesMensajesXMLBean.Mensaje value) {
        this.mensaje = value;
    }

//    /**
//     * Obtiene el valor de la propiedad lote.
//     * 
//     * @return
//     *     possible object is
//     *     {@link String }
//     *     
//     */
//    public String getLote() {
//        return lote;
//    }
//
//    /**
//     * Define el valor de la propiedad lote.
//     * 
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *     
//     */
//    public void setLote(String value) {
//        this.lote = value;
//    }


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
     *         &lt;element name="IdMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "idMensaje",
        "idExterno"
    })
    @XmlRootElement(name = OperacionesMensajesXMLBean.R_CONST_1, namespace=OperacionesMensajesXMLBean.R_CONST_3)
    public static class Mensaje {

        @XmlElement(name = "IdMensaje", required = false, namespace=R_CONST_3)
        protected String idMensaje;
        @XmlElement(name = "IdExterno", required = false, namespace=R_CONST_3)
        protected String idExterno;

        /**
         * Obtiene el valor de la propiedad idMensaje.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdMensaje() {
            return idMensaje;
        }

        /**
         * Define el valor de la propiedad idMensaje.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdMensaje(String value) {
            this.idMensaje = value;
        }

        /**
         * Obtiene el valor de la propiedad idExterno.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdExterno() {
            return idExterno;
        }

        /**
         * Define el valor de la propiedad idExterno.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdExterno(String value) {
            this.idExterno = value;
        }

    }
	
	
	public void loadObjectFromXML (String xmlOperacionesMensajes)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(OperacionesMensajesXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlOperacionesMensajes);
		OperacionesMensajesXMLBean operacionesMensajes = (OperacionesMensajesXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, operacionesMensajes);
		
		
		
		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+R_CONST_2 + e.getMessage()+ "\nXML:\n"+xmlOperacionesMensajes);
		}
	}
	
	public String toXML() throws PlataformaBusinessException{
		
		OperacionesMensajesXMLBean operacionesMensajes = this;
		
		try {
        JAXBContext jaxbContext = JAXBContext.newInstance(OperacionesMensajesXMLBean.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(operacionesMensajes, writer);
//		jaxbMarshaller.marshal(operacionesMensajes, System.out);
		
		return writer.toString();
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+R_CONST_2 + e.getMessage());
		}
		
	}
	
}
