package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.entity.DestinatariosMensajesBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.sm.modelo.DestinatarioDMensaje;
import es.minhap.plataformamensajeria.sm.modelo.Recipients;

/**
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorDestinatariosMensajesImpl extends HibernateDaoSupport implements QueryExecutorDestinatariosMensajes {

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorDestinatariosMensajesImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	private static final String TO= "TO";
	
	private static final String CC= "CC";
	
	private static final String BCC= "BCC";
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public Integer checkIdExternoExists(String idExterno, Boolean sended) {
//		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		BigDecimal mensajeId = new BigDecimal(0);
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT MENSAJEID FROM TBL_DESTINATARIOS_MENSAJES WHERE CODIGOEXTERNO= '"	+ idExterno+"'");
			mensajeId = (BigDecimal) query.uniqueResult();
//			if (sended && null != mensajeId && mensajeId.intValue() > 0) {
//				query	= getSessionFactory().getCurrentSession().createSQLQuery(
//						"SELECT MENSAJEID FROM TBL_DESTINATARIOS_MENSAJES WHERE CODIGOEXTERNO= '"	+ idExterno+"'"
//						+ "AND ESTADO not in ('"+ ps.getMessage("constantes.ESTADO_ENVIADO",null)+"', '" 
//						+  ps.getMessage("constantes.ESTADO_ENVIANDO",null)+ "', '"
//						+ ps.getMessage("constantes.ESTADO_ANULADO",null)+"') ");
//				mensajeId = (BigDecimal) query.uniqueResult();
//				if (null == mensajeId) {
//					mensajeId = new BigDecimal(0);
//				}
//			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR + " idExterno: " + idExterno, e);
			throw new ApplicationException(e);
		}
		return (mensajeId != null) ? mensajeId.intValue() : null;
	}

	@Override
	@Transactional
	public Integer countDistinctStatus(Long mensajeId) {
		BigDecimal countStatus = new BigDecimal(0);
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					" SELECT count(DISTINCT dm.estado) FROM TBL_DESTINATARIOS_MENSAJES dm WHERE dm.MENSAJEID="	+ mensajeId);
			countStatus = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return countStatus.intValue();
	}

	@Override
	public DestinatariosMensajesBean getDestMenByFilters(Long mensajeId, Long destinatariosMensajesId) {
		DestinatariosMensajesBean res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("  SELECT dm.uim, estado.nombre, m.ESTADOACTUAL, (select ESTADOID from TBL_ESTADOS ");
			queryBuilder.append("where NOMBRE = m.ESTADOACTUAL) ");
			queryBuilder.append("    FROM TBL_DESTINATARIOS_MENSAJES  dm, TBL_MENSAJES  m, tbl_estados  estado, tbl_lotesenvios  lote");
			queryBuilder.append("   WHERE m.mensajeId = " +mensajeId);
			if (null != destinatariosMensajesId ) {
				queryBuilder.append("    and dm.DESTINATARIOSMENSAJES = " +destinatariosMensajesId);
			}
			queryBuilder.append("        AND dm.mensajeid = m.mensajeid");
			queryBuilder.append("        AND lote.loteenvioid = m.loteenvioid");
			queryBuilder.append("        AND lote.estadoenvioid = estado.estadoid ");
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(queryBuilder.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
			    res = new DestinatariosMensajesBean();
			    res.setUim((String) row[0]);
			    res.setEstadoActualLote((String) row[1]);
			    res.setEstadoFinalMensaje((String) row[2]);
			    res.setEstadoFinalMensajeId(((BigDecimal) row[3]).longValue());
			    
			}
			if(null != res)
				return res;
			
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
	public List<EnvioGISSXMLBean> obtenerMensajesReenvioGISS(Long servicio, Integer reintentos) {
		List<EnvioGISSXMLBean> peticiones = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT dm.CODIGOEXTERNO,dm.DESTINATARIO,m.CODORGANISMOPAGADOR, to_char(sysdate,'DDMMYYYYHH24MISS') ||'_'|| m.CODORGANISMOPAGADOR AS objeto, TO_CHAR(m.CUERPO) FROM TBL_SERVICIOS  s, TBL_LOTESENVIOS  l, TBL_MENSAJES  m, TBL_DESTINATARIOS_MENSAJES  dm ");
			queryBuilder.append(" WHERE s.SERVICIOID=" + servicio);
			queryBuilder.append(" AND M.NUMEROENVIOS < " + reintentos);
			queryBuilder.append(" AND s.SERVICIOID = l.SERVICIOID ");
			queryBuilder.append(" AND l.LOTEENVIOID = m.LOTEENVIOID ");
			queryBuilder.append(" AND m.MENSAJEID = dm.MENSAJEID ");
			queryBuilder.append(" AND dm.estado IN ('INCIDENCIA') ");
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(queryBuilder.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				EnvioGISSXMLBean envioGISS = new EnvioGISSXMLBean();
				envioGISS.setAplicacion("");
				envioGISS.setCodOrganismoPagadorSMS((String) row[2]);
				envioGISS.setContenidoMsj((null != row[4]) ? (String) row[4] : "");
				envioGISS.setDestinatario((String) row[1]);
				envioGISS.setNumeroTelefonoDestino((String) row[1]);
				envioGISS.setUsuSistemaEnvio((String) row[3]);
				envioGISS.setPassSistemaEnvio((String) row[3]);
				envioGISS.setIdPeticion((String) row[0]);
				peticiones.add(envioGISS);
			    
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return peticiones;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Recipients getRecipientsMultidestinatarioModo1(Long mensajeId, Long destinatarioMensajeId) {
		Recipients res = new Recipients();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT dm.DESTINATARIOSMENSAJES,  d.EMAIL, d.TIPODESTINATARIO");
			queryBuilder.append(" from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_DESTINATARIOS d on  dm.DESTINATARIO = d.DESTINATARIOID ");
			queryBuilder.append(" where dm.DESTINATARIOSMENSAJES = " + destinatarioMensajeId + " and dm.MENSAJEID = "	+ mensajeId);
			queryBuilder.append(" AND (ESTADO NOT LIKE 'ENVIADO' OR ESTADO NOT LIKE 'CANCELADO')");
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(queryBuilder.toString());
			
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				DestinatarioDMensaje dm = new DestinatarioDMensaje();
				String email = (String) row[1];
				String tipoDestinatario = (String) row[2];
				Integer idDestinatariosMensajes = ((BigDecimal) row[0]).intValue();
				asingnacinDestinatarios(res, dm, email, tipoDestinatario, idDestinatariosMensajes);
				
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

	private void asingnacinDestinatarios(Recipients res, DestinatarioDMensaje dm, String email,
			String tipoDestinatario, Integer idDestinatariosMensajes) {
		String destinatario = null;
		if (tipoDestinatario != null) 
			destinatario = tipoDestinatario.toUpperCase();
		if (destinatario != null && destinatario.equals(TO)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			res.To.add(dm);
		} else if (destinatario != null && destinatario.equals(CC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			res.Cc.add(dm);
		} else if (destinatario != null && destinatario.equals(BCC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			res.Bcc.add(dm);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Recipients getRecipientsMultidestinatario(Long mensajeId) {
		Recipients recipients = new Recipients();

		try {

			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dm.DESTINATARIOSMENSAJES AS DESTINATARIOSMENSAJES,  d.EMAIL AS EMAIL, d.TIPODESTINATARIO AS TIPODESTINATARIO");
			queryBuilder.append(" from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_DESTINATARIOS d on  dm.DESTINATARIO = d.DESTINATARIOID ");
			queryBuilder.append("where dm.MENSAJEID = "	+ mensajeId);
			queryBuilder.append("AND (ESTADO NOT LIKE 'ENVIADO' OR ESTADO NOT LIKE 'CANCELADO')");
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(queryBuilder.toString());
			query.addScalar("DESTINATARIOSMENSAJES", new IntegerType());
			query.addScalar("EMAIL", new StringType());
			query.addScalar("TIPODESTINATARIO", new StringType());

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				DestinatarioDMensaje dm = new DestinatarioDMensaje();
				String email = (String) row[1];
				String tipoDestinatario = (String) row[2];
				Integer idDestinatariosMensajes =(row[0] != null) ? (Integer) row[0] : null;
				asignarDestinatario(recipients, dm, email, tipoDestinatario, idDestinatariosMensajes);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}

		} catch (Exception e) {
			LOG.error("Se ha producido un error",e );
		}
		return recipients;

	}

	private void asignarDestinatario(Recipients recipients, DestinatarioDMensaje dm, String email,
			String tipoDestinatario, Integer idDestinatariosMensajes) {
		String destinatario= null;
		if (tipoDestinatario != null)
			destinatario = tipoDestinatario.toUpperCase();
		if (destinatario != null && destinatario.equals(TO)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			recipients.To.add(dm);
		} else if (destinatario != null && destinatario.equals(CC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			recipients.Cc.add(dm);
		} else if (destinatario != null && destinatario.equals(BCC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = idDestinatariosMensajes;
			recipients.Bcc.add(dm);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Recipients getRecipients(Long mensajeId) {
		Recipients recipients = new Recipients();
		if (LOG.isDebugEnabled()) {
			LOG.debug(LOG_START);
		}
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT EMAIL,TIPODESTINATARIO FROM TBL_DESTINATARIOS");
		queryBuilder.append(" WHERE EMAIL IS NOT NULL AND TIPODESTINATARIO IN ('TO', 'CC', 'BCC') AND MENSAJEID = "	+ mensajeId);
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(queryBuilder.toString());

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			DestinatarioDMensaje dm = new DestinatarioDMensaje();
			String email = (String) row[0];
			String tipoDestinatario = (String) row[1];
			asignarDestinatario(recipients, dm, email, tipoDestinatario);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(LOG_END);
		}
		return recipients;
	}

	private void asignarDestinatario(Recipients recipients, DestinatarioDMensaje dm, String email,
			String tipoDestinatario) {
		String destinatario= null;
		if (tipoDestinatario != null)
			destinatario = tipoDestinatario.toUpperCase();
		if (destinatario != null && destinatario.equals(TO)) {
			dm.email = email;
			dm.idDestinatarioMensaje = null;
			recipients.To.add(dm);
		} else if (destinatario != null && destinatario.equals(CC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = null;
			recipients.Cc.add(dm);
		} else if (destinatario != null && destinatario.equals(BCC)) {
			dm.email = email;
			dm.idDestinatarioMensaje = null;
			recipients.Bcc.add(dm);
		}
	}
}
