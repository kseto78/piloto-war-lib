package es.minhap.misim.tranformers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.transformers.peticionesrest.Peticion;
import es.minhap.misim.transformers.respuestasrest.Respuesta;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;

public class RestSender {
	private static Logger LOG = LoggerFactory.getLogger(RestSender.class);

	private KeyStore clientKeyStore;
	private SSLContext sslContext;
	private SecureRandom secureRandom;
	private AllTrustHostnameVerifier hostNameVerifier;
	
	private static final String METHODPOST="POST";
	private static final String METHODGET="GET";

	private TrustManager[] tm = new TrustManager[] { new AllTrustX509TrustManager() };

	public RestSender(String pathKeystore, String passphrase) throws ModelException {
		try {
			if (null != pathKeystore) {
				LOG.debug("MDirectSender - [" + Calendar.getInstance().getTime() + "] - pathKeystore: " + pathKeystore);
				clientKeyStore = KeyStore.getInstance("JKS");
				FileInputStream fileInputStream = new FileInputStream(pathKeystore.trim());
				clientKeyStore.load(fileInputStream, passphrase.toCharArray());

				// inicializamos proceso aleatorio
				secureRandom = new SecureRandom();
				secureRandom.nextInt();

				// Creamos e inicializamos KeyManagerFactory
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				kmf.init(clientKeyStore, passphrase.toCharArray());

				// Creamos un sslContext
				sslContext = SSLContext.getInstance("TLS");
				sslContext.init(kmf.getKeyManagers(), tm, secureRandom);
				SSLSocketFactory sf = sslContext.getSocketFactory();
				HttpsURLConnection.setDefaultSSLSocketFactory(sf);

				hostNameVerifier = new AllTrustHostnameVerifier();
			}

		} catch (Exception e) {
			LOG.error("RestSender - [" + Calendar.getInstance().getTime() + "] - Error en el constructor: "
					+ e.getMessage());
			throw new ModelException(e.getMessage());
		}
	}

	/*---------------------------------------------------------------*/
	/*---------------------- Public Methods -------------------------*/
	/*---------------------------------------------------------------*/

	/**
	 * Hace la peticion
	 * 
	 * @param peticion
	 * @return Identificador del SMS para hacer tracking
	 */
	public String sendPeticion(String endpointUrl, Peticion peticion,String method, String contentType, String encoding, int timeout) throws ModelException {
		Respuesta respuesta = new Respuesta();
		String response = "";
		String paramsPost = "";
		try {
			if (peticion.getParameters() != null && peticion.getParameters().getParameters() != null
					&& !peticion.getParameters().getParameters().isEmpty() && (null == method || method.equals(METHODGET))){
				endpointUrl = getParamsUrl(endpointUrl, peticion);
			}else if(peticion.getParameters() != null && peticion.getParameters().getParameters() != null
					&& !peticion.getParameters().getParameters().isEmpty() && method.equals(METHODPOST)){
				paramsPost = getParamsUrl("", peticion);
			}

			URL urlRequest = new URL(endpointUrl);
			LOG.info("ENDPOINT: " + endpointUrl);

			if (!urlRequest.getProtocol().contains("https")) {

				HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
				conn.addRequestProperty("User-Agent", "Mozilla/4.0");
//				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				if (timeout > 0) {
					conn.setConnectTimeout(timeout);
				}

				setKeyAuthorization(peticion, conn);
				
				//es post con parametross
				if (null != paramsPost && paramsPost.length()>0){
					llamadaPostParametros(contentType, encoding, paramsPost, conn);
				}

				//es json o xml
				if (peticion.getMessage() != null) {
					llamadaPostXMLoJson(peticion, contentType, conn);
				}

				// Leemos la respuesta
				
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//if (true){
					BufferedReader entrada = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String aux;
					StringBuffer buffer = new StringBuffer();
					while ((aux = entrada.readLine()) != null) {
						buffer.append(aux);
					}

					String stringJson = buffer.toString();
//					String stringJson = "<!DOCTYPE response SYSTEM \"https://api.lleida.net/dtd/messages/v3/messages_response.dtd\"><response><request>mt</request><code>200</code><status>Success</status><messages><total>3</total><credits>3.0</credits><total_parts>3</total_parts><offset>0</offset><limit>100</limit><message><id>1496072840030001</id><user_id>ZZ</user_id><state code=\"3\">Sent</state><src>34927068081</src><dst>+34639270711</dst><credits>1</credits><parts>1</parts><timestamp>1496072840</timestamp><date>2017-05-29T17:47:20+02:00</date><txt>Message text</txt></message></messages></response>";
////					String stringJson = "<response>   <request>sms</request>   <code>200</code>   <status>Success</status>   <newcredit>16.0</newcredit></response>";
					if (LOG.isInfoEnabled()){
						LOG.info("RESPUESTA DIRECTA PROVEEDOR: " + stringJson);
					}
					
					try {
						JSONObject json = new JSONObject(stringJson);
						stringJson = XML.toString(json);
					} catch (JSONException e) {
						if (LOG.isInfoEnabled()){
							LOG.info("Excepcion parse json a xml: ", e.getMessage());
						}
					}

					ResponseStatusType status = new ResponseStatusType();
					if (stringJson.contains("OK") || stringJson.contains("Success")) {
//						status.setStatusCode("OK");
						status.setStatusCode(String.valueOf(conn.getResponseCode()));
						status.setStatusText(String.valueOf(conn.getResponseMessage()));
//						status.setStatusText("OK");
						status.setDetails(stringJson);
						
					} else {
						status.setStatusCode("KO");
						status.setStatusText("KO");
						status.setDetails(stringJson);
					}
					//respuesta.setMessage(stringJson);
					respuesta.setStatus(status);
					

				} else {

					ResponseStatusType status = new ResponseStatusType();
					status.setStatusCode(String.valueOf(conn.getResponseCode()));
					status.setStatusText(String.valueOf(conn.getResponseMessage()));
					status.setDetails(String.valueOf(conn.getResponseMessage()));
					respuesta.setStatus(status);

				}

			} else { //si es https

				HttpsURLConnection conn = (HttpsURLConnection) urlRequest.openConnection();
				conn.addRequestProperty("User-Agent", "Mozilla/4.0");
//				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				if (timeout > 0) {
					conn.setConnectTimeout(timeout);
				}
				if (null != hostNameVerifier) {
					conn.setHostnameVerifier(hostNameVerifier);
				}

				setKeyAuthorization(peticion, conn);

				//es post con parametross
				if (null != paramsPost && paramsPost.length()>0){
					llamadaPostParametros(contentType, encoding, paramsPost, conn);
				}

				//es json o xml
				if (peticion.getMessage() != null) {
					llamadaPostXMLoJson(peticion, contentType, conn);
				}

				// Leemos la respuesta
				 if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//			if(true){	
					 BufferedReader entrada = new BufferedReader(new
					 InputStreamReader(conn.getInputStream()));
					
					 String aux;
					 StringBuffer buffer = new StringBuffer();
					 while ((aux = entrada.readLine()) != null) {
						 buffer.append(aux);
					 }
				
				 String stringJson = buffer.toString();

//					String stringJson = "OK | 3911778097405636528";
//			String stringJson = "<response>   <request>sms</request>   <code>200</code>   <status>Success</status>   <newcredit>16.0</newcredit></response>";
//			String stringJson = "<response><request>sms</request><code>1601</code><status>Insufficient credit</status></response>";
//			String stringJson = "<!DOCTYPE response SYSTEM \"https://api.lleida.net/dtd/messages/v3/messages_response.dtd\"><response><request>mt</request><code>200</code><status>Success</status><messages><total>3</total><credits>3.0</credits><total_parts>3</total_parts><offset>0</offset><limit>100</limit><message><id>1496072840030001</id><user_id>ZZ</user_id><state code=\"3\">Sent</state><src>34927068081</src><dst>+34639270711</dst><credits>1</credits><parts>1</parts><timestamp>1496072840</timestamp><date>2017-05-29T17:47:20+02:00</date><txt>Message text</txt></message></messages></response>";	
			try {
						JSONObject json = new JSONObject(stringJson);
						stringJson = XML.toString(json);
						
					} catch (JSONException e) {
						if (LOG.isInfoEnabled()){
							LOG.info("Excepcion parse json a xml: ", e.getMessage());
						}
						
					}
					if (LOG.isInfoEnabled()){
						LOG.info("XML INVOCACION SERVICIO REST--->: " + stringJson);
					}

					ResponseStatusType status = new ResponseStatusType();

					if (stringJson.contains("OK") || stringJson.contains("Success")) {
						status.setStatusCode(String.valueOf(conn.getResponseCode()));
						if (null != conn.getResponseMessage()){
							status.setStatusText(String.valueOf(conn.getResponseMessage()));
						}else{
							status.setStatusText("OK");
						}
//						status.setStatusCode("OK");
//						status.setStatusText("OK");
						status.setDetails(stringJson);
						
					} else {
						status.setStatusCode("KO");
						status.setStatusText("KO");
						status.setDetails(stringJson);
					}

					respuesta.setStatus(status);
					//respuesta.setMessage(stringJson);

				} else {

					ResponseStatusType status = new ResponseStatusType();
					status.setStatusCode(String.valueOf(conn.getResponseCode()));
					status.setStatusText(String.valueOf(conn.getResponseMessage()));
					status.setDetails(String.valueOf(conn.getResponseMessage()));
					respuesta.setStatus(status);

				}

			}
			response = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			LOG.error("[RestSender-sendPeticion]", e);
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode("9999");
			status.setStatusText(e.getMessage());
			status.setDetails(e.getMessage());
			respuesta.setStatus(status);
			try {
				response = respuesta.toXMLSMS(respuesta);
			} catch (Exception e1) {
				LOG.error("[RestSender-sendPeticion]", e1);
				response = null;
			}

		}

		return response;
	}

	private void llamadaPostXMLoJson(Peticion peticion, String contentType, HttpURLConnection conn)
			throws IOException {
		conn.setRequestProperty("Content-Type", contentType); //application/xml 
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", "" + 
	               Integer.toString(peticion.getMessage().getBytes().length));
		conn.setDoOutput(true);

		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(peticion.getMessage().getBytes());
	}

	private void llamadaPostParametros(String contentType, String encoding, String paramsPost, HttpURLConnection conn)
			throws NoSuchFieldException, IllegalAccessException, ProtocolException, IOException {
		Field field = StandardCharsets.class.getField(encoding.replace("-", "_"));
		field.setAccessible(true);
		Charset fieldValue = (Charset) field.get(this);
		byte[] postData = paramsPost.getBytes(fieldValue);
		int postDataLength = postData.length;
		conn.setRequestProperty("Content-Type", contentType); //application/xml 
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
		conn.setUseCaches(false);
		
		 //Send request
		  DataOutputStream wr = new DataOutputStream (
		              conn.getOutputStream ());
		  wr.write (postData);
		  wr.flush ();
		  wr.close ();
	}

	private String getParamsUrl(String endpointUrl, Peticion peticion) {
		if (peticion.getParameters() != null && peticion.getParameters().getParameters() != null
				&& !peticion.getParameters().getParameters().isEmpty()) {

			boolean inicial = true;
			for (int i = 0; i < peticion.getParameters().getParameters().size(); i++) {

				if (peticion.getParameters().getParameters().get(i).getLabel() != null) {
					if (inicial) {
						if (endpointUrl.endsWith("/")) {
							endpointUrl = endpointUrl + peticion.getParameters().getParameters().get(i).getValue();
						} else {
							endpointUrl = endpointUrl + peticion.getParameters().getParameters().get(i).getLabel()
									+ "=" + peticion.getParameters().getParameters().get(i).getValue();
						}

						inicial = false;
					} else {
						endpointUrl = endpointUrl + "&"
								+ peticion.getParameters().getParameters().get(i).getLabel() + "="
								+ peticion.getParameters().getParameters().get(i).getValue();
					}

				} else {

					if (endpointUrl.endsWith("/")) {
						endpointUrl = endpointUrl + peticion.getParameters().getParameters().get(i).getValue();
					} else {
						endpointUrl = endpointUrl + "/"
								+ peticion.getParameters().getParameters().get(i).getValue();
					}

				}
			}
		}
		return endpointUrl;
	}

	private void setKeyAuthorization(Peticion peticion, HttpURLConnection conn) {
		if (peticion.getKeyAuthorization() != null && peticion.getKeyAuthorization().getKey() != null) {
			conn.setRequestProperty("Authorization", "key=" + peticion.getKeyAuthorization().getKey());
		} else if (peticion.getBasicAuthorization() != null) {

			if (peticion.getBasicAuthorization().getUser() != null) {

				String userpass = peticion.getBasicAuthorization().getUser();

				if (peticion.getBasicAuthorization().getPassword() != null) {
					userpass = userpass + ":" + peticion.getBasicAuthorization().getPassword();
				}

				String basicAuth = "Basic "
						+ javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
				conn.setRequestProperty("Authorization", basicAuth);
			}
		}
	}

	/*---------------------------------------------------------------*/
	/*---------------------- Private Classes ------------------------*/
	/*---------------------------------------------------------------*/

	// Clase MyHostnameVerifier implementa HostnameVerifier.
	// Se utiliza para verificar el nombre de host.
	private class AllTrustHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	// La clase myTrust509 implementa la interface X509TrustManager
	// La utilizamos para confiar por defecto en el certificado enviado por el
	// servidor.
	private class AllTrustX509TrustManager implements X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
	}
}