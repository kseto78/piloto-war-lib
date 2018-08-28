/**
 * 
 */
package es.minhap.misim.misim_bus_webapp.test;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.common.spring.ApplicationContextProvider;
import es.redsara.misim.misim_bus_webapp.EnvioMensajes;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortType;
import es.redsara.misim.misim_bus_webapp.peticion.Adjunto;
import es.redsara.misim.misim_bus_webapp.peticion.Adjuntos;
import es.redsara.misim.misim_bus_webapp.peticion.CamposCabecera;
import es.redsara.misim.misim_bus_webapp.peticion.CamposDetalleTrasero;
import es.redsara.misim.misim_bus_webapp.peticion.CamposPrincipales;
import es.redsara.misim.misim_bus_webapp.peticion.CamposSecundarios;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioMail;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.Destinatarios;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosMail;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeEmail;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.Mensajes;
import es.redsara.misim.misim_bus_webapp.peticion.Passbook;
import es.redsara.misim.misim_bus_webapp.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.peticion.PkFields;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;


/**
 * @author everis
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION })
public class EnvioMensajesTest extends AbstractJUnit38SpringContextTests{

	@Resource(name = "envioMensajesService")
	EnvioMensajesServiceWSBindingPortType envioMensajesService;
	
	private PropertiesServices ps;
	
	private ApplicationContextProvider context;
	
	private String url;
	
	private String logoText;
	
	private String description;
	
	private String backgroundColor;
	
	private String foregroundColor;
	
	private String passTypeIdentifier;
	
	private String serialNumber;
	
	private String authenticationToken;
	
	private String teamIdentifier;
	
	private String organizationName;
	
	private String template_path;
	
	private String keyStorePath;
		
	private String appleWWDRCA;
	
	private String keyStorePassword;
	
	@Test
	public final void testEnvioMensaje() {
		
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);
		
		enviarWebPush();
	}

	private void enviarEmail() {

		Peticion peticion = new Peticion();
	    Mensajes mensajes = new Mensajes();
		
		MensajeEmail mensajeEmail = new MensajeEmail();
		mensajeEmail.setAsunto("Pruebas");
		mensajeEmail.setCuerpo("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/><title>Novedades empleo</title><meta content=\"tetx/html; charset=ISO-8859-1\" http-equiv=\"Content-Type\"/></head><body><table style=\"width:100%;border-bottom:1px solid #666\" border=\"0\" cellpading=\"0\" cellspacing=\"0\"><tr><td style=\"padding-bottom:3px;\" valign=\"bottom\"><a href=\"http://administracion.gob.es\"><img alt=\"Ir a administracion.gob.es\" border=\"0\" src=\"http://administracion.gob.es/pag_Home/dms/pag/images/home/logo_admon.png\"/></a></td><td style=\"padding-bottom:3px;\" valign=\"bottom\" align=\"right\"><img alt=\"\" src=\" http://administracion.gob.es/pag_Home/dms/pag/images/home/logo_gobierno.png \"/></td></tr></table><div style=\"font-family:Verdana; font-size:11px; color:#000 \"><p>En el d&amp;iacute;a de hoy se han producido las siguientes novedades de empleo referidas a su suscripci&amp;oacute;n:</p><ul><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157497\" style=\"color:blue\">157497</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157497\" style=\"color:blue\">M&amp;Eacute;DICO DE ADMISI&amp;Oacute;N Y DOCUMENTACI&amp;Oacute;N CL&amp;Iacute;NICA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171139\" style=\"color:blue\">171139</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171139\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168346\" style=\"color:blue\">168346</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168346\" style=\"color:blue\">T&amp;Eacute;CNICO SUPERIOR EN DIET&amp;Eacute;TICA Y NUTRICI&amp;Oacute;N</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157499\" style=\"color:blue\">157499</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157499\" style=\"color:blue\">EPIDEMI&amp;Oacute;LOGO DE ATENCI&amp;Oacute;N PRIMARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170958\" style=\"color:blue\">170958</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170958\" style=\"color:blue\">FACULTATIVOS ESPECIALISTAS</a> (PERSONAL LABORAL).                                    (BOJA de 19/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171136\" style=\"color:blue\">171136</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171136\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168345\" style=\"color:blue\">168345</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168345\" style=\"color:blue\">T&amp;Eacute;CNICO SUPERIOR EN DOCUMENTACI&amp;Oacute;N SANITARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157500\" style=\"color:blue\">157500</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157500\" style=\"color:blue\">FARMAC&amp;Eacute;UTICO DE ATENCI&amp;Oacute;N PRIMARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157496\" style=\"color:blue\">157496</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157496\" style=\"color:blue\">M&amp;Eacute;DICO DE FAMILIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170048\" style=\"color:blue\">170048</a>;                <span style=\"color:red\">UNIVERSIDAD DE VALENCIA, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170048\" style=\"color:blue\">TECNICO SUPERIOR DE APOYO A LA INVESTIGACI&amp;Oacute;N</a> (PERSONAL LABORAL).                                    (DOCV de 20/10/2015).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169535\" style=\"color:blue\">169535</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE EDUCACI&amp;Oacute;N, JUVENTUD Y DEPORTE, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169535\" style=\"color:blue\">PROFESORES DE ENSE&amp;Ntilde;ANZA SECUNDARIA</a> (PERSONAL FUNCIONARIO).                                    (BOCM de 27/07/2015).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171033\" style=\"color:blue\">171033</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171033\" style=\"color:blue\">BI&amp;Oacute;LOGOS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=166924\" style=\"color:blue\">166924</a>;                <span style=\"color:red\">AGENCIA P&amp;Uacute;BLICA EMPRESARIAL SANITARIA HOSPITAL DE PONIENTE             , </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=166924\" style=\"color:blue\">FACULTATIVO SANITARIO ESPECIALISTA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 27/08/2014).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168840\" style=\"color:blue\">168840</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168840\" style=\"color:blue\">M&amp;Eacute;DICO ESPECIALISTA EN PEDIATR&amp;Iacute;A DE ATENCI&amp;Oacute;N PRIMARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168093\" style=\"color:blue\">168093</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168093\" style=\"color:blue\">VETERINARIOS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171394\" style=\"color:blue\">171394</a>;                <span style=\"color:red\">MINISTERIO DE DEFENSA, </span>                        N&amp;ordm; de plazas sin especificar de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171394\" style=\"color:blue\">CIENT&amp;Iacute;FICOS SUPERIORES DE LA DEFENSA</a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171138\" style=\"color:blue\">171138</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171138\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171037\" style=\"color:blue\">171037</a>;                <span style=\"color:red\">CONSEJERIA DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171037\" style=\"color:blue\">PROFESORES NUMERARIOS DE INSTITUTOS POLIT&amp;Eacute;CNICOS MAR&amp;Iacute;TIMO-PESQUEROS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 20/06/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171390\" style=\"color:blue\">171390</a>;                <span style=\"color:red\">MINISTERIO DE SANIDAD, SERVICIOS SOCIALES E IGUALDAD, </span><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171390\" style=\"color:blue\">FARMAC&amp;Eacute;UTICOS TITULARES </a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168316\" style=\"color:blue\">168316</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168316\" style=\"color:blue\">TECNICO SUPERIOR EN HIGIENE BUCODENTAL</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168318\" style=\"color:blue\">168318</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168318\" style=\"color:blue\">TECNICO SUPERIOR EN IMAGEN PARA EL DIAGNOSTICO CLINICO</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=159887\" style=\"color:blue\">159887</a>;                <span style=\"color:red\">CONSEJERIA DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=159887\" style=\"color:blue\">INSPECCI&amp;Oacute;N URBAN&amp;Iacute;STICA</a> (PERSONAL FUNCIONARIO).                                    (DOG de 10/06/2011).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168841\" style=\"color:blue\">168841</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168841\" style=\"color:blue\">M&amp;Eacute;DICO DE FAMILIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170846\" style=\"color:blue\">170846</a>;                <span style=\"color:red\">PRESIDENCIA DE LAS CORTES VALENCIANAS, </span>2 plazas de                          <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170846\" style=\"color:blue\">LETRADOS</a> (PERSONAL FUNCIONARIO).                                    (DOCV de 04/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171278\" style=\"color:blue\">171278</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>2 plazas de                          <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171278\" style=\"color:blue\">DOCTOR</a> (PERSONAL LABORAL).                                    (CIA de 23/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170115\" style=\"color:blue\">170115</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE INFRAESTRUCTURAS, ORDENACI&amp;Oacute;N DEL TERRITORIO Y MEDIO AMBIENTE, </span><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170115\" style=\"color:blue\">CONDUCTOR</a> (PRUEBAS DE CAPACITACI&amp;Oacute;N PROFESIONAL).                                    (BOPA de 30/10/2015).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                17/10/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171389\" style=\"color:blue\">171389</a>;                <span style=\"color:red\">MINISTERIO DE SANIDAD, SERVICIOS SOCIALES E IGUALDAD, </span><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171389\" style=\"color:blue\">FARMAC&amp;Eacute;UTICOS TITULARES </a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171032\" style=\"color:blue\">171032</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171032\" style=\"color:blue\">INGENIEROS INDUSTRIALES</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171034\" style=\"color:blue\">171034</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171034\" style=\"color:blue\">SUPERIOR DE FINANZAS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171035\" style=\"color:blue\">171035</a>;                <span style=\"color:red\">CONSEJERIA DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171035\" style=\"color:blue\">PROFESORES NUMERARIOS DE INSTITUTOS POLIT&amp;Eacute;CNICOS MAR&amp;Iacute;TIMO-PESQUEROS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168842\" style=\"color:blue\">168842</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168842\" style=\"color:blue\">MEDICO ESPECIALISTA EN MEDICINA DEL TRABAJO</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157493\" style=\"color:blue\">157493</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157493\" style=\"color:blue\">FACULTATIVO ESPECIALISTA DE &amp;Aacute;REA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157494\" style=\"color:blue\">157494</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157494\" style=\"color:blue\">PEDIATRA DE ATENCI&amp;Oacute;N PRIMARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171391\" style=\"color:blue\">171391</a>;                <span style=\"color:red\">MINISTERIO DE DEFENSA, </span>                        N&amp;ordm; de plazas sin especificar de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171391\" style=\"color:blue\">CIENT&amp;Iacute;FICOS SUPERIORES DE LA DEFENSA</a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171140\" style=\"color:blue\">171140</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>3 plazas de                          <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171140\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171038\" style=\"color:blue\">171038</a>;                <span style=\"color:red\">CONSEJERIA DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171038\" style=\"color:blue\">PROFESORES NUMERARIOS DE INSTITUTOS POLIT&amp;Eacute;CNICOS MAR&amp;Iacute;TIMO-PESQUEROS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 20/06/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171317\" style=\"color:blue\">171317</a>;                <span style=\"color:red\">CENTRO ESPA&amp;Ntilde;OL DE METROLOGIA, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171317\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 25/01/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170965\" style=\"color:blue\">170965</a>;                <span style=\"color:red\">I.U.ASTROF&amp;Iacute;SICA CANARIAS, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170965\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 22/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171143\" style=\"color:blue\">171143</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171143\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169130\" style=\"color:blue\">169130</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SANIDAD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169130\" style=\"color:blue\">LICENCIADO ESPECIALISTA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOCyL de 11/03/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171141\" style=\"color:blue\">171141</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171141\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170622\" style=\"color:blue\">170622</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=170622\" style=\"color:blue\">ENFERMERO/A ESPECIALISTA DEL TRABAJO</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171036\" style=\"color:blue\">171036</a>;                <span style=\"color:red\">CONSEJERIA DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171036\" style=\"color:blue\">PROFESORES NUMERARIOS DE INSTITUTOS POLIT&amp;Eacute;CNICOS MAR&amp;Iacute;TIMO-PESQUEROS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168317\" style=\"color:blue\">168317</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=168317\" style=\"color:blue\">TECNICO SUPERIOR DE LABORATORIO DE DIAGNOSTICO CL&amp;Iacute;NICO</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171031\" style=\"color:blue\">171031</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171031\" style=\"color:blue\">ARQUITECTOS</a> (PERSONAL FUNCIONARIO).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157498\" style=\"color:blue\">157498</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157498\" style=\"color:blue\">ODONTOESTOMAT&amp;Oacute;LOGO DE ATENCI&amp;Oacute;N PRIMARIA</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171392\" style=\"color:blue\">171392</a>;                <span style=\"color:red\">MINISTERIO DE DEFENSA, </span>                        N&amp;ordm; de plazas sin especificar de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171392\" style=\"color:blue\">CIENT&amp;Iacute;FICOS SUPERIORES DE LA DEFENSA</a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171137\" style=\"color:blue\">171137</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171137\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171265\" style=\"color:blue\">171265</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171265\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 22/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171393\" style=\"color:blue\">171393</a>;                <span style=\"color:red\">MINISTERIO DE DEFENSA, </span>                        N&amp;ordm; de plazas sin especificar de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171393\" style=\"color:blue\">CIENT&amp;Iacute;FICOS SUPERIORES DE LA DEFENSA</a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171142\" style=\"color:blue\">171142</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171142\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171396\" style=\"color:blue\">171396</a>;                <span style=\"color:red\">MINISTERIO DE DEFENSA, </span>                        N&amp;ordm; de plazas sin especificar de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171396\" style=\"color:blue\">ESPECIALISTA</a> (PERSONAL LABORAL).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169041\" style=\"color:blue\">169041</a>;                <span style=\"color:red\">SERVICIO GALLEGO DE SALUD (SERGAS), </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169041\" style=\"color:blue\">M&amp;Eacute;DICO DE URGENCIAS HOSPITALARIAS</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (DOG de 23/09/2013).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171043\" style=\"color:blue\">171043</a>;                <span style=\"color:red\">CONSELLER&amp;Iacute;A DE HACIENDA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171043\" style=\"color:blue\">M&amp;Eacute;DICO</a> (PERSONAL LABORAL).                                    (DOG de 26/02/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                15/07/2016.                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157514\" style=\"color:blue\">157514</a>;                <span style=\"color:red\">CONSEJER&amp;Iacute;A DE SALUD, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=157514\" style=\"color:blue\">T&amp;Eacute;CNICO DE SALUD</a> (PERSONAL ESTATUTARIO, MILITAR, OTRO).                                    (BOJA de 22/07/2010).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169856\" style=\"color:blue\">169856</a>;                <span style=\"color:red\">UNI&amp;Oacute;N EUROPEA, </span>                    Bolsa de empleo de                   <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169856\" style=\"color:blue\">ADMINISTRADORES</a> (PERSONAL LABORAL).                                    (DOUE de 07/10/2015).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169648\" style=\"color:blue\">169648</a>;                <span style=\"color:red\"> , </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=169648\" style=\"color:blue\">BI&amp;Oacute;LOGOS</a> (PERSONAL FUNCIONARIO).                                    (  de  ).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                                    (sin especificar).                  </li><li style=\"margin-bottom:6px\"><strong>Referencia:</strong><a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171144\" style=\"color:blue\">171144</a>;                <span style=\"color:red\">MINISTERIO DE ECONOMIA Y COMPETITIVIDAD, </span>1 plaza de                       <a href=\"http://administracion.gob.es/web/detalleConvocatoriaWeb.do?source=3&amp;id=171144\" style=\"color:blue\">TITULADO SUPERIOR</a> (PERSONAL LABORAL).                                    (CIA de 10/03/2016).                                  Fecha l&amp;iacute;mite de presentaci&amp;oacute;n de instancias:                31/05/2016.                  </li></ul></div><div style=\"font-family:Verdana; font-size:11px; color:#000;margin-bottom:15px \">          Informaci&amp;oacute;n completa en el Punto de Acceso General (<span style=\"font-weight:bold\"><a href=\"http://administracion.gob.es\" style=\"color:blue\">http://administracion.gob.es</a></span>) &amp;oacute; tel&amp;eacute;fono 060.<br/><br/><b>IMPORTANTE:</b> no conteste a este correo, la direcci&amp;oacute;n desde la que se env&amp;iacute;a este correo no est&amp;aacute; habilitada para la recepci&amp;oacute;n de mensajes.<br/><ul><li><b>BAJA:</b> Si desea darse de baja de esta suscripci&amp;oacute;n <a href=\"http://administracion.gob.es/pagFront/empleoBecas/empleo/gestionSuscripcionesEmpleo/acceso/bajaSuscripcionEmpleo.htm?clave=bcf6489f797dcc8df9006d4e723fedb3\" style=\"color:blue\">pulse aqu&amp;iacute;</a></li><li><b>EDICI&amp;Oacute;N:</b> Si desea modificar la configuraci&amp;oacute;n de esta suscripci&amp;oacute;n <a href=\"http://administracion.gob.es/pagFront/empleoBecas /empleo/gestionSuscripcionesEmpleo/acceso/editarSuscripcionEmpleo.htm?clave=bcf6489f797dcc8df9006d4e723fedb3\" style=\"color:blue\">pulse aqu&amp;iacute;</a></li><li><b>GESTI&amp;Oacute;N:</b> Si desea acceder a la gesti&amp;oacute;n de todas sus suscripciones de empleo <a href=\"http://administracion.gob.es/pagFront/empleoBecas/empleo/gestionSuscripcionesEmpleo/acceso/verGestionSuscripciones.htm?tipo=empleo&amp;clave=bcf6489f797dcc8df9006d4e723fedb3\" style=\"color:blue\">pulse aqu&amp;iacute;</a></li></ul></div><div style=\"font-family:Verdana; font-size:9px; color:#666 \"><div style=\"padding:3px 3px 3px 0px ;background-color:#e8edf1\">- Aviso legal</div><p style=\"padding:3px 3px 3px 20px;margin:1px\"><span style=\"font-weight:bold\"><a href=\"http://administracion.gob.es\" style=\"color:blue\">administracion.gob.es</a></span>            es un dominio en Internet de la titularidad del Ministerio de Hacienda y Administraciones P&amp;uacute;blicas. M&amp;ordf; de Molina, 50. 28071 Madrid. Tel&amp;eacute;fono: 060.          </p><ul style=\"margin:3px 3px 3px 40px;padding:0px 0px 0px 5px\"><li style=\"list-style-image:url(http://administracion.gob.es/images/topo_listado_boletin.gif)\">              La fecha que figura en el plazo de presentaci&amp;oacute;n tiene car&amp;aacute;cter indicativo. En convocatorias de la Administraci&amp;oacute;n General del Estado s&amp;oacute;lo se han considerado inh&amp;aacute;biles las fiestas de &amp;aacute;mbito nacional; en convocatorias de Comunidades Aut&amp;oacute;nomas y Entidades Locales, las fiestas de &amp;aacute;mbito nacional y de la Comunidad Aut&amp;oacute;noma correspondiente. Este c&amp;oacute;mputo est&amp;aacute; sujeto a las incidencias derivadas de las prescripciones establecidas en el art&amp;iacute;culo 48 de la Ley 30/1992, de 26 de noviembre, BOE de 27 de noviembre de 1992 y 14 de enero de 1999.            </li></ul></div></body></html>");
//		mensajeEmail.setCuerpo("Buenas, Indicar que para esta prueba he generado el passbook desde el fichero de properties.");
				/*, donde las propiedades"
				+ " son las siguiente:"
				+ "<ul><li># Passbook config</li>"
				+"<li>url=https://des-sede-administracion.redsara.es/pagSedeFront/servicios/descargaCSV.htm?csvquery=FA5v6p6pRvz---2BJKj7CUhnu8nwPJEit7Clsi---2BbpgsMlqV4VakYkYUXmsqW5---2Bp0CFYtmJ---2Bar6R719OTviEU8tEXYA---3D---3D </li>"
				+"<li>logoText=logo text                                                           </li> "
				+"<li>description=My PassBook                                                      </li> "
				+"<li>backgroundColor=rgb(102, 153, 255)                                           </li> "
				+"<li>foregroundColor=rgb(255,255,255)                                             </li> "
				+"<li>passTypeIdentifier=pass.com.kudit.generic                                    </li> "
				+"<li>serialNumber=000000007                                                       </li> "
				+"<li>authenticationToken=vxwxd7J8AlNNFPS8k0a0FfUFtq0ewzFdc                        </li> "
				+"<li>teamIdentifier=myTeamId                                                      </li> "
				+"<li>organizationName=OrgName                                                     </li> "
				+"<li>camposPrincipales.1.key=nombre                                               </li> "
				+"<li>camposPrincipales.1.label=Nombre                                             </li> "
				+"<li>camposPrincipales.1.value=Antonio Romera Cifuentes                           </li> "
				+"<li>keyStorePath=C:/proyectos/MINHAP/SIM/SIM-156/PRE-Componente_SGPEIAE.p12      </li> "
				+"<li>appleWWDRCA=C:/proyectos/MINHAP/SIM/SIM-156/AppleWWDRCA.pem                  </li> "
				+"<li>template_path=C:/template/                      </li>"
				+"<li>keyStorePassword=changeit                       </li>"
				+"<li>                                                </li>"
				+"<li>camposCabecera.1.key=uno                        </li>"
				+"<li>camposCabecera.1.label=Uno                      </li>"
				+"<li>camposCabecera.1.value=campo Uno                </li>"
				+"<li>camposCabecera.2.key=dos                        </li>"
				+"<li>camposCabecera.2.label=Dos                      </li>"
				+"<li>camposCabecera.2.value=campos Dos               </li>"
				+"<li>camposCabecera.3.key=tres                       </li>"
				+"<li>camposCabecera.3.label=Tres                     </li>"
				+"<li>camposCabecera.3.value=campo Tres               </li>"
				+"<li>                                                </li>"
				+"<li>                                                </li>"
				+"<li>camposSecundarios.1.key=unoS                    </li>"
				+"<li>camposSecundarios.1.label=UnoS                  </li>"
				+"<li>camposSecundarios.1.value=campo UnoS            </li>"
				+"<li>camposSecundarios.2.key=dosS                    </li>"
				+"<li>camposSecundarios.2.label=DosS                  </li>"
				+"<li>camposSecundarios.2.value=campos DosS           </li>"
				+"<li>camposSecundarios.3.key=tresS                   </li>"
				+"<li>camposSecundarios.3.label=TresS                 </li>"
				+"<li>camposSecundarios.3.value=campo TresS           </li>"
				+"<li>                                                </li>"
				+"<li>camposDetalleTrasero.1.key=unoDT                </li>"
				+"<li>camposDetalleTrasero.1.label=UnoDT              </li>"
				+"<li>camposDetalleTrasero.1.value=campo UnoDT        </li>"
				+"<li>camposDetalleTrasero.2.key=dosDT                </li>"
				+"<li>camposDetalleTrasero.2.label=DosDT              </li>"
				+"<li>camposDetalleTrasero.2.value=campos DosDT       </li>"
				+"<li>camposDetalleTrasero.3.key=tresDT               </li>"
				+"<li>camposDetalleTrasero.3.label=TresDT             </li>"
				+"<li>camposDetalleTrasero.3.value=campo TresDT       </li>"
				+ "</ul>");*/
		mensajeEmail.setModo("1");
		mensajeEmail.setOrigen("origen");
		
		Destinatarios  destinatarios = new Destinatarios();
		//destinatarios.setTo("franciscojavierescobar@gmail.com");
		destinatarios.setTo("anavarrd@everis.com");
		//destinatarios.setTo("anavarrd@everis.com");
		//destinatarios.setTo("armando.enrique.gutierrez.gouverneur@everis.com");
		//destinatarios.setTo("javier.porti.hernandez@everis.com");
		DestinatarioMail  destinatarioMail = new DestinatarioMail();
		destinatarioMail.setIdExterno("IdExterno");
		destinatarioMail.setDocUsuario("Docusuario");
		destinatarioMail.setDestinatarios(destinatarios);
		
		DestinatariosMail destinatariosMail = new DestinatariosMail();
		destinatariosMail.getDestinatarioMail().add(destinatarioMail);
		mensajeEmail.setDestinatariosMail(destinatariosMail);
		String nombre = "PRO_Formulario_Acceso_FAMILIA_NUMEROSA_APLICACION-CCAA_v1.0.pdf_firmado.pdf";
		String ficheroPath="C:/Users/anavarrd/Desktop/" + nombre;
		File fichero = new File(ficheroPath);
		
		String contenido = null;
		
		try{
			contenido = Base64.encodeBase64String(ModelTestUtil.getBytesFromFile(fichero));
		}catch (IOException e){
			e.printStackTrace();
		}
		
		if (contenido!=null){
			
			Adjunto adjunto = new Adjunto();
			Adjuntos adjuntos = new Adjuntos();
			adjunto.setContenido(contenido);
			adjunto.setNombre(nombre);
			adjuntos.getAdjunto().add(adjunto);
			mensajeEmail.setAdjuntos(adjuntos);
		}
//		String passbook = generatePassbook();
		
//		String passbook = null;
//		File passbokGenerado = null;
//		if (passbook != null) {
//			if (mensajeEmail.getAdjuntos() != null) {
//				Adjunto adjunto = new Adjunto();
//				passbokGenerado  = new File(passbook);
//				setGeneratedPassbook(adjunto, passbokGenerado, passbook);				
//				mensajeEmail.getAdjuntos().getAdjunto().add(adjunto);
//			} else {
//				Adjuntos adjuntos = new Adjuntos();
//				Adjunto adjunto = new Adjunto();
//				passbokGenerado  = new File(passbook);
//				setGeneratedPassbook(adjunto, passbokGenerado, passbook);
//				adjuntos.getAdjunto().add(adjunto);
//				mensajeEmail.setAdjuntos(adjuntos);
//			}
//		}
		
		Passbook passBook = new Passbook();
		
		passBook.setURL("https://des-sede-administracion.redsara.es/pagSedeFront/servicios/descargaCSV.htm?csvquery=FA5v6p6pRvz---2BJKj7CUhnu8nwPJEit7Clsi---2BbpgsMlqV4VakYkYUXmsqW5---2Bp0CFYtmJ---2Bar6R719OTviEU8tEXYA---3D---3D");
		passBook.setLogoText("Texto del logo");
		passBook.setDescription("DEscripcion");
		
		CamposPrincipales principales = new CamposPrincipales();
		
		PkFields pkField1 = new PkFields();
		
		pkField1.setKey("Principal 1 - key");
		pkField1.setLabel("Principal 1 - label");
		pkField1.setValue("Principal 1 - value");
		
		principales.setPkFields(pkField1);
		
		passBook.setCamposPrincipales(principales);
		
		CamposCabecera cabecera = new CamposCabecera();
		
		PkFields pkField2 = new PkFields();
		
		pkField2.setKey("Cabecera 1 - key");
		pkField2.setLabel("Cabecera 1 - label");
		pkField2.setValue("Cabecera 1 - value");
		
		cabecera.getPkFields().add(pkField2);
		
		PkFields pkField3 = new PkFields();
		
		pkField3.setKey("Cabecera 2 - key");
		pkField3.setLabel("Cabecera 2 - label");
		pkField3.setValue("Cabecera 2 - value");
		
		cabecera.getPkFields().add(pkField3);
		
		passBook.setCamposCabecera(cabecera);
		
		CamposSecundarios secundarios = new CamposSecundarios();
		
		PkFields pkField5 = new PkFields();
		
		pkField5.setKey("Trasero 1 - key");
		pkField5.setLabel("Trasero 1 - label");
		pkField5.setValue("Trasero 1 - value");
		
		secundarios.getPkFields().add(pkField5);
		
		passBook.setCamposSecundarios(secundarios);
		
		CamposDetalleTrasero trasero = new CamposDetalleTrasero();
		
		PkFields pkField6 = new PkFields();
		
		pkField6.setKey("Trasero 1 - key");
		pkField6.setLabel("Trasero 1 - label");
		pkField6.setValue("Trasero 1 - value");
		
		trasero.getPkFields().add(pkField6);
		
		passBook.setCamposDetalleTrasero(trasero);
		
		mensajeEmail.setPassBook(passBook);
			
		
		mensajes.getMensajeEmail().add(mensajeEmail);
		peticion.setMensajes(mensajes);
		EnvioMensajes envioMensajes = EnvioMensajes.getInstance();
		envioMensajes.setContext(context);
		envioMensajes.setPeticion(peticion);
		Respuesta respuesta = null;
		
		try{
			
			respuesta = envioMensajes.sendMessage(envioMensajesService);
			System.out.println("Respuestas: " + respuesta.getStatus().getStatusCode());
//			if (passbokGenerado != null) {
//				passbokGenerado.delete();
//			}
			
		}catch(Exception e){
			System.out.println("Respuesta: " );
			e.printStackTrace();
		}
		
	}
	
	private void enviarWebPush() {

		Peticion peticion = new Peticion();
	    Mensajes mensajes = new Mensajes();
	    
	    MensajeWebPush mensajeWp = new MensajeWebPush();
	    mensajeWp.setCuerpo("hola este el cuerpo");
	    mensajeWp.setTitulo("SOY el titulo");
		
		
		
		
		
		DestinatarioWebPush  destinatarioWp = new DestinatarioWebPush();
		destinatarioWp.setIdExterno("IdExterno");
		destinatarioWp.setDocUsuario("Docusuario");
		destinatarioWp.setIdentificadorUsuario("vpe24v114csboagelh90le80crlkcomq");
		
		DestinatariosWebPush  destinatarios = new DestinatariosWebPush();
		destinatarios.getDestinatarioWebPush().add(destinatarioWp);
		mensajeWp.setDestinatariosWebPush(destinatarios);
		
		
		
		mensajes.getMensajeWebPush().add(mensajeWp);
		peticion.setMensajes(mensajes);
		EnvioMensajes envioMensajes = EnvioMensajes.getInstance();
		envioMensajes.setPeticion(peticion);
		Respuesta respuesta = null;
		
		try{

			respuesta = envioMensajes.sendMessage(envioMensajesService);
			System.out.println("Respuestas: " + respuesta.getStatus().getStatusCode());
			
		}catch(Exception e){
			System.out.println("Respuesta: " );
			e.printStackTrace();
		}
		
	}

	
//	private void setGeneratedPassbook(Adjunto adjunto,File passbokGenerado, String passbook) {
//		try{
//			String contenido = Base64.encodeBase64String(ModelTestUtil.getBytesFromFile(passbokGenerado));
//			adjunto.setContenido(contenido);
//			adjunto.setNombre(passbokGenerado.getName());
//
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//	}

//	private String generatePassbook() {
//		String fichero = null;
//		PropertiesServices ps = new PropertiesServices(context);
//		loadPassbookProperties(ps);
//		
//		List<PKField> camposPrincipales = loadCampos("camposPrincipales.");
//		List<PKField> camposSecundarios = loadCampos("camposSecundarios.");
//		List<PKField> camposCabecera = loadCampos("camposCabecera.");
//		List<PKField> camposDetalleTrasera = loadCampos("camposDetalleTrasero.");
//		if (url == null || description== null || passTypeIdentifier == null || serialNumber== null || authenticationToken== null 
//				|| teamIdentifier == null || organizationName == null ||  keyStorePath == null 
//				|| appleWWDRCA == null || keyStorePassword == null) {
//			logger.error("No se puede generar el fichero pkpass al no tener alguno de los siguientes parametros:");
//			logger.error("1. url");
//			logger.error("2. description");
//			logger.error("3. passTypeIdentifier");
//			logger.error("4. serialNumber");
//			logger.error("5. authenticationToken");
//			logger.error("6  teamIdentifier");
//			logger.error("6. organizationName");
//			logger.error("7. keyStorePath");
//			logger.error("7. appleWWDRCA");
//			logger.error("7. keyStorePassword");
//		} else {
//			try {
//				fichero = PassbookGenerator.generate(camposPrincipales, camposSecundarios, camposCabecera, 
//						camposDetalleTrasera, url, logoText, description, backgroundColor, foregroundColor, passTypeIdentifier, 
//						serialNumber, authenticationToken, teamIdentifier, organizationName, template_path, appleWWDRCA, keyStorePath, keyStorePassword, "");
//			} catch (Exception e) {
//				logger.error("Se ha producido un error al generar el passbook " , e);
//			}
//			
//		}
//		return fichero;
//	}
//
//	private List<PKField> loadCampos(String propertie) {
//		List<PKField> campos = new ArrayList<PKField>();
//		for (int i= 0; i <= 3; i++) {
//			if (ps.getMessage(propertie+String.valueOf(i) + ".key", null, null, null) != null && 
//					ps.getMessage(propertie+String.valueOf(i) + ".label", null, null, null) != null &&
//					ps.getMessage(propertie+String.valueOf(i) + ".value", null, null, null) != null) {
//				PKField pkField  = new PKField(ps.getMessage(propertie+String.valueOf(i) + ".key", null, null, null), 
//						ps.getMessage(propertie+String.valueOf(i) + ".label", null, null, null), 
//						ps.getMessage(propertie+String.valueOf(i) + ".value", null, null, null));
//				campos.add(pkField);
//			}
//			
//		}
//		return campos;
//	}

//	private void loadPassbookProperties(PropertiesServices ps) {
//		url = ps.getMessage("url", null, null, null);
//		logoText = ps.getMessage("logoText", null, null, null);
//		description = ps.getMessage("description", null, null, null);
//		backgroundColor = ps.getMessage("backgroundColor", null, null, null);
//		foregroundColor = ps.getMessage("foregroundColor", null, null, null);
//		passTypeIdentifier = ps.getMessage("passTypeIdentifier", null, null, null);
//		serialNumber = ps.getMessage("serialNumber", null, null, null);
//		authenticationToken = ps.getMessage("authenticationToken", null, null, null);
//		teamIdentifier = ps.getMessage("teamIdentifier", null, null, null);
//		organizationName = ps.getMessage("organizationName", null, null, null);
//		template_path = ps.getMessage("template_path", null, null, null);
//		appleWWDRCA = ps.getMessage("appleWWDRCA", null, null, null);
//		keyStorePath = ps.getMessage("keyStorePath", null, null, null);
//		keyStorePassword = ps.getMessage("keyStorePassword", null, null, null);
//	}


	/**
	 * @param args
	 */

}
