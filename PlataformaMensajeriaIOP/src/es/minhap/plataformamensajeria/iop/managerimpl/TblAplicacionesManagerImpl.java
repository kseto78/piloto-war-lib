package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblAplicacionesDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.query.TblAplicacionesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblAplicacionesManagerImpl")
public class TblAplicacionesManagerImpl implements TblAplicacionesManager {

	@Resource 
	private TblAplicacionesDAO aplicacionesDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	 
	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicaciones
	 */
	@Override
	@Transactional
	public List<TblAplicaciones> getAplicaciones(TblAplicacionesQuery query) {
		return getAplicacionesDAO().search(query).getResults();
	}

	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicacion
	 */
	@Override
	@Transactional
	public TblAplicaciones getAplicacion(TblAplicacionesQuery query) {
		return getAplicacionesDAO().searchUnique(query);
	}

	/**
	 * @see es.minhap.TblAplicacionesManager.existeAplicacion
	 */
	@Override
	@Transactional
	public Boolean existeAplicacion(String usuario, String password) {
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.setUsuarioComparator(TextComparator.EQUALS);
		query.setPasswordComparator(TextComparator.EQUALS);
		List<TblAplicaciones> listaAplicaciones = getAplicaciones(query);
		
		return (null != listaAplicaciones && listaAplicaciones.size() == 1)? true : false;
	}

	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicacion
	 */
	@Override
	@Transactional
	public TblAplicaciones getAplicacion(Long idAplicacion) {
		return aplicacionesDAO.get(idAplicacion);
	}

	
	@Override
	@Transactional
	public List<TblAplicaciones> getAplicacionesPaginado(int start, int size, String order, String columnSort, String nombreAplicacion, int idAplicacion) {
		OrderType ord = null;
		
		// Orden ascendente o descendente
		if (order == null || order.equals("1")){
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		
		if (idAplicacion != 0){
			query.setAplicacionid((long) idAplicacion);

		}
		if (null != nombreAplicacion){
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre("%" + nombreAplicacion + "%");
		}
		query.setMaxResults(Integer.valueOf(size));
		query.addOrder(columnSort, ord);
		query.setEliminadoIsNull(true);
		return aplicacionesDAO.search(query).getResults();
	}
	
	@Override
	@Transactional
	public Boolean existeAplicacionUsuario(String usuario) {
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		query.setUsuario(usuario);
		List<TblAplicaciones> lista = aplicacionesDAO.search(query).getResults();
		return (null != lista && !lista.isEmpty())? true : false;
	}
	
	
	@Override
	@Transactional
	public void update(TblAplicaciones aplicacion, String source, String accion, Long accionId) {
		aplicacionesDAO.update(aplicacion);
		
		TblLog log = new TblLog();
		
		log.setAdtfecha(new Date());
		log.setAdtusuario(aplicacion.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(aplicacion.getNombre());
		log.setSourceid(aplicacion.getAplicacionid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
	}
	
	@Override
	@Transactional
	public void updateSMS(TblAplicaciones aplicacion) {
		aplicacionesDAO.update(aplicacion);
	}

	@Override
	@Transactional
	public Long insert(TblAplicaciones aplicacion, String source, String accion, Long accionId) {
		Long id = aplicacionesDAO.insert(aplicacion);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(aplicacion.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(aplicacion.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		return id;
	}

	@Override
	@Transactional
	public List<TblAplicaciones> getAplicacionesNoEliminadasOrdenadas() {
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		query.setEliminadoIsNull(true);
		query.addOrder("nombre", OrderType.ASC);
		return getAplicaciones(query);
	}
	
	
	/**
	 * @return the aplicacionesDAO
	 */
	public TblAplicacionesDAO getAplicacionesDAO() {
		return aplicacionesDAO;
	}

	/**
	 * @param aplicacionesDAO the aplicacionesDAO to set
	 */
	public void setAplicacionesDAO(TblAplicacionesDAO aplicacionesDAO) {
		this.aplicacionesDAO = aplicacionesDAO;
	}

}
