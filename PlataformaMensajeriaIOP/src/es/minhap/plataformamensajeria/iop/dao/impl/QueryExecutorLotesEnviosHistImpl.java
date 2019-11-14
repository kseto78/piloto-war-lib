package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnviosHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorLotesEnviosHistImpl")
public class QueryExecutorLotesEnviosHistImpl extends HibernateDaoSupport implements QueryExecutorLotesEnviosHist {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorLotesEnviosHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public Integer countLotesByServicioYFecha(Long servicioId, Date fecha) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(le.loteenvioid) from TBL_LOTESENVIOS_HIST le inner join TBL_SERVICIOS s on s.servicioid = le.servicioid "
					+ "where s.servicioid = :servicio and le.fechamodificacion <= :fecha order by le.loteenvioid asc";
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong("servicio", servicioId);
			query.setDate("fecha", fecha);

			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdLotesByServicio(Long servicioId, Date fecha, Integer max, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select le.loteenvioid from TblLotesEnviosHist le where le.tblServicios.servicioid = :servicio and le.fechamodificacion <= :fecha";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong("servicio", servicioId);
			query.setDate("fecha", fecha);
			query.setMaxResults(max);
			query.setFirstResult(firstResult);
			
			 return query.list();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	

}
