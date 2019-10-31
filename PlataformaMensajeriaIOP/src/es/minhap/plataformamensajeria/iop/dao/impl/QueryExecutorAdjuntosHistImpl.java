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

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAdjuntosHist;
import es.minhap.sim.model.TblAdjuntosHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorAdjuntosHistImpl")
public class QueryExecutorAdjuntosHistImpl extends HibernateDaoSupport implements QueryExecutorAdjuntosHist {

	protected static final String R_CONST_1 = "unchecked";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorAdjuntosHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@SuppressWarnings(R_CONST_1)
	@Override
	public List<Long> getIdAdjuntosCons(List<Long> listaMensajes) {
		try {
			List<Long> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ma.tblAdjuntosHist.adjuntoid from TblMensajesAdjuntosHist ma where ma.tblMensajesHist.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameterList("lista", listaMensajes);
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


	@SuppressWarnings(R_CONST_1)
	@Override
	public List<TblAdjuntosHist> getAdjuntosByMensaje(Long mensajeId) {
		try {
			List<TblAdjuntosHist> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ma.tblAdjuntosHist from TblMensajesAdjuntosHist ma where ma.tblMensajesHist.mensajeid = :mensajeId ";
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
