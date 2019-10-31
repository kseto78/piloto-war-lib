package es.minhap.plataformamensajeria.iop.threads;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import es.map.sim.jms.sender.SIMMessageSender;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.MensajeEncolarBean;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;


@Component
public class HiloEncolarMensajesActiveMq extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloEncolarMensajesActiveMq.class);

	private SIMMessageSender sender;
	
	private TblMensajesManager mensajesManager;

	private List<MensajeEncolarBean> listaMensajesEncolar;
	
	@Autowired
	private ErroresManager erroresManager;

	private PropertiesServices ps;

	public HiloEncolarMensajesActiveMq() {
		// This method has to be empty.

	}

	public HiloEncolarMensajesActiveMq(List<MensajeEncolarBean> listaMensajeEncolar, PropertiesServices ps, 
			SIMMessageSender sender,TblMensajesManager mensajesManager, ErroresManager erroresManager) {
		this.sender = sender;
		this.mensajesManager = mensajesManager;
		this.listaMensajesEncolar = listaMensajeEncolar;
		this.ps = ps;
		this.erroresManager = erroresManager;
	}

	@Override
	@Transactional()
	public void run() {
		String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
		String usuario = ps.getMessage("constantes.usuarioActiveMQ", null, "ActiveMQ");
		
		int activeMQ = 2;
		
		LOG.info("Ejecucion Thread Encolar Mensajes ActiveMQ");
		try{
			for (MensajeEncolarBean mBean : listaMensajesEncolar) {
				try{
					sender.send(mBean.getMensajeJms(), mBean.getMaxRetries(), mBean.getServicioId(), mBean.getPremium());
					activeMQ = 1;//true
				}catch (CannotCreateTransactionException e) {
					// TODO logger.warn(e.getMessage(), e);
					activeMQ = 0;//false
					LOG.error(errorActiveMq+" HiloEnviarMensajesPremium.run --Error ActiveMq-- Mensaje: " + mBean.getMensajeJms().getIdMensaje());
					if (mBean.getPremium()){
						mensajesManager.setEstadoMensaje(Long.parseLong(mBean.getMensajeJms().getIdMensaje()), estadoAnulado, descripcionErrorActiveMq, 
									false, null, null, usuario, null);
					}
					
				}
			}
		
		} catch (CannotCreateTransactionException e) {
			// TODO logger.warn(e.getMessage(), e);
			//Comprobamos que si ya se ha actualizado la tabla de errores a false
			LOG.debug("Estamos en HiloEncolarMensajesActiveMQ-run en el catch, comprobamos si ya se ha actualizado la tabla de errores a false");
			activeMQ = 0;//false
			LOG.error(errorActiveMq+" HiloEnviarMensajesPremium.run --Error ActiveMq--");
		} catch (Exception e) {
			LOG.error("HiloEnviarMensajesPremium.run --Error general Ejecucion del hilo--", e);
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug("Estamos en HiloEncolarMensajesActiveMQ-run");					
			switch (activeMQ) {
			case 0:
				erroresManager.comprobarActiveMqActivo(false);
				break;
			case 1:
				erroresManager.comprobarActiveMqActivo(true);
				break;
			}
		}
	}

}