/*
 *
 * archivo: ViewRefresgStatusDestDAOImpl.java
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
import es.minhap.sim.dao.ViewRefresgStatusDestDAO;
import es.minhap.sim.model.ViewRefresgStatusDest;
import es.minhap.sim.model.ViewRefresgStatusDestId;
import es.minhap.sim.query.ViewRefresgStatusDestQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewRefresgStatusDestDAOImpl extends
		AbstractHibernateDAO<ViewRefresgStatusDest, ViewRefresgStatusDestId, ViewRefresgStatusDestQuery> implements
		ViewRefresgStatusDestDAO {

	@Override
	protected Class<ViewRefresgStatusDest> getEntityClass() {
		return ViewRefresgStatusDest.class;
	}

	@Override
	protected Class<ViewRefresgStatusDestId> getIdentifierClass() {
		return ViewRefresgStatusDestId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}