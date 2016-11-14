/*
 *
 * archivo: TblGestionEnviosHistDAOImpl.java
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
import es.minhap.sim.dao.TblGestionEnviosHistDAO;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.query.TblGestionEnviosHistQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblGestionEnviosHistDAOImpl extends
		AbstractHibernateDAO<TblGestionEnviosHist, Long, TblGestionEnviosHistQuery> implements TblGestionEnviosHistDAO {

	@Override
	protected Class<TblGestionEnviosHist> getEntityClass() {
		return TblGestionEnviosHist.class;
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