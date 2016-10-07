package es.mpr.plataformamensajeria.servicios.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;

import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;
import es.mpr.plataformamensajeria.model.ProcesoInformesServiciosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoInformesServicios;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
public class ServicioProcesoInformesServiciosImpl implements ServicioProcesoInformesServicios{
	
	Logger logger = Logger.getLogger(ServicioProcesoInformesServiciosImpl.class);
	
	private IPaginationJPADAO jpa;
	
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
	public Long newServicioProcesoInformesServicios(ProcesoInformesServiciosBean procesoInformesServiciosBean) {
		try{
			
			ProcesoInformesServiciosJPA procesoInformesServiciosJPA = new ProcesoInformesServiciosJPA();
			procesoInformesServiciosJPA.setId(null);
			procesoInformesServiciosJPA.setCodigoEstado(procesoInformesServiciosBean.getCodigoEstado());
			procesoInformesServiciosJPA.setDescripcionEstado(procesoInformesServiciosBean.getDescripcionEstado());
			procesoInformesServiciosJPA.setFechaInicio(procesoInformesServiciosBean.getFechaInicio());
			procesoInformesServiciosJPA.setFechaFin(procesoInformesServiciosBean.getFechaFin());
			jpa.insert(procesoInformesServiciosJPA);
			
			return procesoInformesServiciosJPA.getId();
		}catch (DAOException e){
			return null;
		}
	}
	
	@Override
	@Transactional
	public List<InformesServiciosEstadoBean> obtenerInformesServiciosEstado(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		
		List<InformesServiciosEstadoBean> result = new ArrayList<InformesServiciosEstadoBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("SELECT * FROM VIEW_INFORMES_SERVICIOS_ESTADO"+
										" WHERE SERVICIOID = " + servicioId +
										" AND ANNO = " + anno +
										" AND MES = " + mes); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InformesServiciosEstadoBean informeServicioEstado = new InformesServiciosEstadoBean();
				informeServicioEstado.setServicioId(rs.getInt("SERVICIOID"));
				informeServicioEstado.setAnno(rs.getInt("ANNO"));
				informeServicioEstado.setMes(rs.getInt("MES"));
				informeServicioEstado.setEstado(rs.getString("ESTADO"));
				informeServicioEstado.setNumTotal(rs.getInt("NUMTOTAL"));

				result.add(informeServicioEstado);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesEstado" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return result;
		
	}
	
	@Override
	@Transactional
	public List<InformesServiciosCodOrgBean> obtenerInformesServiciosCodOrg(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		
		List<InformesServiciosCodOrgBean> result = new ArrayList<InformesServiciosCodOrgBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("SELECT * FROM VIEW_INFORMES_SERVICIOS_CODORG"+
										" WHERE SERVICIOID = " + servicioId +
										" AND ANNO = " + anno +
										" AND MES = " + mes); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InformesServiciosCodOrgBean informeServicioCodOrg = new InformesServiciosCodOrgBean();
				informeServicioCodOrg.setServicioId(rs.getInt("SERVICIOID"));
				informeServicioCodOrg.setAnno(rs.getInt("ANNO"));
				informeServicioCodOrg.setMes(rs.getInt("MES"));
				informeServicioCodOrg.setCodOrganismo(rs.getString("CODORGANISMO"));
				informeServicioCodOrg.setNumTotal(rs.getInt("NUMTOTAL"));

				result.add(informeServicioCodOrg);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesCodOrg" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return result;
		
	}
	
	@Override
	@Transactional
	public List<InformesServiciosCodSiaBean> obtenerInformesServiciosCodSia(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		
		List<InformesServiciosCodSiaBean> result = new ArrayList<InformesServiciosCodSiaBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("SELECT * FROM VIEW_INFORMES_SERVICIOS_CODSIA"+
										" WHERE SERVICIOID = " + servicioId +
										" AND ANNO = " + anno +
										" AND MES = " + mes); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InformesServiciosCodSiaBean informeServicioCodSia = new InformesServiciosCodSiaBean();
				informeServicioCodSia.setServicioId(rs.getInt("SERVICIOID"));
				informeServicioCodSia.setAnno(rs.getInt("ANNO"));
				informeServicioCodSia.setMes(rs.getInt("MES"));
				informeServicioCodSia.setCodSia(rs.getString("CODSIA"));
				informeServicioCodSia.setNumTotal(rs.getInt("NUMTOTAL"));

				result.add(informeServicioCodSia);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesCodSia" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return result;
		
	}
	
	@Override
	@Transactional
	public List<InformesServiciosCodOrgPagadorBean> obtenerInformesServiciosCodOrgPagador(Integer servicioId, Integer anno, Integer mes) throws BusinessException{
		
		List<InformesServiciosCodOrgPagadorBean> result = new ArrayList<InformesServiciosCodOrgPagadorBean>();
		
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("SELECT * FROM VIEW_INFORMES_SERVICIOS_COP"+
										" WHERE SERVICIOID = " + servicioId +
										" AND ANNO = " + anno +
										" AND MES = " + mes); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InformesServiciosCodOrgPagadorBean informeServicioCodOrgPagador = new InformesServiciosCodOrgPagadorBean();
				informeServicioCodOrgPagador.setServicioId(rs.getInt("SERVICIOID"));
				informeServicioCodOrgPagador.setAnno(rs.getInt("ANNO"));
				informeServicioCodOrgPagador.setMes(rs.getInt("MES"));
				informeServicioCodOrgPagador.setCodOrganismoPagador(rs.getString("CODORGANISMOPAGADOR"));
				informeServicioCodOrgPagador.setNumTotal(rs.getInt("NUMTOTAL"));

				result.add(informeServicioCodOrgPagador);
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.informesServicios.getMensajesCodOrgPagador" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		
		return result;
		
	}


}
