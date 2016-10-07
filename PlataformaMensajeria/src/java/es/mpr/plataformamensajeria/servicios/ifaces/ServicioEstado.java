package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de Estados</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioEstado {
	
	List<EstadoBean> getEstados(EstadoBean criterio) throws BusinessException;
	
	List<EstadoBean> getEstados() throws BusinessException;
	
	PaginatedList<EstadoBean> getCanal(int start, int size, String order, String columnSort,EstadoBean criterio) 
		throws BusinessException;
	
	public Integer getTotalEstados(EstadoBean criterio, EntityManager em) throws BusinessException;
	
	void newEstado(EstadoBean estado)throws BusinessException;
	
	void updateEstado(EstadoBean estado)throws BusinessException;
	
	EstadoBean loadEstado(EstadoBean estado)throws BusinessException;
	
	void deleteEstado(EstadoBean estado)throws BusinessException;
	
	
}
