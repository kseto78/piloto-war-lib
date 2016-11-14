package es.minhap.plataformamensajeria.iop.managerimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.sim.dao.TblServiciosMovilesDAO;
import es.minhap.sim.model.TblServiciosMoviles;
import es.minhap.sim.query.TblServiciosMovilesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblServiciosMovilesManagerImpl")
public class TblServiciosMovilesManagerImpl implements TblServiciosMovilesManager {

	@Resource
	private TblServiciosMovilesDAO serviciosMovilesDAO;
	
	@Resource
	private QueryExecutorUsuariosPush queryExecutorUsuariosPush;

	/**
	 * @see es.minhap.TblServiciosMovilesManagerImpl.checkMobileServie
	 */
	@Override
	public Boolean checkMobileServie(String idServicioMovil) {
		return (null != getServiciosMovilesDAO().get(Long.parseLong(idServicioMovil))) ? true : false;
	}

	
	@Override
	public List<ServicioMovil> consultarServiciosDisponibles(String idDispositivo) {
		List<ServicioMovil> serviciosMoviles = new ArrayList<>();
		Map<String, ServicioMovil> serviciosMovilesUsuario = new HashMap<>();
		if (null != idDispositivo){
			TblServiciosMovilesQuery query = new TblServiciosMovilesQuery();
			query.setEstado(1);
			for (TblServiciosMoviles sm : getServiciosMovilesDAO().search(query).getResults()) {
				ServicioMovil servicioMovil = new ServicioMovil();
				servicioMovil.setMobileService(sm.getNombre());
				servicioMovil.setDescMobileService(sm.getDescripcion());
				servicioMovil.setUrlService(sm.getUrlServicio());
				servicioMovil.setIdService(sm.getServiciosmovilesid().toString());
				servicioMovil.setIcon(sm.getIcono());
				servicioMovil.setTipo(sm.getTipo().toString());
				servicioMovil.setImagen(sm.getImagen());
				servicioMovil.setEstado(sm.getEstado().toString());
				serviciosMovilesUsuario.put(sm.getServiciosmovilesid().toString(), servicioMovil);
			}
			if (!serviciosMovilesUsuario.isEmpty()) {

				Set<String> keys = serviciosMovilesUsuario.keySet();
				for (String key : keys) {
					ServicioMovil servMovil = serviciosMovilesUsuario.get(key);
					List<BigDecimal> lista = getQueryExecutorUsuariosPush().getUsuarioConsultaServiciosDisponibles(idDispositivo, servMovil.getIdService());
					servMovil.setEstado((null != lista && !lista.isEmpty()) ? "1" : "0");
					serviciosMovilesUsuario.put(key, servMovil);
				}
				Collection<ServicioMovil> servMovilCollection = serviciosMovilesUsuario.values();
				serviciosMoviles = new ArrayList<>(servMovilCollection);
			}
		}
		
		return serviciosMoviles;
	}


	@Override
	public Long getServicioMovil(long idServicioMovil) {
		return getServiciosMovilesDAO().get(idServicioMovil).getServiciosmovilesid();
	}

	@Override
	public boolean checkMobileActiveService(String idServicioMovil){
		TblServiciosMovilesQuery query = new TblServiciosMovilesQuery();
		query.setEstado(1);
		query.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		return (null != serviciosMovilesDAO.search(query) && !serviciosMovilesDAO.search(query).getResults().isEmpty())? true : false;
	}
	

	/**
	 * @return the serviciosMovilesDAO
	 */
	public TblServiciosMovilesDAO getServiciosMovilesDAO() {
		return serviciosMovilesDAO;
	}


	/**
	 * @param serviciosMovilesDAO the serviciosMovilesDAO to set
	 */
	public void setServiciosMovilesDAO(TblServiciosMovilesDAO serviciosMovilesDAO) {
		this.serviciosMovilesDAO = serviciosMovilesDAO;
	}


	/**
	 * @return the queryExecutorUsuariosPush
	 */
	public QueryExecutorUsuariosPush getQueryExecutorUsuariosPush() {
		return queryExecutorUsuariosPush;
	}


	/**
	 * @param queryExecutorUsuariosPush the queryExecutorUsuariosPush to set
	 */
	public void setQueryExecutorUsuariosPush(QueryExecutorUsuariosPush queryExecutorUsuariosPush) {
		this.queryExecutorUsuariosPush = queryExecutorUsuariosPush;
	}
}
