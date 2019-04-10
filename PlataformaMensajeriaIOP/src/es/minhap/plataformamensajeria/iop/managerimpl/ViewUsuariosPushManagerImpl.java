package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosPushManager;
import es.minhap.sim.dao.ViewUsuariosPushDAO;
import es.minhap.sim.model.ViewUsuariosPush;
import es.minhap.sim.query.ViewUsuariosPushQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewUsuariosPushManager")
public class ViewUsuariosPushManagerImpl implements ViewUsuariosPushManager {

	@Resource
	private ViewUsuariosPushDAO viewUsuariosDAO;

	/**
	 * @see es.minhap.ViewUsuariosPushManagerImpl.countViewUsuariosPush
	 */
	@Override
	public Integer countViewUsuariosPush(ViewUsuariosPushQuery query) {

		return getViewUsuariosDAO().count(query);
	}


	@Override
	public List<ViewUsuariosPush> getUsuariosPushPaginado(int start, int size, String order, String columnSort,
			UsuariosPushBean criterio, boolean count) {
		OrderType ord = null;
		
		// Orden ascendente o descendente
		if (order == null || order.equals("1")){
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		
		ViewUsuariosPushQuery query = new ViewUsuariosPushQuery();
		
		if(criterio != null){
			if(criterio.getUsuarioId() != null && criterio.getUsuarioId() != 0){
				query.setUsuarioid(Long.parseLong(criterio.getUsuarioId().toString()));
			}
			if(criterio.getAplicacionId() != null && criterio.getAplicacionId() != 0){
				query.setAplicacionid(Long.parseLong(criterio.getAplicacionId().toString()));
			}else{
				if(criterio.getListaIdAplicaciones() != null){
					String[]  listaId = criterio.getListaIdAplicaciones().split(",");
					for(String ids : listaId){
						query.addAplicacionidIn(Long.valueOf(ids));
					}					
				}
			}
			if(criterio.getServicioId() != null  && criterio.getServicioId() != 0){
				query.setServicioid(Long.parseLong(criterio.getServicioId().toString()));
			}
			if(criterio.getPlataformaId() != null  && criterio.getPlataformaId() != 0){
				query.setPlataforma(criterio.getPlataformaId().toString());
			}
			if(criterio.getFechaDesde() != null){
				query.setFechaMin(criterio.getFechaDesde());
			}
			if(criterio.getFechaHasta() != null){
				query.setFechaMax(criterio.getFechaHasta());
			}
			if(null != criterio.getPlataforma() && criterio.getPlataforma().length() > 0){
				query.setPlataforma(criterio.getPlataforma());
				
			}
		}
		
		query.addOrder(columnSort, ord);
		
		if (!count){
			query.setPrimerResultado(start);
			query.setMaxResultados(size);
		}
		
		
		return viewUsuariosDAO.search(query).getResults();
	}

	/**
	 * @return the viewUsuariosDAO
	 */
	public ViewUsuariosPushDAO getViewUsuariosDAO() {
		return viewUsuariosDAO;
	}

	/**
	 * @param viewUsuariosDAO the viewUsuariosDAO to set
	 */
	public void setViewUsuariosDAO(ViewUsuariosPushDAO viewUsuariosDAO) {
		this.viewUsuariosDAO = viewUsuariosDAO;
	}

}
