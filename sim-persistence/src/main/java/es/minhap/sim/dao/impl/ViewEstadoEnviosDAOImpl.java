/*
 *
 * archivo: ViewEstadoEnviosDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadoEnviosDAO;
import es.minhap.sim.model.ViewEstadoEnvios;
import es.minhap.sim.model.ViewEstadoEnviosId;
import es.minhap.sim.query.ViewEstadoEnviosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadoEnviosDAOImpl extends
		AbstractHibernateDAO<ViewEstadoEnvios, ViewEstadoEnviosId, ViewEstadoEnviosQuery> implements
		ViewEstadoEnviosDAO {

	@Override
	protected Class<ViewEstadoEnvios> getEntityClass() {
		return ViewEstadoEnvios.class;
	}

	@Override
	protected Class<ViewEstadoEnviosId> getIdentifierClass() {
		return ViewEstadoEnviosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}