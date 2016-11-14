/*
 *
 * archivo: ViewMensajesAdjuntosHistDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajesAdjuntosHistDAO;
import es.minhap.sim.model.ViewMensajesAdjuntosHist;
import es.minhap.sim.model.ViewMensajesAdjuntosHistId;
import es.minhap.sim.query.ViewMensajesAdjuntosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajesAdjuntosHistDAOImpl extends
		AbstractHibernateDAO<ViewMensajesAdjuntosHist, ViewMensajesAdjuntosHistId, ViewMensajesAdjuntosHistQuery>
		implements ViewMensajesAdjuntosHistDAO {

	@Override
	protected Class<ViewMensajesAdjuntosHist> getEntityClass() {
		return ViewMensajesAdjuntosHist.class;
	}

	@Override
	protected Class<ViewMensajesAdjuntosHistId> getIdentifierClass() {
		return ViewMensajesAdjuntosHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}