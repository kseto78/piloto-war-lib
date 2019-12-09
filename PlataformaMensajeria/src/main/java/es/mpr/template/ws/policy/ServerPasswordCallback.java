package es.mpr.template.ws.policy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;

/**
 * <p>Clase que gestiona los usuarios (alias) y sus contrase&ntilde;a para permitir el mecanismo callback en WS-Security<p>.
 *
 * @author Altran
 */
public class ServerPasswordCallback implements CallbackHandler {

    protected static final String STOREPASS = "storepassword";
	/**  passwords. */
    private Map<String, String> passwords =
        new HashMap<>();

    /**
     * <p>Establecemos cada alias/contrase&ntilde;a</p>.
     */
    public ServerPasswordCallback() {
    	passwords.put("framework","changeit");
        passwords.put("clientx509v1", STOREPASS);
        passwords.put("serverx509v1", STOREPASS);
    }

    /**
     * <p>Validamos la contrase&ntilde;a para cada objeto Callback</p>.
     *
     * @param callbacks 		{@link Callback[]}
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedCallbackException the unsupported callback exception
     */
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
            
            String pass = passwords.get(pc.getIdentifier());
            if (pc.getType()!=null) {
	            		
            	//Validamos la password si viene en texto plano
            	
            	if (WSConstants.PASSWORD_DIGEST.equals(pc.getType())) {
            		pc.setPassword(pass);
            		return;
            	} else
            		if (pass!=null && pass.equals(pc.getPassword())) {
						return;
					} else {
						throw new IOException();
					}
            } else if (pc.getIdentifier()!=null) {
            		//Consideramos valido el usuario o alias
            		pc.setPassword(pass);
            		return;
            	 }
        }
    }

    /**
     * <p>Agrega un nuevo par usuario/contrase&ntilde;a para el mecanismo de callback.</p>
     * 
     * @param alias Usuario o alias de la clave
     * @param password Contrase&ntilde;a
     */
    public void setAliasPassword(String alias, String password) {
        passwords.put(alias, password);
    }
}
