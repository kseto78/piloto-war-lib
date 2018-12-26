
/*
 *
 * archivo: TblCertificadosDAOImpl.java
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

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.sim.dao.TblCertificadosDAO;
import es.minhap.sim.model.TblCertificados;
import es.minhap.sim.query.TblCertificadosQuery;


/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class TblCertificadosDAOImpl extends AbstractHibernateDAO<TblCertificados,Long,TblCertificadosQuery> implements TblCertificadosDAO {

    @Override
    protected Class<TblCertificados> getEntityClass() {
        return TblCertificados.class;
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