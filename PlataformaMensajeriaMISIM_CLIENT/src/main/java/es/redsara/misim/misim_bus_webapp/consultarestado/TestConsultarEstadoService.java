package es.redsara.misim.misim_bus_webapp.consultarestado;

import java.net.MalformedURLException;

import es.redsara.misim.misim_bus_webapp.consultarestado.respuesta.Respuesta;



public class TestConsultarEstadoService {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
	testConsultaEstado();


	}

	public static void testConsultaEstado() throws MalformedURLException {

		try {

		
			Respuesta r = ConsultarEstadoService.consultarEstado("pruebasSIMdes", "pruebasSIMdes","ESTADO_SMS", "VODAFONE", "29284", "29284","pruebasSIMdes","pruebasSIMdes", "SwitchSMS_MT_84205_9871","215039");

			System.out.println(r.getMessageId());
			System.out.println(r.getStatus().getStatusCode());
			System.out.println(r.getStatus().getStatusText());
			System.out.println(r.getStatus().getDetails());
			System.out.println("---");
			
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}
