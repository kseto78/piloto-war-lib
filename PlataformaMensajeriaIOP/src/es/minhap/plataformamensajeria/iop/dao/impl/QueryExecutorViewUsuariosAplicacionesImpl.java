package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewUsuariosAplicaciones;
import es.minhap.sim.model.ViewUsuariosAplicaciones;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_APLICACIONES
 * 
 * @author everis
 *
 */
@Service("QueryExecutorViewUsuariosAplicacionesImpl")
public class QueryExecutorViewUsuariosAplicacionesImpl extends HibernateDaoSupport implements QueryExecutorViewUsuariosAplicaciones {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorViewUsuariosAplicacionesImpl.class);
	
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
	public List<ViewUsuariosAplicaciones> findViewUsuarioAplicacion(Long usuarioId, Long aplicacionId) {
		List<ViewUsuariosAplicaciones> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sqlUsuario="";
			if (null != usuarioId && null != aplicacionId){
				sqlUsuario = "where usuarioid = "+ usuarioId + " and aplicacionid = " + aplicacionId;
			}else if (null != usuarioId){
				sqlUsuario = "where usuarioid = " + usuarioId;
			}else if (null != aplicacionId){
				sqlUsuario = "where aplicacionid = " + aplicacionId;
			}
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery("select * from  VIEW_USUARIOS_APLICACIONES " + sqlUsuario);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ViewUsuariosAplicaciones ua = new ViewUsuariosAplicaciones();
				ua.setUsuarioaplicacionid(( row[0] != null)? ((BigDecimal)row[0]).longValue() : null);
				ua.setUsuarioid(( row[1] != null)? ((BigDecimal)row[1]).longValue() : null);
				ua.setAplicacionid(( row[2] != null)? ((BigDecimal)row[2]).longValue() : null);
				ua.setModo(( row[3] != null)? ((BigDecimal)row[3]).intValue() : null);
				ua.setFechacreacion(( row[4] != null)? (Date)row[4] : null);
				ua.setCreadopor(( row[5] != null)? (String)row[5] : null);
				ua.setNombreaplicacion(( row[6] != null)? (String)row[6] : null);
				ua.setNombreusuario(( row[7] != null)? (String)row[7] : null);
				ua.setRolid(( row[8] != null)? ((BigDecimal)row[8]).longValue() : null);
				ua.setActivo(( row[9] != null)? ((BigDecimal)row[9]).intValue() : null);
				ua.setEliminado(( row[10] != null)? (String)row[10] : null);
				ua.setRolusuario(( row[11] != null)? (String)row[11] : null);
				
			    res.add(ua);
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

}
