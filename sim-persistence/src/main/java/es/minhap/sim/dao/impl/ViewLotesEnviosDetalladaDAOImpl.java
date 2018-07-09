/*
 *
 * archivo: ViewLotesEnviosDetalladaDAOImpl.java
 *
 * Proyecto: Administracion SIM
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.sim.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.sim.dao.ViewLotesEnviosDetalladaDAO;
import es.minhap.sim.model.ViewLotesEnviosDetallada;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewLotesEnviosDetalladaDAOImpl extends
		AbstractHibernateDAO<ViewLotesEnviosDetallada, Long, ViewLotesEnviosDetalladaQuery> implements
		ViewLotesEnviosDetalladaDAO {

	@Override
	protected Class<ViewLotesEnviosDetallada> getEntityClass() {
		return ViewLotesEnviosDetallada.class;
	}

	@Override
	protected Class<Long> getIdentifierClass() {
		return Long.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}