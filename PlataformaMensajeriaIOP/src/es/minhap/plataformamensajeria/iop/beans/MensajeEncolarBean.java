package es.minhap.plataformamensajeria.iop.beans;

import es.map.sim.negocio.modelo.MensajeJMS;

public class MensajeEncolarBean {
	private MensajeJMS mensajeJms;
	private Long maxRetries;
	private String servicioId;
	private Boolean premium;
	
	
	public MensajeEncolarBean(MensajeJMS mensajeJMS, Long maxRetries, String servicioId, Boolean premium) {
		this.mensajeJms = mensajeJMS;
		this.maxRetries = maxRetries;
		this.servicioId = servicioId;
		this.premium = premium;
	}


	/**
	 * @return the mensajeJms
	 */
	public MensajeJMS getMensajeJms() {
		return mensajeJms;
	}


	/**
	 * @param mensajeJms the mensajeJms to set
	 */
	public void setMensajeJms(MensajeJMS mensajeJms) {
		this.mensajeJms = mensajeJms;
	}


	/**
	 * @return the maxRetries
	 */
	public Long getMaxRetries() {
		return maxRetries;
	}


	/**
	 * @param maxRetries the maxRetries to set
	 */
	public void setMaxRetries(Long maxRetries) {
		this.maxRetries = maxRetries;
	}


	/**
	 * @return the servicioId
	 */
	public String getServicioId() {
		return servicioId;
	}


	/**
	 * @param servicioId the servicioId to set
	 */
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}


	/**
	 * @return the premium
	 */
	public Boolean getPremium() {
		return premium;
	}


	/**
	 * @param premium the premium to set
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}


}
