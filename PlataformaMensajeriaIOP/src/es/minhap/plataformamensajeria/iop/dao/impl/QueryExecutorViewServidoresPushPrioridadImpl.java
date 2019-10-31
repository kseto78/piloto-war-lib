package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewServidoresPushPrioridad;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorWebPush;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla
 * 
 * @author everis
 * 
 */
@Service
@Transactional
public class QueryExecutorViewServidoresPushPrioridadImpl extends HibernateDaoSupport implements
		QueryExecutorViewServidoresPushPrioridad {

	protected static final String R_CONST_1 = "      \t\tAND url is not null";

	protected static final String R_CONST_2 = " and servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid";

	protected static final String R_CONST_3 = ") OR servicioId IS NULL)";

	protected static final String R_CONST_4 = "search - end";

	protected static final String R_CONST_5 = "unchecked";

	protected static final String R_CONST_6 = "\t    order by Prioridad";

	protected static final String R_CONST_7 = "\t\t\t                  WHERE mensajeid  = ";

	protected static final String R_CONST_8 = "      WHERE ( plataformaID = ";

	protected static final String R_CONST_9 = "Se ha producido un error ";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorViewServidoresPushPrioridadImpl.class);
	
	@Resource
	private TblMensajesManager mensajesManager;

	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings(R_CONST_5)
	@Override
	public List<ParametrosServidorPush> getServidoresPush(Long mensajeId, Integer plataformaId) {
		List<ParametrosServidorPush> pss = new ArrayList<>();
		try {

			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getMensajesManager().getPrioridadByIdMessage(mensajeId);
			SQLQuery query;
			
			if (null != prioridad && prioridad.intValue() == 1) {
				StringBuilder queryString = new StringBuilder();
				queryString.append("SELECT servidorpushid,plataformaid,url,urlfeedback,puertourl,puertourlfeedback,servicioid,prioridad FROM view_servidorespush_prioridad");
				queryString.append(R_CONST_8);
				queryString.append("?");
				queryString.append(R_CONST_2);
				queryString.append(R_CONST_7);
				queryString.append("?");
				queryString.append("R_CONST_3");
				queryString.append(R_CONST_1);
				queryString.append(R_CONST_6);
				query = getSessionFactory()
						.getCurrentSession()
						.createSQLQuery(queryString.toString());
				query.setInteger(0,plataformaId);
				query.setLong(1,mensajeId);
			} else {
				StringBuilder queryString = new StringBuilder();
				queryString.append("SELECT servidorpushid,plataformaid,url,urlfeedback,puertourl,puertourlfeedback,servicioid,prioridad FROM VIEW_SERV_SERVIDOR_PUSH");
				queryString.append(R_CONST_8);
				queryString.append("?");
				queryString.append(R_CONST_2);
				queryString.append(R_CONST_7);
				queryString.append("?");
				queryString.append(R_CONST_3);
				queryString.append(R_CONST_1);
				queryString.append(R_CONST_6);
				query = getSessionFactory()
						.getCurrentSession()
						.createSQLQuery(queryString.toString());
				query.setInteger(0,plataformaId);
				query.setLong(1, mensajeId);
			}
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosServidorPush ps = new ParametrosServidorPush();
				ps.setServidorPushId((null != row[0])? ((BigDecimal) row[0]).intValue() : 0);
				ps.setPlataformaId((null != row[1])? ((BigDecimal) row[1]).intValue() : 0);
				ps.setUrl((null != row[2])?(String) row[2] : "");
				ps.setUrlFeedback((null != row[3])? (String) row[3] : "");
				ps.setPuertoUrl((null != row[4])? Integer.parseInt((String) row[4]) : 0);
				ps.setPuertoUrlFeedback((null != row[5])? Integer.parseInt((String) row[5]) : 0);
				ps.setServicioId((null != row[6])? ((BigDecimal) row[6]).intValue() : 0);
				ps.setPrioridad((null != row[7])? ((BigDecimal) row[7]).intValue() : 0);
				pss.add(ps);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(R_CONST_4);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_9, e);
			throw new ApplicationException(e);
		}
		return pss;
	}
	
	
	@SuppressWarnings(R_CONST_5)
	@Override
	public List<ParametrosServidorWebPush> getServidoresWebPush(Long mensajeId) {
		List<ParametrosServidorWebPush> pss = new ArrayList<>();
		try {

			SQLQuery query;
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT distinct servidorwebpushid,servicioid, prioridad FROM VIEW_SERV_SERVIDOR_WEBPUSH ");
			sb.append("WHERE ( servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid");
			sb.append(" WHERE mensajeid  = ");
			sb.append("?");
			sb.append(") OR servicioId IS NULL) order by Prioridad");
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(sb.toString());
			query.setLong(0, mensajeId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosServidorWebPush ps = new ParametrosServidorWebPush();
				ps.setServidorWebPushId((null != row[0]) ? ((BigDecimal) row[0]).intValue() : 0);
				ps.setServicioId((null != row[1]) ? ((BigDecimal) row[1]).intValue() : 0);
				pss.add(ps);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(R_CONST_4);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_9, e);
			throw new ApplicationException(e);
		}
		return pss;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}
}
