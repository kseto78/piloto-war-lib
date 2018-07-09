/*
 *
 * archivo: ViewLotesEnviosDAOImpl.java
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
import es.minhap.sim.dao.ViewLotesEnviosDAO;
import es.minhap.sim.model.ViewLotesEnvios;
import es.minhap.sim.model.ViewLotesEnviosId;
import es.minhap.sim.query.ViewLotesEnviosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewLotesEnviosDAOImpl extends
		AbstractHibernateDAO<ViewLotesEnvios, ViewLotesEnviosId, ViewLotesEnviosQuery> implements ViewLotesEnviosDAO {

	@Override
	protected Class<ViewLotesEnvios> getEntityClass() {
		return ViewLotesEnvios.class;
	}

	@Override
	protected Class<ViewLotesEnviosId> getIdentifierClass() {
		return ViewLotesEnviosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}