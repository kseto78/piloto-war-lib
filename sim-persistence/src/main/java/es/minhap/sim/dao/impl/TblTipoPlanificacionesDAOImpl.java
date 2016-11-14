/*
 *
 * archivo: TblTipoPlanificacionesDAOImpl.java
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
import es.minhap.sim.dao.TblTipoPlanificacionesDAO;
import es.minhap.sim.model.TblTipoPlanificaciones;
import es.minhap.sim.query.TblTipoPlanificacionesQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblTipoPlanificacionesDAOImpl extends
		AbstractHibernateDAO<TblTipoPlanificaciones, Long, TblTipoPlanificacionesQuery> implements
		TblTipoPlanificacionesDAO {

	@Override
	protected Class<TblTipoPlanificaciones> getEntityClass() {
		return TblTipoPlanificaciones.class;
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