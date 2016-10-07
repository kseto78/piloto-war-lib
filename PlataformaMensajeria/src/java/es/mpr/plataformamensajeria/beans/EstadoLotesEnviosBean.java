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


	protected Integer loteEnvioId;
	protected String nombreLote;
	protected Integer servicioId;
	protected String nombreServicio;
	protected Integer aplicacionId;
	protected String nombreAplicacion;
	protected Integer enviados;
	protected Integer incidencia;
	protected Integer anulado;
	protected Integer pendiente;
	protected Date fecha;
	
	public String getFechaFormateada(){
		String fecha="";
		if(this.fecha!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fecha = sdf.format(this.fecha);
		}
		return fecha;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Object getId() {
		// TODO Auto-generated method stub
		return this.loteEnvioId;
	}
	public void setId(Object id){
		this.loteEnvioId =(Integer)id;
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
	
	
}
