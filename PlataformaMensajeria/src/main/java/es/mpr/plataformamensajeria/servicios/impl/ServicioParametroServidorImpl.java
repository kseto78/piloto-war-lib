package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblParametrosServidorManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblTiposParametrosManager;
import es.minhap.sim.model.TblParametrosServidor;
import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de parametros servidor a traves de JPA.
 * 
 * @author 
 * 
 */

@Service("servicioParametroServidorImpl")
public class ServicioParametroServidorImpl implements ServicioParametroServidor{

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioParametroServidorImpl.class);
	
	/**  tbl parametros servidor manager. */
	@Resource(name="tblParametrosServidorManagerImpl")
	private TblParametrosServidorManager tblParametrosServidorManager;
	
	/**  tbl servidores manager. */
	@Resource 
	private TblServidoresManager tblServidoresManager;

	/**  tbl tipos parametros. */
	@Resource 
	private TblTiposParametrosManager tblTiposParametros;

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#newParametroServidor(es.mpr.plataformamensajeria.beans.ParametroServidorBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	//////MIGRADO
	@Override
	@Transactional
	public void newParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try{
			TblParametrosServidor parametroServ = new TblParametrosServidor();
			String creadoPor = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			parametroServ.setCreadopor(creadoPor);
			parametroServ.setTblServidores(tblServidoresManager.getServidorById(parametroServidor.getServidorid()));
			parametroServ.setTblTiposParametros(tblTiposParametros.getTipoParametroById(parametroServidor.getTipoparametroid()));
			parametroServ.setValor(parametroServidor.getValor().trim());
			
			Long id = tblParametrosServidorManager.insert(parametroServ, source, accion, accionId, descripcion);
			
			parametroServidor.setId(id);
			parametroServidor.setActivo(true);
			parametroServidor.setFechacreacion(new Date());
			parametroServidor.setCreadopor(creadoPor);

		}catch (Exception e){
			logger.error("ServicioParametroServidorImpl - newParametroServidor:" + e);
			throw new BusinessException(e,"errors.organismo.newOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#deleteParametroServidor(es.mpr.plataformamensajeria.beans.ParametroServidorBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void deleteParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try {
			tblParametrosServidorManager.delete(parametroServidor.getParametroservidorid(), source, accion, accionId, descripcion);			
		}
		catch (Exception e){
			logger.error("ServicioParametroServidorImpl - deleteParametroServidor:" + e);
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#getParametroServidorByServidorId(java.lang.Integer)
	 */
	//////MIGRADO
	@Override
	public List<ParametroServidorBean> getParametroServidorByServidorId(Integer servidorId) throws BusinessException {
		try {
			List<TblParametrosServidor> lista = tblParametrosServidorManager.getParametrosPorServidor(servidorId.longValue(), null);
			List<ParametroServidorBean> result = getListViewParametroServidorBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioParametroServidorImpl - getParametroServidorByServidorId:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}

	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#getParametroServidorByProveedorSMSId(java.lang.Integer)
 */
//////MIGRADO
	@Override
	public List<ParametroServidorBean> getParametroServidorByProveedorSMSId(Integer proveedorSMSId) throws BusinessException {
		try {
			List<TblParametrosServidor> lista = tblParametrosServidorManager.getParametrosPorServidor(proveedorSMSId.longValue(), null);
			List<ParametroServidorBean> result = getListViewParametroServidorBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioParametroServidorImpl - getParametroServidorByServidorId:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#getParametroServidorByReceptorSMSId(java.lang.Integer)
 */
//////MIGRADO
	@Override
	public List<ParametroServidorBean> getParametroServidorByReceptorSMSId(Integer receptorSMSId) throws BusinessException {
		try {
			List<TblParametrosServidor> lista = tblParametrosServidorManager.getParametrosPorServidor(receptorSMSId.longValue(), null);
			List<ParametroServidorBean> result = getListViewParametroServidorBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioParametroServidorImpl - getParametroServidorByServidorId:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#getParametroServidorByServidorPushId(java.lang.Integer)
 */
//////MIGRADO
	@Override
	public List<ParametroServidorBean> getParametroServidorByServidorPushId(Integer servidorPushId) throws BusinessException {
		try {
			List<TblParametrosServidor> lista = tblParametrosServidorManager.getParametrosPorServidor(servidorPushId.longValue(), null);
			List<ParametroServidorBean> result = getListViewParametroServidorBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioParametroServidorImpl - getParametroServidorByServidorId:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor#existeParametroServidor(es.mpr.plataformamensajeria.beans.ParametroServidorBean)
	 */
	/////MIGRADO
	@Override
	public boolean existeParametroServidor(
			ParametroServidorBean parametroServidor) throws BusinessException {
		try{
			List<TblParametrosServidor> lista = tblParametrosServidorManager.getParametrosPorServidor(parametroServidor.getServidorid(), parametroServidor.getTipoparametroid());
			if(lista!=null&&!lista.isEmpty()){
				return true;
			}
		}catch (Exception e) {
			logger.error("ServicioParametroServidorImpl - existeParametroServidor:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
		return false;
	}	
	
	/**
	 * <p>Convertirmos una lista de ViewParametroServidorJPA a una lista de ParametroServidorBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos ParametroServidorBean
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	protected List<ParametroServidorBean> getListViewParametroServidorBean(List<TblParametrosServidor> lista)
			throws BusinessException {
		List<ParametroServidorBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<ParametroServidorBean>();

			for (TblParametrosServidor ps : lista) {
				ParametroServidorBean servidor = new ParametroServidorBean();

				servidor.setActivo(ps.getActivo());
				servidor.setCreadopor(ps.getCreadopor());
				servidor.setFechacreacion(ps.getFechacreacion());
				servidor.setFechamodificacion(ps.getFechamodificacion());
				servidor.setId(ps.getParametroservidorid());
				servidor.setModificadopor(ps.getModificadopor());
				servidor.setParametroservidorid(ps.getParametroservidorid());
				servidor.setServidorid(ps.getTblServidores().getServidorid());
				servidor.setTipodescripcion(ps.getTblTiposParametros().getDescripcion());
				servidor.setTiponombre(ps.getTblTiposParametros().getNombre());
				servidor.setTipoparametroid(ps.getTblTiposParametros().getTipoparametroid());
				servidor.setTipotag(ps.getTblTiposParametros().getTags());
				servidor.setValor(ps.getValor());
				result.add(servidor);
			}

		}
		return result;
	}
}
