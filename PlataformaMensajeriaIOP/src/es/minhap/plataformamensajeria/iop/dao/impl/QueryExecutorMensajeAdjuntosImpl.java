package es.minhap.plataformamensajeria.iop.dao.impl;

import java.sql.Blob;
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

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajeAdjuntos;
import es.minhap.plataformamensajeria.sm.modelo.Adjunto;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_MENSAJESADJUNTOS
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorMensajeAdjuntosImpl extends HibernateDaoSupport implements QueryExecutorMensajeAdjuntos {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorMensajeAdjuntosImpl.class);
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Adjunto> getAttachment(Long mensajeId) {
		List<Adjunto> adjuntos = new ArrayList<>();
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT NOMBRE, CONTENIDO FROM TBL_MENSAJESADJUNTOS MA INNER JOIN TBL_ADJUNTOS A "
							+ " ON MA.ADJUNTOID = A.ADJUNTOID WHERE MA.MENSAJEID = "
							+ mensajeId);
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			Adjunto adjunto= new Adjunto();
		    adjunto.setNombre((String) row[0]);
		    adjunto.setContenido((Blob) row[1]);
		    adjuntos.add(adjunto);
		}
		if(LOG.isDebugEnabled()){
			LOG.debug("Numero adjuntos  : " + adjuntos.size());
		}
		return adjuntos;
	}
	
	
	@Override
	public List<Adjunto> getImage(Long mensajeId) {
		List<Adjunto> adjuntos = new ArrayList<>();
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT NOMBRE, CONTENIDO FROM TBL_MENSAJESADJUNTOS MA INNER JOIN TBL_ADJUNTOS A "
						+ " ON MA.ADJUNTOID = A.ADJUNTOID WHERE MA.MENSAJEID = "
						+ mensajeId + "" + "AND IMAGEN = 1");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			Adjunto adjunto= new Adjunto();
		    adjunto.setNombre((String) row[0]);
		    adjunto.setContenido((Blob) row[1]);
		    adjuntos.add(adjunto);
		}
		if(LOG.isDebugEnabled()){
			LOG.debug("Numero imagenes adjuntas  : " + adjuntos.size());
		}
		return adjuntos;
	}
}
