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
import es.minhap.plataformamensajeria.iop.beans.UsoServidoresBean;
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

	protected static final String R_CONST_1 = " AND url is not null ";

	protected static final String R_CONST_2 = " GROUP BY ";

	protected static final String R_CONST_3 = "SERVIDORID, ESTADOID, CANALID ";

	protected static final String R_CONST_4 = "WHERE mensajeid = ";

	protected static final String R_CONST_5 = "CASE WHEN CANALID = 1 THEN 1 WHEN CANALID = 2 THEN 2 WHEN CANALID = 3 THEN 3 ELSE 4 END AS TIPOSERVIDOR ";

	protected static final String R_CONST_6 = "unchecked";

	protected static final String R_CONST_7 = "UNION ALL ";

	protected static final String R_CONST_8 = "SELECT SERVIDORID, ESTADOID, SUM(1) AS NENVIOS, CANALID, ";

	protected static final String R_CONST_9 = "  WHERE (servicioId = ";

	protected static final String R_CONST_10 = " OR servicioId IS NULL) ";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorServidoresImpl.class);
	
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
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					"select SE.servidorid from TBL_SERVIDORES SE, TBL_SERVIDORES_ORGANISMOS SEO "
					+ " where SE.SERVIDORID = SEO.SERVIDORID AND SEO.ORGANISMOID = "
					+ " (select ORGANISMOID "
					+ "  from TBL_ORGANISMOS "
					+ "  where DIR3= "
					+ " UPPER((SELECT ME.CODORGANISMOPAGADOR "
					+ "  FROM TBL_MENSAJES ME WHERE ME.MENSAJEID= ?)))");
			query.setLong(0, idMensaje);
			res = (BigDecimal) query.uniqueResult();
			
			if (null == res) {
				return null;
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();
	}

	@Override
	@Transactional
	public List<ParametrosProveedor> getProveedoresMultiorganismo(Long idMensaje) {
		List<ParametrosProveedor> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
										
					.createSQLQuery("SELECT proveedorid,url"
							+ "  FROM view_prov_prioridad_multiorg"
							+ R_CONST_9 + this.getIdServicioByIdMensaje(idMensaje)
							+ R_CONST_10
							+ R_CONST_1 + "order by Prioridad");
			
			@SuppressWarnings(R_CONST_6)
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosProveedor pp = setParametrosProveedor(row,true);
				res.add(pp);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}
	
	@Override
	@Transactional
	public List<ParametrosProveedor> getProveedores(Long idMensaje) {
		List<ParametrosProveedor> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT proveedorid,url,id,telefono,texto,uim"
							+ "  FROM view_proveedores_prioridad"
							+ R_CONST_9 + this.getIdServicioByIdMensaje(idMensaje)
							+ R_CONST_10
							+ R_CONST_1 
							+ " order by Prioridad");
			
			@SuppressWarnings(R_CONST_6)
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosProveedor pp = setParametrosProveedor(row, false);
				res.add(pp);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
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
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT servicioid "
							+ "FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid "
							+ R_CONST_4
							+ "?");
			query.setLong(0, idMensaje);
			res = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (res != null)?res.longValue():null;	
	}
	
	@Override
	@Transactional
	public Long getIdServicioByIdMensajeHist(Long idMensaje) {
		BigDecimal res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT servicioid FROM tbl_mensajes_hist m inner join tbl_lotesenvios_hist l on m.loteenvioid = l.loteenvioid "
					+ R_CONST_4 + "?");
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery(sql.toString());
			query.setLong(0, idMensaje);
			res = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();	
	}


	@SuppressWarnings(R_CONST_6)
	@Override
	@Transactional
	public List<UsoServidoresBean> getUsoServidores(String anoActual, String mesActual) {
		List<UsoServidoresBean> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT B.SERVIDORID as SERVIDORID, S.NOMBRE as NOMBRE, SUM(NENVIOS) AS NENVIOS, B.CANALID AS TIPOSERVIDOR FROM ("
							+ R_CONST_8
							+ R_CONST_5
							+ "FROM TBL_GESTIONENVIOS WHERE ESTADOID = 1 AND ANIO = ? AND MES =  ?" + R_CONST_2
							+ R_CONST_3
							+ R_CONST_7
							+ R_CONST_8
							+ R_CONST_5
							+ "FROM TBL_GESTIONENVIOS_HIST WHERE ESTADOID = 1 AND ANIO = ? AND MES = ?" + R_CONST_2
							+ R_CONST_3
							+ R_CONST_7
							+ "SELECT SERVIDORID, ESTADOID, SUM(NUMTOTAL) AS NENVIOS, CANALID, "
							+ R_CONST_5
							+ "FROM TBL_ESTADISTICAS_CONS WHERE ESTADOID = 1 AND ANNO = ? AND MES = ?"
							+ "GROUP BY SERVIDORID, ESTADOID, CANALID) B "
							+ "INNER JOIN TBL_SERVIDORES S ON S.SERVIDORID = B.SERVIDORID "
							+ "GROUP BY B.SERVIDORID, S.NOMBRE, B.CANALID");
		query.setString(0, anoActual);
		query.setString(1, mesActual);
		query.setString(2, anoActual);
		query.setString(3, mesActual);
		query.setString(4, anoActual);
		query.setString(5, mesActual+"_"+anoActual);
		
			 
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				UsoServidoresBean u = new UsoServidoresBean();
				u.setServidorId((null != row[0])? ((BigDecimal) row[0]).longValue() : null);
				u.setNombre((null != row[1])? (String) row[1] : "");
				u.setnEnvios((null != row[2])? ((BigDecimal) row[2]).intValue() : null);
				u.setTipoServidor((null != row[3])? ((BigDecimal) row[3]).longValue() : null);
				res.add(u);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}
	
	@Override
	@Transactional
	public Long getIdLoteByIdMensaje(Long idMensaje) {
		BigDecimal res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT loteenvioid "
							+ "FROM tbl_mensajes "
							+ R_CONST_4
							+ "?");
			query.setLong(0, idMensaje);
			res = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.longValue();	
	}
	
}
