
/*
 *
 * archivo: EndpointDAOImpl.java
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
import es.minhap.misim.bus.dao.EndpointDAO;
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.query.EndpointQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class EndpointDAOImpl extends AbstractHibernateDAO<Endpoint,Long,EndpointQuery> implements EndpointDAO {

    @Override
    protected Class<Endpoint> getEntityClass() {
        return Endpoint.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}