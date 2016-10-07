package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TBL_MENSAJESADJUNTOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllMensajesAdjuntosHistoricosJPA", query = "SELECT e FROM MensajesAdjuntosHistoricosJPA e")})

public class MensajesAdjuntosHistoricosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensajesAdjuntosHistoricosJPA() {
		this.mensajeAdjuntoId=null;
		this.mensajeId=null;
		this.adjuntoId=null;
		this.fechaHistorificacion=null;
		
	}
	
	@Id
	@Column(name="MENSAJEADJUNTOID")
	protected Integer mensajeAdjuntoId;
	
	@Column(name="MENSAJEID")
	protected Integer mensajeId;
	
	@Column(name="ADJUNTOID")
	protected Integer adjuntoId;
	
	@Column(name="FECHAHISTORIFICACION")
	protected Date fechaHistorificacion;
	

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

	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}

	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}

	public Integer getMensajeAdjuntoId() {
		return mensajeAdjuntoId;
	}

	public void setMensajeAdjuntoId(Integer mensajeAdjuntoId) {
		this.mensajeAdjuntoId = mensajeAdjuntoId;
	}
	
	
}
