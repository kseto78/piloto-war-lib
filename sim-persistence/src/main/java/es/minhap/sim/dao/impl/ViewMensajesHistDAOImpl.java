/*
 *
 * archivo: ViewMensajesHistDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajesHistDAO;
import es.minhap.sim.model.ViewMensajesHist;
import es.minhap.sim.model.ViewMensajesHistId;
import es.minhap.sim.query.ViewMensajesHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajesHistDAOImpl extends
		AbstractHibernateDAO<ViewMensajesHist, ViewMensajesHistId, ViewMensajesHistQuery> implements
		ViewMensajesHistDAO {

	@Override
	protected Class<ViewMensajesHist> getEntityClass() {
		return ViewMensajesHist.class;
	}

	@Override
	protected Class<ViewMensajesHistId> getIdentifierClass() {
		return ViewMensajesHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}