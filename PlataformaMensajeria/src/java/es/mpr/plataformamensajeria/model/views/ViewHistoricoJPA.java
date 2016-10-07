package es.mpr.plataformamensajeria.model.views;

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
@Table(name = "VIEW_HISTORICO")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewHistoricoJPA", query = "SELECT e FROM ViewHistoricoJPA e"),
	@NamedQuery(name = "selectViewHistoricoJPA", query = "SELECT e FROM ViewHistoricoJPA e where e.historicoId=:historicoId"),
	@NamedQuery(name = "selectViewHistoricoByMensajeIdJPA", query = "SELECT e FROM ViewHistoricoJPA e where e.mensajeId=:mensajeId order by fecha desc"),
	@NamedQuery(name = "selectViewHistoricoByMensajeDestinatarioIdJPA", query = "SELECT e FROM ViewHistoricoJPA e where e.mensajeId=:mensajeId and e.destinatariosMensajes=:destinatariosMensajesId order by historicoId desc")})

public class ViewHistoricoJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewHistoricoJPA() {
		this.historicoId = null;
		this.fecha = null;
		this.mensajeId=null;
		this.estadoId=null;
		this.servidorId=null;
		this.planificacionId=null;
		//this.mensajeId=null; 
		this.nombreServidor = null;
		this.codigoExterno = null;
		this.usuario = null;
		this.password = null;
		this.destinatariosMensajes = null;
	}

	
	@Id
	@SequenceGenerator(name="historico", sequenceName="HISTORICOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="historico")
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
	//@Column(name="MENSAJEID")
	//private Integer mensajeId;
//	@Column(name="CREADOPOR")
//	private String creadoPor;
	@Column(name="ESTADO")
	private String estado;
	@Column(name="SERVIDOR")
	private String nombreServidor;
	@Column(name="CODIGOEXTERNO")
	private String codigoExterno;
	@Column(name="USUARIO")
	private String usuario;
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="DESTINATARIOSMENSAJES")
	private Integer destinatariosMensajes;
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreServidor() {
		return nombreServidor;
	}
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
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
	public Integer getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
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
//	public Integer getMensajeId() {
//		return mensajeId;
//	}
//	public void setMensajeId(Integer mensajeId) {
//		this.mensajeId = mensajeId;
//	}
	public String getCodigoExterno() {
		return codigoExterno;
	}
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	public void setDestinatariosMensajes(Integer destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
}
