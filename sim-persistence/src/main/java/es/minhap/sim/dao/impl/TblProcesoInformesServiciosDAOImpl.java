/*
 *
 * archivo: TblProcesoInformesServiciosDAOImpl.java
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
import es.minhap.sim.dao.TblProcesoInformesServiciosDAO;
import es.minhap.sim.model.TblProcesoInformesServicios;
import es.minhap.sim.query.TblProcesoInformesServiciosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblProcesoInformesServiciosDAOImpl extends
		AbstractHibernateDAO<TblProcesoInformesServicios, Long, TblProcesoInformesServiciosQuery> implements
		TblProcesoInformesServiciosDAO {

	@Override
	protected Class<TblProcesoInformesServicios> getEntityClass() {
		return TblProcesoInformesServicios.class;
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