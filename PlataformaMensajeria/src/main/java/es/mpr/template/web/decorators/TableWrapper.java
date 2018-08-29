package es.mpr.template.web.decorators;

import java.util.Locale;
import java.util.ResourceBundle;

import org.displaytag.decorator.TableDecorator;

import com.map.j2ee.security.perm.model.GroupVO;
import com.map.j2ee.security.perm.model.UserVO;
import com.map.j2ee.util.Constants;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorMisimBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.ServidorWebPushBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;

/**
 * <p>
 * Decorator para el displayTag
 * </p>.
 *
 * @author Altran
 */
public class TableWrapper extends TableDecorator {

	/**
	 * Obtener user action.
	 *
	 * @return user action
	 */
	public String getUserAction() {

		UserVO lObject = (UserVO) getCurrentRowObject();
		String lId = lObject.getId();
		Locale miLocale = (Locale) getPageContext().getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY);

		ResourceBundle res;

		if (miLocale != null) {
			res = ResourceBundle.getBundle("ApplicationResources", miLocale, this.getClass().getClassLoader());
		} else {
			res = ResourceBundle.getBundle("ApplicationResources");
		}

		return "<a href=\"adminVerUsuario.action?id=" + lId + "\">" + res.getString("field.general.ver") + "</a> | <a href=\"adminEditarUsuario.action?id=" + lId + "\">" + res.getString("buttons.text.editar") + "</a> | <a href=\"adminBorrarUsuario.action?id=" + lId + "\">" + res
				.getString("buttons.text.borrar") + "</a>";

	}

	/**
	 * Obtener grupo action.
	 *
	 * @return grupo action
	 */
	public String getGrupoAction() {

		GroupVO lObject = (GroupVO) getCurrentRowObject();
		String lId = lObject.getId();
		Locale miLocale = (Locale) getPageContext().getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY);

		ResourceBundle res;

		if (miLocale != null) {
			res = ResourceBundle.getBundle("ApplicationResources", miLocale, this.getClass().getClassLoader());
		} else {
			res = ResourceBundle.getBundle("ApplicationResources");
		}

		return "<a href=\"adminVerGrupo.action?id=" + lId + "\">" + res.getString("field.general.ver") + "</a> | " + "<a href=\"adminEditarGrupo.action?id=" + lId + "\">" + res.getString("buttons.text.editar") + "</a> | " + "<a href=\"adminBorrarGrupo.action?id=" + lId + "\">" + res
				.getString("buttons.text.borrar") + "</a>";
	}

	/**
	 * Obtener organismo action.
	 *
	 * @return organismo action
	 */
	public String getOrganismoAction() {

		OrganismoBean organismo = (OrganismoBean) getCurrentRowObject();
		String idOrganismo = organismo.getOrganismoId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editOrganismo.action?idOrganismo=" + idOrganismo + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + organismo.getNombre() + "');\" href=\"deleteOrganismo.action?idOrganismo=" + idOrganismo + "\"></a>";
	}
	
	/**
	 * Obtener servicio movil action.
	 *
	 * @return servicio movil action
	 */
	public String getServicioMovilAction() {

		ServicioMovilBean servicioMovil = (ServicioMovilBean) getCurrentRowObject();
		String idServicioMovil = servicioMovil.getServicioMovilId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServicioMovil.action?idServicioMovil=" + idServicioMovil + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servicioMovil.getNombre() + "');\" href=\"deleteServicioMovil.action?idServicioMovil=" + idServicioMovil + "\"></a>";
	}

//	public String getUnidadOrgAction() {
//
//		UnidadOrganizacionalBean unidadOrg = (UnidadOrganizacionalBean) getCurrentRowObject();
//		String idUnidadOrg = unidadOrg.getId().toString();
//		Locale miLocale = (Locale) getPageContext().getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY);
//		ResourceBundle res;
//
//		if (miLocale != null) {
//			res = ResourceBundle.getBundle("ApplicationResources", miLocale, this.getClass().getClassLoader());
//		} else {
//			res = ResourceBundle.getBundle("ApplicationResources");
//		}
//
//		return "<a href=\"editUnidadOrg.action?idUnidadOrg=" + idUnidadOrg + "\">" + res.getString("buttons.text.editar") + "</a> | <a href=\"deleteUnidadOrg.action?idUnidadOrg=" + idUnidadOrg + "\">" + res.getString("buttons.text.borrar") + "</a>";
//	}

	/**
 * Obtener servidor action.
 *
 * @return servidor action
 */
public String getServidorAction() {

		ServidorBean servidor = (ServidorBean) getCurrentRowObject();
		String idServidor = servidor.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\"  href=\"editServidor.action?idServidor=" + idServidor + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servidor.getNombre() + "');\" href=\"deleteServidor.action?idServidor=" + idServidor + "\"></a>";
	}

	/**
	 * Obtener proveedor SMS action.
	 *
	 * @return proveedor SMS action
	 */
	public String getProveedorSMSAction() {

		ProveedorSMSBean proveedorSMS = (ProveedorSMSBean) getCurrentRowObject();
		String idProveedorSMS = proveedorSMS.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editProveedorSMS.action?idProveedorSMS=" + idProveedorSMS + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + proveedorSMS.getNombre() + "');\" href=\"deleteProveedorSMS.action?idProveedorSMS=" + idProveedorSMS + "\"></a>";
	}
	
	/**
	 * Obtener proveedor misim action.
	 *
	 * @return proveedor misim action
	 */
	public String getProveedorMisimAction() {

		ProveedorMisimBean proveedorMisim = (ProveedorMisimBean) getCurrentRowObject();
		String idProveedor = proveedorMisim.getIdProveedor().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editProveedorMisim.action?idProveedor=" + idProveedor + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + proveedorMisim.getNombre() + "');\" href=\"deleteProveedorMisim.action?idProveedor=" + idProveedor + "\"></a>";
	}

	/**
	 * Obtener receptor SMS action.
	 *
	 * @return receptor SMS action
	 */
	public String getReceptorSMSAction() {

		ReceptorSMSBean receptorSMS = (ReceptorSMSBean) getCurrentRowObject();
		String idReceptorSMS = receptorSMS.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editReceptorSMS.action?idReceptorSMS=" + idReceptorSMS + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + receptorSMS.getNombre() + "');\" href=\"deleteReceptorSMS.action?idReceptorSMS=" + idReceptorSMS + "\"></a>";
	}

	/**
	 * Obtener servidor push action.
	 *
	 * @return servidor push action
	 */
	public String getServidorPushAction() {

		ServidorPushBean servidorPush = (ServidorPushBean) getCurrentRowObject();
		String idServidorPush = servidorPush.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServidorPush.action?idServidorPush=" + idServidorPush + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servidorPush.getNombre() + "');\" href=\"deleteServidorPush.action?idServidorPush=" + idServidorPush + "\"></a>";
	}
	
	/**
	 * Obtener servidor web push action.
	 *
	 * @return servidor web push action
	 */
	public String getServidorWebPushAction() {

		ServidorWebPushBean servidorWebPush = (ServidorWebPushBean) getCurrentRowObject();
		String idServidorWebPush = servidorWebPush.getServidorWebPushId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServidorWebPush.action?idServidorWebPush=" + idServidorWebPush + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servidorWebPush.getNombre() + "');\" href=\"deleteServidorWebPush.action?idServidorWebPush=" + idServidorWebPush + "\"></a>";
	}
	
	/**
	 * Obtener aplicacion action.
	 *
	 * @return aplicacion action
	 */
	public String getAplicacionAction() {

		AplicacionBean aplicacion = (AplicacionBean) getCurrentRowObject();
		String idAplicacion = aplicacion.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editAplicacion.action?idAplicacion=" + idAplicacion + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + aplicacion.getNombre() + "');\" href=\"deleteAplicacion.action?idAplicacion=" + idAplicacion + "\"></a>";
	}

	/**
	 * Obtener servicio action.
	 *
	 * @return servicio action
	 */
	public String getServicioAction() {

		ServicioBean servicio = (ServicioBean) getCurrentRowObject();
		String idServicio = servicio.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServicio.action?idServicio=" + idServicio + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servicio.getNombre() + "');\" href=\"deleteServicio.action?idServicio=" + idServicio + "\"></a>";
	}

	/**
	 * Obtener planificacion action.
	 *
	 * @return planificacion action
	 */
	public String getPlanificacionAction() {

		PlanificacionBean planificacion = (PlanificacionBean) getCurrentRowObject();
		String idPlanificacion = planificacion.getPlanificacionId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editPlanificacion.action?idPlanificacion=" + idPlanificacion + "&nAction=updatePlanificacion\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('Planificación[" + planificacion.getDias() + "]');\" href=\"deletePlanificacion.action?idPlanificacion=" + idPlanificacion + "\"></a>";
	}

	/**
	 * Obtener usuario action.
	 *
	 * @return usuario action
	 */
	public String getUsuarioAction() {

		UsuarioBean usuario = (UsuarioBean) getCurrentRowObject();
		String idUsuario = usuario.getUsuarioId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editUsuario.action?idUsuario=" + idUsuario + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + usuario.getNombre() + "');\" href=\"deleteUsuario.action?idUsuario=" + idUsuario + "\"></a>";
	}

	/**
	 * Obtener gestion envios action.
	 *
	 * @return gestion envios action
	 */
	public String getGestionEnviosAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();

		// filtrado por lotes
		if (null == gestionEnvioBean.getMensajeId() || gestionEnvioBean.getMensajeId() == 0) {
			String tipoEnvio = "viewLote";
			String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getIdLote() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadLote(this,'" + gestionEnvioBean.getIdLote() + "','" + tipoEnvio + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioBean
					.getIdLote() + "\"></span><div>";
			return btnAjax;
		} else if(null != gestionEnvioBean.getVistaEnviosId() && gestionEnvioBean.getVistaEnviosId().equals(3)){
			String tipoEnvio = "viewHistorico";
			String btnAjax = "<div id=\"ajaxloader_ajax_historico" + gestionEnvioBean.getDestinatariosMensajes() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadMensajeHistorico(this,'" + gestionEnvioBean.getMensajeId() + "','" + tipoEnvio + "','" + gestionEnvioBean.getDestinatariosMensajes() + "',' ')"
					+ "\" name=\"ajax\" id=\"ajax_historico" + gestionEnvioBean.getDestinatariosMensajes() + "\"></span><div>";
			return btnAjax;
		}else{
			String tipoMensaje = "";
			switch (gestionEnvioBean.getCanalId().intValue()) {
			case 1:
				tipoMensaje = "EMAIL";
				break;
			case 2:
				tipoMensaje = "SMS";
				break;
			case 3:
				tipoMensaje = "RECEPCION SMS";
				break;
			case 4:
				tipoMensaje = "NOTIFICACION PUSH";
				break;
			}
			String tipoEnvio = "viewMensaje";
			String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getMensajeId() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadMensaje(this,'" + gestionEnvioBean.getMensajeId() + "','" + tipoEnvio + "','" + tipoMensaje + "','" + gestionEnvioBean
					.getIdLote() + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioBean.getMensajeId() + "\"></span><div>";
			
			return btnAjax;
		}
	}
	
	/**
	 * Obtener ver misim action.
	 *
	 * @return ver misim action
	 */
	public String getVerMisimAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String btnAjax = null;
		
		if(gestionEnvioBean.isBotonIntercambios()){
			String tipoEnvio = "viewMisim";
			btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getIdLote() + "_" + gestionEnvioBean.getMensajeId() + "\" class=\"center\"><span class=\"btnDetails gestionEnvio_link\" title=\"Ver Intercambios\" onclick=\"return loadMisim(this,'" + gestionEnvioBean.getIdLote() + "','" + tipoEnvio +  "','" + gestionEnvioBean.getMensajeId() + "')\" name=\"ajax\" id=\"ajax_" + 
					gestionEnvioBean.getIdLote() + "_" + gestionEnvioBean.getMensajeId() + "\"></span><div>";
		}
		
		else {
			btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getIdLote() + "_" + gestionEnvioBean.getMensajeId() + "\" class=\"center\"><span class=\"btnDetailsOff gestionEnvio_link\" disabled=\"true\" title=\"Sin intercambios\" name=\"ajax\" id=\"ajax_" + gestionEnvioBean.getIdLote() + "_" + gestionEnvioBean.getMensajeId() + "\"></span><div>";
		}
		return btnAjax;
	}
	
//	public String getVerPassbookAction() {
//		OrganismoBean organismoBean = (OrganismoBean) getCurrentRowObject();
//		
//		String btnAjax = null;
//
//			String tipoEnvio = "viewPassbook";
//			btnAjax = "<div id=\"ajaxloader_ajax_" + organismoBean.getOrganismoId() + "\" class=\"center\"><span class=\"btnPassbook organismo_link\" title=\"Ver Passbook\" onclick=\"return loadPassbook(this,'" + organismoBean.getOrganismoId() + "','" + tipoEnvio + "')\" name=\"ajax\" id=\"ajax_" + 
//					organismoBean.getOrganismoId() + "\"></span><div>";
//		
//		return btnAjax;
//	}
	
	/**
 * Obtener xml peticion action.
 *
 * @return xml peticion action
 */
public String getXmlPeticionAction() {
		ViewMisimBean viewMisimBean = (ViewMisimBean) getCurrentRowObject();
		String idPeticion = viewMisimBean.getIdPeticion().toString();

			return "<a  class=\"button\" title=\"Descargar\" href=\"loadXmlPeticion.action?idPeticion=" + idPeticion + "\">Descargar</a>";
			
	}
	
	/**
	 * Obtener xml respuesta action.
	 *
	 * @return xml respuesta action
	 */
	public String getXmlRespuestaAction() {
		ViewMisimBean viewMisimBean = (ViewMisimBean) getCurrentRowObject();
		String idPeticion = viewMisimBean.getIdPeticion().toString();
		
			return "<a  class=\"button\" title=\"Descargar\" href=\"loadXmlRespuesta.action?idPeticion=" + idPeticion + "\">Descargar</a>";
	}

	/**
	 * Obtener gestion lotes action.
	 *
	 * @return gestion lotes action
	 */
	public String getGestionLotesAction() {
		MensajeBean mensajeBean = (MensajeBean) getCurrentRowObject();
		String tipoEnvio = "viewMensaje";
		String btnAjax = "<div id=\"ajaxloader_ajax_" + mensajeBean.getMensajeId() + "\" class=\"center\"><span class=\"button\" title=\"Ver\" onclick=\"return loadMensaje(this,'" + mensajeBean.getMensajeId() + "','" + tipoEnvio + "','" + mensajeBean.getTipoMensaje() + "','" + mensajeBean
				.getLoteEnvioId() + "')\" name=\"ajax\" id=\"ajax_" + mensajeBean.getMensajeId() + "\">Ver</span><div>";
		return btnAjax;
	}
	
	/**
	 * Obtener gestion lotes historicos action.
	 *
	 * @return gestion lotes historicos action
	 */
	public String getGestionLotesHistoricosAction() {
		MensajeHistoricosBean mensajeBean = (MensajeHistoricosBean) getCurrentRowObject();
		String tipoEnvio = "viewMensaje";
		String btnAjax = "<div id=\"ajaxloader_ajax_" + mensajeBean.getMensajeId() + "\" class=\"center\"><span class=\"button\" title=\"Ver\" onclick=\"return loadMensajeHist(this,'" + mensajeBean.getMensajeId() + "','" + tipoEnvio + "','" + mensajeBean.getTipoMensaje() + "','" + mensajeBean
				.getLoteEnvioId() + "')\" name=\"ajax\" id=\"ajax_" + mensajeBean.getMensajeId() + "\">Ver</span><div>";
		return btnAjax;
	}

	/**
	 * Obtener gestion envios historicos action.
	 *
	 * @return gestion envios historicos action
	 */
	public String getGestionEnviosHistoricosAction() {
		GestionEnvioHistoricoBean gestionEnvioHistoricoBean = (GestionEnvioHistoricoBean) getCurrentRowObject();

		// filtrado por lotes
				if (null == gestionEnvioHistoricoBean.getMensajeId() || gestionEnvioHistoricoBean.getMensajeId() == 0) {
					String tipoEnvio = "viewLoteHistorico";
					String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getIdLote() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadLoteHist(this,'" + gestionEnvioHistoricoBean.getIdLote() + "','" + tipoEnvio + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioHistoricoBean
							.getIdLote() + "\"></span><div>";
					return btnAjax;
				}else if(null != gestionEnvioHistoricoBean.getVistaEnviosId() && gestionEnvioHistoricoBean.getVistaEnviosId().equals(3)){
					String tipoEnvio = "viewHistoricoHist";
					String btnAjax = "<div id=\"ajaxloader_ajax_historico" + gestionEnvioHistoricoBean.getDestinatariosMensajes() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadMensajeHistorico(this,'" + gestionEnvioHistoricoBean.getMensajeId() + "','" + tipoEnvio + "','" + gestionEnvioHistoricoBean.getDestinatariosMensajes() + "',' ')"
							+ "\" name=\"ajax\" id=\"ajax_historico" + gestionEnvioHistoricoBean.getDestinatariosMensajes() + "\"></span><div>";
					return btnAjax;
				} else {
					String tipoMensaje = "";
					switch (gestionEnvioHistoricoBean.getCanalId().intValue()) {
					case 1:
						tipoMensaje = "EMAIL";
						break;
					case 2:
						tipoMensaje = "SMS";
						break;
					case 3:
						tipoMensaje = "RECEPCION SMS";
						break;
					case 4:
						tipoMensaje = "NOTIFICACION PUSH";
						break;
					}
					String tipoEnvio = "viewMensaje";
					String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getMensajeId() + "\" class=\"center\"><span class=\"btnMagGlass gestionEnvio_link\" title=\"Ver Detalle\" onclick=\"return loadMensajeHist(this,'" + gestionEnvioHistoricoBean.getMensajeId() + "','" + tipoEnvio + "','" + tipoMensaje + "','" + gestionEnvioHistoricoBean
							.getIdLote() + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioHistoricoBean.getMensajeId() + "\"></span><div>";
					return btnAjax;
				}

	}
	
	/**
	 * Obtener ver misim historico action.
	 *
	 * @return ver misim historico action
	 */
	public String getVerMisimHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioHistoricoBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String btnAjax = null;
	
		if(gestionEnvioHistoricoBean.isBotonIntercambios()){
			String tipoEnvio = "viewMisimHistorico";
			btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getIdLote() + "_" + gestionEnvioHistoricoBean.getMensajeId() + "\" class=\"center\"><span class=\"btnDetails gestionEnvio_link\" title=\"Ver Intercambios\" onclick=\"return loadMisim(this,'" + gestionEnvioHistoricoBean.getIdLote() + "','" + tipoEnvio +  "','" + gestionEnvioHistoricoBean.getMensajeId() + "')\" name=\"ajax\" id=\"ajax_" + 
				gestionEnvioHistoricoBean.getIdLote() + "_" + gestionEnvioHistoricoBean.getMensajeId() + "\"></span><div>";
		}
	
		else {
			btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getIdLote() + "_" + gestionEnvioHistoricoBean.getMensajeId() + "\" class=\"center\"><span class=\"btnDetailsOff gestionEnvio_link\" disabled=\"true\" title=\"Sin intercambios\" name=\"ajax\" id=\"ajax_" + gestionEnvioHistoricoBean.getIdLote() + "_" + gestionEnvioHistoricoBean.getMensajeId() + "\"></span><div>";
		}
		return btnAjax;
	}
	
	/**
	 * Obtener historico destinatario action.
	 *
	 * @return historico destinatario action
	 */
	public String getHistoricoDestinatarioAction() {
		DestinatariosMensajesBean dmBean = (DestinatariosMensajesBean) getCurrentRowObject();
		String tipoEnvio = "viewHistorico";
		String btnAjax = "<div id=\"ajaxloader_ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\" class=\"center\"><span class=\"button\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + dmBean.getMensajeId() + "','" + tipoEnvio + "','" + dmBean.getDestinatariosMensajes() + "','" + dmBean
				.getTipoMensaje() + "')\" name=\"ajax\" id=\"ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\">Ver</span><div>";
		return btnAjax;

	}
	
	/**
	 * Obtener historico hist destinatario action.
	 *
	 * @return historico hist destinatario action
	 */
	public String getHistoricoHistDestinatarioAction() {
		DestinatariosMensajesHistoricosBean dmBean = (DestinatariosMensajesHistoricosBean) getCurrentRowObject();
		String tipoEnvio = "viewHistoricoHist";
		String btnAjax = "<div id=\"ajaxloader_ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\" class=\"center\"><span class=\"button\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + dmBean.getMensajeId() + "','" + tipoEnvio + "','" + dmBean.getDestinatariosMensajes() + "','" + dmBean
				.getTipoMensaje() + "')\" name=\"ajax\" id=\"ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\">Ver</span><div>";
		return btnAjax;

	}
	
	/**
	 * Obtener destinatario action.
	 *
	 * @return destinatario action
	 */
	public String getDestinatarioAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getDestinatario()+"\">"+gestionEnvioBean.getDestinatario()+"</span>";
		return spanTitle;
	}
	
	/**
	 * Obtener nombre lote action.
	 *
	 * @return nombre lote action
	 */
	public String getNombreLoteAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getLoteEnvio()+"\">"+gestionEnvioBean.getLoteEnvio()+"</span>";
		return spanTitle;
	}
	
	/**
	 * Obtener nombre servicio action.
	 *
	 * @return nombre servicio action
	 */
	public String getNombreServicioAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getServicio()+"\">"+gestionEnvioBean.getServicio()+"</span>";
		return spanTitle;
	}
	
	/**
	 * Obtener destinatario historico action.
	 *
	 * @return destinatario historico action
	 */
	public String getDestinatarioHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getDestinatario()+"\">"+gestionEnvioBean.getDestinatario()+"</span>";
		return spanTitle;
	}
	
	/**
	 * Obtener nombre lote historico action.
	 *
	 * @return nombre lote historico action
	 */
	public String getNombreLoteHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getLoteEnvio()+"\">"+gestionEnvioBean.getLoteEnvio()+"</span>";
		return spanTitle;
	}
	
	/**
	 * Obtener nombre servicio historico action.
	 *
	 * @return nombre servicio historico action
	 */
	public String getNombreServicioHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getServicio()+"\">"+gestionEnvioBean.getServicio()+"</span>";
		return spanTitle;
	}
	
}
