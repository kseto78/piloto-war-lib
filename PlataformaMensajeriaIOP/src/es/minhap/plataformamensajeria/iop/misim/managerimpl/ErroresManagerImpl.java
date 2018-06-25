package es.minhap.plataformamensajeria.iop.misim.managerimpl;


import java.util.Date;

import javax.annotation.Resource;

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
			error.setEstado(estado);
			error.setFechaActualizacion(new Date());
			getErroresDAO().update(error);
		}
		
	}


}
