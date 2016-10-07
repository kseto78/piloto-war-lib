package es.mpr.template.web.action.ajax;

import java.util.ArrayList;
import java.util.List;

import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionSupport;

public class FormsAjaxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellidos;
			
	private String situacion;
	private List<KeyValueObject> situacionList;
	
	private String condiciones;
	private String opinion;
		
	/**
	 * Creamos la lista de la situaci&oacute;n laboral
	 * 
	 */
	private void createSituacionList(){
		
		situacionList = new ArrayList<KeyValueObject>();
		situacionList.add(new KeyValueObject("1","Asalariado"));
		situacionList.add(new KeyValueObject("2","Autónomo"));
		situacionList.add(new KeyValueObject("3","Otros"));
	}
	
	public String execute()
	{
		createSituacionList();
		
		return INPUT;
	}
	
	public String getJSON(){
				
		createSituacionList();
		
		return SUCCESS;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<KeyValueObject> getSituacionList() {
		return situacionList;
	}

	public void setSituacionList(List<KeyValueObject> situacionList) {
		this.situacionList = situacionList;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	
}
