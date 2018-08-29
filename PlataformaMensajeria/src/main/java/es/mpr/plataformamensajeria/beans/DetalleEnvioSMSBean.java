package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>Bean para mapear la información de los envíos
 *  
 *  <p>
 *  
 *  
 *  @author Altran
 */
public class DetalleEnvioSMSBean implements Audit{

	/**
	 * Constructor de detalle envio SMS bean.
	 */
	public DetalleEnvioSMSBean() {
		this.envioId = null;
		this.nombreAplicacion = null;
		this.nombreServicio = null;
		this.nombreLoteEnvio = null;
		this.movil = null;
		this.mensaje = null;
		this.listadoHistoricos = null;
	}
	
	/**  envio id. */
	private String envioId;
	
	/**  nombre aplicacion. */
	private String nombreAplicacion;
	
	/**  nombre servicio. */
	private String nombreServicio;
	
	/**  nombre lote envio. */
	private String nombreLoteEnvio;
	
	/**  movil. */
	private String movil;
	
	/**  mensaje. */
	private String mensaje;
	
	/**  id lote. */
	private Integer idLote;
	
	/**  sms id. */
	private Integer smsId;
	
	/**  listado historicos. */
	private List<HistoricoBean> listadoHistoricos = new ArrayList<HistoricoBean>();
	

	/**
	 * Obtener id lote.
	 *
	 * @return id lote
	 */
	public Integer getIdLote() {
		return idLote;
	}

	/**
	 * Modificar id lote.
	 *
	 * @param idLote new id lote
	 */
	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	/**
	 * Obtener envio id.
	 *
	 * @return envio id
	 */
	public String getEnvioId() {
		return envioId;
	}

	/**
	 * Modificar envio id.
	 *
	 * @param envioId new envio id
	 */
	public void setEnvioId(String envioId) {
		this.envioId = envioId;
	}

	/**
	 * Obtener nombre aplicacion.
	 *
	 * @return nombre aplicacion
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * Modificar nombre aplicacion.
	 *
	 * @param nombreAplicacion new nombre aplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener nombre lote envio.
	 *
	 * @return nombre lote envio
	 */
	public String getNombreLoteEnvio() {
		return nombreLoteEnvio;
	}

	/**
	 * Modificar nombre lote envio.
	 *
	 * @param nombreLoteEnvio new nombre lote envio
	 */
	public void setNombreLoteEnvio(String nombreLoteEnvio) {
		this.nombreLoteEnvio = nombreLoteEnvio;
	}

	/**
	 * Obtener movil.
	 *
	 * @return movil
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Modificar movil.
	 *
	 * @param movil new movil
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}

	/**
	 * Obtener mensaje.
	 *
	 * @return mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Modificar mensaje.
	 *
	 * @param mensaje new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Obtener listado historicos.
	 *
	 * @return listado historicos
	 */
	public List<HistoricoBean> getListadoHistoricos() {
		return listadoHistoricos;
	}

	/**
	 * Modificar listado historicos.
	 *
	 * @param listadoHistoricos new listado historicos
	 */
	public void setListadoHistoricos(List<HistoricoBean> listadoHistoricos) {
		this.listadoHistoricos = listadoHistoricos;
	}

	/**
	 * Obtener sms id.
	 *
	 * @return sms id
	 */
	public Integer getSmsId() {
		return smsId;
	}

	/**
	 * Modificar sms id.
	 *
	 * @param smsId new sms id
	 */
	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
