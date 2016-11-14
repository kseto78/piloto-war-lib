/*
 *
 * archivo: ViewServSMSDAOImpl.java
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
import es.minhap.sim.dao.ViewServSMSDAO;
import es.minhap.sim.model.ViewServSMS;
import es.minhap.sim.model.ViewServSMSId;
import es.minhap.sim.query.ViewServSMSQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServSMSDAOImpl extends AbstractHibernateDAO<ViewServSMS, ViewServSMSId, ViewServSMSQuery> implements
		ViewServSMSDAO {

	@Override
	protected Class<ViewServSMS> getEntityClass() {
		return ViewServSMS.class;
	}

	@Override
	protected Class<ViewServSMSId> getIdentifierClass() {
		return ViewServSMSId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}