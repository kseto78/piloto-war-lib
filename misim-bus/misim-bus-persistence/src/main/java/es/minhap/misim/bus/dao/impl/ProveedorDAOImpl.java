
/*
 *
 * archivo: ProveedorDAOImpl.java
 *
 * Proyecto: Carpeta Ciudadana
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.misim.bus.dao.ProveedorDAO;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.query.ProveedorQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class ProveedorDAOImpl extends AbstractHibernateDAO<Proveedor,Long,ProveedorQuery> implements ProveedorDAO {

    @Override
    protected Class<Proveedor> getEntityClass() {
        return Proveedor.class;
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