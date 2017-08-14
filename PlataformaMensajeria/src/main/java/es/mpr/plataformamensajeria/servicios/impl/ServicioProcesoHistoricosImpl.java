package es.mpr.plataformamensajeria.servicios.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblProcesoHistManager;
import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblHistoricosHist;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblProcesoHist;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistorificacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistoricoHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;

/**
 * <p>
 * Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioProcesoHistoricosImpl")
public class ServicioProcesoHistoricosImpl implements ServicioProcesoHistoricos {

	private static Logger logger = Logger.getLogger(ServicioProcesoHistoricosImpl.class);

	@Resource(name = "servicioLotesEnviosHistoricosImpl")
	private ServicioLotesEnviosHistoricos servicioLotesEnviosHist;

	@Resource(name = "servicioMensajesHistoricosImpl")
	private ServicioMensajesHistoricos servicioMensajesHist;

	@Resource(name = "servicioMensajesAdjuntosHistoricosImpl")
	private ServicioMensajesAdjuntosHistoricos servicioMensajesAdjuntosHist;

	@Resource(name = "servicioAdjuntoEmailHistoricosImpl")
	private ServicioAdjuntoEmailHistoricos servicioAdjuntoEmailHist;

	@Resource(name = "servicioDestinatarioHistoricosImpl")
	private ServicioDestinatarioHistoricos servicioDestinatarioHist;

	@Resource(name = "servicioHistoricoHistImpl")
	private ServicioHistoricoHist servicioHistoricoHist;

	@Resource(name = "servicioGestionEnviosHistoricosImpl")
	private ServicioGestionEnviosHistoricos servicioGestionEnviosHist;

	@Resource(name = "TblProcesoHistManagerImpl")
	private TblProcesoHistManager tblProcesoHistManager;
	
	@Autowired
    private SessionFactory sessionFactorySIMApp;
		
	private static final Integer MAX = 1000;


	////MIGRADO
	@Override
	@Transactional
	public Long newServicioProcesoHistoricos(ProcesoHistBean procesoHistBean) {
		try {
			TblProcesoHist proceso = new TblProcesoHist();
			proceso.setCodigoestado(procesoHistBean.getCodigoEstado());
			proceso.setDescripcionestado(procesoHistBean.getDescripcionEstado());
			proceso.setFechacreacion(new Date());
			proceso.setFechafin(procesoHistBean.getFechaFin());
			proceso.setFechainicio(procesoHistBean.getFechaInicio());
			return tblProcesoHistManager.insertar(proceso);

		} catch (Exception e) {
			logger.error("ServicioProcesoHistoricosImpl.newServicioProcesoHistoricos",e);
			return null;
		}
	}

	
	////MIGRADO
	@Override
	@Transactional
	public boolean procesoHistoricoLotesEnvio(Long loteEnvio, List<Long> listaMensajes) throws BusinessException {
		try {

			// Realizamos la inserccion de las tablas de historicos
			TblLotesEnviosHist loteHistorico = servicioLotesEnviosHist.getLoteEnvioHist(loteEnvio);
			loteHistorico.setFechahistorificacion(new Date());
			
			servicioLotesEnviosHist.insert(loteHistorico);
			
			
			Integer partes = (int) Math.ceil((double) listaMensajes.size() / MAX);
			long startTime2 = System.currentTimeMillis();
			for (int i = 0; i < partes; i++) {
				Date fecha = new Date();
				long startTime = System.currentTimeMillis();
				
				ProcesoHistorificacionBean procesoHistorificacionBean = new ProcesoHistorificacionBean();
								
				List<Long> subList = listaMensajes.subList(i * MAX, (listaMensajes.size()<=(i + 1) * MAX)? listaMensajes.size() : (i + 1) * MAX);
								
				// procesamos mensajes de 1000 en 1000 (determinado por valor de MAX)
				procesoHistorificacionBean.setListaMensajeHistorico(servicioMensajesHist.getTblMensajesHist(subList, loteHistorico));
				procesoHistorificacionBean.setListasMensajesAdjuntosHist( servicioMensajesAdjuntosHist.getTblMensajesAdjuntosHist(subList,loteHistorico));
				procesoHistorificacionBean.setListasDestinatariosHist(servicioDestinatarioHist.getTblDestinatariosHist(subList));
				procesoHistorificacionBean.setListasHistoricoHist(servicioHistoricoHist.getTblHistoricosHist(subList));
				procesoHistorificacionBean.setListaGestionEnviosHist(servicioGestionEnviosHist.getTblGestionEnviosHist(subList));
				procesoHistorificacionBean.setListasDestinatariosMensajesHist(servicioDestinatarioHist.getTblDestinatariosMensajesHist(subList));
							
				///realizamos la inserciones
				historificaMensajesHist(fecha, procesoHistorificacionBean);

				//Si hay más de 5000 adjuntos en cada 1000 mensajes se fracciona
				historificaMensajesAdjuntosHist(fecha, procesoHistorificacionBean);
				
				//Si hay más de 5000 destinatarios en cada 1000 mensajes se fracciona
				historificaDestinatariosHist(fecha, procesoHistorificacionBean);
				
				//Si hay más de 5000 hisstoricos en cada 1000 mensajes se fracciona
				historificaHistoricoHist(fecha, procesoHistorificacionBean);
				
				historificaGestionEnviosHist(fecha, procesoHistorificacionBean);
				
				//Si hay más de 5000 destinatariosMensaje en cada 1000 mensajes se fracciona
				historificaDestinatariosMensHist(fecha, procesoHistorificacionBean);
						
				//realizamos los borrados
				eliminaMensajesAdjuntos(procesoHistorificacionBean);
				eliminaDestinatarios(procesoHistorificacionBean);
				eliminaHistoricos(procesoHistorificacionBean);
				eliminaGestionEnvios(procesoHistorificacionBean);
				eliminaDestinatariosMensajes(procesoHistorificacionBean);
				eliminaMensajes(procesoHistorificacionBean);
				
				//para evitar out of memory
				sessionFactorySIMApp.getCurrentSession().flush();
				
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;

				logger.info("TIEMPO 1000 mensajes------->" + elapsedTime);
			}
			
			///eliminamos el lote
			servicioLotesEnviosHist.delete(loteHistorico.getLoteenvioid());
			
			
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime2;
			logger.info("TIEMPO TOTAL------->" + elapsedTime);

		} catch (Exception e) {
			throw new BusinessException(e, "errors.job.historico.realizarHistorificacion");
		}

		return true;
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void historificaMensajesHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (TblMensajesHist ma : procesoHistorificacionBean.getListaMensajeHistorico()) {
			ma.setFechahistorificacion(fecha);
			servicioMensajesHist.insert(ma);
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaMensajes(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (TblMensajesHist ma : procesoHistorificacionBean.getListaMensajeHistorico()) {
			servicioMensajesHist.delete(ma.getMensajeid());
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaDestinatariosMensajes(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblDestinatariosMensHist> l :procesoHistorificacionBean.getListasDestinatariosMensajesHist()){
			for (TblDestinatariosMensHist dmh : l) {
				servicioDestinatarioHist.deleteDestinatarioMensaje(dmh.getDestinatariosmensajes());
			}
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaGestionEnvios(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (TblGestionEnviosHist geh : procesoHistorificacionBean.getListaGestionEnviosHist()) {
			servicioGestionEnviosHist.delete(geh.getMensajeid());
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaHistoricos(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblHistoricosHist> l :procesoHistorificacionBean.getListasHistoricoHist()){
			for (TblHistoricosHist hh : l) {
				servicioHistoricoHist.detele(hh.getHistoricoid());
			}
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaDestinatarios(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblDestinatariosHist> l :procesoHistorificacionBean.getListasDestinatariosHist()){
			for (TblDestinatariosHist dh : l) {
				servicioDestinatarioHist.delete(dh.getDestinatarioid());
			}
		}
	}


	/**
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void eliminaMensajesAdjuntos(ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblMensajesAdjuntosHist> l :procesoHistorificacionBean.getListasMensajesAdjuntosHist()){
			for (TblMensajesAdjuntosHist mah : l) {
			//	servicioAdjuntoEmailHist.delete(mah.getTblAdjuntosHist().getAdjuntoid());
				servicioMensajesAdjuntosHist.delete(mah.getMensajeadjuntoid());
				
			}
		}
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void historificaDestinatariosMensHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblDestinatariosMensHist> l :procesoHistorificacionBean.getListasDestinatariosMensajesHist()){
			for (TblDestinatariosMensHist dmh : l) {
				dmh.setFechahistorificacion(fecha);
				servicioDestinatarioHist.insert(dmh);
			}
		}
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 * @throws BusinessException
	 */
	///MIGRADO
	private void historificaGestionEnviosHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean)
			throws BusinessException {
		for (TblGestionEnviosHist geh : procesoHistorificacionBean.getListaGestionEnviosHist()) {
			geh.setFechahistorificacion(fecha);
			servicioGestionEnviosHist.insert(geh);
		}
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void historificaHistoricoHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblHistoricosHist> l :procesoHistorificacionBean.getListasHistoricoHist()){
			for (TblHistoricosHist hh : l) {
				hh.setFechahistorificacion(fecha);
				servicioHistoricoHist.insert(hh);
			}
		}
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void historificaDestinatariosHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblDestinatariosHist> l :procesoHistorificacionBean.getListasDestinatariosHist()){
			for (TblDestinatariosHist dh : l) {
				dh.setFechahistorificacion(fecha);
				servicioDestinatarioHist.insert(dh);
			}
		}
	}


	/**
	 * @param fecha
	 * @param procesoHistorificacionBean
	 */
	///MIGRADO
	private void historificaMensajesAdjuntosHist(Date fecha, ProcesoHistorificacionBean procesoHistorificacionBean) {
		for (List<TblMensajesAdjuntosHist> l :procesoHistorificacionBean.getListasMensajesAdjuntosHist()){
			for (TblMensajesAdjuntosHist mah : l) {
				mah.getTblAdjuntosHist().setFechahistorificacion(fecha);
				servicioAdjuntoEmailHist.insert(mah.getTblAdjuntosHist());
			}
			sessionFactorySIMApp.getCurrentSession().flush();
			for (TblMensajesAdjuntosHist mah : l) {
				mah.setFechahistorificacion(fecha);
				servicioMensajesAdjuntosHist.insert(mah);
			}
		}
	}

}
