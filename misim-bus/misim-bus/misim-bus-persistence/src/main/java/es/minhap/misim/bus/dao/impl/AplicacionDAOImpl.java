
/*
 *
 * archivo: AplicacionDAOImpl.java
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
import es.minhap.misim.bus.dao.AplicacionDAO;
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.query.AplicacionQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class AplicacionDAOImpl extends AbstractHibernateDAO<Aplicacion,Long,AplicacionQuery> implements AplicacionDAO {

    @Override
    protected Class<Aplicacion> getEntityClass() {
        return Aplicacion.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}