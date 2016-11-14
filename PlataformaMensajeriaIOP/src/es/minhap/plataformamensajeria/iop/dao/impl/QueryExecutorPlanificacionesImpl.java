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
					+ " where ((to_char(sysdate,'DY') = 'LUN' OR to_char(sysdate,'DY') = 'MON') AND L = 'S' OR "
					+ " (to_char(sysdate,'DY') = 'MAR' OR to_char(sysdate,'DY') = 'TUE') AND M = 'S' OR "
					+ " (to_char(sysdate,'DY') = 'MIÉ' OR to_char(sysdate,'DY') = 'WED') AND X = 'S' OR "
					+ " (to_char(sysdate,'DY') = 'JUE' OR to_char(sysdate,'DY') = 'THU') AND J = 'S' OR	"
					+ " (to_char(sysdate,'DY') = 'VIE' OR to_char(sysdate,'DY') = 'FRI') AND V = 'S' OR	"
					+ " (to_char(sysdate,'DY') = 'SAB' OR to_char(sysdate,'DY') = 'SAT') AND S = 'S' OR "
					+ " (to_char(sysdate,'DY') = 'DOM' OR to_char(sysdate,'DY') = 'SUN') AND D = 'S')"
					+ " AND horadesde <= to_char(sysdate,'HH24:MI') AND horahasta>=to_char(sysdate,'HH24:MI')"
					+ " AND servicioId is not null AND activo = '1' AND eliminado IS NULL");

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
			List<Long> listaServiciosAExcluir = getServidoresConPlanificacion();
			if (!listaServiciosAExcluir.isEmpty()){
				String sql = "SELECT distinct ss.servicioID FROM tbl_planificaciones p, tbl_servidores_servicios ss, tbl_SERVIDORES	s "
						+ "where ((to_char(sysdate,'DY') = 'LUN' OR to_char(sysdate,'DY') = 'MON') AND L = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'MAR' OR to_char(sysdate,'DY') = 'TUE') AND M = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'MIÉ' OR to_char(sysdate,'DY') = 'WED') AND X = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'JUE' OR to_char(sysdate,'DY') = 'THU') AND J = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'VIE' OR to_char(sysdate,'DY') = 'FRI') AND V = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'SAB' OR to_char(sysdate,'DY') = 'SAT') AND S = 'S' OR "
						+ "(to_char(sysdate,'DY') = 'DOM' OR to_char(sysdate,'DY') = 'SUN') AND D = 'S') AND "
						+ "horadesde <= to_char(sysdate,'HH24:MI') AND horahasta>=to_char(sysdate,'HH24:MI') AND "
						+ "p.servicioId is null AND p.activo = '1' AND p.eliminado IS NULL and "
						+ "p.servidorid = ss.servidorid and s.activo = '1' and ss.servidorid = s.servidorid "
						+ "and ss.servicioid in ("+getStringFromList(listaServiciosAExcluir)+")";
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
	public List<Long> getServidoresConPlanificacion() {
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
