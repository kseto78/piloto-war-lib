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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(EjecucionJobAction.class);

	@Resource(name = "servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	@Resource(name = "estadisticasConsolidadasJob")
	private transient EstadisticasConsolidadasJob estadisticasConsolidadasJob;
	
	@Resource(name = "historificacionJob")
	private transient HistorificacionJob historificacionJob;
	
	@Resource(name = "informesServiciosJob")
	private transient InformesServiciosJob informesServiciosJob;
	
	@Resource(name = "recuperarInforDIRJob")
	private transient RecuperarInforDIRJob recuperarInforDIRJob;

	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
//	@Resource(name = "pushServiceImpl")
//	private IPushService pushServiceImpl;

	private JobBean jobBean;
	
	private static final String INFO_USER = "infoUser";

	private static final String NO_USER = "noUser";

	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	transient List<KeyValueObject> comboJobs = new ArrayList<>();
	
	private static final String PROC_HIST = "HISTORIFICACION";
	private static final String PROC_CONS = "CONSERVACION";
	private static final String PROC_INFORMES = "INFORMES_SERVICIOS";
	private static final String PROC_DIR3 = "DIR3";
	

	/////MIGRADO
	public String ejecucionJobAction() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null)
			return EjecucionJobAction.NO_USER;

		comboServicios = getComboServicios();
		comboJobs = getComboJobs();
		return SUCCESS;
	}
	
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
	
	///MIGRADO
		@Override
		public void prepare() throws Exception {
			this.validate();
		}
		
		/**
		 * 
		 * @return
		 * @throws BusinessException
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
		 * 
		 * @return
		 * @throws BusinessException
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
		 * @return the servicioServicio
		 */
		public ServicioServicio getServicioServicio() {
			return servicioServicio;
		}

		/**
		 * @param servicioServicio the servicioServicio to set
		 */
		public void setServicioServicio(ServicioServicio servicioServicio) {
			this.servicioServicio = servicioServicio;
		}

		/**
		 * @return the properties
		 */
		public PlataformaMensajeriaProperties getProperties() {
			return properties;
		}

		/**
		 * @param properties the properties to set
		 */
		public void setProperties(PlataformaMensajeriaProperties properties) {
			this.properties = properties;
		}

		/**
		 * @return the jobBean
		 */
		public JobBean getJobBean() {
			return jobBean;
		}

		/**
		 * @param jobBean the jobBean to set
		 */
		public void setJobBean(JobBean jobBean) {
			this.jobBean = jobBean;
		}

		/**
		 * @param comboServicios the comboServicios to set
		 */
		public void setComboServicios(List<KeyValueObject> comboServicios) {
			this.comboServicios = comboServicios;
		}

		/**
		 * @param comboJobs the comboJobs to set
		 */
		public void setComboJobs(List<KeyValueObject> comboJobs) {
			this.comboJobs = comboJobs;
		}
	
	
}
