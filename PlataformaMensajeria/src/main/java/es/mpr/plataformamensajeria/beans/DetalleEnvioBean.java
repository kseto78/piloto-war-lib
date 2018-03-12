
package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
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
public class DetalleEnvioBean implements Audit, Serializable{

	private static final long serialVersionUID = -3761280846672129939L;

	public DetalleEnvioBean() {
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
		this.listadoAdjuntos = new ArrayList<>();
		this.listadoImagenes = new ArrayList<>();
		this.listadoHistorico = new ArrayList<>();
		this.descripcion = null;
		this.docUsuario = null;
		this.codSIA = null;
		this.codOrganismo = null;
		this.codOrganismoPagador = null;
		this.icono = null;
		this.sonido = null;
		this.nombreUsuario = null;
	}

	
	protected String envioId;
	private Long mensajeId;
	private String nombreAplicacion;
	private String nombreServicio;
	private String nombreLoteEnvio;
	private String origen;
	private String destino;
	private String bcc;
	private String cc;
	private String asunto;
	private String cuerpo;
	private Long idLote;
	private String idExterno; 
	private List<AdjuntoEmailBean> listadoAdjuntos = new ArrayList<>();
	private List<AdjuntoEmailBean> listadoImagenes = new ArrayList<>();
	private List<HistoricoBean> listadoHistorico = new ArrayList<>();
	private String descripcion;
	private String telefono;
	private String tipoMensaje;
	private String codificacion;
	private Integer prioridad;
	private String tipoContenido;
	private String docUsuario;
	private String codSIA;
	private String codOrganismo;
	private String codOrganismoPagador;
	private String icono;
	private String sonido;
	private String nombreUsuario;
	
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getTipoMensaje() {
		return tipoMensaje;
	}


	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}


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
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
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
	public Long getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
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
		return new ArrayList<>(listadoAdjuntos);
	}
	public void setListadoAdjuntos(List<AdjuntoEmailBean> listadoAdjuntos) {
		this.listadoAdjuntos = new ArrayList<>(listadoAdjuntos);
	}
	public List<HistoricoBean> getListadoHistorico() {
		return new ArrayList<>(listadoHistorico);
	}
	public void setListadoHistorico(List<HistoricoBean> listadoHistorico) {
		this.listadoHistorico = new ArrayList<>(listadoHistorico);
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


	public String getDocUsuario() {
		return docUsuario;
	}


	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}


	public String getCodSIA() {
		return codSIA;
	}


	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
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


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
	}


	public String getSonido() {
		return sonido;
	}


	public void setSonido(String sonido) {
		this.sonido = sonido;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	
	
}
