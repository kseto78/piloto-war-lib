/*
 *
 * archivo: ViewServidoresPushPrioridadDAOImpl.java
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
import es.minhap.sim.dao.ViewServidoresPushPrioridadDAO;
import es.minhap.sim.model.ViewServidoresPushPrioridad;
import es.minhap.sim.model.ViewServidoresPushPrioridadId;
import es.minhap.sim.query.ViewServidoresPushPrioridadQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServidoresPushPrioridadDAOImpl
		extends
		AbstractHibernateDAO<ViewServidoresPushPrioridad, ViewServidoresPushPrioridadId, ViewServidoresPushPrioridadQuery>
		implements ViewServidoresPushPrioridadDAO {

	@Override
	protected Class<ViewServidoresPushPrioridad> getEntityClass() {
		return ViewServidoresPushPrioridad.class;
	}

	@Override
	protected Class<ViewServidoresPushPrioridadId> getIdentifierClass() {
		return ViewServidoresPushPrioridadId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}