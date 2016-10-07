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

import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;

/**
 * 
 * @author jgonzvil
 * 
 */
public class UsuariosPushDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(UsuariosPushDAO.class);
	private static final String PROCEDIMIENTO_ALTA_USUARIO_PUSH = "{call PKG_USUARIOS_PUSH.ALTAUSUARIO(?,?,?,?,?,?,?,?)}";
	private static final String NEXT_VAL_SECUENCIA_DISPOSITIVO = "select DISPOSITIVO_SEC.NEXTVAL from dual";
	private static final String GET_PLATAFORMAS_NOTIFICACIONES = "select * from TBL_PLATAFORMAS where PLATAFORMAID = ? ";
	private static final String PROCEDIMIENTO_CONTROL_USUARIO = "Select aplicacionid from tbl_aplicaciones where password = (utl_raw.cast_to_varchar2(UTL_ENCODE.BASE64_enCODE(UTL_RAW.CAST_TO_RAW('?')))) and usuario = '?'";
	private static final String LISTA_ESTADOS_SELECCIONADOS = "'ENVIADO', 'LEIDO', 'RECIBIDO'";
	private static final String ESTADO_ENVIADO = "ENVIADO";
	private static final String ESTADO_RECIBIDO = "RECIBIDO";
	/**
	 * 
	 * @param recipiente
	 * @return
	 */
	public Integer altaUsuario(String nombreUsuario, String servicioId,
			String usuario, String password, String plataformaId,
			String tokenUsuario, String dispositivoId) {
		BigDecimal result = null;
		CallableStatement call = null;
		try {
			
			if (plataformaCorrecta(plataformaId)){
			
				call = this.getConn().prepareCall(PROCEDIMIENTO_ALTA_USUARIO_PUSH);
	
				call.setString(1, nombreUsuario);
				call.setString(2, servicioId);
				call.setString(3, usuario);
				call.setString(4, password);
				call.setString(5, plataformaId);
				call.setString(6, tokenUsuario);
				call.setString(7, dispositivoId);
				call.registerOutParameter(8, OracleTypes.NUMBER);
				call.executeUpdate();
	
				result = (BigDecimal) call.getObject(8);
			}else{
				result = new BigDecimal(-5);
			}
		} catch (SQLException e) {
			logger.debug("[UsuarioPushDAO.altaUsuario] - Error en bbdd", e);
			result = null;
		} finally {
			this.closeSQLObject(call);
		}

		if (null != result) {
			return result.intValue();
		} else {
			return Integer.valueOf(-1);
		}

	}

	private boolean plataformaCorrecta(String plataformaId) {

		boolean res = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_PLATAFORMAS_NOTIFICACIONES;
			pst = conn.prepareStatement(s);
			pst.setInt(1, Integer.parseInt(plataformaId));
			rs = pst.executeQuery();

			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			logger.debug(
					"[UsuarioPushDAO.PlataformaCorrecta] - Error en bbdd obteniendo dispositivos usuario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[UsuarioPushDAO.PlataformaCorrecta] - La plataformaId introducida no es correcta", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;

	}

	public Integer getSecuencia() {
		Integer res = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = NEXT_VAL_SECUENCIA_DISPOSITIVO;
			pst = conn.prepareStatement(s);
			synchronized (this) {
				rs = pst.executeQuery();
				if (rs.next())
					res = rs.getInt(1);
			}
		}catch (SQLException e) {
			logger.debug("[UsuarioPushDAO.getSecuencia] - Error en bbdd ", e);
			res = null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
	public Integer loginUsuario(String username,String password){
		CallableStatement call = null;
		BigDecimal result = null;
		try {
			call = getConn().prepareCall(PROCEDIMIENTO_CONTROL_USUARIO);

			call.setString(1, username);
			call.setString(2, password);
			call.registerOutParameter(8, OracleTypes.NUMBER);
			call.executeQuery();

			result = (BigDecimal) call.getObject(8);

		} catch (SQLException e) {
			logger.debug("[UsuarioPushDAO.loginUsuario] - Error en bbdd", e);
			result = null;
		} finally {
			this.closeSQLObject(call);
		}

		if (null != result) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public UsuariosPushBean getDatosUsuario(String idServicio, String idPlataforma,String dispositivoId, String nombreUsuario) {
		UsuariosPushBean res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM TBL_USUARIOS_PUSH where  NOMBREUSUARIO = '" +nombreUsuario +"' and "
					+ "DISPOSITIVOID = '"+dispositivoId + "' and SERVICIOID = " + idServicio + " and PLATAFORMAID = "
					+ idPlataforma + "");
			if(rs.next()){
				res = new UsuariosPushBean();
				res.setUsuarioId(rs.getInt("USUARIOID"));
				res.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				res.setDispositivoId(rs.getString("DISPOSITIVOID"));
				res.setPlataformaId(rs.getInt("PLATAFORMAID"));
				res.setServicioId(rs.getInt("SERVICIOID"));
				res.setTokenUsuario(rs.getString("TOKENUSUARIO"));
				res.setNombre(rs.getString("NOMBRE"));
				res.setApellido1(rs.getString("APELLIDO1"));
				res.setApellido2(rs.getString("APELLIDO2"));
			}
		} catch (SQLException e) {
			logger.debug("[UsuarioPushDAO.getDatosUsuario] - Error en bbdd",e);
			return null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}

	public List<Integer> getDatosUsuario(String idServicio, String idPlataforma,String dispositivoId) {
		List<Integer> res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT USUARIOID FROM TBL_USUARIOS_PUSH where  DISPOSITIVOID = '"+dispositivoId + "' and SERVICIOID = " + idServicio + " and PLATAFORMAID = "
					+ idPlataforma + "");
			res = new ArrayList<Integer>();
			while(rs.next()){
				res.add(rs.getInt("USUARIOID"));
				
			}
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.getDatosUsuario] - Error en bbdd",e);
			return null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}
	
	public List<UsuariosPushBean> getListaUsuarios(String idServicio, String idPlataforma,String dispositivoId) {
		List<UsuariosPushBean> res = new ArrayList<>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM TBL_USUARIOS_PUSH where  NOMBREUSUARIO != '"+dispositivoId+"' and "
					+ "DISPOSITIVOID = '"+dispositivoId + "' and SERVICIOID = " + idServicio + " and PLATAFORMAID = "
					+ idPlataforma + "");
			while(rs.next()){
				UsuariosPushBean usuario = new UsuariosPushBean();
				usuario.setUsuarioId(rs.getInt("USUARIOID"));
				usuario.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				usuario.setDispositivoId(rs.getString("DISPOSITIVOID"));
				usuario.setPlataformaId(rs.getInt("PLATAFORMAID"));
				usuario.setServicioId(rs.getInt("SERVICIOID"));
				usuario.setTokenUsuario(rs.getString("TOKENUSUARIO"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setApellido1(rs.getString("APELLIDO1"));
				usuario.setApellido2(rs.getString("APELLIDO2"));
				res.add(usuario);
			}
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.getListaUsuarios] - Error en bbdd",e);
			return null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}
	
	public boolean actualizarUsuario(UsuariosPushBean usuarioBean) {
		boolean res = true;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String s ="";
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_USUARIOS_PUSH set"
					+ " NOMBRE ='"+usuarioBean.getNombre()+ "',"
					+ " APELLIDO1 = '"+usuarioBean.getApellido1()+"',"
					+ " APELLIDO2 = '"+usuarioBean.getApellido2()+"',"
					+ " FECHAMODIFICACION = SYSDATE where USUARIOID = "+usuarioBean.getUsuarioId();
			
			rs = stmt.executeQuery(s);
			
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.actualizarUsuario] - Error en bbdd",e);
			return false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}

	public UsuariosPushBean getUsuarioPorDispositivo(String dispositivoId) {
		UsuariosPushBean res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM TBL_USUARIOS_PUSH where "
					+ "DISPOSITIVOID = '"+dispositivoId+"'");
			if(rs.next()){
				res = new UsuariosPushBean();
				res.setUsuarioId(rs.getInt("USUARIOID"));
				res.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				res.setDispositivoId(rs.getString("DISPOSITIVOID"));
				res.setPlataformaId(rs.getInt("PLATAFORMAID"));
				res.setServicioId(rs.getInt("SERVICIOID"));
				res.setTokenUsuario(rs.getString("TOKENUSUARIO"));
				res.setNombre(rs.getString("NOMBRE"));
				res.setApellido1(rs.getString("APELLIDO1"));
				res.setApellido2(rs.getString("APELLIDO2"));
			}
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.getUsuarioPorDispositivo] - Error en bbdd",e);
			return null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}

	public boolean insertarUsuarioPush(UsuariosPushBean usuario) {
		boolean res = true;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String s ="";
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			s = " INSERT INTO TBL_USUARIOS_PUSH(usuarioid,nombreusuario,servicioid,plataformaid,fechacreacion,"
					+ "tokenusuario,dispositivoid,nombre,apellido1,apellido2) VALUES "
					+ "(USUARIOS_PUSH_SEC.NEXTVAL,'"+usuario.getNombreUsuario()+"',"
							+ ""+usuario.getServicioId()+","+usuario.getPlataformaId()+",sysdate,"
							+ " '"+usuario.getTokenUsuario()+"','"+usuario.getDispositivoId()+"',"
							+ "'"+usuario.getNombre()+"','"+usuario.getApellido1()+"','"+usuario.getApellido2()+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.insertarUsuarioPush] - Error en bbdd",e);
			return false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}

	public UsuariosPushBean comprobarDispositivoAplicacion(String idServicio, String idDispositivo, String idPlataforma) {
		UsuariosPushBean res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;

		conn = this.getConn();

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM TBL_USUARIOS_PUSH where " + "DISPOSITIVOID = '" + idDispositivo + "' and SERVICIOID = '" + idServicio + "' and PLATAFORMAID = '"
					+ idPlataforma + "'");
			if (rs.next()) {

				res = new UsuariosPushBean();
				res.setUsuarioId(rs.getInt("USUARIOID"));
				res.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				res.setDispositivoId(rs.getString("DISPOSITIVOID"));
				res.setPlataformaId(rs.getInt("PLATAFORMAID"));
				res.setServicioId(rs.getInt("SERVICIOID"));
				res.setTokenUsuario(rs.getString("TOKENUSUARIO"));
				res.setNombre(rs.getString("NOMBRE"));
				res.setApellido1(rs.getString("APELLIDO1"));
				res.setApellido2(rs.getString("APELLIDO2"));

			}
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.comprobarDispositivoAplicacion] - Error en bbdd", e);
			return null;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			// this.closeSQLObject(conn);
		}
		return res;
	}

	public boolean existeUsuario(String idServicio, String idDispositivo, String idPlataforma, String docUsuario) {
		boolean res = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;

		conn = this.getConn();
		String sql = "";

		try {
			if (null == docUsuario)
				sql = "SELECT * FROM TBL_USUARIOS_PUSH where " + "DISPOSITIVOID = '" + idDispositivo + "' and SERVICIOID = '" + idServicio + "' and PLATAFORMAID = '"
						+ idPlataforma + "'";
			else
				sql="SELECT * FROM TBL_USUARIOS_PUSH where " + "DISPOSITIVOID = '" + idDispositivo + "' and SERVICIOID = '" + idServicio + "' and PLATAFORMAID = '"
						+ idPlataforma + "' and NOMBREUSUARIO ='"+docUsuario+"'";
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.existeUsuario] - Error en bbdd", e);
			return res;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			// this.closeSQLObject(conn);
		}
		return res;
	}

	public List<Aviso> getAvisosUsuario(String idDispositivo, String idPlataforma, String idServicio, String idUsuario, String numPagina, String tamPagina) {
		List<Aviso> res = new ArrayList<Aviso>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		List<Integer> listaUsuarios = new ArrayList<Integer>();
		try {
			//Buscamos los usaurios
			if (idUsuario != null)
				listaUsuarios.add(getDatosUsuario(idServicio, idPlataforma, idDispositivo, idUsuario).getUsuarioId());
			else
				listaUsuarios = getDatosUsuario(idServicio, idPlataforma, idDispositivo);
//			listaUsuarios.add(407);
			
			//buscamos los mensajes de los usuarios
			String usuarios = listaUsuarios.toString().replace("[", "'").replace("]", "'")
		            .replace(", ", "','");
			
			//obtenemos los avisos de los mensajes antiguos en TBL_MENSAJES
			String sql = getQuery(usuarios, numPagina, tamPagina);
							
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Aviso a = new Aviso();
				a.setCuerpo(rs.getString("CUERPO"));
				String estado = rs.getString("ESTADO");
				if (estado.equals(ESTADO_ENVIADO))
					a.setEstado("0");
				else if (estado.equals(ESTADO_RECIBIDO))
					a.setEstado("1");
				else 
					a.setEstado("2");
				a.setFechaEstado(rs.getString("ULTIMOENVIO"));
				Integer msg = rs.getInt("MENSAJEID");
				a.setIdAviso(msg.toString());
				a.setTitulo(rs.getString("CABECERA"));
				res.add(a);
			}
			
			
			
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.getAvisosUsuario] - Error en bbdd", e);
			return res;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			// this.closeSQLObject(conn);
		}
		return res;
	}
	
	private String getQuery(String usuarios, String numPagina, String tamPagina){
		String sql ="";
		Integer pag = Integer.parseInt(tamPagina);
		Integer num = Integer.parseInt(numPagina);
				
		if (num <= 0)
			sql = "SELECT * FROM ((SELECT m.MENSAJEID as MENSAJEID,m.CABECERA as CABECERA,"
					+ "m.CUERPO as CUERPO,m.ESTADOACTUAL as ESTADO, "
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = m.MENSAJEID AND T2.ESTADOID IN (1,7)) AS ULTIMOENVIO "
					+ "FROM TBL_MENSAJES m INNER JOIN TBL_LOTESENVIOS l ON l.LOTEENVIOID = m.LOTEENVIOID"
					+ " WHERE m.USUARIOID IN ("+usuarios+") AND m.ESTADOACTUAL IN ("+LISTA_ESTADOS_SELECCIONADOS+") "
					+ "AND l.MULTIDESTINATARIO = 0) UNION ALL(SELECT m.MENSAJEID as MENSAJEID, "
					+ "m.CABECERA as CABECERA,m.CUERPO as CUERPO, dm.ESTADO as ESTADO,"
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = dm.MENSAJEID AND T2.ESTADOID IN (1,7)) AS ULTIMOENVIO "
					+ "FROM TBL_MENSAJES m INNER JOIN TBL_LOTESENVIOS l ON l.LOTEENVIOID = m.LOTEENVIOID "
					+ "INNER JOIN TBL_DESTINATARIOS_MENSAJES dm ON dm.MENSAJEID = m.MENSAJEID AND dm.DESTINATARIO = TO_CHAR(m.USUARIOID) "
					+ "WHERE m.USUARIOID IN ("+usuarios+") AND dm.ESTADO IN ("+LISTA_ESTADOS_SELECCIONADOS+") "
					+ "AND l.MULTIDESTINATARIO = 1)) order by MENSAJEID desc";
		else{
			
			int cantidad = pag * num ;
			int pagina = cantidad-pag;
		
			
			sql = "select * from "
					+ "(select a.*, ROWNUM rnum from "
					+ "(select * from ((SELECT m.MENSAJEID as MENSAJEID,"
					+ "m.CABECERA as CABECERA, m.CUERPO as CUERPO,"
					+ "m.ESTADOACTUAL as ESTADO, "
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM USR_MSGPLT.TBL_HISTORICOS T2 WHERE T2.MENSAJEID = m.MENSAJEID AND T2.ESTADOID IN (1,7)) as ULTIMOENVIO "
					+ "from TBL_MENSAJES m inner join TBL_LOTESENVIOS l on "
					+ "l.LOTEENVIOID = m.LOTEENVIOID WHERE m.USUARIOID in ("+usuarios+") "
					+ "and m.ESTADOACTUAL in ("+LISTA_ESTADOS_SELECCIONADOS+") "
					+ "AND l.MULTIDESTINATARIO = 0 "
					+ "UNION ALL "
					+ "SELECT m.MENSAJEID as MENSAJEID,m.CABECERA as CABECERA, "
					+ "m.CUERPO as CUERPO,dm.ESTADO as ESTADO, "
					+ "(SELECT TO_CHAR(MAX(FECHA),'dd/mm/yyyy hh24:mi:ss') FROM TBL_HISTORICOS T2 WHERE T2.MENSAJEID = dm.MENSAJEID AND T2.ESTADOID IN (1,7)) as ULTIMOENVIO "
					+ "from TBL_MENSAJES m inner join TBL_LOTESENVIOS l "
					+ "on l.LOTEENVIOID = m.LOTEENVIOID inner join "
					+ "TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID AND dm.DESTINATARIO = TO_CHAR(m.USUARIOID) "
					+ "WHERE m.USUARIOID in ("+usuarios+") and dm.ESTADO in ("+LISTA_ESTADOS_SELECCIONADOS+") "
					+ "AND l.MULTIDESTINATARIO = 1) )order by MENSAJEID desc) a where ROWNUM <= "+cantidad+" ) where rnum  > "+pagina;
						
		}
		
		return sql;
	}
	
	private String getQueryMultidest(String usuarios, String numPagina, String tamPagina, int size){
		String sql ="";
		if (numPagina == null && null ==tamPagina)
			sql = "SELECT m.MENSAJEID,m.CABECERA,m.CUERPO,dm.ESTADO,"
					+ "TO_CHAR(dm.ULTIMOENVIO,'dd/mm/yyyy hh24:mi:ss') as ULTIMOENVIO from "
					+ "TBL_DESTINATARIOS_MENSAJES m inner join TBL_LOTESENVIOS l on l.LOTEENVIOID = m.LOTEENVIOID "
					+ "WHERE m.USUARIOID in ("+usuarios+") and dm.ESTADOA in ("+LISTA_ESTADOS_SELECCIONADOS+") "
					+ "AND l.MULTIDESTINATARIO = 1 order by m.MENSAJEID desc";
		else{
			Integer pag = Integer.parseInt(tamPagina);
			Integer num = Integer.parseInt(numPagina);
			pag = pag-size;
			int cantidad = pag * num ;
			int pagina = cantidad-pag;
			sql = "select * from ( select a.*, ROWNUM rnum from "
					+ "(SELECT m.MENSAJEID as MENSAJEID,m.CABECERA as CABECERA, "
					+ "m.CUERPO as CUERPO,dm.ESTADO as ESTADO, "
					+ "TO_CHAR(dm.ULTIMOENVIO,'dd/mm/yy hh24:mi:ss') as ULTIMOENVIO "
					+ "from TBL_MENSAJES m inner join TBL_LOTESENVIOS l "
					+ "on l.LOTEENVIOID = m.LOTEENVIOID "
					+ "inner join TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID "
					+ "WHERE m.USUARIOID in ("+usuarios+") and dm.ESTADO in ("+LISTA_ESTADOS_SELECCIONADOS+") " 
					+ "AND l.MULTIDESTINATARIO = 1 order by m.MENSAJEID desc) a "
					+ "where ROWNUM <= "+cantidad+" ) where rnum  > "+pagina;
		}
		return sql;
	}
	
	public boolean eliminarUsuario(String token) {
		boolean res = true;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String s ="";
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_USUARIOS_PUSH set"
					+ " ELIMINADO = 'S', FECHAMODIFICACION = sysdate where TOKENUSUARIO = '"+token+"'";
			
			rs = stmt.executeQuery(s);
			
		} catch (SQLException e) {
			logger.debug("[UsuariosPushDAO.eliminarUsuario] - Error en bbdd",e);
			return false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}
}
