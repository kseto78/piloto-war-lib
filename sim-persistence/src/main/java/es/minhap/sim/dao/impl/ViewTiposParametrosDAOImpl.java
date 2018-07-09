/*
 *
 * archivo: ViewTiposParametrosDAOImpl.java
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
import es.minhap.sim.dao.ViewTiposParametrosDAO;
import es.minhap.sim.model.ViewTiposParametros;
import es.minhap.sim.model.ViewTiposParametrosId;
import es.minhap.sim.query.ViewTiposParametrosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewTiposParametrosDAOImpl extends
		AbstractHibernateDAO<ViewTiposParametros, ViewTiposParametrosId, ViewTiposParametrosQuery> implements
		ViewTiposParametrosDAO {

	@Override
	protected Class<ViewTiposParametros> getEntityClass() {
		return ViewTiposParametros.class;
	}

	@Override
	protected Class<ViewTiposParametrosId> getIdentifierClass() {
		return ViewTiposParametrosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}