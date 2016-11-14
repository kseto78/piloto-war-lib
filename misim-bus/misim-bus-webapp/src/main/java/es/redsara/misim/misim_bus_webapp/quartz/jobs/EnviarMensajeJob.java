package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import es.map.sim.jms.receiver.SIMMessageReceiver;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.model.TblServicios;

/**
 * 
 * 
 * @author everis
 *
 */
public class EnviarMensajeJob{
	private static final Logger LOG = Logger.getLogger(EnviarMensajeJob.class);
	
	@Autowired
	private TblPlanificacionesManager planificacionesManager;
	
	@Autowired
	private TblServiciosManager serviciosManager;
	
	private long maxMensajesLeer;
	
	@Resource(name="enviarMensajeMessageReceiver")
	private SIMMessageReceiver messageReceiver;
	
	public EnviarMensajeJob(){
		
	}

	@Transactional(noRollbackFor=Throwable.class)
	public void execute() throws JobExecutionException {
		checkDependenciesPresent();
		List<Long> idServiciosPlan = planificacionesManager.getServiciosPlanificacion();
//		LOG.info("[EnviarMensajeJob] Servicios planificados: " + idServiciosPlan.size());
		if(!CollectionUtils.isEmpty(idServiciosPlan)){
			Set<String> nombreServiciosPlan= new LinkedHashSet<String>();
			for(Long servicioId:idServiciosPlan){
				TblServicios servicio = serviciosManager.getServicio(servicioId);
				if(servicio!=null&&servicio.getNombre()!=null){
					nombreServiciosPlan.add(servicio.getNombre());
				}
			}
			if(!CollectionUtils.isEmpty(nombreServiciosPlan)){
				boolean leido=true;
				long mensajesLeidos=0;
				while(leido&&mensajesLeidos<maxMensajesLeer){
					leido=false;
					Iterator<String> it = nombreServiciosPlan.iterator();
					for(;it.hasNext()&&mensajesLeidos<maxMensajesLeer;){
						String nombreServicio=it.next();
//						LOG.info("[EnviarMensajeJob] Desencolando mensajes de servicio: " + nombreServicio);
						boolean received=false;
						try{ 
							received=messageReceiver.receiveByServiceName(nombreServicio);
						}catch (Throwable t){
							LOG.error("Error receiving message for service "+nombreServicio,t);
							leido=true;
						}
						if(received){
							leido=true;
							mensajesLeidos++;
						}
					}
				}
			}
		}

	}

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
