package es.mpr.template.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.auditoria.Constants;
import com.map.j2ee.auditoria.annotation.Auditable;
import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

import es.mpr.template.beans.OrganismoBeanOLD;
import es.mpr.template.model.configuracion.OrganismoJPAOLD;
import es.mpr.template.servicios.ifaces.ServicioOrganismoOLD;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de organismos a traves de JPA.
 * 
 * @author Altran
 * 
 */
public class ServicioOrganismoImplOLD implements ServicioOrganismoOLD{

	private IPaginationJPADAO jpa;
		
	/**
	 * Devuelve los organismos que cumplan los criterios contenidos en el objeto
	 * de entrada
	 * 
	 * @param criterio Criterio de b&uacute;squeda de organismos
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public List<OrganismoBeanOLD> getOrganismos(OrganismoBeanOLD criterio)throws BusinessException{
		List<OrganismoJPAOLD> listJPA = null;
		
		try {
			if(criterio== null || criterio.getNombre()== null || criterio.getNombre().equals("")){
							
				OrganismoJPAOLD criterioJPA = new OrganismoJPAOLD();
				criterioJPA.setId(criterio.getId());
				criterioJPA.setNombre(criterio.getNombre());
				criterioJPA.setOrganismoPadre(criterio.getOrganismoPadre());
				criterioJPA.setRol(criterio.getRol());
				
				listJPA= jpa.readAll(0,0,criterioJPA);
			}else{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				listJPA= jpa.executeQuery("selectOrganismoJPA",param);
			}
			
			List<OrganismoBeanOLD> result = getListOrganismoBean(listJPA);					
			
			return result;
			
		} 
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	
	/**
	 * <p>Devuelve los organismos que cumplan los criterios contenidos en el objeto
	 * de entrada.
	 * 
	 * <p>
	 * Solamente devuelve aquellos organismos que deben mostrarse al usuario 
	 * mediante paginaci&oacute;n.
	 * 
	 * @param start Inicio
	 * @param size N&uacute;mero de elementos a mostrar
	 * @param order Ordenaci&oacute;n ascendente (ASC) o descendente (DESC)
	 * @param sort Campo o columna usado para ordenar
	 * @param criterio Criterio de b&uacute;squeda
	 * 
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public PaginatedList<OrganismoBeanOLD> getOrganismos(int start, int size, String order, String columnSort,OrganismoBeanOLD criterio)
		throws BusinessException
	{
		List<OrganismoJPAOLD> listJPA = null;
		
		try {
			
			StringBuffer namedQuery = null;
			
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("0","Id");
			columns.put("1","Nombre");
			columns.put("2","Rol");
			columns.put("3","Padre");
			
			if (columnSort==null)
				columnSort = "1"; //Id
			
			String column = columns.get(columnSort);
			if (column==null)
				column = "Id";
			
			String[] param = {};
			
			
			//Determinamos la NamedQuery
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals("")){
								
				//Sin criterio			
				namedQuery = new StringBuffer("selectOrganismo_orderby");
								
			}else{
				param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				namedQuery = new StringBuffer("selectOrganismoByName_orderby");
			}
			
			namedQuery.append(column);
			
			//Orden ascendente o descendente
			if (order==null || order.equals("1")) //ASC
			{
				namedQuery.append("_ASC"); 
			}
			else if (order!=null && order.equals("2")) //DESC 
			{
				namedQuery.append("_DESC");
			}
			
			//Lista parcial
			listJPA = jpa.executeQuery(namedQuery.toString(), param, start, size);
			
			List<OrganismoBeanOLD> pageList = getListOrganismoBean(listJPA);
			
			//Total de organismos
			Integer rowcount = getTotalOrganismos(criterio); 
			
			PaginatedList<OrganismoBeanOLD> result = new PaginatedList<OrganismoBeanOLD>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result ;
			
		}
		catch (DAOException e){
			
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}
	
	/**
	 * <p>N&uacute;mero total de organismos seg&uacute;n los criterios de 
	 * b&uacute;squeda seleccionados.
	 * 
	 * @param criterio Criterio de b&uacute;squeda
	 * 
	 * @return N&uacute;mero total de organismos
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public Integer getTotalOrganismos(OrganismoBeanOLD criterio) throws BusinessException
	{
		try
		{
			if(criterio==null || criterio.getNombre()== null || criterio.getNombre().equals(""))
			{
				String[] param = {};
				
				Integer rowcount = jpa.getTotalSize("selectOrganismo_count",param);
				
				return rowcount;
			}
			else
			{
				String[] param = new String[]{"%"+criterio.getNombre().toUpperCase()+"%"};
				
				Integer rowcount = jpa.getTotalSize("selectOrganismoByName_count",param);
				
				return rowcount;
			}
			
		}
		catch (DAOException e){
			
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}
	
	/**
	 * Crea un nuevo organismo, y audita el proceso.
	 * 
	 * @param organismo
	 */
	@Auditable(operacion=Constants.AUDITORIA_OPERACION_ALTA)
	public void newOrganismo(OrganismoBeanOLD organismo)throws BusinessException{
		
		try {
			OrganismoJPAOLD organismoJPA = getOrganismoJPA(organismo);
			
			jpa.insert(organismoJPA);
			
			organismo.setId(organismoJPA.getId());
			organismo.setNombre(organismoJPA.getNombre());
			organismo.setOrganismoPadre(organismoJPA.getOrganismoPadre());
			organismo.setRol(organismoJPA.getRol());
			
			return;
			
		}
		catch (DAOException e){
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
			
		}
		
	}
	
	/**
	 * Modifica un organismo
	 * 
	 * @param organismo
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrganismo(OrganismoBeanOLD organismo)throws BusinessException{
	
		try {
			OrganismoJPAOLD organismoJPA = getOrganismoJPA(organismo);
				
			jpa.update(organismoJPA);
			return;	
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
	}
	
	/**
	 * Carga un organismo por su clave primaria
	 * 
	 * @param organismo
	 */
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public OrganismoBeanOLD loadOrganismo(OrganismoBeanOLD organismo)throws BusinessException{
		
		try {
			OrganismoJPAOLD organismoJPA = getOrganismoJPA(organismo);
			
			return getOrganismoBean((OrganismoJPAOLD) jpa.read(organismoJPA));
		}
		catch (DAOException e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
	
	/**
	 * <p>Elimina un organismo y audita el proceso. 
	 * 
	 * <p>Requiere que no haya ninguna transacci&oacute;n en curso
	 * 
	 * @param organismo
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Auditable(operacion=Constants.AUDITORIA_OPERACION_BAJA)
	public void deleteOrganismo(OrganismoBeanOLD organismo)throws BusinessException{
	
		try {
			OrganismoJPAOLD organismoJPA = getOrganismoJPA(organismo);		
			
			jpa.delete(organismoJPA);
			return;
			
		}
		catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/**
	 * <p>Convertirmos una lista de OrganismoJPA a una lista de OrganismoBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<OrganismoBeanOLD> getListOrganismoBean(List<OrganismoJPAOLD> listJPA) throws BusinessException
	{	
		List<OrganismoBeanOLD> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<OrganismoBeanOLD>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				OrganismoJPAOLD organismoJPA = listJPA.get(indice);
				OrganismoBeanOLD organismo =  new OrganismoBeanOLD();
			
				try {
					BeanUtils.copyProperties(organismo, organismoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(organismo);
			}
		}
			
		return result;
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	protected OrganismoJPAOLD getOrganismoJPA(OrganismoBeanOLD organismo) throws BusinessException
	{
		OrganismoJPAOLD organismoJPA = new OrganismoJPAOLD();
		organismoJPA.setId(organismo.getId());
		organismoJPA.setNombre(organismo.getNombre());
		organismoJPA.setOrganismoPadre(organismo.getOrganismoPadre());
		organismoJPA.setRol(organismo.getRol());
				
		return organismoJPA;
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoBean a partir de un objeto OrganismoJPA</p>
	 * 
	 * @param organismoJPA
	 * 
	 * @return objeto OrganismoBean
	 */
	protected OrganismoBeanOLD getOrganismoBean(OrganismoJPAOLD organismoJPA) throws BusinessException
	{
		OrganismoBeanOLD organismo = new OrganismoBeanOLD();
		
		try {
			BeanUtils.copyProperties(organismo, organismoJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return organismo;
	}
	
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

}
