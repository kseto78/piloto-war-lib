/*
 *
 * archivo: ViewEnviosPendientesPorCanalDAOImpl.java
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
import es.minhap.sim.dao.ViewEnviosPendientesPorCanalDAO;
import es.minhap.sim.model.ViewEnviosPendientesPorCanal;
import es.minhap.sim.model.ViewEnviosPendientesPorCanalId;
import es.minhap.sim.query.ViewEnviosPendientesPorCanalQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEnviosPendientesPorCanalDAOImpl
		extends
		AbstractHibernateDAO<ViewEnviosPendientesPorCanal, ViewEnviosPendientesPorCanalId, ViewEnviosPendientesPorCanalQuery>
		implements ViewEnviosPendientesPorCanalDAO {

	@Override
	protected Class<ViewEnviosPendientesPorCanal> getEntityClass() {
		return ViewEnviosPendientesPorCanal.class;
	}

	@Override
	protected Class<ViewEnviosPendientesPorCanalId> getIdentifierClass() {
		return ViewEnviosPendientesPorCanalId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}