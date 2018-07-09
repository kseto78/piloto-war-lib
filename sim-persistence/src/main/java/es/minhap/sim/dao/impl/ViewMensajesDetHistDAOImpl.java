/*
 *
 * archivo: ViewMensajesDetHistDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajesDetHistDAO;
import es.minhap.sim.model.ViewMensajesDetHist;
import es.minhap.sim.model.ViewMensajesDetHistId;
import es.minhap.sim.query.ViewMensajesDetHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajesDetHistDAOImpl extends
		AbstractHibernateDAO<ViewMensajesDetHist, ViewMensajesDetHistId, ViewMensajesDetHistQuery> implements
		ViewMensajesDetHistDAO {

	@Override
	protected Class<ViewMensajesDetHist> getEntityClass() {
		return ViewMensajesDetHist.class;
	}

	@Override
	protected Class<ViewMensajesDetHistId> getIdentifierClass() {
		return ViewMensajesDetHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}