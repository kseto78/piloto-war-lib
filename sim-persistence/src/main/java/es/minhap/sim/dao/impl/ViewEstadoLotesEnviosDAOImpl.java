/*
 *
 * archivo: ViewEstadoLotesEnviosDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadoLotesEnviosDAO;
import es.minhap.sim.model.ViewEstadoLotesEnvios;
import es.minhap.sim.model.ViewEstadoLotesEnviosId;
import es.minhap.sim.query.ViewEstadoLotesEnviosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadoLotesEnviosDAOImpl extends
		AbstractHibernateDAO<ViewEstadoLotesEnvios, ViewEstadoLotesEnviosId, ViewEstadoLotesEnviosQuery> implements
		ViewEstadoLotesEnviosDAO {

	@Override
	protected Class<ViewEstadoLotesEnvios> getEntityClass() {
		return ViewEstadoLotesEnvios.class;
	}

	@Override
	protected Class<ViewEstadoLotesEnviosId> getIdentifierClass() {
		return ViewEstadoLotesEnviosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}