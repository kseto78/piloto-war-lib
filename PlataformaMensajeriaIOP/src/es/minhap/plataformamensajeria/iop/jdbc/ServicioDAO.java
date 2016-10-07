package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 
 * @author i-nercya
 *
 */
public class ServicioDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(ServicioDAO.class);

	public boolean isMultiOrganismo(String servicioID){

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		Integer multiOrganismo = 0;
		boolean resultado = false;
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT MULTIORGANISMO FROM TBL_SERVICIOS WHERE SERVICIOID = '" + servicioID+"'");
	
			while(rs.next()){
				multiOrganismo = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.debug("[ServicioDAO.isMultiOrganismo] - Error en bbdd",e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		if(1==multiOrganismo)
			resultado = true;
		return resultado;
	}
	public boolean asociadoOrganismoServicio(String servicioID,String organismoPagador){

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		boolean resultado = false;
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT TBL_ORGANISMOS.DIR3 "
					+ "FROM TBL_SERVICIOS, TBL_ORGANISMOS, TBL_ORGANISMOS_SERVICIO "
					+ "WHERE (TBL_ORGANISMOS_SERVICIO.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
					+ "AND (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
					+ "AND TBL_SERVICIOS.SERVICIOID = '" + servicioID+"' AND TBL_ORGANISMOS.DIR3 = '" + organismoPagador+"'");

			while(rs.next()){
				resultado = true;
			}
		} catch (SQLException e) {
			logger.debug("[ServicioDAO.asociadoOrganismoServicio] - Error en bbdd",e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return resultado;
	}
	
}
