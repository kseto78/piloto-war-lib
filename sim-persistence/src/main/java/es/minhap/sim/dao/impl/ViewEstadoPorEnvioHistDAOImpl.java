/*
 *
 * archivo: ViewEstadoPorEnvioHistDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadoPorEnvioHistDAO;
import es.minhap.sim.model.ViewEstadoPorEnvioHist;
import es.minhap.sim.model.ViewEstadoPorEnvioHistId;
import es.minhap.sim.query.ViewEstadoPorEnvioHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadoPorEnvioHistDAOImpl extends
		AbstractHibernateDAO<ViewEstadoPorEnvioHist, ViewEstadoPorEnvioHistId, ViewEstadoPorEnvioHistQuery> implements
		ViewEstadoPorEnvioHistDAO {

	@Override
	protected Class<ViewEstadoPorEnvioHist> getEntityClass() {
		return ViewEstadoPorEnvioHist.class;
	}

	@Override
	protected Class<ViewEstadoPorEnvioHistId> getIdentifierClass() {
		return ViewEstadoPorEnvioHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}