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

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorOrganismosImpl.class);
	
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
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 as dir3"
							+ "  FROM TBL_ORGANISMOS_SERVICIO, TBL_ORGANISMOS"
							+ " WHERE (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) and "
							+ "TBL_ORGANISMOS_SERVICIO.SERVICIOID = "+servicioId+" and UPPER(TBL_ORGANISMOS.DIR3) = UPPER('"+organismoPagador+"')"
							+ " and TBL_ORGANISMOS.ACTIVO = 1 ");
			codOrganismo = (String) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != codOrganismo && !codOrganismo.isEmpty())? true: false;
	}

	@Override
	@Transactional
	public Integer checkActiveApplication(Integer servicioId) {
		BigDecimal aplicacionActiva = new BigDecimal("0");
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT AP.ACTIVO from tbl_aplicaciones AP, TBL_SERVICIOS S WHERE S.APLICACIONID = AP.APLICACIONID AND S.SERVICIOID=" + servicioId);
			aplicacionActiva = (BigDecimal) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != aplicacionActiva)? aplicacionActiva.intValue() : 0;
	}
	
	@Override
	@Transactional
	public boolean asociadoOrganismoServicio(Integer servicioId, String organismoPagador) {
		String codOrganismo = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			SQLQuery query	= getSessionFactory().getCurrentSession().createSQLQuery(
					"SELECT TBL_ORGANISMOS.DIR3 "
					+ "FROM TBL_SERVICIOS, TBL_ORGANISMOS, TBL_ORGANISMOS_SERVICIO "
					+ "WHERE (TBL_ORGANISMOS_SERVICIO.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
					+ "AND (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
					+ "AND TBL_SERVICIOS.SERVICIOID = '" + servicioId+"' AND TBL_ORGANISMOS.DIR3 = '" + organismoPagador+"'");
			codOrganismo = (String) query.uniqueResult();
			if (log.isDebugEnabled()) {
				log.debug(LOG_END);
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
		return (null != codOrganismo && !codOrganismo.isEmpty())? true: false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblOrganismos> getOrganismosPaginado(int start, int size, String order, String column, OrganismoBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
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
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblOrganismos> getOrganismosByPdp(long idPdpDiputaciones) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = " from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') AND o.idPdpDiputaciones ="+idPdpDiputaciones;

			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			return  query.list();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countOrganismosPaginado(OrganismoBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select count(o.organismoid) from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S')  ";
			String slqWhere = createWhere(ob, null, null);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
						
			return  ((Long)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListAutocomplete(String term) {
		try {
			List<String> res = new ArrayList<>();
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select o.dir3, o.nombre from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') and activo = 1 and "
					+ " (o.dir3 like '%" + term + "%' or o.nombre like '%" + term + "%')";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			List<Object[]> rows = query.list();
			
			for (Object[] row : rows) {
				String s = (String) row[0] + " | " + (String) row[1];
				res.add(s);
			}
			return res;
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getOrganismoIdByDir3(String search) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select o.organismoid from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') and "
					+ "o.dir3 = :dir3 and o.activo = 1";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString("dir3", search);
			List<Long> lista = query.list();
			
			if (!lista.isEmpty()){
				return lista.get(0).intValue();
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getOrganismoIdByDir3SoloEliminado(String search) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select o.organismoid from TblOrganismos o where (o.eliminado is null or o.eliminado != 'S') and "
					+ "o.dir3 = :dir3";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString("dir3", search);
			List<Long> lista = query.list();
			
			if (!lista.isEmpty()){
				return lista.get(0).intValue();
			}else{
				return 0;
			}
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblOrganismos> getOrganismosHijos(String dir3) {
		try {			
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "from TblOrganismos o where o.codUnidadSuperior ='"+dir3+"'";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
									
			return  query.list();
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}

	private String createWhere(OrganismoBean ob, String order, String column) {
		StringBuilder sb = new StringBuilder();

		if (null != ob.getDir3() && ob.getDir3().length() > 0) {
			sb.append(" and o.dir3 like '%" + ob.getDir3() + "%'");
		}
		if (null != ob.getNombre() && ob.getNombre().length() > 0) {
			sb.append(" and Upper(o.nombre) like UPPER('%" + ob.getNombre() + "%')");
		}
		if (null != ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()!=null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String fechaCreacionDesde = df.format(ob.getFechaCreacionDesde());
			String fechaCreacionHasta= df.format(ob.getFechaCreacionHasta());
			sb.append(" and o.fechacreacion between to_date('" + fechaCreacionDesde+ "','dd/mm/yyyy HH24:MI:SS') and to_date('"+fechaCreacionHasta+ "','dd/mm/yyyy HH24:MI:SS')");
		}
		
		if (null != ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()==null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String fechaCreacionDesde = df.format(ob.getFechaCreacionDesde());
			sb.append(" and o.fechacreacion >= to_date('" + fechaCreacionDesde+ "','dd/mm/yyyy HH24:MI:SS')");
		}
		
		if (null == ob.getFechaCreacionDesde() && ob.getFechaCreacionHasta()!=null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String fechaCreacionHasta = df.format(ob.getFechaCreacionHasta());
			sb.append(" and o.fechacreacion <= to_date('" + fechaCreacionHasta+ "','dd/mm/yyyy HH24:MI:SS')");
		}
		
		if (null != ob.getEstado() && ob.getEstado().length() > 0) {
			sb.append(" and o.estado = '" + ob.getEstado() + "'");
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
