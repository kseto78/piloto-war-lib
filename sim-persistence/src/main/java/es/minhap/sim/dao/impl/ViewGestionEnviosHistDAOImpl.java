/*
 *
 * archivo: ViewGestionEnviosHistDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosHistDAO;
import es.minhap.sim.model.ViewGestionEnviosHist;
import es.minhap.sim.model.ViewGestionEnviosHistId;
import es.minhap.sim.query.ViewGestionEnviosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosHistDAOImpl extends
		AbstractHibernateDAO<ViewGestionEnviosHist, ViewGestionEnviosHistId, ViewGestionEnviosHistQuery> implements
		ViewGestionEnviosHistDAO {

	@Override
	protected Class<ViewGestionEnviosHist> getEntityClass() {
		return ViewGestionEnviosHist.class;
	}

	@Override
	protected Class<ViewGestionEnviosHistId> getIdentifierClass() {
		return ViewGestionEnviosHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}