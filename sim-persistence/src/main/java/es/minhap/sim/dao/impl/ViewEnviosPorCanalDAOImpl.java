/*
 *
 * archivo: ViewEnviosPorCanalDAOImpl.java
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
import es.minhap.sim.dao.ViewEnviosPorCanalDAO;
import es.minhap.sim.model.ViewEnviosPorCanal;
import es.minhap.sim.model.ViewEnviosPorCanalId;
import es.minhap.sim.query.ViewEnviosPorCanalQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEnviosPorCanalDAOImpl extends
		AbstractHibernateDAO<ViewEnviosPorCanal, ViewEnviosPorCanalId, ViewEnviosPorCanalQuery> implements
		ViewEnviosPorCanalDAO {

	@Override
	protected Class<ViewEnviosPorCanal> getEntityClass() {
		return ViewEnviosPorCanal.class;
	}

	@Override
	protected Class<ViewEnviosPorCanalId> getIdentifierClass() {
		return ViewEnviosPorCanalId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}