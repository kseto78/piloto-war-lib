package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosAplicacionesManager;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.model.ViewUsuariosAplicaciones;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioUsuarioAplicacionImpl")
public class ServicioUsuarioAplicacionImpl implements ServicioUsuarioAplicacion{

	private static Logger logger = Logger.getLogger(ServicioUsuarioAplicacionImpl.class);
	
	@Resource(name="TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosAplicacionesManager;
	
	@Resource(name="ViewUsuariosAplicacionesManagerImpl")
	private ViewUsuariosAplicacionesManager viewUsuariosAplicacionesManager;
	
	@Resource(name= "tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuarios;
	
	/**
	 * <p>Convertirmos una lista de ViewUsuarioAplicacionJPA a una lista de UsuarioAplicacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuarioBean
	 */
	
	////MIGRADO
	protected List<UsuarioAplicacionBean> getListViewUsuarioAplicacionBean(List<ViewUsuariosAplicaciones> lista) throws BusinessException {
	
		List<UsuarioAplicacionBean> result = new ArrayList<>();
	
		if (lista!=null && !lista.isEmpty()) {
			for (ViewUsuariosAplicaciones ua : lista) {
				if (null != ua){
					UsuarioAplicacionBean usuario =  new UsuarioAplicacionBean();
					usuario.setAplicacionId(ua.getAplicacionid());
					usuario.setCreadoPor(ua.getCreadopor());
					usuario.setFechaCreacion((null != ua.getFechacreacion())? DateUtils.truncate(ua.getFechacreacion(), Calendar.DATE) : null);
					usuario.setIsActivo(null == ua.getActivo() || ua.getActivo() != 0);
					usuario.setModo(ua.getModo());
					usuario.setNombreAplicacion(ua.getNombreaplicacion());
					usuario.setNombreUsuario(ua.getNombreusuario());
					usuario.setRolId(ua.getRolid().intValue());
					usuario.setRolUsuario(ua.getRolusuario());
					usuario.setUsuarioAplicacionId(ua.getUsuarioaplicacionid());
					usuario.setUsuarioId(ua.getUsuarioid());
					result.add(usuario);
				}
			}				
		}		
		return result;
	}	

	
////MIGRADO
	protected List<UsuarioAplicacionBean> getListUsuarioAplicacionBean(List<TblUsuariosAplicaciones> lista) throws BusinessException {
		
		List<UsuarioAplicacionBean> result = new ArrayList<>();
	
		if (lista!=null && !lista.isEmpty()) {
			for (TblUsuariosAplicaciones ua : lista) {
				if (null != ua){
					UsuarioAplicacionBean usuario =  new UsuarioAplicacionBean();
					usuario.setAplicacionId(ua.getAplicacionid());
					usuario.setModo(ua.getModo());
					result.add(usuario);
				}
			}				
		}		
		return result;
	}	
	

	////MIGRADO
	@Override
	@Transactional
	public Integer newUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try{
			TblUsuariosAplicaciones usuarioAplicacionTO = getUsuarioAplicacionTO(usuarioAplicacion);

			usuarioAplicacionTO.setFechacreacion(new Date());
			usuarioAplicacionTO.setCreadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			Long id = tblUsuariosAplicacionesManager.insert(usuarioAplicacionTO, source, accion, accionId, descripcion);
						
			usuarioAplicacion.setUsuarioAplicacionId(id);
			usuarioAplicacion.setFechaCreacion(usuarioAplicacionTO.getFechacreacion());
			usuarioAplicacion.setCreadoPor(usuarioAplicacionTO.getCreadopor());
			usuarioAplicacion.setModo(usuarioAplicacionTO.getModo());
			return id.intValue();
		}catch (Exception e){
			logger.error("OrganismosAction - newUsuarioAplicacion:" + e);	
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
		
	}

	/**
	 * <p>Obtenemos un objeto UsuarioAplicacionJPA a partir de un objeto UsuarioAplicacionBean</p>
	 * 
	 * @param usuarioBean 
	 * 
	 * @return objeto UsuarioJPA
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	////MIGRADO
	protected TblUsuariosAplicaciones getUsuarioAplicacionTO(UsuarioAplicacionBean usuarioAplicacion) {
		 
		TblUsuariosAplicaciones u = new TblUsuariosAplicaciones();
		u.setAplicacionid((null != usuarioAplicacion.getAplicacionId())? usuarioAplicacion.getAplicacionId() : null);
		u.setModo(usuarioAplicacion.getModo());
		u.setTblUsuarios((null != usuarioAplicacion.getUsuarioId())? tblUsuarios.getById(usuarioAplicacion.getUsuarioId()) : null);
		u.setUsuarioaplicacionid((null!=usuarioAplicacion.getUsuarioAplicacionId())? usuarioAplicacion.getUsuarioAplicacionId() : null);

		return u;
	}
	/**
	 * <p>Obtenemos un objeto UsuarioAplicacionBean a partir de un objeto UsuarioAplicacionJPA</p>
	 * 
	 * @param usuarioAplicacionJPA
	 * 
	 * @return objeto UsuarioBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	///MIGRADO
	protected UsuarioAplicacionBean getUsuarioAplicacionBean(TblUsuariosAplicaciones usuario) {
		 
		UsuarioAplicacionBean ua = new UsuarioAplicacionBean();
		ua.setAplicacionId(usuario.getAplicacionid());
		ua.setCreadoPor(usuario.getCreadopor());
		ua.setFechaCreacion((null !=usuario.getFechacreacion())? DateUtils.truncate(usuario.getFechacreacion(), Calendar.DATE) : null);
		ua.setIsActivo(null != usuario.getTblUsuarios() && 
				null != usuario.getTblUsuarios().getActivo() && usuario.getTblUsuarios().getActivo());
		ua.setModo(usuario.getModo());
		ua.setNombreUsuario((null != usuario.getTblUsuarios())? usuario.getTblUsuarios().getNombre() : null);
		ua.setRolId((null != usuario.getTblUsuarios())? usuario.getTblUsuarios().getRolid() : null);
		ua.setUsuarioAplicacionId(usuario.getUsuarioaplicacionid());
		ua.setUsuarioId((null != usuario.getTblUsuarios())? usuario.getTblUsuarios().getUsuarioid() : null);
				
		return ua;
	}	


	
	////MIGRADO
	@Override
	public List<UsuarioAplicacionBean> getUsuarioAplicacionesByUsuarioId(Integer usuarioId)
			throws BusinessException {
				
		List<ViewUsuariosAplicaciones> lista = viewUsuariosAplicacionesManager.getViewUsuariosAplicacionesBy(usuarioId.longValue(), null);
		
		return getListViewUsuarioAplicacionBean(lista);				
	}
	
	/////MIGRADO
	@Override
	public List<UsuarioAplicacionBean> getUsuarioAplicacionesByAplicacionId(Integer aplicacionId)
			throws BusinessException {
		List<ViewUsuariosAplicaciones>lista = viewUsuariosAplicacionesManager.getViewUsuariosAplicacionesBy(null, aplicacionId.longValue());
		
		return getListViewUsuarioAplicacionBean(lista);		
	}
	
	
	////MIGRADO
	@Override
	@Transactional
	public UsuarioAplicacionBean loadUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)	throws BusinessException {
		TblUsuariosAplicaciones usuarioAplicacionTO = tblUsuariosAplicacionesManager.getUsuariosAplicacionesById(usuarioAplicacionBean.getUsuarioAplicacionId());		
			return getUsuarioAplicacionBean(usuarioAplicacionTO);	
	}
	
	
	/////MIGRADO
	@Override
	@Transactional
	public void deleteUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean, String source, String accion, Long accionId, String descripcion) throws BusinessException {
	try{
		TblUsuariosAplicaciones usuarioAplicacionTO = tblUsuariosAplicacionesManager.getUsuariosAplicacionesById(usuarioAplicacionBean.getUsuarioAplicacionId());
		tblUsuariosAplicacionesManager.delete(usuarioAplicacionTO, source, accion, accionId, descripcion);

	} catch (Exception e){
		logger.error("OrganismosAction - deleteUsuarioAplicacion:" + e);			
		throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
	}
		
	}

	///MIGRADO
	@Override
	public List<UsuarioAplicacionBean> getListPermisosAplicacionesUsuario(String userName) {
		try{
			Long usuarioId = tblUsuarios.getUsuarioByUsername(userName);
			List<ViewUsuariosAplicaciones> lista =viewUsuariosAplicacionesManager.getViewUsuariosAplicacionesBy(usuarioId, null);
			return getListViewUsuarioAplicacionBean(lista);
		}catch (Exception e){
			logger.error("OrganismosAction - create:" + e);			
		} 
		return new ArrayList<>();
	}
}
