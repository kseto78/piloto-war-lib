package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.CanalBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de canales</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioCanal {
	
	List<CanalBean> getCanales(CanalBean criterio) throws BusinessException;
	
	List<CanalBean> getCanales() throws BusinessException;
	
	PaginatedList<CanalBean> getCanal(int start, int size, String order, String columnSort,CanalBean criterio) 
		throws BusinessException;
	
	public Integer getTotalCanales(CanalBean criterio, EntityManager em) throws BusinessException;
	
	void newCanal(CanalBean canal)throws BusinessException;
	
	void updateCanal(CanalBean canal)throws BusinessException;
	
	CanalBean loadCanal(CanalBean canal)throws BusinessException;
	
	void deleteCanal(CanalBean canal)throws BusinessException;
	
	
}
