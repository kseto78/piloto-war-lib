package es.mpr.template.web.action.ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EjemploAjaxAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, Preparable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<KeyValueObject> comboObjList;
	private ArrayList<KeyValueObject> datosEscuderiaList;
	private String escuderia;
	
	public String execute() {

		if (comboObjList==null || comboObjList.isEmpty())
		{
			//Rellenamos la lista de escuderias si esta vacia.
			comboObjList = new ArrayList<KeyValueObject>();
			comboObjList.add(new KeyValueObject("F", "Ferrari"));
			comboObjList.add(new KeyValueObject("RB", "Red Bull"));
			comboObjList.add(new KeyValueObject("MC", "McLaren"));
			comboObjList.add(new KeyValueObject("R", "Renault"));
			comboObjList.add(new KeyValueObject("M", "Mercedes"));
			comboObjList.add(new KeyValueObject("W", "Williams"));
		}
		
		if (escuderia != null && escuderia.equalsIgnoreCase("F")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList.add(new KeyValueObject("Nombre",
					"Scuderia Ferrari Marlboro"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Maranello - Italia"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Fernando Alonso"));
			datosEscuderiaList
					.add(new KeyValueObject("Piloto2", "Felipe Massa"));
			datosEscuderiaList.add(new KeyValueObject("Motor", "Ferrari"));
			datosEscuderiaList
					.add(new KeyValueObject("Chasis", "F150th Italia"));
			datosEscuderiaList.add(new KeyValueObject("Director",
					"Stefano Domenicali"));
			/*datosEscuderiaList.add(new KeyValueObject("Evil",
					"<script>alert('esto sale pero no debe');</script>"));*/
		} else if (escuderia != null && escuderia.equalsIgnoreCase("RB")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList.add(new KeyValueObject("Nombre",
					"Red Bull Racing"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Milton Keynes, Reino Unido"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Sebastian Vettel"));
			datosEscuderiaList
					.add(new KeyValueObject("Piloto2", "Mark Webber"));
			datosEscuderiaList.add(new KeyValueObject("Motor", "Renault"));
			datosEscuderiaList.add(new KeyValueObject("Chasis", "RB7"));
			datosEscuderiaList.add(new KeyValueObject("Director",
					"Christian Horner"));
		} else if (escuderia != null && escuderia.equalsIgnoreCase("MC")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList.add(new KeyValueObject("Nombre",
					"Vodafone McLaren Mercedes"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Woking, Reino Unido"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Lewis Hamilton"));
			datosEscuderiaList.add(new KeyValueObject("Piloto2",
					"Jenson Button"));
			datosEscuderiaList.add(new KeyValueObject("Motor",
					"Mercedes-Benz FO 108Y"));
			datosEscuderiaList.add(new KeyValueObject("Chasis", "MP4-26"));
			datosEscuderiaList.add(new KeyValueObject("Director",
					"Martin Whitmarsh"));
		} else if (escuderia != null && escuderia.equalsIgnoreCase("R")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList.add(new KeyValueObject("Nombre",
					"Renault F1 Team"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Enstone, Reino Unido"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Nick Heidfeld"));
			datosEscuderiaList.add(new KeyValueObject("Piloto2",
					"Vitaly Petrov"));
			datosEscuderiaList.add(new KeyValueObject("Motor", "Renault"));
			datosEscuderiaList.add(new KeyValueObject("Chasis", "R31"));
			datosEscuderiaList.add(new KeyValueObject("Director",
					"Eric Boullier"));
		} else if (escuderia != null && escuderia.equalsIgnoreCase("M")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList.add(new KeyValueObject("Nombre",
					"Mercedes GP Petronas F1 Team"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Brackley, Reino Unido"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Michael Schumacher"));
			datosEscuderiaList
					.add(new KeyValueObject("Piloto2", "Nico Rosberg"));
			datosEscuderiaList
					.add(new KeyValueObject("Motor", "Mercedes-Benz"));
			datosEscuderiaList.add(new KeyValueObject("Chasis", "MGP W02"));
			datosEscuderiaList
					.add(new KeyValueObject("Director", "Ross Brawn"));
		} else if (escuderia != null && escuderia.equalsIgnoreCase("W")) {
			datosEscuderiaList = new ArrayList<KeyValueObject>();
			datosEscuderiaList
					.add(new KeyValueObject("Nombre", "AT&T Williams"));
			datosEscuderiaList.add(new KeyValueObject("Base",
					"Grove, Reino Unido"));
			datosEscuderiaList.add(new KeyValueObject("Piloto1",
					"Rubens Barrichello"));
			datosEscuderiaList.add(new KeyValueObject("Piloto2",
					"Pastor Maldonado"));
			datosEscuderiaList.add(new KeyValueObject("Motor",
					"Cosworth CA2011"));
			datosEscuderiaList.add(new KeyValueObject("Chasis", "FW33"));
			datosEscuderiaList.add(new KeyValueObject("Director",
					"Frank Williams"));
		}
		return SUCCESS;
	}

	/*public String getJSON() {
				
		return execute();
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
	}

	/**
	 * @return the comboObjList
	 */
	public ArrayList<KeyValueObject> getComboObjList() {
		return comboObjList;
	}

	/**
	 * @param comboObjList
	 *            the comboObjList to set
	 */
	public void setComboObjList(ArrayList<KeyValueObject> aComboObjList) {
		this.comboObjList = aComboObjList;
	}

	/**
	 * @return the reloadList
	 */
	public ArrayList<KeyValueObject> getDatosEscuderiaList() {
		return datosEscuderiaList;
	}

	/**
	 * @param reloadList
	 *            the reloadList to set
	 */
	public void setDatosEscuderiaList(
			ArrayList<KeyValueObject> datosEscuderiaList) {
		this.datosEscuderiaList = datosEscuderiaList;
	}

	/**
	 * @return the escuderia
	 */
	public String getEscuderia() {
		return escuderia;
	}

	/**
	 * @param escuderia
	 *            the escuderia to set
	 */
	public void setEscuderia(String aEscuderia) {
		this.escuderia = aEscuderia;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletResponseAware#
	 * setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletRequestAware#
	 * setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}
}
