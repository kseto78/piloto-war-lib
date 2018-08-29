package es.mpr.template.web.action.reports;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.map.j2ee.reports.ReportException;
import com.map.j2ee.reports.ReportParameters;
import com.map.j2ee.reports.struts.RunReportAction;

import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;



/**
 * <p>Clase Action para la generaci&oacute;n de informes mediante 
 * Jasper Reports.
 * 
 * <p>Realiza la generaci&oacute;n de un informe a partir de la lista de organismos
 * 
 * @author Altran
 *
 */
public class ReportGeneratorAction extends RunReportAction {

		
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Definicion del logger. */
	private static Log logger = LogFactory.getLog(ReportGeneratorAction.class);

	/** Variables para manejo de datos. */
	private ServicioOrganismo servicioOrganismos;
	
	/**  lista registros organismo. */
	private List<OrganismoBean> listaRegistrosOrganismo= null;

	/**  form. */
	protected ReportForm form = new ReportForm();
	
	/**
	 * <p>M&eacute;todo que ejecuta la generaci&oacute;n de un informe.</p>
	 *
	 * @return the string
	 * @throws ReportException the report exception
	 */
	public String generateReport() throws ReportException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		logger.debug("generateReport(): Inicio");
		
		// Recogemos de la página el tipo de informe a generar
//		String tipoInforme = form.getTipoInforme();
//
//		// Establecemos la plantilla desde la que generar el informe
//		String reportDesign = "TemplateReportJPA.jasper";
//
//		// Recuperamos los datos a mostrar en el informe
//		// O bien pasamos una query a ejecutar
//		OrganismoBean criterio = new OrganismoBean();
//		try {
//			listaRegistrosOrganismo = servicioOrganismos.getOrganismos(criterio);
//		} catch (BusinessException e) {
//			String mensg = this.getText("errors.organismo.getOrganismos");
//			throw new ReportException(mensg);
//		}
//
//
//		logger.debug("generateReport(): Establecidos parámetros de generación de informe...");
//		
//		try {
//			// Invocamos la generacion
//			doGenerateReport(reportDesign, listaRegistrosOrganismo, getRequest(), getResponse(), tipoInforme);
//			//doGenerateReport(reportDesign, reportQuery, getRequest(), getResponse(), tipoInforme);
//			//doGenerateReport(reportDesign, "", getRequest(), getResponse(), tipoInforme);
//		} catch (Exception e) {
//			String[] paramError = {reportDesign,tipoInforme};
//			throw new ReportException("errors.reports.generar", paramError);
//		}
//
//		logger.debug("generateReport(): Informe generado retornando...");
//
//		// No se retorna nada ya que el retorno es el propio informe
//	    return NONE; 
		return null;

	}

	/**
	 * Método a ejecutar para cargar datos en la pantalla de selección de informe.
	 *
	 * @return the string
	 */
	public String nuevoInforme(){
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 

		return SUCCESS;
		
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);		
	}


	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.setResponse(response);
		
	}

	/**
	 * Obtener lista registros organismo.
	 *
	 * @return lista registros organismo
	 */
	public List<OrganismoBean> getListaRegistrosOrganismo() {
		return listaRegistrosOrganismo;
	}


	/**
	 * Modificar lista registros organismo.
	 *
	 * @param listaRegistrosOrganismo new lista registros organismo
	 */
	public void setListaRegistrosOrganismo(List<OrganismoBean> listaRegistrosOrganismo) {
		this.listaRegistrosOrganismo = listaRegistrosOrganismo;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.reports.struts.RunReportAction#getForm()
	 */
	public ReportForm getForm() {
		return this.form;
	}

	/**
	 * Modificar form.
	 *
	 * @param aForm new form
	 */
	public void setForm(ReportForm aForm) {
		this.form = aForm;
	}

	/**
	 * Obtener servicio organismos.
	 *
	 * @return servicio organismos
	 */
	public ServicioOrganismo getServicioOrganismos() {
		return servicioOrganismos;
	}

	/**
	 * Modificar servicio organismos.
	 *
	 * @param servicioOrganismo new servicio organismos
	 */
	public void setServicioOrganismos(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismos = servicioOrganismo;
	}
	
	/**
	 * Clase que genera los par&aacute;metros del informe.
	 *
	 * @param request Petici&oacute;n HTTP en la cual incluir los par&aacute;metros
	 * @return ReportParameters Conjunto de par&aacute;metros del informe
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public ReportParameters getReportParameters(HttpServletRequest request) throws Exception {
	    reportParameters.put("Titulo", "Informe de Organismos");
	    ServletContext servletCtx = request.getSession().getServletContext();
	    String pathImgs = servletCtx.getRealPath(getReportImgDirectory()) + File.separatorChar;
	    reportParameters.put("PathImgs", pathImgs);
		return reportParameters;	
	}

}
