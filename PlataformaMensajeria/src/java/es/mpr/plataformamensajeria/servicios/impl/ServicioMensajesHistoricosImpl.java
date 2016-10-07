package es.mpr.plataformamensajeria.servicios.impl;

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

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de mensajes historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioMensajesHistoricosImpl implements ServicioMensajesHistoricos{
	Logger logger = Logger.getLogger(ServicioMensajesHistoricosImpl.class);
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
	public List<MensajeBean> getMensajesHist(MensajeBean criterio) {
		
		List<MensajeBean> result = new ArrayList<MensajeBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJES WHERE LOTEENVIOID=" + criterio.getLoteEnvioId() +
					" AND TRUNC(ULTIMOENVIO) <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(criterio.getUltimoEnvio()) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajeBean mensaje = new MensajeBean();
				mensaje.setMensajeId(rs.getInt(1));
				mensaje.setLoteEnvioId(rs.getInt(2));
				mensaje.setCodigoExterno(rs.getString(3));
				mensaje.setCabecera(rs.getString(4));
				mensaje.setEstadoActual(rs.getString(5));
				mensaje.setNumeroEnvios(rs.getString(6));
				mensaje.setFechaCreacion(rs.getDate(7));
				mensaje.setCreadoPor(rs.getString(8));
				mensaje.setFechaModificacion(rs.getDate(9));
				mensaje.setModificadoPor(rs.getString(10));
				mensaje.setUltimoEnvio(rs.getDate(11));
				mensaje.setUltimoIdHistorico(rs.getInt(12));
				mensaje.setCuerpo(rs.getString(13));
				mensaje.setTipoCuerpo(rs.getString(14));
				mensaje.setTipoCodificacion(rs.getString(15));
				mensaje.setPrioridad(rs.getString(16));
				mensaje.setTipoMensaje(rs.getString(17));
				mensaje.setTelefono(rs.getString(18));
				mensaje.setUim(rs.getString(19));
				mensaje.setIdEnviosSms(rs.getInt(20));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public Boolean historificarMensajes(Integer loteEnvioId, Date fechaUltimoEnvio) {
		Boolean historificar = false;
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM TBL_MENSAJES WHERE LOTEENVIOID=" + loteEnvioId); 
			rs = pstmt.executeQuery();
			
			rs.next();
			Integer numTotalMensajes = rs.getInt(1);  
			
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM TBL_MENSAJES WHERE LOTEENVIOID=" + loteEnvioId +
					" AND TRUNC(ULTIMOENVIO) <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaUltimoEnvio) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			rs.next();
			Integer numMensajesHist = rs.getInt(1); 
			
			if(numTotalMensajes == numMensajesHist) {
				historificar = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return historificar;
	}
	
	@Override
	@Transactional
	public Boolean consolidarMensajes(Integer loteEnvioId, Date fechaHistorificacion)  throws BusinessException {
		Boolean consolidar = false;
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM TBL_MENSAJES_HIST WHERE LOTEENVIOID=" + loteEnvioId +
					" AND ULTIMOENVIO <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorificacion) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			rs.next();
			Integer numMensajesCons = rs.getInt(1); 
			
			if(numMensajesCons > 0) {
				consolidar = true;
			}
			
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.evaluarMensajes" + loteEnvioId);	
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return consolidar;
	}
	
	@Override
	@Transactional
	public List<MensajeBean> getTodosMensajesHist(String listIdsLotesEnvios, Date fechaHist) {
		
		List<MensajeBean> result = new ArrayList<MensajeBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJES WHERE LOTEENVIOID IN ( " + listIdsLotesEnvios + ")" +
					" AND TRUNC(ULTIMOENVIO) <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHist) +"','dd/mm/yy')");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajeBean mensaje = new MensajeBean();
				mensaje.setMensajeId(rs.getInt(1));
				mensaje.setLoteEnvioId(rs.getInt(2));
				mensaje.setCodigoExterno(rs.getString(3));
				mensaje.setCabecera(rs.getString(4));
				mensaje.setEstadoActual(rs.getString(5));
				mensaje.setNumeroEnvios(rs.getString(6));
				mensaje.setFechaCreacion(rs.getDate(7));
				mensaje.setCreadoPor(rs.getString(8));
				mensaje.setFechaModificacion(rs.getDate(9));
				mensaje.setModificadoPor(rs.getString(10));
				mensaje.setUltimoEnvio(rs.getDate(11));
				mensaje.setUltimoIdHistorico(rs.getInt(12));
				mensaje.setCuerpo(rs.getString(13));
				mensaje.setTipoCuerpo(rs.getString(14));
				mensaje.setTipoCodificacion(rs.getString(15));
				mensaje.setPrioridad(rs.getString(16));
				mensaje.setTipoMensaje(rs.getString(17));
				mensaje.setTelefono(rs.getString(18));
				mensaje.setUim(rs.getString(19));
				mensaje.setIdEnviosSms(rs.getInt(20));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<MensajeHistoricosBean> getTodosMensajesCons(String listIdsLotesEnvios, Date fechaHist) throws BusinessException {
		
		List<MensajeHistoricosBean> result = new ArrayList<MensajeHistoricosBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MENSAJEID, LOTEENVIOID, ULTIMOENVIO FROM TBL_MENSAJES_HIST WHERE LOTEENVIOID IN ( " + listIdsLotesEnvios + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajeHistoricosBean mensaje = new MensajeHistoricosBean();
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				mensaje.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<MensajesHistoricosJPA> getTodosMensajeJPACons(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<MensajesHistoricosJPA> result = new ArrayList<MensajesHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MH.MENSAJEID, MH.LOTEENVIOID, MH.ULTIMOENVIO FROM TBL_MENSAJES_HIST MH INNER JOIN"+
					" TBL_LOTESENVIOS_HIST LEH ON LEH.LOTEENVIOID = MH.LOTEENVIOID"+
					" WHERE LEH.SERVICIOID=" + servicioId +
					" AND MH.LOTEENVIOID="+loteID+""); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajesHistoricosJPA mensaje = new MensajesHistoricosJPA();
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				mensaje.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

}
