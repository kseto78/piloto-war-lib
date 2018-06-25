package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.AplicacionDAO;
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.query.AplicacionQuery;

@Service("aplicacionManager")
public class AplicacionManagerImpl implements AplicacionManager{

	private static final Logger logger = Logger.getLogger(AplicacionManagerImpl.class);
		
	@Resource
	AplicacionDAO AplicacionDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#guardarAplicacion(es.minhap.misim.bus.model.Aplicacion)
	 */
	public Long guardarAplicacion(Aplicacion aplicacion){
		logger.info("guardarAplicacion - start");
		return AplicacionDAO.insert(aplicacion);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#insertAplicacion(es.minhap.misim.bus.model.Aplicacion)
	 */
	public Long insertAplicacion(Aplicacion aplicacion) {
		Long id = null;
		if(aplicacion!=null){
			id = AplicacionDAO.insert(aplicacion);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#getAplicacionById(Long)
	 */
	public Aplicacion getAplicacionById(Long idAplicacion) {
		if(idAplicacion==null)
			return null;
		
		return AplicacionDAO.get(idAplicacion);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#updateAplicacion(es.minhap.misim.bus.model.Aplicacion)
	 */
	public boolean updateAplicacion(Aplicacion aplicacion) {
		boolean update = false;
		if(aplicacion!=null && aplicacion.getIdAplicacion()>0){
			AplicacionDAO.update(aplicacion);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#eliminarAplicacion(Long)
	 */
	public void eliminarAplicacion(Long id) {
		logger.info("eliminarAplicacion - start");
		AplicacionDAO.delete(id);
		logger.info("eliminarAplicacion - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AplicacionManager#getAplicacion(es.minhap.misim.bus.query.AplicacionQuery)
	 */	
	public List<Aplicacion> getAplicacion(AplicacionQuery query) {
		return AplicacionDAO.search(query).getResults();
	}
	
}
