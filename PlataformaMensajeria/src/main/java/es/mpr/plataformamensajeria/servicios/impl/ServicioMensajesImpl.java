package es.mpr.plataformamensajeria.servicios.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes;

/**
 * <p>Maneja la persistencia de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioMensajesImpl")
public class ServicioMensajesImpl implements ServicioMensajes{
	
	/**  tbl mensajes manager. */
@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	/**  query mensajes. */
	@Resource(name = "QueryExecutorMensajesImpl")
	private QueryExecutorMensajes queryMensajes;
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes#getTodosMensajesLoteHistorificar(java.lang.Long, java.util.Date)
	 */
	@Override
	public List<Long> getTodosMensajesLoteHistorificar(Long loteEnvioID) throws BusinessException {

		return queryMensajes.getIdMensajesByLote(loteEnvioID);
		/* Antes hacia una comparacion para ver si los mensajes y los estados de la tabla mensajes correspondia con la misma cantidad
		  en la tabla gestion de envios con un estado final*/
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes#testLoteSinMensajes(java.lang.Long)
	 */
	@Override
	public Boolean testLoteSinMensajes(Long loteEnvioId) throws BusinessException {
		List<Long> res = queryMensajes.getIdMensajesByLote(loteEnvioId);
		return null == res ||  res.isEmpty();
	}

	
	
	
}
