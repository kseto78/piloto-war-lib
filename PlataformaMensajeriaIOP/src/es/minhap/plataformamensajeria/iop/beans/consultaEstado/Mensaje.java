//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:27:28 AM CET 
//


package es.minhap.plataformamensajeria.iop.beans.consultaEstado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para mensaje complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensaje"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Servicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdAplicacion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdCanal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdLote" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="IdMensaje" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdEstado" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Reintentos" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodSia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodOrganismo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodOrganismoPagador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensaje", propOrder = {
    "idServicio",
    "servicio",
    "idAplicacion",
    "aplicacion",
    "idCanal",
    "canal",
    "idLote",
    "idMensaje",
    "idExterno",
    "idEstado",
    "estado",
    "reintentos",
    "fecha",
    "docUsuario",
    "codSia",
    "codOrganismo",
    "codOrganismoPagador"
})
public class Mensaje {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	@XmlElement(name = "IdServicio", namespace = R_CONST_1)
    protected int idServicio;
    @XmlElement(name = "Servicio",namespace = R_CONST_1, required = true)
    protected String servicio;
    @XmlElement(name = "IdAplicacion", namespace = R_CONST_1)
    protected int idAplicacion;
    @XmlElement(name = "Aplicacion",namespace = R_CONST_1, required = true)
    protected String aplicacion;
    @XmlElement(name = "IdCanal", namespace = R_CONST_1)
    protected int idCanal;
    @XmlElement(name = "Canal", namespace = R_CONST_1,required = true)
    protected String canal;
    @XmlElement(name = "IdLote", namespace = R_CONST_1)
    protected int idLote;
    @XmlElement(name = "IdMensaje", namespace = R_CONST_1)
    protected int idMensaje;
    @XmlElement(name = "IdExterno", namespace = R_CONST_1,required = true)
    protected String idExterno;
    @XmlElement(name = "IdEstado",namespace = R_CONST_1)
    protected int idEstado;
    @XmlElement(name = "Estado",namespace = R_CONST_1, required = true)
    protected String estado;
    @XmlElement(name = "Reintentos", namespace = R_CONST_1)
    protected int reintentos;
    @XmlElement(name = "Fecha", namespace = R_CONST_1,required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    @XmlElement(name = "DocUsuario",namespace = R_CONST_1)
    protected String docUsuario;
    @XmlElement(name = "CodSia",namespace = R_CONST_1)
    protected String codSia;
    @XmlElement(name = "CodOrganismo",namespace = R_CONST_1)
    protected String codOrganismo;
    @XmlElement(name = "CodOrganismoPagador",namespace = R_CONST_1)
    protected String codOrganismoPagador;

    /**
     * Obtiene el valor de la propiedad idServicio.
     * 
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * Define el valor de la propiedad idServicio.
     * 
     */
    public void setIdServicio(int value) {
        this.idServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad servicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Define el valor de la propiedad servicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Obtiene el valor de la propiedad idAplicacion.
     * 
     */
    public int getIdAplicacion() {
        return idAplicacion;
    }

    /**
     * Define el valor de la propiedad idAplicacion.
     * 
     */
    public void setIdAplicacion(int value) {
        this.idAplicacion = value;
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

    /**
     * Obtiene el valor de la propiedad idCanal.
     * 
     */
    public int getIdCanal() {
        return idCanal;
    }

    /**
     * Define el valor de la propiedad idCanal.
     * 
     */
    public void setIdCanal(int value) {
        this.idCanal = value;
    }

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad idLote.
     * 
     */
    public int getIdLote() {
        return idLote;
    }

    /**
     * Define el valor de la propiedad idLote.
     * 
     */
    public void setIdLote(int value) {
        this.idLote = value;
    }

    /**
     * Obtiene el valor de la propiedad idMensaje.
     * 
     */
    public int getIdMensaje() {
        return idMensaje;
    }

    /**
     * Define el valor de la propiedad idMensaje.
     * 
     */
    public void setIdMensaje(int value) {
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

    /**
     * Obtiene el valor de la propiedad idEstado.
     * 
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * Define el valor de la propiedad idEstado.
     * 
     */
    public void setIdEstado(int value) {
        this.idEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad reintentos.
     * 
     */
    public int getReintentos() {
        return reintentos;
    }

    /**
     * Define el valor de la propiedad reintentos.
     * 
     */
    public void setReintentos(int value) {
        this.reintentos = value;
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
     * Obtiene el valor de la propiedad docUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocUsuario() {
        return docUsuario;
    }

    /**
     * Define el valor de la propiedad docUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocUsuario(String value) {
        this.docUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad codSia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSia() {
        return codSia;
    }

    /**
     * Define el valor de la propiedad codSia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSia(String value) {
        this.codSia = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismo() {
        return codOrganismo;
    }

    /**
     * Define el valor de la propiedad codOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismo(String value) {
        this.codOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismoPagador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismoPagador() {
        return codOrganismoPagador;
    }

    /**
     * Define el valor de la propiedad codOrganismoPagador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismoPagador(String value) {
        this.codOrganismoPagador = value;
    }

}
