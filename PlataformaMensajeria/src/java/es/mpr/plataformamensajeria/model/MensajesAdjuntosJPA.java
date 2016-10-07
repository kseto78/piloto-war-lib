package es.mpr.plataformamensajeria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_MENSAJESADJUNTOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllMensajesAdjuntosJPA", query = "SELECT e FROM MensajesAdjuntosJPA e")})

public class MensajesAdjuntosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensajesAdjuntosJPA() {
		this.mensajeAdjuntoId=null;
		this.mensajeId=null;
		this.adjuntoId=null;
		
	}
	
	@Id
	@SequenceGenerator(name="mensajeAdjunto", sequenceName="MENSAJEADJUNTOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mensajeAdjunto")
	protected Integer mensajeAdjuntoId;
	
	@Column(name="MENSAJEID")
	protected Integer mensajeId;
	
	@Column(name="ADJUNTOID")
	protected Integer adjuntoId;
	

	@Override
	public Object getId() {
		return this.mensajeAdjuntoId;
	}
	
	public void setId(Object id){
		this.mensajeAdjuntoId = (Integer)id;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}

	public Integer getAdjuntoId() {
		return adjuntoId;
	}

	public void setAdjuntoId(Integer adjuntoId) {
		this.adjuntoId = adjuntoId;
	}
	
	
}
