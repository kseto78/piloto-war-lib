package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblEstadistitcasCons;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de estadisticas consolidadas</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioProcesoConsolidadas {
	
	Long newServicioProcesoConsolidadas(ProcesoConsBean procesoConsBean);
	
	boolean procesoConsolidacion(List<TblEstadistitcasCons> listaEstadisticasConsolidadas, Long idLote,
			List<Long> listaMensajesHistoricosCons, List<Long> listaIdAdjuntosHist,
			List<Long> listaIdMensajesAdjuntosHist, List<Long> listaIdDestinatariosHist, List<Long> listaHistoricoHist,
			List<Long> listaIdDestinatariosMensajesHist, List<TblGestionEnviosHist> listaGestionEnviosHist) throws BusinessException;
	
}
