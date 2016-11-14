/*
 *
 * archivo: ViewProveedoresPrioridadDAOImpl.java
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
import es.minhap.sim.dao.ViewProveedoresPrioridadDAO;
import es.minhap.sim.model.ViewProveedoresPrioridad;
import es.minhap.sim.model.ViewProveedoresPrioridadId;
import es.minhap.sim.query.ViewProveedoresPrioridadQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewProveedoresPrioridadDAOImpl extends
		AbstractHibernateDAO<ViewProveedoresPrioridad, ViewProveedoresPrioridadId, ViewProveedoresPrioridadQuery>
		implements ViewProveedoresPrioridadDAO {

	@Override
	protected Class<ViewProveedoresPrioridad> getEntityClass() {
		return ViewProveedoresPrioridad.class;
	}

	@Override
	protected Class<ViewProveedoresPrioridadId> getIdentifierClass() {
		return ViewProveedoresPrioridadId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}