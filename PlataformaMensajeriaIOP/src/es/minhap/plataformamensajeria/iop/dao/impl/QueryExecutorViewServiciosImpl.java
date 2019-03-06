package es.minhap.plataformamensajeria.iop.dao.impl;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.beans.entity.DestinatariosMensajesBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewServicios;
import es.minhap.sim.model.ViewServicios;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_SERVIDORES
 * 
 * @author everis
 *
 */
@Service("QueryExecutorViewServiciosImpl")
@Transactional
public class QueryExecutorViewServiciosImpl extends HibernateDaoSupport implements QueryExecutorViewServicios {

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorViewServiciosImpl.class);
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewServicios> getCanalesDistintos(int aplicacionId) {
		try {
			
			List<ViewServicios> res = new ArrayList<>();
// 			String sql = "select  DISTINCT(TBL_SERVICIOS.canalid), TBL_CANALES.NOMBRE from TBL_SERVICIOS  INNER JOIN TBL_CANALES ON TBL_SERVICIOS.CANALID = TBL_CANALES.CANALID WHERE TBL_SERVICIOS.APLICACIONID = :aplicacionId";
			String sql = "select  DISTINCT(canalid), canalNombre from VIEW_SERVICIOS  WHERE APLICACIONID = :aplicacionId";
			Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setInteger("aplicacionId", aplicacionId);

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
			    ViewServicios vw = new ViewServicios();
			    BigDecimal bd = null;
			    bd = (BigDecimal) row[0];
			    vw.setCanalid(bd.longValue());
			    vw.setCanalnombre((String) row[1]);
			    res.add(vw);
			}
		
			return res;
			 			
		} catch (Exception e) {		
			throw new ApplicationException(e);
		}
	}
}


