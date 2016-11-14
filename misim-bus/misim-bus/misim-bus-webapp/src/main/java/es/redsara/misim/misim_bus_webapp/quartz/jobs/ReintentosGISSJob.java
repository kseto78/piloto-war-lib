package es.redsara.misim.misim_bus_webapp.quartz.jobs;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.bean.SoapPayload;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.FactoryServiceSim;


public class ReintentosGISSJob implements Job {
	
	protected static final String SOAP_ACTION = "SOAPAction";

	public static final String RECEPT_QUEUE = "vm://envio-sim-GISS";
	
	private static Logger logger = Logger.getLogger(ReintentosGISSJob.class);
	
	private static final String HEADER_GISS_SMS = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>";    
	
	private static final String FOOTER_GISS_SMS = "</soapenv:Body></soapenv:Envelope>";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
			logger.info("Se comienza la ejecución del job : Reintentos GISS Job");
			Scheduler scheduler = context.getScheduler();
			SchedulerContext schedulerContext = null;
			try {
				schedulerContext = scheduler.getContext();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			MuleContext muleContext = (MuleContext) schedulerContext.get("muleContext");
			
			PropertiesServices ps = new PropertiesServices((ReloadableResourceBundleMessageSource) muleContext.getRegistry().lookupObject("reloadableResourceBundleMessageSource"));
			String usuarioGISS = ps.getMessage("giss.usuario.sms", null, null, null);
			String passwordGISS = ps.getMessage("giss.contrasena.sms", null, null, null);
			Integer idServicioGISS = new Integer(ps.getMessage("giss.servicio.sms.premium", null, null, null));
			Integer reintentos = new Integer(ps.getMessage("giss.reintentos.sms.premium", null, null, null));
			String usuarioMISIM = ps.getMessage("misim.aplicacion.giss.usuario.sms", null, null, null);
			String passwordMISIM = ps.getMessage("misim.aplicacion.giss.contrasena.sms", null, null, null);
			IEnvioPremiumGISSService envioGISSService = FactoryServiceSim.getInstance().getInstanceGISS();
			logger.info("Se lanza el proceso de reintentos de GISS");
			List<EnvioGISSXMLBean> reenvios= envioGISSService.reenviarSMSGISS(usuarioGISS, passwordGISS, idServicioGISS, reintentos, usuarioMISIM, passwordMISIM);
			logger.debug("Se procede a reintentar los SMS fallidos");
			for (EnvioGISSXMLBean envio : reenvios) {
				sendMessage(muleContext, envio);
			}
			logger.debug("Se procede a ANULAR los SMS que han superado el limite de reintentos");
			envioGISSService.anularSMS(idServicioGISS, reintentos);
			logger.info("Se termina la ejecución del job : Reintentos GISS Job");
	}

	private void sendMessage(MuleContext muleContext, EnvioGISSXMLBean envio) {
		try {
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(SOAP_ACTION);
			String xml = mountSoapRequest(envio);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			db = dbf.newDocumentBuilder();
			Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			payload.setSoapMessage(soapDOM);
			
			// Reenviamos el mensaje al InvocarEnvioGISS
			muleContext.getClient().send(RECEPT_QUEUE,payload, null, 10000);
		} catch (Exception e) {
			logger.error("ReintentoGISSJob.execute: Se ha producido un error en el reenvio ", e);
		}
	}

	private String mountSoapRequest(EnvioGISSXMLBean envio) throws PlataformaBusinessException {
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append(HEADER_GISS_SMS);
		String xml = envio.toXML();
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append(FOOTER_GISS_SMS);
		return soapRequest.toString();
	}
}
