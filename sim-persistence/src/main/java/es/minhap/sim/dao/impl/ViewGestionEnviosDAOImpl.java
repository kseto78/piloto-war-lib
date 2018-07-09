/*
 *
 * archivo: ViewGestionEnviosDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosDAO;
import es.minhap.sim.model.ViewGestionEnvios;
import es.minhap.sim.model.ViewGestionEnviosId;
import es.minhap.sim.query.ViewGestionEnviosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosDAOImpl extends
		AbstractHibernateDAO<ViewGestionEnvios, ViewGestionEnviosId, ViewGestionEnviosQuery> implements
		ViewGestionEnviosDAO {

	@Override
	protected Class<ViewGestionEnvios> getEntityClass() {
		return ViewGestionEnvios.class;
	}

	@Override
	protected Class<ViewGestionEnviosId> getIdentifierClass() {
		return ViewGestionEnviosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}