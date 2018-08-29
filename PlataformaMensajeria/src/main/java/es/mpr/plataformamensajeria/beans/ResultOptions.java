package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ResultOptions.
 */
public class ResultOptions implements Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2938205591957165L;
	
	/**  items. */
	private List<SelectOption> items = new ArrayList<SelectOption>();
	
	/**
	 * Obtener items.
	 *
	 * @return items
	 */
	public List<SelectOption> getItems() {
		return items;
	}
	
	/**
	 * Modificar items.
	 *
	 * @param items new items
	 */
	public void setItems(List<SelectOption> items) {
		this.items = items;
	}
}
