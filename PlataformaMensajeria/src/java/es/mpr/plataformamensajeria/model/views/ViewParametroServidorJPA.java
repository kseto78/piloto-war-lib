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
 *  Representa la vista Parametros servidor de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_PARAMETROSSERVIDOR")
@NamedQueries({         
	@NamedQuery(name = "selectViewParametrosServidorJPA", query = "SELECT e FROM ViewParametroServidorJPA e WHERE upper (e.valor) like upper(concat(concat('%',:valor),'%'))"),
	@NamedQuery(name = "selectViewParametrosServidorByServidorIdJPA", query = "SELECT e FROM ViewParametroServidorJPA e WHERE to_char(e.servidorId) = ?"),
	@NamedQuery(name = "selectViewParametrosServidorByProveedorSMSIdJPA", query = "SELECT e FROM ViewParametroServidorJPA e WHERE to_char(e.servidorId) = ?"),
	@NamedQuery(name = "selectViewParametrosServidorByReceptorSMSIdJPA", query = "SELECT e FROM ViewParametroServidorJPA e WHERE to_char(e.servidorId) = ?"),
	@NamedQuery(name = "selectViewParametrosServidorByServidorPushIdJPA", query = "SELECT e FROM ViewParametroServidorJPA e WHERE to_char(e.servidorId) = ?"),
	@NamedQuery(name = "selectViewParametrosServidorByTipo", query = "SELECT e FROM ViewParametroServidorJPA e WHERE to_char(e.servidorId) = ? and to_char(e.tipoParametroId) = ?")})

public class ViewParametroServidorJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewParametroServidorJPA() {
		super();
		this.parametroServidorId = null;
		this.tipoParametroId = null;
		this.tipoNombre = null;
		this.valor = null;
		this.tipoDescripcion = null;
		this.servidorId = null;
		this.tipoTag = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;

	}

	
	@Id
	@SequenceGenerator(name="parametroServidor", sequenceName="PARAMETROSERVIDORID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="parametroServidor")
	protected Long parametroServidorId;

	
	@Column(name = "VALOR")
	protected String valor = null;
	
	@Column(name = "TIPOPARAMETROID")
	protected Integer tipoParametroId = null;
	
	@Column(name="TIPONOMBRE")
	protected String tipoNombre;

	@Column(name = "SERVIDORID")
	protected Integer servidorId = null;
	
	@Column(name = "TIPODESCRIPCION")
	protected String tipoDescripcion;
	
	@Column(name= "TIPOTAG")
	protected String tipoTag;
	
	@Column(name = "ACTIVO")
	protected Integer activo = null;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column (name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	
	@Column (name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.servidorId;
	}
	public void setId(Object id){
		this.servidorId =(Integer)id;
	}
	public Long getParametroServidorId() {
		return parametroServidorId;
	}
	public void setParametroServidorId(Long parametroServidorId) {
		this.parametroServidorId = parametroServidorId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Integer getTipoParametroId() {
		return tipoParametroId;
	}
	public void setTipoParametroId(Integer tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
	}
	public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getTipoNombre() {
		return tipoNombre;
	}
	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}
	public String getTipoDescripcion() {
		return tipoDescripcion;
	}
	public void setTipoDescripcion(String tipoDescripcion) {
		this.tipoDescripcion = tipoDescripcion;
	}
	public String getTipoTag() {
		return tipoTag;
	}
	public void setTipoTag(String tipoTag) {
		this.tipoTag = tipoTag;
	}


	
	
}
