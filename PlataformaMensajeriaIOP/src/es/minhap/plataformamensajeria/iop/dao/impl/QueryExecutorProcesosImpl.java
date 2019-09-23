package es.minhap.plataformamensajeria.iop.dao.impl; 

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPdpDiputaciones;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorProcesos;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class QueryExecutorProcesosImpl extends HibernateDaoSupport implements QueryExecutorProcesos {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorProcesosImpl.class);
	
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
	public List<TblProcesos> getProcesosPaginado(int size, String order, String column, ProcesosBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = " from TblProcesos o";
			String slqWhere = createWhere(ob, order, column);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			if(size!=-1){
				query.setMaxResults(size);
			}			
			
			return  query.list();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countProcesosPaginado(ProcesosBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select count(o.procesosid) from TblProcesos o ";
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
		

	private String createWhere(ProcesosBean ob, String order, String column) {
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
