package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnvios;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.ViewEstadoLotesEnvios;
import es.minhap.sim.model.ViewGestionEnviosDestId;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_LOTESENVIOS
 * 
 * @author everis
 *
 */
@Service
public class QueryExecutorGestionEnviosImpl extends HibernateDaoSupport implements QueryExecutorGestionEnvios {

	protected static final String R_CONST_1 = "SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, ";

	protected static final String R_CONST_2 = "fechaHasta";

	protected static final String R_CONST_3 = " OR ge.servicioid = ";

	protected static final String R_CONST_4 = " AND ";

	protected static final String R_CONST_5 = " AND (";

	protected static final String R_CONST_6 = " FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ";

	protected static final String R_CONST_7 = " ";

	protected static final String R_CONST_8 = "GROUP BY ge.LOTEENVIOID, ge.APLICACION, ge.SERVICIO,ge.CANALID, ge.APLICACIONID,";

	protected static final String R_CONST_9 = "ge.DOCUSUARIO, ge.CODSIA, ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.ICONO, ge.SONIDO ";

	protected static final String R_CONST_10 = "'";

	protected static final String R_CONST_11 = "unchecked";

	protected static final String R_CONST_12 = ")";

	protected static final String R_CONST_13 = ",";

	protected static final String R_CONST_14 = "tbl_gestionenvios ges where ge.loteenvioid = ges.LOTEENVIOID) as ULTIMOENVIO, ge.SERVICIOID,  ge.LOTEENVIOID,";

	protected static final String R_CONST_15 = ", $";

	protected static final String R_CONST_16 = "1";

	protected static final String R_CONST_17 = " AND ge.CODORGANISMO IN (";

	protected static final String R_CONST_18 = " ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID ";

	protected static final String R_CONST_19 = " ASC";

	protected static final String R_CONST_20 = "fechaDesde";

	protected static final String R_CONST_21 = " ge.SERVICIOID, ge.MENSAJEID,ge.ULTIMOENVIO, ge.ESTADOID, ge.LOTEENVIOID,ge.SERVIDORID, ge.codigoexterno, ";

	protected static final String R_CONST_22 = " DESC";

	protected static final String R_CONST_23 = " AND NOT ge.servicioid = ";

	protected static final String R_CONST_24 = "' ";

	protected static final String R_CONST_25 = "', ";

	protected static final String R_CONST_26 = " ORDER BY ";

	protected static final String R_CONST_27 = "FROM tbl_gestionenvios ge WHERE 1=1 ";

	protected static final String R_CONST_28 = " AND (ge.servicioid = ";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorGestionEnviosImpl.class);
	
	private static final String UPDATE_END= "search - end";
	
	private static final String UPDATE_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	private static final String CODORGANISMO = "codorganismo";
	private static final String CODORGANISMOPAGADOR = "codorganismopagador";
	private static final String CODSIA = "codsia";
	private static final String ESTADO = "estado";
	private static final String COLUMNAESTADO = "estado";
	private static final String COLUMNAESTADOLOTE = "estadoLote";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings(R_CONST_11)
	@Override
	public List<ViewEstadoLotesEnvios> getUltimosEstadoEnviosLotes()  {
		List<ViewEstadoLotesEnvios> res = new ArrayList<>();

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			////ORIGINAL QUE CREO MAL porque salen los de 2012
//			String sql = "SELECT GE.loteenvioid, lo.nombre, GE.servicioid, GE.SERVICIO, GE.aplicacionid, GE.APLICACION, "
//					+ "SUM (CASE WHEN GE.estadoid = 1 THEN 1 ELSE 0 END) AS Enviados, "
//					+ "SUM (CASE WHEN GE.estadoid = 2 THEN 1 ELSE 0 END) AS Incidencias, "
//					+ "SUM (CASE WHEN GE.estadoid = 4 THEN 1 ELSE 0 END) AS Anulados, "
//					+ "SUM (CASE WHEN GE.estadoid = 3 OR GE.estadoid = 6  THEN 1 ELSE 0 END) AS Pendientes,"
//					+ " LO.FECHACREACION FROM    TBL_GESTIONENVIOS GE INNER JOIN (SELECT * FROM TBL_LOTESENVIOS WHERE "
//					+ "LOTEENVIOID IN (  SELECT LOTEENVIOID FROM TBL_MENSAJES) AND ROWNUM <= 10 ORDER BY LOTEENVIOID DESC) LO "
//					+ "ON LO.LOTEENVIOID = GE.loteenvioid GROUP BY GE.loteenvioid, lo.nombre, GE.servicioid, GE.SERVICIO, GE.aplicacionid, "
//					+ "GE.APLICACION, LO.FECHACREACION order by  LO.FECHACREACION desc";
			
			String sql = "select * from (SELECT GE.loteenvioid, lo.nombre, GE.servicioid, GE.SERVICIO, GE.aplicacionid, GE.APLICACION, "
					+ "SUM (CASE WHEN GE.estadoid = 1 THEN 1 ELSE 0 END) AS Enviados, SUM (CASE WHEN GE.estadoid = 2 THEN 1 ELSE 0 END) AS Incidencias, "
					+ "SUM (CASE WHEN GE.estadoid = 4 THEN 1 ELSE 0 END) AS Anulados, SUM (CASE WHEN GE.estadoid = 3 OR GE.estadoid = 6  "
					+ "THEN 1 ELSE 0 END) AS Pendientes, LO.FECHACREACION FROM  TBL_GESTIONENVIOS GE inner join "
					+ "( select  * from TBL_LOTESENVIOS  order by LOTEENVIOID desc) LO on GE.LOTEENVIOID = LO.LOTEENVIOID "
					+ "GROUP BY GE.loteenvioid, lo.nombre, GE.servicioid, GE.SERVICIO, GE.aplicacionid,	GE.APLICACION, LO.FECHACREACION "
					+ "order by  LO.FECHACREACION desc) where ROWNUM <= 10";
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ViewEstadoLotesEnvios ele = new ViewEstadoLotesEnvios();
				ele.setLoteenvioid((null != row[0])? ((BigDecimal) row[0]).longValue() : null);
				ele.setNombrelote((null != row[1])? (String) row[1] : "");
				ele.setServicioid((null != row[2])? ((BigDecimal) row[2]).longValue() : null);
				ele.setNombreservicio((null != row[3])? (String) row[3] : null);
				ele.setAplicacionid((null != row[4])? ((BigDecimal) row[4]).longValue() : null);
				ele.setNombreaplicacion((null != row[5])? (String) row[5] : null);
				ele.setEnviados((null != row[6])? ((BigDecimal) row[6]).longValue() : null);
				ele.setIncidencia((null != row[7])? ((BigDecimal) row[7]).longValue() : null);
				ele.setAnulado((null != row[8])? ((BigDecimal) row[8]).longValue() : null);
				ele.setPendiente((null != row[9])? ((BigDecimal) row[9]).longValue() : null);
				ele.setFecha((null != row[9])? (Date) row[10] : null);
				res.add(ele);
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@SuppressWarnings(R_CONST_11)
	@Override
	public List<TblGestionEnvios> getInformesServiciosBy(Long servicioId, Integer year, Integer month, String columna) {
		List<TblGestionEnvios> res = new ArrayList<>();

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT servicioid, anio, mes, ");
			queryString.append(columna);
			queryString.append(" , count(*) as numTotal from TBL_GESTIONENVIOS where servicioid = ");
			queryString.append(servicioId);
			queryString.append(" and anio = ");
			queryString.append(year);
			queryString.append(" and ");
			queryString.append("mes = ");
			queryString.append(month);
			queryString.append(" GROUP BY SERVICIOID, anio, mes, ");
			queryString.append(columna);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				TblGestionEnvios ge = new TblGestionEnvios();
				ge.setServicioid((null != row[0])? ((BigDecimal) row[0]).longValue() : null);
				ge.setAnio((null != row[1])? ((BigDecimal) row[1]).intValue() : null);
				ge.setMes((null != row[2])? ((BigDecimal) row[2]).intValue() : null);
				if(ESTADO.equals(columna)){
					ge.setEstado((null != row[3])? (String) row[3] : null);
				}else if (CODORGANISMO.equals(columna)){
					ge.setCodorganismo((null != row[3])? (String) row[3] : null);
				}else if (CODORGANISMOPAGADOR.equals(columna)){
					ge.setCodorganismopagador((null != row[3])? (String) row[3] : null);
				}else if (CODSIA.equals(columna)){
					ge.setCodsia((null != row[3])? (String) row[3] : null);
				}
				ge.setNumTotal((null != row[4])? ((BigDecimal) row[4]).intValue() : null);
				res.add(ge);
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_END);
			}

		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@Override
	public Integer countGestionEnviosLotes(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ R_CONST_14
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBREUSUARIO,  ge.ESTADOLOTE FROM tbl_gestionenvios ge WHERE  1=1  ");
			
	
			addSqlParameter(eg, sql, true);
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			sql.append(R_CONST_8
					+ " ge.SERVICIOID,  ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBREUSUARIO,  ge.ESTADOLOTE)");
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	
	@Override
	public Integer countGestionEnviosMensajes(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.NOMBRE, ge.ESTADO, ge.DESTINATARIO, ge.CANALID, ge.APLICACIONID,"
					+ R_CONST_21
					+ R_CONST_9
					+ R_CONST_27);
			
	
			addSqlParameter(eg, sql, false);
			
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			sql.append(R_CONST_12);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countGestionEnviosDestinatarios(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
					+ "ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID, "
					+ "ge.ENVIOID FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
	
			addSqlParameter(eg, sql, false);
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			
			sql.append(R_CONST_12);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countGestionEnviosDestinatariosReenvios(GestionEnvioBean eg, String serviciosExcluidos, String serviciosIncluidos) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			
			List<String> serviciosAeatGiss = new ArrayList<>(Arrays.asList(serviciosExcluidos.split(R_CONST_13)));
			String sqlExcluidos = "";
			
			if(serviciosExcluidos != null && !"".equals(serviciosExcluidos)){
				for(String idServ : serviciosAeatGiss){
					sqlExcluidos += R_CONST_23+idServ;
				}
			}
			
			
			StringBuilder sqlIncluidos = new StringBuilder();
			if(serviciosIncluidos != null && !"".equals(serviciosIncluidos)){
				List<String> servicios = new ArrayList<>(Arrays.asList(serviciosIncluidos.split(R_CONST_13)));
				sqlIncluidos.append(R_CONST_28+servicios.get(0));
				servicios.remove(0);			
				for(String idServ : servicios){
					sqlIncluidos.append(R_CONST_3+idServ);
				}
				sqlIncluidos.append(R_CONST_12);
			}
						
			sql.append("SELECT count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
					+ R_CONST_18
					+ R_CONST_6 + sqlExcluidos + sqlIncluidos);
			
//			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
//					+ "ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID, "
//					+ "ge.ENVIOID FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
			addSqlParameter(eg, sql, false);
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			
			sql.append(R_CONST_12);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@SuppressWarnings(R_CONST_11)
	@Override
	public List<TblGestionEnvios> getGestionEnvioLotesPaginado(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<TblGestionEnvios> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || R_CONST_16.equals(order)){
				orden = R_CONST_19;
			} else {
				orden = R_CONST_22;
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ R_CONST_14
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE FROM tbl_gestionenvios ge WHERE  1=1  ");
			
			
			addSqlParameter(criterio, sql, true);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			
			sql.append(R_CONST_8
					+ " ge.SERVICIOID,  ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE ");
			sql.append(R_CONST_26 + column + orden);
						
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			if (pagesize >=0){
				query.setMaxResults(pagesize);
			}
			query.setFirstResult(inicio);
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				TblGestionEnvios ge = new TblGestionEnvios();
				ge.setAplicacion((String)row[0]);
				ge.setServicio((String) row[1]);
				ge.setCanalid(((BigDecimal) null != row[2])? ((BigDecimal) row[2]).longValue() : null);
				ge.setAplicacionid(((BigDecimal) null != row[3])? ((BigDecimal) row[3]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[4])? (Date) row[4] : null);
				ge.setServicioid(((BigDecimal) null != row[5])? ((BigDecimal) row[5]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[6])? ((BigDecimal) row[6]).longValue() : null);
				ge.setCodorganismo((String) row[7]);
				ge.setCodorganismopagador((String) row[8]);
				ge.setNombre((String) row[9]);
				ge.setEstadolote((String) row[10]);
				res.add(ge);
			}
			return res;
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings(R_CONST_11)
	@Override
	public List<TblGestionEnvios> getGestionEnvioMensajesPaginado(int inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<TblGestionEnvios> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || R_CONST_16.equals(order)){
				orden = R_CONST_19;
			} else {
				orden = R_CONST_22;
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.NOMBRE, ge.ESTADO, ge.DESTINATARIO, ge.CANALID, ge.APLICACIONID,"
					+ R_CONST_21
					+ R_CONST_9
					+ R_CONST_27);
			
			
			addSqlParameter(criterio, sql, false);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}

			sql.append(R_CONST_26 + column + orden);
						
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			if (pagesize >=0){
				query.setMaxResults(pagesize);
			}
			query.setFirstResult(inicio);
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				TblGestionEnvios ge = new TblGestionEnvios();
				ge.setAplicacion((String)row[0]);
				ge.setServicio((String) row[1]);
				ge.setNombre((String) row[2]);
				ge.setEstado((String) row[3]);
				ge.setDestinatario((String) row[4]);
				ge.setCanalid(((BigDecimal) null != row[5])? ((BigDecimal) row[5]).longValue() : null);
				ge.setAplicacionid(((BigDecimal) null != row[6])? ((BigDecimal) row[6]).longValue() : null);
				ge.setServicioid(((BigDecimal) null != row[7])? ((BigDecimal) row[7]).longValue() : null);
				ge.setMensajeid(((BigDecimal) null != row[8])? ((BigDecimal) row[8]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[9])? (Date) row[9] : null);
				ge.setEstadoid(((BigDecimal) null != row[10])? ((BigDecimal) row[10]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[11])? ((BigDecimal) row[11]).longValue() : null);
				ge.setServidorid(((BigDecimal) null != row[12])? ((BigDecimal) row[12]).longValue() : null);
				ge.setCodigoexterno((String) row[13]);
				ge.setDocusuario((String) row[14]);
				ge.setCodsia((String) row[15]);
				ge.setCodorganismo((String) row[16]);
				ge.setCodorganismopagador((String) row[17]);
				ge.setIcono((String) row[18]);
				ge.setSonido((String) row[19]);
				
				res.add(ge);
			}
			return res;
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@SuppressWarnings(R_CONST_11)
	@Override
	public List<ViewGestionEnviosDestId> getGestionEnvioDestinatariosPaginado(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<ViewGestionEnviosDestId> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || R_CONST_16.equals(order)){
				orden = R_CONST_19;
			} else {
				orden = R_CONST_22;
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append(R_CONST_1
					+ R_CONST_18
					+ R_CONST_6);
			
			
			addSqlParameter(criterio, sql, false);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			sql.append(R_CONST_26 + column + orden);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			if (pagesize >=0){
				query.setMaxResults(pagesize);
			}
			query.setFirstResult(inicio);
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				ViewGestionEnviosDestId ge = new ViewGestionEnviosDestId();
				ge.setAplicacion((String)row[0]);
				ge.setServicio((String) row[1]);
				ge.setLoteenvio((String) row[2]);
				ge.setAplicacionid(((BigDecimal) null != row[3])? ((BigDecimal) row[3]).longValue() : null);
				ge.setDestinatario((String) row[4]);
				ge.setServicioid(((BigDecimal) null != row[5])? ((BigDecimal) row[5]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[6])? ((BigDecimal) row[6]).longValue() : null);
				ge.setMensajeid(((BigDecimal) null != row[7])? ((BigDecimal) row[7]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[8])? (Date) row[8] : null);
				ge.setEstado((String) row[10]);
				ge.setDestinatariosmensajes(((BigDecimal) null != row[11])? ((BigDecimal) row[11]).longValue() : null);
				ge.setCanalid(((BigDecimal) null != row[12])? ((BigDecimal) row[12]).longValue() : null);
				res.add(ge);
			}
			return res;
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	@SuppressWarnings(R_CONST_11)
	@Override
	public List<ViewGestionEnviosDestId> getGestionEnvioDestinatariosPaginadoReenvioJob(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio, String serviciosExcluidos, String serviciosIncluidos) {
		try {
			List<ViewGestionEnviosDestId> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || R_CONST_16.equals(order)){
				orden = R_CONST_19;
			} else {
				orden = R_CONST_22;
			}
			
			List<String> serviciosAeatGiss = new ArrayList<>(Arrays.asList(serviciosExcluidos.split(R_CONST_13)));
			String sqlExcluidos = "";
			
			if(serviciosExcluidos != null && !"".equals(serviciosExcluidos)){
				for(String idServ : serviciosAeatGiss){
					sqlExcluidos += R_CONST_23+idServ;
				}
			}
			
			
			
			StringBuilder sqlIncluidos = new StringBuilder();
			if(serviciosIncluidos != null && !"".equals(serviciosIncluidos)){
				List<String> servicios = new ArrayList<>(Arrays.asList(serviciosIncluidos.split(R_CONST_13)));
				sqlIncluidos.append(R_CONST_28+servicios.get(0));
				servicios.remove(0);			
				for(String idServ : servicios){
					sqlIncluidos.append(R_CONST_3+idServ);
				}
				sqlIncluidos.append(R_CONST_12);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_START);
			}
			sql.append(R_CONST_1
					+ R_CONST_18
					+ R_CONST_6 + sqlExcluidos + sqlIncluidos);
			
			
			addSqlParameter(criterio, sql, false);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append(R_CONST_10).append(organismo).append(R_CONST_25);
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(R_CONST_15, "");
				sql.append(R_CONST_17 + organismosAeat +R_CONST_12);
			}
			
			sql.append(R_CONST_26 + column + orden);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp(R_CONST_20, cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp(R_CONST_2, cal.getTime());
			}
			if (pagesize >=0){
				query.setMaxResults(pagesize);
			}
			query.setFirstResult(inicio);
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				ViewGestionEnviosDestId ge = new ViewGestionEnviosDestId();
				ge.setAplicacion((String)row[0]);
				ge.setServicio((String) row[1]);
				ge.setLoteenvio((String) row[2]);
				ge.setAplicacionid(((BigDecimal) null != row[3])? ((BigDecimal) row[3]).longValue() : null);
				ge.setDestinatario((String) row[4]);
				ge.setServicioid(((BigDecimal) null != row[5])? ((BigDecimal) row[5]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[6])? ((BigDecimal) row[6]).longValue() : null);
				ge.setMensajeid(((BigDecimal) null != row[7])? ((BigDecimal) row[7]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[8])? (Date) row[8] : null);
				ge.setEstado((String) row[10]);
				ge.setDestinatariosmensajes(((BigDecimal) null != row[11])? ((BigDecimal) row[11]).longValue() : null);
				ge.setCanalid(((BigDecimal) null != row[12])? ((BigDecimal) row[12]).longValue() : null);
				res.add(ge);
			}
			return res;
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	private String getSubqueryEstado(Integer idEstado, String columna){
		String res = "";
		switch (idEstado){
		case 1:
			res = R_CONST_4+columna+" ='ENVIADO' ";
			break;
		case 2:
			res = R_CONST_4+columna+" ='INCIDENCIA' ";
			break;
		case 3:
			res = R_CONST_5+columna+" ='PENDIENTE DE ENVIO' or "+columna+"= 'PENDIENTE') ";
			break;
		case 4:
			res = R_CONST_4+columna+" ='ANULADO' ";
			break;
		case 5:
			res = R_CONST_5+columna+" ='PENDIENTE' or "+columna+"='INCIDENCIA' or "+columna+"='PENDIENTE DE ENVIO') ";
			break;
		case 6:
			res = R_CONST_4+columna+" ='PENDIENTE DE OPERADORA' ";
			break;
		case 7:
			res = R_CONST_4+columna+" ='RECIBIDO' ";
			break;
		case 8:
			res = R_CONST_4+columna+" ='LEIDO' ";
			break;
		case 9:
			res = R_CONST_4+columna+" ='ENVIANDO' ";
			break;
		default:
			break;
		}
	return res;
		}

	/**
	 * @param criterio
	 * @param sql
	 */
	private void addSqlParameter(GestionEnvioBean criterio, StringBuilder sql, boolean porLotes) {
		if (criterio.getAplicacionId()!= null){
			sql.append(" AND ge.aplicacionid ="+criterio.getAplicacionId()+R_CONST_7);
		}
		if (criterio.getServidorId()!= null){
			sql.append(" AND ge.servidorid ="+criterio.getServidorId()+R_CONST_7);
		}
		if (criterio.getCanalId()!= null){
			sql.append(" AND ge.canalid ="+criterio.getCanalId()+R_CONST_7);
		}
		if (criterio.getServicioId()!= null){
			sql.append(" AND ge.servicioid ="+criterio.getServicioId()+R_CONST_7);
		}
		if (criterio.getEstadoId()!= null){
			String e;
			if (porLotes){
				e = getSubqueryEstado(criterio.getEstadoId().intValue(), COLUMNAESTADOLOTE);
			}else{
				e = getSubqueryEstado(criterio.getEstadoId().intValue(), COLUMNAESTADO);
			}
			
			sql.append(e);
		}
		if (criterio.getIdLote()!= null){
			sql.append(" AND ge.loteenvioid ="+criterio.getIdLote()+R_CONST_7);
		}
		if (criterio.getDestinatario()!= null && !criterio.getDestinatario().isEmpty()){
			sql.append(" AND LOWER(ge.destinatario) like lower('%"+criterio.getDestinatario()+"%') ");
		}
		if (criterio.getFechaDesde()!= null){
			sql.append(" AND ge.ultimoEnvio >=:fechaDesde ");
		}
		if (criterio.getFechaHasta()!= null){
			sql.append(" AND ge.ultimoEnvio <=:fechaHasta ");
		}
		if (criterio.getListaIdAplicaciones()!= null && !criterio.getListaIdAplicaciones().isEmpty()){
			sql.append(" AND ge.aplicacionid IN ("+criterio.getListaIdAplicaciones()+") ");
		}
		if (criterio.getDocUsuario()!= null && !criterio.getDocUsuario().isEmpty()){
			sql.append(" AND ge.DocUsuario ='"+criterio.getDocUsuario()+R_CONST_24);
		}
		if (criterio.getCodSIA()!= null && !criterio.getCodSIA().isEmpty()){
			sql.append(" AND ge.CodSIA ='"+criterio.getCodSIA()+R_CONST_24);
		}
		if (criterio.getCodOrganismo()!= null && !criterio.getCodOrganismo().isEmpty()){
			sql.append(" AND ge.CodOrganismo ='"+criterio.getCodOrganismo()+R_CONST_24);
		}
		if (criterio.getCodOrganismoPagador()!= null && !criterio.getCodOrganismoPagador().isEmpty()){
			sql.append(" AND ge.CodOrganismoPagador ='"+criterio.getCodOrganismoPagador()+R_CONST_24);
		}
		if (criterio.getMensajeId()!= null){
			sql.append(" AND ge.mensajeid ="+criterio.getMensajeId()+R_CONST_7);
		}
	}

	
}
