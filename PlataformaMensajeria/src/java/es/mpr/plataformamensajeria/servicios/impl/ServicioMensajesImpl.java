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
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioMensajesImpl implements ServicioMensajes{
	Logger logger = Logger.getLogger(ServicioMensajesImpl.class);
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
	public List<MensajeBean> getMensajesHist(MensajeBean criterio) throws BusinessException {
		
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
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				mensaje.setCodigoExterno(rs.getString("CODIGOEXTERNO"));
				mensaje.setCabecera(rs.getString("CABECERA"));
				mensaje.setEstadoActual(rs.getString("ESTADOACTUAL"));
				mensaje.setNumeroEnvios(rs.getString("NUMEROENVIOS"));
				mensaje.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				mensaje.setCreadoPor(rs.getString("CREADOPOR"));
				mensaje.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				mensaje.setModificadoPor(rs.getString("MODIFICADOPOR"));
				mensaje.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				mensaje.setUltimoIdHistorico(rs.getInt("ULTIMOIDHISTORICO"));
				mensaje.setCuerpo(rs.getString("CUERPO"));
				mensaje.setTipoCuerpo(rs.getString("TIPOCUERPO"));
				mensaje.setTipoCodificacion(rs.getString("TIPOCODIFICACION"));
				mensaje.setPrioridad(rs.getString("PRIORIDAD"));
				mensaje.setTipoMensaje(rs.getString("TIPOMENSAJE"));
				mensaje.setTelefono(rs.getString("TELEFONO"));
				mensaje.setUim(rs.getString("UIM"));
				mensaje.setIdEnviosSms(rs.getInt("IDENVIOSSMS"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public Boolean historificarMensajes(Integer loteEnvioId, Date fechaUltimoEnvio) throws BusinessException {
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
			throw new BusinessException(e, "errors.job.historico.evaluarMensajes" + loteEnvioId);	
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return historificar;
	}
	
	@Override
	@Transactional
	public List<MensajeBean> getTodosMensajesHist(String listIdsLotesEnvios, Date fechaHist) throws BusinessException {
		
		List<MensajeBean> result = new ArrayList<MensajeBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJES WHERE LOTEENVIOID IN ( " + listIdsLotesEnvios + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajeBean mensaje = new MensajeBean();
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				mensaje.setCodigoExterno(rs.getString("CODIGOEXTERNO"));
				mensaje.setCabecera(rs.getString("CABECERA"));
				mensaje.setEstadoActual(rs.getString("ESTADOACTUAL"));
				mensaje.setNumeroEnvios(rs.getString("NUMEROENVIOS"));
				mensaje.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				mensaje.setCreadoPor(rs.getString("CREADOPOR"));
				mensaje.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				mensaje.setModificadoPor(rs.getString("MODIFICADOPOR"));
				mensaje.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				mensaje.setUltimoIdHistorico(rs.getInt("ULTIMOIDHISTORICO"));
				mensaje.setCuerpo(rs.getString("CUERPO"));
				mensaje.setTipoCuerpo(rs.getString("TIPOCUERPO"));
				mensaje.setTipoCodificacion(rs.getString("TIPOCODIFICACION"));
				mensaje.setPrioridad(rs.getString("PRIORIDAD"));
				mensaje.setTipoMensaje(rs.getString("TIPOMENSAJE"));
				mensaje.setTelefono(rs.getString("TELEFONO"));
				mensaje.setUim(rs.getString("UIM"));
				mensaje.setIdEnviosSms(rs.getInt("IDENVIOSSMS"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<MensajeBean> getMensajesByLoteEnvio(Integer loteEnvioId, Date fechaHist) throws BusinessException {
		
		List<MensajeBean> result = new ArrayList<MensajeBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MENSAJEID, ESTADOACTUAL FROM TBL_MENSAJES WHERE LOTEENVIOID=" + loteEnvioId +
					" AND ULTIMOENVIO <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHist) +"','dd/mm/yy')");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajeBean mensaje = new MensajeBean();
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setEstadoActual(rs.getString("ESTADOACTUAL"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<MensajesHistoricosJPA> getTodosMensajesJPAHist(Integer servicioId, Integer loteEnvioID) throws BusinessException {
		
		List<MensajesHistoricosJPA> result = new ArrayList<MensajesHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("SELECT * FROM TBL_MENSAJES M INNER JOIN"+
										" TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"+
										" WHERE LE.SERVICIOID=" + servicioId +
										" AND M.LOTEENVIOID="+loteEnvioID+""); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MensajesHistoricosJPA mensaje = new MensajesHistoricosJPA();
				mensaje.setMensajeId(rs.getInt("MENSAJEID"));
				mensaje.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				mensaje.setCodigoExterno(rs.getString("CODIGOEXTERNO"));
				mensaje.setCabecera(rs.getString("CABECERA"));
				mensaje.setEstadoActual(rs.getString("ESTADOACTUAL"));
				mensaje.setNumeroEnvios(rs.getString("NUMEROENVIOS"));
				mensaje.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				mensaje.setCreadoPor(rs.getString("CREADOPOR"));
				mensaje.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				mensaje.setModificadoPor(rs.getString("MODIFICADOPOR"));
				mensaje.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				mensaje.setUltimoIdHistorico(rs.getInt("ULTIMOIDHISTORICO"));
				mensaje.setCuerpo(rs.getString("CUERPO"));
				mensaje.setTipoCuerpo(rs.getString("TIPOCUERPO"));
				mensaje.setTipoCodificacion(rs.getString("TIPOCODIFICACION"));
				mensaje.setPrioridad(rs.getString("PRIORIDAD"));
				mensaje.setTipoMensaje(rs.getString("TIPOMENSAJE"));
				mensaje.setTelefono(rs.getString("TELEFONO"));
				mensaje.setUim(rs.getString("UIM"));
				mensaje.setIdEnviosSms(rs.getInt("IDENVIOSSMS"));
				mensaje.setDocUsuario(rs.getString("DOCUSUARIO"));
				mensaje.setCodSIA(rs.getString("CODSIA"));
				mensaje.setCodOrganismo(rs.getString("CODORGANISMO"));
				mensaje.setCodOrganismoPagador(rs.getString("CODORGANISMOPAGADOR"));
				mensaje.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				mensaje.setIcono(rs.getString("ICONO"));
				mensaje.setSonido(rs.getString("SONIDO"));
				
				result.add(mensaje);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getMensajes");
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

}
