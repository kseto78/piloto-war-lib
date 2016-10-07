package es.mpr.plataformamensajeria.web.action.gestionenviohistorico;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis.encoding.Base64;
import org.apache.struts2.interceptor.ServletRequestAware;

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
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gestion de los Envios Historicos.
 * 
 * @version 1.0 
 * @author jgonzvil
 *
 */
public class GestionEnviosHistoricosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private int pageSize=20;
	private int pageSizeM = 15;
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPageSize = new ArrayList<KeyValueObject>();
	
	private String[] checkDelList;
	
	private List<GestionEnvioHistoricoBean> listaGestionEnviosHistoricos= null;
	private List<MensajeHistoricosBean> listaGestionEnviosMensajesHistoricos = null;
	private List<DestinatariosMensajesHistoricosBean> listaGestionEnviosDestinatariosMensajeHistoricos = null;
	private List<HistoricoHistBean> listaHistoricosMensajeHistoricos = null;

	private ServicioAplicacion servicioAplicacion;
    private ServicioServidor servicioServidor;
    private ServicioServicio servicioServicio;
    private ServicioEstado servicioEstado;
    private ServicioCanal servicioCanal;
    private ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos;
    
	private String resultCount;
	private GestionEnvioHistoricoBean gestionEnvioHistoricoBean;
	private DetalleEnvioHistBean detalleEmail;
	private DetalleLoteBean detalleLote;
	private GestionEnvioHistoricoBean detalleMensaje;
	private DestinatariosMensajesHistoricosBean destinatariosMensajes;
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
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(validUsuario()){
		   	int page = getPage("tableId"); //Pagina a mostrar
	    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
	    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
	    	int inicio = (page-1)*pageSize; 
	    	if(gestionEnvioHistoricoBean==null){
	    		gestionEnvioHistoricoBean = new GestionEnvioHistoricoBean();
	    	}
	    	if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId() != null){
	    		vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "2";
				// gestionEnvioBean.setVistaEnviosId(1);
			}
	    	gestionEnvioHistoricoBean.setFechaDesde(new Date(new Date().getTime()*2));
	    	gestionEnvioHistoricoBean.setFechaHasta(new Date(new Date().getTime()*2)); 
	    	
	    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

	    	PaginatedList<GestionEnvioHistoricoBean> result = servicioGestionEnviosHistoricos.
	    			getGestionDeEnviosHistoricos(inicio-1,(export)?-1:pageSize-1,order,columnSort,gestionEnvioHistoricoBean,request, true); 
	    	Integer totalSize = result.getTotalList();
	    	gestionEnvioHistoricoBean.setFechaDesde(null);
	    	gestionEnvioHistoricoBean.setFechaHasta(null);
	    	listaGestionEnviosHistoricos =  result.getPageList();
	    	resultCount = (totalSize!=null)?totalSize.toString():"0";
	    	//Atributos de request
	    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
	    	//boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	if(!export){
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, pageSize);
	    	}else{
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
	    	}
	        return SUCCESS;
		}else{
			return ERROR;
		}    	
    }
	public String search() throws BaseException {
		PaginatedList<GestionEnvioHistoricoBean> result = null;
		Integer totalSize = null;
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(validUsuario()){
		   	int page = getPage("tableId"); //Pagina a mostrar
	    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
	    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
	    	int inicio = (page-1)*pageSize;
	    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	if(export){
	    		System.out.println("@@@@ EXPORTANDO");
	    	}
	    	
	    	if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId()!= null){
	    		vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "2";
				// gestionEnvioBean.setVistaEnviosId(1);
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
	    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
	    	//boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	if(!export){
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, pageSize);
	    	}else{
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
	    	}
	        return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	
	public String loadContenidoMensaje() throws BusinessException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idEnvio == null){
    		throw new BusinessException("EL idEnvio recibido es nulo");
    	}
    	try {
    		String[] arrayEnvioId = idEnvio.split("_");
    		idEmail = arrayEnvioId[1];
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idEmail);
			String cuerpo = detalleEmail.getCuerpo();
			ArrayList<AdjuntoEmailHistoricosBean> imagenes = (ArrayList<AdjuntoEmailHistoricosBean>) detalleEmail.getListadoImagenes();
			if(imagenes!=null){
				for (AdjuntoEmailHistoricosBean adjuntoEmailBean : imagenes) {
					String cid = "cid:"+adjuntoEmailBean.getNombre();
					AdjuntoEmailHistoricosBean adjunto = servicioGestionEnviosHistoricos.loadAdjunto(adjuntoEmailBean.getAdjuntoId().toString(),idEmail);
					if(adjunto!=null&&adjunto.getContenido()!=null){
						String data  =Base64.encode(adjunto.getContenido());
						cuerpo = cuerpo.replaceAll(cid,"data:image/png;base64,"+data);
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
			int page = getPage("tableLotesHistoricoId");
			int inicio = (page - 1) * pageSizeM;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnviosHistoricos.loadLote(idLote);
			PaginatedList<MensajeHistoricosBean> result = servicioGestionEnviosHistoricos.getMensajesLotes(inicio, (export) ? -1 : pageSizeM, null, null, idLote);
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaGestionEnviosMensajesHistoricos = result.getPageList();
			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
			// boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			return SUCCESS;

		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
		}
	}
	
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
			String cuerpo = detalleEmail.getCuerpo();
			ArrayList<AdjuntoEmailHistoricosBean> imagenes = (ArrayList<AdjuntoEmailHistoricosBean>) detalleEmail.getListadoImagenes();
			if(imagenes!=null){
				for (AdjuntoEmailHistoricosBean adjuntoEmailBean : imagenes) {
					String cid = "cid:"+adjuntoEmailBean.getNombre();
					AdjuntoEmailHistoricosBean adjunto = servicioGestionEnviosHistoricos.loadAdjunto(adjuntoEmailBean.getAdjuntoId().toString(),idEmail);
					if(adjunto!=null&&adjunto.getContenido()!=null){
						String data  =Base64.encode(adjunto.getContenido());
						cuerpo = cuerpo.replaceAll(cid,"data:image/png;base64,"+data);
					}
					
				}
				detalleEmail.setCuerpo(cuerpo);
			}
			
			// Carga Destinatarios_mensajes
			Integer totalSize = null;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesHistoricosBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnviosHistoricos.isMultidestinatario(Integer.parseInt(idLote))) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajesMultidestinatario(inicio, (export) ? -1 : pageSizeM, null, null, idMensaje);
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			} else {
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajes(inicio, (export) ? -1 : pageSizeM, null, null, idMensaje);
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			}
			
			// Atributos de request
			getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);

			
			return SUCCESS;
		} catch (BusinessException e) {
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
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
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}

	
	public String loadAdjunto() throws IOException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		//Comprobar primero si se ha cargado alguna vez el fichero
		AdjuntoEmailHistoricosBean adjunto = servicioGestionEnviosHistoricos.loadAdjuntoBean(idAdjunto,idEmail);
		try{
		//String ficheroCargado = PlataformaMensajeriaUtil.existeFichero(adjunto);
		//if(ficheroCargado!=null){
		//	File file = new File(ficheroCargado);
		//	this.fileInputStream = new FileInputStream(file);
		//}else{
			//Si no existe ya el fichero en la ruta especificada se carga y se guarda para la próxima vez (para acelerar la descarga)
			//String rutaCarpetas = PlataformaMensajeriaUtil.creaRutaAdjunto(adjunto,false);
			adjunto = servicioGestionEnviosHistoricos.loadAdjunto(idAdjunto,idEmail);
			//File file = new File(rutaCarpetas);
			//file.mkdirs();
			//String rutaCompleta = PlataformaMensajeriaUtil.creaRutaAdjunto(adjunto, true);
			//file=new File(rutaCompleta);
			//file.createNewFile();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());
			
		
			this.fileInputStream = byteArrayInputStream;
		}catch(Exception exc){
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}
		
		//}
		this.adjuntoDescargable="attachment;filename=\""+adjunto.getNombre()+"\"";
		return SUCCESS;
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

	/**
     * Comprueba si hay un usuario logueado y con un rol definido
     * @return boolean
     */
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
	public List<KeyValueObject> getComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<AplicacionBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if(keys!=null&&keys.size()>0){
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
	public List<KeyValueObject> getComboServidores() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<ServidorBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresYProveedores(rolUsuario,idUsuario);
		if(keys!=null&&keys.size()>0){
	        for (ServidorBean key :keys) {
	            
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
		if(gestionEnvioHistoricoBean!=null&&gestionEnvioHistoricoBean.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(gestionEnvioHistoricoBean.getAplicacionId());
		}else{
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
		if(keys!=null&&keys.size()>0){
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
	public List<KeyValueObject> getComboEstados() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<EstadoBean> keys = null;
		keys = (ArrayList<EstadoBean>)servicioEstado.getEstados();
		if(keys!=null&&keys.size()>0){
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
	public List<KeyValueObject> getComboCanales() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<CanalBean> keys = null;
		keys = (ArrayList<CanalBean>)servicioCanal.getCanales();
		if(keys!=null&&keys.size()>0){
	        for (CanalBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getCanalId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }	
	
	public List<KeyValueObject> getComboPageSize() throws BusinessException{
		  List<KeyValueObject> result = new ArrayList<KeyValueObject>();
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
	public int getPageSizeM() {
		return pageSizeM;
	}
	public void setPageSizeM(int pageSizeM) {
		this.pageSizeM = pageSizeM;
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
	
}
