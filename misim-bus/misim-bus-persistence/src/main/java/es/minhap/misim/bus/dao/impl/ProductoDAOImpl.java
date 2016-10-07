
/*
 *
 * archivo: ProductoDAOImpl.java
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
import es.minhap.misim.bus.dao.ProductoDAO;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.query.ProductoQuery;

/**
 * Implementacion de DAO basico con Hibernate
 */
@Service
public class ProductoDAOImpl extends AbstractHibernateDAO<Producto,Long,ProductoQuery> implements ProductoDAO {

    @Override
    protected Class<Producto> getEntityClass() {
        return Producto.class;
    }

    @Override
    protected Class<Long> getIdentifierClass() {
        return Long.class;
    }
}