/*
 *
 * archivo: TblErrorMensajeLogDAOImpl.java
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
import es.minhap.sim.dao.TblErrorMensajeLogDAO;
import es.minhap.sim.model.TblErrorMensajeLog;
import es.minhap.sim.query.TblErrorMensajeLogQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblErrorMensajeLogDAOImpl extends AbstractHibernateDAO<TblErrorMensajeLog, Long, TblErrorMensajeLogQuery>
		implements TblErrorMensajeLogDAO {

	@Override
	protected Class<TblErrorMensajeLog> getEntityClass() {
		return TblErrorMensajeLog.class;
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