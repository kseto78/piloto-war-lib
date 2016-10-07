package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.model.AplicacionJPA;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioAplicacion {
	
	List<AplicacionBean> getAplicaciones(AplicacionBean criterio) throws BusinessException;
	List<AplicacionBean> getAplicaciones() throws BusinessException;
	AplicacionJPA loadAplicacionJPA(AplicacionJPA aplicacion) throws BusinessException;
	PaginatedList<AplicacionBean> getAplicaciones(int start, int size, String order, String columnSort,AplicacionBean criterio) 
		throws BusinessException;
	
	public Integer getTotalAplicaciones(AplicacionBean criterio, EntityManager em) throws BusinessException;
	
	Integer newAplicacion(AplicacionBean aplicacion)throws BusinessException;
	
	void updateAplicacion(AplicacionBean aplicacion)throws BusinessException;
	
	AplicacionBean loadAplicacion(AplicacionBean aplicacion)throws BusinessException;
	
	void deleteAplicacion(AplicacionBean aplicacion)throws BusinessException;
	public List<AplicacionBean> getAplicacionesNoAsignadas(String idUsuario) throws BusinessException;
	
	public List<AplicacionBean> getAplicacionesMenu(String rolUsuario, Integer userName);
	boolean existeUsuario(String usuario);
}

