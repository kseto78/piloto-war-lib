package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.map.directorio.manager.impl.SD01UNDescargaUnidades;
import es.map.directorio.manager.impl.wsexport.Estados;
import es.map.directorio.manager.impl.wsexport.FormatoFichero;
import es.map.directorio.manager.impl.wsexport.TipoConsultaUO;
import es.map.directorio.manager.impl.wsexport.UnidadesWs;
import es.minhap.common.entity.TextComparator;
import es.minhap.dir3.bean.OrganismoDir3Bean;
import es.minhap.dir3.bean.OrganismosContactosDir3Bean;
import es.minhap.dir3.services.SD01UN_DescargaUnidades;
import es.minhap.dir3.services.impl.SD01UN_DescargaUnidadesImpl;
import es.minhap.dir3.utils.ConstantesDir3;
import es.minhap.dir3.utils.ExcepcionesDir3;
import es.minhap.plataformamensajeria.iop.manager.TblMonitorDIR3Manager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.managerimpl.TblOrganismosManagerImpl;
import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.minhap.sim.model.TblMonitorDIR3;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.query.TblOrganismosQuery;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

/**
 * The Class RecuperarInforDIRJob.
 */
@Service("recuperarInforDIRJob")
public class RecuperarInforDIRJob implements Job {

	/** Constante DIR3_ESTADO_PROCESO_DIR3_OK. */
	private static final String DIR3_ESTADO_PROCESO_DIR3_OK = "DIR3.ESTADO_PROCESO_DIR3_OK";

	/**  logger. */
	private static Logger logger = Logger.getLogger(RecuperarInforDIRJob.class);
	
	/**  nombre job. */
	private static String nombreJob = "RecuperarInforDIRJob";
	
	/**  label separador lineas. */
	private static String labelSeparadorLineas = "<br>";

	/**  tbl monitor DIR 3 manager. */
	private TblMonitorDIR3Manager tblMonitorDIR3Manager;
	
	/**  tbl organismos manager. */
	private TblOrganismosManager tblOrganismosManager;
	
	/**  tbl parametros manager. */
	private TblParametrosServidorManagerImpl tblParametrosServidorManager;

	/**  unidades dir 3 WS client. */
	private SD01UNDescargaUnidades unidadesDir3WSClient;

	/**  properties. */
	private PlataformaMensajeriaProperties properties;

	/**  job bean. */
	private JobBean jobBean = null;
	
	/**  str fecha inicio filtro. */
	String strFechaInicioFiltro = null;
	
	/**  str fecha fin filtro. */
	String strFechaFinFiltro = null;

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	// /MIGRADO
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedulerContext schedulerContext;
		try {
			schedulerContext = context.getScheduler().getContext();

			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			this.inicializarVariables(applicationContext);
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ScheduleContext en ejecucion Job Informes Servicios", e);
		}
	}

	/**
	 * Lanzar job.
	 *
	 * @param servletContext the servlet context
	 * @param bean the bean
	 */
	public void lanzarJob(ServletContext servletContext, JobBean bean) {
		try {
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			this.inicializarVariables(applicationContext);
			jobBean = bean;
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ejecucion Job manual Informes Servicios: ", e);
		}

	}

	/**
	 * Ejecutar.
	 */
	private void ejecutar() {
		logger.info("execute - INICIO Job RecuperarJobInfo");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		String mensajeEstado = "";
		StringBuilder descripcionEstado = new StringBuilder();

		try {

			// Obtenemos las propiedades para la conexion con sia
			String user = properties.getProperty("DIR3.USUARIO", null);
			String password = properties.getProperty("DIR3.PASSWORD", null);
			String pathTemporal = properties.getProperty("DIR3.PATHTEMPORAL", null);

			
			construirFechasEjecucion(properties);
			logger.info("Fecha de inicio intervalo de busqueda de DIR3: "+strFechaInicioFiltro);
			logger.info("Fecha fin intervalo de busqueda de DIR3: "+strFechaFinFiltro);

			// Consultamos los organismos de DIR 3
			UnidadesWs unidadesRequest = new UnidadesWs();
			unidadesRequest.setUsuario(user);
			unidadesRequest.setClave(password);
			unidadesRequest.setFormatoFichero(FormatoFichero.CSV);
			unidadesRequest.setTipoConsulta(TipoConsultaUO.UNIDADES);

			if (null != strFechaInicioFiltro || null != strFechaFinFiltro) {
				unidadesRequest.setFechaInicio(strFechaInicioFiltro);
				unidadesRequest.setFechaFin(strFechaFinFiltro);
			} else {
				// Como es la primera ejecución sólo traigo vigentes y
				// transitorios
				Estados estadosDIR3 = new Estados();
				estadosDIR3.getItem().add(ConstantesDir3.ESTADO_VIGENTE);
				estadosDIR3.getItem().add(ConstantesDir3.ESTADO_TRANSITORIO);
				unidadesRequest.setEstados(estadosDIR3);
			}

			boolean resultado = invocacionServicio(pathTemporal, unidadesRequest, descripcionEstado);
			
			if (resultado){
				TblMonitorDIR3 ultimaMonitorizacion = new TblMonitorDIR3();
				ultimaMonitorizacion.setCodEstado(properties.getProperty(RecuperarInforDIRJob.DIR3_ESTADO_PROCESO_DIR3_OK, null));
				mensajeEstado = properties.getProperty(RecuperarInforDIRJob.DIR3_ESTADO_PROCESO_DIR3_OK, null);
				ultimaMonitorizacion.setDescEstado(mensajeEstado);
				ultimaMonitorizacion.setFechaEjecucion(new Date());
				tblMonitorDIR3Manager.guardarDir3MonitorWS(ultimaMonitorizacion);
			}
			
			

		} catch (Exception e) {
			TblMonitorDIR3 ultimaMonitorizacion = new TblMonitorDIR3();
			ultimaMonitorizacion.setCodEstado(properties.getProperty("DIR3.ESTADO_PROCESO_DIR3_KO", null));
			mensajeEstado = properties.getProperty("recuperar.info.dir.error.ud.organicas", null);
			
			if(descripcionEstado.toString().equals("Error con la conexion a DIR3")){
				ultimaMonitorizacion.setDescEstado(descripcionEstado.toString()); //Descripcion estado en el log de la bbdd
				mensajeEstado = "";
			}else{
				ultimaMonitorizacion.setDescEstado(mensajeEstado);				
			}			
			ultimaMonitorizacion.setFechaEjecucion(new Date());
			tblMonitorDIR3Manager.guardarDir3MonitorWS(ultimaMonitorizacion);
			logger.error("[RecuperarInforDIRJob] Ha ocurrido un error al ejecutar el job de actualización de DIR3",e);
		}finally{
			SendMailService sendMailService = new SendMailService();
			try {
				sendMailService.initJob(nombreJob, mensajeEstado, descripcionEstado.toString(), properties, tblParametrosServidorManager);
			} catch (ServletException e) {
				logger.error("RecuperarInforDIRJob.ejecutar " , e);
			}
			Calendar fechaFin = Calendar.getInstance();
			logger.info("execute - Fecha fin: "
					+ new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
			long tiempo2 = fechaFin.getTimeInMillis();
			long tiempo = tiempo2 - tiempo1;
			logger.info("execute - Duracion del Proceso de Recuperar Infromación DIR3: " + tiempo + " milisegundos");
		}
	}

	/**
	 * Invocacion servicio.
	 *
	 * @param pathTemporal the path temporal
	 * @param unidadesRequest the unidades request
	 * @param descripcionEstado the descripcion estado
	 * @return true, if successful
	 */
	private boolean invocacionServicio(String pathTemporal, UnidadesWs unidadesRequest, StringBuilder descripcionEstado) throws ExcepcionesDir3 {
		boolean res = false;
		try {
			SD01UN_DescargaUnidades descargarUnidades = new SD01UN_DescargaUnidadesImpl();
			OrganismosContactosDir3Bean organismosContactosDir3Bean = descargarUnidades
					.descargarOrganismosyContactos(unidadesDir3WSClient, unidadesRequest, pathTemporal);
			List<OrganismoDir3Bean> listaOrganismos = organismosContactosDir3Bean.getListaOrganismos();
			
			if(listaOrganismos != null){
				logger.info("Recuperados --->" + listaOrganismos.size() + " organismos");
			}
			descripcionEstado.append(properties.getProperty("recuperar.info.dir.totalOrganismos", null));
			descripcionEstado.append((listaOrganismos != null && !listaOrganismos.isEmpty()) ? listaOrganismos.size()+ " " : " 0 " ); 
			descripcionEstado.append(properties.getProperty("recuperar.info.dir.totalOrganismos2", null));
			
			// actualizamos los organismos
			if ((listaOrganismos != null) && (!listaOrganismos.isEmpty())){
				actualizarOrganismos(listaOrganismos, descripcionEstado);
			}
			res = true;
		
		} catch (WebServiceException e){
			logger.info("execute - FIN - Recuperar informacion DIR - exception - Descargar Unidades Organicas");
			logger.error("[RecuperarInfoDIRJob] - execute - Error: ", e);
			descripcionEstado.append("Error con la conexion a DIR3");
			
			throw new ExcepcionesDir3();
		}
		return res;
	}
	
	/**
	 * Actualizar organismos.
	 *
	 * @param listaOrganismos the lista organismos
	 * @param descripcionEstado the descripcion estado
	 */
	private void actualizarOrganismos(List<OrganismoDir3Bean> listaOrganismos, StringBuilder descripcionEstado){
		TblOrganismosQuery query = new TblOrganismosQuery();
		query.setEliminadoIsNull(true);
		query.setDir3Comparator(TextComparator.EQUALS);
		List<String> listaActualizados = new ArrayList<>();
		List<String> listaInsertados = new ArrayList<>();
		for (OrganismoDir3Bean ob : listaOrganismos) {
			query.setDir3(ob.getCdOrganismo());
			List<TblOrganismos> listaTO = tblOrganismosManager.getOrganismosByQuery(query);
			if (null != listaTO && !listaTO.isEmpty()){
				
				//actualizamos organismo
				String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
				Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
				String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
				TblOrganismos organismoActualizar = listaTO.get(0);
				completarOrganismo(ob, organismoActualizar, true);
				tblOrganismosManager.update(organismoActualizar, source, accion, accionId);
				listaActualizados.add(ob.getCdOrganismo());
			}else{
				
				//añadimos organismo
				String accion = properties.getProperty("log.ACCION_INSERTAR", null);
				Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
				String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
				TblOrganismos o = new TblOrganismos();
				completarOrganismo(ob,o, false);
				tblOrganismosManager.insert(o, source, accion, accionId);
				listaInsertados.add(ob.getCdOrganismo());
			}
		}
		descripcionEstado.append(labelSeparadorLineas);
		descripcionEstado.append(properties.getProperty("recuperar.info.dir.organismosActualizados", null));
		descripcionEstado.append(labelSeparadorLineas);
		descripcionEstado.append(listaActualizados.toString());
		descripcionEstado.append(labelSeparadorLineas);
		descripcionEstado.append(properties.getProperty("recuperar.info.dir.organismosInsertados", null));
		descripcionEstado.append(labelSeparadorLineas);
		descripcionEstado.append(listaInsertados.toString());
		
	}

	/**
	 * Completar organismo.
	 *
	 * @param ob the ob
	 * @param o the o
	 * @param isActualizar the is actualizar
	 */
	private void completarOrganismo(OrganismoDir3Bean ob, TblOrganismos o, boolean isActualizar) {		
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesDir3.FORMATO_FECHA);

		try{
			o.setCodAmbComunidad(ob.getCdAmbitoComunidad());
			o.setCodAmbElm(ob.getCdAmbitoELM());
			o.setCodAmbEntGeografica(ob.getCdAmbitoEntGeografica());
			o.setCodAmbIsla(ob.getCdAmbitoIsla());
			o.setCodAmbLocExtranjera(ob.getCdAmbitoLocExtranjera());
			o.setCodAmbMunicipio(ob.getCdAmbitoMunicipio());
			o.setCodAmbPais(ob.getCdAmbitoPais());
			o.setCodAmbProvincia(ob.getCdAmbitoProvincia());
			o.setCodAmbTerritorial(ob.getCdAmbitoTerritorial());
			o.setCodComunidad(ob.getCdComunidad());
			o.setCodEdpPrincipal(ob.getCdEdpPrincipal());
			o.setCodEntGeografica(ob.getCdEntGeografica());
			o.setCodLocalidad(ob.getCdLocalidad());
			o.setCodPais(ob.getCdPais());
			o.setCodPostal(ob.getCodigoPostal());
			o.setCodProvincia(ob.getCdProvincia());
			o.setCodTipoEntPublic(ob.getCdTipoEntPublica());
			o.setCodTipoUnidad(ob.getCdTipoUnidad());
			o.setCodUnidadRaiz(ob.getCdOrganismoRaiz());
			o.setCodUnidadSuperior(ob.getCdOrganismoSuperior());
			o.setCompetencias(ob.getCompetencias());
			o.setComplemento(ob.getComplemento());
			o.setDenomEdpPrincipal(ob.getDesEdpPrincipal());
			o.setDenomUnidadRaiz(ob.getDesOrganismoRaiz());
			o.setDenomUnidadSuperior(ob.getDesOrganismoSuperior());
			o.setDescripcion(ob.getDesOrganismo());
			o.setDir3(ob.getCdOrganismo());
			o.setDirExtranjera(ob.getDirExtranjera());
			o.setDisposicionLegal(ob.getDisposicionLegal());
			o.setEsEdp(ob.getBitEdpPrincipal());
			o.setEstado(ob.getCdEstado());			
			if (("A".equals(ob.getCdEstado()) || "E".equals(ob.getCdEstado()))){
				o.setEliminado("S");
				o.setActivo(false);
			}else{
				o.setEliminado(null);
			}	
			o.setExternalid(ob.getCdExterno());
			o.setFechaAltaOficial((null != ob.getFechaAltaOficial())? dateFormat.parse(ob.getFechaAltaOficial()) : null);
			o.setFechaAnulacion((null != ob.getFechaAnulacion())? dateFormat.parse(ob.getFechaAnulacion()) : null);
			o.setFechaBajaOficial((null != ob.getFechaBajaOficial())? dateFormat.parse(ob.getFechaBajaOficial()) : null);
			o.setFechaExtincion((null != ob.getFechaExtincion()) ? dateFormat.parse(ob.getFechaExtincion()) : null);
			if (isActualizar){
				o.setModificadopor("jobDIR3");
				o.setFechamodificacion(new Date());
			}else{
				o.setCreadopor("jobDIR3");
				o.setFechacreacion(new Date());
			}
			o.setLocExtranjera(ob.getLocExtranjera());
			o.setNifcif(ob.getNifCif());
			o.setNivelAdministracion((null != ob.getCdNivelAdmon())? Integer.parseInt(ob.getCdNivelAdmon()) : null);
			o.setNivelJerarquico((null != ob.getNivelJerarquico())? Integer.parseInt(ob.getNivelJerarquico()) : null);
			o.setNombre(ob.getDesOrganismo());
			o.setNombreVia(ob.getNombreVia());
			o.setNumVia(ob.getNumVia());
			o.setObservaciones(ob.getObservaciones());
			o.setObservGenerales(ob.getObservacionesGenerales());
			o.setObservBaja(ob.getObservacionesBaja());
			o.setSiglas(ob.getSiglas());
			o.setTipoVia(ob.getTipoVia());
			o.setManual(false);
			o.setFechaActivo(null);
		}catch (ParseException e){
			logger.error("RecuperarInforDIRJob -anadirOrganismo-", e);
		}	
	}

	/**
	 * Construir fechas ejecucion.
	 *
	 * @param properties the properties
	 */
	private void construirFechasEjecucion(PlataformaMensajeriaProperties properties) {
		TblMonitorDIR3 ultimaMonitorizacion = tblMonitorDIR3Manager.obtenerUltimoDir3MonitorWS(properties
				.getProperty(RecuperarInforDIRJob.DIR3_ESTADO_PROCESO_DIR3_OK, null));
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesDir3.FORMATO_FECHA);
		
		if (null != jobBean && null != jobBean.getFecha()) {
			dateFormat.setLenient(false);
			Date fecIni = jobBean.getFecha();
			Date fecFin;
			if (null != jobBean.getFechaFin()) {
				fecFin = jobBean.getFechaFin();
			} else {
				fecFin = new Date();
			}

			Calendar fechaInicial = Calendar.getInstance();
			fechaInicial.setTime(fecIni);

			Calendar fechaFinal = Calendar.getInstance();
			fechaFinal.setTime(fecFin); // Configuramos la fecha que se
										// recibe
			fechaFinal.add(Calendar.HOUR, 23);
			fechaFinal.add(Calendar.MINUTE, 59);

			strFechaInicioFiltro = dateFormat.format(fechaInicial.getTime());
			strFechaFinFiltro = dateFormat.format(fechaFinal.getTime());
		} else {
			if (ultimaMonitorizacion != null) {
				Calendar fechaInicio = Calendar.getInstance();
				fechaInicio.setTime(ultimaMonitorizacion.getFechaEjecucion());
				Calendar fechaFin = Calendar.getInstance();
				strFechaInicioFiltro = dateFormat.format(fechaInicio.getTime());
				strFechaFinFiltro = dateFormat.format(fechaFin.getTime());
			}
		}
		
	}

	/**
	 * Inicializar variables.
	 *
	 * @param applicationContext the application context
	 * @throws JobExecutionException the job execution exception
	 */
	private void inicializarVariables(ApplicationContext applicationContext) throws JobExecutionException {
		try {
			tblMonitorDIR3Manager = (TblMonitorDIR3Manager) applicationContext.getBean("tblMonitorDIR3Manager");
			unidadesDir3WSClient = (SD01UNDescargaUnidades) applicationContext.getBean("unidadesDir3WSClient");
			tblOrganismosManager = (TblOrganismosManagerImpl) applicationContext.getBean("TblOrganismosManagerImpl");
			tblParametrosServidorManager = (TblParametrosServidorManagerImpl) applicationContext.getBean("tblParametrosServidorManagerImpl");
			
			properties = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		} catch (Exception objException) {
			logger.error("RecuperarInforDIRJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}

}
