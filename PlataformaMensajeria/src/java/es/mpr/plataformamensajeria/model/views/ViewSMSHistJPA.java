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
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_GESTIONSMS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewSMSHistJPA", 
			query = "SELECT e FROM ViewSMSHistJPA e"),
	@NamedQuery(name = "selectViewSMSHistJPA", 
			query = "SELECT e FROM ViewSMSHistJPA e where e.idEnviosSMS=:idEnvioSMS")})

public class ViewSMSHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewSMSHistJPA() {
		this.nombreAplicacion=null;
		this.nombreServicio=null;
		this.destinatario=null;
		this.textoSMS=null;
		this.aplicacionId=null;
		this.servicioId=null;
		this.idEnviosSMS=null;
		this.loteEnvio=null;
		this.loteEnvioId=null;
		//this.servidorId=null;
		//this.nombreServidor=null;
		this.ultimoEnvio=null;
		this.estado=null;
		this.estadoId=null;
		this.fechaHistorificacion=null;
		
	}

	
	@Id
	@Column(name="IDENVIOSSMS")
	protected Integer idEnviosSMS;
	@Column(name="APLICACION")
	private String nombreAplicacion;
	@Column(name="SERVICIO")
	private String nombreServicio;
	@Column(name="DESTINATARIO")
	private String destinatario;
	@Column(name="TEXTOSMS")
	private String textoSMS;
	@Column(name="APLICACIONID")
	private Integer aplicacionId;
	@Column(name="SERVICIOID")
	private Integer servicioId;
	@Column(name="LOTEENVIO")
	private String loteEnvio;
	@Column(name="LOTEENVIOID")
	private Integer loteEnvioId;
//	@Column(name="SERVIDORID")
//	private Integer servidorId;
	//@Column(name="SERVIDOR")
	//private String nombreServidor;
	@Column(name="ULTIMOENVIO")
	private Date ultimoEnvio;
	@Column(name="ESTADO")
	private String estado;
	@Column(name="canalId")
	private Integer canalId;
	@Column(name="estadoId")
	private Integer estadoId;
	@Column(name="FECHAHISTORIFICACION")
	private Date fechaHistorificacion;
	@Override
	public Object getId() {
		return this.idEnviosSMS;
	}
	public void setId(Object id){
		this.idEnviosSMS =(Integer)id;
	}
	public Integer getIdEnviosSMS() {
		return idEnviosSMS;
	}
	public void setIdEnviosSMS(Integer idEnviosSMS) {
		this.idEnviosSMS = idEnviosSMS;
	}
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getTextoSMS() {
		return textoSMS;
	}
	public void setTextoSMS(String textoSMS) {
		this.textoSMS = textoSMS;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Integer getServicioId() {
		return servicioId;
	}
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}
	public String getLoteEnvio() {
		return loteEnvio;
	}
	public void setLoteEnvio(String loteEnvio) {
		this.loteEnvio = loteEnvio;
	}
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}
/*	public Integer getServidorId() {
		return servidorId;
	}
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}*/
/*	public String getNombreServidor() {
		return nombreServidor;
	}
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}*/
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
	public Integer getCanalId() {
		return canalId;
	}
	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}

}
