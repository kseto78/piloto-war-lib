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
import es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPdpDiputaciones;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorProcesos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorProcesosManuales;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.ViewProcesosManuales;

/**
 * 
 * @author everis
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class QueryExecutorProcesosManualesImpl extends HibernateDaoSupport implements QueryExecutorProcesosManuales {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorProcesosManualesImpl.class);
	
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
	public List<ViewProcesosManuales> getProcesosManualesPaginado(int inicio, int size, String order, String column, ProcesosManualesBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = " from ViewProcesosManuales o";
			String slqWhere = createWhere(ob, order, column);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			if(size!=-1){
				query.setMaxResults(size);
			}			
			query.setFirstResult(inicio);
			
			return  query.list();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countProcesosManualesPaginado(ProcesosManualesBean ob) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOG_START);
			}
			String sql = "select count(o.procesosManualesId) from ViewProcesosManuales o ";
			String slqWhere = createWhere(ob, null, null);
			sql = sql + slqWhere;
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
						
			return  ((Long)query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
			

	private String createWhere(ProcesosManualesBean ob, String order, String column) {
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
