package es.minhap.misim.components;


/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisorRPC{ //implements Callable {

//	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisorRPC.class);
//
//	@Resource(name="proveedorManager")
//	private ProveedorManager proveedorManager; 	
//	
//	private static final String ENCODING = "ISO-8859-15";
//
//
//	@Override
//	public Object onCall(final MuleEventContext eventContext) throws ModelException {

//		LOG.info("Inicio invocación emisor RPC");
//		
//		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
//		String respuesta = "";
//		String statusCode = "";
//		Proveedor p = null;
//		
//		try {
//
//			
//			Parameters parametros = new Parameters();
//			parametros = parametros.loadObjectFromXML( XMLUtils.dom2xml(docOriginal));
////			parametros = leerParametros(docOriginal);
//			
//
//			try {
//
//				Long proveedorId = eventContext.getMessage().getOutboundProperty("idProveedor");
//				p = proveedorManager.getProveedorById(proveedorId);
//				
//				String metodoEjecucion = p.getMethod();
//				
//				String url = String.class.cast(eventContext.getMessage().getOutboundProperty("endpointUrl"));
//				String encoding = String.class.cast(eventContext.getMessage().getOutboundProperty("encoding"));
//
//				if (null == encoding){
//					encoding = ENCODING;
//				}
//				
//				if (eventContext.getMessage().getOutboundProperty("urlEndpointSIM") != null
//						&& !("").equals(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"))) {
//					url = String.class.cast(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"));
//				}
//
//				String user = parametros.getParameters().get(0).getValue();
//				String password = parametros.getParameters().get(1).getValue();
//				
//				Vector<Serializable> params = getParametrosEnvio(user, password, parametros);
//				
//				
//				RPCSender sender = new RPCSender(new URL(url), encoding);
//				Object result = sender.sendSMS(metodoEjecucion,params);
//				
//				try{
//				if(result instanceof Integer) {
//					LOG.info("RESULT------>"+result);
//				}else{
//					for(int i=0; i<((Object[])result).length;i++){
//						LOG.info("OBJETO------>"+((Object[])result)[i]);
//					}
//				}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
//		
//				
//				if (respuesta.contains("OK")) {
//					statusCode = "OK";
//				} else {
//					statusCode = "KO";
//				}
//
//			} catch (Exception e) {
//				LOG.error("Error leyendo los parámetros de la llamada 1: " + e.toString());
//				respuesta = "ERROR " + e.toString();
//				statusCode = "KO";
//			}
//
//		} catch (ModelException e) {
//			LOG.error("Error leyendo los parámetros de la llamada 2: " + e.getMensaje());
//			respuesta = "ERROR " + e.getMensaje();
//			statusCode = "KO";
//
//		} catch (Exception e) {
//			LOG.error("Error leyendo los parámetros de la llamada 3: " + e.toString());
//			respuesta = "ERROR " + e.toString();
//			statusCode = "KO";
//		}
//
//		try {
//
//			ResponseStatusType response = new ResponseStatusType();
//			response.setStatusCode(statusCode);
//			response.setStatusText(respuesta);
//			response.setDetails("");
//
//			Respuesta respuestaWS = new Respuesta();
//			respuestaWS.setStatus(response);
//			respuestaWS.setMessageId(String.class.cast(eventContext.getMessage().getOutboundProperty("smsID")));
//			if (null == respuestaWS.getMessageId())
////				respuestaWS.setMessageId(parametros[0]);
//
//			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(
//					XMLUtils.setPayloadFromObject(respuestaWS, Charset.forName("UTF-8"), Respuesta.class));
//
//			eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador",
//					XMLUtils.dom2xml(SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage()));
//
//		} catch (Exception e) {
//			LOG.error("Error al generar la respuesta: " + e.getMessage());
//			throw new ModelException(e.getMessage());
//		}
//
//		LOG.debug("Fin invocación emisor HTTP.");
//
//		return eventContext.getMessage();
//	}
//
//	private Vector<Serializable> getParametrosEnvio(String user, String password, Parameters parametros) {
//		Vector<Serializable> res = new Vector<Serializable>();
//		
//		res.addElement(user);
//		res.addElement(password);
//		
//		Vector<Vector<String>> sms = new Vector<Vector<String>>();
//		Vector<String> primer_sms = new Vector<String>();
//		primer_sms.addElement(parametros.getParameters().get(2).getValue());
//		primer_sms.addElement(parametros.getParameters().get(3).getValue());
//		primer_sms.addElement(parametros.getParameters().get(4).getValue());
//		sms.addElement(primer_sms);
//		
//		res.addElement(sms);
//		return res;
//	}
//
////	private String[] leerParametros(Document docOriginal) throws Exception {
////
////		Parameters param = new Parameters();
////		param = param.loadObjectFromXML( XMLUtils.dom2xml(docOriginal));
////		String[] parametros = new String[4];
////
////		NodeList SMS_ID = docOriginal.getElementsByTagName("SMS_ID");
////		NodeList SMS_DESTINATARIO = docOriginal.getElementsByTagName("SMS_DESTINATARIO");
////		NodeList SMS_TEXTO = docOriginal.getElementsByTagName("SMS_TEXTO");
////		NodeList SMS_HEADER = docOriginal.getElementsByTagName("SMS_HEADER");
////
////		parametros[0] = SMS_ID.item(0).getTextContent();
////		parametros[1] = SMS_DESTINATARIO.item(0).getTextContent();
////		parametros[2] = SMS_TEXTO.item(0).getTextContent();
////		parametros[3] = SMS_HEADER.item(0).getTextContent();
////
////		return parametros;
////	}

}