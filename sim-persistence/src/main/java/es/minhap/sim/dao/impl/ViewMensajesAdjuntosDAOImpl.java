/*
 *
 * archivo: ViewMensajesAdjuntosDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajesAdjuntosDAO;
import es.minhap.sim.model.ViewMensajesAdjuntos;
import es.minhap.sim.model.ViewMensajesAdjuntosId;
import es.minhap.sim.query.ViewMensajesAdjuntosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajesAdjuntosDAOImpl extends
		AbstractHibernateDAO<ViewMensajesAdjuntos, ViewMensajesAdjuntosId, ViewMensajesAdjuntosQuery> implements
		ViewMensajesAdjuntosDAO {

	@Override
	protected Class<ViewMensajesAdjuntos> getEntityClass() {
		return ViewMensajesAdjuntos.class;
	}

	@Override
	protected Class<ViewMensajesAdjuntosId> getIdentifierClass() {
		return ViewMensajesAdjuntosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}