//package es.minhap.misim.bus.webapp.restserviceprovider;
//
//import java.util.StringTokenizer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//
//import sun.misc.BASE64Decoder;
// 
//@Path("auth")
//public class AuthService {
//	 
//	  
//	 @Context
//	 private HttpServletRequest request;
//	  
//	 @GET
//	 @Path("basic")
//	 @Produces(MediaType.TEXT_PLAIN)
//	 public String authenticateHTTPHeader(){
//	   
//	  String decoded;
//	  try{
//	   // Get the Authorisation Header from Request
//	   String header = request.getHeader("authorization");
//	    
//	   // Header is in the format "Basic 3nc0dedDat4"
//	   // We need to extract data before decoding it back to original string
//	   String data = header.substring(header.indexOf(" ") +1 );
//	    
//	   // Decode the data back to original string
//	   byte[] bytes = new BASE64Decoder().decodeBuffer(data);
//	   decoded = new String(bytes);
//	    
//	   System.out.println(decoded);
//	    
//	  }catch(Exception e){
//	   e.printStackTrace();
//	   decoded = "Autenficiación no válida o enviada.";
//	  }
//	  return decoded;
//	 }
//}
