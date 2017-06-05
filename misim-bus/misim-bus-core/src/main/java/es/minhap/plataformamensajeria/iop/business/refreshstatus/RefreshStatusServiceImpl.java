package es.minhap.plataformamensajeria.iop.business.refreshstatus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionNotificacionEstadoSMS;
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
import es.minhap.plataformamensajeria.iop.manager.TblUrlMensajePremiumManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServidores;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;

/**
 * 
 * @author everis
 *
 */
@Service("refreshStatusServiceImpl")
public class RefreshStatusServiceImpl implements IRefreshStatusService, MuleContextAware {
	
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
	
	@Resource
	ISeguimientoMensajesService seguimientoMensajesImpl;
	
	@Resource(name="TblUrlMensajePremiumManagerImpl")
	private TblUrlMensajePremiumManager urlMensajeManager;
	
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
	
	private MuleContext muleContext;
	
	private static final String HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>";    
	
	private static final String FOOTER = "</soapenv:Body></soapenv:Envelope>";
	
	private boolean repetirRefreshStatus = false;
	private static String separador = "-----------------------------------";
	private static String txtPost = "(postSMS)";
	private String aplicacionAEAT = "";
	
	@Override
	public void refreshStatus(Long mensajeId, Long destinatarioMensajeId, Long loteId, String aplicacionPremium, String usuarioAplicacion) throws Exception{
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String ejecutarTodosServidores = ps.getMessage("refreshStatus.ejecutarTodosProveedores", null);
		aplicacionAEAT= ps.getMessage("aeat.aplicacion", null, "AEAT");
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
			sendOK = consultaTodosProveedores(mensajeId, mapServicios, uim, smsData, numServidores, loteId, aplicacionPremium);

		} else {
			// busca el primer proveedor que esta en la tabla
			sendOK = consultaPrimerProveedor(mensajeId, mapServicios, uim, smsData, loteId, aplicacionPremium);
		}
		
		if (!sendOK) {
			throw new Exception("[RefreshStatusServiceImpl.refreshStatus] -ERROR Actualizando Estado- Mensaje: " + mensajeId);
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
			mns.setIdLote(loteId.toString());
			mns.setUsuarioAplicacion(usuarioAplicacion);
			//Se fuerza el delay de consulta de estado a traves de propiedades en ms.
			long refreshStatusDelay = Long.valueOf(ps.getMessage("constantes.consultaEstado.refreshStatusDelay", null, "0"));
			Thread.sleep(refreshStatusDelay);
			messageSender.sendRefresh(mns, numMaxReintentos);
		}

	}


	private boolean consultaTodosProveedores(Long mensajeId, Map<Integer, Servicio> mapServicios, String uim,
			SMSData smsData, int numServidores, Long loteId, String aplicacionPremium) {
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
				sendOK = consultarEstado(ps, mensajeId, smsData, s, uim, loteId, aplicacionPremium);
			}
			indice++;
		}
		return sendOK;
	}


	private boolean consultaPrimerProveedor(Long mensajeId, Map<Integer, Servicio> mapServicios, String uim,
			SMSData smsData, Long loteId, String aplicacionPremium) {
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
		sendOK = consultarEstado(ps, mensajeId, smsData, s, uim, loteId, aplicacionPremium);
		return sendOK;
	}
	
	
	private boolean consultarEstado(ParametrosProveedor ps, long mensajeId, SMSData smsData, Servicio s, String uim, long loteId, String aplicacionPremium) {
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
			pet.setLoteId(String.valueOf(loteId));
			
		
			DatosEspecificosConsultaEstado de = new DatosEspecificosConsultaEstado();

			de.setSMS_ID(smsId);
			de.setSMS_USUARIO(s.getProveedorUsuarioSMS());
			de.setSMS_PASSWORD(s.getProveedorPassSMS());
			de.setSMS_UIM(smsUim);
			de.setSMS_HEADER(smsHeader);
			
			pet.setDatosEspecificos(de);
			
			String respuesta = commonUtilitiesService.sendMessage(pet, pa.getMessage("constantes.SOAP_ACTION", null), pa.getMessage("constantes.RECEPT_QUEUE", null));
			
			
			res = codificaRespuesta(mensajeId, smsData, respuesta, aplicacionPremium, pa);			
			
		} catch (Exception ex) {
			if (LOG.isDebugEnabled())
				LOG.debug("Error refreshStatusSMS: " + ex.getMessage(), ex);
			res = false;
		}
		return res;

	}


	private boolean codificaRespuesta(long mensajeId, SMSData smsData, String respuesta, String aplicacionPremium, PropertiesServices pa) {
		boolean res = false;
		 // ejemplo respuesta error: 'ERROR|-10|UIM not found' ejemplo respuesta ok:	
		try {
		//	respuesta= "OK|00|PENDIENTE DE SER PROCESADO POR LA PLATAFORMA";
//			respuesta = "ERROR|-10|UIM not found";
			es.minhap.plataformamensajeria.iop.business.beans.enviarmensaje.Respuesta resp = new es.minhap.plataformamensajeria.iop.business.beans.enviarmensaje.Respuesta();
			resp.loadObjectFromXML(respuesta);
			
			StringTokenizer st = new StringTokenizer(resp.getStatus().getStatusText(), "|");
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
					
					//Si es AEAT invocar a AEAT
					if (aplicacionPremium.contains(aplicacionAEAT)){
						invocarAEAT(resp, pa);
					}
				}
				res = true;
			}else{
				cod = "-20";
				EstadosBean estadoMensaje = queryExecutorSubestados.getEstadoByCode(cod);
				estadoFinal = tblEstadosManager.getEstadoById(estadoMensaje.getEstadoId()).getNombre();
				descripcion = estadoMensaje.getDescripcion();
				tblMensajesManager.setEstadoMensaje(mensajeId, estadoFinal, descripcion, false, smsData.destinatarioMensajeId, cod, null, null);
				
				//Si es AEAT invocar a AEAT
				if (aplicacionPremium.contains(aplicacionAEAT)){
					invocarAEAT(resp, pa);
				}
				
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
	

	
	private void invocarAEAT(es.minhap.plataformamensajeria.iop.business.beans.enviarmensaje.Respuesta respuesta,
			PropertiesServices pa) {
		String errorClave = pa.getMessage("clave.ERRORCLAVE.AEAT", null, "[ERROR-CL@VE]:");
		String ok = pa.getMessage("clave.respuestaOK.AEAT", null, "OK");

		try {
			Long mensajeId = Long.parseLong(respuesta.getMessageId());
			String endpointUrl = urlMensajeManager.getUrlByMensaje(mensajeId).getUrl();

			SOAPMessage soapMessage = envioAEAT(mensajeId, endpointUrl, null, null, respuesta.getStatus()
					.getStatusText(), pa);

			String respuestaAEAT = "";
			if (null != soapMessage) {
				respuestaAEAT = pharseMessageToString(soapMessage);
				if (!respuestaAEAT.contains(ok)) {
					LOG.error(errorClave + "Error notificar estado AEAT Mensaje [" + mensajeId + "]");
				}
			}
		} catch (Exception e) {
			LOG.error(errorClave + "Error notificar estado AEAT");
			LOG.error("[RefreshStatusServiceImpl.invocarAEAT] Error notificar estado AEAT",e);
		}
	}

	
	public SOAPMessage envioAEAT(Long mensajeId,String endpointUrl,String sender, String recipient, String statusText, PropertiesServices pa){
		LOG.debug("Empezando el proceso de invocación al emisor...");
		String respuestaIncompleta ="";
		SOAPMessage soapMessage = null;
		try{
			String usuarioAEAT = pa.getMessage("aeat.usuario.sms", null);
			String passwordAEAT = pa.getMessage("aeat.contrasena.sms", null);
			Integer idServicioAEAT = new Integer(pa.getMessage("aeat.servicio.sms.premium", null, null, null));
			respuestaIncompleta=seguimientoMensajesImpl.consultarEstadoAEAT(idServicioAEAT, mensajeId.intValue(), null, usuarioAEAT, passwordAEAT,sender,recipient, statusText);
			
			PeticionNotificacionEstadoSMS petNotAEAT = new PeticionNotificacionEstadoSMS();
			petNotAEAT.loadObjectFromXML(respuestaIncompleta);

			soapMessage = sendMessage(petNotAEAT);
			
		}catch(IllegalArgumentException e){
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error al generar el cliente: Endpoint no encontrado en el WSDL");
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL",e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch (SOAPFaultException e) {		
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado",e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch(WebServiceException e){
			
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Comunicación sin respuesta");
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}else{
					soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}
			}else{
				soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + respuestaIncompleta);
			}
		}catch(Exception e){
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error de sistema Invocar Emisor");
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
	return soapMessage;
	}
	
	private SOAPMessage sendMessage(PeticionNotificacionEstadoSMS envio) {
		SOAPMessage res = null;
		String soapAction = "ACUSE_AEAT";
		String receptQueue = "vm://recepcion-AEAT";
		try {
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(soapAction);
			String xml = mountSoapRequest(envio);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			db = dbf.newDocumentBuilder();
			Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			payload.setSoapMessage(soapDOM);
			payload.setSoapAplication(aplicacionAEAT);
			// llamamos al flujo recepcion-AEAT
			final MuleMessage muleResponse = muleContext.getClient().send(receptQueue,payload, null, 10000);
			
			res = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
			
		} catch (Exception e) {
			LOG.error("ReintentoGISSJob.execute: Se ha producido un error en el reenvio ", e);
		}
		return res;
	}
	
	
	private String pharseMessageToString(SOAPMessage soapMessage) throws SOAPException, IOException {
		String xml;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		xml = new String(out.toByteArray());
		return xml;
	}
	
	private String mountSoapRequest(PeticionNotificacionEstadoSMS envio) throws PlataformaBusinessException {
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append(HEADER);
		String xml = envio.toXML();
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append(FOOTER);
		return soapRequest.toString();
	}
	
	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFaultEnvio(SOAPMessage request, String descripcion) {
		try{
			ResponseStatusType response = new ResponseStatusType();
			
			response.setStatusCode("0403");
			response.setStatusText("KO");
			response.setDetails(descripcion);
	
			
			return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(response, Charset.forName("UTF-8"), ResponseStatusType.class));
		}catch (Exception e){
			LOG.error("Error generando Respuesta",e);
			return null;
		}
		
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


	public MuleContext getMuleContext() {
		return muleContext;
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}
	
}
