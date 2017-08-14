package es.mpr.plataformamensajeria.beans;


public class DecodeBean extends PaginacionBean{

	private String xmlCifrado;
	private String xmlDescifrado;
	private String certificado;
	
	public DecodeBean(){
		super();
	}
	

	public String getXmlCifrado() {
		return xmlCifrado;
	}


	public void setXmlCifrado(String xmlCifrado) {
		this.xmlCifrado = xmlCifrado;
	}


	public String getXmlDescifrado() {
		return xmlDescifrado;
	}


	public void setXmlDescifrado(String xmlDescifrado) {
		this.xmlDescifrado = xmlDescifrado;
	}


	public String getCertificado() {
		return certificado;
	}


	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	
}
