/*
 *
 * archivo: ViewSMSDAOImpl.java
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
import es.minhap.sim.dao.ViewSMSDAO;
import es.minhap.sim.model.ViewSMS;
import es.minhap.sim.model.ViewSMSId;
import es.minhap.sim.query.ViewSMSQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewSMSDAOImpl extends AbstractHibernateDAO<ViewSMS, ViewSMSId, ViewSMSQuery> implements ViewSMSDAO {

	@Override
	protected Class<ViewSMS> getEntityClass() {
		return ViewSMS.class;
	}

	@Override
	protected Class<ViewSMSId> getIdentifierClass() {
		return ViewSMSId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}