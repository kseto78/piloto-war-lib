package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.CollectionUtils;

import es.map.sim.jms.receiver.SIMMessageReceiver;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;

/**
 * Clase para el Job de el envios de mensajes
 * 
 * @author everis
 *
 */
public class EnviarMensajeJob {
	private static final Logger LOG = LoggerFactory.getLogger(EnviarMensajeJob.class);
	
	@Autowired
	private TblPlanificacionesManager planificacionesManager;
	
	@Autowired
	private TblServiciosManager serviciosManager;
	
	@Autowired
	private ErroresManager erroresManager;
	
	private long maxMensajesLeer;
	
	@Resource(name="enviarMensajeMessageReceiver")
	private SIMMessageReceiver messageReceiver;
	
	@Resource
	ConnectionFactory pooledConnectionFactory;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	public EnviarMensajeJob(){
		
	}

	/**
	 * Metodo que ejecuta el Job
	 * @throws JobExecutionException
	 */
	public void execute() throws JobExecutionException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
		
		int activeMQ = 2;
		//Se obtiene el estado actual de activeMq
		boolean estadoMq = erroresManager.getEstadoMq();
		
		try {
			
			checkDependenciesPresent();
					
			List<Long> idServiciosPlan = planificacionesManager.getServiciosPlanificacion();
			if(estadoMq) {
				LOG.info("[EnviarMensajeJob] Servicios planificados: " + idServiciosPlan.size());
			}
			if(!CollectionUtils.isEmpty(idServiciosPlan)){
				Set<String> nombreServiciosPlan= new LinkedHashSet<String>();
				for(Long servicioId:idServiciosPlan){
					
						nombreServiciosPlan.add(servicioId.toString());
					
				}
				if(!CollectionUtils.isEmpty(nombreServiciosPlan)){
					boolean leido=true;
					long mensajesLeidos=0;
					while(leido&&mensajesLeidos<maxMensajesLeer){
						leido=false;
						Iterator<String> it = nombreServiciosPlan.iterator();
						for(;it.hasNext()&&mensajesLeidos<maxMensajesLeer;){
							String nombreServicio=it.next();
							if(estadoMq) {
								LOG.info("[EnviarMensajeJob] Desencolando mensajes de servicio: " + nombreServicio);
							}	
							boolean received=false;
							
							try{ 
//								LOG.info("[EnviarMensajeJob] BEFORE " + nombreServicio);
								received=messageReceiver.receiveByServiceName(nombreServicio);
								if(!estadoMq) {
									estadoMq = true;
									activeMQ = 1;
								}								
//								LOG.info("[EnviarMensajeJob] AFTER " + nombreServicio);
							}catch(CannotCreateTransactionException e){
								if(estadoMq) {
									activeMQ = 0;
									LOG.error(errorActiveMq+" [EnviarMensajeJob] Error receiving message for service "+ nombreServicio);
									estadoMq = false;
								}
								mensajesLeidos++;
								leido=true;							
							}catch (Throwable t){
								LOG.error("[EnviarMensajeJob] Error receiving message for service "+ nombreServicio);
								leido=true;				
								mensajesLeidos++;
							}
							if(received){
								leido=true;
								mensajesLeidos++;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			if(estadoMq) {
				LOG.error(errorActiveMq+" [EnviarMensajeJob] Error inesperado en job de enviar mensajes",e);
				activeMQ = 0;
//				}	
			}			
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug("Estamos en EnviarMensajesJob-execute ");					
			if (activeMQ == 0){
				erroresManager.comprobarActiveMqActivo(false);
			}else if (activeMQ == 1){
				erroresManager.comprobarActiveMqActivo(true);
			}
		}

	}
	
	/**
	 * Metodo que comprueba la carga de los manager y los parametros messageReceiver y maxMensajesLeer
	 */
	private void checkDependenciesPresent() {
		if(this.planificacionesManager==null){
			throw new IllegalStateException("PlanificacionesManager is required for this job to execute");
		}
		if(this.serviciosManager==null){
			throw new IllegalStateException("ServiciosManager is required for this job to execute");
		}
		if(this.messageReceiver==null){
			throw new IllegalStateException("EnviarMensajeReceiver is required for this job to execute");
		}
		if(this.maxMensajesLeer<0){
			this.maxMensajesLeer=Long.MAX_VALUE;
		}
		
	}

	/**
	 * @return the planificacionesManager
	 */
	public TblPlanificacionesManager getPlanificacionesManager() {
		return planificacionesManager;
	}

	/**
	 * @param planificacionesManager the planificacionesManager to set
	 */
	public void setPlanificacionesManager(
			TblPlanificacionesManager planificacionesManager) {
		this.planificacionesManager = planificacionesManager;
	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}

	/**
	 * @return the maxMensajesLeer
	 */
	public long getMaxMensajesLeer() {
		return maxMensajesLeer;
	}

	/**
	 * @param maxMensajesLeer the maxMensajesLeer to set
	 */
	public void setMaxMensajesLeer(long maxMensajesLeer) {
		this.maxMensajesLeer = maxMensajesLeer;
	}

	/**
	 * @return the enviarMensajeMessageReceiver
	 */
	public SIMMessageReceiver getEnviarMensajeMessageReceiver() {
		return messageReceiver;
	}

	/**
	 * @param enviarMensajeMessageReceiver the enviarMensajeMessageReceiver to set
	 */
	public void setEnviarMensajeMessageReceiver(
			SIMMessageReceiver enviarMensajeMessageReceiver) {
		this.messageReceiver = enviarMensajeMessageReceiver;
	}


}
