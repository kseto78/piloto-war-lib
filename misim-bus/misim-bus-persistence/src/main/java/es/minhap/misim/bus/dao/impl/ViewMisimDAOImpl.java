
/*
 *
 * archivo: ViewMisimDAOImpl.java
 *
 * Proyecto: Administracion SIM
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.misim.bus.dao.ViewMisimDAO;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ViewMisimDAOImpl extends AbstractHibernateDAO<ViewMisim,Long,ViewMisimQuery> implements ViewMisimDAO {

    @Override
    protected Class<ViewMisim> getEntityClass() {
        return ViewMisim.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
	 @Override
   	@Autowired
   	@Qualifier(value = "sessionFactoryApp")
   	public void setSessionFactoryApp(SessionFactory sessionFactory) {
   		super.setSessionFactory(sessionFactory);
   	}
}