package es.minhap.plataformamensajeria.iop.dao.impl;

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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidoresOrganismos;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorServidoresOrganismosImpl extends HibernateDaoSupport implements QueryExecutorServidoresOrganismos {

	protected static final String R_CONST_1 = "on so.ORGANISMOID = o.ORGANISMOID where so.SERVIDORID =";

	protected static final String R_CONST_2 = "  AND o.DIR3 = '";

	protected static final String R_CONST_3 = "'";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorServidoresOrganismosImpl.class);
	
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
	public String getUsuario(String codigoOrganismo, Integer proveedorId) {
		String res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append("select PROVEEDORUSUARIOSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o ");
			queryString.append(R_CONST_1);
			queryString.append("?");
			queryString.append(R_CONST_2);
			queryString.append("?");
			queryString.append(R_CONST_3);
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			
			query.setInteger(0, proveedorId);
			query.setString(1, codigoOrganismo);
			
			res = (String) query.uniqueResult();
			if(null != res) {
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

	@Override
	@Transactional
	public String getPassword(String codigoOrganismo, Integer proveedorId) {
		String res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append("select PROVEEDORPASSWORDSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o ");
			queryString.append(R_CONST_1);
			queryString.append("?");
			queryString.append(R_CONST_2);
			queryString.append("?");
			queryString.append(R_CONST_3);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			query.setInteger(0, proveedorId);
			query.setString(1, codigoOrganismo);
			
			res = (String) query.uniqueResult();
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
