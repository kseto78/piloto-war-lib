package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  @version 1.0  
 *  @author Altran
 */
public class GestionEnvioBean implements Audit{

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
	}
	private String editableCheckbox;
	private Integer mensajeId;
	private String id;
	private String envioId;
	private String aplicacion;
	private String servicio;
	private String loteEnvio;
	private String servidor;
	private Date ultimoEnvio;
	private String ultimoEnvioStr;
	private String estado;
	private String destinatario;
	private Integer canalId;
	private Integer aplicacionId;
	private Integer servidorId;
	private Integer servicioId;
	private Integer estadoId;
	private Date fechaDesde;
	private Date fechaHasta;
	private String email;
	private String telefonoMovil;
	private Integer idLote;
	private String idExterno;
	private String canal;
	private String nombre;
	private String codigoExterno;
	private Integer anio;
	private Integer mes;
	private String docUsuario;
	private String codSIA;
	private String codOrganismo;
	private String codOrganismoPagador;
	private String icono;
	private String sonido;
	private String nombreUsuario;
	private Integer vistaEnviosId;
	private Integer multidestinatario;
	private Integer destinatariosMensajes;
	
	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}
	public Integer getMensajeId() {
		return mensajeId;
	}
	public String getUltimoEnvioStr() {
		return ultimoEnvioStr;
	}
	public void setUltimoEnvioStr(String ultimoEnvioStr) {
		this.ultimoEnvioStr = ultimoEnvioStr;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getLoteEnvio() {
		return loteEnvio;
	}

	public void setLoteEnvio(String loteEnvio) {
		this.loteEnvio = loteEnvio;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
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

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public String getEnvioId() {
		return envioId;
	}

	public void setEnvioId(String envioId) {
		this.envioId = envioId;
	}
	public String getEditableCheckbox() {
		return editableCheckbox;
	}

	public void setEditableCheckbox(String editableCheckbox) {
		this.editableCheckbox = editableCheckbox;
	}

	@Override
	/**
	 * Obtiene un xml con las propiedades
	 * 
	 */
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoExterno() {
		return codigoExterno;
	}
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
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
	public Integer getVistaEnviosId() {
		return vistaEnviosId;
	}
	public void setVistaEnviosId(Integer vistaEnviosId) {
		this.vistaEnviosId = vistaEnviosId;
	}
	public Integer getMultidestinatario() {
		return multidestinatario;
	}
	public void setMultidestinatario(Integer multidestinatario) {
		this.multidestinatario = multidestinatario;
	}
	public Integer getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	public void setDestinatariosMensajes(Integer destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
}
