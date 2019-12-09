package es.mpr.plataformamensajeria.util;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.minhap.common.properties.PropertiesServices;

// TODO: Auto-generated Javadoc
/**
 * Clase PlataformaMensajeriaProperties.
 */
public class PlataformaMensajeriaProperties {

	/**  instance. */
	private static PlataformaMensajeriaProperties instance = null;

	/**  message source. */
	private ReloadableResourceBundleMessageSource messageSource;

	/**  ps. */
	private PropertiesServices ps;


	/**
	 * Constructor de plataforma mensajeria properties.
	 */
	private PlataformaMensajeriaProperties() {
		// This method has to be empty.
		
	}

	/**
	 * Gets the single instance of PlataformaMensajeriaProperties.
	 *
	 * @return single instance of PlataformaMensajeriaProperties
	 */
	public static PlataformaMensajeriaProperties getInstance() {

		if (instance == null) {
			instance = new PlataformaMensajeriaProperties();
		}
		return instance;
	}

	/**
	 * Obtener property.
	 *
	 * @param property the property
	 * @param defProperty the def property
	 * @return property
	 */
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
	 * Obtener message source.
	 *
	 * @return the messageSource
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * Modificar message source.
	 *
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Obtener ps.
	 *
	 * @return the ps
	 */
	public PropertiesServices getPs() {
		return ps;
	}

	/**
	 * Modificar ps.
	 *
	 * @param ps the ps to set
	 */
	public void setPs(PropertiesServices ps) {
		this.ps = ps;
	}

	/**
	 * Modificar instance.
	 *
	 * @param instance the instance to set
	 */
	public static void setInstance(PlataformaMensajeriaProperties instance) {
		PlataformaMensajeriaProperties.instance = instance;
	}

}
