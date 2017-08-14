package es.mpr.plataformamensajeria.util;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.minhap.common.properties.PropertiesServices;

public class PlataformaMensajeriaProperties {

	private static PlataformaMensajeriaProperties instance = null;

	private ReloadableResourceBundleMessageSource messageSource;

	private PropertiesServices ps;


	private PlataformaMensajeriaProperties() {
		
	}

	public static PlataformaMensajeriaProperties getInstance() {

		if (instance == null) {
			instance = new PlataformaMensajeriaProperties();
		}
		return instance;
	}

	public String getProperty(String property, String defProperty) {
		String prop = null;
		try {
			if (null == ps){
				ps = new PropertiesServices(messageSource);
			}
			prop = ps.getMessage(property, null);

			if (prop == null) {
				prop = defProperty;
			}
		} catch (Exception e) {
			prop = defProperty;
		}
		return prop;

	}

	/**
	 * @return the messageSource
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the ps
	 */
	public PropertiesServices getPs() {
		return ps;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(PropertiesServices ps) {
		this.ps = ps;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(PlataformaMensajeriaProperties instance) {
		PlataformaMensajeriaProperties.instance = instance;
	}

}
