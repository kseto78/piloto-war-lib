package es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS;

import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;

public interface IRecepcionEstadoSMSService {
	public  RespuestaEstadoSMSXMLBean recibirEstadoSMS (RecepcionEstadoSMSXMLBean recepcionEstadoSMS);
	
	public String recibirEstadoSMSXML (RecepcionEstadoSMSXMLBean recepcionEstadoSMS);
}
