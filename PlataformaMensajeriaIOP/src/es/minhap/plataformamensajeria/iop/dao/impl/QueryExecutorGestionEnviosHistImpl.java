package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnviosHist;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.ViewGestionEnviosDestHistId;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_LOTESENVIOS
 * 
 * @author everis
 *
 */
@Service()
public class QueryExecutorGestionEnviosHistImpl extends HibernateDaoSupport implements QueryExecutorGestionEnviosHist {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorGestionEnviosHistImpl.class);
	
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
	public List<TblGestionEnviosHist> getInformesServiciosBy(Long servicioId, Integer year, Integer month, String columna) {
		List<TblGestionEnviosHist> res = new ArrayList<>();

		try {
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			String sql = "SELECT servicioid, anio, mes, " + columna + " , count(*) as numTotal from TBL_GESTIONENVIOS_HIST where servicioid = "+ servicioId +" and anio = "+year+" and "
					+ "mes = "+month+" GROUP BY SERVICIOID, anio, mes, " + columna;
			
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				TblGestionEnviosHist ge = new TblGestionEnviosHist();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnviosHist> convertGestionEnviosTOGestionEnviosHist(List<Long> subList) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			String sql = "select m from TblGestionEnvios m where mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList("lista", subList);
			
			return convertTOHistGestionEnvios(query.list());
			 			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnviosHist> getTodosGestionEnviosCons(List<Long> listaMensajes) {
		try {
			List<TblGestionEnviosHist> res;
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			String sql = "from TblGestionEnviosHist ge where ge.mensajeid in (:lista) ";
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
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
		
	private List<TblGestionEnviosHist> convertTOHistGestionEnvios(List<TblGestionEnvios> lista) {
		List<TblGestionEnviosHist> res = new ArrayList<>();
		if (null != lista){
			for (TblGestionEnvios gestion : lista) {
				TblGestionEnviosHist ge = new TblGestionEnviosHist();
				ge.setAnio(gestion.getAnio());
				ge.setAplicacion(gestion.getAplicacion());
				ge.setAplicacionid(gestion.getAplicacionid());
				ge.setCanal(gestion.getCanal());
				ge.setCanalid(gestion.getCanalid());
				ge.setCodigoexterno(gestion.getCodigoexterno());
				ge.setCodorganismo(gestion.getCodorganismo());
				ge.setCodorganismopagador(gestion.getCodorganismopagador());
				ge.setCodsia(gestion.getCodsia());
				ge.setDestinatario(gestion.getDestinatario());
				ge.setDocusuario(gestion.getDocusuario());
				ge.setEstado(gestion.getEstado());
				ge.setEstadoid(gestion.getEstadoid());
				ge.setEstadolote(gestion.getEstadolote());
				ge.setIcono(gestion.getIcono());
				ge.setLoteenvioid(gestion.getLoteenvioid());
				ge.setMensajeid(gestion.getMensajeid());
				ge.setMes(gestion.getMes());
				ge.setNombre(gestion.getNombre());
				ge.setNombreusuario(gestion.getNombreusuario());
				ge.setServicio(gestion.getServicio());
				ge.setServicioid(gestion.getServicioid());
				ge.setServidorid(gestion.getServidorid());
				ge.setSonido(gestion.getSonido());
				ge.setUltimoenvio(gestion.getUltimoenvio());
				res.add(ge);
			}	
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblGestionEnviosHist> getGestionEnvioLotesPaginado(int inicio, Integer pagesize, String order,
			String column, GestionEnvioHistoricoBean criterio) {
		try {
			List<TblGestionEnviosHist> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();

			// Orden ascendente o descendente
			String orden = "";
			if (order == null || order.equals("1")) {
				orden = " ASC";
			} else {
				orden = " DESC";
			}

			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ "tbl_gestionenvios_hist ges where ge.loteenvioid = ges.LOTEENVIOID) as ULTIMOENVIO, ge.SERVICIOID,  ge.LOTEENVIOID,"
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE FROM tbl_gestionenvios_hist ge WHERE  1=1  ");

			addSqlParameter(criterio, sql, true);

			sql.append("GROUP BY ge.LOTEENVIOID, ge.APLICACION, ge.SERVICIO,ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID,  ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBRE,  ge.ESTADOLOTE ");
			sql.append(" ORDER BY " + column + orden);

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(sql.toString());
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
			
			if (pagesize >= 0) {
				query.setMaxResults(pagesize);
			}
			query.setFirstResult(inicio);
			List<Object[]> rows = query.list();

			for (Object[] row : rows) {
				TblGestionEnviosHist ge = new TblGestionEnviosHist();
				ge.setAplicacion((String) row[0]);
				ge.setServicio((String) row[1]);
				ge.setCanalid(((BigDecimal) null != row[2]) ? ((BigDecimal) row[2]).longValue() : null);
				ge.setAplicacionid(((BigDecimal) null != row[3]) ? ((BigDecimal) row[3]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[4]) ? (Date) row[4] : null);
				ge.setServicioid(((BigDecimal) null != row[5]) ? ((BigDecimal) row[5]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[6]) ? ((BigDecimal) row[6]).longValue() : null);
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
	public List<TblGestionEnviosHist> getGestionEnvioMensajesPaginado(int inicio, Integer pagesize, String order,
			String column, GestionEnvioHistoricoBean criterio) {
		try {
			List<TblGestionEnviosHist> res = new ArrayList<>();
			StringBuilder sql = new StringBuilder();

			// Orden ascendente o descendente
			String orden = "";
			if (order == null || order.equals("1")) {
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
					+ "FROM tbl_gestionenvios_hist ge WHERE 1=1 ");

			addSqlParameter(criterio, sql, false);

			sql.append(" ORDER BY " + column + orden);

			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createSQLQuery(sql.toString());
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
				TblGestionEnviosHist ge = new TblGestionEnviosHist();
				ge.setAplicacion((String) row[0]);
				ge.setServicio((String) row[1]);
				ge.setNombre((String) row[2]);
				ge.setEstado((String) row[3]);
				ge.setDestinatario((String) row[4]);
				ge.setCanalid(((BigDecimal) null != row[5]) ? ((BigDecimal) row[5]).longValue() : null);
				ge.setAplicacionid(((BigDecimal) null != row[6]) ? ((BigDecimal) row[6]).longValue() : null);
				ge.setServicioid(((BigDecimal) null != row[7]) ? ((BigDecimal) row[7]).longValue() : null);
				ge.setMensajeid(((BigDecimal) null != row[8]) ? ((BigDecimal) row[8]).longValue() : null);
				ge.setUltimoenvio(((Date) null != row[9]) ? (Date) row[9] : null);
				ge.setEstadoid(((BigDecimal) null != row[10]) ? ((BigDecimal) row[10]).longValue() : null);
				ge.setLoteenvioid(((BigDecimal) null != row[11]) ? ((BigDecimal) row[11]).longValue() : null);
				ge.setServidorid(((BigDecimal) null != row[12]) ? ((BigDecimal) row[12]).longValue() : null);
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
	
	@Override
	public Integer countGestionEnviosHistoricoLotes(GestionEnvioHistoricoBean eg) {
		
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.CANALID, ge.APLICACIONID,(select max(distinct ges.ULTIMOENVIO) FROM "
					+ "tbl_gestionenvios_hist ges where ge.loteenvioid = ges.LOTEENVIOID) as ULTIMOENVIO, ge.SERVICIOID,  ge.LOTEENVIOID,"
					+ " ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.NOMBREUSUARIO,  ge.ESTADOLOTE FROM tbl_gestionenvios_hist ge WHERE  1=1  ");
			
	
			addSqlParameter(eg, sql, true);
			
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
	public Integer countGestionEnviosHistorico(GestionEnvioHistoricoBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.NOMBRE, ge.ESTADO, ge.DESTINATARIO, ge.CANALID, ge.APLICACIONID,"
					+ " ge.SERVICIOID, ge.MENSAJEID,ge.ULTIMOENVIO, ge.ESTADOID, ge.LOTEENVIOID,ge.SERVIDORID, ge.codigoexterno, "
					+ "ge.DOCUSUARIO, ge.CODSIA, ge.CODORGANISMO, ge.CODORGANISMOPAGADOR, ge.ICONO, ge.SONIDO "
					+ "FROM tbl_gestionenvios_hist ge WHERE 1=1 ");
			
	
			addSqlParameter(eg, sql, false);
			
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
	public Integer countGestionEnviosHistDestinatarios(GestionEnvioHistoricoBean eg) {
		try {
			StringBuilder sql = new StringBuilder();
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_START);
			}
			sql.append("select count(*) from (SELECT ge.APLICACION, ge.SERVICIO, ge.LOTEENVIO, ge.APLICACIONID, ge.DESTINATARIO, ge.SERVICIOID, ge.LOTEENVIOID, "
					+ "ge.MENSAJEID, ge.ULTIMOENVIO AS ULTIMOENVIO, ge.ULTIMOENVIO AS FECHA, ge.ESTADO, ge.DESTINATARIOSMENSAJES, ge.CANALID, "
					+ "ge.ENVIOID FROM VIEW_GESTIONENVIOS_DEST_HIST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
	
			addSqlParameter(eg, sql, false);
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
	public List<ViewGestionEnviosDestHistId> getGestionEnvioDestinatariosHistPaginado(int inicio, Integer pagesize,
			String order, String column, GestionEnvioHistoricoBean criterio) {
		try {
			List<ViewGestionEnviosDestHistId> res = new ArrayList<>();
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
					+ " FROM VIEW_GESTIONENVIOS_DEST_HIST ge WHERE 1=1 and ge.ULTIMOENVIO IS NOT NULL ");
			
			
			addSqlParameter(criterio, sql, false);
			
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
				ViewGestionEnviosDestHistId ge = new ViewGestionEnviosDestHistId();
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
			res = " AND "+columna+" ='PENDIENTE DE ENVIO' ";
			break;
		case 4:
			res = " AND "+columna+" ='ANULADO' ";
			break;
		case 5:
			res = " AND ("+columna+" ='PENDIENTE' or "+columna+"='INCIDENCIA') ";
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
	 * @param eg
	 * @param sql
	 */
	private void addSqlParameter(GestionEnvioHistoricoBean eg, StringBuilder sql, boolean porLotes) {
		if (eg.getAplicacionId()!= null){
			sql.append(" AND ge.aplicacionid ="+eg.getAplicacionId()+" ");
		}
		if (eg.getServidorId()!= null){
			sql.append(" AND ge.servidorid ="+eg.getServidorId()+" ");
		}
		if (eg.getCanalId()!= null){
			sql.append(" AND ge.canalid ="+eg.getCanalId()+" ");
		}
		if (eg.getServicioId()!= null){
			sql.append(" AND ge.servicioid ="+eg.getServicioId()+" ");
		}
		if (eg.getEstadoId()!= null){
			String e;
			if (porLotes){
				e = getSubqueryEstado(eg.getEstadoId().intValue(), COLUMNAESTADOLOTE);
			}else{
				e = getSubqueryEstado(eg.getEstadoId().intValue(), COLUMNAESTADO);
			}
			
			sql.append(e);
		}
		if (eg.getIdLote()!= null){
			sql.append(" AND ge.loteenvioid ="+eg.getIdLote()+" ");
		}
		if (eg.getDestinatario()!= null && eg.getDestinatario().length()>0){
			sql.append(" AND ge.destinatario like '%"+eg.getDestinatario()+"%' ");
		}
		if (eg.getFechaDesde()!= null){
			sql.append("AND ge.ultimoEnvio >=:fechaDesde ");
		}
		if (eg.getFechaHasta()!= null){
			sql.append(" AND ge.ultimoEnvio <=:fechaHasta ");
		}
		if (eg.getListaIdAplicaciones()!= null && eg.getListaIdAplicaciones().length()>0){
			sql.append(" AND ge.aplicacionid IN ("+eg.getListaIdAplicaciones()+") ");
		}
		if (eg.getDocUsuario()!= null && eg.getDocUsuario().length() > 0){
			sql.append(" AND ge.DocUsuario ='"+eg.getDocUsuario()+"' ");
		}
		if (eg.getCodSIA()!= null && eg.getCodSIA().length() > 0){
			sql.append(" AND ge.CodSIA ='"+eg.getCodSIA()+"' ");
		}
		if (eg.getCodOrganismo()!= null && eg.getCodOrganismo().length() > 0){
			sql.append(" AND ge.CodOrganismo ='"+eg.getCodOrganismo()+"' ");
		}
		if (eg.getCodOrganismoPagador()!= null && eg.getCodOrganismoPagador().length() > 0){
			sql.append(" AND ge.CodOrganismoPagador ='"+eg.getCodOrganismoPagador()+"' ");
		}
		if (eg.getMensajeId()!= null){
			sql.append(" AND ge.mensajeid ="+eg.getMensajeId()+" ");
		}
	}



	

}
