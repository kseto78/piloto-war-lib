package es.minhap.plataformamensajeria.iop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.encoding.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static Logger LOG = LoggerFactory.getLogger(Utils.class);
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			LOG.error("Error en Utils",e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error("Error en Utils",e);
				}
			}
		}

		return sb.toString();

	}
	
	 public static String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"));
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
	 
		 
	 public static int validarTelefono(String telefono, String telefonoExcepcion){
		 
		 if (telefono!=null){
			 telefono= telefono.trim();
			 if(telefonoExcepcion.contains(telefono)) {
				 return 0;
			 }
		 } else {
			 return 1;
		 }
				 
		 if (telefono.length() == 9){
			 //comprobamos si son todos digitosif (cadenaUno.matches("[0-9]*"))
			 if (telefono.matches("[0-9]*")){ 
			 	return 0;
			 }
			  else{
			   return 1;
			  }
		 }else{
			 if (telefono.substring(0,1).equals("0")){
				 if (telefono.substring(1, telefono.length()).matches("[0-9]*")){
					 return 0;
				 }else{
					 return 1;
				 }
			 }
			 
			 if (telefono.substring(0,1).equals("+")){
				 if (telefono.substring(1, telefono.length()).matches("[0-9]*")){
					 return 0;
				 }else{
					 return 1;
				 }
			 }
		 }
		 
		 return 1;
	 }
	 
	 public static boolean validarEmail(String email){
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	
	 }
	 
	 public static String encode64(String cadena){
		 String res = "";
		
		 res = Base64.encode(cadena.getBytes());
		return res;
	 }
	 
	 public static String decode64(String cadena){
		 byte[] valueDecoded= Base64.decode(cadena);
		 return new String(valueDecoded);
		
	 }
	 
	
	 public static String eliminarPrefijo(String telefono, String telefonoExcepcion) {
	    	String res = "";
	    		    	
	    	if (telefono.startsWith("+") || telefono.startsWith("00")){
	    		res = telefono.substring(telefono.length()-9);
	    		if (validarTelefono(res, telefonoExcepcion) == 0){
	    			return res;
	    		}
	    	}
	    	return telefono;
	    }
	 
	 public static String crearSmsToken (int len ) {
			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			StringBuilder sb = new StringBuilder( len );
			for( int i = 0; i < len; i++ ) { 
				sb.append(AB.charAt(rnd.nextInt(AB.length()) ) );
			}
			return sb.toString();
}

}
