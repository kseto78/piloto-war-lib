package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultOptions implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938205591957165L;
	private List<SelectOption> items = new ArrayList<SelectOption>();
	
	public List<SelectOption> getItems() {
		return items;
	}
	public void setItems(List<SelectOption> items) {
		this.items = items;
	}
}
