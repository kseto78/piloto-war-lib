package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnviosHist;
import es.minhap.plataformamensajeria.iop.manager.TblProcesoInformesServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblProcesoInformesServicios;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblServiciosQuery;
import es.mpr.plataformamensajeria.beans.InformesServiciosBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoInformesServicios;

/**
 * <p>Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioProcesoInformesServiciosImpl")
public class ServicioProcesoInformesServiciosImpl implements ServicioProcesoInformesServicios{
	
	private static Logger logger = Logger.getLogger(ServicioProcesoInformesServiciosImpl.class);
	
	@Resource(name = "TblProcesoInformesServiciosManagerImpl")
	private TblProcesoInformesServiciosManager tblProcesoInformesServiciosManager;
	
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;
	

	@Autowired
	private QueryExecutorGestionEnvios queryExecutorGestionEnvios;
	
	@Autowired
	private QueryExecutorGestionEnviosHist queryExecutorGestionEnviosHist;
	
	
	private static final String CODORGANISMO = "codorganismo";
	private static final String CODORGANISMOPAGADOR = "codorganismopagador";
	private static final String CODSIA = "codsia";
	private static final String ESTADO = "estado";

	////MIGRADO
	@Override
	@Transactional
	public Long newServicioProcesoInformesServicios(ProcesoInformesServiciosBean procesoInformesServiciosBean) {
		try{
			TblProcesoInformesServicios pis = new TblProcesoInformesServicios();
			pis.setCodigoestado(procesoInformesServiciosBean.getCodigoEstado());
			pis.setDescripcionestado(procesoInformesServiciosBean.getDescripcionEstado());
			pis.setFechacreacion(new Date());
			pis.setFechafin(procesoInformesServiciosBean.getFechaFin());
			pis.setFechainicio(procesoInformesServiciosBean.getFechaInicio());
			
			return tblProcesoInformesServiciosManager.insertar(pis);
			
		}catch (Exception e){
			logger.error("ServicioProcesoInformesServiciosImpl - newServicioProcesoInformesServicios:" + e);
			return null;
		}
	}
	
	///MIGRADO
	@Override
	@Transactional
	public List<InformesServiciosEstadoBean> obtenerInformesServiciosEstado(Integer servicioId, Integer anno, Integer mes) throws BusinessException{	
		List<InformesServiciosEstadoBean> result = new ArrayList<>();

		try {
			List<InformesServiciosBean> lista = obtenerInformesServicios(servicioId, anno, mes, ESTADO);
			for (InformesServiciosBean is : lista) {
				InformesServiciosEstadoBean ise = new InformesServiciosEstadoBean();
				ise.setAnno(is.getAnno());
				ise.setEstado(is.getEstado());
				ise.setMes(is.getMes());
				ise.setNumTotal(is.getNumTotal());
				ise.setServicioId(is.getServicioId());
				result.add(ise);
			}
		} catch (Exception e) {
			logger.error("ServicioProcesoInformesServiciosImpl - obtenerInformesServiciosEstado:" + e);
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} 
		
		return result;
		
	}
	
	

	////MIGRADO
	@Override
	@Transactional
	public List<InformesServiciosCodOrgBean> obtenerInformesServiciosCodOrg(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		List<InformesServiciosCodOrgBean> result = new ArrayList<>();
		
		try {
			List<InformesServiciosBean> lista = obtenerInformesServicios(servicioId, anno, mes, CODORGANISMO);
			for (InformesServiciosBean is : lista) {
				InformesServiciosCodOrgBean ise = new InformesServiciosCodOrgBean();
				ise.setAnno(is.getAnno());
				ise.setCodOrganismo(is.getCodOrganismo());
				ise.setMes(is.getMes());
				ise.setNumTotal(is.getNumTotal());
				ise.setServicioId(is.getServicioId());
				result.add(ise);
			}
		} catch (Exception e) {
			logger.error("ServicioProcesoInformesServiciosImpl - obtenerInformesServiciosCodOrg:" + e);
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} 
		
		return result;
		
	}
	
	////MIGRADO
	@Override
	@Transactional
	public List<InformesServiciosCodSiaBean> obtenerInformesServiciosCodSia(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		List<InformesServiciosCodSiaBean> result = new ArrayList<>();
		
		try {
			List<InformesServiciosBean> lista = obtenerInformesServicios(servicioId, anno, mes, CODSIA);
			for (InformesServiciosBean is : lista) {
				InformesServiciosCodSiaBean ise = new InformesServiciosCodSiaBean();
				ise.setAnno(is.getAnno());
				ise.setCodSia(is.getCodSia());
				ise.setMes(is.getMes());
				ise.setNumTotal(is.getNumTotal());
				ise.setServicioId(is.getServicioId());
				result.add(ise);
			}
		} catch (Exception e) {
			logger.error("ServicioProcesoInformesServiciosImpl - obtenerInformesServiciosCodSia:" + e);
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} 
		
		return result;
	}
	
	///MIGRADO
	@Override
	@Transactional
	public List<InformesServiciosCodOrgPagadorBean> obtenerInformesServiciosCodOrgPagador(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		List<InformesServiciosCodOrgPagadorBean> result = new ArrayList<>();
		
		try {
			List<InformesServiciosBean> lista = obtenerInformesServicios(servicioId, anno, mes, CODORGANISMOPAGADOR);
			for (InformesServiciosBean is : lista) {
				InformesServiciosCodOrgPagadorBean ise = new InformesServiciosCodOrgPagadorBean();
				ise.setAnno(is.getAnno());
				ise.setCodOrganismoPagador(is.getCodOrganismoPagador());
				ise.setMes(is.getMes());
				ise.setNumTotal(is.getNumTotal());
				ise.setServicioId(is.getServicioId());
				result.add(ise);
			}
		} catch (Exception e) {
			logger.error("ServicioProcesoInformesServiciosImpl - obtenerInformesServiciosCodSia:" + e);
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} 
		
		return result;
	}
	
////MIGRADO
	private List<InformesServiciosBean> obtenerInformesServicios(Integer servicioId, Integer anno, Integer mes, String columna) throws BusinessException{
		List<InformesServiciosBean> result = new ArrayList<>();

		try {
			
			//1- sacamos si el servicio está activo y no eliminado
			TblServiciosQuery query = new TblServiciosQuery();
			query.setEliminadoIsNull(true);
			query.setInformesactivo(true);
			query.setServicioid(servicioId.longValue());
			List<TblServicios> lista = tblServiciosManager.getServicios(query);
			
			if (null != lista && !lista.isEmpty()){
				//2- obtenemos el listado de gestionEnvios
				List<TblGestionEnvios> listaGE = queryExecutorGestionEnvios.getInformesServiciosBy(servicioId.longValue(), anno, mes, columna);
				result.addAll(getInformesServiciosBean(listaGE));
				
				//3- obtenemos el listado de gestionEnviosHistorico
				List<TblGestionEnviosHist> listaGEH = queryExecutorGestionEnviosHist.getInformesServiciosBy(servicioId.longValue(), anno, mes, columna);
				result.addAll(getInformesServiciosHistBean(listaGEH));
			}	
		} catch (Exception e) {
			logger.error("ServicioProcesoInformesServiciosImpl - obtenerInformesServicios:" + e);
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} 
		
		return result;
	}
	
	////MIGRADO
	private List<InformesServiciosBean> getInformesServiciosBean(List<TblGestionEnvios> listaGE) {
		List<InformesServiciosBean> res = new ArrayList<>();
		
		if (null != listaGE && !listaGE.isEmpty()){
			for (TblGestionEnvios ge : listaGE) {
				InformesServiciosBean informeServicio = new InformesServiciosBean();
				informeServicio.setServicioId(ge.getServicioid().intValue());
				informeServicio.setCodOrganismo(ge.getCodorganismo());
				informeServicio.setCodOrganismoPagador(ge.getCodorganismopagador());
				informeServicio.setCodSia(ge.getCodsia());
				informeServicio.setAnno(ge.getAnio());
				informeServicio.setMes(ge.getMes());
				informeServicio.setEstado(ge.getEstado());
				informeServicio.setNumTotal(ge.getNumTotal());
				res.add(informeServicio);
			}
			
		}
		
		return res;
	}
	
////MIGRADO
	private List<InformesServiciosBean> getInformesServiciosHistBean(List<TblGestionEnviosHist> listaGEH) {
		List<InformesServiciosBean> res = new ArrayList<>();
		
		if (null != listaGEH && !listaGEH.isEmpty()){
			for (TblGestionEnviosHist ge : listaGEH) {
				InformesServiciosBean informeServicio = new InformesServiciosBean();
				informeServicio.setServicioId(ge.getServicioid().intValue());
				informeServicio.setCodOrganismo(ge.getCodorganismo());
				informeServicio.setCodOrganismoPagador(ge.getCodorganismopagador());
				informeServicio.setCodSia(ge.getCodsia());
				informeServicio.setAnno(ge.getAnio());
				informeServicio.setMes(ge.getMes());
				informeServicio.setEstado(ge.getEstado());
				informeServicio.setNumTotal(ge.getNumTotal());
				res.add(informeServicio);
			}
			
		}
		
		return res;
	}
}
