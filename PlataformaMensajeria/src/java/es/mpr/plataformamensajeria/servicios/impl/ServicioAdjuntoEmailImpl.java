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

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.AdjuntoEmailJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmail;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioAdjuntoEmailImpl implements ServicioAdjuntoEmail{
	Logger logger = Logger.getLogger(ServicioAdjuntoEmailImpl.class);
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
	public List<AdjuntoEmailBean> getAdjuntosHist(AdjuntoEmailBean criterio) throws BusinessException {
		
		List<AdjuntoEmailJPA> listJPA = null;
		
		try{
			if(null!=criterio){
				
				AdjuntoEmailJPA criterioJPA = new AdjuntoEmailJPA();
				if(null!=criterio.getAdjuntoId()){
					criterioJPA.setAdjuntoId(criterio.getAdjuntoId());
				}
				
				listJPA = jpa.readAll(0,0,criterioJPA);
				
			}
			
			List<AdjuntoEmailBean> result = getListAdjuntosEmailBean(listJPA);
			
			return result;
			
		}catch (DAOException e){
			throw new BusinessException(e,"errors.organismo.getUnidadesOrganizacionales");
		}

	}
	
	/**
	 * <p>Convertirmos una lista de AdjuntoEmailJPA a una lista de AdjuntoEmailBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos AdjuntoEmailBean
	 */
	protected List<AdjuntoEmailBean> getListAdjuntosEmailBean(List<AdjuntoEmailJPA> listJPA) throws BusinessException
	{	
		List<AdjuntoEmailBean> result = null;
		
		if (listJPA!=null && !listJPA.isEmpty())
		{
			result = new ArrayList<AdjuntoEmailBean>();
		
			for (int indice=0;indice<listJPA.size();indice++) {
					
				AdjuntoEmailJPA unidadJPA = listJPA.get(indice);
				AdjuntoEmailBean unidad =  new AdjuntoEmailBean();
			
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
	public List<AdjuntoEmailBean> getTodosAdjuntosHist(String listIdsAdjuntos, Integer servicioId) throws BusinessException {
		
		List<AdjuntoEmailBean> result = new ArrayList<AdjuntoEmailBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_ADJUNTOS WHERE ADJUNTOID IN ( " + listIdsAdjuntos + ")");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AdjuntoEmailBean adjuntoEmail = new AdjuntoEmailBean();
				adjuntoEmail.setAdjuntoId(rs.getInt("ADJUNTOID"));
				adjuntoEmail.setNombre(rs.getString("NOMBRE"));
				adjuntoEmail.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				adjuntoEmail.setCreadoPor(rs.getString("CREADOPOR"));
				adjuntoEmail.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				adjuntoEmail.setModificadoPor(rs.getString("MODIFICADOPOR"));
				adjuntoEmail.setContenido(rs.getBytes("CONTENIDO"));
				adjuntoEmail.setImagen(rs.getInt("IMAGEN"));
				
				result.add(adjuntoEmail);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getFicherosAdjuntos" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<AdjuntoEmailHistoricosJPA> getTodosAdjuntosJPAHist(Integer servicioId, Integer loteID) throws BusinessException {
		
		List<AdjuntoEmailHistoricosJPA> result = new ArrayList<AdjuntoEmailHistoricosJPA>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_ADJUNTOS A INNER JOIN"+
					" TBL_MENSAJESADJUNTOS MA ON MA.ADJUNTOID = A.ADJUNTOID INNER JOIN"+
					" TBL_MENSAJES M ON MA.MENSAJEID = M.MENSAJEID INNER JOIN"+
					" TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"+
					" WHERE LE.SERVICIOID=" + servicioId +
					" AND M.LOTEENVIOID="+loteID+""); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AdjuntoEmailHistoricosJPA adjuntoEmail = new AdjuntoEmailHistoricosJPA();
				adjuntoEmail.setAdjuntoId(rs.getInt("ADJUNTOID"));
				adjuntoEmail.setNombre(rs.getString("NOMBRE"));
				adjuntoEmail.setFechaCreacion(rs.getTimestamp("FECHACREACION"));
				adjuntoEmail.setCreadoPor(rs.getString("CREADOPOR"));
				adjuntoEmail.setFechaModificacion(rs.getTimestamp("FECHAMODIFICACION"));
				adjuntoEmail.setModificadoPor(rs.getString("MODIFICADOPOR"));
				adjuntoEmail.setContenido(rs.getBytes("CONTENIDO"));
				adjuntoEmail.setImagen(rs.getInt("IMAGEN"));
				
				result.add(adjuntoEmail);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getFicherosAdjuntos" + servicioId);
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}


}
