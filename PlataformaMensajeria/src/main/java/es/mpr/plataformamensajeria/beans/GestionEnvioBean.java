package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * The Class GestionEnvioBean.
 *
 * @author Altran
 * @version 1.0
 */
public class GestionEnvioBean implements Audit, Serializable{

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2205483197518833217L;

	
	/**
	 * Constructor de gestion envio bean.
	 */
	public GestionEnvioBean() {
		this.aplicacion=null;
		this.servicio=null;
		this.loteEnvio=null;
		this.servidor=null;
		this.ultimoEnvio=null;
		this.estado=null;
		this.destinatario=null;
		this.canalId=null;
		this.aplicacionId=null;
		this.servidorId=null;
		this.servicioId=null;
		this.estadoId=null;
		this.fechaDesde=null;
		this.fechaHasta=null;
		this.email=null;
		this.telefonoMovil=null;		
		this.envioId = null;
		this.id=null;
		this.idLote = null;
		this.idExterno = null;
		this.editableCheckbox=null;
		this.canal=null;
		this.nombre=null;
		this.codigoExterno=null;
		this.anio=null;
		this.mes=null;
		this.docUsuario = null;
		this.codSIA = null;
		this.codOrganismo = null;
		this.codOrganismoPagador = null;
		this.icono = null;
		this.sonido = null;
		this.nombreUsuario = null;
		this.vistaEnviosId = null;
		this.multidestinatario = null;
		this.destinatariosMensajes = null;
		this.arrayOrganismos = null;
	}
	
	/**  editable checkbox. */
	private String editableCheckbox;
	
	/**  mensaje id. */
	private Long mensajeId;
	
	/**  id. */
	private String id;
	
	/**  envio id. */
	private String envioId;
	
	/**  aplicacion. */
	private String aplicacion;
	
	/**  servicio. */
	private String servicio;
	
	/**  lote envio. */
	private String loteEnvio;
	
	/**  servidor. */
	private String servidor;
	
	/**  ultimo envio. */
	private Date ultimoEnvio;
	
	/**  ultimo envio str. */
	private String ultimoEnvioStr;
	
	/**  estado. */
	private String estado;
	
	/**  destinatario. */
	private String destinatario;
	
	/**  canal id. */
	private Long canalId;
	
	/**  aplicacion id. */
	private Long aplicacionId;
	
	/**  servidor id. */
	private Long servidorId;
	
	/**  servicio id. */
	private Long servicioId;
	
	/**  estado id. */
	private Long estadoId;
	
	/**  fecha desde. */
	private Date fechaDesde;
	
	/**  fecha hasta. */
	private Date fechaHasta;
	
	/**  email. */
	private String email;
	
	/**  telefono movil. */
	private String telefonoMovil;
	
	/**  id lote. */
	private Long idLote;
	
	/**  id externo. */
	private String idExterno;
	
	/**  canal. */
	private String canal;
	
	/**  nombre. */
	private String nombre;
	
	/**  codigo externo. */
	private String codigoExterno;
	
	/**  anio. */
	private Integer anio;
	
	/**  mes. */
	private Integer mes;
	
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
	
	/**  vista envios id. */
	private Integer vistaEnviosId;
	
	/**  multidestinatario. */
	private Integer multidestinatario;
	
	/**  destinatarios mensajes. */
	private Long destinatariosMensajes;
	
	/**  boton intercambios. */
	private Boolean botonIntercambios;
	
	/**  array organismos. */
	private List<String> arrayOrganismos;
	
	/**
	 * Modificar mensaje id.
	 *
	 * @param mensajeId new mensaje id
	 */
	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
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
	 * Obtener ultimo envio str.
	 *
	 * @return ultimo envio str
	 */
	public String getUltimoEnvioStr() {
		return ultimoEnvioStr;
	}
	
	/**
	 * Modificar ultimo envio str.
	 *
	 * @param ultimoEnvioStr new ultimo envio str
	 */
	public void setUltimoEnvioStr(String ultimoEnvioStr) {
		this.ultimoEnvioStr = ultimoEnvioStr;
	}

	/**
	 * Comprueba boton intercambios.
	 *
	 * @return true, si es boton intercambios
	 */
	public boolean isBotonIntercambios() {
		return botonIntercambios;
	}
	
	/**
	 * Modificar boton intercambios.
	 *
	 * @param botonIntercambios new boton intercambios
	 */
	public void setBotonIntercambios(boolean botonIntercambios) {
		this.botonIntercambios = botonIntercambios;
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
	 * Obtener id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtener fecha desde.
	 *
	 * @return fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Modificar fecha desde.
	 *
	 * @param fechaDesde new fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Obtener fecha hasta.
	 *
	 * @return fecha hasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Modificar fecha hasta.
	 *
	 * @param fechaHasta new fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Obtener email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modificar email.
	 *
	 * @param email new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtener telefono movil.
	 *
	 * @return telefono movil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	/**
	 * Modificar telefono movil.
	 *
	 * @param telefonoMovil new telefono movil
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Obtener servicio.
	 *
	 * @return servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * Modificar servicio.
	 *
	 * @param servicio new servicio
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * Obtener lote envio.
	 *
	 * @return lote envio
	 */
	public String getLoteEnvio() {
		return loteEnvio;
	}

	/**
	 * Modificar lote envio.
	 *
	 * @param loteEnvio new lote envio
	 */
	public void setLoteEnvio(String loteEnvio) {
		this.loteEnvio = loteEnvio;
	}

	/**
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * Modificar servidor.
	 *
	 * @param servidor new servidor
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	/**
	 * Obtener ultimo envio.
	 *
	 * @return ultimo envio
	 */
	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}

	/**
	 * Modificar ultimo envio.
	 *
	 * @param ultimoEnvio new ultimo envio
	 */
	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

	/**
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtener destinatario.
	 *
	 * @return destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Modificar destinatario.
	 *
	 * @param destinatario new destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Obtener canal id.
	 *
	 * @return canal id
	 */
	public Long getCanalId() {
		return canalId;
	}

	/**
	 * Modificar canal id.
	 *
	 * @param canalId new canal id
	 */
	public void setCanalId(Long canalId) {
		this.canalId = canalId;
	}

	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Long getAplicacionId() {
		return aplicacionId;
	}

	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Long aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	/**
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Long getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Long getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}

	/**
	 * Obtener estado id.
	 *
	 * @return estado id
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * Modificar estado id.
	 *
	 * @param estadoId new estado id
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
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
	 * Obtener editable checkbox.
	 *
	 * @return editable checkbox
	 */
	public String getEditableCheckbox() {
		return editableCheckbox;
	}

	/**
	 * Modificar editable checkbox.
	 *
	 * @param editableCheckbox new editable checkbox
	 */
	public void setEditableCheckbox(String editableCheckbox) {
		this.editableCheckbox = editableCheckbox;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	/**
	 * Obtiene un xml con las propiedades
	 * 
	 */
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Obtener canal.
	 *
	 * @return canal
	 */
	public String getCanal() {
		return canal;
	}
	
	/**
	 * Modificar canal.
	 *
	 * @param canal new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modificar nombre.
	 *
	 * @param nombre new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtener codigo externo.
	 *
	 * @return codigo externo
	 */
	public String getCodigoExterno() {
		return codigoExterno;
	}
	
	/**
	 * Modificar codigo externo.
	 *
	 * @param codigoExterno new codigo externo
	 */
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	
	/**
	 * Obtener anio.
	 *
	 * @return anio
	 */
	public Integer getAnio() {
		return anio;
	}
	
	/**
	 * Modificar anio.
	 *
	 * @param anio new anio
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	/**
	 * Obtener mes.
	 *
	 * @return mes
	 */
	public Integer getMes() {
		return mes;
	}
	
	/**
	 * Modificar mes.
	 *
	 * @param mes new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
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
	
	/**
	 * Obtener vista envios id.
	 *
	 * @return vista envios id
	 */
	public Integer getVistaEnviosId() {
		return vistaEnviosId;
	}
	
	/**
	 * Modificar vista envios id.
	 *
	 * @param vistaEnviosId new vista envios id
	 */
	public void setVistaEnviosId(Integer vistaEnviosId) {
		this.vistaEnviosId = vistaEnviosId;
	}
	
	/**
	 * Obtener multidestinatario.
	 *
	 * @return multidestinatario
	 */
	public Integer getMultidestinatario() {
		return multidestinatario;
	}
	
	/**
	 * Modificar multidestinatario.
	 *
	 * @param multidestinatario new multidestinatario
	 */
	public void setMultidestinatario(Integer multidestinatario) {
		this.multidestinatario = multidestinatario;
	}
	
	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @return destinatarios mensajes
	 */
	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	
	/**
	 * Modificar destinatarios mensajes.
	 *
	 * @param destinatariosMensajes new destinatarios mensajes
	 */
	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
	/**
	 * Obtener array organismos.
	 *
	 * @return the arrayOrganismos
	 */
	public List<String> getArrayOrganismos() {
		return arrayOrganismos;
	}
	
	/**
	 * Modificar array organismos.
	 *
	 * @param arrayOrganismos the arrayOrganismos to set
	 */
	public void setArrayOrganismos(List<String> arrayOrganismos) {
		this.arrayOrganismos = arrayOrganismos;
	}
	
	
}
