/*
 *
 * archivo: ViewAdjuntosHistDAOImpl.java
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
import es.minhap.sim.dao.ViewAdjuntosHistDAO;
import es.minhap.sim.model.ViewAdjuntosHist;
import es.minhap.sim.model.ViewAdjuntosHistId;
import es.minhap.sim.query.ViewAdjuntosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewAdjuntosHistDAOImpl extends
		AbstractHibernateDAO<ViewAdjuntosHist, ViewAdjuntosHistId, ViewAdjuntosHistQuery> implements
		ViewAdjuntosHistDAO {

	@Override
	protected Class<ViewAdjuntosHist> getEntityClass() {
		return ViewAdjuntosHist.class;
	}

	@Override
	protected Class<ViewAdjuntosHistId> getIdentifierClass() {
		return ViewAdjuntosHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}