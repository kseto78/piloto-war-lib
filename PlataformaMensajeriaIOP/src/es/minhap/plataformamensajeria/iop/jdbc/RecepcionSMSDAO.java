package es.minhap.plataformamensajeria.iop.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.RecepcionSMSBean;

/**
 * 
 * @author jgonzvil
 *
 */
public class RecepcionSMSDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(RecepcionSMSDAO.class);
	private static String ESTADOINICIAL = "PENDIENTE DE ENVIO";
	
	private static final String PROCEDIMIENTO_BUSCAR_INFO_LOTE = "{call PKG_RECEPCION_SMS.BUSCARINFOLOTEENVIO(?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_LOTE = "{call PKG_ENVIOS_GENERAL.CREARLOTEMULT(?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_SMS="{call PKG_RECEPCION_SMS.CREARMENSAJESMSLOTES(?,?,?,?,?,?,?)}";
	
	/**
	 *  
	 * @param recipiente
	 * @return 
	 */
	public RecepcionSMSBean consultarLote(String recipiente, String usuario, String password){
		RecepcionSMSBean aux = new RecepcionSMSBean();
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_BUSCAR_INFO_LOTE);
			
			call.setString(1, recipiente);
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.VARCHAR);
			call.registerOutParameter(5, OracleTypes.VARCHAR);
			call.registerOutParameter(6, OracleTypes.VARCHAR);
			call.registerOutParameter(7, OracleTypes.VARCHAR);
			call.executeUpdate();
			
			aux.setServicio((String) call.getObject(4));
			aux.setNombreLote((String) call.getObject(5));
			aux.setUserAplicacion((String) call.getObject(6));
			aux.setPasswordAplicacion((String) call.getObject(7));
			
		}catch (SQLException e) {
			logger.debug("[RecepcionSMSDAO.consultarLote] - Error en bbdd", e);
			aux = null;
		}finally{
			this.closeSQLObject(call);
		}
		return aux;
	}
	
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
			logger.debug("[RecepcionSMSDAO.crearLote] - Error en bbdd", e);
			idLote=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return idLote.intValue();
	}
	
	/**
	 * Crea un nuevo mensaje asociado al idLote correspondiente y devuelve el identificador del SMS creado
	 * @param idLote - Identificador del lote al que se asociar el SMS
	 * @param cuerpo
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearSMS(Integer idLote, String cuerpo, String idExterno, String destinatario, String usuario, String password){
		BigDecimal idSMS = null;
		CallableStatement call = null;
		try{
			call = this.getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE_SMS);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, cuerpo);
		    call.setString(3, idExterno);
		    call.setString(4, destinatario);
		    call.setString(5, usuario);
		    call.setString(6, password);
		    call.registerOutParameter(7, OracleTypes.NUMBER);
			call.executeUpdate();
			
			idSMS =  (BigDecimal) call.getObject(7);
		}catch(SQLException ec){
			logger.debug("RecepcionSMSDAO.crearSMS - Error en bbdd", ec);
			idSMS=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}	
		return idSMS.intValue();
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
		} catch (SQLException e) {
			logger.debug("[EnvioMensajesDAO.comprobarEnvioEmail] - Error en bbdd", e);
		}finally {
			this.closeSQLObject(stmt);
			this.closeSQLObject(rs);
		}
	}
	
}
