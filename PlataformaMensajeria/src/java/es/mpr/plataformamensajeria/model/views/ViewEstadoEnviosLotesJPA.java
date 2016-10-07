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
 *  @author i-nercya
 */
@Entity
@Table(name = "VIEW_ESTADO_LOTESENVIOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewEstadoEnviosLotesJPA", 
			query = "SELECT e FROM ViewEstadoEnviosLotesJPA e order by fecha desc"),
	@NamedQuery(name = "selectAllViewEstadoEnviosLotesJPAByAnyo", 
			query = "SELECT e FROM ViewEstadoEnviosLotesJPA e where fecha>=:fechaInicioAnyo and fecha<=:fechaFinAnyo order by fecha desc"),
	@NamedQuery(name = "selectAllViewEstadoEnviosLotesJPAByMes", 
			query = "SELECT e FROM ViewEstadoEnviosLotesJPA e order by fecha desc"),
	@NamedQuery(name = "selectAllViewEstadoEnviosLotesJPAByMes_count", 
			query = "SELECT count(e) FROM ViewEstadoEnviosLotesJPA e"),
	
	@NamedQuery(name = "selectAllViewEstadoEnviosLotesJPAByAnyoMes", 
			query = "SELECT e FROM ViewEstadoEnviosLotesJPA e where fecha>=TO_DATE(:fechaInicioAnyo,'DD/MM/YYYY HH24:Mi') and fecha<=TO_DATE(:fechaFinAnyo,'DD/MM/YYYY HH24:Mi') and fecha>=:fechaInicioMes and fecha<=:fechaFinMes order by fecha desc")})

public class ViewEstadoEnviosLotesJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewEstadoEnviosLotesJPA() {
		this.loteEnvioId=null;
		this.nombreLote = null;
		this.servicioId = null;
		this.nombreServicio = null;
		this.aplicacionId = null;
		this.nombreAplicacion = null;
		this.enviados = null;
		this.incidencia = null;
		this.anulado = null;
		this.pendiente = null;
		this.fecha = null;
		
	}

	
	@Id
	@Column(name="LOTEENVIOID")
	protected Integer loteEnvioId;
	
	@Column(name="NOMBRELOTE")
	protected String nombreLote;
	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name="NOMBRESERVICIO")
	protected String nombreServicio;
	
	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name="NOMBREAPLICACION")
	protected String nombreAplicacion;
	
	@Column(name="ENVIADOS")
	protected Integer enviados;
	
	@Column(name="INCIDENCIA")
	protected Integer incidencia;
	
	@Column(name="ANULADO")
	protected Integer anulado;
	
	@Column(name="PENDIENTE")
	protected Integer pendiente;
	
	@Column(name="FECHA")
	protected Date fecha;


	/*@Column(name="CONTENIDO")
	protected Blob contenido;
	*/

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.loteEnvioId;
	}


	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}


	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}


	public String getNombreLote() {
		return nombreLote;
	}


	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}


	public Integer getServicioId() {
		return servicioId;
	}


	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}


	public String getNombreServicio() {
		return nombreServicio;
	}


	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}


	public Integer getAplicacionId() {
		return aplicacionId;
	}


	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}


	public String getNombreAplicacion() {
		return nombreAplicacion;
	}


	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}


	public Integer getEnviados() {
		return enviados;
	}


	public void setEnviados(Integer enviados) {
		this.enviados = enviados;
	}


	public Integer getIncidencia() {
		return incidencia;
	}


	public void setIncidencia(Integer incidencia) {
		this.incidencia = incidencia;
	}


	public Integer getAnulado() {
		return anulado;
	}


	public void setAnulado(Integer anulado) {
		this.anulado = anulado;
	}


	public Integer getPendiente() {
		return pendiente;
	}


	public void setPendiente(Integer pendiente) {
		this.pendiente = pendiente;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
