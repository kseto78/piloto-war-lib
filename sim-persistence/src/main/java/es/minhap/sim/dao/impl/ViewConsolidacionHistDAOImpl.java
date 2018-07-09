/*
 *
 * archivo: ViewConsolidacionHistDAOImpl.java
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
import es.minhap.sim.dao.ViewConsolidacionHistDAO;
import es.minhap.sim.model.ViewConsolidacionHist;
import es.minhap.sim.model.ViewConsolidacionHistId;
import es.minhap.sim.query.ViewConsolidacionHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewConsolidacionHistDAOImpl extends
		AbstractHibernateDAO<ViewConsolidacionHist, ViewConsolidacionHistId, ViewConsolidacionHistQuery> implements
		ViewConsolidacionHistDAO {

	@Override
	protected Class<ViewConsolidacionHist> getEntityClass() {
		return ViewConsolidacionHist.class;
	}

	@Override
	protected Class<ViewConsolidacionHistId> getIdentifierClass() {
		return ViewConsolidacionHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}