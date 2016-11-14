package es.minhap.plataformamensajeria.iop.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_LOTESENVIOS
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorLotesEnviosImpl extends HibernateDaoSupport implements QueryExecutorLotesEnvios {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorLotesEnviosImpl.class);
	
	private static final String UPDATE_END= "search - end";
	
	private static final String UPDATE_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Integer updateStatusLoteByIdMensaje(String estado, Long idMensaje) {
		Integer res = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
					"UPDATE TBL_LOTESENVIOS SET ESTADOENVIOID=(SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE = '"+estado+"') where "
							+ "LOTEENVIOID = (SELECT LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = "+idMensaje+")");

			res = (Integer) query.executeUpdate();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@Override
	public Boolean esMultidestinatario(Long mensajeId) {
		Boolean multidestinatario;
		
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
						+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = "
						+ mensajeId + " AND lt.MULTIDESTINATARIO = 1");
		multidestinatario =  !query.list().isEmpty();
		return multidestinatario;
	}
}
