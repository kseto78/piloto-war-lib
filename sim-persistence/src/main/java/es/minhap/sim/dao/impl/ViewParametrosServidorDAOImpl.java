/*
 *
 * archivo: ViewParametrosServidorDAOImpl.java
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
import es.minhap.sim.dao.ViewParametrosServidorDAO;
import es.minhap.sim.model.ViewParametrosServidor;
import es.minhap.sim.model.ViewParametrosServidorId;
import es.minhap.sim.query.ViewParametrosServidorQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewParametrosServidorDAOImpl extends
		AbstractHibernateDAO<ViewParametrosServidor, ViewParametrosServidorId, ViewParametrosServidorQuery> implements
		ViewParametrosServidorDAO {

	@Override
	protected Class<ViewParametrosServidor> getEntityClass() {
		return ViewParametrosServidor.class;
	}

	@Override
	protected Class<ViewParametrosServidorId> getIdentifierClass() {
		return ViewParametrosServidorId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}