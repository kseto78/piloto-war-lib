package es.mpr.template.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.BaseJPADao;
import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;

import es.mpr.template.beans.UnidadOrganizacionalBean;
import es.mpr.template.model.configuracion.UnidadOrganizacionalJPA;
import es.mpr.template.servicios.ifaces.ServicioUnidadOrganizacional;

/**
 * 
 * Maneja la persistencia y busqueda de unidades Organizacionales mediante JPA.
 * 
 * @author Altran 
 *
 */
public class ServicioUnidadOrganizacionalImpl implements ServicioUnidadOrganizacional{

	private BaseJPADao jpa;
	
	/**
	 * Devuelve los objetos que cumplan con los criterios del objeto de entrada 
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public List<UnidadOrganizacionalBean> getUnidadesOrganizacionales(UnidadOrganizacionalBean criterio)throws BusinessException {
		
		List<UnidadOrganizacionalJPA> listJPA = null;
		
		try {
			if(criterio== null || criterio.getNombre()== null || criterio.getNombre().equals("")){
				
				UnidadOrganizacionalJPA criterioJPA = new UnidadOrganizacionalJPA();
				criterioJPA.setId(criterio.getId());
				criterioJPA.setLocalidad(criterio.getLocalidad());
				criterioJPA.setNombre(criterio.getNombre());
				criterioJPA.setProvincia(criterio.getProvincia());
				criterioJPA.setComunidad(criterio.getComunidad());
				listJPA= jpa.readAll(0,0,criterioJPA);
				
			}else{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectUnidadOrgz",param);
			}
				
			List<UnidadOrganizacionalBean> result = getListUnidadesBean(listJPA);
			
			return result;
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getUnidadesOrganizacionales");
			
		}
 	}
	
	/**
	 * Crea una Unidad Organizacional 
	 */
	public void newUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional) throws BusinessException{
		
		try {
			UnidadOrganizacionalJPA unidadJPA = getUnidadOrganizacionalJPA(unidadOrganizacional);
			
			jpa.insert(unidadJPA);
			
			unidadOrganizacional.setId(unidadJPA.getId());
			unidadOrganizacional.setNombre(unidadJPA.getNombre());
			unidadOrganizacional.setProvincia(unidadJPA.getProvincia());
			unidadOrganizacional.setComunidad(unidadJPA.getComunidad());
			unidadOrganizacional.setLocalidad(unidadJPA.getLocalidad());
			
			return;
			
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.newUnidadOrganizacional");			
		}
		
	}
	
	/**
	 * Modifica una unidadOrganizacional. Exige que no haya transacciones en curso.
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional) throws BusinessException{
	
		try {
			UnidadOrganizacionalJPA unidadJPA = getUnidadOrganizacionalJPA(unidadOrganizacional);
			
			jpa.update(unidadJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.updateUnidadOrganizacional");		
		}
		
	}
	
	/**
	 * Carga una unidadorganizacional a trav&aacute;s de su Clave Primaria.
	 * 
	 * Exige crear transacci&oacute;n.
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public UnidadOrganizacionalBean loadUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional)throws BusinessException{
		
		try {
			UnidadOrganizacionalJPA unidadJPA = getUnidadOrganizacionalJPA(unidadOrganizacional);
			
			return getUnidadOrganizacionalBean((UnidadOrganizacionalJPA) jpa.read(unidadJPA));
			
		}
		catch (DAOException e){
		
			throw new BusinessException(e);			
		}
	}
	
	/**
	 * Elimina una unidad Organizacional
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional) throws BusinessException{
	
		try {
			UnidadOrganizacionalJPA unidadJPA = getUnidadOrganizacionalJPA(unidadOrganizacional);		
			
			jpa.delete(unidadJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException("errors.organismo.deleteUnidadOrganizacional");			
		}
		
	}

	/**
	 * <p>Convertirmos una lista de UnidadesOrganizacionalJPA a una lista de UnidadesOrganizacionalBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos UnidadesOrganizacionalBean
	 */
	protected List<UnidadOrganizacionalBean> getListUnidadesBean(List<UnidadOrganizacionalJPA> listJPA) throws BusinessException
	{	
		List<UnidadOrganizacionalBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<UnidadOrganizacionalBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				UnidadOrganizacionalJPA unidadJPA = listJPA.get(indice);
				UnidadOrganizacionalBean unidad =  new UnidadOrganizacionalBean();
			
				try {
					BeanUtils.copyProperties(unidad, unidadJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(unidad);
			}
		}
			
		return result;
	}
	
	/**
	 * <p>Obtenemos un objeto UnidadOrganizacionalJPA a partir de un objeto UnidadOrganizacionalBean</p>
	 * 
	 * @param organismoJPA
	 * 
	 * @return objeto OrganismoBean
	 */
	protected UnidadOrganizacionalJPA getUnidadOrganizacionalJPA(UnidadOrganizacionalBean unidad)
	{
		UnidadOrganizacionalJPA unidadJPA = new UnidadOrganizacionalJPA();
		unidadJPA.setId(unidad.getId());
		unidadJPA.setLocalidad(unidad.getLocalidad());
		unidadJPA.setNombre(unidad.getNombre());
		unidadJPA.setProvincia(unidad.getProvincia());
		unidadJPA.setComunidad(unidad.getComunidad());
		
		return unidadJPA;
	}
	
	/**
	 * <p>Obtenemos un objeto UnidadOrganizacionalBean a partir de un objeto UnidadOrganizacionalJPA</p>
	 * 
	 * @param organismoJPA
	 * 
	 * @return objeto OrganismoBean
	 */
	protected UnidadOrganizacionalBean getUnidadOrganizacionalBean(UnidadOrganizacionalJPA unidadJPA) throws BusinessException
	{
		UnidadOrganizacionalBean unidad = new UnidadOrganizacionalBean();
		
		try {
			BeanUtils.copyProperties(unidad, unidadJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return unidad;
	}
	
	/**
	 * 
	 * @return objeto BaseJPADao
	 */
	public BaseJPADao getJpa() {
		return jpa;
	}
	
	/**
	 * Establece la propiedad jpa
	 * 
	 * @param jpa
	 */
	public void setJpa(BaseJPADao jpa) {
		this.jpa = jpa;
	}
	
}
