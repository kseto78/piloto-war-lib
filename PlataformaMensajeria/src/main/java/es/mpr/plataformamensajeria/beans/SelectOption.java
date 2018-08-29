package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

/**
 * Clase SelectOption.
 */
public class SelectOption implements Serializable {

	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2956467317543416689L;
	
	/**  value. */
	private String value;
	
	/**  text. */
	private String text;
	
	/**
	 * Constructor de select option.
	 *
	 * @param value the value
	 * @param text the text
	 */
	public SelectOption(String value,String text) {
		this.value = value;
		this.text = text;
	}

	/**
	 * Obtener value.
	 *
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Modificar value.
	 *
	 * @param value new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Obtener text.
	 *
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Modificar text.
	 *
	 * @param text new text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	
}
