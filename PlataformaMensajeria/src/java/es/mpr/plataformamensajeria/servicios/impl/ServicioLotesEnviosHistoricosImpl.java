package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.HistoricoDTO;
import es.mpr.plataformamensajeria.beans.LotesEnviosBean;
import es.mpr.plataformamensajeria.beans.LotesEnviosHistoricosBean;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistoricoHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntosHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia de lotes de envios a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioLotesEnviosHistoricosImpl implements ServicioLotesEnviosHistoricos{
	Logger logger = Logger.getLogger(ServicioLotesEnviosHistoricosImpl.class);
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
	public List<LotesEnviosHistoricosBean> getLotesEnviosHist(Integer servicioId, Date fechaHistorico) throws BusinessException {
		
		List<LotesEnviosHistoricosBean> result = new ArrayList<LotesEnviosHistoricosBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT LOTEENVIOID FROM TBL_LOTESENVIOS_HIST WHERE SERVICIOID=" + servicioId +
					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy')"); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LotesEnviosHistoricosBean le = new LotesEnviosHistoricosBean();
				le.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				
				result.add(le);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getLotesEnvio" + servicioId);	
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
			
	pstmt = con.prepareStatement("SELECT LOTEENVIOID FROM TBL_LOTESENVIOS_HIST WHERE SERVICIOID=" + servicioId +
					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy') order by LOTEENVIOID "); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				LotesEnviosHistoricosJPA le = new LotesEnviosHistoricosJPA();
				le.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				
				result.add(le);
			}
			
			

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getLotesEnvio" + servicioId);	
		}finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		return result;
		
	}
	
	@Override
	@Transactional
	public Map<LotesEnviosHistoricosJPA,HistoricoDTO> getLotesEnviosJPAHistMap(Integer servicioId, Date fechaHistorico
			,ServicioAdjuntoEmailHistoricos servicioAdjuntoEmailHistoricos,
			ServicioDestinatarioHistoricos servicioDestinatarioHistoricos,
			ServicioHistoricoHist servicioHistoricoHist,
			ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos,
			ServicioMensajesAdjuntosHistoricos servicioMensajesAdjuntosHistoricos) throws BusinessException {
		
		Map<LotesEnviosHistoricosJPA,HistoricoDTO> mapLotes = new HashMap<LotesEnviosHistoricosJPA, HistoricoDTO>();
		List<LotesEnviosHistoricosJPA> result = new ArrayList<LotesEnviosHistoricosJPA>();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			
	pstmt = con.prepareStatement("SELECT LOTEENVIOID FROM TBL_LOTESENVIOS_HIST WHERE SERVICIOID=" + servicioId +
					" AND FECHAMODIFICACION <= TO_DATE('"+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHistorico) +"','dd/mm/yy') order by LOTEENVIOID "); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				LotesEnviosHistoricosJPA le = new LotesEnviosHistoricosJPA();
				le.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				
				result.add(le);
			}
			
			for (LotesEnviosHistoricosJPA l : result) {
				HistoricoDTO hist = new HistoricoDTO();
				hist.setListaAdjuntosEmailHist(servicioAdjuntoEmailHistoricos.getTodosAdjuntosJPACons(servicioId, l.getLoteEnvioId()));
				hist.setListaDestinatariosHist(servicioDestinatarioHistoricos.getTodosDestinatarioJPACons(servicioId, l.getLoteEnvioId()));
				hist.setListaHistoricoHist(servicioHistoricoHist.getTodosHistoricosJPACons(servicioId, l.getLoteEnvioId()));
				hist.setListaGestionEnviosHist(servicioGestionEnviosHistoricos.getTodosGestionEnviosJPACons(servicioId, l.getLoteEnvioId()));
				hist.setListaMensajesAdjuntosHist(servicioMensajesAdjuntosHistoricos.getTodosMensajesAdjuntosJPACons(servicioId, l.getLoteEnvioId()));
				
				mapLotes.put(l, hist);
				
			}
			

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getLotesEnvio" + servicioId);	
		}catch (Exception e) {
			e.printStackTrace();	
		}
		finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs,null);
		}
		return mapLotes;
		
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


}
