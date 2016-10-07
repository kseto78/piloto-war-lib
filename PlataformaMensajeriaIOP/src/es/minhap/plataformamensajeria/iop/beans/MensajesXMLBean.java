package es.minhap.plataformamensajeria.iop.beans;

import java.util.ArrayList;

import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesMailXMLBean;

public class MensajesXMLBean {
	
	private String idMensaje="";
	private String idExterno="";
	private String asunto="";
	private String cuerpo="";
	private String origen="";
	private String modo="";
	private String formatoCuerpo="";
	private String codificacion="";
	/**
	 * Valores: 1 (Urgente) 0 (Normal)
	 */
	private Integer prioridad;
	
	private String docUsuario="";
	private String codSIA="";
	private String codOrganismo="";
	
	private ArrayList<DestinatarioPeticionLotesMailXMLBean> listaDestinatarios;
	private ArrayList<AdjuntosXMLBean> listaAdjuntos;
	private ArrayList<ImagenXMLBean> listaImagenes;
	//errores para la respuesta
	
	private ArrayList<String> listadoErroresMensajes;
	
	public MensajesXMLBean() {
		listaDestinatarios = new ArrayList<DestinatarioPeticionLotesMailXMLBean>();
		listaAdjuntos = new ArrayList<AdjuntosXMLBean>();
		listadoErroresMensajes= new ArrayList<String>();
		listaImagenes = new ArrayList<ImagenXMLBean>();
	}
	
	public String getCodificacion() {
		return codificacion;
	}

	public void setCodificacion(String codificacion) {
		this.codificacion = codificacion;
	}
	public void setPrioridad(Integer prioridad){
		this.prioridad = prioridad;
		
	
	}
	
	public Integer getPrioridad() {
		return prioridad;
	}

	public void setFormatoCuerpo(String formatoCuerpo) {
		this.formatoCuerpo = formatoCuerpo;
	}
	
	public String getFormatoCuerpo() {
		return formatoCuerpo;
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

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}
	public ArrayList<ImagenXMLBean> getListaImagenes(){
		return listaImagenes;
	}
	public void setListaImagenes(ArrayList<ImagenXMLBean> listaImagenes){
		this.listaImagenes = listaImagenes;
	}
	public ArrayList<DestinatarioPeticionLotesMailXMLBean> getListaDestinatarios() {
		return listaDestinatarios;
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

	
	

	public ArrayList<AdjuntosXMLBean> getListaAdjuntos() {
		return listaAdjuntos;
	}

	public void setListaAdjuntos(ArrayList<AdjuntosXMLBean> listaAdjuntos) {
		this.listaAdjuntos = listaAdjuntos;
	}
	public void addImagen(ImagenXMLBean imagenBean){
		if(this.listaImagenes!=null){
			listaImagenes.add(imagenBean);
		}
		
	}
	public void addAdjunto(AdjuntosXMLBean adjuntoBean){
		if(this.listaAdjuntos!=null){
			listaAdjuntos.add(adjuntoBean);
		}
	}
	public void addDestinatario(DestinatarioPeticionLotesMailXMLBean destinatario){
		if(this.listaDestinatarios!=null){
			this.listaDestinatarios.add(destinatario);
		}
	}
	public void addErroresMensaje(String errorMensaje){
		if(this.listadoErroresMensajes!=null){
			this.listadoErroresMensajes.add(errorMensaje);
		}
	}
	public ArrayList<String> getListadoErroresMensajes(){
		return this.listadoErroresMensajes;
	}
	

	public void setListaDestinatarios(
			ArrayList<DestinatarioPeticionLotesMailXMLBean> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public void setListadoErroresMensajes(ArrayList<String> listadoErroresMensajes) {
		this.listadoErroresMensajes = listadoErroresMensajes;
	}
}
