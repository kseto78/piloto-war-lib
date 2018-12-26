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
import es.minhap.plataformamensajeria.iop.beans.OrganismoPdpBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismosPdp;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblOrganismosPdp;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class QueryExecutorOrganismosPdpImpl extends HibernateDaoSupport implements QueryExecutorOrganismosPdp {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorOrganismosPdpImpl.class);
	
	private static final String LOG_END= "search - end";
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

				
	@SuppressWarnings("unchecked")
	@Override
	public List<TblOrganismosPdp> getOrganismosPdpPaginado(int start, int size, String order, String column, OrganismoPdpBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = " from TblOrganismosPdp o where (o.eliminado is null or o.eliminado != 'S')  ";
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
	
	@Override
	public Integer countOrganismosPdpPaginado(OrganismoPdpBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select count(o.organismoPdpId) from TblOrganismosPdp o where (o.eliminado is null or o.eliminado != 'S')  ";
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
	public List<String> getOrganismosHijos(String dir3) {
		try {
			List<String> res;
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select o.dir3 from TblOrganismos o where codUnidadSuperior = :dir3";
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString("dir3", dir3);
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

	private String createWhere(OrganismoPdpBean ob, String order, String column) {
		StringBuilder sb = new StringBuilder();
	
		if (null != ob.getNombre() && ob.getNombre().length() > 0) {
			sb.append(" and Upper(o.nombre) like UPPER('%" + ob.getNombre() + "%')");
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
