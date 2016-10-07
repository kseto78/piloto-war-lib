package es.minhap.plataformamensajeria.iop.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;

/**
 * 
 * @author i-nercya
 *
 */
public class OperacionesMensajesDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(OperacionesMensajesDAO.class);
	private static final String PROCEDIMIENTO_OPERACION_REENVIARLOTE = "{call PKG_OPERACIONES_SEGUIMIENTO.REENVIARLOTE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_ANULARLOTE = "{call PKG_OPERACIONES_SEGUIMIENTO.ANULARLOTELOTES(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_REENVIARMENSAJE = "{call PKG_OPERACIONES_SEGUIMIENTO.REENVIARMENSAJE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_ANULARMENSAJE = "{call PKG_OPERACIONES_SEGUIMIENTO.ANULARMENSAJE(?,?,?,?)}";
	private static final String GET_ES_MULTIDESTINATARIO_BY_MENSAJE = "SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
								+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = ? AND lt.MULTIDESTINATARIO = 1";
	private static final String UPDATE_DESTINATARIOS_MENSAJES = "Update TBL_DESTINATARIOS_MENSAJES set estado = ? where mensajeID = ?";
	private static final String UPDATE_ESTADO_LOTE = "UPDATE tbl_lotesEnvios set estadoenvioid = (select ESTADOID from TBL_ESTADOS where "
								+ "NOMBRE like ?) where LOTEENVIOID = (select loteenvioid "
								+ "from tbl_mensajes where mensajeid = ? )";
	private static final String GET_ES_MULTIDESTINATARIO_BY_LOTE = "SELECT MULTIDESTINATARIO FROM TBL_LOTESENVIOS where LOTEENVIOID = ? "
								+ "AND MULTIDESTINATARIO = 1";
	private static final String UPDATE_DESTINATARIOS_MENSAJES_LOTE = "Update TBL_DESTINATARIOS_MENSAJES set estado = ? where mensajeID in "
			+ "(select MENSAJEID from TBL_MENSAJES where LOTEENVIOID = ?)";
	private static final String UPDATE_ESTADO_MENSAJE = "UPDATE tbl_lotesEnvios set estadoenvioid = (select ESTADOID from TBL_ESTADOS where "
			+ "NOMBRE like ?) where LOTEENVIOID = ? ";
	private static final String OBTENER_ESTADO_MENSAJES = "SELECT ESTADOACTUAL, count(*) as TOTAL "
			+ "from TBL_MENSAJES where LOTEENVIOID = (select LOTEENVIOID from TBL_MENSAJES "
			+ "where MENSAJEID = ?) group by ESTADOACTUAL";
	private static final String GET_ESTADOMENSAJE_BY_LOTE = "SELECT ESTADOACTUAL FROM TBL_MENSAJES where LOTEENVIOID = ?";
	
	/**
	 * 
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	public BigDecimal reenviarLote(Integer idLote, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_OPERACION_REENVIARLOTE);
			if(idLote!=null){
				call.setInt(1,idLote);
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);

			if (confirmacion.intValue() >= 0 && isMultidestinatarioLote(idLote)){
				//actualizamos destinatariosMensajes
				actualizarDestinatariosLote(idLote,"PENDIENTE DE ENVIO");
				actualizarEstadoMensajesLote(idLote, "PENDIENTE DE ENVIO");	
				actualizarHistoricosLote(idLote, "PENDIENTE DE ENVIO");
			}else{
				actualizarEstadoMensajesLote(idLote, "ANULADO");
			}

		}catch (SQLException e) {
			logger.error("[OperacionesMensajesDAO.reenviarLote] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
	/**
	 * 
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	public BigDecimal anularLote(Integer idLote, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_OPERACION_ANULARLOTE);
			if(idLote!=null){
				call.setInt(1,idLote);
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			int n = confirmacion.intValue();
			
			if (confirmacion.intValue() >= 0 && isMultidestinatarioLote(idLote)){
				//actualizamos destinatariosMensajes
				if (estadoMensajeLote(idLote).equals("ANULADO")){
					actualizarDestinatariosLote(idLote,"ANULADO");
					actualizarEstadoMensajesLote(idLote, "ANULADO");
					actualizarHistoricosLote(idLote, "ANULADO");
				}
			}else{
				actualizarEstadoMensajesLote(idLote, "ANULADO");
			}

		}catch (SQLException e) {
			logger.error("[OperacionesMensajesDAO.anularLote] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
	private void actualizarHistoricosLote(Integer idLote, String estado) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		Statement stmt1 = null;
		String s = null;
		Connection conn = null;
		Integer idEstado = null;
		
		conn = this.getConn();
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			s = "SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE ='"+estado+"'";
			rs = stmt.executeQuery(s);
			if (rs.next())
				idEstado = rs.getInt("ESTADOID");
			
			
			stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			s = "SELECT DESTINATARIOSMENSAJES, MENSAJEID FROM TBL_DESTINATARIOS_MENSAJES WHERE MENSAJEID "
					+ "in ((select MENSAJEID FROM TBL_MENSAJES where LOTEENVIOID ="+idLote+"))";
			rs1 = stmt1.executeQuery(s);
			while( rs1.next()) {
				s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,destinatariosmensajes) VALUES "
						+ "(HISTORICOID_SEC.NEXTVAL,"+rs1.getInt("MENSAJEID")+",sysdate, "+idEstado+","+rs1.getInt("DESTINATARIOSMENSAJES")+")";
				stmt.executeUpdate(s);
			}
		}catch(SQLException e){
			logger.debug("[EnvioMensajesPremiumDAO.crearMensaje] - Error en bbdd",e);
			
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs1);
			this.closeSQLObject(stmt1);
			//this.closeSQLObject(conn);
		}			
	}
	private String estadoMensajeLote(Integer idLote) {
		String res = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_ESTADOMENSAJE_BY_LOTE;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idLote);
			rs = pst.executeQuery();

			if (rs.next()){
					res = rs.getString("ESTADOACTUAL");
			}

		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatarioLote] - Error en bbdd obteniendo si es Multidestinatario",e);
			
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - La loteId introducido no es correcto", e);
		
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
	
	/**
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @return
	 */
	public BigDecimal reenviarMensaje(Integer idMensaje, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_OPERACION_REENVIARMENSAJE);
			if(idMensaje!=null){
				call.setInt(1,idMensaje);
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			
			if (confirmacion.intValue() >= 0 && isMultidestinatario(idMensaje)){
				//actualizamos destinatariosMensajes
				actualizarDestinatarios(idMensaje,"PENDIENTE DE ENVIO");
				actualizarEstadoLote(idMensaje);
				actualizarHistoricosMensaje(idMensaje, "PENDIENTE DE ENVIO");
			}else{
				actualizarEstadoLote(idMensaje);
			}

		}catch (SQLException e) {
			logger.error("[OperacionesMensajesDAO.reenviarMensaje] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
	private void actualizarHistoricosMensaje(Integer idMensaje, String estado) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		Statement stmt1 = null;
		String s = null;
		Connection conn = null;
		Integer idEstado = null;
		
		conn = this.getConn();
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			s = "SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE ='"+estado+"'";
			rs = stmt.executeQuery(s);
			if (rs.next())
				idEstado = rs.getInt("ESTADOID");
			
			
			stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			s = "SELECT DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES WHERE MENSAJEID="+idMensaje+"";
			rs1 = stmt1.executeQuery(s);
			while( rs1.next()) {
				s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,destinatariosmensajes) VALUES "
						+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate, "+idEstado+","+rs1.getInt("DESTINATARIOSMENSAJES")+")";
				stmt.executeUpdate(s);
			}
		}catch(SQLException e){
			logger.debug("[EnvioMensajesPremiumDAO.crearMensaje] - Error en bbdd",e);
			
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs1);
			this.closeSQLObject(stmt1);
			//this.closeSQLObject(conn);
		}			
	}
	/**
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @return
	 */
	public BigDecimal anularMensaje(Integer idMensaje, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_OPERACION_ANULARMENSAJE);
			if(idMensaje!=null){
				call.setInt(1,idMensaje);
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);

			if (confirmacion.intValue() >= 0 && isMultidestinatario(idMensaje)){
				//actualizamos destinatariosMensajes
				actualizarDestinatarios(idMensaje,"ANULADO");
				actualizarEstadoLote(idMensaje);
				actualizarHistoricosMensaje(idMensaje, "ANULADO");
			}else{
				actualizarEstadoLote(idMensaje);
			}
			
		}catch (SQLException e) {
			logger.error("[OperacionesMensajesDAO.anularMensaje] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}
		finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
	private void actualizarEstadoLote(Integer idMensaje) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		Map<String, Integer> map = new HashMap<String,Integer>();
		String estado;
		try {
			conn = this.getConn();
			s = OBTENER_ESTADO_MENSAJES;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idMensaje);
			rs = pst.executeQuery();
			
			while (rs.next()){
				Integer total = rs.getInt("TOTAL");
				String key = rs.getString("ESTADOACTUAL");
				map.put(key, total);
			}
			if (map.size()==1)
				estado = map.entrySet().iterator().next().getKey();
			else
				estado = "INCIDENCIA";
			pst.close();
			rs.close();
			
			if (estado.equals("PENDIENTE"))
				estado = "PENDIENTE DE ENVIO";
						
			s = UPDATE_ESTADO_LOTE;
			pst = conn.prepareStatement(s);
			pst.setString(1, estado+"%");
			pst.setInt(2, idMensaje);
			rs = pst.executeQuery();
			
			
		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoLote] - Error en bbdd actualizando estado Lotes",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoLote] - El mensajeId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
	}
	
	private void actualizarEstadoMensajesLote(Integer idLote, String estado) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = UPDATE_ESTADO_MENSAJE;
			pst = conn.prepareStatement(s);
			pst.setString(1, estado);
			pst.setInt(2, idLote);
			rs = pst.executeQuery();
			
			
		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoMensajesLote] - Error en bbdd actualizando estado Mensajes",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoMensajesLote] - El loteId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
	}
	
	
	private void actualizarDestinatarios(Integer idMensaje, String nuevoEstado) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = UPDATE_DESTINATARIOS_MENSAJES;
			pst = conn.prepareStatement(s);
			pst.setString(1, nuevoEstado);
			pst.setInt(2, idMensaje);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarDestinatarios] - Error en bbdd obteniendo si es Multidestinatario",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarDestinatarios] - La mensajeId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		
	}
	
	private void actualizarDestinatariosLote(Integer idLote, String nuevoEstado) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = UPDATE_DESTINATARIOS_MENSAJES_LOTE;
			pst = conn.prepareStatement(s);
			pst.setString(1, nuevoEstado);
			pst.setInt(2, idLote);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarDestinatariosLote] - Error en bbdd actualizando destinatarios Lote",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarDestinatariosLote] - El LoteId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		
	}
	private boolean isMultidestinatario(Integer idMensaje) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_ES_MULTIDESTINATARIO_BY_MENSAJE;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idMensaje);
			rs = pst.executeQuery();

			if (rs.next()){
					return true;
			}

		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - Error en bbdd obteniendo si es Multidestinatario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - La mensajeId introducido no es correcto", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
	
	private boolean isMultidestinatarioLote(Integer idLote) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_ES_MULTIDESTINATARIO_BY_LOTE;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idLote);
			rs = pst.executeQuery();

			if (rs.next()){
					return true;
			}

		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatarioLote] - Error en bbdd obteniendo si es Multidestinatario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - La loteId introducido no es correcto", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
}
