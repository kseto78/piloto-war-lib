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
@Table(name = "TBL_HISTORICOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllHistoricoHistJPA", query = "SELECT e FROM HistoricoHistJPA e"),
	@NamedQuery(name = "selectHistoricoHistJPA", query = "SELECT e FROM HistoricoHistJPA e where e.historicoId=:historicoId")})

public class HistoricoHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoricoHistJPA() {
		this.historicoId = null;
		this.fecha = null;
		this.mensajeId=null;
		this.estadoId=null;
		this.servidorId=null;
		this.planificacionId=null;
		this.descripcion=null;
		this.subEstadoId=null;
		this.fechaHistorificado=null;
	}

	
	@Id
	@Column(name="HISTORICOID")
	protected Integer historicoId;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="MENSAJEID")
	private Integer mensajeId;
	@Column(name="ESTADOID")
	private Integer estadoId;
	@Column(name="SERVIDORID")
	private Integer servidorId;
	@Column(name="PLANIFICACIONID")
	private Integer planificacionId;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="SUBESTADOID")
	private Integer subEstadoId;
	@Column (name="FECHAHISTORIFICACION")
	protected Date fechaHistorificado = null;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.historicoId;
	}
	public void setId(Object id){
		this.historicoId =(Integer)id;
	}
	public Integer getHistoricoId() {
		return historicoId;
	}
	public void setHistoricoId(Integer historicoId) {
		this.historicoId = historicoId;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}
	public Integer getPlanificacionId() {
		return planificacionId;
	}
	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}
	public Integer getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}
	public Date getFechaHistorificado() {
		return fechaHistorificado;
	}
	public void setFechaHistorificado(Date fechaHistorificado) {
		this.fechaHistorificado = fechaHistorificado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getSubEstadoId() {
		return subEstadoId;
	}
	public void setSubEstadoId(Integer subEstadoId) {
		this.subEstadoId = subEstadoId;
	}
	
}
