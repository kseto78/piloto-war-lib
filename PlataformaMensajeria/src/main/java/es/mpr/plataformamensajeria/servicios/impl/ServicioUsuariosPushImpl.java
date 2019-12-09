package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosPushManager;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.model.ViewUsuariosPush;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;


/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios movil a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioUsuariosPushImpl")
public class ServicioUsuariosPushImpl implements ServicioUsuariosPush{

	protected static final String FECHA = "fecha";

	protected static final String R_CONST_REF = "2";

	protected static final String R_CONST_0 = "5";

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioParametroServidorImpl.class);
	
	/**  view usuarios push manager. */
	@Resource(name="ViewUsuariosPushManager")
	private ViewUsuariosPushManager viewUsuariosPushManager;
	
	/**  tbl usuarios aplicaciones manager. */
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosAplicacionesManager;
	
	/**  map permisos usuario aplicacion. */
	static HashMap<Integer,Integer> mapPermisosUsuarioAplicacion = null;
	
	/**  rol usuario. */
	static String rolUsuario = null;
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosPush#getUsuariosPush(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.UsuariosPushBean, boolean, javax.servlet.http.HttpServletRequest)
	 */
	///MIGRADO
	@Override
	public PaginatedList<UsuariosPushBean> getUsuariosPush(int start, int size, String order,
			String columnSort, UsuariosPushBean criterio, boolean isExport, HttpServletRequest request) throws BusinessException {
		try {
			HashMap<String, String> columns = new HashMap<>();
			
			columns.put("0","usuarioid");
			columns.put("1","nombreusuario");
			columns.put(R_CONST_REF,"aplicacion");
			columns.put("3","servicio");
			columns.put("4","plataforma");
			columns.put(R_CONST_0,FECHA);
			if (columnSort==null){
				columnSort = R_CONST_0; 
				//Fecha
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = FECHA;
			}
			if (order == null) {
				order = R_CONST_REF;
			}
			
			es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean upb = new es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean();
			
			if(upb != null){
				upb = createUsuariosPushBean(upb,criterio);
			}
			
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
	    	Integer userName = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
	    	if (rolUsuario != null && PlataformaMensajeriaUtil.ROL_PROPIETARIO.equals(rolUsuario)){
	    		TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
				TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
				queryUsuarios.setUsuarioid((null != userName)? userName.longValue() : null);
				queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
				List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosAplicacionesManager.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);
				if(listaUsuarioAplicaciones != null){
		
					boolean first = true;
					String listaIdAplicaciones = "";
					for(TblUsuariosAplicaciones apl : listaUsuarioAplicaciones){
						if (!first) {
							listaIdAplicaciones += ",";
						}
							listaIdAplicaciones += apl.getAplicacionid();
							first = false;
						}
					upb.setListaIdAplicaciones(listaIdAplicaciones);
					}				
				}			
			
			List<ViewUsuariosPush> lista = viewUsuariosPushManager.getUsuariosPushPaginado(start, size, order, column, 
					upb, false);
			List<UsuariosPushBean> pageList = getListViewUsuariosPushBean(lista);
			
			// Total de organismos
			Integer rowcount = viewUsuariosPushManager.getUsuariosPushPaginado(start, size, order, column, 
					upb, true).size();
					
			PaginatedList<UsuariosPushBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e) {
			logger.error("ServicioUsuariosPushImpl - getUsuariosPush:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}
	
	/**
	 * <p>Convertirmos una lista de usuariosPush a una lista de AuditoriasBean</p>.
	 *
	 * @param upb the upb
	 * @param criterio the criterio
	 * @return Lista de objetos UsuariosPushBean
	 */
	
	///MIGRADO
	private es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean createUsuariosPushBean(
			es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean upb, UsuariosPushBean criterio) {

		upb.setUsuarioId(criterio.getUsuarioId());
		upb.setAplicacionId(criterio.getAplicacionId());
		upb.setServicioId(criterio.getServicioId());
		upb.setPlataformaId(criterio.getPlataformaId());
		upb.setFechaDesde(criterio.getFechaDesde());
		upb.setFechaHasta(criterio.getFechaHasta());
		upb.setPlataforma(criterio.getPlataforma());
		
		return upb;
	}
	
	/**
	 * Obtener list view usuarios push bean.
	 *
	 * @param lista the lista
	 * @return list view usuarios push bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private List<UsuariosPushBean> getListViewUsuariosPushBean(List<ViewUsuariosPush> lista) throws BusinessException{
		List<UsuariosPushBean> result = null;
		
		if(lista != null && !lista.isEmpty()){
			result = new ArrayList<>();
			
			for(ViewUsuariosPush vup : lista){
				UsuariosPushBean usuariosPush = new UsuariosPushBean();
				
				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter (defaultValue);
					ConvertUtils.register (converter, java.util.Date.class);
					
					Integer defaultIntegerValue=null;
					IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(intergerConverter, java.lang.Integer.class);
					BeanUtils.copyProperties(usuariosPush, vup);
					
					usuariosPush.setNombreUsuario(vup.getNombreusuario());
					usuariosPush.setUsuarioId(vup.getUsuarioid().intValue());
					
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioUsuariosPushImpl - getListViewUsuariosPushBean:" + e);
					throw new BusinessException(e);
				}
				
				result.add(usuariosPush);
			}
		}
		
		return result;
	}
}
