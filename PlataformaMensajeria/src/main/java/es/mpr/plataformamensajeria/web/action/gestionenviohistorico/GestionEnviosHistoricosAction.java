package es.mpr.plataformamensajeria.web.action.gestionenviohistorico;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gestion de los Envios Historicos.
 * 
 * @version 1.0 
 * @author jgonzvil
 *
 */
@Controller("gestionEnvioHistoricoAction")
@Scope("prototype")
public class GestionEnviosHistoricosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(GestionEnviosHistoricosAction.class);
	
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

	@Resource(name = "servicioGestionEnviosHistoricosImpl")
	private ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos;
    
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	private GestionEnvioHistoricoBean gestionEnvioHistoricoBean;
	private DetalleEnvioHistBean detalleEmail;
	private DetalleLoteBean detalleLote;
	private GestionEnvioHistoricoBean detalleMensaje;
	private DestinatariosMensajesHistoricosBean destinatariosMensajes;
	
	
	private List<GestionEnvioHistoricoBean> listaGestionEnviosHistoricos= null;
	private List<MensajeHistoricosBean> listaGestionEnviosMensajesHistoricos = null;
	private List<DestinatariosMensajesHistoricosBean> listaGestionEnviosDestinatariosMensajeHistoricos = null;
	private List<HistoricoHistBean> listaHistoricosMensajeHistoricos = null;
	
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPageSize = new ArrayList<KeyValueObject>();
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
			if (gestionEnvioHistoricoBean == null) {
				gestionEnvioHistoricoBean = new GestionEnvioHistoricoBean();
			}
			if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "1";
			}
//			gestionEnvioHistoricoBean.setFechaDesde(new Date(new Date().getTime() * 2));
//			gestionEnvioHistoricoBean.setFechaHasta(new Date(new Date().getTime() * 2));

			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			PaginatedList<GestionEnvioHistoricoBean> result = servicioGestionEnviosHistoricos
					.getGestionDeEnviosHistoricos(inicio - 1, (export) ? -1 : pageSize - 1, order, columnSort,
							gestionEnvioHistoricoBean, request, false);
			Integer totalSize = result.getTotalList();
			gestionEnvioHistoricoBean.setFechaDesde(null);
			gestionEnvioHistoricoBean.setFechaHasta(null);
			listaGestionEnviosHistoricos = result.getPageList();
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
		PaginatedList<GestionEnvioHistoricoBean> result = null;
		Integer totalSize = null;
		if(getRequest().getSession().getAttribute("infoUser")==null){
			return "noUser"; 
		}
		if(validUsuario()){
		   	int page = getPage("tableId"); //Pagina a mostrar
	    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
	    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
	    	int inicio = (page-1)*pageSize;
	    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	
	    	if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId()!= null){
	    		vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "1";
			}
			if (vistaEnviosIdSelected.equals("1")) {
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosHistoricos(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request, false);
				totalSize = result.getTotalList();
			} else if (vistaEnviosIdSelected.equals("3")){
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosDestinatariosHistoricos(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request);
				totalSize = result.getTotalList();
			}else {
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosHistoricos(inicio, (export) ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request, true);
				totalSize = result.getTotalList();
			}
	    	
	    	
	    	listaGestionEnviosHistoricos =  result.getPageList();
	    	resultCount = (totalSize!=null)?totalSize.toString():"0";
	    	//Atributos de request
	    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
	    	if(!export){
	    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), pageSize);
	    	}else{
	    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
	    	}
	        return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	////MIGRADO
	public String loadContenidoMensaje() throws BusinessException{
		if(getRequest().getSession().getAttribute("infoUser")==null){
			return "noUser"; 
		}
    	if(idEnvio == null){
    		throw new BusinessException("EL idEnvio recibido es nulo");
    	}
    	try {
    		String[] arrayEnvioId = idEnvio.split("_");
    		idEmail = arrayEnvioId[1];
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idEmail);
			
			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadContenidoMensaje:" + e);
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
			int page = getPage("tableLotesHistoricoId");
			int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20"));
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnviosHistoricos.loadLote(idLote);
			PaginatedList<MensajeHistoricosBean> result = servicioGestionEnviosHistoricos.getMensajesLotes(inicio, 
					(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleLote.getIdLoteEnvio().longValue());
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaGestionEnviosMensajesHistoricos = result.getPageList();
			
			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadLote:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
		}
	}
	
	////MIGRADO
	public String loadMensaje() throws BusinessException{
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
    	try {
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idMensaje);
						
			// Carga Destinatarios_mensajes
			Integer totalSize = null;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesHistoricosBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnviosHistoricos.isMultidestinatario(detalleEmail.getMensajeId())) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajesMultidestinatario(inicio, 
						(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			} else {
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajes(inicio, 
						(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			}
						
			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadMensaje:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
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
		if (idDestinatariosMensajes != null && !idDestinatariosMensajes.equals("null") && Integer.parseInt(idDestinatariosMensajes) > 0) {
			destinatariosMensajes = servicioGestionEnviosHistoricos.getDestinatariosMensajesHistoricos(idDestinatariosMensajes);
		}else{
			idDestinatariosMensajes = null;
		}
		try {
			detalleMensaje = servicioGestionEnviosHistoricos.getMensajeHistorico(idMensaje);
			
			if (null == destinatariosMensajes) {
				//se trata de un mensaje antiguo
				//cargamos los datos a mostrar de destinatarios Mensajes
				destinatariosMensajes = new DestinatariosMensajesHistoricosBean();
				destinatariosMensajes.setCodigoExterno(detalleMensaje.getIdExterno());
			}
			listaHistoricosMensajeHistoricos = servicioGestionEnviosHistoricos.getHistoricosHistMensaje(idMensaje,idDestinatariosMensajes);
			

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadHistoricoMsj:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}

	////MIGRADO
	public String loadAdjunto() throws IOException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		try{
			AdjuntoEmailHistoricosBean adjunto = servicioGestionEnviosHistoricos.loadAdjunto(Long.parseLong(idAdjunto), Long.parseLong(idEmail));
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());
			
		
			this.fileInputStream = byteArrayInputStream;
			this.adjuntoDescargable = "attachment;filename=\"" + adjunto.getNombre() + "\"";
		}catch(Exception exc){
			logger.error("GestionEnviosHistoricosAction - loadAdjunto:" + exc);
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}
		return SUCCESS;
	}
	
	/**
     * Comprueba si hay un usuario logueado y con un rol definido
     * @return boolean
     */
	////MIGRADO
	private boolean validUsuario() {
	 	boolean sw = true;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(PlataformaMensajeriaUtil.isEmpty(rolUsuario)||PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)){
			sw=false;
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
		keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if(keys!=null&& !keys.isEmpty()){
	        for (AplicacionBean key :keys) {
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
		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresYProveedores(rolUsuario,idUsuario);
		if(keys!=null&& !keys.isEmpty()){
	        for (ServidorBean key :keys) {
	            
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
		if(gestionEnvioHistoricoBean!=null&&gestionEnvioHistoricoBean.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(gestionEnvioHistoricoBean.getAplicacionId().intValue());
		}else{
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
		if(keys!=null&& !keys.isEmpty()){
	        for (ServicioBean key :keys) {
	            
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
		keys = (ArrayList<EstadoBean>)servicioEstado.getEstados();
		if(keys!=null&&!keys.isEmpty()){
	        for (EstadoBean key :keys) {
	            
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
		keys = (ArrayList<CanalBean>)servicioCanal.getCanales();
		if(keys!=null&&!keys.isEmpty()){
	        for (CanalBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getCanalId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }	
	
	////MIGRADO
	public List<KeyValueObject> getComboPageSize() throws BusinessException{
		  List<KeyValueObject> result = new ArrayList<>();
	      KeyValueObject option10 = new KeyValueObject("10","10");
	      KeyValueObject option20 = new KeyValueObject("20","20");
	      KeyValueObject option50 = new KeyValueObject("50","50");
	      KeyValueObject option100 = new KeyValueObject("100","100");
	      result.add(option10);
	      result.add(option20);
	      result.add(option50);
	      result.add(option100);
	      
	      return result;
	        
		
	}

	// /MIGRADO
	@Override
	public void prepare() throws Exception {
		pageSize = (Integer) getRequest().getAttribute(
				properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null));
		if (null == pageSize) {
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
	public List<GestionEnvioHistoricoBean> getListaGestionEnviosHistoricos() {
		return new ArrayList<GestionEnvioHistoricoBean>(listaGestionEnviosHistoricos);
	}
	public void setListaGestionEnviosHistoricos(List<GestionEnvioHistoricoBean> listaGestionEnviosHistoricos) {
		this.listaGestionEnviosHistoricos = new ArrayList<GestionEnvioHistoricoBean>(listaGestionEnviosHistoricos);
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
    public GestionEnvioHistoricoBean getGestionEnvioHistoricoBean() {
		return gestionEnvioHistoricoBean;
	}
	public void setGestionEnvioHistoricoBean(GestionEnvioHistoricoBean gestionEnvioHistoricoBean) {
		this.gestionEnvioHistoricoBean = gestionEnvioHistoricoBean;
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
	public DetalleEnvioHistBean getDetalleEmail() {
		return detalleEmail;
	}
	
	public void setDetalleEmail(DetalleEnvioHistBean detalleEmail) {
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
	
	public List<MensajeHistoricosBean> getListaGestionEnviosMensajesHistoricos() {
		return listaGestionEnviosMensajesHistoricos;
	}
	public void setListaGestionEnviosMensajesHistoricos(List<MensajeHistoricosBean> listaGestionEnviosMensajesHistoricos) {
		this.listaGestionEnviosMensajesHistoricos = listaGestionEnviosMensajesHistoricos;
	}
	public List<DestinatariosMensajesHistoricosBean> getListaGestionEnviosDestinatariosMensajeHistoricos() {
		return listaGestionEnviosDestinatariosMensajeHistoricos;
	}
	public void setListaGestionEnviosDestinatariosMensajeHistoricos(List<DestinatariosMensajesHistoricosBean> listaGestionEnviosDestinatariosMensajeHistoricos) {
		this.listaGestionEnviosDestinatariosMensajeHistoricos = listaGestionEnviosDestinatariosMensajeHistoricos;
	}
	public List<HistoricoHistBean> getListaHistoricosMensajeHistoricos() {
		return listaHistoricosMensajeHistoricos;
	}
	public void setListaHistoricosMensajeHistoricos(List<HistoricoHistBean> listaHistoricosMensajeHistoricos) {
		this.listaHistoricosMensajeHistoricos = listaHistoricosMensajeHistoricos;
	}
	public DetalleLoteBean getDetalleLote() {
		return detalleLote;
	}
	public void setDetalleLote(DetalleLoteBean detalleLote) {
		this.detalleLote = detalleLote;
	}
	public GestionEnvioHistoricoBean getDetalleMensaje() {
		return detalleMensaje;
	}
	public void setDetalleMensaje(GestionEnvioHistoricoBean detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}
	public DestinatariosMensajesHistoricosBean getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	public void setDestinatariosMensajes(DestinatariosMensajesHistoricosBean destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	public String getIdLote() {
		return idLote;
	}
	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
	public String getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
	public String getIdDestinatariosMensajes() {
		return idDestinatariosMensajes;
	}
	public void setIdDestinatariosMensajes(String idDestinatariosMensajes) {
		this.idDestinatariosMensajes = idDestinatariosMensajes;
	}
	public String getVistaEnviosIdSelected() {
		return vistaEnviosIdSelected;
	}
	public void setVistaEnviosIdSelected(String vistaEnviosIdSelected) {
		this.vistaEnviosIdSelected = vistaEnviosIdSelected;
	}
	/**
	 * @return the properties
	 */
	public PlataformaMensajeriaProperties getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(PlataformaMensajeriaProperties properties) {
		this.properties = properties;
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
	  /**
     * Devuelve el servicio de gestion de envios historicos
     * @return 
     */
	public ServicioGestionEnviosHistoricos getServicioGestionEnviosHistoricos() {
		return servicioGestionEnviosHistoricos;
	}
	public void setServicioGestionEnviosHistoricos(ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos) {
		this.servicioGestionEnviosHistoricos = servicioGestionEnviosHistoricos;
	}
}
