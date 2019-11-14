package es.minhap.plataformamensajeria.iop.dao.impl;

import java.util.ArrayList;
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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewEnviosPendientesPorCanal;
import es.minhap.sim.model.ViewEnviosPendientesPorCanal;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla
 * 
 * @author everis
 * 
 */
@Service
@Transactional
public class QueryExecutorViewEnviosPendientesPorCanalImpl extends HibernateDaoSupport implements
QueryExecutorViewEnviosPendientesPorCanal {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorViewEnviosPendientesPorCanalImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";

	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewEnviosPendientesPorCanal> getAll() {
		List<ViewEnviosPendientesPorCanal> res = new ArrayList<>();
		try {

			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}

			Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("FROM ViewEnviosPendientesPorCanal");

			res = query.list();
									
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
	} catch (Exception e) {
		log.error(HAS_ERROR, e);
		throw new ApplicationException(e);
	}
		return res;
	}

	
}
