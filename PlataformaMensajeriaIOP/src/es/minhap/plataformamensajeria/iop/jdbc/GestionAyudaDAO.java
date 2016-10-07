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
public class GestionAyudaDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(GestionAyudaDAO.class);
	/**
	 * 
	 * @param servicioId
	 * @param canalId
	 * @param aplicacionId
	 * @param loteId
	 * @param mensajeId
	 * @param codExterno
	 * @param estadoId
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param usuario
	 * @param password
	 * @return
	 */
	public String consultarAyuda(){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		String ayuda = "";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT TEXTOAYUDA FROM USR_MSGPLT.VIEW_AYUDAS ORDER BY AYUDAID DESC");
			while(rs.next()){
				ayuda = rs.getString("TEXTOAYUDA");
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug("[GestionAyudaDAO.consultarAyuda] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return ayuda;
	}
}
