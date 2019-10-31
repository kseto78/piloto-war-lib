package es.minhap.plataformamensajeria.iop.beans;


public class FiltroXMLBean {
	
	private String idServicio="";
	private String usuario="";
	private String pass="";
	private String servicio="";
	private String idCanal="";
	private String canal="";
	private String idAplicacion="";
	private String aplicacion="";
	//private String idLote;
	
	private String lote="";
	private String idMensaje="";
	private String idExterno="";
	private String estado="";
	private String fechaDesde="";
	private String fechaHasta="";
	private String docUsuario="";
	private String codSia="";
	private String codOrganismo="";
	private String codOrganismoPagador="";
	@Deprecated
	private MensajesXMLBean mensaje;
	
	public FiltroXMLBean() { //

	}
	
	public void addMensaje(MensajesXMLBean mensaje){
		this.mensaje=mensaje;
	}
	
	
	
	public MensajesXMLBean getMensaje() {
		return mensaje;
	}

	public void setMensaje(MensajesXMLBean mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	
	
	public String getDocUsuario() {
		return docUsuario;
	}

	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}


	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}



	public String getIdServicio() {
		return idServicio;
	}



	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getServicio() {
		return servicio;
	}



	public void setServicio(String servicio) {
		this.servicio = servicio;
	}



	public String getIdCanal() {
		return idCanal;
	}



	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}



	public String getCanal() {
		return canal;
	}



	public void setCanal(String canal) {
		this.canal = canal;
	}



	public String getIdAplicacion() {
		return idAplicacion;
	}



	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}



	public String getAplicacion() {
		return aplicacion;
	}



	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}



	public String getLote() {
		return lote;
	}



	public void setLote(String lote) {
		this.lote = lote;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getFechaDesde() {
		return fechaDesde;
	}



	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}



	public String getFechaHasta() {
		return fechaHasta;
	}



	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}



	public String getCodSia() {
		return codSia;
	}



	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}



	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}



	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	
	
}
