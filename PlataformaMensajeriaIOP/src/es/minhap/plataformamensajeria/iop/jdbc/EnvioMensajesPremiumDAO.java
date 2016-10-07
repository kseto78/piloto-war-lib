package es.minhap.plataformamensajeria.iop.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.DatosAplicacionBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.UrlMensajePremiumBean;
import es.minhap.plataformamensajeria.sm.modelo.DatosServicio;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.Proveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;

/**
 * 
 * @author i-nercya
 *
 */
public class EnvioMensajesPremiumDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(EnvioMensajesPremiumDAO.class);
	private static final Integer MAX_REINTENTOS=4;
	
	private static final String PROCEDIMIENTO_CREAR_AUDITORIA = "{call PKG_MAESTROS.SETAUDITORIA(?,?,?,?,?,?,?,?,?,?)}";
	private static final String ACCION ="CREAR_MENSAJE_SMS_PREMIUM";
	private static final String ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ENVIADO = "ENVIADO";
	private static final String RESULTADO_ACCION ="Mensaje SMS Premium Creado Correctamente";
	
	public Integer obtenerServicio(String codOrganismoPagadorSMS, String canal) {
		Integer res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT s.SERVICIOID FROM TBL_ORGANISMOS_SERVICIO os "
					+ "INNER JOIN TBL_SERVICIOS s ON s.SERVICIOID = os.SERVICIOID "
					+ "INNER JOIN TBL_ORGANISMOS o ON os.ORGANISMOID = o.ORGANISMOID WHERE "
					+ "s.CANALID = "+canal+" and s.activo = 1 and s.premium = 1 and o.activo = 1 and o.DIR3 = '" +codOrganismoPagadorSMS+"'");
		

			while(rs.next()){
				res = rs.getInt("SERVICIOID");
			}
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesPremiumDAO.obtenerServicio] - Error en bbdd",e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}


	public DatosAplicacionBean getDatosAplicacion(Integer servicioId) {
		DatosAplicacionBean res = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		conn = this.getConn();
		
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT USUARIO,PASSWORD FROM TBL_APLICACIONES a "
					+ "INNER JOIN TBL_SERVICIOS s ON s.APLICACIONID = a.APLICACIONID "
					+ "WHERE s.SERVICIOID = " +servicioId);
			while(rs.next()){
			String pass = rs.getString("PASSWORD");
			byte[] valueDecoded= Base64.decode(pass);
			
				res = new DatosAplicacionBean(rs.getString("USUARIO"), new String(valueDecoded));
			}
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesPremiumDAO.getDatosAplicacion] - Error en bbdd",e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return res;
	}


	public Integer crearMensaje(Integer idLote, String cuerpo, String docUsuario,
			String codOrganismoPagadorSMS, String idExterno, String destinatario, String usuario,
			String password, String url, Integer reintentos) {
		Integer idMensaje = null;
		Integer idDestinatariosMensajes = null;
		ResultSet rs = null;
		Statement stmt = null;
		String s = null;
		Connection conn = null;
		
		conn = this.getConn();
		try{

			s = "INSERT INTO TBL_MENSAJES (mensajeid, loteenvioid, codigoexterno, estadoactual, numeroenvios, "
					+ "fechacreacion, creadopor, fechamodificacion, modificadopor, cuerpo, tipomensaje, telefono, "
					+ "docusuario, codsia, codorganismo, codorganismopagador) "
					+ "VALUES (MENSAJEID_SEC.NEXTVAL,"+idLote+",null,'PENDIENTE DE ENVIO',0,sysdate,"
							+ "'"+usuario+"',sysdate,'"+usuario+"','"+cuerpo+"','SMS',null,null,"
							+ "null,'"+codOrganismoPagadorSMS+"','"+codOrganismoPagadorSMS+"' )";
			
			stmt = conn.createStatement();
			stmt.executeUpdate(s, new int[] {1});
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				BigDecimal a =(BigDecimal) rs.getObject(1); 
				idMensaje = a.intValue();
			}
			
			rs.close();
			stmt.close();
			//insertamos en tbl Destinatarios_Mensajes
			s = "INSERT INTO tbl_destinatarios_mensajes"
					+ " (destinatariosmensajes, mensajeid, destinatario,estado,fechacreacion,creadopor,"
					+ "codigoExterno,ultimoenvio) "
					+ "VALUES(DESTINATARIOSMENSAJES_SEC.NEXTVAL,"+idMensaje+",'"+destinatario+"','PENDIENTE DE ENVIO',"
					+ "sysdate,'"+ usuario + "','"+idExterno+"',sysdate)";
			stmt = conn.createStatement();
			stmt.executeUpdate(s, new int[] {1});
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				BigDecimal a =(BigDecimal) rs.getObject(1); 
				idDestinatariosMensajes = a.intValue();
			}
			
			//insertamos la url 
			rs.close();
			stmt.close();
			s = "insert into TBL_URL_MENSAJE_PREMIUM (URL_MENSAJE_PREMIUM_ID,MENSAJE_ID,URL,REINTENTOS) "
					+ "VALUES (URL_MENSAJE_PREMIUM_SEC.NEXTVAL,"+idMensaje+",'"+url+"',"+ reintentos +")";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			//recogemos el id del estado
			Integer estado = null;
			rs.close();
			stmt.close();
			s = "select ESTADOID from TBL_ESTADOS where nombre = 'PENDIENTE DE ENVIO'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			while(rs.next()){
			estado = rs.getInt("ESTADOID");
			}
			
			//actualizamos el estado del lote
			rs.close();
			stmt.close();
			s = "UPDATE TBL_LOTESENVIOS set ESTADOENVIOID = "+estado+ "where LOTEENVIOID = "+ idLote;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			rs.close();
			stmt.close();
			//insertamos tabla historicos
			s = " INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,destinatariosmensajes) VALUES "
					+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate, "+estado+","+idDestinatariosMensajes+")";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
						
			//insertamos tabla auditoria
			crearAuditoria(idLote, usuario, password, idMensaje);
			
		}catch(SQLException e){
			logger.debug("[EnvioMensajesPremiumDAO.crearMensaje] - Error en bbdd",e);
			idMensaje = -10;
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		return idMensaje;
		
	}
	
	
	public Integer crearMensajeGISS(Integer idLote, String cuerpo, String docUsuario,
			String codOrganismoPagadorSMS, String idExterno, String destinatario, String usuario,
			String password) {
		Integer idMensaje = null;
		Integer idDestinatariosMensajes = null;
		
		try{
			//insertamos el mensaje
			idMensaje = insertarMensaje(idLote, cuerpo, codOrganismoPagadorSMS,  usuario);
			
			//insertamos en tbl Destinatarios_Mensajes
			idDestinatariosMensajes = insertarDestinatarioMensaje(idMensaje, destinatario, idExterno, usuario);
						
			//recogemos el id del estado
			Integer estado = seleccionarIdEstado("PENDIENTE DE ENVIO");
			
			//actualizamos el estado del lote
			actualizarEstadoLote(idLote, estado);
						
			//insertamos tabla historicos
			insertarTablaHistoricos(idMensaje, estado, idDestinatariosMensajes);
						
			//insertamos tabla auditoria
			crearAuditoria(idLote, usuario, password, idMensaje);
			
		}catch (Exception e){
			idMensaje = -10;
		}
		
		return idMensaje;
		
	}
	
	public void crearAuditoria(Integer idLote, String usuario, String password, Integer idMensaje) {
		CallableStatement call = null;
		Date fecha = new Date();
		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_AUDITORIA);
			
			call.setString(1, ACCION);
			call.setDate(2, new java.sql.Date(fecha.getTime()));
			if (idLote != null) {
				call.setInt(3, new Integer(idLote));
			} else {
				call.setNull(3, java.sql.Types.INTEGER);
			}

			call.setNull(4, java.sql.Types.INTEGER);
			call.setNull(5, java.sql.Types.INTEGER);
			call.setNull(6, java.sql.Types.INTEGER);
			
			call.setString(7, usuario);
			call.setString(8, password);
			call.setInt(9, idMensaje);
			call.setString(10, RESULTADO_ACCION);
			
//			call.registerOutParameter(11, OracleTypes.NUMBER);
			call.executeUpdate();
//			res =  (BigDecimal) call.getObject(11);

		}catch (SQLException e) {
			logger.debug("[EnvioMensajesPremiumDAO.crearAuditoria] - Error en bbdd", e);
			
		}finally{
			this.closeSQLObject(call);
		}
		
	}


	public SMSData getSMSData(Integer idMensaje) {
		SMSData res = new SMSData();
		
			res.ServiceData = getDataFromServices(idMensaje);
			res.Servers = GetProveedoresMultiorganismo(idMensaje);
			res.Servers.addAll(GetProveedores(idMensaje));
			getDetailsMultidestinatario(idMensaje,res);
			res.esMultidestinatario = true;
					
		return res;
	}
	
	
	private void getDetailsMultidestinatario(Integer idMensaje, SMSData smsData) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT m.CABECERA,m.CUERPO,m.TIPOCUERPO,m.TIPOCODIFICACION,dm.DESTINATARIO, dm.DESTINATARIOSMENSAJES"
							+ " FROM TBL_MENSAJES m "
							+ "inner join TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID "
							+ " WHERE m.MENSAJEID = " + idMensaje);

			while (rs.next()) {
				smsData.Telefono = rs.getString("DESTINATARIO");
				smsData.destinatarioMensajeId = rs.getBigDecimal(
						"DESTINATARIOSMENSAJES").longValue();
				if (rs.getString("CUERPO") == null) {
					smsData.Body = "";
				} else {
					smsData.Body = rs.getString("CUERPO");
				}
			}
				
		} catch (SQLException e) {
			logger.error("JDBCAccess.GetDetails : " + e.getErrorCode() + " - "
					+ e.getMessage());

		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}


		
	}


	private ArrayList<ParametrosProveedor> GetProveedores(
			Integer idMensaje) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		ArrayList<ParametrosProveedor> pss = new ArrayList<ParametrosProveedor>();
		conn = this.getConn();
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT proveedorid,url,id,telefono,texto,uim,servicioid,prioridad"
							+ "      FROM view_proveedores_prioridad"
							+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid                           "
							+ "				  			WHERE mensajeid = "
							+ idMensaje
							+ ")"
							+ "		       OR servicioId IS NULL)"
							+ "      AND url is not null"
							+ "	     order by Prioridad");
			while (rs.next()) {
				ParametrosProveedor ps = new ParametrosProveedor();

				ps.setProveedorId(rs.getInt("PROVEEDORID"));
				ps.setUrl(rs.getString("URL"));
				ps.setId(rs.getString("ID"));
				ps.setTexto(rs.getString("TEXTO"));
				ps.setUIM(rs.getString("UIM"));
				ps.setTelefono(rs.getString("TELEFONO"));
				pss.add(ps);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.GetProveedores : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return pss;
	}


	private ArrayList<ParametrosProveedor> GetProveedoresMultiorganismo(Integer idMensaje) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		ArrayList<ParametrosProveedor> pss = new ArrayList<ParametrosProveedor>();
		conn = this.getConn();
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT proveedorid,url,organismoid,servicioid,prioridad"
							+ "  FROM view_prov_prioridad_multiorg"
							+ "  WHERE (servicioId = (SELECT servicioid "
							+ "FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid "
							+ "WHERE mensajeid = "
							+ idMensaje
							+ ") "
							+ "OR servicioId IS NULL) "
							+ "AND url is not null " + "order by Prioridad");
			while (rs.next()) {
				ParametrosProveedor ps = new ParametrosProveedor();

				ps.setProveedorId(rs.getInt("PROVEEDORID"));
				ps.setUrl(rs.getString("URL"));
				pss.add(ps);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.GetProveedoresMultiorganismo : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return pss;
	}


	private DatosServicio getDataFromServices(Integer msID){
		ResultSet rs = null;
		Statement stmt = null;
		DatosServicio datos = new DatosServicio();
		
		Connection conn = null;
		conn = this.getConn();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT NMAXENVIOS,CUENTAENVIO,NOMBRECUENTAENVIO, HEADERSMS, MULTIORGANISMO FROM TBL_SERVICIOS WHERE SERVICIOID = (SELECT SERVICIOID"
							+ " FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID"
							+ " WHERE M.MENSAJEID=" + msID + ")");

			while(rs.next()){

				datos.setNMaxEnvios(rs.getInt("NMAXENVIOS"));
				datos.setFromMail(rs.getString("CUENTAENVIO"));
				datos.setFromMailNombre(rs.getString("NOMBRECUENTAENVIO"));
				datos.setHeaderSMS(rs.getString("HEADERSMS"));
				datos.setMultiOrganismo(rs.getInt("MULTIORGANISMO"));
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.GetDataFromServices : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return datos;
	}


	public Map<Integer, Servicio> findServicioByMessageId(Integer idMensaje) {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String s = "select serv.SERVICIOID, serv.MULTIORGANISMO from  TBL_SERVICIOS SERV, TBL_LOTESENVIOS LOT, TBL_MENSAJES MEN WHERE MEN.mensajeID = "
					+ idMensaje
					+ " and LOT.loteenvioid = MEN.loteenvioid and serv.SERVICIOID = LOT.servicioid";
			logger.debug(s);
			rs = stmt.executeQuery(s);
			Integer servicioid = 0;
			Integer multiorganismo = 0;
			if (rs.next()){
			// Integer servicioid = rs.getInt(1);
			servicioid = rs.getInt("SERVICIOID");
			multiorganismo = rs.getInt("MULTIORGANISMO");
			}
			if (1 == multiorganismo) {
				res = findServicioMultiorganismo(servicioid, idMensaje);
				if (res.size() <= 0) {
					res = findServicio(servicioid);
				}
			} else {
				res = findServicio(servicioid);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.findServicioByMessageId : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;
	}
	
	private Map<Integer, Servicio> findServicioMultiorganismo(Integer servicioID, Integer messageId)
			throws SQLException {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();

		// Servicio servicio = new Servicio();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT TBL_ORGANISMOS.NOMBRE, TBL_ORGANISMOS.DESCRIPCION, TBL_ORGANISMOS.ACTIVO, "
							+ "TBL_SERVIDORES_ORGANISMOS.SERVIDORID, TBL_SERVIDORES_ORGANISMOS.ORGANISMOID, TBL_SERVIDORES_ORGANISMOS.HEADERSMS, "
							+ "TBL_SERVIDORES_ORGANISMOS.NUMINTENTOS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORUSUARIOSMS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORPASSWORDSMS, "
							+ "TBL_ORGANISMOS.DIR3, TBL_SERVICIOS.SERVICIOID "
							+ "FROM TBL_SERVIDORES_ORGANISMOS, TBL_SERVICIOS, TBL_MENSAJES, TBL_ORGANISMOS, TBL_LOTESENVIOS, TBL_SERVIDORES "
							+ "WHERE (TBL_ORGANISMOS.DIR3 = TBL_MENSAJES.CODORGANISMOPAGADOR) "
							+ "AND (TBL_ORGANISMOS.ORGANISMOID = TBL_SERVIDORES_ORGANISMOS.ORGANISMOID) "
							+ "AND (TBL_LOTESENVIOS.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
							+ "AND (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) "
							+ "AND (TBL_MENSAJES.MENSAJEID = "
							+ messageId
							+ ") AND (TBL_LOTESENVIOS.SERVICIOID = "
							+ servicioID + ")"
							+ "AND (TBL_SERVIDORES.SERVIDORID = TBL_SERVIDORES_ORGANISMOS.SERVIDORID) "
							+ "AND (TBL_SERVIDORES.ACTIVO = 1)");
			
			while (rs.next()) {
				Servicio servicio = new Servicio();
				servicio.setNombre(rs.getString("NOMBRE"));
				servicio.setDescripcion(rs.getString("DESCRIPCION"));
				servicio.setProveedorUsuarioSMS(rs
						.getString("PROVEEDORUSUARIOSMS"));
				servicio.setProveedorPassSMS(rs
						.getString("PROVEEDORPASSWORDSMS"));
				servicio.setHeaderSMS(rs.getString("HEADERSMS"));
				servicio.setServicioId(Integer.parseInt(rs.getString("SERVIDORID")));
				res.put(Integer.parseInt(rs.getString("SERVIDORID")), servicio);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.findServicioMultiorganismo : "
					+ e.getErrorCode() + " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;
	}
	
	private Map<Integer, Servicio> findServicio(Integer servicioID) throws SQLException {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT ss.servidorId, ss.headersms, s.nombre,s.descripcion, ss.proveedorusuariosms, ss.proveedorpasswordsms"
							+ " FROM TBL_SERVICIOS s inner join TBL_SERVIDORES_SERVICIOS ss on s.SERVICIOID = ss.SERVICIOID"
							+ " WHERE s.SERVICIOID = " + servicioID);

			while (rs.next()) {
				Servicio servicio = new Servicio();
				servicio.setNombre(rs.getString("nombre"));
				servicio.setDescripcion(rs.getString("descripcion"));
				servicio.setProveedorUsuarioSMS(rs
						.getString("proveedorusuariosms"));
				servicio.setProveedorPassSMS(rs
						.getString("proveedorpasswordsms"));
				servicio.setHeaderSMS(rs.getString("headersms"));
				servicio.setServicioId(Integer.parseInt(rs.getString("servidorId")));
				res.put(Integer.parseInt(rs.getString("servidorId")), servicio);
			}
		}catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.findServicio : "
					+ e.getErrorCode() + " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;
	}
	
	// Procedimiento que guarda en un log en la BBDD con los errores que han
		// sucedido en el envio
		public void setLogError(String source, int number, String message) throws Exception {
			Statement stmt = null;
			ResultSet rs = null;
			Connection conn = null;
			conn = this.getConn();
			
			try {
				
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				String s = "";
				s += "INSERT INTO TBL_ERRORMENSAJE_LOG (ERRORMENSAJELOGID, FECHA,OPERACION, CODIGOERROR,DESCRIPCIONERROR)";
				s += "VALUES (errormensajelogid_sec.NEXTVAL,SYSDATE,'" + source
						+ "'," + number + ",'" + message + "')";
				rs = stmt.executeQuery(s);
				stmt.close();

			} catch (SQLException e) {
				logger.error("EnvioMensajesPremiumDAO.SetLogError : " + e.getErrorCode() + " - "
						+ e.getMessage());
			} finally {
				this.closeSQLObject(rs);
				this.closeSQLObject(stmt);
			}
		}
		
		public Proveedor findProveedor(Integer proveedorid)
				throws SQLException {

			ResultSet rs = null;
			Statement stmt = null;
			Connection conn = null;
			conn = this.getConn();
			Proveedor proveedor = new Proveedor();
			try {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = stmt
						.executeQuery("SELECT nombre,descripcion,pordefecto, fechacreacion, creadopor, fechamodificacion, modificadopor, urlDestino, tipo, externalid, eliminado, METODOCONSULTA, usuario, password FROM TBL_SERVIDORES WHERE SERVIDORID = "
								+ proveedorid);

				while(rs.next()){
					proveedor.setNombre(rs.getString("nombre"));
					proveedor.setDescripcion(rs.getString("descripcion"));
					proveedor.setPordefecto(rs.getBigDecimal("pordefecto"));
					proveedor.setFechacreacion(rs.getDate("fechacreacion"));
					proveedor.setCreadopor(rs.getString("creadopor"));
					proveedor.setFechamodificacion(rs.getDate("fechamodificacion"));
					proveedor.setModificadopor(rs.getString("modificadopor"));
					proveedor.setUrlDestino(rs.getString("urlDestino"));
					proveedor.setTipo(rs.getBigDecimal("tipo"));
					proveedor.setExternalid(rs.getString("externalid"));
					proveedor.setEliminado(rs.getString("eliminado"));
					proveedor.setMetodoConsulta(rs.getInt("METODOCONSULTA"));
					proveedor.setUsuario(rs.getString("usuario"));
					proveedor.setPassword(rs.getString("password"));
				}
			} catch (SQLException e) {
				logger.error("EnvioMensajesPremiumDAO.findProveedor : " + e.getErrorCode() + " - "
						+ e.getMessage());
			} finally {
				this.closeSQLObject(rs);
				this.closeSQLObject(stmt);
			}

			return proveedor;

		}
		// Procedimiento que cambia el metodo de consulta de estadoa de un SMS
		public void mensajeSetMetodoConsulta(int msID, int metodoConsulta) throws Exception {
			Statement stmt = null;
			String s;
			Connection conn = null;
			conn = this.getConn();

			try {

				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				s = "UPDATE tbl_mensajes SET METODOCONSULTA = " + metodoConsulta
						+ " WHERE mensajeid = " + msID;
				stmt.executeUpdate(s);

			} catch (SQLException e) {
				logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : "
						+ e.getErrorCode() + " - " + e.getMessage());

			} finally {
				this.closeSQLObject(stmt);

			}
		}


	public Integer checkIdExternoExists(String idExterno) {
		return checkIdExternoExists(idExterno, false);
	}
	
	public Integer checkIdExternoExists(String idExterno, Boolean sended) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = this.getConn();
		Integer mensajeId = null;
		StringBuilder query = new StringBuilder();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			query.append("SELECT MENSAJEID FROM TBL_DESTINATARIOS_MENSAJES WHERE CODIGOEXTERNO= '"	+ idExterno+"'");
			rs = stmt.executeQuery(query.toString());
			if( rs.next()) {
				mensajeId = rs.getInt(1);
				rs.close();
			}
			if (true == sended && null != mensajeId && mensajeId > 0) {
				query.append("AND ESTADO not in ('"+ ESTADO_ENVIADO+"', '"+ ESTADO_ANULADO+"') ");
				rs = stmt.executeQuery(query.toString());
				if( rs.next()) {
					mensajeId = rs.getInt(1);
				} else {
					mensajeId = 0;
				}
			}
			return mensajeId;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.checkIdExternoExists : "	+ e.getErrorCode() + " - " + e.getMessage());
			return mensajeId;
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}
	
	public UrlMensajePremiumBean getURLMensajePremiumBeanByIdMessage(Integer idMensaje) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = this.getConn();
		UrlMensajePremiumBean bean = new UrlMensajePremiumBean();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT URL_MENSAJE_PREMIUM_ID, MENSAJE_ID,	URL,REINTENTOS FROM TBL_URL_MENSAJE_PREMIUM WHERE MENSAJE_ID= "	+ idMensaje);
			if( rs.next()) {
				bean.setUrlMensajePremiumId(rs.getLong(1));
				bean.setMensajeid(rs.getBigDecimal(2));
				bean.setUrl(rs.getString(3));
				bean.setReintentos(rs.getBigDecimal(4));
			}
			return bean;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.checkIdExternoExists : "	+ e.getErrorCode() + " - " + e.getMessage());
			return bean;
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}
	
	public Boolean decrementReintentos(Integer mensajeId) {
		Statement stmt = null;
		String s;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_URL_MENSAJE_PREMIUM SET REINTENTOS = (SELECT REINTENTOS FROM TBL_URL_MENSAJE_PREMIUM WHERE MENSAJE_ID="
					+ mensajeId + ")-1 " + "WHERE MENSAJE_ID=" + mensajeId;
			stmt.executeUpdate(s);
			return Boolean.TRUE;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : "
					+ e.getErrorCode() + " - " + e.getMessage());
			return Boolean.FALSE;
		} finally {
			this.closeSQLObject(stmt);
		}
	}
	
	public Boolean updateReintentos(Integer mensajeId) {
		Statement stmt = null;
		String s;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_URL_MENSAJE_PREMIUM SET REINTENTOS = "+MAX_REINTENTOS+" WHERE MENSAJE_ID=" + mensajeId;
			stmt.executeUpdate(s);
			return Boolean.TRUE;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : "
					+ e.getErrorCode() + " - " + e.getMessage());
			return Boolean.FALSE;
		} finally {
			this.closeSQLObject(stmt);
		}
	}
	
	public String getStatusMessage(Integer idMensaje) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = this.getConn();
		String estado = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT ESTADO FROM TBL_DESTINATARIOS_MENSAJES where MENSAJEID= "+ idMensaje);
			if( rs.next()) {
				estado = rs.getString(1);
			}
			return estado;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.checkIdExternoExists : "	+ e.getErrorCode() + " - " + e.getMessage());
			return estado;
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}

	public void setEstadoMensaje(Integer idMensaje, String estado) {
		this.setEstadoMensaje(idMensaje, estado, null);
	}

	public void setEstadoMensaje(Integer idMensaje, String estado, String descripcion) {
		Statement stmt = null;
		String s;
		Connection conn = null;
		conn = this.getConn();
		ResultSet rs = null;
		Integer idDestinatariosMensajes = null;
		Integer idEstado = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			if (!ESTADO_ANULADO.equals(estado)) {
				s = "UPDATE TBL_MENSAJES SET ESTADOACTUAL='"+estado+"' , NUMEROENVIOS= (SELECT NUMEROENVIOS FROM TBL_MENSAJES WHERE MENSAJEID="+idMensaje+
						")+1 WHERE MENSAJEID=" + idMensaje;
			} else {
				s = "UPDATE TBL_MENSAJES SET ESTADOACTUAL='"+estado+"'  WHERE MENSAJEID=" + idMensaje;
				
			}
			
			stmt.executeUpdate(s);
			stmt.close();
			conn.commit();

			conn = this.getConn();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_DESTINATARIOS_MENSAJES SET ESTADO='"+estado+"' WHERE MENSAJEID=" + idMensaje;
			stmt.executeUpdate(s);
			conn.commit();
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_LOTESENVIOS SET ESTADOENVIOID=(SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE = '"+estado+"') where "
					+ "LOTEENVIOID = (SELECT LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = "+idMensaje+")";
			stmt.executeUpdate(s);
			conn.commit();
			//recuperamos idDestinatariosMensajes
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES where MENSAJEID= "+ idMensaje);
			if( rs.next()) {
				idDestinatariosMensajes = rs.getInt("DESTINATARIOSMENSAJES");
			}
			stmt.close();
			rs.close();
			//recuperamos id estado
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE = '"+estado+"'");
			if( rs.next()) {
				idEstado = rs.getInt("ESTADOID");
			}
			stmt.close();
			rs.close();
			//insertamos tabla historicos
			if (null != descripcion && ESTADO_ANULADO.equals(estado)) {
				s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,descripcion,destinatariosmensajes) VALUES "
						+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate, "+idEstado+",'"+descripcion+"',"+idDestinatariosMensajes+")";
			} else {
				s = " INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,destinatariosmensajes) VALUES "
						+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate,"+idEstado+","+idDestinatariosMensajes+")";
			}
			
			logger.debug("EnvioMensajesPremiumDAO - actualizar estado: "+idEstado);
			stmt = conn.createStatement();
			stmt.executeQuery(s);
			conn.commit();
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : " + e.getErrorCode() + " - " + e.getMessage(), e);
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}


	public void setEstadoMensajeEnviado(Integer idMensaje, String estado, String descripcion) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		Integer destinatariosMensajes = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select SE.servidorid from TBL_SERVIDORES SE, TBL_SERVIDORES_ORGANISMOS SEO where SE.SERVIDORID = SEO.SERVIDORID AND SEO.ORGANISMOID = (select ORGANISMOID from TBL_ORGANISMOS where DIR3= (SELECT ME.CODORGANISMOPAGADOR FROM TBL_MENSAJES ME WHERE ME.MENSAJEID="	+ idMensaje + "))");
			Integer servidorId = null;
			if( rs.next()) {
				servidorId = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_MENSAJES SET ESTADOACTUAL='"+estado+"' WHERE MENSAJEID=" + idMensaje;
			stmt.executeUpdate(s);
			
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_DESTINATARIOS_MENSAJES SET ESTADO='"+estado+"' WHERE MENSAJEID=" + idMensaje;
			stmt.executeUpdate(s);
			
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_LOTESENVIOS SET ESTADOENVIOID=(SELECT ESTADOID FROM TBL_ESTADOS WHERE NOMBRE = '"+estado+"') where "
					+ "LOTEENVIOID = (SELECT LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = "+idMensaje+")";
			stmt.executeUpdate(s);
			
			//insertamos tabla historicos
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES WHERE MENSAJEID= "	+ idMensaje);
			if( rs.next()) {
				destinatariosMensajes = rs.getInt(1);
			}
			
			Integer estadoId = null;
			rs.close();
			stmt.close();
			s = "select ESTADOID from TBL_ESTADOS where nombre = '"+estado+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			while(rs.next()){
			estadoId = rs.getInt("ESTADOID");
			}
			
			stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,servidorId,descripcion,destinatariosmensajes) VALUES "
					+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate, "+estadoId+","+ servidorId+",'"+descripcion+"',"+destinatariosMensajes+")";
			stmt.executeUpdate(s);
			
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : "
					+ e.getErrorCode() + " - " + e.getMessage());
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}


	public Boolean updateURL(Integer idMensaje, String deliveryReportURL) {
		Statement stmt = null;
		String s;
		Connection conn = null;
		conn = this.getConn();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE TBL_URL_MENSAJE_PREMIUM SET URL = '"+ deliveryReportURL+"' WHERE MENSAJE_ID=" + idMensaje;
			stmt.executeUpdate(s);
			return Boolean.TRUE;
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.mensajeSetMetodoConsulta : "
					+ e.getErrorCode() + " - " + e.getMessage());
			return Boolean.FALSE;
		} finally {
			this.closeSQLObject(stmt);
		}
	}


	public String getNombreLote(Integer idLote) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String res= null;
		Connection conn = null;
		conn = this.getConn();
		try {
			s = "select NOMBRE from TBL_LOTESENVIOS where LOTEENVIOID = "+idLote;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			while(rs.next()){
				res = rs.getString("NOMBRE");
			}
			
			
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.getNombreLote : "
					+ e.getErrorCode() + " - " + e.getMessage());
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return res;
	}
	
	public String getIdExterno(Integer idMensaje) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String res= null;
		Connection conn = null;
		conn = this.getConn();
		try {
			s = "select CODIGOEXTERNO from TBL_DESTINATARIOS_MENSAJES where MENSAJEID = "+idMensaje;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			while(rs.next()){
				res = rs.getString("CODIGOEXTERNO");
			}
			
			
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.getIdExterno : "
					+ e.getErrorCode() + " - " + e.getMessage());
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return res;
	}


	public String getPassword(String codigoOrganismo, Integer proveedorId) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String res= null;
		Connection conn = null;
		conn = this.getConn();
		
		try {
			s = "select PROVEEDORPASSWORDSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o "
					+ "on so.ORGANISMOID = o.ORGANISMOID where so.SERVIDORID ="+ proveedorId+"  AND o.DIR3 = '"+codigoOrganismo+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			if (rs.next())
				res = rs.getString("PROVEEDORPASSWORDSMS");
			
			
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.getPassword : "
					+ e.getErrorCode() + " - " + e.getMessage());
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return res;
	}
	public String getUsuario(String codigoOrganismo, Integer proveedorId) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String res= null;
		Connection conn = null;
		conn = this.getConn();
		try {
			s = "select PROVEEDORUSUARIOSMS from TBL_SERVIDORES_ORGANISMOS so inner join TBL_ORGANISMOS o "
					+ "on so.ORGANISMOID = o.ORGANISMOID where so.SERVIDORID ="+ proveedorId+"  AND o.DIR3 = '"+codigoOrganismo+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			if (rs.next())
				res = rs.getString("PROVEEDORUSUARIOSMS");
			
			
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.getPassword : "
					+ e.getErrorCode() + " - " + e.getMessage());
			
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return res;
	}


	public boolean idExternoRepetido(String idExterno) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		boolean res= false;
		Connection conn = null;
		conn = this.getConn();
		try {
			s = "select CODIGOEXTERNO from TBL_DESTINATARIOS_MENSAJES where CODIGOEXTERNO ='"+ idExterno+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			if (rs.next())
				return true;	
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.idExternoRepetido : "
					+ e.getErrorCode() + " - " + e.getMessage());		
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return res;
	}
	
	private Integer insertarMensaje(Integer idLote, String cuerpo, String codOrganismoPagadorSMS, String usuario){
		Integer idMensaje = null;
		String s = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		conn = this.getConn();
		try{

			s = "INSERT INTO TBL_MENSAJES (mensajeid, loteenvioid, codigoexterno, estadoactual, numeroenvios, "
					+ "fechacreacion, creadopor, fechamodificacion, modificadopor, cuerpo, tipomensaje, telefono, "
					+ "docusuario, codsia, codorganismo, codorganismopagador) "
					+ "VALUES (MENSAJEID_SEC.NEXTVAL,"+idLote+",null,'PENDIENTE DE ENVIO',0,sysdate,"
							+ "'"+usuario+"',sysdate,'"+usuario+"','"+cuerpo+"','SMS',null,null,"
							+ "null,'"+codOrganismoPagadorSMS+"','"+codOrganismoPagadorSMS+"' )";
			
			stmt = conn.createStatement();
			stmt.executeUpdate(s, new int[] {1});
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				BigDecimal a =(BigDecimal) rs.getObject(1); 
				idMensaje = a.intValue();
			}
		}catch(SQLException e){
			logger.debug("[EnvioMensajesPremiumDAO.crearMensaje] - Error en bbdd",e);
			idMensaje = -10;
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		
		return idMensaje;
	}
	
	
	private Integer insertarDestinatarioMensaje(Integer idMensaje, String destinatario, String idExterno, String usuario)throws Exception{
		Integer idDestinatariosMensajes = null;
		String s = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		conn = this.getConn();
		try{

			s = "INSERT INTO tbl_destinatarios_mensajes"
					+ " (destinatariosmensajes, mensajeid, destinatario,estado,fechacreacion,creadopor,"
					+ "codigoExterno,ultimoenvio) "
					+ "VALUES(DESTINATARIOSMENSAJES_SEC.NEXTVAL,"+idMensaje+",'"+destinatario+"','PENDIENTE DE ENVIO',"
					+ "sysdate,'"+ usuario + "','"+idExterno+"',sysdate)";
			stmt = conn.createStatement();
			stmt.executeUpdate(s, new int[] {1});
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				BigDecimal a =(BigDecimal) rs.getObject(1); 
				idDestinatariosMensajes = a.intValue();
			}
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		
		return idDestinatariosMensajes;
	}
	
	private Integer seleccionarIdEstado(String nombreEstado)throws Exception{
		Integer estado = null;
		String s = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		conn = this.getConn();
		try{

			s = "select ESTADOID from TBL_ESTADOS where nombre = 'PENDIENTE DE ENVIO'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
			
			while(rs.next()){
			estado = rs.getInt("ESTADOID");
			}
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
		
		return estado;
	}
	
	private void actualizarEstadoLote(Integer idLote, Integer estado)throws Exception{
		String s = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		conn = this.getConn();
		try{
			s = "UPDATE TBL_LOTESENVIOS set ESTADOENVIOID = "+estado+ "where LOTEENVIOID = "+ idLote;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			//this.closeSQLObject(conn);
		}	
	}
	
	private void insertarTablaHistoricos(Integer idMensaje, Integer estado, Integer idDestinatariosMensajes)throws Exception{
		String s = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		conn = this.getConn();
		try{
			s = " INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid,destinatariosmensajes) VALUES "
					+ "(HISTORICOID_SEC.NEXTVAL,"+idMensaje+",sysdate, "+estado+","+idDestinatariosMensajes+")";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(s);
		}
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
	}

	public void setMetodoConsulta(int msID, int metodoConsulta) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		String s;
		conn = this.getConn();
		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE tbl_mensajes SET METODOCONSULTA = " + metodoConsulta + " WHERE mensajeid = " + msID;
			stmt.executeUpdate(s);

		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.setMetodoConsulta : " + e.getErrorCode() + " - " + e.getMessage());

		} finally {
			this.closeSQLObject(stmt);

		}

	}
	
	public List<EnvioGISSXMLBean> obtenerMensajesReenvioGISS(Integer servicio, Integer reintentos) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder();
		List<EnvioGISSXMLBean>  reenvios = new ArrayList<EnvioGISSXMLBean>(); 
		try {
			Connection conn = this.getConn();
			query.append(" SELECT dm.CODIGOEXTERNO,dm.DESTINATARIO,m.CODORGANISMOPAGADOR, to_char(sysdate,'DDMMYYYYHH24MISS') ||'_'|| m.CODORGANISMOPAGADOR AS objeto,m.cuerpo FROM TBL_SERVICIOS  s, TBL_LOTESENVIOS  l, TBL_MENSAJES  m, TBL_DESTINATARIOS_MENSAJES  dm ");
			query.append(" WHERE s.SERVICIOID=" + servicio);
			query.append(" AND M.NUMEROENVIOS < " + reintentos);
			query.append(" AND s.SERVICIOID = l.SERVICIOID ");
			query.append(" AND l.LOTEENVIOID = m.LOTEENVIOID ");
			query.append(" AND m.MENSAJEID = dm.MENSAJEID ");
			query.append(" AND dm.estado IN ('INCIDENCIA') ");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				EnvioGISSXMLBean envioGISS = new EnvioGISSXMLBean();
				envioGISS.setAplicacion("");
				envioGISS.setCodOrganismoPagadorSMS(rs.getString(3));
				envioGISS.setContenidoMsj(rs.getString(5));
				envioGISS.setDestinatario(rs.getString(2));
				envioGISS.setNumeroTelefonoDestino(rs.getString(2));
				envioGISS.setUsuSistemaEnvio(rs.getString(4));
				envioGISS.setPassSistemaEnvio(rs.getString(4));
				envioGISS.setIdPeticion(rs.getString(1));
				reenvios.add(envioGISS);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.reenviarMensajesGISS : "
					+ e.getErrorCode() + " - " + e.getMessage(), e);
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
		return reenvios;
	}
	
	public void anularMensajesCaducadosGISS(Integer servicio, Integer reintentos, String descripcion) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder();
		try {
			Connection conn = this.getConn();
			query.append(" SELECT m.MENSAJEID FROM TBL_SERVICIOS  s, TBL_LOTESENVIOS  l, TBL_MENSAJES  m, TBL_DESTINATARIOS_MENSAJES  dm ");
			query.append(" WHERE s.SERVICIOID=" + servicio);
			query.append(" AND M.NUMEROENVIOS >= " + reintentos);
			query.append(" AND s.SERVICIOID = l.SERVICIOID ");
			query.append(" AND l.LOTEENVIOID = m.LOTEENVIOID ");
			query.append(" AND m.MENSAJEID = dm.MENSAJEID ");
			query.append(" AND dm.estado IN ('INCIDENCIA') ");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				this.setEstadoMensaje(rs.getInt(1), ESTADO_ANULADO, descripcion);
			}
		} catch (SQLException e) {
			logger.error("EnvioMensajesPremiumDAO.anularMensajesCaducadosGISS : "
					+ e.getErrorCode() + " - " + e.getMessage());	
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}
}
