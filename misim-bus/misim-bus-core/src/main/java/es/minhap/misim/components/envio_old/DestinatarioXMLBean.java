package es.minhap.misim.components.envio_old;


public class DestinatarioXMLBean {
	
	public static int TIPO_TO = 1;
	public static int TIPO_CC = 2;
	public static int TIPO_BCC = 3;
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
