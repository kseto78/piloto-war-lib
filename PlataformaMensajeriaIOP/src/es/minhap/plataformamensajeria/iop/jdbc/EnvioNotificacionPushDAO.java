package es.minhap.plataformamensajeria.iop.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.UsuariosServiciosMovilesBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;

/**
 * 
 * @author jgonzvil
 *
 */
public class EnvioNotificacionPushDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(EnvioNotificacionPushDAO.class);
	private static final String PROCEDIMIENTO_CREAR_LOTE = "{call PKG_ENVIOS_GENERAL.CREARLOTEMULT(?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_NOTIFICACION_PUSH=
			"{call PKG_ENVIOS_NOTIFICACIONES_PUSH.CREARMENSAJENOT_PUSH_LOTES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private static final String GET_LISTA_USUARIOS_DISPOSITIVOS_SERVICIO ="SELECT US.USUARIOSID FROM TBL_USUARIOS_SERVICIOSMOVILES US , TBL_USUARIOS_PUSH UP "  
	        +  " WHERE UP.NOMBREUSUARIO like ? and UP.SERVICIOID = ? AND US.SERVICIOSMOVILESID = ?  AND (UP.ELIMINADO != 'S' OR UP.ELIMINADO IS NULL)"
	        + "  AND UP.USUARIOID = US.USUARIOSID AND US.ESTADOSUSCRIPCION=1";
	private static final String GET_LISTA_USUARIOS_DISPOSITIVOS = "select USUARIOID from TBL_USUARIOS_PUSH where NOMBREUSUARIO like ? "
			+ "and SERVICIOID = ? AND (ELIMINADO != 'S' OR ELIMINADO IS NULL)";
	private static final String GET_LISTA_USUARIOS_POR_SERVICIO = "SELECT US.USUARIOSID, UP.NOMBREUSUARIO FROM TBL_USUARIOS_SERVICIOSMOVILES US , TBL_USUARIOS_PUSH UP  WHERE  US.SERVICIOSMOVILESID = ? AND UP.USUARIOID = US.USUARIOSID AND US.ESTADOSUSCRIPCION=1";

	private static final String GET_MENSAJE = "select CODIGOEXTERNO from TBL_MENSAJES where MENSAJEID = ? ";
	
	private static String STATUSCODE_KO = "4200";
	
	private static String STATUSTEXT_KO = "KO";
	
	
	private static String STATUSERROR;
	private static String ESTADOINICIAL = "PENDIENTE DE ENVIO";
	
	/**
	 *  
	 * @param servicioId
	 * @param nombreLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearLote(String servicioId, String nombreLote, String usuario, String password, int multidestinatario){
		BigDecimal idLote = null;
		CallableStatement call = null;
		try{
			
			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_LOTE);
			if(servicioId!=null){
				call.setInt(1,new Integer(servicioId));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			
			call.setString(2, nombreLote);
			call.setString(3, usuario);
			call.setString(4, password);
			call.setInt(5, multidestinatario);
			call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idLote =  (BigDecimal) call.getObject(6);
			
		}catch (SQLException e) {
			logger.error("[EnvioNotificacionPushDAO.crearLote] - Error en bbdd", e);
			idLote=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return idLote.intValue();
	}
	
	/**
	 * @param idLote
	 * @param titulo
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param icono
	 * @param sonido
	 * @param idExterno
	 * @param nombreUsuarioDestinatario
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Mensaje crearMensajeNotificacionPush(Integer idLote, String titulo, String cuerpo, String docUsuario, String codSIA, 
			String codOrganismo, String icono, String sonido, String idExterno, String nombreUsuarioDestinatario, String usuario, 
			String password, Integer usuarioId){
		Mensaje mensaje = new Mensaje();
		BigDecimal idSMS = null;
		CallableStatement call = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s ="";
		
		try{
			
			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE_NOTIFICACION_PUSH);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, titulo);
			call.setString(3, cuerpo);
			call.setString(4, docUsuario);
			call.setString(5, codSIA);
			call.setString(6, codOrganismo);
		    call.setString(7, idExterno);
		    call.setString(8, nombreUsuarioDestinatario);
		    call.setString(9, icono);
		    call.setString(10, sonido);
		    call.setString(11, usuario);
		    call.setString(12, password);

		    if(usuarioId!=null){
				call.setInt(13,new Integer(usuarioId));
			}else{
				call.setNull(13,java.sql.Types.INTEGER);
			}
		    
		    call.registerOutParameter(14, OracleTypes.NUMBER);
			call.executeUpdate();
			idSMS =  (BigDecimal) call.getObject(14);
			if (idSMS.intValue()> 0){
				conn = this.getConn();
				s = GET_MENSAJE;
				pst = conn.prepareStatement(s);
				pst.setInt(1, idSMS.intValue());
				rs = pst.executeQuery();
	
				if(rs.next()) {
					mensaje.setIdMensaje(idSMS+"");
					if (rs.getString(1) != null){
						mensaje.setIdExterno(rs.getString(1));
					}else{
						mensaje.setIdExterno("");
					}					
				}
			}else{
				if (WSPlataformaErrors.getErrorCrearSMS(idSMS.intValue()) != null) {
					ResponseStatusType status = new ResponseStatusType();
					status.setStatusCode(STATUSCODE_KO);
					status.setStatusCode(STATUSTEXT_KO);
					status.setDetails(WSPlataformaErrors.getErrorCrearSMS(idSMS.intValue()));
					mensaje.setErrorMensaje(status);
				}
			}
			
		}catch(SQLException ec){
			logger.debug("[EnvioNotificacionPushDAO.crearMensajeNotificacionPush] - Error en bbdd", ec);
			idSMS=new BigDecimal(-10); //error base de datos
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusCode(STATUSTEXT_KO);
			status.setDetails(STATUSERROR);
			mensaje.setErrorMensaje(status);
			
		}finally{
			this.closeSQLObject(rs);
			this.closeSQLObject(call);
			this.closeSQLObject(pst);
			this.closeSQLObject(conn);
		}	
		return mensaje;
	}
	public ArrayList<Integer> getDispositivosUsuario(String identificadorUsuario, Integer servicioID) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		String s;
		try {
			conn = this.getConn();
			s = GET_LISTA_USUARIOS_DISPOSITIVOS;
			pst = conn.prepareStatement(s);
			pst.setString(1, identificadorUsuario);
			pst.setInt(2, servicioID);
			rs = pst.executeQuery();
			
			while (rs.next()){
				res.add(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			logger.debug("[EnvioNotificacionPushDAO.getDispositovosUsuarios] - Error en bbdd obteniendo dispositivos usuario", e);
			res = null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
			this.closeSQLObject(conn);
		}
		return res;
	}
	
	public ArrayList<Integer> getDispositivosUsuarioServicioMovil(String identificadorUsuario, Integer servicioID, Integer idServicioMovil) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		String s;
		try {
			conn = this.getConn();
			s = GET_LISTA_USUARIOS_DISPOSITIVOS_SERVICIO;
			pst = conn.prepareStatement(s);
			pst.setString(1, identificadorUsuario);
			pst.setInt(2, servicioID);
			pst.setInt(3, idServicioMovil);
			rs = pst.executeQuery();
			
			while (rs.next()){
				res.add(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			logger.debug("[EnvioNotificacionPushDAO.getDispositovosUsuarios] - Error en bbdd obteniendo dispositivos usuario", e);
			res = null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
			this.closeSQLObject(conn);
		}
		return res;
	}
	
	
	
	public List<UsuariosServiciosMovilesBean> getUsuarioPorServicio(Integer servicioID) {
		ArrayList<UsuariosServiciosMovilesBean> res = new ArrayList<UsuariosServiciosMovilesBean>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		String s;
		try {
			conn = this.getConn();
			s = GET_LISTA_USUARIOS_POR_SERVICIO;
			pst = conn.prepareStatement(s);
			pst.setInt(1, servicioID);
			rs = pst.executeQuery();
			
			while (rs.next()){
				UsuariosServiciosMovilesBean bean = new UsuariosServiciosMovilesBean();
				bean.setIdUsuario(rs.getInt(1));
				bean.setUsuario(rs.getString(2));
				res.add(bean);
			}
			
		}catch (SQLException e) {
			logger.debug("[EnvioNotificacionPushDAO.getUsuarioPorServicio] - Error en bbdd obteniendo dispositivos usuario", e);
			res = null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
			this.closeSQLObject(conn);
		}
		return res;
	}
	
	
	public void setDestinatarioMensajePUSH(String mensajeId, String destinatario, String codExterno, String codUsuario, Integer usuarioId) throws Exception {

		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String s = null;
		String idExterno = null;
		Integer idDestinatariosMensajes = null;
		try {
			if (null != codExterno)
				idExterno = "'" + codExterno + "'";
			conn = this.getConn();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			s = "INSERT INTO tbl_destinatarios_mensajes" + " (destinatariosmensajes, mensajeId,destinatario,estado,fechacreacion,creadopor,codigoExterno) "
					+ "VALUES(DESTINATARIOSMENSAJES_SEC.NEXTVAL," + Long.parseLong(mensajeId) + ",'" + usuarioId + "','" + ESTADOINICIAL + "',sysdate,'" + codUsuario + "',"
					+ idExterno + ")";

			stmt.executeUpdate(s, new int[] { 1 });
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				BigDecimal a = (BigDecimal) rs.getObject(1);
				idDestinatariosMensajes = a.intValue();
			}
			// insertamos en TBL_HISTORICOS
			stmt.close();
			rs.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid, destinatariosmensajes)" + "VALUES (HISTORICOID_SEC.NEXTVAL," + mensajeId + ", sysdate , 3,"
					+ idDestinatariosMensajes + ")";
			rs = stmt.executeQuery(s);
		} catch (SQLException e) {
			e.printStackTrace();
			if (stmt != null)
				stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			this.closeSQLObject(conn);
		}
	}
}
