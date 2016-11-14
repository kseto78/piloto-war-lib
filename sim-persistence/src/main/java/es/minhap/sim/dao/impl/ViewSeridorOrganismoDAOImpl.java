/*
 *
 * archivo: ViewSeridorOrganismoDAOImpl.java
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
import es.minhap.sim.dao.ViewSeridorOrganismoDAO;
import es.minhap.sim.model.ViewSeridorOrganismo;
import es.minhap.sim.model.ViewSeridorOrganismoId;
import es.minhap.sim.query.ViewSeridorOrganismoQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewSeridorOrganismoDAOImpl extends
		AbstractHibernateDAO<ViewSeridorOrganismo, ViewSeridorOrganismoId, ViewSeridorOrganismoQuery> implements
		ViewSeridorOrganismoDAO {

	@Override
	protected Class<ViewSeridorOrganismo> getEntityClass() {
		return ViewSeridorOrganismo.class;
	}

	@Override
	protected Class<ViewSeridorOrganismoId> getIdentifierClass() {
		return ViewSeridorOrganismoId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}