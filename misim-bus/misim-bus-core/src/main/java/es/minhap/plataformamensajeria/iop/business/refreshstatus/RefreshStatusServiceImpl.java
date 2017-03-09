package es.minhap.plataformamensajeria.iop.business.refreshstatus;

import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;
import es.minhap.plataformamensajeria.iop.business.beans.consultaestado.DatosEspecificosConsultaEstado;
import es.minhap.plataformamensajeria.iop.business.beans.consultaestado.PeticionConsultaEstado;
import es.minhap.plataformamensajeria.iop.business.common.ICommonUtilitiesService;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorSubEstados;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServidores;

/**
 * 
 * @author everis
 *
 */
@Service("refreshStatusServiceImpl")
public class RefreshStatusServiceImpl implements IRefreshStatusService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RefreshStatusServiceImpl.class);

	@Resource
	private TblDestinatariosMensajesManager tblDestinatariosMensajesManager;
	
	@Resource
	private TblMensajesManager tblMensajesManager;
	
	@Resource
	private TblServidoresManager tblServidoresManager;
	
	@Resource
	private TblEstadosManager tblEstadosManager;
	
	@Resource
	private TblHistoricosManager tblHistoricosManager;
	
	@Autowired
	private QueryExecutorMensajes queryExecutorMensajes;
	
	@Autowired
	private QueryExecutorServidores queryExecutorServidores;
	
	@Autowired
	private QueryExecutorSubEstados queryExecutorSubestados;
	
	@Autowired
	private QueryExecutorServicios queryExecutorServicios;
	
	@Resource
	private ICommonUtilitiesService commonUtilitiesService;
	
	@Resource
	private TblMensajesManager mensajesManager;

	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Autowired(required=true)
	private SIMMessageSender messageSender;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	

	private boolean repetirRefreshStatus = false;
	private static String separador = "-----------------------------------";
	private static String txtPost = "(postSMS)";
	
	@Override
	public void refreshStatus(Long mensajeId, Long destinatarioMensajeId) throws Exception{
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String ejecutarTodosServidores = ps.getMessage("refreshStatus.ejecutarTodosProveedores", null);
		Map<Integer, Servicio> mapServicios;
		boolean sendOK;
		String uim;
		repetirRefreshStatus = false;
		SMSData smsData = commonUtilitiesService.getSMSData(mensajeId, destinatarioMensajeId);

		int numServidores = smsData.Servers.size();

		registerSMSDetailsDebug(smsData);
		mapServicios = commonUtilitiesService.getMapServicios(mensajeId);

		// obtenemos el UIM
		uim = tblDestinatariosMensajesManager.getUim(smsData.destinatarioMensajeId);

		if ("S".equals(ejecutarTodosServidores)) {
			sendOK = consultaTodosProveedores(mensajeId, mapServicios, uim, smsData, numServidores);

		} else {
			// busca el primer proveedor que esta en la tabla
			sendOK = consultaPrimerProveedor(mensajeId, mapServicios, uim, smsData);
		}
		
		if (!sendOK) {
			throw new Exception();
		}
		if (repetirRefreshStatus){
			MensajeJMS mns = new MensajeJMS();
			TblMensajes mensaje = mensajesManager.getMensaje(mensajeId);
			Integer numMaxReintentos = Integer.parseInt(ps.getMessage("constantes.consultaEstado.numMaxReintentos", null)); 
			if (null != smsData.destinatarioMensajeId){
				mns.setIdExterno(destinatariosMensajesManager.getDestinatarioMensaje(smsData.destinatarioMensajeId)
						.getCodigoexterno());
				mns.setDestinatarioMensajeId(smsData.destinatarioMensajeId.toString());
			}
			else{
				mns.setIdExterno(mensaje.getCodigoexterno());
			}

			mns.setCodSia(mensaje.getCodsia());
			mns.setIdMensaje(mensaje.getMensajeid().toString());
			messageSender.sendRefresh(mns, numMaxReintentos);
		}

	}


	private boolean consultaTodosProveedores(Long mensajeId, Map<Integer, Servicio> mapServicios, String uim,
			SMSData smsData, int numServidores) {
		boolean sendOK;
		ParametrosProveedor ps;
		Integer proveedorID;
		int indice = 0;

		sendOK = false;

		if (LOG.isInfoEnabled())
			LOG.info("Numero de proveedores: " + numServidores);

		while ((indice < numServidores) && !sendOK) {
			proveedorID = smsData.Servers.get(indice).getProveedorId();
			registerSMSParametersDebug(smsData, indice);
			if (mapServicios.containsKey(proveedorID)) {
				Servicio s = mapServicios.get(proveedorID);
				ps = smsData.Servers.get(indice);
				sendOK = consultarEstado(ps, mensajeId, smsData, s, uim);
			}
			indice++;
		}
		return sendOK;
	}


	private boolean consultaPrimerProveedor(Long mensajeId, Map<Integer, Servicio> mapServicios, String uim,
			SMSData smsData) {
		boolean sendOK;
		ParametrosProveedor ps;
		Integer proveedorID = 0;
		if (mapServicios.size() > 0) {
			for (ParametrosProveedor pp : smsData.Servers) {
				if (mapServicios.containsKey(pp.getProveedorId())) {
					proveedorID = pp.getProveedorId();
					break;
				}
			}
		} else {// si no tomamos el primero recuperado
			proveedorID = smsData.Servers.get(0).getProveedorId();
		}
		Servicio s = mapServicios.get(proveedorID);
		registerSMSParametersDebug(smsData, 0);
		ps = smsData.Servers.get(0);
		sendOK = consultarEstado(ps, mensajeId, smsData, s, uim);
		return sendOK;
	}
	
	
	private boolean consultarEstado(ParametrosProveedor ps, long mensajeId, SMSData smsData, Servicio s, String uim) {
			boolean res = false;
		try {
			PropertiesServices pa = new PropertiesServices(reloadableResourceBundleMessageSource);
			PeticionConsultaEstado pet = new PeticionConsultaEstado();
			TblServidores p = tblServidoresManager.getServidorById(Long.valueOf(ps.getProveedorId()));
			String usuarioMISIM = pa.getMessage("usuarioMISIM", null);
			String passMISIM = pa.getMessage("passwordMISIM", null);
			
			// llamamos al modulo MISIM y le pasamos el mensaje a enviar
			String producto = "ESTADO_SMS";
			String proveedor = p.getNombre();

			String smsId = Long.toString(mensajeId);
			String smsHeader = s.getHeaderSMS();
			String smsUim = uim;

			pet.setUsuario(usuarioMISIM);
			pet.setPassword(passMISIM);
			pet.setProducto(producto);
			pet.setProveedor(proveedor);
			pet.setMensajeId(String.valueOf(mensajeId));
			
		
			DatosEspecificosConsultaEstado de = new DatosEspecificosConsultaEstado();

			de.setSMS_ID(smsId);
			de.setSMS_USUARIO(s.getProveedorUsuarioSMS());
			de.setSMS_PASSWORD(s.getProveedorPassSMS());
			de.setSMS_UIM(smsUim);
			de.setSMS_HEADER(smsHeader);
			
			pet.setDatosEspecificos(de);
			
			String respuesta = commonUtilitiesService.sendMessage(pet, pa.getMessage("constantes.SOAP_ACTION", null), pa.getMessage("constantes.RECEPT_QUEUE", null));
			
			
			res = codificaRespuesta(mensajeId, smsData, respuesta);			
			
		} catch (Exception ex) {
			if (LOG.isDebugEnabled())
				LOG.debug("Error refreshStatusSMS: " + ex.getMessage(), ex);
			res = false;
		}
		return res;

	}


	private boolean codificaRespuesta(long mensajeId, SMSData smsData, String respuesta) {
		boolean res = false;
		 // ejemplo respuesta error: 'ERROR|-10|UIM not found' ejemplo respuesta ok:	
		try {
//			respuesta= "OK|00|PENDIENTE DE SER PROCESADO POR LA PLATAFORMA";
			StringTokenizer st = new StringTokenizer(respuesta, "|");
			String exito = st.nextToken();
			String cod;
			
			String descripcion;
			String estadoFinal;
			
			Long ultimoEstadoHistorialId = tblHistoricosManager.getUltimoEstadoHistorico(mensajeId, smsData.destinatarioMensajeId);
			if ("OK".equals(exito)) {
				cod = st.nextToken();
				EstadosBean estadoMensaje = queryExecutorSubestados.getEstadoByCode(cod);
				estadoFinal = tblEstadosManager.getEstadoById(estadoMensaje.getEstadoId()).getNombre();
				descripcion = estadoMensaje.getDescripcion();
				if (ultimoEstadoHistorialId != estadoMensaje.getEstadoId()){
					tblMensajesManager.setEstadoMensaje(mensajeId, estadoFinal, descripcion, false, smsData.destinatarioMensajeId, cod, null, null);
				}
				res = true;
			}else{
				cod = "-20";
				EstadosBean estadoMensaje = queryExecutorSubestados.getEstadoByCode(cod);
				estadoFinal = tblEstadosManager.getEstadoById(estadoMensaje.getEstadoId()).getNombre();
				descripcion = estadoMensaje.getDescripcion();
				tblMensajesManager.setEstadoMensaje(mensajeId, estadoFinal, descripcion, false, smsData.destinatarioMensajeId, cod, null, null);
				res = false;
			}
			//Si el subestado se corresponde con el estado de Pendiente de Operador se reencola
			if ("00".equals(cod) || "10".equals(cod) || "20".equals(cod) || "30".equals(cod) ){
				repetirRefreshStatus = true;
			}
		} catch (Exception ex) {
			LOG.error("Error refreshStatusSMS: Recibido estado SMS no reconocido '" + respuesta + "'", ex);				
		}
		return res;
	}
	

	
	
	private void registerSMSDetailsDebug(SMSData smsData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(separador);
			LOG.debug("DATOS DE SMS (refreshStatusSMS)");
			LOG.debug(separador);
			LOG.debug("Telefono: " + smsData.Telefono + " (refreshStatusSMS)");
		}
	}
	
	private void registerSMSParametersDebug(SMSData smsData, int indice) {
		ParametrosProveedor ps = smsData.Servers.get(indice);
		String url = getUrl(ps);
		String id = getId(ps);
		String telefono = getTelefono(ps);
		String texto = getTexto(ps);
		String uim = getUim(ps);
		

		if (LOG.isDebugEnabled()) {
			LOG.debug(separador);
			LOG.debug("DATOS DEL PROVEEDOR (postSMS)");
			LOG.debug(separador);
			LOG.debug("Url: " + url + txtPost);
			if (id != "")
				LOG.debug("Id:" + id + txtPost);
			if (telefono != "")
				LOG.debug("Telefono: " + telefono + txtPost);
			if (texto != "")
				LOG.debug("Texto: " + texto + txtPost);
			if (uim != "")
				LOG.debug("UIM: " + uim + txtPost);
			LOG.debug(separador);
		}

	}


	private String getUim(ParametrosProveedor ps) {
		String uim ="";
		if (ps.getUIM() != null) {
			uim = ps.getUIM();
		}
		return uim;
	}


	private String getTexto(ParametrosProveedor ps) {
		String texto = "";
		if (ps.getTexto() != null) {
			texto = ps.getTexto();
		}
		return texto;
	}


	private String getTelefono(ParametrosProveedor ps) {
		String telefono = "";
		if (ps.getTelefono() != null) {
			telefono = ps.getTelefono();
		}
		return telefono;
	}


	private String getId(ParametrosProveedor ps) {
		String id = "";
		if (ps.getId() != null) {
			id = ps.getId();
		}
		return id;
	}


	private String getUrl(ParametrosProveedor ps) {
		String url = "";
		if (ps.getUrl() != null) {
			url = ps.getUrl();
		}
		return url;
	}


	/**
	 * @return the tblDestinatariosMensajesManager
	 */
	public TblDestinatariosMensajesManager getTblDestinatariosMensajesManager() {
		return tblDestinatariosMensajesManager;
	}


	/**
	 * @param tblDestinatariosMensajesManager the tblDestinatariosMensajesManager to set
	 */
	public void setTblDestinatariosMensajesManager(TblDestinatariosMensajesManager tblDestinatariosMensajesManager) {
		this.tblDestinatariosMensajesManager = tblDestinatariosMensajesManager;
	}


	/**
	 * @return the tblMensajesManager
	 */
	public TblMensajesManager getTblMensajesManager() {
		return tblMensajesManager;
	}


	/**
	 * @param tblMensajesManager the tblMensajesManager to set
	 */
	public void setTblMensajesManager(TblMensajesManager tblMensajesManager) {
		this.tblMensajesManager = tblMensajesManager;
	}


	/**
	 * @return the tblServidoresManager
	 */
	public TblServidoresManager getTblServidoresManager() {
		return tblServidoresManager;
	}


	/**
	 * @param tblServidoresManager the tblServidoresManager to set
	 */
	public void setTblServidoresManager(TblServidoresManager tblServidoresManager) {
		this.tblServidoresManager = tblServidoresManager;
	}


	/**
	 * @return the tblEstadosManager
	 */
	public TblEstadosManager getTblEstadosManager() {
		return tblEstadosManager;
	}


	/**
	 * @param tblEstadosManager the tblEstadosManager to set
	 */
	public void setTblEstadosManager(TblEstadosManager tblEstadosManager) {
		this.tblEstadosManager = tblEstadosManager;
	}


	/**
	 * @return the queryExecutorMensajes
	 */
	public QueryExecutorMensajes getQueryExecutorMensajes() {
		return queryExecutorMensajes;
	}


	/**
	 * @param queryExecutorMensajes the queryExecutorMensajes to set
	 */
	public void setQueryExecutorMensajes(QueryExecutorMensajes queryExecutorMensajes) {
		this.queryExecutorMensajes = queryExecutorMensajes;
	}


	/**
	 * @return the queryExecutorServidores
	 */
	public QueryExecutorServidores getQueryExecutorServidores() {
		return queryExecutorServidores;
	}


	/**
	 * @param queryExecutorServidores the queryExecutorServidores to set
	 */
	public void setQueryExecutorServidores(QueryExecutorServidores queryExecutorServidores) {
		this.queryExecutorServidores = queryExecutorServidores;
	}


	/**
	 * @return the queryExecutorSubestados
	 */
	public QueryExecutorSubEstados getQueryExecutorSubestados() {
		return queryExecutorSubestados;
	}


	/**
	 * @param queryExecutorSubestados the queryExecutorSubestados to set
	 */
	public void setQueryExecutorSubestados(QueryExecutorSubEstados queryExecutorSubestados) {
		this.queryExecutorSubestados = queryExecutorSubestados;
	}


	/**
	 * @return the queryExecutorServicios
	 */
	public QueryExecutorServicios getQueryExecutorServicios() {
		return queryExecutorServicios;
	}


	/**
	 * @param queryExecutorServicios the queryExecutorServicios to set
	 */
	public void setQueryExecutorServicios(QueryExecutorServicios queryExecutorServicios) {
		this.queryExecutorServicios = queryExecutorServicios;
	}


	/**
	 * @return the commonUtilitiesService
	 */
	public ICommonUtilitiesService getCommonUtilitiesService() {
		return commonUtilitiesService;
	}


	/**
	 * @param commonUtilitiesService the commonUtilitiesService to set
	 */
	public void setCommonUtilitiesService(ICommonUtilitiesService commonUtilitiesService) {
		this.commonUtilitiesService = commonUtilitiesService;
	}


	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}


	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}


	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}


	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}


	/**
	 * @return the destinatariosMensajesManager
	 */
	public TblDestinatariosMensajesManager getDestinatariosMensajesManager() {
		return destinatariosMensajesManager;
	}


	/**
	 * @param destinatariosMensajesManager the destinatariosMensajesManager to set
	 */
	public void setDestinatariosMensajesManager(TblDestinatariosMensajesManager destinatariosMensajesManager) {
		this.destinatariosMensajesManager = destinatariosMensajesManager;
	}


	/**
	 * @return the messageSender
	 */
	public SIMMessageSender getMessageSender() {
		return messageSender;
	}


	/**
	 * @param messageSender the messageSender to set
	 */
	public void setMessageSender(SIMMessageSender messageSender) {
		this.messageSender = messageSender;
	}


	/**
	 * @return the tblHistoricosManager
	 */
	public TblHistoricosManager getTblHistoricosManager() {
		return tblHistoricosManager;
	}


	/**
	 * @param tblHistoricosManager the tblHistoricosManager to set
	 */
	public void setTblHistoricosManager(TblHistoricosManager tblHistoricosManager) {
		this.tblHistoricosManager = tblHistoricosManager;
	}
	
}
