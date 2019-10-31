package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.List;

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
import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorSubEstados;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a
 * partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 * 
 */
@Service
@Transactional
public class QueryExecutorSubEstadosImpl extends HibernateDaoSupport implements QueryExecutorSubEstados {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorSubEstadosImpl.class);

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
	public EstadosBean getEstadoByCode(String code) {
		EstadosBean res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate()
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"  SELECT estadoid, descripcion" + " FROM tbl_subestados"
									+ " WHERE subestadoid = (SELECT subestadoid" + " FROM tbl_subestado_mensaje"
									+ "  WHERE codigo = ? )");
			query.setString(0, code);
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				res = new EstadosBean();
				res.setEstadoId(((BigDecimal) row[0]).longValue());
				res.setDescripcion((String) row[1]);

			}
			if (null != res) {
				return res;
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
