package es.mpr.plataformamensajeria.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;

/**
 * 
 * @author i-nercya
 *
 */
public class OperacionesMensajesDAO extends PlataformaWSDAO {
	static Logger logger = Logger.getLogger(OperacionesMensajesDAO.class);
	private static final String PROCEDIMIENTO_OPERACION_REENVIARLOTE = "{call PKG_OPERACIONES_SEGUIMIENTO.REENVIARLOTE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_ANULARLOTE = "{call PKG_OPERACIONES_SEGUIMIENTO.ANULARLOTE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_REENVIARMENSAJE = "{call PKG_OPERACIONES_SEGUIMIENTO.REENVIARMENSAJE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_OPERACION_ANULARMENSAJE = "{call PKG_OPERACIONES_SEGUIMIENTO.ANULARMENSAJE(?,?,?,?)}";
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
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_OPERACION_REENVIARLOTE);
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
			endTransaction(true);
		}catch (SQLException e) {
			logger.error("[reenviarLote] - Error en bbdd",e);
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
			beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_OPERACION_ANULARLOTE);
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
			endTransaction(true);
		}catch (SQLException e) {
			logger.error("[anularLote] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
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
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_OPERACION_REENVIARMENSAJE);
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
			endTransaction(true);
		}catch (SQLException e) {
			logger.error("[reenviarMensaje] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
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
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_OPERACION_ANULARMENSAJE);
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
			endTransaction(true);
		}catch (SQLException e) {
			logger.error("[anularMensaje] - Error en bbdd",e);
			confirmacion=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
}
