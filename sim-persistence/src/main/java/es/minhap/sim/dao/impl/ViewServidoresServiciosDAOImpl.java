/*
 *
 * archivo: ViewServidoresServiciosDAOImpl.java
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
import es.minhap.sim.dao.ViewServidoresServiciosDAO;
import es.minhap.sim.model.ViewServidoresServicios;
import es.minhap.sim.model.ViewServidoresServiciosId;
import es.minhap.sim.query.ViewServidoresServiciosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServidoresServiciosDAOImpl extends
		AbstractHibernateDAO<ViewServidoresServicios, ViewServidoresServiciosId, ViewServidoresServiciosQuery>
		implements ViewServidoresServiciosDAO {

	@Override
	protected Class<ViewServidoresServicios> getEntityClass() {
		return ViewServidoresServicios.class;
	}

	@Override
	protected Class<ViewServidoresServiciosId> getIdentifierClass() {
		return ViewServidoresServiciosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}