package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblEstadistitcasCons;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de estadisticas consolidadas</p>.
 *
 * @author jgonzvil
 */

public interface ServicioProcesoConsolidadas {
	
	/**
	 * New servicio proceso consolidadas.
	 *
	 * @param procesoConsBean the proceso cons bean
	 * @return the long
	 */
	Long newServicioProcesoConsolidadas(ProcesoConsBean procesoConsBean);
	
	/**
	 * Proceso consolidacion.
	 *
	 * @param listaEstadisticasConsolidadas the lista estadisticas consolidadas
	 * @param idLote the id lote
	 * @param listaMensajesHistoricosCons the lista mensajes historicos cons
	 * @param listaIdAdjuntosHist the lista id adjuntos hist
	 * @param listaIdMensajesAdjuntosHist the lista id mensajes adjuntos hist
	 * @param listaIdDestinatariosHist the lista id destinatarios hist
	 * @param listaHistoricoHist the lista historico hist
	 * @param listaIdDestinatariosMensajesHist the lista id destinatarios mensajes hist
	 * @param listaGestionEnviosHist the lista gestion envios hist
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean procesoConsolidacion(List<TblEstadistitcasCons> listaEstadisticasConsolidadas, Long idLote,
			List<Long> listaMensajesHistoricosCons, List<Long> listaIdAdjuntosHist,
			List<Long> listaIdMensajesAdjuntosHist, List<Long> listaIdDestinatariosHist, List<Long> listaHistoricoHist,
			List<Long> listaIdDestinatariosMensajesHist, List<TblGestionEnviosHist> listaGestionEnviosHist) throws BusinessException;
	
}
