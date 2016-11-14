/*
 *
 * archivo: ViewMensajeAdjuntosDetalladaDAOImpl.java
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
import es.minhap.sim.dao.ViewMensajeAdjuntosDetalladaDAO;
import es.minhap.sim.model.ViewMensajeAdjuntosDetallada;
import es.minhap.sim.query.ViewMensajeAdjuntosDetalladaQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMensajeAdjuntosDetalladaDAOImpl extends
		AbstractHibernateDAO<ViewMensajeAdjuntosDetallada, Long, ViewMensajeAdjuntosDetalladaQuery> implements
		ViewMensajeAdjuntosDetalladaDAO {

	@Override
	protected Class<ViewMensajeAdjuntosDetallada> getEntityClass() {
		return ViewMensajeAdjuntosDetallada.class;
	}

	@Override
	protected Class<Long> getIdentifierClass() {
		return Long.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}