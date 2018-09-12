package es.minhap.plataformamensajeria.iop.misim.managerimpl;


import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.ErroresDAO;
import es.minhap.misim.bus.model.Errores;
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;


/**
 * 
 * @author everis
 *
 */
@Service("ErroresManagerImpl")
public class ErroresManagerImpl implements ErroresManager {

	private static final Logger LOG = LoggerFactory.getLogger(ErroresManagerImpl.class);
	
	@Resource 
	private ErroresDAO erroresDAO;

	
	/**
	 * @return the erroresDAO
	 */
	public ErroresDAO getErroresDAO() {
		return erroresDAO;
	}

	/**
	 * @param erroresDAO the erroresDAO to set
	 */
	public void setErroresDAO(ErroresDAO erroresDAO) {
		this.erroresDAO = erroresDAO;
	}

	@Override
	public boolean getEstadoMq() {
		Errores error = getErroresDAO().get(1L);
		
		if (error != null && error.getEstado() != null) {
			return error.getEstado();
		} 
		
		return false;
	}

	@Override
	public void updateErrorMq(boolean estado) {
		Errores error = getErroresDAO().get(1L);
		
		if (error != null) {
			actualizarEstado(estado, error);
		}
		
	}

	@Override
	public boolean comprobarActiveMqActivo(boolean estado) {
		LOG.debug("Estamos en comprobarActiveMqActivo - ErroresManagerImpl");
		Errores error = getErroresDAO().get(1L);
		boolean actualizado = false;
		//Comprobamos que coincida con el estado pasado por parametro
		if (!(estado == error.getEstado())) {
			actualizarEstado(estado, error);
			actualizado = true;
		}
		return actualizado;
	}

	private void actualizarEstado(boolean estado, Errores error) {
		LOG.debug("Estamos en actualizarEstado - ErroresManagerImpl");
		error.setEstado(estado);
		error.setFechaActualizacion(new Date());
		getErroresDAO().update(error);
	}

}
