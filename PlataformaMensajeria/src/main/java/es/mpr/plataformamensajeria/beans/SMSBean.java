package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Bean para mapear la información de los envíos
 *  
 *  <p>
 *  
 *  
 *  @author i-nercya
 */
public class SMSBean implements Audit{

	public SMSBean() {
		this.nombreAplicacion=null;
		this.nombreServicio=null;
		this.destinatario=null;
		this.textoSMS=null;
		this.aplicacionId=null;
		this.servicioId=null;
		this.idEnviosSMS=null;
		this.loteEnvio=null;
		this.loteEnvioId=null;
		this.servidorId=null;
		this.nombreServidor=null;
		this.ultimoEnvio=null;
		this.estado=null;
		this.estadoId=null;
		this.idSMS=null;
	}
	
	private String nombreAplicacion;
	private String nombreServicio;
	private String destinatario;
	private String textoSMS;
	private Integer aplicacionId;
	private Integer servicioId;
	private Integer idEnviosSMS;
	private String loteEnvio;
	private Integer loteEnvioId;
	private Integer servidorId;
	private String nombreServidor;
	private Date ultimoEnvio;
	private String estado;
	private Integer canalId;
	private Integer estadoId;
	private String idSMS;

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

	public Integer getIdEnviosSMS() {
		return idEnviosSMS;
	}

	public void setIdEnviosSMS(Integer idEnviosSMS) {
		this.idEnviosSMS = idEnviosSMS;
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

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
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

	public String getIdSMS() {
		return idSMS;
	}

	public void setIdSMS(String idSMS) {
		this.idSMS = idSMS;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
