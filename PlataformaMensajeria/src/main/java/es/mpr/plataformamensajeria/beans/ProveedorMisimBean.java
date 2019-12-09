package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class ProveedorMisimBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  id proveedor. */
	protected Long idProveedor = null;

	/**  nombre. */
	protected String nombre = null;

	/**  id producto. */
	protected Long idProducto = null;

	/**  id endpoint. */
	protected Long idEndpoint = null;

	/**  id transformacion. */
	protected Long idTransformacion = null;

	/**  usuario. */
	protected String usuario = null;

	/**  password. */
	protected String password = null;

	/**  company. */
	protected String company = null;

	/**  type. */
	protected String type = null;

	/**  encoding. */
	/*DUDA proveedores*/
	protected String encoding = null;

	/**  basic autentication. */
	protected String basicAutentication = null;

	/**  method. */
	protected String method = null;

	/**  media type. */
	protected String mediaType = null;

	/**  anadir uim. */
	protected String anadirUim = null;

	/**  user autentication. */
	protected String userAutentication = null;

	/**  pass autentication. */
	protected String passAutentication = null;

	/**  certificado. */
	protected String certificado = null;

	/**  certificado pass. */
	protected String certificadoPass = null;

	/**  firma. */
	protected String firma = null;

	/**  cifrado. */
	protected String cifrado = null;

	/**  nodo cifrado. */
	protected String nodoCifrado = null;

	/**  esquema cifrado. */
	protected String esquemaCifrado = null;

	/**  tipo firma. */
	protected String tipoFirma = null;

	/**  endpoint. */
	protected EndpointBean endpoint = null;

	/**  producto. */
	protected ProductoBean producto = null;

	/**  transformacion. */
	protected TransformacionBean transformacion = null;


	
	/**
	 * Constructor de proveedor misim bean.
	 */
	public ProveedorMisimBean() {
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


	/**
	 * Obtener id proveedor.
	 *
	 * @return id proveedor
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}


	/**
	 * Modificar id proveedor.
	 *
	 * @param idProveedor new id proveedor
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Modificar nombre.
	 *
	 * @param nombre new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Obtener id producto.
	 *
	 * @return id producto
	 */
	public Long getIdProducto() {
		return idProducto;
	}


	/**
	 * Modificar id producto.
	 *
	 * @param idProducto new id producto
	 */
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	/**
	 * Obtener id endpoint.
	 *
	 * @return id endpoint
	 */
	public Long getIdEndpoint() {
		return idEndpoint;
	}


	/**
	 * Modificar id endpoint.
	 *
	 * @param idEndpoint new id endpoint
	 */
	public void setIdEndpoint(Long idEndpoint) {
		this.idEndpoint = idEndpoint;
	}


	/**
	 * Obtener id transformacion.
	 *
	 * @return id transformacion
	 */
	public Long getIdTransformacion() {
		return idTransformacion;
	}


	/**
	 * Modificar id transformacion.
	 *
	 * @param idTransformacion new id transformacion
	 */
	public void setIdTransformacion(Long idTransformacion) {
		this.idTransformacion = idTransformacion;
	}


	/**
	 * Obtener usuario.
	 *
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}


	/**
	 * Modificar usuario.
	 *
	 * @param usuario new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	/**
	 * Obtener password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Modificar password.
	 *
	 * @param password new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Obtener company.
	 *
	 * @return company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * Modificar company.
	 *
	 * @param company new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}


	/**
	 * Obtener type.
	 *
	 * @return type
	 */
	public String getType() {
		return type;
	}


	/**
	 * Modificar type.
	 *
	 * @param type new type
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * Obtener certificado.
	 *
	 * @return certificado
	 */
	public String getCertificado() {
		return certificado;
	}


	/**
	 * Modificar certificado.
	 *
	 * @param certificado new certificado
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}


	/**
	 * Obtener certificado pass.
	 *
	 * @return certificado pass
	 */
	public String getCertificadoPass() {
		return certificadoPass;
	}


	/**
	 * Modificar certificado pass.
	 *
	 * @param certificadoPass new certificado pass
	 */
	public void setCertificadoPass(String certificadoPass) {
		this.certificadoPass = certificadoPass;
	}


	/**
	 * Obtener firma.
	 *
	 * @return firma
	 */
	public String getFirma() {
		return firma;
	}


	/**
	 * Modificar firma.
	 *
	 * @param firma new firma
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}


	/**
	 * Obtener cifrado.
	 *
	 * @return cifrado
	 */
	public String getCifrado() {
		return cifrado;
	}


	/**
	 * Modificar cifrado.
	 *
	 * @param cifrado new cifrado
	 */
	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}


	/**
	 * Obtener nodo cifrado.
	 *
	 * @return nodo cifrado
	 */
	public String getNodoCifrado() {
		return nodoCifrado;
	}


	/**
	 * Modificar nodo cifrado.
	 *
	 * @param nodoCifrado new nodo cifrado
	 */
	public void setNodoCifrado(String nodoCifrado) {
		this.nodoCifrado = nodoCifrado;
	}


	/**
	 * Obtener esquema cifrado.
	 *
	 * @return esquema cifrado
	 */
	public String getEsquemaCifrado() {
		return esquemaCifrado;
	}


	/**
	 * Modificar esquema cifrado.
	 *
	 * @param esquemaCifrado new esquema cifrado
	 */
	public void setEsquemaCifrado(String esquemaCifrado) {
		this.esquemaCifrado = esquemaCifrado;
	}


	/**
	 * Obtener tipo firma.
	 *
	 * @return tipo firma
	 */
	public String getTipoFirma() {
		return tipoFirma;
	}


	/**
	 * Modificar tipo firma.
	 *
	 * @param tipoFirma new tipo firma
	 */
	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}


	/**
	 * Obtener endpoint.
	 *
	 * @return endpoint
	 */
	public EndpointBean getEndpoint() {
		return endpoint;
	}


	/**
	 * Modificar endpoint.
	 *
	 * @param endpoint new endpoint
	 */
	public void setEndpoint(EndpointBean endpoint) {
		this.endpoint = endpoint;
	}


	/**
	 * Obtener producto.
	 *
	 * @return producto
	 */
	public ProductoBean getProducto() {
		return producto;
	}


	/**
	 * Modificar producto.
	 *
	 * @param producto new producto
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}


	/**
	 * Obtener transformacion.
	 *
	 * @return transformacion
	 */
	public TransformacionBean getTransformacion() {
		return transformacion;
	}


	/**
	 * Modificar transformacion.
	 *
	 * @param transformacion new transformacion
	 */
	public void setTransformacion(TransformacionBean transformacion) {
		this.transformacion = transformacion;
	}


	/**
	 * Obtener encoding.
	 *
	 * @return encoding
	 */
	public String getEncoding() {
		return encoding;
	}


	/**
	 * Modificar encoding.
	 *
	 * @param encoding new encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}


	/**
	 * Obtener basic autentication.
	 *
	 * @return basic autentication
	 */
	public String getBasicAutentication() {
		return basicAutentication;
	}


	/**
	 * Modificar basic autentication.
	 *
	 * @param basicAutentication new basic autentication
	 */
	public void setBasicAutentication(String basicAutentication) {
		this.basicAutentication = basicAutentication;
	}


	/**
	 * Obtener method.
	 *
	 * @return method
	 */
	public String getMethod() {
		return method;
	}


	/**
	 * Modificar method.
	 *
	 * @param method new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}


	/**
	 * Obtener media type.
	 *
	 * @return media type
	 */
	public String getMediaType() {
		return mediaType;
	}


	/**
	 * Modificar media type.
	 *
	 * @param mediaType new media type
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}


	/**
	 * Obtener anadir uim.
	 *
	 * @return anadir uim
	 */
	public String getAnadirUim() {
		return anadirUim;
	}


	/**
	 * Modificar anadir uim.
	 *
	 * @param anadirUim new anadir uim
	 */
	public void setAnadirUim(String anadirUim) {
		this.anadirUim = anadirUim;
	}


	/**
	 * Obtener user autentication.
	 *
	 * @return user autentication
	 */
	public String getUserAutentication() {
		return userAutentication;
	}


	/**
	 * Modificar user autentication.
	 *
	 * @param userAutentication new user autentication
	 */
	public void setUserAutentication(String userAutentication) {
		this.userAutentication = userAutentication;
	}


	/**
	 * Obtener pass autentication.
	 *
	 * @return pass autentication
	 */
	public String getPassAutentication() {
		return passAutentication;
	}


	/**
	 * Modificar pass autentication.
	 *
	 * @param passAutentication new pass autentication
	 */
	public void setPassAutentication(String passAutentication) {
		this.passAutentication = passAutentication;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
