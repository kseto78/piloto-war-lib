/*
 *
 * archivo: ViewInformesServiciosCodOrgDAOImpl.java
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
import es.minhap.sim.dao.ViewInformesServiciosCodOrgDAO;
import es.minhap.sim.model.ViewInformesServiciosCodOrg;
import es.minhap.sim.model.ViewInformesServiciosCodOrgId;
import es.minhap.sim.query.ViewInformesServiciosCodOrgQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewInformesServiciosCodOrgDAOImpl
		extends
		AbstractHibernateDAO<ViewInformesServiciosCodOrg, ViewInformesServiciosCodOrgId, ViewInformesServiciosCodOrgQuery>
		implements ViewInformesServiciosCodOrgDAO {

	@Override
	protected Class<ViewInformesServiciosCodOrg> getEntityClass() {
		return ViewInformesServiciosCodOrg.class;
	}

	@Override
	protected Class<ViewInformesServiciosCodOrgId> getIdentifierClass() {
		return ViewInformesServiciosCodOrgId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}