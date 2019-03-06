package es.minhap.plataformamensajeria.iop.beans;

public class DestinatarioXMLBean {
	
	public static final int TIPO_TO = 1;
	public static final int TIPO_CC = 2;
	public static final int TIPO_BCC = 3;
/**
 * 
 * 
 */
    /**
	 * Tipo = 1 > TO
	 * Tipo = 2 > CC
	 * Tipo = 3 > BCC
     **/
	private Integer tipoDestinatario;
	private String emailDestinatario;
	
	public Integer getTipoDestinatario() {
		return tipoDestinatario;
	}

	public void setTipoDestinatario(Integer tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String toXML(){
		//Generar el XML a partir de los otros objetos.
		return "";
	}
}
