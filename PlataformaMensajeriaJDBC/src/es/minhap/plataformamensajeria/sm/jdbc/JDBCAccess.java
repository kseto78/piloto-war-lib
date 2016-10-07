package es.minhap.plataformamensajeria.sm.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.Address;
import javax.mail.SendFailedException;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.rowset.OracleCachedRowSet;

import com.sun.mail.smtp.SMTPAddressFailedException;

import es.minhap.plataformamensajeria.sm.modelo.Adjunto;
import es.minhap.plataformamensajeria.sm.modelo.Aplicacion;
import es.minhap.plataformamensajeria.sm.modelo.DatosServicio;
import es.minhap.plataformamensajeria.sm.modelo.DestinatarioDMensaje;
import es.minhap.plataformamensajeria.sm.modelo.Historico;
import es.minhap.plataformamensajeria.sm.modelo.HistoricoData;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;
import es.minhap.plataformamensajeria.sm.modelo.Proveedor;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.Recipients;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;

public class JDBCAccess {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(JDBCAccess.class);

	// Procedimiento que recupera uno a uno el msID de la cola de mensajes
	public Integer DequeueMail(Connection conn) throws SQLException {

		Integer output = 0;
		CallableStatement stmt = null;
		log.info("DequeueMail entrando: ");

		try {
			stmt = conn.prepareCall("{? = call DEQUEUE_MAIL }",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setQueryTimeout(180);

			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			log.debug("DequeueMail ejecutando llamada: " + Calendar.getInstance().getTimeInMillis());
			stmt.execute();
			log.debug("DequeueMail llamada ejecutada: " + Calendar.getInstance().getTimeInMillis());
			BigDecimal messageQueueId = (BigDecimal) stmt.getObject(1);

			if (messageQueueId != null)
				output = messageQueueId.intValue();

			if (null != stmt) {
				stmt.close();
			}

		} catch (Exception e) {
			log.error("Error DequeueMail",e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			log.info("DequeueMail saliendo: ");
		}

		return output;

	}

	public ArrayList<MailData> GetMailData(int msID, Connection conn)
			throws SQLException {
		ArrayList<MailData> res = new ArrayList<MailData>();
		ResultSet details = null;
		try {
			if (esMultidestinatario(msID, conn)) {
				MailData mailDataComun = new MailData();
				mailDataComun.Attachments = GetAttachment(msID, conn);
				mailDataComun.Images = GetImage(msID, conn);
				mailDataComun.Servers = GetServidores(msID, conn);
				mailDataComun.ServiceData = GetDataFromServices(msID, conn);
				Integer modo = GetModoMensaje(msID,conn);
				details = GetDetailsMultidestinatario(msID, conn);
				if (modo.equals(1)) {
						ArrayList<Recipients> recipients = getRecipientsMultidestinatarioModo1(
								msID, conn);
						for (Recipients rec : recipients) {
							MailData data = crearMailData(details, modo);

							data.esMultidestinatario = true;
							data.Recipients = rec;
							data.Attachments = mailDataComun.Attachments;
							data.Images = mailDataComun.Images;
							data.Servers = mailDataComun.Servers;
							data.ServiceData = mailDataComun.ServiceData;

							res.add(data);
						}
					} else {
						MailData data = crearMailData(details, modo);
						data.esMultidestinatario = true;
						data.Recipients = GetRecipientsMultidestinatario(msID,
								conn);
						data.Attachments = mailDataComun.Attachments;
						data.Images = mailDataComun.Images;
						data.Servers = mailDataComun.Servers;
						data.ServiceData = mailDataComun.ServiceData;

						res.add(data);

					}
				

			} else {
				MailData data = new MailData();

				data.Recipients = GetRecipients(msID, conn);
				data.Attachments = GetAttachment(msID, conn);
				data.Images = GetImage(msID, conn);
				details = GetDetails(msID, conn);
				if (null != details && details.next()) {
					data.Subject = details.getString("CABECERA");
					data.Body = details.getString("CUERPO");
					if (details.getString("TIPOCUERPO") != null)
						data.TipoCuerpo = details.getString("TIPOCUERPO");
					if (details.getString("TIPOCODIFICACION") != null)
						data.TipoCodificacion = details
								.getString("TIPOCODIFICACION");
				}

				data.Servers = GetServidores(msID, conn);

				data.ServiceData = GetDataFromServices(msID, conn);
				res.add(data);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetMailData : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(details);
		}
		return res;
	}

	private Integer GetModoMensaje(int msID, Connection conn) {
		ResultSet rs = null;
		Statement stmt = null;
		Integer res = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT MODO FROM TBL_MENSAJES  WHERE MENSAJEID = " + msID);
			if (rs.next())
				res = rs.getInt("MODO");
			
		} catch (SQLException e) {
			log.error("JDBCAccess.GetDetails : " + e.getErrorCode() + " - "
					+ e.getMessage());

		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;
	}

	private MailData crearMailData(ResultSet details, Integer modo) throws SQLException {
		MailData data = new MailData();
		if (details.next()){
			data.Subject = details.getString("CABECERA");
			data.Body = details.getString("CUERPO");
			if (details.getString("TIPOCUERPO") != null)
				data.TipoCuerpo = details.getString("TIPOCUERPO");
			if (details.getString("TIPOCODIFICACION") != null)
				data.TipoCodificacion = details.getString("TIPOCODIFICACION");
			data.modo = modo;
		}
		return data;
	}

	public ArrayList<SMSData> GetSMSData(int smsId, Connection conn)
			throws SQLException {
		ArrayList<SMSData> res = new ArrayList<SMSData>();
		ResultSet details = null;
		try {
			if (esMultidestinatario(smsId, conn)) {
				// TODO:
				SMSData smsDataComun = new SMSData();
				smsDataComun.ServiceData = GetDataFromServices(smsId, conn);

				if (smsDataComun.ServiceData.getMultiOrganismo() == 1) {
					smsDataComun.Servers = GetProveedoresMultiorganismo(smsId,
							conn);
					smsDataComun.Servers.addAll(GetProveedores(smsId, conn));
				} else {
					smsDataComun.Servers = GetProveedores(smsId, conn);
				}

				details = GetDetailsMultidestinatario(smsId, conn);
				while (details.next()) {
					SMSData data = new SMSData();
					data.Telefono = details.getString("DESTINATARIO");
					data.destinatarioMensajeId = details.getBigDecimal(
							"DESTINATARIOSMENSAJES").longValue();
					if (details.getString("CUERPO") == null) {
						data.Body = "";
					} else {
						data.Body = details.getString("CUERPO");
					}

					data.Servers = smsDataComun.Servers;
					data.ServiceData = smsDataComun.ServiceData;
					data.esMultidestinatario = true;
					res.add(data);
				}

			} else {
				SMSData data = new SMSData();
				details = GetDetails(smsId, conn);
				if (details.next()) {
					data.Telefono = details.getString("TELEFONO");
					if (details.getString("CUERPO") == null) {
						data.Body = "";
					} else {
						data.Body = details.getString("CUERPO");
					}
				}
				data.ServiceData = GetDataFromServices(smsId, conn);
				if (data.ServiceData.getMultiOrganismo() == 1) {
					data.Servers = GetProveedoresMultiorganismo(smsId, conn);
					data.Servers.addAll(GetProveedores(smsId, conn));
				} else {
					data.Servers = GetProveedores(smsId, conn);
				}
				res.add(data);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetSMSData : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(details);
		}
		return res;
	}

	private boolean esMultidestinatario(int smsId, Connection conn)
			throws SQLException {
		Statement stmt0 = null;
		ResultSet rs = null;
		boolean multidestinatario = false;
		stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs = stmt0
				.executeQuery("SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
						+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = "
						+ smsId + " AND lt.MULTIDESTINATARIO = 1");
		if (null != rs && rs.next())
			multidestinatario = true;
		rs.close();
		stmt0.close();
		return multidestinatario;

	}

	public SMSData GetSMSDataMultiorganismo(int smsId, Connection conn)
			throws SQLException {
		SMSData data = new SMSData();
		ResultSet details = null;
		try {
			details = GetDetails(smsId, conn);
			if (details.next()) {
				data.Telefono = details.getString("TELEFONO");
				if (details.getString("CUERPO") == null) {
					data.Body = "";
				} else {
					data.Body = details.getString("CUERPO");
				}
			}
			data.Servers = GetProveedoresMultiorganismo(smsId, conn);
			data.ServiceData = GetDataFromServicesMultiorganismo(smsId, conn);
		} catch (SQLException e) {
			log.error("JDBCAccess.GetSMSDataMultiorganismo : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(details);
		}
		return data;
	}

	public ArrayList<ReceptorSMSData> GetRecepcionSMSData(int smsId,
			Connection conn) throws SQLException {
		ArrayList<ReceptorSMSData> res = new ArrayList<ReceptorSMSData>();

		ResultSet details = null;
		try {
			if (esMultidestinatario(smsId, conn)) {

				ReceptorSMSData smsDataComun = new ReceptorSMSData();
				smsDataComun.ServiceData = GetDataFromServices(smsId, conn);

				smsDataComun.Servers = GetReceptores(smsId, conn);

				details = GetDetailsReceptorSMSMultidestinatario(smsId, conn);

				while (details.next()) {
					ReceptorSMSData data = new ReceptorSMSData();
					data.User = details.getString("USUARIO");
					data.Password = details.getString("PASSWORD");
					data.Telefono = details.getString("DESTINATARIO");
					data.destinatarioMensajeId = details.getBigDecimal(
							"DESTINATARIOSMENSAJES").longValue();
					data.HeaderSMS = details.getString("HEADERSMS");
					if (details.getString("CUERPO") == null) {
						data.Body = "";
					} else {
						data.Body = details.getString("CUERPO");
					}
					data.LoteEnvioId = details.getString("LOTEENVIOID");
					data.Servers = smsDataComun.Servers;
					data.ServiceData = smsDataComun.ServiceData;
					data.esMultidestinatario = true;
					res.add(data);
				}
			} else {
				ReceptorSMSData data = new ReceptorSMSData();
				details = GetDetailsReceptorSMS(smsId, conn);
				if (details.next()) {
					data.User = details.getString("USUARIO");
					data.Password = details.getString("PASSWORD");
					data.Telefono = details.getString("DESTINATARIO");
					data.HeaderSMS = details.getString("HEADERSMS");
					if (details.getString("CUERPO") == null) {
						data.Body = "";
					} else {
						data.Body = details.getString("CUERPO");
					}
					data.LoteEnvioId = details.getString("LOTEENVIOID");
				}
				data.ServiceData = GetDataFromServices(smsId, conn);

				data.Servers = GetReceptores(smsId, conn);
				res.add(data);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetRecepcionSMSData : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(details);
		}
		return res;
	}

	public ArrayList<NotificacionPushData> GetNotificacionPushData(
			int messageId, Connection conn) throws SQLException {
		ArrayList<NotificacionPushData> res = new ArrayList<NotificacionPushData>();
		ResultSet details = null;
		try {
			if (esMultidestinatario(messageId, conn)) {
				NotificacionPushData smsDataComun = new NotificacionPushData();

				smsDataComun.serviceData = GetDataFromServices(messageId, conn);
				smsDataComun.esMultidestinatario = true;

				details = GetDetailsServidorPushMultidestinatario(messageId,
						conn);

				while (details.next()) {
					NotificacionPushData data = new NotificacionPushData();
					data.cabecera = details.getString("CABECERA");
					data.cuerpo = details.getString("CUERPO");
					data.icono = details.getString("ICONO");
					data.sonido = details.getString("SONIDO");
					data.nombreUsuario = details.getString("NOMBREUSUARIO");
					data.gCMApiKey = details.getString("GCMPROJECTKEY");
					data.rutaCertificadoAPNS = details
							.getString("APNSRUTACERTIFICADO");
					data.passwordCertificadoAPNS = details
							.getString("APNSPASSWORDCERTIFICADO");
					data.badge = details.getString("BADGE");
					data.destinatarioMensajeId = details.getBigDecimal(
							"DESTINATARIOSMENSAJES").longValue();
					data.plataformaId = details.getBigDecimal("PLATAFORMAID")
							.intValue();
					data.UsuarioId = Long.parseLong(details
							.getString("DESTINATARIO"));

					ResultSet detailsTokens = GetDetailsTokensUsuarioMultidestinatario(
							data.UsuarioId, conn);
					ArrayList<String> tokensUsuario = new ArrayList<String>();

					while (detailsTokens.next()) {
						tokensUsuario.add(detailsTokens
								.getString("TOKENUSUARIO"));
					}

					data.tokensUsuario = tokensUsuario;
					data.esMultidestinatario = true;
					data.servers = GetServidoresPush(messageId,
							data.plataformaId, conn);
					data.serviceData = smsDataComun.serviceData;
					res.add(data);
				}
			} else {
	
				NotificacionPushData data = new NotificacionPushData();

				details = GetDetailsServidorPush(messageId, conn);
				if (details.next()) {
					data.cabecera = details.getString("CABECERA");
					data.cuerpo = details.getString("CUERPO");
					data.icono = details.getString("ICONO");
					data.sonido = details.getString("SONIDO");
					data.nombreUsuario = details.getString("NOMBREUSUARIO");
					data.gCMApiKey = details.getString("GCMPROJECTKEY");
					data.rutaCertificadoAPNS = details
							.getString("APNSRUTACERTIFICADO");
					data.passwordCertificadoAPNS = details
							.getString("APNSPASSWORDCERTIFICADO");
					data.badge = details.getString("BADGE");
				}
				
				// filtramos por plataforma.
				Integer plataforma = getPlataformaUsuario(messageId, conn);

				ResultSet detailsTokens = GetDetailsTokensUsuario(messageId,
						conn);
				ArrayList<String> tokensUsuario = new ArrayList<String>();

				while (detailsTokens.next()) {
					tokensUsuario.add(detailsTokens.getString("TOKENUSUARIO"));
				}

				data.tokensUsuario = tokensUsuario;

				data.servers = GetServidoresPush(messageId, plataforma, conn);
				data.serviceData = GetDataFromServices(messageId, conn);

				res.add(data);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetNotificacionPushData : "
					+ e.getErrorCode() + " - " + e.getMessage(), e);
		} finally {
			this.closeSQLObject(details);
		}

		return res;
	}

	@SuppressWarnings("null")
	public boolean esMultiorganismo(Integer msID, Connection conn)
			throws SQLException {
		Statement stmt0 = null;
		ResultSet rs = null;
		boolean multiorganismo = false;
		try {
			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt0
					.executeQuery("SELECT TBL_SERVICIOS.MULTIORGANISMO"
							+ " FROM TBL_SERVICIOS, TBL_ORGANISMOS, TBL_ORGANISMOS_SERVICIO, TBL_MENSAJES "
							+ "WHERE (TBL_ORGANISMOS_SERVICIO.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
							+ "AND (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
							+ "AND (TBL_ORGANISMOS.DIR3 = TBL_MENSAJES.CODORGANISMOPAGADOR) "
							+ "AND TBL_MENSAJES.MENSAJEID = " + msID
							+ " AND TBL_SERVICIOS.MULTIORGANISMO= 1");
			if (null != rs || rs.next())
				multiorganismo = true;
		} catch (SQLException e) {
			log.error("JDBCAccess.esMultiorganismo : " + e.getErrorCode()
					+ " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}

		return multiorganismo;
	}

	public Integer getPrioridad(Integer msID, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		Statement stmt0 = null;
		Integer prioridad = new Integer(0);
		try {
			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt0
					.executeQuery("SELECT prioridad FROM TBL_MENSAJES WHERE mensajeid = "
							+ msID);
			rs.next();
			prioridad = rs.getInt(1);
		} catch (SQLException e) {
			log.error("JDBCAccess.getPrioridad : " + e.getErrorCode() + " - "
					+ e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}

		return prioridad;
	}

	// Procedimiento que obtiene los Servidores disponibles para mandar el MAIL
	public ArrayList<ParametrosServidor> GetServidores(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt0 = null;

		ArrayList<ParametrosServidor> pss = new ArrayList<ParametrosServidor>();
		try {
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getPrioridad(msID, conn);

			// SI LA PRIORIDAD DEL MENSAJE ES 1 (MENSAJE INSTANTANEO) NO SE
			// COMPRUEBA LA PLANIFICACION
			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (prioridad.intValue() == 1) {
				rs = stmt0
						.executeQuery("  SELECT servidorid,ip,usuario,contraseña,conexion,puerto,autentificacion,servicioid,prioridad,tiempoespera"
								+ "      FROM view_servidores_prioridad"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "             WHERE mensajeid = "
								+ msID
								+ ")"
								+ "      OR servicioId IS NULL)"
								+ "      AND ip is not null"
								+ "      order by Prioridad");
			} else {
				rs = stmt0
						.executeQuery("SELECT servidorid,ip,usuario,contraseña,conexion,puerto,autentificacion,servicioid,prioridad,tiempoespera"
								+ "      FROM VIEW_SERV_EMAIL"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "		        WHERE mensajeid = "
								+ msID
								+ ")"
								+ "		 OR servicioId IS NULL)"
								+ "   	 AND ip is not null"
								+ "      order by Prioridad");
			}

			stmt0.setQueryTimeout(660);

			while (rs.next()) {
				ParametrosServidor ps = new ParametrosServidor();
				ps.setServidor(rs.getInt("SERVIDORID"));
				ps.setIP(rs.getString("IP"));
				ps.setUsuario(rs.getString("USUARIO"));
				ps.setContrasena(rs.getString("CONTRASEÑA"));
				ps.setConexion(rs.getString("CONEXION"));
				ps.setPuerto(rs.getString("PUERTO"));
				ps.setAutentificacion(rs.getString("AUTENTIFICACION"));
				ps.setTimeOut(rs.getString("TIEMPOESPERA"));
				pss.add(ps);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetServidores : " + e.getErrorCode() + " - "
					+ e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}
		return pss;
	}

	public ArrayList<ParametrosProveedor> GetProveedores(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt0 = null;

		ArrayList<ParametrosProveedor> pss = new ArrayList<ParametrosProveedor>();
		try {
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getPrioridad(msID, conn);

			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (prioridad.intValue() == 1) {
				rs = stmt0
						.executeQuery("SELECT proveedorid,url,id,telefono,texto,uim,servicioid,prioridad"
								+ "      FROM view_proveedores_prioridad"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid                           "
								+ "				  			WHERE mensajeid = "
								+ msID
								+ ")"
								+ "		       OR servicioId IS NULL)"
								+ "      AND url is not null"
								+ "	     order by Prioridad");
			} else {
				rs = stmt0
						.executeQuery("SELECT proveedorid,url,id,telefono,texto,uim,servicioid,prioridad"
								+ "      FROM VIEW_SERV_SMS"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid                           "
								+ "							WHERE mensajeid = "
								+ msID
								+ ")"
								+ "		       OR servicioId IS NULL)"
								+ "      AND url is not null"
								+ "	     order by Prioridad");
			}

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
			log.error("JDBCAccess.GetProveedores : " + e.getErrorCode() + " - " + e.getMessage(), e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}
		return pss;
	}

	public ArrayList<ParametrosProveedor> GetProveedoresMultiorganismo(
			Integer msID, Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt0 = null;
		ArrayList<ParametrosProveedor> pss = new ArrayList<ParametrosProveedor>();

		try {
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getPrioridad(msID, conn);

			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (prioridad.intValue() == 1) {
				rs = stmt0
						.executeQuery("SELECT proveedorid,url,organismoid,servicioid,prioridad"
								+ "  FROM view_prov_prioridad_multiorg"
								+ "  WHERE (servicioId = (SELECT servicioid "
								+ "FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid "
								+ "WHERE mensajeid = "
								+ msID
								+ ") "
								+ "OR servicioId IS NULL) "
								+ "AND url is not null " + "order by Prioridad");
			} else {
				rs = stmt0
						.executeQuery(" SELECT TBL_MENSAJES.CODORGANISMOPAGADOR, VIEW_SERV_SMS_MULTIORG.PROVEEDORID, VIEW_SERV_SMS_MULTIORG.URL, "
								+ "VIEW_SERV_SMS_MULTIORG.SERVICIOID, VIEW_SERV_SMS_MULTIORG.PRIORIDAD, TBL_ORGANISMOS.ORGANISMOID "
								+ "FROM VIEW_SERV_SMS_MULTIORG, TBL_ORGANISMOS, TBL_MENSAJES "
								+ "WHERE (TBL_MENSAJES.CODORGANISMOPAGADOR = TBL_ORGANISMOS.DIR3) "
								+ "AND (VIEW_SERV_SMS_MULTIORG.ORGANISMOID = TBL_ORGANISMOS.ORGANISMOID) "
								+ "AND (TBL_MENSAJES.MENSAJEID = "
								+ msID
								+ ") "
								+ "AND url is not null order by Prioridad");
			}

			while (rs.next()) {
				ParametrosProveedor ps = new ParametrosProveedor();

				ps.setProveedorId(rs.getInt("PROVEEDORID"));
				ps.setUrl(rs.getString("URL"));
				/*
				 * ps.setId(rs.getString("ORGANISMOID"));
				 * ps.setTexto(rs.getString("TEXTO"));
				 * ps.setUIM(rs.getString("UIM"));
				 * ps.setTelefono(rs.getString("TELEFONO"));
				 */pss.add(ps);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.GetProveedoresMultiorganismo : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}
		return pss;
	}

	public ArrayList<ParametrosReceptor> GetReceptores(Integer msID,
			Connection conn) throws SQLException {

		ResultSet rs = null;
		Statement stmt0 = null;

		ArrayList<ParametrosReceptor> pss = new ArrayList<ParametrosReceptor>();

		try {
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getPrioridad(msID, conn);

			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (prioridad.intValue() == 1) {
				rs = stmt0
						.executeQuery("SELECT receptorid,url,id,telefono,texto,uim,servicioid,prioridad FROM view_receptores_prioridad"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "				            WHERE mensajeid = "
								+ msID
								+ ")"
								+ "      OR servicioId IS NULL)"
								+ "      AND url is not null"
								+ "      order by Prioridad");
			} else {
				rs = stmt0
						.executeQuery("SELECT receptorid,url,id,telefono,texto,uim,servicioid,prioridad FROM VIEW_SERV_RECEPTOR_SMS"
								+ "      WHERE (servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "				            WHERE mensajeid = "
								+ msID
								+ ")"
								+ "      OR servicioId IS NULL)"
								+ "      AND url is not null"
								+ "      order by Prioridad");
			}

			while (rs.next()) {
				ParametrosReceptor ps = new ParametrosReceptor();

				ps.setReceptorId(rs.getInt("RECEPTORID"));
				ps.setUrl(rs.getString("URL"));
				ps.setId(rs.getString("ID"));
				ps.setTexto(rs.getString("TEXTO"));
				ps.setUIM(rs.getString("UIM"));
				ps.setTelefono(rs.getString("TELEFONO"));
				pss.add(ps);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.GetReceptores : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);
		}
		return pss;
	}

	public ArrayList<ParametrosServidorPush> GetServidoresPush(Integer msID,
			Integer plataformaId, Connection conn) throws SQLException {

		ResultSet rs = null;
		Statement stmt0 = null;

		ArrayList<ParametrosServidorPush> pss = new ArrayList<ParametrosServidorPush>();

		try {
			// Obtenemos primero la prioridad del mensaje
			Integer prioridad = getPrioridad(msID, conn);

			stmt0 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (prioridad.intValue() == 1) {
				rs = stmt0
						.executeQuery("SELECT servidorpushid,plataformaid,url,urlfeedback,puertourl,puertourlfeedback,servicioid,prioridad FROM view_servidorespush_prioridad"
								+ "      WHERE ( plataformaID = "
								+ plataformaId
								+ " and servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "			                  WHERE mensajeid  = "
								+ msID
								+ ") OR servicioId IS NULL)"
								+ "      		AND url is not null"
								+ "	    order by Prioridad");
			} else {
				rs = stmt0
						.executeQuery("SELECT servidorpushid,plataformaid,url,urlfeedback,puertourl,puertourlfeedback,servicioid,prioridad FROM VIEW_SERV_SERVIDOR_PUSH"
								+ "      WHERE ( plataformaID = "
								+ plataformaId
								+ " and servicioId = (SELECT servicioid FROM tbl_mensajes m inner join tbl_lotesenvios l on m.loteenvioid = l.loteenvioid"
								+ "			                  WHERE mensajeid  = "
								+ msID
								+ ") OR servicioId IS NULL)"
								+ "      		AND url is not null"
								+ "	    order by Prioridad");
			}

			while (rs.next()) {
				ParametrosServidorPush ps = new ParametrosServidorPush();

				ps.setServidorPushId(rs.getInt("SERVIDORPUSHID"));
				ps.setPlataformaId(rs.getInt("PLATAFORMAID"));
				ps.setUrl(rs.getString("URL"));
				ps.setUrlFeedback(rs.getString("URLFEEDBACK"));
				ps.setPuertoUrl(rs.getInt("PUERTOURL"));
				ps.setPuertoUrlFeedback(rs.getInt("PUERTOURLFEEDBACK"));
				ps.setServicioId(rs.getInt("SERVICIOID"));
				ps.setPrioridad(rs.getInt("PRIORIDAD"));
				pss.add(ps);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.GetServidoresPush : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt0);

		}

		return pss;
	}

	public Recipients GetRecipients(int msID, Connection conn)
			throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		Recipients recipients = new Recipients();

		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery(" SELECT EMAIL,TIPODESTINATARIO FROM TBL_DESTINATARIOS"
							+ " WHERE EMAIL IS NOT NULL AND TIPODESTINATARIO IN ('TO', 'CC', 'BCC') AND MENSAJEID = "
							+ msID);

			while (rs.next()) {
				DestinatarioDMensaje dm = new DestinatarioDMensaje();
				String email = rs.getString("EMAIL");
				String tipoDestinatario = rs.getString("TIPODESTINATARIO");
				if (tipoDestinatario != null)
					tipoDestinatario = tipoDestinatario.toUpperCase();
				if (tipoDestinatario.equals("TO")) {
					dm.email = email;
					dm.idDestinatarioMensaje = null;
					recipients.To.add(dm);
				} else if (tipoDestinatario.equals("CC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = null;
					recipients.Cc.add(dm);
				} else if (tipoDestinatario.equals("BCC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = null;
					recipients.Bcc.add(dm);
				}
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return recipients;

	}

	public ArrayList<Recipients> getRecipientsMultidestinatarioModo1(
			Integer mID, Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Recipients> res = new ArrayList<Recipients>();
		PreparedStatement pstmt = null;

		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT dm.DESTINATARIOSMENSAJES,  d.EMAIL, d.TIPODESTINATARIO"
							+ " from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_DESTINATARIOS d on  dm.DESTINATARIO = d.DESTINATARIOID "
							+ "where dm.MENSAJEID = "
							+ mID
							+ "AND (ESTADO NOT LIKE 'ENVIADO' OR ESTADO NOT LIKE 'CANCELADO')");

			while (rs.next()) {
				Recipients rec = new Recipients();
				DestinatarioDMensaje dm = new DestinatarioDMensaje();
				String email = rs.getString("EMAIL");
				String tipoDestinatario = rs.getString("TIPODESTINATARIO");
				Integer idDestinatariosMensajes = rs
						.getInt("DESTINATARIOSMENSAJES");
				if (tipoDestinatario != null)
					tipoDestinatario = tipoDestinatario.toUpperCase();
				if (tipoDestinatario.equals("TO")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					rec.To.add(dm);
				} else if (tipoDestinatario.equals("CC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					rec.Cc.add(dm);
				} else if (tipoDestinatario.equals("BCC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					rec.Bcc.add(dm);
				}
				res.add(rec);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
		}

		return res;

	}

	public Recipients GetRecipientsMultidestinatario(Integer mID,
			Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Recipients recipients = new Recipients();

		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT dm.DESTINATARIOSMENSAJES,  d.EMAIL, d.TIPODESTINATARIO"
							+ " from TBL_DESTINATARIOS_MENSAJES dm inner join TBL_DESTINATARIOS d on  dm.DESTINATARIO = d.DESTINATARIOID "
							+ "where dm.MENSAJEID = "
							+ mID
							+ "AND (ESTADO NOT LIKE 'ENVIADO' OR ESTADO NOT LIKE 'CANCELADO')");

			while (rs.next()) {
				DestinatarioDMensaje dm = new DestinatarioDMensaje();
				String email = rs.getString("EMAIL");
				String tipoDestinatario = rs.getString("TIPODESTINATARIO");
				Integer idDestinatariosMensajes = rs
						.getInt("DESTINATARIOSMENSAJES");
				if (tipoDestinatario != null)
					tipoDestinatario = tipoDestinatario.toUpperCase();
				if (tipoDestinatario.equals("TO")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					recipients.To.add(dm);
				} else if (tipoDestinatario.equals("CC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					recipients.Cc.add(dm);
				} else if (tipoDestinatario.equals("BCC")) {
					dm.email = email;
					dm.idDestinatarioMensaje = idDestinatariosMensajes;
					recipients.Bcc.add(dm);
				}
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.GetRecipients : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return recipients;

	}

	// Procedimiento para ver si el Mensaje ya ha sido enviado pero ha vuelto a
	// la cola de mensajeria
	public boolean GetStatusMessage(Integer messageId, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		String Estado = "";
		Statement stmt = null;
		boolean status = false;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(" SELECT ESTADOACTUAL FROM TBL_MENSAJES"
					+ " WHERE MENSAJEID = " + messageId);

			rs.next();
			Estado = rs.getString(1);

			if (Estado.equals("PENDIENTE") || Estado.equals("INCIDENCIA") || Estado.equals("ENVIANDO") )
				status = true;

		} catch (SQLException e) {
			log.error("JDBCAccess.mensajeSetMetodoConsulta : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);

		}
		return status;
	}

	// Procedimiento que cambia el metodo de consulta de estadoa de un SMS
	public void mensajeSetMetodoConsulta(int msID, int metodoConsulta,
			Connection conn) throws Exception {

		Statement stmt = null;
		String s;

		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "UPDATE tbl_mensajes SET METODOCONSULTA = " + metodoConsulta
					+ " WHERE mensajeid = " + msID;
			stmt.executeUpdate(s);

		} catch (SQLException e) {
			log.error("JDBCAccess.mensajeSetMetodoConsulta : " + e.getErrorCode() + " - " + e.getMessage(),e);

		} finally {
			this.closeSQLObject(stmt);

		}
	}

	public void SetMessageStatusMail(int msID, int estado, int servidorId,
			String description, Object data, Object o, Connection conn)
			throws Exception {
		Statement stmt = null;
		ResultSet rs;
		String s;
		Address[] valid = null;
		String estado_desc = "";
		ArrayList<String> listaIdDestMensajesValidos = new ArrayList<String>();
		try {
			if (o instanceof SMTPAddressFailedException) {
				SMTPAddressFailedException smtpEx = (SMTPAddressFailedException) o;
				valid = (Address[]) smtpEx.getValidSentAddresses();
			} else if (o instanceof SendFailedException) {
				SendFailedException sfex = (SendFailedException) o;
				valid = (Address[]) sfex.getValidSentAddresses();
			}

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			if (null != valid && valid.length > 0) {
				for (Address address : valid) {
					estado_desc = "ENVIADO";

					rs = stmt
							.executeQuery(" SELECT m.DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES dm "
									+ "inner join TBL_DESTINATARIOS d on dm.DESTINATARIO = d.DESTINATARIOID "
									+ " WHERE dm.MENSAJEID = "
									+ msID
									+ " and d.EMAIL = " + address.getType());
					if (rs.next()) {
						Integer dm = rs.getInt("DESTINATARIOSMENSAJES");
						listaIdDestMensajesValidos.add(dm.toString());
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where "
								+ "destinatariosmensajes =" + dm;
						rs = stmt.executeQuery(s);

						setEstadoData(data, estado_desc);
					}

				}
			}

			ArrayList<String> lista = new ArrayList<String>(
					Arrays.asList(getIdDestinatarioMensaje(data).split(",")));

			estado_desc = "INCIDENCIA";
			for (String iddm : lista) {
				if (!listaIdDestMensajesValidos.contains(iddm)) {

					s = "UPDATE TBL_DESTINATARIOS_MENSAJES set estado = '"
							+ estado_desc + "' where "
							+ "destinatariosmensajes ="
							+ Integer.parseInt(iddm);
					rs = stmt.executeQuery(s);
				}
			}

			s = "UPDATE tbl_mensajes set estado = '" + estado_desc + "' where "
					+ "mensajeId =" + msID;
			rs = stmt.executeQuery(s);

			rs = stmt.executeQuery(" SELECT LOTEENVIOID FROM TBL_MENSAJES "
					+ " WHERE MENSAJEID = " + msID);

			if (rs.next()) {
				s = "UPDATE tbl_lotesenvio set estado = '" + estado_desc
						+ "' where " + "loteenvioId ="
						+ rs.getInt("LOTEENVIOID");
				rs = stmt.executeQuery(s);
			}

		} catch (SQLException e) {
			if (log.isInfoEnabled())
				log.info("JDBCAccess.SetMessageStatus : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(conn);
		}
	}

	
	public void SetMessageStatus(int msID, int estado, int servidorId, String description, Connection conn,Boolean refreshStatus) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String s;

		String estado_desc = "";

		try {

			if (estado == 1) {
				estado_desc = "ENVIADO";
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				s = "UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID;
				rs = stmt.executeQuery(s);
				stmt.close();
				rs.close();
			} else if (estado == 2) {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("SELECT numeroenvios FROM tbl_mensajes WHERE mensajeid = " + msID);
				rs.next();
				Integer numeroEnvios = rs.getInt(1);

				//recuperamos el numero m�ximo de intentos para el mensaje
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = stmt.executeQuery("SELECT TBL_SERVIDORES_SERVICIOS.NUMINTENTOS, TBL_LOTESENVIOS.SERVICIOID,TBL_SERVIDORES_SERVICIOS.SERVIDORID,"
						+ "  TBL_MENSAJES.NUMEROENVIOS  FROM TBL_MENSAJES, TBL_SERVIDORES_SERVICIOS, TBL_LOTESENVIOS"
						+ " WHERE     (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) AND (TBL_SERVIDORES_SERVICIOS.SERVICIOID = TBL_LOTESENVIOS.SERVICIOID)"
						+ " AND TBL_MENSAJES.MENSAJEID = " + msID);
				rs1.next();
				Integer numeroMaxEnvios = rs1.getInt(1);

				if (numeroEnvios >= numeroMaxEnvios) {
					estado_desc = "ANULADO";
				} else {
					estado_desc = "INCIDENCIA";
				}
				stmt.close();
				rs1.close();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
				stmt.close();
				rs.close();
			}  else if (estado == 6) {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("SELECT numeroenvios FROM tbl_mensajes WHERE mensajeid = " + msID);
				rs.next();
				Integer numeroEnvios = rs.getInt(1);

				//recuperamos el numero m�ximo de intentos para el mensaje
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = stmt.executeQuery("SELECT TBL_SERVIDORES_SERVICIOS.NUMINTENTOS, TBL_LOTESENVIOS.SERVICIOID,TBL_SERVIDORES_SERVICIOS.SERVIDORID,"
						+ "  TBL_MENSAJES.NUMEROENVIOS  FROM TBL_MENSAJES, TBL_SERVIDORES_SERVICIOS, TBL_LOTESENVIOS"
						+ " WHERE     (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) AND (TBL_SERVIDORES_SERVICIOS.SERVICIOID = TBL_LOTESENVIOS.SERVICIOID)"
						+ " AND TBL_MENSAJES.MENSAJEID = " + msID);
				rs1.next();
				Integer numeroMaxEnvios = rs1.getInt(1);

				if (numeroEnvios >= numeroMaxEnvios && refreshStatus) {
					estado_desc = "ANULADO";
					stmt.close();
					rs1.close();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery("UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
					stmt.close();
					rs.close();
				} else {
					stmt.close();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery("UPDATE tbl_mensajes SET  NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
					stmt.close();
					rs.close();
				}
			}

			// SE MODIFICA EL VALOR DEL ESTADO PARA QUE SEA 'PENDIENTE DE OPERADORA'
			int estado_aux;

			if (estado == 3)
				estado_aux = 6;
			else
				estado_aux = estado;
			
			//actualizamos el estado del lote
			actualizarEstadoLote(msID, conn);

			// AQUI HAY QUE CAMBIAR EL ESTADO A RECHAZADO SI HACE 3 INTENTOS Y NO SE HA ENVIADO

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT historicoid_sec.nextval FROM dual");
			rs.next();
			Integer historicoid = rs.getInt(1);

			stmt.close();
			rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String estado_id;
			if (estado_desc.equals("ANULADO")) {
				estado_id = "4";
			} else {
				estado_id = "" + estado_aux;
			}
			s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion) ";
			s += "VALUES(" + historicoid + ",sysdate," + msID + "," + estado_id + "," + servidorId + ",'" + ((!description.isEmpty() && description.length()> 1) ? description : estado_desc) + "')";
			rs = stmt.executeQuery(s);

			
		} catch (SQLException e) {
			log.error("JDBCAccess.SetMessageStatus : " + e.getErrorCode() + " - " + e.getMessage(),e);
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(rs1);
			this.closeSQLObject(stmt);
		}
		
	}
	
	// Procedimiento que cambia el estado del MAIL
	public void SetMessageStatus(int msID, int estado, int servidorId, String description, Connection conn) throws Exception {
		this.SetMessageStatus(msID, estado, servidorId, description, conn, false);
	}
	
	public HistoricoData setMessageStatusMult(int msID, int estado, int servidorId,
			String description, Object data, Connection conn, Boolean refreshStatus) throws Exception {
		HistoricoData res = new HistoricoData();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String s;

		String estado_desc = "";

		try {

			if (estado == 1) {
				estado_desc = "ENVIADO";
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				if (data instanceof MailData){
					MailData mailData = (MailData) data;
					if (mailData.modo == 0){
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where "
								+ "destinatariosmensajes in( "
								+ getIdDestinatarioMensaje(data) + ")";
					}else{
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where " + "destinatariosmensajes = "
								+ getIdDestinatarioMensaje(data);
					}
				}else{
					
					s = "UPDATE tbl_destinatarios_mensajes set estado = '"
							+ estado_desc + "' where "
							+ "destinatariosmensajes = " + getIdDestinatarioMensaje(data) ;
				}
				rs = stmt.executeQuery(s);
				rs.close();
				stmt.close();
//				setEstadoData(data, estado_desc);
				
				//Actualizo en tabla de mensajes.
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
				stmt.close();
				rs.close();
				
				//Inserto historico
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery("SELECT historicoid_sec.nextval FROM dual");
				rs.next();
				Integer historicoid = rs.getInt(1);

				stmt.close();
				rs.close();
				
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion, destinatariosmensajes) ";
				s += "VALUES(" + historicoid + ",sysdate," + msID + ",1," + servidorId + ",'" + ((!description.isEmpty() && description.length()> 1) ? description : estado_desc) + "',"+getIdDestinatarioMensaje(data)+")";
				rs = stmt.executeQuery(s);

			} else if (estado == 2) {

				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = stmt
						.executeQuery("SELECT numeroenvios FROM tbl_mensajes WHERE mensajeid = "
								+ msID);
				rs.next();
				Integer numeroEnvios = rs.getInt(1);

				stmt.close();
				rs.close();
				
				// recuperamos el numero maximo de intentos para el mensaje
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				rs1 = stmt
						.executeQuery("SELECT TBL_SERVIDORES_SERVICIOS.NUMINTENTOS, TBL_LOTESENVIOS.SERVICIOID,TBL_SERVIDORES_SERVICIOS.SERVIDORID,"
								+ "  TBL_MENSAJES.NUMEROENVIOS  FROM TBL_MENSAJES, TBL_SERVIDORES_SERVICIOS, TBL_LOTESENVIOS"
								+ " WHERE     (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) AND (TBL_SERVIDORES_SERVICIOS.SERVICIOID = TBL_LOTESENVIOS.SERVICIOID)"
								+ " AND TBL_MENSAJES.MENSAJEID = " + msID);
				rs1.next();
				Integer numeroMaxEnvios = rs1.getInt(1);

				if (numeroEnvios >= numeroMaxEnvios) {
					estado_desc = "ANULADO";
				} else {
					estado_desc = "INCIDENCIA";
				}
				stmt.close();
				rs.close();
				if (data instanceof MailData){
					MailData mailData = (MailData) data;
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					if (mailData.modo == 0){
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where "
								+ "destinatariosmensajes in( "
								+ getIdDestinatarioMensaje(data) + ")";
					}else{
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where " + "destinatariosmensajes = "
								+ getIdDestinatarioMensaje(data);
					}
				}else{
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					s = "UPDATE tbl_destinatarios_mensajes set estado = '"
							+ estado_desc + "' where "
							+ "destinatariosmensajes = " + getIdDestinatarioMensaje(data) ;
				}
				
				rs = stmt.executeQuery(s);
				rs.close();
				stmt.close();
//				setEstadoData(data, estado_desc);
			} else if (estado == 6) {

				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = stmt
						.executeQuery("SELECT numeroenvios FROM tbl_mensajes WHERE mensajeid = "
								+ msID);
				rs.next();
				Integer numeroEnvios = rs.getInt(1);

				stmt.close();
				rs.close();
				
				//Verificar si es multiorganismo
				Integer multiorganismo = 0;
				Integer servicioId = null;
				Integer organismoId =  null;
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				
				rs = stmt
						.executeQuery("SELECT TBL_SERVICIOS.MULTIORGANISMO,TBL_SERVICIOS.SERVICIOID, TBL_ORGANISMOS.ORGANISMOID FROM TBL_MENSAJES, TBL_LOTESENVIOS, TBL_SERVICIOS, TBL_ORGANISMOS"
								+ "  WHERE  (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) AND  (TBL_SERVICIOS.SERVICIOID = TBL_LOTESENVIOS.SERVICIOID) AND (TBL_MENSAJES.CODORGANISMO = TBL_ORGANISMOS.DIR3)"
								+ " AND TBL_MENSAJES.MENSAJEID = " + msID);
				if(rs.next()){
					multiorganismo = rs.getInt(1);
					servicioId = rs.getInt(2);
					organismoId =  rs.getInt(3);
				}
				
				stmt.close();
				rs.close();
				
				// recuperamos el numero maximo de intentos para el mensaje
				Integer numeroMaxEnvios = Integer.MAX_VALUE;
				if(multiorganismo==0) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
	
					rs1 = stmt
							.executeQuery("SELECT TBL_SERVIDORES_SERVICIOS.NUMINTENTOS, TBL_LOTESENVIOS.SERVICIOID,TBL_SERVIDORES_SERVICIOS.SERVIDORID,"
									+ "  TBL_MENSAJES.NUMEROENVIOS  FROM TBL_MENSAJES, TBL_SERVIDORES_SERVICIOS, TBL_LOTESENVIOS"
									+ " WHERE     (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) AND (TBL_SERVIDORES_SERVICIOS.SERVICIOID = TBL_LOTESENVIOS.SERVICIOID)"
									+ " AND TBL_MENSAJES.MENSAJEID = " + msID);
					
					if(rs1.next()) {
						numeroMaxEnvios = rs1.getInt(1);
					}
					
					stmt.close();
					rs1.close();
				} else if (multiorganismo==1) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
	
					rs1 = stmt
							.executeQuery("SELECT TBL_SERVIDORES_ORGANISMOS.NUMINTENTOS"
									+ " FROM TBL_ORGANISMOS_SERVICIO, TBL_SERVIDORES_ORGANISMOS"
									+ " WHERE (TBL_ORGANISMOS_SERVICIO.ORGANISMOID = TBL_SERVIDORES_ORGANISMOS.ORGANISMOID) "
									+ "  AND TBL_ORGANISMOS_SERVICIO.SERVICIOID = " + servicioId 
									+ "  AND TBL_SERVIDORES_ORGANISMOS.ORGANISMOID = " + organismoId);
					
					if(rs1.next()) {
						numeroMaxEnvios = rs1.getInt(1);
					}
					
					stmt.close();
					rs1.close();
				}				

				if (numeroEnvios >= numeroMaxEnvios && refreshStatus) {
					estado_desc = "ANULADO";
					stmt.close();
					rs.close();
					if (data instanceof MailData){
						MailData mailData = (MailData) data;
						stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						if (mailData.modo == 0){
							s = "UPDATE tbl_destinatarios_mensajes set estado = '"
									+ estado_desc + "' where "
									+ "destinatariosmensajes in( "
									+ getIdDestinatarioMensaje(data) + ")";
						}else{
							s = "UPDATE tbl_destinatarios_mensajes set estado = '"
									+ estado_desc + "' where " + "destinatariosmensajes = "
									+ getIdDestinatarioMensaje(data);
						}
					}else{
						stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						s = "UPDATE tbl_destinatarios_mensajes set estado = '"
								+ estado_desc + "' where "
								+ "destinatariosmensajes = " + getIdDestinatarioMensaje(data) ;
					}
					
					rs = stmt.executeQuery(s);
					rs.close();
					stmt.close();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery("UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
					stmt.close();
					rs.close();
					
					// AQUI HAY QUE CAMBIAR EL ESTADO A RECHAZADO SI HACE 3 INTENTOS Y NO SE HA ENVIADO

					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery("SELECT historicoid_sec.nextval FROM dual");
					rs.next();
					Integer historicoid = rs.getInt(1);

					stmt.close();
					rs.close();
					
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion, destinatariosmensajes) ";
					s += "VALUES(" + historicoid + ",sysdate," + msID + ",4," + servidorId + ",'" + ((!description.isEmpty() && description.length()> 1) ? description : estado_desc) + "',"+getIdDestinatarioMensaje(data)+")";
					rs = stmt.executeQuery(s);
					
				} else {
					stmt.close();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery("UPDATE tbl_mensajes SET  NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + msID);
					stmt.close();
					rs.close();
				}
			}

			// SE MODIFICA EL VALOR DEL ESTADO PARA QUE SEA 'PENDIENTE DE
			// OPERADORA'
			int estado_aux;

			if (estado == 3)
				estado_aux = 6;
			else
				estado_aux = estado;

			// AQUI HAY QUE CAMBIAR EL ESTADO A RECHAZADO SI HACE 3 INTENTOS Y NO SE HA ENVIADO

			String estado_id;
			if (estado_desc.equals("ANULADO")) {
				estado_id = "4";
			} else {
				estado_id = "" + estado_aux;
			}
					
			res.setMensajeId(msID);
			res.setEstado_id(estado_id);
			res.setServidorId(servidorId);
			res.setDescripcion(description);
			res.setDestinatariosMensajes(getIdDestinatarioMensaje(data));
		} catch (SQLException e) {
				log.error("JDBCAccess.SetMessageStatus : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
			this.closeSQLObject(rs1);
		}
		return res;
	}
	
	
	public HistoricoData setMessageStatusMult(int msID, int estado, int servidorId,
			String description, Object data, Connection conn) throws Exception {
		return this.setMessageStatusMult(msID, estado, servidorId, description, data, conn, false);
	}
	
	public void actualizarEstadosMensajesSMS(ArrayList<HistoricoData> listHistoricoData, Connection conn) {
		Integer mensajeAnalizado = 0;
		
			for (HistoricoData hData : listHistoricoData) {
				if (!mensajeAnalizado.equals(hData.getMensajeId())){
					actualizarEstadoMensaje(hData.getMensajeId(), conn);
					actualizarEstadoLote(hData.getMensajeId(),conn);
					mensajeAnalizado = hData.getMensajeId();
				}
			}
	}
	
	private void actualizarEstadoLote(Integer mensajeId, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String estado_desc = "";
		Map<String, Integer> map = new HashMap<String,Integer>();
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "SELECT ESTADOACTUAL, count(*) as TOTAL from TBL_MENSAJES where "
					+ "LOTEENVIOID = (Select LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = " + mensajeId +") group by ESTADOACTUAL";
			rs = stmt.executeQuery(s);
			
			while (rs.next()){
				Integer total = rs.getInt("TOTAL");
				String key = rs.getString("ESTADOACTUAL");
				map.put(key, total);
			}
			if (map.size()==1)
				estado_desc = map.entrySet().iterator().next().getKey();
			else
				estado_desc = "INCIDENCIA";
		
			stmt.close();
			rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "UPDATE tbl_lotesEnvios set estadoenvioid = (select ESTADOID from TBL_ESTADOS where "
					+ "NOMBRE like '"+estado_desc+"') where LOTEENVIOID = (select loteenvioid "
					+ "from tbl_mensajes where mensajeid = "
					+ mensajeId
					+ ")";
			
			rs = stmt.executeQuery(s);
			
			stmt.close();
			rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			s = "UPDATE TBL_GESTIONENVIOS set ESTADOLOTE = '"+estado_desc+"' where LOTEENVIOID = (select loteenvioid "
					+ "from tbl_mensajes where mensajeid = "
					+ mensajeId
					+ ")";
			
			rs = stmt.executeQuery(s);
		}catch (SQLException e){
			log.error("JDBCAccess.actualizarEstadoMensaje : " + e.getErrorCode() + " - " + e.getMessage(),e);
		}finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}

	private void actualizarEstadoMensaje(Integer mensajeId, Connection conn){
		Statement stmt = null;
		ResultSet rs = null;
		String s;
		String estado_desc = "";
		Map<String, Integer> map = new HashMap<String,Integer>();
		
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "SELECT ESTADO, count(*) as TOTAL from TBL_DESTINATARIOS_MENSAJES where "
					+ "MENSAJEID = " + mensajeId +" group by ESTADO";
			rs = stmt.executeQuery(s);
			
			while (rs.next()){
				Integer total = rs.getInt("TOTAL");
				String key = rs.getString("ESTADO");
				map.put(key, total);
			}
			if (map.size()==1)
				estado_desc = map.entrySet().iterator().next().getKey();
			else
				estado_desc = "INCIDENCIA";
		stmt.close();
		rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (estado_desc.equals("ANULADO")){
				s = "UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "' WHERE mensajeid = " + mensajeId;
			}else{
				s = "UPDATE tbl_mensajes SET estadoactual = '" + estado_desc + "', NUMEROENVIOS = NUMEROENVIOS + 1 WHERE mensajeid = " + mensajeId;
			}
			rs = stmt.executeQuery(s);
		}catch (SQLException e){
			log.error("JDBCAccess.actualizarEstadoMensaje : " + e.getErrorCode() + " - " + e.getMessage(),e);	
		}finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}

	public void insertarHistoricos(ArrayList<HistoricoData> listHistoricoData, Connection conn){
		Statement stmt = null;
		ResultSet rs = null;
		
		String s;
		try{
		for (HistoricoData hData : listHistoricoData) {
			
				if (null != hData && null != hData.getDestinatariosMensajes()
						&& hData.getDestinatariosMensajes().split(",") != null) {
					for (String destMensaje : hData.getDestinatariosMensajes()
							.split(",")) {
						stmt = conn.createStatement(
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion,destinatariosmensajes) ";
						s += "VALUES( historicoid_sec.nextval,sysdate,"
								+ hData.getMensajeId() + ","
								+ hData.getEstado_id() + ","
								+ hData.getServidorId() + ",'"
								+ hData.getDescripcion() + "'," + destMensaje
								+ ")";
						rs = stmt.executeQuery(s);
						stmt.close();
						rs.close();
					}
				} else {
					String destMensaje = hData.getDestinatariosMensajes();
					stmt = conn.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,servidorid,descripcion,destinatariosmensajes) ";
					s += "VALUES( historicoid_sec.nextval,sysdate,"
							+ hData.getMensajeId() + "," + hData.getEstado_id()
							+ "," + hData.getServidorId() + ",'"
							+ hData.getDescripcion() + "'," + destMensaje + ")";
					rs = stmt.executeQuery(s);
					stmt.close();
					rs.close();
				}
		}
		stmt.close();
		}catch(SQLException e){
			log.error("JDBCAccess.insertarHistoricos : " + e.getErrorCode() + " - " + e.getMessage(),e);	
		}
		finally{
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
	}
	
	
	private void setEstadoData(Object data, String estado) {
		if (data instanceof SMSData) {
			SMSData smsData = (SMSData) data;
			smsData.estado = estado;
		} else if (data instanceof ReceptorSMSData) {
			ReceptorSMSData smsData = (ReceptorSMSData) data;
			smsData.estado = estado;
		} else if (data instanceof NotificacionPushData) {
			NotificacionPushData smsData = (NotificacionPushData) data;
			smsData.setEstado(estado);
		} else if (data instanceof MailData) {
			MailData smsData = (MailData) data;
			smsData.estado = estado;
		}

	}

	private String getIdDestinatarioMensaje(Object data) {
		ArrayList<String> aux = new ArrayList<String>();
		String res = "";
		if (data instanceof SMSData) {
			SMSData smsData = (SMSData) data;
			return smsData.destinatarioMensajeId.toString();
		} else if (data instanceof ReceptorSMSData) {
			ReceptorSMSData smsData = (ReceptorSMSData) data;
			return smsData.destinatarioMensajeId.toString();
		} else if (data instanceof NotificacionPushData) {
			NotificacionPushData smsData = (NotificacionPushData) data;
			return smsData.destinatarioMensajeId.toString();
		} else if (data instanceof MailData) {
			MailData smsData = (MailData) data;
			Recipients recipients = smsData.Recipients;
			for (DestinatarioDMensaje ddm : recipients.To) {
				aux.add(ddm.idDestinatarioMensaje.toString());
			}
			for (DestinatarioDMensaje ddm : recipients.Cc) {
				aux.add(ddm.idDestinatarioMensaje.toString());
			}
			for (DestinatarioDMensaje ddm : recipients.Bcc) {
				aux.add(ddm.idDestinatarioMensaje.toString());
			}
			return aux.toString().replace("[", "").replace("]", "");

		}

		return res;
	}

	// Procedimiento que obtiene los Adjuntos relacionados con el MAIL

	public ArrayList<Adjunto> GetAttachment(Integer msID, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<Adjunto> adjuntos = new ArrayList<Adjunto>();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT NOMBRE, CONTENIDO FROM TBL_MENSAJESADJUNTOS MA INNER JOIN TBL_ADJUNTOS A "
							+ " ON MA.ADJUNTOID = A.ADJUNTOID WHERE MA.MENSAJEID = "
							+ msID);

			while (rs.next()) {
				Adjunto ads = new Adjunto();
				ads.setNombre(rs.getString("NOMBRE"));
				ads.setContenido(rs.getBlob("CONTENIDO"));
				adjuntos.add(ads);
			}
			log.info("JDBCAccess.GetAttachment : numero adjuntos  : " + adjuntos.size());
		} catch (SQLException e) {
			log.error("JDBCAccess.GetAttachment : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return adjuntos;
	}

	// Procedimiento que obtiene las imagenes embebidas del EMAIL
	public ArrayList<Adjunto> GetImage(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<Adjunto> adjuntos = new ArrayList<Adjunto>();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT NOMBRE, CONTENIDO FROM TBL_MENSAJESADJUNTOS MA INNER JOIN TBL_ADJUNTOS A "
							+ " ON MA.ADJUNTOID = A.ADJUNTOID WHERE MA.MENSAJEID = "
							+ msID + "" + "AND IMAGEN = 1");

			while (rs.next()) {
				Adjunto ads = new Adjunto();
				ads.setNombre(rs.getString("NOMBRE"));
				ads.setContenido(rs.getBlob("CONTENIDO"));
				adjuntos.add(ads);
			}
			log.info("JDBCAccess.GetImage : numero adjuntos  : "
					+ adjuntos.size());

		} catch (SQLException e) {
			log.error("JDBCAccess.GetImage : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return adjuntos;
	}

	// Procedimiento que obtiene el numero de intentos de envio por Mail
	public int GetRetriesForService(Integer msID, Connection conn)
			throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		int nmaxenvios = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT NMAXENVIOS FROM TBL_SERVICIOS WHERE SERVICIOID = (SELECT SERVICIOID"
							+ " FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID"
							+ " WHERE MENSAJEID=" + msID + ")");

			rs.next();
			nmaxenvios = rs.getInt(1);
			return nmaxenvios;
		} catch (SQLException e) {
			log.error("JDBCAccess.GetRetriesForService : " + e.getErrorCode()+ " - " + e.getMessage(),e);
			return nmaxenvios;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
	}

	// Procedimiento que obtiene los Servidores disponibles para mandar el MAIL
	public DatosServicio GetDataFromServices(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		DatosServicio datos = new DatosServicio();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT NMAXENVIOS,CUENTAENVIO,NOMBRECUENTAENVIO, HEADERSMS, MULTIORGANISMO FROM TBL_SERVICIOS WHERE SERVICIOID = (SELECT SERVICIOID"
							+ " FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID"
							+ " WHERE M.MENSAJEID=" + msID + ")");

			rs.next();

			datos.setNMaxEnvios(rs.getInt("NMAXENVIOS"));
			datos.setFromMail(rs.getString("CUENTAENVIO"));
			datos.setFromMailNombre(rs.getString("NOMBRECUENTAENVIO"));
			datos.setHeaderSMS(rs.getString("HEADERSMS"));
			datos.setMultiOrganismo(rs.getInt("MULTIORGANISMO"));
		} catch (SQLException e) {
			log.error("JDBCAccess.GetDataFromServices : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return datos;
	}

	// Procedimiento que obtiene los Servidores disponibles para mandar el MAIL
	public DatosServicio GetDataFromServicesSMS(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		DatosServicio datos = new DatosServicio();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT SS.NUMINTENTOS AS NMAXENVIOS,CUENTAENVIO,NOMBRECUENTAENVIO, SS.HEADERSMS, MULTIORGANISMO FROM TBL_SERVICIOS S, TBL_SERVIDORES_SERVICIOS SS WHERE S.SERVICIOID = (SELECT SERVICIOID  FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID  WHERE M.MENSAJEID="
							+ msID + ") AND S.SERVICIOID = SS.SERVICIOID");

			rs.next();

			datos.setNMaxEnvios(rs.getInt("NMAXENVIOS"));
			datos.setFromMail(rs.getString("CUENTAENVIO"));
			datos.setFromMailNombre(rs.getString("NOMBRECUENTAENVIO"));
			datos.setHeaderSMS(rs.getString("HEADERSMS"));
			datos.setMultiOrganismo(rs.getInt("MULTIORGANISMO"));
		} catch (SQLException e) {
			log.error("JDBCAccess.GetDataFromServices : " + e.getErrorCode()  + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return datos;
	}

	// Procedimiento que obtiene los Servidores disponibles para mandar el SMS
	// Multiorganismo
	public DatosServicio GetDataFromServicesMultiorganismo(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		DatosServicio datos = new DatosServicio();
		try {

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT NMAXENVIOS,CUENTAENVIO,NOMBRECUENTAENVIO, HEADERSMS FROM TBL_SERVICIOS WHERE SERVICIOID = (SELECT SERVICIOID"
							+ " FROM TBL_MENSAJES M INNER JOIN TBL_LOTESENVIOS L ON M.LOTEENVIOID = L.LOTEENVIOID"
							+ " WHERE M.MENSAJEID=" + msID + ")");

			rs.next();

			datos.setNMaxEnvios(rs.getInt("NMAXENVIOS"));
			datos.setFromMail(rs.getString("CUENTAENVIO"));
			datos.setFromMailNombre(rs.getString("NOMBRECUENTAENVIO"));
			datos.setHeaderSMS(rs.getString("HEADERSMS"));
		} catch (SQLException e) {
			log.error("JDBCAccess.GetDataFromServicesMultiorganismo : "	+ e.getErrorCode()  + " - " + e.getMessage(),e);

		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return datos;
	}

	// Procedimiento que obtiene los Detalles del Mensaje (ASUNTO, CUERPO)
	public ResultSet GetDetails(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT CABECERA,CUERPO,TIPOCUERPO,TIPOCODIFICACION,TELEFONO FROM TBL_MENSAJES"
							+ " WHERE MENSAJEID = " + msID);

			cachedRowSet.populate(rs);

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return cachedRowSet;
	}

	// Procedimiento que obtiene los Detalles del Mensaje (ASUNTO, CUERPO)
	public ResultSet GetDetailsMultidestinatario(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT m.CABECERA,m.CUERPO,m.TIPOCUERPO,m.TIPOCODIFICACION,dm.DESTINATARIO, dm.DESTINATARIOSMENSAJES"
							+ " FROM TBL_MENSAJES m "
							+ "inner join TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID "
							+ " WHERE m.MENSAJEID = " + msID);

			cachedRowSet.populate(rs);
		} catch (SQLException e) {
			log.error("JDBCAccess.GetDetails : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	// Procedimiento que obtiene los Detalles del Mensaje (ASUNTO, CUERPO)
	public ResultSet GetDetailsReceptorSMS(Integer msID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT M.LOTEENVIOID, M.CUERPO, M.TELEFONO, S.HEADERSMS, AP.USUARIO, AP.PASSWORD FROM TBL_MENSAJES M"
							+ " INNER JOIN TBL_LOTESENVIOS LE ON M.LOTEENVIOID = LE.LOTEENVIOID"
							+ " INNER JOIN TBL_SERVICIOS S ON LE.SERVICIOID = S.SERVICIOID"
							+ " INNER JOIN TBL_APLICACIONES AP ON S.APLICACIONID = AP.APLICACIONID"
							+ " WHERE M.MENSAJEID = " + msID);

			cachedRowSet.populate(rs);

		} catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsReceptorSMS : " + e.getErrorCode() 	+ " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	public ResultSet GetDetailsReceptorSMSMultidestinatario(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT M.LOTEENVIOID, M.CUERPO, dm.DESTINATARIO, dm.DESTINATARIOSMENSAJES, S.HEADERSMS, AP.USUARIO, AP.PASSWORD FROM TBL_MENSAJES M"
							+ " INNER JOIN TBL_LOTESENVIOS LE ON M.LOTEENVIOID = LE.LOTEENVIOID"
							+ " INNER JOIN TBL_SERVICIOS S ON LE.SERVICIOID = S.SERVICIOID"
							+ " INNER JOIN TBL_APLICACIONES AP ON S.APLICACIONID = AP.APLICACIONID"
							+ " INNER JOIN TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = m.MENSAJEID"
							+ " WHERE M.MENSAJEID = " + msID);

			cachedRowSet.populate(rs);

		}catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsReceptorSMSMultidestinatario : " + e.getErrorCode()
					+ " - " + e.getMessage());
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	// Procedimiento que obtiene los Detalles de la notificacion Push (ASUNTO,
	// CUERPO)
	public ResultSet GetDetailsServidorPush(Integer MessageID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT M.CABECERA, M.CUERPO, M.ICONO, M.SONIDO, M.NOMBREUSUARIO, S.GCMPROJECTKEY, S.APNSRUTACERTIFICADO, S.APNSPASSWORDCERTIFICADO, S.BADGE FROM TBL_MENSAJES M "
							+ " INNER JOIN TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"
							+ " INNER JOIN TBL_SERVIDORES_SERVICIOS SS ON SS.SERVICIOID = LE.SERVICIOID"
							+ " INNER JOIN TBL_SERVICIOS S ON S.SERVICIOID = LE.SERVICIOID"
							+ " WHERE MENSAJEID = " + MessageID);

			cachedRowSet.populate(rs);

		}catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsServidorPush : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	public ResultSet GetDetailsServidorPushMultidestinatario(Integer MessageID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT M.CABECERA, M.CUERPO, M.ICONO, M.SONIDO, up.NOMBREUSUARIO, dm.DESTINATARIO, up.PLATAFORMAID, dm.DESTINATARIOSMENSAJES, S.GCMPROJECTKEY, S.APNSRUTACERTIFICADO, S.APNSPASSWORDCERTIFICADO, S.BADGE FROM TBL_MENSAJES M "
							+ " INNER JOIN TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID"
							+ " INNER JOIN TBL_SERVICIOS S ON S.SERVICIOID = LE.SERVICIOID"
							+ " INNER JOIN TBL_DESTINATARIOS_MENSAJES dm on dm.MENSAJEID = M.MENSAJEID"
							+ " INNER JOIN TBL_USUARIOS_PUSH up on up.USUARIOID = dm.DESTINATARIO"
							+ " WHERE M.MENSAJEID = "
							+ MessageID +" AND (up.ELIMINADO != 'S' OR up.ELIMINADO IS NULL)"
							+ " AND (ESTADO NOT LIKE 'ENVIADO' AND ESTADO NOT LIKE 'ANULADO')");

			cachedRowSet.populate(rs);

		} catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsServidorPushMultidestinatario : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		log.info("CachedRowSet size: " + cachedRowSet.size());
		
		return cachedRowSet;
	}

	// Procedimiento que obtiene todos los Tokens de un usuario
	public ResultSet GetDetailsTokensUsuario(Integer MessageID, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		Integer usuarioId = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT USUARIOID from TBL_MENSAJES WHERE MENSAJEID = "
							+ MessageID);

			if (rs.next()) {
				usuarioId = rs.getInt(1);
			}

			rs = stmt
					.executeQuery("SELECT DISTINCT UP.TOKENUSUARIO FROM TBL_USUARIOS_PUSH UP "
							+ " INNER JOIN TBL_LOTESENVIOS LE ON LE.SERVICIOID = UP.SERVICIOID"
							+ " INNER JOIN TBL_MENSAJES M ON M.LOTEENVIOID = LE.LOTEENVIOID"
							+ " WHERE MENSAJEID = "
							+ MessageID
							+ " AND UP.USUARIOID = " + usuarioId);

			cachedRowSet.populate(rs);

		}  catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsTokensUsuario : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	// Procedimiento que obtiene todos los Tokens de un usuario
	public ResultSet GetDetailsTokensUsuarioMultidestinatario(Long usuarioId,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT DISTINCT UP.TOKENUSUARIO FROM TBL_USUARIOS_PUSH UP "
							+ "WHERE UP.USUARIOID = " + usuarioId);

			cachedRowSet.populate(rs);

		} catch (SQLException e) {
			log.error("JDBCAccess.GetDetailsTokensUsuarioMultidestinatario : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return cachedRowSet;
	}

	public Integer getPlataformaUsuario(Integer MessageID, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		Integer plataformaID = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt
					.executeQuery("SELECT PLATAFORMAID from TBL_USUARIOS_PUSH pu INNER JOIN TBL_MENSAJES m on m.USUARIOID = pu.USUARIOID  WHERE m.MENSAJEID = "
							+ MessageID);

			if (rs.next()) {
				plataformaID = rs.getInt(1);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.getPlataformaUsuario : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return plataformaID;
	}

	// Procedimiento que guarda en un log en la BBDD con los errores que han
	// sucedido en el envio
	public void SetLogError(String source, int number, String message,
			Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String s = "";
			s += "INSERT INTO TBL_ERRORMENSAJE_LOG (ERRORMENSAJELOGID, FECHA,OPERACION, CODIGOERROR,DESCRIPCIONERROR)";
			s += "VALUES (errormensajelogid_sec.NEXTVAL,SYSDATE,'" + source
					+ "'," + number + ",'" + message + "')";
			rs = stmt.executeQuery(s);
//			stmt.close();

		} catch (SQLException e) {
			log.error("JDBCAccess.SetLogError : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
	}

	// Procedimiento para obtener el tamano del ResultSet
	public int getSizeRS(ResultSet rs) throws Exception {
		int output = 0;
		rs.beforeFirst();
		while (rs.next()) {
			output++;
		}
		rs.beforeFirst();
		return output;
	}

	public String GetTypeMessage(int messageId, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		String Estado = "";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(" SELECT TIPOMENSAJE FROM TBL_MENSAJES"
					+ " WHERE MENSAJEID = " + messageId);

			rs.next();
			Estado = rs.getString(1);
		} catch (SQLException e) {
			log.error("JDBCAccess.GetTypeMessage : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return Estado;
	}

	public ResultSet RefreshStatusSMS(Connection conn, Integer idNodo,
			Integer difTiempo) throws SQLException {
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement stmt = null;
		Statement stmt2 = null;
		OracleCachedRowSet cachedRowSet = new OracleCachedRowSet();
		boolean failed = false;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT * FROM VIEW_REFRESH_STATUS WHERE NODO = "
							+ idNodo
							+ "AND ULTIMOENVIO + ( "
							+ difTiempo
							+ " / 86400) < SYSDATE");

			cachedRowSet.populate(rs);
		} catch (SQLException e) {
			log.error("JDBCAccess.RefreshStatusSMS : " + e.getErrorCode() + " - " + e.getMessage(),e);
			failed = true;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		if (!failed) {
			try {
				stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs2 = stmt2
						.executeQuery(" SELECT * FROM VIEW_REFRESH_STATUS_DEST WHERE NODO = "
								+ idNodo
								+ "AND ULTIMOENVIO + ( "
								+ difTiempo
								+ " / 86400) < SYSDATE");
				cachedRowSet.populate(rs2);
			} catch (SQLException e) {
				log.error("JDBCAccess.RefreshStatusSMS : " + e.getErrorCode() + " - " + e.getMessage(),e);
			} finally {
				this.closeSQLObject(rs2);
				this.closeSQLObject(stmt2);
			}
		}
		return cachedRowSet;
	}

	public int deleteNodoFromSMS(Connection conn, Integer idNodo)
			throws SQLException {
		int rowsUpdate = 0;
		Statement stmt = null;
		int res = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rowsUpdate = stmt
					.executeUpdate(" UPDATE TBL_MENSAJES SET NODO = NULL WHERE NODO = "
							+ idNodo);
			
			res = res + rowsUpdate;
			stmt.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			rowsUpdate = stmt
					.executeUpdate(" UPDATE TBL_DESTINATARIOS_MENSAJES SET NODO = NULL WHERE NODO = "
							+ idNodo);
			res = res + rowsUpdate;		
		} catch (SQLException e) {
			log.error("JDBCAccess.deleteNodoFromSMS : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(conn);
		}

		return res;
	}

	public int assignNodoFromSMS(Connection conn, Integer idNodo,
			Integer numTotalNodos, Integer difTiempo, Integer maxMensajesBloque)
			throws SQLException, Exception {
		int rowsUpdate = 0;
		String listaMensajeID = new String();
		String listaDestinatariosMensajesID = new String();
		ResultSet rs = null;
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			stmt.setQueryTimeout(180);
			//recuparamos nodos mensajes sin lotes
			String s = "SELECT MENSAJEID FROM TBL_MENSAJES WHERE MENSAJEID IN (SELECT MENSAJEID FROM  (SELECT ROWNUM AS NUMERO, MENSAJEID FROM TBL_MENSAJES WHERE TIPOMENSAJE = 'SMS' AND (METODOCONSULTA = 1 OR METODOCONSULTA IS NULL) AND ESTADOACTUAL = 'PENDIENTE DE OPERADORA' AND NODO IS NULL AND ULTIMOENVIO + ( "
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ " ORDER BY ULTIMOENVIO ASC)"
					+ " WHERE NUMERO BETWEEN"
					+ " (("
					+ idNodo
					+ "-1)*TRUNC((SELECT COUNT(*) FROM TBL_MENSAJES WHERE TIPOMENSAJE = 'SMS' AND (METODOCONSULTA = 1 OR METODOCONSULTA IS NULL) AND ESTADOACTUAL = 'PENDIENTE DE OPERADORA' AND NODO IS NULL AND ULTIMOENVIO + ( "
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ ") / "
					+ numTotalNodos
					+ ") + 1)"
					+ " AND (GREATEST("
					+ idNodo
					+ "*TRUNC((SELECT COUNT(*) FROM TBL_MENSAJES WHERE TIPOMENSAJE = 'SMS' AND (METODOCONSULTA = 1 OR METODOCONSULTA IS NULL) AND ESTADOACTUAL = 'PENDIENTE DE OPERADORA' AND UIM != '00' AND NODO IS NULL AND ULTIMOENVIO + ( "
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ ") / "
					+ numTotalNodos
					+ "), 1)))"
					+ " FOR UPDATE";
			rs = stmt.executeQuery(s);

			while (rs.next()) {
				if (!rs.isLast()) {
					listaMensajeID = listaMensajeID + rs.getString("MensajeID")
							+ " ,";
				} else {
					listaMensajeID = listaMensajeID + rs.getString("MensajeID");
				}
			}
			
			
			stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			stmt2.setQueryTimeout(180);
			s = "SELECT DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES WHERE DESTINATARIOSMENSAJES IN (SELECT DESTINATARIOSMENSAJES FROM (SELECT ROWNUM AS NUMERO, dm.DESTINATARIOSMENSAJES FROM TBL_DESTINATARIOS_MENSAJES dm INNER JOIN TBL_MENSAJES m on m.MENSAJEID = dm.MENSAJEID WHERE m.TIPOMENSAJE = 'SMS' AND (m.METODOCONSULTA = 1 OR m.METODOCONSULTA IS NULL) AND dm.ESTADO = 'PENDIENTE DE OPERADORA' AND dm.NODO IS NULL AND dm.ULTIMOENVIO + ( "
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ " ORDER BY dm.ULTIMOENVIO ASC)"
					+ " WHERE NUMERO BETWEEN "
					+ " (("
					+ idNodo
					+ "-1)*TRUNC((SELECT COUNT(*) FROM TBL_DESTINATARIOS_MENSAJES dm INNER JOIN TBL_MENSAJES m on m.MENSAJEID = dm.MENSAJEID WHERE m.TIPOMENSAJE = 'SMS' AND (m.METODOCONSULTA = 1 OR m.METODOCONSULTA IS NULL) AND dm.ESTADO = 'PENDIENTE DE OPERADORA' AND dm.NODO IS NULL AND dm.ULTIMOENVIO + ("
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ ") / "
					+ numTotalNodos
					+ ") + 1)"
					+ " AND (GREATEST("
					+ idNodo
					+ "*TRUNC((SELECT COUNT(*) FROM TBL_DESTINATARIOS_MENSAJES dm INNER JOIN TBL_MENSAJES  m on m.MENSAJEID = dm.MENSAJEID WHERE m.TIPOMENSAJE = 'SMS' AND (m.METODOCONSULTA = 1 OR m.METODOCONSULTA IS NULL) AND dm.ESTADO = 'PENDIENTE DE OPERADORA' AND dm.UIM != '00' AND dm.NODO IS NULL AND dm.ULTIMOENVIO + ("
					+ difTiempo
					+ " / 86400) < SYSDATE AND ROWNUM < "
					+ maxMensajesBloque
					+ ") / "
					+ numTotalNodos
					+ "), 1)))"
					+ " FOR UPDATE";
			
			rs2 = stmt2.executeQuery(s);

			while (rs2.next()) {
				if (!rs2.isLast()) {
					listaDestinatariosMensajesID = listaDestinatariosMensajesID + rs2.getString("DESTINATARIOSMENSAJES")
							+ " ,";
				} else {
					listaDestinatariosMensajesID = listaDestinatariosMensajesID + rs2.getString("DESTINATARIOSMENSAJES");
				}
			}
			
			// TODO: hay que asignar a los destrinatariosmensajes
			if (null != listaMensajeID && !listaMensajeID.isEmpty()) {
				log.info("Mensajes a asignar: " + listaMensajeID);
				rowsUpdate = stmt
						.executeUpdate(" UPDATE TBL_MENSAJES SET NODO = "
								+ idNodo + " WHERE MENSAJEID IN ( "
								+ listaMensajeID + ")");
			}
			if (null != listaDestinatariosMensajesID && !listaDestinatariosMensajesID.isEmpty()) {
				log.info("Mensajes multidestinatario a asignar: " + listaDestinatariosMensajesID);
				rowsUpdate = rowsUpdate + stmt2
						.executeUpdate(" UPDATE TBL_DESTINATARIOS_MENSAJES SET NODO = "
								+ idNodo + " WHERE DESTINATARIOSMENSAJES IN ( "
								+ listaDestinatariosMensajesID + ")");
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.assignNodoFromSMS : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs2);
			this.closeSQLObject(stmt2);
		}

		return rowsUpdate;
	}

	public boolean evaluateStatusServer(Connection conn, Integer idNodo)
			throws SQLException {
		boolean stopService = true;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery(" SELECT * FROM TBL_CONFIG_REFRESH WHERE NODO = "
							+ idNodo + " AND ACTIVO = '1'");

			if (rs.next()) {
				stopService = false;
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.evaluateStatusServer : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return stopService;
	}

	// INI - SIM-106 - gbermude - 04/02/2016 - Modificación SendMail y
	// RefreshStatus para módulo de interoperabilidad y sacar lógica BBDD a
	// código Viejos metodos que llaman a PLs
	// Añade historico (metodo originalmente situado en el proyecto SMS)
	public void setHistorico(Historico hist, Connection conn) throws Exception {

		Statement stmt = null;
		ResultSet rs = null;
		String s;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,subestadoid,servidorid,descripcion) ";
			s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate,"
					+ hist.getMensajeid() + "," + hist.getEstadoid() + ","
					+ hist.getSubestadoid() + "," + hist.getServidorid() + ",'"
					+ hist.getDescripcion() + "')";
			System.out.println(s);
			rs = stmt.executeQuery(s);

		} catch (SQLException e) {
			log.error("JDBCAccess.setHistorico : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
	}
	
	public void setHistoricoMultidestinatario(Historico hist, Connection conn) throws Exception {

		Statement stmt = null;
		ResultSet rs = null;
		String s;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,subestadoid,servidorid,descripcion,destinatariosmensajes) ";
			s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate,"
					+ hist.getMensajeid() + "," + hist.getEstadoid() + ","
					+ hist.getSubestadoid() + "," + hist.getServidorid() + ",'"
					+ hist.getDescripcion() + "',"+hist.getDestinatariosMensajes()+")";
			log.info(s);
			rs = stmt.executeQuery(s);

		} catch (SQLException e) {
			log.error("JDBCAccess.setHistoricoMultidestinatario : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
	}

	public Proveedor findProveedor(Integer proveedorid, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		Statement stmt = null;
		Proveedor proveedor = new Proveedor();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT nombre,descripcion,pordefecto, fechacreacion, creadopor, fechamodificacion, modificadopor, urlDestino, tipo, externalid, eliminado, METODOCONSULTA FROM TBL_SERVIDORES WHERE SERVIDORID = "
							+ proveedorid);

			rs.next();

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

		} catch (SQLException e) {
			log.error("JDBCAccess.findProveedor : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return proveedor;

	}

	public Map<Integer, Servicio> findServicio(Integer servicioID,
			Connection conn) throws SQLException {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();
		ResultSet rs = null;
		Statement stmt = null;
		// Servicio servicio = new Servicio();

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
				res.put(Integer.parseInt(rs.getString("servidorId")), servicio);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.findServicio : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;

	}

	public Map<Integer, Servicio> findServicioMultiorganismo(
			Integer servicioID, Integer messageId, Connection conn)
			throws SQLException {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();

		ResultSet rs = null;
		Statement stmt = null;

		// Servicio servicio = new Servicio();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT TBL_ORGANISMOS.NOMBRE, TBL_ORGANISMOS.DESCRIPCION, TBL_ORGANISMOS.ACTIVO, "
							+ "TBL_SERVIDORES_ORGANISMOS.SERVIDORID, TBL_SERVIDORES_ORGANISMOS.ORGANISMOID, TBL_SERVIDORES_ORGANISMOS.HEADERSMS, "
							+ "TBL_SERVIDORES_ORGANISMOS.NUMINTENTOS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORUSUARIOSMS, TBL_SERVIDORES_ORGANISMOS.PROVEEDORPASSWORDSMS, "
							+ "TBL_ORGANISMOS.DIR3, TBL_SERVICIOS.SERVICIOID "
							+ "FROM TBL_SERVIDORES_ORGANISMOS, TBL_SERVICIOS, TBL_MENSAJES, TBL_ORGANISMOS, TBL_LOTESENVIOS "
							+ "WHERE (TBL_ORGANISMOS.DIR3 = TBL_MENSAJES.CODORGANISMOPAGADOR) "
							+ "AND (TBL_ORGANISMOS.ORGANISMOID = TBL_SERVIDORES_ORGANISMOS.ORGANISMOID) "
							+ "AND (TBL_LOTESENVIOS.SERVICIOID = TBL_SERVICIOS.SERVICIOID) "
							+ "AND (TBL_MENSAJES.LOTEENVIOID = TBL_LOTESENVIOS.LOTEENVIOID) "
							+ "AND (TBL_MENSAJES.MENSAJEID = "
							+ messageId
							+ ") AND (TBL_LOTESENVIOS.SERVICIOID = "
							+ servicioID + ")");
			// rs =
			// stmt.executeQuery("SELECT ss.organismoId, ss.headersms, s.nombre,s.descripcion, ss.proveedorusuariosms, ss.proveedorpasswordsms"
			// +
			// " FROM TBL_SERVICIOS s inner join TBL_ORGANISMOS_SERVICIO ss on s.SERVICIOID = ss.SERVICIOID"
			// + " WHERE s.SERVICIOID = " + servicioID);

			while (rs.next()) {
				Servicio servicio = new Servicio();
				servicio.setNombre(rs.getString("NOMBRE"));
				servicio.setDescripcion(rs.getString("DESCRIPCION"));
				servicio.setProveedorUsuarioSMS(rs
						.getString("PROVEEDORUSUARIOSMS"));
				servicio.setProveedorPassSMS(rs
						.getString("PROVEEDORPASSWORDSMS"));
				servicio.setHeaderSMS(rs.getString("HEADERSMS"));
				res.put(Integer.parseInt(rs.getString("SERVIDORID")), servicio);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.findServicioMultiorganismo : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;

	}

	public Aplicacion findAplicacion(Integer aplicacionID, Connection conn)
			throws SQLException {

		ResultSet rs = null;
		Statement stmt = null;
		Aplicacion aplicacion = new Aplicacion();

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt
					.executeQuery("SELECT nombre,descripcion FROM TBL_APLICACIONES WHERE APLICACIONID = "
							+ aplicacionID);
			rs.next();

			aplicacion.setNombre(rs.getString("nombre"));
			aplicacion.setDescripcion(rs.getString("descripcion"));

		} catch (SQLException e) {
			log.error("JDBCAccess.findAplicacion : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return aplicacion;

	}

	public Map<Integer, Servicio> findServicioByMessageId(Integer messageID,
			Connection conn) throws SQLException {
		Map<Integer, Servicio> res = new TreeMap<Integer, Servicio>();

		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String s = "select serv.SERVICIOID, serv.MULTIORGANISMO from  TBL_SERVICIOS SERV, TBL_LOTESENVIOS LOT, TBL_MENSAJES MEN WHERE MEN.mensajeID = "
					+ messageID
					+ " and LOT.loteenvioid = MEN.loteenvioid and serv.SERVICIOID = LOT.servicioid";
			System.out.println(s);
			rs = stmt.executeQuery(s);
			rs.next();

			// Integer servicioid = rs.getInt(1);
			Integer servicioid = rs.getInt("SERVICIOID");
			Integer multiorganismo = rs.getInt("MULTIORGANISMO");
	
			if (1 == multiorganismo) {
				res = findServicioMultiorganismo(servicioid, messageID, conn);
				if (res.size() <= 0) {
					res = findServicio(servicioid, conn);
				}
			} else {
				res = findServicio(servicioid, conn);
			}
		} catch (SQLException e) {
			log.error("JDBCAccess.findServicioByMessageId : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return res;
	}

	public Aplicacion findAplicacionByMessageId(Integer messageID,
			Connection conn) throws SQLException {

		ResultSet rs = null;
		Statement stmt = null;
		Integer aplicacionid = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String s = "select APP.APLICACIONID from  TBL_APLICACIONES APP, TBL_SERVICIOS SERV, TBL_LOTESENVIOS LOT, TBL_MENSAJES MEN WHERE MEN.mensajeID = "
					+ messageID
					+ " and LOT.loteenvioid = MEN.loteenvioid and serv.SERVICIOID = LOT.servicioid AND APP.APLICACIONID = SERV.APLICACIONID";
			System.out.println(s);
			rs = stmt.executeQuery(s);
			rs.next();

			aplicacionid = rs.getInt(1);

		} catch (SQLException e) {
			log.error("JDBCAccess.findAplicacionByMessageId : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}
		return findAplicacion(aplicacionid, conn);
	}

	// FIN - SIM-106 - gbermude - 04/02/2016 - Modificación SendMail y
	// RefreshStatus para módulo de interoperabilidad y sacar lógica BBDD a
	// código Viejos metodos que llaman a PLs

	// INI - SIM-106 - gbermude - 03/02/2016 - Modificación SendMail y
	// RefreshStatus para módulo de interoperabilidad y sacar lógica BBDD a
	// código Viejos metodos que llaman a PLs

	public ArrayList<ParametrosServidor> __old_GetServidores(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		CallableStatement stmt = null;
		ArrayList<ParametrosServidor> pss = new ArrayList<ParametrosServidor>();

		try {
			stmt = conn.prepareCall("{call OBTENERSERVIDORES (?,?)  }",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setQueryTimeout(660);
			stmt.setInt(1, msID);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(2);

			while (rs.next()) {
				ParametrosServidor ps = new ParametrosServidor();
				ps.setServidor(rs.getInt("SERVIDORID"));
				ps.setIP(rs.getString("IP"));
				ps.setUsuario(rs.getString("USUARIO"));
				ps.setContrasena(rs.getString("CONTRASEÑA"));
				ps.setConexion(rs.getString("CONEXION"));
				ps.setPuerto(rs.getString("PUERTO"));
				ps.setAutentificacion(rs.getString("AUTENTIFICACION"));
				ps.setTimeOut(rs.getString("TIEMPOESPERA"));
				pss.add(ps);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.__old_GetServidores : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return pss;
	}

	public ArrayList<ParametrosProveedor> __old_GetProveedores(Integer msID,
			Connection conn) throws SQLException {

		ResultSet rs = null;
		CallableStatement stmt = null;
		ArrayList<ParametrosProveedor> pss = new ArrayList<ParametrosProveedor>();

		try {
			stmt = conn.prepareCall("{call OBTENERPROVEEDORES (?,?)  }",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setQueryTimeout(660);
			stmt.setInt(1, msID);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(2);

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
			log.error("JDBCAccess.__old_GetProveedores : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return pss;
	}

	public ArrayList<ParametrosReceptor> __old_GetReceptores(Integer msID,
			Connection conn) throws SQLException {
		ResultSet rs = null;
		CallableStatement stmt = null;
		ArrayList<ParametrosReceptor> pss = new ArrayList<ParametrosReceptor>();

		try {
			stmt = conn.prepareCall("{call OBTENERRECEPTORES (?,?)  }",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setQueryTimeout(660);
			stmt.setInt(1, msID);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(2);

			while (rs.next()) {
				ParametrosReceptor ps = new ParametrosReceptor();

				ps.setReceptorId(rs.getInt("RECEPTORID"));
				ps.setUrl(rs.getString("URL"));
				ps.setId(rs.getString("ID"));
				ps.setTexto(rs.getString("TEXTO"));
				ps.setUIM(rs.getString("UIM"));
				ps.setTelefono(rs.getString("TELEFONO"));
				pss.add(ps);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.__old_GetReceptores : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);

			this.closeSQLObject(stmt);
		}

		return pss;
	}

	public ArrayList<ParametrosServidorPush> __old_GetServidoresPush(
			Integer msID, Connection conn) throws SQLException {
		ResultSet rs = null;
		CallableStatement stmt = null;
		ArrayList<ParametrosServidorPush> pss = new ArrayList<ParametrosServidorPush>();

		try {
			stmt = conn.prepareCall("{call OBTENERSERVIDORESPUSH (?,?)  }",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setQueryTimeout(660);
			stmt.setInt(1, msID);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(2);

			while (rs.next()) {
				ParametrosServidorPush ps = new ParametrosServidorPush();

				ps.setServidorPushId(rs.getInt("SERVIDORPUSHID"));
				ps.setPlataformaId(rs.getInt("PLATAFORMAID"));
				ps.setUrl(rs.getString("URL"));
				ps.setUrlFeedback(rs.getString("URLFEEDBACK"));
				ps.setPuertoUrl(rs.getInt("PUERTOURL"));
				ps.setPuertoUrlFeedback(rs.getInt("PUERTOURLFEEDBACK"));
				ps.setServicioId(rs.getInt("SERVICIOID"));
				ps.setPrioridad(rs.getInt("PRIORIDAD"));

				pss.add(ps);
			}

		} catch (SQLException e) {
			log.error("JDBCAccess.__old_GetServidoresPush : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}

		return pss;
	}

	// Procedimiento que cambia el estado del MAIL
	public void __old_SetMessageStatus(int msID, int Status, int ServidorId,
			String Description, Connection conn) throws Exception {

		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall("{call SET_MESSAGE_STATUS (?,?,?,?) }");

			stmt.setInt(1, msID);
			stmt.setInt(2, Status);
			stmt.setInt(3, ServidorId);
			stmt.setString(4, Description);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			log.error("JDBCAccess.__old_SetMessageStatus : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(stmt);
		}
	}

	// Procedimiento que guarda en un log en la BBDD con los errores que han
	// sucedido en el envio
	public void __old_SetLogError(String Source, int Number, String Message,
			Connection conn) throws Exception {
		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall("{call LOG_ERROR_MENSAJES (?,?,?) }");

			stmt.setString(1, Source);
			stmt.setInt(2, Number);
			stmt.setString(3, Message);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			log.error("JDBCAccess.__old_SetLogError : " + e.getErrorCode() + " - " + e.getMessage(),e);
		} finally {
			this.closeSQLObject(stmt);
		}
	}


	// FIN - SIM-106 - gbermudez - 03/02/2016 - ModificaciOn SendMail y
	// RefreshStatus para módulo de interoperabilidad y sacar lógica BBDD a
	// código

	private boolean closeSQLObject(Object sqlObject) {
		if (sqlObject != null) {
			if (sqlObject instanceof CallableStatement) {
				CallableStatement callableStmt = (CallableStatement) sqlObject;
				try {
					callableStmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof PreparedStatement) {
				PreparedStatement pstmt = (PreparedStatement) sqlObject;
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof ResultSet) {
				ResultSet rs = (ResultSet) sqlObject;
				try {
					rs.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof Connection) {
				Connection con = (Connection) sqlObject;
				try {
					con.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return false;
		} else {
			return false;
		}
	}

	

	
}