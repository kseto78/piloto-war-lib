package es.mpr.plataformamensajeria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.map.j2ee.pagination.PaginationAction;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * Clase PlataformaPaginationAction.
 */
/////MIGRADO
@Controller("plataformaPaginationAction")
public class PlataformaPaginationAction extends PaginationAction implements ServletRequestAware, Preparable {
	
	protected static final String R_CONST_REF = "00:00";

	protected static final String R_CONST_0 = "23:00";

	protected static final String MSGPLT_WARNING = "MSGPLT_WARNING";

	protected static final String R_CONST_1 = "03:30";

	protected static final String R_CONST_2 = "04:00";

	protected static final String R_CONST_3 = "08:00";

	protected static final String R_CONST_4 = "07:30";

	protected static final String R_CONST_5 = "22:30";

	protected static final String R_CONST_6 = "13:00";

	protected static final String R_CONST_7 = "17:00";

	protected static final String R_CONST_8 = "16:30";

	protected static final String R_CONST_9 = "12:30";

	protected static final String R_CONST_10 = "01:00";

	protected static final String R_CONST_11 = "19:30";

	protected static final String R_CONST_12 = "22:00";

	protected static final String R_CONST_13 = "05:00";

	protected static final String UNCHECKED = "unchecked";

	protected static final String R_CONST_14 = "00:30";

	protected static final String R_CONST_15 = "09:00";

	protected static final String R_CONST_16 = "04:30";

	protected static final String R_CONST_17 = "21:30";

	protected static final String R_CONST_18 = "08:30";

	protected static final String R_CONST_19 = "12:00";

	protected static final String R_CONST_20 = "15:30";

	protected static final String R_CONST_21 = "11:30";

	protected static final String R_CONST_22 = "16:00";

	protected static final String R_CONST_23 = "02:00";

	protected static final String R_CONST_24 = "06:00";

	protected static final String MSGPLT_ERROR = "MSGPLT_ERROR";

	protected static final String R_CONST_25 = "21:00";

	protected static final String R_CONST_26 = "01:30";

	protected static final String R_CONST_27 = "20:30";

	protected static final String R_CONST_28 = "05:30";

	protected static final String MSGPLT = "MSGPLT";

	protected static final String R_CONST_29 = "09:30";

	protected static final String R_CONST_30 = "10:30";

	protected static final String R_CONST_31 = "11:00";

	protected static final String R_CONST_32 = "18:30";

	protected static final String R_CONST_33 = "15:00";

	protected static final String R_CONST_34 = "14:30";

	protected static final String R_CONST_35 = "19:00";

	protected static final String R_CONST_36 = "24:00";

	protected static final String MSGPLT_FIELD_ER = "MSGPLT_FIELD_ERROR";

	protected static final String R_CONST_37 = "02:30";

	protected static final String R_CONST_38 = "03:00";

	protected static final String R_CONST_39 = "20:00";

	protected static final String R_CONST_40 = "06:30";

	protected static final String R_CONST_41 = "07:00";

	protected static final String R_CONST_42 = "23:30";

	protected static final String R_CONST_43 = "10:00";

	protected static final String R_CONST_44 = "14:00";

	protected static final String R_CONST_45 = "17:30";

	protected static final String R_CONST_46 = "13:30";

	protected static final String R_CONST_47 = "18:00";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/**  servicio usuario aplicacion. */
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  lista aplicaciones usuario. */
	public List<AplicacionBean> listaAplicacionesUsuario = new ArrayList<>();

	/**  from. */
	protected String from = "";
	
	/**  id from. */
	protected String idFrom = "";
	
	/**  var. */
	protected String var = "";

	
	/**
 * Obtener combo horas inicio.
 *
 * @return combo horas inicio
 */
public List<KeyValueObject> getComboHorasInicio(){
		    List<KeyValueObject> result = new ArrayList<>();
	        KeyValueObject option = null;
			option = new KeyValueObject();option.setCodigo(R_CONST_REF);option.setDescripcion(R_CONST_REF);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_14);option.setDescripcion(R_CONST_14);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_10);option.setDescripcion(R_CONST_10);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_26);option.setDescripcion(R_CONST_26);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_23);option.setDescripcion(R_CONST_23);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_37);option.setDescripcion(R_CONST_37);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_38);option.setDescripcion(R_CONST_38);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_1);option.setDescripcion(R_CONST_1);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_2);option.setDescripcion(R_CONST_2);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_16);option.setDescripcion(R_CONST_16);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_13);option.setDescripcion(R_CONST_13);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_28);option.setDescripcion(R_CONST_28);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_24);option.setDescripcion(R_CONST_24);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_40);option.setDescripcion(R_CONST_40);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_41);option.setDescripcion(R_CONST_41);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_4);option.setDescripcion(R_CONST_4);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_3);option.setDescripcion(R_CONST_3);result.add(option);
     		option = new KeyValueObject();option.setCodigo(R_CONST_18);option.setDescripcion(R_CONST_18);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_15);option.setDescripcion(R_CONST_15);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_29);option.setDescripcion(R_CONST_29);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_43);option.setDescripcion(R_CONST_43);result.add(option);
     		option = new KeyValueObject();option.setCodigo(R_CONST_30);option.setDescripcion(R_CONST_30);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_31);option.setDescripcion(R_CONST_31);result.add(option);
     		option = new KeyValueObject();option.setCodigo(R_CONST_21);option.setDescripcion(R_CONST_21);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_19);option.setDescripcion(R_CONST_19);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_9);option.setDescripcion(R_CONST_9);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_6);option.setDescripcion(R_CONST_6);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_46);option.setDescripcion(R_CONST_46);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_44);option.setDescripcion(R_CONST_44);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_34);option.setDescripcion(R_CONST_34);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_33);option.setDescripcion(R_CONST_33);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_20);option.setDescripcion(R_CONST_20);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_22);option.setDescripcion(R_CONST_22);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_8);option.setDescripcion(R_CONST_8);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_7);option.setDescripcion(R_CONST_7);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_45);option.setDescripcion(R_CONST_45);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_47);option.setDescripcion(R_CONST_47);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_32);option.setDescripcion(R_CONST_32);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_35);option.setDescripcion(R_CONST_35);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_11);option.setDescripcion(R_CONST_11);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_39);option.setDescripcion(R_CONST_39);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_27);option.setDescripcion(R_CONST_27);result.add(option);
     		option = new KeyValueObject();option.setCodigo(R_CONST_25);option.setDescripcion(R_CONST_25);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_17);option.setDescripcion(R_CONST_17);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_12);option.setDescripcion(R_CONST_12);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_5);option.setDescripcion(R_CONST_5);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_0);option.setDescripcion(R_CONST_0);result.add(option);
 			option = new KeyValueObject();option.setCodigo(R_CONST_42);option.setDescripcion(R_CONST_42);result.add(option);
		    return result;
	}
	
	/**
	 * Obtener combo horas fin.
	 *
	 * @return combo horas fin
	 */
	public List<KeyValueObject> getComboHorasFin(){
	    List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option = null;
		option = new KeyValueObject();option.setCodigo(R_CONST_REF);option.setDescripcion(R_CONST_REF);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_14);option.setDescripcion(R_CONST_14);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_10);option.setDescripcion(R_CONST_10);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_26);option.setDescripcion(R_CONST_26);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_23);option.setDescripcion(R_CONST_23);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_37);option.setDescripcion(R_CONST_37);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_38);option.setDescripcion(R_CONST_38);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_1);option.setDescripcion(R_CONST_1);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_2);option.setDescripcion(R_CONST_2);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_16);option.setDescripcion(R_CONST_16);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_13);option.setDescripcion(R_CONST_13);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_28);option.setDescripcion(R_CONST_28);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_24);option.setDescripcion(R_CONST_24);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_40);option.setDescripcion(R_CONST_40);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_41);option.setDescripcion(R_CONST_41);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_4);option.setDescripcion(R_CONST_4);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_3);option.setDescripcion(R_CONST_3);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_18);option.setDescripcion(R_CONST_18);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_15);option.setDescripcion(R_CONST_15);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_29);option.setDescripcion(R_CONST_29);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_43);option.setDescripcion(R_CONST_43);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_30);option.setDescripcion(R_CONST_30);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_31);option.setDescripcion(R_CONST_31);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_21);option.setDescripcion(R_CONST_21);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_19);option.setDescripcion(R_CONST_19);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_9);option.setDescripcion(R_CONST_9);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_6);option.setDescripcion(R_CONST_6);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_46);option.setDescripcion(R_CONST_46);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_44);option.setDescripcion(R_CONST_44);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_34);option.setDescripcion(R_CONST_34);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_33);option.setDescripcion(R_CONST_33);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_20);option.setDescripcion(R_CONST_20);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_22);option.setDescripcion(R_CONST_22);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_8);option.setDescripcion(R_CONST_8);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_7);option.setDescripcion(R_CONST_7);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_45);option.setDescripcion(R_CONST_45);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_47);option.setDescripcion(R_CONST_47);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_32);option.setDescripcion(R_CONST_32);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_35);option.setDescripcion(R_CONST_35);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_11);option.setDescripcion(R_CONST_11);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_39);option.setDescripcion(R_CONST_39);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_27);option.setDescripcion(R_CONST_27);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_25);option.setDescripcion(R_CONST_25);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_17);option.setDescripcion(R_CONST_17);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_12);option.setDescripcion(R_CONST_12);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_5);option.setDescripcion(R_CONST_5);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_0);option.setDescripcion(R_CONST_0);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_42);option.setDescripcion(R_CONST_42);result.add(option);
			option = new KeyValueObject();option.setCodigo(R_CONST_36);option.setDescripcion(R_CONST_36);result.add(option);
	    return result;
}
	
	/**
	 * Obtener from.
	 *
	 * @return from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Modificar from.
	 *
	 * @param from new from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Obtener id from.
	 *
	 * @return id from
	 */
	public String getIdFrom() {
		return idFrom;
	}

	/**
	 * Modificar id from.
	 *
	 * @param idFrom new id from
	 */
	public void setIdFrom(String idFrom) {
		this.idFrom = idFrom;
	}

	/**
	 * Obtener var.
	 *
	 * @return var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * Modificar var.
	 *
	 * @param var new var
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * Agrega action message session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings(UNCHECKED)
	public void addActionMessageSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute(MSGPLT) != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute(MSGPLT);
		} else {
			msgPltList = new ArrayList<>();
		}
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute(MSGPLT, msgPltList);
		}
	}
	
	/**
	 * Agrega action warning message session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings(UNCHECKED)
	public void addActionWarningMessageSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute(MSGPLT_WARNING) != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute(MSGPLT_WARNING);
		} else {
			msgPltList = new ArrayList<>();
		}
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute(MSGPLT_WARNING, msgPltList);
		}
	}

	/**
	 * Agrega action error session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings(UNCHECKED)
	public void addActionErrorSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute(MSGPLT_ERROR) != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute(MSGPLT_ERROR);
		} else {
			msgPltList = new ArrayList<>();
		}
		// CAMBIO 24/05/2012 Si ya existe el mensaje que no se repita
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute(MSGPLT_ERROR, msgPltList);
		}
	}

	/**
	 * Agrega field error session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings(UNCHECKED)
	public void addFieldErrorSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute(MSGPLT_FIELD_ER) != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute(MSGPLT_FIELD_ER);
		} else {
			msgPltList = new ArrayList<>();
		}
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute(MSGPLT_FIELD_ER, msgPltList);
		}
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	///MIGRADO
	@Override
	public void validate() {
		String rol = (String) request.getSession().getAttribute(
				properties.getProperty("PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA", null));
		Integer idUsuario = (Integer) request.getSession().getAttribute(
				properties.getProperty("PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO", null));
		listaAplicacionesUsuario = servicioAplicacion.getAplicacionesMenu(rol, idUsuario);

		java.util.HashMap<Integer, Integer> mapPermisosAplicaciones = PlataformaMensajeriaUtil
				.getMapPermisosAplicaciones(
						PlataformaMensajeriaUtil.getUsuarioLogueado(request.getSession().getAttribute("infoUser"))
								.getUsername(), servicioUsuarioAplicacion);
		request.getSession().setAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES, mapPermisosAplicaciones);
	}



	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * Obtener lista aplicaciones usuario.
	 *
	 * @return the listaAplicacionesUsuario
	 */
	public List<AplicacionBean> getListaAplicacionesUsuario() {
		return listaAplicacionesUsuario;
	}

	/**
	 * Modificar lista aplicaciones usuario.
	 *
	 * @param listaAplicacionesUsuario            the listaAplicacionesUsuario to set
	 */
	public void setListaAplicacionesUsuario(List<AplicacionBean> listaAplicacionesUsuario) {
		this.listaAplicacionesUsuario = listaAplicacionesUsuario;
	}
}
