package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoInformesServicios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

public class InformesServiciosJob implements Job {
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InformesServiciosJob.class);
	
	private static String NOMBRE_JOB = "Informes Servicios";
	private static String ESTADO_PROCESO_OK = "OK";
	private static String ESTADO_PROCESO_KO = "KO";
	private static String AGRUPACION_ESTADOS = "ESTADOS";
	private static String AGRUPACION_ORGANISMOS = "ORGANISMOS";
	private static String AGRUPACION_PROCEDIMIENTOS = "PROCEDIMIENTOS";
	private static String AGRUPACION_ORGANISMOS_PAGADORES = "ORGANISMOS PAGADORES";
	private static String COLUMNA_ESTADO = "Estado";
	private static String COLUMNA_ORGANISMO = "Organismo";
	private static String COLUMNA_PROCEDIMIENTO = "Procedimiento";
	private static String COLUMNA_ORGANISMO_PAGADOR = "Organismo pagador";
	private static String COLUMNA_TOTAL = "Total";
	private static String TOTAL = "TOTAL:";
	
	//Constantes para formato de correo
	private static String LABEL_SEPARADOR_LINEAS = "<br>";
	private static String LABEL_APERTURA_NEGRITA = "<strong>";
	private static String LABEL_CIERRE_NEGRITA = "</strong>";
	private static String LABEL_MARGIN_LEFT = "<div style=\"margin-left:20px;\">";
	private static String LABEL_APERTURA_TABLA = "<table>";
	private static String LABEL_CIERRE_TABLA = "</table>";
	private static String LABEL_CIERRE_DIV = "</div>";
	private static String LABEL_APERTURA_FILA = "<tr>";
	private static String LABEL_CIERRE_FILA = "</tr>";
	private static String LABEL_APERTURA_COLUMNA = "<td>";
	private static String LABEL_CIERRE_COLUMNA = "</td>";
	private static String LABEL_APERTURA_COLUMNA_ANCHO = "<td width=\"160\">";
	
	private ServicioProcesoInformesServicios servicioProcesoInformesServicios;
	private ServicioServicio servicioServicios;
	private ServicioAplicacion servicioAplicacion;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		this.inicializarVariables(context);
		logger.info("execute - INICIO Job Informes Servicios");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		
		ProcesoInformesServiciosBean procesoInformesServiciosBean = new ProcesoInformesServiciosBean();
		procesoInformesServiciosBean.setFechaInicio(new Date());
		
		try{
			
			boolean exito = true;
			
			Calendar calendar = Calendar.getInstance();
			Integer anno = calendar.get(Calendar.YEAR);
			Integer mes = calendar.get(Calendar.MONTH); //Los meses los recupera del 0 (enero) al 11 (diciembre), por lo que ya tenemos en mes anterior
			
			//Si el mes es 0 (enero), debemos de calcular las estadisticas para el mes 12 (diciembre) del anno anterior
			if(mes.equals(Integer.valueOf(0))){
				anno--;
				mes = Integer.valueOf(12);
			}
			
			//Recuperamos todas las aplicaciones para setearle el nombre en el mensaje por servicio
			List<AplicacionBean> listaAplicaciones = servicioAplicacion.getAplicaciones();
			
			//Buscamos los servicios que tienen activada la generacion de informes
			List<ServicioBean> listaServicios = servicioServicios.getServiciosInformes();
			
			if(null != listaServicios){
				
				for(ServicioBean servicio : listaServicios){
					
					StringBuilder cuerpoMensajes = new StringBuilder();
					
					boolean agrupacionEstado = false;
					boolean agrupacionCodOrganismo = false;
					boolean agrupacionCodSia = false;
					boolean agrupacionCodOrganismoPagador = false;
					
					if(null!=servicio){
						
						int numTotalMensajes = 0;
						
						if(null!=servicio.getAgrupacionEstado() && servicio.getAgrupacionEstado().equals(Integer.valueOf(1))){
							agrupacionEstado = true;
						}
						if(null!=servicio.getAgrupacionCodOrg() && servicio.getAgrupacionCodOrg().equals(Integer.valueOf(1))){
							agrupacionCodOrganismo = true;
						}
						if(null!=servicio.getAgrupacionCodSia() && servicio.getAgrupacionCodSia().equals(Integer.valueOf(1))){
							agrupacionCodSia = true;
						}
						if(null!=servicio.getAgrupacionCodOrgPagador() && servicio.getAgrupacionCodOrgPagador().equals(Integer.valueOf(1))){
							agrupacionCodOrganismoPagador = true;
						}
						
						StringBuilder descripcionEstado = new StringBuilder();
						StringBuilder descripcionCodOrganismo = new StringBuilder();
						StringBuilder descripcionCodSia = new StringBuilder();
						StringBuilder descripcionCodOrganismoPagador = new StringBuilder();
						
						if(agrupacionEstado){
							
							descripcionEstado.append(cabeceraTabla(AGRUPACION_ESTADOS, COLUMNA_ESTADO));
							
							List<InformesServiciosEstadoBean> informesServiciosEstado = servicioProcesoInformesServicios.obtenerInformesServiciosEstado(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesEstado = 0;
							
							if(null!=informesServiciosEstado && !informesServiciosEstado.isEmpty()){
								
								for(InformesServiciosEstadoBean informe : informesServiciosEstado){
									descripcionEstado.append(cuerpoTabla(informe.getEstado(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesEstado = numTotalMensajesEstado + informe.getNumTotal();
								}
								
							}
							
							descripcionEstado.append(pieTabla(numTotalMensajesEstado));
						}
						
						if(agrupacionCodOrganismo){
							
							descripcionCodOrganismo.append(cabeceraTabla(AGRUPACION_ORGANISMOS, COLUMNA_ORGANISMO));
							
							List<InformesServiciosCodOrgBean> informesServiciosCodOrg = servicioProcesoInformesServicios.obtenerInformesServiciosCodOrg(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodOrganismo = 0;
							
							if(null!=informesServiciosCodOrg && !informesServiciosCodOrg.isEmpty()){
								for(InformesServiciosCodOrgBean informe : informesServiciosCodOrg){
									descripcionCodOrganismo.append(cuerpoTabla(informe.getCodOrganismo(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodOrganismo = numTotalMensajesCodOrganismo + informe.getNumTotal();
								}
							} 
							
							descripcionCodOrganismo.append(pieTabla(numTotalMensajesCodOrganismo));
							
						}
						
						if(agrupacionCodSia){
							
							descripcionCodSia.append(cabeceraTabla(AGRUPACION_PROCEDIMIENTOS, COLUMNA_PROCEDIMIENTO));
							
							List<InformesServiciosCodSiaBean> informesServiciosCodSia = servicioProcesoInformesServicios.obtenerInformesServiciosCodSia(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodSia = 0;
							
							if(null!=informesServiciosCodSia && !informesServiciosCodSia.isEmpty()){
								for(InformesServiciosCodSiaBean informe : informesServiciosCodSia){
									descripcionCodSia.append(cuerpoTabla(informe.getCodSia(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodSia = numTotalMensajesCodSia + informe.getNumTotal();
								}
							}
							
							descripcionCodSia.append(pieTabla(numTotalMensajesCodSia));
							
						}
						
						if(agrupacionCodOrganismoPagador){
							
							descripcionCodOrganismoPagador.append(cabeceraTabla(AGRUPACION_ORGANISMOS_PAGADORES, COLUMNA_ORGANISMO_PAGADOR));
							
							List<InformesServiciosCodOrgPagadorBean> informesServiciosCodOrgPagador = servicioProcesoInformesServicios.obtenerInformesServiciosCodOrgPagador(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodOrgPagador = 0;
							
							if(null!=informesServiciosCodOrgPagador && !informesServiciosCodOrgPagador.isEmpty()){
								for(InformesServiciosCodOrgPagadorBean informe : informesServiciosCodOrgPagador){
									descripcionCodOrganismoPagador.append(cuerpoTabla(informe.getCodOrganismoPagador(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodOrgPagador = numTotalMensajesCodOrgPagador + informe.getNumTotal();
								}
							}
							
							descripcionCodOrganismoPagador.append(pieTabla(numTotalMensajesCodOrgPagador));
							
						}
						
						//Obtenemos el nombre de la aplicacion asociada al servicio
						String nombreAplicacion = "";
						
						if(null!=listaAplicaciones && !listaAplicaciones.isEmpty()){
							for(AplicacionBean aplicacion : listaAplicaciones){
								if(servicio.getAplicacionId().equals(aplicacion.getAplicacionId())){
									nombreAplicacion = aplicacion.getNombre();
								}
							}
						}
						
						//Construimos la entrada con la informacion del servicio
						StringBuilder cabecera = new StringBuilder();
						cabecera.append("El servicio '" + servicio.getNombre() + "' de la aplicacion '" + nombreAplicacion 
								+ "' ha enviado un total de " + numTotalMensajes + " mensajes.").append(LABEL_SEPARADOR_LINEAS);
						cabecera.append("Los mensajes estan distribuidos de la siguiente forma:").append(LABEL_SEPARADOR_LINEAS).append(LABEL_SEPARADOR_LINEAS);
						
						cuerpoMensajes.append(cabecera).append(descripcionEstado).append(descripcionCodOrganismo).append(descripcionCodSia).append(descripcionCodOrganismoPagador);
						
						//Se envía un correo informando del resultado de la ejecución del JOB
						SendMailService sendMailService = new SendMailService();
						try {
							sendMailService.initInformesServicios(NOMBRE_JOB, ESTADO_PROCESO_OK, 
									servicio.getInformesDestinatarios(), cuerpoMensajes.toString());
						} catch (ServletException e) {
							e.printStackTrace();
							exito = false;
						}
					}
				}	
			}
			
			if(exito){
				procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_OK);
				procesoInformesServiciosBean.setDescripcionEstado("La generacion y envio de informes de servicios se ha realizado correctamente.");
			} else {
				procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_KO);
				procesoInformesServiciosBean.setDescripcionEstado("Se han producido fallos en el envio de informes de servicios. Para mas informacion, consulte logs.");
			}
			
			procesoInformesServiciosBean.setFechaFin(new Date());
			servicioProcesoInformesServicios.newServicioProcesoInformesServicios(procesoInformesServiciosBean);
			
		} catch (Exception e) {
			logger.info("execute - FIN Job Informes Servicios - exception");
			procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_KO);
			StringBuilder descripcionEstado = new StringBuilder();
			descripcionEstado.append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(LABEL_SEPARADOR_LINEAS);
			procesoInformesServiciosBean.setDescripcionEstado("Se han producido fallos en la generacion de los informes de servicios. Para mas informacion, consulte logs.");
			procesoInformesServiciosBean.setFechaFin(new Date());
			servicioProcesoInformesServicios.newServicioProcesoInformesServicios(procesoInformesServiciosBean);
			e.printStackTrace();
		}
		
		//Se envía un correo informando del resultado de la ejecución del JOB
		SendMailService sendMailService = new SendMailService();
		try {
			sendMailService.initJob(NOMBRE_JOB, procesoInformesServiciosBean.getCodigoEstado(), procesoInformesServiciosBean.getDescripcionEstado());
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		Calendar fechaFin = Calendar.getInstance();
		logger.info("execute - Fecha fin: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
		long tiempo2 = fechaFin.getTimeInMillis();
		long tiempo = tiempo2 - tiempo1;
		logger.info("execute - Duracion del Proceso de Informes Servicios: " + tiempo + " milisegundos");
	}
	
	private void inicializarVariables(JobExecutionContext context) throws JobExecutionException {
		try {
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			servicioProcesoInformesServicios = (ServicioProcesoInformesServicios) applicationContext.getBean("servicioProcesoInformesServicios");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicios");
			servicioAplicacion = (ServicioAplicacion) applicationContext.getBean("servicioAplicaciones");
		} catch (Exception objException) {
			logger.error("InformesServiciosJob - InicializarVariables - Error: " + objException.getMessage());
			objException.printStackTrace();
			throw new JobExecutionException("Un error en el planificador");
		}
	}
	
	private StringBuilder cabeceraTabla(String agrupacion, String columna) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_NEGRITA).append(agrupacion).append(LABEL_CIERRE_NEGRITA).append(LABEL_SEPARADOR_LINEAS).append(LABEL_SEPARADOR_LINEAS);
		cadena.append(LABEL_MARGIN_LEFT);
		cadena.append(LABEL_APERTURA_TABLA);
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA_ANCHO).append(LABEL_APERTURA_NEGRITA).append(columna).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(COLUMNA_TOTAL).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		
		return cadena;
	}
	
	private StringBuilder cuerpoTabla(String atributoCelda, Integer numTotal) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA).append(atributoCelda).append(LABEL_CIERRE_COLUMNA)
			.append(LABEL_APERTURA_COLUMNA).append(numTotal).append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		
		return cadena;
	}
	
	private StringBuilder pieTabla(Integer numTotalMensajes) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(TOTAL).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(numTotalMensajes)
			.append(LABEL_CIERRE_NEGRITA).append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		cadena.append(LABEL_CIERRE_TABLA);
		cadena.append(LABEL_CIERRE_DIV).append(LABEL_SEPARADOR_LINEAS);
		
		return cadena;
	}

}
