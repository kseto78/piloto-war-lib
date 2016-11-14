/*
 *
 * archivo: ViewGestionEnviosDetMultideDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosDetMultideDAO;
import es.minhap.sim.model.ViewGestionEnviosDetMultide;
import es.minhap.sim.model.ViewGestionEnviosDetMultideId;
import es.minhap.sim.query.ViewGestionEnviosDetMultideQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosDetMultideDAOImpl
		extends
		AbstractHibernateDAO<ViewGestionEnviosDetMultide, ViewGestionEnviosDetMultideId, ViewGestionEnviosDetMultideQuery>
		implements ViewGestionEnviosDetMultideDAO {

	@Override
	protected Class<ViewGestionEnviosDetMultide> getEntityClass() {
		return ViewGestionEnviosDetMultide.class;
	}

	@Override
	protected Class<ViewGestionEnviosDetMultideId> getIdentifierClass() {
		return ViewGestionEnviosDetMultideId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}