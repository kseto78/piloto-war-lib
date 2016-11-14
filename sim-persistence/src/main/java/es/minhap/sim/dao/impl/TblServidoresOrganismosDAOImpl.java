/*
 *
 * archivo: TblServidoresOrganismosDAOImpl.java
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
import es.minhap.sim.dao.TblServidoresOrganismosDAO;
import es.minhap.sim.model.TblServidoresOrganismos;
import es.minhap.sim.model.TblServidoresOrganismosId;
import es.minhap.sim.query.TblServidoresOrganismosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblServidoresOrganismosDAOImpl extends
		AbstractHibernateDAO<TblServidoresOrganismos, TblServidoresOrganismosId, TblServidoresOrganismosQuery>
		implements TblServidoresOrganismosDAO {

	@Override
	protected Class<TblServidoresOrganismos> getEntityClass() {
		return TblServidoresOrganismos.class;
	}

	@Override
	protected Class<TblServidoresOrganismosId> getIdentifierClass() {
		return TblServidoresOrganismosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}