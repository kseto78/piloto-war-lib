
/*
 *
 * archivo: ViewServServidorPushDAOImpl.java
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
import es.minhap.sim.dao.ViewServServidorPushDAO;
import es.minhap.sim.model.ViewServServidorPush;
import es.minhap.sim.model.ViewServServidorPushId;
import es.minhap.sim.query.ViewServServidorPushQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewServServidorPushDAOImpl extends AbstractHibernateDAO<ViewServServidorPush,ViewServServidorPushId,ViewServServidorPushQuery> implements ViewServServidorPushDAO {

    @Override
    protected Class<ViewServServidorPush> getEntityClass() {
        return ViewServServidorPush.class;
    }

    @Override
    protected Class<ViewServServidorPushId> getIdentifierClass() {
        return ViewServServidorPushId.class;
    }
    @Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}