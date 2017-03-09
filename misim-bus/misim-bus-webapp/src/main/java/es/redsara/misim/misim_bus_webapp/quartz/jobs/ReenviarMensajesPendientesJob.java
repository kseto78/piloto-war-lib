package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.SMTPAuthenticator;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.model.TblServicios;

/**
 * 
 * 
 * @author everis
 * 
 */
public class ReenviarMensajesPendientesJob {
	private static final Logger LOG = LoggerFactory.getLogger(ReenviarMensajesPendientesJob.class);

	@Autowired
	private TblServiciosManager serviciosManager;

	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;

	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajesManager;

	@Resource
	ConnectionFactory pooledConnectionFactory;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	private static String caracterSeparadorLineas = "<br>";

	public ReenviarMensajesPendientesJob() {

	}

	@Transactional(noRollbackFor = Throwable.class)
	public void execute() throws JobExecutionException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		
		if("S".equals(ps.getMessage("activemq.job.activarReenvio", null, null, null))) {
			String prefijoNormal = ps.getMessage("activemq.queueNamePrefix", null);
			String prefijoPremium = ps.getMessage("activemq.premiumQueueName", null);
			List<String> mensajesConError = new ArrayList<>();
			List<String> destinatariosConError = new ArrayList<>();
			
			LOG.info("---Inicio JOB ReenviarMensajes---");
			checkDependenciesPresent();
			
			// Obtenemos mensaje pendientes de envío.Hay que mirar el estado en
			// destinatarios-mensaje
			Map<Long, List<MensajeJMS>> mapMensajes = tblMensajesManager.getMensajesReenviar();
			for (Map.Entry<Long, List<MensajeJMS>> entry : mapMensajes.entrySet()) {
				Long servicioId = entry.getKey();
				List<MensajeJMS> listaMensajesPendientes = entry.getValue();
				infoMensajes(servicioId, listaMensajesPendientes);
				
				TblServicios servicio = serviciosManager.getServicio(servicioId);
				List<MensajeJMS> listaMensajesEncolados = new ArrayList<>();
				try {
					if (null != servicio && null != servicio.getPremium() && servicio.getPremium()) {
						listaMensajesEncolados = getListaMensajesEncolados(servicioId, prefijoPremium);
					} else {
						listaMensajesEncolados = getListaMensajesEncolados(servicioId, prefijoNormal);
					}
				} catch (Exception e) {
					for (MensajeJMS mensajeJMS : listaMensajesPendientes) {
						completarMensajesConError(mensajesConError, destinatariosConError, mensajeJMS, ps);
					}
				}
				for (MensajeJMS mensajeJMSPendiente : listaMensajesPendientes) {
					try {
						boolean encolado = false;
						for (MensajeJMS mensajeJMSEncolado : listaMensajesEncolados) {
							if(mensajeJMSPendiente.getIdMensaje().equals(mensajeJMSEncolado.getIdMensaje()) 
									&& (mensajeJMSPendiente.getDestinatarioMensajeId()!=null &&
											mensajeJMSPendiente.getDestinatarioMensajeId().equals(mensajeJMSEncolado.getDestinatarioMensajeId())
											|| (mensajeJMSPendiente.getDestinatarioMensajeId() == null 
												&& mensajeJMSEncolado.getDestinatarioMensajeId() == null))) {
								encolado=true;
								break;
							}
						}
						if (!encolado) {
							LOG.info("Mensaje " + mensajeJMSPendiente.getIdMensaje() + " - " + mensajeJMSPendiente.getDestinatarioMensajeId() + " no ha sido encolado previamente.");
							encolarMensaje(ps, servicioId, servicio, mensajeJMSPendiente);
						} else {
							LOG.info("Mensaje " + mensajeJMSPendiente.getIdMensaje() + " - " + mensajeJMSPendiente.getDestinatarioMensajeId() + " ya ha sido encolado previamente.");
						}
					} catch (Exception e) {
						LOG.error("[ReenviarMensajesPendientesJob] Error al encolarMensaje: " , e);
						completarMensajesConError(mensajesConError, destinatariosConError, mensajeJMSPendiente, ps);
					}
				}
	
			}// del for del map
			if (null != mensajesConError && mensajesConError.size() > 0) {
				LOG.info("--- Envio email Job ReenviarMensajesPendientes ---");
				if("S".equals(ps.getMessage("job.mail.avisar", null, null, null))) {
					enviarEmail(ps, mensajesConError, destinatariosConError);
				}
			}
			LOG.info("--- Fin Job ReenvioMensajesPendientes ---");
		}
	}

	private void encolarMensaje(PropertiesServices ps, Long servicioId, TblServicios servicio, MensajeJMS mensajeJMS) {
		Long maxRetries = null;
		if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() > 0) {
			maxRetries = servicio.getNumeroMaxReenvios().longValue();
		} else {
			maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
		}
		boolean premium = false;
		if (servicio.getPremium() != null && servicio.getPremium()) {
			premium = true;
		}
//		throw new CannotCreateTransactionException("Error manual");
		LOG.info("Se encola Mensaje:  ---->" + mensajeJMS.getIdMensaje() + " -- DestinatarioMensajeId: " + mensajeJMS.getDestinatarioMensajeId());
		sender.send(mensajeJMS, maxRetries, servicioId.toString(), premium);
	}

	private void infoMensajes(Long servicioId, List<MensajeJMS> listaMensajes) {
		LOG.info("------------------");
		LOG.info("SERVICIO ---->" + servicioId);
		LOG.info("MENSAJES ---->" + listaMensajes.size());
		LOG.info("------------------");
	}

	private void enviarEmail(PropertiesServices ps, List<String> mensajesConError, List<String> destinatariosConError) {
		String dns = ps.getMessage("job.mail.ip.dns", null);
		String usuario = ps.getMessage("job.mail.usuario", null);
		String password = ps.getMessage("job.mail.password", null);
		String conexionSegura = ps.getMessage("job.mail.requiereConexionSegura", null);
		String puerto = ps.getMessage("job.mail.puerto", null);
		String autenticacion = ps.getMessage("job.mail.requiereAutenticacion", null);
		String tiempEspera = ps.getMessage("job.mail.tiempoEspera", null);
		String destTO = ps.getMessage("job.mail.to", null);
		String destCC = ps.getMessage("job.mail.cc", null);
		String destBcc = ps.getMessage("job.mail.bcc", null);
		String tituloCorreo = ps.getMessage("job.mail.titulo", null);
		String cuerpoCorreo = ps.getMessage("job.mail.cuerpo", null);

		boolean debug = false;

		try {
			// CONFIGURAMOS EL SERVIDOR
			Properties props = new Properties();
			javax.mail.Session session = null;
			Authenticator auth = null;

			// HOST
			props.put("mail.smtp.host", dns);

			// PUERTO
			props.put("mail.smtp.port", puerto);

			// TIEMPO DE ESPERA
			int timeout = Integer.parseInt(tiempEspera) * 1000;
			props.put("mail.smtp.connectiontimeout", timeout);
			props.put("mail.smtp.timeout", timeout);

			// SI REQUIERE CONEXION SEGURA
			if (conexionSegura != null && conexionSegura.equals("S")) {
				props.setProperty("mail.smtp.starttls.enable", "true");
			}

			// SI REQUIERE AUTENTIFICACION
			if (null != autenticacion && autenticacion.equals("S")) {
				props.put("mail.smtp.auth", true);
				auth = new SMTPAuthenticator(usuario, password);
				session = javax.mail.Session.getInstance(props, auth);
			} else {
				props.put("mail.smtp.auth", false);
				session = javax.mail.Session.getInstance(props, null);
			}

			session.setDebug(debug);

			// CREAMOS EL MENSAJE
			MimeMessage msg = new MimeMessage(session);

			InternetAddress addressFrom = null;

			// ANADIR EL SENDER
			if (usuario != null) {
				addressFrom = new InternetAddress(usuario, "Informe SIM");
				msg.setFrom(addressFrom);
			} else {
				addressFrom = new InternetAddress(usuario);
				msg.setFrom(addressFrom);
			}

			String addressToProperties = destTO;
			String addressCcProperties = destCC;
			String addressBccProperties = destBcc;

			ArrayList<String> recipientsTo = recuperarInternetAddress(addressToProperties);
			ArrayList<String> recipientsCc = recuperarInternetAddress(addressCcProperties);
			ArrayList<String> recipientsBcc = recuperarInternetAddress(addressBccProperties);

			// ANADIR DESTINATARIOS
			InternetAddress[] addressTo = generateInternetAddress(recipientsTo);
			InternetAddress[] addressCC = generateInternetAddress(recipientsCc);
			InternetAddress[] addressBCC = generateInternetAddress(recipientsBcc);

			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);
			msg.setRecipients(javax.mail.Message.RecipientType.CC, addressCC);
			msg.setRecipients(javax.mail.Message.RecipientType.BCC, addressBCC);

			msg.setSubject(tituloCorreo, "UTF-8");

			// /////// CREACION DEL CUERPO DEL MENSAJE ///////////////
			Multipart multipart = new MimeMultipart();
			MimeBodyPart cuerpo = new MimeBodyPart();

			cuerpoCorreo = cuerpoCorreo.concat(caracterSeparadorLineas);
			cuerpoCorreo = cuerpoCorreo.concat(mensajesConError.toString());

			String typeContent = "text/HTML" + "; charset=" + "UTF-8";
			cuerpo.setContent(cuerpoCorreo, typeContent);
			multipart.addBodyPart(cuerpo);

			// INSERTAR EL CUERPO EN EL MENSAJE
			msg.setContent(multipart);

			// REALIZAR EL ENVIO FISICO DEL MENSAJE
			Transport.send(msg);
		} catch (Exception e) {
			LOG.error("ReenviarMensajesPendientesJob.enviarEmail", e);
		}

	}

	private void completarMensajesConError(List<String> mensajesConError,
			List<String> destinatariosConError, MensajeJMS mensajeJMS, PropertiesServices ps) {
		Integer maxIntentosReenvio = Integer.parseInt(ps.getMessage("constantes.servicio.maxIntentosReenvio", null));
		
		List<String> listaDestinatarios = new ArrayList<String>(Arrays.asList(mensajeJMS.getDestinatarioMensajeId()
				.split(";")));
		for (String ds : listaDestinatarios) {
			Integer intentos = tblDestinatariosMensajesManager.updateNumIntentosEncolar(Long.parseLong(ds));
			if (intentos >= maxIntentosReenvio) {
				if (!mensajesConError.contains(mensajeJMS.getIdMensaje()))
					mensajesConError.add(mensajeJMS.getIdMensaje());
				if (!destinatariosConError.contains(ds))
					destinatariosConError.add(ds);
			}
			
		}
	}

	private List<MensajeJMS> getListaMensajesEncolados(Long servicioId, String prefijo) throws Exception {
		QueueBrowser browser = null;
		Session session = null;
		Connection connection = null;
		List<MensajeJMS> listaMensajesEncolados = new ArrayList<>();
		try {
			connection = pooledConnectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(prefijo + servicioId);
			connection.start();
			browser = session.createBrowser(queue);
			Enumeration<?> e = browser.getEnumeration();

			while (e.hasMoreElements()) {
				Message tempMsg = (Message) e.nextElement();
				ActiveMQBytesMessage bytesMessage = (ActiveMQBytesMessage) tempMsg;
				String messageText = new String(bytesMessage.getContent().data);

				MensajeJMS data = new Gson().fromJson(messageText, MensajeJMS.class);
				listaMensajesEncolados.add(data);
			}
		} catch (Exception e) {
			LOG.error("ReenviarMensajesPendientesJob.getListaMensajesEncolados", e);
			throw new Exception(e);
		} finally {
			if (null != browser)
				browser.close();
			if (null != session)
				session.close();
			if (null != connection)
				connection.close();
		}
		return listaMensajesEncolados;
	}

	// METODO QUE RECUPERA LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	private ArrayList<String> recuperarInternetAddress(String address) throws AddressException {

		ArrayList<String> recipients = new ArrayList<String>();
		int contador = 0;

		if (null != address && !address.isEmpty()) {
			// Si existe solo una direccion de correo
			if (address.indexOf(";") == -1) {
				recipients.add(address);
			} else {
				// Recuperamos todos las direcciones de correo
				while (address.indexOf(";", contador) > -1) {
					recipients.add(address.substring(contador, address.indexOf(";", contador)));
					contador = address.indexOf(";", contador) + 1;
				}
				recipients.add(address.substring(contador, address.length()));
			}
		}

		return recipients;
	}

	// METODO QUE ANADE LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	private InternetAddress[] generateInternetAddress(ArrayList<String> recipients) throws AddressException {

		InternetAddress[] address = new InternetAddress[recipients.size()];

		for (int i = 0; i < recipients.size(); i++) {
			address[i] = new InternetAddress(recipients.get(i));
		}
		return address;
	}

	private void checkDependenciesPresent() {
		if (this.serviciosManager == null) {
			throw new IllegalStateException("ServiciosManager is required for this job to execute");
		}

	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager
	 *            the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}

}
