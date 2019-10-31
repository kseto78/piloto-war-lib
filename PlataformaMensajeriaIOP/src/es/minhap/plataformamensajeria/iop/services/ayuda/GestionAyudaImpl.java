package es.minhap.plataformamensajeria.iop.services.ayuda;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ResponseAyudaStatusType;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.Respuesta;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAyuda;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

/**
 * 
 * @author EVERIS
 *
 */
@Service("gestionAyudaImpl")
public class GestionAyudaImpl implements IGestionAyudaService {
	protected static final String R_CONST_1 = "plataformaErrores.gestionAyudaService.STATUSTEXT_KO";

	static Logger logger = LoggerFactory.getLogger(GestionAyudaImpl.class);

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Autowired
	private QueryExecutorAyuda queryExecutorAyuda;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public String gestionAyuda(String username, String password) {
		logger.debug("[GestionAyuda] Consultando la ayuda de la app movil");
		PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		String statusTextKO = ps.getMessage(R_CONST_1, null);
		String codKO = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSLOGINCODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSLOGIN_KO", null);
		String detailsGenerando = ps.getMessage("plataformaErrores.gestionAyudaService.TAG_ERROR_GENERANDO_RESPUESTA_XML", null);
		String xmlResultado = "";
		Respuesta respuesta = new Respuesta();
		try {
			// comprobamos aplicacion
			Boolean existeUsuario = getAplicacionesManager().existeAplicacion(username, password);
			if (existeUsuario) {
				String ayuda = getQueryExecutorAyuda().getAyuda();
				logger.debug("[GestionAyuda] Generando XML de respuesta");
				respuesta = generarRespuestaAyuda(ayuda, ps);
				xmlResultado = respuesta.toXML();
				logger.trace(xmlResultado);
			} else {
				respuesta = generarRespuesta("", codKO, statusTextKO, detailsKO);
				xmlResultado = respuesta.toXML();
			}
			logger.trace(xmlResultado);
			logger.debug("[GestionAyuda] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			logger.error("[GestionAyudaImpl.gestionAyuda] Obteniendo ayuda", e);
			respuesta = generarRespuesta("", codKO, statusTextKO, detailsGenerando);
			try {
				return respuesta.toXML();
			} catch (PlataformaBusinessException e1) {
				logger.error(
						"[GestionAyudaImpl.gestionAyuda] Obteniendo String con la respuesta", e1);
			}
		}
		return xmlResultado;
	}

	private Respuesta generarRespuestaAyuda(String ayuda, PropertiesServices ps) {
		String statusTextOK = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSTEXT_OK", null);
		String codOK = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSDETAILS_OK", null);
		String statusTextKO = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSCODE_KO", null);
		String codKO = ps.getMessage(R_CONST_1, null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionAyudaService.STATUSDETAILS_KO", null);

		if (null != ayuda && !ayuda.isEmpty()) {
			return generarRespuesta(ayuda, codOK, statusTextOK, detailsOK);
		} else {
			return generarRespuesta("", codKO, statusTextKO, detailsKO);
		}
	}

	private Respuesta generarRespuesta(String ayuda, String statusCode, String statusText, String details) {
		Respuesta res = new Respuesta();
		ResponseAyudaStatusType status = new ResponseAyudaStatusType();

		res.setAyuda(ayuda);
		status.setDetails(details);
		status.setStatusCode(statusCode);
		status.setStatusText(statusText);
		res.setStatus(status);

		return res;
	}

	/**
	 * @return the aplicacionesManager
	 */
	public TblAplicacionesManager getAplicacionesManager() {
		return aplicacionesManager;
	}

	/**
	 * @param aplicacionesManager the aplicacionesManager to set
	 */
	public void setAplicacionesManager(TblAplicacionesManager aplicacionesManager) {
		this.aplicacionesManager = aplicacionesManager;
	}

	/**
	 * @return the queryExecutorAyuda
	 */
	public QueryExecutorAyuda getQueryExecutorAyuda() {
		return queryExecutorAyuda;
	}

	/**
	 * @param queryExecutorAyuda the queryExecutorAyuda to set
	 */
	public void setQueryExecutorAyuda(QueryExecutorAyuda queryExecutorAyuda) {
		this.queryExecutorAyuda = queryExecutorAyuda;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}
}
