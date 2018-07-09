/*
 *
 * archivo: ViewAdjuntosDAOImpl.java
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
import es.minhap.sim.dao.ViewAdjuntosDAO;
import es.minhap.sim.model.ViewAdjuntos;
import es.minhap.sim.model.ViewAdjuntosId;
import es.minhap.sim.query.ViewAdjuntosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewAdjuntosDAOImpl extends AbstractHibernateDAO<ViewAdjuntos, ViewAdjuntosId, ViewAdjuntosQuery>
		implements ViewAdjuntosDAO {

	@Override
	protected Class<ViewAdjuntos> getEntityClass() {
		return ViewAdjuntos.class;
	}

	@Override
	protected Class<ViewAdjuntosId> getIdentifierClass() {
		return ViewAdjuntosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}