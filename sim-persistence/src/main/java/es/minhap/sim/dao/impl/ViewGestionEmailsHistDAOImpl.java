/*
 *
 * archivo: ViewGestionEmailsHistDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEmailsHistDAO;
import es.minhap.sim.model.ViewGestionEmailsHist;
import es.minhap.sim.model.ViewGestionEmailsHistId;
import es.minhap.sim.query.ViewGestionEmailsHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEmailsHistDAOImpl extends
		AbstractHibernateDAO<ViewGestionEmailsHist, ViewGestionEmailsHistId, ViewGestionEmailsHistQuery> implements
		ViewGestionEmailsHistDAO {

	@Override
	protected Class<ViewGestionEmailsHist> getEntityClass() {
		return ViewGestionEmailsHist.class;
	}

	@Override
	protected Class<ViewGestionEmailsHistId> getIdentifierClass() {
		return ViewGestionEmailsHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}