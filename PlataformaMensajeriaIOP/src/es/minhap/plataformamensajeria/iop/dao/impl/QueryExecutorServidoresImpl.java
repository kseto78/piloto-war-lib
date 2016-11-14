package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorServidoresImpl extends HibernateDaoSupport implements QueryExecutorServidores {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorServidoresImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public Long obtenerServidorByIdMensaje(Long idMensaje) {
		BigDecimal res = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					"select SE.servidorid from TBL_SERVIDORES SE, TBL_SERVIDORES_ORGANISMOS SEO "
					+ " where SE.SERVIDORID = SEO.SERVIDORID AND SEO.ORGANISMOID = "
					+ " (select ORGANISMOID "
					+ "  from TBL_ORGANISMOS "
					+ "  where DIR3= "
					+ " (SELECT ME.CODORGANISMOPAGADOR "
					+ "  FROM TBL_MENSAJES ME WHERE ME.MENSAJEID="	+ idMensaje + "))");

			res = (BigDecimal) query.uniqueResult();
			
			if (null == res)
				return null;
			
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();
	}

	@Override
	@Transactional
	public List<ParametrosProveedor> getProveedoresMultiorganismo(Long idMensaje) {
		List<ParametrosProveedor> res = new ArrayList<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
										
					.createSQLQuery("SELECT proveedorid,url"
							+ "  FROM view_prov_prioridad_multiorg"
							+ "  WHERE (servicioId = " + this.getIdServicioByIdMensaje(idMensaje)
							+ " OR servicioId IS NULL) "
							+ " AND url is not null " + "order by Prioridad");
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosProveedor pp = setParametrosProveedor(row,true);
				res.add(pp);
			}
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}
	
	@Override
	@Transactional
	public List<ParametrosProveedor> getProveedores(Long idMensaje) {
		List<ParametrosProveedor> res = new ArrayList<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT proveedorid,url,id,telefono,texto,uim"
							+ "  FROM view_proveedores_prioridad"
							+ "  WHERE (servicioId = " + this.getIdServicioByIdMensaje(idMensaje)
							+ " OR servicioId IS NULL) "
							+ " AND url is not null " 
							+ " order by Prioridad");
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosProveedor pp = setParametrosProveedor(row, false);
				res.add(pp);
			}
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	private ParametrosProveedor setParametrosProveedor(Object[] row,Boolean multiorganismo) {
		ParametrosProveedor pp = new ParametrosProveedor();
		pp.setProveedorId((null != row[0])?((BigDecimal) row[0]).intValue() : 0);
		pp.setUrl((null != row[1])? (String) row[1] : "");
		if (!multiorganismo) {
			pp.setId((null != row[2])? (String) row[2] : "");
			pp.setTelefono((null != row[3])? (String) row[3] : "");
			pp.setTexto((null != row[4])? (String) row[4] : "");
			pp.setUIM((null != row[5])? (String) row[5] : "");
		}
		return pp;
	}
	
	

	@Override
	@Transactional
	public Long getIdServicioByIdMensaje(Long idMensaje) {
		BigDecimal res = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT servicioid "
							+ "FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid "
							+ "WHERE mensajeid = "
							+ idMensaje);
			res = (BigDecimal) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();	
	}

	
}
