package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.DestinatarioHistoricosBean;
import es.mpr.plataformamensajeria.beans.EstadisticasConsolidadasBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.LotesEnviosHistoricosBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.beans.MensajesAdjuntosHistoricosBean;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.EstadisticasConsolidadasJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de estadisticas consolidadas</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioProcesoConsolidadas {
	
	Long newServicioProcesoConsolidadas(ProcesoConsBean procesoConsBean);
	
	boolean procesoCons(List<EstadisticasConsolidadasBean> listaEstadisticasConsolidadasBean ,List<LotesEnviosHistoricosBean> listaLotesEnviosHistBean, 
			List<MensajeHistoricosBean> listaMensajesHistoricosBean, List<AdjuntoEmailHistoricosBean> listaAdjuntosEmailHistBean, 
			List<MensajesAdjuntosHistoricosBean> listaMensajesAdjuntosHistBean, List<DestinatarioHistoricosBean> listaDestinatariosHistBean, 
			List<HistoricoHistBean> listaHistoricoHistBean, List<GestionEnvioHistoricoBean> listaGestionEnviosHistBean) throws BusinessException;
	
	boolean procesoConsJPA(List<EstadisticasConsolidadasJPA> listaEstadisticasConsolidadasJPA , List<LotesEnviosHistoricosJPA> listaLotesEnviosHistJPA, 
			List<MensajesHistoricosJPA> listaMensajesHistoricosJPA, List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailHistJPA, 
			List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHistJPA, List<DestinatarioHistoricosJPA> listaDestinatariosHistJPA, 
			List<HistoricoHistJPA> listaHistoricoHistJPA, List<GestionEnviosHistoricosJPA> listaGestionEnviosHistJPA) throws BusinessException;
	
}
