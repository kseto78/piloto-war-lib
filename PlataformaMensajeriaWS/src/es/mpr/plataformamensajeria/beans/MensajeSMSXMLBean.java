package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;

public class MensajeSMSXMLBean {
	private String idMensaje;
	private String idExterno;
	private String cuerpo;
	private String modo;
	private ArrayList<String> listaDestinatarios;
	
	private String docUsuario;
	private String codSIA;
	private String codOrganismo;
	private String codOrganismoPagador;
	
	//errores para la respuesta
	 
	public ArrayList<String> getListadoErroresMensajes() {
		return listadoErroresMensajes;
	}

	public void setListadoErroresMensajes(ArrayList<String> listadoErroresMensajes) {
		this.listadoErroresMensajes = listadoErroresMensajes;
	}
	private ArrayList<String> listadoErroresMensajes;
	
	public MensajeSMSXMLBean() {
		listaDestinatarios = new ArrayList<String>();
		listadoErroresMensajes= new ArrayList<String>();
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

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public ArrayList<String> getListaDestinatarios() {
		return listaDestinatarios;
	}

	public void setListaDestinatarios(
			ArrayList<String> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}


	public void addDestinatario(String numTlf){
		if(this.listaDestinatarios!=null){
			this.listaDestinatarios.add(numTlf);
		}
	}
	public void addErroresMensaje(String errorMensaje){
		if(this.listadoErroresMensajes!=null){
			this.listadoErroresMensajes.add(errorMensaje);
		}
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

	
}
