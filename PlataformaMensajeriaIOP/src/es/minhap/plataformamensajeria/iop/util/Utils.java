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
	
	protected static final String R_CONST_1 = "+";

	protected static final String R_CONST_2 = "[0-9]*";

	protected static final String R_CONST_3 = "0";

	protected static final String R_CONST_4 = "6";

	protected static final String R_CONST_5 = "7";

	protected static final String R_CONST_6 = "Error en Utils";

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
			LOG.error(R_CONST_6,e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error(R_CONST_6,e);
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
	            // TODO logger.warn(e.getMessage(), e);
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
			 if (telefono.matches(R_CONST_2)){ 
			 	return 0;
			 }
			  else{
			   return 1;
			  }
		 }else{
			 if (R_CONST_3.equals(telefono.substring(0,1))){
				 if (telefono.substring(1, telefono.length()).matches(R_CONST_2)){
					 return 0;
				 }else{
					 return 1;
				 }
			 }
			 
			 if (R_CONST_1.equals(telefono.substring(0,1))){
				 if (telefono.substring(1, telefono.length()).matches(R_CONST_2)){
					 return 0;
				 }else{
					 return 1;
				 }
			 }
		 }
		 
		 return 1;
	 }
	 
 public static int validarTelefonoExtranjeros(String telefono, String telefonoExcepcion,boolean extranjero){
	
	 	
		 if (telefono!=null){
			 telefono= telefono.trim();
			 if(telefonoExcepcion.contains(telefono)) {
				 return 0;
			 }
		 } else {
			 return 1;
		 }
		 if(extranjero){			 
			 if (telefono.length() == 9){
				 //comprobamos si son todos digitosif (cadenaUno.matches("[0-9]*"))
				 if (telefono.matches(R_CONST_2)){ 
				 	return 0;
				 }
				  else{
				   return 1;
				  }
			 }else{
				 if (R_CONST_3.equals(telefono.substring(0,1))){
					 if (telefono.substring(1, telefono.length()).matches(R_CONST_2)){
						 return 0;
					 }else{
						 return 1;
					 }
				 }
				 
				 if (R_CONST_1.equals(telefono.substring(0,1))){
					 if (telefono.substring(1, telefono.length()).matches(R_CONST_2)){
						 return 0;
					 }else{
						 return 1;
					 }
				 }
			 }
			 
			 return 1;
	 	}else{
	 		
					 
			 if (telefono.length() == 9){
				 //comprobamos si son todos digitosif (cadenaUno.matches("[0-9]*"))
				 if (telefono.matches(R_CONST_2)){ 
					 if ( R_CONST_4.equals(telefono.substring(0,1)) || R_CONST_5.equals(telefono.substring(0,1)) ){
						 return 0;
					 }else{
						 return 1;
					 }				 
				 }
				  else{
				   return 1;
				  }
				 }else{
					 if ( "0034".equals(telefono.substring(0,4)) && 
							 telefono.substring(1, telefono.length()).matches(R_CONST_2) &&
							 telefono.length() == 13 && 
							 ( R_CONST_4.equals(telefono.substring(4,5)) || R_CONST_5.equals(telefono.substring(0,1)) )){
						 return 0;
						 
					 }			
					 if ( "+0034".equals(telefono.substring(0,5)) && 
							 telefono.substring(1, telefono.length()).matches(R_CONST_2) &&
							 telefono.length() == 14 && 
							 ( R_CONST_4.equals(telefono.substring(5,6)) || R_CONST_5.equals(telefono.substring(0,1)) )){
								 						 
							 return 0;	 
								
						 }
					 
					 if ( "+34".equals(telefono.substring(0,3)) && 
						 telefono.substring(1, telefono.length()).matches(R_CONST_2) &&
						 telefono.length() == 12 && 
						 ( R_CONST_4.equals(telefono.substring(3,4)) || R_CONST_5.equals(telefono.substring(0,1)) )){
							 						 
						 return 0;	 
							
					 }else{
						 return 1;
					 }
						
				 }	 
			
	 	}
	 }
	 
	 public static boolean validarEmail(String email){
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	
	 }
	 
	 public static String encode64(String cadena){
		 String res = "";
		
		 return Base64.encode(cadena.getBytes());
	 }
	 
	 public static String decode64(String cadena){
		 byte[] valueDecoded= Base64.decode(cadena);
		 return new String(valueDecoded);
		
	 }
	 
	
	 public static String eliminarPrefijo(String telefono, String telefonoExcepcion) {
	    	String res = "";
	    		    	
	    	if (telefono.startsWith(R_CONST_1) || telefono.startsWith("00")){
	    		res = telefono.substring(telefono.length()-9);
	    		if (validarTelefono(res, telefonoExcepcion) == 0){
	    			return res;
	    		}
	    	}
	    	return telefono;
	    }
	 
	 public static String crearSmsToken (int len ) {
			String ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			StringBuilder sb = new StringBuilder( len );
			for( int i = 0; i < len; i++ ) { 
				sb.append(ab.charAt(rnd.nextInt(ab.length()) ) );
			}
			return sb.toString();
}

}
