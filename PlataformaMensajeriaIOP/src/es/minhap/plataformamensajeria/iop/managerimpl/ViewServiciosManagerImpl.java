package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.ViewServiciosManager;
import es.minhap.sim.dao.ViewServiciosDAO;
import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.ViewServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewServiciosManagerImpl")
public class ViewServiciosManagerImpl implements ViewServiciosManager {

	protected static final String R_CONST_1 = "%";
	protected static final String R_CONST_2 = "unchecked";
	protected static final String R_CONST_3 = "nombre";
	@Resource
	private ViewServiciosDAO viewServiciosDAO;

	/**
	 * @see es.minhap.ViewMensajesDetalladaManagerImpl.countViewMensajeAdjuntosDetallada
	 */
	
	///MIGRADO
	@Override
	@Transactional
	public ViewServicios getAplicacionId(ViewServiciosQuery query) {
		return getViewServiciosDAO().searchUnique(query);
	}

	///MIGRADO
	@Override
	@Transactional
	public List<ViewServicios> getServiciosByAplicacion(Long aplicacionId) {
		ViewServiciosQuery query = new ViewServiciosQuery();
		query.setAplicacionid(aplicacionId);
		query.addOrder(R_CONST_3, OrderType.ASC);
		return viewServiciosDAO.search(query).getResults();
	}

	
	///MIGRADO
	@Override
	@Transactional
	public List<ViewServicios> getServiciosPaginado(int start, int size, String order, String columnSort, String nombreServicio, Long aplicacionId, int servicioId, boolean count) {
		OrderType ord = null;
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)) {
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		ViewServiciosQuery query = new ViewServiciosQuery();

		if (null != nombreServicio) {
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre(R_CONST_1+nombreServicio+R_CONST_1);
		}
		if (null != aplicacionId) {
			query.setAplicacionid(aplicacionId);
		}
		if (servicioId != 0) {
			query.setServicioid((long) servicioId);
		}
		
		query.addOrder(columnSort, ord);
		if("aplicacionnombre".equals(columnSort)){
			query.addOrder("servicioid",ord);
		}
		
		query.setEliminadoIsNull(true);
		if (!count){
			query.setPrimerResultado(start);
			query.setMaxResultados(size);
		}

		return viewServiciosDAO.search(query).getResults();
	}
	
	@SuppressWarnings(R_CONST_2)
	@Override
	public List<ViewServicios> getCanalesByServicioId(String idServicio) {
		ViewServiciosQuery query = new ViewServiciosQuery();
		query.setServicioid(Long.parseLong(idServicio));
		
		return viewServiciosDAO.search(query).getResults();

	}

	@SuppressWarnings(R_CONST_2)
	@Override
	public List<ViewServicios> getServiciosPorCanal(String idAplicacion, String idCanal) {
		ViewServiciosQuery query = new ViewServiciosQuery();
		query.setAplicacionid(Long.parseLong(idAplicacion));
		query.setCanalid(Long.valueOf(idCanal));
		
		return viewServiciosDAO.search(query).getResults();

	}
	
	@SuppressWarnings(R_CONST_2)
	@Override
	public List<ViewServicios> getServicios() {
		ViewServiciosQuery query = new ViewServiciosQuery();
		query.addOrder(R_CONST_3, OrderType.ASC);
		
		return viewServiciosDAO.search(query).getResults();

	}

	/**
	 * @return the viewServiciosDAO
	 */
	public ViewServiciosDAO getViewServiciosDAO() {
		return viewServiciosDAO;
	}

	/**
	 * @param viewServiciosDAO the viewServiciosDAO to set
	 */
	public void setViewServiciosDAO(ViewServiciosDAO viewServiciosDAO) {
		this.viewServiciosDAO = viewServiciosDAO;
	}

	

}
