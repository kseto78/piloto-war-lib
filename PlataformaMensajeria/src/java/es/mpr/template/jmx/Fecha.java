package es.mpr.template.jmx;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha implements FechaMBean
{
	private String formato;
	public String devolverHora(){
		return new Long(System.currentTimeMillis()).toString();
	}

	
	public void create() throws Exception {
		System.out.println("Creando el bean...");
		// TODO Auto-generated method stub
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void jbossInternalLifecycle(java.lang.String arg0) throws java.lang.Exception {
		
	}

	
	public void start() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void stop() {
		// TODO Auto-generated method stub
		
	}


	
	public String formatFecha(String formatoActual) throws Exception {
		// TODO Auto-generated method stub
		
		String formatoUsar = "";
		if(this.formato!=null && !"".equals(this.formato.trim())) {
            formatoUsar=this.formato.trim();
        } else {
            formatoUsar="dd/MM/yyyy";
        }
        
        if(formatoActual!=null && !"".equals(formatoActual.trim())) {
            formatoUsar = formatoActual.trim();
        }        
        SimpleDateFormat formatter=new SimpleDateFormat(formatoUsar);
        return formatter.format(new Date());    
    }
		
	


	
	public String getFormato() {
		// TODO Auto-generated method stub
	    return formato;
	}


	
	public void setFormato(String formato) {
       this.formato=formato;
		
	}


	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public String getStateString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
