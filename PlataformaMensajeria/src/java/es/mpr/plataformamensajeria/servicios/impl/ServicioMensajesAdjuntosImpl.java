package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.MensajesAdjuntosBean;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de adjuntos de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioMensajesAdjuntosImpl implements ServicioMensajesAdjuntos{
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosImpl.class);
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
	public List<MensajesAdjuntosBean> getMensajesAdjuntosHist(MensajesAdjuntosBean criterio) throws BusinessException {
		
		List<MensajesAdjuntosJPA> listJPA = null;
		
		try{
			if(null!=criterio){
				
				MensajesAdjuntosJPA criterioJPA = new MensajesAdjuntosJPA();
				if(null!=criterio.getMensajeId()){
					criterioJPA.setMensajeId(criterio.getMensajeId());
				}
				
				listJPA = jpa.readAll(0,0,criterioJPA);
				
			}
			
			List<MensajesAdjuntosBean> result = getListMensajesAdjuntosBean(listJPA);
			
			return result;
			
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getUnidadesOrganizacionales");
		}

	}
	
	/**
	 * <p>Convertirmos una lista de MensajesAdjuntosJPA a una lista de MensajesAdjuntosBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos MensajesAdjuntosBean
	 */
	protected List<MensajesAdjuntosBean> getListMensajesAdjuntosBean(List<MensajesAdjuntosJPA> listJPA) throws BusinessException
	{	
		List<MensajesAdjuntosBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<MensajesAdjuntosBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				MensajesAdjuntosJPA unidadJPA = listJPA.get(indice);
				MensajesAdjuntosBean unidad =  new MensajesAdjuntosBean();
			
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
	
	@Override
	@Transactional
	public List<MensajesAdjuntosBean> getTodosMensajesAdjuntosHist(String listIdsMensajes, Integer servicioId) throws BusinessException {
		
		List<MensajesAdjuntosBean> result = new ArrayList<MensajesAdjuntosBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJESADJUNTOS WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajesAdjuntosBean mensajeAdjunto = new MensajesAdjuntosBean();
				mensajeAdjunto.setMensajeAdjuntoId(rs.getInt("MENSAJEADJUNTOID"));
				mensajeAdjunto.setMensajeId(rs.getObject("MENSAJEID") != null ? rs.getInt("MENSAJEID") : null);
				mensajeAdjunto.setAdjuntoId(rs.getObject("ADJUNTOID") != null ? rs.getInt("ADJUNTOID") : null);
				
				result.add(mensajeAdjunto);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajesAdjuntos" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<MensajesAdjuntosHistoricosJPA> getTodosMensajesAdjuntosJPAHist(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<MensajesAdjuntosHistoricosJPA> result = new ArrayList<MensajesAdjuntosHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJESADJUNTOS MA INNER JOIN"+
					" TBL_MENSAJES M ON MA.MENSAJEID = M.MENSAJEID INNER JOIN"+
					" TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"+
					" WHERE LE.SERVICIOID=" + servicioId +
					" AND M.LOTEENVIOID="+loteID+""); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajesAdjuntosHistoricosJPA mensajeAdjunto = new MensajesAdjuntosHistoricosJPA();
				mensajeAdjunto.setId(rs.getInt("MENSAJEADJUNTOID"));
				mensajeAdjunto.setMensajeId(rs.getObject("MENSAJEID") != null ? rs.getInt("MENSAJEID") : null);
				mensajeAdjunto.setAdjuntoId(rs.getObject("ADJUNTOID") != null ? rs.getInt("ADJUNTOID") : null);
				
				result.add(mensajeAdjunto);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajesAdjuntos" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

}
