package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.beans.PlanificacionBean;
import es.minhap.plataformamensajeria.iop.manager.ViewPlanificacionesManager;
import es.minhap.sim.dao.ViewPlanificacionesDAO;
import es.minhap.sim.model.ViewPlanificaciones;
import es.minhap.sim.query.ViewPlanificacionesQuery;

/**
 * 
 * @author ereris
 *
 */
@Service("viewPlanificacionesManagerImpl")
public class ViewPlanificacionesManagerImpl implements ViewPlanificacionesManager {

	protected static final String R_CONST_1 = "00:00";

	protected static final String R_CONST_2 = "S";

	protected static final String R_CONST_3 = "23:59";

	@Resource
	private ViewPlanificacionesDAO viewPlanificacionesDAO;
	
	private static final String VERDADERO = "true";
	
	@Override
	public List<ViewPlanificaciones> getPlanificacionesServidor(Long servidorId) {
		ViewPlanificacionesQuery query = new ViewPlanificacionesQuery();
		
		query.setServidorId(servidorId);
		query.setServicioIdIsNull(true);
		query.setEliminadoIsNull(true);
		
		return viewPlanificacionesDAO.search(query).getResults();
	}

	@Override
	public List<ViewPlanificaciones> getPlanificacionesPaginadas(int start, int size, String order, String column,
			String nombre, PlanificacionBean criterio, boolean count) {
		OrderType ord = null;
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)) {
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}

		ViewPlanificacionesQuery query = new ViewPlanificacionesQuery();
		if(criterio!=null&&criterio.getAplicacionId()!=null&&criterio.getAplicacionId().intValue()!=0){
			query.setAplicacionId(criterio.getAplicacionId().longValue());
		}
		if(criterio!=null&&criterio.getServicioId()!=null&&criterio.getServicioId().intValue()!=0){
			query.setServicioId(criterio.getServicioId().longValue());
		}
		if(criterio!=null&&criterio.getLunes()!=null&&VERDADERO.equals(criterio.getLunes())){
			query.setLunes(R_CONST_2);
		}
		if(criterio!=null&&criterio.getMartes()!=null&&VERDADERO.equals(criterio.getMartes())){
			query.setMartes(R_CONST_2);
		}
		if(criterio!=null&&criterio.getMiercoles()!=null&&VERDADERO.equals(criterio.getMiercoles())){
			query.setMiercoles(R_CONST_2);
		}
		if(criterio!=null&&criterio.getJueves()!=null&&VERDADERO.equals(criterio.getJueves())){
			query.setJueves(R_CONST_2);
		}
		if(criterio!=null&&criterio.getViernes()!=null&&VERDADERO.equals(criterio.getViernes())){
			query.setViernes(R_CONST_2);
		}
		if(criterio!=null&&criterio.getSabado()!=null&&VERDADERO.equals(criterio.getSabado())){
			query.setSabado(R_CONST_2);
		}
		if(criterio!=null&&criterio.getDomingo()!=null&&VERDADERO.equals(criterio.getDomingo())){
			query.setDomingo(R_CONST_2);
		}
		if(criterio!=null&&(!isEmpty(criterio.getHoraDesde()) || !isEmpty(criterio.getHoraHasta()))){
			if(criterio!=null&&!isEmpty(criterio.getHoraDesde())){
				query.setHoraDesdeIni(criterio.getHoraDesde());
				if (!isEmpty(criterio.getHoraHasta())){
					query.setHoraDesdeFin(criterio.getHoraHasta());
				}else{
					query.setHoraDesdeFin(R_CONST_3);
				}
			}else{
				query.setHoraDesdeIni(R_CONST_1);
				query.setHoraDesdeFin(criterio.getHoraHasta());
			}
		}
		
		if(criterio!=null&&(!isEmpty(criterio.getHoraDesdeFin()) || !isEmpty(criterio.getHoraHastaFin()))){
			if(criterio!=null&&!isEmpty(criterio.getHoraDesdeFin())){
				query.setHoraHastaIni(criterio.getHoraDesdeFin());
				if (!isEmpty(criterio.getHoraHastaFin())){
					query.setHoraHastaFin(criterio.getHoraHastaFin());
				}else{
					query.setHoraHastaFin(R_CONST_3);
				}
			}else{
				query.setHoraHastaIni(R_CONST_1);
				query.setHoraHastaFin(criterio.getHoraHastaFin());
			}
		}
		if(criterio!=null&&criterio.getCanalId()!=null&&criterio.getCanalId().intValue()!=0){
			query.setCanalId(criterio.getCanalId().longValue());
		}
		if(criterio!=null&&criterio.getServidorId()!=null&&criterio.getServidorId().intValue()!=0){
			query.setServidorId(criterio.getServidorId().longValue());
		}
		if(criterio != null && criterio.getPlanificacionId()!= null && criterio.getPlanificacionId().intValue() != 0){
			query.setPlanificacionId(criterio.getPlanificacionId().longValue());
		}
		
		//para que no aparezcan las planificaciones que no tienen un servicio asociado
		query.setServicioIdIsNotNull(true);
		query.setEliminadoIsNull(true);
		
		query.addOrder(column, ord);
		query.setEliminadoIsNull(true);
		if (!count){
			query.setPrimerResultado(start);
			if (size>0){
				query.setMaxResultados(size);
			}
		}
		return viewPlanificacionesDAO.search(query).getResults();
	}

	@Override
	public ViewPlanificaciones getPlanificacionById(long planificacionId) {
		return viewPlanificacionesDAO.get(planificacionId);
	}


	/**
	 * Comprueba si una cadena está vacía o es nula
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return value==null||"".equals(value);
	}


}