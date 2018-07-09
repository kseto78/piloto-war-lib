/*
 *
 * archivo: ViewServidoresPrioridadDAOImpl.java
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
import es.minhap.sim.dao.ViewServidoresPrioridadDAO;
import es.minhap.sim.model.ViewServidoresPrioridad;
import es.minhap.sim.model.ViewServidoresPrioridadId;
import es.minhap.sim.query.ViewServidoresPrioridadQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServidoresPrioridadDAOImpl extends
		AbstractHibernateDAO<ViewServidoresPrioridad, ViewServidoresPrioridadId, ViewServidoresPrioridadQuery>
		implements ViewServidoresPrioridadDAO {

	@Override
	protected Class<ViewServidoresPrioridad> getEntityClass() {
		return ViewServidoresPrioridad.class;
	}

	@Override
	protected Class<ViewServidoresPrioridadId> getIdentifierClass() {
		return ViewServidoresPrioridadId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}