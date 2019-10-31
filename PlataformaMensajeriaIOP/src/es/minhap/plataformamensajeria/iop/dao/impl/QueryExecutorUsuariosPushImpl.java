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
import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
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

	protected static final String R_CONST_1 = "[QueryExecutorUsuariosPushImpl.getIdUsersFromUserName] Se ha producido un error ";

	protected static final String R_CONST_2 = "'";

	protected static final String R_CONST_3 = "unchecked";

	protected static final String R_CONST_4 = "[QueryExecutorUsuariosPushImpl.getNextDispositivo] Se ha producido un error ";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorUsuariosPushImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";

	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings(R_CONST_3)
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
							"select USUARIOID from TBL_USUARIOS_PUSH where NOMBREUSUARIO = ?"
									+ " and SERVICIOID = ?"
									+ " AND (ELIMINADO != 'S' OR ELIMINADO IS NULL)");
			query.setString(0, identificadorUsuario);
			query.setInteger(1, servicioId);
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
	public List<UsuariosPushBean> listaUsuariosPushbyServicioMovilId(Long servicioMovilId) {
		List<UsuariosPushBean> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("select t2.nombreusuario, t2.nombre as nomuser, t2.apellido1, t2.apellido2, t2.dispositivoid, t3.nombre as nomplat");
			queryBuilder.append(" from tbl_usuarios_Serviciosmoviles t1 ");
			queryBuilder.append(" inner join tbl_usuarios_push t2 on t1.usuariosid = t2.usuarioid");
			queryBuilder.append(" inner join tbl_plataformas t3 on t2.plataformaid = t3.plataformaid");
			queryBuilder.append(" where serviciosmovilesid= ?"); 

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(queryBuilder.toString());
			query.setLong(0, servicioMovilId);
			
			@SuppressWarnings(R_CONST_3)
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				UsuariosPushBean usupush = new UsuariosPushBean();
				usupush.setNombreUsuario((String)row[0]);
				usupush.setNombre((String) row[1]);
				usupush.setApellido1((String) row[2]);
				usupush.setApellido2((String) row[3]);
				usupush.setDispositivoId((String) row[4]);
				usupush.setPlataforma((String) row[5]);
				res.add(usupush);
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
	public Integer getNextDispositivo() {
		Integer res = null;

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery("select DISPOSITIVO_SEC.NEXTVAL from dual");

			BigDecimal next = (BigDecimal) query.uniqueResult();

			if (null != next) {
				res = next.intValue();
			}

			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_4, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@SuppressWarnings(R_CONST_3)
	@Override
	@Transactional
	public List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario) {
		List<String> listaIdUsuarios = new ArrayList<>();
		List<BigDecimal> result = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID = ");
			queryString.append("?");
			queryString.append(R_CONST_2);
			if (null != nombreUsuario) {
				queryString.append(queryString.toString() + " AND NOMBREUSUARIO = '" + nombreUsuario + R_CONST_2);
			}
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			query.setString(0, idDispositivo);
			
			result = query.list();
			for (BigDecimal bigDecimal : result) {
				listaIdUsuarios.add(bigDecimal.toString());
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_1, e);
			throw new ApplicationException(e);
		}
		return listaIdUsuarios;
	}

	@SuppressWarnings(R_CONST_3)
	@Override
	@Transactional
	public List<BigDecimal> getUsuarioConsultaServiciosDisponibles(String idDispositivo, String idServicioMovil) {

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "SELECT UP.USUARIOID as ID from TBL_USUARIOS_PUSH UP, TBL_USUARIOS_SERVICIOSMOVILES US, "
					+ "TBL_SERVICIOSMOVILES SERV where UP.USUARIOID = US.USUARIOSID AND up.USUARIOID "
					+ "IN (SELECT  DISTINCT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID= ?" 
					+ ") AND US.SERVICIOSMOVILESID = SERV.SERVICIOSMOVILESID "
					+ "AND US.ESTADOSUSCRIPCION= 1 AND SERV.SERVICIOSMOVILESID = ?";

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setString(0, idDispositivo);
			query.setString(1, idServicioMovil);
			return query.list();

		} catch (Exception e) {
			LOG.error(R_CONST_1, e);
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
							"SELECT PLATAFORMAID from TBL_USUARIOS_PUSH pu INNER JOIN TBL_MENSAJES m on m.USUARIOID = pu.USUARIOID  WHERE m.MENSAJEID = ?");
			query.setLong(0, mensajeId);

			res = (Integer) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_4, e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	@Transactional
	public List<UsuariosServiciosMovilesBean> getUsuarioPorServicio(Integer servicioID) {
		List<UsuariosServiciosMovilesBean> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "SELECT US.USUARIOSID, UP.NOMBREUSUARIO FROM TBL_USUARIOS_SERVICIOSMOVILES US , "
					+ "TBL_USUARIOS_PUSH UP  WHERE  US.SERVICIOSMOVILESID = ? AND UP.USUARIOID = US.USUARIOSID AND US.ESTADOSUSCRIPCION=1 AND "
							+ "(UP.ELIMINADO != 'S' or UP.ELIMINADO IS NULL)";

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, servicioID);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				UsuariosServiciosMovilesBean us = new UsuariosServiciosMovilesBean();
				us.setIdUsuario(((BigDecimal) row[0]).intValue());
				us.setUsuario(((String) row[1] != null) ? (String) row[1] : "");
				res.add(us);
			}

		} catch (Exception e) {
			LOG.error(R_CONST_1, e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	@SuppressWarnings(R_CONST_3)
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
					+ " WHERE UP.NOMBREUSUARIO = ? and UP.SERVICIOID = ? AND US.SERVICIOSMOVILESID = ? AND (UP.ELIMINADO != 'S' OR UP.ELIMINADO IS NULL)";
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setString(0, identificadorUsuario);
			query.setInteger(1, servicioID);
			query.setInteger(2, idServicioMovil);
			
				if (query.list() != null) {
					result = query.list();
				}

				for (BigDecimal bigDecimal : result) {
					res.add(bigDecimal.intValue());
				}

		} catch (Exception e) {
			LOG.error(R_CONST_1, e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
}
