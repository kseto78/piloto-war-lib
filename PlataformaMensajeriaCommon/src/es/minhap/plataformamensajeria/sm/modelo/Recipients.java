package es.minhap.plataformamensajeria.sm.modelo;

import java.util.ArrayList;

public class Recipients {
	public ArrayList<DestinatarioDMensaje> To;
	public ArrayList<DestinatarioDMensaje> Cc;
	public ArrayList<DestinatarioDMensaje> Bcc;
	
	public Recipients()
	{
		this.To = new ArrayList<DestinatarioDMensaje>();
		this.Cc = new ArrayList<DestinatarioDMensaje>();
		this.Bcc = new ArrayList<DestinatarioDMensaje>();
	}
}
