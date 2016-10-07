package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.DestinatarioBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.LotesEnviosBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.MensajesAdjuntosBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de historificacion</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioProcesoHistoricos {
	
	Long newServicioProcesoHistoricos(ProcesoHistBean procesoHistBean);
	
	void updateServicioProcesoHistoricos(ProcesoHistBean procesoHistBean);
	
	boolean procesoHistorico(List<LotesEnviosBean> listaLotesEnviosBean, List<MensajeBean> listaMensajesHistBean,
			List<AdjuntoEmailBean> listaAdjuntosEmailBean, List<MensajesAdjuntosBean> listaMensajesAdjuntosHistBean,
			List<DestinatarioBean> listaDestinatariosBean, List<HistoricoBean> listaHistoricoBean, 
			List<GestionEnvioBean> listaGestionEnviosBean) throws BusinessException;
	
	boolean procesoHistoricoLE(LotesEnviosBean loteEnvioBean, List<MensajeBean> listaMensajesHistBean,
			List<AdjuntoEmailBean> listaAdjuntosEmailBean, List<MensajesAdjuntosBean> listaMensajesAdjuntosHistBean,
			List<DestinatarioBean> listaDestinatariosBean, List<HistoricoBean> listaHistoricoBean, 
			List<GestionEnvioBean> listaGestionEnviosBean) throws BusinessException;
	
	boolean procesoHistoricoJPALotesEnvio(LotesEnviosHistoricosJPA loteEnvioHistJPA, List<MensajesHistoricosJPA> listaMensajesHistJPA,
			List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailJPA, List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHistJPA,
			List<DestinatarioHistoricosJPA> listaDestinatariosJPA, List<HistoricoHistJPA> listaHistoricoJPA, 
			List<GestionEnviosHistoricosJPA> listaGestionEnviosJPA, List<DestinatariosMensajesHistoricosJPA> listaDetinatariosMensajesJPA) throws BusinessException;
	
}
