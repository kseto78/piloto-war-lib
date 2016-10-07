package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
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
 *  @author i-nercya
 */
@Entity
@Table(name = "TBL_HISTORICOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllHistoricoJPA", query = "SELECT e FROM HistoricoJPA e"),
	@NamedQuery(name = "selectHistoricoJPA", query = "SELECT e FROM HistoricoJPA e where e.historicoId=:historicoId")})

public class HistoricoJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoricoJPA() {
		this.historicoId = null;
		this.fecha = null;
		this.mensajeId=null;
		this.estadoId=null;
		this.servidorId=null;
		this.planificacionId=null;
		this.descripcion=null;
		this.subestadoId=null;
		this.destinatariosMensajes = null;
	}

	
	@Id
	@SequenceGenerator(name="historico", sequenceName="HISTORICOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="historico")
	protected Integer historicoId;

	@Column(name="FECHA")
	private Date fecha;
	@Column(name="ESTADOID")
	private Integer estadoId;
	@Column(name="SERVIDORID")
	private Integer servidorId;
	@Column(name="PLANIFICACIONID")
	private Integer planificacionId;
	@Column(name="MENSAJEID")
	private Integer mensajeId;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="SUBESTADOID")
	private Integer subestadoId;
	
	@Column(name="DESTINATARIOSMENSAJES")
	private Integer destinatariosMensajes;

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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getSubestadoId() {
		return subestadoId;
	}
	public void setSubestadoId(Integer subestadoId) {
		this.subestadoId = subestadoId;
	}
	public Integer getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	public void setDestinatariosMensajes(Integer destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
}
