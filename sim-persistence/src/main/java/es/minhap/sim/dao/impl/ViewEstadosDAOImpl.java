/*
 *
 * archivo: ViewEstadosDAOImpl.java
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
import es.minhap.sim.dao.ViewEstadosDAO;
import es.minhap.sim.model.ViewEstados;
import es.minhap.sim.model.ViewEstadosId;
import es.minhap.sim.query.ViewEstadosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewEstadosDAOImpl extends AbstractHibernateDAO<ViewEstados, ViewEstadosId, ViewEstadosQuery> implements
		ViewEstadosDAO {

	@Override
	protected Class<ViewEstados> getEntityClass() {
		return ViewEstados.class;
	}

	@Override
	protected Class<ViewEstadosId> getIdentifierClass() {
		return ViewEstadosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}