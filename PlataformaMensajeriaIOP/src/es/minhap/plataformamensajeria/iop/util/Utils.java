package es.minhap.plataformamensajeria.iop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.encoding.Base64;

public class Utils {
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	 
	 
	 public static int validarTelefono(String telefono){
		 
		 if (telefono!=null){
			 telefono= telefono.trim();
		 }
				 
		 if (telefono.length() == 9){
			 //comprobamos si son todos dígitosif (cadenaUno.matches("[0-9]*"))
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
	 
	
	 public static String eliminarPrefijo(String telefono) {
	    	String res = "";
	    		    	
	    	if (telefono.startsWith("+") || telefono.startsWith("00")){
	    		res = telefono.substring(telefono.length()-9);
	    		if (validarTelefono(res) == 0){
	    			return res;
	    		}
	    	}
	    	return telefono;
	    }

}
