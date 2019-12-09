package es.mpr.plataformamensajeria.beans;

import java.text.DecimalFormat;

import com.map.j2ee.auditoria.ifaces.Audit;

// TODO: Auto-generated Javadoc
/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista Envios Pendientes por Canala de la base de datos
 *  
 *  @author 
 */
public class UsoServidoresBean implements Audit{


	/**  servidor. */
	protected String servidor;

	/**  nenvios. */
	protected Integer nenvios;

	/**  tipo servidor. */
	protected Integer tipoServidor;


	/**
	 * Constructor de uso servidores bean.
	 */
	public UsoServidoresBean() {
		super();
		this.servidor = null;
		this.nenvios = null;
		this.tipoServidor = null;
	}
	
	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		// TODO Auto-generated method stub
		return this.servidor;
	}
	
	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id){
		this.servidor =(String)id;
	}

	/**
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public String getServidor() {
		return servidor;
	}
	
	/**
	 * Modificar servidor.
	 *
	 * @param servidor new servidor
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	
	/**
	 * Obtener nenvios.
	 *
	 * @return nenvios
	 */
	public String getNenvios() {
		DecimalFormat formateador = new DecimalFormat("###,###.##"); 
		return formateador.format(nenvios);
	}
	
	/**
	 * Modificar nenvios.
	 *
	 * @param nenvios new nenvios
	 */
	public void setNenvios(Integer nenvios) {
		this.nenvios = nenvios;
	}
	
	/**
	 * Obtener tipo servidor.
	 *
	 * @return tipo servidor
	 */
	public Integer getTipoServidor() {
		return tipoServidor;
	}
	
	/**
	 * Modificar tipo servidor.
	 *
	 * @param tipoServidor new tipo servidor
	 */
	public void setTipoServidor(Integer tipoServidor) {
		this.tipoServidor = tipoServidor;
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
