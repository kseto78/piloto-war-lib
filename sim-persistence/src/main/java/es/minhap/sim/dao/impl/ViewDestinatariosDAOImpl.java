/*
 *
 * archivo: ViewDestinatariosDAOImpl.java
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
import es.minhap.sim.dao.ViewDestinatariosDAO;
import es.minhap.sim.model.ViewDestinatarios;
import es.minhap.sim.model.ViewDestinatariosId;
import es.minhap.sim.query.ViewDestinatariosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewDestinatariosDAOImpl extends
		AbstractHibernateDAO<ViewDestinatarios, ViewDestinatariosId, ViewDestinatariosQuery> implements
		ViewDestinatariosDAO {

	@Override
	protected Class<ViewDestinatarios> getEntityClass() {
		return ViewDestinatarios.class;
	}

	@Override
	protected Class<ViewDestinatariosId> getIdentifierClass() {
		return ViewDestinatariosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}