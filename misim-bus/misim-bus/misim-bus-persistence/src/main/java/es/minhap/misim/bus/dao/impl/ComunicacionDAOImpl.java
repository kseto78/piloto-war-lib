
/*
 *
 * archivo: ComunicacionDAOImpl.java
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
import es.minhap.misim.bus.dao.ComunicacionDAO;
import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.query.ComunicacionQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class ComunicacionDAOImpl extends AbstractHibernateDAO<Comunicacion,Long,ComunicacionQuery> implements ComunicacionDAO {

    @Override
    protected Class<Comunicacion> getEntityClass() {
        return Comunicacion.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}