package es.mpr.plataformamensajeria.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PlataformaMensajeriaProperties {

   private Properties configProp = new Properties();

   public static void main(String[] args) {
	   PlataformaMensajeriaProperties sample = new PlataformaMensajeriaProperties();
       sample.loadDBProps();
   
   }
   public PlataformaMensajeriaProperties() {
	   loadDBProps();
   }
   public void loadDBProps() {
       InputStream in = this.getClass().getResourceAsStream("/es/mpr/plataformamensajeria/util/conf.properties");
       try {
           configProp.load(in);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public String getProperty(String property, String defProperty){
	   String prop = null;
	   prop = (String) configProp.getProperty(property);
	   if(prop==null){
		   prop=defProperty;
	   }
		   return prop;
	   
   }
   
   // INI - gbermude - 20/01/2016
   private static PlataformaMensajeriaProperties instance = null;
   
   public static PlataformaMensajeriaProperties getInstance() {
	   
	   if (instance == null ) {
		   instance = new PlataformaMensajeriaProperties();
	   }
	   return instance;	   
   }
   // FIN - gbermude - 20/01/2016
   
   
   
}
