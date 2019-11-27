package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.beans.ContactoBean;
import es.minhap.plataformamensajeria.iop.manager.TblContactosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.sim.dao.TblContactosDAO;
import es.minhap.sim.dao.ViewContactosDAO;
import es.minhap.sim.model.TblContactos;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.ViewContactos;
import es.minhap.sim.query.TblContactosQuery;
import es.minhap.sim.query.ViewContactosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblContactosManagerImpl")
public class TblContactosManagerImpl implements TblContactosManager {

	@Resource
	private TblContactosDAO contactosDAO;

	@Resource
	private TblLogManager tblLogManager;

	@Resource
	private ViewContactosDAO viewContactosDAO;
	
	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicacion
	 */

	@Override
	@Transactional
	public List<ViewContactos> getContactosPaginado(int start, int size, String order, String column, ContactoBean ob) {
		OrderType ord = null;
		//
		// // Orden ascendente o descendente
		if (order == null || order.equals("1")) {
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		ViewContactosQuery query = new ViewContactosQuery();

		if (null != ob.getNombre()) {
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre("%" + ob.getNombre() + "%");
		}
		if (null != ob.getApellidos()) {
			query.setApellidosComparator(TextComparator.ILIKE);
			query.setApellidos("%" + ob.getApellidos() + "%");
		}
		if (null != ob.getAplicacionid()){
			query.setAplicacionid(ob.getAplicacionid());
		}
		query.setMaxResults(Integer.valueOf(size));
		query.addOrder(column, ord);
		
		return viewContactosDAO.search(query).getResults();		
	}


	/**
	 * @return the aplicacionesDAO
	 */
	public TblContactosDAO getContactosDAO() {
		return contactosDAO;
	}

	/**
	 * @param aplicacionesDAO
	 *            the aplicacionesDAO to set
	 */
	public void setContactosDAO(TblContactosDAO contactosDAO) {
		this.contactosDAO = contactosDAO;
	}

	@Override
	public TblContactos getById(Long contactoID) {
		return contactosDAO.get(contactoID);
	}

	@Override
	public Long insert(TblContactos contactoTO, String source, String accion, Long accionId) {

		Long id = contactosDAO.insert(contactoTO);

		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(contactoTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(contactoTO.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		return id;

	}

	@Override
	public void update(TblContactos contacto, String source, String accion, Long accionId) {
		contactosDAO.update(contacto);

		TblLog log = new TblLog();

		log.setAdtfecha(new Date());
		log.setAdtusuario(contacto.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(contacto.getNombre());
		log.setSourceid(contacto.getAplicacionid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);

	}

	@Override
	public void delete(Long contactoid, String source, String accion, Long accionId) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<TblContactos> getContactos(TblContactosQuery query) {
		return getContactosDAO().search(query).getResults();

	}
}
