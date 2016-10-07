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

import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.HistoricoJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistorico;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioHistoricoImpl implements ServicioHistorico{
	Logger logger = Logger.getLogger(ServicioHistoricoImpl.class);
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
	public List<HistoricoBean> getDestinatarioHist(HistoricoBean criterio) throws BusinessException {
		
		List<HistoricoJPA> listJPA = null;
		
		try{
			if(null!=criterio){
				
				HistoricoJPA criterioJPA = new HistoricoJPA();
				if(null!=criterio){
					criterioJPA.setMensajeId(criterio.getMensajeId());
				}
				
				listJPA = jpa.readAll(0,0,criterioJPA);
				
			}
			
			List<HistoricoBean> result = getListHistoricoBean(listJPA);
			
			return result;
			
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getUnidadesOrganizacionales");
		}

	}
	
	/**
	 * <p>Convertirmos una lista de HistoricoJPA a una lista de HistoricoBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos HistoricoBean
	 */
	protected List<HistoricoBean> getListHistoricoBean(List<HistoricoJPA> listJPA) throws BusinessException
	{	
		List<HistoricoBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<HistoricoBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				HistoricoJPA unidadJPA = listJPA.get(indice);
				HistoricoBean unidad =  new HistoricoBean();
			
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
	public List<HistoricoBean> getTodosHistoricosHist(String listIdsMensajes, Integer servicioId) throws BusinessException {
		
		List<HistoricoBean> result = new ArrayList<HistoricoBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_HISTORICOS WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HistoricoBean historico = new HistoricoBean();
				historico.setHistoricoId(rs.getInt("HISTORICOID"));
				historico.setFecha(rs.getTimestamp("FECHA"));
				historico.setMensajeId(rs.getInt("MENSAJEID"));
				historico.setEstadoId(rs.getObject("ESTADOID") != null ? rs.getInt("ESTADOID") : null);
				historico.setServidorId(rs.getObject("SERVIDORID") != null ? rs.getInt("SERVIDORID") : null);
				historico.setPlanificacionId(rs.getObject("PLANIFICACIONID") != null ? rs.getInt("PLANIFICACIONID") : null);
				historico.setDescripcion(rs.getString("DESCRIPCION"));
				historico.setSubEstadoId(rs.getObject("SUBESTADOID") != null ? rs.getInt("SUBESTADOID") : null);
				
				result.add(historico);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getHistorico" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<HistoricoHistJPA> getTodosHistoricosJPAHist(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<HistoricoHistJPA> result = new ArrayList<HistoricoHistJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_HISTORICOS H INNER JOIN"+
					" TBL_MENSAJES M ON H.MENSAJEID = M.MENSAJEID INNER JOIN"+
					" TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"+
					" WHERE LE.SERVICIOID=" + servicioId +
					" AND M.LOTEENVIOID="+loteID+""); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HistoricoHistJPA historico = new HistoricoHistJPA();
				historico.setHistoricoId(rs.getInt("HISTORICOID"));
				historico.setFecha(rs.getTimestamp("FECHA"));
				historico.setMensajeId(rs.getInt("MENSAJEID"));
				historico.setEstadoId(rs.getObject("ESTADOID") != null ? rs.getInt("ESTADOID") : null);
				historico.setServidorId(rs.getObject("SERVIDORID") != null ? rs.getInt("SERVIDORID") : null);
				historico.setPlanificacionId(rs.getObject("PLANIFICACIONID") != null ? rs.getInt("PLANIFICACIONID") : null);
				historico.setDescripcion(rs.getString("DESCRIPCION"));
				historico.setSubEstadoId(rs.getObject("SUBESTADOID") != null ? rs.getInt("SUBESTADOID") : null);
				
				result.add(historico);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getHistorico" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

}
