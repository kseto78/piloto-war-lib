package es.minhap.plataformamensajeria.iop.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author i-nercya
 *
 */
public class MensajeDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(MensajeDAO.class);
	
	private static final String NOTIFICACION_PUSH= "NOTIFICACION PUSH";
	
	private static final String STATUS_LEIDO = "LEIDO";
	
	private static final String STATUS_RECIBIDO = "RECIBIDO";
	
	private static final String STATUS_ENVIADO = "ENVIADO";
	
	private static final String DESCRIPTION_MESSAGE = "El estado de la notificacion push ha cambiado a ";
	
	private static final String GET_ES_MULTIDESTINATARIO_BY_MENSAJE = "SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
			+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = ? AND lt.MULTIDESTINATARIO = 1";
	private static final String OBTENER_ESTADO_MENSAJES_LOTES = "SELECT ESTADOACTUAL, count(*) as TOTAL from TBL_MENSAJES where "
			+ "MENSAJEID = ? group by ESTADOACTUAL";
	private static final String OBTENER_ESTADO_DESTINATARIOS_MENSAJES = "SELECT ESTADO, count(*) as TOTAL from TBL_DESTINATARIOS_MENSAJES where "
			+ "MENSAJEID = ? group by ESTADO";
	private static final String UPDATE_ESTADO_MENSAJE = "UPDATE TBL_MENSAJES SET ESTADOACTUAL = ? WHERE MENSAJEID = ?";
	
	public Integer getMensajeIDByUIM(String uim){

		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		Integer mensajeId =null; 
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT mensajeid FROM TBL_DESTINATARIOS_MENSAJES WHERE uim = '" + uim+"'");
			if(null!= rs){
				if (rs.next()){
					mensajeId =	rs.getInt(1);
				}else{//se trata de multidest lo buscamos en tbl_destinatariosmensajes
					rs2 = stmt.executeQuery("SELECT mensajeid FROM TBL_MENSAJES WHERE uim = '" + uim+"'");
					if (null != rs2){
						if (rs2.next())
							mensajeId = rs2.getInt(1);
					}
				}
			} 
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getMensajeIDByUIM] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(rs2);
			this.closeSQLObject(stmt);
		}	
		return mensajeId;
	}

	public String getStatusMessage(String idMensaje, List<String> usersId) {

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String sql = "";
		conn = this.getConn();
		String estadoactual = ""; 
		Map<String, Integer> map = new HashMap<String,Integer>();
		try{
			if (null != usersId && usersId.size() > 0 && isMultidestinatario(Integer.parseInt(idMensaje))){
				
				String usuarios = usersId.toString().replace("[", "'").replace("]", "'")
			            .replace(", ", "','");
				sql = "SELECT ESTADO, count(*) as TOTAL from TBL_DESTINATARIOS_MENSAJES where "
						+ "MENSAJEID = "+idMensaje+" and DESTINATARIO in ("+usuarios+")  group by ESTADO";
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				while (rs.next()){
					Integer total = rs.getInt("TOTAL");
					String key = rs.getString("ESTADO");
					map.put(key, total);
				}
				if (map.size()==1)
					estadoactual = map.entrySet().iterator().next().getKey();
				else
					estadoactual = "INCIDENCIA";
			}else{
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("SELECT ESTADOACTUAL FROM TBL_MENSAJES WHERE MENSAJEID = " + idMensaje);
				while(rs.next()){
					estadoactual =	rs.getString(1);
				} 
			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getStatusMessage] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return estadoactual;
	}

	
	public int updateMessageStatus(String idMensaje, String status, List<String> usersId){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String s;
		String estadoMensaje = "";
		int valor = Integer.valueOf(status);
		List<Integer> listaDestinatarios = new ArrayList<Integer>();
		switch (valor) {
		case 1:
			estadoMensaje = STATUS_RECIBIDO;
			break;
		case 2:
			estadoMensaje = STATUS_LEIDO;
			break;
		default:
			estadoMensaje = STATUS_ENVIADO;
			break;
		}
		conn = this.getConn();
		int estado = -1;
		try{
			String usuarios = usersId.toString().replace("[", "'").replace("]", "'")
		            .replace(", ", "','");
			if (null != usersId && usersId.size() > 0 && isMultidestinatario(Integer.parseInt(idMensaje))){
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				s = "UPDATE TBL_DESTINATARIOS_MENSAJES SET ESTADO='" + estadoMensaje +"' WHERE "
						+ "MENSAJEID= " +idMensaje + " AND DESTINATARIO in (" + usuarios+")";
				estado  = stmt.executeUpdate(s);
				stmt.close();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				s = "SELECT DESTINATARIOSMENSAJES from TBL_DESTINATARIOS_MENSAJES where ESTADO='" + estadoMensaje +"' and "
						+ "MENSAJEID= " +idMensaje + " AND DESTINATARIO in (" + usuarios+")";
				rs = stmt.executeQuery(s);
				while (rs.next()){
					listaDestinatarios.add(rs.getInt("DESTINATARIOSMENSAJES"));
				}
				
				actualizarEstadoMensaje(Integer.parseInt(idMensaje));
				actualizarEstadoLote(Integer.parseInt(idMensaje));
				for (Integer d : listaDestinatarios) {
					updateHistoricoMensajes(idMensaje, estadoMensaje, d.toString());
				}
				
			}else{
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				s = "UPDATE TBL_MENSAJES SET ESTADOACTUAL='" + estadoMensaje +"' WHERE MENSAJEID= " +idMensaje;
				estado  = stmt.executeUpdate(s);
				actualizarEstadoLote(Integer.parseInt(idMensaje));
				updateHistoricoMensajes(idMensaje, estadoMensaje, null);
				
			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.updateMessageStatus] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}	
		return estado;
	}

	private void updateHistoricoMensajes(String mensajeId, String estado,String destinatarios)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String s = null;
		String servicioId = null;
		Integer historicoid = null;
		Integer estadoId = null;
		try {
			
			// recuperamos el estadoId a partir del estado 
			stmt = this.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT estadoId  FROM TBL_ESTADOS WHERE NOMBRE='" + estado+"'");
			if (rs.next()) {
				estadoId = rs.getInt(1);
			}
			rs.close();
			stmt.close();

			// recuperamos el servicio asociado al mensaje
			stmt = this.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select serv.SERVICIOID from TBL_LOTESENVIOS lote, TBL_MENSAJES mens, tbl_servicios serv  where lote.LOTEENVIOID = mens.LOTEENVIOID  and lote.SERVICIOID = serv.SERVICIOID  and mens.MENSAJEID="+mensajeId);
			if (rs.next()){
				servicioId=rs.getString(1);
			}
			
			if (servicioId != null && estadoId != null) {
				stmt = this.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if (destinatarios != null) {
					s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion,destinatariosmensajes) ";
					s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate," + mensajeId + ","
							+ estadoId + "," + servicioId + ",'"+DESCRIPTION_MESSAGE + estado + ".',"
							+ destinatarios + ")";
					rs = stmt.executeQuery(s);
				} else {
					s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion) ";
					s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate," + mensajeId + ","
							+ estadoId + "," + servicioId + ",'"+DESCRIPTION_MESSAGE+ estado + ".')";
					rs = stmt.executeQuery(s);
				}
			}
		} catch (SQLException e) {
			logger.debug(
					"[MensajesDAO.updateHistoricoMensajes] - Error en bbdd actualizando el historico de mensajes",
					e);
		} finally {
			closeSQLObject(stmt);
			closeSQLObject(rs);
		}
	}
	
	private void actualizarEstadoMensaje(int idMensaje) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		Map<String, Integer> map = new HashMap<String,Integer>();
		String estado;
		Statement stmt = null;
		try {
			conn = this.getConn();
			s = OBTENER_ESTADO_DESTINATARIOS_MENSAJES;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idMensaje);
			rs = pst.executeQuery();
			
			while (rs.next()){
				Integer total = rs.getInt("TOTAL");
				String key = rs.getString("ESTADO");
				map.put(key, total);
			}
			if (map.size()==1)
				estado = map.entrySet().iterator().next().getKey();
			else
				estado = "INCIDENCIA";
			pst.close();
			rs.close();			
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE TBL_MENSAJES SET ESTADOACTUAL = '"+estado+"' WHERE MENSAJEID = "+idMensaje;
			stmt.executeUpdate(sql);	
			
		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoLote] - Error en bbdd actualizando estado Lotes",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.actualizarEstadoLote] - El mensajeId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
			this.closeSQLObject(stmt);
		}
	}

	public Boolean checkIsPushNotification(String idMensaje){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		String tipoMensaje = "";
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT TIPOMENSAJE FROM TBL_MENSAJES WHERE MENSAJEID = " + idMensaje);
			while(rs.next()){
				tipoMensaje = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.checkIsPushNotification] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);

		}	
		return NOTIFICACION_PUSH.equalsIgnoreCase(tipoMensaje);
	}
	
	public List<String> getIdUsersFromUserName(String idUsuario){
		List<String> usersId = new ArrayList<String>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID = '" + idUsuario +"'");
			while (rs.next()) {
				usersId.add(rs.getString("USUARIOID"));
			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getIdUsersFromUserName] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return usersId;
	}
	
	public Boolean checkIsNotificationPushUser(String idUsuario){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		boolean usuarioid = false;
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT USUARIOID FROM TBL_USUARIOS_PUSH WHERE USUARIOID = " + idUsuario);
			while(rs.next()){
				usuarioid = true; 
			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.checkIsNotificationPushUser] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return usuarioid;
	}
	
	public boolean isMultidestinatario(Integer idMensaje) {
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
					"[MensajeDAO.isMultidestinatario] - Error en bbdd obteniendo si es Multidestinatario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[MensajeDAO.isMultidestinatario] - La mensajeId introducido no es correcto", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
	private void actualizarEstadoLote(Integer idMensaje) {
		Connection conn = null;
		PreparedStatement pst = null;
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		Map<String, Integer> map = new HashMap<String,Integer>();
		String estado;
		try {
			conn = this.getConn();
			s = OBTENER_ESTADO_MENSAJES_LOTES;
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
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE tbl_lotesEnvios set estadoenvioid = (select ESTADOID from TBL_ESTADOS where "
					+ "NOMBRE like '"+estado+"%') where LOTEENVIOID = (select loteenvioid "
					+ "from tbl_mensajes where mensajeid = "+idMensaje+" )";
			stmt.executeUpdate(sql);		
			
		} catch (SQLException e) {
			logger.debug(
					"[MensajeDAO.actualizarEstadoLote] - Error en bbdd actualizando estado Lotes",e);	
		} catch (NumberFormatException e) {
			logger.debug(
					"[MensajeDAO.actualizarEstadoLote] - El mensajeId introducido no es correcto", e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
			this.closeSQLObject(stmt);
		}
	}

	public BigDecimal getDestinatarioMensajeByUim_Mensaje(Integer mensajeID,
			String uim) {
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		BigDecimal res =null; 
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT destinatariosmensajes FROM TBL_DESTINATARIOS_MENSAJES WHERE uim = '" + uim+"'" +
			" and mensajeId = " + mensajeID);
			
			if (rs.next()){
					res = new BigDecimal(rs.getInt(1));
			} 
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getMensajeIDByUIM] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(rs2);
			this.closeSQLObject(stmt);
		}	
		return res;
	}

	public String getUrlPremium(Integer mensajeID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		String res =""; 
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT URL FROM TBL_URL_MENSAJE_PREMIUM WHERE MENSAJE_ID =" + mensajeID);
			
			if (rs.next()){
					res = rs.getString("URL");
			} 
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getUrlPremium] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return res;
	}

	public boolean isMessageUser(List<String> usersId, String idMensaje) {
		boolean res = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String sql = "";
		conn = this.getConn();
		
		try{
			String usuarios = usersId.toString().replace("[", "'").replace("]", "'")
		            .replace(", ", "','");
			
			if (isMultidestinatario(Integer.parseInt(idMensaje))){
				sql = "SELECT * from TBL_DESTINATARIOS_MENSAJES where MENSAJEID = "+idMensaje+" and "
						+ "destinatario in ("+usuarios+")";
			}else{
				sql = "SELECT * from TBL_MENSAJES where MENSAJEID = "+idMensaje+" and "
						+ "usuarioID in ("+usuarios+")";
			}			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);			
			if (rs.next()){
					return true;
			} 
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getUrlPremium] - Error en bbdd", e);
			return false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		
		return res;
		
	}

	public int updateMessagesUsers(List<String> usersId, String estadoInicial, String estadoFinal) {
		int res = -1;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String sql = "";
		conn = this.getConn();
		Map<Integer,List<Integer>> mapMensajesMult = new HashMap<Integer, List<Integer>>();
		List<Integer> listaMensajes = new ArrayList<Integer>();
		
		try{
			logger.info("Inicio Actualizacion de estados notificaciones de "+estadoInicial+" a "+estadoFinal);
			
			String usuarios = usersId.toString().replace("[", "'").replace("]", "'")
		            .replace(", ", "','");
			
			//recuperamos los mensajes multides
			sql = getQuery(usuarios, estadoInicial, true);
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer m = rs.getInt("MENSAJEID");
				Integer dm = rs.getInt("DESTINATARIOSMENSAJES");
				if (mapMensajesMult.containsKey(m)){
					mapMensajesMult.get(m).add(dm);
				}else{
					List<Integer> list = new ArrayList<Integer>();
					list.add(dm);
					mapMensajesMult.put(m, list);
				}
			}
			stmt.close();
			rs.close();
			
			if (!mapMensajesMult.isEmpty()) {
				//actualizamos los mensajes multidest a Recibido y son multidestinatarios
				listaMensajes = new ArrayList<Integer>(mapMensajesMult.keySet());
				setEstadoDestinatariosMensajes(listaMensajes, usuarios,estadoInicial, estadoFinal);
				setEstadoMensajesMult(listaMensajes);
				setEstadoLotes(listaMensajes);
				setHistoricoMensajesMultidest(mapMensajesMult,estadoFinal);
				
				
				//recuperamos mensajes no MULTIDESTINATARIO
				listaMensajes.clear();
				listaMensajes = new ArrayList<Integer>();
				sql = getQuery(usuarios,estadoInicial, false);
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					listaMensajes.add(rs.getInt("MENSAJEID"));
				}
				if(!listaMensajes.isEmpty()) {
					setEstadoMensajes(listaMensajes, estadoFinal);
					setEstadoLotes(listaMensajes);
					setHistoricoMensajes(listaMensajes,estadoFinal);
				}
				
			}
			logger.info("Fin Actualizacion de estados notificaciones de "+estadoInicial+" a "+estadoFinal);
			res = 1;
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getUrlPremium] - Error en bbdd", e);
			
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		
		return res;
	}

	private void setEstadoDestinatariosMensajes(List<Integer> listaMensajes, String usuarios, String estadoInicial, String estadoFinal) {
		Statement stmt = null;
		Connection conn = null;
		String sql = "";
		conn = this.getConn();
		
		try{
			for (Integer m : listaMensajes) {
				sql ="UPDATE TBL_DESTINATARIOS_MENSAJES SET ESTADO='" + estadoFinal +"' WHERE "
						+ "MENSAJEID= " +m + " AND DESTINATARIO in (" + usuarios+") and ESTADO = '"+estadoInicial+"'";
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.executeUpdate(sql);	
				stmt.close();
			}
				
			
			
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.getUrlPremium] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(stmt);
		}	
	}

	private void setHistoricoMensajesMultidest(Map<Integer, List<Integer>> mapMensajesMult, String estadoFinal) {
		try {
			for (Integer key : mapMensajesMult.keySet()) {
				List<Integer> listaDestinatarios = mapMensajesMult.get(key);
				for (Integer d : listaDestinatarios) {
					updateHistoricoMensajes(key.toString(), estadoFinal, d.toString());
				}

			}
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.setHistoricoMensajes] - Error en bbdd", e);
		} 
	}

	private void setHistoricoMensajes(List<Integer> listaMensajes, String estadoFinal) {
		try {
			for (Integer idMensaje : listaMensajes) {
				updateHistoricoMensajes(idMensaje.toString(), estadoFinal, null);
			}		
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.setHistoricoMensajes] - Error en bbdd", e);
		} 
	}
	
	private void setEstadoMensajes(List<Integer> listaMensajes, String estado) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String sql = "";
		conn = this.getConn();
		
		try{
			String mensajes = listaMensajes.toString().replace("[", "").replace("]", "");
			
			sql ="update TBL_MENSAJES set estadoActual = '"+estado+"' where MENSAJEID in ("+mensajes+")";	
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);		
			
			
		} catch (SQLException e) {
			logger.debug("[MensajeDAO.setEstadoMensajes] - Error en bbdd", e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		
	}
	
	private void setEstadoMensajesMult(List<Integer> listaMensajes) {

		for (Integer idMensaje : listaMensajes) {
			actualizarEstadoMensaje(idMensaje);
		}

	}
	
	private void setEstadoLotes(List<Integer> listaMensajes) {
		//No se indica el estado Espec�fico pues un lote puede tener varios mensajes y no todos
		//pueden estar en el mismo estado
		for (Integer idMensaje : listaMensajes) {
			actualizarEstadoLote(idMensaje);
		}
					
	}

	private String getQuery(String usersId, String estado, boolean multidestinatario) {
		String sql = "";

		if (multidestinatario)
			sql = "SELECT dm.MENSAJEID,dm.DESTINATARIOSMENSAJES from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_MENSAJES m " + "on dm.MENSAJEID = m.MENSAJEID inner join TBL_LOTESENVIOS l on "
				+ "l.LOTEENVIOID = m.LOTEENVIOID where dm.DESTINATARIO in (" + usersId + ") and " + "l.MULTIDESTINATARIO = 1 and m.TIPOMENSAJE like '%PUSH%' and dm.ESTADO = '"
				+ estado + "'";
		
		else
			sql = "SELECT m.MENSAJEID from TBL_MENSAJES m inner join TBL_LOTESENVIOS l on "
					+ "l.LOTEENVIOID = m.LOTEENVIOID where m.USUARIOID in  (" + usersId + ") and " + "l.MULTIDESTINATARIO = 0 and m.TIPOMENSAJE like '%PUSH%' and m.ESTADOACTUAL = '"
					+ estado + "'";
		return sql;
	}
}