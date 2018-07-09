/*
 *
 * archivo: TblAdjuntosDAOImpl.java
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
import es.minhap.sim.dao.TblAdjuntosDAO;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.query.TblAdjuntosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service("TblAdjuntosDAOImpl")
@Transactional(propagation= Propagation.REQUIRES_NEW, readOnly=false)
public class TblAdjuntosDAOImpl extends
		AbstractHibernateDAO<TblAdjuntos, Long, TblAdjuntosQuery> implements
		TblAdjuntosDAO {

	@Override
	protected Class<TblAdjuntos> getEntityClass() {
		return TblAdjuntos.class;
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