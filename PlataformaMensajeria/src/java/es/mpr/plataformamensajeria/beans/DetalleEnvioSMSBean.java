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

	public DetalleEnvioSMSBean() {
		this.envioId = null;
		this.nombreAplicacion = null;
		this.nombreServicio = null;
		this.nombreLoteEnvio = null;
		this.movil = null;
		this.mensaje = null;
		this.listadoHistoricos = null;
	}
	private String envioId;
	private String nombreAplicacion;
	private String nombreServicio;
	private String nombreLoteEnvio;
	private String movil;
	private String mensaje;
	private Integer idLote;
	private Integer smsId;
	
	private List<HistoricoBean> listadoHistoricos = new ArrayList<HistoricoBean>();
	

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public String getEnvioId() {
		return envioId;
	}

	public void setEnvioId(String envioId) {
		this.envioId = envioId;
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

	public String getNombreLoteEnvio() {
		return nombreLoteEnvio;
	}

	public void setNombreLoteEnvio(String nombreLoteEnvio) {
		this.nombreLoteEnvio = nombreLoteEnvio;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<HistoricoBean> getListadoHistoricos() {
		return listadoHistoricos;
	}

	public void setListadoHistoricos(List<HistoricoBean> listadoHistoricos) {
		this.listadoHistoricos = listadoHistoricos;
	}

	public Integer getSmsId() {
		return smsId;
	}

	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
