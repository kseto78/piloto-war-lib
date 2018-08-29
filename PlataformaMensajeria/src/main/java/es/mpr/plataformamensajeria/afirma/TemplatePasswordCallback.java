package es.mpr.plataformamensajeria.afirma;

import es.mpr.j2ee.afirma.wss4j.FirmaPasswordCallback;

/**
 * Clase TemplatePasswordCallback
 * @author ralberoc
 *
 */
public class TemplatePasswordCallback extends FirmaPasswordCallback {
	/**
	 * Metodo que añade el usuario y password 
	 */
	public TemplatePasswordCallback() {
    	passwords.put("framework","changeit");
    	
    	//passwords.put("accv-ca2", "password");
    }
	
}
