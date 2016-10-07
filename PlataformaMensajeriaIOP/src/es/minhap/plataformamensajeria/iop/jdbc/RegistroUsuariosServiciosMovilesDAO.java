package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author jgonzvil
 * 
 */
public class RegistroUsuariosServiciosMovilesDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(RegistroUsuariosServiciosMovilesDAO.class);
	
	private static final String INSERT_USUARIO_SERVICIO = "INSERT INTO TBL_USUARIOS_SERVICIOSMOVILES (USUARIOSSERVICIOSMOVILESID,ESTADOSUSCRIPCION,FECHACREACION,USUARIOSID,SERVICIOSMOVILESID,"
			+ "CREADOPOR) VALUES(USUARIOS_SERVICIOSMOVILES_SEC.NEXTVAL,1, SYSDATE,";
	
	private static final String UPDATE_SUSCRIPCION_USUARIO_SERVICIO = "UPDATE TBL_USUARIOS_SERVICIOSMOVILES  SET FECHAMODIFICACION= SYSDATE ,ESTADOSUSCRIPCION = ";
	
	
	/**
	 * Este metodo comprueba si existe el usuario y lo actualiza, en caso contrario lo inserta
	 * 
	 * @param idUsuario
	 * @param idServicioMovil
	 * @param usuario
	 * @return Boolean 
	 */
	public Boolean registraUsuarioServicio(String idUsuario, String idServicioMovil, String usuario){
		Boolean result = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String insertSentence = INSERT_USUARIO_SERVICIO + idUsuario + "," + idServicioMovil + ",'" + usuario +"')";
			rs = stmt.executeQuery(insertSentence);			
			result = true;
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.registraUsuarioServicio] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return result;
	}
	
	/**
	 * Este metodo comprueba si un usuario está suscrito a un servicio movil
	 * 
	 * @param idUsuario
	 * @param idServicioMovil
	 * @return
	 */
	public String checkSuscriptionStatus(String idUsuario, String idServicioMovil){
		String estadoSuscripcion="";
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT ESTADOSUSCRIPCION FROM TBL_USUARIOS_SERVICIOSMOVILES WHERE USUARIOSID = " + idUsuario + " AND SERVICIOSMOVILESID = "
					+ idServicioMovil);
			if (rs.next()) {
				estadoSuscripcion = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.checkSuscriptionStatus] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return estadoSuscripcion;
	}

	/**
	 * Este metodo comprueba si el servicio movil existe
	 * 
	 * @param idServicioMovil
	 * @return
	 */
	public boolean checkMobileServie(String idServicioMovil){
		boolean isMobileService = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT SERVICIOSMOVILESID FROM TBL_SERVICIOSMOVILES WHERE SERVICIOSMOVILESID = " + idServicioMovil);
			while(rs.next()){
				isMobileService = true;
			}
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.checkMobileServie] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return isMobileService;
	}
	
	/**
	 * Este metodo comprueba si el servicio movil existe y si está activo
	 * 
	 * @param idServicioMovil
	 * @return
	 */
	public boolean checkMobileActiveService(String idServicioMovil){
		boolean isMobileService = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT SERVICIOSMOVILESID FROM TBL_SERVICIOSMOVILES WHERE SERVICIOSMOVILESID = " + idServicioMovil + "  AND ESTADO =1" );
			while(rs.next()){
				isMobileService = true;
			}
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.checkMobileServie] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return isMobileService;
	}

	/**
	 * Este metodo comprueba que los id usuarios existan en un servicio movil
	 * 
	 * @param users
	 * @param idServicioMovil
	 * @return
	 */
	public boolean comprobarUsuarioServicio(List<String> users, String idServicioMovil){
		boolean exists =false;
		ResultSet rs = null;
		Statement stmt =null;
		Connection conn = null;
		try {
			conn = this.getConn();
			for (String user : users) {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("SELECT US.* FROM TBL_USUARIOS_SERVICIOSMOVILES US WHERE  US.USUARIOSID = '"+ user+"'  AND US.SERVICIOSMOVILESID = " + idServicioMovil);
				while(rs.next()){
				exists = true; 
				}
				
				if (exists== true) {
					break;
				}
				stmt.close();
				rs.close();
			}
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.comprobarUsuarioServicio] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return exists;
	}
	
	/**
	 * Este metodo comprueba que los id usuarios esten todos asociado al serivicio movil
	 * 
	 * @param usuario
	 * @param idServicioMovil
	 * @return
	 */
	public boolean comprobarUsuarioServicioValido(Integer usuario, String idServicioMovil){
		boolean exists =false;
		ResultSet rs = null;
		Statement stmt =null;
		Connection conn = null;
		try {
			conn = this.getConn();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT US.* FROM TBL_USUARIOS_SERVICIOSMOVILES US WHERE  US.USUARIOSID = '"+ usuario+"'  AND US.SERVICIOSMOVILESID = " + idServicioMovil+ " AND US.ESTADOSUSCRIPCION=1");
			if (rs.next()){
				exists = true;
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.comprobarUsuarioServicio] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return exists;
	}
	
	
	/**
	 * Este metodo actualiza el estado de la suscripcion de un usuario a un servicio
	 * 
	 * @param idUsuario el nombre del usuario
	 * @param idServicioMovil identificador del servicio
	 * @param usuario el usuario registrado de la aplicacion 
	 * @param activo es la variable de tipo boolean que indicar� con el valor  <code>0</code> que se trata de una baja
	 * en caso contrario el valor ser� <code>1</code> 
	 * @return
	 */
	public Boolean updateSuscricionServicio(String idUsuario, String idServicioMovil, String usuario, boolean activo){
		Boolean result = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String updateSentence = UPDATE_SUSCRIPCION_USUARIO_SERVICIO+ ((activo == true) ? "1" : "0" ) + " , MODIFICADOPOR= '" +usuario +"' WHERE USUARIOSID = " + idUsuario + " AND SERVICIOSMOVILESID= " + idServicioMovil ;
			rs = stmt.executeQuery(updateSentence);			
			result = true;
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.updateSuscricionServicio] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return result;
	}
	
	/**
	 * Este metodo recupera todos los id de usuario a partir del nombre de usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<String> getIdUsersFromUserName(String idUsuario, String dispositivoId) {
		List<String> usersId = new ArrayList<String>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = this.getConn();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT USUARIOID FROM TBL_USUARIOS_PUSH WHERE NOMBREUSUARIO = '" + idUsuario + "' and "
					+ "DISPOSITIVOID = '" +dispositivoId+"'");
			while (rs.next()) {
				usersId.add(rs.getString("USUARIOID"));
			}
		} catch (SQLException e) {
			logger.debug("[RegistroUsuarioServiciosMovilesDAO.getIdUsersFromUserName] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return usersId;
	}
}
