package es.minhap.plataformamensajeria.iop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAdjuntos;
import es.minhap.sim.model.TblAdjuntos;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorAdjuntosImpl")
public class QueryExecutorAdjuntosImpl extends HibernateDaoSupport implements QueryExecutorAdjuntos {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorAdjuntosImpl.class);
	
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
	public List<TblAdjuntos> getAdjuntosByMensaje(Long mensajeId) {
		try {
			List<TblAdjuntos> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ma.tblAdjuntos from TblMensajesAdjuntos ma where ma.tblMensajes.mensajeid = :mensajeId ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong("mensajeId", mensajeId);
			res = query.list();
			if (null == res){
				return new ArrayList<>();
			}else{
				return res;
			}
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}




}
