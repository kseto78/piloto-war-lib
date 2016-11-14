/*
 *
 * archivo: ViewMensajeAdjuntosDetHistDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajeAdjuntosDetHistDAO;
import es.minhap.sim.model.ViewMensajeAdjuntosDetHist;
import es.minhap.sim.model.ViewMensajeAdjuntosDetHistId;
import es.minhap.sim.query.ViewMensajeAdjuntosDetHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajeAdjuntosDetHistDAOImpl extends
		AbstractHibernateDAO<ViewMensajeAdjuntosDetHist, ViewMensajeAdjuntosDetHistId, ViewMensajeAdjuntosDetHistQuery>
		implements ViewMensajeAdjuntosDetHistDAO {

	@Override
	protected Class<ViewMensajeAdjuntosDetHist> getEntityClass() {
		return ViewMensajeAdjuntosDetHist.class;
	}

	@Override
	protected Class<ViewMensajeAdjuntosDetHistId> getIdentifierClass() {
		return ViewMensajeAdjuntosDetHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}