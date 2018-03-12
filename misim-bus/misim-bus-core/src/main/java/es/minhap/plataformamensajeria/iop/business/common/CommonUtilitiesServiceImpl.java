package es.minhap.plataformamensajeria.iop.business.common;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionEnvioXML;
import es.minhap.plataformamensajeria.iop.beans.entity.DetallesMensajesBean;
import es.minhap.plataformamensajeria.iop.business.beans.consultaestado.PeticionConsultaEstado;
import es.minhap.plataformamensajeria.iop.business.beans.enviarmensaje.Respuesta;
import es.minhap.plataformamensajeria.iop.business.beans.push.PeticionPush;
import es.minhap.plataformamensajeria.iop.business.beans.push.PeticionWebPush;
import es.minhap.plataformamensajeria.iop.business.beans.recepcionsms.PeticionRecepcionSMS;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblErrorMensajeLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.util.UtilCreateFile;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.minhap.sim.model.TblErrorMensajeLog;
import es.minhap.sim.model.TblMensajes;

/**
 * 
 * @author everis
 *
 */
@Service("commonUtilitiesService")
public class CommonUtilitiesServiceImpl implements ICommonUtilitiesService, MuleContextAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonUtilitiesServiceImpl.class);

	@Resource
	private TblDestinatariosMensajesManager tblDestinatariosMensajesManager;
	
	@Resource
	private TblServidoresManager tblServidoresManager;

	@Resource
	private TblMensajesManager mensajesManager;
	
	@Autowired
	private QueryExecutorLotesEnvios queryExecutorLotesEnvios;
	
	@Autowired
	private QueryExecutorMensajes queryExecutorMensajes;
	
	@Autowired
	private QueryExecutorServidores queryExecutorServidores;

	@Autowired
	private QueryExecutorServicios queryExecutorServicios;

	@Resource
	private TblErrorMensajeLogManager errorMensajeLogManager;
	
	@Resource(name="UtilCreateFile")
	private UtilCreateFile utilFile;
	
	private MuleContext muleContext;
	private static final CharSequence CONSTANTEUIM = "#UIM#";

	
	@Override
	public SMSData getSMSData(Long mensajeId,Long destinatarioMensajeId ) {
		SMSData data = new SMSData();
		try {
			SMSData smsDataComun = new SMSData();
			smsDataComun.Servers= new ArrayList<>();
			smsDataComun.ServiceData = queryExecutorMensajes.getDataFromServices(mensajeId);
			if (null != destinatarioMensajeId) {
				if (smsDataComun.ServiceData.getMultiOrganismo() == 1) {
					smsDataComun.Servers = (ArrayList<ParametrosProveedor>) queryExecutorServidores.getProveedoresMultiorganismo(mensajeId);
				}
				smsDataComun.Servers.addAll((ArrayList<ParametrosProveedor>) queryExecutorServidores.getProveedores(mensajeId));
				data = queryExecutorMensajes.getDetailsSMSMultidestinatario(mensajeId, destinatarioMensajeId, smsDataComun);
			} else {
				DetallesMensajesBean detallesMensajesBean = queryExecutorMensajes.getDetallesMensaje(mensajeId);
				
				TblMensajes mensaje = mensajesManager.getMensaje(mensajeId);
				data.Telefono = mensaje.getTelefono();
				
				//comprueba si el cuerpo está en fichero
				if (null == mensaje.getCuerpofile()){
					data.Body = mensaje.getCuerpo();
				}else{
					//recuperamos el cuerpo. 
					data.Body = utilFile.getCuerpoMensajeFromFile(mensaje.getCuerpofile());
				}
				
				
				data.destinatarioMensajeId = detallesMensajesBean.getDestinatariosMensajes();
				if (smsDataComun.ServiceData.getMultiOrganismo() == 1) {
					data.Servers = (ArrayList<ParametrosProveedor>) queryExecutorServidores.getProveedoresMultiorganismo(mensajeId);
				}
				data.Servers.addAll((ArrayList<ParametrosProveedor>) queryExecutorServidores.getProveedores(mensajeId));
			}
		} catch (Exception e) {
			TblErrorMensajeLog tblErrorMensajeLog = new TblErrorMensajeLog();
			tblErrorMensajeLog.setCodigoerror(new Long("0"));
			tblErrorMensajeLog.setDescripcionerror("Error: getSMSData : " + e.getMessage());
			tblErrorMensajeLog.setFecha(new Date());
			tblErrorMensajeLog.setOperacion("postSMS");
			errorMensajeLogManager.insertarLogError(tblErrorMensajeLog);
			LOG.error("getNotificacionPushData : " + e.getMessage(), e);
		} 
		return data;
	}
	
	@Override
	public Map<Integer, Servicio> getMapServicios(Long idMensaje) {
		Map<Integer,Servicio> res = new HashMap<>();
		List<String> servMultiorganismo = queryExecutorServicios.findServicioByMessageId(idMensaje);
		for (String idServMultiorganismo: servMultiorganismo) {
			String[] datos = idServMultiorganismo.split("~");
			Long idServicio = Long.parseLong(datos[0]);
			Long multiorganismo = Long.parseLong(datos[1]);
			if (1 == multiorganismo.longValue()) {
				res = queryExecutorServicios.findServicioMultiorganismo(idServicio, idMensaje);
				if (res.size() <= 0) {
					res = queryExecutorServicios.findServicio(idServicio);
				}
			} else {
				res = queryExecutorServicios.findServicio(idServicio);
			}
		}
		return res;
	}

	@Override
	public String sendMessage(Object data, String soapAction, String receptQueue, Long mensajeId) throws Exception {
		SoapPayload<?> payload = new PeticionPayload();
		payload.setSoapAction(soapAction);
		String xml = "";
		Boolean isConsulta = false;
		if (data instanceof PeticionEnvioXML) {
			xml = ((PeticionEnvioXML) data).toXMLSMS();
		} else if (data instanceof PeticionPush) {
			xml = ((PeticionPush) data).toXML();
		} else if (data instanceof PeticionConsultaEstado){
			xml = ((PeticionConsultaEstado) data).toXML();
			isConsulta = true;
		}else if (data instanceof PeticionRecepcionSMS){
			xml = ((PeticionRecepcionSMS) data).toXML();
		}else if (data instanceof PeticionWebPush){
			xml = ((PeticionWebPush) data).toXML();
		}
		
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>");
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append("</soapenv:Body></soapenv:Envelope>");
		xml = soapRequest.toString();
		xml = xml.replaceAll("<Peticion>", "<Peticion xmlns=\"http://misim.redsara.es/misim-bus-webapp/peticion\">");
		
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
		payload.setSoapMessage(soapDOM);

		final MuleMessage muleResponse = muleContext.getClient().send(receptQueue, payload, null, 10000);
		Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();

		NodeList nodoRespuesta = respuestaSOAP.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/respuesta", "Respuesta");
		String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));
				
		JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlRespuesta);
		Respuesta respuesta = (Respuesta) unmarshaller.unmarshal(reader);
		if(null != respuesta && respuesta.getStatus().getStatusText().contains(CONSTANTEUIM)){
			respuesta.getStatus().setStatusText(respuesta.getStatus().getStatusText().replace(CONSTANTEUIM, String.valueOf(mensajeId)));
		}
		return isConsulta? xmlRespuesta : respuesta.getStatus().getStatusText();
	}
	
	@Override
	public TblMensajes getMensaje(Long idMensaje) {
		return mensajesManager.getMensaje(idMensaje);
	}
	
	public MuleContext getMuleContext() {
		return muleContext;
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
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
	 * @return the queryExecutorLotesEnvios
	 */
	public QueryExecutorLotesEnvios getQueryExecutorLotesEnvios() {
		return queryExecutorLotesEnvios;
	}

	/**
	 * @param queryExecutorLotesEnvios the queryExecutorLotesEnvios to set
	 */
	public void setQueryExecutorLotesEnvios(QueryExecutorLotesEnvios queryExecutorLotesEnvios) {
		this.queryExecutorLotesEnvios = queryExecutorLotesEnvios;
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
	 * @return the errorMensajeLogManager
	 */
	public TblErrorMensajeLogManager getErrorMensajeLogManager() {
		return errorMensajeLogManager;
	}

	/**
	 * @param errorMensajeLogManager the errorMensajeLogManager to set
	 */
	public void setErrorMensajeLogManager(TblErrorMensajeLogManager errorMensajeLogManager) {
		this.errorMensajeLogManager = errorMensajeLogManager;
	}	


}
