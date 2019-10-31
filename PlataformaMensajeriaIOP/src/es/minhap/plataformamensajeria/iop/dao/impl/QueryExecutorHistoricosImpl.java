package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorHistoricos;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a
 * partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 * 
 */
@Service
public class QueryExecutorHistoricosImpl extends HibernateDaoSupport implements QueryExecutorHistoricos {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorHistoricosImpl.class);

	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Long getServidorByMensaje(Long mensajeId) {
		Long res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate()
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT servidorid FROM tbl_historicos " + "WHERE mensajeid = ? AND "
									+ "servidorid IS NOT NULL AND ROWNUM = 1 ORDER BY fecha DESC ");
			query.setLong(0, mensajeId);
			BigDecimal servidor = (BigDecimal) query.uniqueResult();

			if (null != servidor) {
				return servidor.longValue();
			}

			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}
}
