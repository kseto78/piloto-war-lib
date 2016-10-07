package es.minhap.plataformamensajeria.iop.jdbc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
import oracle.sql.BLOB;
import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;

/**
 * 
 * @author i-nercya
 * 
 */
public class EnvioMensajesDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(EnvioMensajesDAO.class);
	private static final String PROCEDIMIENTO_CREAR_LOTE = "{call PKG_ENVIOS_GENERAL.CREARLOTEMULT(?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ELIMINAR_LOTE = "{call PKG_ENVIOS_GENERAL.ELIMINARLOTE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE = "{call PKG_ENVIOS_EMAIL_LOTES.CREARMENSAJEINDICADORES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	private static final String PROCEDIMIENTO_ELIMINAR_MENSAJE = "{call PKG_ENVIOS_EMAIL.ELIMINARMENSAJE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_NUEVO_ANEXO = "{call PKG_ENVIOS_EMAIL.NUEVOANEXO (?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ASOCIAR_ANEXO = "{call PKG_ENVIOS_EMAIL.ASOCIARANEXO (?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_SMS = "{call PKG_ENVIOS_SMS.CREARMENSAJESMS_LOTES(?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ELIMINAR_MENSAJE_SMS = "{call PKG_ENVIOS_SMS.ELIMINARMENSAJESMS(?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_COMPROBAR_ENVIO = "{call PKG_ENVIOS_EMAIL.COMPROBAREMAIL(?,?,?)}";
	// Actualizaciones bug numero caracteres
//	private static final String PROCEDIMIENTO_CREAR_MENSAJE_SINCUERPO = "{call PKG_ENVIOS_EMAIL.CREARMENSAJESINCUERPO(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String GET_DESTINATARIOS_MENSAJES_EMAIL = "select DESTINATARIOID from TBL_DESTINATARIOS where MENSAJEID = ? ";

	private static String STATUSCODE_KO = "4200";

	private static String STATUSTEXT_KO = "KO";

	private static String STATUSERROR;

	private static String ESTADOINICIAL = "PENDIENTE DE ENVIO";

	// Nuevos procedimientos para a�adir imagenes y asociarlas a un mensaje.
	/**
	 * PROCEDURE NUEVAIMAGEN (paramEmailId in number, paramCID in varchar2,
	 * paramAnexo in blob, paramUsuario in varchar2, paramPassword in varchar2,
	 * outConfirmacion out number);
	 * 
	 * PROCEDURE ASOCIARIMAGEN (paramEmailId in number, paramAdjuntoId in
	 * number, paramUsuario in varchar2, paramPassword in varchar2,
	 * outConfirmacion out number);
	 */

	private static final String PROCEDIMIENTO_NUEVA_IMAGEN = "{call PKG_ENVIOS_EMAIL.NUEVAIMAGEN(?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ASOCIAR_IMAGEN = "{call PKG_ENVIOS_EMAIL.ASOCIARIMAGEN(?,?,?,?,?,?)}";
	private static final String GET_MENSAJE = "select CODIGOEXTERNO from TBL_MENSAJES where MENSAJEID = ? ";

	/**
	 * 
	 * @param servicioId
	 * @param nombreLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearLote(String servicioId, String nombreLote,
			String usuario, String password, int multidestinatario) {
		BigDecimal idLote = null;
		CallableStatement call = null;

		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_LOTE);
			if (servicioId != null) {
				call.setInt(1, new Integer(servicioId));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}

			call.setString(2, nombreLote);
			call.setString(3, usuario);
			call.setString(4, password);
			call.setInt(5, multidestinatario);
			call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idLote =  (BigDecimal) call.getObject(6);

		}catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.crearLote] - Error en bbdd", e);
			
			idLote=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return idLote.intValue();
	}

	/**
	 * 
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public BigDecimal eliminarLote(String idLote, String usuario,
			String password) {
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			 call = this.getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_LOTE);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);

		}catch(SQLException e){
			logger.debug("[EnvioMensajesDAO.eliminarLote] - Error en bbdd", e);
			confirmacion = new BigDecimal(-10);
		} finally {
			this.closeSQLObject(call);
		}
		return confirmacion;
	}

	/**
	 * 
	 * @param idLote
	 * @param asunto
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param codExterno
	 * @param cc
	 * @param bcc
	 * @param to
	 * @param usuario
	 * @param password
	 * @return paramLoteId in number, paramCabecera in varchar, paramCuerpo in
	 *         clob, paramCodExterno in varchar, paramCC in varchar2, paramBCC
	 *         in varchar2, paramTO in varchar2, paramTipoCuerpo in varchar2,
	 *         paramTipoCodificacion in varchar2, paramPrioridad in number,
	 *         paramUsuario in varchar2, paramPassword in varchar2, outEmailId
	 *         out number)
	 */
	public Mensaje crearEmail(Integer idLote, String asunto, String cuerpo,
			String docUsuario, String codSIA, String codOrganismo,
			String codExterno, String formatoCuerpo, String codificacion,
			Integer prioridad, String cc, String bcc, String to,
			String usuario, String password, String modo) {
		Mensaje mensaje = new Mensaje();
		BigDecimal idSMS = null;
		CallableStatement call = null;
		Connection conn = null;
		PreparedStatement pst = null;

		ResultSet rs = null;
		String s ="";
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE);
			if (idLote != null) {
				call.setInt(1, new Integer(idLote));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, asunto);
			CLOB tempClob = CLOB.createTemporary(getConn(), true,
					CLOB.DURATION_SESSION);
			tempClob.open(CLOB.MODE_READWRITE);
			Writer tempClobWriter = tempClob.getCharacterOutputStream();
			tempClobWriter.write(cuerpo);
			tempClobWriter.flush();
			tempClobWriter.close();
			tempClob.close();
			call.setClob(3, tempClob);
			// call.setString(3, cuerpo);
			call.setString(4, docUsuario);
			call.setString(5, codSIA);
			call.setString(6, codOrganismo);
			call.setString(7, codExterno);
			call.setString(8, cc);
			call.setString(9, bcc);
			call.setString(10, to);

			call.setString(11, (formatoCuerpo != null) ? formatoCuerpo : "");
			call.setString(12, (codificacion != null) ? codificacion : "");
			call.setInt(13, (prioridad != null) ? prioridad : 0);
			call.setString(14, usuario);
			call.setString(15, password);
			call.setString(16, modo);
			call.registerOutParameter(17, OracleTypes.NUMBER);
			call.executeUpdate();
			idSMS = (BigDecimal) call.getObject(17);
			if (idSMS.intValue() > 0) {
				conn = this.getConn();
				s = GET_MENSAJE;
				pst = conn.prepareStatement(s);
				pst.setInt(1, idSMS.intValue());
				rs = pst.executeQuery();

				if (rs.next()) {
					mensaje.setIdMensaje(idSMS + "");
					if (rs.getString(1) != null) {
						mensaje.setIdExterno(rs.getString(1));
					} else {
						mensaje.setIdExterno("");
					}
				}
			} else {
				if (WSPlataformaErrors.getErrorCrearSMS(idSMS.intValue()) != null) {
					ResponseStatusType status = new ResponseStatusType();
					status.setStatusCode(STATUSCODE_KO);
					status.setStatusCode(STATUSTEXT_KO);
					status.setDetails(WSPlataformaErrors.getErrorCrearSMS(idSMS
							.intValue()));
					mensaje.setErrorMensaje(status);
				}
			}

		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.crearEmail] - Error en bbdd", ec);
			idSMS=new BigDecimal(-10); //error base de datos
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusCode(STATUSTEXT_KO);
			status.setDetails(STATUSERROR);
			mensaje.setErrorMensaje(status);
		
		} catch (IOException e) {
			logger.debug("[EnvioMensajesDAO.crearEmail] - Error en bbdd", e);
			idSMS = new BigDecimal(-10); // error base de datos
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusCode(STATUSTEXT_KO);
			status.setDetails(STATUSERROR);
			mensaje.setErrorMensaje(status);
		}catch (Exception e) {
			idSMS = new BigDecimal(-10); // error base de datos
			logger.debug("[EnvioMensajesDAO.crearEmail] - Error en bbdd", e);
			idSMS=new BigDecimal(-10); //error base de datos
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusCode(STATUSTEXT_KO);
			status.setDetails(STATUSERROR);
			mensaje.setErrorMensaje(status);
		}  
		finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(call);
			this.closeSQLObject(pst);
		}	
		return mensaje;
	}

	/**
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer eliminarEmail(Integer idMensaje, String usuario,
			String password) {
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_MENSAJE);
			if (idMensaje != null) {
				call.setInt(1, new Integer(idMensaje));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			
		}catch(SQLException ec){
			confirmacion=new BigDecimal(-10);
			logger.debug("[EnvioMensajesDAO.eliminarEmail] - Error en bbdd", ec);
			
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}

	/**
	 * 
	 * @param idMensaje
	 * @param CID
	 *            Imagen
	 * @param contenido
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer nuevaImagen(Integer idMensaje, String cid, byte[] contenido,
			String usuario, String password) {
		BigDecimal idAnexo = null;
		CallableStatement call = null;
		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_NUEVA_IMAGEN);

			if (idMensaje != null) {
				call.setInt(1, new Integer(idMensaje));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, cid);
			BLOB blob = BLOB.createTemporary(getConn(), true,
					BLOB.DURATION_SESSION);
			OutputStream blob_os = blob.getBinaryOutputStream();
			if (contenido != null) {
				blob_os.write(contenido);
				blob_os.flush();
				call.setBlob(3, blob);
			} else {
				blob = null;
				call.setBlob(3, blob);
			}
			call.setString(4, usuario);
			call.setString(5, password);
			call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idAnexo =  (BigDecimal) call.getObject(6);

		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.nuevaImagen] - Error en bbdd", ec);
			idAnexo=new BigDecimal(-10); //error base de datos
		} catch (IOException e) {
			logger.debug("[EnvioMensajesDAO.nuevaImagen] - Error en bbdd", e);
			e.printStackTrace();
		} finally {
			this.closeSQLObject(call);
		}
		return idAnexo.intValue();
	}

	/**
	 * 
	 * @param idMensaje
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer asociarImagen(Integer idMensaje, Integer idImagen,
			String usuario, String password) {
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			
			call = this.getConn().prepareCall(PROCEDIMIENTO_ASOCIAR_IMAGEN);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setInt(2, idImagen);
			call.setString(3, usuario);
			call.setString(4, password);
			call.registerOutParameter(5, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(5);
		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.asociaImagen] - Error en bbdd", ec);
			confirmacion= new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}

	/**
	 * 
	 * @param idMensaje
	 * @param nombreAnexo
	 * @param contenido
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer nuevoAnexo(Integer idMensaje, String nombreAnexo,
			byte[] contenido, String usuario, String password) {
		BigDecimal idAnexo = null;
		CallableStatement call = null;

		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_NUEVO_ANEXO);
			if (idMensaje != null) {
				call.setInt(1, new Integer(idMensaje));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, nombreAnexo);
			BLOB blob = BLOB.createTemporary(getConn(), true,
					BLOB.DURATION_SESSION);
			OutputStream blob_os = blob.getBinaryOutputStream();
			if (contenido != null) {
				blob_os.write(contenido);
				blob_os.flush();
				call.setBlob(3, blob);
			} else {
				blob = null;
				call.setBlob(3, blob);
			}
			call.setString(4, usuario);
			call.setString(5, password);
			call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idAnexo =  (BigDecimal) call.getObject(6);

		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.nuevoAnexo] - Error en bbdd", ec);
			idAnexo=new BigDecimal(-10); //error base de datos
		} catch (IOException e) {
			logger.debug("[EnvioMensajesDAO.nuevoAnexo] - Error en bbdd", e);
		}finally{
			this.closeSQLObject(call);
		}
		return idAnexo.intValue();
	}

	/**
	 * 
	 * @param idMensaje
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer asociarAnexo(Integer idMensaje, Integer idAdjunto,
			String usuario, String password) {
		BigDecimal confirmacion = null;
		CallableStatement call = null;

		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_ASOCIAR_ANEXO);

			if (idMensaje != null) {
				call.setInt(1, new Integer(idMensaje));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setInt(2, idAdjunto);
			call.setString(3, usuario);
			call.setString(4, password);
			call.registerOutParameter(5, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(5);

		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.asociaAnexo] - Error en bbdd", ec);
			confirmacion= new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}

	/**
	 * Crea un nuevo mensaje asociado al idLote correspondiente y devuelve el
	 * identificador del SMS creado
	 * 
	 * @param idLote
	 *            - Identificador del lote al que se asociar� el SMS
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param codOrganismoPagador
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Mensaje crearSMS(Integer idLote, String cuerpo, String docUsuario,
			String codSIA, String codOrganismo, String codOrganismoPagador,
			String idExterno, String destinatario, String usuario,
			String password) {
		Mensaje mensaje = new Mensaje();
		BigDecimal idSMS = null;
		CallableStatement call = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s ="";
		
		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE_SMS);
			if (idLote != null) {
				call.setInt(1, new Integer(idLote));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, cuerpo);
			call.setString(3, docUsuario);
			call.setString(4, codSIA);
			call.setString(5, codOrganismo);
			call.setString(6, codOrganismoPagador);
			call.setString(7, idExterno);
			call.setString(8, destinatario);
			call.setString(9, usuario);
			call.setString(10, password);
			call.registerOutParameter(11, OracleTypes.NUMBER);
			call.executeUpdate();
			idSMS = (BigDecimal) call.getObject(11);

			if (idSMS.intValue() > 0) {
				conn = this.getConn();
				s = GET_MENSAJE;
				pst = conn.prepareStatement(s);
				pst.setInt(1, idSMS.intValue());
				rs = pst.executeQuery();

				if (rs.next()) {
					mensaje.setIdMensaje(idSMS + "");
					if (rs.getString(1) != null) {
						mensaje.setIdExterno(rs.getString(1));
					} else {
						mensaje.setIdExterno("");
					}
				}
			} else {
				if (WSPlataformaErrors.getErrorCrearSMS(idSMS.intValue()) != null) {
					ResponseStatusType status = new ResponseStatusType();
					status.setStatusCode(STATUSCODE_KO);
					status.setStatusCode(STATUSTEXT_KO);
					status.setDetails(WSPlataformaErrors.getErrorCrearSMS(idSMS
							.intValue()));
					mensaje.setErrorMensaje(status);
				}
			}

		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.crearSMS] crearSMS - Error en bbdd", ec);
			ec.printStackTrace();
			idSMS = new BigDecimal(-10); // error base de datos
			ResponseStatusType status = new ResponseStatusType();
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusCode(STATUSTEXT_KO);
			status.setDetails(STATUSERROR);
			mensaje.setErrorMensaje(status);

		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(call);
			this.closeSQLObject(pst);
		}	
		return mensaje;

	}

	/**
	 * Elimina un mensaje y devuelve el c�digo de confimraci�n (Ver
	 * documentaci�n para identificar los posibles c�digos de confirmaci�n
	 * 
	 * @param idSMS
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer eliminarSMS(Integer idSMS, String usuario, String password) {
		BigDecimal confirmacion = null;
		CallableStatement call =null;
		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_MENSAJE_SMS);
			if (idSMS != null) {
				call.setInt(1, new Integer(idSMS));
			} else {
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();

			confirmacion =  (BigDecimal) call.getObject(4);
			
		}catch(SQLException ec){
			logger.debug("[EnvioMensajesDAO.eliminarSMS] - Error en bbdd", ec);
			confirmacion=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}

	public Integer comprobarEnvioEmail(Integer idMensaje, String usuario,
			String password) {
		BigDecimal confirmacion = new BigDecimal(1);
		CallableStatement call =null;
		try{ 

			call = this.getConn().prepareCall(PROCEDIMIENTO_COMPROBAR_ENVIO);
			if (idMensaje != null) {
				call.setInt(1, new Integer(idMensaje));
			} else {
				return 0;
			}
			call.setString(2, usuario);
			call.setString(3, password);
			// call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			//confirmacion =  (BigDecimal) call.getObject(4);

		}catch(SQLException ec){

			logger.debug("[EnvioMensajesDAO.comprobarEnvioEmail] - Error en bbdd", ec);
			confirmacion=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}

	public void setDestinatarioMensajeSMS(String mensajeId, String destinatario,
			String codExterno, String codUsuario){

		Statement stmt = null;
		Connection conn = null;
		ResultSet rs= null;
		String s = null;
		String idExterno = null;
		Integer idDestinatariosMensajes = null;
		try {
			if (null != codExterno)
				idExterno = "'" + codExterno + "'";
			conn = this.getConn();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			s = "INSERT INTO tbl_destinatarios_mensajes"
					+ " (destinatariosmensajes, mensajeId,destinatario,estado,fechacreacion,creadopor,codigoExterno) "
					+ "VALUES(DESTINATARIOSMENSAJES_SEC.NEXTVAL,"
					+ Long.parseLong(mensajeId) + ",'" + destinatario + "','"
					+ ESTADOINICIAL + "',sysdate,'" + codUsuario + "',"
					+ idExterno + ")";
			
			stmt.executeUpdate(s, new int[] {1});
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				BigDecimal a =(BigDecimal) rs.getObject(1); 
				idDestinatariosMensajes = a.intValue();
			}
			//insertamos en TBL_HISTORICOS
			stmt.close();
			rs.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid, destinatariosmensajes)"
					+ "VALUES (HISTORICOID_SEC.NEXTVAL,"+ mensajeId+", sysdate , 3,"+idDestinatariosMensajes+")";
			rs = stmt.executeQuery(s);
			
			stmt.close();
			rs.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid, destinatariosmensajes)"
					+ "VALUES (HISTORICOID_SEC.NEXTVAL,"+ mensajeId+", sysdate , 9,"+idDestinatariosMensajes+")";
			rs = stmt.executeQuery(s);
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.comprobarEnvioEmail] - Error en bbdd", e);
		}finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}

	public ArrayList<Long> getListaTblDestinatarios(String idMensaje) {
		ArrayList<Long> res = new ArrayList<Long>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.getConn();

			pst = conn.prepareStatement(GET_DESTINATARIOS_MENSAJES_EMAIL);
			pst.setInt(1, Integer.parseInt(idMensaje));
			rs = pst.executeQuery();

			while (rs.next()) {
				res.add(rs.getLong("DESTINATARIOID"));

			}

		} catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.getListaTblDestinatarios] - Error en bbdd", e);
		} catch (NumberFormatException e) {
			logger.debug("[EnvioMensajesDAO.getListaTblDestinatarios] - MensajeId Incorrecto:" +idMensaje , e);
		}finally {
			this.closeSQLObject(pst);
			this.closeSQLObject(rs);
		}

		return res;
	}

	
	public ArrayList<Long> getListaDestinatariosInsertados(String idMensaje, String to, String cc, String bcc, String usuario) {
		ArrayList<Long> res = new ArrayList<Long>();
		Connection conn = null;
			conn = this.getConn();
			if (null != to && to.length() > 0){
				res.addAll(insertarDestinatarios(conn, to,  idMensaje, usuario, "TO"));
			}
			if (null != cc && cc.length() > 0){
				res.addAll(insertarDestinatarios(conn, cc,  idMensaje, usuario, "CC"));
			}
			if (null != bcc && cc.length() > 0){
				res.addAll(insertarDestinatarios(conn, bcc,  idMensaje, usuario, "BCC"));
			}

		return res;
	}
	
	
	private ArrayList<Long> insertarDestinatarios(Connection conn, String destinatarios, String idMensaje, String usuario, String tipoDestinatario){ 
		ArrayList<Long> res = new ArrayList<Long>();
		String[] cadena;
		String s = "";
		Statement st = null;
		ResultSet rs = null;
		try{
		
			cadena = destinatarios.split(";");
			for (String email : cadena) {
				if(Utils.validarEmail(email)){
					s = "INSERT INTO tbl_destinatarios"
							+ " (destinatarioid, email, mensajeId,fechacreacion,creadopor,tipodestinatario) "
							+ "VALUES(DESTINATARIOID_SEC.NEXTVAL,'"+email+"',"
							+ Long.parseLong(idMensaje) + ",sysdate,'"
							+ usuario + "','"+tipoDestinatario+"')";
	
					st = conn.createStatement();
					st.executeUpdate(s, new int[] {1});
					rs = st.getGeneratedKeys();
					if (rs.next()){
						BigDecimal a =(BigDecimal) rs.getObject(1); 
						res.add(a.longValue());
					}
				}	
			}	
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.insertarDestinatarios] - Error en bbdd", e);
		}finally {
			this.closeSQLObject(st);
			this.closeSQLObject(rs);
		}
		return res;
	}
	
	public void setDestinatarioMensajeMail(String mensajeId,
			ArrayList<Long> listaTblDestinatarios, String codExterno, String codUsuario)
			throws Exception {

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
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			for (Long dest : listaTblDestinatarios) {
				s = "INSERT INTO tbl_destinatarios_mensajes"
						+ " (destinatariosmensajes, mensajeId,destinatario,estado,fechacreacion,creadopor,codigoExterno) "
						+ "VALUES(DESTINATARIOSMENSAJES_SEC.NEXTVAL,"
						+ Long.parseLong(mensajeId) + ",'" + dest + "','"
						+ ESTADOINICIAL + "',sysdate,'" + codUsuario + "',"
						+ idExterno + ")";
				stmt.executeUpdate(s, new int[] {1});
				rs = stmt.getGeneratedKeys();
				if (rs.next()){
					BigDecimal a =(BigDecimal) rs.getObject(1); 
					idDestinatariosMensajes = a.intValue();
				}
				//insertamos en TBL_HISTORICOS
				stmt.close();
				rs.close();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				s = "INSERT INTO tbl_historicos(historicoid,mensajeid,fecha,estadoid, destinatariosmensajes)"
						+ "VALUES (HISTORICOID_SEC.NEXTVAL,"+ mensajeId+", sysdate , 3,"+idDestinatariosMensajes+")";
				rs = stmt.executeQuery(s);
			}
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.setDestinatarioMensajeMail] - Error en bbdd", e);
		}finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}
	
}
