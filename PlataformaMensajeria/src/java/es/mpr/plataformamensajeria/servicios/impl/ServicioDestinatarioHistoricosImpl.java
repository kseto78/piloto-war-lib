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

import es.mpr.plataformamensajeria.beans.DestinatarioBean;
import es.mpr.plataformamensajeria.beans.DestinatarioHistoricosBean;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de adjuntos de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioDestinatarioHistoricosImpl implements ServicioDestinatarioHistoricos{
	Logger logger = Logger.getLogger(ServicioDestinatarioHistoricosImpl.class);
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
	public List<DestinatarioBean> getDestinatarioHist(DestinatarioBean criterio) throws BusinessException {
		
		List<DestinatarioJPA> listJPA = null;
		
		try{
			if(null!=criterio){
				
				DestinatarioJPA criterioJPA = new DestinatarioJPA();
				if(null!=criterio){
					criterioJPA.setMensajeId(Integer.parseInt(criterio.getMensajeId()));
				}
				
				listJPA = jpa.readAll(0,0,criterioJPA);
				
			}
			
			List<DestinatarioBean> result = getListDestinatarioBean(listJPA);
			
			return result;
			
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getUnidadesOrganizacionales");
		}

	}
	
	/**
	 * <p>Convertirmos una lista de DestinatarioJPA a una lista de DestinatarioBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos DestinatarioBean
	 */
	protected List<DestinatarioBean> getListDestinatarioBean(List<DestinatarioJPA> listJPA) throws BusinessException
	{	
		List<DestinatarioBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<DestinatarioBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				DestinatarioJPA unidadJPA = listJPA.get(indice);
				DestinatarioBean unidad =  new DestinatarioBean();
			
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
	public List<DestinatarioBean> getTodosDestinatarioHist(String listIdsMensajes) {
		
		List<DestinatarioBean> result = new ArrayList<DestinatarioBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_DESTINATARIOS WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DestinatarioBean destinatario = new DestinatarioBean();
				destinatario.setDestinatarioId(rs.getInt("DESTINATARIOID"));
				destinatario.setNombre(rs.getString("NOMBRE"));
				destinatario.setEmail(rs.getString("EMAIL"));
				destinatario.setMensajeId(rs.getString("MENSAJEID"));
				destinatario.setFechaCreacion(rs.getDate("FECHACREACION"));
				destinatario.setCreadoPor(rs.getString("CREADOPOR"));
				destinatario.setFechaModificacion(rs.getDate("FECHAMODIFICACION"));
				destinatario.setModificadoPor(rs.getString("MODIFICADOPOR"));
				destinatario.setTipoDestinatario(rs.getString("TIPODESTINATARIO"));
				
				result.add(destinatario);
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
	public List<DestinatarioHistoricosBean> getTodosDestinatarioCons(String listIdsMensajes, Integer servicioId) throws BusinessException {
		
		List<DestinatarioHistoricosBean> result = new ArrayList<DestinatarioHistoricosBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT DESTINATARIOID FROM TBL_DESTINATARIOS_HIST WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DestinatarioHistoricosBean destinatario = new DestinatarioHistoricosBean();
				destinatario.setDestinatarioId(rs.getInt("DESTINATARIOID"));
				
				result.add(destinatario);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getDestinatarios" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<DestinatarioHistoricosJPA> getTodosDestinatarioJPACons(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<DestinatarioHistoricosJPA> result = new ArrayList<DestinatarioHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT DH.DESTINATARIOID, DH.MENSAJEID FROM TBL_DESTINATARIOS_HIST DH INNER JOIN"+
					" TBL_MENSAJES_HIST MH ON DH.MENSAJEID = MH.MENSAJEID INNER JOIN"+
					" TBL_LOTESENVIOS_HIST LEH ON LEH.LOTEENVIOID = MH.LOTEENVIOID"+
					" WHERE LEH.SERVICIOID=" + servicioId +
					" AND MH.LOTEENVIOID="+loteID+""); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DestinatarioHistoricosJPA destinatario = new DestinatarioHistoricosJPA();
				destinatario.setDestinatarioId(rs.getInt("DESTINATARIOID"));
				destinatario.setMensajeId(rs.getString("MENSAJEID"));
				
				result.add(destinatario);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getDestinatarios" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}


}
