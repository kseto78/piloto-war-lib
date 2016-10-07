package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.beans.TipoPlanificacionBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidor</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioTipoPlanificacion {
	
	List<TipoPlanificacionBean> getTipoPlanificaciones() throws BusinessException;
	
	//public Integer getTipoPlanificaciones(ServidorBean criterio, EntityManager em) throws BusinessException;
	
	void newTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)throws BusinessException;
	
	void updateTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)throws BusinessException;
	
	TipoPlanificacionBean loadTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)throws BusinessException;
	
	void deleteTipoPlanificacion(TipoPlanificacionBean tipoPlanificacion)throws BusinessException;
}
