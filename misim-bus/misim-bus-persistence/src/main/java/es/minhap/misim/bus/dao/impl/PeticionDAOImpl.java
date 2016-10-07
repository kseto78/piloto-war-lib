
/*
 *
 * archivo: PeticionDAOImpl.java
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
import es.minhap.misim.bus.dao.PeticionDAO;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class PeticionDAOImpl extends AbstractHibernateDAO<Peticion,Long,PeticionQuery> implements PeticionDAO {

    @Override
    protected Class<Peticion> getEntityClass() {
        return Peticion.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}