package es.mpr.template.jmx;

import org.jboss.system.ServiceMBean;

public interface OrganismosMBean extends ServiceMBean  {
	
	public String devolverOrganismos() throws Exception; 

}
