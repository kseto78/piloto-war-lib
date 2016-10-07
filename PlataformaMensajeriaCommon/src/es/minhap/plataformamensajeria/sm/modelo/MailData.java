package es.minhap.plataformamensajeria.sm.modelo;

import java.util.ArrayList;



public class MailData {
	public Recipients Recipients;
	public ArrayList<ParametrosServidor> Servers;
	public String Subject = "";
	public String Body = "";
	public String TipoCuerpo ="text/HTML";
	public String TipoCodificacion = "UTF-8";
	public ArrayList<Adjunto> Attachments;
	public ArrayList<Adjunto> Images;
	public DatosServicio ServiceData;
	public boolean esMultidestinatario = false;
	public Long destinatarioMensajeId;
	public String estado = "";
	public Integer modo = null;
}
