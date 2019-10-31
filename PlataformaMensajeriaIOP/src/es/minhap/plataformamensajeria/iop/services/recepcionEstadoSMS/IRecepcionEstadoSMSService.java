package es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS;

import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;

public interface IRecepcionEstadoSMSService {
	RespuestaEstadoSMSXMLBean recibirEstadoSMS (RecepcionEstadoSMSXMLBean recepcionEstadoSMS);
	
	String recibirEstadoSMSXML (RecepcionEstadoSMSXMLBean recepcionEstadoSMS);
}
