package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewUsuariosAplicaciones;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosAplicacionesManager;
import es.minhap.sim.model.ViewUsuariosAplicaciones;

/**
 * 
 * @author everis
 *
 */
@Service("ViewUsuariosAplicacionesManagerImpl")
public class ViewUsuariosAplicacionesManagerImpl implements ViewUsuariosAplicacionesManager {

	@Resource(name="QueryExecutorViewUsuariosAplicacionesImpl")
	private QueryExecutorViewUsuariosAplicaciones queryExecutorViewUsuariosAplicaciones;

	/**
	 * @see es.minhap.ViewUsuariosPushManagerImpl.getViewUsuariosAplicacionesByQuery
	 */

	@Override
	public List<ViewUsuariosAplicaciones> getViewUsuariosAplicacionesBy(Long usuarioId, Long aplicacionId) {
		return queryExecutorViewUsuariosAplicaciones.findViewUsuarioAplicacion(usuarioId, aplicacionId);
	}



	
}
