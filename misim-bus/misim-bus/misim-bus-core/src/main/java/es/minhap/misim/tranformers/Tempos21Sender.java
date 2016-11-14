package es.minhap.misim.tranformers;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.misim.bus.model.exception.ModelException;


public class Tempos21Sender
{
    private TrustManager[] tm = new TrustManager[] {
                                new AllTrustX509TrustManager()
    };

    private KeyStore clientKeyStore;
    private SSLContext sslContext;
    private SecureRandom secureRandom;
    private AllTrustHostnameVerifier hostNameVerifier;
	
	private String url;

    private static final String USER = "user=";
    private static final String COMPANY = "&company=";
    private static final String PASSWORD = "&passwd=";
    private static final String GSM = "&gsm=";
    private static final String MSG = "&msg=";
    private static final String UIM = "&uim=";
    private static final String HEADER = "&sender=";
	private static final String TYPE = "&type=plus";

	private static Logger LOG = LoggerFactory.getLogger(Tempos21Sender.class);


    /*---------------------------------------------------------------*/
    /*---------------------- Constructors ---------------------------*/
    /*---------------------------------------------------------------*/

    /**
     * Crea un cliente para interactuar con el servidor de M-Direct.
     * 
     * @param URL 		URL para el env�o de SMS
     * @param company	Compa�ia provisionada en la plataforma
     * @param usuario	Usuario de la plataforma
     * @param passwd	Password del usuario
     * @param header	Cabecera del usuario
     * @param pathKeystore	Ruta donde se encuentra el keystore que incluye el certificado cliente
     * @param passphrase	Contrase�a del certificado. 
     * @throws Tempos21SenderException	Se lanza si se produce un error en la comunicaci�n o 
     * bien el servidor devuelve un error
     */
    public Tempos21Sender(String URL, String company, String usuario, String passwd, String header,
                         String pathKeystore, String passphrase, String accion) throws ModelException{
        try {

			LOG.info("MDirectSender - [" + Calendar.getInstance().getTime() + "] - accion: " + accion);
			
        	if (accion.equals("envio")) {
				url = URL + USER + usuario + COMPANY + company + PASSWORD + passwd + TYPE + HEADER + header;
			} else {
				url = URL + USER + usuario + COMPANY + company + PASSWORD + passwd;
			}
			LOG.info("MDirectSender - [" + Calendar.getInstance().getTime() + "] - url: " + url);

            // Leemos los certificados que contiene el fichero client.private
			LOG.debug("MDirectSender - [" + Calendar.getInstance().getTime() + "] - pathKeystore: " + pathKeystore);
			
            clientKeyStore = KeyStore.getInstance("JKS");
            FileInputStream fileInputStream = new FileInputStream(pathKeystore.trim());
            clientKeyStore.load(fileInputStream,passphrase.toCharArray());
            
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

        } catch (Exception e) {
			LOG.error("MDirectSender - [" + Calendar.getInstance().getTime() + "] - Error en el constructor: " + e.getMessage());
			throw new ModelException(e.getMessage()); 
        }
    }


    /*---------------------------------------------------------------*/
    /*---------------------- Public Methods -------------------------*/
    /*---------------------------------------------------------------*/

    /**
     * Env�a un SMS al tel�fono dado.
     *
     * @param tlf Tel�fono al cual enviar el SMS
     * @param msg Mensaje del SMS
     * @return Identificador del SMS para hacer tracking
     * @throws Tempos21SenderException	Se lanza si se produce un error en la comunicaci�n o 
     * bien el servidor devuelve un error
     */
    public String sendSMS(String tlf, String msg) throws ModelException
    {
        String uim = null;

        LOG.info("sendSMS - TLF "+tlf+" - msg a enviar: " + msg);
        try {
        	if(null != msg){
        		msg = msg.replaceAll("�", "E");
        		msg = msg.replaceAll("�", "e");
        	}
            msg = URLEncoder.encode(msg, "ISO-8859-1");
        	//msg = URLEncoder.encode(msg, "UTF8");
        	
            
            String urlStr = url + GSM + tlf + MSG + msg;
			LOG.info("sendSMS - [" + Calendar.getInstance().getTime() + "] - url a enviar: " + urlStr);

            uim = send(urlStr);
			LOG.debug("sendSMS - [" + Calendar.getInstance().getTime() + "] - uim: " + uim);

        } catch (ModelException e) {
			LOG.error("sendSMS - [" + Calendar.getInstance().getTime() + "] - Excepcion de MDirect: " + e.getMensaje());
			throw e;
        } catch (Exception e) {
			LOG.error("sendSMS - [" + Calendar.getInstance().getTime() + "] - Excepcion: " + e.getMessage());
            throw new ModelException(e.getMessage()); 
        }

        return uim;
    }


    /**
     * Obtiene el tracking de un mensaje identificado por su UIM.
     *
     * @param uim	Identificador del mensaje
     * @return 		Identificador del mensaje
     * @throws Tempos21SenderException	Se lanza si se produce un error en la comunicaci�n o 
     * bien el servidor devuelve un error
     */
	public String getTracking(String uim) throws ModelException
    {
        String result = null;

        try {

            String urlStr = url + UIM + uim;
			LOG.info("getTracking - [" + Calendar.getInstance().getTime() + "] - url a enviar: " + urlStr);

            result = send(urlStr);
			LOG.debug("getTracking - [" + Calendar.getInstance().getTime() + "] - result: " + result);

		} catch (ModelException e) {
			LOG.error("getTracking - [" + Calendar.getInstance().getTime() + "] - Excepcion de MDirect: " + e.getMensaje());
			throw e;
        } catch (Exception e) {
			LOG.error("getTracking - [" + Calendar.getInstance().getTime() + "] - Excepcion: " + e.getMessage());
			throw new ModelException(e.getMessage()); 
        }

        return result;
    }



    /*---------------------------------------------------------------*/
    /*--------------------- Private Methods -------------------------*/
    /*---------------------------------------------------------------*/

    private String send(String urlStr) throws ModelException
    {

        String result = null;
        
        try {
            // creamos e inicializamos conexi�n con el servidor remoto
            URL url = new URL(urlStr);
            HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection();

            // verificamos el Hostname con nuestra clase MyHostnameVerifier().
            // Siempre lo aceptaremos.
            urlcon.setHostnameVerifier(hostNameVerifier);

			LOG.debug("send - [" + Calendar.getInstance().getTime() + "] - codigo de vuelta al realizar la llamada: " + urlcon.getResponseCode());

            // Leemos la respuesta
            if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader entrada = new BufferedReader(
                                                new InputStreamReader(
                                                        urlcon.getInputStream()));
                String aux;
                StringBuffer buffer = new StringBuffer();
                while ((aux = entrada.readLine()) != null) {
                    buffer.append(aux);
                }

                result = buffer.toString();
            } else {
                //No se puede conectar con el servidor
    			LOG.error("send - [" + Calendar.getInstance().getTime() + "] - Error en la peticion");
				throw new ModelException("Error en la peticion");
            }
        } catch (Exception e) {
			LOG.error("send - [" + Calendar.getInstance().getTime() + "] - Excepcion de MDirect: " + e.getMessage());
			throw new ModelException(e.getMessage()); 
        }

		LOG.debug("send - [" + Calendar.getInstance().getTime() + "] - result: " + result);
        return result;
    }

    /*---------------------------------------------------------------*/
    /*---------------------- Private Classes ------------------------*/
    /*---------------------------------------------------------------*/

    // Clase MyHostnameVerifier implementa HostnameVerifier.
    // Se utiliza para verificar el nombre de host.
    private class AllTrustHostnameVerifier implements HostnameVerifier
    {
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    // La clase myTrust509 implementa la interface X509TrustManager
    // La utilizamos para confiar por defecto en el certificado enviado por el
    // servidor.
    private class AllTrustX509TrustManager implements X509TrustManager
    {
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
        {
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
        {
        }
    }
}