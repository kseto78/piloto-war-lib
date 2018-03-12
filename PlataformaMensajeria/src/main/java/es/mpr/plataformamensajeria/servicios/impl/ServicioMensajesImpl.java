package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
private static Logger logger = Logger.getLogger(ServicioLotesEnviosImpl.class);
	
	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name = "QueryExecutorMensajesImpl")
	private QueryExecutorMensajes queryMensajes;
	
	
	@Override
	public List<Long> getTodosMensajesLoteHistorificar(Long loteEnvioID, Date fecha) throws BusinessException {

		List<Long> res = queryMensajes.getIdMensajesByLote(loteEnvioID);
		
		if (res.size() == queryMensajes.countMensajesHistorificacion(loteEnvioID, fecha)){
			return res;
		}else{
			return new ArrayList<>();
		}
	}
	
	@Override
	public Boolean testLoteSinMensajes(Long loteEnvioId) throws BusinessException {
		List<Long> res = queryMensajes.getIdMensajesByLote(loteEnvioId);
		return (null == res ||  res.isEmpty())? true : false;
	}

	
	
	
}
