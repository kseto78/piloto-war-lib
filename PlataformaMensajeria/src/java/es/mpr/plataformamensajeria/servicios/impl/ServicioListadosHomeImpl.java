package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.EnviosPendientesCanalBean;
import es.mpr.plataformamensajeria.beans.EstadoLotesEnviosBean;
import es.mpr.plataformamensajeria.beans.UsoServidoresBean;
import es.mpr.plataformamensajeria.model.views.ViewEnviosPendientesCanalJPA;
import es.mpr.plataformamensajeria.model.views.ViewEstadoEnviosLotesJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author 
 * 
 */
public class ServicioListadosHomeImpl implements ServicioListadosHome{
	private static final String PROC_USOSERVIDORES = "{call PROC_USOSERVIDORES(?,?,?)}";
	private IPaginationJPADAO jpa;

	protected EntityManager em;
	 
    public EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
	/**
	/**
	 * 
	 * @return Objeto BaseJPADao
	 */
	public IPaginationJPADAO getJpa() {
		return jpa;
	}

	/**
	 * Establece la propiedad jpa
	 * 
	 * @param jpa
	 */
	public void setJpa(IPaginationJPADAO jpa) {
		this.jpa = jpa;
	}

	

	
	
	/**
	 * <p>Convertirmos una lista de CanalJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<EnviosPendientesCanalBean> getListEnviosPendientesBeam
		(List<ViewEnviosPendientesCanalJPA> listJPA) throws BusinessException
	{	
		List<EnviosPendientesCanalBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<EnviosPendientesCanalBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewEnviosPendientesCanalJPA envioPendienteJPA = listJPA.get(indice);
				EnviosPendientesCanalBean envioPendiente =  new EnviosPendientesCanalBean();
			
				try {
					BeanUtils.copyProperties(envioPendiente, envioPendienteJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(envioPendiente);
			}
		}
			
		return result;
	}

	@Override
	public List<EnviosPendientesCanalBean> getEnviosPendientesCanal() throws BusinessException {
		List<ViewEnviosPendientesCanalJPA> listJPA = null;
		try {
			ViewEnviosPendientesCanalJPA criterioJPA = new ViewEnviosPendientesCanalJPA();
			listJPA= jpa.readAll(0,0,criterioJPA);
			List<EnviosPendientesCanalBean> result = getListEnviosPendientesBeam(listJPA);					
			return result;
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	@Override
	public PaginatedList<EstadoLotesEnviosBean> getEstadosLotesEnvios(int start, int size,String anyo, String mes) throws BusinessException {
		ArrayList<EstadoLotesEnviosBean> listaServidores = new ArrayList<EstadoLotesEnviosBean>();
//	 	EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
//	 	EntityManager em=emf.createEntityManager();
	 	String tipoFiltro = "";
	 	String fechaInicio = getFechaInicio(anyo,mes);
	 	String fechaFin = getFechaFin(anyo,mes);
	 	Query query = em.createNamedQuery("selectAllViewEstadoEnviosLotesJPAByMes");
	 	//query.setParameter("fechaInicioMes", fechaInicio);
	 	//query.setParameter("fechaFinMes", fechaFin);
	 	//query.setFirstResult(0);
	 	//query.setMaxResults(10);
	 	List<ViewEstadoEnviosLotesJPA> listJPA  = query.getResultList();
	 	List<EstadoLotesEnviosBean> pageList = getListViewEstadoEnviosLotesBean(listJPA);
		
		//Total de organismos
		//Integer rowcount = getTotalEnvios(fechaInicio,fechaFin,em); 
		
		PaginatedList<EstadoLotesEnviosBean> result = new PaginatedList<EstadoLotesEnviosBean>();
		result.setPageList(pageList);
		result.setTotalList(10);
	 	return result;
	}	
	
	public Integer getTotalEnvios(String fechaInicio,String fechaFin, EntityManager em)	throws BusinessException {
		Integer result = null;
		try
		{
			Query rowCountQuery = em.createNamedQuery("selectAllViewEstadoEnviosLotesJPAByMes_count");
		 	//rowCountQuery.setParameter("fechaInicioMes", fechaInicio);
		 	//rowCountQuery.setParameter("fechaFinMes", fechaFin);
			Long rowcount = (Long) rowCountQuery.getSingleResult();
			result=  (rowcount!=null)?rowcount.intValue():0;
			
		}
		catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getOrganismos");
		}
		return result;
	}
	/**
	 * <p>Convertirmos una lista de ViewServidoresJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<EstadoLotesEnviosBean> getListViewEstadoEnviosLotesBean(List<ViewEstadoEnviosLotesJPA> listJPA) throws BusinessException
	{	
		List<EstadoLotesEnviosBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<EstadoLotesEnviosBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				ViewEstadoEnviosLotesJPA servidorJPA = listJPA.get(indice);
				EstadoLotesEnviosBean servidor =  new EstadoLotesEnviosBean();
			
				try {
					
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}
	private static String getFechaInicio(String anyo,String mes){
		String result = "01/"+mes+"/"+anyo+" 00:00";
		return result;
		
	}
	private static String getFechaFin(String anyo,String mes){
		String result = "";
		Calendar calendar = Calendar.getInstance();
	 	int year = new Integer(anyo);
	 	int month = new Integer(mes)-1;
	 	int date = 1;
   	    calendar.set(year, month, date);
   	    int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
   	    if(maxDay<10){
   	    	result+="0"+maxDay+"/"+mes+"/"+anyo+" 23:59";
   	    }else{
   	    	result+=maxDay+"/"+mes+"/"+anyo+" 23:59";
   	    }
	 	return result;
	}
	@Override
	public List<UsoServidoresBean> getUsoServidoresBean(String anyo, String mes)
			throws BusinessException {
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		List<UsoServidoresBean> listaUsoServidores = new ArrayList<UsoServidoresBean>();
		try{
			
			con = PlataformaMensajeriaUtil.getConexion();
			call = con.prepareCall(PROC_USOSERVIDORES);
			call.setString(1,anyo);
			call.setString(2,mes);
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.executeUpdate();
			rs =  (ResultSet)call.getObject(3);//Obtenemos el cursor
			UsoServidoresBean uso = null;
			while(rs.next()){
				uso = new UsoServidoresBean();
				uso.setServidor(rs.getString("nombre"));
				uso.setNenvios(rs.getInt("nenvios"));
				uso.setTipoServidor(rs.getInt("tipoServidor"));
				listaUsoServidores.add(uso);
			}
			
		}catch(Exception e){
			 e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs, call);
		}
		return listaUsoServidores;
	}
	
}
