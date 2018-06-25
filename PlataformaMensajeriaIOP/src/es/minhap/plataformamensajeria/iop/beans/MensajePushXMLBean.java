package es.minhap.plataformamensajeria.iop.beans;

import java.util.ArrayList;

public class MensajePushXMLBean {
	private String idMensaje="";
	private String idExterno="";
	private String cuerpo="";
	
	
	private String docUsuario="";
	private String codSIA="";
	private String codOrganismo="";
	private String identificadorUsuario="";
	private String icono="";
	private String sonido="";
	private String titulo="";
	
	
	

	//errores para la respuesta
	 
	public ArrayList<String> getListadoErroresMensajes() {
		return listadoErroresMensajes;
	}

	public void setListadoErroresMensajes(ArrayList<String> listadoErroresMensajes) {
		this.listadoErroresMensajes = listadoErroresMensajes;
	}
	private ArrayList<String> listadoErroresMensajes;
	
	public MensajePushXMLBean() {		
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

	

	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
}
