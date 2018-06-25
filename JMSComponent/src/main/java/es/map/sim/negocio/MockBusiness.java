package es.map.sim.negocio;

import es.map.sim.negocio.modelo.MensajeJMS;

public class MockBusiness {
	public void doSomething(MensajeJMS msg){
		System.out.println("Recibido mensaje: "+msg.toString());
		throw new RuntimeException("");
	}
}
