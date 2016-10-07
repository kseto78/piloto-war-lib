package es.minhap.plataformamensajeria.iop.services.seguimiento;

import javax.jws.WebService;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface ISeguimientoMensajesService {

	/**
	 * 
	 * @param servicioId
	 * @param canalId
	 * @param loteId
	 * @param idMensaje
	 * @param idExterno
	 * @param estadoId
	 * @param fechaDesde
	 * @param FechaHasta
	 * @param usuario
	 * @param password
	 * @return Devuelve un xml con los resultados
	 */
	String consultarEstado(Integer servicioId, Integer canalId,Integer aplicacionId, Integer loteId, Integer idMensaje, String idExterno, Integer estadoId, String fechaDesde, String FechaHasta, String usuario, String password);
    
    /**
	 * 
	 * @param servicioId
	 * @param canalId
	 * @param loteId
	 * @param idMensaje
	 * @param idExterno
	 * @param estadoId
	 * @param fechaDesde
	 * @param FechaHasta
	 * @param usuario
	 * @param password
	 * @param ps
	 * @return Devuelve un xml con los resultados
	 */
    String consultarEstado(Integer servicioId, Integer canalId,Integer aplicacionId, Integer loteId, Integer idMensaje, String idExterno, Integer estadoId, String fechaDesde, String FechaHasta, String usuario, String password, PropertiesServices ps);
	
	String consultarEstado(ConsultaEstadoBean consultaEstado);
	
	String consultarEstado(ConsultaEstadoBean consultaEstado, PropertiesServices ps);
	

    /**
     * 
     * @param idMensaje
     * @param idExterno
     * @param usuario
     * @param password
     * @return 
     */
    String consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password);
    
    String consultarEstado(ConsultaEstadoXMLBean consultaEstado, PropertiesServices ps);
    
    String consultarEstado(ConsultaEstadoXMLBean consultaEstado);
	
	String consultarHistorial(ConsultaHistoricoXMLBean consultaHistorico);

    String consultarEstadoAEAT(Integer servicioId, Integer idMensaje, String idExterno, String usuario, String password, String sender, String recipient, String statusText);

}
