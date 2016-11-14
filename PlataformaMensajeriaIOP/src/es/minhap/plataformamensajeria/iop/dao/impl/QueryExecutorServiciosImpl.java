package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVICIOS
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorServiciosImpl extends HibernateDaoSupport implements QueryExecutorServicios {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorServiciosImpl.class);
	
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
	public Integer comprobarServicioUnico(String recipient, Long canalId) {
		BigDecimal res = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					" SELECT count(*) FROM tbl_servicios ser, "
					+ "tbl_servidores_servicios ss WHERE ss.headersms = '" +recipient
					+ "' and SER.SERVICIOID = SS.SERVICIOID AND ser.canalid = "+canalId+ " AND "
					+ "ser.activo = 1");

			res = (BigDecimal) query.uniqueResult();
						
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res.intValue();
	}

	@Override
	@Transactional
	public Integer obtenerServicio(String codOrganismoPagadorSMS, String canal) {
		Integer res = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					 "SELECT s.SERVICIOID FROM TBL_ORGANISMOS_SERVICIO os "
					+ "INNER JOIN TBL_SERVICIOS s ON s.SERVICIOID = os.SERVICIOID "
					+ "INNER JOIN TBL_ORGANISMOS o ON os.ORGANISMOID = o.ORGANISMOID WHERE "
					+ "s.CANALID = "+canal+" and s.activo = 1 and s.premium = 1 and o.activo = 1 and o.DIR3 = '" +codOrganismoPagadorSMS+"'");
			res = (Integer) query.uniqueResult();
			if(null != res)
				return res.intValue();
			
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
	public List<String> findServicioByMessageId(Long idMensaje) {
		List<String> res = new ArrayList<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("select serv.SERVICIOID, serv.MULTIORGANISMO from  TBL_SERVICIOS SERV, TBL_LOTESENVIOS LOT, TBL_MENSAJES MEN WHERE MEN.mensajeID = "
							+ idMensaje
							+ " and LOT.loteenvioid = MEN.loteenvioid and serv.SERVICIOID = LOT.servicioid");
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				res.add(((BigDecimal) row[0]).toString() + "~" +((null != (BigDecimal) row[1]) ? ((BigDecimal) row[1]).toString() : 0));
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
	public Map<Integer, Servicio> findServicioMultiorganismo(Long idServicio, Long idMensaje) {
		Map<Integer, Servicio> res = new HashMap<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT TBL_ORGANISMOS.NOMBRE, TBL_ORGANISMOS.DESCRIPCION, TBL_ORGANISMOS.ACTIVO, "
							+ "TBL_SERVIDORES_ORGANISMOS.SERVIDORID, TBL_SERVIDORES_ORGANISMOS.ORGANISMOID, TBL_SERVIDORES_ORGANISMOS.HEADERSMS, "
							+ "TBL_SERVIDORES_ORGANISMOS.NUMINTENTOS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORUSUARIOSMS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORPASSWORDSMS, "
							+ "TBL_ORGANISMOS.DIR3, TBL_SERVICIOS.SERVICIOID "
							+ "FROM TBL_SERVIDORES_ORGANISMOS, TBL_SERVICIOS, TBL_MENSAJES, TBL_ORGANISMOS, TBL_LOTESENVIOS, TBL_SERVIDORES "
							+ "WHERE (TBL_ORGANISMOS.DIR3 = TBL_MENSAJES.CODORGANISMOPAGADOR) "
							+ "AND (TBL_ORGANISMOS.ORGANISMOID = TBL_SERVIDORES_ORGANISMOS.ORGANISMOID) "
							+ "AND (TBL_LOTESENVIOS.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
							+ "AND (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) "
							+ "AND (TBL_MENSAJES.MENSAJEID = "
							+ idMensaje
							+ ") AND (TBL_LOTESENVIOS.SERVICIOID = "
							+ idServicio + ")"
							+ "AND (TBL_SERVIDORES.SERVIDORID = TBL_SERVIDORES_ORGANISMOS.SERVIDORID) "
							+ "AND (TBL_SERVIDORES.ACTIVO = 1)");
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				
				Servicio servicio = new Servicio();
				servicio.setNombre((String) row[0]);
				servicio.setDescripcion((String) row[1]);
				servicio.setServicioId(((BigDecimal) row[3]).intValue());
				servicio.setProveedorUsuarioSMS((String) row[7]);
				servicio.setProveedorPassSMS((String) row[8]);
				servicio.setHeaderSMS((String) row[5]);
				res.put(((BigDecimal) row[3]).intValue(), servicio);
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
	public Map<Integer, Servicio> findServicio(Long idServicio) {
		Map<Integer, Servicio> res = new HashMap<>();
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession()
					.createSQLQuery("SELECT ss.servidorId, ss.headersms, s.nombre,s.descripcion, ss.proveedorusuariosms, ss.proveedorpasswordsms"
							+ " FROM TBL_SERVICIOS s inner join TBL_SERVIDORES_SERVICIOS ss on s.SERVICIOID = ss.SERVICIOID"
							+ " WHERE s.SERVICIOID = " + idServicio);
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				Servicio servicio = new Servicio();
				servicio.setServicioId(((BigDecimal) row[0]).intValue());
				servicio.setHeaderSMS((String) row[1]);
				servicio.setNombre((String) row[2]);
				servicio.setDescripcion((String) row[3]);
				servicio.setProveedorUsuarioSMS((String) row[4]);
				servicio.setProveedorPassSMS((String) row[5]);
				res.put(((BigDecimal) row[0]).intValue(), servicio);
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
}
