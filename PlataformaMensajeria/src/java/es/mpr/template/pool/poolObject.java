package es.mpr.template.pool;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;


import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author juanantonio.caro
 * Action que permite comprobar el funcionamiento correcto del POOL.
 * Recupera un objeto, y devuelve su string asociado.
 */


public class poolObject extends ActionSupport implements ServletRequestAware{
	
	
	
	private static final long serialVersionUID = 7777553116269030273L;
	protected HttpServletRequest request;
	
    public String obtenerPool(){
    	DocumentBuilder buil;
    	String obj=null;
    	try {
			buil = (DocumentBuilder) staticPool.getPool().borrowObject();
		     obj=buil.toString();
		     staticPool.getPool().returnObject(buil);
		    this.request.setAttribute("pool", obj);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
    }

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
