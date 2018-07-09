/*
 *
 * archivo: ViewEstadisticaDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadisticaDAO;
import es.minhap.sim.model.ViewEstadistica;
import es.minhap.sim.model.ViewEstadisticaId;
import es.minhap.sim.query.ViewEstadisticaQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadisticaDAOImpl extends
		AbstractHibernateDAO<ViewEstadistica, ViewEstadisticaId, ViewEstadisticaQuery> implements ViewEstadisticaDAO {

	@Override
	protected Class<ViewEstadistica> getEntityClass() {
		return ViewEstadistica.class;
	}

	@Override
	protected Class<ViewEstadisticaId> getIdentifierClass() {
		return ViewEstadisticaId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}