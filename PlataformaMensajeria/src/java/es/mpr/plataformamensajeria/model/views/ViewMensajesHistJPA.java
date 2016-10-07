package es.mpr.plataformamensajeria.model.views;

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
@Table(name = "VIEW_MENSAJES_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewMensajesHistJPA", query = "SELECT e FROM ViewMensajesHistJPA e"),
	@NamedQuery(name = "selectViewMensajesHistJPA", query = "SELECT e FROM ViewMensajesHistJPA e where e.mensajeId=:mensajeId")})

public class ViewMensajesHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewMensajesHistJPA() {
		
		this.mensajeId=null;
		this.loteEnvioId=null;
		this.codigoExterno=null;
		this.cuerpo=null;
		this.cabecera=null;
		this.estadoActual=null;
		this.numeroEnvios=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.ultimoIdHistorico=null;
		this.fechaHistorificacion=null;

	}

	
	@Id
	@Column(name="MENSAJEID")
	protected Integer mensajeId;
	@Column(name="LOTEENVIOID")
	private Integer loteEnvioId;
	@Column(name="CODIGOEXTERNO")
	private String codigoExterno;
	@Column(name="CUERPO")
	private String cuerpo;
	@Column(name="CABECERA")
	private String cabecera;
	@Column(name="ESTADOACTUAL")
	private String estadoActual;
	@Column(name="NUMEROENVIOS")
	private String numeroEnvios;
	@Column(name="FECHACREACION")
	private Date fechaCreacion;
	@Column(name="CREADOPOR")
	private String creadoPor;
	@Column(name="FECHAMODIFICACION")
	private Date fechaModificacion;
	@Column(name="MODIFICADOPOR")
	private String modificadoPor;
	@Column(name="ULTIMOIDHISTORICO")
	private Integer ultimoIdHistorico;
	@Column(name="TELEFONO")
	private String telefono;
	@Column(name="TIPOMENSAJE")
	private String tipoMensaje;
	@Column(name="FECHAHISTORIFICACION")
	private Date fechaHistorificacion;
	
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}
	public String getCodigoExterno() {
		return codigoExterno;
	}
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getEstadoActual() {
		return estadoActual;
	}
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}
	public String getNumeroEnvios() {
		return numeroEnvios;
	}
	public void setNumeroEnvios(String numeroEnvios) {
		this.numeroEnvios = numeroEnvios;
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
	public Integer getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}
	public void setUltimoIdHistorico(Integer ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.mensajeId;
	}
	public void setId(Object id){
		this.mensajeId =(Integer)id;
	}


	
	public Integer getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}



	
	
}
