package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.MigracionFilesystemManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.sim.query.TblMensajesHistQuery;
import es.minhap.sim.query.TblMensajesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("MigracionFilesystemManagerImpl")
public class MigracionFilesystemManagerImpl implements MigracionFilesystemManager {
	
	@Resource(name="TblMensajesManagerImpl")
	private TblMensajesManager mensajesManager;
	
	@Resource(name="TblMensajesHistManagerImpl")
	private TblMensajesHistManager mensajesHistManager;
	
	@Resource (name = "TblLotesEnviosHistManagerImpl")
	private TblLotesEnviosHistManager tblLotesEnviosHist;
	


	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	public List<Object> obtenerMensajesMigracion(Long mensajeId, Date fechaInicio, Date fechaFin, Boolean historico, Integer max, Integer indice) {
		List<Object> res;
		
		if (historico){
			res =  new ArrayList<Object>(mensajesHistManager.getMensajesByQuery(createQueryMensajeHistorico(mensajeId, fechaInicio, fechaFin, max, indice)));
		}else{
			res = new ArrayList<Object>(mensajesManager.getMensajesByQuery(createQueryMensaje(mensajeId, fechaInicio, fechaFin, max, indice)));
			
		}
		
		

		return res;
	}
	
	@Override
	public Integer countMensajesMigracion(Long idMensaje, Date fechaInicio, Date fechaFin, Boolean historicos) {
		if (historicos){
			return mensajesHistManager.countMensajesHistoricosByQuery(createQueryMensajeHistorico(idMensaje, fechaInicio, fechaFin, null, null));
		}
		else{
			return mensajesManager.countMensajesByQuery(createQueryMensaje(idMensaje, fechaInicio, fechaFin, null, null));
		}
	}


	private TblMensajesQuery createQueryMensaje(Long mensajeId, Date fechaInicio, Date fechaFin, Integer max, Integer contador) {
		TblMensajesQuery query = new TblMensajesQuery();
		
		if (null != mensajeId)
			query.setMensajeid(mensajeId);
		if (null != fechaInicio){
			query.setFechacreacionMin(fechaInicio);
			query.setFechacreacionMax((null != fechaFin)? fechaFin : new Date()) ;
		}
		if (null != max)
			query.setMaxResults(max);
		if (contador != null)
			query.setFirstResult(contador*max);
		
		query.setCuerpofileIsNull(true);
		query.setCuerpoIsNull(true);
		
		return query;
	}


	private TblMensajesHistQuery createQueryMensajeHistorico(Long mensajeId, Date fechaInicio, Date fechaFin, Integer max, Integer contador) {
		TblMensajesHistQuery query = new TblMensajesHistQuery();
		
		if (null != mensajeId)
			query.setMensajeid(mensajeId);
		if (null != fechaInicio){
			query.setFechacreacionMin(fechaInicio);
			query.setFechacreacionMax((null != fechaFin)? fechaFin : new Date()) ;
		}
		if (null != max){
			query.setMaxResults(max);
		}
		if(contador != null){
			query.setFirstResult(contador*max);
		}
		
		return query;
	}


	

}
