/*
 *
 * archivo: ViewAplicacionesDAOImpl.java
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
import es.minhap.sim.dao.ViewAplicacionesDAO;
import es.minhap.sim.model.ViewAplicaciones;
import es.minhap.sim.model.ViewAplicacionesId;
import es.minhap.sim.query.ViewAplicacionesQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewAplicacionesDAOImpl extends
		AbstractHibernateDAO<ViewAplicaciones, ViewAplicacionesId, ViewAplicacionesQuery> implements
		ViewAplicacionesDAO {

	@Override
	protected Class<ViewAplicaciones> getEntityClass() {
		return ViewAplicaciones.class;
	}

	@Override
	protected Class<ViewAplicacionesId> getIdentifierClass() {
		return ViewAplicacionesId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}