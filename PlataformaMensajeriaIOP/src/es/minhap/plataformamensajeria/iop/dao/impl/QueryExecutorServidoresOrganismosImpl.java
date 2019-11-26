package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorServidoresOrganismosImpl.class);
	
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
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
					"select PROVEEDORUSUARIOSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o "
							+ "on so.ORGANISMOID = o.ORGANISMOID where so.SERVIDORID ="+ proveedorId+"  AND o.DIR3 = '"+codigoOrganismo+"'");
			res = (String) query.uniqueResult();
			if(null != res)
				return res;
			
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@Override
	@Transactional
	public String getPassword(String codigoOrganismo, Integer proveedorId) {
		String res = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
					"select PROVEEDORPASSWORDSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o "
							+ "on so.ORGANISMOID = o.ORGANISMOID where so.SERVIDORID =" + proveedorId
							+ "  AND o.DIR3 = '" + codigoOrganismo + "'");
			res = (String) query.uniqueResult();
			if (null != res)
				return res;

			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> obtenerOrganismosNoActivos(Long idProveedorSMS) {
		List<Long> res = new ArrayList<Long>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			StringBuilder stringQuery = new StringBuilder();
			stringQuery.append("SELECT serv.organismoID FROM TBL_SERVIDORES_ORGANISMOS serv");
			stringQuery.append(" INNER JOIN TBL_ORGANISMOS org ON serv.organismoid = org.organismoid");
			stringQuery.append(" WHERE serv.SERVIDORID=");
			stringQuery.append(idProveedorSMS);
			stringQuery.append(" AND org.activo = 1");
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(stringQuery.toString());
			
			List<Object> rows = query.list();
			for (Object row : rows) {
				res.add(((BigDecimal) row).longValue());
			}		
			
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
