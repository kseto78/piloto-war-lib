/*
 *
 * archivo: ViewServEmailDAOImpl.java
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
import es.minhap.sim.dao.ViewServEmailDAO;
import es.minhap.sim.model.ViewServEmail;
import es.minhap.sim.model.ViewServEmailId;
import es.minhap.sim.query.ViewServEmailQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServEmailDAOImpl extends AbstractHibernateDAO<ViewServEmail, ViewServEmailId, ViewServEmailQuery>
		implements ViewServEmailDAO {

	@Override
	protected Class<ViewServEmail> getEntityClass() {
		return ViewServEmail.class;
	}

	@Override
	protected Class<ViewServEmailId> getIdentifierClass() {
		return ViewServEmailId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}