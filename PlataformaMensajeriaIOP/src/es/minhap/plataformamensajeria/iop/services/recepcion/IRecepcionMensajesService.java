package es.minhap.plataformamensajeria.iop.services.recepcion;

import es.minhap.plataformamensajeria.iop.beans.RecibirSMSRequest;

/**
 * This class was generated by Apache CXF 2.6.3 Generated source version: 2.6.3
 * 
 */

public interface IRecepcionMensajesService {

	
	public RecibirSMSResponse recibirSMS(RecibirSMSRequest parameters);
	
	public String recibirSMSXML(RecibirSMSRequest recepcionMensajes);
}
