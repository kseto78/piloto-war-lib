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

import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosPushManager;
import es.minhap.sim.model.ViewUsuariosPush;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosPush;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios movil a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioUsuariosPushImpl")
public class ServicioUsuariosPushImpl implements ServicioUsuariosPush{

	private static Logger logger = Logger.getLogger(ServicioParametroServidorImpl.class);
	
	@Resource(name="ViewUsuariosPushManager")
	private ViewUsuariosPushManager viewUsuariosPushManager;
	
	static HashMap<Integer,Integer> mapPermisosUsuarioAplicacion = null;
	static String rolUsuario = null;
	
	///MIGRADO
	@Override
	public PaginatedList<UsuariosPushBean> getUsuariosPush(int start, int size, String order,
			String columnSort, UsuariosPushBean criterio, boolean isExport, HttpServletRequest request) throws BusinessException {
		try {
			HashMap<String, String> columns = new HashMap<>();
			
			columns.put("0","usuarioid");
			columns.put("1","nombreusuario");
			columns.put("2","aplicacion");
			columns.put("3","servicio");
			columns.put("4","plataforma");
			columns.put("5","fecha");
			if (columnSort==null){
				columnSort = "5"; //Fecha
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "fecha";
			}
			if (order == null) {
				order = "2";
			}
			
			es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean upb = new es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean();
			
			if(upb != null){
				upb = createUsuariosPushBean(upb,criterio);
			}
			
			List<ViewUsuariosPush> lista = viewUsuariosPushManager.getUsuariosPushPaginado(start, size, order, column, 
					upb, false);
			List<UsuariosPushBean> pageList = getListViewUsuariosPushBean(lista);
			
			// Total de organismos
			Integer rowcount = viewUsuariosPushManager.getUsuariosPushPaginado(start, size, order, column, 
					upb, true).size();
					
			PaginatedList<UsuariosPushBean> result = new PaginatedList<UsuariosPushBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e) {
			logger.error("ServicioUsuariosPushImpl - getUsuariosPush:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}
	
	/**
	 * <p>Convertirmos una lista de usuariosPush a una lista de AuditoriasBean</p>
	 * 
	 * @param listJPA
	 * 
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
	
	///MIGRADO
	private List<UsuariosPushBean> getListViewUsuariosPushBean(List<ViewUsuariosPush> lista) throws BusinessException{
		List<UsuariosPushBean> result = null;
		
		if(lista != null && !lista.isEmpty()){
			result = new ArrayList<UsuariosPushBean>();
			
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
