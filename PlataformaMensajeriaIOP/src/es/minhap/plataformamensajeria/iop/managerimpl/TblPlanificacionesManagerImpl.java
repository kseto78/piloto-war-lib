package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPlanificaciones;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;

/**
 * 
 * @author everis
 *
 */
@Service("TblPlanificacionesManagerImpl")
public class TblPlanificacionesManagerImpl implements TblPlanificacionesManager {

	@Autowired
	private QueryExecutorPlanificaciones queryExecutoPlanificaciones;
	 
	/**
	 * @see es.minhap.TblServidoresManager.getServidor
	 */
	@Override
	public List<Long> getServiciosPlanificacion() {

		List<Long> listaServiciosConPlanificacion = getQueryExecutoPlanificaciones().obtenerServiciosPlanificacion();
		List<Long> listaServiciosSinPlanificacion = getQueryExecutoPlanificaciones().obtenerServiciosSinPlanificacion();
		
		Set<Long> set = new LinkedHashSet<>(listaServiciosConPlanificacion);
		set.addAll(listaServiciosSinPlanificacion);
		return new ArrayList<>(set);
		
	}

	/**
	 * @return the queryExecutoPlanificaciones
	 */
	public QueryExecutorPlanificaciones getQueryExecutoPlanificaciones() {
		return queryExecutoPlanificaciones;
	}

	/**
	 * @param queryExecutoPlanificaciones the queryExecutoPlanificaciones to set
	 */
	public void setQueryExecutoPlanificaciones(QueryExecutorPlanificaciones queryExecutoPlanificaciones) {
		this.queryExecutoPlanificaciones = queryExecutoPlanificaciones;
	}
	

}
