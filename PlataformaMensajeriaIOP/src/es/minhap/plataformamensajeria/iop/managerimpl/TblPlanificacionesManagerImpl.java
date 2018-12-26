package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPlanificaciones;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblTipoPlanificacionesManager;
import es.minhap.sim.dao.TblPlanificacionesDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblPlanificacionesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblPlanificacionesManagerImpl")
public class TblPlanificacionesManagerImpl implements TblPlanificacionesManager {

	
	@Resource
	private TblPlanificacionesDAO tblPlanificacionesDAO;
	
	@Resource
	private TblServidoresManager tblServidoresManager;
	
	@Resource
	private TblServiciosManager tblServiciosManager;
	
	@Resource
	private TblTipoPlanificacionesManager tblTipoPlanificacionesManager;
	
	@Resource
	private TblLogManager tblLogManager;
	
	@Autowired
	private QueryExecutorPlanificaciones queryExecutoPlanificaciones;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	
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
	 * @see es.minhap.TblServidoresManager.getPlanificacionesByServidor
	 */
	@Override
	public List<TblPlanificaciones> getPlanificacionesByServidor(Long servidorId) {
		TblPlanificacionesQuery query = new TblPlanificacionesQuery();
		TblServidoresQuery servidoresQuery = new TblServidoresQuery();
		servidoresQuery.setServidorid(servidorId);
		query.setTblServidores(servidoresQuery);
		query.setEliminadoIsNull(true);
		List<TblPlanificaciones> p = tblPlanificacionesDAO.search(query).getResults();
		for (TblPlanificaciones pl : p) {
			Hibernate.initialize(pl.getTblServidores());
			Hibernate.initialize(pl.getTblServicios());
			Hibernate.initialize(pl.getTblTipoPlanificaciones());
		}
		return p;
		
	}
	
	/**
	 * @see es.minhap.TblServidoresManager.updatePlanificacion
	 */
	@Override
	@Transactional
	public void updatePlanificacion(TblPlanificaciones p, String source, String accion, Long accionId, String descripcion) {
		tblPlanificacionesDAO.update(p);
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(p.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion);
		
		if (null != p.getTblServidores() && null == p.getTblServicios()){
			log.setSourceid(p.getTblServidores().getServidorid());
			TblServidores servidor = p.getTblServidores();
			servidor.setModificadopor(p.getCreadopor());
			servidor.setFechamodificacion(new Date());
			tblServidoresManager.update(servidor, null, null, null);
		}	
		else
			log.setSourceid(p.getTblServicios().getServicioid());
		log.setSourcename(source);
		
		tblLogManager.insertLog(log);
	}


	/**
	 * @see es.minhap.TblServidoresManager.getPlanificacionById
	 */
	@Override
	@Transactional
	public TblPlanificaciones getPlanificacionById(Long planificacionId) {
		TblPlanificaciones res = tblPlanificacionesDAO.get(planificacionId);
		Hibernate.initialize(res.getTblServicios());
		Hibernate.initialize(res.getTblServidores());
		return tblPlanificacionesDAO.get(planificacionId);
	}
	
	@Override
	@Transactional
	public Long insert(TblPlanificaciones planificacion, String source, String accion, Long accionId, String descripcion) {
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(planificacion.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion);
		if (null != planificacion.getTblServidores() && null == planificacion.getTblServicios()){
			log.setSourceid(planificacion.getTblServidores().getServidorid());
			TblServidores servidor = planificacion.getTblServidores();
			servidor.setModificadopor(planificacion.getCreadopor());
			servidor.setFechamodificacion(new Date());
			tblServidoresManager.update(servidor, null, null, null);
		}
		else
			log.setSourceid(planificacion.getTblServicios().getServicioid());
		log.setSourcename(source);
		
		tblLogManager.insertLog(log);
		
		
		return tblPlanificacionesDAO.insert(planificacion);
	}

	@Override
	@Transactional
	public List<TblPlanificaciones> getPlanificacionesByQuery(TblPlanificacionesQuery query) {
		List<TblPlanificaciones> lista = tblPlanificacionesDAO.search(query).getResults(); 
		for (TblPlanificaciones p : lista) {
			p.setTblServicios((null !=p.getTblServicios())? tblServiciosManager.getServicio(p.getTblServicios().getServicioid()) : null);
			p.setTblServidores((null !=p.getTblServidores())? tblServidoresManager.getServidorById(p.getTblServidores().getServidorid()) : null);
			p.setTblTipoPlanificaciones((null != p.getTblTipoPlanificaciones())? tblTipoPlanificacionesManager.getTipoPlanificacionById(p.getTblTipoPlanificaciones().getTipoplanificacionid()) : null);
		}
		return lista;
	}

	/**
	 * @see es.minhap.TblServidoresManager.getPlanificacionesByServidor
	 */
	@Override
	@Transactional
	public List<TblPlanificaciones> getPlanificacionesByServicio(Long servicioId) {
		TblPlanificacionesQuery query = new TblPlanificacionesQuery();
		TblServiciosQuery serviciosQuery = new TblServiciosQuery();
		serviciosQuery.setServicioid(servicioId);
		query.setTblServicios(serviciosQuery);
		query.setEliminadoIsNull(true);
		List<TblPlanificaciones> res = tblPlanificacionesDAO.search(query).getResults();
		
		for (TblPlanificaciones r : res) {
			Hibernate.initialize(r.getTblServicios());
		}
		
		return res;
		
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
