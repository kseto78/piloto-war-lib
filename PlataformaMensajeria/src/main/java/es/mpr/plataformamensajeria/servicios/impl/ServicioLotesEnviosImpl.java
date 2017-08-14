package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.sim.query.TblLotesEnviosQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnvios;

/**
 * <p>Maneja la persistencia de lotes de envios a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioLotesEnviosImpl")
public class ServicioLotesEnviosImpl implements ServicioLotesEnvios{
	private static Logger logger = Logger.getLogger(ServicioLotesEnviosImpl.class);
	
	@Resource(name = "TblLotesEnviosManagerImpl")
	private TblLotesEnviosManager tblLotesEnviosManager;
	
	////MIGRADO
	@Override
	@Transactional
	public List<Long> getLotesEnviosTOHist(Integer servicioId, Date fechaHistorico)
			throws BusinessException {
		try {
			TblLotesEnviosQuery query = new TblLotesEnviosQuery();
			TblServiciosQuery queryServicios = new TblServiciosQuery();
			queryServicios.setServicioid(servicioId.longValue());
			query.setTblServicios(queryServicios);
			query.setFechamodificacionMax(fechaHistorico);

			return (null != tblLotesEnviosManager.getIdLotesEnviosByQuery(query))? tblLotesEnviosManager.getIdLotesEnviosByQuery(query) : new ArrayList<Long>();

		} catch (Exception e) {
			logger.error("ServicioLotesEnviosImpl - getLotesEnviosTOHist:" + e);
			throw new BusinessException(e);
		}
	}
	
}
