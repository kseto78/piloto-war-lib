
/*
 *
 * archivo: AuditoriaDAOImpl.java
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
import es.minhap.misim.bus.dao.AuditoriaDAO;
import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.query.AuditoriaQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class AuditoriaDAOImpl extends AbstractHibernateDAO<Auditoria,Long,AuditoriaQuery> implements AuditoriaDAO {

    @Override
    protected Class<Auditoria> getEntityClass() {
        return Auditoria.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}