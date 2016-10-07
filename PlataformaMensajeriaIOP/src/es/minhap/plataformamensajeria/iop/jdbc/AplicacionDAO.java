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
public class AplicacionDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(AplicacionDAO.class);

	public Integer loginUsuario(String username,String password){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		Integer login = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.debug("AplicacionDAO.loginUsuario - callableStmt abierta");
			rs = stmt.executeQuery("SELECT aplicacionid from tbl_aplicaciones WHERE password = "
									+ "(utl_raw.cast_to_varchar2(UTL_ENCODE.BASE64_enCODE(UTL_RAW.CAST_TO_RAW('"+password+"')))) AND usuario = '"+username+"'");
			while(rs.next()){
			login = rs.getInt("aplicacionid");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			logger.debug("AplicacionDAO.loginUsuario - callableStmt cerrada");
		} 
		if (null != login) {
			return 1;
		} else {
			return 0;
		}

	}
	

	/**
	 * This method check if the application is active or inactive 
	 * 
	 * @param servicioId is the identification service related with application
	 * @return active <code>1</code> or inactive <code>0</code>
	 */
	public Integer checkActiveApplication(Integer servicioId) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		Integer active = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.debug("AplicacionDAO.checkActiveApplication - callableStmt abierta");
			rs = stmt.executeQuery("SELECT AP.ACTIVO from tbl_aplicaciones AP, TBL_SERVICIOS S WHERE S.APLICACIONID = AP.APLICACIONID AND S.SERVICIOID=" + servicioId);
			if (rs.next()) {
				active = rs.getInt("activo");
			} else {
				active = 0;
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("AplicacionDAO.checkActiveApplication - ERROR:", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			logger.debug("AplicacionDAO.checkActiveApplication - callableStmt cerrada");
		}
		return active;
	}
}
