package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.misim.bus.model.ParametrosProveedor;
import es.minhap.plataformamensajeria.iop.misim.manager.ParametrosProveedorManager;
import es.mpr.plataformamensajeria.beans.ParametrosProveedorBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroProveedorMisim;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de parametros servidor a traves de JPA.
 * 
 * @author 
 * 
 */

@Service("servicioParametroProveedorMisimImpl")
public class ServicioParametroProveedorMisimImpl implements ServicioParametroProveedorMisim{

	private static Logger logger = Logger.getLogger(ServicioParametroProveedorMisimImpl.class);
	
	@Resource(name="ParametrosProveedorManagerImpl")
	private ParametrosProveedorManager parametrosProveedorManager;
	
	@Override
	@Transactional
	public void newParametroProveedorMisim(ParametrosProveedorBean parametroProveedorMisim, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try{
			ParametrosProveedor parametroProv = new ParametrosProveedor();
			parametroProv.setIdProveedor(parametroProveedorMisim.getIdProveedor());
			parametroProv.setParametro(parametroProveedorMisim.getParametro());
			
			if("parametroProveedor.valor".equals(parametroProveedorMisim.getTipoValor())){
				parametroProv.setValor(parametroProveedorMisim.getResultadoValor().trim());
			} else{
				parametroProv.setVariable(parametroProveedorMisim.getResultadoValor().trim());
			}
			
			Long id = parametrosProveedorManager.insert(parametroProv, source, accion, accionId, descripcion);
			
			parametroProv.setIdParametrosProveedor(id);

		}catch (Exception e){
			logger.error("ServicioParametroServidorImpl - newParametroProveedorMisim:" + e);
			throw new BusinessException(e,"errors.proveedorMisim.newParametroProveedorMisim");
		}
	}
	
	/////MIGRADO
	@Override
	public void deleteParametroProveedorMisim(ParametrosProveedorBean parametrosProveedor, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try {
			parametrosProveedorManager.delete(parametrosProveedor.getIdParametrosProveedor(), source, accion, accionId,descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - deleteParametroProveedorMisim:" + e);
			throw new BusinessException(e, "errors.proveedorMisim.deleteParametroProveedorMisim");
		}
	}
	
	@Override
	public List<ParametrosProveedorBean> getParametrosProveedorMisimByProveedorId(Long idProveedor) throws BusinessException {
		try {
			List<ParametrosProveedor> lista = parametrosProveedorManager.getParametrosPorProveedorMisim(idProveedor);
			return getListViewParametroProveedorBean(lista);
		} catch (Exception e) {
			logger.error("ServicioParametroProveedorMisimImpl - getParametroServidorByServidorId:" + e);
			throw new BusinessException(e, "errors.proveedorMisim.getParametrosProveedorMisimByProveedorId");
		}
	}
	
	@Override
	public boolean existeParametroProveedorMisim(ParametrosProveedorBean parametroProveedor) {
		try{
			if(parametroProveedor.getIdParametrosProveedor() != null){
				ParametrosProveedor parametroExisteProveedor = parametrosProveedorManager.getParametroPorIdParametrosProveedor(parametroProveedor.getIdParametrosProveedor());
				if(parametroExisteProveedor!=null){
					return true;
				}
			}
		}catch (Exception e) {
			logger.error("ServicioParametroProveedorMisimImpl - existeParametroProveedorMisim:" + e);
		}
		return false;
	}	
	
	/**
	 * <p>Convertirmos una lista de ViewParametroServidorJPA a una lista de ParametroServidorBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos ParametroServidorBean
	 */
	/////MIGRADO
	protected List<ParametrosProveedorBean> getListViewParametroProveedorBean(List<ParametrosProveedor> lista)
			throws BusinessException {
		List<ParametrosProveedorBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ParametrosProveedor ps : lista) {
				ParametrosProveedorBean proveedor = new ParametrosProveedorBean();

				proveedor.setIdParametrosProveedor(ps.getIdParametrosProveedor());
				proveedor.setIdProveedor(ps.getIdProveedor());
				proveedor.setParametro(ps.getParametro());
				
				if(ps.getValor() != null){
					proveedor.setValor(ps.getValor());
				}
				
				if(ps.getVariable() != null){
					proveedor.setVariable(ps.getVariable());
				}
				
				result.add(proveedor);
			}

		}
		return result;
	}
}
