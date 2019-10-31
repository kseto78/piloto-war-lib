package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajesHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblDestinatariosMensajes;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorDestinatariosMensajesHistImpl")
public class QueryExecutorDestinatariosMensajesHistImpl extends HibernateDaoSupport implements QueryExecutorDestinatariosMensajesHist {

	protected static final String R_CONST_1 = "lista";

	protected static final String R_CONST_2 = "mensajeId";

	protected static final String R_CONST_3 = "unchecked";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorDestinatariosMensajesHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@SuppressWarnings(R_CONST_3)
	@Override
	public List<TblDestinatariosMensHist> convertDestinatarioMensTODestinatarioMensHist(List<Long> subList, Integer max, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m from TblDestinatariosMensajes m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList(R_CONST_1, subList);
			
			return convertTOHistDestinatariosMens(query.list());
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countConvertDestinatarioMensTODestinatarioMensHist(List<Long> subList) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m) from TblDestinatariosMensajes m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList(R_CONST_1, subList);
			
			return ((Long) query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public List<Long> getIdDestinatariosMensajesCons(List<Long> listaMensajes) {
		try {
			List<Long> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select dm.destinatariosmensajes from TblDestinatariosMensHist dm where dm.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameterList(R_CONST_1, listaMensajes);
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

	@Override
	public Integer countDestinatariosByMensaje(Long idMensaje) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(dm.destinatariosmensajes) from TBL_DESTINATARIOS_MENS_HIST dm  where dm.mensajeid = :mensajeId ";
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong(R_CONST_2, idMensaje);
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	

	@SuppressWarnings(R_CONST_3)
	@Override
	public List<TblDestinatariosMensHist> getDestinatarioMensHist(Long idMensaje, int size, int start) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "from TblDestinatariosMensHist dm where dm.mensajeid = :mensajeId ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong(R_CONST_2, idMensaje);
			query.setFirstResult(start);
			query.setMaxResults(size);
			return query.list();
						 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	private List<TblDestinatariosMensHist> convertTOHistDestinatariosMens(List<TblDestinatariosMensajes> lista) {
		List<TblDestinatariosMensHist> res = new ArrayList<>();
		if (null != lista){
			for (TblDestinatariosMensajes destinatario : lista) {
				TblDestinatariosMensHist dm = new TblDestinatariosMensHist();
				dm.setCodigoexterno(destinatario.getCodigoexterno());
				dm.setCreadopor(destinatario.getCreadopor());
				dm.setDestinatario(destinatario.getDestinatario());
				dm.setDestinatariosmensajes(destinatario.getDestinatariosmensajes());
				dm.setEstado(destinatario.getEstado());
				dm.setFechacreacion(destinatario.getFechacreacion());
				dm.setFechamodificacion(destinatario.getFechamodificacion());
				dm.setMensajeid(destinatario.getMensajeid());
				dm.setModificadopor(destinatario.getModificadopor());
				dm.setNodo(destinatario.getNodo());
				dm.setUim(destinatario.getUim());
				dm.setUltimoenvio(destinatario.getUltimoenvio());
				res.add(dm);
			}	
		}
		return res;
	}




	


}
