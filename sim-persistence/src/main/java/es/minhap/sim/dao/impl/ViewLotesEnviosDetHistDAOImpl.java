/*
 *
 * archivo: ViewLotesEnviosDetHistDAOImpl.java
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
import es.minhap.sim.dao.ViewLotesEnviosDetHistDAO;
import es.minhap.sim.model.ViewLotesEnviosDetHist;
import es.minhap.sim.model.ViewLotesEnviosDetHistId;
import es.minhap.sim.query.ViewLotesEnviosDetHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewLotesEnviosDetHistDAOImpl extends
		AbstractHibernateDAO<ViewLotesEnviosDetHist, ViewLotesEnviosDetHistId, ViewLotesEnviosDetHistQuery> implements
		ViewLotesEnviosDetHistDAO {

	@Override
	protected Class<ViewLotesEnviosDetHist> getEntityClass() {
		return ViewLotesEnviosDetHist.class;
	}

	@Override
	protected Class<ViewLotesEnviosDetHistId> getIdentifierClass() {
		return ViewLotesEnviosDetHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}