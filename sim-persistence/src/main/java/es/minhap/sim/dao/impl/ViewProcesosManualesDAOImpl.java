
/*
 *
 * archivo: ViewProcesosManualesDAOImpl.java
 *
 * Proyecto: Administracion PAG
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

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.sim.dao.ViewProcesosManualesDAO;
import es.minhap.sim.model.ViewProcesosManuales;
import es.minhap.sim.query.ViewProcesosManualesQuery;


/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class ViewProcesosManualesDAOImpl extends AbstractHibernateDAO<ViewProcesosManuales,Long,ViewProcesosManualesQuery> implements ViewProcesosManualesDAO {

    @Override
    protected Class<ViewProcesosManuales> getEntityClass() {
        return ViewProcesosManuales.class;
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