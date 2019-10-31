package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosManager;
import es.minhap.sim.dao.TblUsuariosDAO;
import es.minhap.sim.dao.ViewUsuariosDAO;
import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.model.ViewUsuarios;
import es.minhap.sim.query.TblUsuariosQuery;
import es.minhap.sim.query.ViewUsuariosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewUsuariosManagerImpl")
public class ViewUsuariosManagerImpl implements ViewUsuariosManager {

	protected static final String R_CONST_1 = "%";

	@Resource
	private ViewUsuariosDAO viewUsuariosDAO;
	
	@Resource
	private TblUsuariosDAO tblUsuariosDAO;

	
	///MIGRADO
	@Override
	@Transactional
	public List<TblUsuarios> getViewUsuariosPaginado(int start, int size, String order, String column, String nombre,
			Long aplicacionId, Integer rolId, boolean count) {
		OrderType ord = null;
		List<TblUsuarios> res = new ArrayList<>();
		
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)){
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		ViewUsuariosQuery query = new ViewUsuariosQuery();
	
		if (null != nombre){
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre(R_CONST_1 +nombre+ R_CONST_1);
		}
		
		if (null != aplicacionId && aplicacionId > 0){
			query.setAplicacionid(aplicacionId);
		}
		if (null != rolId && rolId >= 0){
			query.setRolid(rolId.longValue());
		}
				
		List<ViewUsuarios> list = viewUsuariosDAO.search(query).getResults();
		
		TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
		if (null != list && !list.isEmpty()){
			for (ViewUsuarios vu : list) {
				queryUsuarios.addUsuarioidIn(vu.getUsuarioid());
			}
			if (!count){
				query.setPrimerResultado(start);
				query.setMaxResultados(size);
			}
			queryUsuarios.addOrder(column, ord);
			
			return tblUsuariosDAO.search(queryUsuarios).getResults();
		}
		
		return res;
		
	}
	
}
