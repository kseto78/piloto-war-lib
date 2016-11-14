/*
 *
 * archivo: ViewServReceptorSMSDAOImpl.java
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
import es.minhap.sim.dao.ViewServReceptorSMSDAO;
import es.minhap.sim.model.ViewServReceptorSMS;
import es.minhap.sim.model.ViewServReceptorSMSId;
import es.minhap.sim.query.ViewServReceptorSMSQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServReceptorSMSDAOImpl extends
		AbstractHibernateDAO<ViewServReceptorSMS, ViewServReceptorSMSId, ViewServReceptorSMSQuery> implements
		ViewServReceptorSMSDAO {

	@Override
	protected Class<ViewServReceptorSMS> getEntityClass() {
		return ViewServReceptorSMS.class;
	}

	@Override
	protected Class<ViewServReceptorSMSId> getIdentifierClass() {
		return ViewServReceptorSMSId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}