package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.TipoParametroBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de tipo parametro</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioTipoParametro {
	
	List<TipoParametroBean> getTipoParametrosServidor() throws BusinessException;
	List<TipoParametroBean> getTipoParametrosProveedorSMS() throws BusinessException;
	List<TipoParametroBean> getTipoParametrosReceptorSMS() throws BusinessException;
	List<TipoParametroBean> getTipoParametrosServidorPush() throws BusinessException;
	
	//public Integer getTotalServidores(ServidorBean criterio, EntityManager em) throws BusinessException;
	
	void newTipoParametro(TipoParametroBean tipoParametro)throws BusinessException;
	
	void updateTipoParametro(TipoParametroBean tipoParametro)throws BusinessException;
	
	TipoParametroBean loadTipoParametro(TipoParametroBean tipoParametro)throws BusinessException;
	
	void deleteTipoParametro(TipoParametroBean tipoParametro)throws BusinessException;
	

}
