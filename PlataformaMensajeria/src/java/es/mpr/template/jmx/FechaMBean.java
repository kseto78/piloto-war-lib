package es.mpr.template.jmx;

import org.jboss.system.ServiceMBean;

public interface FechaMBean  extends ServiceMBean     { 
    /*  Método que formatea la fecha actual en el formato que le pasemos. 
    Si no recibe ningún formato, usa el formato por defecto  */ 
    public String formatFecha(String formato) throws Exception; 
    
    // Metodos accesores del atributo formato. (formato por defecto) 
    public String getFormato(); 
    public void setFormato(String formato);    
}
