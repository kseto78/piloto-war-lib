package es.mpr.template.jmx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Organismos implements OrganismosMBean{

	
	public String devolverOrganismos() throws Exception {
		// TODO Auto-generated method stub
		
		InitialContext context=new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:dsOraclePlataforma"); 
		String sQuery="select * from TEMP_ORGANISMO";
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sQuery);
		String resultado="<table cellspacing='10' cellpadding='10' border='3'>";
		resultado=resultado+"\n"+"<tr>";
		resultado=resultado+("<table border=\"5\">");
		while (rs.next()){
		resultado=resultado+"<tr><td><b>NOMBRE:</b></td><td><i>"+rs.getString("nombre")+"</i></td></tr>";
			}
		resultado=resultado+"</table><p>";
        con.close();
		return resultado;
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

	
	public void jbossInternalLifecycle(String arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void create() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	public void start() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
