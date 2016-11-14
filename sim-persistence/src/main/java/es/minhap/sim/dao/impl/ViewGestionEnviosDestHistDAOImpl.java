/*
 *
 * archivo: ViewGestionEnviosDestHistDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosDestHistDAO;
import es.minhap.sim.model.ViewGestionEnviosDestHist;
import es.minhap.sim.model.ViewGestionEnviosDestHistId;
import es.minhap.sim.query.ViewGestionEnviosDestHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosDestHistDAOImpl extends
		AbstractHibernateDAO<ViewGestionEnviosDestHist, ViewGestionEnviosDestHistId, ViewGestionEnviosDestHistQuery>
		implements ViewGestionEnviosDestHistDAO {

	@Override
	protected Class<ViewGestionEnviosDestHist> getEntityClass() {
		return ViewGestionEnviosDestHist.class;
	}

	@Override
	protected Class<ViewGestionEnviosDestHistId> getIdentifierClass() {
		return ViewGestionEnviosDestHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}