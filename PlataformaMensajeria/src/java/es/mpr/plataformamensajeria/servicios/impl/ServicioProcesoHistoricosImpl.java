package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.DestinatarioBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.LotesEnviosBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.MensajesAdjuntosBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.AdjuntoEmailJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.HistoricoJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesJPA;
import es.mpr.plataformamensajeria.model.ProcesoHistJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;

/**
 * <p>Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioProcesoHistoricosImpl implements ServicioProcesoHistoricos{
	
	Logger logger = Logger.getLogger(ServicioProcesoHistoricosImpl.class);
	
	private IPaginationJPADAO jpa;

	@Override
	@Transactional
	public Long newServicioProcesoHistoricos(ProcesoHistBean procesoHistBean) {
		try{
			
			ProcesoHistJPA procesoHistJPA = new ProcesoHistJPA();
			procesoHistJPA.setId(null);
			procesoHistJPA.setCodigoEstado(procesoHistBean.getCodigoEstado());
			procesoHistJPA.setDescripcionEstado(procesoHistBean.getDescripcionEstado());
			procesoHistJPA.setFechaInicio(procesoHistBean.getFechaInicio());
			procesoHistJPA.setFechaFin(procesoHistBean.getFechaFin());
			jpa.insert(procesoHistJPA);
			
			return procesoHistJPA.getId();
		}catch (DAOException e){
//			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
//			exc.mRechargeInput();
//			throw exc;
			return null;
		}
	}

	@Override
	public void updateServicioProcesoHistoricos(ProcesoHistBean procesoHistBean) {
		// TODO Auto-generated method stub
		
	}
	
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
	
	/**
	 * <p>Obtenemos un objeto ProcesoHistJPA a partir de un objeto ProcesoHistBean</p>
	 * 
	 * @param procesoHistBean 
	 * 
	 * @return ProcesoHistJPA
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected ProcesoHistJPA getProcesoHistJPA(ProcesoHistBean procesoHistBean) {
		 
		ProcesoHistJPA procesoHistJPA = new ProcesoHistJPA();
		
		procesoHistJPA.setCodigoEstado(procesoHistBean.getCodigoEstado());
		procesoHistJPA.setDescripcionEstado(procesoHistBean.getDescripcionEstado());
		if(procesoHistBean.getFechaCreacion()!=null){
			procesoHistJPA.setFechaCreacion(procesoHistBean.getFechaCreacion());
		}
		if(procesoHistBean.getProcesoHistId()!=null){
			procesoHistJPA.setId(procesoHistBean.getProcesoHistId());
		}
		if(procesoHistBean.getFechaInicio()!=null){
			procesoHistJPA.setFechaInicio(procesoHistBean.getFechaInicio());
		}
		if(procesoHistBean.getFechaFin()!=null){
			procesoHistJPA.setFechaFin(procesoHistBean.getFechaFin());
		}
		
//		try {
//			BeanUtils.copyProperties(procesoHistJPA, procesoHistBean);
//		} catch (IllegalAccessException  e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (InvocationTargetException e){
//			e.printStackTrace();
//		}
		
		return procesoHistJPA;
	}
	
	@Override
	@Transactional
	public boolean procesoHistorico(List<LotesEnviosBean> listaLotesEnviosBean, List<MensajeBean> listaMensajesHistBean,
			List<AdjuntoEmailBean> listaAdjuntosEmailBean, List<MensajesAdjuntosBean> listaMensajesAdjuntosHist,
			List<DestinatarioBean> listaDestinatariosBean, List<HistoricoBean> listaHistoricoBean,
			List<GestionEnvioBean> listaGestionEnviosBean)  throws BusinessException {
		try{
			
			//Realizamos la inserccion de las tablas de historicos
			
			if(null != listaLotesEnviosBean && !listaLotesEnviosBean.isEmpty()){
				for(LotesEnviosBean loteEnvioHist : listaLotesEnviosBean){
					LotesEnviosHistoricosJPA loteEnvioHistJPA = new LotesEnviosHistoricosJPA();
					loteEnvioHistJPA.setId(loteEnvioHist.getLoteEnvioId());
					loteEnvioHistJPA.setNombre(loteEnvioHist.getNombre());
					loteEnvioHistJPA.setDescripcion(loteEnvioHist.getDescripcion());
					loteEnvioHistJPA.setServicioId(loteEnvioHist.getServicioId());
					loteEnvioHistJPA.setEstadoEnvioId(loteEnvioHist.getEstadoEnvioId());
					loteEnvioHistJPA.setFechaCreacion(loteEnvioHist.getFechaCreacion());
					loteEnvioHistJPA.setCreadorPor(loteEnvioHist.getCreadorPor());
					loteEnvioHistJPA.setFechaModificacion(loteEnvioHist.getFechaModificacion());
					loteEnvioHistJPA.setModificadoPor(loteEnvioHist.getModificadoPor());
					loteEnvioHistJPA.setFechaHistorificacion(new Date());
					
					jpa.insert(loteEnvioHistJPA);
				}
			}
			
			if(null != listaMensajesHistBean && !listaMensajesHistBean.isEmpty()){
				for(MensajeBean mensajeHist : listaMensajesHistBean){
					MensajesHistoricosJPA mensajeHistJPA = new MensajesHistoricosJPA();
					mensajeHistJPA.setId(mensajeHist.getMensajeId());
					mensajeHistJPA.setLoteEnvioId(mensajeHist.getLoteEnvioId());
					mensajeHistJPA.setCodigoExterno(mensajeHist.getCodigoExterno());
					mensajeHistJPA.setCabecera(mensajeHist.getCabecera());
					mensajeHistJPA.setEstadoActual(mensajeHist.getEstadoActual());
					mensajeHistJPA.setNumeroEnvios(mensajeHist.getNumeroEnvios());
					mensajeHistJPA.setFechaCreacion(mensajeHist.getFechaCreacion());
					mensajeHistJPA.setCreadoPor(mensajeHist.getCreadoPor());
					mensajeHistJPA.setFechaModificacion(mensajeHist.getFechaModificacion());
					mensajeHistJPA.setModificadoPor(mensajeHist.getModificadoPor());
					mensajeHistJPA.setUltimoEnvio(mensajeHist.getUltimoEnvio());
					mensajeHistJPA.setUltimoIdHistorico(mensajeHist.getUltimoIdHistorico());
					mensajeHistJPA.setCuerpo(mensajeHist.getCuerpo());
					mensajeHistJPA.setTipoCuerpo(mensajeHist.getTipoCuerpo());
					mensajeHistJPA.setTipoCodificacion(mensajeHist.getTipoCodificacion());
					mensajeHistJPA.setPrioridad(mensajeHist.getPrioridad());
					mensajeHistJPA.setTipoMensaje(mensajeHist.getTipoMensaje());
					mensajeHistJPA.setTelefono(mensajeHist.getTelefono());
					mensajeHistJPA.setUim(mensajeHist.getUim());
					mensajeHistJPA.setIdEnviosSms(mensajeHist.getIdEnviosSms());
					mensajeHistJPA.setFechaHistorificado(new Date());
					
					jpa.insert(mensajeHistJPA);
				}
			}
			
			if(null != listaAdjuntosEmailBean && !listaAdjuntosEmailBean.isEmpty()){
				for(AdjuntoEmailBean adjuntoEmail : listaAdjuntosEmailBean){
					AdjuntoEmailHistoricosJPA adjuntoEmailHistoricosJPA = new AdjuntoEmailHistoricosJPA();
					adjuntoEmailHistoricosJPA.setId(adjuntoEmail.getAdjuntoId());
					adjuntoEmailHistoricosJPA.setNombre(adjuntoEmail.getNombre());
					adjuntoEmailHistoricosJPA.setFechaCreacion(adjuntoEmail.getFechaCreacion());
					adjuntoEmailHistoricosJPA.setCreadoPor(adjuntoEmail.getCreadoPor());
					adjuntoEmailHistoricosJPA.setFechaModificacion(adjuntoEmail.getFechaModificacion());
					adjuntoEmailHistoricosJPA.setModificadoPor(adjuntoEmail.getModificadoPor());
					adjuntoEmailHistoricosJPA.setContenido(adjuntoEmail.getContenido());
					adjuntoEmailHistoricosJPA.setImagen(adjuntoEmail.getImagen());
					adjuntoEmailHistoricosJPA.setFechaHistorificado(new Date());
					
					jpa.insert(adjuntoEmailHistoricosJPA);
				}
			}
			
			if(null != listaMensajesAdjuntosHist && !listaMensajesAdjuntosHist.isEmpty()){
				for(MensajesAdjuntosBean mensajesAdjuntosJPA : listaMensajesAdjuntosHist){
					MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistoricosJPA = new MensajesAdjuntosHistoricosJPA();
					mensajesAdjuntosHistoricosJPA.setId(mensajesAdjuntosJPA.getMensajeAdjuntoId());
					mensajesAdjuntosHistoricosJPA.setMensajeId(mensajesAdjuntosJPA.getMensajeId());
					mensajesAdjuntosHistoricosJPA.setAdjuntoId(mensajesAdjuntosJPA.getAdjuntoId());
					mensajesAdjuntosHistoricosJPA.setFechaHistorificacion(new Date());
					
					jpa.insert(mensajesAdjuntosHistoricosJPA);
				}
			}
			
			if(null != listaDestinatariosBean && !listaDestinatariosBean.isEmpty()){
				for(DestinatarioBean destinatario : listaDestinatariosBean){
					DestinatarioHistoricosJPA destinatarioHistoricosJPA = new DestinatarioHistoricosJPA();
					destinatarioHistoricosJPA.setId(destinatario.getDestinatarioId());
					destinatarioHistoricosJPA.setNombre(destinatario.getNombre());
					destinatarioHistoricosJPA.setEmail(destinatario.getEmail());
					destinatarioHistoricosJPA.setMensajeId(destinatario.getMensajeId());
					destinatarioHistoricosJPA.setFechaCreacion(destinatario.getFechaCreacion());
					destinatarioHistoricosJPA.setCreadoPor(destinatario.getCreadoPor());
					destinatarioHistoricosJPA.setFechaModificacion(destinatario.getFechaModificacion());
					destinatarioHistoricosJPA.setModificadoPor(destinatario.getModificadoPor());
					destinatarioHistoricosJPA.setTipoDestinatario(destinatario.getTipoDestinatario());
					destinatarioHistoricosJPA.setFechaHistorificacion(new Date());
					
					jpa.insert(destinatarioHistoricosJPA);
				}
			}
			
			if(null != listaHistoricoBean && !listaHistoricoBean.isEmpty()){
				for(HistoricoBean historico : listaHistoricoBean){
					HistoricoHistJPA historicoHistJPA = new HistoricoHistJPA();
					historicoHistJPA.setId(historico.getHistoricoId());
					historicoHistJPA.setFecha(historico.getFecha());
					historicoHistJPA.setMensajeId(historico.getMensajeId());
					historicoHistJPA.setEstadoId(historico.getEstadoId());
					historicoHistJPA.setServidorId(historico.getServidorId());
					historicoHistJPA.setPlanificacionId(historico.getPlanificacionId());
					historicoHistJPA.setDescripcion(historico.getDescripcion());
					historicoHistJPA.setSubEstadoId(historico.getSubEstadoId());
					historicoHistJPA.setFechaHistorificado(new Date());
					
					jpa.insert(historicoHistJPA);
				}
			}
			
			if(null != listaGestionEnviosBean && !listaGestionEnviosBean.isEmpty()){
				for(GestionEnvioBean gestionEnvio : listaGestionEnviosBean){
					GestionEnviosHistoricosJPA gestionEnviosHistoricosJPA = new GestionEnviosHistoricosJPA();
					gestionEnviosHistoricosJPA.setAplicacion(gestionEnvio.getAplicacion());
					gestionEnviosHistoricosJPA.setCanal(gestionEnvio.getCanal());
					gestionEnviosHistoricosJPA.setServicio(gestionEnvio.getServicio());
					gestionEnviosHistoricosJPA.setEstado(gestionEnvio.getEstado());
					gestionEnviosHistoricosJPA.setDestinatario(gestionEnvio.getDestinatario());
					gestionEnviosHistoricosJPA.setAplicacionId(gestionEnvio.getAplicacionId());
					gestionEnviosHistoricosJPA.setServicioId(gestionEnvio.getServicioId());
					gestionEnviosHistoricosJPA.setCanalId(gestionEnvio.getCanalId());
					gestionEnviosHistoricosJPA.setLoteEnvioId(gestionEnvio.getIdLote());
					gestionEnviosHistoricosJPA.setNombre(gestionEnvio.getNombre());
					gestionEnviosHistoricosJPA.setId(gestionEnvio.getMensajeId());
					gestionEnviosHistoricosJPA.setUltimoEnvio(gestionEnvio.getUltimoEnvio());
					gestionEnviosHistoricosJPA.setEstadoId(gestionEnvio.getEstadoId());
					gestionEnviosHistoricosJPA.setServidorId(gestionEnvio.getServidorId());
					gestionEnviosHistoricosJPA.setCodigoExterno(gestionEnvio.getCodigoExterno());
					gestionEnviosHistoricosJPA.setAnio(gestionEnvio.getAnio());
					gestionEnviosHistoricosJPA.setMes(gestionEnvio.getMes());
					gestionEnviosHistoricosJPA.setFechaHistorificacion(new Date());
					
					jpa.insert(gestionEnviosHistoricosJPA);
					
				}
				
			}
			
			//Realizamos la eliminacion de las tablas originales
			

			if(null != listaGestionEnviosBean && !listaGestionEnviosBean.isEmpty()){
				for(GestionEnvioBean gestionEnvio : listaGestionEnviosBean){
					GestionEnviosJPA gestionEnviosJPA = new GestionEnviosJPA();
					gestionEnviosJPA.setId(gestionEnvio.getMensajeId());
					
					jpa.delete(gestionEnviosJPA);
				}
				
			}
			
			if(null != listaHistoricoBean && !listaHistoricoBean.isEmpty()){
				for(HistoricoBean historico : listaHistoricoBean){
					HistoricoJPA historicoJPA = new HistoricoJPA();
					historicoJPA.setId(historico.getHistoricoId());
					
					jpa.delete(historicoJPA);
				}
			}
			
			if(null != listaDestinatariosBean && !listaDestinatariosBean.isEmpty()){
				for(DestinatarioBean destinatario : listaDestinatariosBean){
					DestinatarioJPA destinatarioJPA = new DestinatarioJPA();
					destinatarioJPA.setId(destinatario.getDestinatarioId());
					
					jpa.delete(destinatarioJPA);
				}
			}
			
			if(null != listaMensajesAdjuntosHist && !listaMensajesAdjuntosHist.isEmpty()){
				for(MensajesAdjuntosBean mensajesAdjuntos : listaMensajesAdjuntosHist){
					MensajesAdjuntosJPA mensajesAdjuntosJPA = new MensajesAdjuntosJPA();
					mensajesAdjuntosJPA.setId(mensajesAdjuntos.getMensajeAdjuntoId());
					
					jpa.delete(mensajesAdjuntosJPA);
				}
			}
			
			if(null != listaAdjuntosEmailBean && !listaAdjuntosEmailBean.isEmpty()){
				for(AdjuntoEmailBean adjuntoEmail : listaAdjuntosEmailBean){
					AdjuntoEmailJPA adjuntoEmailJPA = new AdjuntoEmailJPA();
					adjuntoEmailJPA.setId(adjuntoEmail.getAdjuntoId());
					
					jpa.delete(adjuntoEmailJPA);
				}
			}
			
			if(null != listaMensajesHistBean && !listaMensajesHistBean.isEmpty()){
				for(MensajeBean mensajeHist : listaMensajesHistBean){
					MensajesJPA mensajeJPA = new MensajesJPA();
					mensajeJPA.setId(mensajeHist.getMensajeId());
					
					jpa.delete(mensajeJPA);
				}
			}
			
			if(null != listaLotesEnviosBean && !listaLotesEnviosBean.isEmpty()){
				for(LotesEnviosBean loteEnvioHist : listaLotesEnviosBean){
					LotesEnviosJPA loteEnvioJPA = new LotesEnviosJPA();
					loteEnvioJPA.setId(loteEnvioHist.getLoteEnvioId());
					
					jpa.delete(loteEnvioJPA);
				}
			}
			
		}catch (DAOException e){
			throw new BusinessException(e, "errors.job.historico.realizarHistorificacion");
		}
		
		return true;
		
	}
	
	@Override
	@Transactional
	public boolean procesoHistoricoLE(LotesEnviosBean loteEnvioBean, List<MensajeBean> listaMensajesHistBean,
			List<AdjuntoEmailBean> listaAdjuntosEmailBean, List<MensajesAdjuntosBean> listaMensajesAdjuntosHist,
			List<DestinatarioBean> listaDestinatariosBean, List<HistoricoBean> listaHistoricoBean,
			List<GestionEnvioBean> listaGestionEnviosBean)  throws BusinessException {
		try{
			
			//Realizamos la inserccion de las tablas de historicos
			
			if(null != loteEnvioBean){
				
				LotesEnviosHistoricosJPA loteEnvioHistJPA = new LotesEnviosHistoricosJPA();
				loteEnvioHistJPA.setId(loteEnvioBean.getLoteEnvioId());
				loteEnvioHistJPA.setNombre(loteEnvioBean.getNombre());
				loteEnvioHistJPA.setDescripcion(loteEnvioBean.getDescripcion());
				loteEnvioHistJPA.setServicioId(loteEnvioBean.getServicioId());
				loteEnvioHistJPA.setEstadoEnvioId(loteEnvioBean.getEstadoEnvioId());
				loteEnvioHistJPA.setFechaCreacion(loteEnvioBean.getFechaCreacion());
				loteEnvioHistJPA.setCreadorPor(loteEnvioBean.getCreadorPor());
				loteEnvioHistJPA.setFechaModificacion(loteEnvioBean.getFechaModificacion());
				loteEnvioHistJPA.setModificadoPor(loteEnvioBean.getModificadoPor());
				loteEnvioHistJPA.setFechaHistorificacion(new Date());
				
				jpa.insert(loteEnvioHistJPA);
				
				
				if(null != listaMensajesHistBean && !listaMensajesHistBean.isEmpty()){
					for(MensajeBean mensajeHist : listaMensajesHistBean){
						MensajesHistoricosJPA mensajeHistJPA = new MensajesHistoricosJPA();
						mensajeHistJPA.setId(mensajeHist.getMensajeId());
						mensajeHistJPA.setLoteEnvioId(mensajeHist.getLoteEnvioId());
						mensajeHistJPA.setCodigoExterno(mensajeHist.getCodigoExterno());
						mensajeHistJPA.setCabecera(mensajeHist.getCabecera());
						mensajeHistJPA.setEstadoActual(mensajeHist.getEstadoActual());
						mensajeHistJPA.setNumeroEnvios(mensajeHist.getNumeroEnvios());
						mensajeHistJPA.setFechaCreacion(mensajeHist.getFechaCreacion());
						mensajeHistJPA.setCreadoPor(mensajeHist.getCreadoPor());
						mensajeHistJPA.setFechaModificacion(mensajeHist.getFechaModificacion());
						mensajeHistJPA.setModificadoPor(mensajeHist.getModificadoPor());
						mensajeHistJPA.setUltimoEnvio(mensajeHist.getUltimoEnvio());
						mensajeHistJPA.setUltimoIdHistorico(mensajeHist.getUltimoIdHistorico());
						mensajeHistJPA.setCuerpo(mensajeHist.getCuerpo());
						mensajeHistJPA.setTipoCuerpo(mensajeHist.getTipoCuerpo());
						mensajeHistJPA.setTipoCodificacion(mensajeHist.getTipoCodificacion());
						mensajeHistJPA.setPrioridad(mensajeHist.getPrioridad());
						mensajeHistJPA.setTipoMensaje(mensajeHist.getTipoMensaje());
						mensajeHistJPA.setTelefono(mensajeHist.getTelefono());
						mensajeHistJPA.setUim(mensajeHist.getUim());
						mensajeHistJPA.setIdEnviosSms(mensajeHist.getIdEnviosSms());
						mensajeHistJPA.setFechaHistorificado(new Date());
						
						jpa.insert(mensajeHistJPA);
					}
				}
				
				if(null != listaAdjuntosEmailBean && !listaAdjuntosEmailBean.isEmpty()){
					for(AdjuntoEmailBean adjuntoEmail : listaAdjuntosEmailBean){
						AdjuntoEmailHistoricosJPA adjuntoEmailHistoricosJPA = new AdjuntoEmailHistoricosJPA();
						adjuntoEmailHistoricosJPA.setId(adjuntoEmail.getAdjuntoId());
						adjuntoEmailHistoricosJPA.setNombre(adjuntoEmail.getNombre());
						adjuntoEmailHistoricosJPA.setFechaCreacion(adjuntoEmail.getFechaCreacion());
						adjuntoEmailHistoricosJPA.setCreadoPor(adjuntoEmail.getCreadoPor());
						adjuntoEmailHistoricosJPA.setFechaModificacion(adjuntoEmail.getFechaModificacion());
						adjuntoEmailHistoricosJPA.setModificadoPor(adjuntoEmail.getModificadoPor());
						adjuntoEmailHistoricosJPA.setContenido(adjuntoEmail.getContenido());
						adjuntoEmailHistoricosJPA.setImagen(adjuntoEmail.getImagen());
						adjuntoEmailHistoricosJPA.setFechaHistorificado(new Date());
						
						jpa.insert(adjuntoEmailHistoricosJPA);
					}
				}
				
				if(null != listaMensajesAdjuntosHist && !listaMensajesAdjuntosHist.isEmpty()){
					for(MensajesAdjuntosBean mensajesAdjuntosJPA : listaMensajesAdjuntosHist){
						MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistoricosJPA = new MensajesAdjuntosHistoricosJPA();
						mensajesAdjuntosHistoricosJPA.setId(mensajesAdjuntosJPA.getMensajeAdjuntoId());
						mensajesAdjuntosHistoricosJPA.setMensajeId(mensajesAdjuntosJPA.getMensajeId());
						mensajesAdjuntosHistoricosJPA.setAdjuntoId(mensajesAdjuntosJPA.getAdjuntoId());
						mensajesAdjuntosHistoricosJPA.setFechaHistorificacion(new Date());
						
						jpa.insert(mensajesAdjuntosHistoricosJPA);
					}
				}
				
				if(null != listaDestinatariosBean && !listaDestinatariosBean.isEmpty()){
					for(DestinatarioBean destinatario : listaDestinatariosBean){
						DestinatarioHistoricosJPA destinatarioHistoricosJPA = new DestinatarioHistoricosJPA();
						destinatarioHistoricosJPA.setId(destinatario.getDestinatarioId());
						destinatarioHistoricosJPA.setNombre(destinatario.getNombre());
						destinatarioHistoricosJPA.setEmail(destinatario.getEmail());
						destinatarioHistoricosJPA.setMensajeId(destinatario.getMensajeId());
						destinatarioHistoricosJPA.setFechaCreacion(destinatario.getFechaCreacion());
						destinatarioHistoricosJPA.setCreadoPor(destinatario.getCreadoPor());
						destinatarioHistoricosJPA.setFechaModificacion(destinatario.getFechaModificacion());
						destinatarioHistoricosJPA.setModificadoPor(destinatario.getModificadoPor());
						destinatarioHistoricosJPA.setTipoDestinatario(destinatario.getTipoDestinatario());
						destinatarioHistoricosJPA.setFechaHistorificacion(new Date());
						
						jpa.insert(destinatarioHistoricosJPA);
					}
				}
				
				if(null != listaHistoricoBean && !listaHistoricoBean.isEmpty()){
					for(HistoricoBean historico : listaHistoricoBean){
						HistoricoHistJPA historicoHistJPA = new HistoricoHistJPA();
						historicoHistJPA.setId(historico.getHistoricoId());
						historicoHistJPA.setFecha(historico.getFecha());
						historicoHistJPA.setMensajeId(historico.getMensajeId());
						historicoHistJPA.setEstadoId(historico.getEstadoId());
						historicoHistJPA.setServidorId(historico.getServidorId());
						historicoHistJPA.setPlanificacionId(historico.getPlanificacionId());
						historicoHistJPA.setDescripcion(historico.getDescripcion());
						historicoHistJPA.setSubEstadoId(historico.getSubEstadoId());
						historicoHistJPA.setFechaHistorificado(new Date());
						
						jpa.insert(historicoHistJPA);
					}
				}
				
				if(null != listaGestionEnviosBean && !listaGestionEnviosBean.isEmpty()){
					for(GestionEnvioBean gestionEnvio : listaGestionEnviosBean){
						GestionEnviosHistoricosJPA gestionEnviosHistoricosJPA = new GestionEnviosHistoricosJPA();
						gestionEnviosHistoricosJPA.setAplicacion(gestionEnvio.getAplicacion());
						gestionEnviosHistoricosJPA.setCanal(gestionEnvio.getCanal());
						gestionEnviosHistoricosJPA.setServicio(gestionEnvio.getServicio());
						gestionEnviosHistoricosJPA.setEstado(gestionEnvio.getEstado());
						gestionEnviosHistoricosJPA.setDestinatario(gestionEnvio.getDestinatario());
						gestionEnviosHistoricosJPA.setAplicacionId(gestionEnvio.getAplicacionId());
						gestionEnviosHistoricosJPA.setServicioId(gestionEnvio.getServicioId());
						gestionEnviosHistoricosJPA.setCanalId(gestionEnvio.getCanalId());
						gestionEnviosHistoricosJPA.setLoteEnvioId(gestionEnvio.getIdLote());
						gestionEnviosHistoricosJPA.setNombre(gestionEnvio.getNombre());
						gestionEnviosHistoricosJPA.setId(gestionEnvio.getMensajeId());
						gestionEnviosHistoricosJPA.setUltimoEnvio(gestionEnvio.getUltimoEnvio());
						gestionEnviosHistoricosJPA.setEstadoId(gestionEnvio.getEstadoId());
						gestionEnviosHistoricosJPA.setServidorId(gestionEnvio.getServidorId());
						gestionEnviosHistoricosJPA.setCodigoExterno(gestionEnvio.getCodigoExterno());
						gestionEnviosHistoricosJPA.setAnio(gestionEnvio.getAnio());
						gestionEnviosHistoricosJPA.setMes(gestionEnvio.getMes());
						gestionEnviosHistoricosJPA.setFechaHistorificacion(new Date());
						
						jpa.insert(gestionEnviosHistoricosJPA);
						
					}
					
				}
				
				//Realizamos la eliminacion de las tablas originales
				

				if(null != listaGestionEnviosBean && !listaGestionEnviosBean.isEmpty()){
					for(GestionEnvioBean gestionEnvio : listaGestionEnviosBean){
						GestionEnviosJPA gestionEnviosJPA = new GestionEnviosJPA();
						gestionEnviosJPA.setId(gestionEnvio.getMensajeId());
						
						jpa.delete(gestionEnviosJPA);
					}
					
				}
				
				if(null != listaHistoricoBean && !listaHistoricoBean.isEmpty()){
					for(HistoricoBean historico : listaHistoricoBean){
						HistoricoJPA historicoJPA = new HistoricoJPA();
						historicoJPA.setId(historico.getHistoricoId());
						
						jpa.delete(historicoJPA);
					}
				}
				
				if(null != listaDestinatariosBean && !listaDestinatariosBean.isEmpty()){
					for(DestinatarioBean destinatario : listaDestinatariosBean){
						DestinatarioJPA destinatarioJPA = new DestinatarioJPA();
						destinatarioJPA.setId(destinatario.getDestinatarioId());
						
						jpa.delete(destinatarioJPA);
					}
				}
				
				if(null != listaMensajesAdjuntosHist && !listaMensajesAdjuntosHist.isEmpty()){
					for(MensajesAdjuntosBean mensajesAdjuntos : listaMensajesAdjuntosHist){
						MensajesAdjuntosJPA mensajesAdjuntosJPA = new MensajesAdjuntosJPA();
						mensajesAdjuntosJPA.setId(mensajesAdjuntos.getMensajeAdjuntoId());
						
						jpa.delete(mensajesAdjuntosJPA);
					}
				}
				
				if(null != listaAdjuntosEmailBean && !listaAdjuntosEmailBean.isEmpty()){
					for(AdjuntoEmailBean adjuntoEmail : listaAdjuntosEmailBean){
						AdjuntoEmailJPA adjuntoEmailJPA = new AdjuntoEmailJPA();
						adjuntoEmailJPA.setId(adjuntoEmail.getAdjuntoId());
						
						jpa.delete(adjuntoEmailJPA);
					}
				}
				
				if(null != listaMensajesHistBean && !listaMensajesHistBean.isEmpty()){
					for(MensajeBean mensajeHist : listaMensajesHistBean){
						MensajesJPA mensajeJPA = new MensajesJPA();
						mensajeJPA.setId(mensajeHist.getMensajeId());
						
						jpa.delete(mensajeJPA);
					}
				}
				
				
				LotesEnviosJPA loteEnvioJPA = new LotesEnviosJPA();
				loteEnvioJPA.setId(loteEnvioBean.getLoteEnvioId());
				
				jpa.delete(loteEnvioJPA);


			}
			
			
		}catch (DAOException e){
			throw new BusinessException(e, "errors.job.historico.realizarHistorificacion");
		}
		
		return true;
		
	}
	
	@Override
	@Transactional
	public boolean procesoHistoricoJPALotesEnvio(LotesEnviosHistoricosJPA loteEnvioHistJPA, List<MensajesHistoricosJPA> listaMensajesHistJPA,
			List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailJPA, List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHistJPA,
			List<DestinatarioHistoricosJPA> listaDestinatariosJPA, List<HistoricoHistJPA> listaHistoricoJPA, 
			List<GestionEnviosHistoricosJPA> listaGestionEnviosJPA, List<DestinatariosMensajesHistoricosJPA> listaDetinatariosMensajesHistJPA) throws BusinessException {
		try{
			
			//Realizamos la inserccion de las tablas de historicos
			
			if(null != loteEnvioHistJPA){
				
				Date fechaHistorificacion = new Date();
				
				loteEnvioHistJPA.setFechaHistorificacion(fechaHistorificacion);
				jpa.insert(loteEnvioHistJPA);
				
				if(null != listaMensajesHistJPA && !listaMensajesHistJPA.isEmpty()){
					for(MensajesHistoricosJPA mensajeHistJPA : listaMensajesHistJPA){
						mensajeHistJPA.setFechaHistorificado(fechaHistorificacion);
						
						jpa.insert(mensajeHistJPA);
					}
				}
				
				if(null != listaAdjuntosEmailJPA && !listaAdjuntosEmailJPA.isEmpty()){
					for(AdjuntoEmailHistoricosJPA adjuntoEmailHistoricosJPA : listaAdjuntosEmailJPA){
						adjuntoEmailHistoricosJPA.setFechaHistorificado(fechaHistorificacion);
						
						jpa.insert(adjuntoEmailHistoricosJPA);
					}
				}
				
				if(null != listaMensajesAdjuntosHistJPA && !listaMensajesAdjuntosHistJPA.isEmpty()){
					for(MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistoricosJPA : listaMensajesAdjuntosHistJPA){
						mensajesAdjuntosHistoricosJPA.setFechaHistorificacion(fechaHistorificacion);
						
						jpa.insert(mensajesAdjuntosHistoricosJPA);
					}
				}
				
				if(null != listaDestinatariosJPA && !listaDestinatariosJPA.isEmpty()){
					for(DestinatarioHistoricosJPA destinatarioHistoricosJPA : listaDestinatariosJPA){
						destinatarioHistoricosJPA.setFechaHistorificacion(fechaHistorificacion);
						
						jpa.insert(destinatarioHistoricosJPA);
					}
				}
				
				if(null != listaHistoricoJPA && !listaHistoricoJPA.isEmpty()){
					for(HistoricoHistJPA historicoHistJPA : listaHistoricoJPA){
						historicoHistJPA.setFechaHistorificado(fechaHistorificacion);
						
						jpa.insert(historicoHistJPA);
					}
				}
				
				if(null != listaGestionEnviosJPA && !listaGestionEnviosJPA.isEmpty()){
					for(GestionEnviosHistoricosJPA gestionEnviosHistoricosJPA : listaGestionEnviosJPA){
						gestionEnviosHistoricosJPA.setFechaHistorificacion(fechaHistorificacion);
						
						jpa.insert(gestionEnviosHistoricosJPA);
					}
					
				}
				
				if(null != listaDetinatariosMensajesHistJPA && !listaDetinatariosMensajesHistJPA.isEmpty()){
					for(DestinatariosMensajesHistoricosJPA dmHistoricosJPA : listaDetinatariosMensajesHistJPA){
						dmHistoricosJPA.setFechaHistorificacion(fechaHistorificacion);
						
						jpa.insert(listaDetinatariosMensajesHistJPA);
					}
				}
				
				//Realizamos la eliminacion de las tablas originales
				

				if(null != listaGestionEnviosJPA && !listaGestionEnviosJPA.isEmpty()){
					for(GestionEnviosHistoricosJPA gestionEnviosHistoricosJPA : listaGestionEnviosJPA){
						GestionEnviosJPA gestionEnviosJPA = new GestionEnviosJPA();
						gestionEnviosJPA.setId(gestionEnviosHistoricosJPA.getMensajeId());
						
						jpa.delete(gestionEnviosJPA);
					}
					
				}
				
				if(null != listaHistoricoJPA && !listaHistoricoJPA.isEmpty()){
					for(HistoricoHistJPA historicoHistJPA : listaHistoricoJPA){
						HistoricoJPA historicoJPA = new HistoricoJPA();
						historicoJPA.setId(historicoHistJPA.getHistoricoId());
						
						jpa.delete(historicoJPA);
					}
				}
				
				if(null != listaDestinatariosJPA && !listaDestinatariosJPA.isEmpty()){
					for(DestinatarioHistoricosJPA destinatarioHistoricosJPA : listaDestinatariosJPA){
						DestinatarioJPA destinatarioJPA = new DestinatarioJPA();
						destinatarioJPA.setId(destinatarioHistoricosJPA.getDestinatarioId());
						
						jpa.delete(destinatarioJPA);
					}
				}
				
				if(null != listaMensajesAdjuntosHistJPA && !listaMensajesAdjuntosHistJPA.isEmpty()){
					for(MensajesAdjuntosHistoricosJPA mensajesAdjuntosHistoricosJPA : listaMensajesAdjuntosHistJPA){
						MensajesAdjuntosJPA mensajesAdjuntosJPA = new MensajesAdjuntosJPA();
						mensajesAdjuntosJPA.setId(mensajesAdjuntosHistoricosJPA.getMensajeAdjuntoId());
						
						jpa.delete(mensajesAdjuntosJPA);
					}
				}
				
				if(null != listaAdjuntosEmailJPA && !listaAdjuntosEmailJPA.isEmpty()){
					for(AdjuntoEmailHistoricosJPA adjuntoEmailHistoricosJPA : listaAdjuntosEmailJPA){
						AdjuntoEmailJPA adjuntoEmailJPA = new AdjuntoEmailJPA();
						adjuntoEmailJPA.setId(adjuntoEmailHistoricosJPA.getAdjuntoId());
						
						jpa.delete(adjuntoEmailJPA);
					}
				}
				
				if(null != listaMensajesHistJPA && !listaMensajesHistJPA.isEmpty()){
					for(MensajesHistoricosJPA mensajeHistJPA : listaMensajesHistJPA){
						MensajesJPA mensajeJPA = new MensajesJPA();
						mensajeJPA.setId(mensajeHistJPA.getMensajeId());
						
						jpa.delete(mensajeJPA);
					}
				}
				
				if(null != listaDetinatariosMensajesHistJPA && !listaDetinatariosMensajesHistJPA.isEmpty()){
					for(DestinatariosMensajesHistoricosJPA dmHistoricosJPA : listaDetinatariosMensajesHistJPA){
						DestinatariosMensajesHistoricosJPA dmJPA = new DestinatariosMensajesHistoricosJPA();
						dmJPA.setDestinatariosMensajes(dmHistoricosJPA.getDestinatariosMensajes());
						
						jpa.delete(dmJPA);
					}
				}
				
				
				LotesEnviosJPA loteEnvioJPA = new LotesEnviosJPA();
				loteEnvioJPA.setId(loteEnvioHistJPA.getLoteEnvioId());
				
				jpa.delete(loteEnvioJPA);


			} else {
				return false;
			}
			
			
		}catch (DAOException e){
			throw new BusinessException(e, "errors.job.historico.realizarHistorificacion");
		}
		
		return true;
		
	}



}
