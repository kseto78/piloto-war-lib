package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosManager;
import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioUsuarioImpl")
public class ServicioUsuarioImpl implements ServicioUsuario{

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioUsuarioImpl.class);
	
	/**  view usuarios manager. */
	@Resource(name="ViewUsuariosManagerImpl")
	private ViewUsuariosManager viewUsuariosManager;
	
	/**  tbl usuarios. */
	@Resource(name= "tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuarios;
	
	/**  tbl usuarios push. */
	@Resource(name= "TblUsuariosPushManagerImpl")
	private TblUsuariosPushManager tblUsuariosPush;
	
	/**  query executor usuarios push. */
	@Resource
	private QueryExecutorUsuariosPush queryExecutorUsuariosPush;
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#getUsuarios(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.UsuarioBean)
	 */
	///MIGRADO
	@Override
	public PaginatedList<UsuarioBean> getUsuarios(int start, int size, String order,
			String columnSort, UsuarioBean criterio) throws BusinessException {
		String nombre=null;
		Long aplicacionId = null;
		Integer rolId = null;
		try {
			//Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("2","nombre");
			columns.put("3","rolid");
			
			if (columnSort==null){
				columnSort = "2"; //Id
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "nombre";
			}
		
			if (null != criterio && null != criterio.getNombre()){
				nombre = criterio.getNombre();
			}
			if (null != criterio && null != criterio.getAplicacionId()){
				aplicacionId = criterio.getAplicacionId().longValue();
			}
			if (null != criterio && null != criterio.getRolId()){
				rolId = criterio.getRolId();
			}
			
			List<TblUsuarios> lista = viewUsuariosManager.getViewUsuariosPaginado(start, size, order, column, nombre, aplicacionId, rolId, false);
			
			List<UsuarioBean> pageList = getListUsuarioBean(lista);
			
			 
			//Total de organismos
			Integer rowcount = viewUsuariosManager.getViewUsuariosPaginado(start, size, order, column, nombre, aplicacionId, rolId, true).size();
			
			PaginatedList<UsuarioBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			return result;
		}
		catch (Exception e){
			logger.error("ServicioUsuarioImpl - getUsuarios:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#getUsuariosByServicioMovilId(java.lang.Long)
	 */
	@Override
	public List<UsuariosPushBean> getUsuariosByServicioMovilId(Long servicioMovilId) throws BusinessException {
	
	List<es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean> result = null;
	List<UsuariosPushBean> res = new ArrayList<>();
	try {

		result = queryExecutorUsuariosPush.listaUsuariosPushbyServicioMovilId(servicioMovilId);
		
		for (es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean upb : result) {
			UsuariosPushBean bean = new UsuariosPushBean();
			BeanUtils.copyProperties(bean, upb);	
			res.add(bean);
		}
			
	} catch (Exception e) {
		throw new BusinessException(e, "errors.job.usuariosServiciosMoviles.getUsuariosByServicioMovilId" + servicioMovilId);
	}
	
	return res;
}
	

/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#newUsuario(es.mpr.plataformamensajeria.beans.UsuarioBean, java.lang.String, java.lang.String, java.lang.Long)
 */
/////MIGRADO
	@Override
	@Transactional
	public Integer newUsuario(UsuarioBean usuario, String source, String accion, Long accionId) throws BusinessException {
		try{
			TblUsuarios usuarioTO = getUsuarioTO(usuario);
			usuarioTO.setCreadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			usuarioTO.setFechacreacion(new Date());
			
			Long usuarioId = tblUsuarios.insert(usuarioTO, source, accion, accionId);
			
			usuario.setFechaCreacion(usuarioTO.getFechacreacion());
			usuario.setCreadoPor(usuarioTO.getCreadopor());
			return usuarioId.intValue();
		}catch (Exception e){
			logger.error("ServicioUsuarioImpl - newUsuario:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#existeUsuario(java.lang.String)
	 */
	////MIGRADO
	@Override
	@Transactional
	public boolean existeUsuario(String loginUsuario) throws BusinessException {
		TblUsuariosQuery query = new TblUsuariosQuery();
		query.setLogin(loginUsuario);
		query.setLoginComparator(TextComparator.EQUALS);
		List<TblUsuarios> lista = tblUsuarios.getUsuariosAplicacionesByQuery(query);
		return (null != lista && !lista.isEmpty())? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#existeUsuarioEdicion(java.lang.Integer, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException {
		boolean res = false;
		
		if(null!=idUsuario && null!=loginUsuario && !loginUsuario.isEmpty()){
			TblUsuariosQuery query = new TblUsuariosQuery();
			query.setLogin(loginUsuario);
			query.setLoginComparator(TextComparator.EQUALS);
			query.addUsuarioidNotIn(idUsuario.longValue());
			List<TblUsuarios> lista = tblUsuarios.getUsuariosAplicacionesByQuery(query);
			res = (null != lista && !lista.isEmpty())? true : false;
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#updateUsuario(es.mpr.plataformamensajeria.beans.UsuarioBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateUsuario(UsuarioBean usuario, String source, String accion, Long accionId) throws BusinessException {
			try {
				TblUsuarios usuarioTO = getUsuarioTO(usuario);
				usuarioTO.setFechamodificacion(new Date());
				usuarioTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				tblUsuarios.update(usuarioTO, source, accion, accionId);
			}
			catch (Exception e){
				logger.error("ServicioUsuarioImpl - updateUsuario:" + e);
				throw new BusinessException(e,"errors.organismo.updateOrganismo");		
			}	
	
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#loadUsuario(es.mpr.plataformamensajeria.beans.UsuarioBean)
	 */
	////MIGRADO
	@Override
	public UsuarioBean loadUsuario(UsuarioBean usuario) throws BusinessException {
		try {
			TblUsuarios usuarioTO = tblUsuarios.getById(usuario.getUsuarioId());
			return getUsuarioBean(usuarioTO);
		}
		catch (Exception e){
			logger.error("ServicioUsuarioImpl - loadUsuario:" + e);
			throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}	
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#deleteUsuario(es.mpr.plataformamensajeria.beans.UsuarioBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void deleteUsuario(UsuarioBean usuario, String source, String accion, Long accionId) throws BusinessException {
		try {
			tblUsuarios.delete(usuario.getUsuarioId(), source, accion, accionId);
			return;
		}catch (Exception e){
			logger.error("ServicioUsuarioImpl - deleteUsuario:" + e);
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario#getRolIdByUsername(java.lang.String)
	 */
	///MIGRADO
	@Override
	public Integer getRolIdByUsername(String userName) {
		return tblUsuarios.getRolByUsername(userName);
	}
	
	/**
	 * <p>Obtenemos un objeto TblUsuarios a partir de un objeto UsuarioBean</p>.
	 *
	 * @param usuario the usuario
	 * @return objeto TblUsuarios
	 */
	////MIGRADO
	protected TblUsuarios getUsuarioTO(UsuarioBean usuario) {

		TblUsuarios u = new TblUsuarios();

		u.setActivo(usuario.getActivo());
		u.setCreadopor(usuario.getCreadoPor());
		u.setEmail(usuario.getEmail());
		u.setFechacreacion(usuario.getFechaCreacion());
		u.setFechamodificacion(usuario.getFechaModificacion());
		u.setLogin(usuario.getLogin());
		u.setModificadopor(usuario.getModificadoPor());
		u.setNombre(usuario.getNombre());
		u.setRolid(usuario.getRolId());
		u.setUsuarioid((null != usuario.getUsuarioId()) ? usuario.getUsuarioId() : null);

		return u;
	}
	
	/**
	 * <p>Obtenemos un objeto UsuarioBean a partir de un objeto UsuarioJPA</p>.
	 *
	 * @param usuario the usuario
	 * @return objeto UsuarioBean
	 */
	////MIGRADO
	protected UsuarioBean getUsuarioBean(TblUsuarios usuario)
	{
		 
		UsuarioBean u = new UsuarioBean();
		
		u.setActivo(usuario.getActivo());
		u.setCreadoPor(usuario.getCreadopor());
		u.setEmail(usuario.getEmail());
		u.setFechaCreacion((null != usuario.getFechacreacion() ) ? DateUtils.truncate(usuario.getFechacreacion(), Calendar.DATE) : null);
		u.setFechaModificacion((null != usuario.getFechamodificacion() ) ? DateUtils.truncate(usuario.getFechamodificacion(), Calendar.DATE) : null);
		u.setLogin(usuario.getLogin());
		u.setModificadoPor(usuario.getModificadopor());
		u.setNombre(usuario.getNombre());
		u.setRolId(usuario.getRolid());
		u.setUsuarioId((null != usuario.getUsuarioid()) ? usuario.getUsuarioid() : null);
		return u;
	}	
	
	
	/**
	 * Obtener list usuario bean.
	 *
	 * @param lista the lista
	 * @return list usuario bean
	 */
	private List<UsuarioBean> getListUsuarioBean(List<TblUsuarios> lista) {
		List<UsuarioBean> result = new ArrayList<>();
		
		if (lista!=null && !lista.isEmpty()){
			for (TblUsuarios u : lista) {
				result.add(getUsuarioBean(u));
			}
		}
		return result;
	}
}
