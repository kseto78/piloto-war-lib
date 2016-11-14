/*
 *
 * archivo: ViewInformesServiciosCodSIADAOImpl.java
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
import es.minhap.sim.dao.ViewInformesServiciosCodSIADAO;
import es.minhap.sim.model.ViewInformesServiciosCodSIA;
import es.minhap.sim.model.ViewInformesServiciosCodSIAId;
import es.minhap.sim.query.ViewInformesServiciosCodSIAQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewInformesServiciosCodSIADAOImpl
		extends
		AbstractHibernateDAO<ViewInformesServiciosCodSIA, ViewInformesServiciosCodSIAId, ViewInformesServiciosCodSIAQuery>
		implements ViewInformesServiciosCodSIADAO {

	@Override
	protected Class<ViewInformesServiciosCodSIA> getEntityClass() {
		return ViewInformesServiciosCodSIA.class;
	}

	@Override
	protected Class<ViewInformesServiciosCodSIAId> getIdentifierClass() {
		return ViewInformesServiciosCodSIAId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}