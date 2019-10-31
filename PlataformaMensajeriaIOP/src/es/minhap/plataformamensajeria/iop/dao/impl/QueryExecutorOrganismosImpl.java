package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.beans.OrganismoBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;
import es.minhap.sim.model.TblOrganismos;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class QueryExecutorOrganismosImpl extends HibernateDaoSupport implements QueryExecutorOrganismos {

	protected static final String R_CONST_1 = "','dd/mm/yyyy HH24:MI:SS')";

	protected static final String R_CONST_2 = "'";

	protected static final String R_CONST_3 = "unchecked";

	protected static final String R_CONST_4 = "dd/MM/yyyy HH:mm:ss";

	protected static final String R_CONST_5 = "select o.organismoid from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') and ";

	protected static final String R_CONST_6 = "%')";

	protected static final String R_CONST_7 = "dir3";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorOrganismosImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public boolean organismoActivoEnServicio(Integer servicioId, String organismoPagador) {
		String codOrganismo = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 as dir3"
							+ "  FROM TBL_ORGANISMOS_SERVICIO, TBL_ORGANISMOS"
							+ " WHERE (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) and "
							+ "TBL_ORGANISMOS_SERVICIO.SERVICIOID = ? and UPPER(TBL_ORGANISMOS.DIR3) = UPPER(?)"
							+ " and TBL_ORGANISMOS.ACTIVO = 1 ");
			query.setInteger(0, servicioId);
			query.setString(1, organismoPagador);
			codOrganismo = (String) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return null != codOrganismo && !codOrganismo.isEmpty();
	}

	@Override
	@Transactional
	public Integer checkActiveApplication(Integer servicioId) {
		BigDecimal aplicacionActiva = BigDecimal.ZERO;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT AP.ACTIVO from tbl_aplicaciones AP, TBL_SERVICIOS S WHERE S.APLICACIONID = AP.APLICACIONID AND S.SERVICIOID= ?");
			query.setInteger(0, servicioId);
			aplicacionActiva = (BigDecimal) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != aplicacionActiva)? aplicacionActiva.intValue() : 0;
	}
	
	@Override
	@Transactional
	public boolean asociadoOrganismoServicio(Integer servicioId, String organismoPagador) {
		String codOrganismo = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 "
					+ "FROM TBL_SERVICIOS, TBL_ORGANISMOS, TBL_ORGANISMOS_SERVICIO "
					+ "WHERE (TBL_ORGANISMOS_SERVICIO.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
					+ "AND (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
					+ "AND TBL_SERVICIOS.SERVICIOID = ? AND TBL_ORGANISMOS.DIR3 = ?");
			query.setInteger(0, servicioId);
			query.setString(1, organismoPagador);
			codOrganismo = (String) query.uniqueResult();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_END);
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return null != codOrganismo && !codOrganismo.isEmpty();
	}

	@SuppressWarnings(R_CONST_3)
	@Override
	public List<TblOrganismos> getOrganismosPaginado(int start, int size, String order, String column, OrganismoBean ob) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = " from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S')  ";
			String slqWhere = createWhere(ob, order, column);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			if(size!=-1){
				query.setMaxResults(size);
			}
			query.setFirstResult(start);
			
			return  query.list();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public List<TblOrganismos> getOrganismosByPdp(long idPdpDiputaciones) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = " from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') AND o.idPdpDiputaciones = ?";

			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setLong(0, idPdpDiputaciones);
			return  query.list();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countOrganismosPaginado(OrganismoBean ob) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(o.organismoid) from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S')  ";
			String slqWhere = createWhere(ob, null, null);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
						
			return  ((Long)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public List<String> getListAutocomplete(String term) {
		try {
			List<String> res = new ArrayList<>();
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			StringBuilder queryString = new StringBuilder();
			queryString.append("select o.dir3, o.nombre from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') and activo = 1 and ");
			queryString.append(" (o.dir3 like '%");
			queryString.append(term);
			queryString.append("%' or o.nombre like '%");
			queryString.append(term);
			queryString.append(R_CONST_6);
						
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString.toString());
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				String s = (String) row[0] + " | " + (String) row[1];
				res.add(s);
			}
			return res;
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public Integer getOrganismoIdByDir3(String search) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = R_CONST_5
					+ "o.dir3 = :dir3 and o.activo = 1";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString(R_CONST_7, search);
			List<Long> lista = query.list();
			
			if (!lista.isEmpty()){
				return lista.get(0).intValue();
			}else{
				return null;
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public Integer getOrganismoIdByDir3SoloEliminado(String search) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = R_CONST_5
					+ "o.dir3 = :dir3";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString(R_CONST_7, search);
			List<Long> lista = query.list();
			
			if (!lista.isEmpty()){
				return lista.get(0).intValue();
			}else{
				return 0;
			}
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_3)
	@Override
	public List<TblOrganismos> getOrganismosHijos(String dir3) {
		try {			
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "from TblOrganismos o where o.codUnidadSuperior = ?";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
									
			query.setString(0, dir3);
			
			return  query.list();
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	private String createWhere(OrganismoBean ob, String order, String column) {
		StringBuilder sb = new StringBuilder();

		if (null != ob.getDir3() && !ob.getDir3().isEmpty()) {
			sb.append(" and o.dir3 like '%" + ob.getDir3() + "%'");
		}
		if (null != ob.getNombre() && !ob.getNombre().isEmpty()) {
			sb.append(" and Upper(o.nombre) like UPPER('%" + ob.getNombre() + R_CONST_6);
		}
		if (null != ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()!=null) {
			DateFormat df = new SimpleDateFormat(R_CONST_4);
			String fechaCreacionDesde = df.format(ob.getFechaCreacionDesde());
			String fechaCreacionHasta= df.format(ob.getFechaCreacionHasta());
			sb.append(" and o.fechacreacion between to_date('" + fechaCreacionDesde+ "','dd/mm/yyyy HH24:MI:SS') and to_date('"+fechaCreacionHasta+ R_CONST_1);
		}
		
		if (null != ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()==null) {
			DateFormat df = new SimpleDateFormat(R_CONST_4);
			String fechaCreacionDesde = df.format(ob.getFechaCreacionDesde());
			sb.append(" and o.fechacreacion >= to_date('" + fechaCreacionDesde+ R_CONST_1);
		}
		
		if (null == ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()!=null) {
			DateFormat df = new SimpleDateFormat(R_CONST_4);
			String fechaCreacionHasta = df.format(ob.getFechaCreacionHasta());
			sb.append(" and o.fechacreacion <= to_date('" + fechaCreacionHasta+ R_CONST_1);
		}
		
		if (null != ob.getEstado() && !ob.getEstado().isEmpty()) {
			sb.append(" and o.estado = '" + ob.getEstado() + R_CONST_2);
		}
		
		if (null != ob.getAsociadosServicio() && ob.getAsociadosServicio()) {
			sb.append(" and  o.organismoid in (select tblOrganismos.organismoid from TblOrganismosServicio)");
		} else {
			sb.append(" and  o.organismoid not in (select tblOrganismos.organismoid from TblOrganismosServicio)");
		}

		if (null != column) {
			getOrder(order, column, sb);
		}

		return sb.toString();
	}
	
	/**
	 * @param order
	 * @param column
	 * @param sb
	 */
	private void getOrder(String order, String column, StringBuilder sb) {
		String orden;
		if (order == null || "1".equals(order)){
			orden = " ASC";
		} else {
			orden = " DESC";
		}
		sb.append(" ORDER BY " + column + orden);
	}


}
