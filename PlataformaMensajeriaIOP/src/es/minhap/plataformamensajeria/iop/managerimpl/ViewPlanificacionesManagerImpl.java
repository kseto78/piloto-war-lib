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
		if (order == null || order.equals("1")) {
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
		if(criterio!=null&&criterio.getLunes()!=null&&criterio.getLunes().equals(VERDADERO)){
			query.setLunes("S");
		}
		if(criterio!=null&&criterio.getMartes()!=null&&criterio.getMartes().equals(VERDADERO)){
			query.setMartes("S");
		}
		if(criterio!=null&&criterio.getMiercoles()!=null&&criterio.getMiercoles().equals(VERDADERO)){
			query.setMiercoles("S");
		}
		if(criterio!=null&&criterio.getJueves()!=null&&criterio.getJueves().equals(VERDADERO)){
			query.setJueves("S");
		}
		if(criterio!=null&&criterio.getViernes()!=null&&criterio.getViernes().equals(VERDADERO)){
			query.setViernes("S");
		}
		if(criterio!=null&&criterio.getSabado()!=null&&criterio.getSabado().equals(VERDADERO)){
			query.setSabado("S");
		}
		if(criterio!=null&&criterio.getDomingo()!=null&&criterio.getDomingo().equals(VERDADERO)){
			query.setDomingo("S");
		}
		if(criterio!=null&&(!isEmpty(criterio.getHoraDesde()) || (!isEmpty(criterio.getHoraHasta())))){
			if(criterio!=null&&!isEmpty(criterio.getHoraDesde())){
				query.setHoraDesdeIni(criterio.getHoraDesde());
				if (!isEmpty(criterio.getHoraHasta())){
					query.setHoraDesdeFin(criterio.getHoraHasta());
				}else{
					query.setHoraDesdeFin("23:59");
				}
			}else{
				query.setHoraDesdeIni("00:00");
				query.setHoraDesdeFin(criterio.getHoraHasta());
			}
		}
		
		if(criterio!=null&&(!isEmpty(criterio.getHoraDesdeFin()) || (!isEmpty(criterio.getHoraHastaFin())))){
			if(criterio!=null&&!isEmpty(criterio.getHoraDesdeFin())){
				query.setHoraHastaIni(criterio.getHoraDesdeFin());
				if (!isEmpty(criterio.getHoraHastaFin())){
					query.setHoraHastaFin(criterio.getHoraHastaFin());
				}else{
					query.setHoraHastaFin("23:59");
				}
			}else{
				query.setHoraHastaIni("00:00");
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
		if(value==null||(value!=null&&value.equals(""))){
			return true;
		}else{
			return false;
		}
	}


}