package es.redsara.misim.misim_bus_webapp.enviarmensaje;

import java.net.MalformedURLException;

public class TestEnviarMensajeService {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		testEnviarMensaje();
	}

	public static void testEnviarMensaje() throws MalformedURLException {

		try {
			/*
			Respuesta r = EnviarMensajeService.enviarMensaje("pruebasSIMdes", "pruebasSIMdes", "SMS", "VODAFONE", "1234", "99999", "pruebasSIMdes", "pruebasSIMdes", "649592368", "215039", "PREUBA ENVIO");

			System.out.println(r.getMessageId());
			System.out.println(r.getStatus().getStatusCode());
			System.out.println(r.getStatus().getStatusText());
			System.out.println(r.getStatus().getDetails());
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
