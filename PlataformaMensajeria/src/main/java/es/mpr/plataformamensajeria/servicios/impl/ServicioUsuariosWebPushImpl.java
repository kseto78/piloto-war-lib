package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.manager.TblUsuariosWebPushManager;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush.IPushService;
import es.minhap.sim.model.TblUsuariosWebPush;
import es.mpr.plataformamensajeria.beans.UsuariosWebPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosWebPush;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios movil a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioUsuariosWebPushImpl")
public class ServicioUsuariosWebPushImpl implements ServicioUsuariosWebPush{

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioUsuariosWebPushImpl.class);
	

	/**  push service impl. */
	@Resource(name="pushServiceImpl")
	private IPushService pushServiceImpl;
	
	/**  tbl usuarios web push manager impl. */
	@Resource(name="tblUsuariosWebPushManagerImpl")
	private TblUsuariosWebPushManager tblUsuariosWebPushManagerImpl;
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosWebPush#generarnuevasClaves()
	 */
	@Override
	public String generarnuevasClaves() throws BusinessException {
		try{
			return pushServiceImpl.getNewKeys();
		} catch (Exception e) {
			logger.error("ServicioUsuariosPushImpl - getUsuariosPush:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosWebPush#getUsuariosPush(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.UsuariosWebPushBean, boolean, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public PaginatedList<UsuariosWebPushBean> getUsuariosPush(int start, int size, String order,
			String columnSort, UsuariosWebPushBean criterio, boolean isExport, HttpServletRequest request) throws BusinessException {
		try {
			HashMap<String, String> columns = new HashMap<>();
			
			columns.put("0","usuariowebpushid");
			columns.put("1","usuarioid");
			columns.put("3","servicioid");
			columns.put("4","fechacreacion");
			if (columnSort==null){
				columnSort = "4"; //Fecha
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "fechacreacion";
			}
			if (order == null) {
				order = "2";
			}
			
			es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean upb = new es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean();
			upb = createUsuariosWebPushBean(upb,criterio);
			
			
			List<TblUsuariosWebPush> lista = tblUsuariosWebPushManagerImpl.getUsuariosWebPushPaginado(start, size, order, column, 
					upb, false);
			List<UsuariosWebPushBean> pageList = getListTblUsuariosWebPushBean(lista);
			
			// Total de organismos
			Integer rowcount =  tblUsuariosWebPushManagerImpl.getUsuariosWebPushPaginado(start, size, order, column, 
					upb, true).size();
					
			PaginatedList<UsuariosWebPushBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e) {
			logger.error("ServicioUsuariosPushImpl - getUsuariosPush:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
		
	}


	/**
	 * Creates the usuarios web push bean.
	 *
	 * @param upb the upb
	 * @param criterio the criterio
	 * @return the es.minhap.plataformamensajeria.iop.beans. usuarios web push bean
	 */
	private es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean createUsuariosWebPushBean(
			es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean upb, UsuariosWebPushBean criterio) {

		if (criterio.getUsuarioId() != null && criterio.getUsuarioId() == 0) {
			upb.setUsuarioId(-1);
		} else {
			upb.setUsuarioId(criterio.getUsuarioId());
		}	
		upb.setNombreUsuario(criterio.getNombreUsuario());
		upb.setAplicacionId(criterio.getAplicacionId());
		upb.setServicioId(criterio.getServicioId());
		upb.setFechaDesde(criterio.getFechaDesde());
		upb.setFechaHasta(criterio.getFechaHasta());
				
		return upb;
	}

	
	/**
	 * Obtener list tbl usuarios web push bean.
	 *
	 * @param lista the lista
	 * @return list tbl usuarios web push bean
	 * @throws BusinessException the business exception
	 */
	private List<UsuariosWebPushBean> getListTblUsuariosWebPushBean(List<TblUsuariosWebPush> lista) throws BusinessException{
			List<UsuariosWebPushBean> result = new ArrayList<UsuariosWebPushBean>();
			
			if(lista != null && !lista.isEmpty()){
								
				for(TblUsuariosWebPush upTo : lista){
					UsuariosWebPushBean upBean = new UsuariosWebPushBean();
					upBean.setAplicacion(upTo.getTblServicios().getTblAplicaciones().getNombre());
					upBean.setAplicacionId(upTo.getTblServicios().getTblAplicaciones().getAplicacionid().intValue());
					upBean.setFechaCreacion(upTo.getFechacreacion());
					upBean.setFechaModificacion(upTo.getFechamedificacion());
					upBean.setNombreUsuario(upTo.getUsuarioid());
					upBean.setServicio(upTo.getTblServicios().getNombre());
					upBean.setServicioId(upTo.getTblServicios().getServicioid().intValue());
					upBean.setUsuarioId(upTo.getUsuariowebpushid().intValue());
					
					result.add(upBean);
				}
			}
			return result;
		}
			
	
}
