/*
 *
 * archivo: ViewServicioOrganismosDAOImpl.java
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
import es.minhap.sim.dao.ViewServicioOrganismosDAO;
import es.minhap.sim.model.ViewServicioOrganismos;
import es.minhap.sim.model.ViewServicioOrganismosId;
import es.minhap.sim.query.ViewServicioOrganismosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServicioOrganismosDAOImpl extends
		AbstractHibernateDAO<ViewServicioOrganismos, ViewServicioOrganismosId, ViewServicioOrganismosQuery> implements
		ViewServicioOrganismosDAO {

	@Override
	protected Class<ViewServicioOrganismos> getEntityClass() {
		return ViewServicioOrganismos.class;
	}

	@Override
	protected Class<ViewServicioOrganismosId> getIdentifierClass() {
		return ViewServicioOrganismosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}