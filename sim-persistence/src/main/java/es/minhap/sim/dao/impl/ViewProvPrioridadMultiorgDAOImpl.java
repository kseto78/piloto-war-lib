/*
 *
 * archivo: ViewProvPrioridadMultiorgDAOImpl.java
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
import es.minhap.sim.dao.ViewProvPrioridadMultiorgDAO;
import es.minhap.sim.model.ViewProvPrioridadMultiorg;
import es.minhap.sim.model.ViewProvPrioridadMultiorgId;
import es.minhap.sim.query.ViewProvPrioridadMultiorgQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewProvPrioridadMultiorgDAOImpl extends
		AbstractHibernateDAO<ViewProvPrioridadMultiorg, ViewProvPrioridadMultiorgId, ViewProvPrioridadMultiorgQuery>
		implements ViewProvPrioridadMultiorgDAO {

	@Override
	protected Class<ViewProvPrioridadMultiorg> getEntityClass() {
		return ViewProvPrioridadMultiorg.class;
	}

	@Override
	protected Class<ViewProvPrioridadMultiorgId> getIdentifierClass() {
		return ViewProvPrioridadMultiorgId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}