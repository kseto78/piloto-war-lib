package es.minhap.plataformamensajeria.sm.modelo;


import java.util.ArrayList;

public class SMSData {
	public ArrayList<ParametrosProveedor> Servers;
	public String Body = "";
	public String Telefono="";
	public DatosServicio ServiceData;
	public boolean esMultidestinatario = false;
	public Long destinatarioMensajeId;
	public String estado = "";
}
