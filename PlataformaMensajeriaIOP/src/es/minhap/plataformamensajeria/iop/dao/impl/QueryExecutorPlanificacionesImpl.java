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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPlanificaciones;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorPlanificacionesImpl extends HibernateDaoSupport implements QueryExecutorPlanificaciones {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorPlanificacionesImpl.class);
	
	private static final String ERROR = "Se ha producido un error";
	private static final String INICIOBUSQUEDA = "search - start";
	private static final String FINALBUSQUEDA = "search - end";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> obtenerServiciosPlanificacion() {
		List<Long> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(INICIOBUSQUEDA);
			}

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT distinct SERVICIOID FROM tbl_planificaciones"
					+ " where ("
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '1' AND L = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '2' AND M = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '3' AND X = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '4' AND J = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '5' AND V = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '6' AND S = 'S' OR "
					+ " 1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '7' AND D = 'S')"

					+ " AND horadesde <= to_char(sysdate,'HH24:MI') AND horahasta>=to_char(sysdate,'HH24:MI')"
					+ " AND servicioId is not null AND activo = '1' AND eliminado IS NULL AND servicioId IN (SELECT DISTINCT SERVICIOID FROM TBL_SERVICIOS WHERE PREMIUM IS NULL OR PREMIUM <> 1)"
					+ " AND servicioId IN (SELECT DISTINCT SERVICIOID FROM TBL_LOTESENVIOS T1 INNER JOIN TBL_MENSAJES T2 ON T1.LOTEENVIOID = T2.LOTEENVIOID WHERE ESTADOACTUAL = 'PENDIENTE DE ENVIO')");

			List<Object> rows = query.list();
			for (Object row : rows) {
				res.add(((BigDecimal) row).longValue());
			}
					
			if (log.isDebugEnabled()) {
				log.debug(FINALBUSQUEDA);
			}

		} catch (Exception e) {
			log.error(ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> obtenerServiciosSinPlanificacion() {
		List<Long> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(INICIOBUSQUEDA);
			}
			List<Long> listaServiciosAIncluir = getServicioSinPlanificacion();
			if (!listaServiciosAIncluir.isEmpty()){
				String sql = "SELECT distinct ss.servicioID FROM tbl_planificaciones p, tbl_servidores_servicios ss, tbl_SERVIDORES	s "
						+ "where ("
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '1' AND L = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '2' AND M = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '3' AND X = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '4' AND J = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '5' AND V = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '6' AND S = 'S' OR "
						+ "1 + TRUNC(SYSDATE) - TRUNC (SYSDATE, 'IW') = '7' AND D = 'S') "
						+ "AND horadesde <= to_char(sysdate,'HH24:MI') AND horahasta>=to_char(sysdate,'HH24:MI') AND "
						+ "p.servicioId is null AND p.activo = '1' AND p.eliminado IS NULL and "
						+ "p.servidorid = ss.servidorid and s.activo = '1' and ss.servidorid = s.servidorid "
						+ "and ss.servicioid in ("+getStringFromList(listaServiciosAIncluir)+") "
						+ "AND ss.servicioId IN (SELECT DISTINCT SERVICIOID FROM TBL_LOTESENVIOS T1 INNER JOIN TBL_MENSAJES T2 ON T1.LOTEENVIOID = T2.LOTEENVIOID WHERE ESTADOACTUAL = 'PENDIENTE DE ENVIO') "
						+ "AND ss.servicioId IN (SELECT DISTINCT SERVICIOID FROM TBL_SERVICIOS WHERE PREMIUM IS NULL OR PREMIUM <> 1)";
				SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
	
				List<Object> rows = query.list();
				for (Object row : rows) {
					res.add(((BigDecimal) row).longValue());
				}
			}	
			if (log.isDebugEnabled()) {
				log.debug(FINALBUSQUEDA);
			}

		} catch (Exception e) {
			log.error(ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	
	private String getStringFromList(List<Long> listaServiciosAExcluir) {
		StringBuilder res = new StringBuilder();
		for (Long s : listaServiciosAExcluir)
		{
			res.append(s.toString());
			res.append(",");
		}
		return res.toString().substring(0, res.length()-1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Long> getServicioSinPlanificacion() {
		List<Long> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(INICIOBUSQUEDA);
			}

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					"select s.SERVICIOID from TBL_SERVICIOS s where s.SERVICIOID "
					+ "not in (select DISTINCT SERVICIOID from TBL_PLANIFICACIONES where servicioID is not null and ACTIVO =1 and ELIMINADO IS NULL)");

			List<Object> rows = query.list();
			for (Object row : rows) {
				res.add(((BigDecimal) row).longValue());
			}
					
			if (log.isDebugEnabled()) {
				log.debug(FINALBUSQUEDA);
			}

		} catch (Exception e) {
			log.error(ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}
}
