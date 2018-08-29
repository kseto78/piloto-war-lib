package es.mpr.plataformamensajeria.web.action.ejecucionjob;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.quartz.jobs.EstadisticasConsolidadasJob;
import es.mpr.plataformamensajeria.quartz.jobs.HistorificacionJob;
import es.mpr.plataformamensajeria.quartz.jobs.InformesServiciosJob;
import es.mpr.plataformamensajeria.quartz.jobs.RecuperarInforDIRJob;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */
@Controller("ejecucionJobAction")
@Scope("prototype")
public class EjecucionJobAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(EjecucionJobAction.class);

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	/**  estadisticas consolidadas job. */
	@Resource(name = "estadisticasConsolidadasJob")
	private transient EstadisticasConsolidadasJob estadisticasConsolidadasJob;
	
	/**  historificacion job. */
	@Resource(name = "historificacionJob")
	private transient HistorificacionJob historificacionJob;
	
	/**  informes servicios job. */
	@Resource(name = "informesServiciosJob")
	private transient InformesServiciosJob informesServiciosJob;
	
	/**  recuperar infor DIR job. */
	@Resource(name = "recuperarInforDIRJob")
	private transient RecuperarInforDIRJob recuperarInforDIRJob;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
//	@Resource(name = "pushServiceImpl")
//	private IPushService pushServiceImpl;

	/**  job bean. */
private JobBean jobBean;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/**  combo servicios. */
	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/**  combo jobs. */
	transient List<KeyValueObject> comboJobs = new ArrayList<>();
	
	/** Constante PROC_HIST. */
	private static final String PROC_HIST = "HISTORIFICACION";
	
	/** Constante PROC_CONS. */
	private static final String PROC_CONS = "CONSERVACION";
	
	/** Constante PROC_INFORMES. */
	private static final String PROC_INFORMES = "INFORMES_SERVICIOS";
	
	/** Constante PROC_DIR3. */
	private static final String PROC_DIR3 = "DIR3";
	

	/**
	 * Ejecucion job action.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String ejecucionJobAction() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null)
			return EjecucionJobAction.NO_USER;

		comboServicios = getComboServicios();
		comboJobs = getComboJobs();
		return SUCCESS;
	}
	
	/**
	 * Seleccionar job action.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	// ///MIGRADO
	public String seleccionarJobAction() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null)
			return EjecucionJobAction.NO_USER;
		
		try {
			ServletContext servletContext= getRequest().getSession().getServletContext();
			if(jobBean != null){
				if (jobBean.getNombreJob().equals(PROC_HIST)){
					historificacionJob.lanzarJob(servletContext, jobBean);
				}else if (jobBean.getNombreJob().equals(PROC_CONS)){
					estadisticasConsolidadasJob.lanzarJob(servletContext, jobBean);
				}else if (jobBean.getNombreJob().equals(PROC_INFORMES)){
					informesServiciosJob.lanzarJob(servletContext, jobBean);
	//				pushServiceImpl.sendPush(null, null);
				}else{
					recuperarInforDIRJob.lanzarJob(servletContext, jobBean);
				}
			}

		} catch (Exception e1) {
			logger.error("EjecucionJobAction - seleccionarJobAction", e1);
			addActionErrorSession("Ha ocurrido un error en la ejecucion del job, pongase en contacto con el administrador");
		}
		if(jobBean != null){
			addActionMessageSession("Job ejecutandose en segundo plano");
		}
		return SUCCESS;
	}

/**
 * Cargar servicios conservacion event.
 *
 * @return the string
 * @throws BaseException the base exception
 */
/////MIGRADO
	public String cargarServiciosConservacionEvent() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null)
			return EjecucionJobAction.NO_USER;

		if (null != jobBean){
			if(jobBean.getNombreJob().equals(PROC_HIST)){
				comboServicios = getComboServicios();
			}else if(jobBean.getNombreJob().equals(PROC_CONS)){
				comboServicios = getComboServiciosConservacion();
			}
		}else{
			comboServicios = getComboServicios();
		}
		
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	///MIGRADO
		@Override
		public void prepare() throws Exception {
			this.validate();
		}
		
		/**
		 * Obtener combo servicios.
		 *
		 * @return combo servicios
		 * @throws BusinessException the business exception
		 */
		////MIGRADO
	public List<KeyValueObject> getComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);

		if (keys != null && !keys.isEmpty()) {
			for (ServicioBean key : keys) {

				if (null != key.getHistorificacion()) {
					option = new KeyValueObject();
					option.setCodigo(key.getServicioId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}

			}
		}
		return result;
	}
	
	/**
	 * Obtener combo servicios conservacion.
	 *
	 * @return combo servicios conservacion
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
public List<KeyValueObject> getComboServiciosConservacion() throws BusinessException {
	List<KeyValueObject> result = new ArrayList<>();
	KeyValueObject option;
	ArrayList<ServicioBean> keys;
	String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
	Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
	keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);

	if (keys != null && !keys.isEmpty()) {
		for (ServicioBean key : keys) {

			if (null != key.getConservacion()) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}

		}
	}
	return result;
}
		
		/**
		 * Obtener combo jobs.
		 *
		 * @return combo jobs
		 * @throws BusinessException the business exception
		 */
		////MIGRADO
	public List<KeyValueObject> getComboJobs() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;

		option = new KeyValueObject();
		option.setCodigo(PROC_HIST);
		option.setDescripcion(PROC_HIST);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_CONS);
		option.setDescripcion(PROC_CONS);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_INFORMES);
		option.setDescripcion(PROC_INFORMES);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_DIR3);
		option.setDescripcion(PROC_DIR3);
		result.add(option);

		return result;
	}

		/**
		 * Obtener servicio servicio.
		 *
		 * @return the servicioServicio
		 */
		public ServicioServicio getServicioServicio() {
			return servicioServicio;
		}

		/**
		 * Modificar servicio servicio.
		 *
		 * @param servicioServicio the servicioServicio to set
		 */
		public void setServicioServicio(ServicioServicio servicioServicio) {
			this.servicioServicio = servicioServicio;
		}

		/**
		 * Obtener properties.
		 *
		 * @return the properties
		 */
		public PlataformaMensajeriaProperties getProperties() {
			return properties;
		}

		/**
		 * Modificar properties.
		 *
		 * @param properties the properties to set
		 */
		public void setProperties(PlataformaMensajeriaProperties properties) {
			this.properties = properties;
		}

		/**
		 * Obtener job bean.
		 *
		 * @return the jobBean
		 */
		public JobBean getJobBean() {
			return jobBean;
		}

		/**
		 * Modificar job bean.
		 *
		 * @param jobBean the jobBean to set
		 */
		public void setJobBean(JobBean jobBean) {
			this.jobBean = jobBean;
		}

		/**
		 * Modificar combo servicios.
		 *
		 * @param comboServicios the comboServicios to set
		 */
		public void setComboServicios(List<KeyValueObject> comboServicios) {
			this.comboServicios = comboServicios;
		}

		/**
		 * Modificar combo jobs.
		 *
		 * @param comboJobs the comboJobs to set
		 */
		public void setComboJobs(List<KeyValueObject> comboJobs) {
			this.comboJobs = comboJobs;
		}
	
	
}
