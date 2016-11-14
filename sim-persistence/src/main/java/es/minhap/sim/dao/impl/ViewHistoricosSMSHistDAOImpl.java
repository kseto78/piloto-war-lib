/*
 *
 * archivo: ViewHistoricosSMSHistDAOImpl.java
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
import es.minhap.sim.dao.ViewHistoricosSMSHistDAO;
import es.minhap.sim.model.ViewHistoricosSMSHist;
import es.minhap.sim.model.ViewHistoricosSMSHistId;
import es.minhap.sim.query.ViewHistoricosSMSHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewHistoricosSMSHistDAOImpl extends
		AbstractHibernateDAO<ViewHistoricosSMSHist, ViewHistoricosSMSHistId, ViewHistoricosSMSHistQuery> implements
		ViewHistoricosSMSHistDAO {

	@Override
	protected Class<ViewHistoricosSMSHist> getEntityClass() {
		return ViewHistoricosSMSHist.class;
	}

	@Override
	protected Class<ViewHistoricosSMSHistId> getIdentifierClass() {
		return ViewHistoricosSMSHistId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}