package es.minhap.plataformamensajeria.sm.sendmessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sun.mail.smtp.SMTPAddressFailedException;

import es.minhap.plataformamensajeria.sm.jdbc.JDBCAccess;
import es.minhap.plataformamensajeria.sm.modelo.Adjunto;
import es.minhap.plataformamensajeria.sm.modelo.Aplicacion;
import es.minhap.plataformamensajeria.sm.modelo.DestinatarioDMensaje;
import es.minhap.plataformamensajeria.sm.modelo.HistoricoData;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.Proveedor;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMTPAuthenticator;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.redsara.misim.misim_bus_webapp.enviarmensaje.EnviarMensajeService;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.EnviarNotificacionService;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.peticion.NotificacionDataRequest;
import es.redsara.misim.misim_bus_webapp.recpecionsms.RecibirSmsService;
import es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.EnvioAplicacionRequest;

public class SendMailService
{
  private static final String VERSION_SEND_MAIL = "1.3";
 
  private Integer maxSecondsRetryBBDD = Integer.valueOf(60);
  private Boolean firstTime = Boolean.valueOf(true);
  public Connection conn = null;
  private Boolean stopService;
  private String connectionString;
  private String bbddUser;
  private String bbddPassword;
  private String urlSMS;
  private static SendMailService sendMailService = new SendMailService();
  private static Logger log = Logger.getLogger(SendMailService.class);
  private static JDBCAccess jdbc = new JDBCAccess();
  private static String ruta = "conf/sendMail/";
  private String ejecutarTodosProveedores;
  private String ejecutarTodosServidores;
  private String ejecutarTodosReceptores;
  private String EjecutarTodosServidoresPush;
  private String MISIM_URL_ENVIARMENSAJES_WS = "";
  private String MISIM_SERVICE_NAME = "";
  private String MISIM_USUARIO = "";
  private String MISIM_PASSWORD = "";
  private String MISIM_WSDL = "";
  private Integer TIME_TO_SLEEP_PROCESS = 0;
  private Integer TIME_SLEEP_H_PROCESS = 0;
  private String intervaloMensajes="";
  private String intervaloHistorico="";
 
  
  public static void main(String[] args)
  {
    if ((null != args) && (args.length > 0)) {
      ruta = args[0];
    }
    try
    {
      PropertyConfigurator.configure(ruta + "log4j.properties");
      if (log.isInfoEnabled()) {
        log.info("Start Servicio SendMail (Envio Emails y SMS) Version Actual: 1.3");
      }
      sendMailService.initializeVariables();
      
      sendMailService.dequeueMails();
      if (log.isInfoEnabled()) {
        log.info("Stop SendMailService-Main");
      }
    }
    catch (Exception e)
    {
      log.error("Error and Stoping SendMailService-Main", e);
    }
  }
  
  public Integer postNotificacionPush(Integer i)
  {
    Integer res = postNotificacionPush(i);
    return res;
  }
  
  public Integer postSMS(Integer i)
  {
    Integer res = Integer.valueOf(postRecepcionSMS(i.intValue()));
    return res;
  }
  
  public void initializeVariables()
  {
    try
    {
      this.stopService = Boolean.valueOf(false);
      
      Properties configFile = new Properties();
      File f = new File(ruta + "configuration.properties");
      log.info("Ruta para propeties: " + f.getAbsolutePath());
      
      FileInputStream in = new FileInputStream(f);
      
      configFile.load(in);
      in.close();
      
      this.connectionString = configFile.getProperty("DatabaseConectionString");
      
      this.bbddUser = configFile.getProperty("DatabaseUser");
      this.bbddPassword = configFile.getProperty("DatabasePassword");
      this.urlSMS = configFile.getProperty("UrlSMS");
      this.ejecutarTodosProveedores = configFile.getProperty("EjecutarTodosProveedores");
      
      this.ejecutarTodosServidores = configFile.getProperty("EjecutarTodosServidores");
      
      this.ejecutarTodosReceptores = configFile.getProperty("EjecutarTodosReceptores");
      
      this.EjecutarTodosServidoresPush = configFile.getProperty("EjecutarTodosServidoresPush");
      
      this.MISIM_URL_ENVIARMENSAJES_WS = configFile.getProperty("MISIM_URL_ENVIARMENSAJES_WS");
      
      this.MISIM_SERVICE_NAME = configFile.getProperty("MISIM_SERVICE_NAME");
      this.MISIM_USUARIO = configFile.getProperty("MISIM_USUARIO");
      this.MISIM_PASSWORD = configFile.getProperty("MISIM_PASSWORD");
      this.MISIM_WSDL = configFile.getProperty("MISIM_WSDL");
      this.intervaloHistorico= configFile.getProperty("TIME_SLEEP_H_PROCESS");
      this.intervaloMensajes= configFile.getProperty("TIME_TO_SLEEP_PROCESS");
      
		if (intervaloMensajes != null && !"".equals(intervaloMensajes)) {
			try {
				this.TIME_TO_SLEEP_PROCESS = Integer.valueOf(intervaloMensajes);
			} catch (Exception e) {
				this.TIME_TO_SLEEP_PROCESS = 1;
			}
		}
		String intervaloHistorico = configFile.getProperty("TIME_SLEEP_H_PROCESS");
		if (intervaloHistorico != null && !"".equals(intervaloHistorico)) {
			try {
				this.TIME_SLEEP_H_PROCESS = Integer.valueOf(intervaloHistorico);
			} catch (Exception e) {
				this.TIME_SLEEP_H_PROCESS = 1;
			}
		}
      
		log.info("TIME_TO_SLEEP_PROCESS:" + this.TIME_TO_SLEEP_PROCESS);
		log.info("TIME_SLEEP_H_PROCESS:" + this.TIME_SLEEP_H_PROCESS);
		
      File fu = new File(ruta + this.MISIM_WSDL);
      URL urlmisim = new URL("file:///" + fu.getAbsolutePath());
      log.info("Ruta para WSDL: " + fu.getAbsolutePath());
      
      EnviarMensajeService.wsdlURL = urlmisim;
      EnviarMensajeService.serviceName = new QName(this.MISIM_URL_ENVIARMENSAJES_WS, this.MISIM_SERVICE_NAME);
      
      es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.wsdlURL = urlmisim;
      es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.serviceName = new QName(this.MISIM_URL_ENVIARMENSAJES_WS, this.MISIM_SERVICE_NAME);
      
      RecibirSmsService.wsdlURL = urlmisim;
      RecibirSmsService.serviceName = new QName(this.MISIM_URL_ENVIARMENSAJES_WS, this.MISIM_SERVICE_NAME);
      
      EnviarNotificacionService.wsdlURL = urlmisim;
      EnviarNotificacionService.serviceName = new QName(this.MISIM_URL_ENVIARMENSAJES_WS, this.MISIM_SERVICE_NAME);
      try
      {
        this.maxSecondsRetryBBDD = Integer.valueOf(Integer.parseInt(configFile.getProperty("DatabaseMaxRetry")));
      }
      catch (Exception e)
      {
        if (log.isInfoEnabled()) {
          log.info("No se puede parsear la variable 'DatabaseMaxRetry': " + e.getMessage() + " (initializeVariables)");
        }
      }
      openConnection();
    }
    catch (Exception e)
    {
      if (log.isInfoEnabled()) {
        log.info("No se puede leer el fichero de configuracion: " + e.getMessage() + " (initializeVariables)");
      }
    }
  }
  
  private void dequeueMails()
    throws InterruptedException
  {
    Integer sended = Integer.valueOf(0);
    if (log.isDebugEnabled())
    {
      log.debug("------------");
      log.debug("dequeueMails");
      log.debug("------------");
    }
    while (!this.stopService.booleanValue())
    {
      int messageId = getNextMessageId();
      if (log.isDebugEnabled()) {
        log.debug("MessageId: " + messageId);
      }
      if (messageId > 0)
      {
        if (isMessagePending(messageId))
        {
        	sleepProcess();
          if (getTypeMessage(messageId).equals("EMAIL"))
          {
        	  sleepProcess();
            if (log.isDebugEnabled()) {
              log.debug("Enviamos el Mail (dequeueMails)");
            }
            try {
				conn.setAutoCommit(false);
	            sended = Integer.valueOf(postMail(Integer.valueOf(messageId)));
	            conn.commit();
	            reOpenConnection();
			} catch (SQLException e) {
				log.error("Se ha producido un error de transaccionalidad jdbc", e);
			}
          }
          else if (getTypeMessage(messageId).equals("SMS"))
          {
            if (log.isDebugEnabled()) {
              log.debug("Enviamos el SMS (dequeueMails)");
            }
            
            try {
				conn.setAutoCommit(false);
				sended = Integer.valueOf(postSMS(messageId));
				conn.commit();
	            reOpenConnection();
			} catch (SQLException e) {
				log.error("Se ha producido un error de transaccionalidad jdbc", e);
			}
          }
          else if (getTypeMessage(messageId).equals("RECEPCION SMS"))
          {
            if (log.isDebugEnabled()) {
              log.debug("Enviamos el SMS (dequeueMails)");
            }
            
            try {
				conn.setAutoCommit(false);
	            sended = Integer.valueOf(postRecepcionSMS(messageId));
				conn.commit();
	            reOpenConnection();
			} catch (SQLException e) {
				log.error("Se ha producido un error de transaccionalidad jdbc", e);
			}

          }
          else if (getTypeMessage(messageId).equals("NOTIFICACION PUSH"))
          {
            if (log.isDebugEnabled()) {
              log.debug("Enviamos la notificacion Push (dequeueMails)");
            }
            
            try {
				conn.setAutoCommit(false);
				sended = Integer.valueOf(postNotificacionPush(messageId));
				conn.commit();
	            reOpenConnection();
			} catch (SQLException e) {
				log.error("Se ha producido un error de transaccionalidad jdbc", e);
			}
          }
          if (sended.intValue() == 0)
          {
            if (log.isDebugEnabled()) {
              log.debug("Envio " + messageId + " no se ha enviado correctamente (dequeueMails)");
            }
          }
          else if (log.isDebugEnabled()) {
            log.debug("Envio " + messageId + " aparentemente correcto (dequeueMails)");
          }
        }
        else if (log.isDebugEnabled())
        {
          log.debug("Envio " + messageId + " ya fue enviado (dequeueMails)");
        }
      }
      else if (messageId == -1)
      {
        if (log.isDebugEnabled()) {
          log.debug("MessageId = -1, paramos el servicio (dequeueMails)");
        }
        this.stopService = Boolean.valueOf(true);
      }
    }
  }
  
  private int postSMS(int messageId)
  {
    Map<Integer, Servicio> mapServicios = null;
    Integer proveedorID = Integer.valueOf(0);
    ArrayList<SMSData> ListaSMSData = new ArrayList();
    ArrayList<HistoricoData> listHistoricoData = new ArrayList();
    try
    {
      if (log.isDebugEnabled()) {
        log.debug("Intentamos recuperar los datos del mail con id: " + messageId + " (postSMS)");
      }
      ListaSMSData = getSMSData(messageId);
      sleepProcess();
      for (SMSData smsData : ListaSMSData)
      {
        boolean sendOK = false;
        String resultSMS = "";
        if (smsData.Servers.isEmpty())
        {
          if (smsData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, 0, "SMS_ID: " + messageId + ". Error: No existe ningun Servidor Disponible", smsData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 2, 0, "SMS_ID: " + messageId + ". Error: No existe ningun Servidor Disponible", this.conn);
            sleepProcess();
          }
          jdbc.SetLogError("SendMailService.postSMS", 0, "Error: No existe ningun Servidor Disponible", this.conn);
          
          return proveedorID.intValue();
        }
        if (null != smsData.Body)
        {
          registerSMSDetailsDebug(smsData);
          
          int indice = 0;
          int numServidores = smsData.Servers.size();
          
          mapServicios = jdbc.findServicioByMessageId(Integer.valueOf(messageId), this.conn);
          
          if (log.isInfoEnabled()) {
            log.info("Numero de proveedores: " + numServidores);
          }
          if ("S".equals(this.ejecutarTodosProveedores)) {
            while ((indice < numServidores) && (!sendOK))
            {
              registerSMSParametersDebug(smsData, indice);
              proveedorID = Integer.valueOf(((ParametrosProveedor)smsData.Servers.get(indice)).getProveedorId());
              if (mapServicios.containsKey(proveedorID))
              {
                Servicio s = (Servicio)mapServicios.get(proveedorID);
                if (log.isDebugEnabled()) {
                  log.debug("Llamamos a Envio SMS (postSMS)");
                }
                try
                {
                  smsData.ServiceData.setHeaderSMS(s.getHeaderSMS());
                  
                  resultSMS = sendSMS(smsData, messageId, s, indice);
                  if (log.isInfoEnabled()) {
                    log.info("Respuesta: " + resultSMS);
                  }
                  if (resultSMS.contains("OK")) {
                    sendOK = true;
                  }
                }
                catch (Exception e)
                {
                  String errorMessage = e.getMessage();
                  log.error("Excepcion :" + errorMessage + " (postSMS)", e);
                  
                  jdbc.SetLogError("SendMailService.postSMS", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
                }
              }
              indice++;
            }
          } else {
          boolean encontrado = false;
          if (numServidores > 0)
          {
            if (mapServicios.size() > 0) {
              for (ParametrosProveedor pp : smsData.Servers)
              {
                if (mapServicios.containsKey(Integer.valueOf(pp.getProveedorId())))
                {
                  proveedorID = Integer.valueOf(pp.getProveedorId());
                  encontrado = true;
                  break;
                }
                indice++;
              }
            } else {
              proveedorID = Integer.valueOf(((ParametrosProveedor)smsData.Servers.get(indice)).getProveedorId());
            }
            if (encontrado) {
              registerSMSParametersDebug(smsData, indice);
            }
            if (log.isDebugEnabled()) {
              log.debug("Llamamos a Envio SMS (postSMS)");
            }
            try
            {
              Servicio s = (Servicio)mapServicios.get(proveedorID);
              
              smsData.ServiceData.setHeaderSMS(s.getHeaderSMS());
              
              resultSMS = sendSMS(smsData, messageId, s, indice);
              if (log.isInfoEnabled()) {
                log.info("Respuesta: " + resultSMS);
              }
              if (resultSMS.contains("OK")) {
                sendOK = true;
              }
            }
            catch (Exception e)
            {
              String errorMessage = e.getMessage();
              log.error("Excepcion :" + errorMessage + " (postSMS)", e);
              
              jdbc.SetLogError("SendMailService.postSMS", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
            }
          }
        }
        }
        if (sendOK)
        {
          if (log.isInfoEnabled()) {
            log.info("SMS numero " + messageId + " enviado (postSMS) - Version sendMail " + "1.3");
          }
          if (smsData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 1, proveedorID.intValue(), resultSMS, smsData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 1, proveedorID.intValue(), resultSMS, this.conn);
            sleepProcess();
          }
        }
        else if (smsData.esMultidestinatario)
        {
          listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, proveedorID.intValue(), resultSMS, smsData, this.conn));
          sleepProcess();
        }
        else
        {
          jdbc.SetMessageStatus(messageId, 2, proveedorID.intValue(), resultSMS, this.conn);
          sleepProcess();
        }
      }
      if ((null != listHistoricoData) && (listHistoricoData.size() > 0))
      {
        jdbc.actualizarEstadosMensajesSMS(listHistoricoData, this.conn);
        sleepProcess();
//        log.info("Proceso SMS, Antes de insertar en TBL_HISTORICOS y disparo del trigger, num insercciones :" + listHistoricoData.size());
//        jdbc.insertarHistoricos(listHistoricoData, this.conn);
//        sleepHistoryProcess();
//        log.info("Proceso SMS, Despues de insertar en TBL_HISTORICOS y disparo del trigger");
      }
    }
    catch (Exception e)
    {
      if (log.isInfoEnabled()) {
        log.info("postSMS Try Catch General: " + e.toString());
      }
      try
      {
        jdbc.SetLogError("SendMailService.postSMS", 0, "", this.conn);
      }
      catch (Exception e1)
      {
        if (log.isInfoEnabled()) {
          log.info("SendMailService.postSMS: " + e1.toString());
        }
      }
    }
    proveedorID = Integer.valueOf(0);
    return proveedorID.intValue();
  }

private void sleepProcess() {
	try {
	    TimeUnit.MILLISECONDS.sleep(TIME_TO_SLEEP_PROCESS);
	} catch (InterruptedException e) {
		log.error("Se ha producido un error al pausar la ejecucion del trigger de historicos" , e);
	}
}
  
private void sleepHistoryProcess() {
	try {
	    TimeUnit.MILLISECONDS.sleep(TIME_SLEEP_H_PROCESS);
	} catch (InterruptedException e) {
		log.error("Se ha producido un error al pausar la ejecucion del trigger de historicos" , e);
	}
}

  private int postRecepcionSMS(int messageId)
  {
    Integer receptorID = Integer.valueOf(0);
    ArrayList<ReceptorSMSData> listaReceptorSMSData = new ArrayList();
    ArrayList<HistoricoData> listHistoricoData = new ArrayList();
    try
    {
      listaReceptorSMSData = getRecepcionSMSData(messageId);
      sleepProcess();
      for (ReceptorSMSData smsData : listaReceptorSMSData)
      {
        if (log.isDebugEnabled()) {
          log.debug("Intentamos recuperar los datos del SMS con id: " + messageId + " destinatariosMensajeId:" + smsData.destinatarioMensajeId + " (postRecepcionSMS)");
        }
        boolean sendOK = false;
        String resultSMS = "";
        if (smsData.Servers.isEmpty())
        {
          if (smsData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, 0, "SMS_ID: " + messageId + ". Error: No existe ningun Receptor Disponible", smsData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 2, 0, "SMS_ID: " + messageId + ". Error: No existe ningun Receptor Disponible", this.conn);
            sleepProcess();
          }
          jdbc.SetLogError("SendMailService.postRecepcionSMS", 0, "Error: No existe ningun Receptor Disponible", this.conn);
          
          return receptorID.intValue();
        }
        if (null != smsData.Body)
        {
          registerReceptorSMSDetailsDebug(smsData);
          
          int indice = 0;
          int numReceptores = smsData.Servers.size();
          if (log.isInfoEnabled()) {
            log.info("Numero de receptores: " + numReceptores);
          }
          if ("S".equals(this.ejecutarTodosReceptores)) {
            while ((indice < numReceptores) && (!sendOK))
            {
              registerReceptorSMSParametersDebug(smsData, indice);
              
              receptorID = Integer.valueOf(((ParametrosReceptor)smsData.Servers.get(indice)).getReceptorId());
              if (log.isDebugEnabled()) {
                log.debug("Llamamos a Envio SMS (postRecepcionSMS)");
              }
              try
              {
                resultSMS = sendReceptorSMS(smsData, messageId, indice);
                if (log.isInfoEnabled()) {
                  log.info("Respuesta: " + resultSMS);
                }
                if (resultSMS.contains("OK")) {
                  sendOK = true;
                }
              }
              catch (Exception e)
              {
                String errorMessage = e.getMessage();
                log.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
                
                jdbc.SetLogError("SendMailService.postRecepcionSMS", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
              }
              indice++;
            }
          } else {
          if (numReceptores > 0)
          {
            registerReceptorSMSParametersDebug(smsData, indice);
            
            receptorID = Integer.valueOf(((ParametrosReceptor)smsData.Servers.get(indice)).getReceptorId());
            if (log.isDebugEnabled()) {
              log.debug("Llamamos a Envio SMS (postRecepcionSMS)");
            }
            try
            {
              resultSMS = sendReceptorSMS(smsData, messageId, indice);
              if (log.isInfoEnabled()) {
                log.info("Respuesta: " + resultSMS);
              }
              if (resultSMS.contains("OK")) {
                sendOK = true;
              }
            }
            catch (Exception e)
            {
              String errorMessage = e.getMessage();
              log.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
              
              jdbc.SetLogError("SendMailService.postRecepcionSMS", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
            }
          }
        }
        }
        if (sendOK)
        {
          if (log.isInfoEnabled()) {
            log.info("SMS numero " + messageId + " enviado a aplicacion (postRecepcionSMS) - Version sendMail " + "1.3");
          }
          if (smsData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 1, receptorID.intValue(), resultSMS, smsData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 1, receptorID.intValue(), resultSMS, this.conn);
            sleepProcess();
          }
        }
        else if (smsData.esMultidestinatario)
        {
          listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, receptorID.intValue(), resultSMS, smsData, this.conn));
          sleepProcess();
        }
        else
        {
          jdbc.SetMessageStatus(messageId, 2, receptorID.intValue(), resultSMS, this.conn);
          sleepProcess();
        }
      }
      if ((null != listHistoricoData) && (listHistoricoData.size() > 0))
      {
        jdbc.actualizarEstadosMensajesSMS(listHistoricoData, this.conn);
        sleepProcess();
//        log.info("Recepcion SMS, Antes de insertar en TBL_HISTORICOS y disparo del trigger, num insercciones :" + listHistoricoData.size());
//        jdbc.insertarHistoricos(listHistoricoData, this.conn);
//        sleepHistoryProcess();
//        log.info("Recepcion SMS, Despues de insertar en TBL_HISTORICOS y disparo del trigger");
      }
    }
    catch (Exception e)
    {
      if (log.isInfoEnabled()) {
        log.info("postRecepcionSMS Try Catch General: " + e.toString());
      }
      try
      {
        jdbc.SetLogError("SendMailService.postRecepcionSMS", 0, "", this.conn);
      }
      catch (Exception e1)
      {
        if (log.isInfoEnabled()) {
          log.info("SendMailService.postRecepcionSMS: " + e1.toString());
        }
      }
    }
    receptorID = Integer.valueOf(0);
    return receptorID.intValue();
  }
  
  private int postNotificacionPush(int messageId)
  {
    Integer servidorPushID = Integer.valueOf(0);
    ArrayList<NotificacionPushData> listaNotificacionPushData = new ArrayList();
    ArrayList<HistoricoData> listHistoricoData = new ArrayList();
    try
    {
      reOpenConnection();
      if (log.isDebugEnabled()) {
        log.debug("Intentamos recuperar los datos de la notificacion Push con id: " + messageId + " (postNotificacionPush)");
      }
      listaNotificacionPushData = getNotificacionPushData(messageId);
      sleepProcess();
      if ((listaNotificacionPushData != null) && (listaNotificacionPushData.size() > 0)) {
        log.info("getNotificacionPushData distinto a null y mayor que 0");
      } else {
        log.info("getNotificacionPushData null o vacio");
      }
      for (NotificacionPushData notificacionPushData : listaNotificacionPushData)
      {
        boolean sendOK = false;
        String resultNotificacionPush = "";
        if (notificacionPushData.servers.isEmpty())
        {
          if (notificacionPushData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, 0, "MESSAGE_ID: " + messageId + ". Error: No existe ningun Servidor Push Disponible", notificacionPushData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 2, 0, "MESSAGE_ID: " + messageId + ". Error: No existe ningun Servidor Push Disponible", this.conn);
            sleepProcess();
          }
          jdbc.SetLogError("SendMailService.postNotificacionPush", 0, "Error: No existe ningun Servidor Push Disponible", this.conn);
        }
        else
        {
          registerServidorPushDetailsDebug(notificacionPushData);
          
          int indice = 0;
          int numServidoresPush = notificacionPushData.servers.size();
          if (log.isInfoEnabled()) {
            log.info("Numero de servidores Push: " + numServidoresPush);
          }
          if ("S".equals(this.EjecutarTodosServidoresPush)) {
            while ((indice < numServidoresPush) && (!sendOK))
            {
              registerServidorPushParametersDebug(notificacionPushData, indice);
              
              servidorPushID = Integer.valueOf(((ParametrosServidorPush)notificacionPushData.servers.get(indice)).getServidorPushId());
              if (log.isDebugEnabled()) {
                log.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
              }
              try
              {
                resultNotificacionPush = sendServidorPushByMISIM(notificacionPushData, messageId, indice);
                if (log.isInfoEnabled()) {
                  log.info("Respuesta: " + resultNotificacionPush);
                }
                if (resultNotificacionPush.contains("OK")) {
                  sendOK = true;
                }
              }
              catch (Exception e)
              {
                String errorMessage = e.getMessage();
                log.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
                
                jdbc.SetLogError("SendMailService.postNotificacionPush", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
              }
              indice++;
            }
          } else {
          if (numServidoresPush > 0)
          {
            registerServidorPushParametersDebug(notificacionPushData, indice);
            
            servidorPushID = Integer.valueOf(((ParametrosServidorPush)notificacionPushData.servers.get(indice)).getServidorPushId());
            if (log.isDebugEnabled()) {
              log.debug("Llamamos a Envio Notificacion Push (postNotificacionPush)");
            }
            try
            {
              resultNotificacionPush = sendServidorPushByMISIM(notificacionPushData, messageId, indice);
              if (log.isInfoEnabled()) {
                log.info("Respuesta: " + resultNotificacionPush);
              }
              if (resultNotificacionPush.contains("OK")) {
                sendOK = true;
              }
            }
            catch (Exception e)
            {
              String errorMessage = e.getMessage();
              log.error("Excepcion :" + errorMessage + " (postRecepcionSMS)", e);
              
              jdbc.SetLogError("SendMailService.postNotificacionPush", 0, "SMS_ID: " + messageId + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
            }
          }
        }
        }
        if (sendOK)
        {
          if (log.isInfoEnabled()) {
            log.info("Notificacion numero " + messageId + " enviado (postNotificacionPush) - Version SendMail " + "1.3");
          }
          if (notificacionPushData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 1, servidorPushID.intValue(), resultNotificacionPush, notificacionPushData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(messageId, 1, servidorPushID.intValue(), resultNotificacionPush, this.conn);
            sleepProcess();
          }
        }
        else if (notificacionPushData.esMultidestinatario)
        {
          listHistoricoData.add(jdbc.setMessageStatusMult(messageId, 2, servidorPushID.intValue(), resultNotificacionPush, notificacionPushData, this.conn));
          sleepProcess();
        }
        else
        {
          jdbc.SetMessageStatus(messageId, 2, servidorPushID.intValue(), resultNotificacionPush, this.conn);
          sleepProcess();
        }
      }
      if ((null != listHistoricoData) && (listHistoricoData.size() > 0))
      {
        jdbc.actualizarEstadosMensajesSMS(listHistoricoData, this.conn);
        sleepProcess();
//        log.info("Notificacion PUSH, Antes de insertar en TBL_HISTORICOS y disparo del trigger, num insercciones :" + listHistoricoData.size());
//        jdbc.insertarHistoricos(listHistoricoData, this.conn);
//        sleepHistoryProcess();
//        log.info("Notificacion PUSH, Despues de insertar en TBL_HISTORICOS y disparo del trigger");

      }
    }
    catch (Exception e)
    {
      if (log.isInfoEnabled()) {
        log.info("postNotificacionPush Try Catch General: " + e.toString());
      }
      try
      {
        jdbc.SetLogError("SendMailService.postNotificacionPush", 0, "", this.conn);
      }
      catch (Exception e1)
      {
        if (log.isInfoEnabled()) {
          log.info("SendMailService.postNotificacionPush: " + e1.toString());
        }
      }
    }
    return servidorPushID.intValue();
  }
  
  public int postMail(Integer mailID)
  {
    String errorMessage = "";
    Integer servidorID = Integer.valueOf(0);
    ArrayList<MailData> listaMailData = new ArrayList();
    ArrayList<HistoricoData> listHistoricoData = new ArrayList();
    boolean sendOK = false;
    try
    {
      if (log.isDebugEnabled()) {
        log.debug("Intentamos recuperar los datos del mail con id: " + mailID + " (postMail)");
      }
      listaMailData = getMailData(mailID.intValue());
      sleepProcess();
      for (MailData mailData : listaMailData)
      {
        sendOK = false;
        if (mailData.Servers.isEmpty())
        {
          if (mailData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(mailID.intValue(), 2, 0, "MAIL_ID: " + mailID + ". Error: No existe ningun Servidor Disponible", mailData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(mailID.intValue(), 2, 0, "MAIL_ID: " + mailID + ". Error: No existe ningun Servidor Disponible", this.conn);
            sleepProcess();
          }
          jdbc.SetLogError("SendMailService.postMail", 0, "Error: No existe ningun Servidor Disponible", this.conn);
          sleepProcess();
          
          return servidorID.intValue();
        }
        if ((!mailData.Subject.isEmpty()) || (!mailData.Body.isEmpty()))
        {
          registerMailDetailsDebug(mailData);
          
          int indice = 0;
          int numServidores = mailData.Servers.size();
          if (log.isInfoEnabled()) {
            log.info("Numero de servidores: " + numServidores);
          }
          if ("S".equals(this.ejecutarTodosServidores)) {
            while ((indice < numServidores) && (!sendOK))
            {
              registerMailParametersDebug(mailData, indice);
              servidorID = Integer.valueOf(((ParametrosServidor)mailData.Servers.get(indice)).getServidor());
              if (log.isDebugEnabled()) {
                log.debug("Llamamos a sendMail (postMail)");
              }
              try
              {
                sendMail(mailData, indice);
                sendOK = true;
              }
              catch (Exception e)
              {
                sendOK = false;
                errorMessage = e.getMessage();
                Exception ex = e;
                if ((ex instanceof SMTPAddressFailedException))
                {
                  SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)ex;
                  errorMessage = errorMessage + ". Address: " + smtpEx.getAddress() + ". Command failed: " + smtpEx.getCommand() + ". Reason for the failure: " + smtpEx.getReturnCode() + ". Error: " + smtpEx.getMessage();
                }
                else if ((ex instanceof SendFailedException))
                {
                  SendFailedException sfex = (SendFailedException)ex;
                  Address[] invalid = sfex.getInvalidAddresses();
                  if (invalid.length <= 0) {
                    errorMessage = errorMessage + ". Causa: " + sfex.getCause();
                  } else {
                    errorMessage = errorMessage + ". Mail: " + invalid[0].toString() + " Causa: " + sfex.getCause();
                  }
                }
                log.error("Excepcion :" + errorMessage + " (postMail)", e);
                
                jdbc.SetLogError("SendMailService.postMail", 0, "MAIL_ID: " + mailID + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
                
                sendOK = false;
                
                indice++;
              }
            }
		} else { // SOLO PROBAMOS EL ENVIO DEL EMAIL EN EL
				// PRIMER SERVIDOR RECUPERADO
          if (numServidores > 0)
          {
            registerMailParametersDebug(mailData, indice);
            servidorID = Integer.valueOf(((ParametrosServidor)mailData.Servers.get(indice)).getServidor());
            if (log.isDebugEnabled()) {
              log.debug("Llamamos a sendMail (postMail)");
            }
            try
            {
              sendMail(mailData, indice);
              sendOK = true;
            }
            catch (Exception e)
            {
              sendOK = false;
              errorMessage = e.getMessage();
              Exception ex = e;
              if ((ex instanceof SMTPAddressFailedException))
              {
                SMTPAddressFailedException smtpEx = (SMTPAddressFailedException)ex;
                errorMessage = errorMessage + ". Address: " + smtpEx.getAddress() + ". Command failed: " + smtpEx.getCommand() + ". Reason for the failure: " + smtpEx.getReturnCode() + ". Error: " + smtpEx.getMessage();
              }
              else if ((ex instanceof SendFailedException))
              {
                SendFailedException sfex = (SendFailedException)ex;
                Address[] invalid = sfex.getInvalidAddresses();
                if (invalid.length <= 0) {
                  errorMessage = errorMessage + ". Causa: " + sfex.getCause();
                } else {
                  errorMessage = errorMessage + ". Mail: " + invalid[0].toString() + " Causa: " + sfex.getCause();
                }
              }
              log.error("Excepcion :" + errorMessage + " (postMail)", e);
              
              jdbc.SetLogError("SendMailService.postMail", 0, "MAIL_ID: " + mailID + ". Error: (" + e.hashCode() + ") " + errorMessage, this.conn);
              sendOK = false;
            }
          }
        }
	  }
	// MANDAR MENSAJE COMO QUE SE HA ENVIADO CORRECTAMENTE O QUE
	// HA HABIDO UN ERROR
        if (sendOK)
        {
          if (log.isInfoEnabled()) {
            log.info("Mail numero " + mailID + " enviado (postMail) - Version sendMail " + "1.3");
          }
          if (mailData.esMultidestinatario) {
            listHistoricoData.add(jdbc.setMessageStatusMult(mailID.intValue(), 1, servidorID.intValue(), "Mensaje Enviado Correctamente", mailData, this.conn));
            sleepProcess();
          } else {
            jdbc.SetMessageStatus(mailID.intValue(), 1, servidorID.intValue(), "Mensaje Enviado Correctamente", this.conn);
            sleepProcess();
          }
        }
        else if (mailData.esMultidestinatario)
        {
          listHistoricoData.add(jdbc.setMessageStatusMult(mailID.intValue(), 2, servidorID.intValue(), "MAIL_ID: " + mailID + "; Error: Se ha producido un error en el envio del Mensaje. Detalle del Error: " + errorMessage.toString(), mailData, this.conn));
          sleepProcess();
        }
        else
        {
          jdbc.SetMessageStatus(mailID.intValue(), 2, servidorID.intValue(), "MAIL_ID: " + mailID + "; Error: Se ha producido un error en el envio del Mensaje. Detalle del Error: " + errorMessage.toString(), this.conn);
          sleepProcess();
        }
      }
      if ((null != listHistoricoData) && (listHistoricoData.size() > 0))
      {
        jdbc.actualizarEstadosMensajesSMS(listHistoricoData, this.conn);
        sleepProcess();
//        log.info("Email, Antes de insertar en TBL_HISTORICOS y disparo del trigger, num insercciones :" + listHistoricoData.size());
//        jdbc.insertarHistoricos(listHistoricoData, this.conn);
//        sleepHistoryProcess();
//        log.info("Email, Despues de insertar en TBL_HISTORICOS y disparo del trigger");

      }
    }
    catch (Exception e)
    {
      if (log.isInfoEnabled()) {
        log.info("postMail Try Catch General: " + e.toString());
      }
      try
      {
        jdbc.SetLogError("SendMailService.postMail", 0, "", this.conn);
      }
      catch (Exception e1)
      {
        if (log.isInfoEnabled()) {
          log.info("SendMailService.postMail: " + e1.toString());
        }
      }
    }
    servidorID = Integer.valueOf(0);
    return servidorID.intValue();
  }
  
  public void sendMail(MailData mailData, int indice)
    throws Exception
  {
    boolean debug = false;
    
    Properties props = new Properties();
    
    ParametrosServidor ps = (ParametrosServidor)mailData.Servers.get(indice);
    
    props.put("mail.smtp.host", ps.getIP().toString());
    if (log.isDebugEnabled()) {
      log.debug("Con el Servidor: " + ps.getIP().toString() + " (SendMail)");
    }
    if (ps.getPuerto() != "") {
      props.put("mail.smtp.port", ps.getPuerto());
    } else {
      props.put("mail.smtp.port", Integer.valueOf(25));
    }
    Session session = null;
    Authenticator auth = null;
    if (ps.getTimeOut() != "")
    {
      int timeout = Integer.parseInt(ps.getTimeOut()) * 1000;
      if (log.isDebugEnabled()) {
        log.debug("Con Tiempo de Espera " + ps.getTimeOut() + " milisegundos(SendMail)");
      }
      props.put("mail.smtp.connectiontimeout", Integer.valueOf(timeout));
      props.put("mail.smtp.timeout", Integer.valueOf(timeout));
    }
    if ((ps.getConexion() != null) && (ps.getConexion().equals("true")))
    {
      if (log.isDebugEnabled()) {
        log.debug("Con conexion segura (SendMail)");
      }
      props.setProperty("mail.smtp.starttls.enable", "true");
    }
    if ((ps.getAutentificacion() != null) && (ps.getAutentificacion().equals("true")))
    {
      if (log.isDebugEnabled()) {
        log.debug("Con autenticacion basica (SendMail)");
      }
      props.put("mail.smtp.auth", Boolean.valueOf(true));
      auth = new SMTPAuthenticator(ps.getUsuario(), ps.getContrasena());
      session = Session.getInstance(props, auth);
    }
    else
    {
      if (log.isDebugEnabled()) {
        log.debug("Sin autenticacion basica (SendMail)");
      }
      props.put("mail.smtp.auth", Boolean.valueOf(false));
      session = Session.getInstance(props, null);
    }
    session.setDebug(debug);
    
    MimeMessage msg = new MimeMessage(session);
    
    InternetAddress addressFrom = null;
    if (mailData.ServiceData.getFromMailNombre() != null)
    {
      addressFrom = new InternetAddress(mailData.ServiceData.getFromMail(), mailData.ServiceData.getFromMailNombre());
      
      msg.setFrom(addressFrom);
    }
    else
    {
      addressFrom = new InternetAddress(mailData.ServiceData.getFromMail());
      
      msg.setFrom(addressFrom);
    }
    InternetAddress[] addressTo = generateInternetAddress(mailData.Recipients.To);
    InternetAddress[] addressCC = generateInternetAddress(mailData.Recipients.Cc);
    InternetAddress[] addressBCC = generateInternetAddress(mailData.Recipients.Bcc);
    
    msg.setRecipients(Message.RecipientType.TO, addressTo);
    msg.setRecipients(Message.RecipientType.CC, addressCC);
    msg.setRecipients(Message.RecipientType.BCC, addressBCC);
    if (log.isDebugEnabled())
    {
      log.debug("-----------------------------------");
      log.debug("Subject (SendMail)");
      log.debug(mailData.Subject);
      log.debug("-----------------------------------");
      log.debug("Body (SendMail)");
      log.debug(mailData.Body + " (SendMail)");
      log.debug("-----------------------------------");
    }
    msg.setSubject(mailData.Subject, mailData.TipoCodificacion);
    
    Multipart multipart = null;
    if (mailData.Images.size() > 0) {
      multipart = new MimeMultipart("related");
    } else {
      multipart = new MimeMultipart();
    }
    MimeBodyPart cuerpo = new MimeBodyPart();
    String body = updateBody(mailData.Body, multipart);
    String typeContent = mailData.TipoCuerpo + "; charset=" + mailData.TipoCodificacion;
    
    cuerpo.setContent(body, typeContent);
    multipart.addBodyPart(cuerpo);
    if (log.isDebugEnabled())
    {
      log.debug("-----------------------------------------");
      log.debug("Adjuntos: " + mailData.Attachments.size());
    }
    if (mailData.Attachments.size() > 0) {
      addAttachToMail(mailData.Attachments, multipart);
    }
    if (mailData.Images.size() > 0) {
      addImagesToMail(mailData.Images, multipart);
    }
    msg.setContent(multipart);
    if (log.isDebugEnabled()) {
      log.debug("Realizamos el envio desde mail.jar (SendMail)");
    }
    Transport.send(msg);
  }
  
  private String sendSMS(SMSData smsData, int messageId, Servicio s, int indice)
    throws Exception
  {
    try
    {
      ParametrosProveedor pp = (ParametrosProveedor)smsData.Servers.get(indice);
      
      Proveedor p = jdbc.findProveedor(Integer.valueOf(pp.getProveedorId()), this.conn);
      if (p != null)
      {
        log.debug("Recuperado proveedor: " + p.getServidorid());
      }
      else
      {
        log.debug("No encontrado proveedor con id: " + pp.getProveedorId());
        
        return null;
      }
      String usuario = s.getProveedorUsuarioSMS();
      String password = s.getProveedorPassSMS();
      String producto = "SMS";
      String proveedor = p.getNombre();
      
      String SMS_ID = "" + messageId;
      String SMS_HEADER = smsData.ServiceData.getHeaderSMS();
      String SMS_DESTINATARIO = smsData.Telefono;
      String SMS_TEXTO = smsData.Body;
      
      es.redsara.misim.misim_bus_webapp.enviarmensaje.respuesta.Respuesta r1 = EnviarMensajeService.enviarMensaje(this.MISIM_USUARIO, this.MISIM_PASSWORD, producto, proveedor, SMS_ID, usuario, password, SMS_ID, SMS_DESTINATARIO, SMS_HEADER, SMS_TEXTO, p.getUrlDestino());
      
      log.debug("Recibida respuesta MISIM: " + r1);
      
      String respuesta = r1.getStatus().getStatusText();
      if (respuesta.contains("OK")) {
        jdbc.mensajeSetMetodoConsulta(messageId, p.getMetodoConsulta().intValue(), this.conn);
        sleepProcess();
      }
      return respuesta;
    }
    catch (Exception ex)
    {
      log.error("No se ha podido abrir la conexion: " + ex.getMessage(), ex);
    }
    return "";
  }
  
  private String sendReceptorSMS(ReceptorSMSData smsData, int messageId, int indice)
    throws Exception
  {
    ParametrosReceptor ps = (ParametrosReceptor)smsData.Servers.get(indice);
    
    Aplicacion a = jdbc.findAplicacionByMessageId(Integer.valueOf(messageId), this.conn);
    sleepProcess();
    
    EnvioAplicacionRequest envioAplicacionRequest = new EnvioAplicacionRequest();
    
    envioAplicacionRequest.setUser(smsData.User);
    envioAplicacionRequest.setPassword(smsData.Password);
    envioAplicacionRequest.setSender(smsData.Telefono);
    envioAplicacionRequest.setRecipient(smsData.HeaderSMS);
    envioAplicacionRequest.setSMSText(smsData.Body);
    envioAplicacionRequest.setLoteId(smsData.LoteEnvioId);
    envioAplicacionRequest.setMessageId(Integer.toString(messageId));
    
    String proveedor = a.getNombre();
    try
    {
      es.redsara.misim.misim_bus_webapp.recpecionsms.respuesta.Respuesta r1 = RecibirSmsService.recpecionsms(this.MISIM_USUARIO, this.MISIM_PASSWORD, proveedor, "" + messageId, envioAplicacionRequest);
      
      log.debug("Recibida respuesta MISIM: " + r1);
      return r1.getStatus().getStatusText();
    }
    catch (Exception e)
    {
      log.error("Error en sendReceptorSMS", e);
    }
    return "";
  }
  
  private String sendServidorPushByMISIM(NotificacionPushData messageData, int messageId, int indice)
    throws Exception
  {
    String resultado = "";
    messageData.setIndice("" + indice);
    messageData.setMessageID("" + messageId);
    String proveedor = "";
    
    NotificacionDataRequest ndr = new NotificacionDataRequest();
    
    ParametrosServidorPush ps = (ParametrosServidorPush)messageData.servers.get(indice);
    if ((null != ps) && (ps.getPlataformaId() == 1))
    {
      if ((null != messageData.tokensUsuario) && (!messageData.tokensUsuario.isEmpty()))
      {
        proveedor = "Google PUSH";
        ndr.setgCMApiKey(messageData.gCMApiKey);
        
        ndr.setBadge(messageData.badge);
      }
      else
      {
        resultado = "KO | El username no esta dado de alta en la plataforma Android";
      }
    }
    else if (ps.getPlataformaId() == 2) {
      if ((null != messageData.tokensUsuario) && (!messageData.tokensUsuario.isEmpty()))
      {
        proveedor = "Apple PUSH";
        ndr.setRutaCertificadoAPNS(messageData.rutaCertificadoAPNS);
        ndr.setPasswordCertificadoAPNS(messageData.passwordCertificadoAPNS);
        ndr.setBadge(messageData.badge);
        ndr.setPuertoUrl(ps.getPuertoUrl() + "");
        ndr.setUrlFeedback(ps.getUrlFeedback() + "");
        ndr.setPuertoUrlFeedback(ps.getPuertoUrlFeedback() + "");
      }
      else
      {
        resultado = "KO | El username no esta dado de alta en la plataforma iOS";
      }
    }
    if (!resultado.equals("")) {
      return resultado;
    }
    ndr.setToken(messageData.tokensUsuario);
    ndr.setUrl(ps.getUrl());
    ndr.setCabecera(messageData.cabecera);
    ndr.setCuerpo(messageData.cuerpo);
    
    String producto = "PUSH";
    
    es.redsara.misim.misim_bus_webapp.enviarnotificacion.respuesta.Respuesta r1 = EnviarNotificacionService.enviarNotificacion(this.MISIM_USUARIO, this.MISIM_PASSWORD, proveedor, producto, messageId, ndr);
    
    log.debug("Recibida respuesta MISIM: " + r1);
    
    return r1.getStatus().getStatusText();
  }
  
  private void addAttachToMail(ArrayList<Adjunto> attachs, Multipart multipart)
    throws FileNotFoundException, SQLException, IOException, MessagingException
  {
    for (int indice = 0; indice < attachs.size(); indice++)
    {
      FileOutputStream fos = null;
      File file = new File("Temp/File" + indice);
      fos = new FileOutputStream(file);
      MimeBodyPart adjunto = new MimeBodyPart();
      Blob bin = ((Adjunto)attachs.get(indice)).getContenido();
      InputStream input = bin.getBinaryStream();
      
      int size = (int)bin.length();
      byte[] buffer = new byte[size];
      int length = -1;
      while ((length = input.read(buffer)) != -1) {
        fos.write(buffer, 0, length);
      }
      if (fos != null) {
        fos.close();
      }
      input.close();
      
      DataSource source1 = new FileDataSource(file);
      adjunto.setDataHandler(new DataHandler(source1));
      adjunto.setFileName(((Adjunto)attachs.get(indice)).getNombre());
      multipart.addBodyPart(adjunto);
      if (log.isDebugEnabled()) {
        log.debug("Se ha adjuntado el documento " + ((Adjunto)attachs.get(indice)).getNombre() + " en el HTML (SendMail)");
      }
    }
  }
  
  private void addImagesToMail(ArrayList<Adjunto> images, Multipart multipart)
    throws FileNotFoundException, SQLException, IOException, MessagingException
  {
    for (int indice = 0; indice < images.size(); indice++)
    {
      FileOutputStream fos = null;
      File file = new File("Temp/File" + indice);
      fos = new FileOutputStream(file);
      Blob bin = ((Adjunto)images.get(indice)).getContenido();
      InputStream input = bin.getBinaryStream();
      
      int size = (int)bin.length();
      byte[] buffer = new byte[size];
      int length = -1;
      while ((length = input.read(buffer)) != -1) {
        fos.write(buffer, 0, length);
      }
      if (fos != null) {
        fos.close();
      }
      input.close();
      
      MimeBodyPart imagen = new MimeBodyPart();
      DataSource source1 = new FileDataSource(file);
      imagen.setDataHandler(new DataHandler(source1));
      imagen.setHeader("Content-ID", "<" + ((Adjunto)images.get(indice)).getNombre() + ">");
      
      multipart.addBodyPart(imagen);
      if (log.isInfoEnabled()) {
        log.info("Imagen Embebida " + ((Adjunto)images.get(indice)).getNombre() + " se ha anadido (SendMail)");
      }
    }
  }
  
  private String updateBody(String body, Multipart multipart)
    throws IOException, MessagingException
  {
    String newBody = "";
    String[] partImages = body.split("<img");
    int cont = 0;
    for (String part : partImages)
    {
      if (cont > 0)
      {
        String img = "<img" + part.substring(0, part.split(">")[0].length() + 1);
        try
        {
          String base64 = img.split("src=\"data:image")[1].split(",")[1].split("\"")[0];
          
          byte[] bytes = Base64.decodeBase64(base64.getBytes());
          FileOutputStream fos = null;
          File file = new File("Temp/File" + cont);
          fos = new FileOutputStream(file);
          int size = bytes.length;
          fos.write(bytes, 0, size);
          if (fos != null) {
            fos.close();
          }
          MimeBodyPart imagen = new MimeBodyPart();
          DataSource source1 = new FileDataSource(file);
          imagen.setDataHandler(new DataHandler(source1));
          imagen.setHeader("Content-ID", "<imagen00" + cont + ">");
          multipart.addBodyPart(imagen);
          
          String texto = img.split("src=")[1].split("\"")[1];
          newBody = newBody + "<img" + part.replace(texto, new StringBuilder().append("cid:imagen00").append(cont).toString());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
          newBody = newBody + "<img" + part;
        }
      }
      else
      {
        newBody = part;
      }
      cont++;
    }
    if (!newBody.isEmpty()) {
      return newBody;
    }
    return body;
  }
  
  private InternetAddress[] generateInternetAddress(ArrayList<DestinatarioDMensaje> recipients)
    throws AddressException
  {
    InternetAddress[] address = new InternetAddress[recipients.size()];
    for (int i = 0; i < recipients.size(); i++) {
      address[i] = new InternetAddress(((DestinatarioDMensaje)recipients.get(i)).email);
    }
    return address;
  }
  
  private String convertRecipientsToString(ArrayList<DestinatarioDMensaje> recipients)
  {
    String correos = "";
    for (int i = 0; i < recipients.size(); i++) {
      if (i == 0) {
        correos = correos + ((DestinatarioDMensaje)recipients.get(i)).email;
      } else {
        correos = correos + ", " + ((DestinatarioDMensaje)recipients.get(i)).email;
      }
    }
    return correos;
  }
  
  private void openConnection()
    throws InterruptedException
  {
    boolean existeConexion = false;
    while (!existeConexion) {
      try
      {
        if (null == this.conn) {
          this.conn = DriverManager.getConnection(this.connectionString, this.bbddUser, this.bbddPassword);
        }
        log.debug("Se abre conexion.");
        
        existeConexion = true;
      }
      catch (Exception ex)
      {
        if (log.isInfoEnabled()) {
          log.info("No se ha podido abrir la conexion: " + ex.getMessage());
        }
        Thread.sleep(this.maxSecondsRetryBBDD.intValue() * 1000);
      }
    }
  }
  
  private void reOpenConnection()
    throws InterruptedException
  {
    boolean conexionReabierta = false;
    while (!conexionReabierta)
    {
      if (null != this.conn) {
        try
        {
          this.conn.close();
          log.debug("Se cierra conexion.");
        }
        catch (Exception ex)
        {
          if (log.isInfoEnabled()) {
            log.info("No se ha podido cerrar la conexion: " + ex.getMessage());
          }
        }
        finally
        {
          this.conn = null;
        }
      }
      openConnection();
      conexionReabierta = true;
    }
  }
  
  private int getNextMessageId()
    throws InterruptedException
  {
    int messageId = 0;
    while (messageId == 0) {
      try
      {
        messageId = jdbc.DequeueMail(this.conn).intValue();
        sleepProcess();
        if (0 != messageId)
        {
          if (log.isInfoEnabled()) {
            log.info("Mensaje numero " + String.valueOf(messageId));
          }
        }
        else if (log.isInfoEnabled()) {
          log.info("No hay mensajes disponibles para su envio");
        }
        if (messageId > 0)
        {
          this.firstTime = Boolean.valueOf(false);
          return messageId;
        }
        if ((messageId == -1) && (this.firstTime.booleanValue()))
        {
          if (log.isInfoEnabled()) {
            log.info("No se puede parar el server hasta que se obtenga un mensaje de la cola");
          }
          messageId = 0;
        }
      }
      catch (SQLException e)
      {
        log.error("[SQLException] Error.DequeueMail", e);
        reOpenConnection();
      }
    }
    return messageId;
  }
  
  private boolean isMessagePending(int messageId)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetStatusMessage(Integer.valueOf(messageId), this.conn);
        
      }
      catch (SQLException ex)
      {
        if (log.isDebugEnabled()) {
          log.debug("Error GetStatusMessage: " + ex.getMessage());
        }
        reOpenConnection();
      }
    }
  }
  
  private ArrayList<MailData> getMailData(int mailID)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetMailData(mailID, this.conn);
      }
      catch (SQLException e)
      {
        log.error("Error getting mail data: " + e.getMessage(), e);
        reOpenConnection();
      }
    }
  }
  
  private ArrayList<SMSData> getSMSData(int mailID)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetSMSData(mailID, this.conn);
      }
      catch (SQLException e)
      {
        log.error("Error getting SMS data: " + e.getMessage(), e);
        reOpenConnection();
      }
    }
  }
  
  private ArrayList<ReceptorSMSData> getRecepcionSMSData(int mailID)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetRecepcionSMSData(mailID, this.conn);
      }
      catch (SQLException e)
      {
        log.error("Error getRecepcionSMSData: " + e.getMessage(), e);
        reOpenConnection();
      }
    }
  }
  
  private ArrayList<NotificacionPushData> getNotificacionPushData(int mailID)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetNotificacionPushData(mailID, this.conn);
      }
      catch (SQLException e)
      {
        log.error("Error getNotificacionPushData: " + e.getMessage(), e);
        reOpenConnection();
      }
    }
  }
  
  private String getTypeMessage(int messageId)
    throws InterruptedException
  {
    for (;;)
    {
      try
      {
        return jdbc.GetTypeMessage(messageId, this.conn);
      }
      catch (SQLException e)
      {
        log.error("Error getTypeMessage: " + e.getMessage(), e);
        reOpenConnection();
      }
    }
  }
  
  private void registerMailDetailsDebug(MailData mailData)
  {
    if (log.isDebugEnabled())
    {
      String correosTO = convertRecipientsToString(mailData.Recipients.To);
      String correosCC = convertRecipientsToString(mailData.Recipients.Cc);
      String correosBCC = convertRecipientsToString(mailData.Recipients.Bcc);
      
      log.debug("-----------------------------------");
      log.debug("DATOS DE ENVIO DE CORREO (postMail)");
      log.debug("-----------------------------------");
      log.debug("RecipientsTO: " + correosTO + " (postMail)");
      log.debug("RecipientsCC: " + correosCC + " (postMail)");
      log.debug("RecipientsBCC: " + correosBCC + " (postMail)");
      log.debug("Subject: " + mailData.Subject + " (postMail)");
    }
  }
  
  private void registerMailParametersDebug(MailData mailData, int indice)
  {
    String mailFrom = mailData.ServiceData.getFromMail();
    ParametrosServidor ps = (ParametrosServidor)mailData.Servers.get(indice);
    String smtpServer = "";
    String smtpUserName = "";
    String smtpPassword = "";
    String smtpPort = "";
    if (ps.getIP() != null) {
      smtpServer = ps.getIP();
    }
    if (ps.getUsuario() != null) {
      smtpUserName = ps.getUsuario();
    }
    if (ps.getContrasena() != null) {
      smtpPassword = ps.getContrasena();
    }
    if (ps.getPuerto() != null) {
      smtpPort = ps.getPuerto();
    }
    if (log.isDebugEnabled())
    {
      log.debug("----------------------------");
      log.debug("DATOS DEL SERVIDOR (postMail)");
      log.debug("----------------------------");
      log.debug("Smtp server: " + smtpServer + " (postMail)");
      log.debug("Sender: " + mailFrom + " (postMail)");
      if (smtpUserName != "") {
        log.debug("Smtp user name:" + smtpUserName + " (postMail)");
      }
      if (smtpPassword != "") {
        log.debug("Smpt password: " + smtpPassword + " (postMail)");
      }
      if (smtpPort != "") {
        log.debug("Puerto: " + smtpPort + " (postMail)");
      }
      log.debug("-----------------------------------");
    }
  }
  
  private void registerSMSParametersDebug(SMSData smsData, int indice)
  {
    ParametrosProveedor ps = (ParametrosProveedor)smsData.Servers.get(indice);
    String url = "";
    String id = "";
    String telefono = "";
    String texto = "";
    String uim = "";
    if (ps.getUrl() != null) {
      url = ps.getUrl();
    }
    if (ps.getId() != null) {
      id = ps.getId();
    }
    if (ps.getTelefono() != null) {
      telefono = ps.getTelefono();
    }
    if (ps.getTexto() != null) {
      texto = ps.getTexto();
    }
    if (ps.getUIM() != null) {
      uim = ps.getUIM();
    }
    if (log.isDebugEnabled())
    {
      log.debug("----------------------------");
      log.debug("DATOS DEL PROVEEDOR (postSMS)");
      log.debug("----------------------------");
      log.debug("Url: " + url + " (postSMS)");
      if (id != "") {
        log.debug("Id:" + id + " (postSMS)");
      }
      if (telefono != "") {
        log.debug("Telefono: " + telefono + " (postSMS)");
      }
      if (texto != "") {
        log.debug("Texto: " + texto + " (postSMS)");
      }
      if (uim != "") {
        log.debug("Texto: " + uim + " (postSMS)");
      }
      log.debug("-----------------------------------");
    }
  }
  
  private void registerSMSDetailsDebug(SMSData smsData)
  {
    if (log.isDebugEnabled())
    {
      log.debug("-----------------------------------");
      log.debug("DATOS DE ENVIO DE SMS (postSMS)");
      log.debug("-----------------------------------");
      log.debug("Telefono: " + smsData.Telefono + " (postSMS)");
    }
  }
  
  private void registerReceptorSMSDetailsDebug(ReceptorSMSData smsData)
  {
    if (log.isDebugEnabled())
    {
      log.debug("----------------------------------------");
      log.debug("DATOS DE ENVIO DE SMS (postRecepcionSMS)");
      log.debug("----------------------------------------");
      log.debug("Telefono: " + smsData.Telefono + " (postRecepcionSMS)");
    }
  }
  
  private void registerServidorPushDetailsDebug(NotificacionPushData notificacionPushData)
  {
    if (log.isDebugEnabled())
    {
      log.debug("----------------------------------------------------------");
      log.debug("DATOS DE ENVIO DE NOTIFICACION PUSH (postNotificacionPush)");
      log.debug("----------------------------------------------------------");
      log.debug("Titulo: " + notificacionPushData.cabecera + " (postNotificacionPush)");
      
      log.debug("Cuerpo: " + notificacionPushData.cuerpo + " (postNotificacionPush)");
    }
  }
  
  private void registerReceptorSMSParametersDebug(ReceptorSMSData smsData, int indice)
  {
    ParametrosReceptor ps = (ParametrosReceptor)smsData.Servers.get(indice);
    String url = "";
    String id = "";
    String telefono = "";
    String texto = "";
    String uim = "";
    if (ps.getUrl() != null) {
      url = ps.getUrl();
    }
    if (ps.getId() != null) {
      id = ps.getId();
    }
    if (ps.getTelefono() != null) {
      telefono = ps.getTelefono();
    }
    if (ps.getTexto() != null) {
      texto = ps.getTexto();
    }
    if (ps.getUIM() != null) {
      uim = ps.getUIM();
    }
    if (log.isDebugEnabled())
    {
      log.debug("-------------------------------------");
      log.debug("DATOS DEL RECEPTOR (postRecepcionSMS)");
      log.debug("-------------------------------------");
      log.debug("Url: " + url + " (postRecepcionSMS)");
      if (id != "") {
        log.debug("Id:" + id + " (postRecepcionSMS)");
      }
      if (telefono != "") {
        log.debug("Telefono: " + telefono + " (postRecepcionSMS)");
      }
      if (texto != "") {
        log.debug("Texto: " + texto + " (postRecepcionSMS)");
      }
      if (uim != "") {
        log.debug("Texto: " + uim + " (postRecepcionSMS)");
      }
      log.debug("-----------------------------------");
    }
  }
  
  private void registerServidorPushParametersDebug(NotificacionPushData notificacionPushData, int indice)
  {
    ParametrosServidorPush ps = (ParametrosServidorPush)notificacionPushData.servers.get(indice);
    int plataformaId = 0;
    String url = "";
    String urlFeedback = "";
    int puertoUrl = 0;
    int puertoUrlFeedback = 0;
    plataformaId = ps.getPlataformaId();
    if (ps.getUrl() != null) {
      url = ps.getUrl();
    }
    if (ps.getUrlFeedback() != null) {
      urlFeedback = ps.getUrlFeedback();
    }
    puertoUrl = ps.getPuertoUrl();
    puertoUrlFeedback = ps.getPuertoUrlFeedback();
    if (log.isDebugEnabled())
    {
      log.debug("-------------------------------------");
      log.debug("DATOS DEL RECEPTOR (postNotificacionPush)");
      log.debug("-------------------------------------");
      log.debug("PlataformaId: " + plataformaId + " (postNotificacionPush)");
      if (url != "") {
        log.debug("URL: " + url + " (postNotificacionPush)");
      }
      if (urlFeedback != "") {
        log.debug("URLFeedback: " + urlFeedback + " (postNotificacionPush)");
      }
      log.debug("PuertoURL: " + puertoUrl + " (postNotificacionPush)");
      log.debug("PuertoURLFeedback: " + puertoUrlFeedback + " (postNotificacionPush)");
      
      log.debug("-----------------------------------");
    }
  }
}
