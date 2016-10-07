package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

public class SelectOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2956467317543416689L;
	private String value;
	private String text;
	
	public SelectOption(String value,String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
