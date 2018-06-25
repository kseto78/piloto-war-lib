package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajesHist;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblMensajesHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorMensajesHistImpl")
public class QueryExecutorMensajesHistImpl extends HibernateDaoSupport implements QueryExecutorMensajesHist {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorMensajesHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public Integer countMensajesHistorificacion(Long loteId, Date fecha) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m.mensajeid) from TBL_GESTIONENVIOS_HIST ge inner join TBL_MENSAJES_HIST m on ge.mensajeid = m.mensajeid where m.loteenvioid = :lote ";
			if (null != fecha){
					sql = sql + "and ge.ultimoenvio <= :fecha";
			}
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong("lote", loteId);
			if (null != fecha){
				query.setDate("fecha", fecha);
			}

			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdMensajesByLote(Long loteEnvioID, Integer maxResult, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m.mensajeid from TblMensajesHist m where m.tblLotesEnviosHist.loteenvioid = :lote ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong("lote", loteEnvioID);
			query.setMaxResults(maxResult);
			query.setFirstResult(firstResult);
			
			 return query.list();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TblMensajesHist> convertMensajeTOMensajeHist(List<Long> subList, TblLotesEnviosHist loteHistorico) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m from TblMensajes m where m.tblLotesEnvios.loteenvioid = :lote and mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong("lote", loteHistorico.getLoteenvioid());
			query.setParameterList("lista", subList);
			
			return convertTOHist(query.list(), loteHistorico);
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@Override
	@Transactional
	public Integer countMensajesByLote(Long loteId) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m.mensajeid) from TBL_MENSAJES_HIST m  where m.loteenvioid = :lote ";
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong("lote", loteId);
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		
	}
		
	private List<TblMensajesHist> convertTOHist(List<TblMensajes> listaMensajes, TblLotesEnviosHist loteHistorico) {
		List<TblMensajesHist> res = new ArrayList<>();
		
		if (null != listaMensajes){
			for (TblMensajes mensaje : listaMensajes) {
				TblMensajesHist m = new TblMensajesHist();
				m.setCabecera(mensaje.getCabecera());
				m.setCodigoexterno(mensaje.getCodigoexterno());
				m.setCodorganismo(mensaje.getCodorganismo());
				m.setCodorganismopagador(mensaje.getCodorganismopagador());
				m.setCodsia(mensaje.getCodsia());
				m.setCreadopor(mensaje.getCreadopor());
				m.setCuerpo(mensaje.getCuerpo());
				m.setCuerpo_clob(mensaje.getCuerpo_clob());
				m.setCuerpofile(mensaje.getCuerpofile());
				m.setDocusuario(mensaje.getDocusuario());
				m.setEstadoactual(mensaje.getEstadoactual());
				m.setFechacreacion(mensaje.getFechacreacion());
				m.setFechamodificacion(mensaje.getFechamodificacion());
				m.setIcono(mensaje.getIcono());
				m.setIdenviossms(mensaje.getIdenviossms());
				m.setMensajeid(mensaje.getMensajeid());
				m.setModificadopor(mensaje.getModificadopor());
				m.setNombreusuario(mensaje.getNombreusuario());
				m.setNumeroenvios(mensaje.getNumeroenvios());
				m.setPrioridad(mensaje.getPrioridad());
				m.setSonido(mensaje.getSonido());
				m.setTblEstados(mensaje.getTblEstados());
				m.setTblLotesEnviosHist(loteHistorico);
				m.setTelefono(mensaje.getTelefono());
				m.setTipocodificacion(mensaje.getTipocodificacion());
				m.setTipocuerpo(mensaje.getTipocuerpo());
				m.setTipomensaje(mensaje.getTipomensaje());
				m.setUim(mensaje.getUim());
				m.setUltimoenvio(mensaje.getUltimoenvio());
				m.setUltimoidhistorico(mensaje.getUltimoidhistorico());
				res.add(m);
			}
			
		}
		return res;
	}

	
	
}
