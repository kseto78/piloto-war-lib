package es.minhap.plataformamensajeria.iop.beans;

import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Transformacion;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n
 * 
 * @author everis
 * 
 */
public class FiltroProveedorMisimBean {

	

	protected Long idProveedor = null;
	protected String nombre = null;
	protected Long idProducto = null;
	protected Long idEndpoint = null;
	protected Long idTransformacion = null;
	protected String usuario = null;
	protected String password = null;
	protected String company = null;
	protected String type = null;
	/*DUDA proveedores*/
	protected String encoding = null;
	protected String basicAutentication = null;
	protected String method = null;
	protected String mediaType = null;
	protected String anadirUim = null;
	protected String userAutentication = null;
	protected String passAutentication = null;
	protected String certificado = null;
	protected String certificadoPass = null;
	protected String firma = null;
	protected String cifrado = null;
	protected String nodoCifrado = null;
	protected String esquemaCifrado = null;
	protected String tipoFirma = null;
	protected Endpoint endpoint = null;
	protected Producto producto = null;
	protected Transformacion transformacion = null;


	public FiltroProveedorMisimBean() {
		super();
		this.idProveedor = null;
		this.nombre = null;
		this.idProducto = null;
		this.idEndpoint = null;
		this.idTransformacion = null;
		this.usuario = null;
		this.password = null;
		this.company = null;
		this.type = null;
		/*DUDA proveedores*/
		this.encoding = null;
		this.basicAutentication = null;
		this.method = null;
		this.mediaType = null;
		this.anadirUim = null;
		this.userAutentication = null;
		this.passAutentication = null;
		this.certificado = null;
		this.certificadoPass = null;
		this.firma = null;
		this.cifrado = null;
		this.nodoCifrado = null;
		this.esquemaCifrado = null;
		this.tipoFirma = null;
		this.endpoint = null;
		this.producto = null;
		this.transformacion = null;

	}


	public Long getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	public Long getIdEndpoint() {
		return idEndpoint;
	}


	public void setIdEndpoint(Long idEndpoint) {
		this.idEndpoint = idEndpoint;
	}


	public Long getIdTransformacion() {
		return idTransformacion;
	}


	public void setIdTransformacion(Long idTransformacion) {
		this.idTransformacion = idTransformacion;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCertificado() {
		return certificado;
	}


	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}


	public String getCertificadoPass() {
		return certificadoPass;
	}


	public void setCertificadoPass(String certificadoPass) {
		this.certificadoPass = certificadoPass;
	}


	public String getFirma() {
		return firma;
	}


	public void setFirma(String firma) {
		this.firma = firma;
	}


	public String getCifrado() {
		return cifrado;
	}


	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}


	public String getNodoCifrado() {
		return nodoCifrado;
	}


	public void setNodoCifrado(String nodoCifrado) {
		this.nodoCifrado = nodoCifrado;
	}


	public String getEsquemaCifrado() {
		return esquemaCifrado;
	}


	public void setEsquemaCifrado(String esquemaCifrado) {
		this.esquemaCifrado = esquemaCifrado;
	}


	public String getTipoFirma() {
		return tipoFirma;
	}


	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}


	public Endpoint getEndpoint() {
		return endpoint;
	}


	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Transformacion getTransformacion() {
		return transformacion;
	}


	public void setTransformacion(Transformacion transformacion) {
		this.transformacion = transformacion;
	}


	public String getEncoding() {
		return encoding;
	}


	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}


	public String getBasicAutentication() {
		return basicAutentication;
	}


	public void setBasicAutentication(String basicAutentication) {
		this.basicAutentication = basicAutentication;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getMediaType() {
		return mediaType;
	}


	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}


	public String getAnadirUim() {
		return anadirUim;
	}


	public void setAnadirUim(String anadirUim) {
		this.anadirUim = anadirUim;
	}


	public String getUserAutentication() {
		return userAutentication;
	}


	public void setUserAutentication(String userAutentication) {
		this.userAutentication = userAutentication;
	}


	public String getPassAutentication() {
		return passAutentication;
	}


	public void setPassAutentication(String passAutentication) {
		this.passAutentication = passAutentication;
	}

}
