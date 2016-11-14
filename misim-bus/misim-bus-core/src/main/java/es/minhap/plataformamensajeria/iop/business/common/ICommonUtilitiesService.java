package es.minhap.plataformamensajeria.iop.business.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.mule.api.MuleException;
import org.xml.sax.SAXException;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;
import es.minhap.sim.model.TblMensajes;



/**
 * Clase interfaz de metodos comunes que utilizan los Dispatcher de JMS
 * 
 * @author everis
 *
 */
public interface ICommonUtilitiesService {

	
	/**
	 * Se recupera un listado de servidores de SMS a partir de un mensajeId
	 * 
	 * @param mensajeId is the mensaje id
	 * @param destinatarioMensajeId is the destinatary message id
	 * @return
	 */
	public SMSData getSMSData(Long mensajeId, Long destinatarioMensajeId); 
	
	/**
	 * Se recupera un mapa de todos los servicios a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public Map<Integer, Servicio> getMapServicios(Long idMensaje);
	
	/**
	 * 
	 * @param data
	 * @param soapAction
	 * @param receptQueue TODO
	 * @return
	 * @throws PlataformaBusinessException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MuleException
	 * @throws Exception
	 */
	public String sendMessage(Object data, String soapAction, String receptQueue) throws PlataformaBusinessException,
	ParserConfigurationException, SAXException, IOException, UnsupportedEncodingException, MuleException, Exception;
	
	/**
	 * 
	 * @param idMensaje
	 * @return
	 */
	public TblMensajes getMensaje(Long idMensaje);

}
