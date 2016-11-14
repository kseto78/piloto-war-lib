/*
 *
 * archivo: ViewHistoricoSMSDAOImpl.java
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
import es.minhap.sim.dao.ViewHistoricoSMSDAO;
import es.minhap.sim.model.ViewHistoricoSMS;
import es.minhap.sim.model.ViewHistoricoSMSId;
import es.minhap.sim.query.ViewHistoricoSMSQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewHistoricoSMSDAOImpl extends
		AbstractHibernateDAO<ViewHistoricoSMS, ViewHistoricoSMSId, ViewHistoricoSMSQuery> implements
		ViewHistoricoSMSDAO {

	@Override
	protected Class<ViewHistoricoSMS> getEntityClass() {
		return ViewHistoricoSMS.class;
	}

	@Override
	protected Class<ViewHistoricoSMSId> getIdentifierClass() {
		return ViewHistoricoSMSId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}