package es.minhap.plataformamensajeria.iop.dao.impl;

import java.text.SimpleDateFormat;
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
import es.minhap.plataformamensajeria.iop.beans.EstadisticasBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorEstadisticas;

/**
 * Query Executor encargado de lanzar las consultas especificas para la tabla TBL_LOTESENVIOS
 * 
 * @author everis
 *
 */
@Service("QueryExecutorEstadisticasImpl")
public class QueryExecutorEstadisticasImpl extends HibernateDaoSupport implements QueryExecutorEstadisticas {

	protected static final String R_CONST_1 = " AND CodOrganismo='";

	protected static final String R_CONST_2 = ", DIA";

	protected static final String R_CONST_3 = " AND CodOrganismoPagador='";

	protected static final String R_CONST_4 = "servicioid,Servicionombre";

	protected static final String R_CONST_5 = "#";

	protected static final String R_CONST_6 = "'";

	protected static final String R_CONST_7 = " AND DocUsuario='";

	protected static final String R_CONST_8 = ")";

	protected static final String R_CONST_9 = " AND ServidorId=";

	protected static final String R_CONST_10 = "3";

	protected static final String R_CONST_11 = " AND CanalId=";

	protected static final String R_CONST_12 = "canalid,Canalnombre";

	protected static final String R_CONST_13 = ", Anno";

	protected static final String R_CONST_14 = "aplicacionid,Aplicacionnombre";

	protected static final String R_CONST_15 = ", Mes";

	protected static final String R_CONST_16 = " AND AplicacionId=";

	protected static final String R_CONST_17 = "servidorid,Servidornombre";

	protected static final String R_CONST_18 = " AND CodSIA='";

	protected static final String R_CONST_19 = " group by ";

	protected static final String R_CONST_20 = " SELECT ";

	protected static final String R_CONST_21 = "_";

	protected static final String R_CONST_22 = " AND AplicacionId IN(";

	protected static final String R_CONST_23 = " AND ServicioId=";

	protected static final String R_CONST_24 = "estadoid,Estadonombre";

	protected static final String R_CONST_25 = ", ";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorEstadisticasImpl.class);
	
	private static final String UPDATE_END= "search - end";
	
	private static final String UPDATE_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getEstadisticas(EstadisticasBean estadistica, StringBuffer aplicaciones)  {

		if (LOG.isDebugEnabled()) {
			LOG.debug(UPDATE_START);
		}
		
		List<Object[]> rows = new ArrayList<>();
		
		try{
			StringBuffer query = new StringBuffer();
			StringBuffer queryJoin = new StringBuffer();
			StringBuffer querySelect = new StringBuffer();
			StringBuffer queryGroup = new StringBuffer();
			StringBuffer queryOrder = new StringBuffer();
			StringBuffer queryPivot = new StringBuffer();
			StringBuffer queryJoinSelect = new StringBuffer();
			StringBuffer queryEstado = new StringBuffer();
			StringBuffer queryEstadisticasCons = new StringBuffer();
			
			Integer paramAgrupar = estadistica.getAgruparId();
			Integer paramVistaID = estadistica.getVistaId();
			Date paramDesde = estadistica.getFechaDesde();
			Date paramHasta = estadistica.getFechaHasta();
			String paramListAplicacion = aplicaciones.toString();
			if("".equals(paramListAplicacion)){
				paramListAplicacion = null;
			}
			Integer paramAplicacionID = estadistica.getAplicacionId();
			Integer paramServidorID = estadistica.getServidorId();
			Integer paramCanalID = estadistica.getCanalId();
			Integer paramServicioID = estadistica.getServicioId();
			if(paramServicioID == null){
				paramServicioID = 0;
			}
			Integer paramEstadoID = estadistica.getEstadoId();
			String paramDocUsuario = estadistica.getDocUsuario();
			if("".equals(paramDocUsuario)){
				paramDocUsuario = null;
			}
			String paramCodSIA = estadistica.getCodSIA();
			if("".equals(paramCodSIA)){
				paramCodSIA = null;
			}
			String paramCodOrganismo = estadistica.getCodOrganismo();
			if("".equals(paramCodOrganismo)){
				paramCodOrganismo = null;
			}
			String paramCodOrganismoPagador = estadistica.getCodOrganismoPagador();
			if("".equals(paramCodOrganismoPagador)){
				paramCodOrganismoPagador = null;
			}
			
			Integer num = 0;
			Calendar fechaActualCalendar = Calendar.getInstance();
			fechaActualCalendar.setTime(paramDesde);
			
			StringBuffer valores = new StringBuffer();
			StringBuffer valoresCons = new StringBuffer();
			
			//Calculamos la diferencia en meses de las fechas
	    	Calendar startCalendar = Calendar.getInstance();
	    	Calendar endCalendar = Calendar.getInstance();
	    	
	    	startCalendar.setTime(paramDesde);
	        endCalendar.setTime(paramHasta);
	        
	        //Cálculo de meses para las fechas de inicio y finalización
	        int startMes = startCalendar.get(Calendar.YEAR) * 12 + startCalendar.get(Calendar.MONTH);
	        int endMes = endCalendar.get(Calendar.YEAR) * 12 + endCalendar.get(Calendar.MONTH);
	        
	        //Diferencia en meses entre las dos fechas
	        int months = endMes - startMes;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
		    //AGRUPACIONES
		    if (paramAgrupar == 1){
		        querySelect.append(R_CONST_14);
		        queryGroup.append(R_CONST_14);
		        queryOrder.append("Aplicacionnombre");
		        queryJoin.append("right join (select aplicacionid,NOMBRE from view_aplicaciones where 1=1 #queryJoinSelect# ) B on A.aplicacionid= B.aplicacionid");
		    } else if(paramAgrupar == 2){
		        querySelect.append(R_CONST_17);
		        queryGroup.append(R_CONST_17);
		        queryOrder.append("Servidornombre");
		        queryJoin.append("right join (select servidorid,NOMBRE from view_servidores where 1=1 #queryJoinSelect#) B on A.servidorid = B.servidorid");
		    } else if(paramAgrupar == 3){
		        querySelect.append(R_CONST_4);
		        queryGroup.append(R_CONST_4);
		        queryOrder.append("Servicionombre");
		        queryJoin.append("right join (select servicioid,NOMBRE from view_servicios where 1=1 #queryJoinSelect#) B on A.servicioid = B.servicioid");
		    } else if(paramAgrupar == 4){
		        querySelect.append(R_CONST_12);
		        queryGroup.append(R_CONST_12);
		        queryOrder.append("Canalnombre");
		        queryJoin.append("right join (select canalid,NOMBRE from view_canales where 1=1 #queryJoinSelect#) B on A.canalid = B.canalid");
		    } else if(paramAgrupar == 5){
		        querySelect.append(R_CONST_24);
		        queryGroup.append(R_CONST_24);
		        queryOrder.append("Estadonombre");
		        queryJoin.append("right join (select estadoid,NOMBRE from view_estados where 1=1 #queryJoinSelect#) B on A.estadoid = B.estadoid");
		    }
	
		    //VISTAS
		    if (paramVistaID == 1){
		    	querySelect.append(R_CONST_13);
		    	queryGroup.append(R_CONST_13);
		        queryPivot.append("Anno");
		    } else if(paramVistaID == 2){
		    	querySelect.append(R_CONST_15);
		    	queryGroup.append(R_CONST_15);
		        queryPivot.append("Mes");
		    } else if(paramVistaID == 3){
		    	querySelect.append(R_CONST_2);
		    	queryGroup.append(R_CONST_2);
		        queryPivot.append("Dia");
		    }
		    
		    
		    if (paramVistaID == 1){
		        Integer anoinicio = fechaActualCalendar.get(Calendar.YEAR);
		        Integer anofinal = endCalendar.get(Calendar.YEAR);
	
		        while (anoinicio <= anofinal){
	              if (valores.length() != 0){
	            	  valores.append(R_CONST_25);
	              }
	              valores.append(anoinicio.toString());
	              anoinicio = anoinicio + 1;
		        }
		    } else if(paramVistaID == 2){
	
		    	while (num<=months){
		        	if (valores.length() != 0){
		        		valores.append(R_CONST_25);
		    		}
		            valores.append(R_CONST_6
		            		+ fechaActualCalendar.get(Calendar.MONTH)
		            		+ R_CONST_21
		            		+ fechaActualCalendar.get(Calendar.YEAR) 
		            		+R_CONST_6);
		            int month = fechaActualCalendar.get(Calendar.MONTH) + 1;
		            fechaActualCalendar.set(Calendar.MONTH,month);
		            num = num +1;
		        }
		    } else if(paramVistaID == 3){
		        while (fechaActualCalendar.getTime().before(paramHasta)){
		        	if (valores.length() != 0){
		    			valores.append(R_CONST_25);
		    		}
		        	valores.append(R_CONST_6
		            		+ fechaActualCalendar.get(Calendar.DAY_OF_MONTH)
		            		+ R_CONST_21
		            		+ fechaActualCalendar.get(Calendar.MONTH)
		            		+ R_CONST_21
		            		+ fechaActualCalendar.get(Calendar.YEAR) 
		            		+ R_CONST_6);
		        	fechaActualCalendar.add(Calendar.DAY_OF_YEAR, 1);
					
					num = num +1;
		        }
		    }
	
		    query.append(R_CONST_20 
		    		+ querySelect 
		    		+" , sum(1) AS valor FROM view_estadistica WHERE 1=1 ");
		    query.append("AND ULTIMOENVIO>= to_date(' "
		    		+ sdf.format(paramDesde)
		    		+"') AND ULTIMOENVIO < to_date('"
		    		+ sdf.format(paramHasta)
		    		+ "') + 1");
		    
		    //FILTRO SERVICIOS PERFIL USUARIO
		    if(paramListAplicacion != null){
		          queryJoinSelect.append(R_CONST_22
		          		+ paramListAplicacion
		          		+ R_CONST_8);
		    }
	
		    if(paramAplicacionID != 0){
		      query.append(R_CONST_16
		      		+ paramAplicacionID);
	
		      if(paramAgrupar.toString().contains("1") || paramAgrupar.toString().contains(R_CONST_10)){
		        queryJoinSelect.append(R_CONST_16
		        		+ paramAplicacionID);
		      }
		    }
	
		    if(paramServidorID != 0){
		    	query.append(R_CONST_9
		    			+ paramServidorID );
		        if(paramAgrupar.toString().contains("2")) {
		        	queryJoinSelect.append(R_CONST_9
		        			+paramServidorID);
		        }
	        }
	
		    if(paramCanalID != 0){
		      query.append(R_CONST_11 
		    		  +paramCanalID);
		       if(paramAgrupar.toString().contains(R_CONST_10) || paramAgrupar.toString().contains("4")) {
		        queryJoinSelect.append(R_CONST_11
		        		+paramCanalID);
		       }
		    }
	
		    if(paramServicioID != 0){
		    	query.append(R_CONST_23
		    			+paramServicioID);
	
		       if(paramAgrupar.toString().contains(R_CONST_10)) {
		       queryJoinSelect.append(R_CONST_23
		    		   +paramServicioID);
		       }
		    }
	
		    //FILTRO ESTADO
		    if(paramEstadoID != 0){
		      if (paramEstadoID == 1) {
		        queryEstado.append(" AND EstadoId = 1 ");
		      } else if(paramEstadoID == 2) {
		        queryEstado.append(" AND EstadoId = 2 ");
		      } else if(paramEstadoID == 3) {
		        queryEstado.append(" AND EstadoId = 3 ");
		      } else if(paramEstadoID == 4) {
		        queryEstado.append(" AND EstadoId = 4 ");
		      } else if(paramEstadoID == 5) {
		        queryEstado.append(" AND EstadoId in (2,3,6) ");
		      } else if(paramEstadoID == 6) {
		        queryEstado.append(" AND EstadoId = 6 ");
		      }
	
		      query.append(queryEstado);
	
		      if(paramAgrupar.toString().contains("5")) {
		        queryJoinSelect.append(queryEstado);
		      }
		    }
		    
		    //FILTRO DOC USUARIO
		    if(paramDocUsuario != null) {
		      query.append(R_CONST_7
		      		+ paramDocUsuario
		      		+ R_CONST_6);
		    }
		  
		    //FILTRO CODIGO SIA
		    if(paramCodSIA != null) {
		      query.append(R_CONST_18
		      		+ paramCodSIA
		      		+ R_CONST_6);
		    }
		  
		    //FILTRO CODIGO ORGANISMO
		    if(paramCodOrganismo != null) {
		      query.append(R_CONST_1
		      		+ paramCodOrganismo
		      		+ R_CONST_6);
		    }
		  
		    //FILTRO CODIGO ORGANISMO PAGADOR
		    if(paramCodOrganismoPagador != null) {
		      query.append(R_CONST_3
		      		+ paramCodOrganismoPagador
		      		+ R_CONST_6);
		    }
	
		    if(paramListAplicacion != null){
		          query.append(R_CONST_22
		          		+ paramListAplicacion
		          		+ R_CONST_8);
		    }
	
		    //AGRUPACION
		    query.append(R_CONST_19
		    		+queryGroup);
		    
		    //ESTADISTICAS CONSOLIDADAS (SOLO PARA ANNOS Y MESES)
		    if (paramVistaID != 3) {
		    
		    	queryEstadisticasCons.append(R_CONST_20
		      		+ querySelect
		      		+ ", sum(numtotal) AS valor FROM tbl_estadisticas_cons WHERE 1=1");
		      
				if (paramVistaID == 1) {
					
					while (num<=months){
					if (valoresCons.length() != 0) {
					valoresCons.append(",");
					}
					valoresCons.append(R_CONST_6
							+ fechaActualCalendar.get(Calendar.MONTH)
							+ R_CONST_21
							+ fechaActualCalendar.get(Calendar.YEAR)
							+ R_CONST_6);
					
		        	fechaActualCalendar.add(Calendar.DAY_OF_YEAR, 1);
					
					num = num +1;
				}	
				queryEstadisticasCons.append(" AND Mes IN ("
						+ valoresCons
						+ R_CONST_8);
			}
		      
		      if(paramAplicacionID != 0){
		    	  queryEstadisticasCons.append(R_CONST_16
		    	  		+ paramAplicacionID);
		      }
		  
		      if(paramServidorID != 0){
		    	  queryEstadisticasCons.append(R_CONST_9
		    	  		+ paramServidorID);
		      }
		  
		      if(paramCanalID != 0){
		    	  queryEstadisticasCons.append(R_CONST_11
		    	  		+ paramCanalID);
		      }
		  
		      if(paramServicioID != 0){
		    	  queryEstadisticasCons.append(R_CONST_23
		    			  + paramServicioID);
		      }
		         
		      // FILTRO ESTADO
		      if(paramEstadoID != 0){
		    	  queryEstadisticasCons.append(queryEstado);
		      }
		      
		      //FILTRO DOC USUARIO
		      if(paramDocUsuario != null) {
		    	  queryEstadisticasCons.append(R_CONST_7
		    	  		+ paramDocUsuario
		    	  		+ R_CONST_6) ;
		      }
		  
		      //FILTRO CODIGO SIA
		      if(paramCodSIA != null) {
		    	  queryEstadisticasCons.append(R_CONST_18
		    	  		+ paramCodSIA
		    	  		+ R_CONST_6);
		      }
		  
		      //FILTRO CODIGO ORGANISMO
		      if(paramCodOrganismo != null) {
		        queryEstadisticasCons.append(R_CONST_1
		        		+ paramCodOrganismo
		        		+ R_CONST_6);
		      }
		  
		      //FILTRO CODIGO ORGANISMO PAGADOR
		      if(paramCodOrganismoPagador != null) {
		        queryEstadisticasCons .append(R_CONST_3
		        		+ paramCodOrganismoPagador
		        		+ R_CONST_6);
		      }
		  
		      if(paramListAplicacion != null){
		          queryEstadisticasCons.append(R_CONST_22
		          		+ paramListAplicacion
		          		+ R_CONST_8);
		      }
		  
		      //AGRUPACION
		      queryEstadisticasCons .append(R_CONST_19
		    		  + queryGroup);
		      
		      //UNION ALL
		      query.append(" union all "
		      		+ queryEstadisticasCons);
		      
		    }
		    
		    queryJoin.replace(queryJoin.indexOf(R_CONST_5),
		    		queryJoin.lastIndexOf(R_CONST_5) + 1,
		    		queryJoinSelect.toString());
		    
	
		    StringBuffer queryFrom = new StringBuffer("SELECT * FROM ("
		    		+ query
		    		+ ") datos ");
	
			StringBuffer queryFinal = new StringBuffer(" select B.NOMBRE, A.* from ("
					+ queryFrom
					+ ") A "
					+ queryJoin
					+ " order by b.nombre");
			
			SQLQuery queryExecute = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryFinal.toString());
			
			rows = queryExecute.list();
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(UPDATE_END);
			}
		
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	
		return rows;
	}
}
