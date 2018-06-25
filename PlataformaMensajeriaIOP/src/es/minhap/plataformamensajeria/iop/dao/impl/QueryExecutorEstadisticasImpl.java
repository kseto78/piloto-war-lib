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

	private static final Logger log = LoggerFactory.getLogger(QueryExecutorEstadisticasImpl.class);
	
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

		if (log.isDebugEnabled()) {
			log.debug(UPDATE_START);
		}
		
		List<Object[]> rows = new ArrayList<Object[]>();
		
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
			if(paramListAplicacion.equals("")){
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
			if(paramDocUsuario.equals("")){
				paramDocUsuario = null;
			}
			String paramCodSIA = estadistica.getCodSIA();
			if(paramCodSIA.equals("")){
				paramCodSIA = null;
			}
			String paramCodOrganismo = estadistica.getCodOrganismo();
			if(paramCodOrganismo.equals("")){
				paramCodOrganismo = null;
			}
			String paramCodOrganismoPagador = estadistica.getCodOrganismoPagador();
			if(paramCodOrganismoPagador.equals("")){
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
	        int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
	        int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
	        
	        //Diferencia en meses entre las dos fechas
	        int months = endMes - startMes;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
		    //AGRUPACIONES
		    if (paramAgrupar == 1){
		        querySelect.append("aplicacionid,Aplicacionnombre");
		        queryGroup.append("aplicacionid,Aplicacionnombre");
		        queryOrder.append("Aplicacionnombre");
		        queryJoin.append("right join (select aplicacionid,NOMBRE from view_aplicaciones where 1=1 #queryJoinSelect# ) B on A.aplicacionid= B.aplicacionid");
		    } else if(paramAgrupar == 2){
		        querySelect.append("servidorid,Servidornombre");
		        queryGroup.append("servidorid,Servidornombre");
		        queryOrder.append("Servidornombre");
		        queryJoin.append("right join (select servidorid,NOMBRE from view_servidores where 1=1 #queryJoinSelect#) B on A.servidorid = B.servidorid");
		    } else if(paramAgrupar == 3){
		        querySelect.append("servicioid,Servicionombre");
		        queryGroup.append("servicioid,Servicionombre");
		        queryOrder.append("Servicionombre");
		        queryJoin.append("right join (select servicioid,NOMBRE from view_servicios where 1=1 #queryJoinSelect#) B on A.servicioid = B.servicioid");
		    } else if(paramAgrupar == 4){
		        querySelect.append("canalid,Canalnombre");
		        queryGroup.append("canalid,Canalnombre");
		        queryOrder.append("Canalnombre");
		        queryJoin.append("right join (select canalid,NOMBRE from view_canales where 1=1 #queryJoinSelect#) B on A.canalid = B.canalid");
		    } else if(paramAgrupar == 5){
		        querySelect.append("estadoid,Estadonombre");
		        queryGroup.append("estadoid,Estadonombre");
		        queryOrder.append("Estadonombre");
		        queryJoin.append("right join (select estadoid,NOMBRE from view_estados where 1=1 #queryJoinSelect#) B on A.estadoid = B.estadoid");
		    }
	
		    //VISTAS
		    if (paramVistaID == 1){
		    	querySelect.append(", Anno");
		    	queryGroup.append(", Anno");
		        queryPivot.append("Anno");
		    } else if(paramVistaID == 2){
		    	querySelect.append(", Mes");
		    	queryGroup.append(", Mes");
		        queryPivot.append("Mes");
		    } else if(paramVistaID == 3){
		    	querySelect.append(", DIA");
		    	queryGroup.append(", DIA");
		        queryPivot.append("Dia");
		    }
		    
		    
		    if (paramVistaID == 1){
		        Integer anoinicio = fechaActualCalendar.get(Calendar.YEAR);
		        Integer anofinal = endCalendar.get(Calendar.YEAR);
	
		        while (anoinicio <= anofinal){
	              if (valores.length() != 0){
	            	  valores.append(", ");
	              }
	              valores.append(anoinicio.toString());
	              anoinicio = anoinicio + 1;
		        }
		    } else if(paramVistaID == 2){
	
		    	while (num<=months){
		        	if (valores.length() != 0){
		        		valores.append(", ");
		    		}
		            valores.append("'"
		            		+ fechaActualCalendar.get(Calendar.MONTH)
		            		+ "_"
		            		+ fechaActualCalendar.get(Calendar.YEAR) 
		            		+"'");
		            int month = fechaActualCalendar.get(Calendar.MONTH) + 1;
		            fechaActualCalendar.set(Calendar.MONTH,month);
		            num = num +1;
		        }
		    } else if(paramVistaID == 3){
		        while (fechaActualCalendar.getTime().before(paramHasta)){
		        	if (valores.length() != 0){
		    			valores.append(", ");
		    		}
		        	valores.append("'"
		            		+ fechaActualCalendar.get(Calendar.DAY_OF_MONTH)
		            		+ "_"
		            		+ fechaActualCalendar.get(Calendar.MONTH)
		            		+ "_"
		            		+ fechaActualCalendar.get(Calendar.YEAR) 
		            		+ "'");
		        	fechaActualCalendar.add(Calendar.DAY_OF_YEAR, 1);
					
					num = num +1;
		        }
		    }
	
		    query.append(" SELECT " 
		    		+ querySelect 
		    		+" , sum(1) AS valor FROM view_estadistica WHERE 1=1 ");
		    query.append("AND ULTIMOENVIO>= to_date(' "
		    		+ sdf.format(paramDesde)
		    		+"') AND ULTIMOENVIO < to_date('"
		    		+ sdf.format(paramHasta)
		    		+ "') + 1");
		    
		    //FILTRO SERVICIOS PERFIL USUARIO
		    if(paramListAplicacion != null){
		          queryJoinSelect.append(" AND AplicacionId IN("
		          		+ paramListAplicacion
		          		+ ")");
		    }
	
		    if(paramAplicacionID != 0){
		      query.append(" AND AplicacionId="
		      		+ paramAplicacionID);
	
		      if(paramAgrupar.toString().contains("1") || paramAgrupar.toString().contains("3")){
		        queryJoinSelect.append(" AND AplicacionId="
		        		+ paramAplicacionID);
		      }
		    }
	
		    if(paramServidorID != 0){
		    	query.append(" AND ServidorId="
		    			+ paramServidorID );
		        if(paramAgrupar.toString().contains("2")) {
		        	queryJoinSelect.append(" AND ServidorId="
		        			+paramServidorID);
		        }
	        }
	
		    if(paramCanalID != 0){
		      query.append(" AND CanalId=" 
		    		  +paramCanalID);
		       if(paramAgrupar.toString().contains("3") || paramAgrupar.toString().contains("4")) {
		        queryJoinSelect.append(" AND CanalId="
		        		+paramCanalID);
		       }
		    }
	
		    if(paramServicioID != 0){
		    	query.append(" AND ServicioId="
		    			+paramServicioID);
	
		       if(paramAgrupar.toString().contains("3")) {
		       queryJoinSelect.append(" AND ServicioId="
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
		      query.append(" AND DocUsuario='"
		      		+ paramDocUsuario
		      		+ "'");
		    }
		  
		    //FILTRO CODIGO SIA
		    if(paramCodSIA != null) {
		      query.append(" AND CodSIA='"
		      		+ paramCodSIA
		      		+ "'");
		    }
		  
		    //FILTRO CODIGO ORGANISMO
		    if(paramCodOrganismo != null) {
		      query.append(" AND CodOrganismo='"
		      		+ paramCodOrganismo
		      		+ "'");
		    }
		  
		    //FILTRO CODIGO ORGANISMO PAGADOR
		    if(paramCodOrganismoPagador != null) {
		      query.append(" AND CodOrganismoPagador='"
		      		+ paramCodOrganismoPagador
		      		+ "'");
		    }
	
		    if(paramListAplicacion != null){
		          query.append(" AND AplicacionId IN("
		          		+ paramListAplicacion
		          		+ ")");
		    }
	
		    //AGRUPACION
		    query.append(" group by "
		    		+queryGroup);
		    
		    //ESTADISTICAS CONSOLIDADAS (SOLO PARA ANNOS Y MESES)
		    if (paramVistaID != 3) {
		    
		    	queryEstadisticasCons.append(" SELECT "
		      		+ querySelect
		      		+ ", sum(numtotal) AS valor FROM tbl_estadisticas_cons WHERE 1=1");
		      
				if (paramVistaID == 1) {
					
					while (num<=months){
					if (valoresCons.length() != 0) {
					valoresCons.append(",");
					}
					valoresCons.append("'"
							+ fechaActualCalendar.get(Calendar.MONTH)
							+ "_"
							+ fechaActualCalendar.get(Calendar.YEAR)
							+ "'");
					
		        	fechaActualCalendar.add(Calendar.DAY_OF_YEAR, 1);
					
					num = num +1;
				}	
				queryEstadisticasCons.append(" AND Mes IN ("
						+ valoresCons
						+ ")");
			}
		      
		      if(paramAplicacionID != 0){
		    	  queryEstadisticasCons.append(" AND AplicacionId="
		    	  		+ paramAplicacionID);
		      }
		  
		      if(paramServidorID != 0){
		    	  queryEstadisticasCons.append(" AND ServidorId="
		    	  		+ paramServidorID);
		      }
		  
		      if(paramCanalID != 0){
		    	  queryEstadisticasCons.append(" AND CanalId="
		    	  		+ paramCanalID);
		      }
		  
		      if(paramServicioID != 0){
		    	  queryEstadisticasCons.append(" AND ServicioId="
		    			  + paramServicioID);
		      }
		         
		      // FILTRO ESTADO
		      if(paramEstadoID != 0){
		    	  queryEstadisticasCons.append(queryEstado);
		      }
		      
		      //FILTRO DOC USUARIO
		      if(paramDocUsuario != null) {
		    	  queryEstadisticasCons.append(" AND DocUsuario='"
		    	  		+ paramDocUsuario
		    	  		+ "'") ;
		      }
		  
		      //FILTRO CODIGO SIA
		      if(paramCodSIA != null) {
		    	  queryEstadisticasCons.append(" AND CodSIA='"
		    	  		+ paramCodSIA
		    	  		+ "'");
		      }
		  
		      //FILTRO CODIGO ORGANISMO
		      if(paramCodOrganismo != null) {
		        queryEstadisticasCons.append(" AND CodOrganismo='"
		        		+ paramCodOrganismo
		        		+ "'");
		      }
		  
		      //FILTRO CODIGO ORGANISMO PAGADOR
		      if(paramCodOrganismoPagador != null) {
		        queryEstadisticasCons .append(" AND CodOrganismoPagador='"
		        		+ paramCodOrganismoPagador
		        		+ "'");
		      }
		  
		      if(paramListAplicacion != null){
		          queryEstadisticasCons.append(" AND AplicacionId IN("
		          		+ paramListAplicacion
		          		+ ")");
		      }
		  
		      //AGRUPACION
		      queryEstadisticasCons .append(" group by "
		    		  + queryGroup);
		      
		      //UNION ALL
		      query.append(" union all "
		      		+ queryEstadisticasCons);
		      
		    }
		    
		    queryJoin.replace(queryJoin.indexOf("#"),
		    		queryJoin.lastIndexOf("#") + 1,
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
			
			if (log.isDebugEnabled()) {
				log.debug(UPDATE_END);
			}
		
		} catch (Exception e) {
			log.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	
		return rows;
	}
}
