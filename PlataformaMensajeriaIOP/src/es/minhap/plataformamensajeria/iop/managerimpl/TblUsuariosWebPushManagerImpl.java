package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosWebPushManager;
import es.minhap.sim.dao.TblUsuariosWebPushDAO;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosWebPush;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosWebPushQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblUsuariosWebPushManagerImpl")
public class TblUsuariosWebPushManagerImpl implements TblUsuariosWebPushManager {
	protected static final String R_CONST_1 = "TblUsuariosWebPushManager.eliminarUsuario";

	static Logger logger = LoggerFactory.getLogger(TblUsuariosWebPushManagerImpl.class);
	
	@Resource
	private TblUsuariosWebPushDAO tblUsuariosWebPushDAO;
	
	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource
	private TblServiciosManager serviciosManager;

	@Override
	@Transactional
	public boolean eliminarUsuario(TblUsuariosWebPushQuery query) {
		Boolean res = false;
		try{
			TblUsuariosWebPush usuario = getUsuarioPushByQuery(query);
			tblUsuariosWebPushDAO.delete(usuario.getUsuariowebpushid());
			res = true;
		}catch(Exception e){
			// TODO logger.warn(e.getMessage(), e);
			logger.error(R_CONST_1);
		}
		return res;
	}

	@Override
	@Transactional
	public boolean insertUsuario(TblUsuariosWebPush usuario) {
		Boolean res = false;
		try{
			tblUsuariosWebPushDAO.insert(usuario);
			res = true;
		}catch(Exception e){
			// TODO logger.warn(e.getMessage(), e);
			logger.error(R_CONST_1);
		}
		return res;
	}

	@Override
	public TblUsuariosWebPush getUsuarioPushByQuery(TblUsuariosWebPushQuery query) {
		return (null != tblUsuariosWebPushDAO.search(query) && !tblUsuariosWebPushDAO.search(query).getResults().isEmpty())? tblUsuariosWebPushDAO.search(query).getResults().get(0) : null;
	}

	@Override
	public List<TblUsuariosWebPush> getListUsuarioPushByQuery(TblUsuariosWebPushQuery query) {
		return tblUsuariosWebPushDAO.search(query).getResults();
	}
	
	@Override
	@Transactional
	public boolean comprobarDispositivoRepetido(String codigo) {
		TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
		query.setUsuarioid(codigo);
		return tblUsuariosWebPushDAO.count(query) <= 0;
	}
	
	@Override
	@Transactional
	public List<Long> getUsuariosPorServicio(String servicio){
		TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
		query.setEliminadoIsNull(true);
		
		TblServiciosQuery queryServicios = new TblServiciosQuery();
		queryServicios.setServicioid(Long.parseLong(servicio));
		
		
		query.setTblServicios(queryServicios);
		
		return tblUsuariosWebPushDAO.searchId(query).getResults();

	}

	@Override
	@Transactional
	public List<Long> getDispositivosUsuarioServicio(String identificadorUsuario, String servicio) {
		TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
		
		query.setUsuarioid(identificadorUsuario);
		query.setUsuarioidComparator(TextComparator.EQUALS);
		query.setEliminadoIsNull(true);
		TblServiciosQuery queryServicios = new TblServiciosQuery();
		queryServicios.setServicioid(Long.parseLong(servicio));
		
		query.setTblServicios(queryServicios);
		
		return tblUsuariosWebPushDAO.searchId(query).getResults();
	}

	@Override
	@Transactional
	public void establecerUsuarioEliminado(TblUsuariosWebPushQuery query) {
		List<TblUsuariosWebPush> listaUsuarios = tblUsuariosWebPushDAO.search(query).getResults();
		for (TblUsuariosWebPush u : listaUsuarios) {
			u.setEliminado("S");
			u.setFechamedificacion(new Date());
			tblUsuariosWebPushDAO.update(u);
		}
		
	}

	@Override
	@Transactional
	public void updateUsuario(TblUsuariosWebPush uwp) {
		tblUsuariosWebPushDAO.update(uwp);
		
	}

	@Override
	public List<String> getUsuarioIdMensaje(long mensajeId) {
		List<String> res = new ArrayList<>();
		List<TblDestinatariosMensajes> lista = destinatariosMensajesManager.getDestinatarioMensajes(mensajeId);
				
		for (TblDestinatariosMensajes dm : lista) {
			TblUsuariosWebPush us = tblUsuariosWebPushDAO.get(Long.parseLong(dm.getDestinatario()));
			if (null != us){
				res.add(us.getUsuarioid());
			}
		}
		
		
		return res;
	}
	
@Override
	public List<TblUsuariosWebPush> getUsuariosWebPushPaginado(int start, int size, String order, String columnSort,
			UsuariosWebPushBean criterio, boolean count) {
	OrderType ord = null;
		
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)){
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}
		
		TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
		TblServiciosQuery queryServicio = null;
		TblAplicacionesQuery queryAplicacion = null;
				
		if(criterio != null){
			if(criterio.getUsuarioId() != null && criterio.getUsuarioId() != 0){
				query.setUsuariowebpushid(Long.parseLong(criterio.getUsuarioId().toString()));
			}
			if(criterio.getNombreUsuario() != null && !criterio.getNombreUsuario().isEmpty()){
				query.setUsuarioid(criterio.getNombreUsuario());
			}
			if(criterio.getFechaDesde() != null){
				query.setFechaMin(criterio.getFechaDesde());
			}
			if(criterio.getFechaHasta() != null){
				query.setFechaMax(criterio.getFechaHasta());
			}
			if(criterio.getAplicacionId() != null && criterio.getAplicacionId() != 0){
				queryServicio = new TblServiciosQuery();
				queryAplicacion = new TblAplicacionesQuery();
				queryAplicacion.setAplicacionid(criterio.getAplicacionId().longValue());
				queryServicio.setTblAplicaciones(queryAplicacion);
			}
			else{
				if(criterio.getListaIdAplicaciones() != null){
					String[]  listaId = criterio.getListaIdAplicaciones().split(",");
					TblServiciosQuery queryServ = new TblServiciosQuery();
					queryAplicacion = new TblAplicacionesQuery();
					for(String ids : listaId){						
						queryAplicacion.addAplicacionidIn(Long.valueOf(ids));
					}
					queryServ.setTblAplicaciones(queryAplicacion);
					List<TblServicios> listaServi = serviciosManager.getServicios(queryServ);
					for(TblServicios tblServi : listaServi){
						query.addTblServiciosIdIn(tblServi);
					}					
				}
			}
			if(criterio.getServicioId() != null  && criterio.getServicioId() != 0){
				if (null == queryServicio){
					queryServicio = new TblServiciosQuery();
				}
				queryServicio.setServicioid(criterio.getServicioId().longValue());
			}
			query.setTblServicios(queryServicio);
		}
		
		if (columnSort.contains("servicio")){
			query.addOrder("tblServicios." + columnSort, ord);
		}else{
			query.addOrder(columnSort, ord);
		}
		
		if (!count){
			query.setPrimerResultado(start);
			query.setMaxResultados(size);
		}
		List<TblUsuariosWebPush> res = tblUsuariosWebPushDAO.search(query).getResults();
		
		for (TblUsuariosWebPush uwp : res) {
			Hibernate.initialize(uwp.getTblServicios());
			Hibernate.initialize(uwp.getTblServicios().getTblAplicaciones());
		}
		
		
		return tblUsuariosWebPushDAO.search(query).getResults();
	}
	
}

