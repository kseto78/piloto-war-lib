package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.business.thread.HiloEnviarMensajesExclusivo;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.sim.model.TblDestinatariosMensajes;

/**
 * Clase para el Job de reintentos GISS
 * @author ralberoc
 *
 */
public class ReintentosGISSJob {
	private static final Logger LOG = LoggerFactory.getLogger(ReintentosGISSJob.class);

	@Resource(name = "envioPremiumGISSServiceImpl")
	private IEnvioPremiumGISSService envioGISSService;

	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;

	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajes;

	@Resource(name = "sendMessageService")
	private ISendMessageService sendMessageService;
	
	@Autowired
	private QueryExecutorLotesEnvios queryExecutorLotesEnvios;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	/**
	 * Metodo que ejecuta el Job
	 * @throws JobExecutionException
	 */
	@Transactional(noRollbackFor = Throwable.class)
	public void execute() throws JobExecutionException {
		LOG.debug("----Inicio Job Reintentos mensajes GISS----");
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Long idServicioGISS = new Long(ps.getMessage("giss.servicio.sms.premium", null, null, null));
		Integer reintentosDefault = new Integer(ps.getMessage("giss.reintentos.sms.premium", null, null, null));
		String estadoIncidencia = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		String utilizarActiveMq = ps.getMessage("constantes.ENVIO_ACTIVEMQ", null,"S");

		if ("S".equals(ps.getMessage("activemq.job.activarReintentosGISS", null, null, null)) && !"S".equals(utilizarActiveMq)) {
			List<EnvioGISSXMLBean> reenvios = envioGISSService.reenviarSMSGISS(idServicioGISS, reintentosDefault);
			LOG.debug("Se procede a reintentar los SMS fallidos");
			for (EnvioGISSXMLBean envio : reenvios) {
				List<Long> listaMensajes = tblDestinatariosMensajes.getIdMensajeByIdExterno(envio.getIdPeticion());
				for (Long idMensaje : listaMensajes) {
					levantarHilo(estadoIncidencia, idMensaje);
				}
			}

			LOG.debug("Se procede a ANULAR los SMS que han superado el limite de reintentos");
			envioGISSService.anularSMS(idServicioGISS, reintentosDefault);
			LOG.debug("----FIN Job Reintentos mensajes GISS----");
		}
	}

	/**
	 * Metodo que lenvanta un hilo por destinatario
	 * @param estadoIncidencia
	 * @param idMensaje
	 */
	private void levantarHilo(String estadoIncidencia, Long idMensaje) {
		String estadoActual = tblMensajesManager.getMensaje(idMensaje).getEstadoactual();
		List<TblDestinatariosMensajes> listaDestinatarios = tblDestinatariosMensajes.getDestinatarioMensajes(idMensaje);
		Long idLote = queryExecutorLotesEnvios.getIdLoteByIdMensaje(idMensaje);
		
		for (TblDestinatariosMensajes d : listaDestinatarios) {
			if (estadoActual.equals(estadoIncidencia)) {
				HiloEnviarMensajesExclusivo hilo1 = new HiloEnviarMensajesExclusivo(sendMessageService,tblMensajesManager, idMensaje, idLote,
						d.getDestinatariosmensajes(), false, reloadableResourceBundleMessageSource);
				hilo1.start();
			}
		}
	}

}
