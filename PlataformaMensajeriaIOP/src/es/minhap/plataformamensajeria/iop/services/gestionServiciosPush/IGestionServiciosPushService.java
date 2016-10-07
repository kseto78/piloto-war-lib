package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ServiciosDisponiblesXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IGestionServiciosPushService {

	String consultaServiciosDisponibles(Integer servicioId, Integer canalId,
			Integer aplicacionId, Integer loteId, Integer idMensaje,
			String idExterno, Integer estadoId, String fechaDesde,
			String fechaHasta, String usuario, String password);

	String registroUsuarioEnServicio(Integer servicioId, Integer canalId,
			Integer aplicacionId, Integer loteId, Integer idMensaje,
			String idExterno, Integer estadoId, String fechaDesde,
			String fechaHasta, String usuario, String password);

	String consultaServiciosDisponibles(ServiciosDisponiblesXMLBean servDispXMLBean);

	String consultaServiciosDisponibles(ConsultaEstadoBean consultaEstado);

	String registroUsuarioEnServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean);
}
