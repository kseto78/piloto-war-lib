/*
 *
 * archivo: ViewUsuariosDAOImpl.java
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
import es.minhap.sim.dao.ViewUsuariosDAO;
import es.minhap.sim.model.ViewUsuarios;
import es.minhap.sim.model.ViewUsuariosId;
import es.minhap.sim.query.ViewUsuariosQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewUsuariosDAOImpl extends AbstractHibernateDAO<ViewUsuarios, ViewUsuariosId, ViewUsuariosQuery>
		implements ViewUsuariosDAO {

	@Override
	protected Class<ViewUsuarios> getEntityClass() {
		return ViewUsuarios.class;
	}

	@Override
	protected Class<ViewUsuariosId> getIdentifierClass() {
		return ViewUsuariosId.class;
	}

	@Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}