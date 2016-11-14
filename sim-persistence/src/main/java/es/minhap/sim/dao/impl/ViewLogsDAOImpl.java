/*
 *
 * archivo: ViewLogsDAOImpl.java
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
import es.minhap.sim.dao.ViewLogsDAO;
import es.minhap.sim.model.ViewLogs;
import es.minhap.sim.model.ViewLogsId;
import es.minhap.sim.query.ViewLogsQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewLogsDAOImpl extends AbstractHibernateDAO<ViewLogs, ViewLogsId, ViewLogsQuery> implements ViewLogsDAO {

	@Override
	protected Class<ViewLogs> getEntityClass() {
		return ViewLogs.class;
	}

	@Override
	protected Class<ViewLogsId> getIdentifierClass() {
		return ViewLogsId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}