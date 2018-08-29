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

//	private List<KeyValueObject> comboHorasInicio = new ArrayList<KeyValueObject>();
//	private List<KeyValueObject> comboHorasFin = new ArrayList<KeyValueObject>();
	
	/**
 * Obtener combo horas inicio.
 *
 * @return combo horas inicio
 */
public List<KeyValueObject> getComboHorasInicio(){
		    List<KeyValueObject> result = new ArrayList<KeyValueObject>();
	        KeyValueObject option = null;
//	        ArrayList<EstadoBean> keys = null;
			option = new KeyValueObject();option.setCodigo("00:00");option.setDescripcion("00:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("00:30");option.setDescripcion("00:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("01:00");option.setDescripcion("01:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("01:30");option.setDescripcion("01:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("02:00");option.setDescripcion("02:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("02:30");option.setDescripcion("02:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("03:00");option.setDescripcion("03:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("03:30");option.setDescripcion("03:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("04:00");option.setDescripcion("04:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("04:30");option.setDescripcion("04:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("05:00");option.setDescripcion("05:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("05:30");option.setDescripcion("05:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("06:00");option.setDescripcion("06:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("06:30");option.setDescripcion("06:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("07:00");option.setDescripcion("07:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("07:30");option.setDescripcion("07:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("08:00");option.setDescripcion("08:00");result.add(option);
     		option = new KeyValueObject();option.setCodigo("08:30");option.setDescripcion("08:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("09:00");option.setDescripcion("09:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("09:30");option.setDescripcion("09:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("10:00");option.setDescripcion("10:00");result.add(option);
     		option = new KeyValueObject();option.setCodigo("10:30");option.setDescripcion("10:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("11:00");option.setDescripcion("11:00");result.add(option);
     		option = new KeyValueObject();option.setCodigo("11:30");option.setDescripcion("11:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("12:00");option.setDescripcion("12:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("12:30");option.setDescripcion("12:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("13:00");option.setDescripcion("13:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("13:30");option.setDescripcion("13:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("14:00");option.setDescripcion("14:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("14:30");option.setDescripcion("14:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("15:00");option.setDescripcion("15:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("15:30");option.setDescripcion("15:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("16:00");option.setDescripcion("16:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("16:30");option.setDescripcion("16:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("17:00");option.setDescripcion("17:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("17:30");option.setDescripcion("17:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("18:00");option.setDescripcion("18:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("18:30");option.setDescripcion("18:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("19:00");option.setDescripcion("19:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("19:30");option.setDescripcion("19:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("20:00");option.setDescripcion("20:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("20:30");option.setDescripcion("20:30");result.add(option);
     		option = new KeyValueObject();option.setCodigo("21:00");option.setDescripcion("21:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("21:30");option.setDescripcion("21:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("22:00");option.setDescripcion("22:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("22:30");option.setDescripcion("22:30");result.add(option);
 			option = new KeyValueObject();option.setCodigo("23:00");option.setDescripcion("23:00");result.add(option);
 			option = new KeyValueObject();option.setCodigo("23:30");option.setDescripcion("23:30");result.add(option);
		    return result;
	}
	
	/**
	 * Obtener combo horas fin.
	 *
	 * @return combo horas fin
	 */
	public List<KeyValueObject> getComboHorasFin(){
	    List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
//        ArrayList<EstadoBean> keys = null;
		option = new KeyValueObject();option.setCodigo("00:00");option.setDescripcion("00:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("00:30");option.setDescripcion("00:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("01:00");option.setDescripcion("01:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("01:30");option.setDescripcion("01:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("02:00");option.setDescripcion("02:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("02:30");option.setDescripcion("02:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("03:00");option.setDescripcion("03:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("03:30");option.setDescripcion("03:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("04:00");option.setDescripcion("04:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("04:30");option.setDescripcion("04:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("05:00");option.setDescripcion("05:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("05:30");option.setDescripcion("05:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("06:00");option.setDescripcion("06:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("06:30");option.setDescripcion("06:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("07:00");option.setDescripcion("07:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("07:30");option.setDescripcion("07:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("08:00");option.setDescripcion("08:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("08:30");option.setDescripcion("08:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("09:00");option.setDescripcion("09:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("09:30");option.setDescripcion("09:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("10:00");option.setDescripcion("10:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("10:30");option.setDescripcion("10:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("11:00");option.setDescripcion("11:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("11:30");option.setDescripcion("11:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("12:00");option.setDescripcion("12:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("12:30");option.setDescripcion("12:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("13:00");option.setDescripcion("13:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("13:30");option.setDescripcion("13:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("14:00");option.setDescripcion("14:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("14:30");option.setDescripcion("14:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("15:00");option.setDescripcion("15:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("15:30");option.setDescripcion("15:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("16:00");option.setDescripcion("16:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("16:30");option.setDescripcion("16:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("17:00");option.setDescripcion("17:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("17:30");option.setDescripcion("17:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("18:00");option.setDescripcion("18:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("18:30");option.setDescripcion("18:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("19:00");option.setDescripcion("19:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("19:30");option.setDescripcion("19:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("20:00");option.setDescripcion("20:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("20:30");option.setDescripcion("20:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("21:00");option.setDescripcion("21:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("21:30");option.setDescripcion("21:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("22:00");option.setDescripcion("22:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("22:30");option.setDescripcion("22:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("23:00");option.setDescripcion("23:00");result.add(option);
			option = new KeyValueObject();option.setCodigo("23:30");option.setDescripcion("23:30");result.add(option);
			option = new KeyValueObject();option.setCodigo("24:00");option.setDescripcion("24:00");result.add(option);
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
	@SuppressWarnings("unchecked")
	public void addActionMessageSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute("MSGPLT") != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute("MSGPLT");
		} else {
			msgPltList = new ArrayList<String>();
		}
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute("MSGPLT", msgPltList);
		}
	}

	/**
	 * Agrega action error session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings("unchecked")
	public void addActionErrorSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute("MSGPLT_ERROR") != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute("MSGPLT_ERROR");
		} else {
			msgPltList = new ArrayList<String>();
		}
		// CAMBIO 24/05/2012 Si ya existe el mensaje que no se repita
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute("MSGPLT_ERROR", msgPltList);
		}
	}

	/**
	 * Agrega field error session.
	 *
	 * @param message the message
	 */
	@SuppressWarnings("unchecked")
	public void addFieldErrorSession(String message) {
		List<String> msgPltList = null;
		if (getRequest().getSession().getAttribute("MSGPLT_FIELD_ERROR") != null) {
			msgPltList = (List<String>) getRequest().getSession().getAttribute("MSGPLT_FIELD_ERROR");
		} else {
			msgPltList = new ArrayList<String>();
		}
		if (!msgPltList.contains(message)) {
			msgPltList.add(message);
			getRequest().getSession().setAttribute("MSGPLT_FIELD_ERROR", msgPltList);
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
