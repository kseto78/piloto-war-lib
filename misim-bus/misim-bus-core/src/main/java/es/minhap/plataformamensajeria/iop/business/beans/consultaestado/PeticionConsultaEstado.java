
package es.minhap.plataformamensajeria.iop.business.beans.consultaestado;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Proveedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MensajeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatosEspecificos" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DatosEspecificos" minOccurs="0"/>
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
    "usuario",
    "password",
    "producto",
    "proveedor",
    "mensajeId",
    "loteId",
    "datosEspecificos"
})
@XmlRootElement(name = "Peticion")
public class PeticionConsultaEstado {

    @XmlElement(name = "Usuario", required = true)
    protected String usuario;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "Producto", required = true)
    protected String producto;
    @XmlElement(name = "Proveedor", required = true)
    protected String proveedor;
    @XmlElement(name = "MensajeId", required = true)
    protected String mensajeId;
    @XmlElement(name = "idLote", required = true)
    protected String loteId;
    @XmlElement(name = "DatosEspecificos")
    protected DatosEspecificosConsultaEstado datosEspecificos;
    
    private static Logger LOG = LoggerFactory.getLogger(PeticionConsultaEstado.class);

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
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducto(String value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad proveedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Define el valor de la propiedad proveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProveedor(String value) {
        this.proveedor = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeId() {
        return mensajeId;
    }

    /**
     * Define el valor de la propiedad mensajeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeId(String value) {
        this.mensajeId = value;
    }
    
    public String getLoteId() {
		return loteId;
	}

	public void setLoteId(String loteId) {
		this.loteId = loteId;
	}

	/**
     * Obtiene el valor de la propiedad datosEspecificos.
     * 
     * @return
     *     possible object is
     *     {@link DatosEspecifico }
     *     
     */
    public DatosEspecificosConsultaEstado getDatosEspecificos() {
        return datosEspecificos;
    }

    /**
     * Define el valor de la propiedad datosEspecificos.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosEspecificosConsultaEstado }
     *     
     */
    public void setDatosEspecificos(DatosEspecificosConsultaEstado value) {
        this.datosEspecificos = value;
    }

	@Override
	public String toString() {
		return "PeticionMisim [usuario=" + usuario + ", password=" + password + ", producto=" + producto + ", proveedor=" + proveedor + ", mensajeId=" + mensajeId + ", loteId=" + loteId + ", datosEspecificos=" + datosEspecificos + "]";
	}
	
	public String toXML() {

		PeticionConsultaEstado pet = this;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PeticionConsultaEstado.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(pet, writer);
		

			return writer.toString();
		} catch (Exception e) {
			LOG.error("[PeticionConsultaEstado] toXML", e);
			return "";
		}

	}


}
