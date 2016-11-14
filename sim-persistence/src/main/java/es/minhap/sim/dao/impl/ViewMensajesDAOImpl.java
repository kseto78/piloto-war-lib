/*
 *
 * archivo: ViewMensajesDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajesDAO;
import es.minhap.sim.model.ViewMensajes;
import es.minhap.sim.model.ViewMensajesId;
import es.minhap.sim.query.ViewMensajesQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajesDAOImpl extends AbstractHibernateDAO<ViewMensajes, ViewMensajesId, ViewMensajesQuery>
		implements ViewMensajesDAO {

	@Override
	protected Class<ViewMensajes> getEntityClass() {
		return ViewMensajes.class;
	}

	@Override
	protected Class<ViewMensajesId> getIdentifierClass() {
		return ViewMensajesId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}