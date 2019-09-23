package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>.
 *
 * @author Selered
 */
public interface ServicioProcesosManuales {
	

	PaginatedList<ProcesosManualesBean> getProcesosManuales(int inicio, int size, String order,
			String columnSort, ProcesosManualesBean criterio) throws BusinessException;

	void updateProcesoManual(ProcesosManualesBean procesoManual, String source, String accion,
			Long accionId) throws BusinessException;
	
	
	void deleteProcesoManual(Long procesoManualId, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load proceso manual.
	 *
	 * @param procesoManual the procesoManual
	 * @return the procesosManualesBean
	 * @throws BusinessException the business exception
	 */
	ProcesosManualesBean loadProcesoManual(ProcesosManualesBean procesoManual)throws BusinessException;
	
	Integer newProcesoManual(ProcesosManualesBean procesoManual, String source, String accion, Long accionId)throws BusinessException;

}

