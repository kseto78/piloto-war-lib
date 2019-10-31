package es.minhap.plataformamensajeria.iop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorHistoricosHist;
import es.minhap.sim.model.TblHistoricos;
import es.minhap.sim.model.TblHistoricosHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorHistoricosHistImpl")
public class QueryExecutorHistoricosHistImpl extends HibernateDaoSupport implements QueryExecutorHistoricosHist {

	protected static final String R_CONST_1 = "lista";

	protected static final String R_CONST_2 = "unchecked";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorHistoricosHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@SuppressWarnings(R_CONST_2)
	@Override
	public List<TblHistoricosHist> convertHistoricoTOHistoricoHist(List<Long> subList, Integer max, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m from TblHistoricos m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList(R_CONST_1, subList);
			query.setMaxResults(max);
			query.setFirstResult(firstResult);
			return convertTOHistHistoricos(query.list());
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	@Override
	public Integer countConvertHistoricoTOHistoricoHist(List<Long> subList) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m) from TblHistoricos m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);

			query.setParameterList(R_CONST_1, subList);
			return ((Long) query.uniqueResult()).intValue();

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_2)
	@Override
	public List<Long> getIdHistoricosCons(List<Long> listaMensajesHistoricosCons) {
		try {
			List<Long> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select h.historicoid from TblHistoricosHist h where h.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameterList(R_CONST_1, listaMensajesHistoricosCons);
			res = query.list();
			if (null == res){
				return new ArrayList<>();
			}else{
				return res;
			}
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	private List<TblHistoricosHist> convertTOHistHistoricos(List<TblHistoricos> lista) {
		List<TblHistoricosHist> res = new ArrayList<>();
		if (null != lista){
			for (TblHistoricos historico : lista) {
				TblHistoricosHist h = new TblHistoricosHist();
				h.setDescripcion(historico.getDescripcion());
				h.setDestinatariosmensajes(historico.getDestinatariosmensajes());
				h.setFecha(historico.getFecha());
				h.setHistoricoid(historico.getHistoricoid());
				h.setMensajeid(historico.getMensajeid());
				h.setServidorid(historico.getServidorid());
				h.setSubestadoid(historico.getSubestadoid());
				h.setTblEstados(historico.getTblEstados());
				h.setTblPlanificaciones(historico.getTblPlanificaciones());
				res.add(h);
			}	
		}
		return res;
	}


	

	
}
