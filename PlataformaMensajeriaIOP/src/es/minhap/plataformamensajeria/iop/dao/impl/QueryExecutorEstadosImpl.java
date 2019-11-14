package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorEstados;

/**
 * Query Executor encargado de lanzar las consultas relacionadas con la TBL_ESTADOS 
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorEstadosImpl extends HibernateDaoSupport implements QueryExecutorEstados {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorEstadosImpl.class);

	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public EstadosBean getEstadoByLoteId(Long loteId) {
		EstadosBean res = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}

			//obtenemos estado por prioridad
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("select estado, estadoid, prioridad From "
							+ "(select distinct m.estadoactual as estado, st.estadoid as estadoid, "
							+ "st.prioridad from TBL_MENSAJES m inner join TBL_ESTADOS st "
							+ "on (st.nombre = case when m.ESTADOACTUAL = 'PENDIENTE' "
							+ "then 'PENDIENTE DE ENVIO' else m.estadoactual end)"
							+ " where m.LOTEENVIOID = "+loteId+" order by st.PRIORIDAD asc) "
							+ "where rownum < 2");
			
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
			    res = new EstadosBean();
			    res.setDescripcion((String) row[0]);
			    res.setEstadoId(((BigDecimal) row[1]).longValue());
			    
			}
			if(null != res)
				return res;
			
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public EstadosBean getEstadoByMensajeId(Long mensajeId) {
		EstadosBean res = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			
			//obtiene el estado por prioridad
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("select estado, estadoid From ("
							+ "select distinct st.nombre as estado, st.estadoid as estadoid, "
							+ "st.prioridad from TBL_ESTADOS st  "
							+ "inner join TBL_DESTINATARIOS_MENSAJES dm on "
							+ "(st.nombre = case when dm.estado = 'PENDIENTE' then 'PENDIENTE DE ENVIO' "
							+ "else dm.estado end) where dm.MENSAJEID = "+mensajeId+" order by "
							+ "st.PRIORIDAD asc) where rownum < 2");

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
			    res = new EstadosBean();
			    res.setDescripcion((String) row[0]);
			    res.setEstadoId(((BigDecimal) row[1]).longValue());
			    
			}
			if(null != res)
				return res;
			
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String getEstadoByMensajeIdUsuarioPush(Long mensajeId, List<String> listaUsuarios) {
		String res;
		Map<String, BigDecimal> map = new HashMap<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String usuarios = listaUsuarios.toString().replace("[", "'").replace("]", "'")
		            .replace(", ", "','");
			//obtiene el estado del mensaje y usuario Push
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT ESTADO, count(*) as TOTAL from TBL_DESTINATARIOS_MENSAJES where "
					+ "MENSAJEID = "+mensajeId+" and DESTINATARIO in ("+usuarios+") group by ESTADO");

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				BigDecimal total = (BigDecimal) row[1];
				String key = (String) row[0];
				map.put(key, total);
			}
			if (map.size()==1)
				res = map.entrySet().iterator().next().getKey();
			else
				res = "INCIDENCIA";
			if(null != res)
				return res;
			
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}



	
}
