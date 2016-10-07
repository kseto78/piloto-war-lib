package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 
 * @author jgonzvil
 * 
 */
public class OrganismosDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(OrganismosDAO.class);

	public boolean organismoActivoEnServicio(Integer servicioId,String organismoPagador){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		boolean resultado = false;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.debug("OrganismosDAO.organismoActivoEnServicio - callableStmt abierta");
			rs = stmt.executeQuery("SELECT TBL_ORGANISMOS.DIR3 as dir3,"
					+ "       TBL_ORGANISMOS_SERVICIO.SERVICIOORGANISMOID,"
					+ "       TBL_ORGANISMOS_SERVICIO.SERVICIOID"
					+ "  FROM TBL_ORGANISMOS_SERVICIO, TBL_ORGANISMOS"
					+ " WHERE (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) and "
					+ "TBL_ORGANISMOS_SERVICIO.SERVICIOID = "+servicioId+" and TBL_ORGANISMOS.DIR3 = '"+organismoPagador+"'"
					+ " and TBL_ORGANISMOS.ACTIVO = 1 ");
			if(rs.next()){
				resultado = true;
			}else{
				resultado = false;
			}
		} catch (SQLException e) {
			logger.error("OrganismosDAO.organismoActivoEnServicio - Error: ", e);
			return false;
		}finally{
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			logger.debug("OrganismosDAO.organismoActivoEnServicio - callableStmt cerrada");
		} 
		return resultado;
	}

	public boolean existeOrganismo(String codOrganismoPagadorSMS) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		boolean res = false;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.debug("OrganismosDAO.organismoActivoEnServicio - callableStmt abierta");
			rs = stmt.executeQuery("SELECT NOMBRE FROM TBL_ORGANISMOS WHERE DIR3= '"+codOrganismoPagadorSMS+"' ");
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			logger.error("OrganismosDAO.existeOrganismo - Error: ", e);
			return false;
		}finally{
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			logger.debug("OrganismosDAO.existeOrganismo - callableStmt cerrada");
		} 
		return res;
	}
	

}
