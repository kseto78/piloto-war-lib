package es.minhap.plataformamensajeria.iop.business.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;

@Component
public class HiloEnviarEmailExclusivo extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloEnviarEmailExclusivo.class);

	private ISendMessageService sendMessageService;

	private TblMensajesManager tblMensajesManager;

	private Long mensajeId;

	private Long destinatarioMensajeId;

	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	public HiloEnviarEmailExclusivo() {}

	public HiloEnviarEmailExclusivo(ISendMessageService sendMessageService, TblMensajesManager tblMensajesManager,
			Long mensajeId, Long loteId, Long destinatarioMensajeId, boolean isAEAT,
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.sendMessageService = sendMessageService;
		this.tblMensajesManager = tblMensajesManager;
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
		this.mensajeId = mensajeId;
		this.destinatarioMensajeId = destinatarioMensajeId;
	}

	@Override
	public void run() {
		LOG.info("Ejecución Thread envío mensaje email PREMIUM: " + mensajeId);
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String estado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcion =  ps.getMessage("constantes.descripcionAnularMensaje", null);
		String usuario =  ps.getMessage("constantes.usuarioActiveMQ", null);
		
		try{
			sendMessageService.postMail(mensajeId, String.valueOf(destinatarioMensajeId));
		}catch(Exception e){
			LOG.error("Excepcion en el Thread enviando Mensaje email PREMIUM: " + mensajeId, e);
			tblMensajesManager.setEstadoMensaje(mensajeId, estado, descripcion, null, destinatarioMensajeId, null, usuario, null);
		}
	}

}