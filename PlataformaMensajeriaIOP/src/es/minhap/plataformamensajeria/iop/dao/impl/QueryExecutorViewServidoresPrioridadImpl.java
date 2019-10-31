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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewServidoresPrioridad;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorViewServidoresPrioridadImpl extends HibernateDaoSupport implements QueryExecutorViewServidoresPrioridad {

	protected static final String R_CONST_1 = ")";

	protected static final String R_CONST_2 = "      order by Prioridad";

	protected static final String R_CONST_3 = "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid";

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorViewServidoresPrioridadImpl.class);
	
	@Resource
	private TblMensajesManager mensajesManager;
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosServidor> getServidores(Long mensajeId) {
		List<ParametrosServidor> pss = new ArrayList<>();
		try {
			
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getMensajesManager().getPrioridadByIdMessage(mensajeId);
			SQLQuery query;
			StringBuilder queryString = new StringBuilder();
			if (prioridad.intValue() == 1) {
				queryString.append("  SELECT servidorid,ip,usuario,contrasena,conexion,puerto,autentificacion,servicioid,prioridad,tiempoespera");
				queryString.append("      FROM view_servidores_prioridad");
				queryString.append(R_CONST_3);
				queryString.append("             WHERE mensajeid = ");
				queryString.append("?");
				queryString.append(R_CONST_1);
				queryString.append("      OR servicioId IS NULL)");
				queryString.append("      AND ip is not null");
				queryString.append("R_CONST_2");
				
				query = getSessionFactory().getCurrentSession()
						.createSQLQuery(queryString.toString());
				query.setLong(0, mensajeId);

			} else {
				queryString.append("SELECT servidorid,ip,usuario,contrasena,conexion,puerto,autentificacion,servicioid,prioridad,tiempoespera");
				queryString.append("      FROM VIEW_SERV_EMAIL");
				queryString.append(R_CONST_3);
				queryString.append("		        WHERE mensajeid = ");
				queryString.append("?");
				queryString.append(R_CONST_1);
				queryString.append("		 OR servicioId IS NULL)");
				queryString.append("   	 AND ip is not null");
				queryString.append(R_CONST_2);
				query = getSessionFactory().getCurrentSession()
						.createSQLQuery(queryString.toString());
				query.setLong(0, mensajeId);
			}
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ParametrosServidor ps = new ParametrosServidor();
				ps.setServidor(((BigDecimal) row[0]).intValue());
				ps.setIP((String) row[1]);
				ps.setUsuario((String) row[2]);
				ps.setContrasena((String) row[3]);
				ps.setConexion((String) row[4]);
				ps.setPuerto((String) row[5]);
				ps.setAutentificacion((String) row[6]);
				ps.setTimeOut((String) row[9]);
				pss.add(ps);
			}
			if (log.isDebugEnabled()) {
				log.debug("search - end");
			}
			
		} catch (Exception e) {
			log.error("Se ha producido un error ", e);
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
