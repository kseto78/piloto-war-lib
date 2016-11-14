/*
 *
 * archivo: ViewReceptoresPrioridadDAOImpl.java
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
import es.minhap.sim.dao.ViewReceptoresPrioridadDAO;
import es.minhap.sim.model.ViewReceptoresPrioridad;
import es.minhap.sim.model.ViewReceptoresPrioridadId;
import es.minhap.sim.query.ViewReceptoresPrioridadQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewReceptoresPrioridadDAOImpl extends
		AbstractHibernateDAO<ViewReceptoresPrioridad, ViewReceptoresPrioridadId, ViewReceptoresPrioridadQuery>
		implements ViewReceptoresPrioridadDAO {

	@Override
	protected Class<ViewReceptoresPrioridad> getEntityClass() {
		return ViewReceptoresPrioridad.class;
	}

	@Override
	protected Class<ViewReceptoresPrioridadId> getIdentifierClass() {
		return ViewReceptoresPrioridadId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}