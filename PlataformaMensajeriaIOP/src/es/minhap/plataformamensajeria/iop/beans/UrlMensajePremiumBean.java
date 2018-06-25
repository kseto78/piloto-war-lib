package es.minhap.plataformamensajeria.iop.beans;

import java.math.BigDecimal;

public class UrlMensajePremiumBean {
	private Long urlMensajePremiumId;
	private BigDecimal mensajeid;
	private String url;
	private BigDecimal reintentos;
	
	public UrlMensajePremiumBean() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the mensajeid
	 */
	public BigDecimal getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid the mensajeid to set
	 */
	public void setMensajeid(BigDecimal mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the reintentos
	 */
	public BigDecimal getReintentos() {
		return reintentos;
	}

	/**
	 * @param reintentos the reintentos to set
	 */
	public void setReintentos(BigDecimal reintentos) {
		this.reintentos = reintentos;
	}


	/**
	 * @return the urlMensajePremiumId
	 */
	public Long getUrlMensajePremiumId() {
		return urlMensajePremiumId;
	}


	/**
	 * @param urlMensajePremiumId the urlMensajePremiumId to set
	 */
	public void setUrlMensajePremiumId(Long urlMensajePremiumId) {
		this.urlMensajePremiumId = urlMensajePremiumId;
	}
}
