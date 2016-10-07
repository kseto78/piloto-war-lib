package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>
 *  Representa los envios de tipo email
 *  <p>
 * 
 *  
 *  @author i-nercya
 */
public class DetalleEnvioEmailBean implements Audit{

	public DetalleEnvioEmailBean() {
		this.envioId=null;
		this.nombreAplicacion=null;
		this.nombreServicio=null;
		this.nombreLoteEnvio = null;
		this.origen = null;
		this.destino = null;
		this.bcc = null;
		this.cc = null;
		this.asunto = null;
		this.cuerpo = null;
		this.listadoAdjuntos = new ArrayList<AdjuntoEmailBean>();
		this.listadoImagenes = new ArrayList<AdjuntoEmailBean>();
		this.listadoHistorico = new ArrayList<HistoricoBean>();
		this.descripcion = null;
	}

	
	protected String envioId;
	private Integer emailId;
	private String nombreAplicacion;
	private String nombreServicio;
	private String nombreLoteEnvio;
	private String origen;
	private String destino;
	private String bcc;
	private String cc;
	private String asunto;
	private String cuerpo;
	private Integer idLote;
	private String idExterno;
	private List<AdjuntoEmailBean> listadoAdjuntos = new ArrayList<AdjuntoEmailBean>();
	private List<AdjuntoEmailBean> listadoImagenes = new ArrayList<AdjuntoEmailBean>();
	private List<HistoricoBean> listadoHistorico = new ArrayList<HistoricoBean>();
	private String descripcion;
	
	private String codificacion;
	private String prioridad;
	private String tipoContenido;
	
	public String getPrioridadHTML(){
		if(prioridad!=null){
			if(prioridad.equals("1")){
				return "Urgente";
				
			}else{
				return "Normal";
			}
		}
		return "";
		
	}
	
	
	public String getCodificacion() {
		return codificacion;
	}
	public void setCodificacion(String codificacion) {
		this.codificacion = codificacion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	public List<AdjuntoEmailBean> getListadoImagenes() {
		return listadoImagenes;
	}
	public void setListadoImagenes(List<AdjuntoEmailBean> listadoImagenes) {
		this.listadoImagenes = listadoImagenes;
	}
	public Integer getEmailId() {
		return emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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

	public void addCc(String cc){
		if(this.cc!=null&&this.cc.length()>0){
			this.cc+=", <"+cc+">";
		}else{
			this.cc="<"+cc+">";
		}
	}
	
	public void addBcc(String bcc){
		if(this.bcc!=null&&this.bcc.length()>0){
			this.bcc+=", <"+bcc+">";
		}else{
			this.bcc="<"+bcc+">";
		}
	}
	public void addDestino(String to){
		if(destino!=null&&destino.length()>0){
			destino+=", <"+to+">";
		}else{
			destino="<"+to+">";
		}
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
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String cabecera) {
		this.asunto = cabecera;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public List<AdjuntoEmailBean> getListadoAdjuntos() {
		return new ArrayList<AdjuntoEmailBean>(listadoAdjuntos);
	}
	public void setListadoAdjuntos(List<AdjuntoEmailBean> listadoAdjuntos) {
		this.listadoAdjuntos = new ArrayList<AdjuntoEmailBean>(listadoAdjuntos);
	}
	public List<HistoricoBean> getListadoHistorico() {
		return new ArrayList<HistoricoBean>(listadoHistorico);
	}
	public void setListadoHistorico(List<HistoricoBean> listadoHistorico) {
		this.listadoHistorico = new ArrayList<HistoricoBean>(listadoHistorico);
	}
	public Object getId() {
		return this.envioId;
	}
	public void setId(Object id){
		this.envioId =(String)id;
	}

	public String getEnvioId() {
		return envioId;
	}
	public void setEnvioId(String envioId) {
		this.envioId = envioId;
	}
	@Override
	public String obtenerXML() {
		return null;
	}


	
	
}
