/*
 *
 * archivo: ViewInformesServiciosCopDAOImpl.java
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
import es.minhap.sim.dao.ViewInformesServiciosCopDAO;
import es.minhap.sim.model.ViewInformesServiciosCop;
import es.minhap.sim.model.ViewInformesServiciosCopId;
import es.minhap.sim.query.ViewInformesServiciosCopQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewInformesServiciosCopDAOImpl extends
		AbstractHibernateDAO<ViewInformesServiciosCop, ViewInformesServiciosCopId, ViewInformesServiciosCopQuery>
		implements ViewInformesServiciosCopDAO {

	@Override
	protected Class<ViewInformesServiciosCop> getEntityClass() {
		return ViewInformesServiciosCop.class;
	}

	@Override
	protected Class<ViewInformesServiciosCopId> getIdentifierClass() {
		return ViewInformesServiciosCopId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}