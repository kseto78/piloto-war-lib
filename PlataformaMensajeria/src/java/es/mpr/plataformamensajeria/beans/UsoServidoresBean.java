package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsoServidoresBean() {
		super();
		this.servidor = null;
		this.nenvios = null;
		this.tipoServidor = null;
	}
	protected String servidor;
	protected Integer nenvios;
	protected Integer tipoServidor;

	
	public Object getId() {
		// TODO Auto-generated method stub
		return this.servidor;
	}
	public void setId(Object id){
		this.servidor =(String)id;
	}

	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getNenvios() {
		DecimalFormat formateador = new DecimalFormat("###,###.##"); 
		return formateador.format(nenvios);
	}
	public void setNenvios(Integer nenvios) {
		this.nenvios = nenvios;
	}
	public Integer getTipoServidor() {
		return tipoServidor;
	}
	public void setTipoServidor(Integer tipoServidor) {
		this.tipoServidor = tipoServidor;
	}
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
