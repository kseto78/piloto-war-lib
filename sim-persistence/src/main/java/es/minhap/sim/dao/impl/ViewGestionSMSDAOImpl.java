
/*
 *
 * archivo: ViewGestionSMSDAOImpl.java
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
import es.minhap.sim.dao.ViewGestionSMSDAO;
import es.minhap.sim.model.ViewGestionSMS;
import es.minhap.sim.model.ViewGestionSMSId;
import es.minhap.sim.query.ViewGestionSMSQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewGestionSMSDAOImpl extends AbstractHibernateDAO<ViewGestionSMS,ViewGestionSMSId,ViewGestionSMSQuery> implements ViewGestionSMSDAO {

    @Override
    protected Class<ViewGestionSMS> getEntityClass() {
        return ViewGestionSMS.class;
    }

    @Override
    protected Class<ViewGestionSMSId> getIdentifierClass() {
        return ViewGestionSMSId.class;
    }
    @Override
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}