/*
 *
 * archivo: ViewRefreshStatusDAOImpl.java
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
import es.minhap.sim.dao.ViewRefreshStatusDAO;
import es.minhap.sim.model.ViewRefreshStatus;
import es.minhap.sim.model.ViewRefreshStatusId;
import es.minhap.sim.query.ViewRefreshStatusQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewRefreshStatusDAOImpl extends
		AbstractHibernateDAO<ViewRefreshStatus, ViewRefreshStatusId, ViewRefreshStatusQuery> implements
		ViewRefreshStatusDAO {

	@Override
	protected Class<ViewRefreshStatus> getEntityClass() {
		return ViewRefreshStatus.class;
	}

	@Override
	protected Class<ViewRefreshStatusId> getIdentifierClass() {
		return ViewRefreshStatusId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}