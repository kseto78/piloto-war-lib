/*
 *
 * archivo: ViewServSmsMultiorgDAOImpl.java
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
import es.minhap.sim.dao.ViewServSmsMultiorgDAO;
import es.minhap.sim.model.ViewServSmsMultiorg;
import es.minhap.sim.model.ViewServSmsMultiorgId;
import es.minhap.sim.query.ViewServSmsMultiorgQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServSmsMultiorgDAOImpl extends
		AbstractHibernateDAO<ViewServSmsMultiorg, ViewServSmsMultiorgId, ViewServSmsMultiorgQuery> implements
		ViewServSmsMultiorgDAO {

	@Override
	protected Class<ViewServSmsMultiorg> getEntityClass() {
		return ViewServSmsMultiorg.class;
	}

	@Override
	protected Class<ViewServSmsMultiorgId> getIdentifierClass() {
		return ViewServSmsMultiorgId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}