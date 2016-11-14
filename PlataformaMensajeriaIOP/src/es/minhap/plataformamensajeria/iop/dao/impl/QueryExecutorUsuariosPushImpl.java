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
import es.minhap.plataformamensajeria.iop.beans.UsuariosServiciosMovilesBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional
public class QueryExecutorUsuariosPushImpl extends HibernateDaoSupport implements QueryExecutorUsuariosPush {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorUsuariosPushImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";

	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> listaUsuariosDispositivosPush(String identificadorUsuario, Integer servicioId) {
		List<BigDecimal> result = new ArrayList<>();
		List<Long> listaIdUsuarios = new ArrayList<>();

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate()
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"select USUARIOID from TBL_USUARIOS_PUSH where NOMBREUSUARIO = '" + identificadorUsuario
									+ "' and SERVICIOID = " + servicioId
									+ " AND (ELIMINADO != 'S' OR ELIMINADO IS NULL)");

			if (query.list() != null) {
				result = query.list();
			}

			for (BigDecimal bigDecimal : result) {
				listaIdUsuarios.add(bigDecimal.longValue());
			}

			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.listaUsuariosDispositivosPush] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return listaIdUsuarios;
	}

	@Override
	@Transactional
	public Integer getNextDispositivo() {
		Integer res = null;

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery("select DISPOSITIVO_SEC.NEXTVAL from dual");

			BigDecimal next = (BigDecimal) query.uniqueResult();

			if (null != next)
				res = next.intValue();

			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getNextDispositivo] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario) {
		List<String> listaIdUsuarios = new ArrayList<>();
		List<BigDecimal> result = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "SELECT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID = '" + idDispositivo + "'";
			if (null != nombreUsuario)
				sql = sql + " AND NOMBREUSUARIO = '" + nombreUsuario + "'";
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			result = query.list();
			for (BigDecimal bigDecimal : result) {
				listaIdUsuarios.add(bigDecimal.toString());
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getIdUsersFromUserName] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return listaIdUsuarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BigDecimal> getUsuarioConsultaServiciosDisponibles(String idDispositivo, String idServicioMovil) {

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "SELECT UP.USUARIOID as ID from TBL_USUARIOS_PUSH UP, TBL_USUARIOS_SERVICIOSMOVILES US, "
					+ "TBL_SERVICIOSMOVILES SERV where UP.USUARIOID = US.USUARIOSID AND up.USUARIOID "
					+ "IN (SELECT  DISTINCT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID='" + idDispositivo
					+ "') AND US.SERVICIOSMOVILESID = SERV.SERVICIOSMOVILESID "
					+ "AND US.ESTADOSUSCRIPCION= 1 AND SERV.SERVICIOSMOVILESID = " + idServicioMovil;

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			return query.list();

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getIdUsersFromUserName] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}

	}

	@Override
	public Integer getPlataformaUsuario(Long mensajeId) {
		Integer res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate()
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT PLATAFORMAID from TBL_USUARIOS_PUSH pu INNER JOIN TBL_MENSAJES m on m.USUARIOID = pu.USUARIOID  WHERE m.MENSAJEID = "
									+ mensajeId);

			res = (Integer) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getNextDispositivo] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UsuariosServiciosMovilesBean> getUsuarioPorServicio(Integer servicioID) {
		List<UsuariosServiciosMovilesBean> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "SELECT US.USUARIOSID, UP.NOMBREUSUARIO FROM TBL_USUARIOS_SERVICIOSMOVILES US , "
					+ "TBL_USUARIOS_PUSH UP  WHERE  US.SERVICIOSMOVILESID = "+servicioID+" AND UP.USUARIOID = US.USUARIOSID AND US.ESTADOSUSCRIPCION=1 AND "
							+ "(UP.ELIMINADO != 'S' or UP.ELIMINADO IS NULL)";

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				UsuariosServiciosMovilesBean us = new UsuariosServiciosMovilesBean();
				us.setIdUsuario(((BigDecimal) row[0]).intValue());
				us.setUsuario(((String) row[1] != null) ? (String) row[1] : "");
				res.add(us);
			}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getIdUsersFromUserName] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Integer> getDispositivosUsuarioServicioMovil(String identificadorUsuario, Integer servicioID, Integer idServicioMovil) {
		List<BigDecimal> result = new ArrayList<>();
		ArrayList<Integer> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "select us.USUARIOSID FROM TBL_USUARIOS_SERVICIOSMOVILES us inner join TBL_USUARIOS_PUSH up on us.USUARIOSID = up.USUARIOID"
					+ " WHERE UP.NOMBREUSUARIO = '" + identificadorUsuario +"'  and UP.SERVICIOID = " + servicioID + " AND US.SERVICIOSMOVILESID = " + idServicioMovil
					+ " AND (UP.ELIMINADO != 'S' OR UP.ELIMINADO IS NULL)";
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

				if (query.list() != null) {
					result = query.list();
				}

				for (BigDecimal bigDecimal : result) {
					res.add(bigDecimal.intValue());
				}

		} catch (Exception e) {
			LOG.error("[QueryExecutorUsuariosPushImpl.getIdUsersFromUserName] Se ha producido un error ", e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
}
