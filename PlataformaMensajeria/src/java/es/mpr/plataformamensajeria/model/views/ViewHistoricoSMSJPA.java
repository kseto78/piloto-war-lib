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
@Table(name = "VIEW_HISTORICOSMS2")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewHistoricoSMSJPA", query = "SELECT e FROM ViewHistoricoSMSJPA e"),
	@NamedQuery(name = "selectViewHistoricoSMSJPA", query = "SELECT e FROM ViewHistoricoSMSJPA e where e.idEnviosSMS=:idEnvioSMS order by fecha desc")})

public class ViewHistoricoSMSJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewHistoricoSMSJPA() {
		this.idEnviosSMS=null;
		this.uim=null;
		this.fecha = null;
		this.estadoId=null;
		this.estado=null;
		this.idGrupo=null;
		this.descripcion= null;
		
	}

	
	@Id
	@Column(name="ID")
	private String idFinal;
	
	


	public String getIdFinal() {
		return idFinal;
	}
	public void setIdFinal(String idFinal) {
		this.idFinal = idFinal;
	}


	@Column(name="IDENVIOSSMS")
	private Integer idEnviosSMS;
	
	@Column(name="UIM")
	private String uim;
	@Column(name="IDESTADO")
	private Integer estadoId;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="ESTADO")
	private String estado;
	@Column(name="IDGRUPO")
	private Integer idGrupo;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="SERVIDOR")
	private String nombreServidorSMS;
	@Column(name="IDVENTANA")
	private Integer idVentana;
	@Column(name="PLANIFICACIONID")
	private Integer planificacionId;
	@Column(name="SERVIDORID")
	private Integer servidorId;
	@Column(name="USUARIO")
	private String usuario;
	@Column(name="PASSWORD")
	private String password;

	public String getNombreServidorSMS() {
		return nombreServidorSMS;
	}
	public void setNombreServidorSMS(String nombreServidorSMS) {
		this.nombreServidorSMS = nombreServidorSMS;
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
		return this.idEnviosSMS;
	}
	public void setId(Object id){
		this.idEnviosSMS =(Integer)id;
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
	public Integer getIdEnviosSMS() {
		return idEnviosSMS;
	}
	public void setIdEnviosSMS(Integer idEnviosSMS) {
		this.idEnviosSMS = idEnviosSMS;
	}
	public String getUim() {
		return uim;
	}
	public void setUim(String uim) {
		this.uim = uim;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdVentana() {
		return idVentana;
	}
	public void setIdVentana(Integer idVentana) {
		this.idVentana = idVentana;
	}
	public Integer getPlanificacionId() {
		return planificacionId;
	}
	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}
	public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
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

	
}
