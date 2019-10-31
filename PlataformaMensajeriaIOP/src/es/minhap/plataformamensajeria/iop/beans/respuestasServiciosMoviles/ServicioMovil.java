
package es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseStatusType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MobileService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescMobileService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Icon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UrlService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Imagen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author everis
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicioMovil", propOrder = {
	"idService",
	"mobileService",
    "descMobileService",
    "icon",
    "urlService",
    "tipo",
    "estado",
    "imagen"
    
})
public class ServicioMovil {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuestaServiciosDisponibles";
	@XmlElement(name = "IdService", required = true, namespace = R_CONST_1)
	protected String idService;
    @XmlElement(name = "MobileService", required = true, namespace = R_CONST_1)
	protected String mobileService;
    @XmlElement(name = "DescMobileService", required = true, namespace = R_CONST_1)
	protected String descMobileService;
    @XmlElement(name = "Icon", required = true, namespace = R_CONST_1)
	protected String icon;
    @XmlElement(name = "UrlService", namespace = R_CONST_1)
	protected String urlService;
    @XmlElement(name = "Tipo", required = true, namespace = R_CONST_1)
	protected String tipo;
    @XmlElement(name = "Estado", required = true, namespace = R_CONST_1)
	protected String estado;
    @XmlElement(name = "Imagen", required = true, namespace = R_CONST_1)
	protected String imagen;

	/**
	 * @return the mobileService
	 */
	public String getMobileService() {
		return mobileService;
	}
	/**
	 * @param mobileService the mobileService to set
	 */
	public void setMobileService(String mobileService) {
		this.mobileService = mobileService;
	}
	/**
	 * @return the descMobileService
	 */
	public String getDescMobileService() {
		return descMobileService;
	}
	/**
	 * @param descMobileService the descMobileService to set
	 */
	public void setDescMobileService(String descMobileService) {
		this.descMobileService = descMobileService;
	}
	/**
	 * @return the urlService
	 */
	public String getUrlService() {
		return urlService;
	}
	/**
	 * @param urlService the urlService to set
	 */
	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}
	/**
	 * @return the details
	 */
	/**
	 * @return the idService
	 */
	public String getIdService() {
		return idService;
	}
	/**
	 * @param idService the idService to set
	 */
	public void setIdService(String idService) {
		this.idService = idService;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
