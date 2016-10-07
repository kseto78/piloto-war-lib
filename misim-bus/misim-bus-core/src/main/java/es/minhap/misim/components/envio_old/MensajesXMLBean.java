package es.minhap.misim.components.envio_old;


import java.util.ArrayList;

public class MensajesXMLBean {
	
	private String idMensaje;
	private String idExterno;
	private String asunto;
	private String cuerpo;
	private String origen;
	private String modo;
	private String formatoCuerpo;
	private String codificacion;
	/**
	 * Valores: 1 (Urgente) 0 (Normal)
	 */
	private Integer prioridad;
	
	private String docUsuario;
	private String codSIA;
	private String codOrganismo;
	
	private ArrayList<DestinatarioXMLBean> listaDestinatarios;
	private ArrayList<AdjuntosXMLBean> listaAdjuntos;
	private ArrayList<ImagenXMLBean> listaImagenes;
	//errores para la respuesta
	
	private ArrayList<String> listadoErroresMensajes;
	
	public MensajesXMLBean() {
		listaDestinatarios = new ArrayList<DestinatarioXMLBean>();
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
	public ArrayList<DestinatarioXMLBean> getListaDestinatarios() {
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

	
	/**
	 * Devuelve todos los emails de forma individual
	 * Si un objeto destinatario contiene algo como: email1@em.com;email2@em.com se devuelve un array list con los emails separados 
	 * @return
	 */
	public ArrayList<String> getListaDestinatariosCompleta(){ 
		ArrayList<String> listaDestinatariosCompleta = new ArrayList<String>();
		for(DestinatarioXMLBean destinatario : listaDestinatarios){
			String emails = destinatario.getEmailDestinatario();
			String[] arrayEmails = emails.split(";");
			for(int i = 0; i<arrayEmails.length;i++){
				String email = arrayEmails[i];
				if(!listaDestinatariosCompleta.contains(email)){
					listaDestinatariosCompleta.add(email);
				}
			}
		}
		
		return listaDestinatariosCompleta;
	}

	public void setListaDestinatarios(
			ArrayList<DestinatarioXMLBean> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
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
	public void addDestinatario(DestinatarioXMLBean destinatario){
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
	public String getDestinatarios(int tipo){
		StringBuffer sbf = new StringBuffer();
		for(int i=0;this.listaDestinatarios!=null&&i<this.listaDestinatarios.size();i++){
			DestinatarioXMLBean dest = listaDestinatarios.get(i);
			if(dest.getTipoDestinatario()==DestinatarioXMLBean.TIPO_TO&&tipo==DestinatarioXMLBean.TIPO_TO){
				if(sbf.length()>0){
					sbf.append(";");
				}
				sbf.append(dest.getEmailDestinatario());
			}
			if(dest.getTipoDestinatario()==DestinatarioXMLBean.TIPO_CC&&tipo==DestinatarioXMLBean.TIPO_CC){
				if(sbf.length()>0){
					sbf.append(";");
				}
				sbf.append(dest.getEmailDestinatario());
			}			
			if(dest.getTipoDestinatario()==DestinatarioXMLBean.TIPO_BCC&&tipo==DestinatarioXMLBean.TIPO_BCC){
				if(sbf.length()>0){
					sbf.append(";");
				}
				sbf.append(dest.getEmailDestinatario());
			}
		}
		return sbf.toString();
	}
}
