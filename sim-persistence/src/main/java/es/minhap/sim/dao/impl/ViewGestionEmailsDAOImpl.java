/*
 *
 * archivo: ViewGestionEmailsDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEmailsDAO;
import es.minhap.sim.model.ViewGestionEmails;
import es.minhap.sim.model.ViewGestionEmailsId;
import es.minhap.sim.query.ViewGestionEmailsQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEmailsDAOImpl extends
		AbstractHibernateDAO<ViewGestionEmails, ViewGestionEmailsId, ViewGestionEmailsQuery> implements
		ViewGestionEmailsDAO {

	@Override
	protected Class<ViewGestionEmails> getEntityClass() {
		return ViewGestionEmails.class;
	}

	@Override
	protected Class<ViewGestionEmailsId> getIdentifierClass() {
		return ViewGestionEmailsId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}