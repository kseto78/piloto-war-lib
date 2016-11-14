/*
 *
 * archivo: ViewHistoricoEmailHistDAOImpl.java
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
import es.minhap.sim.dao.ViewHistoricoEmailHistDAO;
import es.minhap.sim.model.ViewHistoricoEmailHist;
import es.minhap.sim.model.ViewHistoricoEmailHistId;
import es.minhap.sim.query.ViewHistoricoEmailHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewHistoricoEmailHistDAOImpl extends
		AbstractHibernateDAO<ViewHistoricoEmailHist, ViewHistoricoEmailHistId, ViewHistoricoEmailHistQuery> implements
		ViewHistoricoEmailHistDAO {

	@Override
	protected Class<ViewHistoricoEmailHist> getEntityClass() {
		return ViewHistoricoEmailHist.class;
	}

	@Override
	protected Class<ViewHistoricoEmailHistId> getIdentifierClass() {
		return ViewHistoricoEmailHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}