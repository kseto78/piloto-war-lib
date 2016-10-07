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
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.HistoricoJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistoricoHist;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioHistoricoHistImpl implements ServicioHistoricoHist{
	Logger logger = Logger.getLogger(ServicioHistoricoHistImpl.class);
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
	public List<HistoricoBean> getTodosHistoricosHist(String listIdsMensajes) {
		
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
				historico.setFecha(rs.getDate("FECHA"));
				historico.setMensajeId(rs.getInt("MENSAJEID"));
				historico.setEstadoId(rs.getInt("ESTADOID"));
				historico.setServidorId(rs.getInt("SERVIDORID"));
				historico.setPlanificacionId(rs.getInt("PLANIFICACIONID"));
				historico.setDescripcion(rs.getString("DESCRIPCION"));
				historico.setSubEstadoId(rs.getInt("SUBESTADOID"));
				
				result.add(historico);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<HistoricoHistBean> getTodosHistoricosCons(String listIdsMensajes, Integer servicioId) throws BusinessException {
		
		List<HistoricoHistBean> result = new ArrayList<HistoricoHistBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT HISTORICOID FROM TBL_HISTORICOS_HIST WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HistoricoHistBean historico = new HistoricoHistBean();
				historico.setHistoricoId(rs.getInt("HISTORICOID"));
				
				result.add(historico);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getHistorico" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<HistoricoHistJPA> getTodosHistoricosJPACons(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<HistoricoHistJPA> result = new ArrayList<HistoricoHistJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT HH.HISTORICOID, HH.MENSAJEID FROM TBL_HISTORICOS_HIST HH INNER JOIN"+
					" TBL_MENSAJES_HIST MH ON HH.MENSAJEID = MH.MENSAJEID INNER JOIN"+
					" TBL_LOTESENVIOS_HIST LEH ON LEH.LOTEENVIOID = MH.LOTEENVIOID"+
					" WHERE LEH.SERVICIOID=" + servicioId +
					" AND MH.LOTEENVIOID="+loteID+""); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HistoricoHistJPA historico = new HistoricoHistJPA();
				historico.setHistoricoId(rs.getInt("HISTORICOID"));
				historico.setMensajeId(rs.getInt("MENSAJEID"));
				
				result.add(historico);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getHistorico" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

}
