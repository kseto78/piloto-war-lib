package es.mpr.plataformamensajeria.web.action.gestionenvio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.minhap.common.spring.ApplicationContextProvider;
import es.minhap.sim.model.TblGestionEnvios;
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
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 * 
 * @version 1.0
 * @author i-nercya
 * 
 */
@Controller("gestionEnvioAction")
@Scope("prototype")
public class GestionEnviosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(GestionEnviosAction.class);

	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;

	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;

	@Resource(name = "servicioEstadoImpl")
	private ServicioEstado servicioEstado;

	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;

	@Resource(name = "servicioGestionEnviosImpl")
	private ServicioGestionEnvios servicioGestionEnvios;
	
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	

	
	
	private GestionEnvioBean gestionEnvioBean;
	private DetalleEnvioBean detalleEmail;
	private DetalleLoteBean detalleLote;
	private GestionEnvioBean detalleMensaje;
	private DestinatariosMensajesBean destinatariosMensajes;

	private List<GestionEnvioBean> listaGestionEnvios = null;
	private List<MensajeBean> listaGestionEnviosMensajes = null;
	private List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje = null;
	private List<HistoricoBean> listaHistoricosMensaje = null;

	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPageSize = new ArrayList<KeyValueObject>();
	private List<ArrayList<String>> aaData;
	private String[] checkDelList;

	private String resultCount;
	private String idEnvio;
	private String idAdjunto;
	private String idEmail;
	private String idLote;
	private String idMensaje;
	private String idDestinatariosMensajes;
	private String operacionMsg;
	private String CheckAllS;
	private String vistaEnviosIdSelected;
	private String adjuntoDescargable;
	private InputStream fileInputStream;
	private Integer pageSize = 20;

	////MIGRADO
	public String newSearch() throws BusinessException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (validUsuario()) {
			int page = getPage("tableId"); // Pagina a mostrar
			String order = getOrder("tableId"); // Ordenar de modo ascendente o
												// descendente
			String columnSort = getColumnSort("tableId"); // Columna usada para
															// ordenar
			int inicio = (page - 1) * pageSize;
			if (gestionEnvioBean == null) {
				gestionEnvioBean = new GestionEnvioBean();
			}
			if (gestionEnvioBean != null && gestionEnvioBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "1";
			}

//			gestionEnvioBean.setFechaDesde(new Date(new Date().getTime() * 2));
//			gestionEnvioBean.setFechaHasta(new Date(new Date().getTime() * 2));

			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			PaginatedList<GestionEnvioBean> result = servicioGestionEnvios.getGestionDeEnvios(inicio, (export) ? -1
					: pageSize, order, columnSort,
					gestionEnvioBean, request, false);
			Integer totalSize = result.getTotalList();
			gestionEnvioBean.setFechaDesde(null);
			gestionEnvioBean.setFechaHasta(null);
			listaGestionEnvios = result.getPageList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			// Atributos de request
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
			if (!export) {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
						pageSize);
			} else {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
						totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

////MIGRADO
	public String search() throws BaseException {
		PaginatedList<GestionEnvioBean> result = null;
		Integer totalSize = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (validUsuario()) {
			int page = getPage("tableId"); // Pagina a mostrar
			String order = getOrder("tableId"); // Ordenar de modo ascendente o
												// descendente
			String columnSort = getColumnSort("tableId"); // Columna usada para
															// ordenar
			int inicio = (page - 1) * pageSize;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			
			if (gestionEnvioBean != null && gestionEnvioBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "1";
			}
			if (vistaEnviosIdSelected.equals("1")) {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request, false);
				totalSize = result.getTotalList();
			} else if (vistaEnviosIdSelected.equals("3")) {
				result = servicioGestionEnvios.getGestionDeEnviosDestinatarios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request);
				totalSize = result.getTotalList();
			} else {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request, true);
				totalSize = result.getTotalList();
			}

			listaGestionEnvios = result.getPageList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			// Atributos de request
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

			if (!export) {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
						pageSize);
			} else {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
						totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// //MIGRADO
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

			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadContenidoMensaje:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
		}
	}

	////MIGRADO
	public String loadLote() throws BusinessException {
		Integer totalSize = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		try {
			int page = getPage("tableLotesId");
			int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20"));
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnvios.loadLote(idLote);
			PaginatedList<MensajeBean> result = servicioGestionEnvios.getMensajesLotes(inicio,
					(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleLote.getIdLoteEnvio().longValue());
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaGestionEnviosMensajes = result.getPageList();

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadLote:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
		}
	}

	////MIGRADO
	public String loadHistoricoMsj() throws BusinessException {
		destinatariosMensajes = null;
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idDestinatariosMensajes != null && !idDestinatariosMensajes.equals("null")
				&& Integer.parseInt(idDestinatariosMensajes) > 0) {
			destinatariosMensajes = servicioGestionEnvios.getDestinatariosMensajes(idDestinatariosMensajes);
		} else {
			idDestinatariosMensajes = null;
		}
		try {
			detalleMensaje = servicioGestionEnvios.getMensaje(idMensaje);

			if (null == destinatariosMensajes) {
				// se trata de un mensaje antiguo
				// cargamos los datos a mostrar de destinatarios Mensajes
				destinatariosMensajes = new DestinatariosMensajesBean();
				destinatariosMensajes.setCodigoExterno(detalleMensaje.getIdExterno());
			}
			listaHistoricosMensaje = servicioGestionEnvios.getHistoricosMensaje(idMensaje, idDestinatariosMensajes);
			idDestinatariosMensajes = null;

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadHistoricoMsj:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}

	////MIGRADO
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
			
			// Carga Destinatarios_mensajes
			Integer totalSize = null;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnvios.isMultidestinatario(detalleEmail.getMensajeId())) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnvios.getDestinatariosMensajesMultidestinatario(inicio, (export) ? -1
						: Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			} else {
				result = servicioGestionEnvios.getDestinatariosMensajes(inicio,
						(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			}
			

			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadMensaje:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
		}
	}

	/////MIGRADO
	public String loadAdjunto() throws IOException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		// Comprobar primero si se ha cargado alguna vez el fichero
		//AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjuntoBean(idAdjunto, idEmail);
		try {
			AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjunto(Long.parseLong(idAdjunto), Long.parseLong(idEmail));

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());

			this.fileInputStream = byteArrayInputStream;
			this.adjuntoDescargable = "attachment;filename=\"" + adjunto.getNombre() + "\"";
		} catch (Exception exc) {
			logger.error("GestionEnviosAction - loadAdjunto:" + exc);
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}

		return SUCCESS;
	}

	////MIGRADO
	public String accionSeleccionados() {
		try{
		List<TblGestionEnvios> listaEnvios = new ArrayList<TblGestionEnvios>();
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		boolean algunoEnviado = false;

//		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
//		OperacionMensajesServicePortType operacionMensajesService = (OperacionMensajesServicePortType) applicationContext.getBean("operacionMensajesService");
		ApplicationContext  applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
		
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		ApplicationContextProvider context = ApplicationContextProvider.getInstance();
		
		if (checkDelList != null) {
			for (String id : checkDelList) {
				String[] idArray = id.split("_");
				try {
					if (idArray.length > 1) {
						TblGestionEnvios env = servicioGestionEnvios.getEnvio(idArray[1]);
						listaEnvios.add(env);
					} else {
						listaEnvios = servicioGestionEnvios.getEnviosLote(id);
					}
				} catch (BusinessException e) {
					logger.error("GestionEnviosAction - accionSeleccionados:" + e);
				}
				for (TblGestionEnvios envio : listaEnvios) {

					if (envio != null) {
						Integer tipoPermiso = PlataformaMensajeriaUtil.getMapPermisosAplicaciones(
								PlataformaMensajeriaUtil.getUsuarioLogueado().getUsername(), servicioUsuarioAplicacion).get(
								envio.getAplicacionid().toString());
						if (tipoPermiso != null
								&& tipoPermiso == 2
								|| PlataformaMensajeriaUtil.getRolFromSession(request).equals(
										PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
							// Tiene permisos lectura escritura sobre el envio
							if (operacionMsg != null && operacionMsg.equals("A")) {
								boolean anulacionCorrecta = false;
								if (envio != null && !envio.getEstado().equals("ENVIADO")) {
									if (idArray.length > 1) {
										anulacionCorrecta = servicioGestionEnvios.anulaEnvio(new Integer(idArray[1]),context);
									} else {
										anulacionCorrecta = servicioGestionEnvios.anulaEnvioLote(new Integer(idArray[0]),context);
										if (anulacionCorrecta){
											addActionMessageSession("Envios seleccionados anulados correctamente");
										}else{
											addActionErrorSession("Ha ocurrido un error anulando los envios, pongase en contacto con el administrador");
										}
										break;
									}
									if (anulacionCorrecta){
										addActionMessageSession("Envios seleccionados anulados correctamente");
									}else{
										addActionErrorSession("Ha ocurrido un error anulando los envios, pongase en contacto con el administrador");
									}
								} else {
									addActionErrorSession("No se puede anular un envio ya realizado");
								}

							} else if (operacionMsg != null && operacionMsg.equals("R")) {
								boolean reenvioCorrecto = false;
								if (idArray.length > 1) {
									reenvioCorrecto = servicioGestionEnvios.reenviarEnvio(new Integer(idArray[1]),context);
								} else {
									reenvioCorrecto = servicioGestionEnvios.reenviarEnvioLote(new Integer(idArray[0]),context);
									if(reenvioCorrecto){
										addActionMessageSession("Envios seleccionados reenviados correctamente");
									}else{
										addActionErrorSession("Ha ocurrido un error reenviando los envios, pongase en contacto con el administrador");
									}
									break;
								}
								if(reenvioCorrecto){
									addActionMessageSession("Envios seleccionados reenviados correctamente");
								}else{
									addActionErrorSession("Ha ocurrido un error reenviando los envios, pongase en contacto con el administrador");
								}
							}
							algunoEnviado = true;
						} else {
							// No tiene permisos de lectura escritura y no es
							// administrador
							addActionErrorSession(this.getText("plataforma.gestionenvios.permisos.error",
									envio.getAplicacion()));
							sw = false;
						}
					} else {
						sw = false;
					}
				}// del for
			}
		}
		if (!sw && algunoEnviado) {
			addActionMessageSession("Accion realizada sobre los envios en las aplicaciones sobre las que tiene permisos de escritura");
		} else if (!sw && !algunoEnviado) {
			addActionErrorSession("No se ha realizado ninguna accion. Permiso denegado");
		}
		}catch(Exception e){
			logger.error("GestionEnviosAction - accionSeleccionados:" + e);
		}
		return SUCCESS;
	}

	/**
	 * Comprueba si hay un usuario logueado y con un rol definido
	 * 
	 * @return boolean
	 */
	////MIGRADO
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
	////MIGRADO
	public List<KeyValueObject> getComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if (keys != null && !keys.isEmpty()) {
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
	////MIGRADO
	public List<KeyValueObject> getComboServidores() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(rolUsuario, idUsuario);
		if (keys != null && !keys.isEmpty()) {
			for (ServidorBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServidorid().toString());
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
	////MIGRADO
	public List<KeyValueObject> getComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (gestionEnvioBean != null && gestionEnvioBean.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(gestionEnvioBean
					.getAplicacionId().intValue());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
		}
		if (keys != null && !keys.isEmpty()) {
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
	////MIGRADO
	public List<KeyValueObject> getComboEstados() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<EstadoBean> keys;
		keys = (ArrayList<EstadoBean>) servicioEstado.getEstados();
		if (keys != null && !keys.isEmpty()) {
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
	////MIGRADO
	public List<KeyValueObject> getComboCanales() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<CanalBean> keys;
		keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		if (keys != null && !keys.isEmpty()) {
			for (CanalBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getCanalId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	////MIGRADO
	public List<KeyValueObject> getComboPageSize() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
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

	///MIGRADO
	@Override
	public void prepare() throws Exception {
		pageSize = (Integer) getRequest().getAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null));
		if (null == pageSize){
			pageSize = Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		}
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

	public void setListaGestionEnviosDestinatariosMensaje(
			List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje) {
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

	public InputStream getFileInputStream() {
		return fileInputStream;
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
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
