
/*
 *
 * archivo: TransformacionDAOImpl.java
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

import org.springframework.stereotype.Service;

import es.minhap.common.persistence.hibernate.dao.AbstractHibernateDAO;
import es.minhap.misim.bus.dao.TransformacionDAO;
import es.minhap.misim.bus.model.Transformacion;
import es.minhap.misim.bus.query.TransformacionQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class TransformacionDAOImpl extends AbstractHibernateDAO<Transformacion,Long,TransformacionQuery> implements TransformacionDAO {

    @Override
    protected Class<Transformacion> getEntityClass() {
        return Transformacion.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}