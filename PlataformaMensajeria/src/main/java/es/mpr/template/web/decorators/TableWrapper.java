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
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;

/**
 * <p>
 * Decorator para el displayTag
 * </p>
 * 
 * @author Altran
 * 
 */
public class TableWrapper extends TableDecorator {

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

	public String getOrganismoAction() {

		OrganismoBean organismo = (OrganismoBean) getCurrentRowObject();
		String idOrganismo = organismo.getOrganismoId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editOrganismo.action?idOrganismo=" + idOrganismo + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + organismo.getNombre() + "');\" href=\"deleteOrganismo.action?idOrganismo=" + idOrganismo + "\"></a>";
	}
	
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

	public String getServidorAction() {

		ServidorBean servidor = (ServidorBean) getCurrentRowObject();
		String idServidor = servidor.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\"  href=\"editServidor.action?idServidor=" + idServidor + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servidor.getNombre() + "');\" href=\"deleteServidor.action?idServidor=" + idServidor + "\"></a>";
	}

	public String getProveedorSMSAction() {

		ProveedorSMSBean proveedorSMS = (ProveedorSMSBean) getCurrentRowObject();
		String idProveedorSMS = proveedorSMS.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editProveedorSMS.action?idProveedorSMS=" + idProveedorSMS + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + proveedorSMS.getNombre() + "');\" href=\"deleteProveedorSMS.action?idProveedorSMS=" + idProveedorSMS + "\"></a>";
	}

	public String getReceptorSMSAction() {

		ReceptorSMSBean receptorSMS = (ReceptorSMSBean) getCurrentRowObject();
		String idReceptorSMS = receptorSMS.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editReceptorSMS.action?idReceptorSMS=" + idReceptorSMS + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + receptorSMS.getNombre() + "');\" href=\"deleteReceptorSMS.action?idReceptorSMS=" + idReceptorSMS + "\"></a>";
	}

	public String getServidorPushAction() {

		ServidorPushBean servidorPush = (ServidorPushBean) getCurrentRowObject();
		String idServidorPush = servidorPush.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServidorPush.action?idServidorPush=" + idServidorPush + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servidorPush.getNombre() + "');\" href=\"deleteServidorPush.action?idServidorPush=" + idServidorPush + "\"></a>";
	}

	public String getAplicacionAction() {

		AplicacionBean aplicacion = (AplicacionBean) getCurrentRowObject();
		String idAplicacion = aplicacion.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editAplicacion.action?idAplicacion=" + idAplicacion + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + aplicacion.getNombre() + "');\" href=\"deleteAplicacion.action?idAplicacion=" + idAplicacion + "\"></a>";
	}

	public String getServicioAction() {

		ServicioBean servicio = (ServicioBean) getCurrentRowObject();
		String idServicio = servicio.getId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editServicio.action?idServicio=" + idServicio + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + servicio.getNombre() + "');\" href=\"deleteServicio.action?idServicio=" + idServicio + "\"></a>";
	}

	public String getPlanificacionAction() {

		PlanificacionBean planificacion = (PlanificacionBean) getCurrentRowObject();
		String idPlanificacion = planificacion.getPlanificacionId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editPlanificacion.action?idPlanificacion=" + idPlanificacion + "&nAction=updatePlanificacion\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('Planificación[" + planificacion.getDias() + "]');\" href=\"deletePlanificacion.action?idPlanificacion=" + idPlanificacion + "\"></a>";
	}

	public String getUsuarioAction() {

		UsuarioBean usuario = (UsuarioBean) getCurrentRowObject();
		String idUsuario = usuario.getUsuarioId().toString();

		return "<a  class=\"btnEdit\" title=\"Editar\" href=\"editUsuario.action?idUsuario=" + idUsuario + "\"></a> <a class=\"btnDelete\" title=\"Eliminar\" onclick=\"return confirmDelete('" + usuario.getNombre() + "');\" href=\"deleteUsuario.action?idUsuario=" + idUsuario + "\"></a>";
	}

	public String getGestionEnviosAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();

		// filtrado por lotes
		if (null == gestionEnvioBean.getMensajeId() || gestionEnvioBean.getMensajeId() == 0) {
			String tipoEnvio = "viewLote";
			String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getIdLote() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadLote(this,'" + gestionEnvioBean.getIdLote() + "','" + tipoEnvio + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioBean
					.getIdLote() + "\"></span><div>";
			return btnAjax;
		} else if(null != gestionEnvioBean.getVistaEnviosId() && gestionEnvioBean.getVistaEnviosId().equals(3)){
			String tipoEnvio = "viewHistorico";
			String btnAjax = "<div id=\"ajaxloader_ajax_historico" + gestionEnvioBean.getDestinatariosMensajes() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + gestionEnvioBean.getMensajeId() + "','" + tipoEnvio + "','" + gestionEnvioBean.getDestinatariosMensajes() + "',' ')"
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
			String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioBean.getMensajeId() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensaje(this,'" + gestionEnvioBean.getMensajeId() + "','" + tipoEnvio + "','" + tipoMensaje + "','" + gestionEnvioBean
					.getIdLote() + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioBean.getMensajeId() + "\"></span><div>";
			
			return btnAjax;
		}
	}

	public String getGestionLotesAction() {
		MensajeBean mensajeBean = (MensajeBean) getCurrentRowObject();
		String tipoEnvio = "viewMensaje";
		String btnAjax = "<div id=\"ajaxloader_ajax_" + mensajeBean.getMensajeId() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensaje(this,'" + mensajeBean.getMensajeId() + "','" + tipoEnvio + "','" + mensajeBean.getTipoMensaje() + "','" + mensajeBean
				.getLoteEnvioId() + "')\" name=\"ajax\" id=\"ajax_" + mensajeBean.getMensajeId() + "\"></span><div>";
		return btnAjax;
	}
	
	public String getGestionLotesHistoricosAction() {
		MensajeHistoricosBean mensajeBean = (MensajeHistoricosBean) getCurrentRowObject();
		String tipoEnvio = "viewMensaje";
		String btnAjax = "<div id=\"ajaxloader_ajax_" + mensajeBean.getMensajeId() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHist(this,'" + mensajeBean.getMensajeId() + "','" + tipoEnvio + "','" + mensajeBean.getTipoMensaje() + "','" + mensajeBean
				.getLoteEnvioId() + "')\" name=\"ajax\" id=\"ajax_" + mensajeBean.getMensajeId() + "\"></span><div>";
		return btnAjax;
	}

	public String getGestionEnviosHistoricosAction() {
		GestionEnvioHistoricoBean gestionEnvioHistoricoBean = (GestionEnvioHistoricoBean) getCurrentRowObject();

		// filtrado por lotes
				if (null == gestionEnvioHistoricoBean.getMensajeId() || gestionEnvioHistoricoBean.getMensajeId() == 0) {
					String tipoEnvio = "viewLoteHistorico";
					String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getIdLote() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadLoteHist(this,'" + gestionEnvioHistoricoBean.getIdLote() + "','" + tipoEnvio + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioHistoricoBean
							.getIdLote() + "\"></span><div>";
					return btnAjax;
				}else if(null != gestionEnvioHistoricoBean.getVistaEnviosId() && gestionEnvioHistoricoBean.getVistaEnviosId().equals(3)){
					String tipoEnvio = "viewHistoricoHist";
					String btnAjax = "<div id=\"ajaxloader_ajax_historico" + gestionEnvioHistoricoBean.getDestinatariosMensajes() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + gestionEnvioHistoricoBean.getMensajeId() + "','" + tipoEnvio + "','" + gestionEnvioHistoricoBean.getDestinatariosMensajes() + "',' ')"
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
					String btnAjax = "<div id=\"ajaxloader_ajax_" + gestionEnvioHistoricoBean.getMensajeId() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHist(this,'" + gestionEnvioHistoricoBean.getMensajeId() + "','" + tipoEnvio + "','" + tipoMensaje + "','" + gestionEnvioHistoricoBean
							.getIdLote() + "')\" name=\"ajax\" id=\"ajax_" + gestionEnvioHistoricoBean.getMensajeId() + "\"></span><div>";
					return btnAjax;
				}

	}

	public String getHistoricoDestinatarioAction() {
		DestinatariosMensajesBean dmBean = (DestinatariosMensajesBean) getCurrentRowObject();
		String tipoEnvio = "viewHistorico";
		String btnAjax = "<div id=\"ajaxloader_ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + dmBean.getMensajeId() + "','" + tipoEnvio + "','" + dmBean.getDestinatariosMensajes() + "','" + dmBean
				.getTipoMensaje() + "')\" name=\"ajax\" id=\"ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\"></span><div>";
		return btnAjax;

	}
	
	public String getHistoricoHistDestinatarioAction() {
		DestinatariosMensajesHistoricosBean dmBean = (DestinatariosMensajesHistoricosBean) getCurrentRowObject();
		String tipoEnvio = "viewHistoricoHist";
		String btnAjax = "<div id=\"ajaxloader_ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\"><span class=\"btnView gestionEnvio_link\" title=\"Ver\" onclick=\"return loadMensajeHistorico(this,'" + dmBean.getMensajeId() + "','" + tipoEnvio + "','" + dmBean.getDestinatariosMensajes() + "','" + dmBean
				.getTipoMensaje() + "')\" name=\"ajax\" id=\"ajax_historico_destinatario" + dmBean.getDestinatariosMensajes() + "\"></span><div>";
		return btnAjax;

	}
	
	public String getDestinatarioAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getDestinatario()+"\">"+gestionEnvioBean.getDestinatario()+"</span>";
		return spanTitle;
	}
	
	public String getNombreLoteAction() {
		GestionEnvioBean gestionEnvioBean = (GestionEnvioBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getLoteEnvio()+"\">"+gestionEnvioBean.getLoteEnvio()+"</span>";
		return spanTitle;
	}
	
	public String getDestinatarioHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getDestinatario()+"\">"+gestionEnvioBean.getDestinatario()+"</span>";
		return spanTitle;
	}
	
	public String getNombreLoteHistoricoAction() {
		GestionEnvioHistoricoBean gestionEnvioBean = (GestionEnvioHistoricoBean) getCurrentRowObject();
		String spanTitle = "<span title=\""+gestionEnvioBean.getLoteEnvio()+"\">"+gestionEnvioBean.getLoteEnvio()+"</span>";
		return spanTitle;
	}
}
