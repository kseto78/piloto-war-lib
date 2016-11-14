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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class QueryExecutorOrganismosImpl extends HibernateDaoSupport implements QueryExecutorOrganismos {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorOrganismosImpl.class);
	
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
	public boolean organismoActivoEnServicio(Integer servicioId, String organismoPagador) {
		String codOrganismo = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 as dir3"
							+ "  FROM TBL_ORGANISMOS_SERVICIO, TBL_ORGANISMOS"
							+ " WHERE (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) and "
							+ "TBL_ORGANISMOS_SERVICIO.SERVICIOID = "+servicioId+" and TBL_ORGANISMOS.DIR3 = '"+organismoPagador+"'"
							+ " and TBL_ORGANISMOS.ACTIVO = 1 ");
			codOrganismo = (String) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != codOrganismo && !codOrganismo.isEmpty())? true: false;
	}

	@Override
	@Transactional
	public Integer checkActiveApplication(Integer servicioId) {
		BigDecimal aplicacionActiva = new BigDecimal("0");
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT AP.ACTIVO from tbl_aplicaciones AP, TBL_SERVICIOS S WHERE S.APLICACIONID = AP.APLICACIONID AND S.SERVICIOID=" + servicioId);
			aplicacionActiva = (BigDecimal) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != aplicacionActiva)? aplicacionActiva.intValue() : 0;
	}
	
	@Override
	@Transactional
	public boolean asociadoOrganismoServicio(Integer servicioId, String organismoPagador) {
		String codOrganismo = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 "
					+ "FROM TBL_SERVICIOS, TBL_ORGANISMOS, TBL_ORGANISMOS_SERVICIO "
					+ "WHERE (TBL_ORGANISMOS_SERVICIO.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
					+ "AND (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
					+ "AND TBL_SERVICIOS.SERVICIOID = '" + servicioId+"' AND TBL_ORGANISMOS.DIR3 = '" + organismoPagador+"'");
			codOrganismo = (String) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != codOrganismo && !codOrganismo.isEmpty())? true: false;
	}
}
