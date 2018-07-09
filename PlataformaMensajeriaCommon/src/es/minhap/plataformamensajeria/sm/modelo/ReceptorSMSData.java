package es.minhap.plataformamensajeria.sm.modelo;


import java.util.ArrayList;

public class ReceptorSMSData {
	public ArrayList<ParametrosReceptor> Servers;
	public String User = "";
	public String Password = "";
	public String Telefono="";
	public String HeaderSMS = "";
	public String Body = "";
	public String LoteEnvioId = "";
	public boolean esMultidestinatario = false;
	public Long destinatarioMensajeId;
	public String estado = "";
	public DatosServicio ServiceData;
}
