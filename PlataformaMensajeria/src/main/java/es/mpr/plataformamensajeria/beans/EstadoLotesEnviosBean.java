package es.mpr.plataformamensajeria.beans;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 *  <p>Clase que representa un objeto EstadoLoteEnvio
 *  <p>
 *  Representa la vista VIEW_ESTADO_LOTESENVIOS de la base de datos.
 *
 * @author i-nercya
 */
public class EstadoLotesEnviosBean implements Audit{

	
	protected static final String DOT = "###,###.##";

	/**  loteenvioid. */
	protected Long loteenvioid;

	/**  nombrelote. */
	protected String nombrelote;

	/**  servicioid. */
	protected Long servicioid;

	/**  nombreservicio. */
	protected String nombreservicio;

	/**  aplicacionid. */
	protected Long aplicacionid;

	/**  nombreaplicacion. */
	protected String nombreaplicacion;

	/**  enviados. */
	protected Long enviados;

	/**  incidencia. */
	protected Long incidencia;

	/**  anulado. */
	protected Long anulado;

	/**  pendiente. */
	protected Long pendiente;

	/**  fecha. */
	protected Date fecha;



	/**
	 * Constructor de estado lotes envios bean.
	 */
	public EstadoLotesEnviosBean() {
		this.loteenvioid = null;
		this.nombrelote = null;
		this.servicioid = null;
		this.nombreservicio = null;
		this.aplicacionid = null;
		this.nombreaplicacion = null;
		this.enviados = null;
		this.incidencia = null;
		this.anulado = null;
		this.pendiente = null;
		this.fecha = null;
	}


	/**
	 * Obtener fecha formateada.
	 *
	 * @return fecha formateada
	 */
	public String getFechaFormateada(){
		String fecha="";
		if(this.fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fecha = sdf.format(this.fecha);
		}
		return fecha;
	}


	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		// TODO Auto-generated method stub
		return this.loteenvioid;
	}
	
	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id){
		this.loteenvioid =(Long)id;
	}
	

	/**
	 * Obtener enviados.
	 *
	 * @return enviados
	 */
	public Long getEnviados() {
		return enviados;
	}
	
	/**
	 * Modificar enviados.
	 *
	 * @param enviados new enviados
	 */
	public void setEnviados(Long enviados) {
		this.enviados = enviados;
	}
	
	/**
	 * Obtener incidencia.
	 *
	 * @return incidencia
	 */
	public Long getIncidencia() {
		return incidencia;
	}
	
	/**
	 * Modificar incidencia.
	 *
	 * @param incidencia new incidencia
	 */
	public void setIncidencia(Long incidencia) {
		this.incidencia = incidencia;
	}
	
	/**
	 * Obtener anulado.
	 *
	 * @return anulado
	 */
	public Long getAnulado() {
		return anulado;
	}
	
	/**
	 * Modificar anulado.
	 *
	 * @param anulado new anulado
	 */
	public void setAnulado(Long anulado) {
		this.anulado = anulado;
	}
	
	/**
	 * Obtener pendiente.
	 *
	 * @return pendiente
	 */
	public Long getPendiente() {
		return pendiente;
	}
	
	/**
	 * Modificar pendiente.
	 *
	 * @param pendiente new pendiente
	 */
	public void setPendiente(Long pendiente) {
		this.pendiente = pendiente;
	}
	
	/**
	 * Obtener fecha.
	 *
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Modificar fecha.
	 *
	 * @param fecha new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Obtener enviados string.
	 *
	 * @return enviados string
	 */
	public String getEnviadosString(){
		if(this.enviados != null){
			DecimalFormat formateador = new DecimalFormat(DOT); 
			return formateador.format(enviados);
		}
		return null;
	}

	/**
	 * Obtener incidencia string.
	 *
	 * @return incidencia string
	 */
	public String getIncidenciaString(){
		if(this.incidencia != null){
			DecimalFormat formateador = new DecimalFormat(DOT); 
			return formateador.format(incidencia);
		}
		return null;
	}
	
	/**
	 * Obtener anulado string.
	 *
	 * @return anulado string
	 */
	public String getAnuladoString(){
		if(this.anulado != null){
			DecimalFormat formateador = new DecimalFormat(DOT); 
			return formateador.format(anulado);
		}
		return null;
	}
	
	/**
	 * Obtener pendiente string.
	 *
	 * @return pendiente string
	 */
	public String getPendienteString(){
		if(this.pendiente != null){
			DecimalFormat formateador = new DecimalFormat(DOT); 
			return formateador.format(pendiente);
		}
		return null;
	}


	/**
	 * Obtener loteenvioid.
	 *
	 * @return the loteenvioid
	 */
	public Long getLoteenvioid() {
		return loteenvioid;
	}


	/**
	 * Modificar loteenvioid.
	 *
	 * @param loteenvioid the loteenvioid to set
	 */
	public void setLoteenvioid(Long loteenvioid) {
		this.loteenvioid = loteenvioid;
	}


	/**
	 * Obtener nombrelote.
	 *
	 * @return the nombrelote
	 */
	public String getNombrelote() {
		return nombrelote;
	}


	/**
	 * Modificar nombrelote.
	 *
	 * @param nombrelote the nombrelote to set
	 */
	public void setNombrelote(String nombrelote) {
		this.nombrelote = nombrelote;
	}


	/**
	 * Obtener servicioid.
	 *
	 * @return the servicioid
	 */
	public Long getServicioid() {
		return servicioid;
	}


	/**
	 * Modificar servicioid.
	 *
	 * @param servicioid the servicioid to set
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}


	/**
	 * Obtener nombre servicio.
	 *
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreservicio;
	}


	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreservicio = nombreServicio;
	}


	/**
	 * Obtener aplicacionid.
	 *
	 * @return the aplicacionid
	 */
	public Long getAplicacionid() {
		return aplicacionid;
	}


	/**
	 * Modificar aplicacionid.
	 *
	 * @param aplicacionid the aplicacionid to set
	 */
	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}


	/**
	 * Obtener nombreaplicacion.
	 *
	 * @return the nombreaplicacion
	 */
	public String getNombreaplicacion() {
		return nombreaplicacion;
	}


	/**
	 * Modificar nombreaplicacion.
	 *
	 * @param nombreaplicacion the nombreaplicacion to set
	 */
	public void setNombreaplicacion(String nombreaplicacion) {
		this.nombreaplicacion = nombreaplicacion;
	}
	
	
}
