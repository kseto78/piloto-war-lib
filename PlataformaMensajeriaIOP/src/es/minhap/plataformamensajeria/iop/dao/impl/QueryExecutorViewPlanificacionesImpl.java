package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewPlanificaciones;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla
 * 
 * @author everis
 * 
 */
@Service
@Transactional
public class QueryExecutorViewPlanificacionesImpl extends HibernateDaoSupport implements QueryExecutorViewPlanificaciones {

	protected static final String R_CONST_1 = "','24:00','23:59','";
	protected static final String R_CONST_2 = "and ";
	protected static final String R_CONST_3 = "') AND horahasta >= trim('";
	protected static final String R_CONST_4 = "') AND horadesde <= trim('";
	protected static final String R_CONST_5 = "Se ha producido un error ";
	protected static final String R_CONST_6 = " and horadesde <= trim('";
	protected static final String R_CONST_7 = "horadesde <= trim('";
	protected static final String R_CONST_8 = " and ";
	protected static final String R_CONST_9 = "is not null";
	protected static final String R_CONST_10 = " and planificacionId  ";
	protected static final String R_CONST_11 = " != ";
	protected static final String R_CONST_12 = "= 'S'";
	protected static final String R_CONST_13 = " and (eliminado is null or eliminado = 'N') and activo = 1 and ";
	protected static final String R_CONST_14 = "')";
	protected static final String R_CONST_15 = "select count(*) from view_planificaciones where servicioid = ";
	protected static final String R_CONST_16 = "') ";
	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorViewPlanificacionesImpl.class);
	
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Integer validaPlanificacionServicio(Long planificacionId, Integer tipo, String dia, Integer servidorId,
			String paramHoraHasta, String paramHoraDesde, Integer servicioId) {
		Integer resultado = null;
		try {
			if (null != servicioId){
				resultado = comprobarSolapaPlanificacionServicio(planificacionId, dia, paramHoraHasta,
						paramHoraDesde, servicioId);
				if (resultado>0) {
					return 2;
				}
			}
			
			if (null != servidorId){
				return servidorPropioAsignadoServicio(dia, servidorId, paramHoraHasta, paramHoraDesde);
			}
			
			//Comprobamos si tiene el servicio algún servidor
			resultado = comprobarServicioTieneServidor(dia, paramHoraHasta, paramHoraDesde, servicioId);
			
			if (null != resultado){
				return resultado;
			}
			
			///Comprobamos si tiene servidor por defecto
			return comprobarServidorPorDefecto(tipo, dia, paramHoraHasta, paramHoraDesde);
			
		} catch (Exception e) {
			LOG.error(R_CONST_5, e);
			throw new ApplicationException(e);
		}
	}

	
	@Override
	public Integer validaPlanificacionServidor(Long planificacionId, String dia, Integer servidorId,
			String paramHoraHasta, String paramHoraDesde) {
		
		try {
			if (null != servidorId && servidorId > 0){
				 return comprobarSolapaPlanificacionServidor(planificacionId, dia, paramHoraHasta,
						paramHoraDesde, servidorId);
			}else{
				return 1;
			}
						
		} catch (Exception e) {
			LOG.error(R_CONST_5, e);
			throw new ApplicationException(e);
		}
	}
	
	
	
	@Override
	public Integer countPlanificacionPorHorasOrganismo(Integer organismoId, String dia, Long planificacionId,
			String paramHoraHasta, String paramHoraDesde, Integer servicioId, Integer servidorId, Integer tipo) {
		Integer resultado = null;
		try {
			if (null != servicioId && servicioId > 0){
				resultado = comprobarSolapaPlanificacionOrganismo(planificacionId, dia, paramHoraHasta,
						paramHoraDesde, servicioId, organismoId);
				if (resultado==2) {
					return resultado;
				}
			}
			
			if (null != servidorId && servidorId > 0){
				return servidorPropioAsignadoServicio(dia, servidorId, paramHoraHasta, paramHoraDesde);
			}
			
			//Comprobamos si tiene el servicio algún servidor
			resultado = comprobarServicioTieneServidor(dia, paramHoraHasta, paramHoraDesde, servicioId);
			
			if (null != resultado){
				return resultado;
			}
			
			///Comprobamos si tiene servidor por defecto
			return comprobarServidorPorDefecto(tipo, dia, paramHoraHasta, paramHoraDesde);
			
		} catch (Exception e) {
			LOG.error(R_CONST_5, e);
			throw new ApplicationException(e);
		}
	}
		
	

	private Integer comprobarSolapaPlanificacionOrganismo(Long planificacionId, String dia, String paramHoraHasta,
			String paramHoraDesde, Integer servicioId, Integer organismoId) {
		String sqlDia = dia + R_CONST_12; 
		String sqlPlanificacion = (null == planificacionId)? R_CONST_9 : R_CONST_11 +planificacionId;
		StringBuilder queryString = new StringBuilder();
		queryString.append(R_CONST_15);
		queryString.append("?");
		queryString.append(" and organismoid = ");
		queryString.append("?");
		queryString.append(R_CONST_8);
		queryString.append(sqlDia);
		queryString.append(" and planificacionId ");
		queryString.append(sqlPlanificacion);
		queryString.append(" and (eliminado is null or eliminado = 'N') and activo = 1 ");
		queryString.append("and (to_date(horadesde, 'HH24:mi') < to_date(decode('");
		queryString.append(paramHoraHasta);
		queryString.append(R_CONST_1);
		queryString.append(paramHoraHasta);
		queryString.append("'), 'HH24:mi') ");
		queryString.append("and to_date(decode(horahasta,'24:00','23:59',horahasta), 'HH24:mi') > to_date(decode('");
		queryString.append(paramHoraDesde);
		queryString.append(R_CONST_1);
		queryString.append(paramHoraDesde);
		queryString.append("'),");
		queryString.append(" 'HH24:mi'))");
		
		String sql = queryString.toString();
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setInteger(0, servicioId);
		query.setInteger(1, organismoId);
		
		Integer resultado = ((BigDecimal) query.uniqueResult()).intValue();
		return (resultado > 0)? 2 : 0;
	}

	
	
	/**
	 * @param tipo
	 * @param dia
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 */
	private Integer comprobarServidorPorDefecto(Integer tipo, String dia, String paramHoraHasta, String paramHoraDesde) {
		String sql = "select count(*) from TBL_SERVIDORES where tipo = ? and pordefecto = 1";
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setInteger(0, tipo);
		Integer res = ((BigDecimal) query.uniqueResult()).intValue();
		if (res > 0){
			
			String sqlDia = dia + R_CONST_12; 
			StringBuilder queryString = new StringBuilder();
			queryString.append("select count(*) from TBL_PLANIFICACIONES where servidorid in (select servidorid from TBL_SERVIDORES where tipo = " + tipo + R_CONST_8
					+ "pordefecto = 1) and (eliminado is null or eliminado = 'N') and activo = 1 and " + sqlDia + R_CONST_2
					+ R_CONST_7+paramHoraDesde+R_CONST_3+paramHoraDesde+R_CONST_4+paramHoraHasta+R_CONST_16
							+ "AND horahasta >= trim('"+paramHoraHasta+R_CONST_14);
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			res = ((BigDecimal) query.uniqueResult()).intValue();
			return (res == 0)? 0 : 1;
		}
		return 0;
	}

	/**
	 * @param dia
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @param servicioId
	 */
	private Integer comprobarServicioTieneServidor(String dia, String paramHoraHasta, String paramHoraDesde,
			Integer servicioId) {
		
		String sql = " select count(servicioId) from TBL_SERVIDORES_SERVICIOS where servicioid = ?";
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setInteger(0, servicioId);
		Integer res = ((BigDecimal) query.uniqueResult()).intValue();
		if (res > 0){
			String sqlDia = dia + R_CONST_12; 
			StringBuilder queryString = new StringBuilder();
			queryString.append("select count(*) from TBL_PLANIFICACIONES where servidorid in (select servidorId "
					+ "from tbl_servidores_servicios where servicioid = "+servicioId+") and (eliminado is null or eliminado = 'N') and activo = 1 "
					+ R_CONST_2 + sqlDia +R_CONST_6+paramHoraDesde+R_CONST_3+paramHoraDesde+"') AND "
					+ R_CONST_7+paramHoraHasta+R_CONST_3+paramHoraHasta+R_CONST_14);
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
			res = ((BigDecimal) query.uniqueResult()).intValue();
			if (res == 0){
				return 0;
			}else{
				return 1;
			}
		}else{
			return null;
		}
	}

	/**
	 * @param dia
	 * @param servidorId
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @return 
	 */
	private Integer servidorPropioAsignadoServicio(String dia, Integer servidorId, String paramHoraHasta,
			String paramHoraDesde) {
		String sqlDia = dia + R_CONST_12; 
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*) from TBL_PLANIFICACIONES where servidorid = "+ servidorId +" and servicioid is null"
				+ R_CONST_13 + sqlDia + R_CONST_6+paramHoraDesde+R_CONST_16
				+ " AND horahasta >= trim('"+paramHoraDesde+R_CONST_4+paramHoraHasta+R_CONST_3+paramHoraHasta+R_CONST_14);
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
		Integer resultado = ((BigDecimal) query.uniqueResult()).intValue();
		return (resultado == 0)? 0 : 1;
	}

	/**
	 * @param planificacionId
	 * @param dia
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @param servicioId
	 * @return
	 */
	private Integer comprobarSolapaPlanificacionServicio(Long planificacionId, String dia, String paramHoraHasta,
			String paramHoraDesde, Integer servicioId) {
		String sqlDia = dia + R_CONST_12; 
		String sqlPlanificacion = (null == planificacionId)? R_CONST_9 : R_CONST_11+planificacionId;
		StringBuilder queryString = new StringBuilder();
		queryString.append(R_CONST_15 + servicioId + R_CONST_8
				+ sqlDia + R_CONST_10 + sqlPlanificacion + " and organismoid is null and (eliminado is null or eliminado = 'N') and activo = 1"
				+ " and ( to_date(horadesde, 'HH24:mi') < to_date(decode('"+paramHoraHasta+R_CONST_1+paramHoraHasta+"'), 'HH24:mi')"
				+ " and to_date(decode(horahasta,'24:00','23:59',horahasta), 'HH24:mi') > to_date(decode('"+paramHoraDesde+R_CONST_1
				+paramHoraDesde+"'), 'HH24:mi'))");
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
		Integer resultado = ((BigDecimal) query.uniqueResult()).intValue();
		return (resultado > 0)? 2 : 0;
	}

	/**
	 * @param planificacionId
	 * @param dia
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @param servidor
	 * @return
	 */
	private Integer comprobarSolapaPlanificacionServidor(Long planificacionId, String dia, String paramHoraHasta,
			String paramHoraDesde, Integer servidorId) {
		String sqlDia = dia + R_CONST_12; 
		String sqlPlanificacion = (null == planificacionId)? R_CONST_9 : R_CONST_11 + planificacionId;
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*) from view_planificaciones where servidorid = " + servidorId + " and servicioid is null "
				+ R_CONST_2 + sqlDia + R_CONST_10 + sqlPlanificacion + R_CONST_13
				+ " to_date(horadesde, 'HH24:mi') < to_date(decode('"+paramHoraHasta+R_CONST_1+paramHoraHasta+"'), 'HH24:mi') and "
				+ "to_date(decode(horahasta,'24:00','23:59',horahasta), 'HH24:mi') > to_date(decode('"+paramHoraDesde+R_CONST_1+paramHoraDesde+"'), "
				+ "'HH24:mi')");
						
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(queryString.toString());
		Integer resultado = ((BigDecimal) query.uniqueResult()).intValue();
		return (resultado > 0)? 0 : 1;
	}
		
}
