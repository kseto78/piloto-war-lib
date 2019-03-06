
/*
 *
 * archivo: TblPdpDiputacionesDAOImpl.java
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
import es.minhap.sim.dao.TblPdpDiputacionesDAO;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.query.TblPdpDiputacionesQuery;


/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public class TblPpdDiputacionesDAOImpl extends AbstractHibernateDAO<TblPdpDiputaciones,Long,TblPdpDiputacionesQuery> implements TblPdpDiputacionesDAO {

    @Override
    protected Class<TblPdpDiputaciones> getEntityClass() {
        return TblPdpDiputaciones.class;
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