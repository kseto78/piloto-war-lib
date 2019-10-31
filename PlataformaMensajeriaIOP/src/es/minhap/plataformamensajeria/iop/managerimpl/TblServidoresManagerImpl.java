package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.sim.dao.TblServidoresDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServidoresManagerImpl")
public class TblServidoresManagerImpl implements TblServidoresManager {

	protected static final String R_CONST_1 = "%";

	@Resource 
	private TblServidoresDAO servidoresDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	 
	/**
	 * @see es.minhap.TblServidoresManager.getServidor
	 */
	@Override
	@Transactional
	public TblServidores getServidor(TblServidoresQuery query) {
		return getServidoresDAO().searchUnique(query);
	}
	
	/**
	 * @see es.minhap.TblServidoresManager.countServidor
	 */
	@Override
	@Transactional
	public Integer countServidor(TblServidoresQuery query) {
		return getServidoresDAO().count(query);
	}

	
	/**
	 * @see es.minhap.TblServidoresManager.getServidorById
	 */
	@Override
	@Transactional
	public TblServidores getServidorById(Long servidorId) {
		return getServidoresDAO().get(servidorId);
	}
	
	/**
	 * @see es.minhap.TblServidoresManager.getServidoresPaginado
	 */
	@Override
	@Transactional
	public List<TblServidores> getServidoresPaginado(int start, int size, String order, String columnSort, String nombreServidor, int tipoServidor, boolean count){
		OrderType ord = null;
		
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)){
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		TblServidoresQuery query = new TblServidoresQuery();
		

		if (null != nombreServidor){
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre(R_CONST_1 + nombreServidor + R_CONST_1);
		}
		query.addOrder(columnSort, ord);
		query.setEliminadoIsNull(true);
		query.setTipo(Integer.valueOf(tipoServidor));	
		if (!count){
			query.setPrimerResultado(start);
			query.setMaxResultados(size);
		}
		
		return servidoresDAO.search(query).getResults();
	}

	/**
	 * @see es.minhap.TblServidoresManager.update
	 */
	@Override
	@Transactional
	public void update(TblServidores servidor, String source, String accion, Long accionId) {
		servidoresDAO.update(servidor);
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());
			log.setAdtusuario(servidor.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(servidor.getNombre());
			log.setSourceid(servidor.getServidorid());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}
	}
	
	@Override
	@Transactional
	public Long insert(TblServidores servidor, String source, String accion, Long accionId) {
		Long id = servidoresDAO.insert(servidor);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(servidor.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(servidor.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	@Override
	@Transactional
	public List<TblServidores> getServidoresByQuery(TblServidoresQuery query) {
		return servidoresDAO.search(query).getResults();
	}
	
	/**
	 * @return the servidoresDAO
	 */
	public TblServidoresDAO getServidoresDAO() {
		return servidoresDAO;
	}

	/**
	 * @param servidoresDAO the servidoresDAO to set
	 */
	public void setServidoresDAO(TblServidoresDAO servidoresDAO) {
		this.servidoresDAO = servidoresDAO;
	}

}
