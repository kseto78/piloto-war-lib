package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.DetallesMensajesBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewServidoresPushPrioridad;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.util.UtilCreateFile;
import es.minhap.plataformamensajeria.sm.modelo.DatosServicio;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionWebPushData;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorWebPush;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblUsuariosPush;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla
 * TBL_SERVICIOS
 * 
 * @author everis
 * 
 */
@Service("QueryExecutorMensajesImpl")
@Transactional
public class QueryExecutorMensajesImpl extends HibernateDaoSupport implements QueryExecutorMensajes {

	protected static final String R_CONST_1 = "CUERPOFILE";

	protected static final String R_CONST_2 = "lote";

	protected static final String R_CONST_3 = ") ";

	protected static final String R_CONST_4 = "SELECT dm.MENSAJEID,dm.DESTINATARIOSMENSAJES from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_MENSAJES m ";

	protected static final String R_CONST_5 = "'";

	protected static final String R_CONST_6 = ") and ";

	protected static final String R_CONST_7 = ")";

	protected static final String R_CONST_8 = "SELECT M.CABECERA as CABECERA, M.CUERPO as CUERPO, M.ICONO as ICONO, M.SONIDO as SONIDO, ";

	protected static final String R_CONST_9 = ",";

	protected static final String R_CONST_10 = "l.LOTEENVIOID = m.LOTEENVIOID where dm.DESTINATARIO in (";

	protected static final String R_CONST_11 = "TIPOCUERPO";

	protected static final String R_CONST_12 = " INNER JOIN TBL_APLICACIONES AP ON S.APLICACIONID = AP.APLICACIONID";

	protected static final String R_CONST_13 = " FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID";

	protected static final String R_CONST_14 = "BADGE";

	protected static final String R_CONST_15 = "FCMPROJECTKEY";

	protected static final String R_CONST_16 = " INNER JOIN TBL_LOTESENVIOS LE ON M.LOTEENVIOID = LE.LOTEENVIOID";

	protected static final String R_CONST_17 = "TIPOCODIFICACION";

	protected static final String R_CONST_18 = " INNER JOIN TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID";

	protected static final String R_CONST_19 = "CABECERA";

	protected static final String R_CONST_20 = " INNER JOIN TBL_SERVICIOS S ON S.SERVICIOID = LE.SERVICIOID";

	protected static final String R_CONST_21 = "APNSRUTACERTIFICADO";

	protected static final String R_CONST_22 = " WHERE MENSAJEID = ";

	protected static final String R_CONST_23 = "on dm.MENSAJEID = m.MENSAJEID inner join TBL_LOTESENVIOS l on ";

	protected static final String R_CONST_24 = " INNER JOIN TBL_SERVICIOS S ON LE.SERVICIOID = S.SERVICIOID";

	protected static final String R_CONST_25 = "APNSPASSWORDCERTIFICADO";

	protected static final String R_CONST_26 = "DESTINATARIOSMENSAJES";

	protected static final String R_CONST_27 = "l.MULTIDESTINATARIO = 1 and m.TIPOMENSAJE = 'NOTIFICACION PUSH' and dm.ESTADO = '";

	protected static final String R_CONST_28 = "unchecked";

	protected static final String R_CONST_29 = " WHERE M.MENSAJEID=";

	protected static final String R_CONST_30 = "S.APNSRUTACERTIFICADO as APNSRUTACERTIFICADO, S.APNSPASSWORDCERTIFICADO as APNSPASSWORDCERTIFICADO, ";

	protected static final String R_CONST_31 = " AND m.MENSAJEID = ";

	protected static final String R_CONST_32 = "PASSWORD";

	protected static final String R_CONST_33 = "SONIDO";

	protected static final String R_CONST_34 = "DESTINATARIO";

	protected static final String R_CONST_35 = " WHERE dm.DESTINATARIOSMENSAJES = ";

	protected static final String R_CONST_36 = " SELECT NMAXENVIOS,CUENTAENVIO,NOMBRECUENTAENVIO, HEADERSMS, MULTIORGANISMO FROM TBL_SERVICIOS WHERE SERVICIOID = (SELECT SERVICIOID";

	protected static final String R_CONST_37 = "NOMBREUSUARIO";

	protected static final String R_CONST_38 = "ICONO";

	protected static final String R_CONST_39 = " WHERE M.MENSAJEID = ";

	protected static final String R_CONST_40 = "LOTEENVIOID";

	protected static final String R_CONST_41 = "HEADERSMS";

	protected static final String R_CONST_42 = "USUARIO";

	protected static final String R_CONST_43 = "CUERPO";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorMensajesImpl.class);

	private static final String LOG_END = "search - end";

	private static final String LOG_START = "search - start";

	private static final String HAS_ERROR = "Se ha producido un error ";

	@Resource
	private TblUsuariosPushManager tblUsuariosPushManager;

	@Resource
	private TblMensajesManager tblMensajesManager;

	@Resource
	private QueryExecutorViewServidoresPushPrioridad queryExecutorViewServidoresPushPrioridad;

	@Resource
	private QueryExecutorUsuariosPush queryExecutorUsuariosPush;
	
	@Resource(name="UtilCreateFile")
	private UtilCreateFile utilFile;

	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public List<Long> getMensajesParaAnular(Long servicio, Integer reintentos) {
		List<Long> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder
					.append(" SELECT m.MENSAJEID FROM TBL_SERVICIOS  s, TBL_LOTESENVIOS  l, TBL_MENSAJES  m, TBL_DESTINATARIOS_MENSAJES  dm ");
			queryBuilder.append(" WHERE s.SERVICIOID=" + servicio);
			queryBuilder.append(" AND M.NUMEROENVIOS >= " + reintentos);
			queryBuilder.append(" AND s.SERVICIOID = l.SERVICIOID ");
			queryBuilder.append(" AND l.LOTEENVIOID = m.LOTEENVIOID ");
			queryBuilder.append(" AND m.MENSAJEID = dm.MENSAJEID ");
			queryBuilder.append(" AND dm.estado IN ('INCIDENCIA') ");
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(queryBuilder.toString());

			List<BigDecimal> lista = query.list();
			
			if (null != lista && !lista.isEmpty()){
				for (BigDecimal bd : lista) {
					res.add(bd.longValue());
				}
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
	public DetallesMensajesBean getDetallesMensaje(Long idMensaje) {
		DetallesMensajesBean res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			SQLQuery query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							" SELECT  TO_CHAR(m.CABECERA),TO_CHAR(m.CUERPO),TO_CHAR(m.TIPOCUERPO),m.TIPOCODIFICACION,dm.DESTINATARIO, dm.DESTINATARIOSMENSAJES "
									+ " FROM TBL_MENSAJES m,TBL_DESTINATARIOS_MENSAJES dm "
									+ " WHERE dm.MENSAJEID = m.MENSAJEID " + R_CONST_31 + "?");
			query.setLong(0, idMensaje);

			@SuppressWarnings(R_CONST_28)
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				res = new DetallesMensajesBean();
				res.setCabecera((String) row[0]);
				res.setCuerpo(((String) row[1] != null) ? (String) row[1] : "");
				res.setTipoCuerpo((String) row[2]);
				res.setTipoCodificacion((String) row[3]);
				res.setDestinatario((String) row[4]);
				res.setDestinatariosMensajes(((BigDecimal) row[5]).longValue());
			}
			if (null != res) {
				return res;
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
	public DatosServicio getDatosServicios(Long idMensaje) {
		DatosServicio res = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							R_CONST_36
									+ R_CONST_13
									+ R_CONST_29 + "?" + R_CONST_7);
			query.setLong(0, idMensaje);

			@SuppressWarnings(R_CONST_28)
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				res = new DatosServicio();
				res.setNMaxEnvios(((BigDecimal) row[0]).intValue());
				res.setFromMail((String) row[1]);
				res.setFromMailNombre((String) row[2]);
				res.setHeaderSMS((String) row[3]);
				res.setMultiOrganismo(((BigDecimal) row[4]).intValue());
			}
			if (null != res) {
				return res;
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

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public List<Long> getMensajesPorLoteYEstado(Long idLote, String estado) {
		List<Long> res = new ArrayList<>();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(
					" SELECT MENSAJEID FROM TBL_MENSAJES WHERE LOTEENVIOID = ? and ESTADOACTUAL = ?");
			query.setLong(0, idLote);
			query.setString(1, estado);
			List<Object> rows = query.list();
			for (Object row : rows) {
				res.add(((BigDecimal) row).longValue());
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

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public Map<Long, List<Long>> getMensajesPorUsuariosPushYEstadoMultidest(String usersId, String estado) {
		Map<Long, List<Long>> res = new HashMap<>();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append(R_CONST_4);
			queryString.append(R_CONST_23);
			queryString.append(R_CONST_10);
			queryString.append(usersId);
			queryString.append(R_CONST_6);
			queryString.append(R_CONST_27);
			queryString.append(estado);
			queryString.append(R_CONST_5);
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				Long m = ((BigDecimal) row[0]).longValue();
				Long dm = ((BigDecimal) row[1]).longValue();
				if (res.containsKey(m)) {
					res.get(m).add(dm);
				} else {
					List<Long> list = new ArrayList<>();
					list.add(dm);
					res.put(m, list);
				}
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}
	
	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public Map<Long, List<Long>> getMensajesPorUsuariosPushYEstadoYMensajeMultidest(String usersId, String estado, Long mensajeId) {
		Map<Long, List<Long>> res = new HashMap<>();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append(R_CONST_4);
			queryString.append(R_CONST_23);
			queryString.append(R_CONST_10);
			queryString.append(usersId);
			queryString.append(R_CONST_6);
			queryString.append(R_CONST_27);
			queryString.append(estado);
			queryString.append("' and m.MENSAJEID = '");
			queryString.append(mensajeId);
			queryString.append(R_CONST_5);
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				Long m = ((BigDecimal) row[0]).longValue();
				Long dm = ((BigDecimal) row[1]).longValue();
				if (res.containsKey(m)) {
					res.get(m).add(dm);
				} else {
					List<Long> list = new ArrayList<>();
					list.add(dm);
					res.put(m, list);
				}
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public List<Long> getMensajesPorUsuariosPushYEstado(String usersId, String estado) {
		List<Long> res = new ArrayList<>();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT m.MENSAJEID from TBL_MENSAJES m inner join TBL_LOTESENVIOS l on ");
			queryString.append("l.LOTEENVIOID = m.LOTEENVIOID where m.USUARIOID in  (");
			queryString.append(usersId);
			queryString.append(R_CONST_6);
			queryString.append("l.MULTIDESTINATARIO = 0 and m.TIPOMENSAJE = 'NOTIFICACION PUSH' and m.ESTADOACTUAL = '");
			queryString.append(estado);
			queryString.append(R_CONST_5);
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			
			res = query.list();

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public List<Aviso> getAvisosMensajeUsuario(String usuarios, String numPagina, String tamPagina,
			PropertiesServices ps) {
		List<Aviso> res = new ArrayList<>();
		String estadosSeleccionados = ps.getMessage("constantes.LISTA_ESTADOS_SELECCIONADOS", null);
		String codeEnviado = ps.getMessage("plataformaErrores.gestionNotificacionesPush.CODE_ENVIADO", null);
		String codeRecibido = ps.getMessage("plataformaErrores.gestionNotificacionesPush.CODE_RECIBIDO", null);
		String codeLeido = ps.getMessage("plataformaErrores.gestionNotificacionesPush.CODE_LEIDO", null);
		String estadoEnviado = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		String estadoRecibido = ps.getMessage("constantes.ESTADO_RECIBIDO", null);
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			query = mountSQLQuery(usuarios, numPagina, tamPagina, estadosSeleccionados);
			List<Object[]> rows = query.list();
			mountAvisos(res, codeEnviado, codeRecibido, codeLeido, estadoEnviado, estadoRecibido, rows);

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;
	}

	private void mountAvisos(List<Aviso> res, String codeEnviado, String codeRecibido, String codeLeido,
			String estadoEnviado, String estadoRecibido, List<Object[]> rows) {
		for (Object[] row : rows) {
			Aviso a = new Aviso();
			a.setIdAviso((null == row[0]) ? "" : row[0].toString());
			a.setTitulo((null == row[1]) ? "" : row[1].toString());
			a.setCuerpo((null == row[2]) ? "" : row[2].toString());
			String estado = row[3].toString();
			if (estado.equals(estadoEnviado)) {
				a.setEstado(codeEnviado);
			} else if (estado.equals(estadoRecibido)) {
				a.setEstado(codeRecibido);
			} else {
				a.setEstado(codeLeido);
			}
			a.setFechaEstado((null == row[4]) ? "" : row[4].toString());

			res.add(a);
		}
	}

	private SQLQuery mountSQLQuery(String usuarios, String numPagina, String tamPagina, String estadosSeleccionados) {
		SQLQuery query;
		String sql = getQuery(usuarios, numPagina, tamPagina, estadosSeleccionados);

		query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("MENSAJEID", new LongType());
		query.addScalar(R_CONST_19, new StringType());
		query.addScalar(R_CONST_43, new StringType());
		query.addScalar("ESTADO", new StringType());
		query.addScalar("ULTIMOENVIO", new StringType());
		return query;
	}

	private String getQuery(String usuarios, String numPagina, String tamPagina, String listaEstadosSeleccionados) {
		String sql;
		Integer pag = Integer.parseInt(tamPagina);
		Integer num = Integer.parseInt(numPagina);

		if (num <= 0) {
			sql = "SELECT MENSAJEID,CABECERA,CUERPO,ESTADO,ULTIMOENVIO FROM ((SELECT m.MENSAJEID as MENSAJEID,m.CABECERA as CABECERA,"
					+ "m.CUERPO as CUERPO,m.ESTADOACTUAL as ESTADO, "
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = m.MENSAJEID AND T2.ESTADOID IN (1,7)) AS ULTIMOENVIO "
					+ "FROM TBL_MENSAJES m INNER JOIN TBL_LOTESENVIOS l ON l.LOTEENVIOID = m.LOTEENVIOID"
					+ " WHERE m.USUARIOID IN ("
					+ usuarios
					+ ") AND m.ESTADOACTUAL IN ("
					+ listaEstadosSeleccionados
					+ R_CONST_3
					+ "AND l.MULTIDESTINATARIO = 0) UNION ALL(SELECT m.MENSAJEID as MENSAJEID, "
					+ "m.CABECERA as CABECERA,m.CUERPO as CUERPO, dm.ESTADO as ESTADO,"
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = dm.MENSAJEID AND T2.ESTADOID IN (1,7)) AS ULTIMOENVIO "
					+ "FROM TBL_MENSAJES m INNER JOIN TBL_LOTESENVIOS l ON l.LOTEENVIOID = m.LOTEENVIOID "
					+ "INNER JOIN TBL_DESTINATARIOS_MENSAJES dm ON dm.MENSAJEID = m.MENSAJEID "
					+ "WHERE dm.DESTINATARIO IN ("
					+ usuarios
					+ ") AND dm.ESTADO IN ("
					+ listaEstadosSeleccionados
					+ R_CONST_3 + "AND l.MULTIDESTINATARIO = 1)) order by MENSAJEID desc";
		} else {

			int cantidad = pag * num;
			int pagina = cantidad - pag;

			sql = "select * from "
					+ "(select a.*, ROWNUM rnum from "
					+ "(select * from ((SELECT m.MENSAJEID as MENSAJEID,m.CABECERA as CABECERA, "
					+ "m.CUERPO as CUERPO,dm.ESTADO as ESTADO, "
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = dm.MENSAJEID AND T2.ESTADOID IN (1,7)) as ULTIMOENVIO "
					+ "from TBL_MENSAJES m inner join TBL_LOTESENVIOS l "
					+ "on l.LOTEENVIOID = m.LOTEENVIOID inner join "
					+ "TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID "
					+ "WHERE dm.DESTINATARIO in ("
					+ usuarios
					+ ") and dm.ESTADO in ("
					+ listaEstadosSeleccionados
					+ R_CONST_3
					+ "AND l.MULTIDESTINATARIO = 1) )order by MENSAJEID desc) a where ROWNUM <= "
					+ cantidad
					+ " ) where rnum  >= " + pagina;

		}

		return sql;
	}

	@SuppressWarnings(R_CONST_28)
	@Override
	@Transactional
	public DatosServicio getDataFromServices(Long mensajeId) {
		DatosServicio datos = new DatosServicio();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							R_CONST_36
									+ R_CONST_13
									+ R_CONST_29 + "?" + R_CONST_7);
			query.setLong(0, mensajeId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				datos.setNMaxEnvios((null != row[0]) ? ((BigDecimal) row[0]).intValue() : null);
				datos.setFromMail((null != row[1]) ? (String) row[1] : null);
				datos.setFromMailNombre((null != row[2]) ? (String) row[2] : null);
				datos.setHeaderSMS((null != row[3]) ? (String) row[3] : null);
				datos.setMultiOrganismo((null != row[4]) ? ((BigDecimal) row[4]).intValue() : 0);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return datos;
	}

	@Override
	@Transactional
	public MailData getDetailsMultidestinatario(Long mensajeId) {

		MailData data = new MailData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							" SELECT m.CABECERA as CABECERA ,m.CUERPO AS CUERPO ,m.TIPOCUERPO AS TIPOCUERPO, m.TIPOCODIFICACION AS TIPOCODIFICACION, m.CUERPOFILE AS CUERPOFILE  FROM TBL_MENSAJES m "
									+ " WHERE m.MENSAJEID = ?");
			query.setLong(0, mensajeId);
			query.addScalar(R_CONST_19, new StringType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_11, new StringType());
			query.addScalar(R_CONST_17, new StringType());
			query.addScalar(R_CONST_1, new StringType());
			Object[] row = (Object[]) query.uniqueResult();

			data.Subject = (String) row[0];
			
			if ((String) row[2] != null) {
				data.TipoCuerpo = (String) row[2];
			}
			if ((String) row[3] != null) {
				data.TipoCodificacion = (String) row[3];
			}
			//comprueba si el cuerpo está en fichero
			if (null == row[4]){
				data.Body = (null != row[1]) ? (String) row[1] : "";
			}else{
				//recuperamos el cuerpo. 
				data.Body = utilFile.getCuerpoMensajeFromFile((String)row[4]);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	public MailData getDetails(Long mensajeId) {
		MailData data = new MailData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT CABECERA,CUERPO,TIPOCUERPO,TIPOCODIFICACION, CUERPOFILE FROM TBL_MENSAJES");
			queryString.append(R_CONST_22);
			queryString.append(mensajeId);
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			query.addScalar(R_CONST_19, new StringType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_11, new StringType());
			query.addScalar(R_CONST_17, new StringType());
			query.addScalar(R_CONST_1, new StringType());

			Object[] row = (Object[]) query.uniqueResult();

			data.Subject = (String) row[0];
			
			if ((String) row[2] != null) {
				data.TipoCuerpo = (String) row[2];
			}
			if ((String) row[3] != null) {
				data.TipoCodificacion = (String) row[3];
			}
			
			//comprueba si el cuerpo está en fichero
			if (null == row[4]){
				data.Body = (null != row[1]) ? (String) row[1] : "";
			}else{
				//recuperamos el cuerpo. 
				data.Body = utilFile.getCuerpoMensajeFromFile((String)row[4]);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	public NotificacionPushData getDetailsServidorPushMultidestinatario(Long mensajeId, Long destinatarioMensajeId,
			DatosServicio serviceData) {
		NotificacionPushData data = new NotificacionPushData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							R_CONST_8
									+ "up.NOMBREUSUARIO as NOMBREUSUARIO, dm.DESTINATARIO as DESTINATARIO, up.PLATAFORMAID as PLATAFORMA, "
									+ "dm.DESTINATARIOSMENSAJES as DESTINATARIOSMENSAJES, S.FCMPROJECTKEY as FCMPROJECTKEY, "
									+ R_CONST_30
									+ "S.BADGE as BADGE, m.CUERPOFILE as CUERPOFILE, M.NOTIFICACIONSILENCIOSA as NOTIFICACIONSILENCIOSA FROM TBL_MENSAJES M "
									+ R_CONST_18
									+ R_CONST_20
									+ " INNER JOIN TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = M.MENSAJEID"
									+ " INNER JOIN TBL_USUARIOS_PUSH up on up.USUARIOID = dm.DESTINATARIO"
									+ R_CONST_39
									+ "?"
									+ " AND dm.DESTINATARIOSMENSAJES = "
									+ "?"
									+ " AND (up.ELIMINADO != 'S' OR up.ELIMINADO IS NULL)"
									+ " AND (ESTADO NOT LIKE 'ENVIADO' AND ESTADO NOT LIKE 'ANULADO' AND ESTADO NOT LIKE 'LEIDO' AND ESTADO NOT LIKE 'RECIBIDO')");
			query.setLong(0, mensajeId);
			query.setLong(1, destinatarioMensajeId);
			query.addScalar(R_CONST_19, new StringType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_38, new StringType());
			query.addScalar(R_CONST_33, new StringType());
			query.addScalar(R_CONST_37, new StringType());
			query.addScalar(R_CONST_34, new LongType());
			query.addScalar("PLATAFORMA", new IntegerType());
			query.addScalar(R_CONST_26, new LongType());
			query.addScalar(R_CONST_15, new StringType());
			query.addScalar(R_CONST_21, new StringType());
			query.addScalar(R_CONST_25, new StringType());
			query.addScalar(R_CONST_14, new StringType());
			query.addScalar(R_CONST_1, new StringType());
			query.addScalar("NOTIFICACIONSILENCIOSA", new BooleanType());

			Object[] row = (Object[]) query.uniqueResult();

			if (null != row) {
				data.cabecera = (String) row[0];
				data.icono = (String) row[2];
				data.sonido = (String) row[3];
				data.nombreUsuario = (String) row[4];
				data.UsuarioId = (Long) row[5];
				data.plataformaId = (Integer) row[6];
				data.destinatarioMensajeId = (Long) row[7];
				data.fCMApiKey = (String) row[8];
				data.rutaCertificadoAPNS = (String) row[9];
				data.passwordCertificadoAPNS = (String) row[10];
				data.badge = (String) row[11];
				
				//comprueba si el cuerpo está en fichero
				if (null == row[12]){
					data.cuerpo = (null != row[1]) ? (String) row[1] : "";
				}else{
					//recuperamos el cuerpo. 
					data.cuerpo = utilFile.getCuerpoMensajeFromFile((String)row[12]);
				}
				
				//Notificacion Silenciosa
				data.setNotificacionSilenciosa(row[13] != null && (Boolean)row[13]);
								
				TblUsuariosPush tblUsuariosPush = getTblUsuariosPushManager().getUsuarioPush(data.UsuarioId);
				List<String> tokenUsuario = new ArrayList<>();
				tokenUsuario.add(tblUsuariosPush.getTokenusuario());
				data.tokensUsuario = (ArrayList<String>) tokenUsuario;
				data.esMultidestinatario = true;
				data.servers = (ArrayList<ParametrosServidorPush>) getQueryExecutorViewServidoresPushPrioridad()
						.getServidoresPush(mensajeId, data.plataformaId);
				data.serviceData = serviceData;
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	public NotificacionWebPushData getDetailsServidorWebPushMultidestinatario(Long mensajeId, Long destinatarioMensajeId) {
		NotificacionWebPushData data = new NotificacionWebPushData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("Select m.cabecera as cabecera, m.cuerpo as cuerpo,m.cuerpofile as cuerpofile, uwp.usuariowebpushid as usuariowebpushid, "
					+ "dm.destinatariosmensajes as destinatariosmensajes, s.caducidadwebpush as caducidadwebpush, "
					+ "s.vapidpublickey as vapidpublickey, s.vapidprivatekey as vapidprivatekey, "
					+ "uwp.endpoint as endpoint, uwp.auth as auth, uwp.p256dh as pdh ");
			sb.append("From tbl_mensajes m inner join tbl_lotesenvios le on le.loteenvioid = m.loteenvioid inner join "
					+ "tbl_servicios s on s.servicioid = le.servicioid inner join tbl_destinatarios_mensajes dm on dm.mensajeid = m.mensajeid "
					+ "inner join tbl_usuarios_webpush uwp on uwp.usuariowebpushid = dm.destinatario where m.mensajeid = ");
			sb.append(mensajeId);
			sb.append(" and dm.destinatariosmensajes = ");
			sb.append(destinatarioMensajeId);
			sb.append(" and (uwp.eliminado != 'S' or uwp.eliminado is null and (estado not like 'ENVIADO' and estado not like 'ANULADO'))" );
			
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(sb.toString());

			query.addScalar("cabecera", new StringType());
			query.addScalar("cuerpo", new StringType());
			query.addScalar("cuerpofile", new StringType());
			query.addScalar("usuariowebpushid", new LongType());
			query.addScalar("destinatariosmensajes", new LongType());
			query.addScalar("caducidadwebpush", new IntegerType());
			query.addScalar("vapidpublickey", new StringType());
			query.addScalar("vapidprivatekey", new StringType());
			query.addScalar("endpoint", new StringType());
			query.addScalar("auth", new StringType());
			query.addScalar("pdh", new StringType());

			Object[] row = (Object[]) query.uniqueResult();

			if (null != row) {
				data.cabecera = (String) row[0];
				data.setUsuarioId((Long) row[3]) ;
				data.setDestinatarioMensajeId((Long) row[4]);
				data.setCaducidadWebPush((Integer) row[5]);
				data.setVapidPublicKey((String) row[6]);
				data.setVapidPrivateKey((String) row[7]);
				data.setEndpoint((String) row[8]);
				data.setAuth((String) row[9]);
				data.setPdh((String) row[10]);
				
				//comprueba si el cuerpo está en fichero
				if (null == row[2]){
					data.cuerpo = (null != row[1]) ? (String) row[1] : "";
				}else{
					//recuperamos el cuerpo. 
					data.cuerpo = utilFile.getCuerpoMensajeFromFile((String)row[2]);
				}
								

				data.setEsMultidestinatario(true);
				data.servers = (ArrayList<ParametrosServidorWebPush>) getQueryExecutorViewServidoresPushPrioridad()
						.getServidoresWebPush(mensajeId);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}
	
	
	@Override
	public NotificacionPushData getDetailsServidorPush(Long mensajeId, DatosServicio serviceData) {
		NotificacionPushData data = new NotificacionPushData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			Long idUsuario = tblMensajesManager.getMensaje(mensajeId).getUsuarioid();
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							R_CONST_8
									+ "M.NOMBREUSUARIO as NOMBREUSUARIO, S.FCMPROJECTKEY as FCMPROJECTKEY, "
									+ R_CONST_30
									+ "S.BADGE as BADGE, m.CUERPOFILE as CUERPOFILE FROM TBL_MENSAJES M "
									+ R_CONST_18
									+ " INNER JOIN TBL_SERVIDORES_SERVICIOS SS ON SS.SERVICIOID = LE.SERVICIOID"
									+ R_CONST_20
									+ R_CONST_22 + "?" + " AND USUARIOID = ?" );
			query.setLong(0, mensajeId);
			query.setLong(1, idUsuario);

			query.addScalar(R_CONST_19, new StringType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_38, new StringType());
			query.addScalar(R_CONST_33, new StringType());
			query.addScalar(R_CONST_37, new StringType());
			query.addScalar(R_CONST_15, new StringType());
			query.addScalar(R_CONST_21, new StringType());
			query.addScalar(R_CONST_25, new StringType());
			query.addScalar(R_CONST_14, new StringType());
			query.addScalar(R_CONST_1, new StringType());

			Object[] row = (Object[]) query.uniqueResult();

			if (null != row) {
				data.cabecera = (String) row[0];
				data.icono = (String) row[2];
				data.sonido = (String) row[3];
				data.nombreUsuario = (String) row[4];
				data.fCMApiKey = (String) row[5];
				data.rutaCertificadoAPNS = (String) row[6];
				data.passwordCertificadoAPNS = (String) row[7];
				data.badge = (String) row[8];

				//comprueba si el cuerpo está en fichero
				if (null == row[9]){
					data.cuerpo = (null != row[1]) ? (String) row[1] : "";
				}else{
					//recuperamos el cuerpo. 
					data.cuerpo = utilFile.getCuerpoMensajeFromFile((String)row[9]);
				}

				data.esMultidestinatario = false;
				Integer plataforma = getQueryExecutorUsuariosPush().getPlataformaUsuario(mensajeId);
				data.servers = (ArrayList<ParametrosServidorPush>) getQueryExecutorViewServidoresPushPrioridad()
						.getServidoresPush(mensajeId, plataforma);
				data.serviceData = serviceData;

				TblUsuariosPush tblUsuariosPush = getTblUsuariosPushManager().getUsuarioPush(idUsuario);
				List<String> tokenUsuario = new ArrayList<>();
				tokenUsuario.add(tblUsuariosPush.getTokenusuario());
				data.tokensUsuario = (ArrayList<String>) tokenUsuario;
				data.UsuarioId = idUsuario;
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	public ReceptorSMSData getDetailsReceptorSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId,
			ReceptorSMSData receptorSMSData) {
		ReceptorSMSData data = new ReceptorSMSData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			query = getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT M.LOTEENVIOID as LOTEENVIOID, M.CUERPO as CUERPO, dm.DESTINATARIO as DESTINATARIO, "
							+ "dm.DESTINATARIOSMENSAJES as DESTINATARIOSMENSAJES, S.HEADERSMS as HEADERSMS, "
							+ "AP.USUARIO as USUARIO, AP.PASSWORD as PASSWORD, m.CUERPOFILE as CUERPOFILE FROM TBL_MENSAJES M"
							+ R_CONST_16
							+ R_CONST_24
							+ R_CONST_12
							+ " INNER JOIN TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID"
							+ R_CONST_35 + "?" + " AND M.MENSAJEID = ?");
			
			query.setLong(0, destinatarioMensajeId);
			query.setLong(1, mensajeId);
			
			query.addScalar(R_CONST_40, new LongType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_34, new StringType());
			query.addScalar(R_CONST_26, new LongType());
			query.addScalar(R_CONST_41, new StringType());
			query.addScalar(R_CONST_42, new StringType());
			query.addScalar(R_CONST_32, new StringType());
			query.addScalar(R_CONST_1, new StringType());

			Object[] row = (Object[]) query.uniqueResult();

			data.LoteEnvioId = (null != row[0]) ? ((Long) row[0]).toString() : "";
			data.Telefono = (null != row[2]) ? (String) row[2] : "";
			data.destinatarioMensajeId = (null != row[3]) ? ((Long) row[3]).longValue() : null;
			data.HeaderSMS = (null != row[4]) ? (String) row[4] : "";
			data.User = (null != row[5]) ? (String) row[5] : "";
			data.Password = (null != row[6]) ? (String) row[6] : "";
			
			//comprueba si el cuerpo está en fichero
			if (null == row[7]){
				data.Body = (null != row[1]) ? (String) row[1] : "";
			}else{
				//recuperamos el cuerpo. 
				data.Body = utilFile.getCuerpoMensajeFromFile((String)row[7]);
			}
			
			data.esMultidestinatario = true;
			data.Servers = receptorSMSData.Servers;
			data.ServiceData = receptorSMSData.ServiceData;

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	public ReceptorSMSData getDetailsReceptorSMS(Long mensajeId, ReceptorSMSData receptorSMSData) {
		SQLQuery query = null;
		ReceptorSMSData data = new ReceptorSMSData();
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT M.LOTEENVIOID as LOTEENVIOID, M.CUERPO as CUERPO, M.TELEFONO as TELEFONO, "
									+ "S.HEADERSMS as HEADERSMS, AP.USUARIO as USUARIO, AP.PASSWORD as PASSWORD, m.CUERPOFILE as CUERPOFILE FROM TBL_MENSAJES M"
									+ R_CONST_16
									+ R_CONST_24
									+ R_CONST_12
									+ R_CONST_39 + "?");
			query.setLong(0, mensajeId);
			
			query.addScalar(R_CONST_40, new LongType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar("TELEFONO", new StringType());
			query.addScalar(R_CONST_41, new StringType());
			query.addScalar(R_CONST_42, new StringType());
			query.addScalar(R_CONST_32, new StringType());

			Object[] row = (Object[]) query.uniqueResult();

			data.User = (null != row[4]) ? (String) row[4] : "";
			data.Password = (null != row[5]) ? (String) row[5] : "";
			data.Telefono = (null != row[2]) ? (String) row[2] : "";
			data.LoteEnvioId = (null != row[0]) ? ((Long) row[0]).toString() : "";
			data.HeaderSMS = (null != row[3]) ? (String) row[3] : "";

			//comprueba si el cuerpo está en fichero
			if (null == row[6]){
				data.Body = (null != row[1]) ? (String) row[1] : "";
			}else{
				//recuperamos el cuerpo. 
				data.Body = utilFile.getCuerpoMensajeFromFile((String)row[6]);
			}
			
			data.Servers = receptorSMSData.Servers;
			data.ServiceData = receptorSMSData.ServiceData;

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}

	@Override
	@Transactional
	public SMSData getDetailsSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId, SMSData smsDataComun) {
		SMSData data = new SMSData();
		SQLQuery query = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			query = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							" SELECT m.CABECERA as CABECERA,m.CUERPO AS CUERPO ,m.TIPOCUERPO AS TIPOCUERPO ,m.TIPOCODIFICACION AS TIPOCODIFICACION,"
									+ "dm.DESTINATARIO AS DESTINATARIO, dm.DESTINATARIOSMENSAJES AS DESTINATARIOSMENSAJES, m.CUERPOFILE AS CUERPOFILE,"
									+ "m.FECHACREACION AS FECHACREACION FROM TBL_MENSAJES m "
									+ " inner join TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID "
									+ R_CONST_35
									+ "?"
									+ R_CONST_31 + "?");
			query.setLong(0, destinatarioMensajeId);
			query.setLong(1, mensajeId);
			
			query.addScalar(R_CONST_19, new StringType());
			query.addScalar(R_CONST_43, new StringType());
			query.addScalar(R_CONST_11, new StringType());
			query.addScalar(R_CONST_17, new StringType());
			query.addScalar(R_CONST_34, new StringType());
			query.addScalar(R_CONST_26, new LongType());
			query.addScalar(R_CONST_1, new StringType());

			Object[] row = (Object[]) query.uniqueResult();
			data.Telefono = (null != row[4]) ? (String) row[4] : "";
			data.destinatarioMensajeId = (null != row[5]) ? (Long) row[5] : 0;
			data.Servers = smsDataComun.Servers;
			data.ServiceData = smsDataComun.ServiceData;
			data.esMultidestinatario = true;
			
			//comprueba si el cuerpo está en fichero
			if (null == row[6]){
				data.Body = (null != row[1]) ? (String) row[1] : "";
			}else{
				//recuperamos el cuerpo. 
				data.Body = utilFile.getCuerpoMensajeFromFile((String)row[6]);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return data;
	}


@SuppressWarnings(R_CONST_28)
@Override
@Transactional
//Consulta para sacar los mensajes pendientes, sin los servicios excluidos que estaran por properties y seran los de AEAT y GISS
public List<Long> getMensajesPendientes(Date fechaInicio, Date fechaFin, String serviciosExcluidos, String serviciosIncluidos) {
	List<Long> res = new ArrayList<>();
	try {
		if (LOG.isDebugEnabled()) {
			LOG.debug(LOG_START);
		}
		
		List<String> serviciosAeatGiss = new ArrayList<>();
		if(!"".equals(serviciosExcluidos)) {
			serviciosAeatGiss = new ArrayList<>(Arrays.asList(serviciosExcluidos.split(R_CONST_9)));
		} 
		
		StringBuilder sqlExcluidos = new StringBuilder();
		
		
		for(String idServ : serviciosAeatGiss){
			sqlExcluidos.append(" AND NOT ge.servicioid = "+idServ);
		}
		
		StringBuilder sqlIncluidos = new StringBuilder();
		if(serviciosIncluidos != null && !"".equals(serviciosIncluidos)){
			List<String> servicios = new ArrayList<>(Arrays.asList(serviciosIncluidos.split(R_CONST_9)));
			sqlIncluidos.append(" AND (ge.servicioid = "+servicios.get(0));
			servicios.remove(0);			
			for(String idServ : servicios){
				sqlIncluidos.append(" OR ge.servicioid = "+idServ);
			}
			sqlIncluidos.append(R_CONST_7);
		}
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT DISTINCT ge.MENSAJEID FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL AND (" +
		        "estado ='PENDIENTE DE ENVIO' or estado= 'PENDIENTE') and ge.ULTIMOENVIO IS NOT NULL "+ sqlExcluidos + sqlIncluidos +
		        " AND ge.ultimoEnvio >=:fechaDesde AND ge.ultimoEnvio <=:fechaHasta");
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
		
		
		query.setTimestamp("fechaDesde", fechaInicio);		
		query.setTimestamp("fechaHasta", fechaFin);
		
		List<Object> rows = query.list();
		for (Object row : rows) {
			res.add(((BigDecimal) row).longValue());
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
	public Integer countMensajesHistorificacion(Long loteId, Date fecha) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}

			String sql = "select count(m.mensajeid) from TBL_GESTIONENVIOS ge inner join TBL_MENSAJES m on ge.mensajeid = m.mensajeid where m.loteenvioid = :lote and "
					+ " ge.ultimoenvio <= :fecha";
//			String sql = "select count(m.mensajeid) from TBL_GESTIONENVIOS ge inner join TBL_MENSAJES m on ge.mensajeid = m.mensajeid where m.loteenvioid = :lote and "
//					+ "(m.estadoactual = 'ENVIADO' or m.estadoactual = 'ANULADO') and ge.ultimoenvio <= :fecha";

			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong(R_CONST_2, loteId);
			query.setDate("fecha", fecha);

			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		
	}
	
	@SuppressWarnings(R_CONST_28)
	@Override
	public List<Long> getIdMensajesByLote(Long loteEnvioID) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m.mensajeid from TblMensajes m where m.tblLotesEnvios.loteenvioid = :lote ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong(R_CONST_2, loteEnvioID);
			
			 return query.list();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@Override
	@Transactional
	public Integer countMensajesByLote(Long loteId) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m.mensajeid) from TBL_MENSAJES m  where m.loteenvioid = :lote ";
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong(R_CONST_2, loteId);
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		
	}

	@Override
	@Transactional
	public TblAplicaciones getAplicacionFromMensaje(Long mensajeId) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ap from TblMensajes m inner join m.tblLotesEnvios le inner join le.tblServicios s inner join s.tblAplicaciones ap "
					+ "where m.mensajeid = :mensajeId";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setLong("mensajeId", mensajeId);
			
			return (TblAplicaciones) query.uniqueResult();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	@Override
	@Transactional
	public TblAplicaciones getAplicacionFromLote(Integer loteId) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ap from TblLotesEnvios le inner join le.tblServicios s inner join s.tblAplicaciones ap "
					+ "where le.loteenvioid = :loteId";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setInteger("loteId", loteId);
			
			return (TblAplicaciones) query.uniqueResult();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	

	/**
	 * @return the tblUsuariosPushManager
	 */
	public TblUsuariosPushManager getTblUsuariosPushManager() {
		return tblUsuariosPushManager;
	}

	/**
	 * @param tblUsuariosPushManager
	 *            the tblUsuariosPushManager to set
	 */
	public void setTblUsuariosPushManager(TblUsuariosPushManager tblUsuariosPushManager) {
		this.tblUsuariosPushManager = tblUsuariosPushManager;
	}

	/**
	 * @return the queryExecutorViewServidoresPushPrioridad
	 */
	public QueryExecutorViewServidoresPushPrioridad getQueryExecutorViewServidoresPushPrioridad() {
		return queryExecutorViewServidoresPushPrioridad;
	}

	/**
	 * @param queryExecutorViewServidoresPushPrioridad
	 *            the queryExecutorViewServidoresPushPrioridad to set
	 */
	public void setQueryExecutorViewServidoresPushPrioridad(
			QueryExecutorViewServidoresPushPrioridad queryExecutorViewServidoresPushPrioridad) {
		this.queryExecutorViewServidoresPushPrioridad = queryExecutorViewServidoresPushPrioridad;
	}

	/**
	 * @return the queryExecutorUsuariosPush
	 */
	public QueryExecutorUsuariosPush getQueryExecutorUsuariosPush() {
		return queryExecutorUsuariosPush;
	}

	/**
	 * @param queryExecutorUsuariosPush
	 *            the queryExecutorUsuariosPush to set
	 */
	public void setQueryExecutorUsuariosPush(QueryExecutorUsuariosPush queryExecutorUsuariosPush) {
		this.queryExecutorUsuariosPush = queryExecutorUsuariosPush;
	}
	
}
