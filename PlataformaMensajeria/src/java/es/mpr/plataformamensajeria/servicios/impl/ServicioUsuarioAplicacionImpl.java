package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;
import com.map.j2ee.util.beanutils.converters.IntegerConverter;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.model.AplicacionJPA;
import es.mpr.plataformamensajeria.model.UsuarioAplicacionJPA;
import es.mpr.plataformamensajeria.model.views.ViewParametroServidorJPA;
import es.mpr.plataformamensajeria.model.views.ViewUsuarioAplicacionJPA;
import es.mpr.plataformamensajeria.model.views.ViewUsuarioJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de usuarios a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioUsuarioAplicacionImpl implements ServicioUsuarioAplicacion{

	private IPaginationJPADAO jpa;

	
	/**
	/**
	 * 
	 * @return Objeto BaseJPADao
	 */
	public IPaginationJPADAO getJpa() {
		return jpa;
	}

	/**
	 * Establece la propiedad jpa
	 * 
	 * @param jpa
	 */
	public void setJpa(IPaginationJPADAO jpa) {
		this.jpa = jpa;
	}

	

	
	/**
	 * <p>Convertirmos una lista de ViewUsuarioJPA a una lista de UsuarioBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuarioBean
	 */
	protected List<UsuarioBean> getListViewUsuarioBean(List<ViewUsuarioJPA> listJPA) throws BusinessException
	{	
		List<UsuarioBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UsuarioBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuarioJPA usuarioJPA = listJPA.get(indice);
				UsuarioBean usuario =  new UsuarioBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(usuario, usuarioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(usuario);
			}
		}
			
		return result;
	}
	/**
	 * <p>Convertirmos una lista de ViewUsuarioAplicacionJPA a una lista de UsuarioAplicacionBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UsuarioBean
	 */
	protected List<UsuarioAplicacionBean> getListViewUsuarioAplicacionBean(List<ViewUsuarioAplicacionJPA> listJPA) throws BusinessException
	{	
		List<UsuarioAplicacionBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty()&&listJPA.size()>0)
		{
			result = new ArrayList<UsuarioAplicacionBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewUsuarioAplicacionJPA usuarioJPA = listJPA.get(indice);
				UsuarioAplicacionBean usuario =  new UsuarioAplicacionBean();
				if(usuarioJPA!=null){
					try {
						Date defaultValue = null;         
						DateConverter converter = new DateConverter (defaultValue);         
						ConvertUtils.register (converter, java.util.Date.class);
						BeanUtils.copyProperties(usuario, usuarioJPA);
					} catch (IllegalAccessException e) {
						throw new BusinessException(e);
					} catch (InvocationTargetException e) {
						throw new BusinessException(e);
					}
					result.add(usuario);
				}
			}
		}
			
		return result;
	}	

	

	@Override
	@Transactional
	public Integer newUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion) throws BusinessException {
		try{
			UsuarioAplicacionJPA usuarioAplicacionJPA = getUsuarioAplicacionJPA(usuarioAplicacion);
			usuarioAplicacionJPA.setFechaCreacion(new Date());
			//TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			usuarioAplicacionJPA.setCreadoPor(usuarioLogueado.getNombre()+ " " + usuarioLogueado.getApellidos());
			jpa.insert(usuarioAplicacionJPA);
			
			usuarioAplicacion.setUsuarioAplicacionId((Integer)usuarioAplicacionJPA.getId());
			usuarioAplicacion.setFechaCreacion(usuarioAplicacionJPA.getFechaCreacion());
			usuarioAplicacion.setCreadoPor(usuarioAplicacionJPA.getCreadoPor());
			usuarioAplicacion.setModo(usuarioAplicacionJPA.getModo());
			usuarioAplicacion.setUsuarioId((Integer) usuarioAplicacionJPA.getUsuarioId());
			return usuarioAplicacion.getUsuarioAplicacionId();
		}catch (DAOException e){
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
	protected UsuarioAplicacionJPA getUsuarioAplicacionJPA(UsuarioAplicacionBean usuarioAplicacion)
	{
		 
		UsuarioAplicacionJPA usuarioAplicacionJPA = new UsuarioAplicacionJPA();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(usuarioAplicacionJPA,usuarioAplicacion);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return usuarioAplicacionJPA;
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
	protected UsuarioAplicacionBean getUsuarioAplicacionBean(UsuarioAplicacionJPA usuario)
	{
		 
		UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(usuarioAplicacionBean,usuario);
		} catch (IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return usuarioAplicacionBean;
	}	


	@Override
	public List<UsuarioAplicacionBean> getUsuarioAplicacionesByUsuarioId(Integer usuarioId)
			throws BusinessException {
//"selectViewUsuarioAplicacionByUsuarioId"
		List<ViewUsuarioAplicacionJPA> listJPA = null;
		String[] params = new String[]{usuarioId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewUsuarioAplicacionByUsuarioId", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UsuarioAplicacionBean> result = getListViewUsuarioAplicacionBean(listJPA);		
		return result;
		
	}
	@Override
	public List<UsuarioAplicacionBean> getUsuarioAplicacionesByAplicacionId(Integer aplicacionId)
			throws BusinessException {
//"selectViewUsuarioAplicacionByUsuarioId"
		List<ViewUsuarioAplicacionJPA> listJPA = null;
		String[] params = new String[]{aplicacionId.toString()};
		try {
			listJPA= jpa.executeQuery("selectViewUsuarioAplicacionByAplicacionId", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UsuarioAplicacionBean> result = getListViewUsuarioAplicacionBean(listJPA);		
		return result;
		
	}
	@Override
	//@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public UsuarioAplicacionJPA loadUsuarioAplicacionJPA(UsuarioAplicacionJPA usuarioAplicacion)	throws BusinessException {
		try {
	
			
			return (UsuarioAplicacionJPA) jpa.read(usuarioAplicacion);
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	//@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public UsuarioAplicacionBean loadUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)	throws BusinessException {
		try {
			UsuarioAplicacionJPA usuarioAplicacionJPA = getUsuarioAplicacionJPA(usuarioAplicacionBean);
			
			return getUsuarioAplicacionBean((UsuarioAplicacionJPA) jpa.read(usuarioAplicacionJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	@Override
	@Transactional
	public void deleteUsuarioAplicacion(
			UsuarioAplicacionBean usuarioAplicacionBean)
			throws BusinessException {
	try{
		UsuarioAplicacionBean usuarioAplicacionBBDD = loadUsuarioAplicacion(usuarioAplicacionBean);
		UsuarioAplicacionJPA usuarioAplicacionJPA = getUsuarioAplicacionJPA(usuarioAplicacionBBDD);		
		/*UsuarioAplicacionJPA delete = new UsuarioAplicacionJPA();
		delete.setUsuarioAplicacionId(usuarioAplicacionJPA.getAplicacionId());*/
		jpa.delete(usuarioAplicacionJPA);
		return;
		
	}
	catch (DAOException e){
		throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
	}
		
	}
}
