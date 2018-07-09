/*
 *
 * archivo: ViewHistoricoEmailDAOImpl.java
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
import es.minhap.sim.dao.ViewHistoricoEmailDAO;
import es.minhap.sim.model.ViewHistoricoEmail;
import es.minhap.sim.model.ViewHistoricoEmailId;
import es.minhap.sim.query.ViewHistoricoEmailQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewHistoricoEmailDAOImpl extends
		AbstractHibernateDAO<ViewHistoricoEmail, ViewHistoricoEmailId, ViewHistoricoEmailQuery> implements
		ViewHistoricoEmailDAO {

	@Override
	protected Class<ViewHistoricoEmail> getEntityClass() {
		return ViewHistoricoEmail.class;
	}

	@Override
	protected Class<ViewHistoricoEmailId> getIdentifierClass() {
		return ViewHistoricoEmailId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}