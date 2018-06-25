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
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAplicaciones;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_APLICACIONES
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorAplicacionesImpl extends HibernateDaoSupport implements QueryExecutorAplicaciones {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorAplicacionesImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@Override
	@Transactional
	public Long  findAplicacionByMessageId(Long mensajeId) {
		Long res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery("select APP.APLICACIONID from  TBL_APLICACIONES APP, TBL_SERVICIOS SERV, TBL_LOTESENVIOS LOT, TBL_MENSAJES MEN WHERE MEN.mensajeID = "
							+ mensajeId
							+ " and LOT.loteenvioid = MEN.loteenvioid and serv.SERVICIOID = LOT.servicioid AND APP.APLICACIONID = SERV.APLICACIONID");

			res = ((BigDecimal) query.uniqueResult()).longValue();
									
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
