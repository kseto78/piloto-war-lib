package es.mpr.template.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.template.beans.UnidadOrganizacionalBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de unidades organizacionales</p>
 * 
 * @author Altran
 *
 */
@Service
public interface ServicioUnidadOrganizacional {
	
	List<UnidadOrganizacionalBean> getUnidadesOrganizacionales(UnidadOrganizacionalBean criterio)throws BusinessException;
	
	void newUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional)throws BusinessException;
	
	void updateUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional)throws BusinessException;
	
	UnidadOrganizacionalBean loadUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional)throws BusinessException;
	
	void deleteUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional)throws BusinessException;
}
