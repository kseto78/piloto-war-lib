/*
 *
 * archivo: ViewDestinatariosHistDAOImpl.java
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
import es.minhap.sim.dao.ViewDestinatariosHistDAO;
import es.minhap.sim.model.ViewDestinatariosHist;
import es.minhap.sim.model.ViewDestinatariosHistId;
import es.minhap.sim.query.ViewDestinatariosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewDestinatariosHistDAOImpl extends
		AbstractHibernateDAO<ViewDestinatariosHist, ViewDestinatariosHistId, ViewDestinatariosHistQuery> implements
		ViewDestinatariosHistDAO {

	@Override
	protected Class<ViewDestinatariosHist> getEntityClass() {
		return ViewDestinatariosHist.class;
	}

	@Override
	protected Class<ViewDestinatariosHistId> getIdentifierClass() {
		return ViewDestinatariosHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}