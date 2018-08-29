package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.EstadoBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de Estados</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioEstado {
	
	/**
	 * Obtener estados.
	 *
	 * @return estados
	 * @throws BusinessException the business exception
	 */
	List<EstadoBean> getEstados() throws BusinessException;
	
}
