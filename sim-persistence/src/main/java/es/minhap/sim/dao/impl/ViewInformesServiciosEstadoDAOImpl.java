/*
 *
 * archivo: ViewInformesServiciosEstadoDAOImpl.java
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
import es.minhap.sim.dao.ViewInformesServiciosEstadoDAO;
import es.minhap.sim.model.ViewInformesServiciosEstado;
import es.minhap.sim.model.ViewInformesServiciosEstadoId;
import es.minhap.sim.query.ViewInformesServiciosEstadoQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewInformesServiciosEstadoDAOImpl
		extends
		AbstractHibernateDAO<ViewInformesServiciosEstado, ViewInformesServiciosEstadoId, ViewInformesServiciosEstadoQuery>
		implements ViewInformesServiciosEstadoDAO {

	@Override
	protected Class<ViewInformesServiciosEstado> getEntityClass() {
		return ViewInformesServiciosEstado.class;
	}

	@Override
	protected Class<ViewInformesServiciosEstadoId> getIdentifierClass() {
		return ViewInformesServiciosEstadoId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}