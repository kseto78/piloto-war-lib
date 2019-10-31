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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewReceptoresPrioridad;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorViewReceptoresPrioridadImpl extends HibernateDaoSupport implements QueryExecutorViewReceptoresPrioridad {

	protected static final String R_CONST_1 = "      OR servicioId IS NULL)";

	protected static final String R_CONST_2 = "\t\t\t\t            WHERE mensajeid = ";

	protected static final String R_CONST_3 = ")";

	protected static final String R_CONST_4 = "      order by Prioridad";

	protected static final String R_CONST_5 = "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid";

	protected static final String R_CONST_6 = "      AND url is not null";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorViewReceptoresPrioridadImpl.class);
	
	@Resource
	private TblMensajesManager mensajesManager;
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosReceptor> getReceptores(Long mensajeId)  {
		List<ParametrosReceptor> pss = new ArrayList<>();
		
		try {
			
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getMensajesManager().getPrioridadByIdMessage(mensajeId);
			SQLQuery query;
			StringBuilder queryString = new StringBuilder();
			if (null != prioridad && prioridad.intValue() == 1) {
				queryString.append("SELECT receptorid,url,id,telefono,texto,uim,servicioid,prioridad FROM view_receptores_prioridad");
				queryString.append(R_CONST_5);
				queryString.append(R_CONST_2);
				queryString.append("?");
				queryString.append(R_CONST_3);
				queryString.append(R_CONST_1);
				queryString.append(R_CONST_6);
				queryString.append(R_CONST_4);
				query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
				query.setLong(0, mensajeId);


			} else {
				queryString.append("SELECT receptorid,url,id,telefono,texto,uim,servicioid,prioridad FROM VIEW_SERV_RECEPTOR_SMS");
				queryString.append(R_CONST_5);
				queryString.append(R_CONST_2);
				queryString.append("?");
				queryString.append(R_CONST_3);
				queryString.append(R_CONST_1);
				queryString.append(R_CONST_6);
				queryString.append(R_CONST_4);
				query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
				query.setLong(0, mensajeId);

			}
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosReceptor ps = new ParametrosReceptor();
				ps.setReceptorId(((BigDecimal) row[0]).intValue());
				ps.setUrl((String) row[1]);
				ps.setId((String) row[2]);
				ps.setTelefono((String) row[3]);
				ps.setTexto((String) row[4]);
				pss.add(ps);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("search - end");
			}
			
		} catch (Exception e) {
			LOG.error("Se ha producido un error ", e);
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
