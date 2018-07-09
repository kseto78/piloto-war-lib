/*
 *
 * archivo: ViewServidoresDAOImpl.java
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
import es.minhap.sim.dao.ViewServidoresDAO;
import es.minhap.sim.model.ViewServidores;
import es.minhap.sim.model.ViewServidoresId;
import es.minhap.sim.query.ViewServidoresQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServidoresDAOImpl extends AbstractHibernateDAO<ViewServidores, ViewServidoresId, ViewServidoresQuery>
		implements ViewServidoresDAO {

	@Override
	protected Class<ViewServidores> getEntityClass() {
		return ViewServidores.class;
	}

	@Override
	protected Class<ViewServidoresId> getIdentifierClass() {
		return ViewServidoresId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}