/*
 *
 * archivo: TblOrganismosServicioDAOImpl.java
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
import es.minhap.sim.dao.TblOrganismosServicioDAO;
import es.minhap.sim.model.TblOrganismosServicio;
import es.minhap.sim.query.TblOrganismosServicioQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblOrganismosServicioDAOImpl extends
		AbstractHibernateDAO<TblOrganismosServicio, Long, TblOrganismosServicioQuery> implements
		TblOrganismosServicioDAO {

	@Override
	protected Class<TblOrganismosServicio> getEntityClass() {
		return TblOrganismosServicio.class;
	}

	@Override
	protected Class<Long> getIdentifierClass() {
		return Long.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}