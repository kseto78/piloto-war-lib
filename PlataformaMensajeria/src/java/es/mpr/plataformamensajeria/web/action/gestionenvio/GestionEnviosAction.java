package es.mpr.plataformamensajeria.web.action.gestionenvio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis.encoding.Base64;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.model.views.ViewGestionEnviosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 * 
 * @version 1.0
 * @author i-nercya
 * 
 */
public class GestionEnviosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";

	private static final Integer PAGESIZE = new Integer(10); // Elementos por pagina
	private int pageSize = 20;
	private int pageSizeM = 20;
	 private List<ArrayList<String>> aaData;

    
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPageSize = new ArrayList<KeyValueObject>();

	private String[] checkDelList;

	private List<GestionEnvioBean> listaGestionEnvios = null;
	private List<MensajeBean> listaGestionEnviosMensajes = null;
	private List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje = null;
	private List<HistoricoBean> listaHistoricosMensaje = null;

	private ServicioAplicacion servicioAplicacion;
	private ServicioServidor servicioServidor;
	private ServicioServicio servicioServicio;
	private ServicioEstado servicioEstado;
	private ServicioCanal servicioCanal;
	private ServicioGestionEnvios servicioGestionEnvios;

	private String resultCount;
	private GestionEnvioBean gestionEnvioBean;
	private DetalleEnvioBean detalleEmail;
	private DetalleLoteBean detalleLote;
	private GestionEnvioBean detalleMensaje;
	private DestinatariosMensajesBean destinatariosMensajes;
	private String idEnvio;
	private String idAdjunto;
	private String idEmail;
	private String idLote;
	private String idMensaje;
	private String idDestinatariosMensajes;
	private String operacionMsg;
	private String CheckAllS;

	private String vistaEnviosIdSelected;

	public String getOperacionMsg() {
		return operacionMsg;
	}

	public void setOperacionMsg(String operacionMsg) {
		this.operacionMsg = operacionMsg;
	}

	public String getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}

	private String adjuntoDescargable;

	public String getAdjuntoDescargable() {
		return adjuntoDescargable;
	}

	public void setAdjuntoDescargable(String adjuntoDescargable) {
		this.adjuntoDescargable = adjuntoDescargable;
	}

	public String getIdAdjunto() {
		return idAdjunto;
	}

	public void setIdAdjunto(String idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String newSearch() throws BusinessException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (validUsuario()) {
			int page = getPage("tableId"); // Pagina a mostrar
			String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
			String columnSort = getColumnSort("tableId"); // Columna usada para ordenar
			int inicio = (page - 1) * pageSize;
			if (gestionEnvioBean == null) {
				gestionEnvioBean = new GestionEnvioBean();
			}
			if (gestionEnvioBean != null && gestionEnvioBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "2";
				// gestionEnvioBean.setVistaEnviosId(1);
			}

			gestionEnvioBean.setFechaDesde(new Date(new Date().getTime() * 2));
			gestionEnvioBean.setFechaHasta(new Date(new Date().getTime() * 2));

			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			PaginatedList<GestionEnvioBean> result = servicioGestionEnvios.getGestionDeEnvios(inicio - 1, (export) ? -1 : pageSize - 1, order, columnSort, gestionEnvioBean, request, true);
			Integer totalSize = result.getTotalList();
			gestionEnvioBean.setFechaDesde(null);
			gestionEnvioBean.setFechaHasta(null);
			listaGestionEnvios = result.getPageList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
			// boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			if (!export) {
				getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, pageSize);
			} else {
				getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String search() throws BaseException {
		PaginatedList<GestionEnvioBean> result = null;
		Integer totalSize = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (validUsuario()) {
			int page = getPage("tableId"); // Pagina a mostrar
			String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
			String columnSort = getColumnSort("tableId"); // Columna usada para ordenar
			int inicio = (page - 1) * pageSize;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			if (export) {
				System.out.println("@@@@ EXPORTANDO");
			}

			if (gestionEnvioBean != null && gestionEnvioBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "2";
				// gestionEnvioBean.setVistaEnviosId(1);
			}
			if (vistaEnviosIdSelected.equals("1")) {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioBean, request, false);
				totalSize = result.getTotalList();
			} else if (vistaEnviosIdSelected.equals("3")){
				result = servicioGestionEnvios.getGestionDeEnviosDestinatarios(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioBean, request);
				totalSize = result.getTotalList();
			}else {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioBean, request, true);
				totalSize = result.getTotalList();
			}

			listaGestionEnvios = result.getPageList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
			// boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			if (!export) {
				getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, pageSize);
			} else {
				getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String loadContenidoMensaje() throws BusinessException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idEnvio == null) {
			throw new BusinessException("EL idEnvio recibido es nulo");
		}
		try {
			String[] arrayEnvioId = idEnvio.split("_");
			idEmail = arrayEnvioId[1];
			detalleEmail = servicioGestionEnvios.loadMensaje(idEmail);
			String cuerpo = detalleEmail.getCuerpo();
			ArrayList<AdjuntoEmailBean> imagenes = (ArrayList<AdjuntoEmailBean>) detalleEmail.getListadoImagenes();
			if (imagenes != null) {
				for (AdjuntoEmailBean adjuntoEmailBean : imagenes) {
					String cid = "cid:" + adjuntoEmailBean.getNombre();
					AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjunto(adjuntoEmailBean.getAdjuntoId().toString(), idEmail);
					if (adjunto != null && adjunto.getContenido() != null) {
						String data = Base64.encode(adjunto.getContenido());
						cuerpo = cuerpo.replaceAll(cid, "data:image/png;base64," + data);
					}

				}
				detalleEmail.setCuerpo(cuerpo);
			}
			return SUCCESS;
		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
		}
	}

	public String loadLote() throws BusinessException {
		Integer totalSize = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		try {
			int page = getPage("tableLotesId");
			int inicio = (page - 1) * pageSizeM;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnvios.loadLote(idLote);
			PaginatedList<MensajeBean> result = servicioGestionEnvios.getMensajesLotes(inicio, (export) ? -1 : pageSizeM, null, null, idLote);
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaGestionEnviosMensajes = result.getPageList();
			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
			// boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			return SUCCESS;

		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
		}
	}

	public String loadHistoricoMsj() throws BusinessException {
		destinatariosMensajes = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idDestinatariosMensajes != null && !idDestinatariosMensajes.equals("null") && Integer.parseInt(idDestinatariosMensajes) > 0) {
			destinatariosMensajes = servicioGestionEnvios.getDestinatariosMensajes(idDestinatariosMensajes);
		}else{
			idDestinatariosMensajes = null;
		}
		try {
			detalleMensaje = servicioGestionEnvios.getMensaje(idMensaje);
			
			if (null == destinatariosMensajes) {
				//se trata de un mensaje antiguo
				//cargamos los datos a mostrar de destinatarios Mensajes
				destinatariosMensajes = new DestinatariosMensajesBean();
				destinatariosMensajes.setCodigoExterno(detalleMensaje.getIdExterno());
			}
			listaHistoricosMensaje = servicioGestionEnvios.getHistoricosMensaje(idMensaje,idDestinatariosMensajes);
			idDestinatariosMensajes = null;

			return SUCCESS;

		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}

	public String loadMensaje() throws BusinessException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		try {
			idEmail = idMensaje;
			detalleEmail = servicioGestionEnvios.loadMensaje(idEmail);
			String cuerpo = detalleEmail.getCuerpo();
			ArrayList<AdjuntoEmailBean> imagenes = (ArrayList<AdjuntoEmailBean>) detalleEmail.getListadoImagenes();
			if (imagenes != null) {
				for (AdjuntoEmailBean adjuntoEmailBean : imagenes) {
					String cid = "cid:" + adjuntoEmailBean.getNombre();
					AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjunto(adjuntoEmailBean.getAdjuntoId().toString(), idEmail);
					if (adjunto != null && adjunto.getContenido() != null) {
						String data = Base64.encode(adjunto.getContenido());
						cuerpo = cuerpo.replaceAll(cid, "data:image/png;base64," + data);
					}

				}
				detalleEmail.setCuerpo(cuerpo);
			}

			// Carga Destinatarios_mensajes
			Integer totalSize = null;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnvios.isMultidestinatario(Integer.parseInt(idLote))) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnvios.getDestinatariosMensajesMultidestinatario(inicio, (export) ? -1 : pageSizeM, null, null, idMensaje);
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			} else {
				result = servicioGestionEnvios.getDestinatariosMensajes(inicio, (export) ? -1 : pageSizeM, null, null, idMensaje);
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			}
			// PaginatedList<MensajeBean> result = servicioGestionEnvios.getMensajesLotes(inicio, (export) ? -1 : pageSizeM, null, null, idLote);

			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);

			return SUCCESS;
		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
		}
	}

	public String loadAdjunto() throws IOException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		// Comprobar primero si se ha cargado alguna vez el fichero
		AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjuntoBean(idAdjunto, idEmail);
		try {
			adjunto = servicioGestionEnvios.loadAdjunto(idAdjunto, idEmail);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());

			this.fileInputStream = byteArrayInputStream;
		} catch (Exception exc) {
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}

		this.adjuntoDescargable = "attachment;filename=\"" + adjunto.getNombre() + "\"";
		return SUCCESS;
	}

	public String accionSeleccionados() {
		List<ViewGestionEnviosJPA> listaEnvios = new ArrayList<ViewGestionEnviosJPA>();
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		boolean algunoEnviado = false;
				
		if (checkDelList != null) {
			for (String id : checkDelList) {
				String[] idArray = id.split("_");
				try {
					if (idArray.length > 1){
						ViewGestionEnviosJPA env = servicioGestionEnvios.getEnvio(id);
						listaEnvios.add(env);
					}else{
						listaEnvios = servicioGestionEnvios.getEnviosLote(id);
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (ViewGestionEnviosJPA envio : listaEnvios) {
									
				if (envio != null) {
					Integer tipoPermiso = PlataformaMensajeriaUtil.getMapPermisosAplicaciones(PlataformaMensajeriaUtil.getUsuarioLogueado().getUsername()).get(envio.getAplicacionId());
					if (tipoPermiso != null && tipoPermiso == 2 || PlataformaMensajeriaUtil.getRolFromSession(request).equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
						// Tiene permisos lectura escritura sobre el envio
						if (operacionMsg != null && operacionMsg.equals("A")) {
							
							if (envio != null && !envio.getEstado().equals("ENVIADO")) {
								if (idArray.length > 1){
									servicioGestionEnvios.anulaEnvio(idArray[0], new Integer(idArray[1]));
								}else{
									servicioGestionEnvios.anulaEnvioLote(idArray[0]);
									break;
								}
								addActionMessageSession("Envios seleccionados anulados correctamente");
							} else {
								addActionErrorSession("No se puede anular un envio ya realizado");
							}

						} else if (operacionMsg != null && operacionMsg.equals("R")) {
							if (idArray.length > 1){
								servicioGestionEnvios.reenviarEnvio(idArray[0], new Integer(idArray[1]));
							}else{
								servicioGestionEnvios.reenviarEnvioLote(idArray[0]);
								break;
							}
							addActionMessageSession("Envios seleccionados reenviados correctamente");
						}
						algunoEnviado = true;
					} else {
						// No tiene permisos de lectura escritura y no es administrador
						addActionErrorSession(this.getText("plataforma.gestionenvios.permisos.error", envio.getAplicacion()));
						sw = false;
					}
				} else {
					sw = false;
				}
			}//del for
			}
		}
		if (!sw && algunoEnviado) {
			addActionMessageSession("Accion realizada sobre los envios en las aplicaciones sobre las que tiene permisos de escritura");
		} else if (!sw && !algunoEnviado) {
			addActionErrorSession("No se ha realizado ninguna accion. Permiso denegado");
		}
		return SUCCESS;
	}

	/**
	 * Devuelve el servicio de gestion de envios
	 * 
	 * @return
	 */
	public ServicioGestionEnvios getServicioGestionEnvios() {
		return servicioGestionEnvios;
	}

	public void setServicioGestionEnvios(ServicioGestionEnvios servicioGestionEnvios) {
		this.servicioGestionEnvios = servicioGestionEnvios;
	}

	/**
	 * Comprueba si hay un usuario logueado y con un rol definido
	 * 
	 * @return boolean
	 */
	private boolean validUsuario() {
		boolean sw = true;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (PlataformaMensajeriaUtil.isEmpty(rolUsuario) || PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)) {
			sw = false;
		}
		return sw;
	}

	/**
	 * 
	 * @return
	 */
	public List<KeyValueObject> getComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<AplicacionBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if (keys != null && keys.size() > 0) {
			for (AplicacionBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<KeyValueObject> getComboServidores() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<ServidorBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(rolUsuario, idUsuario);
		if (keys != null && keys.size() > 0) {
			for (ServidorBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServidorId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<KeyValueObject> getComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<ServicioBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (gestionEnvioBean != null && gestionEnvioBean.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(gestionEnvioBean.getAplicacionId());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
		}
		if (keys != null && keys.size() > 0) {
			for (ServicioBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<KeyValueObject> getComboEstados() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<EstadoBean> keys = null;
		keys = (ArrayList<EstadoBean>) servicioEstado.getEstados();
		if (keys != null && keys.size() > 0) {
			for (EstadoBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getEstadoId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<KeyValueObject> getComboCanales() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<CanalBean> keys = null;
		keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		if (keys != null && keys.size() > 0) {
			for (CanalBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getCanalId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	public List<KeyValueObject> getComboPageSize() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option10 = new KeyValueObject("10", "10");
		KeyValueObject option20 = new KeyValueObject("20", "20");
		KeyValueObject option50 = new KeyValueObject("50", "50");
		KeyValueObject option100 = new KeyValueObject("100", "100");
		result.add(option10);
		result.add(option20);
		result.add(option50);
		result.add(option100);

		return result;

	}

	/**
	 * 
	 * @return
	 */
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = new ArrayList<KeyValueObject>(comboAplicaciones);
	}

	/**
	 * 
	 * @return
	 */
	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}

	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}

	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}

	/**
	 * 
	 * @return
	 */
	public List<GestionEnvioBean> getListaGestionEnvios() {
		return new ArrayList<GestionEnvioBean>(listaGestionEnvios);
	}

	public void setListaGestionEnvios(List<GestionEnvioBean> listaGestionEnvios) {
		this.listaGestionEnvios = new ArrayList<GestionEnvioBean>(listaGestionEnvios);
	}

	/**
	 * 
	 * @return
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = new ArrayList<KeyValueObject>(comboServidores);
	}

	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = new ArrayList<KeyValueObject>(comboServicios);
	}

	public void setComboEstados(List<KeyValueObject> comboEstados) {
		this.comboEstados = new ArrayList<KeyValueObject>(comboEstados);
	}

	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = new ArrayList<KeyValueObject>(comboCanales);
	}

	/**
	 * 
	 * @return
	 */
	public GestionEnvioBean getGestionEnvioBean() {
		return gestionEnvioBean;
	}

	public void setGestionEnvioBean(GestionEnvioBean gestionEnvioBean) {
		this.gestionEnvioBean = gestionEnvioBean;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}

	/**
	 * 
	 * @return
	 */
	public DetalleEnvioBean getDetalleEmail() {
		return detalleEmail;
	}

	public void setDetalleEmail(DetalleEnvioBean detalleEmail) {
		this.detalleEmail = detalleEmail;
	}

	public String[] getCheckDelList() {
		return checkDelList;
	}

	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	public String getCheckAllS() {
		return CheckAllS;
	}

	public void setCheckAllS(String checkAllS) {
		CheckAllS = checkAllS;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getVistaEnviosIdSelected() {
		return vistaEnviosIdSelected;
	}

	public void setVistaEnviosIdSelected(String vistaEnviosIdSelected) {
		this.vistaEnviosIdSelected = vistaEnviosIdSelected;
	}

	public DetalleLoteBean getDetalleLote() {
		return detalleLote;
	}

	public void setDetalleLote(DetalleLoteBean detalleLote) {
		this.detalleLote = detalleLote;
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public List<MensajeBean> getListaGestionEnviosMensajes() {
		return listaGestionEnviosMensajes;
	}

	public void setListaGestionEnviosMensajes(List<MensajeBean> listaGestionEnviosMensajes) {
		this.listaGestionEnviosMensajes = listaGestionEnviosMensajes;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public GestionEnvioBean getDetalleMensaje() {
		return detalleMensaje;
	}

	public void setDetalleMensaje(GestionEnvioBean detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	public List<DestinatariosMensajesBean> getListaGestionEnviosDestinatariosMensaje() {
		return listaGestionEnviosDestinatariosMensaje;
	}

	public void setListaGestionEnviosDestinatariosMensaje(List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje) {
		this.listaGestionEnviosDestinatariosMensaje = listaGestionEnviosDestinatariosMensaje;
	}

	public String getIdDestinatariosMensajes() {
		return idDestinatariosMensajes;
	}

	public void setIdDestinatariosMensajes(String idDestinatariosMensajes) {
		this.idDestinatariosMensajes = idDestinatariosMensajes;
	}

	public DestinatariosMensajesBean getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(DestinatariosMensajesBean destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	public List<HistoricoBean> getListaHistoricosMensaje() {
		return listaHistoricosMensaje;
	}

	public void setListaHistoricosMensaje(List<HistoricoBean> listaHistoricosMensaje) {
		this.listaHistoricosMensaje = listaHistoricosMensaje;
	}

	public List<ArrayList<String>> getAaData() {
		return aaData;
	}

	public void setAaData(List<ArrayList<String>> aaData) {
		this.aaData = aaData;
	}

//	public List<MensajeObject> getAaData() {
//		return aaData;
//	}
//
//	public void setAaData(List<MensajeObject> aaData) {
//		this.aaData = aaData;
//	}

	
}
