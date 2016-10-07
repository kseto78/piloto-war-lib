package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.LotesEnviosBean;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnvios;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de lotes de envios a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioLotesEnviosImpl implements ServicioLotesEnvios{
	Logger logger = Logger.getLogger(ServicioLotesEnviosImpl.class);
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

	@Override
	@Transactional
	public List<LotesEnviosBean> getLotesEnviosHist(Integer servicioId, Date fechaHistorico) throws BusinessException {
		
		List<LotesEnviosBean> result = new ArrayList<LotesEnviosBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_LOTESENVIOS WHERE SERVICIOID=" + servicioId +
					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LotesEnviosBean le = new LotesEnviosBean();
				le.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				le.setNombre(rs.getString("NOMBRE"));
				le.setDescripcion(rs.getString("DESCRIPCION"));
				le.setServicioId(rs.getInt("SERVICIOID"));
				le.setEstadoEnvioId(rs.getInt("ESTADOENVIOID"));
				le.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				le.setCreadorPor(rs.getString("CREADOPOR"));
				le.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				le.setModificadoPor(rs.getString("MODIFICADOPOR"));
				
				result.add(le);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getLotesEnvio" + servicioId);	
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		return result;
		
	}
	
	@Override
	@Transactional
	public List<LotesEnviosHistoricosJPA> getLotesEnviosJPAHist(Integer servicioId, Date fechaHistorico) throws BusinessException {
		
		List<LotesEnviosHistoricosJPA> result = new ArrayList<LotesEnviosHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_LOTESENVIOS WHERE SERVICIOID=" + servicioId +
					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LotesEnviosHistoricosJPA le = new LotesEnviosHistoricosJPA();
				le.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				le.setNombre(rs.getString("NOMBRE"));
				le.setDescripcion(rs.getString("DESCRIPCION"));
				le.setServicioId(rs.getInt("SERVICIOID"));
				le.setEstadoEnvioId(rs.getInt("ESTADOENVIOID"));
				le.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				le.setCreadorPor(rs.getString("CREADOPOR"));
				le.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				le.setModificadoPor(rs.getString("MODIFICADOPOR"));
				
				result.add(le);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getLotesEnvio" + servicioId);	
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		return result;
		
	}
	
	/**
	 * <p>Convertirmos una lista de LotesEnviosJPA a una lista de LotesEnviosBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos LotesEnviosBean
	 */
	protected List<LotesEnviosBean> getListLotesEnviosBean(List<LotesEnviosJPA> listJPA) throws BusinessException
	{	
		List<LotesEnviosBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<LotesEnviosBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				LotesEnviosJPA unidadJPA = listJPA.get(indice);
				LotesEnviosBean unidad =  new LotesEnviosBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(unidad, unidadJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(unidad);
			}
		}
			
		return result;
	}
//	@Override
//	public int compruebaLotesPorHistorificar(Integer servicioId,
//			Date fechaHistorico) throws BusinessException {
////	List<LotesEnviosHistoricosJPA> result = new ArrayList<LotesEnviosHistoricosJPA>();
//		
//		int cantidad=0;
//		
//		Connection con = PlataformaMensajeriaUtil.getConexion();
//		PreparedStatement pstmt =null;
//		ResultSet rs = null;
//		try {
//			pstmt = con.prepareStatement("SELECT * FROM TBL_LOTESENVIOS WHERE SERVICIOID=" + servicioId +
//					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy')"); 
//			rs = pstmt.executeQuery();
//
//			rs.last();
//			
//			cantidad=rs.getRow();
//			
//		} catch (SQLException e) {
//			throw new BusinessException(e, "errors.job.historico.getLotesEnvio" + servicioId);	
//		}finally{
//			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
//		}
//		return cantidad;
//		
//	}


}
