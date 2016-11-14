package es.minhap.misim.tranformers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import es.minhap.misim.bus.model.exception.ModelException;



public class GMCSendMessage {
	
	private static final Logger log = LoggerFactory.getLogger(GMCSendMessage.class);

	
	
	/**
	 * Metodo que se encarga de realizar el envio de la notificacion Push a los dispositivos
	 * a traves de Google Cloud Messaging 
	 * 
	 * @param gcmApiKey Key del proyecto Android
	 * @param gcmTokens Tokens de los usuarios a los que se desea enviar la notificacion Push
	 * @param url URL a la que realizar la peticion
	 * @param badge Indica el numero de notificaciones que el usuario tiene pendientes de leer
	 * @param title Titulo del mensaje de la notificacion Push
	 * @param body Cuerpo del mensaje de la notificacion Push
	 * 
	 * @return Respuesta de la peticion
	 */
	public String enviarGoogle(String gcmApiKey, List<String> gcmTokens, String url,
			String badge, String title, String body, String sound, String icon, String idMensaje) throws ModelException {

			try {
	
	        // Create connection to send GCM Message request
	        URL urlRequest = new URL(url);
	        
	        if(!urlRequest.getProtocol().contains("https")){
	        
	            HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
	            conn.setRequestProperty("Authorization", "key=" + gcmApiKey);
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            
	            String messageToSend = newMessage(gcmTokens, badge, title, body, sound, icon,idMensaje);
	            
	            log.info(messageToSend);
	            
	            // Send GCM message content
	            OutputStream outputStream = conn.getOutputStream();
	            outputStream.write(messageToSend.getBytes());
	            
	            log.info("Ha enviado el mensaje");
	            
	            // Read GCM response
	            InputStream inputStream = conn.getInputStream();
	            String resp = IOUtils.toString(inputStream);
	            
	            log.info("Se ha recibido la respuesta");
	            
	            return resp;
	            
	            
	        }else{
	        	
	        	HttpsURLConnection conn = null;
	        	TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
					public X509Certificate[] getAcceptedIssuers(){return null;}
					public void checkClientTrusted(X509Certificate[] certs, String authType){}
					public void checkServerTrusted(X509Certificate[] certs, String authType){}
				}};
	
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};
	 
				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
				
	
				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				
				conn = (HttpsURLConnection) urlRequest.openConnection();
				conn.setRequestProperty("Authorization", "key=" + gcmApiKey);
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
//TODO:			Mensaje válido de data
//	            { "registration_ids": [ "APA91bEAI5WzTsswjqftDmN9DW4_cczfc8eRveae9xoCvc-ME0uuoaqLz9Qy0D06Uek9189v4jm3sihpeYbWI2Zyp9mI5Ky1_jROLaaMkLTnEy_L7avvuYqZxwFPE147I8BPq5FAtfGe59GIrbEodbefI1fhTR_Mkg", "APA91bEVZx1Bxm7QGLXyCFNgsufhCFZ1EBt3R6J1kXxyQGp4CQXUp96fO7ZTRMuh0ga9OS0wMnxji4eDkc0W3NwC0SMsPbhNt6kJg1ktT39CNRkRsFH7Kp-6OOeBqZuz05zGApkHXLOPxUeQ5esDSHDk-N-eW4wlSg" ], 
//	            	"data": {"contentTitle":"titulo", "tickerText":"TicketText", "id_convocatoria":"123891293812983", "id_suscripcion":"12931289312", "bodyMessage":"Prueba de avisos PUSH por favor avisad si os llega", "url": "http://google.com"}}
//	            {"title":"titulo", "tickerText":"TicketText", "id_convocatoria":"123891293812983", "id_suscripcion":"12931289312", "bodyMessage":"ArmandoPichafloja", "url": "http://google.com"}	            
	            String messageToSend = newMessage(gcmTokens, badge, title, body, sound, icon, idMensaje);
	            
	            log.info(messageToSend);
	            
	            // Send GCM message content
	            OutputStream outputStream = conn.getOutputStream();
	            outputStream.write(messageToSend.getBytes());
	            
	            log.info("Ha enviado el mensaje");
	            
	            // Read GCM response
	            InputStream inputStream = conn.getInputStream();
	            String resp = IOUtils.toString(inputStream);
	            
	            log.info("Se ha recibido la respuesta");
	            
	            return resp;
	        	
	        }
	
	    } catch (MalformedURLException e) {
	    	log.error("GMCSendMessage", e);
	    	throw new ModelException(e.getMessage(),101);
	    	
	    } catch (UnknownServiceException e) {
	    	log.error("GMCSendMessage", e);
	    	throw new ModelException(e.getMessage(), 101);
	
	    } catch (IOException e) {
	    	log.error("GMCSendMessage", e);
	    	throw new ModelException(e.getMessage(), 101);
	
	    } catch (Exception e) {
	    	log.error("GMCSendMessage", e);
	    	throw new ModelException(e.toString(),101); 
	    }


	}
	
	/**
	 * Metodo que construye el mensaje que se envia a la peticion POST en formato JSON
	 * 
	 * @param gcmTokens Tokens de los usuarios a los que se desea enviar la notificacion Push
	 * @param badge Indica el numero de notificaciones que el usuario tiene pendientes de leer
	 * @param title Titulo del mensaje de la notificacion Push
	 * @param body Cuerpo del mensaje de la notificacion Push
	 * 
	 * @return Mensaje que se envia a la peticion POST
	 * @throws JSONException 
	 */
	private String newMessage(List<String> gcmTokens, String badge, String title, String body, String sound, String icon, String idMensaje) throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
        JSONObject notificationObject = new JSONObject();
        String data = body;
        String string="";
        if (body.contains("{")){
//        	data = data.replaceAll("\t","'");
        	data = data.replaceAll("\\\\\"","\"");
//        	while (data.contains(":\\\"")){
//        		data.replace(":\\", ":");
//        	}
	    	if(gcmTokens.size()>1){
	    		
				string = "{ \"registration_ids\": [ ";
	
				boolean primerToken = true;
				for (String token : gcmTokens) {
					
					if(primerToken){
						string = string +"\""+ token +"\"";
						primerToken = false;
					}else{
						string = string +", \""+ token +"\"";
					}
				}
	
				string = string + " ], ";
	    		
	    	}else{
	    		
	    		string = "{ \"to\": \"";
	    		string = string + gcmTokens.get(0) + "\", ";
	    	}
	    	
//	    	data = data.replace("contentTitle", "TituloNoValido");
//	    	data = data.replace("tickerText", "contentTitle");
//	    	data = data.replace("TituloNoValido", "tickerText");
//	    	System.out.println("GMCSendMessage :".concat(data));
	    	data.replaceAll("}", " ,\"idMensaje\":\""+ idMensaje +"\" } ");
	    	string = string.concat(" \"data\": ").concat(data.replaceAll("}", " ,\"idMensaje\":\""+ idMensaje +"\" } ")).concat("}");
//        	{ "registration_ids": [ "APA91bEAI5WzTsswjqftDmN9DW4_cczfc8eRveae9xoCvc-ME0uuoaqLz9Qy0D06Uek9189v4jm3sihpeYbWI2Zyp9mI5Ky1_jROLaaMkLTnEy_L7avvuYqZxwFPE147I8BPq5FAtfGe59GIrbEodbefI1fhTR_Mkg", "APA91bEVZx1Bxm7QGLXyCFNgsufhCFZ1EBt3R6J1kXxyQGp4CQXUp96fO7ZTRMuh0ga9OS0wMnxji4eDkc0W3NwC0SMsPbhNt6kJg1ktT39CNRkRsFH7Kp-6OOeBqZuz05zGApkHXLOPxUeQ5esDSHDk-N-eW4wlSg" ], "data": {"contentTitle":"titulo", "tickerText":"TicketText", "id_convocatoria":"123891293812983", "id_suscripcion":"12931289312", "message":"Prueba de avisos PUSH por favor avisad si os llega", "url": "http://google.com"}}        	
        	
        	
        	
        	jsonObject.accumulate("data", string);
        	
        	
        	
        	//string = data;
        	log.info("GMCSendMessage string:".concat(string));
//	    	System.out.println("GMCSendMessage data:".concat(data));	    	
        } 	else{
            notificationObject.accumulate("title", title);
            notificationObject.accumulate("badge", badge);
            
            if (icon!=null && !("").equals(icon)){
            	notificationObject.accumulate("icon", icon);
            }
            if (sound!=null && !("").equals(sound)){
            	notificationObject.accumulate("sound", sound);
            }
            
            notificationObject.accumulate("idMensaje", idMensaje);
            notificationObject.accumulate("body", body);
        
//        if (gcmTokens!=null){
//        	
//        	if(gcmTokens.size()>1){
//        
//		        JSONArray regIdArray = new JSONArray();
//		        		        
//			    for (String id : gcmTokens) {			    	
//			        regIdArray.put(id);
//			    }
			    
//			    String tokens = null;
//			    
//			    for (int i=0; i<gcmTokens.size(); i++){
//			    	
//			    	if(tokens!=null){
//			    		tokens = tokens+ ",\"" +  gcmTokens.get(i) + "\"";
//			    	}else{
//			    		tokens = "\"" +  gcmTokens.get(i) + "\"";
//			    	}
//			    	
//			    }
//			    
//			    tokens="[" + tokens + "]"; 
			    
//		        jsonObject.accumulate("registration_ids", regIdArray);
//			}else{
//				
//				 jsonObject.accumulate("to", gcmTokens.get(0));
//			}
//        }
        	jsonObject.accumulate("notification", notificationObject);
        
	        	
	    	if(gcmTokens.size()>1){
	    		
				string = "{ \"registration_ids\": [ ";
	
				boolean primerToken = true;
				for (String token : gcmTokens) {
					
					if(primerToken){
						string = string +"\""+ token +"\"";
						primerToken = false;
					}else{
						string = string +", \""+ token +"\"";
					}
				}
	
				string = string + " ], ";
	    		
	    	}else{
	    		
	    		string = "{ \"to\": \"";
	    		string = string + gcmTokens.get(0) + "\", ";
	    	}
	    	
	    	string = string + "\"notification\" :" + notificationObject.toString();
	    	string = string + "}";
    	}
        
//        return jsonObject.toString();
        
//        String string = "{ \"registration_ids\": [ \"" +  gcmTokens.get(0) + "\" ], " +
//        "\"data\": {\"tickerText\":\"" + title + "\", " +
//                   "\"contentTitle\":\"" + title + "\", " +
//                   "\"message\": \"" + body + "\"}}";
//    	System.out.println("GMCSendMessage string:".concat(string));	    	
        return string;

//		return getJsonResponse(jsonObject);
		
	}
	
	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();
		
		String result = gson.toJson(conResponse);
		return result;
	}
	
}
