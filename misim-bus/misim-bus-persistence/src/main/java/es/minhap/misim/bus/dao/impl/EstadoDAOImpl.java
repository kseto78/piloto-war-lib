
/*
 *
 * archivo: EstadoDAOImpl.java
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
import es.minhap.misim.bus.dao.EstadoDAO;
import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.query.EstadoQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class EstadoDAOImpl extends AbstractHibernateDAO<Estado,Long,EstadoQuery> implements EstadoDAO {

    @Override
    protected Class<Estado> getEntityClass() {
        return Estado.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}