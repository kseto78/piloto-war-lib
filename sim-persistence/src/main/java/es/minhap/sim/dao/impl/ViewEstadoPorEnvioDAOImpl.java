/*
 *
 * archivo: ViewEstadoPorEnvioDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadoPorEnvioDAO;
import es.minhap.sim.model.ViewEstadoPorEnvio;
import es.minhap.sim.model.ViewEstadoPorEnvioId;
import es.minhap.sim.query.ViewEstadoPorEnvioQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadoPorEnvioDAOImpl extends
		AbstractHibernateDAO<ViewEstadoPorEnvio, ViewEstadoPorEnvioId, ViewEstadoPorEnvioQuery> implements
		ViewEstadoPorEnvioDAO {

	@Override
	protected Class<ViewEstadoPorEnvio> getEntityClass() {
		return ViewEstadoPorEnvio.class;
	}

	@Override
	protected Class<ViewEstadoPorEnvioId> getIdentifierClass() {
		return ViewEstadoPorEnvioId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}