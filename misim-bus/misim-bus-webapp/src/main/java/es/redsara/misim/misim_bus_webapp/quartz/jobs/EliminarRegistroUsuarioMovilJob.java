package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import javax.annotation.Resource;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.IRegistroMovilService;

/**
 * 
 * 
 * @author everis
 * 
 */
public class EliminarRegistroUsuarioMovilJob {
	private static final Logger LOG = LoggerFactory.getLogger(EliminarRegistroUsuarioMovilJob.class);

	@Resource(name = "registroMovilServiceImpl")
	private IRegistroMovilService registroMovilService;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	public EliminarRegistroUsuarioMovilJob() {

	}

	@Transactional(noRollbackFor = Throwable.class)
	public void execute() throws JobExecutionException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		try {
			if ("S".equals(ps.getMessage("eliminarRegistroUsuario.job.activarJob", null, null, null))) {

				LOG.info("---Inicio JOB EliminarRegistroUsuarioMovilJob---");
				registroMovilService.eliminarRegistrosCaducados();
				LOG.info("---Fin JOB EliminarRegistroUsuarioMovilJob---");
			}
		} catch (Exception e) {
			LOG.error("---Error Job EliminarRegistroUsuarioMovilJob---", e);
		}

	}

}
