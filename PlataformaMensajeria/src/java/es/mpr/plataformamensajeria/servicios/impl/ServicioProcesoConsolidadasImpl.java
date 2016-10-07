package es.mpr.plataformamensajeria.servicios.impl;

import java.awt.print.Printable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

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
import es.mpr.plataformamensajeria.model.ProcesoConsJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoConsolidadas;

/**
 * <p>Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioProcesoConsolidadasImpl implements ServicioProcesoConsolidadas{
	
	Logger logger = Logger.getLogger(ServicioProcesoConsolidadasImpl.class);
	
	private IPaginationJPADAO jpa;
	
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

	@Override
	@Transactional
	public Long newServicioProcesoConsolidadas(ProcesoConsBean procesoConsBean) {
		try{
			
			ProcesoConsJPA procesoConsJPA = new ProcesoConsJPA();
			procesoConsJPA.setId(null);
			procesoConsJPA.setCodigoEstado(procesoConsBean.getCodigoEstado());
			procesoConsJPA.setDescripcionEstado(procesoConsBean.getDescripcionEstado());
			procesoConsJPA.setFechaInicio(procesoConsBean.getFechaInicio());
			procesoConsJPA.setFechaFin(procesoConsBean.getFechaFin());
			jpa.insert(procesoConsJPA);
			
			return procesoConsJPA.getId();
		}catch (DAOException e){
//			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
//			exc.mRechargeInput();
//			throw exc;
			return null;
		}
	}

	@Override
	@Transactional
	public boolean procesoCons(
			List<EstadisticasConsolidadasBean> listaEstadisticasConsolidadasBean,
			List<LotesEnviosHistoricosBean> listaLotesEnviosHistBean,
			List<MensajeHistoricosBean> listaMensajesHistoricosBean,
			List<AdjuntoEmailHistoricosBean> listaAdjuntosEmailHistBean,
			List<MensajesAdjuntosHistoricosBean> listaMensajesAdjuntosHistBean,
			List<DestinatarioHistoricosBean> listaDestinatariosHistBean,
			List<HistoricoHistBean> listaHistoricoHistBean,
			List<GestionEnvioHistoricoBean> listaGestionEnviosHistBean)
			throws BusinessException {
		
		try{
		
			//Realizamos la inserccion de las estadisticas consolidadas
		
			if(null != listaEstadisticasConsolidadasBean && !listaEstadisticasConsolidadasBean.isEmpty()){
				for(EstadisticasConsolidadasBean estadisticaConsolidada : listaEstadisticasConsolidadasBean){
					EstadisticasConsolidadasJPA estadisticasConsolidadasJPA = new EstadisticasConsolidadasJPA();
					estadisticasConsolidadasJPA.setServidorId(estadisticaConsolidada.getServidorId());
					estadisticasConsolidadasJPA.setServidorNombre(estadisticaConsolidada.getServidorNombre());
					estadisticasConsolidadasJPA.setAplicacionId(estadisticaConsolidada.getAplicacionId());
					estadisticasConsolidadasJPA.setPlanificacionId(estadisticaConsolidada.getPlanificacionId());
					estadisticasConsolidadasJPA.setAplicacionNombre(estadisticaConsolidada.getAplicacionNombre());
					estadisticasConsolidadasJPA.setServicioId(estadisticaConsolidada.getServicioId());
					estadisticasConsolidadasJPA.setServicioNombre(estadisticaConsolidada.getServicioNombre());
					estadisticasConsolidadasJPA.setCanalId(estadisticaConsolidada.getCanalId());
					estadisticasConsolidadasJPA.setCanalNombre(estadisticaConsolidada.getCanalNombre());
					estadisticasConsolidadasJPA.setEstadoId(estadisticaConsolidada.getEstadoId());
					estadisticasConsolidadasJPA.setEstadoNombre(estadisticaConsolidada.getEstadoNombre());
					estadisticasConsolidadasJPA.setAnno(estadisticaConsolidada.getAnno());
					estadisticasConsolidadasJPA.setMes(estadisticaConsolidada.getMes());
					estadisticasConsolidadasJPA.setNumTotal(estadisticaConsolidada.getNumTotal());
					
					jpa.insert(estadisticasConsolidadasJPA);
				}
				
			}	
			
			//Realizamos la eliminacion de las tablas de historicos

			if(null != listaGestionEnviosHistBean && !listaGestionEnviosHistBean.isEmpty()){
				for(GestionEnvioHistoricoBean gestionEnvioHist : listaGestionEnviosHistBean){
					GestionEnviosHistoricosJPA gestionEnviosHistJPA = new GestionEnviosHistoricosJPA();
					gestionEnviosHistJPA.setId(gestionEnvioHist.getMensajeId());
					
					jpa.delete(gestionEnviosHistJPA);
				}
				
			}
			
			if(null != listaHistoricoHistBean && !listaHistoricoHistBean.isEmpty()){
				for(HistoricoHistBean historicoHist : listaHistoricoHistBean){
					HistoricoHistJPA historicoHistJPA = new HistoricoHistJPA();
					historicoHistJPA.setId(historicoHist.getHistoricoId());
					
					jpa.delete(historicoHistJPA);
				}
			}
			
			if(null != listaDestinatariosHistBean && !listaDestinatariosHistBean.isEmpty()){
				for(DestinatarioHistoricosBean destinatarioHist : listaDestinatariosHistBean){
					DestinatarioHistoricosJPA destinatarioHistJPA = new DestinatarioHistoricosJPA();
					destinatarioHistJPA.setId(destinatarioHist.getDestinatarioId());
					
					jpa.delete(destinatarioHistJPA);
				}
			}
			
			if(null != listaMensajesAdjuntosHistBean && !listaMensajesAdjuntosHistBean.isEmpty()){
				for(MensajesAdjuntosHistoricosBean mensajesAdjuntosHist : listaMensajesAdjuntosHistBean){
					MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistJPA = new MensajesAdjuntosHistoricosJPA();
					mensajesAdjuntosHistJPA.setId(mensajesAdjuntosHist.getMensajeAdjuntoId());
					
					jpa.delete(mensajesAdjuntosHistJPA);
				}
			}
			
			if(null != listaAdjuntosEmailHistBean && !listaAdjuntosEmailHistBean.isEmpty()){
				for(AdjuntoEmailHistoricosBean adjuntoEmailHist : listaAdjuntosEmailHistBean){
					AdjuntoEmailHistoricosJPA adjuntoEmailHistJPA = new AdjuntoEmailHistoricosJPA();
					adjuntoEmailHistJPA.setId(adjuntoEmailHist.getAdjuntoId());
					
					jpa.delete(adjuntoEmailHistJPA);
				}
			}
			
			if(null != listaMensajesHistoricosBean && !listaMensajesHistoricosBean.isEmpty()){
				for(MensajeHistoricosBean mensajeHist : listaMensajesHistoricosBean){
					MensajesHistoricosJPA mensajeHistJPA = new MensajesHistoricosJPA();
					mensajeHistJPA.setId(mensajeHist.getMensajeId());
					
					jpa.delete(mensajeHistJPA);
				}
			}
			
			if(null != listaLotesEnviosHistBean && !listaLotesEnviosHistBean.isEmpty()){
				for(LotesEnviosHistoricosBean loteEnvioHist : listaLotesEnviosHistBean){
					LotesEnviosHistoricosJPA loteEnvioHistJPA = new LotesEnviosHistoricosJPA();
					loteEnvioHistJPA.setId(loteEnvioHist.getLoteEnvioId());
					
					jpa.delete(loteEnvioHistJPA);
				}
			}
			
		} catch (DAOException e){
			throw new BusinessException(e, "errors.job.cons.realizarConsolidacionEstadisticas");
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean procesoConsJPA(
			List<EstadisticasConsolidadasJPA> listaEstadisticasConsolidadasJPA , List<LotesEnviosHistoricosJPA> listaLotesEnviosHistJPA, 
			List<MensajesHistoricosJPA> listaMensajesHistoricosJPA, List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailHistJPA, 
			List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHistJPA, List<DestinatarioHistoricosJPA> listaDestinatariosHistJPA, 
			List<HistoricoHistJPA> listaHistoricoHistJPA, List<GestionEnviosHistoricosJPA> listaGestionEnviosHistJPA)
			throws BusinessException {
		
		try{
		
			//Realizamos la inserccion de las estadisticas consolidadas
			
			if(null != listaEstadisticasConsolidadasJPA){
				
				if(!listaEstadisticasConsolidadasJPA.isEmpty()){
					for(EstadisticasConsolidadasJPA estadisticaConsolidadaJPA : listaEstadisticasConsolidadasJPA){
						jpa.insert(estadisticaConsolidadaJPA);
					}
				}	
				
				//Realizamos la eliminacion de las tablas de historicos

				if(null != listaGestionEnviosHistJPA && !listaGestionEnviosHistJPA.isEmpty()){
					for(GestionEnviosHistoricosJPA gestionEnviosHistJPA : listaGestionEnviosHistJPA){
//						jpa.delete(gestionEnviosHistJPA);
					}
					
				}
				
				if(null != listaHistoricoHistJPA && !listaHistoricoHistJPA.isEmpty()){
					for(HistoricoHistJPA historicoHistJPA : listaHistoricoHistJPA){
//						jpa.delete(historicoHistJPA);
					}
				}
				
				if(null != listaDestinatariosHistJPA && !listaDestinatariosHistJPA.isEmpty()){
					for(DestinatarioHistoricosJPA destinatarioHistJPA : listaDestinatariosHistJPA){
//						jpa.delete(destinatarioHistJPA);
					}
				}
				
				if(null != listaMensajesAdjuntosHistJPA && !listaMensajesAdjuntosHistJPA.isEmpty()){
					for(MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistJPA : listaMensajesAdjuntosHistJPA){
//						jpa.delete(mensajesAdjuntosHistJPA);
					}
				}
				
				if(null != listaAdjuntosEmailHistJPA && !listaAdjuntosEmailHistJPA.isEmpty()){
					for(AdjuntoEmailHistoricosJPA adjuntoEmailHistJPA : listaAdjuntosEmailHistJPA){
//						jpa.delete(adjuntoEmailHistJPA);
					}
				}
				
				if(null != listaMensajesHistoricosJPA && !listaMensajesHistoricosJPA.isEmpty()){
					for(MensajesHistoricosJPA mensajeHistJPA : listaMensajesHistoricosJPA){
//						jpa.delete(mensajeHistJPA);
					}
				}
				
				if(null != listaLotesEnviosHistJPA && !listaLotesEnviosHistJPA.isEmpty()){
					for(LotesEnviosHistoricosJPA loteEnvioJPA : listaLotesEnviosHistJPA){
//						jpa.delete(loteEnvioJPA);
					}
				}
			}
			
			else {
				return false;
			}	
			
		} catch (DAOException e){
			e.printStackTrace();
			throw new BusinessException(e, "errors.job.cons.realizarConsolidacionEstadisticas");
		}
		
		return true;
	}


}
