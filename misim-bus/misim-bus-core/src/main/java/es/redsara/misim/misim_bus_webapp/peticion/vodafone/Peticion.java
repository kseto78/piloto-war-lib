
package es.redsara.misim.misim_bus_webapp.peticion.vodafone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Origen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Asunto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdContexto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Operador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "tipo",
    "origen",
    "destino",
    "fecha",
    "asunto",
    "idOperador",
    "idContexto",
    "operador",
    "aplicacion"
})
@XmlRootElement(name = "Peticion", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
public class Peticion {

    @XmlElement(name = "Tipo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String tipo;
    @XmlElement(name = "Origen", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String origen;
    @XmlElement(name = "Destino", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String destino;
    @XmlElement(name = "Fecha", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    @XmlElement(name = "Asunto", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String asunto;
    @XmlElement(name = "IdOperador", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String idOperador;
    @XmlElement(name = "IdContexto", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String idContexto;
    @XmlElement(name = "Operador", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String operador;
    @XmlElement(name = "Aplicacion", namespace = "http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String aplicacion;

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperador() {
        return idOperador;
    }

    /**
     * Define el valor de la propiedad idOperador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperador(String value) {
        this.idOperador = value;
    }

    /**
     * Obtiene el valor de la propiedad idContexto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdContexto() {
        return idContexto;
    }

    /**
     * Define el valor de la propiedad idContexto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdContexto(String value) {
        this.idContexto = value;
    }

    /**
     * Obtiene el valor de la propiedad operador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Define el valor de la propiedad operador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperador(String value) {
        this.operador = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * Define el valor de la propiedad aplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

}
