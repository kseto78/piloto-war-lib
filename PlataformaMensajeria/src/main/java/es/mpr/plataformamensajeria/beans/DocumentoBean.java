package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>Bean para mapear la información de los envíos
 *  
 *  <p>
 *  
 *  
 *  @author i-nercya
 */
public class DocumentoBean implements Audit{

	/**  elemento */
	private String elemento;

	/**  fichero */
	private String fichero;

	/**  destinatario. */
	private String fechaSubida;



	/**
	 * Constructor de SMS bean.
	 */
	public DocumentoBean() {
		this.elemento=null;
		this.fichero=null;
		this.fechaSubida=null;		
	}
	
	

	public String getElemento() {
		return elemento;
	}



	public String getFichero() {
		return fichero;
	}



	public String getFechaSubida() {
		return fechaSubida;
	}



	public void setElemento(String elemento) {
		this.elemento = elemento;
	}



	public void setFichero(String fichero) {
		this.fichero = fichero;
	}



	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}



	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
