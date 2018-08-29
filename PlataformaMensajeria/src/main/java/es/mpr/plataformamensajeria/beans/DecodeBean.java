package es.mpr.plataformamensajeria.beans;

/**
 * Clase DecodeBean
 * @author ralberoc
 *
 */
public class DecodeBean extends PaginacionBean{

	private String xmlCifrado;
	private String xmlDescifrado;
	private String certificado;
	
	/**
	 * COnstructor por defecto
	 */
	public DecodeBean(){
		super();
	}
	
	/**
	 * Obtener XmlCifrado
	 * @return
	 */
	public String getXmlCifrado() {
		return xmlCifrado;
	}

	/**
	 * Modificar xmlCifrado
	 * @param xmlCifrado
	 */
	public void setXmlCifrado(String xmlCifrado) {
		this.xmlCifrado = xmlCifrado;
	}

	/**
	 * Obtener xmlDescifrado
	 * @return
	 */
	public String getXmlDescifrado() {
		return xmlDescifrado;
	}

	/**
	 * Modificar xmlDescifrado
	 * @param xmlDescifrado
	 */
	public void setXmlDescifrado(String xmlDescifrado) {
		this.xmlDescifrado = xmlDescifrado;
	}

	/**
	 * Obtener certificado
	 * @return
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Modificar certificado
	 * @param certificado
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	
}
