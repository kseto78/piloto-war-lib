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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnvios;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_LOTESENVIOS
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorLotesEnviosImpl extends HibernateDaoSupport implements QueryExecutorLotesEnvios {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorLotesEnviosImpl.class);
	
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
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
					"UPDATE TBL_LOTESENVIOS SET ESTADOENVIOID=(SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE = ? ) where "
							+ "LOTEENVIOID = (SELECT LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = ?)");
			query.setString(0, estado);
			query.setLong(1, idMensaje);
			res = (Integer) query.executeUpdate();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@Override
	public Boolean esMultidestinatario(Long mensajeId) {
		Boolean multidestinatario;
		
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
						+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = ? AND lt.MULTIDESTINATARIO = 1");
		query.setLong(0, mensajeId);
		return !query.list().isEmpty();
	}
	
	@Override
	@Transactional
	public Long getIdLoteByIdMensaje(Long idMensaje) {
		BigDecimal res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT loteenvioid "
							+ "FROM tbl_mensajes "
							+ "WHERE mensajeid = ?");
			res = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();	
	}
}
