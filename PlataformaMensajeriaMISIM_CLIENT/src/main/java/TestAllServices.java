import java.net.MalformedURLException;



public class TestAllServices {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		try {

			es.redsara.misim.misim_bus_webapp.enviarmensaje.respuesta.Respuesta r1;
			/*r1 = es.redsara.misim.misim_bus_webapp.enviarmensaje.EnviarMensajeService.enviarMensaje("pruebasSIMdes", "pruebasSIMdes", "SMS", "VODAFONE", "1234", "99999", "pruebasSIMdes","pruebasSIMdes","649592368", "215039", "PREUBA ENVIO");

			System.out.println(r1.getMessageId());
			System.out.println(r1.getStatus().getStatusCode());
			System.out.println(r1.getStatus().getStatusText());
			System.out.println(r1.getStatus().getDetails());
			*/
			
			
			System.out.println("--------------------------");

			
			es.redsara.misim.misim_bus_webapp.consultarestado.respuesta.Respuesta r2;
			r2 = es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.consultarEstado("pruebasSIMdes", "pruebasSIMdes","ESTADO_SMS", "VODAFONE", "1234", "99999","pruebasSIMdes","pruebasSIMdes","SwitchSMS_MT_84205_9871","215039");

			System.out.println(r2.getMessageId());
			System.out.println(r2.getStatus().getStatusCode());
			System.out.println(r2.getStatus().getStatusText());
			System.out.println(r2.getStatus().getDetails());
			System.out.println("---");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
