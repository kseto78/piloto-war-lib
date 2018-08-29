package es.mpr.template.web.action.reports;

import java.util.List;

import com.map.j2ee.struts.base.BaseForm;

/**
 * Clase que contiene los atributos del formulario de informes.
 */
public class ReportForm extends BaseForm {

	/**  tipo informe. */
	private String tipoInforme;
	
	/**  lista tipos informe. */
	private List<String> listaTiposInforme = null;

    /**
     * Constructor por defecto de la clase.
     */
    public ReportForm() {
        super();
    }

	/**
	 * Retorna el tipo de informe seleccionado.
	 *
	 * @return the tipoInforme
	 */
	public String getTipoInforme() {
		return tipoInforme;
	}

	/**
	 * Establece el tipo de informe.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipoInforme(String tipo) {
		this.tipoInforme = tipo;
	}

	/**
	 * Establece los valores de la lista de tipos de informe.
	 *
	 * @param listaTiposInforme Lista de tipos de informe para seleccionar
	 */
	public void setListaTiposInforme(List<String> listaTiposInforme) {
		this.listaTiposInforme = listaTiposInforme;
	}

	/**
	 * Devuelve la lista de tipos de informe.
	 *
	 * @return the listTipoUsuario
	 */
	public List<String> getListaTiposInforme() {
		return listaTiposInforme;
	}

}
