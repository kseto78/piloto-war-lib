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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosHist;
import es.minhap.sim.model.TblDestinatarios;
import es.minhap.sim.model.TblDestinatariosHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorDestinatariosHistImpl")
public class QueryExecutorDestinatariosHistImpl extends HibernateDaoSupport implements QueryExecutorDestinatariosHist {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorDestinatariosHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblDestinatariosHist> convertDestinatarioTODestinatarioHist(List<Long> subList, Integer max, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m from TblDestinatarios m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList("lista", subList);
			query.setMaxResults(max);
			query.setFirstResult(firstResult);
			return convertTOHistDestinatarios(query.list());
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	@Override
	public Integer countConvertDestinatarioTODestinatarioHist(List<Long> subList) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m) from TblDestinatarios m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList("lista", subList);
			
			return ((Long)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdDestinatariosCons(List<Long> listaMensajes) {
		try {
			List<Long> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select d.destinatarioid from TblDestinatariosHist d where d.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameterList("lista", listaMensajes);
			res = query.list();
			if (null == res){
				res = new ArrayList<>();
				return res;
			}else{
				return res;
			}
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	private List<TblDestinatariosHist> convertTOHistDestinatarios(List<TblDestinatarios> lista) {
		List<TblDestinatariosHist> res = new ArrayList<>();
		if (null != lista){
			for (TblDestinatarios destinatario : lista) {
				TblDestinatariosHist d = new TblDestinatariosHist();
				d.setCreadopor(destinatario.getCreadopor());
				d.setDestinatarioid(destinatario.getDestinatarioid());
				d.setEmail(destinatario.getEmail());
				d.setFechacreacion(destinatario.getFechacreacion());
				d.setFechamodificacion(destinatario.getFechamodificacion());
				d.setMensajeid(destinatario.getMensajeid());
				d.setModificadopor(destinatario.getModificadopor());
				d.setNombre(destinatario.getNombre());
				d.setTipodestinatario(destinatario.getTipodestinatario());
				res.add(d);
			}
		}
		return res;
	}

	
	
}
