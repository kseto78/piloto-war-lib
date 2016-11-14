
/*
 *
 * archivo: ProveedorDAOImpl.java
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
import es.minhap.misim.bus.dao.ProveedorDAO;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.query.ProveedorQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class ProveedorDAOImpl extends AbstractHibernateDAO<Proveedor,Long,ProveedorQuery> implements ProveedorDAO {

    @Override
    protected Class<Proveedor> getEntityClass() {
        return Proveedor.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}