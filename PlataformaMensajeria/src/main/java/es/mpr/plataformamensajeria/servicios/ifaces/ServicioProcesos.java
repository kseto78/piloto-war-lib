package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>.
 *
 * @author Selered
 */
public interface ServicioProcesos {
	
	/**
	 * Obtener organismos.
	 *
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<ProcesosBean> getAllProcesos() throws BusinessException;
	
		
	/**
	 * Update proceso.
	 *
	 * @param proceso the organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateProceso(ProcesosBean proceso, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load procesos.
	 *
	 * @param procesos the proceso
	 * @return the procesos bean
	 * @throws BusinessException the business exception
	 */
	ProcesosBean loadProceso(ProcesosBean procesos)throws BusinessException;
	

	/**
	 * List autocomplete.
	 *
	 * @param term the term
	 * @return the list
	 */
	List<String> listAutocomplete(String term);



	PaginatedList<ProcesosBean> getProcesos(int size, String order,
			String columnSort, ProcesosBean criterio) throws BusinessException;


	ProcesosBean loadProcesoNombreClase(ProcesosBean proceso)
			throws BusinessException;

}

