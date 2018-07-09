/*
 *
 * archivo: ViewLotesEnviosHistDAOImpl.java
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
import es.minhap.sim.dao.ViewLotesEnviosHistDAO;
import es.minhap.sim.model.ViewLotesEnviosHist;
import es.minhap.sim.model.ViewLotesEnviosHistId;
import es.minhap.sim.query.ViewLotesEnviosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewLotesEnviosHistDAOImpl extends
		AbstractHibernateDAO<ViewLotesEnviosHist, ViewLotesEnviosHistId, ViewLotesEnviosHistQuery> implements
		ViewLotesEnviosHistDAO {

	@Override
	protected Class<ViewLotesEnviosHist> getEntityClass() {
		return ViewLotesEnviosHist.class;
	}

	@Override
	protected Class<ViewLotesEnviosHistId> getIdentifierClass() {
		return ViewLotesEnviosHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}