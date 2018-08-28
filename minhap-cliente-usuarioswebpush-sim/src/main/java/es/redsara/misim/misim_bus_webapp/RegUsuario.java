package es.redsara.misim.misim_bus_webapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.redsara.misim.misim_bus_webapp.peticion.PeticionRegistroUsuarioWebPush;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusTypeRegistroWebPush;
import es.redsara.misim.misim_bus_webapp.respuesta.RespuestaRegistroWebPush;

public class RegUsuario {

	private static final Logger LOGGER = Logger.getLogger(RegUsuario.class);

	private static RegUsuario regUsuario;

	private String endpoint;

	private PeticionRegistroUsuarioWebPush peticion;


	private RegUsuario(String endpoint, PeticionRegistroUsuarioWebPush peticion) {
		this.endpoint = endpoint;
		this.peticion = peticion;
	}

	public static RegUsuario getNewInstance(String endpoint, PeticionRegistroUsuarioWebPush peticion) {
		regUsuario = new RegUsuario(endpoint, peticion);
		return regUsuario;
	}

	public RespuestaRegistroWebPush sendMessage() {
		LOGGER.info("Invoking RegistroUsuario...");
		RespuestaRegistroWebPush respuesta = null;
		String jsonInString = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonInString = mapper.writeValueAsString(peticion);

			URL urlRequest = new URL(regUsuario.getEndpoint());
			
			LOGGER.info("ENDPOINT: " + regUsuario.getEndpoint());
			
			if (!urlRequest.getProtocol().contains("https")) {
				HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // application/xml
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", "" + Integer.toString(jsonInString.length()));
				conn.setDoOutput(true);

				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(jsonInString.getBytes());

				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader entrada = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String aux;
					StringBuilder buffer = new StringBuilder();
					while ((aux = entrada.readLine()) != null) {
						buffer.append(aux);
					}

					String stringJson = buffer.toString();
					respuesta = mapper.readValue(stringJson, RespuestaRegistroWebPush.class);
					
				}else{
					respuesta = crearRespuestaError(conn.getResponseMessage());
				}
			}else{
				HttpsURLConnection conn = (HttpsURLConnection) urlRequest.openConnection();
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // application/xml
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", "" + Integer.toString(jsonInString.length()));
				conn.setDoOutput(true);

				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(jsonInString.getBytes());

				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader entrada = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String aux;
					StringBuilder buffer = new StringBuilder();
					while ((aux = entrada.readLine()) != null) {
						buffer.append(aux);
					}

					String stringJson = buffer.toString();
					respuesta = mapper.readValue(stringJson, RespuestaRegistroWebPush.class);
					
				}else{
					respuesta = crearRespuestaError(conn.getResponseMessage());
				}
			
			}

		} catch (Exception e) {
			LOGGER.error("Error Inesperado: " , e);
			respuesta = crearRespuestaError(e.getMessage());
			
		}

		return respuesta;
	}

	private RespuestaRegistroWebPush crearRespuestaError(String details) {
		RespuestaRegistroWebPush res = new RespuestaRegistroWebPush();
		
		ResponseStatusTypeRegistroWebPush status = new ResponseStatusTypeRegistroWebPush();
		status.setStatusCode("9999");
		status.setStatusText("KO");
		status.setDetails(details);
		res.setStatus(status);
		
		return res;
	}

	/**
	 * @return the regUsuario
	 */
	public static RegUsuario getRegUsuario() {
		return regUsuario;
	}

	/**
	 * @param regUsuario
	 *            the regUsuario to set
	 */
	public static void setRegUsuario(RegUsuario regUsuario) {
		RegUsuario.regUsuario = regUsuario;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	
	/**
	 * @param peticion
	 *            the peticion to set
	 */
	public void setPeticion(PeticionRegistroUsuarioWebPush peticion) {
		this.peticion = peticion;
	}

}
