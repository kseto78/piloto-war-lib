
/*
 *
 * archivo: TblMonitorDIR3DAOImpl.java
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
import es.minhap.sim.dao.TblMonitorDIR3DAO;
import es.minhap.sim.model.TblMonitorDIR3;
import es.minhap.sim.query.TblMonitorDIR3Query;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblMonitorDIR3DAOImpl extends AbstractHibernateDAO<TblMonitorDIR3,Long,TblMonitorDIR3Query> implements TblMonitorDIR3DAO {

    @Override
    protected Class<TblMonitorDIR3> getEntityClass() {
        return TblMonitorDIR3.class;
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