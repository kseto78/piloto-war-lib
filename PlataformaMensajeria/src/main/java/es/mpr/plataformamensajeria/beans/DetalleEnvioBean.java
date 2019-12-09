
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

	protected static final String BLANK = ", <";

	protected static final String R_CONST_REF = "<";

	protected static final String R_CONST_0 = ">";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3761280846672129939L;

	/**  envio id. */
	protected String envioId;

	/**  mensaje id. */
	private Long mensajeId;

	/**  nombre aplicacion. */
	private String nombreAplicacion;

	/**  nombre servicio. */
	private String nombreServicio;

	/**  nombre lote envio. */
	private String nombreLoteEnvio;

	/**  origen. */
	private String origen;

	/**  destino. */
	private String destino;

	/**  bcc. */
	private String bcc;

	/**  cc. */
	private String cc;

	/**  asunto. */
	private String asunto;

	/**  cuerpo. */
	private String cuerpo;

	/**  id lote. */
	private Long idLote;

	/**  id externo. */
	private String idExterno;

	/**  listado adjuntos. */
	private List<AdjuntoEmailBean> listadoAdjuntos = new ArrayList<>();

	/**  listado imagenes. */
	private List<AdjuntoEmailBean> listadoImagenes = new ArrayList<>();

	/**  listado historico. */
	private List<HistoricoBean> listadoHistorico = new ArrayList<>();

	/**  descripcion. */
	private String descripcion;

	/**  telefono. */
	private String telefono;

	/**  tipo mensaje. */
	private String tipoMensaje;

	/**  codificacion. */
	private String codificacion;

	/**  prioridad. */
	private Integer prioridad;

	/**  tipo contenido. */
	private String tipoContenido;

	/**  doc usuario. */
	private String docUsuario;

	/**  cod SIA. */
	private String codSIA;

	/**  cod organismo. */
	private String codOrganismo;

	/**  cod organismo pagador. */
	private String codOrganismoPagador;

	/**  icono. */
	private String icono;

	/**  sonido. */
	private String sonido;

	/**  nombre usuario. */
	private String nombreUsuario;

	/**
	 * Constructor de detalle envio bean.
	 */
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

	
	/**
	 * Obtener telefono.
	 *
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * Modificar telefono.
	 *
	 * @param telefono new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
	 * Obtener tipo mensaje.
	 *
	 * @return tipo mensaje
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}


	/**
	 * Modificar tipo mensaje.
	 *
	 * @param tipoMensaje new tipo mensaje
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}


	/**
	 * Obtener prioridad HTML.
	 *
	 * @return prioridad HTML
	 */
	public String getPrioridadHTML(){
		if(prioridad!=null){
			if("1".equals(prioridad)){
				return "Urgente";
				
			}else{
				return "Normal";
			}
		}
		return "";
		
	}
	
	
	/**
	 * Obtener codificacion.
	 *
	 * @return codificacion
	 */
	public String getCodificacion() {
		return codificacion;
	}
	
	/**
	 * Modificar codificacion.
	 *
	 * @param codificacion new codificacion
	 */
	public void setCodificacion(String codificacion) {
		this.codificacion = codificacion;
	}
	
	/**
	 * Obtener prioridad.
	 *
	 * @return prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}
	
	/**
	 * Modificar prioridad.
	 *
	 * @param prioridad new prioridad
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	/**
	 * Obtener tipo contenido.
	 *
	 * @return tipo contenido
	 */
	public String getTipoContenido() {
		return tipoContenido;
	}
	
	/**
	 * Modificar tipo contenido.
	 *
	 * @param tipoContenido new tipo contenido
	 */
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	
	/**
	 * Obtener listado imagenes.
	 *
	 * @return listado imagenes
	 */
	public List<AdjuntoEmailBean> getListadoImagenes() {
		return listadoImagenes;
	}
	
	/**
	 * Modificar listado imagenes.
	 *
	 * @param listadoImagenes new listado imagenes
	 */
	public void setListadoImagenes(List<AdjuntoEmailBean> listadoImagenes) {
		this.listadoImagenes = listadoImagenes;
	}
	
	/**
	 * Obtener mensaje id.
	 *
	 * @return mensaje id
	 */
	public Long getMensajeId() {
		return mensajeId;
	}

	/**
	 * Modificar mensaje id.
	 *
	 * @param mensajeId new mensaje id
	 */
	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtener id lote.
	 *
	 * @return id lote
	 */
	public Long getIdLote() {
		return idLote;
	}

	/**
	 * Modificar id lote.
	 *
	 * @param idLote new id lote
	 */
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	/**
	 * Obtener id externo.
	 *
	 * @return id externo
	 */
	public String getIdExterno() {
		return idExterno;
	}

	/**
	 * Modificar id externo.
	 *
	 * @param idExterno new id externo
	 */
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	/**
	 * Agrega cc.
	 *
	 * @param cc the cc
	 */
	public void addCc(String cc){
		if(this.cc!=null&&!this.cc.isEmpty()){
			this.cc+=BLANK+cc+R_CONST_0;
		}else{
			this.cc=R_CONST_REF+cc+R_CONST_0;
		}
	}
	
	/**
	 * Agrega bcc.
	 *
	 * @param bcc the bcc
	 */
	public void addBcc(String bcc){
		if(this.bcc!=null&&!this.bcc.isEmpty()){
			this.bcc+=BLANK+bcc+R_CONST_0;
		}else{
			this.bcc=R_CONST_REF+bcc+R_CONST_0;
		}
	}
	
	/**
	 * Agrega destino.
	 *
	 * @param to the to
	 */
	public void addDestino(String to){
		if(destino!=null&&!destino.isEmpty()){
			destino+=BLANK+to+R_CONST_0;
		}else{
			destino=R_CONST_REF+to+R_CONST_0;
		}
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
	 * Obtener origen.
	 *
	 * @return origen
	 */
	public String getOrigen() {
		return origen;
	}
	
	/**
	 * Modificar origen.
	 *
	 * @param origen new origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	/**
	 * Obtener destino.
	 *
	 * @return destino
	 */
	public String getDestino() {
		return destino;
	}
	
	/**
	 * Modificar destino.
	 *
	 * @param destino new destino
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	/**
	 * Obtener bcc.
	 *
	 * @return bcc
	 */
	public String getBcc() {
		return bcc;
	}
	
	/**
	 * Modificar bcc.
	 *
	 * @param bcc new bcc
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	/**
	 * Obtener cc.
	 *
	 * @return cc
	 */
	public String getCc() {
		return cc;
	}
	
	/**
	 * Modificar cc.
	 *
	 * @param cc new cc
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	/**
	 * Obtener asunto.
	 *
	 * @return asunto
	 */
	public String getAsunto() {
		return asunto;
	}
	
	/**
	 * Modificar asunto.
	 *
	 * @param cabecera new asunto
	 */
	public void setAsunto(String cabecera) {
		this.asunto = cabecera;
	}
	
	/**
	 * Obtener cuerpo.
	 *
	 * @return cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}
	
	/**
	 * Modificar cuerpo.
	 *
	 * @param cuerpo new cuerpo
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	/**
	 * Obtener listado adjuntos.
	 *
	 * @return listado adjuntos
	 */
	public List<AdjuntoEmailBean> getListadoAdjuntos() {
		return new ArrayList<>(listadoAdjuntos);
	}
	
	/**
	 * Modificar listado adjuntos.
	 *
	 * @param listadoAdjuntos new listado adjuntos
	 */
	public void setListadoAdjuntos(List<AdjuntoEmailBean> listadoAdjuntos) {
		this.listadoAdjuntos = new ArrayList<>(listadoAdjuntos);
	}
	
	/**
	 * Obtener listado historico.
	 *
	 * @return listado historico
	 */
	public List<HistoricoBean> getListadoHistorico() {
		return new ArrayList<>(listadoHistorico);
	}
	
	/**
	 * Modificar listado historico.
	 *
	 * @param listadoHistorico new listado historico
	 */
	public void setListadoHistorico(List<HistoricoBean> listadoHistorico) {
		this.listadoHistorico = new ArrayList<>(listadoHistorico);
	}
	
	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return this.envioId;
	}
	
	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id){
		this.envioId =(String)id;
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
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}


	/**
	 * Obtener doc usuario.
	 *
	 * @return doc usuario
	 */
	public String getDocUsuario() {
		return docUsuario;
	}


	/**
	 * Modificar doc usuario.
	 *
	 * @param docUsuario new doc usuario
	 */
	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}


	/**
	 * Obtener cod SIA.
	 *
	 * @return cod SIA
	 */
	public String getCodSIA() {
		return codSIA;
	}


	/**
	 * Modificar cod SIA.
	 *
	 * @param codSIA new cod SIA
	 */
	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
	}


	/**
	 * Obtener cod organismo.
	 *
	 * @return cod organismo
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}


	/**
	 * Modificar cod organismo.
	 *
	 * @param codOrganismo new cod organismo
	 */
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}


	/**
	 * Obtener cod organismo pagador.
	 *
	 * @return cod organismo pagador
	 */
	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}


	/**
	 * Modificar cod organismo pagador.
	 *
	 * @param codOrganismoPagador new cod organismo pagador
	 */
	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}


	/**
	 * Obtener icono.
	 *
	 * @return icono
	 */
	public String getIcono() {
		return icono;
	}


	/**
	 * Modificar icono.
	 *
	 * @param icono new icono
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}


	/**
	 * Obtener sonido.
	 *
	 * @return sonido
	 */
	public String getSonido() {
		return sonido;
	}


	/**
	 * Modificar sonido.
	 *
	 * @param sonido new sonido
	 */
	public void setSonido(String sonido) {
		this.sonido = sonido;
	}


	/**
	 * Obtener nombre usuario.
	 *
	 * @return nombre usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	/**
	 * Modificar nombre usuario.
	 *
	 * @param nombreUsuario new nombre usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	
	
}
