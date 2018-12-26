package es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush;


import javax.annotation.Resource;

import nl.martijndwars.webpush.cli.GetNewKeys;
import nl.martijndwars.webpush.cli.SendNotification;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeGenerica;
import es.minhap.plataformamensajeria.iop.beans.RespuestaGenerica;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


/**
 * 
 * @author everis
 *
 */
@Service("pushServiceImpl")
public class PushServiceImpl implements IPushService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PushServiceImpl.class);

	@Resource(name = "registroUsuarioWebPushImpl")
	private IRegistroUsuarioWebPushService registroUsuarioWebPush;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Override
	public String sendPush(DatosEspecificosWebPush datos, String mensajeId) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusOK = ps.getMessage("plataformaErrores.registroWebPush.STATUSTEXT_OK", null);
		String statusKO = ps.getMessage("plataformaErrores.registroWebPush.STATUSTEXT_KO", null);
		RespuestaGenerica response = null;; 
		try{ 
			
			
			String endpoint = datos.getEndpoint();

	        // Base64 string user public key/auth
	        String userPublicKey = datos.getPdh();
	        String userAuth = datos.getAuth();
	        
	        String vapidPublicKey = datos.getVapidPublicKey();
	        String vapidPrivateKey = datos.getVapidPrivateKey();
	        
	        String caducidad =datos.getCaducidad();
	        

			SendNotification sn = new SendNotification(endpoint, userPublicKey, userAuth, vapidPrivateKey, vapidPublicKey, 
					caducidad, getPayload(datos.getCabecera(), datos.getCuerpo()));
			Integer responseCode = sn.sendWebPush();
						
			switch (responseCode){
			case 201:
					
					response = generarRespuesta(mensajeId, statusOK, ps.getMessage("plataformaErrores.registroWebPush.CODOK", null),
							ps.getMessage("plataformaErrores.registroWebPush.DESCOK", null));
				break;
			case 400:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.DETAILS_CODERRORSERVER_400", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERRORSERVER_400", null));
				break;
			case 401:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.DETAILS_CODERRORSERVER_401", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERRORSERVER_401", null));
				break;
			case 404:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.DETAILS_CODERRORSERVER_404", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERRORSERVER_404", null));
				
				//eliminamos el usuario
				registroUsuarioWebPush.eliminarUsuario(endpoint, userAuth, userPublicKey);
				break;
			case 410:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.DETAILS_CODERRORSERVER_410", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERRORSERVER_410", null));
				
				//eliminamos el usuario
				registroUsuarioWebPush.eliminarUsuario(endpoint, userAuth, userPublicKey);
				break;
			case 413:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.DETAILS_CODERRORSERVER_413", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERRORSERVER_413", null));
				break;
			default:
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.COD_ERROR_GENERAL", null),
						ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERROR_GENERAL", null));
				break;
			}
			return response.toXML(response);
		}catch(Exception e){			
			if (e.getMessage().contains("SSLEngine problem")){				
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("Error certificado", null),
				ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERROR_CERTIFICADO", null));
			}
			else{
				response = generarRespuesta(mensajeId, statusKO, ps.getMessage("plataformaErrores.registroWebPush.COD_ERROR_GENERAL", null),
				ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERROR_GENERAL", null));
			}			
			 LOG.error("PushServiceImp.sendPush " ,e);			 
			 try {
				return response.toXML(response);
			} catch (PlataformaBusinessException e1) {
				 LOG.error("PushServiceImp.sendPush " ,e);
			}
		}
		return "";
	}


	private RespuestaGenerica generarRespuesta(String mensajeId, String statusText, String code, String details) {
		RespuestaGenerica res = new RespuestaGenerica();
		ResponseStatusTypeGenerica status = new ResponseStatusTypeGenerica();
		
		if ("OK".equals(statusText)){
			status.setStatusText(statusText);
		}else{
			status.setStatusText(statusText + " | " + details);
		}
		status.setStatusCode(code);
		status.setDetails(details);
		res.setStatus(status);
		res.setMessageId(mensajeId);
		
		return res;
	}

	@Override
	public String getNewKeys() {
		

		GetNewKeys gnk = new GetNewKeys();
		return gnk.generateKey();
	}
	
	 /**
     * Some dummy payload (a JSON object)
     *
     * @return
     */
    private byte[] getPayload(String titulo, String cuerpo) {
    	JSONObject jsonObject = new JSONObject();
    	try{
    	
        jsonObject.append("title", titulo);
        jsonObject.append("message", cuerpo);
        return jsonObject.toString().getBytes();
       }catch(Exception e){
    	   LOG.error("PushServiceImp.getPayLoad " ,e);
       }
       return null;
    }

		

}
