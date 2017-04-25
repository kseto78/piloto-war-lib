package es.minhap.plataformamensajeria.iop.business.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;

@Component
public class HiloEnviarMensajesPremium extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloEnviarMensajesPremium.class);

	private ISendMessageService sendMessageService;

	private TblMensajesManager tblMensajesManager;

	private Long mensajeId;
	
	private Long loteId;

	private Long destinatarioMensajeId;

	private boolean isAEAT;

	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	public HiloEnviarMensajesPremium() {

	}

	public HiloEnviarMensajesPremium(ISendMessageService sendMessageService, TblMensajesManager tblMensajesManager,
			Long mensajeId, Long loteId, Long destinatarioMensajeId, boolean isAEAT,
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.sendMessageService = sendMessageService;
		this.tblMensajesManager = tblMensajesManager;
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
		this.mensajeId = mensajeId;
		this.loteId = loteId;
		this.destinatarioMensajeId = destinatarioMensajeId;
		this.isAEAT = isAEAT;
	}

	@Override
	@Transactional()
	public void run() {
		LOG.info("Ejecución Thread envío mensaje: " + mensajeId);
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String estado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcion =  ps.getMessage("constantes.descripcionAnularMensaje", null);
		String usuario =  ps.getMessage("constantes.usuarioActiveMQ", null);
		try{
			sendMessageService.postSMS(mensajeId, loteId, destinatarioMensajeId, null, null, null, null);
		
		}catch(Exception e){
			LOG.error("Excepcion en el Thread enviando Mensaje: " + mensajeId, e);
			if (isAEAT){
				tblMensajesManager.setEstadoMensaje(mensajeId, estado, descripcion, null, destinatarioMensajeId, null, usuario, null);
			}
		}
	}

}