package es.mpr.plataformamensajeria.afirma;

import es.mpr.j2ee.afirma.wss4j.FirmaPasswordCallback;

public class TemplatePasswordCallback extends FirmaPasswordCallback {

	public TemplatePasswordCallback() {
    	passwords.put("framework","changeit");
    	
    	//passwords.put("accv-ca2", "password");
    }
	
}
