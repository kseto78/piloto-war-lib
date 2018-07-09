/*
 *
 * archivo: ViewGestionEnviosDetHistDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionEnviosDetHistDAO;
import es.minhap.sim.model.ViewGestionEnviosDetHist;
import es.minhap.sim.model.ViewGestionEnviosDetHistId;
import es.minhap.sim.query.ViewGestionEnviosDetHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionEnviosDetHistDAOImpl extends
		AbstractHibernateDAO<ViewGestionEnviosDetHist, ViewGestionEnviosDetHistId, ViewGestionEnviosDetHistQuery>
		implements ViewGestionEnviosDetHistDAO {

	@Override
	protected Class<ViewGestionEnviosDetHist> getEntityClass() {
		return ViewGestionEnviosDetHist.class;
	}

	@Override
	protected Class<ViewGestionEnviosDetHistId> getIdentifierClass() {
		return ViewGestionEnviosDetHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}