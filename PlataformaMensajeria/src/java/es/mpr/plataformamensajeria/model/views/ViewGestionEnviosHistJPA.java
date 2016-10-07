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

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista gestión envios historicos de la base de datos
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_GESTIONENVIOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectViewGestionEnviosHistJPA", query = "SELECT e FROM ViewGestionEnviosHistJPA e"),
	@NamedQuery(name = "selectViewGestionEnviosByIdHistJPA", query = "SELECT e FROM ViewGestionEnviosHistJPA e where e.mensajeId=:mensajeId")})

public class ViewGestionEnviosHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewGestionEnviosHistJPA() {
		this.aplicacion=null;
		this.servicio=null;
		this.loteEnvio=null;
		this.estado=null;
		this.destinatario=null;
		this.canalId=null;
		this.aplicacionId=null;
		this.servicioId=null;
		this.envioId=null;
		this.ultimoEnvio=null;
		this.mensajeId = null;
		this.fechaHistorificacion=null;
		this.docUsuario = null;
		this.codSia = null;
		this.codOrganismo = null;
		this.codOrganismoPagador = null;
	}
	@Id
	@Column(name="ENVIOID")
	private String envioId;
	@Column(name="MENSAJEID")
	private Integer mensajeId;
	@Column(name="APLICACION")
	private String aplicacion;
	@Column(name="SERVICIO")
	private String servicio;
	@Column(name="LOTEENVIO")
	private String loteEnvio;
	@Column(name="ULTIMOENVIO")
	private Date ultimoEnvio;
	@Column(name="ESTADO")
	private String estado;
	@Column(name="DESTINATARIO")
	private String destinatario;
	@Column(name="CANALID")
	private Integer canalId;
	@Column(name="APLICACIONID")
	private Integer aplicacionId;
	@Column(name="SERVIDORID")
	private Integer servidorId;
	@Column(name="FECHAHISTORIFICACION")
	private Date fechaHistorificacion;
	@Column(name="DOCUSUARIO")
	private String docUsuario;
	@Column(name="CODSIA")
	private String codSia;
	@Column(name="CODORGANISMO")
	private String codOrganismo;
	@Column(name="CODORGANISMOPAGADOR")
	private String codOrganismoPagador;
	
	public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}
	@Column(name="SERVICIOID")
	private Integer servicioId;
	@Column(name="ESTADOID")
	private Integer estadoId;
	
	@Column(name="LOTEENVIOID")
	private Integer idLote;
	@Column(name="IDEXTERNO")
	private String idExterno;
	
	public Integer getIdLote() {
		return idLote;
	}
	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}
	public String getIdExterno() {
		return idExterno;
	}
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	public String getEnvioId() {
		return envioId;
	}
	public void setEnvioId(String envioId) {
		this.envioId = envioId;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getLoteEnvio() {
		return loteEnvio;
	}
	public void setLoteEnvio(String loteEnvio) {
		this.loteEnvio = loteEnvio;
	}
	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}
	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public Integer getCanalId() {
		return canalId;
	}
	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	/*public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}*/
	public Integer getServicioId() {
		return servicioId;
	}
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return envioId;
	}
	
	public void setId(String id){
		this.envioId=id;
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
	public String getDocUsuario() {
		return docUsuario;
	}
	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}
	public String getCodSia() {
		return codSia;
	}
	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}
	public String getCodOrganismo() {
		return codOrganismo;
	}
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}
	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}
	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	
}
