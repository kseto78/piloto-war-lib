package es.mpr.plataformamensajeria.beans;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 *  <p>Clase que representa un objeto EstadoLoteEnvio
 *  <p>
 *  Representa la vista VIEW_ESTADO_LOTESENVIOS de la base de datos
 *  @author i-nercya
 */
public class EstadoLotesEnviosBean implements Audit{

	/**
	 * 
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


	protected Long loteenvioid;
	protected String nombrelote;
	protected Long servicioid;
	protected String nombreservicio;
	protected Long aplicacionid;
	protected String nombreaplicacion;
	protected Long enviados;
	protected Long incidencia;
	protected Long anulado;
	protected Long pendiente;
	protected Date fecha;
	
	
	
	public String getFechaFormateada(){
		String fecha="";
		if(this.fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fecha = sdf.format(this.fecha);
		}
		return fecha;
	}


	public Object getId() {
		// TODO Auto-generated method stub
		return this.loteenvioid;
	}
	public void setId(Object id){
		this.loteenvioid =(Long)id;
	}
	

	public Long getEnviados() {
		return enviados;
	}
	public void setEnviados(Long enviados) {
		this.enviados = enviados;
	}
	public Long getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(Long incidencia) {
		this.incidencia = incidencia;
	}
	public Long getAnulado() {
		return anulado;
	}
	public void setAnulado(Long anulado) {
		this.anulado = anulado;
	}
	public Long getPendiente() {
		return pendiente;
	}
	public void setPendiente(Long pendiente) {
		this.pendiente = pendiente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getEnviadosString(){
		if(this.enviados != null){
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(enviados);
		}
		return null;
	}

	public String getIncidenciaString(){
		if(this.incidencia != null){
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(incidencia);
		}
		return null;
	}
	
	public String getAnuladoString(){
		if(this.anulado != null){
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(anulado);
		}
		return null;
	}
	
	public String getPendienteString(){
		if(this.pendiente != null){
			DecimalFormat formateador = new DecimalFormat("###,###.##"); 
			return formateador.format(pendiente);
		}
		return null;
	}


	/**
	 * @return the loteenvioid
	 */
	public Long getLoteenvioid() {
		return loteenvioid;
	}


	/**
	 * @param loteenvioid the loteenvioid to set
	 */
	public void setLoteenvioid(Long loteenvioid) {
		this.loteenvioid = loteenvioid;
	}


	/**
	 * @return the nombrelote
	 */
	public String getNombrelote() {
		return nombrelote;
	}


	/**
	 * @param nombrelote the nombrelote to set
	 */
	public void setNombrelote(String nombrelote) {
		this.nombrelote = nombrelote;
	}


	/**
	 * @return the servicioid
	 */
	public Long getServicioid() {
		return servicioid;
	}


	/**
	 * @param servicioid the servicioid to set
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}


	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreservicio;
	}


	/**
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreservicio = nombreServicio;
	}


	/**
	 * @return the aplicacionid
	 */
	public Long getAplicacionid() {
		return aplicacionid;
	}


	/**
	 * @param aplicacionid the aplicacionid to set
	 */
	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}


	/**
	 * @return the nombreaplicacion
	 */
	public String getNombreaplicacion() {
		return nombreaplicacion;
	}


	/**
	 * @param nombreaplicacion the nombreaplicacion to set
	 */
	public void setNombreaplicacion(String nombreaplicacion) {
		this.nombreaplicacion = nombreaplicacion;
	}
	
	
}
