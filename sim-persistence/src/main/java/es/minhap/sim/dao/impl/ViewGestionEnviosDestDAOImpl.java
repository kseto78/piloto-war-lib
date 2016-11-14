/*
 *
 * archivo: ViewGestionEnviosDestDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosDestDAO;
import es.minhap.sim.model.ViewGestionEnviosDest;
import es.minhap.sim.model.ViewGestionEnviosDestId;
import es.minhap.sim.query.ViewGestionEnviosDestQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosDestDAOImpl extends
		AbstractHibernateDAO<ViewGestionEnviosDest, ViewGestionEnviosDestId, ViewGestionEnviosDestQuery> implements
		ViewGestionEnviosDestDAO {

	@Override
	protected Class<ViewGestionEnviosDest> getEntityClass() {
		return ViewGestionEnviosDest.class;
	}

	@Override
	protected Class<ViewGestionEnviosDestId> getIdentifierClass() {
		return ViewGestionEnviosDestId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}