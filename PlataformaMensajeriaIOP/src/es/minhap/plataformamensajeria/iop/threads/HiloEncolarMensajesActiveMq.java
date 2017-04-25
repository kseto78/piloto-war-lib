package es.minhap.plataformamensajeria.iop.threads;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import es.map.sim.jms.sender.SIMMessageSender;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.MensajeEncolarBean;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;


@Component
public class HiloEncolarMensajesActiveMq extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloEncolarMensajesActiveMq.class);

	private SIMMessageSender sender;
	
	private TblMensajesManager mensajesManager;

	private List<MensajeEncolarBean> listaMensajesEncolar;

	private PropertiesServices ps;

	public HiloEncolarMensajesActiveMq() {

	}

	public HiloEncolarMensajesActiveMq(List<MensajeEncolarBean> listaMensajeEncolar, PropertiesServices ps, 
			SIMMessageSender sender,TblMensajesManager mensajesManager) {
		this.sender = sender;
		this.mensajesManager = mensajesManager;
		this.listaMensajesEncolar = listaMensajeEncolar;
		this.ps = ps;
	}

	@Override
	@Transactional()
	public void run() {
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		String usuario = ps.getMessage("constantes.usuarioActiveMQ", null, "ActiveMQ");
		
		LOG.info("Ejecucion Thread Encolar Mensajes ActiveMQ");
		try{
			for (MensajeEncolarBean mBean : listaMensajesEncolar) {
				try{
					sender.send(mBean.getMensajeJms(), mBean.getMaxRetries(), mBean.getServicioId(), mBean.getPremium());
				}catch (CannotCreateTransactionException e) {
					LOG.error("HiloEnviarMensajesPremium.run --Error ActiveMq-- Mensaje: " + mBean.getMensajeJms().getIdMensaje());
					if (mBean.getPremium()){
						mensajesManager.setEstadoMensaje(Long.parseLong(mBean.getMensajeJms().getIdMensaje()), estadoAnulado, descripcionErrorActiveMq, 
									false, null, null, usuario, null);
					}
					
				}
			}
		
		}catch (Exception e) {
			LOG.error("HiloEnviarMensajesPremium.run --Error general Ejecucion del hilo--", e);
		}
	}

}