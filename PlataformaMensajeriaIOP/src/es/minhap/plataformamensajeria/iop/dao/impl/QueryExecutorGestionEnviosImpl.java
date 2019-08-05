package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorGestionEnviosImpl.class);
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewEstadoLotesEnvios> getUltimosEstadoEnviosLotes()  {
		List<ViewEstadoLotesEnvios> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
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
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnvios> getInformesServiciosBy(Long servicioId, Integer year, Integer month, String columna) {
		List<TblGestionEnvios> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			String sql = "SELECT servicioid, anio, mes, " + columna + " , count(*) as numTotal from TBL_GESTIONENVIOS where servicioid = "+ servicioId +" and anio = "+year+" and "
					+ "mes = "+month+" GROUP BY SERVICIOID, anio, mes, " + columna;
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				TblGestionEnvios ge = new TblGestionEnvios();
				ge.setServicioid((null != row[0])? ((BigDecimal) row[0]).longValue() : null);
				ge.setAnio((null != row[1])? ((BigDecimal) row[1]).intValue() : null);
				ge.setMes((null != row[2])? ((BigDecimal) row[2]).intValue() : null);
				if(ESTADO.equals(columna)){
					ge.setEstado((null != row[3])? ((String) row[3]) : null);
				}else if (CODORGANISMO.equals(columna)){
					ge.setCodorganismo((null != row[3])? ((String) row[3]) : null);
				}else if (CODORGANISMOPAGADOR.equals(columna)){
					ge.setCodorganismopagador((null != row[3])? ((String) row[3]) : null);
				}else if (CODSIA.equals(columna)){
					ge.setCodsia((null != row[3])? ((String) row[3]) : null);
				}
				ge.setNumTotal((null != row[4])? ((BigDecimal) row[4]).intValue() : null);
				res.add(ge);
			}
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_END);
			}

		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return res;	
	}

	@Override
	public Integer countGestionEnviosLotes(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ "tbl_gestionenvios ges where ge.loteenvioid = ges.LOTEENVIOID) as ULTIMOENVIO, ge.SERVICIOID,  ge.LOTEENVIOID,"
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBREUSUARIO,  ge.ESTADOLOTE FROM tbl_gestionenvios ge WHERE  1=1  ");
			
	
			addSqlParameter(eg, sql, true);
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}
			
			sql.append("GROUP BY ge.LOTEENVIOID, ge.APLICACION, ge.SERVICIO,ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID,  ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBREUSUARIO,  ge.ESTADOLOTE)");
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	
	@Override
	public Integer countGestionEnviosMensajes(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.NOMBRE, ge.ESTADO, ge.DESTINATARIO, ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID, ge.MENSAJEID,ge.ULTIMOENVIO, ge.ESTADOID, ge.LOTEENVIOID,ge.SERVIDORID, ge.codigoexterno, "
					+ "ge.DOCUSUARIO, ge.CODSIA, ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.ICONO, ge.SONIDO "
					+ "FROM tbl_gestionenvios ge WHERE 1=1 ");
			
	
			addSqlParameter(eg, sql, false);
			
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}
			
			sql.append(")");
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countGestionEnviosDestinatarios(GestionEnvioBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
					+ "ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID, "
					+ "ge.ENVIOID FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
	
			addSqlParameter(eg, sql, false);
			
			if(eg.getArrayOrganismos()!=null && !eg.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : eg.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}
			
			
			sql.append(")");
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != eg.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != eg.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(eg.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
			}
			
			return ((BigDecimal)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnvios> getGestionEnvioLotesPaginado(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<TblGestionEnvios> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || order.equals("1")){
				orden = " ASC";
			} else {
				orden = " DESC";
			}
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ "tbl_gestionenvios ges where ge.loteenvioid = ges.LOTEENVIOID) as ULTIMOENVIO, ge.SERVICIOID,  ge.LOTEENVIOID,"
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE FROM tbl_gestionenvios ge WHERE  1=1  ");
			
			
			addSqlParameter(criterio, sql, true);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}
			
			
			sql.append("GROUP BY ge.LOTEENVIOID, ge.APLICACION, ge.SERVICIO,ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID,  ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE ");
			sql.append(" ORDER BY " + column + orden);
						
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
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
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnvios> getGestionEnvioMensajesPaginado(int inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<TblGestionEnvios> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || order.equals("1")){
				orden = " ASC";
			} else {
				orden = " DESC";
			}
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.NOMBRE, ge.ESTADO, ge.DESTINATARIO, ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID, ge.MENSAJEID,ge.ULTIMOENVIO, ge.ESTADOID, ge.LOTEENVIOID,ge.SERVIDORID, ge.codigoexterno, "
					+ "ge.DOCUSUARIO, ge.CODSIA, ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.ICONO, ge.SONIDO "
					+ "FROM tbl_gestionenvios ge WHERE 1=1 ");
			
			
			addSqlParameter(criterio, sql, false);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}

			sql.append(" ORDER BY " + column + orden);
						
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
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
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ViewGestionEnviosDestId> getGestionEnvioDestinatariosPaginado(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio) {
		try {
			List<ViewGestionEnviosDestId> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			
			// Orden ascendente o descendente
			String orden = "";
			if (order == null || order.equals("1")){
				orden = " ASC";
			} else {
				orden = " DESC";
			}
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
					+ " ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID "
					+ " FROM VIEW_GESTIONENVIOS_DEST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
			
			addSqlParameter(criterio, sql, false);
			
			if(criterio.getArrayOrganismos()!=null && !criterio.getArrayOrganismos().isEmpty()){
				String organismosAeat = null;
				StringBuffer sb = new StringBuffer(); 
				
				for (String organismo : criterio.getArrayOrganismos()){
					sb.append("'").append(organismo).append("', ");
				}
				
				organismosAeat = sb.toString();
				organismosAeat = organismosAeat.replaceAll(", $", "");
				sql.append(" AND ge.CODORGANISMO IN (" + organismosAeat +")");
			}
			
			sql.append(" ORDER BY " + column + orden);
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if (null != criterio.getFechaDesde()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaDesde());
				
				query.setTimestamp("fechaDesde", cal.getTime());
			}
			if (null != criterio.getFechaHasta()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(criterio.getFechaHasta());
				
				query.setTimestamp("fechaHasta", cal.getTime());
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
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	
	
	private String getSubqueryEstado(Integer idEstado, String columna){
		String res = "";
		switch (idEstado){
		case 1:
			res = " AND "+columna+" ='ENVIADO' ";
			break;
		case 2:
			res = " AND "+columna+" ='INCIDENCIA' ";
			break;
		case 3:
			res = " AND ("+columna+" ='PENDIENTE DE ENVIO' or "+columna+"= 'PENDIENTE') ";
			break;
		case 4:
			res = " AND "+columna+" ='ANULADO' ";
			break;
		case 5:
			res = " AND ("+columna+" ='PENDIENTE' or "+columna+"='INCIDENCIA' or "+columna+"='PENDIENTE DE ENVIO') ";
			break;
		case 6:
			res = " AND "+columna+" ='PENDIENTE DE OPERADORA' ";
			break;
		case 7:
			res = " AND "+columna+" ='RECIBIDO' ";
			break;
		case 8:
			res = " AND "+columna+" ='LEIDO' ";
			break;
		case 9:
			res = " AND "+columna+" ='ENVIANDO' ";
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
			sql.append(" AND ge.aplicacionid ="+criterio.getAplicacionId()+" ");
		}
		if (criterio.getServidorId()!= null){
			sql.append(" AND ge.servidorid ="+criterio.getServidorId()+" ");
		}
		if (criterio.getCanalId()!= null){
			sql.append(" AND ge.canalid ="+criterio.getCanalId()+" ");
		}
		if (criterio.getServicioId()!= null){
			sql.append(" AND ge.servicioid ="+criterio.getServicioId()+" ");
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
			sql.append(" AND ge.loteenvioid ="+criterio.getIdLote()+" ");
		}
		if (criterio.getDestinatario()!= null && criterio.getDestinatario().length()>0){
			sql.append(" AND ge.destinatario like lower('%"+criterio.getDestinatario()+"%') ");
		}
		if (criterio.getFechaDesde()!= null){
			sql.append(" AND ge.ultimoEnvio >=:fechaDesde ");
		}
		if (criterio.getFechaHasta()!= null){
			sql.append(" AND ge.ultimoEnvio <=:fechaHasta ");
		}
		if (criterio.getListaIdAplicaciones()!= null && criterio.getListaIdAplicaciones().length()>0){
			sql.append(" AND ge.aplicacionid IN ("+criterio.getListaIdAplicaciones()+") ");
		}
		if (criterio.getDocUsuario()!= null && criterio.getDocUsuario().length() > 0){
			sql.append(" AND ge.DocUsuario ='"+criterio.getDocUsuario()+"' ");
		}
		if (criterio.getCodSIA()!= null && criterio.getCodSIA().length() > 0){
			sql.append(" AND ge.CodSIA ='"+criterio.getCodSIA()+"' ");
		}
		if (criterio.getCodOrganismo()!= null && criterio.getCodOrganismo().length() > 0){
			sql.append(" AND ge.CodOrganismo ='"+criterio.getCodOrganismo()+"' ");
		}
		if (criterio.getCodOrganismoPagador()!= null && criterio.getCodOrganismoPagador().length() > 0){
			sql.append(" AND ge.CodOrganismoPagador ='"+criterio.getCodOrganismoPagador()+"' ");
		}
		if (criterio.getMensajeId()!= null){
			sql.append(" AND ge.mensajeid ="+criterio.getMensajeId()+" ");
		}
	}

	
}
