
/*
 *
 * archivo: ErroresDAOImpl.java
 *
 * Proyecto: Comunidades
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

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.misim.bus.dao.ErroresDAO;
import es.minhap.misim.bus.model.Errores;
import es.minhap.misim.bus.query.ErroresQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class ErroresDAOImpl extends AbstractHibernateDAO<Errores,Long,ErroresQuery> implements ErroresDAO {

    @Override
    protected Class<Errores> getEntityClass() {
        return Errores.class;
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