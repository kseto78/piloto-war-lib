package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.sim.dao.TblLogDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.query.TblLogQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblLogManagerImpl")
public class TblLogManagerImpl implements TblLogManager {

	protected static final String R_CONST_1 = "%";
	@Resource
	private TblLogDAO tblLogDAO;
	
	@Override
	public void insertLog(TblLog log) {
		tblLogDAO.insert(log);
		
	}

	@Override
	public List<TblLog> getAuditoriasPaginadas(int start, int size, String order, String column, String nombre,
			AuditoriaPlataformaBean criterio, boolean count, String recursosFiltro) {
		OrderType ord = null;
		// Orden ascendente o descendente
		if (order == null) {
			ord = OrderType.DESC;
		} else if ("1".equals(order)){
			ord = OrderType.ASC;
		}else{
			ord = OrderType.DESC;
		}
		
		TblLogQuery query = new TblLogQuery();
		
		if (null != criterio && null != criterio.getSourceName() && !criterio.getSourceName().isEmpty()){
			query.setSourcename(criterio.getSourceName());
			query.setSourcenameComparator(TextComparator.EQUALS);
		}else{
			for (String a : recursosFiltro.split(",")) {
				query.addSourcenameIn(a);
			}
		}
		if (null != criterio && null != criterio.getSourceDescription() && !criterio.getSourceDescription().isEmpty()){
			query.setSourcedescriptionComparator(TextComparator.ILIKE);
			query.setSourcedescription(R_CONST_1 + criterio.getSourceDescription() + R_CONST_1);
		}
		
		if (null != criterio && null != criterio.getAdtUsuario() && !criterio.getAdtUsuario().isEmpty()){
			query.setAdtusuario(criterio.getAdtUsuario());
		}
		
		if(null != criterio && null != criterio.getFechaDesde()){
			query.setAdtfechaMin(criterio.getFechaDesde());
		}
		if(null != criterio && null != criterio.getFechaHasta()){
			query.setAdtfechaMax(criterio.getFechaHasta());
		}
		if(null != criterio && null != criterio.getLogAccion() && criterio.getLogAccion()>0){
			query.setLogaccion(criterio.getLogAccion().longValue());
		}
		
		query.addOrder(column, ord);
		
		if (!count){
			query.setPrimerResultado(start);
			query.setMaxResultados(size);
		}
		
		return tblLogDAO.search(query).getResults();
	}


}
