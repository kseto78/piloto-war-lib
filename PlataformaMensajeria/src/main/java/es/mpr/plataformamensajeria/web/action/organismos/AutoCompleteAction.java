package es.mpr.plataformamensajeria.web.action.organismos;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;

/**
 * Clase AutoCompleteAction.
 */
public class AutoCompleteAction implements Action {
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(AutoCompleteAction.class);
	
	/**  servicio organismo. */
	@Resource(name="servicioOrganismoImpl")
	private ServicioOrganismo servicioOrganismo;
	
	/**  term. */
	private String term;

	/**  list. */
	private List<String> list;


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Action#execute()
	 */
	public String execute() {
		try {
			list = servicioOrganismo.listAutocomplete(term);
			
		} catch (Exception e) {
			logger.error("Error Autocomplete", e);
		}
		return SUCCESS;
	}

	/**
	 * Obtener list.
	 *
	 * @return list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * Modificar list.
	 *
	 * @param list new list
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * Obtener term.
	 *
	 * @return term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Modificar term.
	 *
	 * @param term new term
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	
	
}