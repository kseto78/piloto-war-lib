package es.mpr.plataformamensajeria.ws.seguimiento;

import javax.jws.WebService;

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
	//idservicio, idcanal, idaplicacion, idLote, idmensaje, idexterno, idestado, FechaDesde, FechaHasta, usuario, password
    String consultarEstado(Integer servicioId, Integer canalId,Integer aplicacionId, Integer loteId, Integer idMensaje, String idExterno, Integer estadoId, String fechaDesde, String FechaHasta,
    		String usuario, String password);

    /**
     * 
     * @param idMensaje
     * @param idExterno
     * @param usuario
     * @param password
     * @return 
     */
    String consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password);
}
