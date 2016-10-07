/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.mpr.plataformamensajeria.ws.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import es.mpr.plataformamensajeria.beans.AdjuntosXMLBean;
import es.mpr.plataformamensajeria.beans.ConsultaEstadoBean;
import es.mpr.plataformamensajeria.beans.ConsultaHistoricoBean;
import es.mpr.plataformamensajeria.beans.DestinatarioXMLBean;
import es.mpr.plataformamensajeria.beans.EnvioEmailXMLBean;
import es.mpr.plataformamensajeria.beans.EnvioSMSXMLBean;
import es.mpr.plataformamensajeria.beans.MensajeSMSXMLBean;
import es.mpr.plataformamensajeria.beans.MensajesXMLBean;
import es.mpr.plataformamensajeria.response.RespuestaConsultaEstado;
import es.mpr.plataformamensajeria.response.RespuestaConsultaHistorial;
import es.mpr.plataformamensajeria.response.RespuestaEnvioXMLBean;
import es.mpr.plataformamensajeria.response.RespuestaOperacion;
import es.mpr.plataformamensajeria.ws.envio.IEnvioMensajesService;
import es.mpr.plataformamensajeria.ws.exceptions.IncompleteMessageException;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;
import es.mpr.plataformamensajeria.ws.operaciones.IOperacionesMensajesService;
import es.mpr.plataformamensajeria.ws.seguimiento.ISeguimientoMensajesService;

/**
 *  En este test crearemos un cliente del Web Service utilizando Apache CXF.
 *  El cliente se crea dinámicamente a través de Spring. En el archivo de
 *  configuración de Spring applicationContext.xml de este test se declara
 *  el cliente, el cual cumplirá con la interfaz del Web Service. 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"applicationTest.xml"})
public class ClienteTest {

    @Autowired
    private IEnvioMensajesService instance;
    
    @Autowired
    private ISeguimientoMensajesService instanceSeguimiento;
    
    @Autowired
    private IOperacionesMensajesService instanceOperaciones;

    public static byte[] getBytesFromFile(File file) throws IOException {        
        long length = file.length();

        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        if (offset < bytes.length) {
            throw new IOException(""+file.getName());
        }
        return bytes;
    }
    Integer LOTE_PRUEBAS = 88;//84//86
    Integer MENSAJE_PRUEBAS = 111;
    @Test 
    public void testReenviarLote() throws PlataformaBusinessException{
    	System.out.println("testReenviarLote");
    	String resultado = instanceOperaciones.reenviarLote(LOTE_PRUEBAS, "jose", "1234");
    	RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
    	respuestaOperacion.parseResponse(resultado);
    	if(respuestaOperacion.getErrores().isEmpty()){
    		System.out.println("Ejecutado correctamente. Se ha reenviado el lote: " + LOTE_PRUEBAS);
    	}else{
    		System.out.println("Error reenviando lote " + LOTE_PRUEBAS+ " | " + respuestaOperacion.getErrores().get(0));
    	}
    		
    }
    @Test 
    public void testAnularLote() throws PlataformaBusinessException{
    	System.out.println("testReenviarLote");
    	String resultado = instanceOperaciones.anularLote(LOTE_PRUEBAS, "jose", "1234");
    	RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
    	respuestaOperacion.parseResponse(resultado);
    	if(respuestaOperacion.getErrores().isEmpty()){
    		System.out.println("Ejecutado correctamente. Se ha anulado el lote: " + LOTE_PRUEBAS);
    	}else{
    		System.out.println("Error anulando lote " + LOTE_PRUEBAS+ " | " + respuestaOperacion.getErrores().get(0));
    	}
    		
    }
    @Test 
    public void testReenviarMensaje() throws PlataformaBusinessException{
    	System.out.println("testReenviarMensaje");
    	String resultado = instanceOperaciones.reenviarMensaje(MENSAJE_PRUEBAS, "Roberto", "1234");
    	RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
    	respuestaOperacion.parseResponse(resultado);
    	if(respuestaOperacion.getErrores().isEmpty()){
    		System.out.println("Ejecutado correctamente. Se ha reenviado el mensaje: " + MENSAJE_PRUEBAS);
    	}else{
    		System.out.println("Error reenviando mensaje: " + MENSAJE_PRUEBAS+ " | " + respuestaOperacion.getErrores().get(0));
    	}
    		
    }
    @Test 
    public void testAnularMensaje() throws PlataformaBusinessException{
    	System.out.println("testAnularMensaje");
    	String resultado = instanceOperaciones.anularMensaje(MENSAJE_PRUEBAS, "Roberto", "1234");
    	RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
    	respuestaOperacion.parseResponse(resultado);
    	if(respuestaOperacion.getErrores().isEmpty()){
    		System.out.println("Ejecutado correctamente. Se ha anulado el mensaje: " + MENSAJE_PRUEBAS);
    	}else{
    		System.out.println("Errro anulando mensaje: "  + MENSAJE_PRUEBAS + " | " + respuestaOperacion.getErrores().get(0));
    	}
    		
    }  
    @Test
    public void testConsultarEstado() throws PlataformaBusinessException{
    	System.out.println("testConsultarEstado");
    	
    	String resultado = instanceSeguimiento.consultarEstado(0, 0, 0, 0, 0, null, 0, "26/01/2012", "26/01/2012", "jose", "1234");
    	System.out.println(resultado);
    	RespuestaConsultaEstado estado = new RespuestaConsultaEstado();
    	try {
			estado.parseResponse(resultado);
		} catch (PlataformaBusinessException e) {
			throw new PlataformaBusinessException("[TEST] Error consultado estado");
		}
    	ArrayList<ConsultaEstadoBean> resultados = estado.getResultados();
    	int i=0;
    	for (ConsultaEstadoBean consultaEstadoBean : resultados) {
			System.out.println(consultaEstadoBean.getAplicacion() + " || " + consultaEstadoBean.getFecha() + " || "+ consultaEstadoBean.getCanal() + " || " + consultaEstadoBean.getEstado() + " || " + consultaEstadoBean.getIdMensaje());
			i++;
		}
    	System.out.println("Total resultados: " + i);
    	
    }
    @Test
    public void testConsultarHistorico() throws PlataformaBusinessException{
    	System.out.println("testConsultarHistorico");
    	
    	String resultado = instanceSeguimiento.consultarHistorial(130, null, "jose", "1234");
    	//System.out.println(resultado);
    	RespuestaConsultaHistorial respuesta = new RespuestaConsultaHistorial();
    	respuesta.parseResponse(resultado);
    	ArrayList<ConsultaHistoricoBean> listado = respuesta.getResultados();
    	int i=0;
    	for (ConsultaHistoricoBean consultaHistoricoBean : listado) {
			System.out.println(consultaHistoricoBean.getIdMensaje() + " || " + consultaHistoricoBean.getIdExterno() + " || " +  consultaHistoricoBean.getFecha());
			i++;
		}
    	System.out.println("Total resultados: " + i);
    	
    }
    @Test
    public void testEnviarEmail()throws PlataformaBusinessException{
        System.out.println("testEnviarEmail");
        
        EnvioEmailXMLBean envio = new EnvioEmailXMLBean();
        envio.setNombreLote("LOTE-WS-JUAN-4");
        envio.setServicio("168");
        envio.setUsuario("jose");
        envio.setPassword("1234");
        MensajesXMLBean mensaje = new MensajesXMLBean();
        mensaje.setAsunto("Asunto Mensaje - MSG_JUAN_3");
        mensaje.setCuerpo("Env�o de prueba a trav�s del servicio web ");
        mensaje.setIdExterno("MSG_JUAN_3");
        mensaje.setModo("1");
        DestinatarioXMLBean destinatario = new DestinatarioXMLBean();
        destinatario.setEmailDestinatario("correoincorrecto");
        destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_TO);
        mensaje.addDestinatario(destinatario);
        destinatario = new DestinatarioXMLBean();
        destinatario.setEmailDestinatario("correo1@i-nercya.com;correo2@i-nercya.com");
        destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_CC);
        mensaje.addDestinatario(destinatario);
        AdjuntosXMLBean adjunto1 = new AdjuntosXMLBean();
        adjunto1.setNombre("FP_BBDD060-2011_INERCYA.doc");
        File fichero = new File("C:\\FP_BBDD060-2011_INERCYA.doc");
        try {
			adjunto1.setContenido(getBytesFromFile(fichero));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mensaje.addAdjunto(adjunto1);
        envio.addMensaje(mensaje);
        String result = null;
		try {
			String xml = envio.toXML();
			System.out.println(xml);
			result = instance.enviarEmail(xml);
		} catch (IncompleteMessageException e) {
			throw new PlataformaBusinessException("Se ha producido un error enviando el mensaje");
		}
		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
        System.out.println(result);
    }

    /**
     * @throws PlataformaBusinessException 
     * 
     */
    @Test
    public void testEnviarMensaje() throws PlataformaBusinessException {
        EnvioSMSXMLBean envio = new EnvioSMSXMLBean();
        envio.setNombreLote("LOTE-WS-JUAN-5-sms");
        envio.setServicio("174"); //sms
        envio.setUsuario("jose");
        envio.setPassword("1234");
        MensajeSMSXMLBean sms = new MensajeSMSXMLBean();
        sms.setIdExterno("ID_SMS_SELERED");
        sms.setCuerpo("Mensaje de texto prueba selered");
        sms.addDestinatario("690039102");
        envio.addMensaje(sms);
        sms = new MensajeSMSXMLBean();
        sms.setIdExterno("ID_SMS_SELERED_2");
        sms.setCuerpo("Mensaje de texto prueba selered 2");
        sms.addDestinatario("690039102");
        envio.addMensaje(sms);
        try {
			String xmlEnvioSMS = envio.toXML();
			String result = instance.enviarMensaje(xmlEnvioSMS);
			RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
			respuesta.parseResponse(result);
			System.out.println(result);
		} catch (IncompleteMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}