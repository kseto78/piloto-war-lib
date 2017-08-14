package es.mpr.plataformamensajeria.servicios.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadisticasConsManager;
import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblProcesoConsManager;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblEstadistitcasCons;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblProcesoCons;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoConsolidadas;
import es.mpr.plataformamensajeria.util.UtilCreateFile;

/**
 * <p>Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioProcesoConsolidadasImpl")
public class ServicioProcesoConsolidadasImpl implements ServicioProcesoConsolidadas{
	
	private static Logger logger = Logger.getLogger(ServicioProcesoConsolidadasImpl.class);
	
	@Resource(name = "TblProcesoConsManagerImpl")
	private TblProcesoConsManager tblProcesoConsManager;
	
	@Resource(name = "TblGestionEnviosHistManagerImpl")
	private TblGestionEnviosHistManager tblGestionEnviosHistManager;
	
	@Resource(name = "TblHistoricosHistManagerImpl")
	private TblHistoricosHistManager tblHistoricosHistManager;
	
	@Resource(name = "TblDestinatariosHistManagerImpl")
	private TblDestinatariosHistManager tblDestinatariosHistManager;
	
	@Resource(name = "TblDestinatariosMensHistManagerImpl")
	private TblDestinatariosMensajesHistManager tblDestinatariosMensajesHistManager;
	
	@Resource(name = "TblMensajesAdjuntosHistManagerImpl")
	private TblMensajesAdjuntosHistManager tblMensajesAdjuntosHistManager;
	
	@Resource(name = "TblEstadisticasConsManagerImpl")
	private TblEstadisticasConsManager tblEstadisticasConsManager;
	
	@Resource(name = "TblAdjuntosHistManagerImpl")
	private TblAdjuntosHistManager tblAdjuntosHistManager;
	
	@Resource(name ="TblMensajesHistManagerImpl")
	private TblMensajesHistManager tblMensajesHistManager;
	

	////MIGRADO
	@Override
	@Transactional
	public Long newServicioProcesoConsolidadas(ProcesoConsBean procesoConsBean) {
		try{
			TblProcesoCons procesoCons = new TblProcesoCons();
			procesoCons.setCodigoestado(procesoConsBean.getCodigoEstado());
			procesoCons.setDescripcionestado(procesoConsBean.getDescripcionEstado());
			procesoCons.setFechainicio(procesoConsBean.getFechaInicio());
			procesoCons.setFechafin(procesoConsBean.getFechaFin());
			procesoCons.setFechacreacion(new Date());
						
			return tblProcesoConsManager.insertar(procesoCons);
		}catch (Exception e){
			logger.error("ServicioProcesoConsolidadasImpl.newServicioProcesoConsolidadas",e);
			return null;
		}
	}

	////MIGRADO
	@Override
	public boolean procesoConsolidacion(List<TblEstadistitcasCons> listaEstadisticasConsolidadas, Long idLote,
			List<Long> listaMensajesHistoricosCons, List<Long> listaIdAdjuntosHist,
			List<Long> listaIdMensajesAdjuntosHist, List<Long> listaIdDestinatariosHist, List<Long> listaHistoricoHist,
			List<Long> listaIdDestinatariosMensajesHist, List<TblGestionEnviosHist> listaGestionEnviosHist) throws BusinessException {
		//Realizamos la inserccion de las estadisticas consolidadas
	try{	
		if(null != listaEstadisticasConsolidadas){
			
			if(!listaEstadisticasConsolidadas.isEmpty()){
				for(TblEstadistitcasCons estadisticaConsolidada : listaEstadisticasConsolidadas){
					tblEstadisticasConsManager.insertar(estadisticaConsolidada);
				}
			}	
			
			//Realizamos la eliminacion de las tablas de historicos

			if(null != listaGestionEnviosHist && !listaGestionEnviosHist.isEmpty()){
				for(TblGestionEnviosHist gestionEnviosHist : listaGestionEnviosHist){
					if (null != tblGestionEnviosHistManager.getGestionEnviosById(gestionEnviosHist.getMensajeid())){
						tblGestionEnviosHistManager.eliminar(gestionEnviosHist.getMensajeid());
					}
				}
				
			}
			
			if(null != listaHistoricoHist && !listaHistoricoHist.isEmpty()){
				for(Long idHistoricoHist : listaHistoricoHist){
					tblHistoricosHistManager.eliminar(idHistoricoHist);
				}
			}
			
			if(null != listaIdDestinatariosHist && !listaIdDestinatariosHist.isEmpty()){
				for(Long destinatarioHist : listaIdDestinatariosHist){
					if (null != tblDestinatariosHistManager.getDestinatario(destinatarioHist)){
						tblDestinatariosHistManager.eliminar(destinatarioHist);
					}
				}
			}
			
			if(null != listaIdDestinatariosMensajesHist && !listaIdDestinatariosMensajesHist.isEmpty()){
				for(Long destinatarioMensajeHist : listaIdDestinatariosMensajesHist){
					if (null != tblDestinatariosMensajesHistManager.getDestinatarioMensaje(destinatarioMensajeHist)){
						tblDestinatariosMensajesHistManager.eliminar(destinatarioMensajeHist);
					}
				}
			}
			
			if(null != listaIdMensajesAdjuntosHist && !listaIdMensajesAdjuntosHist.isEmpty()){
				for(Long mensajesAdjuntosHist : listaIdMensajesAdjuntosHist){
					tblMensajesAdjuntosHistManager.eliminar(mensajesAdjuntosHist);
				}
			}
			
			if(null != listaIdAdjuntosHist && !listaIdAdjuntosHist.isEmpty()){
				for(Long adjuntoEmailHist : listaIdAdjuntosHist){
					TblAdjuntosHist adjunto = tblAdjuntosHistManager.getById(adjuntoEmailHist);
					
					if (null != adjunto){
						UtilCreateFile.eliminarAdjunto(adjunto.getContenidofile());
						tblAdjuntosHistManager.eliminar(adjuntoEmailHist);
					}
					
				}
			}
			
			if(null != listaMensajesHistoricosCons && !listaMensajesHistoricosCons.isEmpty()){
				for(Long mensajeHist : listaMensajesHistoricosCons){
					TblMensajesHist mensaje = tblMensajesHistManager.getMensaje(mensajeHist);
					if (null != mensaje){
						if(null != mensaje.getCuerpofile()){
							UtilCreateFile.eliminarAdjunto(mensaje.getCuerpofile());
						}
						tblMensajesHistManager.delete(mensajeHist);
					}
				}
			}
			
		}
		
		else {
			return false;
		}	
		
	} catch (Exception e){
		logger.error("ServicioProcesoConsolidadasImpl.procesoConsolidacion",e);
		throw new BusinessException(e, "errors.job.cons.realizarConsolidacionEstadisticas");
	}
	
	return true;
	}


}
